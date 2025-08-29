/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.crue.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2RescateGestion;
import com.saviasaludeps.savia.dominio.crue.AuTipoRescate;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucEgresoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionAdversoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionEstanciaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionInoportunidadRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionObjecionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionPatologiaRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionServicioRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucIngresoRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucJustificacionEstanciasProlongadaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorProfesionalRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2Remoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateGestionRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2RescateRemoto;
import com.saviasaludeps.savia.negocio.especial.PeSugeridoAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.especial.PeTelefonosRemoto;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.crue.bean.RescateBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author iavenegas
 */
public class RescateImpl extends AccionesBO implements RescateIface {

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AuAnexo2RescateRemoto getAuAnexo2RescateRemoto() throws Exception {
        return (AuAnexo2RescateRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateServicio", AuAnexo2RescateRemoto.class.getName());
    }

    private AuAnexo2RescateGestionRemoto getAuAnexo2RescateGestionRemoto() throws Exception {
        return (AuAnexo2RescateGestionRemoto) RemotoEJB.getEJBRemoto("AuAnexo2RescateGestionServicio", AuAnexo2RescateGestionRemoto.class.getName());
    }

    private AuAnexo3Remoto getAuAnexo3Remoto() throws Exception {
        return (AuAnexo3Remoto) RemotoEJB.getEJBRemoto("AuAnexo3Servicio", AuAnexo3Remoto.class.getName());
    }

    private AuAnexo2Remoto getAuAnexo2Remoto() throws Exception {
        return (AuAnexo2Remoto) RemotoEJB.getEJBRemoto("AuAnexo2Servicio", AuAnexo2Remoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private CntPrestadorProfesionalRemoto getCntPrestadorProfesionalRemoto() throws Exception {
        return (CntPrestadorProfesionalRemoto) RemotoEJB.getEJBRemoto("CntPrestadorProfesionalServicio", CntPrestadorProfesionalRemoto.class.getName());
    }

//    hostipalizacion
    private AucHospitalizacionServicioRemoto getAucHospitalizacionServicioRemoto() throws Exception {
        return (AucHospitalizacionServicioRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionServicioServicio", AucHospitalizacionServicioRemoto.class.getName());
    }

    private AucEgresoRemoto getAucEgresoRemoto() throws Exception {
        return (AucEgresoRemoto) RemotoEJB.getEJBRemoto("AucEgresoServicio", AucEgresoRemoto.class.getName());
    }

    private AucHospitalizacionSeguimientoRemoto getAucHospitalizacionSeguimientoRemoto() throws Exception {
        return (AucHospitalizacionSeguimientoRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionSeguimientoServicio", AucHospitalizacionSeguimientoRemoto.class.getName());
    }

    private AucHospitalizacionInoportunidadRemoto getAucHospitalizacionInoportunidadRemoto() throws Exception {
        return (AucHospitalizacionInoportunidadRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionInoportunidadServicio", AucHospitalizacionInoportunidadRemoto.class.getName());
    }

    private AucHospitalizacionObjecionRemoto getAucHospitalizacionObjecionRemoto() throws Exception {
        return (AucHospitalizacionObjecionRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionObjecionServicio", AucHospitalizacionObjecionRemoto.class.getName());
    }

    private AucHospitalizacionRemoto getAucHospitalizacionRemoto() throws Exception {
        return (AucHospitalizacionRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionServicio", AucHospitalizacionRemoto.class.getName());
    }

    private AucHospitalizacionAdversoRemoto getAucHospitalizacionAdversoRemoto() throws Exception {
        return (AucHospitalizacionAdversoRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionAdversoServicio", AucHospitalizacionAdversoRemoto.class.getName());
    }

    private AucIngresoRemoto getAucIngresoRemoto() throws Exception {
        return (AucIngresoRemoto) RemotoEJB.getEJBRemoto("AucIngresoServicio", AucIngresoRemoto.class.getName());
    }

    private AucAfiliadoRemoto getAucAfiliadoRemoto() throws Exception {
        return (AucAfiliadoRemoto) RemotoEJB.getEJBRemoto("AucAfiliadoServicio", AucAfiliadoRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private AucHospitalizacionPatologiaRemoto getAucHospitalizacionPatologiaRemoto() throws Exception {
        return (AucHospitalizacionPatologiaRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionPatologiaServicio", AucHospitalizacionPatologiaRemoto.class.getName());
    }

    private AucHospitalizacionEstanciaRemoto getAucHospitalizacionEstanciaRemoto() throws Exception {
        return (AucHospitalizacionEstanciaRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionEstanciaServicio", AucHospitalizacionEstanciaRemoto.class.getName());
    }

    private PeSugeridoAdjuntoRemoto getPeSugeridoAdjuntoRemoto() throws Exception {
        return (PeSugeridoAdjuntoRemoto) RemotoEJB.getEJBRemoto("PeSugeridoAdjuntoServicio", PeSugeridoAdjuntoRemoto.class.getName());
    }

    private PeTelefonosRemoto getPeTelefonosRemoto() throws Exception {
        return (PeTelefonosRemoto) RemotoEJB.getEJBRemoto("PeTelefonosServicio", PeTelefonosRemoto.class.getName());
    }

    private AucJustificacionEstanciasProlongadaRemoto getAucJustificacionEstanciasProlongadaRemoto() throws Exception {
        return (AucJustificacionEstanciasProlongadaRemoto) RemotoEJB.getEJBRemoto("AucJustificacionEstanciasProlongadaServicio", AucJustificacionEstanciasProlongadaRemoto.class.getName());
    }
    //
     private AuSolicitudAdjuntoRemoto getAuSolicitudAdjuntoRemoto() throws Exception {
        return (AuSolicitudAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuSolicitudAdjuntoServicio", AuSolicitudAdjuntoRemoto.class.getName());
    }
    @Override
    public void Accion(RescateBean bean) {
        if (super.ValidarSesion(bean)) {
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(0).setEmpresaId(null);
            } else {
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
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
                            verGestiones(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_ADICIONAL_1://Gestionar
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearGestion(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarGestion(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
//                            cambiarEstadoGestionar(bean);
                            break;
                        case RescateBean.ACCION_CREAR_ADJUNTO_RESCATE:
                            crearAdjunto(bean);
                            break;
                        case RescateBean.ACCION_GUARDAR_ADJUNTO_RESCATE:
                            guardarAdjuntoRescate(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2://cancelar
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            crearGestion(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            cancelar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(RescateBean bean) {
        try {
            //Maestro Tipo Documento
            bean.setListaTiposDocumentoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            //Maestro motivos
            bean.setListaMaeMotivos(getMaestroRemoto().consultarPorTipo("94"));//TODO: cambiar cuando el tipo sea agregado en MaestroTipo
            //para municipio y departamento
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setListadoTipoRescates(AuConstantes.getListadoTipoRescates());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void verAnexo3(RescateBean bean) {
        try {
            bean.setObjetoAnexo3(getAuAnexo3Remoto().consultar(bean.getObjetoAnexo3().getId()));
            bean.getObjetoAnexo3().getAsegAfiliadoId().setEdad(Util.calcularEdad(bean.getObjetoAnexo3().getAsegAfiliadoId().getFechaNacimiento()));

        } catch (Exception e) {
            bean.addError("Error al consultar anexo3");
        }
    }

    @Override
    public void verAnexo2(RescateBean bean) {
        try {
            bean.setObjetoAnexo2(getAuAnexo2Remoto().consultar(bean.getObjetoAnexo2().getId()));

            bean.getObjetoAnexo2().getAsegAfiliado().setEdad(Util.calcularEdad(bean.getObjetoAnexo2().getAsegAfiliado().getFechaNacimiento()));
            if (bean.getObjetoAnexo2().getCntProfesionales() != null) {
                bean.setObjetoProfesionalPrestador(getCntPrestadorProfesionalRemoto().consultarPorProfesionalYPrestador(
                        bean.getObjetoAnexo2().getCntProfesionales().getId(),
                        bean.getObjetoAnexo2().getCntPrestadorSede().getCntPrestador().getId()
                ));
            }
        } catch (Exception e) {
            bean.addError("Error al consultar anexo2");
        }
    }

    @Override
    public void verHospitalizacion(RescateBean bean) {
        try {
            bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            bean.setObjetoHospitalizacion(getAucHospitalizacionRemoto().consultar(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucAfiliadoId(getAucAfiliadoRemoto().consultar(bean.getObjetoHospitalizacion().getAucAfiliadoId().getId()));
            bean.getObjetoHospitalizacion().setAucIngresoId(getAucIngresoRemoto().consultar(bean.getObjetoHospitalizacion().getAucIngresoId().getId()));
            bean.getObjetoHospitalizacion().getAucIngresoId().setUltimaFechaIngreso(bean.getObjetoHospitalizacion().getAucIngresoId().getFechaIngreso());
            if (bean.getObjetoHospitalizacion().getAucIngresoId().getMaeRemisionNoPertinenteId() != null) {
                bean.getObjetoHospitalizacion().getAucIngresoId().setHabilitarDescripcion(1);
            } else {
                bean.getObjetoHospitalizacion().getAucIngresoId().setHabilitarDescripcion(0);
            }
            if (bean.getObjetoHospitalizacion().getAucEgresoId() != null && bean.getObjetoHospitalizacion().getAucEgresoId().getId() != null) {
                bean.getObjetoHospitalizacion().setAucEgresoId(getAucEgresoRemoto().consultar(bean.getObjetoHospitalizacion().getAucEgresoId().getId()));
            }
            bean.getObjetoHospitalizacion().setCntPrestadorId(getCntPrestadorRemoto().consultar(bean.getObjetoHospitalizacion().getCntPrestadorId().getId()));
            bean.getObjetoHospitalizacion().setCntPrestadorSedeId(getCntPrestadorSedeRemoto().consultar(bean.getObjetoHospitalizacion().getCntPrestadorSedeId().getId()));
            bean.getObjetoHospitalizacion().setAucHosptalizacionAdversoList(getAucHospitalizacionAdversoRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionSeguimientoList(getAucHospitalizacionSeguimientoRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionInoportunidadList(getAucHospitalizacionInoportunidadRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionObjecionList(getAucHospitalizacionObjecionRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionPatologiaList(getAucHospitalizacionPatologiaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionServicioList(getAucHospitalizacionServicioRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionEstanciaList(getAucHospitalizacionEstanciaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
            bean.getObjetoHospitalizacion().setAucHospitalizacionJustificacionEstanciasProlongadaList(getAucJustificacionEstanciasProlongadaRemoto().consultarPorIdHospitalizacion(bean.getObjetoHospitalizacion().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listar(RescateBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getAuAnexo2RescateRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getAuAnexo2RescateRemoto().consultarLista(bean.getParamConsulta(0)));
            Date fechaActual = new Date();
            for (AuAnexo2Rescate registro : bean.getRegistros()) {
                registro.setColorAnexo2Anexo3("white");
                if (registro.getFuenteOrigen() == AuAnexo2Rescate.FUENTE_ORIGEN_ANEXO2
                        && (registro.getEstado() == AuAnexo2Rescate.ESTADO_PENDIENTE || registro.getEstado() == AuAnexo2Rescate.ESTADO_GESTION)) {
                    boolean tiene = getAuAnexo2RescateRemoto().tieneRescateAnexo3(
                            registro.getAsegAfiliado().getId(), registro.getCntPrestadorSedeOrigen().getId(), registro.getFechaHoraCrea()
                    );
                    if (!tiene) {
                        long milisegundos = Math.abs(fechaActual.getTime() - registro.getFechaHoraCrea().getTime());
                        long horas = TimeUnit.HOURS.convert(milisegundos, TimeUnit.MILLISECONDS);
                        if (horas > 24) {
                            registro.setColorAnexo2Anexo3("blue");
                        }
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(RescateBean bean) {
        try {
            bean.setObjeto(getAuAnexo2RescateRemoto().consultar(bean.getObjeto().getId()));
            //complementar
            bean.getObjeto().getAsegAfiliado().setEdad(Util.calcularEdad(bean.getObjeto().getAsegAfiliado().getFechaNacimiento()));
            bean.setEstadoRescate(bean.getObjeto().getEstado());
            contactosAfiliado(bean);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void crearGestion(RescateBean bean) {
        try {
            bean.setObjetoGestion(new AuAnexo2RescateGestion());
            bean.setEstadoRescate(bean.getObjeto().getEstado());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verGestiones(RescateBean bean) {
        try {
            bean.getParamConsulta(1).setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsulta(1).setCantidadRegistros(getAuAnexo2RescateGestionRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosGestiones(getAuAnexo2RescateGestionRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarGestion(RescateBean bean) {
        try {
            //Consultar el estado real del AuAnexo2Rescate
            AuAnexo2Rescate rescateActual = getAuAnexo2RescateRemoto().consultar(bean.getObjeto().getId());
            if (validarEstado(rescateActual.getEstado())) {
                bean.getObjeto().setEstado(bean.getEstadoRescate());
                guardarGestionCambio(bean, AuAnexo2RescateGestion.TIPO_GESTION, bean.getObjetoGestion().getObservacion());

                cambiarEstadoGestionar(bean, rescateActual);

                bean.getObjeto().setAuAnexo2RescateGestionList(
                        getAuAnexo2RescateGestionRemoto().consutarGestionesPorRescate(bean.getObjeto().getId())
                );
                bean.addMensaje("El registro se creo correctamente");
            } else {
                bean.addError("El rescate ya fue gestionado y se encuentra en estado " + rescateActual.getEstadoStr());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void crearAdjunto(RescateBean bean) {
        try {
            bean.setObjectoAdjunto(new AuSolicitudAdjunto());
            bean.setListaTiposRescateAdjuntos(getMaestroRemoto().consultarPorTipo(MaestroTipo.CR_ADJUNTO_TIPO));
            bean.setHashTiposRescateAdjuntos(AuConstantes.obtenerHashMaestro(bean.getListaTiposRescateAdjuntos()));
            bean.setAuSolicitudAdjunto(new ArrayList<>());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void guardarAdjuntoRescate(RescateBean bean) {
        try {
            String ruta = PropApl.getInstance().get(PropApl.AU_RUTA_ADJUNTOS_RESCATES);
            if (ruta == null || ruta.isEmpty()) {
                bean.addError("No esta configurada la ruta para los manuales del sistema");
                return;
            }
            if (bean.getAuSolicitudAdjunto().isEmpty()) {
                bean.addError("No hay adjuntos para guardar");
                return;
            }
            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            StringBuilder nombreArchivo = new StringBuilder();
            StringBuilder prefijoNombreArchivo = new StringBuilder();
            prefijoNombreArchivo = nombreArchivo
                    .append(bean.getObjeto().getAsegAfiliado().getMaeTipoDocumentoCodigo())
                    .append(bean.getObjeto().getAsegAfiliado().getNumeroDocumento());
                    
             for (AuSolicitudAdjunto adjunto : bean.getAuSolicitudAdjunto()) {
                if (adjunto.getId() == null) {
                    bean.auditoriaGuardar(adjunto);
                    Maestro tipoArchivo = bean.getHashTiposRescateAdjuntos().get(adjunto.getMaeTipoArchivoId());
                    if(tipoArchivo != null){
                        adjunto.setMaeTipoArchivoCodigo(tipoArchivo.getValor());
                        adjunto.setMaeTipoArchivoValor(tipoArchivo.getNombre());
                    }
                    prefijoNombreArchivo = nombreArchivo.append(bean.getHashTiposRescateAdjuntos().get(adjunto.getMaeTipoArchivoId()).getValor())
                    .append(sdf.format(new Date()));
                    nombreArchivo = new StringBuilder(prefijoNombreArchivo.toString()); 
                    adjunto.setRuta(ruta);
                    adjunto.setExiste(true);
                    adjunto.setOrigen(AuSolicitudAdjunto.ORIGEN_RESCATES);
                    adjunto.setArchivo(nombreArchivo.append(adjunto.getExtension()).toString());
                    if (bean.getObjeto().getId() > 0) {
                        adjunto.setAuAnexo2Rescate(bean.getObjeto());
                    }
                    // crea archivo en el servidor
                    File archivo = new File(ruta, adjunto.getArchivo());
                    OutputStream destino = new FileOutputStream(archivo);
                    IOUtils.copy(adjunto.getAdjuntoStream(), destino);
                    IOUtils.closeQuietly(adjunto.getAdjuntoStream());
                    IOUtils.closeQuietly(destino);
                    int idAdjunto = getAuSolicitudAdjuntoRemoto().insertar(adjunto);
                    adjunto.setId(idAdjunto);
                    bean.getObjeto().getAuSolicitudAdjuntosList().add(adjunto);
                }
            }
            bean.addMensaje("El registro se creo correctamente");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void cambiarEstadoGestionar(RescateBean bean, AuAnexo2Rescate rescateActual) {
        try {

            if (!Objects.equals(bean.getObjeto().getEstado(), rescateActual.getEstado())) {
                bean.auditoriaModificar(bean.getObjeto());
                getAuAnexo2RescateRemoto().actualizarEstado(bean.getObjeto());
                if (bean.getObjeto().getEstado() == AuAnexo2Rescate.ESTADO_RESCATADO) {
                    getAuAnexo2RescateRemoto().actualizarFechaRescate(bean.getObjeto().getId(), bean.getObjeto().getFechaHoraRescate());
                }
                guardarGestionCambio(bean, AuAnexo2RescateGestion.TIPO_CAMBIO_ESTADO, bean.getObjeto().getEstadoStr());
                bean.addMensaje("Se gestionó el estado correctamente");
            }
            bean.getObjeto().setDescripcion(bean.getObjetoGestion().getObservacion());
            getAuAnexo2RescateRemoto().actualizarDescripcion(bean.getObjeto().getId(), bean.getObjeto().getDescripcion());

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void cancelar(RescateBean bean) {
        try {
            //Consultar el estado real del AuAnexo2Rescate
            AuAnexo2Rescate rescateActual = getAuAnexo2RescateRemoto().consultar(bean.getObjeto().getId());
            if (validarEstado(rescateActual.getEstado())) {
                bean.getObjeto().setEstado(AuAnexo2Rescate.ESTADO_CANCELADO);
                bean.auditoriaModificar(bean.getObjeto());
                getAuAnexo2RescateRemoto().actualizarEstado(bean.getObjeto());
                //gestion
                guardarGestionCambio(bean, AuAnexo2RescateGestion.TIPO_GESTION, bean.getObjeto().getDescripcion());
                guardarGestionCambio(bean, AuAnexo2RescateGestion.TIPO_CAMBIO_ESTADO, bean.getObjeto().getEstadoStr());
                bean.addMensaje("Se cancelo el rescate correctamente");
            } else {
                bean.addError("El rescate ya fue gestionado y se encuentra en estado " + rescateActual.getEstadoStr());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean validarEstado(int estado) {
        return estado == AuAnexo2Rescate.ESTADO_PENDIENTE
                || AuAnexo2Rescate.ESTADO_GESTION == estado;
    }

    private void contactosAfiliado(RescateBean bean) {
        if (bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto() != null) {
            bean.setTelefonoFijoAfiliado(
                    bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto().stream()
                            .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_TELEFONO))
                            .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
            );
            bean.setTelefonoMovilAfiliado(
                    bean.getObjeto().getAsegAfiliado().getListaAsegAfiliadoContacto().stream()
                            .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.CODIGO_CONTACTO_CELULAR))
                            .findFirst().orElse(new AsegAfiliadoContacto()).getNumeroContacto()
            );
        } else {
            bean.setTelefonoFijoAfiliado(null);
            bean.setTelefonoMovilAfiliado(null);
        }
    }

    private void guardarGestionCambio(RescateBean bean, short tipo, String desc) throws Exception {
        AuAnexo2RescateGestion gestionCambio = new AuAnexo2RescateGestion();
        gestionCambio.setAuAnexo2Rescate(bean.getObjeto());
        gestionCambio.setTipo(tipo);
        gestionCambio.setObservacion(desc);
        gestionCambio.setFechaHoraGestion(new Date());
        //datos asignados cuando la gestion sea tipo cambio de estado, el rescate tenga estado rescatado y el tipo de rescate es gestion capita 
        if (tipo == AuAnexo2RescateGestion.TIPO_CAMBIO_ESTADO && bean.getObjeto().getEstado() == AuAnexo2Rescate.ESTADO_RESCATADO 
                && bean.getObjeto().getTipoRescate() == AuTipoRescate.GESTION_CAPITA.getId()) {
            gestionCambio.setFechaHoraDireccionamiento(bean.getObjetoGestion().getFechaHoraDireccionamiento());
            
            Maestro maestroMotivo = getMaestroRemoto().consultar(bean.getObjetoGestion().getMaeMotivoRescateId());
            gestionCambio.setMaeMotivoRescateId(maestroMotivo.getId());
            gestionCambio.setMaeMotivoRescateCodigo(maestroMotivo.getValor());
            gestionCambio.setMaeMotivoRescateValor(maestroMotivo.getNombre());
            gestionCambio.setMaeMotivoRescateTipo(maestroMotivo.getTipo());
        }
        bean.auditoriaGuardar(gestionCambio);
        getAuAnexo2RescateGestionRemoto().insertar(gestionCambio);
    }

}
