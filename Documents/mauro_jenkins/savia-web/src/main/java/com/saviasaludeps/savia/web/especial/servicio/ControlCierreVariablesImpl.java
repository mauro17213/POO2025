/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.especial.servicio;

import com.saviasaludeps.savia.dominio.especial.PeCierreCarga;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.negocio.especial.PeControlCierreVariablesRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.web.especial.bean.ControlCierreVariablesBean;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jdlopez
 */
public class ControlCierreVariablesImpl extends AccionesBO implements ControlCierreVariablesIface {
    
    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }
    
    private PeControlCierreVariablesRemoto getPeControlCierreVariableRemoto() throws Exception {
        return (PeControlCierreVariablesRemoto) RemotoEJB.getEJBRemoto("PeControlCierreVariableServicio", PeControlCierreVariablesRemoto.class.getName());
    }

    @Override
    public void Accion(ControlCierreVariablesBean bean) {
         if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    this.listar(bean);
                    break;
                case Url.ACCION_VER:
                    this.ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    this.crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    this.guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    this.editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    this.modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
//                    this.borrar(bean);
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(ControlCierreVariablesBean bean) {
        try {
            bean.setListaMeses(PeConstantes.listaMeses());
            bean.setListaPeriodoCarga(PeConstantes.listaPeriodoCargaMasivaVariable());
            PeCierreCarga cierreCarga = new PeCierreCarga();
            cierreCarga.setPrograma(new PePrograma());
            bean.setObjeto(cierreCarga);
            bean.setProgramas(List.of(getPeProgramaRemoto().consultar(PeConstantes.ID_PROGRAMA_RIESGO_CARDIOVASCULAR)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listar(ControlCierreVariablesBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getPeControlCierreVariableRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getPeControlCierreVariableRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crear(ControlCierreVariablesBean bean) {
        try {
            PeCierreCarga cierreCarga = new PeCierreCarga();
            cierreCarga.setPrograma(new PePrograma());
            bean.setObjeto(cierreCarga);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardar(ControlCierreVariablesBean bean) {
        try {
            if (!this.validarGuardar(bean)) {
                return;
            }
            bean.auditoriaGuardar(bean.getObjeto());
            getPeControlCierreVariableRemoto().insertar(bean.getObjeto());
            bean.addMensaje("Se creo un registro de manera exitosa. ");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(ControlCierreVariablesBean bean) {
         try {
            bean.setObjeto(this.consultarCierrePorId(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void editar(ControlCierreVariablesBean bean) {
        try {
            bean.setObjeto(this.consultarCierrePorId(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private PeCierreCarga consultarCierrePorId(Integer id) throws Exception {
        return getPeControlCierreVariableRemoto().consultar(id);
    }

    private void modificar(ControlCierreVariablesBean bean) {
         try {
            if (!this.validarActualizar(bean)) {
                return;
            }
            bean.auditoriaModificar(bean.getObjeto());
            getPeControlCierreVariableRemoto().actualizar(bean.getObjeto());
            bean.addMensaje("Se actualizó un registro de manera exitosa. ");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean validarGuardar(ControlCierreVariablesBean bean) throws Exception {
        
        Date fechaActual = new Date();
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        if (!this.validarFechaCierre(bean, fechaActual, sdf)) {
            bean.getObjeto().setFechaHoraDesde(null);
            return false;
        }
        
        if (!this.validarFechaApertura(bean, sdf)) {
            bean.getObjeto().setFechaHoraHasta(null);
            return false;
        }
        
        if (!this.validarFechaCierreApertura(bean)) {
            return false;
        }
        
        PeCierreCarga cierreConsultado = getPeControlCierreVariableRemoto().consultarIdProgramaPeriodo(bean.getObjeto().getPrograma().getId(), bean.getObjeto().getPeriodo());
        if (Objects.nonNull(cierreConsultado)) { //validamos si no existe otro cierre del mismo programa con el mismo periodo
            bean.addError("Error: Ya existe un registro de cierre en el programa para el mismo periodo. ");
            return false;
        }

        return true;

    }

    private boolean validarFechaCierre(ControlCierreVariablesBean bean, Date fechaActual, DateFormat sdf) throws Exception {
        if (bean.getObjeto().getFechaHoraDesde().before(fechaActual)) {
            bean.addError("Error: La fecha de cierre debe ser mayor o igual a la fecha actual.(" + sdf.format(fechaActual) + ")");
            return false;
        }
        return true;
    }

    private boolean validarFechaApertura(ControlCierreVariablesBean bean, DateFormat sdf) {

        if (bean.getObjeto().getFechaHoraHasta().before(bean.getObjeto().getFechaHoraDesde())) {
            bean.addError("Error: La fecha de apertura debe ser mayor o igual a la fecha de cierre.(" + sdf.format(bean.getObjeto().getFechaHoraDesde()) + ")");
            return false;
        }
        return true;
    }
    

    private boolean validarFechaCierreApertura(ControlCierreVariablesBean bean) {
        if (bean.getObjeto().getFechaHoraDesde().after(bean.getObjeto().getFechaHoraHasta())) {
            bean.addError("Error: La fecha y hora de cierre no debe ser mayor que la fecha de apertura.");
            return false;
        }
        return true;
    }

    private boolean validarActualizar(ControlCierreVariablesBean bean) throws Exception {
        Date fechaActual = new Date();
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        if (bean.getObjeto().getId() != null) {
            PeCierreCarga consulta = getPeControlCierreVariableRemoto().consultar(bean.getObjeto().getId());
            String fechaCierreConsulta = sdf.format(consulta.getFechaHoraDesde());
            String fechaCierreActualizacion = sdf.format(bean.getObjeto().getFechaHoraDesde());

            if (!fechaCierreConsulta.equals(fechaCierreActualizacion)) {//si no son iguales es necesario mirar las otras validaciones
                if (!this.validarFechaCierre(bean, fechaActual, sdf)) {
                    bean.getObjeto().setFechaHoraDesde(null);
                    return false;
                }
            }
        }
        
        
        if (!this.validarFechaApertura(bean, sdf)) {
            bean.getObjeto().setFechaHoraHasta(null);
            return false;
        }
        
        return this.validarFechaCierreApertura(bean);
    }
    
}
