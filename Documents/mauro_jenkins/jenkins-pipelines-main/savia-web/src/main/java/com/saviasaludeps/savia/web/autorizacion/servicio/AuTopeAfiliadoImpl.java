/*
 * To change this license header, choose License Headers in Project Proobjties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.autorizacion.AuTopeAfiliado;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuTopeAfiliadoBean;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.saviasaludeps.savia.negocio.autorizacion.AuTopeAfiliadoRemoto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author raul-palacios
 */
public class AuTopeAfiliadoImpl extends AccionesBO implements AuTopeAfiliadoIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }
    
    private AuTopeAfiliadoRemoto getTopeAfiliadoRemoto() throws Exception {
        return (AuTopeAfiliadoRemoto) RemotoEJB.getEJBRemoto("AuTopeAfiliadoServicio", AuTopeAfiliadoRemoto.class.getName());
    }
    
    private AuSolicitudAdjuntoRemoto getAuSolicitudAdjuntoRemoto() throws Exception {
        return (AuSolicitudAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuSolicitudAdjuntoServicio", AuSolicitudAdjuntoRemoto.class.getName());
    }
    
    @Override
    public void Accion(AuTopeAfiliadoBean bean) {
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
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            editar(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            guardarTopeAfiliadosAdjuntos(bean);
                            break;
                    }
                    
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            gestionar(bean);
                            break;
                       
                        case Url.ACCION_GUARDAR:
                            guardarGestionar(bean);
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

    private void listar(AuTopeAfiliadoBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getTopeAfiliadoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getTopeAfiliadoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(AuTopeAfiliadoBean bean) {
        try {
            //cargarMaestros(bean);
            bean.setObjeto(getTopeAfiliadoRemoto().consultar(bean.getObjeto().getId()));
            if(bean.getObjeto().getAseAfiliadosId() != null){
                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAseAfiliadosId().getId());
                bean.getObjeto().setAseAfiliadosId(afiliado);
                bean.getObjeto().getAseAfiliadosId().setListaAsegAfiliadoContacto(afiliado.getListaAsegAfiliadoContacto());
            }
            bean.getObjeto().setAuSolicitudAdjuntoList(getAuSolicitudAdjuntoRemoto().listarAdjuntosByIdTopeAfiliado(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crear(AuTopeAfiliadoBean bean) {
        try {
            cargarMaestros(bean);
            bean.setObjeto(new AuTopeAfiliado());
            bean.getObjeto().setActivo(Boolean.TRUE);
            bean.setObjectoAdjunto(new AuSolicitudAdjunto());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void editar(AuTopeAfiliadoBean bean) {
        try {
            //cargarMaestros(bean);
            bean.setObjeto(getTopeAfiliadoRemoto().consultar(bean.getObjeto().getId()));
            if(bean.getObjeto().getAseAfiliadosId()!= null){
                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAseAfiliadosId().getId());
                bean.getObjeto().setAseAfiliadosId(afiliado);
                bean.getObjeto().getAseAfiliadosId().setListaAsegAfiliadoContacto(afiliado.getListaAsegAfiliadoContacto());
            }
            bean.getObjeto().setAuSolicitudAdjuntoList(getAuSolicitudAdjuntoRemoto().listarAdjuntosByIdTopeAfiliado(bean.getObjeto().getId()));
           
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void gestionar(AuTopeAfiliadoBean bean) {
        try {
            //cargarMaestros(bean);
            bean.setObjeto(getTopeAfiliadoRemoto().consultar(bean.getObjeto().getId()));
            if(bean.getObjeto().getAseAfiliadosId() != null){
                AsegAfiliado afiliado = getAfiliadoRemoto().consultar(bean.getObjeto().getAseAfiliadosId().getId());
                bean.getObjeto().setAseAfiliadosId(afiliado);
                bean.getObjeto().getAseAfiliadosId().setListaAsegAfiliadoContacto(afiliado.getListaAsegAfiliadoContacto());
            }
            bean.getObjeto().setAuSolicitudAdjuntoList(getAuSolicitudAdjuntoRemoto().listarAdjuntosByIdTopeAfiliado(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardar(AuTopeAfiliadoBean bean) { 
        try {
            AuTopeAfiliado obj = bean.getObjeto();
            bean.auditoriaGuardar(obj);
            obj.setId(getTopeAfiliadoRemoto().insertar(obj));
            guardarTopeAfiliadosCrear(bean);
            bean.addMensaje("El tope afiliado se creo exitosamente " + obj.getId());
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarGestionar(AuTopeAfiliadoBean bean) { 
        try {
            AuTopeAfiliado obj = bean.getObjeto();
            obj.setActivo(Boolean.FALSE);
            bean.auditoriaModificar(obj);
            getTopeAfiliadoRemoto().actualizar(obj);
            bean.addMensaje("Se inactivó el registro de manera exitosa");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarTopeAfiliadosCrear(AuTopeAfiliadoBean bean) { 
        try {
            for(AuSolicitudAdjunto adjunto: bean.getObjeto().getAuSolicitudAdjuntoList()){
                if(adjunto.getId() == null){
                    bean.auditoriaGuardar(adjunto);
                    adjunto.setAuTopeAfiliadosId(bean.getObjeto());
                    adjunto.setId(getAuSolicitudAdjuntoRemoto().insertar(adjunto));
                    generarArchivo(adjunto);
                }
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private void guardarTopeAfiliadosAdjuntos(AuTopeAfiliadoBean bean) { 
        try {
            for(AuSolicitudAdjunto adjunto: bean.getListaAntAdjuntos()){
                if(adjunto.getId() == null){
                    bean.auditoriaGuardar(adjunto);
                    adjunto.setAuTopeAfiliadosId(bean.getObjeto());
                    adjunto.setId(getAuSolicitudAdjuntoRemoto().insertar(adjunto));
                    bean.getObjeto().getAuSolicitudAdjuntoList().add(adjunto);
                    generarArchivo(adjunto);
                }
            }
            bean.addMensaje("Los adjunto se crearon exitosamente");
        } catch (Exception ex) {
            bean.addError("Hubo un fallo al guardar, favor contactar al administrador");
        }
    }
    
    private boolean generarArchivo(AuSolicitudAdjunto adjunto) throws Exception {
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
    
    private void modificar(AuTopeAfiliadoBean bean) {
        
    }
    
    @Override
    public boolean validarEstadoAfiliado(int maeEstadoAfiliacion, int idAfiliado ,AuTopeAfiliadoBean bean) {
        boolean validar = true;
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(maeEstadoAfiliacion);
            if (!maeEstado.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                bean.addError("No se puede seleccionar el afiliado ya que se encuentra " + maeEstado.getNombre());
                return false;
            } 
            AuTopeAfiliado auTopeAfiliado = getTopeAfiliadoRemoto().consultarAfiliadoActivo(idAfiliado);
            if(auTopeAfiliado != null){
                bean.addError("No se puede seleccionar el afiliado ya que se encuentra con topes activos ");
                return false;
            }
            return validar;
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando el estado del afiliado, intentelo nuevamente");
            return false;
        }
    }

    public void cargarMaestros(AuTopeAfiliadoBean bean){
        try {
            //Singleton Ubicaciones
            bean.setListaUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            
            bean.setListaTipoDocumentoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTipoDocumentoPersona(AuConstantes.obtenerHashMaestro(bean.getListaTipoDocumentoPersona()));
                    
            /// listas lazy afiliado, prestado
            bean.setListaTipoDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setListaGeneroAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO));
            bean.setListaEstadosAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.setListaRegimenAfiliado(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setListaCiudades(UbicacionSingle.getInstance().getListaMunicipios());
            
        } catch (Exception ex) {
            Logger.getLogger(AuTopeAfiliadoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    @Override
    public void listarAfiliado(AuTopeAfiliadoBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getAfiliadoRemoto().consultarCantidadListaBuscador(bean.getParamConsulta(0)));
            bean.setListaAfiliados(getAfiliadoRemoto().consultarListaBuscador(bean.getParamConsulta(0)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void cargas(AuTopeAfiliadoBean bean) {
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
    public void cargaInicial(AuTopeAfiliadoBean bean) {
        try {
            bean.setListaClasificacion(getMaestroRemoto().consultarPorTipo(MaestroTipo.ANT_CLASIFICACION));
            bean.setHashClasificacion(AuConstantes.obtenerHashMaestro(bean.getListaClasificacion()));
            
            bean.setListaTiposAdjuntos(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_TOPES_ADJUNTO_TIPO));
            bean.setHashTiposAdjuntos(AuConstantes.obtenerHashMaestro(bean.getListaTiposAdjuntos()));
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }
}
