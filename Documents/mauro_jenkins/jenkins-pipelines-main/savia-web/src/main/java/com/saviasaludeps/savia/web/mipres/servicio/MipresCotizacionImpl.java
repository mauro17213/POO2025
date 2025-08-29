package com.saviasaludeps.savia.web.mipres.servicio;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionAdjunto;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionItem;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpPrescripcionDetalleRemoto;
import com.saviasaludeps.savia.web.mipres.bean.CotizacionMipresBean;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.IOUtils;

/**
 * @author BSGomez
 */
public class MipresCotizacionImpl extends AccionesBO implements MipresCotizacionIface {

    private MpPrescripcionDetalleRemoto getMpPrescripcionDetalleRemoto() throws Exception {
        return (MpPrescripcionDetalleRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionDetalleServicio", MpPrescripcionDetalleRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private AuCotizacionRemoto getAuCotizacionRemoto() throws Exception {
        return (AuCotizacionRemoto) RemotoEJB.getEJBRemoto("AuCotizacionServicio", AuCotizacionRemoto.class.getName());
    }

    private AuCotizacionItemRemoto getAuCotizacionItemRemoto() throws Exception {
        return (AuCotizacionItemRemoto) RemotoEJB.getEJBRemoto("AuCotizacionItemServicio", AuCotizacionItemRemoto.class.getName());
    }

    private AuCotizacionAdjuntoRemoto getAuCotizacionAdjuntoRemoto() throws Exception {
        return (AuCotizacionAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuCotizacionAdjuntoServicio", AuCotizacionAdjuntoRemoto.class.getName());
    }

    @Override
    public void Accion(CotizacionMipresBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR: {
                    listar(bean);
                    break;
                }
                case Url.ACCION_GUARDAR: {
                    guardar(bean);
                    break;
                }
                case Url.ACCION_CREAR: {
//                    crearAuditoria(bean);
                    break;
                }
                case Url.ACCION_VER: {
                    ver(bean);
                    break;
                }
                case Url.ACCION_ADICIONAL_1: {
                    gestionar(bean);
                    break;
                }
                case Url.ACCION_ADICIONAL_2: {
                    rechazar(bean);
                    break;
                }
                default:
                    break;
            }
        }
    }

    @Override
    public void CargaInicial(CotizacionMipresBean bean) {
        try {
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void completarAfiliado(CotizacionMipresBean bean) {
        try {
            bean.getObjetoPrescripcion().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjetoPrescripcion().getAsegAfiliado().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al consultar el afiliado");
        }
    }

    @Override
    public void listarPrestador(CotizacionMipresBean bean) {
        try {
            bean.getParamConsulta2().getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta2().setParametroConsulta1(bean.getConexion().getEmpresa().getCodigoHabilitacion());
            bean.getParamConsulta2().setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta2()));
            bean.setListaPrestadoresSedes(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsulta2()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaMedicamento(CotizacionMipresBean bean) {
        try {
            bean.getParamConsultaMaMedicamento().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaMaMedicamento(bean.getParamConsultaMaMedicamento()));
            bean.setRegistroMaMedicamento(getMpPrescripcionDetalleRemoto().consultarListaMaMedicamento(bean.getParamConsultaMaMedicamento()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaTecnologia(CotizacionMipresBean bean) {
        try {
            bean.getParamConsultaMaTecnologia().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaTecnologia(bean.getParamConsultaMaTecnologia()));
            bean.setRegistroMaTecnologia(getMpPrescripcionDetalleRemoto().consultarListaMaTecnologia(bean.getParamConsultaMaTecnologia()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaTecnologiaMipres(CotizacionMipresBean bean) {
        try {
            bean.getParamConsultaMaTecnologiaMipres().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaMaTecnologiaMipres(bean.getParamConsultaMaTecnologiaMipres()));
            bean.setRegistroMaTecnologiaMipres(getMpPrescripcionDetalleRemoto().consultarListaMaTecnologiaMipres(bean.getParamConsultaMaTecnologiaMipres()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaInsumoMipres(CotizacionMipresBean bean) {
        try {
            bean.getParamConsultaMaInsumo().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaMaInsumoMipres(bean.getParamConsultaMaInsumo()));
            bean.setRegistroMaInsumoMipres(getMpPrescripcionDetalleRemoto().consultarListaMaInsumoMipres(bean.getParamConsultaMaInsumo()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaPaqueteMipres(CotizacionMipresBean bean) {
        try {
            bean.getParamConsultaMaPaquete().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaMaPaqueteMipres(bean.getParamConsultaMaPaquete()));
            bean.setRegistroMaPaqueteMipres(getMpPrescripcionDetalleRemoto().consultarListaMaPaqueteMipres(bean.getParamConsultaMaPaquete()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listar(CotizacionMipresBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaCotizacion(bean.getParamConsulta()));
            bean.setRegistros(getMpPrescripcionDetalleRemoto().consultarListaCotizaciones(bean.getParamConsulta()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    public void ver(CotizacionMipresBean bean) {
        try {
            bean.setObjeto(getMpPrescripcionDetalleRemoto().consultarParaCotizacion(bean.getObjeto().getId()));
            if (bean.getObjeto().getIdCotizacion() != null) {
                bean.setObjetoCotizacion(getMpPrescripcionDetalleRemoto().consultarAuCotizacion(bean.getObjeto().getIdCotizacion()));
                bean.setMpCotizacionAdjuntosList(getAuCotizacionAdjuntoRemoto().listarAdjuntosByIdCotizacion(bean.getObjeto().getIdCotizacion()));
                bean.setTipoEditable(true);
            } else {
                bean.setTipoEditable(false);
            }
            bean.setObjetoPrescripcion(getMpPrescripcionDetalleRemoto().consultarPrescripcion(bean.getObjeto().getIdPrescripcion()));
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    public void gestionar(CotizacionMipresBean bean) {
        try {
            bean.setObjeto(getMpPrescripcionDetalleRemoto().consultarParaCotizacion(bean.getObjeto().getId()));
            bean.setObjetoCotizacion(new AuCotizacion());
            bean.setMpCotizacionAdjuntosList(new ArrayList());
            bean.getObjetoCotizacion().setActivo(true);
            bean.setObjetoPrescripcion(getMpPrescripcionDetalleRemoto().consultarPrescripcion(bean.getObjeto().getIdPrescripcion()));
            bean.getObjetoCotizacion().setAsegAfiliadosId(new AsegAfiliado(bean.getObjetoPrescripcion().getAsegAfiliado().getId()));
            bean.getObjetoCotizacion().setMpPrescripcionId(new MpPrescripcion(bean.getObjetoPrescripcion().getId()));
            bean.getObjetoCotizacion().setFechaInicioVigencia(new Date());
            bean.getObjetoCotizacion().setFuenteOrigen(CotizacionMipresBean.FUENTE_ORIGEN_MIPRES);
            bean.getObjetoCotizacion().setMpNumeroPrescripcion(bean.getObjeto().getNumeroPrescripcion());
            bean.getObjetoCotizacion().setTipoTecnologiaMipres(bean.getObjeto().getTipoTecnologia());
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    public void rechazar(CotizacionMipresBean bean) {
        try {
            bean.setObjeto(getMpPrescripcionDetalleRemoto().consultarParaCotizacion(bean.getObjeto().getId()));

            if (!getMpPrescripcionDetalleRemoto()
                    .consultarDireccionamientoCot(
                            bean.getObjeto().getIdPrescripcion(),
                            bean.getObjeto().getIdItem(),
                            bean.getObjeto().getTipoTecnologia())) {
                if (bean.getObjeto().getEstado() != CotizacionMipresBean.ESTADO_RECHAZO_COTIZACION) {
                    getMpPrescripcionDetalleRemoto().cambiarEstadoCotizacionMp(bean.getObjeto().getId(), CotizacionMipresBean.ESTADO_RECHAZO_COTIZACION);
                    getMpPrescripcionDetalleRemoto().cambiarEstadoItem(bean.getObjeto().getIdItem(), bean.getObjeto().getTipoTecnologia(), CotizacionMipresBean.ESTADO_RECHAZO_COTIZACION);
                    getMpPrescripcionDetalleRemoto().asignarUsuarioRechaza(bean.getObjeto().getId(), bean.getConexion().getUsuario().getUsuarioNombre(), bean.getConexion().getIp());
                bean.addMensaje("Cotizaciòn Rechazada Con Exito");
                } else {
                    bean.addMensaje("Cotizaciòn Ya Fue Rechazada");
                }
            } else {
                bean.addError("Acciòn No Permitida - Cotizaciòn Ya Fue Direccionada");
            }
        } catch (Exception ex) {
            bean.addError(ex.toString());
        }
    }

    private void guardar(CotizacionMipresBean bean) {

        try {
            if (bean.getObjetoCotizacion().getValorTecnologia().longValue() < 1) {
                bean.addError("Debe ingresar un valor superior a 0");
                return;
            }
            if (bean.getObjetoCotizacion().getFechaInicioVigencia().after(bean.getObjetoCotizacion().getFechaFinVigencia())) {
                bean.addError("Fecha Inicio no puede ser superior a Fecha Final");
                return;
            }
            bean.auditoriaGuardar(bean.getObjetoCotizacion());

            int idCotizacion = getAuCotizacionRemoto().insertar(bean.getObjetoCotizacion());
            bean.getObjetoCotizacion().setId(idCotizacion);

            AuCotizacionItem itemCotizacion = new AuCotizacionItem();
            itemCotizacion.setAuCotizacionId(new AuCotizacion(idCotizacion));
            itemCotizacion.setAuAnexo3ItemId(null);
            itemCotizacion.setId(null);
            itemCotizacion.setMpPrescripcionItem(bean.getObjeto().getIdItem());
            itemCotizacion.setTipoTecnologiaMipres(bean.getObjeto().getTipoTecnologia());
            bean.auditoriaGuardar(itemCotizacion);
            getAuCotizacionItemRemoto().insertarItem(itemCotizacion);
            if (!bean.getMpCotizacionAdjuntosList().isEmpty()) {
                guardarAdjunto(bean);
            }
            getMpPrescripcionDetalleRemoto().cambiarEstadoCotizacionMp(bean.getObjeto().getId(), CotizacionMipresBean.ESTADO_CON_COTIZACION);
            getMpPrescripcionDetalleRemoto().asignarCotizacionASolicitud(bean.getObjeto().getId(), idCotizacion);
            getMpPrescripcionDetalleRemoto().cambiarEstadoItem(bean.getObjeto().getIdItem(), bean.getObjeto().getTipoTecnologia(), CotizacionMipresBean.ESTADO_CON_COTIZACION);
            getMpPrescripcionDetalleRemoto().asignarUsuarioGestion(bean.getObjeto().getId(), bean.getConexion().getUsuario().getUsuarioNombre(), bean.getConexion().getIp());

        } catch (Exception e) {
        }
        bean.setObjetoCotizacion(new AuCotizacion());
        bean.addMensaje("Cotización Realizada Con Exito");
    }

    private void guardarAdjunto(CotizacionMipresBean bean) {
        try {
            boolean error = false;
            String ruta = PropApl.getInstance().get(PropApl.AU_RUTA_ADJUNTOS_COTIZACIONES);
            //ruta = "C:\\Adjuntos\\";
            if (ruta == null || ruta.isEmpty()) {
                bean.addError("No esta configurada la ruta para los adjuntos");
                error = true;
            }
            if (error) {
                return;
            }
            //Generar nombre del archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            for (AuCotizacionAdjunto adjunto : bean.getMpCotizacionAdjuntosList()) {
                if (adjunto.getId() == null) {
                    String tipoDocumento = adjunto.getMaeTipoArchivoCodigo();
                    nombreArchivo.append(tipoDocumento)
                            .append("_")
                            .append(sdf.format(new Date()));
                    nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
                    adjunto.setArchivo(nombreArchivo.append(adjunto.getExtension()).toString());
                    adjunto.setRuta(ruta);
                    File archivo = new File(ruta, adjunto.getArchivo());
                    OutputStream destino = new FileOutputStream(archivo);
                    IOUtils.copy(adjunto.getAdjuntoStream(), destino);
                    IOUtils.closeQuietly(adjunto.getAdjuntoStream());
                    IOUtils.closeQuietly(destino);
                    adjunto.setAuCotizacionId(new AuCotizacion(bean.getObjetoCotizacion().getId()));
                    adjunto.setRuta(ruta);
                    bean.auditoriaGuardar(adjunto);
                    adjunto.setAdjuntoStream(null);
                    getAuCotizacionAdjuntoRemoto().insertar(adjunto);
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo guardando adjuntos");
        }
    }

    public static String reemplazarCaracteresEspeciales(String palabra) {
        String[] caracteresMalos = {" ", "ñ", "|", "à", "á", "À", "Á", "è", "é", "È", "É", "ì", "í", "Ì", "Í", "ò", "ó", "Ò", "Ó", "ù", "ú", "Ù", "Ú", "\b", "/", ":", "<", "*", "?", ">", "@"};
        String[] caracteresBuenos = {"-", "n", "", "a", "a", "A", "A", "e", "e", "E", "E", "i", "i", "I", "I", "o", "o", "O", "O", "u", "u", "U", "U", "", "", "", "", "", "", "", "a"};
        for (String letraMala : caracteresMalos) {
            if (palabra.contains(letraMala)) {
                palabra = palabra.replace(letraMala, caracteresBuenos[Arrays.asList(caracteresMalos).indexOf(letraMala)]);
            }
        }
        return palabra;
    }

}
