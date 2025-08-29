/*
 * To change this license header, choose License Headers in Project Proobjties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.anticipo.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoAdjunto;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoGestion;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.anticipo.ReporteAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.ReporteAnticipoItem;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoGestionRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoItemRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoValorRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.web.anticipo.bean.AnticipoBean;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import com.sun.mail.util.BASE64DecoderStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author raul-palacios
 */
public class AnticipoServicioImpl extends AccionesBO implements AnticipoIface {

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }
    
    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }
    
    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }
    
    private AnticipoRemoto getAnticipoRemoto() throws Exception {
        return (AnticipoRemoto) RemotoEJB.getEJBRemoto("AnticipoServicio", AnticipoRemoto.class.getName());
    }
    
    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }
    
    private AnticipoGestionRemoto getAnticipoGestionRemoto() throws Exception {
        return (AnticipoGestionRemoto) RemotoEJB.getEJBRemoto("AnticipoGestionServicio", AnticipoGestionRemoto.class.getName());
    }
    
    private AnticipoItemRemoto getAnticipoItemRemoto() throws Exception {
        return (AnticipoItemRemoto) RemotoEJB.getEJBRemoto("AnticipoItemServicio", AnticipoItemRemoto.class.getName());
    }
    
    private AnticipoAdjuntoRemoto getAnticipoAdjuntoRemoto() throws Exception {
        return (AnticipoAdjuntoRemoto) RemotoEJB.getEJBRemoto("AnticipoAdjuntoServicio", AnticipoAdjuntoRemoto.class.getName());
    }
    
    private AnticipoValorRemoto getAnticipoValorRemoto() throws Exception {
        return (AnticipoValorRemoto) RemotoEJB.getEJBRemoto("AnticipoValorServicio", AnticipoValorRemoto.class.getName());
    }
    
    private AuCotizacionRemoto getAuCotizacionRemoto() throws Exception {
        return (AuCotizacionRemoto) RemotoEJB.getEJBRemoto("AuCotizacionServicio", AuCotizacionRemoto.class.getName());
    }
    
    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
        return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto("AuAnexo4Servicio", AuAnexo4Remoto.class.getName());
    }
    
    @Override
    public void Accion(AnticipoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verItem(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verGestion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            ventanaAnticipoValor(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            switch(bean.getTakeAccion()){
                                case Url.ACCION_VER:
                                    verItem(bean);
                                    break;
                                case Url.ACCION_EDITAR:
                                    editarItem(bean);
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    modificarItem(bean);
                                    break;
                                case Url.ACCION_BORRAR:
                                    borrarItem(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verGestion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            ventanaAnticipoValor(bean);
                            break;
                    }
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            gestionar(bean);
                            break;
                        case Url.ACCION_CREAR:
                            switch(bean.getTakeAccion()){
                                case Url.ACCION_CREAR:
                                    crearGestiones(bean);
                                    break;
                                case Url.ACCION_GUARDAR:
                                    guardarGestiones(bean);
                                    break;
                                case Url.ACCION_VER:
                                    verGestion(bean);
                                break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            switch(bean.getTakeAccion()){
                                case Url.ACCION_CREAR:
                                    crearAdjuntosCotizacion(bean);
                                    break;
                                case Url.ACCION_GUARDAR:
                                    guardarAnticipoAdjuntos(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            guardarGestionar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            switch(bean.getTakeAccion()){
                                case Url.ACCION_EDITAR:
                                    editarItem(bean);
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    modificarItem(bean);
                                    break;
                                case Url.ACCION_VER:
                                    verItem(bean);
                                    break;
                                case Url.ACCION_BORRAR:
                                    borrarItem(bean);
                                    break;
                                case Url.ACCION_GUARDAR:
                                    guardarItem(bean);
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            ventanaAnticipoValor(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_2:
                            break;
                        case Url.ACCION_GUARDAR:
                            generarReporteAnticipo(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_3:
                            devolver(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarDevolver(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_4:
                            ventanaAutorizacion(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarAutorizacion(bean);
                            break;
                    }
                    break;        
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_5:
                            //ventanaCancelar(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarCancelar(bean);
                            break;
                    }
                    break;
           
                case Url.ACCION_ADICIONAL_6:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_6:
                            ventanaPago(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarPago(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }
            cargas(bean);
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    private void listar(AnticipoBean bean) {
        try {
            if(!bean.getConexion().getEmpresa().isAdministradora()){
                bean.getParamConsulta().setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta().setCantidadRegistros(getAnticipoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getAnticipoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(AnticipoBean bean) {
        try {
            cargarMaestros(bean);
            bean.setObjeto(getAnticipoRemoto().consultar(bean.getObjeto().getId()));
            if(bean.getObjeto().getAsegAfiliadosId() != null){
                bean.getObjeto().setAsegAfiliadosId(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId()));
                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId());
                bean.getObjeto().getAsegAfiliadosId().setListaAsegAfiliadoContacto(afiliado.getListaAsegAfiliadoContacto());
            }
            if(bean.getObjeto().getCntPrestadoresId() != null){
                bean.getObjeto().setCntPrestadoresId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadoresId().getId()));
            }
            if(bean.getObjeto().getCntPrestadorSedesId() != null){
                bean.getObjeto().setCntPrestadorSedesId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedesId().getId()));
            }
            bean.getObjeto().setAntAnticipoGestionesList(getAnticipoGestionRemoto().consultarGestionPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setAntAnticipoItemsList(getAnticipoItemRemoto().consultarItemPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setAntAnticipoAdjuntosList(getAnticipoAdjuntoRemoto().consultarAdjuntoPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setAntAnticipoValoresList(getAnticipoValorRemoto().consultarPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setGnUsuariosId(getUsuarioRemoto().consultar(bean.getObjeto().getGnUsuariosId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verGestion(AnticipoBean bean) {
        try {
            bean.setAntAnticipoGestion(getAnticipoGestionRemoto().consultar(bean.getAntAnticipoGestion().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verItem(AnticipoBean bean) {
        try {
            bean.setObjetoItem(getAnticipoItemRemoto().consultar(bean.getObjetoItem().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void ventanaAnticipoValor(AnticipoBean bean) {
        try {
            bean.setObjetoAnticipoValor(getAnticipoValorRemoto().consultar(bean.getObjetoAnticipoValor().getId()));
            bean.getObjetoAnticipoValor().setAntAnticipoItemsId(getAnticipoItemRemoto().consultar(bean.getObjetoAnticipoValor().getAntAnticipoItemsId().getId()));
            bean.getObjetoAnticipoValor().setAuCotizacionesId(getAuCotizacionRemoto().consultar(bean.getObjetoAnticipoValor().getAuCotizacionesId().getId()));
            bean.getObjetoAnticipoValor().setAuAnexo4(getAuAnexo4Remoto().consultarByIdCotizacion(bean.getObjetoAnticipoValor().getAuCotizacionesId().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crear(AnticipoBean bean) {
        try {
            cargarMaestros(bean);
            bean.setObjeto(new AntAnticipo());
            bean.getObjeto().setPbs(Boolean.TRUE);
            bean.getObjeto().setGnUsuariosId(new Usuario());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crearGestiones(AnticipoBean bean) {
        try {
            bean.setAntAnticipoGestion(new AntAnticipoGestion());
            bean.getAntAnticipoGestion().setTipo(AntAnticipoGestion.TIPO_NOTA);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crearAdjuntosCotizacion(AnticipoBean bean) {
        try {
            AntAnticipo obj = getAnticipoRemoto().consultar(bean.getObjeto().getId());
            if (obj.getEstado().equals(AntAnticipo.ESTADO_GESTION_FIRMAS)) {
                bean.addError("No se puede agregar un afiliado");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void editar(AnticipoBean bean) {
        try {
            cargarMaestros(bean);
            bean.setObjeto(getAnticipoRemoto().consultar(bean.getObjeto().getId()));
         
            if (!bean.getObjeto().getEstado().equals(AntAnticipo.ESTADO_PENDIENTE_COTIZACION)) {
                bean.addError("No se puede editar el anticipo no esta en el estado pendiente cotización");
                return;
            }
       
            if(bean.getObjeto().getAsegAfiliadosId() != null){
                bean.getObjeto().setAsegAfiliadosId(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId()));
                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId());
                bean.getObjeto().getAsegAfiliadosId().setListaAsegAfiliadoContacto(afiliado.getListaAsegAfiliadoContacto());
            }
            if(bean.getObjeto().getCntPrestadoresId() != null){
                bean.getObjeto().setCntPrestadoresId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadoresId().getId()));
            }
            if(bean.getObjeto().getCntPrestadorSedesId() != null){
                bean.getObjeto().setCntPrestadorSedesId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedesId().getId()));
            }
            bean.getObjeto().setAntAnticipoGestionesList(getAnticipoGestionRemoto().consultarGestionPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setAntAnticipoItemsList(getAnticipoItemRemoto().consultarItemPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setAntAnticipoAdjuntosList(getAnticipoAdjuntoRemoto().consultarAdjuntoPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setAntAnticipoValoresList(getAnticipoValorRemoto().consultarPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setGnUsuariosId(getUsuarioRemoto().consultar(bean.getObjeto().getGnUsuariosId().getId()));
            bean.metodoHabilitarCampoRetencionSugerida();
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void editarItem(AnticipoBean bean) {
        try {
            bean.setObjetoItem(getAnticipoItemRemoto().consultar(bean.getObjetoItem().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void gestionar(AnticipoBean bean) {
        try {
            cargarMaestros(bean);
            bean.setObjeto(getAnticipoRemoto().consultar(bean.getObjeto().getId()));
            if(bean.getObjeto().getAsegAfiliadosId() != null){
                bean.getObjeto().setAsegAfiliadosId(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId()));
                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId());
                bean.getObjeto().getAsegAfiliadosId().setListaAsegAfiliadoContacto(afiliado.getListaAsegAfiliadoContacto());
            }
            if(bean.getObjeto().getCntPrestadoresId() != null){
                bean.getObjeto().setCntPrestadoresId(getPrestadorRemoto().consultar(bean.getObjeto().getCntPrestadoresId().getId()));
            }
            if(bean.getObjeto().getCntPrestadorSedesId() != null){
                bean.getObjeto().setCntPrestadorSedesId(getCntPrestadorSedeRemoto().consultar(bean.getObjeto().getCntPrestadorSedesId().getId()));
            }
            bean.getObjeto().setAntAnticipoGestionesList(getAnticipoGestionRemoto().consultarGestionPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setAntAnticipoItemsList(getAnticipoItemRemoto().consultarItemPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setAntAnticipoAdjuntosList(getAnticipoAdjuntoRemoto().consultarAdjuntoPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setAntAnticipoValoresList(getAnticipoValorRemoto().consultarPorAnticipoId(bean.getObjeto().getId()));
            bean.getObjeto().setGnUsuariosId(getUsuarioRemoto().consultar(bean.getObjeto().getGnUsuariosId().getId()));
            // habilidatar boton estionar
            if(bean.getObjeto().getEstado() == AntAnticipo.ESTADO_PENDIENTE_COTIZACION 
                    || bean.getObjeto().getEstado() == AntAnticipo.ESTADO_CON_COTIZACION){
                bean.getObjeto().setHabilitarGestionar(true);
            }
            bean.metodoHabilitarCampoRetencionSugerida();
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void devolver(AnticipoBean bean) {
        try {
            bean.setObjeto(getAnticipoRemoto().consultar(bean.getObjeto().getId()));
            if(bean.getObjeto().getEstado().equals(AntAnticipo.ESTADO_PAGADO)){
                bean.addError("No puede devolver");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void ventanaAutorizacion(AnticipoBean bean) {
        try {
            cargarMaestros(bean);
            bean.setObjeto(getAnticipoRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getAsegAfiliadosId() != null) {
                bean.getObjeto().setAsegAfiliadosId(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliadosId().getId()));
            }
            if(!bean.getObjeto().getEstado().equals(AntAnticipo.ESTADO_GESTION_FIRMAS)){
                bean.addError("No puede Autortizar el anticipo");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void ventanaPago(AnticipoBean bean) {
        try {
            bean.setObjeto(getAnticipoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setRetencionAplicada(bean.getObjeto().getRetencionSugerida());
            bean.getObjeto().setValorPagado(bean.getObjeto().getValorCotizado().subtract(bean.getObjeto().getValorCotizado().multiply(bean.getObjeto().getRetencionAplicada().divide(new BigDecimal(100)))));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    
    private void guardar(AnticipoBean bean) { 
        try {
            AntAnticipo obj = bean.getObjeto();
            bean.auditoriaGuardar(obj);
            obj.setEstado(AntAnticipo.ESTADO_PENDIENTE_COTIZACION);
            obj.setAplicaRetencion(Boolean.FALSE);
            //obj.setGnUsuariosId(bean.getConexion().getUsuario());
            if(obj.getCntPrestadorSedesId() != null){
                Empresa empresa = getEmpresaRemoto().consultarPorPrestador(obj.getCntPrestadorSedesId().getCntPrestador().getId());
                if (empresa != null) {
                    obj.setGnEmpresasId(empresa);
                } else {
                    obj.setGnEmpresasId(bean.getConexion().getEmpresa());
                }
            }else{
                obj.setGnEmpresasId(bean.getConexion().getEmpresa());
            }
            Maestro clase = bean.getHashClasificacion().get(obj.getMaeClasificacionId());
            if(clase != null){
                obj.setMaeClasificacionCodigo(clase.getValor());
                obj.setMaeClasificacionValor(clase.getNombre());
                obj.setMaeClasificacionTipo(clase.getTipo());
            }
            obj.setId(getAnticipoRemoto().insertar(obj));          
            registrarHistoricoGestiones(AntAnticipoGestion.TIPO_CAMBIO_ESTADO,  bean);
            bean.addMensaje("El anticipo se creo exitosamente " + obj.getId());
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarAutorizacion(AnticipoBean bean) { 
        try {
            AntAnticipo obj = bean.getObjeto();
            bean.auditoriaModificar(obj);
            obj.setEstado(AntAnticipo.ESTADO_AUTORIZADO);
            getAnticipoRemoto().actualizarEstadoProceso(obj);
            for(AntAnticipoAdjunto adjunto: bean.getListaAutorizarAntAdjuntos()){
                if(adjunto.getId() == null){
                    bean.auditoriaGuardar(adjunto);
                    adjunto.setAntAnticiposId(bean.getObjeto());
                    adjunto.setId(getAnticipoAdjuntoRemoto().insertar(adjunto));
                    generarArchivo(adjunto);
                }
            }
            registrarHistoricoAutorizar(AntAnticipoGestion.TIPO_CAMBIO_ESTADO, bean);
            bean.addMensaje("El autorizo exitosamente ");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarCancelar(AnticipoBean bean) { 
        try {
            AntAnticipo obj = bean.getObjeto();
            bean.auditoriaModificar(obj);
            obj.setEstado(AntAnticipo.ESTADO_CANCELADO);
            getAnticipoRemoto().actualizarEstadoProceso(obj);
            registrarHistoricoGestionesCancelar(AntAnticipoGestion.TIPO_CAMBIO_ESTADO, bean);
            bean.addMensaje("El anticipo se cancelo exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarPago(AnticipoBean bean) { 
        try {
            AntAnticipo obj = bean.getObjeto();
            bean.auditoriaModificar(obj);
            obj.setEstado(AntAnticipo.ESTADO_PAGADO);
            //Double valorCotazado = obj.getValorCotizado().doubleValue();
            //Double retecionAplicada = obj.getRetencionAplicada().doubleValue();  
            //Double valor = valorCotazado - (valorCotazado * (retecionAplicada / 100));
            //obj.getValorCotizado().subtract(obj.getValorCotizado().multiply(obj.getRetencionAplicada().divide(new BigDecimal(100))));
            obj.setValorDisponible(obj.getValorCotizado());
            getAnticipoRemoto().actualizarPago(obj);
            List<AntAnticipoItem> anticipoItems = getAnticipoItemRemoto().consultarItemPorAnticipoId(bean.getObjeto().getId()); 
            for(AntAnticipoItem item:anticipoItems){
                item.setValorTecnologiaPagada(item.getValorTecnologiaCotizada().subtract(item.getValorTecnologiaCotizada().multiply(obj.getRetencionAplicada().divide(new BigDecimal(100)))));
                getAnticipoItemRemoto().actualizarValorTecnologiaPagada(item);
            }
            getAnticipoRemoto().actualizarEstadoProceso(obj);
            registrarHistoricoGestionesPago(AntAnticipoGestion.TIPO_CAMBIO_ESTADO, bean);
            bean.addMensaje("El pago se realizo exitosamente ");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarGestiones(AnticipoBean bean) { 
        try {
            AntAnticipo obj = bean.getObjeto();
            AntAnticipoGestion objGestion = bean.getAntAnticipoGestion();
            objGestion.setEstado(obj.getEstado());
            objGestion.setAntAnticiposId(obj);
            bean.auditoriaGuardar(objGestion);
            objGestion.setId(getAnticipoGestionRemoto().insertar(objGestion));
            obj.getAntAnticipoGestionesList().add(objGestion);
            bean.addMensaje("La Gestion se creo exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarDevolver(AnticipoBean bean) { 
        try {
            //actualizar el acticipo el estado
            AntAnticipo anti = getAnticipoRemoto().consultar(bean.getObjeto().getId());
            registrarHistoricoGestionesDevolver(AntAnticipoGestion.TIPO_CAMBIO_ESTADO, bean);
            bean.auditoriaModificar(bean.getObjeto());
            switch(anti.getEstado()){
                case AntAnticipo.ESTADO_AUTORIZADO:
                    bean.getObjeto().setEstado(AntAnticipo.ESTADO_GESTION_FIRMAS);
                    break;
                case AntAnticipo.ESTADO_GESTION_FIRMAS:
                    bean.getObjeto().setEstado(AntAnticipo.ESTADO_CON_COTIZACION);
                    break;    
                case AntAnticipo.ESTADO_CON_COTIZACION:
                    bean.getObjeto().setEstado(AntAnticipo.ESTADO_PENDIENTE_COTIZACION);
                    break;
            }
            getAnticipoRemoto().actualizarEstadoProceso(bean.getObjeto());
            // se inserta una gestion
            registrarHistoricoGestiones(AntAnticipoGestion.TIPO_CAMBIO_ESTADO, bean);
            bean.addMensaje("EL Anticipo se devolvio exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarItem(AnticipoBean bean) { 
        try {
            AntAnticipoItem obj = bean.getObjetoItem();
            bean.auditoriaGuardar(obj);
            obj.setId(getAnticipoItemRemoto().insertar(obj));
            bean.getObjeto().getAntAnticipoItemsList().add(obj);
            bean.addMensaje("El Item se creo exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarGestionar(AnticipoBean bean) { 
        try {
            AntAnticipo anticipo = getAnticipoRemoto().consultar(bean.getObjeto().getId());
            if(anticipo.getEstado().equals(AntAnticipo.ESTADO_GESTION_FIRMAS)){
                bean.addError("El anticipo esta en estado con gestión firmas");
                return;
            }
            
            AntAnticipo obj = bean.getObjeto();
            if(anticipo.getValorCotizado() != null && anticipo.getRetencionSugerida() != null){
                if(anticipo.getValorCotizado().equals(obj.getValorCotizado()) 
                    && anticipo.getRetencionSugerida().equals(obj.getRetencionSugerida())){
                    return;
                }
            }
            obj.setEstado(AntAnticipo.ESTADO_CON_COTIZACION);
            bean.auditoriaModificar(obj);
            //actualizar afiliado
            if(obj.getAsegAfiliadosId() != null){
                getAnticipoRemoto().actualizarAfiliado(obj);
            }
            // actualizar prestador
            if(obj.getCntPrestadorSedesId() != null){
                getAnticipoRemoto().actualizarPrestador(obj);
            }
           
            getAnticipoRemoto().actualizarConCotizacion(obj);
            if(anticipo.getEstado().equals(AntAnticipo.ESTADO_CON_COTIZACION)){
                registrarHistoricoGestionesConValor(AntAnticipoGestion.TIPO_CAMBIO_ESTADO,  bean, obj.getValorCotizado().toString());
            }else{
                registrarHistoricoGestiones(AntAnticipoGestion.TIPO_CAMBIO_ESTADO,  bean);
            }
            bean.addMensaje("La cotización es exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarAnticipoAdjuntos(AnticipoBean bean) { 
        try {
            for(AntAnticipoAdjunto adjunto: bean.getListaAntAdjuntos()){
                if(adjunto.getId() == null){
                    bean.auditoriaGuardar(adjunto);
                    adjunto.setAntAnticiposId(bean.getObjeto());
                    adjunto.setId(getAnticipoAdjuntoRemoto().insertar(adjunto));
                    if(bean.getObjeto().getAntAnticipoAdjuntosList() == null){
                        bean.getObjeto().setAntAnticipoAdjuntosList(new ArrayList<>());
                    }
                    bean.getObjeto().getAntAnticipoAdjuntosList().add(adjunto);
                    generarArchivo(adjunto);
                }
            }
            bean.addMensaje("El Adjunto se creo exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void modificar(AnticipoBean bean) {
        try {
            AntAnticipo obj = bean.getObjeto();
            if (obj.getTipo() == AntAnticipo.TIPO_INDIVIDUAL && obj.getAsegAfiliadosId() == null) {
                bean.addError("El afiliado es obligatorio");
                return;
            }
            bean.auditoriaModificar(obj);
            Maestro clase = bean.getHashClasificacion().get(obj.getMaeClasificacionId());
            if(clase != null){
                obj.setMaeClasificacionCodigo(clase.getValor());
                obj.setMaeClasificacionValor(clase.getNombre());
                obj.setMaeClasificacionTipo(clase.getTipo());
            }
            getAnticipoRemoto().actualizar(obj);
            bean.addMensaje("El anticipo se actualizo exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void modificarItem(AnticipoBean bean) {
        try {
            AntAnticipoItem obj = bean.getObjetoItem();
            bean.auditoriaModificar(obj);
            getAnticipoItemRemoto().actualizar(obj);
            bean.getObjeto().setAntAnticipoItemsList(getAnticipoItemRemoto().consultarItemPorAnticipoId(obj.getAntAnticiposId().getId()));
            bean.addMensaje("El Item se actualizo exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void borrar(AnticipoBean bean) {
        try {
            //bean.setObjeto(getAnticipoRemoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarItem(AnticipoBean bean) {
        try {
            AntAnticipoItem obj = bean.getObjetoItem();
            obj.setBorrado(Boolean.TRUE);
            bean.auditoriaModificar(obj);
            obj.setUsuarioBorra(obj.getUsuarioModifica());
            obj.setTerminalBorra(obj.getTerminalModifica());
            obj.setFechaHoraBorra(obj.getFechaHoraModifica());
            getAnticipoItemRemoto().actualizarBorradoLogico(obj);
            bean.getObjeto().setAntAnticipoItemsList(getAnticipoItemRemoto().consultarItemPorAnticipoId(obj.getAntAnticiposId().getId()));
            bean.addMensaje("El Item se elimino exitosamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void generarReporteAnticipo(AnticipoBean bean){
        try {
            bean.setReporteAnticipo(new ArrayList<>());
            LocalDate fecha = LocalDate.now();
            SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyy-MM-dd");
            ReporteAnticipo reporteAnticipo = new ReporteAnticipo();
            // consulta el anticipo
            AntAnticipo obj = getAnticipoRemoto().consultar(bean.getObjeto().getId());
          
            if(!obj.getEstado().equals(AntAnticipo.ESTADO_GESTION_FIRMAS)){
                bean.getObjeto().setEstado(AntAnticipo.ESTADO_GESTION_FIRMAS);
                getAnticipoRemoto().actualizarEstadoProceso(bean.getObjeto());
                registrarHistoricoGestiones(AntAnticipoGestion.TIPO_CAMBIO_ESTADO,  bean);
            }
            
            if(obj.getAsegAfiliadosId() != null){
                obj.setAsegAfiliadosId(getAfiliadoRemoto().consultar(obj.getAsegAfiliadosId().getId()));
            }
            if(obj.getCntPrestadoresId() != null){
                obj.setCntPrestadoresId(getPrestadorRemoto().consultar(obj.getCntPrestadoresId().getId()));
            }
            if(obj.getGnUsuariosId() != null){
                obj.setGnUsuariosId(getUsuarioRemoto().consultar(obj.getGnUsuariosId().getId()));
            }
            if(obj.getCntPrestadorSedesId() != null){
               obj.setCntPrestadorSedesId(getCntPrestadorSedeRemoto().consultar(obj.getCntPrestadorSedesId().getId()));
            }
            obj.setAntAnticipoItemsList(getAnticipoItemRemoto().consultarItemPorAnticipoId(bean.getObjeto().getId()));
            //Empezar llenar informacion para el reporte
            String formatFechaAnticio = formatterFecha.format(obj.getFechaHoraCrea());
            reporteAnticipo.setStrFechaAnticipo(formatFechaAnticio);
            reporteAnticipo.setStrFechaSolicitud(fecha.toString());
            reporteAnticipo.setStrSolicitante(obj.getGnUsuariosId().getNombre());
            reporteAnticipo.setStrCedula(obj.getGnUsuariosId().getDocumento());
            reporteAnticipo.setStrAreaoDepencencia(obj.getGnUsuariosId().getMaeAreaValor());
            reporteAnticipo.setStrCargo(obj.getGnUsuariosId().getMaeCargoValor());
            reporteAnticipo.setStrDetalleAnticipo(obj.getClasificacionObservacion());
            if(obj.getAsegAfiliadosId() != null){
                reporteAnticipo.setStrUsuario(obj.getAsegAfiliadosId().getNombreCompleto());
                reporteAnticipo.setStrCedulaCodigoyNumeroCedula(obj.getAsegAfiliadosId().getMaeTipoDocumentoCodigo()+ " : "+ obj.getAsegAfiliadosId().getNumeroDocumento());
            }
            reporteAnticipo.setStrMaDiagnosticoCodigo(obj.getMaDiagnosticoCodigo());
            reporteAnticipo.setStrMaDiagnosticoValor(obj.getMaDiagnosticoValor());
            reporteAnticipo.setStrObservacion(obj.getJustificacion());
            reporteAnticipo.setStrAreaSolicitante(obj.getGnUsuariosId().getMaeAreaValor());
            reporteAnticipo.setStrElaboradoPor(obj.getGnUsuariosId().getNombre());
            reporteAnticipo.setBigValorCotizado(obj.getValorCotizado());
            if(obj.getRetencionSugerida() != null){
                reporteAnticipo.setStrRetencionSugerida(obj.getRetencionSugerida().toString().substring(0, (obj.getRetencionSugerida().toString().length() - 1)));
                reporteAnticipo.setBigValorDespuesRetencionSugerida(obj.getValorCotizado().subtract(obj.getValorCotizado().multiply(obj.getRetencionSugerida().divide(new BigDecimal(100)))));
            }else{
                reporteAnticipo.setStrRetencionSugerida("0");
                obj.setRetencionSugerida(new BigDecimal(0));
                reporteAnticipo.setBigValorDespuesRetencionSugerida(obj.getValorCotizado().subtract(obj.getValorCotizado().multiply(obj.getRetencionSugerida().divide(new BigDecimal(100)))));
            }
            
            
            for(AntAnticipoItem item: obj.getAntAnticipoItemsList()){
                ReporteAnticipoItem reportItem = new ReporteAnticipoItem();
                reportItem.setStrMaTecnologiaValor(item.getMaTecnologiaValor());
                reportItem.setStrNit(obj.getCntPrestadoresId().getNumeroDocumento());
                reportItem.setStrPrestador(obj.getCntPrestadoresId().getRazonSocial());
                reportItem.setIntCatidad(item.getCantidad());
                reporteAnticipo.getListItems().add(reportItem);
                
                BigDecimal valorUnitarioPorCantidad = item.getValorTecnologiaCotizada().multiply(new BigDecimal(item.getCantidad()));
                BigDecimal convetirPocentaje = obj.getRetencionSugerida().divide(new BigDecimal(100));
                BigDecimal calcularRetencion = valorUnitarioPorCantidad.multiply(convetirPocentaje);
                BigDecimal valorMenosRetencion = valorUnitarioPorCantidad.subtract(calcularRetencion);
                reportItem.setBigValor(valorMenosRetencion);
                
            }
            reporteAnticipo.setBigTotalAnticipo(obj.getValorCotizado().subtract(obj.getValorCotizado().multiply(obj.getRetencionSugerida().divide(new BigDecimal(100)))));
            bean.getReporteAnticipo().add(reporteAnticipo);
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
        }
    }
    
    public void registrarHistoricoGestiones(int tipo, AnticipoBean bean) throws Exception {
        AntAnticipoGestion antAnticipoGestion = new AntAnticipoGestion();
        antAnticipoGestion.setAntAnticiposId(bean.getObjeto());
        antAnticipoGestion.setTipo(tipo);
        antAnticipoGestion.setEstado(bean.getObjeto().getEstado());
        antAnticipoGestion.setDescripcion(bean.getObjeto().getEstadoStr());
        bean.auditoriaGuardar(antAnticipoGestion);
        getAnticipoGestionRemoto().insertar(antAnticipoGestion);
    }
    
    public void registrarHistoricoGestionesConValor(int tipo, AnticipoBean bean, String valorCotizado) throws Exception {
        AntAnticipoGestion antAnticipoGestion = new AntAnticipoGestion();
        antAnticipoGestion.setAntAnticiposId(bean.getObjeto());
        antAnticipoGestion.setTipo(tipo);
        antAnticipoGestion.setEstado(bean.getObjeto().getEstado());
        antAnticipoGestion.setDescripcion(bean.getObjeto().getEstadoStr() + " " + valorCotizado);
        bean.auditoriaGuardar(antAnticipoGestion);
        getAnticipoGestionRemoto().insertar(antAnticipoGestion);
    }
    
    public void registrarHistoricoGestionesDevolver(int tipo, AnticipoBean bean) throws Exception {
        AntAnticipoGestion antAnticipoGestion = new AntAnticipoGestion();
        antAnticipoGestion.setAntAnticiposId(bean.getObjeto());
        antAnticipoGestion.setTipo(tipo);
        antAnticipoGestion.setEstado(AntAnticipo.ESTADO_DEVUELTO);
        antAnticipoGestion.setDescripcion(bean.getObjeto().getObservacionDevolver());
        bean.auditoriaGuardar(antAnticipoGestion);
        getAnticipoGestionRemoto().insertar(antAnticipoGestion);
    }
    
    public void registrarHistoricoGestionesCancelar(int tipo, AnticipoBean bean) throws Exception {
        AntAnticipoGestion antAnticipoGestion = new AntAnticipoGestion();
        antAnticipoGestion.setAntAnticiposId(bean.getObjeto());
        antAnticipoGestion.setTipo(tipo);
        antAnticipoGestion.setEstado(bean.getObjeto().getEstado());
        antAnticipoGestion.setDescripcion(bean.getObjeto().getObservacionCancelar());
        bean.auditoriaGuardar(antAnticipoGestion);
        getAnticipoGestionRemoto().insertar(antAnticipoGestion);
    }
    
    public void registrarHistoricoAutorizar(int tipo, AnticipoBean bean) throws Exception {
        AntAnticipoGestion antAnticipoGestion = new AntAnticipoGestion();
        antAnticipoGestion.setAntAnticiposId(bean.getObjeto());
        antAnticipoGestion.setTipo(tipo);
        antAnticipoGestion.setEstado(bean.getObjeto().getEstado());
        antAnticipoGestion.setDescripcion(bean.getObjeto().getObservacionAutorizar());
        bean.auditoriaGuardar(antAnticipoGestion);
        getAnticipoGestionRemoto().insertar(antAnticipoGestion);
    }
    
    public void registrarHistoricoGestionesPago(int tipo, AnticipoBean bean) throws Exception {
        AntAnticipoGestion antAnticipoGestion = new AntAnticipoGestion();
        antAnticipoGestion.setAntAnticiposId(bean.getObjeto());
        antAnticipoGestion.setTipo(tipo);
        antAnticipoGestion.setEstado(bean.getObjeto().getEstado());
        antAnticipoGestion.setDescripcion(bean.getObjeto().getObservacionPago());
        bean.auditoriaGuardar(antAnticipoGestion);
        getAnticipoGestionRemoto().insertar(antAnticipoGestion);
    }
    
    @Override
    public boolean validarEstadoAfiliado(int maeEstadoAfiliacion, AnticipoBean bean) {
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(maeEstadoAfiliacion);
            if (maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                return true;
            } else {
                bean.addError("No se puede seleccionar el afiliado ya que se encuentra " + maeEstado.getNombre());
                return false;
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return false;
        }
    }

    public void cargarMaestros(AnticipoBean bean){
        try {
            //Singleton Ubicaciones
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            
            bean.setListaTipoDocumentoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumentoPersona(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoPersona()));
            
            bean.setListaTiposAdjuntos(getMaestroRemoto().consultarPorTipo(MaestroTipo.ANT_ANTICIPOS_ADJUNTO_TIPO));
            bean.setHashTiposAdjuntos(AuConstantes.obtenerHashMaestro(bean.getListaTiposAdjuntos()));
            
            bean.setListaTiposAutorizarAdjuntos(getMaestroRemoto().consultarPorTipo(MaestroTipo.ANT_ANTICIPOS_ADJUNTO_TIPO));
            bean.setHashTiposAutorizarAdjuntos(AuConstantes.obtenerHashMaestro(bean.getListaTiposAdjuntos()));
            
            //
            bean.setListaUsuarios(getUsuarioRemoto().consultarPorEmpresa(bean.getConexion().getEmpresa().getId()));
            bean.setHashUsuarios(getUsuarioRemoto().consultarIdNombreHashTodos(bean.getConexion().getEmpresa().getId()));
            
            /// listas lazy afiliado, prestado
            bean.setListaTipoDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setListaGeneroAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setListaEstadosAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setListaRegimenAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setListaCiudades(UbicacionSingle.getInstance().getListaMunicipios());
            
        } catch (Exception ex) {
            Logger.getLogger(AnticipoServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private boolean generarArchivo(AntAnticipoAdjunto adjunto) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(adjunto.getRuta(), adjunto.getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(adjunto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(adjunto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException ex) {

            }
        }
        return esArchivoGuardado;
    }
    
    @Override
    public void listarAfiliado(AnticipoBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getAfiliadoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta(0)));
            bean.setListaAfiliados(getAfiliadoRemoto().consultarListaBuscador(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public void listarPrestadores(AnticipoBean bean) {
        try {
            if(bean.getParamConsulta(1).getFiltros() == null){
                bean.getParamConsulta(1).setFiltros(new HashMap());
            }
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(1).setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
            }
            
            bean.getParamConsulta(1).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(1).getFiltros().put("fecha", Util.fechaDateAString(new Date()));
            bean.getParamConsulta(1).setCantidadRegistros(getPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(1)));
            bean.setListaPrestadores(getPrestadorRemoto().consultarListaSede(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    @Override
    public AntAnticipo consultarAnticipoId(int idAnticipo, AnticipoBean bean) {
        AntAnticipo anticipo = null;
        try {
            anticipo = getAnticipoRemoto().consultar(idAnticipo);
            if (anticipo != null && anticipo.getAsegAfiliadosId() != null) {
                anticipo.setAsegAfiliadosId(getAfiliadoRemoto().consultar(anticipo.getAsegAfiliadosId().getId()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un error al consultar el anticipo");
        }
        return anticipo;
    }
    
    private void cargas(AnticipoBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }
    
    @Override
    public void cargaInicial(AnticipoBean bean) {
        try {
            bean.setListaClasificacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ANT_CLASIFICACION));
            bean.setHashClasificacion(AuConstantes.obtenerHashMaestro(bean.getListaClasificacion()));
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }
}
