/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.informe.servicio;

import com.saviasaludeps.savia.dominio.informe.InfProcedimiento;
import com.saviasaludeps.savia.dominio.informe.InfRoutine;
import com.saviasaludeps.savia.negocio.informe.InformeProcedimientosRemoto;
import com.saviasaludeps.savia.negocio.informe.SPRemoto;
import com.saviasaludeps.savia.web.informe.bean.ProcedimientosBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author aguevara
 */
public class ProcedimientosServicioImpl extends AccionesBO implements ProcedimientosServicioIface {

    private InformeProcedimientosRemoto getProcedimientosRemoto() throws Exception {
        return (InformeProcedimientosRemoto) RemotoEJB.getEJBRemoto("InformeProcedimientoServicio", InformeProcedimientosRemoto.class.getName());
    }
    
    private SPRemoto getSPRemoto() throws Exception {
        return (SPRemoto) RemotoEJB.getEJBRemotoExterno("InfProcedimientoServicio", SPRemoto.class.getName());
    }

    @Override
    public void Accion(ProcedimientosBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verHistorial(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(ProcedimientosBean bean) {
        try {
            System.out.println("Carga inicial ejecutada correctamente.");
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void listar(ProcedimientosBean bean) {
        try {
            bean.getParamConsultaGrupo().setCantidadRegistros(getProcedimientosRemoto().consultarCantidadLista(bean.getParamConsultaGrupo()));
            bean.setRegistros(getProcedimientosRemoto().consultarLista(bean.getParamConsultaGrupo()));
            
            bean.getParamConsultaInfRoutine().setCantidadRegistros(getSPRemoto().consultarCantidadLista(bean.getParamConsultaInfRoutine()));
            bean.setRegistrosRoutines(getSPRemoto().consultarLista(bean.getParamConsultaInfRoutine()));
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void ver(ProcedimientosBean bean) {
        try {
            bean.setObjetoInfRoutine(getSPRemoto().consultar(bean.getObjetoInfRoutine().getSpecificName()));
            if (bean.getObjetoInfRoutine().getRoutineDefinition()!= null) {              
                bean.setScriptTexto(bean.getObjetoInfRoutine().getRoutineDefinition());
            } else {
                bean.setScriptTexto(null);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(ProcedimientosBean bean) {
        try {
            bean.setObjetoInfRoutine(new InfRoutine());
            bean.setObjeto(new InfProcedimiento());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(ProcedimientosBean bean) {
    String nombre = null;
    try {
        bean.auditoriaGuardar(bean.getObjeto());

        // 1) Nombre obligatorio (viene del input "Nombre Procedimiento")
        nombre = Optional.ofNullable(bean.getObjetoInfRoutine().getRoutineName())
                .map(String::trim).orElse("");
        if (nombre.isEmpty()) {
            bean.addError("El nombre del procedimiento es obligatorio.");
            return;
        }
        bean.getObjeto().setNombreScript(nombre);

        // 2) Validar duplicados en ROUTINES y en inf_procedimientos (sin traer listas completas)
        boolean existeRoutine = getSPRemoto().existeRoutinePorNombre(nombre);
        if (existeRoutine) {
            bean.addError("Ya existe un procedimiento SQL con el nombre: " + nombre);
            return;
        }
        InfProcedimiento ya = getProcedimientosRemoto().consultarPorNombreScript(nombre);
        if (ya != null) {
            bean.addError("Ya existe un registro en la tabla inf_procedimientos con el nombre: " + nombre);
            return;
        }

        // 3) Ejecutar creación del SP
        getSPRemoto().insertar(bean.getObjetoInfRoutine());

        // 4) Guardar fila en inf_procedimientos (script en bytes; puedes guardar el DDL o el cuerpo original)
        if (bean.getObjetoInfRoutine().getRoutineDefinition() != null) {
            bean.getObjeto().setScript(bean.getObjetoInfRoutine().getRoutineDefinition().getBytes(StandardCharsets.UTF_8));
        } else {
            bean.getObjeto().setScript(null);
        }
        bean.getObjeto().setExitoso(true);

        getProcedimientosRemoto().insertar(bean.getObjeto());

        bean.addMensaje("Se ha creado el registro de manera exitosa");
    } catch (Exception ex) {
        // Compensación: si alcanzamos a crear el SP pero falló la fila, intenta eliminar el SP
        try {
            if (nombre != null && !nombre.isBlank()) {
                getSPRemoto().eliminar(nombre);
            }
        } catch (Exception ign) { /* noop */ }
        bean.addError("Error al guardar: " + ex.getMessage());
    }
}


    private void editar(ProcedimientosBean bean) {
        try {
            bean.setObjeto(new InfProcedimiento());
            bean.setObjetoInfRoutine(getSPRemoto().consultar(bean.getObjetoInfRoutine().getSpecificName()));
            bean.getObjetoInfRoutine().setRoutineDefinition("");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(ProcedimientosBean bean) {
    String nombre = null;
    byte[] scriptAnterior = bean.getObjeto().getScript(); // para rollback
    try {
       

        nombre = Optional.ofNullable(bean.getObjetoInfRoutine().getRoutineName())
                .orElse(bean.getObjetoInfRoutine().getSpecificName());
        if (nombre == null || nombre.isBlank()) {
            bean.addError("El nombre del procedimiento es obligatorio.");
            return;
        }
        bean.getObjeto().setNombreScript(nombre);

        // 1) Actualizar SP en servidor (DROP + CREATE internamente)
        getSPRemoto().actualizar(bean.getObjetoInfRoutine());

        // 2) Persistir cambios en la fila (guardar el script actual en bytes)
        if (bean.getObjetoInfRoutine().getRoutineDefinition() != null) {
            bean.getObjeto().setScript(bean.getObjetoInfRoutine().getRoutineDefinition().getBytes(StandardCharsets.UTF_8));
        } else {
            bean.getObjeto().setScript(null);
        }
        
        String schema = PropApl.getInstance().get(PropApl.GN_ESQUEMA_AMBIENTE);
        String name = bean.getObjetoInfRoutine().getRoutineName();
        
        String drop = "DROP PROCEDURE IF EXISTS `" + schema + "`.`" + name + "`";
        String script  = drop + "\n" + bean.getObjetoInfRoutine().getRoutineDefinition();
        
        bean.auditoriaGuardar(bean.getObjeto());
        
        //primero inserta el registro en inf_procedimiento
        bean.getObjeto().setNombreScript(bean.getObjetoInfRoutine().getRoutineName());
        bean.getObjeto().setExitoso(true);
        bean.getObjeto().setScript(script.getBytes(StandardCharsets.UTF_8));
        getProcedimientosRemoto().insertar(bean.getObjeto());

        bean.addMensaje("Se actualizó un registro de manera exitosa");
    } catch (Exception ex) {
        // Compensación: si falla la fila, intenta restaurar el SP anterior con el scriptAnterior
        try {
            if (nombre != null && scriptAnterior != null) {
                InfRoutine backup = new InfRoutine();
                backup.setRoutineName(nombre);
                backup.setRoutineDefinition(new String(scriptAnterior, StandardCharsets.UTF_8));
                getSPRemoto().actualizar(backup); // vuelve a la versión previa
            }
        } catch (Exception ign) { /* noop */ }
        bean.addError("Error al actualizar: " + ex.getMessage());
    }
}


    private void borrar(ProcedimientosBean bean) {
        try {
            bean.setObjeto(getProcedimientosRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError("Hubo un error eliminando, favor contactar con el administrador");
        }
    }

    private void verHistorial(ProcedimientosBean bean) {
         try {
            bean.setRegistros(getProcedimientosRemoto().consultarTodosPorNombre(bean.getObjetoInfRoutine().getSpecificName()));
            if (bean.getObjeto().getScript() != null) {
                String texto = new String(bean.getObjeto().getScript(), StandardCharsets.UTF_8);
                bean.setScriptTexto(texto);
            } else {
                bean.setScriptTexto(null);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

}
