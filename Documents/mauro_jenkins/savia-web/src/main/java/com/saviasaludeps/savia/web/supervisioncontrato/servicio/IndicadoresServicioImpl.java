/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.supervisioncontrato.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.supervisioncontrato.ScIndicador;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.supervisionContrato.IndicadoresRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.supervisioncontrato.bean.IndicadoresBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.Date;

/**
 *
 * @author aguevara
 */
public class IndicadoresServicioImpl extends AccionesBO implements IndicadoresServicioIface{
    
    
    private IndicadoresRemoto getIndicadoresRemoto() throws Exception {
        return (IndicadoresRemoto) RemotoEJB.getEJBRemoto("IndicadorServicio", IndicadoresRemoto.class.getName());
    }
    
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    @Override
    public void Accion(IndicadoresBean bean) {
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
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
        @Override
    public void cargasInicial(IndicadoresBean bean) {
        try {
            bean.setListaAreas(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_AREA));
            bean.setHashAreas(AuConstantes.obtenerHashMaestro(bean.getListaAreas()));
            
            bean.setListaIndicadorClase(getMaestroRemoto().consultarPorTipo(MaestroTipo.SC_INDICADOR_CLASES));
            bean.setHashIndicadorClase(AuConstantes.obtenerHashMaestro(bean.getListaIndicadorClase()));
            
            bean.setListaIndicadorMacroproceso(getMaestroRemoto().consultarPorTipo(MaestroTipo.SC_INDICADOR_MACROPROCESOS));
            bean.setHashIndicadorMacroproceso(AuConstantes.obtenerHashMaestro(bean.getListaIndicadorMacroproceso()));
            
            bean.setListaIndicadorProceso(getMaestroRemoto().consultarPorTipo(MaestroTipo.SC_INDICADOR_PROCESOS));
            bean.setHashIndicadorProceso(AuConstantes.obtenerHashMaestro(bean.getListaIndicadorProceso()));
           
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }

    }

    private void listar(IndicadoresBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getIndicadoresRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getIndicadoresRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(IndicadoresBean bean) {
        try {
            bean.setObjeto(getIndicadoresRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(IndicadoresBean bean) {
        try {
            bean.setObjeto(new ScIndicador());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(IndicadoresBean bean) {
        try {
            bean.setObjeto(getIndicadoresRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(IndicadoresBean bean) {
        try {
            
            Maestro clase = bean.getHashIndicadorClase().get(bean.getObjeto().getMaeClaseId());
            bean.getObjeto().setMaeClaseCodigo(clase.getValor());
            bean.getObjeto().setMaeClaseValor(clase.getNombre());
            bean.getObjeto().setMaeClaseTipo(clase.getTipo());
            
            Maestro macroProceso = bean.getHashIndicadorMacroproceso().get(bean.getObjeto().getMaeMacroprocesoId());
            bean.getObjeto().setMaeMacroprocesoCodigo(macroProceso.getValor());
            bean.getObjeto().setMaeMacroprocesoValor(macroProceso.getNombre());
            bean.getObjeto().setMaeMacroprocesoTipo(macroProceso.getTipo());
            
            Maestro proceso = bean.getHashIndicadorProceso().get(bean.getObjeto().getMaeProcesoId());
            bean.getObjeto().setMaeProcesoCodigo(proceso.getValor());
            bean.getObjeto().setMaeProcesoValor(proceso.getNombre());
            bean.getObjeto().setMaeProcesoTipo(proceso.getTipo());
            
            Maestro area = bean.getHashAreas().get(bean.getObjeto().getMaeAreaId());
            bean.getObjeto().setMaeAreaCodigo(area.getValor());
            bean.getObjeto().setMaeAreaValor(area.getNombre());
            bean.getObjeto().setMaeAreaTipo(area.getTipo());
            
            bean.auditoriaGuardar(bean.getObjeto());
            bean.getObjeto().setId(getIndicadoresRemoto().insertar(bean.getObjeto()));
            bean.addMensaje("Se creo un registro de manera exitosa.");
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void modificar(IndicadoresBean bean) {
        try {
            Maestro clase = bean.getHashIndicadorClase().get(bean.getObjeto().getMaeClaseId());
            bean.getObjeto().setMaeClaseCodigo(clase.getValor());
            bean.getObjeto().setMaeClaseValor(clase.getNombre());
            bean.getObjeto().setMaeClaseTipo(clase.getTipo());
            
            Maestro macroProceso = bean.getHashIndicadorMacroproceso().get(bean.getObjeto().getMaeMacroprocesoId());
            bean.getObjeto().setMaeMacroprocesoCodigo(macroProceso.getValor());
            bean.getObjeto().setMaeMacroprocesoValor(macroProceso.getNombre());
            bean.getObjeto().setMaeMacroprocesoTipo(macroProceso.getTipo());
            
            Maestro proceso = bean.getHashIndicadorProceso().get(bean.getObjeto().getMaeProcesoId());
            bean.getObjeto().setMaeProcesoCodigo(proceso.getValor());
            bean.getObjeto().setMaeProcesoValor(proceso.getNombre());
            bean.getObjeto().setMaeProcesoTipo(proceso.getTipo());
            
            Maestro area = bean.getHashAreas().get(bean.getObjeto().getMaeAreaId());
            bean.getObjeto().setMaeAreaCodigo(area.getValor());
            bean.getObjeto().setMaeAreaValor(area.getNombre());
            bean.getObjeto().setMaeAreaTipo(area.getTipo());
            
            bean.auditoriaModificar(bean.getObjeto());
            getIndicadoresRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa.");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(IndicadoresBean bean) {
        try {
            bean.getObjeto().setBorrado(true);
            bean.getObjeto().setUsuarioBorra(bean.getConexion().getUsuario().getUsuarioNombre());
            bean.getObjeto().setTerminalBorra(bean.getConexion().getIp());
            bean.getObjeto().setFechaHoraBorra(new Date());
            
            bean.auditoriaModificar(bean.getObjeto());            
            getIndicadoresRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se eliminó un registro de manera exitosa.");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }


}
