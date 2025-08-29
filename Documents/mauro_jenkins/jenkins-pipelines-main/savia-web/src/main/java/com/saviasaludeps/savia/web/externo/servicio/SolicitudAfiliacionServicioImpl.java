/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.dominio.solicitud.GsAdjunto;
import com.saviasaludeps.savia.dominio.solicitud.GsAfiliado;
import com.saviasaludeps.savia.dominio.solicitud.GsAsignacionUsuario;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsNotificacionRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsSolicitudRemoto;
import com.saviasaludeps.savia.negocio.webservices.AseguramientoRemoto;
import com.saviasaludeps.savia.web.externo.bean.SolicitudAfiliacionBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Fabian Coronel
 */


public class SolicitudAfiliacionServicioImpl extends AccionesBO implements SolicitudAfiliacionServicioIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

//    private AfiliadoActualizacionRemoto getAfiliadoActualizacionRemoto() throws Exception {
//        return (AfiliadoActualizacionRemoto) RemotoEJB.getEJBRemoto("AfiliadoActualizacionServicio", AfiliadoActualizacionRemoto.class.getName());
//    }


    private AseguramientoRemoto getAseguramientoRemoto() throws Exception {
        return (AseguramientoRemoto) RemotoEJB.getEJBRemoto("AseguramientoServicio", AseguramientoRemoto.class.getName());
    }
    
    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private GsSolicitudRemoto getGsSolicitudRemoto() throws Exception {
        return (GsSolicitudRemoto) RemotoEJB.getEJBRemoto("GsSolicitudServicio", GsSolicitudRemoto.class.getName());
    }

    private GsAfiliadoRemoto getGsAfiliadoRemoto() throws Exception {
        return (GsAfiliadoRemoto) RemotoEJB.getEJBRemoto("GsAfiliadoServicio", GsAfiliadoRemoto.class.getName());
    }

    private GsAdjuntoRemoto getGsAdjuntoRemoto() throws Exception {
        return (GsAdjuntoRemoto) RemotoEJB.getEJBRemoto("GsAdjuntoServicio", GsAdjuntoRemoto.class.getName());
    }

    private GsNotificacionRemoto getGsNotificacionRemoto() throws Exception {
        return (GsNotificacionRemoto) RemotoEJB.getEJBRemoto("GsNotificacionServicio", GsNotificacionRemoto.class.getName());
    }

    @Override
    public void Accion(SolicitudAfiliacionBean bean) {
        switch (bean.getAccion()) {
            case SolicitudAfiliacionBean.ACCION_BUSCAR_AFILLIADO_SERVICE:
                consultarAfiliado(bean);
                break;
            case SolicitudAfiliacionBean.ACCION_GUARDAR_AFILIADO_SERVICE:
                guardarAfiliado(bean);
                break;
            default:
                break;
        }
    }

    private void consultarAfiliado(SolicitudAfiliacionBean bean) {
        try {
            Maestro maeTipoDocumento = getMaestroRemoto().consultarPorValorTipo(
                      bean.getObjeto().getGsAfiliado().getDocumentoTipo(), 
                      MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
                      
              AsegAfiliado afiliado = getAfiliadoRemoto().consultar(
                      maeTipoDocumento.getId(), 
                      bean.getObjeto().getGsAfiliado().getDocumentoNumero());
            if (afiliado != null && bean.getObjeto().getGsAfiliado()
                    .getFechaNacimiento().equals(afiliado.getFechaNacimiento())) {
                GsAfiliado gsAfiliado = castAfiliadoGS(afiliado);
                bean.getObjeto().setGsAfiliado(gsAfiliado);
                bean.setHabilitarSeccionResultados(true);
            }else{
                String tipoDocumento = bean.getObjeto().getGsAfiliado().getDocumentoTipo();
                String numeroDocumento = bean.getObjeto().getGsAfiliado().getDocumentoNumero();
                Date fecha = bean.getObjeto().getGsAfiliado().getFechaNacimiento();
                GsAfiliado gsAfiliado = new GsAfiliado();
                gsAfiliado.setDocumentoTipo(tipoDocumento);
                gsAfiliado.setDocumentoNumero(numeroDocumento);
                gsAfiliado.setFechaNacimiento(fecha);
                bean.getObjeto().setGsAfiliado(gsAfiliado);
                bean.setHabilitarSeccionResultados(true);
            }

        } catch (Exception e) {
            bean.addError("Error consultar afiliado : " + e.getMessage());
        }
    }
    
    private GsAfiliado castAfiliadoGS(AsegAfiliado asegAfiliado ){
        GsAfiliado gsAfiliado = new GsAfiliado();
        try{
        Maestro maeTipoDocumento = getMaestroRemoto().consultar(asegAfiliado.getMaeTipoDocumento());
        gsAfiliado.setPrimerNombre(asegAfiliado.getPrimerNombre());
        gsAfiliado.setSegundoNombre(asegAfiliado.getSegundoNombre());
        gsAfiliado.setPrimerApellido(asegAfiliado.getPrimerApellido());
        gsAfiliado.setSegundoApellido(asegAfiliado.getSegundoApellido());
        gsAfiliado.setFechaNacimiento(asegAfiliado.getFechaNacimiento());
        gsAfiliado.setDocumentoNumero(asegAfiliado.getNumeroDocumento());
        gsAfiliado.setDocumentoTipo(maeTipoDocumento.getValor());
        //ubicaciones
//        gsAfiliado.setResidenciaUbicacion(asegAfiliado.getResidenciaUbicacion());
//        gsAfiliado.setResidenciaUbicacionNombre(asegAfiliado.getResidenciaUbicacion().getNombre());
//        gsAfiliado.setResidenciaDireccion(asegAfiliado.getDireccionDescripcion());
//        gsAfiliado.setResidenciaZonaTipo(asegAfiliado.getZona());
//        gsAfiliado.setAtencionUbicacion(asegAfiliado.getAfiliacionUbicacion());
//        gsAfiliado.setAtencionUbicacionNombre(asegAfiliado.getAfiliacionUbicacion().getNombre());
        //direccion
//        gsAfiliado.setViaDireccion(asegAfiliado.getDireccionVia());
//        gsAfiliado.setNumeroDireccion(asegAfiliado.getDireccionNro());
//        gsAfiliado.setOrden1Direccion(asegAfiliado.getDireccionOrd1());
//        gsAfiliado.setOrientacionDireccion(asegAfiliado.getDireccionOrientacion());
//        gsAfiliado.setPlacaDireccion(asegAfiliado.getDireccionPlaca1());
//        gsAfiliado.setOrden2Direccion(asegAfiliado.getDireccionOrd2());
//        gsAfiliado.setDescripcionDireccion(asegAfiliado.getDireccionDescripcion());
//        gsAfiliado.setObservacionDireccion(asegAfiliado.getDireccionResidencia());
//        gsAfiliado.setResidenciaZonaTipo(asegAfiliado.getZona());
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return gsAfiliado;
    }

    private void guardarAfiliado(SolicitudAfiliacionBean bean) {
        try {
            //String rutaDescarga = PropApl.getInstance().get(PropApl.RUTA_ADJUNTOS_SOLICITUDES);
            String rutaDescarga = PropApl.getInstance().get(PropApl.GS_SOLICITUDES_CARGA);
            if (rutaDescarga == null || rutaDescarga.equals("")) {
                bean.addError("Error configure la ruta adjunto de solicitudes en tabla parametros");
                return;
            }
            GsAfiliado afiliado = bean.getObjeto().getGsAfiliado();
            afiliado.setResidenciaDireccion(afiliado.getResidenciaDireccionGenerar());
            GsSolicitud solitud = bean.getObjeto();
            int idAfiliado = getGsAfiliadoRemoto().insertar(afiliado);
            solitud.setGsAfiliado(new GsAfiliado(idAfiliado));
            solitud.setUsuario(new Usuario(GsSolicitud.USUARIO_ID_DEFECTO));
            solitud.setTipo(GsSolicitud.TIPO_SOLICITUD_AFILIACION);
            solitud.setNombre(solitud.getTipoStr());
            solitud.setEstado(GsSolicitud.ESTADO_REGISTRADO);
            verificarTipoNotificacion(bean);
            GsAsignacionUsuario asignacion = getGsSolicitudRemoto().
                    proximaAsignacion(GsSolicitud.TIPO_SOLICITUD_AFILIACION, afiliado.getAtencionUbicacion().getId());
            if (asignacion != null) {
                solitud.setUsuario(new Usuario(asignacion.getUsuarioId()));
                solitud.setGsZona(new GsZona(asignacion.getZonasId()));
            }
            solitud.setFechaHoraCrea(new Date());
            int idSolicitud = getGsSolicitudRemoto().insertar(solitud);
            StringBuilder sbuilder = new StringBuilder();
            int indiceCertificados = 1;
            for (GsAdjunto gsAdjunto : bean.getObjeto().getListaGsAdjuntos()) {
                String prefijoCertificados = (gsAdjunto.getTipo() == GsAdjunto.TIPO_ADJUNTO_CERTIFICADO)
                        ? indiceCertificados + "_" : "";
                String nombre = generarNombreArchivo(afiliado.getDocumentoNumero(), idSolicitud, gsAdjunto, prefijoCertificados);
                gsAdjunto.setArchivo(nombre);
                gsAdjunto.setGsSolicitud(new GsSolicitud(idSolicitud));
                gsAdjunto.setRuta(rutaDescarga);
                boolean esGuardado = guardarAdjuntoEnSistema(rutaDescarga, nombre, gsAdjunto.getAdjuntoStream(), bean);
                if (esGuardado) {
                    getGsAdjuntoRemoto().insertar(gsAdjunto);
                } else {
                    sbuilder.append("El archivo ");
                    sbuilder.append(gsAdjunto.getArchivo());
                    sbuilder.append(" no se pudo guardar en disco");
                }
                if (gsAdjunto.getTipo() == GsAdjunto.TIPO_ADJUNTO_CERTIFICADO) {
                    indiceCertificados++;
                }
            }
            asignarNotificaciones(bean, idSolicitud);
            if (sbuilder.length() > 0) {
                bean.addError(sbuilder.toString());
            }
            bean.addMensaje("Se creo el registro de manera exitosa con el identificador de radicado: " + idSolicitud);
        } catch (Exception e) {
            bean.addError("Error al guardar un afiliado :" + e.getMessage());
        }
    }

    private String generarNombreArchivo(String numeroDocumento, int idSolitud,
            GsAdjunto adjunto, String indice) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String nombre = numeroDocumento + "_" + idSolitud + "_"
                + GsSolicitud.TIPO_SOLICITUD_AFILIACION + "_" + adjunto.getTipo() + "_" + indice + sdf.format(new Date())
                + adjunto.getExtensionAdjunto();
        return nombre;
    }

    private boolean guardarAdjuntoEnSistema(String ruta, String nombre, InputStream adjuntoStream, SolicitudAfiliacionBean bean) {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(ruta, nombre);
            destino = new FileOutputStream(archivo);
            IOUtils.copy(adjuntoStream, destino);
            IOUtils.closeQuietly(adjuntoStream);
            IOUtils.closeQuietly(destino);
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            bean.addError("Error al subir un adjunto " + ex.getMessage());
            Logger.getLogger(SolicitudAfiliacionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            bean.addError("Error al subir un adjunto " + ex.getMessage());
            Logger.getLogger(SolicitudAfiliacionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                destino.close();
            } catch (IOException ex) {
                Logger.getLogger(SolicitudAfiliacionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return esArchivoGuardado;
    }

    private void verificarTipoNotificacion(SolicitudAfiliacionBean bean) {
        GsSolicitud solicitud = bean.getObjeto();
        int tipoNotificacion = 0;
        if (bean.getNotificacionCorreo() && bean.getNotificacionSMS()) {
            tipoNotificacion = GsSolicitud.TIPO_NOTIFICACION_AMBOS;
        }
        if (!bean.getNotificacionCorreo() && !bean.getNotificacionSMS()) {
            tipoNotificacion = GsSolicitud.TIPO_NOTIFICACION_NINGUNA;
        }
        if (bean.getNotificacionCorreo() && !bean.getNotificacionSMS()) {
            tipoNotificacion = GsSolicitud.TIPO_NOTIFICACION_EMAIL;
        }
        if (!bean.getNotificacionCorreo() && bean.getNotificacionSMS()) {
            tipoNotificacion = GsSolicitud.TIPO_NOTIFICACION_SMS;
        }
        solicitud.setNotificacion(tipoNotificacion);
    }

    @Override
    public void cargaInicial(SolicitudAfiliacionBean bean) {
        try {
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipiosAntioquia());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipiosAntioquia());
        } catch (Exception ex) {
            bean.addError("Error carga inicial : " + ex.getMessage());
        }
    }

    
    private void asignarNotificaciones(SolicitudAfiliacionBean bean, int idSolicitud) {
//        GsNotificacion notificacion = new GsNotificacion();
//        notificacion.setFechaHoraCrea(new Date());
//        try {
//            if (bean.getNotificacionSMS()) {
//                notificacion.setId(null);
//                notificacion.setTipo(GsNotificacion.TIPO_SMS);
//                notificacion.setEstado(GsNotificacion.ESTADO_CREADO);
//                notificacion.setGsSolicitudesId(new GsSolicitud(idSolicitud));
//                notificacion.setEncabezado("Notificación Afiliación ");
//                notificacion.setDetalle("Se ha reportado nueva solicitud  :  " + idSolicitud);
//                getGsNotificacionRemoto().insertar(notificacion);
//            }
//            if (bean.getNotificacionCorreo()) {
//                notificacion.setId(null);
//                notificacion.setTipo(GsNotificacion.TIPO_CORREO);
//                notificacion.setEstado(GsNotificacion.ESTADO_CREADO);
//                notificacion.setGsSolicitudesId(new GsSolicitud(idSolicitud));
//                notificacion.setEncabezado("Notificación Afiliación");
//                notificacion.setDetalle("Se ha reportado nueva solicitud : " + idSolicitud);
//                getGsNotificacionRemoto().insertar(notificacion);
//            }
//        } catch (Exception ex) {
//            bean.addError("Error al insertar notificaciones " + ex.getMessage());
//            Logger.getLogger(SolicitudAfiliacionServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
