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
import com.saviasaludeps.savia.dominio.solicitud.GsAdjunto;
import com.saviasaludeps.savia.dominio.solicitud.GsAfiliado;
import com.saviasaludeps.savia.dominio.solicitud.GsAsignacionUsuario;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.solicitud.GsSolicitudRemoto;
import com.saviasaludeps.savia.web.externo.bean.SolicitudAutorizacionBean;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Fabian Coronel
 */
public class SolicitudAutorizacionServicioImpl extends AccionesBO implements SolicitudAutorizacionServicioIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
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
    
    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    @Override
    public void Accion(SolicitudAutorizacionBean bean) {
        switch (bean.getAccion()) {
            case SolicitudAutorizacionBean.ACCION_BUSCAR_AFILLIADO_SERVICE:
                consultarAfiliado(bean);
                break;
            case SolicitudAutorizacionBean.ACCION_GUARDAR:
                guardar(bean);
                break;
            default:
                break;
        }
    }

    private void consultarAfiliado(SolicitudAutorizacionBean bean) {
        try {
            Maestro maeTipoDocumento = getMaestroRemoto().consultarPorValorTipo(
                      bean.getAfiliado().getDocumentoTipo(), 
                      MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
                      
              AsegAfiliado afiliado = getAfiliadoRemoto().consultar(
                      maeTipoDocumento.getId(), 
                     bean.getAfiliado().getDocumentoNumero());
            if (afiliado != null && bean.getAfiliado()
                    .getFechaNacimiento().equals(afiliado.getFechaNacimiento())) {
                GsAfiliado gsAfiliado = castAfiliadoGS(afiliado);
                bean.setAfiliado(gsAfiliado);
                bean.setHabilitarSeccionResultados(true);
            }else{
            bean.addError("Afiliado no encontrado");
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
        gsAfiliado.setResidenciaUbicacion(asegAfiliado.getResidenciaUbicacion());
        gsAfiliado.setResidenciaUbicacionNombre(asegAfiliado.getResidenciaUbicacion().getNombre());
        gsAfiliado.setResidenciaDireccion(asegAfiliado.getDireccionDescripcion());
        gsAfiliado.setResidenciaZonaTipo(asegAfiliado.getZona());
        gsAfiliado.setAtencionUbicacion(asegAfiliado.getAfiliacionUbicacion());
        gsAfiliado.setAtencionUbicacionNombre(asegAfiliado.getAfiliacionUbicacion().getNombre());        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        return gsAfiliado;
    }

    private void guardar(SolicitudAutorizacionBean bean) {
        try {
            String ruta = PropApl.getInstance().get(PropApl.GS_SOLICITUDES_CARGA);
            if (ruta == null || ruta.isEmpty()) {
                throw new Exception("No esta configurada la ruta para los adjuntos de contratos");
            }
            //Insertar afiliado
            bean.getAfiliado().setId(getGsAfiliadoRemoto().insertar(bean.getAfiliado()));
            bean.getObjeto().setGsAfiliado(bean.getAfiliado());
            //Consultar próxima asignación
            GsAsignacionUsuario gsAsignacionUsuario = getGsSolicitudRemoto().proximaAsignacion(GsSolicitud.TIPO_SOLICITUD_AUTORIZACION, bean.getAfiliado().getAtencionUbicacion().getId());
            if (gsAsignacionUsuario == null) {
                bean.getObjeto().setUsuario(new Usuario(GsSolicitud.USUARIO_ID_DEFECTO));
                bean.getObjeto().setGsZona(null);
            } else {
                bean.getObjeto().setUsuario(new Usuario(gsAsignacionUsuario.getUsuarioId()));
                bean.getObjeto().setGsZona(new GsZona(gsAsignacionUsuario.getZonasId()));
            }
            bean.getObjeto().setTipo(GsSolicitud.TIPO_SOLICITUD_AUTORIZACION);
            bean.getObjeto().setNombre(bean.getObjeto().getTipoStr());
            bean.getObjeto().setEstado(GsSolicitud.ESTADO_REGISTRADO);
            if (!bean.getObjeto().getEnviarCorreo() && !bean.getObjeto().getEnviarMensaje()) {
                bean.getObjeto().setNotificacion(GsSolicitud.TIPO_NOTIFICACION_NINGUNA);
            }else if (bean.getObjeto().getEnviarCorreo() && bean.getObjeto().getEnviarMensaje()) {
                bean.getObjeto().setNotificacion(GsSolicitud.TIPO_NOTIFICACION_AMBOS);
            }else if (bean.getObjeto().getEnviarCorreo() && !bean.getObjeto().getEnviarMensaje()) {
                bean.getObjeto().setNotificacion(GsSolicitud.TIPO_NOTIFICACION_EMAIL);
            }else if (!bean.getObjeto().getEnviarCorreo() && bean.getObjeto().getEnviarMensaje()) {
                bean.getObjeto().setNotificacion(GsSolicitud.TIPO_NOTIFICACION_SMS);
            }
            bean.getObjeto().setFechaHoraCrea(new Date());
            bean.getObjeto().setId(getGsSolicitudRemoto().insertar(bean.getObjeto()));
            //Crear archivos 
            for (GsAdjunto gsAdjunto : bean.getObjeto().getListaGsAdjuntos()) {
                //Generar nombre del archivo
                SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
                StringBuilder nombreArchivo = new StringBuilder();
                nombreArchivo
                        .append(bean.getObjeto().getGsAfiliado().getDocumentoNumero())
                        .append("_")
                        .append(bean.getObjeto().getId())
                        .append("_")
                        .append(GsSolicitud.TIPO_SOLICITUD_AUTORIZACION)
                        .append("_")
                        .append(gsAdjunto.getTipo())
                        .append("_")
                        .append(sdf.format(new Date()))
                        .append(gsAdjunto.getExtensionAdjunto());
                gsAdjunto.setArchivo(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
                gsAdjunto.setRuta(ruta);
                gsAdjunto.setGsSolicitud(new GsSolicitud(bean.getObjeto().getId()));
                File archivo = new File(ruta, gsAdjunto.getArchivo());
                OutputStream destino = new FileOutputStream(archivo);
                IOUtils.copy(gsAdjunto.getAdjuntoStream(), destino);
                IOUtils.closeQuietly(gsAdjunto.getAdjuntoStream());
                IOUtils.closeQuietly(destino);
                gsAdjunto.setId(getGsAdjuntoRemoto().insertar(gsAdjunto));
            }
            //Crear registro de envío de SMS
//            crearNotificaciones(bean, bean.getObjeto().getId());
            bean.addMensaje("El registro se guardo correctamente con el identificador de radicado: ".concat(bean.getObjeto().getId().toString()));
        } catch (FileNotFoundException ex) {
            bean.addError("Error al subir un adjunto. " + ex.getMessage());
        } catch (Exception ex) {
            bean.addError("Se presento un error al momento de guardar. " + ex.getMessage());
        }
    }
    
    @Override
    public void cargaInicial(SolicitudAutorizacionBean bean) {
        try {
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setUbicaciones(bean.getUbicacionSingle().getListaMunicipiosAntioquia());
            bean.setUbicacionesRecursiva(bean.getUbicacionSingle().getHashMunicipiosAntioquia());
        } catch (Exception ex) {
            bean.addError("Error carga inicial : " + ex.getMessage());
        }
    }

//    private GsAfiliado cargarAfiliado(Afiliado afiliadoService) {
//        GsAfiliado gsAfiliado;
//        try {
//            gsAfiliado = new GsAfiliado();
//            gsAfiliado.setDocumentoTipo(afiliadoService.getTipoDocumento());
//            gsAfiliado.setDocumentoNumero(afiliadoService.getDocumento());
//            gsAfiliado.setFechaNacimiento(afiliadoService.getFechaNacimiento());
//            gsAfiliado.setPrimerNombre(afiliadoService.getPrimerNombre());
//            gsAfiliado.setSegundoNombre(afiliadoService.getSegundoNombre());
//            gsAfiliado.setPrimerApellido(afiliadoService.getPrimerApellido());
//            gsAfiliado.setSegundoApellido(afiliadoService.getSegundoApellido());
////            autorizacion.setGsZona(new GsZona());
////            autorizacion.getGsZona().setDescripcion(afiliadoService.getZonaDescripcion());
//            gsAfiliado.setSexo(afiliadoService.getSexoDescripcion());
////            gsAfiliado.setResidenciaPrefijoDepartamento(afiliadoService.getPrefijoDepartamento());
////            gsAfiliado.setResidenciaPrefijoMunicipio(afiliadoService.getPrefijoMunicipio());
//            gsAfiliado.setResidenciaUbicacion(
//                    getUbicacionRemoto().consultarMunicipiosPorPrefijo(
//                            afiliadoService.getPrefijoDepartamento(),
//                            afiliadoService.getPrefijoMunicipio()
//                    )
//            );
//            gsAfiliado.setAtencionUbicacion(
//                    getUbicacionRemoto().consultarMunicipiosPorPrefijo(
//                            afiliadoService.getPrefijoDepartamentoAfiliacion(),
//                            afiliadoService.getPrefijoMunicipioAfiliacion()
//                    )
//            );
//            gsAfiliado.setResidenciaDireccion(afiliadoService.getDireccion());
//        } catch (Exception ex) {
//            gsAfiliado = null;
//        }
//        return gsAfiliado;
//    }

}
