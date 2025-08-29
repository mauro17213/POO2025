package com.saviasaludeps.savia.web.mipres.servicio;

import com.google.gson.Gson;
import com.saviasaludeps.savia.dominio.administracion.GnCorreoEnvio;
import com.saviasaludeps.savia.dominio.administracion.GnSmsEnvio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.mipres.MpAnuladaPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpConsumo;
import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import com.saviasaludeps.savia.dominio.mipres.MpCotizacion;
import com.saviasaludeps.savia.dominio.mipres.MpDescripcionEntregaCoigo;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaFactura;
import com.saviasaludeps.savia.dominio.mipres.MpEntregaSuministro;
import com.saviasaludeps.savia.dominio.mipres.MpNoDireccionado;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionAuditoria;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionDetalle;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionHistorico;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.ReporteDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.ReportePlanManejo;
import com.saviasaludeps.savia.negocio.administracion.GnCorreoEnvioRemoto;
import com.saviasaludeps.savia.negocio.administracion.GnSmsEnvioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpPrescripcionDetalleRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.MpConsumoFalloWsRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.PrescripcionWsRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.prescripcion.MpDireccionamientoWSRemoto;
import com.saviasaludeps.savia.negocio.webservices.mipres.prescripcion.MpPrescripcionTecnologiaWSRemoto;
import com.saviasaludeps.savia.web.mipres.bean.DTO.MpDetalleDTO;

import com.saviasaludeps.savia.web.mipres.bean.MipresBean;
import com.saviasaludeps.savia.web.servicios.webservices.mipres.DireccionamientoBaseDTO;
import com.saviasaludeps.savia.web.servicios.webservices.mipres.FacturacionBaseDTO;
import com.saviasaludeps.savia.web.servicios.webservices.mipres.JuntaMedicaBaseDTO;
import com.saviasaludeps.savia.web.servicios.webservices.mipres.MiPresConsumoExterno;
import com.saviasaludeps.savia.web.servicios.webservices.mipres.NoDireccionamientoBaseDTO;
import com.saviasaludeps.savia.web.servicios.webservices.mipres.ReporteEntregaBaseDTO;
import com.saviasaludeps.savia.web.servicios.webservices.mipres.RespuestaAnulaacionPrescripcionDTO;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Token;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author BSGomez
 */
public class MipresImpl extends AccionesBO implements MipresIface {

    HashMap<String, Maestro> hashMaestroTipoDocumentos;

    public MipresImpl() {
        this.consumoExterno = new MiPresConsumoExterno();
        try {
            hashMaestroTipoDocumentoEmpresas = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA);
            hashMaestroTipoDocumentos = getMaestroRemoto().consultarHashPorTipoValor(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA);
        } catch (Exception e) {
        }
    }

    private MpPrescripcionDetalleRemoto getMpPrescripcionDetalleRemoto() throws Exception {
        return (MpPrescripcionDetalleRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionDetalleServicio", MpPrescripcionDetalleRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private GnCorreoEnvioRemoto getGnCorreoEnvioRemoto() throws Exception {
        return (GnCorreoEnvioRemoto) RemotoEJB.getEJBRemoto("GnCorreoEnvioServicio", GnCorreoEnvioRemoto.class.getName());
    }

    private GnSmsEnvioRemoto getGnSmsEnvioRemoto() throws Exception {
        return (GnSmsEnvioRemoto) RemotoEJB.getEJBRemoto("GnSmsEnvioServicio", GnSmsEnvioRemoto.class.getName());
    }

    private PrescripcionWsRemoto getPrescripcionWsRemoto() throws Exception {
        return (PrescripcionWsRemoto) RemotoEJB.getEJBRemoto("PrescripcionWsServicio", PrescripcionWsRemoto.class
                .getName());
    }

    private MpConsumoFalloWsRemoto getConsumoFallosWsRemoto() throws Exception {
        return (MpConsumoFalloWsRemoto) RemotoEJB.getEJBRemoto("MpConsumoFalloWsServicio", MpConsumoFalloWsRemoto.class.getName());
    }

    private MpPrescripcionTecnologiaWSRemoto getMpPrescripcionTecnologiaWSRemoto() throws Exception {
        return (MpPrescripcionTecnologiaWSRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionTecnologiaWSServicio", MpPrescripcionTecnologiaWSRemoto.class.getName());
    }

    private MpDireccionamientoWSRemoto getMpDireccionamientoWSRemoto() throws Exception {
        return (MpDireccionamientoWSRemoto) RemotoEJB.getEJBRemoto("MpDireccionamientoWSServicio", MpDireccionamientoWSRemoto.class.getName());
    }

    HashMap<String, Maestro> hashMaestroTipoDocumentoEmpresas;
    private MiPresConsumoExterno consumoExterno;
    private final SimpleDateFormat formatoF = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void Accion(MipresBean bean) {
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
                    crearAuditoria(bean);
                    break;
                }

                case Url.ACCION_VER: {
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verItem(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verItemOrigenGestion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            verH(bean);
                            break;
                    }
                    break;
                }

                case Url.ACCION_ADICIONAL_1: {
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            listarPrestadorSede(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            auditoria(bean);
                            break;
                    }
                    break;
                }

                case Url.ACCION_ADICIONAL_2: {
                    Direccionar(bean);
                    break;
                }

                case Url.ACCION_ADICIONAL_3: {
                    Direccionar(bean);
                    break;
                }

                case Url.ACCION_ADICIONAL_10: {
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            sincronizarConsumos(bean);
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            sincronizarDireccionamiento(bean);
                            break;
                    }
                    break;
                }
                case Url.ACCION_ADICIONAL_12: {
                    borradoLogico(bean);
                    break;
                }
                default:
                    break;
            }
        }
    }

    @Override
    public void CargaInicial(MipresBean bean) {
        try {
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipios());
            bean.setListaTiposDocumentoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumentoPersona(Util.convertToHash(bean.getListaTiposDocumentoPersona()));
            bean.setHashTiposGenero(Util.convertToHash(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO)));
            bean.setHashEstadosAfiliacion(Util.convertToHash(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION)));
            bean.setListaHomologaciones(getMpPrescripcionDetalleRemoto().consultarHomologacion());
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashMunicipios());
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void consultarTutela(MipresBean bean) {
        try {
            bean.setNumeroDeTutelas(getMpPrescripcionDetalleRemoto().consultarTutelas(bean.getObjeto().getIdAfiliado()));
        } catch (Exception ex) {
            Logger.getLogger(MipresImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void validarCotizacion(MipresBean bean) {
        Integer valor = null;
        switch (bean.getNoDireccionado().getTipoTecnologia()) {
            case 1:
            case 4:
                valor = bean.getNoDireccionado().getMpPrescripcionMedicamentosId().getId();
                break;
            case 2:
                valor = bean.getNoDireccionado().getMpPrescripcionTecnologiasId().getId();
                break;
            case 3:
            case 5:
                valor = bean.getNoDireccionado().getMpPrescripcionInsumosId().getId();
                break;
            default:
                break;
        }
        try {
            bean.setObjetoCotizacionDetalle(getMpPrescripcionDetalleRemoto().consultarCotizacionParaDireccionar(bean.getNoDireccionado().getMpPrescripcionesId().getId(), valor, bean.getNoDireccionado().getTipoTecnologia()));
            if (bean.getObjetoCotizacionDetalle() != null) {
                if (bean.getObjetoCotizacionDetalle().getIdCotizacion() != null) {
                    bean.setObjetoCotizacion(getMpPrescripcionDetalleRemoto().consultarAuCotizacionVigencia(bean.getObjetoCotizacionDetalle().getIdCotizacion()));
                    if (bean.getObjetoCotizacion() != null) {
                        bean.setTieneCotizacion(true);
                    } else {
                        bean.setTieneCotizacion(false);
                    }
                } else {
                    bean.setTieneCotizacion(false);
                }
            } else {
                bean.setTieneCotizacion(null);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actualizarAtencion(Integer id, Integer tipo) {
        try {
            getMpPrescripcionDetalleRemoto().actualizarAtencionItem(id, tipo);
        } catch (Exception ex) {
            Logger.getLogger(MipresImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actualizarFinAtencion(Integer id, Integer tipo) {
        try {
            getMpPrescripcionDetalleRemoto().actualizarFinAtencion(id, tipo);
        } catch (Exception ex) {
            Logger.getLogger(MipresImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void liberarTecnologia(Integer id, Integer tipo) {
        try {
            getMpPrescripcionDetalleRemoto().liberarTecnologia(id, tipo);
        } catch (Exception ex) {
            Logger.getLogger(MipresImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void verHistorico(MipresBean bean, int id) {
        try {
            bean.setPrescripcionH(getMpPrescripcionDetalleRemoto().consultarPrescripcion(id));
            bean.getPrescripcionH().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getPrescripcionH().getAsegAfiliado().getId()));
            bean.setNumeroDeTutelasH(getMpPrescripcionDetalleRemoto().consultarTutelas(bean.getPrescripcionH().getAsegAfiliado().getId()));
            if (bean.getPrescripcionH() != null
                    && bean.getPrescripcionH().getSedeCodigoHabilitacion() != null
                    && !bean.getPrescripcionH().getSedeCodigoHabilitacion().isBlank()) {
                bean.getPrescripcionH().setCntPrestadorSede(getCntPrestadorSedeRemoto().consultarPorCodigoHabilitacion(bean.getPrescripcionH().getSedeCodigoHabilitacion()));
                if (bean.getPrescripcionH().getCntPrestadorSede() != null) {
                    bean.getPrescripcionH().getCntPrestadorSede().setUbicacion(UbicacionSingle.getInstance().consultarPadre(bean.getPrescripcionH().getCntPrestadorSede().getUbicacionId()));
//                    bean.getPrescripcion().getCntPrestadorSede().setUbicacion(getUbicacionRemoto().consultar(bean.getPrescripcion().getCntPrestadorSede().getUbicacionId()));
                }

            }
            if (bean.getPrescripcionH().isRecobrante() == true) {
                bean.setPrescripcionRecobranteH(getMpPrescripcionDetalleRemoto().consultarRecobrante(id));
            }
//            
            MpDetalleDTO mpDetalleDTOH = null;
            for (MpPrescripcionMedicamento medicamento : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionMedicamento(id)) {
                mpDetalleDTOH = new MpDetalleDTO();
                mpDetalleDTOH.setId(medicamento.getId());
                mpDetalleDTOH.setCantidadTotal(medicamento.getCantidadTotalEntrega() != null ? medicamento.getCantidadTotalEntrega().intValue() : null);
                mpDetalleDTOH.setCantidadTotalPrescrita(medicamento.getCantidadTotalFormulada() != null ? medicamento.getCantidadTotalFormulada().toString() : null);

                mpDetalleDTOH.setCodigoTecnologia(String.valueOf(medicamento.getTipoTecnologia()));
                mpDetalleDTOH.setCodigoTecnologiaAvalEps("");
                mpDetalleDTOH.setConceptoEvaluacion("");
                mpDetalleDTOH.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(medicamento.getEstadoJuntaProfesionales()));
                mpDetalleDTOH.setConsecutivo(medicamento.getConsecutivoOrden());
                mpDetalleDTOH.setDuracionTratamientoOrdenado(medicamento.getCantidadTratamiento());
                mpDetalleDTOH.setEstado(bean.obtenerEstado(medicamento.getEstado()));
                mpDetalleDTOH.setPendientes(medicamento.getPendientes());
                mpDetalleDTOH.setFechaDireccionamiento(medicamento.getFechaDireccionamiento());
                mpDetalleDTOH.setNombreTecnologia(medicamento.getMedPbsUtilizado());
                mpDetalleDTOH.setNombreTecnologiaAvalEps(medicamento.getMedPbsUtilizado());
                if (medicamento.getTipoTecnologia() == 4) {
                    mpDetalleDTOH.setNombreTecnologiaPrescripta(medicamento.getMaeProductosNutricionalesValor());
                    mpDetalleDTOH.setCodigoFormaFarmaceutica(medicamento.getMaeProductosNutricionalesCodigo());
                } else {
                    mpDetalleDTOH.setNombreTecnologiaPrescripta(medicamento.getDescripcionMedicamentoPrincipioActivo());
                    mpDetalleDTOH.setCodigoFormaFarmaceutica(medicamento.getCodigoFormulaFarmaceutica());
                }
                mpDetalleDTOH.setNumeroEntregas(medicamento.getEntregados());
                mpDetalleDTOH.setNumeroTransaccion(medicamento.getIdTransaccion());
                mpDetalleDTOH.setTipoMedicamento(bean.obtenerTipoMedicamento(medicamento.getTipoMedicamento() == null ? medicamento.getTipoTecnologia() : medicamento.getTipoMedicamento()));
                mpDetalleDTOH.setIntTipoMedicamento(medicamento.getTipoMedicamento() == null ? medicamento.getTipoTecnologia() : medicamento.getTipoMedicamento());
                mpDetalleDTOH.setTipoTecnologia(bean.obtenerTipoTecnologia(medicamento.getTipoTecnologia()));
                mpDetalleDTOH.setTipoTecnologiaId(medicamento.getTipoTecnologia());
                mpDetalleDTOH.setTipoPrestacion(bean.obtenerTipoPrestacion(medicamento.getTipoPrestacion()));
                mpDetalleDTOH.setUnidadesAprobadasPeriodo(medicamento.getCantidadTratamiento());
                bean.getListaPrescripcionDtoH().add(mpDetalleDTOH);
            }

            for (MpPrescripcionTecnologia tecnologia : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionTecnologia(id)) {
                mpDetalleDTOH = new MpDetalleDTO();
                mpDetalleDTOH.setId(tecnologia.getId());
                mpDetalleDTOH.setCantidadTotal(tecnologia.getCantidadTotal());
                mpDetalleDTOH.setCantidadTotalPrescrita(tecnologia.getCantidadFormulada() != null ? tecnologia.getCantidadFormulada().toString() : null);
                mpDetalleDTOH.setCodigoTecnologia(tecnologia.getMaTecnologiaCodigo());
                mpDetalleDTOH.setConsecutivo(tecnologia.getConsecutivoOrden());
                mpDetalleDTOH.setDuracionTratamientoOrdenado(tecnologia.getCantidadDuracionTratamiento());
                mpDetalleDTOH.setNombreTecnologia(tecnologia.getMaTecnologiaValor());
                mpDetalleDTOH.setNombreTecnologiaAvalEps(tecnologia.getMaTecnologiaValor());
                mpDetalleDTOH.setNombreTecnologiaPrescripta(tecnologia.getMaTecnologiaValor());
                mpDetalleDTOH.setTipoTecnologia(bean.obtenerTipoTecnologia(tecnologia.getTipoTecnologia()));
                mpDetalleDTOH.setTipoTecnologiaId(tecnologia.getTipoTecnologia());
                mpDetalleDTOH.setTipoPrestacion(bean.obtenerTipoPrestacion(tecnologia.getTipoPrestacion()));
                bean.getListaPrescripcionDtoH().add(mpDetalleDTOH);
            }

            for (MpPrescripcionInsumo insumo : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionInsumo(id)) {
                mpDetalleDTOH = new MpDetalleDTO();
                mpDetalleDTOH.setId(insumo.getId());
                mpDetalleDTOH.setCantidadTotal(insumo.getCantidad() != null ? Integer.parseInt(insumo.getCantidad()) : null);
                mpDetalleDTOH.setCantidadTotalPrescrita(insumo.getCantidadFormulada());
                mpDetalleDTOH.setCodigoTecnologia(insumo.getCodigoDispositivo());
                mpDetalleDTOH.setCodigoTecnologiaAvalEps(insumo.getCodigoDispositivo());
                mpDetalleDTOH.setConceptoEvaluacion("");
                mpDetalleDTOH.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(insumo.getEstadoJuntaProfesionales()));
                mpDetalleDTOH.setConsecutivo(insumo.getConsecutivoOrden());
                BigDecimal cantidadTotalEntrega = insumo.getCantidadTotalEntrega();
                Integer duracionTratamientoOrdenado = cantidadTotalEntrega.intValue();

                mpDetalleDTOH.setDuracionTratamientoOrdenado(duracionTratamientoOrdenado);

                mpDetalleDTOH.setEstado(bean.obtenerEstado(insumo.getEstado()));
                mpDetalleDTOH.setFechaDireccionamiento(insumo.getFechaDireccionamiento());
                mpDetalleDTOH.setNombreTecnologia(insumo.getNombreTecnologiaAvalada());
                mpDetalleDTOH.setPendientes(insumo.getPendiente());
                mpDetalleDTOH.setNombreTecnologiaAvalEps(insumo.getNombreTecnologiaAvalada());
                switch (insumo.getTipoTecnologia()) {
                    case 3:
                        mpDetalleDTOH.setNombreTecnologiaPrescripta(insumo.getMaeDispositivosNombre());
                        mpDetalleDTOH.setCodigoFormaFarmaceutica(insumo.getMaeDispositivosCodigo());
                        break;
                    case 5:
                        mpDetalleDTOH.setNombreTecnologiaPrescripta(insumo.getMaeServiciosComplementariosNombre());
                        mpDetalleDTOH.setCodigoFormaFarmaceutica(insumo.getMaeServiciosComplementariosCodigo());
                        break;
                    default:
                        mpDetalleDTOH.setNombreTecnologiaPrescripta(insumo.getNombreTecnologiaAvalada());
                        mpDetalleDTOH.setCodigoFormaFarmaceutica(insumo.getCodigoForma());
                        break;
                }
                mpDetalleDTOH.setNumeroEntregas(insumo.getEntregados());
                mpDetalleDTOH.setNumeroTransaccion(insumo.getIdTransaccion());
                mpDetalleDTOH.setTipoMedicamento(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTOH.setIntTipoMedicamento(insumo.getTipoTecnologia());
                mpDetalleDTOH.setTipoTecnologia(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTOH.setTipoTecnologiaId(insumo.getTipoTecnologia());
                mpDetalleDTOH.setTipoPrestacion(bean.obtenerTipoPrestacion(insumo.getTipoPrestacion()));
                mpDetalleDTOH.setUnidadesAprobadasPeriodo(insumo.getCantidadTotalEntrega().intValue());
                bean.getListaPrescripcionDtoH().add(mpDetalleDTOH);
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void listar(MipresBean bean) {
        try {
            if (bean.getParamConsulta().getFiltros() != null) {
                if (bean.getParamConsulta().getFiltros().get("tipoTecnologiaItem") != null) {
                    String tecnologia = bean.getParamConsulta().getFiltros().get("tipoTecnologiaItem").toString();
                    if (tecnologia != null) {
                        bean.getParamConsulta().setParametroConsulta1(tecnologia);
                    }
                }
                boolean faltanParametros = false;
                if (bean.getParamConsulta().getFiltros().get("estadoItem") != null) {
                    if (bean.getParamConsulta().getFiltros().get("estadoItem") != "0") {
                        String estado = bean.getParamConsulta().getFiltros().get("estadoItem").toString();
                        if (estado != null) {
                            if (bean.getParamConsulta().getParametroConsulta1() == null) {
                                bean.addMensaje("debe seleccionar una tecnologia para poder buscar por estado");
                                faltanParametros = true;
                            }
                            bean.getParamConsulta().setParametroConsulta2(estado);

                        }
                    }
                }

                if (bean.getParamConsulta().getFiltros().get("juntaP") != null) {
                    if (bean.getParamConsulta().getFiltros().get("juntaP") != "0") {
                        String junta = bean.getParamConsulta().getFiltros().get("juntaP").toString();
                        if (junta != null) {
                            if (bean.getParamConsulta().getParametroConsulta1() == null) {
                                bean.addMensaje("debe seleccionar una tecnologia para poder buscar por junta");
                                faltanParametros = true;
                            }
                            bean.getParamConsulta().setParametroConsulta3(junta);

                        }
                    }
                }

                if (bean.getParamConsulta().getFiltros().get("nombreItem") != null) {
                    if (bean.getParamConsulta().getFiltros().get("nombreItem") != "0") {
                        String nombre = bean.getParamConsulta().getFiltros().get("nombreItem").toString();
                        if (nombre != null) {
                            if (bean.getParamConsulta().getParametroConsulta1() == null) {
                                bean.addMensaje("debe seleccionar una tecnologia para poder buscar por junta");
                                faltanParametros = true;
                            }
                            bean.getParamConsulta().setParametroConsulta4(nombre);

                        }
                    }
                }

                if (faltanParametros) {
                    return;
                }

            }
            bean.getParamConsulta().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMpPrescripcionDetalleRemoto().consultarLista(bean.getParamConsulta()));
            bean.getParamConsulta().setParametroConsulta1(null);
            bean.getParamConsulta().setParametroConsulta2(null);
            bean.limpiarMensajes();
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void ver(MipresBean bean) {
        try {
            bean.setPrescripcion(getMpPrescripcionDetalleRemoto().consultarPrescripcion(bean.getObjeto().getIdPrescripcion()));
            bean.setPrescripcionListaHistoricos(new ArrayList<>());
            bean.setPrescripcionListaHistoricos(getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionPorDocumento(bean.getObjeto().getNumeroDocumentoAfiliado()));

            if (bean.getPrescripcion().getAsegAfiliado() != null) {
                bean.getPrescripcion().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getPrescripcion().getAsegAfiliado().getId()));
            } else {
                bean.getPrescripcion().setAsegAfiliado(new AsegAfiliado());
            }
            bean.setNumeroDeTutelas(getMpPrescripcionDetalleRemoto().consultarTutelas(bean.getPrescripcion().getAsegAfiliado().getId()));
            bean.setMpPrescripcionAnulada(getMpPrescripcionDetalleRemoto().consultarMpPrescripcionAnulada(bean.getObjeto().getIdPrescripcion()));

            if (bean.getPrescripcion() != null
                    && bean.getPrescripcion().getSedeCodigoHabilitacion() != null
                    && !bean.getPrescripcion().getSedeCodigoHabilitacion().isBlank()) {
                bean.getPrescripcion().setCntPrestadorSede(getCntPrestadorSedeRemoto().consultarPorCodigoHabilitacion(bean.getPrescripcion().getSedeCodigoHabilitacion()));
                if (bean.getPrescripcion().getCntPrestadorSede() != null) {
                    bean.getPrescripcion().getCntPrestadorSede().setUbicacion(UbicacionSingle.getInstance().consultarPadre(bean.getPrescripcion().getCntPrestadorSede().getUbicacionId()));
//                    bean.getPrescripcion().getCntPrestadorSede().setUbicacion(getUbicacionRemoto().consultar(bean.getPrescripcion().getCntPrestadorSede().getUbicacionId()));
                }

            }
            if (bean.getPrescripcion().isRecobrante() == true) {
                bean.setPrescripcionRecobrante(getMpPrescripcionDetalleRemoto().consultarRecobrante(bean.getObjeto().getIdPrescripcion()));
            }
//            bean.setListaPrescripcionDto(new ArrayList<>());

            MpDetalleDTO mpDetalleDTO = null;
            for (MpPrescripcionMedicamento medicamento : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionMedicamento(bean.getObjeto().getIdPrescripcion())) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(medicamento.getId());
                mpDetalleDTO.setCantidadTotal(medicamento.getCantidadTotalEntrega() != null ? medicamento.getCantidadTotalEntrega().intValue() : null);
                mpDetalleDTO.setCantidadTotalPrescrita(medicamento.getCantidadTotalFormulada() != null ? medicamento.getCantidadTotalFormulada().toString() : null);

                mpDetalleDTO.setCodigoTecnologia(String.valueOf(medicamento.getTipoTecnologia()));
                mpDetalleDTO.setCodigoTecnologiaAvalEps("");
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(medicamento.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(medicamento.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(medicamento.getCantidadTratamiento());
                mpDetalleDTO.setEstado(bean.obtenerEstado(medicamento.getEstado()));
                mpDetalleDTO.setPendientes(medicamento.getPendientes());
                mpDetalleDTO.setFechaDireccionamiento(medicamento.getFechaDireccionamiento());
                mpDetalleDTO.setNombreTecnologia(medicamento.getMedPbsUtilizado());
                mpDetalleDTO.setNombreTecnologiaAvalEps(medicamento.getMedPbsUtilizado());
                if (medicamento.getTipoTecnologia() == 4) {
                    mpDetalleDTO.setNombreTecnologiaPrescripta(medicamento.getMaeProductosNutricionalesValor());
                    mpDetalleDTO.setCodigoFormaFarmaceutica(medicamento.getMaeProductosNutricionalesCodigo());
                } else {
                    mpDetalleDTO.setNombreTecnologiaPrescripta(medicamento.getDescripcionMedicamentoPrincipioActivo());
                    mpDetalleDTO.setCodigoFormaFarmaceutica(medicamento.getCodigoFormulaFarmaceutica());
                }
                mpDetalleDTO.setNumeroEntregas(medicamento.getEntregados());
                mpDetalleDTO.setNumeroTransaccion(medicamento.getIdTransaccion());
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoMedicamento(medicamento.getTipoMedicamento() == null ? medicamento.getTipoTecnologia() : medicamento.getTipoMedicamento()));
                mpDetalleDTO.setIntTipoMedicamento(medicamento.getTipoMedicamento() == null ? medicamento.getTipoTecnologia() : medicamento.getTipoMedicamento());
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(medicamento.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(medicamento.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(medicamento.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(medicamento.getCantidadTratamiento());
                bean.getListaPrescripcionDto().add(mpDetalleDTO);
            }

            for (MpPrescripcionTecnologia tecnologia : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionTecnologia(bean.getObjeto().getIdPrescripcion())) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(tecnologia.getId());
                mpDetalleDTO.setCantidadTotal(tecnologia.getCantidadTotal());
                mpDetalleDTO.setCantidadTotalPrescrita(tecnologia.getCantidadFormulada() != null ? tecnologia.getCantidadFormulada().toString() : null);
                mpDetalleDTO.setCodigoTecnologia(tecnologia.getMaTecnologiaCodigo());
                mpDetalleDTO.setConsecutivo(tecnologia.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(tecnologia.getCantidadDuracionTratamiento());
                mpDetalleDTO.setNombreTecnologia(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNombreTecnologiaAvalEps(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNombreTecnologiaPrescripta(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(tecnologia.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(tecnologia.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(tecnologia.getTipoPrestacion()));
                bean.getListaPrescripcionDto().add(mpDetalleDTO);
            }

            for (MpPrescripcionInsumo insumo : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionInsumo(bean.getObjeto().getIdPrescripcion())) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(insumo.getId());
                mpDetalleDTO.setCantidadTotal(insumo.getCantidad() != null ? Integer.parseInt(insumo.getCantidad()) : null);
                mpDetalleDTO.setCantidadTotalPrescrita(insumo.getCantidadFormulada());
                mpDetalleDTO.setCodigoTecnologia(insumo.getCodigoDispositivo());
                mpDetalleDTO.setCodigoTecnologiaAvalEps(insumo.getCodigoDispositivo());
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(insumo.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(insumo.getConsecutivoOrden());
                BigDecimal cantidadTotalEntrega = insumo.getCantidadTotalEntrega();
                Integer duracionTratamientoOrdenado = cantidadTotalEntrega.intValue();

                mpDetalleDTO.setDuracionTratamientoOrdenado(duracionTratamientoOrdenado);

                mpDetalleDTO.setEstado(bean.obtenerEstado(insumo.getEstado()));
                mpDetalleDTO.setFechaDireccionamiento(insumo.getFechaDireccionamiento());
                mpDetalleDTO.setNombreTecnologia(insumo.getNombreTecnologiaAvalada());
                mpDetalleDTO.setPendientes(insumo.getPendiente());
                mpDetalleDTO.setNombreTecnologiaAvalEps(insumo.getNombreTecnologiaAvalada());
                switch (insumo.getTipoTecnologia()) {
                    case 3:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getMaeDispositivosNombre());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getMaeDispositivosCodigo());
                        break;
                    case 5:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getMaeServiciosComplementariosNombre());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getMaeServiciosComplementariosCodigo());
                        break;
                    default:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getNombreTecnologiaAvalada());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getCodigoForma());
                        break;
                }
                mpDetalleDTO.setNumeroEntregas(insumo.getEntregados());
                mpDetalleDTO.setNumeroTransaccion(insumo.getIdTransaccion());
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTO.setIntTipoMedicamento(insumo.getTipoTecnologia());
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(insumo.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(insumo.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(insumo.getCantidadTotalEntrega().intValue());
                bean.getListaPrescripcionDto().add(mpDetalleDTO);
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void verItem(MipresBean bean) {
        int tipoTecnologia = bean.getObjeto().getTipoTecnologiaItem();
        bean.setTieneDireccionamiento(false);
        bean.setTienePrincipioActivo(false);
        bean.setTieneEntregas(false);
        bean.setTieneNoDireccionamientos(false);
        switch (tipoTecnologia) {
            case 1: {
                try {
                    bean.setPrescripcionMedicamento(getMpPrescripcionDetalleRemoto().consultarPorMedicamento(bean.getObjeto().getIdItem()));
                    bean.setListaPrescripcionHistoricos(getMpPrescripcionDetalleRemoto().consultarListaDeHistorico(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId()));
                    bean.setListaPrincipiosActivos(getMpPrescripcionDetalleRemoto().consultarPrincipioActivo(bean.getPrescripcionMedicamento().getId()));
                    bean.setListaMpIndicacionUnirs(getMpPrescripcionDetalleRemoto().consultarUnirs(bean.getPrescripcionMedicamento().getId()));
                    if (!bean.getListaPrincipiosActivos().isEmpty()) {
                        bean.setTienePrincipioActivo(true);
                    }
                    bean.setListaPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaaLista(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), 1));
//                    bean.setPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaa(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), 1));
                    if (!bean.getListaPrescripcionAuditoriaRespaldo().isEmpty() && bean.getListaPrescripcionAuditoriaRespaldo() != null) {
                        bean.setTieneAuditoria(true);
                    }
                    //------------------------------------
                    bean.setListadireccionamientosAux(getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamiento(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), tipoTecnologia));
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        bean.setTieneDireccionamiento(true);
                    }
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        for (MpDireccionamiento direccionamiento : bean.getListadireccionamientosAux()) {
                            List<MpDireccionamientoEntregado> entregados = null;
                            try {
                                entregados = getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamientoEntregado(direccionamiento.getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (entregados != null && !entregados.isEmpty()) {
                                bean.setListaDireccionamientosEntregados(entregados);
                            }

                        }
                        if (bean.getListaDireccionamientosEntregados() != null) {

                            if (!bean.getListaDireccionamientosEntregados().isEmpty()) {
                                bean.setTieneEntregas(true);
                            }
                        }
                    }
                    bean.setListanoDireccionados(getMpPrescripcionDetalleRemoto().consultarListaDeNoDireccionamiento(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), tipoTecnologia));
                    if (!bean.getListanoDireccionados().isEmpty()) {
                        bean.setTieneNoDireccionamientos(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MipresImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case 4: {
                try {
                    bean.setPrescripcionMedicamento(getMpPrescripcionDetalleRemoto().consultarPorMedicamento(bean.getObjeto().getIdItem()));
                    bean.setListaPrescripcionHistoricos(getMpPrescripcionDetalleRemoto().consultarListaDeHistorico(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId()));
                    bean.setListaPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaaLista(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), 1));
//                    bean.setPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaa(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), 1));
                    if (!bean.getListaPrescripcionAuditoriaRespaldo().isEmpty() && bean.getListaPrescripcionAuditoriaRespaldo() != null) {
                        bean.setTieneAuditoria(true);
                    }
                    //------------------------------------
                    bean.setListadireccionamientosAux(getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamiento(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), tipoTecnologia));
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        bean.setTieneDireccionamiento(true);
                    }
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        for (MpDireccionamiento direccionamiento : bean.getListadireccionamientosAux()) {
                            List<MpDireccionamientoEntregado> entregados = null;
                            try {
                                entregados = getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamientoEntregadoPresItem(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), 1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (entregados != null && !entregados.isEmpty()) {
                                bean.setListaDireccionamientosEntregados(entregados);
                            }

                        }
                        if (bean.getListaDireccionamientosEntregados() != null) {

                            if (!bean.getListaDireccionamientosEntregados().isEmpty()) {
                                bean.setTieneEntregas(true);
                            }
                        }
                    }
                    bean.setListanoDireccionados(getMpPrescripcionDetalleRemoto().consultarListaDeNoDireccionamiento(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), tipoTecnologia));
                    if (!bean.getListanoDireccionados().isEmpty()) {
                        bean.setTieneNoDireccionamientos(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MipresImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 2: {
                try {
                    bean.setPrescripcionTecnologia(getMpPrescripcionDetalleRemoto().consultarPorTecnologia(bean.getObjeto().getIdItem()));
                    bean.setListaPrescripcionHistoricos(getMpPrescripcionDetalleRemoto().consultarListaDeHistorico(bean.getPrescripcionTecnologia().getMpPrescripcion().getId(), bean.getPrescripcionTecnologia().getId()));
                    bean.setListaPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaaLista(bean.getPrescripcionTecnologia().getMpPrescripcion().getId(), bean.getPrescripcionTecnologia().getId(), 2));
//                    bean.setPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaa(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), 1));
                    if (!bean.getListaPrescripcionAuditoriaRespaldo().isEmpty() && bean.getListaPrescripcionAuditoriaRespaldo() != null) {
                        bean.setTieneAuditoria(true);
                    }
                    //------------------------------------
                    bean.setListadireccionamientosAux(getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamiento(bean.getPrescripcionTecnologia().getMpPrescripcion().getId(), bean.getPrescripcionTecnologia().getId(), tipoTecnologia));
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        bean.setTieneDireccionamiento(true);
                    }
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        for (MpDireccionamiento direccionamiento : bean.getListadireccionamientosAux()) {
                            List<MpDireccionamientoEntregado> entregados = null;
                            try {
                                entregados = getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamientoEntregadoPresItem(bean.getPrescripcionTecnologia().getMpPrescripcion().getId(), bean.getPrescripcionTecnologia().getId(), 2);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (entregados != null && !entregados.isEmpty()) {
                                bean.setListaDireccionamientosEntregados(entregados);
                            }

                        }
                        if (bean.getListaDireccionamientosEntregados() != null) {

                            if (!bean.getListaDireccionamientosEntregados().isEmpty()) {
                                bean.setTieneEntregas(true);
                            }
                        }
                    }
                    bean.setListanoDireccionados(getMpPrescripcionDetalleRemoto().consultarListaDeNoDireccionamiento(bean.getPrescripcionTecnologia().getMpPrescripcion().getId(), bean.getPrescripcionTecnologia().getId(), tipoTecnologia));
                    if (!bean.getListanoDireccionados().isEmpty()) {
                        bean.setTieneNoDireccionamientos(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MipresImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 3:
            case 5: {
                try {
                    bean.setPrescripcionInsumo(getMpPrescripcionDetalleRemoto().consultarPorInsumo(bean.getObjeto().getIdItem()));
                    bean.setListaPrescripcionHistoricos(getMpPrescripcionDetalleRemoto().consultarListaDeHistorico(bean.getPrescripcionInsumo().getMpPrescripcion().getId(), bean.getPrescripcionInsumo().getId()));
                    bean.setListaPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaaLista(bean.getPrescripcionInsumo().getMpPrescripcion().getId(), bean.getPrescripcionInsumo().getId(), 3));
//                    bean.setPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaa(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), 1));
                    if (!bean.getListaPrescripcionAuditoriaRespaldo().isEmpty() && bean.getListaPrescripcionAuditoriaRespaldo() != null) {
                        bean.setTieneAuditoria(true);
                    }
                    //------------------------------------
                    bean.setListadireccionamientosAux(getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamiento(bean.getPrescripcionInsumo().getMpPrescripcion().getId(), bean.getPrescripcionInsumo().getId(), tipoTecnologia));
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        bean.setTieneDireccionamiento(true);
                    }
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        for (MpDireccionamiento direccionamiento : bean.getListadireccionamientosAux()) {
                            List<MpDireccionamientoEntregado> entregados = null;
                            try {
                                entregados = getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamientoEntregadoPresItem(bean.getPrescripcionInsumo().getMpPrescripcion().getId(), bean.getPrescripcionInsumo().getId(), 3);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (entregados != null && !entregados.isEmpty()) {
                                bean.setListaDireccionamientosEntregados(entregados);
                            }

                        }
                        if (bean.getListaDireccionamientosEntregados() != null) {

                            if (!bean.getListaDireccionamientosEntregados().isEmpty()) {
                                bean.setTieneEntregas(true);
                            }
                        }
                    }
                    bean.setListanoDireccionados(getMpPrescripcionDetalleRemoto().consultarListaDeNoDireccionamiento(bean.getPrescripcionInsumo().getMpPrescripcion().getId(), bean.getPrescripcionInsumo().getId(), tipoTecnologia));
                    if (!bean.getListanoDireccionados().isEmpty()) {
                        bean.setTieneNoDireccionamientos(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            break;

            default:
                break;
        }

    }

    private void verItemOrigenGestion(MipresBean bean) {
        int tipoTecnologia = bean.getObjetoRespaldo().getTipoTecnologiaItem();
        bean.setTieneDireccionamiento(false);
        bean.setTienePrincipioActivo(false);
        bean.setTieneEntregas(false);
        bean.setTieneNoDireccionamientos(false);
        switch (tipoTecnologia) {
            case 1: {
                try {
                    bean.setPrescripcionMedicamento(getMpPrescripcionDetalleRemoto().consultarPorMedicamento(bean.getObjetoRespaldo().getIdItem()));
                    bean.setListaPrescripcionHistoricos(getMpPrescripcionDetalleRemoto().consultarListaDeHistorico(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId()));
                    bean.setListaPrincipiosActivos(getMpPrescripcionDetalleRemoto().consultarPrincipioActivo(bean.getPrescripcionMedicamento().getId()));
                    bean.setListaMpIndicacionUnirs(getMpPrescripcionDetalleRemoto().consultarUnirs(bean.getPrescripcionMedicamento().getId()));
                    if (!bean.getListaPrincipiosActivos().isEmpty()) {
                        bean.setTienePrincipioActivo(true);
                    }
                    bean.setPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaa(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), 1));
                    if (bean.getPrescripcionAuditoriaRespaldo() != null) {
                        bean.setTieneAuditoria(true);
                    }
                    //------------------------------------
                    bean.setListadireccionamientosAux(getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamiento(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), tipoTecnologia));
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        bean.setTieneDireccionamiento(true);
                    }
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        for (MpDireccionamiento direccionamiento : bean.getListadireccionamientosAux()) {
                            List<MpDireccionamientoEntregado> entregados = null;
                            try {
                                entregados = getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamientoEntregado(direccionamiento.getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (entregados != null && !entregados.isEmpty()) {
                                bean.setListaDireccionamientosEntregados(entregados);
                            }

                        }
                        if (bean.getListaDireccionamientosEntregados() != null) {

                            if (!bean.getListaDireccionamientosEntregados().isEmpty()) {
                                bean.setTieneEntregas(true);
                            }
                        }
                    }
                    bean.setListanoDireccionados(getMpPrescripcionDetalleRemoto().consultarListaDeNoDireccionamiento(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), tipoTecnologia));
                    if (!bean.getListanoDireccionados().isEmpty()) {
                        bean.setTieneNoDireccionamientos(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MipresImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case 4: {
                try {
                    bean.setPrescripcionMedicamento(getMpPrescripcionDetalleRemoto().consultarPorMedicamento(bean.getObjetoRespaldo().getIdItem()));
                    bean.setListaPrescripcionHistoricos(getMpPrescripcionDetalleRemoto().consultarListaDeHistorico(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId()));
                    bean.setPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaa(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), 1));
                    if (bean.getPrescripcionAuditoriaRespaldo() != null) {
                        bean.setTieneAuditoria(true);
                    }
                    //------------------------------------
                    bean.setListadireccionamientosAux(getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamiento(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), tipoTecnologia));
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        bean.setTieneDireccionamiento(true);
                    }
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        for (MpDireccionamiento direccionamiento : bean.getListadireccionamientosAux()) {
                            List<MpDireccionamientoEntregado> entregados = null;
                            try {
                                entregados = getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamientoEntregado(direccionamiento.getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (entregados != null && !entregados.isEmpty()) {
                                bean.setListaDireccionamientosEntregados(entregados);
                            }

                        }
                        if (bean.getListaDireccionamientosEntregados() != null) {

                            if (!bean.getListaDireccionamientosEntregados().isEmpty()) {
                                bean.setTieneEntregas(true);
                            }
                        }
                    }
                    bean.setListanoDireccionados(getMpPrescripcionDetalleRemoto().consultarListaDeNoDireccionamiento(bean.getPrescripcionMedicamento().getMpPrescripcion().getId(), bean.getPrescripcionMedicamento().getId(), tipoTecnologia));
                    if (!bean.getListanoDireccionados().isEmpty()) {
                        bean.setTieneNoDireccionamientos(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MipresImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 2: {
                try {
                    bean.setPrescripcionTecnologia(getMpPrescripcionDetalleRemoto().consultarPorTecnologia(bean.getObjetoRespaldo().getIdItem()));
                    bean.setListaPrescripcionHistoricos(getMpPrescripcionDetalleRemoto().consultarListaDeHistorico(bean.getPrescripcionTecnologia().getMpPrescripcion().getId(), bean.getPrescripcionTecnologia().getId()));
                    bean.setPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaa(bean.getPrescripcionTecnologia().getMpPrescripcion().getId(), bean.getPrescripcionTecnologia().getId(), 2));
                    if (bean.getPrescripcionAuditoriaRespaldo() != null) {
                        bean.setTieneAuditoria(true);
                    }
                    //------------------------------------
                    bean.setListadireccionamientosAux(getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamiento(bean.getPrescripcionTecnologia().getMpPrescripcion().getId(), bean.getPrescripcionTecnologia().getId(), tipoTecnologia));
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        bean.setTieneDireccionamiento(true);
                    }
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        for (MpDireccionamiento direccionamiento : bean.getListadireccionamientosAux()) {
                            List<MpDireccionamientoEntregado> entregados = null;
                            try {
                                entregados = getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamientoEntregado(direccionamiento.getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (entregados != null && !entregados.isEmpty()) {
                                bean.setListaDireccionamientosEntregados(entregados);
                            }

                        }
                        if (bean.getListaDireccionamientosEntregados() != null) {

                            if (!bean.getListaDireccionamientosEntregados().isEmpty()) {
                                bean.setTieneEntregas(true);
                            }
                        }
                    }
                    bean.setListanoDireccionados(getMpPrescripcionDetalleRemoto().consultarListaDeNoDireccionamiento(bean.getPrescripcionTecnologia().getMpPrescripcion().getId(), bean.getPrescripcionTecnologia().getId(), tipoTecnologia));
                    if (!bean.getListanoDireccionados().isEmpty()) {
                        bean.setTieneNoDireccionamientos(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MipresImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 3:
            case 5: {
                try {
                    bean.setPrescripcionInsumo(getMpPrescripcionDetalleRemoto().consultarPorInsumo(bean.getObjetoRespaldo().getIdItem()));
                    bean.setListaPrescripcionHistoricos(getMpPrescripcionDetalleRemoto().consultarListaDeHistorico(bean.getPrescripcionInsumo().getMpPrescripcion().getId(), bean.getPrescripcionInsumo().getId()));
                    bean.setPrescripcionAuditoriaRespaldo(getMpPrescripcionDetalleRemoto().consultarAuditoriaa(bean.getPrescripcionInsumo().getMpPrescripcion().getId(), bean.getPrescripcionInsumo().getId(), 3));
                    if (bean.getPrescripcionAuditoriaRespaldo() != null) {
                        bean.setTieneAuditoria(true);
                    }
                    //------------------------------------
                    bean.setListadireccionamientosAux(getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamiento(bean.getPrescripcionInsumo().getMpPrescripcion().getId(), bean.getPrescripcionInsumo().getId(), tipoTecnologia));
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        bean.setTieneDireccionamiento(true);
                    }
                    if (!bean.getListadireccionamientosAux().isEmpty()) {
                        for (MpDireccionamiento direccionamiento : bean.getListadireccionamientosAux()) {
                            List<MpDireccionamientoEntregado> entregados = null;
                            try {
                                entregados = getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamientoEntregado(direccionamiento.getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (entregados != null && !entregados.isEmpty()) {
                                bean.setListaDireccionamientosEntregados(entregados);
                            }

                        }
                        if (bean.getListaDireccionamientosEntregados() != null) {

                            if (!bean.getListaDireccionamientosEntregados().isEmpty()) {
                                bean.setTieneEntregas(true);
                            }
                        }
                    }
                    bean.setListanoDireccionados(getMpPrescripcionDetalleRemoto().consultarListaDeNoDireccionamiento(bean.getPrescripcionInsumo().getMpPrescripcion().getId(), bean.getPrescripcionInsumo().getId(), tipoTecnologia));
                    if (!bean.getListanoDireccionados().isEmpty()) {
                        bean.setTieneNoDireccionamientos(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            break;

            default:
                break;
        }

    }

    private void Direccionar(MipresBean bean) {
        int tipoTecnologia = bean.getObjeto().getTipoTecnologiaItem();
        try {
            bean.setNumeroDeTutelas(getMpPrescripcionDetalleRemoto().consultarTutelas(bean.getObjeto().getIdAfiliado()));
            bean.setPrescripcionListaHistoricos(new ArrayList<>());
            bean.setPrescripcionListaHistoricos(getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionPorDocumento(bean.getObjeto().getNumeroDocumentoAfiliado()));
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(MipresImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bean.setDireccionamientoDireccionado(getMpPrescripcionDetalleRemoto().consultarListaDeDireccionamientoDireccionado(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getIdItem(), tipoTecnologia));
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(MipresImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        try {
            bean.setObjetoCotizacionDetalle(getMpPrescripcionDetalleRemoto().consultarCotizacionParaDireccionar(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getIdItem(), bean.getObjeto().getTipoTecnologiaItem()));
            if (bean.getObjetoCotizacionDetalle() != null) {
                if (bean.getObjetoCotizacionDetalle().getIdCotizacion() != null) {
                    bean.setObjetoCotizacion(getMpPrescripcionDetalleRemoto().consultarAuCotizacionVigencia(bean.getObjetoCotizacionDetalle().getIdCotizacion()));
                    if (bean.getObjetoCotizacion() != null) {
                        bean.setTieneCotizacion(true);
                    } else {
                        bean.setTieneCotizacion(true);
                    }
                } else {
                    bean.setTieneCotizacion(false);

                }
            } else {
                bean.setTieneCotizacion(false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        switch (tipoTecnologia) {
            case 1:
            case 4: {
                try {
                    bean.setDireccionamiento(getMpPrescripcionDetalleRemoto().direccionarMedicamento(bean.getObjeto().getIdItem()));
                } catch (Exception ex) {
                    Logger.getLogger(MipresImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case 2: {
                try {
                    bean.setDireccionamiento(getMpPrescripcionDetalleRemoto().direccionarTecnologia(bean.getObjeto().getIdItem()));
                } catch (Exception ex) {
                    Logger.getLogger(MipresImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case 3:
            case 5: {
                try {
                    bean.setDireccionamiento(getMpPrescripcionDetalleRemoto().direccionarInsumo(bean.getObjeto().getIdItem()));
                } catch (Exception ex) {
                    Logger.getLogger(MipresImpl.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            default:
                break;
        }
    }

    private void borradoLogico(MipresBean bean) {
        int tipoTecnologia = bean.getObjeto().getTipoTecnologiaItem();
        try {
            List<Integer> id = getMpPrescripcionDetalleRemoto().consultarIdParaBorrar(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getIdItem(), tipoTecnologia);

            if (id != null && !id.isEmpty()) {
                getMpPrescripcionDetalleRemoto().borradoLogico(id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(MipresImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verH(MipresBean bean) {

        try {
            bean.setPrescripcionListaHistoricos(new ArrayList<>());
            bean.setPrescripcionListaHistoricos(getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionPorDocumento(bean.getObjeto().getNumeroDocumentoAfiliado()));

            // Obtener las tecnologías de la prescripción actual
            Set<Integer> tecnologiasActuales = new HashSet<>();

            // Agregar los tipos de tecnologías de la prescripción actual
            for (MpPrescripcionMedicamento med : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionMedicamentoH(bean.getObjeto().getIdPrescripcion())) {
                tecnologiasActuales.add(med.getTipoTecnologia());
            }
            for (MpPrescripcionInsumo ins : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionInsumoH(bean.getObjeto().getIdPrescripcion())) {
                tecnologiasActuales.add(ins.getTipoTecnologia());
            }
            for (MpPrescripcionTecnologia tec : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionTecnologiaH(bean.getObjeto().getIdPrescripcion())) {
                tecnologiasActuales.add(tec.getTipoTecnologia());
            }

            // Lista donde almacenaremos los historiales que coinciden
            List<MpPrescripcion> historicosValidos = new ArrayList<>();

            // Recorrer cada prescripción histórica
            for (MpPrescripcion historico : bean.getPrescripcionListaHistoricos()) {
                Integer idHistorico = historico.getId();
                Set<Integer> tecnologiasHistorico = new HashSet<>();

                // Obtener tecnologías del histórico
                for (MpPrescripcionMedicamento med : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionMedicamento(idHistorico)) {
                    tecnologiasHistorico.add(med.getTipoTecnologia());
                }
                for (MpPrescripcionInsumo ins : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionInsumo(idHistorico)) {
                    tecnologiasHistorico.add(ins.getTipoTecnologia());
                }
                for (MpPrescripcionTecnologia tec : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionTecnologia(idHistorico)) {
                    tecnologiasHistorico.add(tec.getTipoTecnologia());
                }

                // Verificar si tiene al menos una coincidencia con la prescripción actual
                if (!Collections.disjoint(tecnologiasActuales, tecnologiasHistorico)) {
                    historicosValidos.add(historico);
                }
            }
            List<MpPrescripcion> histori = new ArrayList<>();

            for (MpPrescripcion h : historicosValidos) {
                if (!h.getId().equals(bean.getObjeto().getIdPrescripcion())) {
                    histori.add(h);
                }
            }
            if (!histori.isEmpty() && histori != null) {
                bean.setPrescripcionListaHistoricos(histori);
            } else {
                bean.setPrescripcionListaHistoricos(histori);
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void auditoria(MipresBean bean) {
        try {
            if (bean.getObjeto() != null) {

                Integer tipoTecnologia = bean.getObjeto().getTipoTecnologiaItem();
                Integer idItem = bean.getObjeto().getIdItem();
                int idprescripcion = bean.getObjeto().getIdPrescripcion();
                bean.setNumeroDeTutelas(getMpPrescripcionDetalleRemoto().consultarTutelas(bean.getObjeto().getIdAfiliado()));
                MpPrescripcionAuditoria prescripcionAuditoria = new MpPrescripcionAuditoria();

                prescripcionAuditoria.setMpPrescripcionId(new MpPrescripcion(idprescripcion));
                prescripcionAuditoria.getMpPrescripcionId().setNumeroPrescripcion(bean.getObjeto().getNumeroPrescripcion());
                prescripcionAuditoria.setDocumentoPrestador(bean.getObjeto().getDocumentoPrestador());
                prescripcionAuditoria.setIdAfiliado(bean.getObjeto().getIdAfiliado());
                if (tipoTecnologia != null) {
                    switch (bean.getObjeto().getTipoTecnologiaItem()) {
                        case 1:
                        case 4:
                            prescripcionAuditoria.setMpMedicamentoId(new MpPrescripcionMedicamento(idItem));
                            break;
                        case 2:
                            prescripcionAuditoria.setMpTecnologiaId(new MpPrescripcionTecnologia(idItem));
                            break;
                        case 3:
                        case 5:
                            prescripcionAuditoria.setMpInsumoId(new MpPrescripcionInsumo(idItem));
                            break;
                        default:
                            break;
                    }
                }
                bean.auditoriaGuardar(prescripcionAuditoria);
                prescripcionAuditoria.setEstado(null);
                prescripcionAuditoria.setNotaAuditoria(null);
//                if (("22".equals(bean.getObjeto().getCodigoAmbitoAtencion())) || ("30".equals(bean.getObjeto().getCodigoAmbitoAtencion()))) {
//                    if (bean.getObjeto().getReferenciaContra() == true) {
//                        bean.getNotificacionHistorico().setMensaje(inicializarNotificacion(bean.getObjeto()));
//                    }
//                } else {
                bean.getNotificacionHistorico().setMensaje(inicializarNotificacion(bean.getObjeto()));
//                }
                bean.setPrescripcionAuditoria(prescripcionAuditoria);

                int idRegistro = 0;
                int tabla = 0;

                if (prescripcionAuditoria.getMpMedicamentoId() != null) {
                    idRegistro = prescripcionAuditoria.getMpMedicamentoId().getId();
                    tabla = 1;
                    MpPrescripcionMedicamento medicamento = getMpPrescripcionDetalleRemoto().consultarPorMedicamento(idRegistro);
                    if (medicamento.getAtendido() == null || medicamento.getAtendido() == false) {
                        String usuario = bean.getConexion().getUsuario().getUsuarioNombre();
                        String terminal = bean.getConexion().getIp();
                        boolean atendido = true;
                        Date horaAtiende = (new Date());
                        getMpPrescripcionDetalleRemoto().insertarAtencion(usuario, terminal, atendido, horaAtiende, tabla, idRegistro);
                        bean.setRefrescarAtencion(true);

                        MpPrescripcionHistorico prescripcionHistorico = new MpPrescripcionHistorico();
                        prescripcionHistorico.setMpPrescripcion(new MpPrescripcion(medicamento.getMpPrescripcion().getId()));
                        prescripcionHistorico.setTipoTecnologia(medicamento.getTipoTecnologia());
                        prescripcionHistorico.setIdPrescripcionTecnologia(medicamento.getId());
                        prescripcionHistorico.setEstado(27);
                        bean.auditoriaGuardar(prescripcionHistorico);
                        getMpPrescripcionDetalleRemoto().inicioHistorico(prescripcionHistorico);
                    }

                } else if (prescripcionAuditoria.getMpTecnologiaId() != null) {
                    idRegistro = prescripcionAuditoria.getMpTecnologiaId().getId();
                    tabla = 2;
                    MpPrescripcionTecnologia tecnologia = getMpPrescripcionDetalleRemoto().consultarPorTecnologia(idRegistro);
                    if (tecnologia.getAtendido() == null || tecnologia.getAtendido() == false) {
                        String usuario = bean.getConexion().getUsuario().getUsuarioNombre();
                        String terminal = bean.getConexion().getIp();
                        boolean atendido = true;
                        Date horaAtiende = (new Date());
                        getMpPrescripcionDetalleRemoto().insertarAtencion(usuario, terminal, atendido, horaAtiende, tabla, idRegistro);
                        bean.setRefrescarAtencion(true);

                        MpPrescripcionHistorico prescripcionHistorico = new MpPrescripcionHistorico();
                        prescripcionHistorico.setMpPrescripcion(new MpPrescripcion(tecnologia.getMpPrescripcion().getId()));
                        prescripcionHistorico.setTipoTecnologia(tecnologia.getTipoTecnologia());
                        prescripcionHistorico.setIdPrescripcionTecnologia(tecnologia.getId());
                        prescripcionHistorico.setEstado(27);
                        bean.auditoriaGuardar(prescripcionHistorico);
                        getMpPrescripcionDetalleRemoto().inicioHistorico(prescripcionHistorico);
                    }

                } else if (prescripcionAuditoria.getMpInsumoId() != null) {
                    idRegistro = prescripcionAuditoria.getMpInsumoId().getId();
                    tabla = 3;
                    MpPrescripcionInsumo insumo = getMpPrescripcionDetalleRemoto().consultarPorInsumo(idRegistro);
                    if (insumo.getAtendido() == null || insumo.getAtendido() == false) {
                        String usuario = bean.getConexion().getUsuario().getUsuarioNombre();
                        String terminal = bean.getConexion().getIp();
                        boolean atendido = true;
                        Date horaAtiende = (new Date());
                        getMpPrescripcionDetalleRemoto().insertarAtencion(usuario, terminal, atendido, horaAtiende, tabla, idRegistro);
                        bean.setRefrescarAtencion(true);

                        MpPrescripcionHistorico prescripcionHistorico = new MpPrescripcionHistorico();
                        prescripcionHistorico.setMpPrescripcion(new MpPrescripcion(insumo.getMpPrescripcion().getId()));
                        prescripcionHistorico.setTipoTecnologia(insumo.getTipoTecnologia());
                        prescripcionHistorico.setIdPrescripcionTecnologia(insumo.getId());
                        prescripcionHistorico.setEstado(27);
                        bean.auditoriaGuardar(prescripcionHistorico);
                        getMpPrescripcionDetalleRemoto().inicioHistorico(prescripcionHistorico);
                    }

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            bean.addError("error mpl auditar");
        }

    }

    @Override
    public void listarPrestador(MipresBean bean) {
        try {
            bean.getParamConsultaPrestador().setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadLista(bean.getParamConsultaPrestador()));
            bean.setRegistroPrestadores(getCntPrestadorRemoto().consultarLista(bean.getParamConsultaPrestador()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaMedicamento(MipresBean bean) {
        try {
            bean.getParamConsultaMaMedicemento().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaMaMedicamento(bean.getParamConsultaMaMedicemento()));
            bean.setRegistroMaMedicamento(getMpPrescripcionDetalleRemoto().consultarListaMaMedicamento(bean.getParamConsultaMaMedicemento()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaInsumo(MipresBean bean) {
        try {
            bean.getParamConsultaMaInsumo().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaMaInsumo(bean.getParamConsultaMaInsumo()));
            bean.setRegistroMaInsumo(getMpPrescripcionDetalleRemoto().consultarListaMaInsumo(bean.getParamConsultaMaInsumo()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaInsumoMipres(MipresBean bean) {
        try {
            bean.getParamConsultaMaInsumo().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaMaInsumoMipres(bean.getParamConsultaMaInsumo()));
            bean.setRegistroMaInsumoMipres(getMpPrescripcionDetalleRemoto().consultarListaMaInsumoMipres(bean.getParamConsultaMaInsumo()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaPaqueteMipres(MipresBean bean) {
        try {
            bean.getParamConsultaMaPaquete().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaMaPaqueteMipres(bean.getParamConsultaMaPaquete()));
            bean.setRegistroMaPaqueteMipres(getMpPrescripcionDetalleRemoto().consultarListaMaPaqueteMipres(bean.getParamConsultaMaPaquete()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaTecnologiaMipres(MipresBean bean) {
        try {
            bean.getParamConsultaMaTecnologiaMipres().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaMaTecnologiaMipres(bean.getParamConsultaMaTecnologiaMipres()));
            bean.setRegistroMaTecnologiaMipres(getMpPrescripcionDetalleRemoto().consultarListaMaTecnologiaMipres(bean.getParamConsultaMaTecnologiaMipres()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarMaTecnologia(MipresBean bean) {
        try {
            bean.getParamConsultaMaTecnologia().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaTecnologia(bean.getParamConsultaMaTecnologia()));
            bean.setRegistroMaTecnologia(getMpPrescripcionDetalleRemoto().consultarListaMaTecnologia(bean.getParamConsultaMaTecnologia()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void listarPrestadorSede(MipresBean bean) {
        try {
            bean.getParamConsultaPrestadorSede().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaPrestadorSede(bean.getParamConsultaPrestadorSede()));
            bean.setRegistroPrestadorSede(getMpPrescripcionDetalleRemoto().consultarListaPrestadorSede(bean.getParamConsultaPrestadorSede()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    public void guardar(MipresBean bean) {
        try {
            int est;
            Integer val = 3;
            Integer prescripcionM = null;
            Integer prescripcionT = null;
            Integer prescripcionI = null;
            for (MpDireccionamiento direc : bean.getListadireccionamientos()) {
                if (direc.getPrestadorNumeroDocumentoStr() != null) {
                    if (direc.getId() != null) {
                        direc.setId(null);
                    }
                    if (direc.getId() == null) {
                        direc.setMpPrescripcionId(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                        direc.setUbicacionSedeId(bean.getDireccionamiento().getUbicacionSedeId());
                        direc.setUbicacionSedeIdStr(bean.getDireccionamiento().getUbicacionSedeIdStr());
                        direc.setCodigoMpEntrega(bean.getDireccionamiento().getCodigoMpEntrega());
                        direc.setCodigoMpPropio(bean.getDireccionamiento().getCodigoMpPropio());
                        direc.setNumeroDocumentoPaciente(bean.getObjeto().getNumeroDocumentoAfiliadoMp()); //numero                       
                        direc.setMaeTipoDocumentoPacienteCodigo(bean.getObjeto().getMaeTipoDocumentoAfiliadoCodigoMp());//codigo cc
                        direc.setMaeTipoDocumentoPacienteId(bean.getObjeto().getMaeTipoDocumentoAfiliadoIdMp());//id documento
                        direc.setMaeTipoDocumentoPacienteValor(bean.getObjeto().getTipoDocumentoAfiliadoMp());//valor documento
                        direc.setCodigoPrestadorSede(direc.getPrestadorNumeroDocumentoStr());

                        direc.setUltimoDireccionamiento(direc.getUltimoDireccionamiento());

                        if (Boolean.TRUE.equals(bean.getDireccionamiento().getEnvioCorreoAuto())) {
                            direc.setEnvioCorreoAuto(bean.getDireccionamiento().getEnvioCorreoAuto());
                        }
                        if (bean.getDireccionamiento().getFechaEnvioAuto() != null) {
                            direc.setFechaEnvioAuto(bean.getDireccionamiento().getFechaEnvioAuto());
                        }
                        direc.setValorTecContratada(direc.getValorTecContratada());
                        direc.setMaeTipoDocumentoPrestadorId(direc.getMaeTipoDocumentoPrestadorId());
                        direc.setMaeTipoDocumentoPrestadorCodigo(direc.getMaeTipoDocumentoPrestadorCodigo());
                        direc.setMaeTipoDocumentoPrestadorValor((direc.getMaeTipoDocumentoPrestadorValor()));
                        direc.setPrestadorRazonSocial(direc.getPrestadorRazonSocial());
                        direc.setPrestadorNumeroDocumento(direc.getPrestadorNumeroDocumento());

                        direc.setEstado(3);
                        if (null != bean.getObjeto().getTipoTecnologiaItem()) {
                            switch (bean.getObjeto().getTipoTecnologiaItem()) {
                                case 1:
                                case 4:
                                    direc.setMpPrescripcionMedicamentoId(new MpPrescripcionMedicamento(bean.getObjeto().getIdItem()));
                                    prescripcionM = bean.getObjeto().getIdItem();
                                    break;
                                case 2:
                                    direc.setMpPrescripcionTecnologiaId(new MpPrescripcionTecnologia(bean.getObjeto().getIdItem()));
                                    prescripcionT = bean.getObjeto().getIdItem();
                                    break;
                                case 3:
                                case 5:
                                    direc.setMpPrescripcionInsumoId(new MpPrescripcionInsumo(bean.getObjeto().getIdItem()));
                                    prescripcionI = bean.getObjeto().getIdItem();
                                    break;
                                default:
                                    break;
                            }
                        }
                        direc.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());

                        if (bean.getDireccionamiento().getEsEntregaParcial()) {
                            direc.setEsEntregaParcial(true);
                            direc.setEstado(3);
                            val = 11;
                            direc.setCodigoTipoDireccionamiento(val);
                        } else if (bean.getDireccionamiento().getEsEntregaDiferida()) {
                            direc.setEsEntregaDiferida(true);
                            direc.setEstado(3);
                            val = 12;
                            direc.setCodigoTipoDireccionamiento(val);
                        } else {
                            direc.setEstado(val);
                            direc.setCodigoTipoDireccionamiento(val);
                            direc.setNumeroPrescripcionAso(bean.getValorAsociado());
                        }
                        est = 1;
                        bean.auditoriaGuardar(direc);
                        getMpPrescripcionDetalleRemoto().insertarDireccionamiento(direc);
                        getMpPrescripcionDetalleRemoto().cambiarEstadoPrescripcion(prescripcionM, prescripcionT, prescripcionI, est);
                    }
                } else {
                    bean.addError("debe seleccionar un prestador para poder realizar la inserción");
                    return;
                }
            }

            String correoPrestador = getMpPrescripcionDetalleRemoto().consultarCorreoPrestador(bean.getDireccionamiento().getPrestadorNumeroDocumento(), bean.getDireccionamiento().getCodigoHabilitacionSede());

            if (correoPrestador != null) {
                String encabezadoPrestador = "Direccionamiento";
                String mensajePrestador = generarMensajeCorreoDir(bean.getDireccionamiento(), bean, bean.getListadireccionamientos().size());
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_MIPRES, correoPrestador, encabezadoPrestador, mensajePrestador, GnCorreoEnvio.TIPO_TEXTO);
                try {
                    getGnCorreoEnvioRemoto().insertar(envio);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            MpPrescripcionHistorico historico = new MpPrescripcionHistorico();
            historico.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
            historico.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
            historico.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
            if (bean.getDireccionamiento().getEsEntregaParcial() == true) {
                historico.setEstado(50);
            } else if (bean.getDireccionamiento().getEsEntregaDiferida() == true) {
                historico.setEstado(51);
            } else {
                historico.setEstado(1);
            }
            bean.auditoriaGuardar(historico);
            getMpPrescripcionDetalleRemoto().inicioHistorico(historico);

            GnSmsEnvio envio = new GnSmsEnvio();
            envio.setOrigen(GnSmsEnvio.ORIGEN_MIPRES);
            envio.setEstado(GnSmsEnvio.ESTADO_CREADO);
            if (bean.getObjeto().getIdAfiliado() != null) {
                envio.setCelulares(getMpPrescripcionDetalleRemoto().consultarContactoAfiliado(bean.getObjeto().getIdAfiliado()));
                if (envio.getCelulares().length() > 10) {
                    envio.setCelulares(envio.getCelulares().substring(0, 10));
                } else if (envio.getCelulares().length() < 10) {
                    envio.setCelulares("");
                }
            } else {
                envio.setCelulares("");
            }
            String mensaje = inicializarNotificacionDireccionamiento(bean);
            if (mensaje.length() > 256) {
                mensaje = mensaje.substring(0, 256);
            }
            envio.setTexto(mensaje);
            envio.setFechaHoraCrea(new Date());
            try {
                getGnSmsEnvioRemoto().insertar(envio);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (bean.getTieneCotizacion()) {
                if (bean.getIdCotizacion() != null) {
                    getMpPrescripcionDetalleRemoto().cambiarEstadoCotizacionMpAu(bean.getIdCotizacion(), 14);
                    MpPrescripcionHistorico historico1 = new MpPrescripcionHistorico();
                    historico1.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                    historico1.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
                    historico1.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
                    historico1.setEstado(14);//direccionado con cotizacion
                    bean.auditoriaGuardar(historico1);
                    getMpPrescripcionDetalleRemoto().inicioHistorico(historico1);
                } else {
                    Integer valor = getMpPrescripcionDetalleRemoto().consultarCotizacion2(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getIdItem(), bean.getObjeto().getTipoTecnologiaItem());
                    if (valor != null) {
                        getMpPrescripcionDetalleRemoto().cambiarEstadoCotizacionMp(valor, 17);//direccionado - cotizacion activa
                        MpPrescripcionHistorico historico1 = new MpPrescripcionHistorico();
                        historico1.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                        historico1.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
                        historico1.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
                        historico1.setEstado(17);//direccionado -  cotizacion activa
                        bean.auditoriaGuardar(historico1);
                        getMpPrescripcionDetalleRemoto().inicioHistorico(historico1);
                    }
                }
            } else {
                Integer valor = getMpPrescripcionDetalleRemoto().consultarCotizacion2(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getIdItem(), bean.getObjeto().getTipoTecnologiaItem());
                if (valor != null) {
                    getMpPrescripcionDetalleRemoto().cambiarEstadoCotizacionMp(valor, 15);
                }
                MpPrescripcionHistorico historico2 = new MpPrescripcionHistorico();
                historico2.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                historico2.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
                historico2.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
                historico2.setEstado(15);//direccionado sin cotizacion
                bean.auditoriaGuardar(historico2);
                getMpPrescripcionDetalleRemoto().inicioHistorico(historico2);

            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    @Override
    public void guardarDireccionamientoIndividual(MpDireccionamiento objeto, MipresBean bean, int numero) {
        try {
            if (numero == 2) {
                getMpPrescripcionDetalleRemoto().EliminarDireccionamiento(objeto.getId());
            }
            int est;
            Integer prescripcionM = null;
            Integer prescripcionT = null;
            Integer prescripcionI = null;

            objeto.setId(null);
            if (objeto.getPrestadorNumeroDocumentoStr() != null) {
                if (objeto.getId() != null) {
                    objeto.setId(null);
                }
                if (objeto.getId() == null) {
                    objeto.setMpPrescripcionId(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                    objeto.setUbicacionSedeId(objeto.getUbicacionSedeId());
                    objeto.setUbicacionSedeIdStr(objeto.getUbicacionSedeIdStr());
                    objeto.setCodigoMpEntrega(objeto.getCodigoMpEntrega());
                    objeto.setConsecutivoEntrega(objeto.getConsecutivoEntrega());
                    objeto.setCodigoMpPropio(objeto.getCodigoMpPropio());
                    objeto.setNumeroDocumentoPaciente(bean.getObjeto().getNumeroDocumentoAfiliadoMp()); //numero                       
                    objeto.setMaeTipoDocumentoPacienteCodigo(bean.getObjeto().getMaeTipoDocumentoAfiliadoCodigoMp());//codigo cc
                    objeto.setMaeTipoDocumentoPacienteId(bean.getObjeto().getMaeTipoDocumentoAfiliadoIdMp());//id documento
                    objeto.setMaeTipoDocumentoPacienteValor(bean.getObjeto().getTipoDocumentoAfiliadoMp());//valor documento
                    objeto.setCodigoPrestadorSede(objeto.getPrestadorNumeroDocumentoStr());
                    objeto.setPrestadorNumeroDocumento(objeto.getPrestadorNumeroDocumento());
                    objeto.setUltimoDireccionamiento(objeto.getUltimoDireccionamiento());
                    if (null != bean.getObjeto().getTipoTecnologiaItem()) {
                        switch (bean.getObjeto().getTipoTecnologiaItem()) {
                            case 1:
                            case 4:
                                objeto.setMpPrescripcionMedicamentoId(new MpPrescripcionMedicamento(bean.getObjeto().getIdItem()));
                                prescripcionM = bean.getObjeto().getIdItem();
                                break;
                            case 2:
                                objeto.setMpPrescripcionTecnologiaId(new MpPrescripcionTecnologia(bean.getObjeto().getIdItem()));
                                prescripcionT = bean.getObjeto().getIdItem();
                                break;
                            case 3:
                            case 5:
                                objeto.setMpPrescripcionInsumoId(new MpPrescripcionInsumo(bean.getObjeto().getIdItem()));
                                prescripcionI = bean.getObjeto().getIdItem();
                                break;
                            default:
                                break;
                        }
                    }

                    objeto.setMaeTipoDocumentoPrestadorId(objeto.getMaeTipoDocumentoPrestadorId());
                    objeto.setMaeTipoDocumentoPrestadorCodigo(objeto.getMaeTipoDocumentoPrestadorCodigo());
                    objeto.setMaeTipoDocumentoPrestadorValor(objeto.getMaeTipoDocumentoPrestadorValor());
                    objeto.setPreeliminado(null);
                    objeto.setEliminado(null);
                    objeto.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
                    est = 3;
                    objeto.setEstado(est);
                    objeto.setValorTecContratada(objeto.getValorTecContratada());
                    bean.auditoriaGuardar(objeto);
                    getMpPrescripcionDetalleRemoto().insertarDireccionamiento(objeto);
                    getMpPrescripcionDetalleRemoto().cambiarEstadoPrescripcion(prescripcionM, prescripcionT, prescripcionI, 1);

                    String correoPrestador = getMpPrescripcionDetalleRemoto().consultarCorreoPrestador(objeto.getPrestadorNumeroDocumento(), objeto.getCodigoHabilitacionSede());

                    if (correoPrestador != null) {
                        String encabezadoPrestador = "Direccionamiento";
                        String mensajePrestador = generarMensajeCorreoDirIndividual(objeto, bean);
                        GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_MIPRES, correoPrestador, encabezadoPrestador, mensajePrestador, GnCorreoEnvio.TIPO_TEXTO);
                        try {
                            getGnCorreoEnvioRemoto().insertar(envio);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            } else {
                bean.addError("debe seleccionar un prestador para poder realizar la inserción");
                return;
            }

            MpPrescripcionHistorico historico = new MpPrescripcionHistorico();
            historico.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
            historico.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
            historico.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
            historico.setEstado(1);
            bean.auditoriaGuardar(historico);
            getMpPrescripcionDetalleRemoto().inicioHistorico(historico);

            GnSmsEnvio envio = new GnSmsEnvio();
            envio.setOrigen(GnSmsEnvio.ORIGEN_MIPRES);
            envio.setEstado(GnSmsEnvio.ESTADO_CREADO);
            if (bean.getObjeto().getIdAfiliado() != null) {
                envio.setCelulares(getMpPrescripcionDetalleRemoto().consultarContactoAfiliado(bean.getObjeto().getIdAfiliado()));
                if (envio.getCelulares().length() > 10) {
                    envio.setCelulares(envio.getCelulares().substring(0, 10));
                } else if (envio.getCelulares().length() < 10) {
                    envio.setCelulares("");
                }
            } else {
                envio.setCelulares("");
            }
            String mensaje = inicializarNotificacionDireccionamiento(bean);
            if (mensaje.length() > 256) {
                mensaje = mensaje.substring(0, 256);
            }
            envio.setTexto(mensaje);
            envio.setFechaHoraCrea(new Date());
            try {
                getGnSmsEnvioRemoto().insertar(envio);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (bean.getDireccionamiento().getTieneCotizacion()) {
                if (bean.getDireccionamiento().getIdCotizacion() != null) {
                    getMpPrescripcionDetalleRemoto().cambiarEstadoCotizacionMp(bean.getDireccionamiento().getIdCotizacion(), 14); // direccionado con cotizacion
                    MpPrescripcionHistorico historico1 = new MpPrescripcionHistorico();
                    historico1.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                    historico1.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
                    historico1.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
                    historico1.setEstado(14);//direccionado con cotizacion
                    bean.auditoriaGuardar(historico1);
                    getMpPrescripcionDetalleRemoto().inicioHistorico(historico1);
                } else {
                    Integer valor = getMpPrescripcionDetalleRemoto().consultarCotizacion2(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getIdItem(), bean.getObjeto().getTipoTecnologiaItem());
                    if (valor != null) {
                        getMpPrescripcionDetalleRemoto().cambiarEstadoCotizacionMp(bean.getDireccionamiento().getIdCotizacion(), 17);//direccionado - cotizacion activa
                        MpPrescripcionHistorico historico1 = new MpPrescripcionHistorico();
                        historico1.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                        historico1.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
                        historico1.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
                        historico1.setEstado(17);//direccionado - sin asignar cotizacion activa
                        bean.auditoriaGuardar(historico1);
                        getMpPrescripcionDetalleRemoto().inicioHistorico(historico1);
                    }
                }
            } else {
                Integer valor = getMpPrescripcionDetalleRemoto().consultarCotizacion2(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getIdItem(), bean.getObjeto().getTipoTecnologiaItem());
                if (valor != null) {
                    getMpPrescripcionDetalleRemoto().cambiarEstadoCotizacionMp(valor, 15);
                }
                MpPrescripcionHistorico historico2 = new MpPrescripcionHistorico();
                historico2.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                historico2.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
                historico2.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
                historico2.setEstado(15);//direccionado - sin cotizacion
                bean.auditoriaGuardar(historico2);
                getMpPrescripcionDetalleRemoto().inicioHistorico(historico2);

            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    public String generarMensajeCorreoDir(MpDireccionamiento dir, MipresBean bean, int entregas) {
        StringBuilder mensaje = new StringBuilder();
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMM yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaFormateada = formatoHora.format(fechaActual);

        mensaje.append("Se direcciono un Mipres de manera exitosa para la prescripción").append("\n");
        mensaje.append(" Número: ").append(bean.getObjeto().getNumeroPrescripcion()).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" Fecha: ").append(fechaFormateada).append("\n");
        mensaje.append(" Hora: ").append(horaFormateada).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" Tecnologìa: ").append(obtenerTecnologia(bean.getObjeto().getTipoTecnologiaItem())).append("\n");
        mensaje.append(" Nombre Tecnologìa: ").append(dir.getCodigoMpPropio()).append(" - ").append(dir.getCodigoMpEntrega()).append("\n");
        mensaje.append(" Cantidad: ").append(dir.getEntregaTotal()).append("\n");
        mensaje.append(" Entregas: ").append(entregas).append("\n");
        mensaje.append(" Paciente: ");
        if (bean.getObjeto().getPrimerNombreAfiliado() != null) {
            mensaje.append(bean.getObjeto().getPrimerNombreAfiliado());
        }
        if (bean.getObjeto().getSegundoNombreAfiliado() != null) {
            mensaje.append(" ").append(bean.getObjeto().getSegundoNombreAfiliado());
        }
        if (bean.getObjeto().getPrimerApellidoAfiliado() != null) {
            mensaje.append(" ").append(bean.getObjeto().getPrimerApellidoAfiliado());
        }
        if (bean.getObjeto().getSegundoApellidoAfiliado() != null) {
            mensaje.append(" ").append(bean.getObjeto().getSegundoApellidoAfiliado());
        }
        mensaje.append(" Ámbito: ").append(obtenerAmbito(bean.getObjeto().getCodigoAmbitoAtencion(), bean.getObjeto().getReferenciaContra())).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" Prestador: ").append(dir.getPrestadorRazonSocial()).append("\n");
        mensaje.append(" Dirección: ").append(dir.getDireccionSede()).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" SAVIA SALUD E.P.S ").append("\n");
        mensaje.append("").append("\n");

        return mensaje.toString();
    }

    public String generarMensajeCorreoDirIndividual(MpDireccionamiento dir, MipresBean bean) {
        StringBuilder mensaje = new StringBuilder();
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMM yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaFormateada = formatoHora.format(fechaActual);

        mensaje.append("Se direcciono un Mipres de manera exitosa para la prescripción").append("\n");
        mensaje.append(" Número : ").append(bean.getObjeto().getNumeroPrescripcion()).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" Fecha : ").append(fechaFormateada).append("\n");
        mensaje.append(" Hora : ").append(horaFormateada).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" Tecnologia : ").append(obtenerTecnologia(bean.getObjeto().getTipoTecnologiaItem())).append("\n");
        mensaje.append(" Nombre Tecnologia : ").append(dir.getCodigoMpPropio()).append(" - ").append(dir.getCodigoMpEntrega()).append("\n");
        mensaje.append(" Cantidad : ").append(dir.getEntregaTotal()).append("\n");
        mensaje.append(" Entregas : ").append(dir.getConsecutivoEntrega()).append("\n");
        mensaje.append(" Paciente : ");
        if (bean.getObjeto().getPrimerNombreAfiliado() != null) {
            mensaje.append(bean.getObjeto().getPrimerNombreAfiliado());
        }
        if (bean.getObjeto().getSegundoNombreAfiliado() != null) {
            mensaje.append(" ").append(bean.getObjeto().getSegundoNombreAfiliado());
        }
        if (bean.getObjeto().getPrimerApellidoAfiliado() != null) {
            mensaje.append(" ").append(bean.getObjeto().getPrimerApellidoAfiliado());
        }
        if (bean.getObjeto().getSegundoApellidoAfiliado() != null) {
            mensaje.append(" ").append(bean.getObjeto().getSegundoApellidoAfiliado());
        }
        mensaje.append(" Ámbito : ").append(obtenerAmbito(bean.getObjeto().getCodigoAmbitoAtencion(), bean.getObjeto().getReferenciaContra())).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" Prestador : ").append(dir.getPrestadorRazonSocial()).append("\n");
        mensaje.append(" Dirección : ").append(dir.getDireccionSede()).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" SAVIA SALUD E.P.S ").append("\n");
        mensaje.append("").append("\n");

        return mensaje.toString();
    }

    private boolean tieneAuditoria(Integer prescripcion, Integer medicamento, Integer tecnologia, Integer insumo) throws Exception {
        boolean respuesta = false;
        respuesta = getMpPrescripcionDetalleRemoto().tieneDireccionamiento(prescripcion, medicamento, tecnologia, insumo);
        if (!respuesta) {
            respuesta = getMpPrescripcionDetalleRemoto().tieneNoDireccionamiento(prescripcion, medicamento, tecnologia, insumo);
        }
        return respuesta;
    }

    public void crearAuditoria(MipresBean bean) {
        try {
            Integer prescripcionM = null;
            Integer prescripcionT = null;
            Integer prescripcionI = null;
            Integer tipoTec;
            getMpPrescripcionDetalleRemoto().insertarAuditoria(bean.getPrescripcionAuditoria());

            if (bean.getPrescripcionAuditoria().getMpMedicamentoId() != null) {
                prescripcionM = bean.getPrescripcionAuditoria().getMpMedicamentoId().getId();
            } else if (bean.getPrescripcionAuditoria().getMpTecnologiaId() != null) {
                prescripcionT = bean.getPrescripcionAuditoria().getMpTecnologiaId().getId();
            } else if (bean.getPrescripcionAuditoria().getMpInsumoId() != null) {
                prescripcionI = bean.getPrescripcionAuditoria().getMpInsumoId().getId();
            }
            int est = bean.getPrescripcionAuditoria().getEstado();
            int estado = 0;

            switch (est) {
                case 1:
                    estado = 4;
                    break;
                case 2:
                    estado = 5;
                    break;
                case 3:
                    estado = 6;
                    break;
                case 4:
                    estado = 7;
                    break;
                case 5:
                    estado = 8;
                    break;
                case 6:
                    estado = 9;
                    break;
                case 7:
                    estado = 10;
                    break;
                default:
                    break;
            }
            if (!tieneAuditoria(bean.getPrescripcionAuditoria().getMpPrescripcionId().getId(), prescripcionM, prescripcionT, prescripcionI)) {
                getMpPrescripcionDetalleRemoto().cambiarEstadoPrescripcion(prescripcionM, prescripcionT, prescripcionI, estado);
            }

            MpPrescripcionHistorico historico = new MpPrescripcionHistorico();
            historico.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
            historico.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
            historico.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
            historico.setEstado(estado);
            bean.auditoriaGuardar(historico);
            getMpPrescripcionDetalleRemoto().inicioHistorico(historico);
            if (bean.getCorreoPrestador() != null) {
                String cavezadoAnulacion = "";
                String correoPrestador = bean.getCorreoPrestador();
                String encabezadoPrestador = inicializarEncabezado(bean.getPrescripcionAuditoria().getEstado());
                if (bean.isRequiereAnulacion()) {
                    cavezadoAnulacion = "Solicitud Anulación - ";
                    getMpPrescripcionDetalleRemoto().cambiarEstadoPrescripcionInicial(bean.getObjeto().getIdPrescripcion());
                }
                encabezadoPrestador = cavezadoAnulacion + encabezadoPrestador;
                String mensajePrestador = bean.getNotificacionHistorico().getMensaje();
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_MIPRES, correoPrestador, encabezadoPrestador, mensajePrestador, GnCorreoEnvio.TIPO_TEXTO);
                try {
                    getGnCorreoEnvioRemoto().insertar(envio);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (bean.getCorreoPaciente() != null) {
                String cavezadoAnulacion = "";
                String correoPaciente = bean.getCorreoPaciente();
                String encabezadoPaciente = inicializarEncabezado(bean.getPrescripcionAuditoria().getEstado());
                if (bean.isRequiereAnulacion()) {
                    cavezadoAnulacion = "Solicitud Anulación - ";
                }
                encabezadoPaciente = cavezadoAnulacion + encabezadoPaciente;
                String mensajePaciente = bean.getNotificacionHistorico().getMensaje();
                GnCorreoEnvio envio = new GnCorreoEnvio(GnCorreoEnvio.ORIGEN_MIPRES, correoPaciente, encabezadoPaciente, mensajePaciente, GnCorreoEnvio.TIPO_TEXTO);

                try {
                    getGnCorreoEnvioRemoto().insertar(envio);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (bean.getTelefonoPrestador() != null) {
                GnSmsEnvio envio = new GnSmsEnvio();
                envio.setOrigen(GnSmsEnvio.ORIGEN_MIPRES);
                envio.setEstado(GnSmsEnvio.ESTADO_CREADO);
                envio.setCelulares(bean.getTelefonoPrestador());
                String mensaje = bean.getNotificacionHistorico().getMensaje();
                if (mensaje.length() > 256) {
                    mensaje = mensaje.substring(0, 256);
                }
                envio.setTexto(mensaje);
                envio.setFechaHoraCrea(new Date());
                try {
                    getGnSmsEnvioRemoto().insertar(envio);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (bean.getTelefonoPaciente() != null) {
                GnSmsEnvio envio = new GnSmsEnvio();
                envio.setOrigen(GnSmsEnvio.ORIGEN_MIPRES);
                envio.setEstado(GnSmsEnvio.ESTADO_CREADO);
                envio.setCelulares(bean.getTelefonoPaciente());
                String mensaje = bean.getNotificacionHistorico().getMensaje();
                if (mensaje.length() > 256) {
                    mensaje = mensaje.substring(0, 256);
                }
                envio.setTexto(mensaje);
                envio.setFechaHoraCrea(new Date());
                try {
                    getGnSmsEnvioRemoto().insertar(envio);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    public String inicializarEncabezado(Integer estado) {
        String encabezado = "";
        switch (estado) {
            case 1:
                encabezado = "Auditado - Direccionado";
                break;
            case 2:
                encabezado = "Auditado - No direccionamiento";
                break;
            case 3:
                encabezado = "Ampliación justificación no pbs insuficiente o invalida para definir el aval de la tecnologí­a";
                break;
            case 4:
                encabezado = "Error en la prescripción: cantidad duración tratamiento, via admin";
                break;
            case 5:
                encabezado = "Avalado Hos_Urg";
                break;
            case 6:
                encabezado = "No Avalado Hos_Urg";
                break;
            case 7:
                encabezado = "No direccionamiento sin causa";
                break;
            default:
                break;
        }

        return encabezado;
    }

    @Override
    public void consultarAuditoria(MipresBean bean) {
        try {
            bean.setMostrarBotonDireccionamiento(getMpPrescripcionDetalleRemoto().consultarAuditoria(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getIdItem(), bean.getObjeto().getTipoTecnologiaItem()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
            e.printStackTrace();
            bean.addError("error en consulta tabla auditoria");
        }
    }

    @Override
    public MpPrescripcionDetalle consultarPrescripcionDetalle(Integer tipoTec, Integer id) {
        try {
            return getMpPrescripcionDetalleRemoto().consultarPrescripcionDetalle(tipoTec, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retorna null en caso de excepción
        }
    }

    @Override
    public void consultarCorreoPrestador(MipresBean bean) {
        try {
            bean.setCorreoPrestador(getMpPrescripcionDetalleRemoto().consultarCorreoPrestador(bean.getPrescripcionAuditoria().getDocumentoPrestador()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
            e.printStackTrace();
            bean.addError("error en consulta Documento Prestador");
        }
    }

    @Override
    public void consultarContratoDetalle(MipresBean bean) {
        try {
            bean.getParamConsultaContratoDetalle().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaContratoDetalle(bean.getParamConsultaContratoDetalle(), bean.getIdSeleccionTecnologia(), bean.getTipoSeleccionTecnologia()));
            bean.setRegistroDetalleContrato(getMpPrescripcionDetalleRemoto().consultarListaContratoDetalle(bean.getParamConsultaContratoDetalle(), bean.getIdSeleccionTecnologia(), bean.getTipoSeleccionTecnologia()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public void consultarContratoDetalleAnulado(MipresBean bean) {
        try {
            bean.getParamConsultaContratoDetalle().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaContratoDetalle(bean.getParamConsultaContratoDetalle(), bean.getIdSeleccionTecnologiaAnulada(), bean.getTipoSeleccionTecnologiaAnulada()));
            bean.setRegistroDetalleContrato(getMpPrescripcionDetalleRemoto().consultarListaContratoDetalle(bean.getParamConsultaContratoDetalle(), bean.getIdSeleccionTecnologiaAnulada(), bean.getTipoSeleccionTecnologiaAnulada()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    @Override
    public MpEntregaFactura consultarFactura(Integer id) {
        MpEntregaFactura factura = null;
        try {
            factura = getMpPrescripcionDetalleRemoto().consultarFactura(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factura;
    }

    @Override
    public MpDireccionamientoEntregado actualizarDireEntrega(Boolean cierre, Integer id) {
        MpDireccionamientoEntregado direcEntrega = new MpDireccionamientoEntregado();
        try {
            direcEntrega = (getMpPrescripcionDetalleRemoto().actualizarCiclo(cierre, id));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return direcEntrega;
    }

    @Override
    public MpDireccionamientoEntregado actualizarDireEntregaAnula(Boolean anula, Integer id) {
        MpDireccionamientoEntregado direcEntrega = new MpDireccionamientoEntregado();
        try {
            direcEntrega = (getMpPrescripcionDetalleRemoto().actualizarAnula(anula, id));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return direcEntrega;
    }

    @Override
    public MpDireccionamientoEntregado ActualizarCompletaUltima(Boolean val, Integer valor, Integer id) {
        MpDireccionamientoEntregado direcEntrega = new MpDireccionamientoEntregado();
        try {
            direcEntrega = (getMpPrescripcionDetalleRemoto().ActualizarCompletaUltima(val, valor, id));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return direcEntrega;
    }

    @Override
    public void consultarCorreoAfiliado(MipresBean bean) {
        try {
            bean.setCorreoPaciente(getMpPrescripcionDetalleRemoto().consultarCorreoAfiliado(bean.getPrescripcionAuditoria().getIdAfiliado()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
            e.printStackTrace();
            bean.addError("error en consulta Documento Prestador");
        }
    }

    @Override
    public void consultarPrescripcionPorId(MipresBean bean) {
        try {
            bean.setListaPrescripcionesAsociadas(getMpPrescripcionDetalleRemoto().consultarPrescripciones(bean.getObjeto().getNumeroDocumentoAfiliado()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
            e.printStackTrace();
            bean.addError("error en consulta prescripcines afiliado");
        }
    }

    @Override
    public void consultarItemsPorId(MipresBean bean) {
        try {
            bean.setListaPrescripcionDtoRespaldo(new ArrayList<>());
            MpDetalleDTO mpDetalleDTO = null;
            for (MpPrescripcionMedicamento medicamento : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionMedicamento(Integer.parseInt(bean.getNoDireccionado().getNumeroPrescripcionAsociada()))) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(medicamento.getId());
                mpDetalleDTO.setCantidadTotal(medicamento.getCantidadTotalEntrega() != null ? medicamento.getCantidadTotalEntrega().intValue() : null);
                mpDetalleDTO.setCantidadTotalPrescrita(medicamento.getCantidadTotalFormulada() != null ? medicamento.getCantidadTotalFormulada().toString() : null);

                mpDetalleDTO.setCodigoTecnologia(String.valueOf(medicamento.getTipoTecnologia()));
                mpDetalleDTO.setCodigoTecnologiaAvalEps("");
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(medicamento.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(medicamento.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(medicamento.getCantidadTratamiento());
                mpDetalleDTO.setEstado(bean.obtenerEstado(medicamento.getEstado()));
                mpDetalleDTO.setPendientes(medicamento.getPendientes());
                mpDetalleDTO.setFechaDireccionamiento(medicamento.getFechaDireccionamiento());
                mpDetalleDTO.setNombreTecnologia(medicamento.getMedPbsUtilizado());
                mpDetalleDTO.setNombreTecnologiaAvalEps(medicamento.getMedPbsUtilizado());
                if (medicamento.getTipoTecnologia() == 4) {
                    mpDetalleDTO.setNombreTecnologiaPrescripta(medicamento.getMaeProductosNutricionalesValor());
                    mpDetalleDTO.setCodigoFormaFarmaceutica(medicamento.getMaeProductosNutricionalesCodigo());
                } else {
                    mpDetalleDTO.setNombreTecnologiaPrescripta(medicamento.getDescripcionMedicamentoPrincipioActivo());
                    mpDetalleDTO.setCodigoFormaFarmaceutica(medicamento.getCodigoFormulaFarmaceutica());
                }
                mpDetalleDTO.setNumeroEntregas(medicamento.getEntregados());
                mpDetalleDTO.setNumeroTransaccion(medicamento.getIdTransaccion());
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoMedicamento(medicamento.getTipoMedicamento() == null ? medicamento.getTipoTecnologia() : medicamento.getTipoMedicamento()));
                mpDetalleDTO.setIntTipoMedicamento(medicamento.getTipoMedicamento() == null ? medicamento.getTipoTecnologia() : medicamento.getTipoMedicamento());
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(medicamento.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(medicamento.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(medicamento.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(medicamento.getCantidadTratamiento());
                bean.getListaPrescripcionDtoRespaldo().add(mpDetalleDTO);
            }

            for (MpPrescripcionTecnologia tecnologia : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionTecnologia(Integer.parseInt(bean.getNoDireccionado().getNumeroPrescripcionAsociada()))) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(tecnologia.getId());
                mpDetalleDTO.setCantidadTotal(tecnologia.getCantidadTotal());
                mpDetalleDTO.setCantidadTotalPrescrita(tecnologia.getCantidadFormulada() != null ? tecnologia.getCantidadFormulada().toString() : null);
                mpDetalleDTO.setCodigoTecnologia(tecnologia.getMaTecnologiaCodigo());
                mpDetalleDTO.setConsecutivo(tecnologia.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(tecnologia.getCantidadDuracionTratamiento());
                mpDetalleDTO.setNombreTecnologia(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNombreTecnologiaAvalEps(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNombreTecnologiaPrescripta(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(tecnologia.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(tecnologia.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(tecnologia.getTipoPrestacion()));
                bean.getListaPrescripcionDtoRespaldo().add(mpDetalleDTO);
            }

            for (MpPrescripcionInsumo insumo : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionInsumo(Integer.parseInt(bean.getNoDireccionado().getNumeroPrescripcionAsociada()))) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(insumo.getId());
                mpDetalleDTO.setCantidadTotal(insumo.getCantidad() != null ? Integer.parseInt(insumo.getCantidad()) : null);
                mpDetalleDTO.setCantidadTotalPrescrita(insumo.getCantidadFormulada());
                mpDetalleDTO.setCodigoTecnologia(insumo.getCodigoDispositivo());
                mpDetalleDTO.setCodigoTecnologiaAvalEps(insumo.getCodigoDispositivo());
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(insumo.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(insumo.getConsecutivoOrden());
                BigDecimal cantidadTotalEntrega = insumo.getCantidadTotalEntrega();
                Integer duracionTratamientoOrdenado = cantidadTotalEntrega.intValue();

                mpDetalleDTO.setDuracionTratamientoOrdenado(duracionTratamientoOrdenado);

                mpDetalleDTO.setEstado(bean.obtenerEstado(insumo.getEstado()));
                mpDetalleDTO.setFechaDireccionamiento(insumo.getFechaDireccionamiento());
                mpDetalleDTO.setNombreTecnologia(insumo.getNombreTecnologiaAvalada());
                mpDetalleDTO.setPendientes(insumo.getPendiente());
                mpDetalleDTO.setNombreTecnologiaAvalEps(insumo.getNombreTecnologiaAvalada());
                switch (insumo.getTipoTecnologia()) {
                    case 3:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getMaeDispositivosNombre());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getMaeDispositivosCodigo());
                        break;
                    case 5:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getMaeServiciosComplementariosNombre());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getMaeServiciosComplementariosCodigo());
                        break;
                    default:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getNombreTecnologiaAvalada());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getCodigoForma());
                        break;
                }
                mpDetalleDTO.setNumeroEntregas(insumo.getEntregados());
                mpDetalleDTO.setNumeroTransaccion(insumo.getIdTransaccion());
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTO.setIntTipoMedicamento(insumo.getTipoTecnologia());
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(insumo.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(insumo.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(insumo.getCantidadTotalEntrega().intValue());
                bean.getListaPrescripcionDtoRespaldo().add(mpDetalleDTO);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
            e.printStackTrace();
            bean.addError("error en consulta prescripcines afiliado");
        }
    }

    @Override
    public void consultarItemsPorIdDir(MipresBean bean) {
        try {
            bean.setListaPrescripcionDtoRespaldo(new ArrayList<>());
            MpDetalleDTO mpDetalleDTO = null;
            for (MpPrescripcionMedicamento medicamento : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionMedicamento(Integer.parseInt(bean.getDireccionamiento().getNumeroPrescripcionAso()))) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(medicamento.getId());
                mpDetalleDTO.setCantidadTotal(medicamento.getCantidadTotalEntrega() != null ? medicamento.getCantidadTotalEntrega().intValue() : null);
                mpDetalleDTO.setCantidadTotalPrescrita(medicamento.getCantidadTotalFormulada() != null ? medicamento.getCantidadTotalFormulada().toString() : null);

                mpDetalleDTO.setCodigoTecnologia(String.valueOf(medicamento.getTipoTecnologia()));
                mpDetalleDTO.setCodigoTecnologiaAvalEps("");
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(medicamento.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(medicamento.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(medicamento.getCantidadTratamiento());
                mpDetalleDTO.setEstado(bean.obtenerEstado(medicamento.getEstado()));
                mpDetalleDTO.setPendientes(medicamento.getPendientes());
                mpDetalleDTO.setFechaDireccionamiento(medicamento.getFechaDireccionamiento());
                mpDetalleDTO.setNombreTecnologia(medicamento.getMedPbsUtilizado());
                mpDetalleDTO.setNombreTecnologiaAvalEps(medicamento.getMedPbsUtilizado());
                if (medicamento.getTipoTecnologia() == 4) {
                    mpDetalleDTO.setNombreTecnologiaPrescripta(medicamento.getMaeProductosNutricionalesValor());
                    mpDetalleDTO.setCodigoFormaFarmaceutica(medicamento.getMaeProductosNutricionalesCodigo());
                } else {
                    mpDetalleDTO.setNombreTecnologiaPrescripta(medicamento.getDescripcionMedicamentoPrincipioActivo());
                    mpDetalleDTO.setCodigoFormaFarmaceutica(medicamento.getCodigoFormulaFarmaceutica());
                }
                mpDetalleDTO.setNumeroEntregas(medicamento.getEntregados());
                mpDetalleDTO.setNumeroTransaccion(medicamento.getIdTransaccion());
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoMedicamento(medicamento.getTipoMedicamento() == null ? medicamento.getTipoTecnologia() : medicamento.getTipoMedicamento()));
                mpDetalleDTO.setIntTipoMedicamento(medicamento.getTipoMedicamento() == null ? medicamento.getTipoTecnologia() : medicamento.getTipoMedicamento());
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(medicamento.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(medicamento.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(medicamento.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(medicamento.getCantidadTratamiento());
                bean.getListaPrescripcionDtoRespaldo().add(mpDetalleDTO);
            }

            for (MpPrescripcionTecnologia tecnologia : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionTecnologia(Integer.parseInt(bean.getDireccionamiento().getNumeroPrescripcionAso()))) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(tecnologia.getId());
                mpDetalleDTO.setCantidadTotal(tecnologia.getCantidadTotal());
                mpDetalleDTO.setCantidadTotalPrescrita(tecnologia.getCantidadFormulada() != null ? tecnologia.getCantidadFormulada().toString() : null);
                mpDetalleDTO.setCodigoTecnologia(tecnologia.getMaTecnologiaCodigo());
                mpDetalleDTO.setConsecutivo(tecnologia.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(tecnologia.getCantidadDuracionTratamiento());
                mpDetalleDTO.setNombreTecnologia(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNombreTecnologiaAvalEps(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNombreTecnologiaPrescripta(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(tecnologia.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(tecnologia.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(tecnologia.getTipoPrestacion()));
                bean.getListaPrescripcionDtoRespaldo().add(mpDetalleDTO);
            }

            for (MpPrescripcionInsumo insumo : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionInsumo(Integer.parseInt(bean.getDireccionamiento().getNumeroPrescripcionAso()))) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(insumo.getId());
                mpDetalleDTO.setCantidadTotal(insumo.getCantidad() != null ? Integer.parseInt(insumo.getCantidad()) : null);
                mpDetalleDTO.setCantidadTotalPrescrita(insumo.getCantidadFormulada());
                mpDetalleDTO.setCodigoTecnologia(insumo.getCodigoDispositivo());
                mpDetalleDTO.setCodigoTecnologiaAvalEps(insumo.getCodigoDispositivo());
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(insumo.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(insumo.getConsecutivoOrden());
                BigDecimal cantidadTotalEntrega = insumo.getCantidadTotalEntrega();
                Integer duracionTratamientoOrdenado = cantidadTotalEntrega.intValue();

                mpDetalleDTO.setDuracionTratamientoOrdenado(duracionTratamientoOrdenado);

                mpDetalleDTO.setEstado(bean.obtenerEstado(insumo.getEstado()));
                mpDetalleDTO.setFechaDireccionamiento(insumo.getFechaDireccionamiento());
                mpDetalleDTO.setNombreTecnologia(insumo.getNombreTecnologiaAvalada());
                mpDetalleDTO.setPendientes(insumo.getPendiente());
                mpDetalleDTO.setNombreTecnologiaAvalEps(insumo.getNombreTecnologiaAvalada());
                switch (insumo.getTipoTecnologia()) {
                    case 3:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getMaeDispositivosNombre());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getMaeDispositivosCodigo());
                        break;
                    case 5:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getMaeServiciosComplementariosNombre());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getMaeServiciosComplementariosCodigo());
                        break;
                    default:
                        mpDetalleDTO.setNombreTecnologiaPrescripta(insumo.getNombreTecnologiaAvalada());
                        mpDetalleDTO.setCodigoFormaFarmaceutica(insumo.getCodigoForma());
                        break;
                }
                mpDetalleDTO.setNumeroEntregas(insumo.getEntregados());
                mpDetalleDTO.setNumeroTransaccion(insumo.getIdTransaccion());
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTO.setIntTipoMedicamento(insumo.getTipoTecnologia());
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(insumo.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(insumo.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(insumo.getCantidadTotalEntrega().intValue());
                bean.getListaPrescripcionDtoRespaldo().add(mpDetalleDTO);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
            e.printStackTrace();
            bean.addError("error en consulta prescripcines afiliado");
        }
    }

    public String inicializarNotificacion(MpPrescripcionDetalle objeto) {
        StringBuilder mensaje = new StringBuilder();

        SimpleDateFormat fecha = new SimpleDateFormat("dd MMM yyyy");
        String fechaFormateada = fecha.format(objeto.getFechaPrescripcion());

        SimpleDateFormat hora = new SimpleDateFormat("hh:mm:ss");
        String horaFormateada = hora.format(objeto.getHoraPrescripcion());

        mensaje.append(" Número : ").append(objeto.getNumeroPrescripcion()).append("\n");
        mensaje.append(" Fecha : ").append(fechaFormateada).append("\n");
        mensaje.append(" Hora : ").append(horaFormateada).append("\n");
        mensaje.append(" Paciente : ");
        if (objeto.getPrimerNombreAfiliado() != null) {
            mensaje.append(objeto.getPrimerNombreAfiliado());
        }
        if (objeto.getSegundoNombreAfiliado() != null) {
            mensaje.append(" ").append(objeto.getSegundoNombreAfiliado());
        }
        if (objeto.getPrimerApellidoAfiliado() != null) {
            mensaje.append(" ").append(objeto.getPrimerApellidoAfiliado());
        }
        if (objeto.getSegundoApellidoAfiliado() != null) {
            mensaje.append(" ").append(objeto.getSegundoApellidoAfiliado());
        }
        mensaje.append(" (").append(objeto.getNumeroDocumentoAfiliado()).append(")\n");

        mensaje.append(" Profesional : ");
        if (objeto.getPrimerNombrePrestador() != null) {
            mensaje.append(objeto.getPrimerNombrePrestador());
        }
        if (objeto.getSegundoNombrePrestador() != null) {
            mensaje.append(" ").append(objeto.getSegundoNombrePrestador());
        }
        if (objeto.getPrimerApellidoPrestador() != null) {
            mensaje.append(" ").append(objeto.getPrimerApellidoPrestador());
        }
        if (objeto.getSegundoApellidoPrestador() != null) {
            mensaje.append(" ").append(objeto.getSegundoApellidoPrestador());
        }
        mensaje.append(" (").append(objeto.getDocumentoPrestador()).append(")\n");
        if (objeto.getReferenciaContra() == null) {
            objeto.setReferenciaContra(false);
        }
        if (objeto.getCodigoAmbitoAtencion() == null) {
            objeto.setCodigoAmbitoAtencion("1");
        }
        mensaje.append(" Ámbito : ").append(obtenerAmbito(objeto.getCodigoAmbitoAtencion(), objeto.getReferenciaContra())).append("\n");
        mensaje.append(" SAVIA SALUD E.P.S : ").append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" Nota : ").append("\n");

        return mensaje.toString();
    }

    public String obtenerAmbito(String id, Boolean referencia) {
        String resultado = "";

        if (id != null) {
            switch (id) {
                case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_PRIORIZADO:
                    resultado = "Ambulatorio - Priorizado";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_AMBULATORIO_NO_PRIORIZADO:
                    resultado = "Ambulatorio - No Priorizado";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_DOMICILIARIO:
                    resultado = "Hospitalario - Domiciliario";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_HOSPITALARIO_INTERNACION:
                    resultado = "Hospitalario - Internacion";
                    break;
                case MpPrescripcionDetalle.ID_AMBITO_URGENCIAS:
                    resultado = "Urgencias";
                    break;
                case "1":
                    resultado = "Recobrante";
                    break;
                default:
                    resultado = "";
                    break;
            }

            if (Boolean.TRUE.equals(referencia)) {
                resultado += " - Referencia Contrarreferencia";
            }
        }

        return resultado;
    }

    public String inicializarNotificacionDireccionamiento(MipresBean bean) {
        StringBuilder mensaje = new StringBuilder();
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMM yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaFormateada = formatoHora.format(fechaActual);

        mensaje.append("Se direcciono un Mipres de manera exitosa para la prescripción").append("\n");
        mensaje.append(" Número : ").append(bean.getObjeto().getNumeroPrescripcion()).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" Fecha : ").append(fechaFormateada).append("\n");
        mensaje.append(" Hora : ").append(horaFormateada).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" Tecnologia : ").append(obtenerTecnologia(bean.getObjeto().getTipoTecnologiaItem())).append("\n");
        mensaje.append(" Nombre Tecnologia : ").append(bean.getObjeto().getNombreItem()).append("\n");
        mensaje.append(" Cantidad : ").append(bean.getDireccionamiento().getEntregaTotal()).append("\n");
        mensaje.append(" Entregas : ").append(bean.getDireccionamiento().getConsecutivoEntrega()).append("\n");
        mensaje.append(" Paciente : ");
        if (bean.getObjeto().getPrimerNombreAfiliado() != null) {
            mensaje.append(bean.getObjeto().getPrimerNombreAfiliado());
        }
        if (bean.getObjeto().getSegundoNombreAfiliado() != null) {
            mensaje.append(" ").append(bean.getObjeto().getSegundoNombreAfiliado());
        }
        if (bean.getObjeto().getPrimerApellidoAfiliado() != null) {
            mensaje.append(" ").append(bean.getObjeto().getPrimerApellidoAfiliado());
        }
        if (bean.getObjeto().getSegundoApellidoAfiliado() != null) {
            mensaje.append(" ").append(bean.getObjeto().getSegundoApellidoAfiliado());
        }
        mensaje.append(" Ámbito : ").append(obtenerAmbito(bean.getObjeto().getCodigoAmbitoAtencion(), bean.getObjeto().getReferenciaContra())).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" Prestador : ").append(bean.getDireccionamiento().getNombreSede()).append("\n");
        mensaje.append(" Dirección : ").append(bean.getDireccionamiento().getDireccionSede()).append("\n");
        mensaje.append("").append("\n");
        mensaje.append(" SAVIA SALUD E.P.S ").append("\n");
        mensaje.append("").append("\n");

        return mensaje.toString();
    }

    public String obtenerTecnologia(Integer valor) {
        String resultado = "";
        if (valor != null) {

            switch (valor) {
                case 1:
                    resultado = "Medicamento/s ";
                    break;
                case 2:
                    resultado = "procedimiento/s ";
                    break;
                case 3:
                    resultado = "dispositivo/s ";
                    break;
                case 4:
                    resultado = "Producto/s Nutricional/es ";
                    break;
                case 5:
                    resultado = "Servicio/s Complementario/s";
                    break;
                default:
                    resultado = "";
                    break;
            }
        }
        return resultado;
    }

    @Override
    public void guardarNoDirec(MipresBean bean) {
        Integer prescripcionM = null;
        Integer prescripcionT = null;
        Integer prescripcionI = null;
        bean.getNoDireccionado().setNumeroPrescripcionAsociada(bean.getNoDireccionado().getNumeroPrescripcionAsociada());
        if (bean.getNoDireccionado().getMpPrescripcionMedicamentosId() != null) {
            prescripcionM = bean.getNoDireccionado().getMpPrescripcionMedicamentosId().getId();
        } else if (bean.getNoDireccionado().getMpPrescripcionTecnologiasId() != null) {
            prescripcionT = bean.getNoDireccionado().getMpPrescripcionTecnologiasId().getId();
        } else if (bean.getNoDireccionado().getMpPrescripcionInsumosId() != null) {
            prescripcionI = bean.getNoDireccionado().getMpPrescripcionInsumosId().getId();
        }
        bean.getNoDireccionado().setJustificacionNoDireccionamiento(bean.getNoDireccionado().getJustificacionNoDireccionamiento());
        bean.getNoDireccionado().setCodigoNoDireccionamiento(bean.getNoDireccionado().getCodigoNoDireccionamiento());
        int est = 2;
        MpPrescripcionHistorico historico = new MpPrescripcionHistorico();
        historico.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
        historico.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
        historico.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
        historico.setEstado(est);
        bean.auditoriaGuardar(historico);
        try {
            getMpPrescripcionDetalleRemoto().inicioHistorico(historico);
        } catch (Exception ex) {
            Logger.getLogger(MipresImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getMpPrescripcionDetalleRemoto().cambiarEstadoPrescripcion(prescripcionM, prescripcionT, prescripcionI, est);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bean.auditoriaGuardar(bean.getNoDireccionado());
        try {
            getMpPrescripcionDetalleRemoto().insertarNoDireccionamiento(bean.getNoDireccionado());
            bean.crearLog("Guardar No Direccionamiento", bean.getNoDireccionado().toString());

            if (!bean.getTieneCotizacion()) {
                Integer valor = getMpPrescripcionDetalleRemoto().consultarCotizacion2(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getIdItem(), bean.getObjeto().getTipoTecnologiaItem());
                if (valor != null) {
                    getMpPrescripcionDetalleRemoto().cambiarEstadoCotizacionMp(valor, 18);//nodireccionado - sin cotizacion 
                    MpPrescripcionHistorico historico1 = new MpPrescripcionHistorico();
                    historico1.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                    historico1.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
                    historico1.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
                    historico1.setEstado(20);//nodireccionado - sin cotizacion 
                    bean.auditoriaGuardar(historico1);
                    getMpPrescripcionDetalleRemoto().inicioHistorico(historico1);
                }
            } else {
                Integer valor = getMpPrescripcionDetalleRemoto().consultarCotizacion2(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getIdItem(), bean.getObjeto().getTipoTecnologiaItem());
                if (valor != null) {
                    getMpPrescripcionDetalleRemoto().cambiarEstadoCotizacionMp(valor, 19);//nodireccionado - con cotizacion 

                    MpPrescripcionHistorico historico2 = new MpPrescripcionHistorico();
                    historico2.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                    historico2.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
                    historico2.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
                    historico2.setEstado(21);//nodireccionado - con cotizacion 
                    bean.auditoriaGuardar(historico2);
                    getMpPrescripcionDetalleRemoto().inicioHistorico(historico2);

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void anularDireccionamiento(MipresBean bean, int id, Integer estado) {
        try {
            String usuario = bean.getConexion().getUsuario().getUsuarioNombre();
            String terminal = bean.getConexion().getIp();
            Date fecha = (new Date());
            getMpPrescripcionDetalleRemoto().anularDireccionamiento(id, estado, usuario, terminal, fecha);

            MpPrescripcionHistorico historico = new MpPrescripcionHistorico();
            historico.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
            historico.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
            historico.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
            historico.setEstado(18); //anulacion direccionamiento
            bean.auditoriaGuardar(historico);
            getMpPrescripcionDetalleRemoto().inicioHistorico(historico);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String consultarPrefijo(int id) {
        String prefijo = "";
        try {
            prefijo = UbicacionSingle.getInstance().obtenerPrefijoCompleto(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prefijo;
    }

    @Override
    public String consultarHorarioDePrescripcion(Integer id) {
        MpPrescripcion obj = null;
        String prefijo = "";
        try {
            obj = getMpPrescripcionDetalleRemoto().traerDatosPrescripcion(id);
            if (obj != null) {
                Date fecha = obj.getFechaPrescripcion();
                Date hora = obj.getHoraPrescripcion();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                prefijo = formatoFecha.format(fecha) + " " + formatoHora.format(hora);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prefijo;
    }

    @Override
    public MpDireccionamientoEntregado verEntrega(Integer id) {
        MpDireccionamientoEntregado direcciona = new MpDireccionamientoEntregado();
        try {
            direcciona = getMpPrescripcionDetalleRemoto().verEntrega(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return direcciona;
    }

    @Override
    public MpDireccionamientoEntregado verEntregaD(Integer id) {
        MpDireccionamientoEntregado direcciona = new MpDireccionamientoEntregado();
        try {
            direcciona = getMpPrescripcionDetalleRemoto().verEntrega(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return direcciona;
    }

    @Override
    public MpDireccionamientoEntregado verEntregaSumin(Integer id) {
        MpDireccionamientoEntregado direcciona = new MpDireccionamientoEntregado();
        try {
            direcciona = getMpPrescripcionDetalleRemoto().verEntregaSuministroD(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return direcciona;
    }

    @Override
    public MpPrescripcion consultarPrescripcion(Integer id) {
        MpPrescripcion pres = new MpPrescripcion();
        try {
            pres = getMpPrescripcionDetalleRemoto().consultarPrescripcionS(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pres;
    }

    @Override
    public Boolean anularSuministro(Integer id) {
        boolean res = false;
        try {
            res = getMpPrescripcionDetalleRemoto().anularSuministro(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void afectaPresupuestoMax(boolean afecta, Integer id) {
        try {
            getMpPrescripcionDetalleRemoto().afectaPresupuestoMax(afecta, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void registroSolicitudCotizacion(Integer prescripcion, Integer item, Integer tipo, MipresBean bean) {
        try {
            MpPrescripcionMedicamento med = new MpPrescripcionMedicamento();
            MpPrescripcionTecnologia tec = new MpPrescripcionTecnologia();
            MpPrescripcionInsumo ins = new MpPrescripcionInsumo();

            MpPrescripcion pres = getMpPrescripcionDetalleRemoto().consultarPrescripcionCot(prescripcion);

            switch (tipo) {
                case 1:
                case 4:
                    med = getMpPrescripcionDetalleRemoto().consultarPrescripcionMCot(item);
                    getMpPrescripcionDetalleRemoto().actualizarItemCot(item, tipo);
                    break;
                case 2:
                    tec = getMpPrescripcionDetalleRemoto().consultarPrescripcionTCot(item);
                    getMpPrescripcionDetalleRemoto().actualizarItemCot(item, tipo);
                    break;
                case 3:
                case 5:
                    ins = getMpPrescripcionDetalleRemoto().consultarPrescripcionICot(item);
                    getMpPrescripcionDetalleRemoto().actualizarItemCot(item, tipo);
                    break;
                default:
                    break;
            }

            MpCotizacion cotizacion = new MpCotizacion();
            cotizacion.setMpPrescripcioneId(pres);
            cotizacion.setNumeroPrescripcion(pres.getNumeroPrescripcion());
            switch (tipo) {
                case 1:
                case 4:
                    cotizacion.setMpPrescripcionMedicamentoId(med);
                    cotizacion.setTipoTecnologia(med.getTipoTecnologia());
                    cotizacion.setConsecutivoOrden(med.getConsecutivoOrden());
                    if (tipo == 1) {
                        cotizacion.setNombreTecnologia(med.getDescripcionMedicamentoPrincipioActivo());
                    } else {
                        cotizacion.setNombreTecnologia(med.getMaeProductosNutricionalesValor());
                    }
                    break;
                case 2:
                    cotizacion.setMpPrescripcionTecnologiaId(tec);
                    cotizacion.setTipoTecnologia(tec.getTipoTecnologia());
                    cotizacion.setConsecutivoOrden(tec.getConsecutivoOrden());
                    cotizacion.setNombreTecnologia(tec.getMaTecnologiaValor());
                    break;
                case 3:
                case 5:
                    cotizacion.setMpPrescripcionInsumoId(ins);
                    cotizacion.setTipoTecnologia(ins.getTipoTecnologia());
                    cotizacion.setConsecutivoOrden(ins.getConsecutivoOrden());
                    if (tipo == 3) {
                        cotizacion.setNombreTecnologia(ins.getMaeDispositivosNombre());
                    } else {
                        cotizacion.setNombreTecnologia(ins.getMaeServiciosComplementariosNombre());
                    }
                    break;
                default:
                    break;
            }
            cotizacion.setEstado(11);
            bean.auditoriaGuardar(cotizacion);
            getMpPrescripcionDetalleRemoto().insetarSolicitudCot(cotizacion);

            MpPrescripcionHistorico prescripcionHistorico = new MpPrescripcionHistorico();
            prescripcionHistorico.setMpPrescripcion(new MpPrescripcion(cotizacion.getMpPrescripcioneId().getId()));
            prescripcionHistorico.setTipoTecnologia(cotizacion.getTipoTecnologia());
            switch (tipo) {
                case 1:
                case 4:
                    prescripcionHistorico.setIdPrescripcionTecnologia(cotizacion.getMpPrescripcionMedicamentoId().getId());
                    break;
                case 2:
                    prescripcionHistorico.setIdPrescripcionTecnologia(cotizacion.getMpPrescripcionTecnologiaId().getId());
                    break;
                case 3:
                case 5:
                    prescripcionHistorico.setIdPrescripcionTecnologia(cotizacion.getMpPrescripcionInsumoId().getId());
                    break;
                default:
                    break;
            }
            prescripcionHistorico.setEstado(11);
            bean.auditoriaGuardar(prescripcionHistorico);
            getMpPrescripcionDetalleRemoto().inicioHistorico(prescripcionHistorico);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public MpPrescripcionMedicamento consultarMedicamentoSuministro(Integer id) {
        MpPrescripcionMedicamento med = new MpPrescripcionMedicamento();
        try {
            med = getMpPrescripcionDetalleRemoto().consultarMedicamentoSuministro(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return med;
    }

    @Override
    public MpPrescripcionTecnologia consultarTecnologiaSuministro(Integer id) {
        MpPrescripcionTecnologia tec = new MpPrescripcionTecnologia();
        try {
            tec = getMpPrescripcionDetalleRemoto().consultarTecnologiaSuministro(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tec;
    }

    @Override
    public MpPrescripcionInsumo consultarInsumoSuministro(Integer id) {
        MpPrescripcionInsumo ins = new MpPrescripcionInsumo();
        try {
            ins = getMpPrescripcionDetalleRemoto().consultarInsumoSuministro(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ins;
    }

    @Override
    public MpDireccionamiento consultarDireccionamientoS(Integer id) {
        MpDireccionamiento dir = new MpDireccionamiento();
        try {
            dir = getMpPrescripcionDetalleRemoto().consultarDireccionamientoS(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dir;
    }

    @Override
    public MpPrescripcionAuditoria consultarAudirotiaS(Integer id) {
        MpPrescripcionAuditoria aud = new MpPrescripcionAuditoria();
        try {
            aud = getMpPrescripcionDetalleRemoto().consultarAudirotiaS(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aud;
    }

    @Override
    public MpPrescripcionAuditoria consultarAudirotiaSis(Integer id, Integer tipo, Integer idTipo) {
        MpPrescripcionAuditoria aud = new MpPrescripcionAuditoria();
        try {
            aud = getMpPrescripcionDetalleRemoto().consultarAudirotiaSis(id, tipo, idTipo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aud;
    }

    @Override
    public List<Integer> verDireccionamientos(Integer idPrescripcion, Integer idItem, Integer tipo) {
        List<Integer> direccionamientoIds = new ArrayList<>();
        try {
            direccionamientoIds = getMpPrescripcionDetalleRemoto().consultarIdDireccionamiento(idPrescripcion, idItem, tipo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return direccionamientoIds;
    }

    @Override
    public List<MpDireccionamientoEntregado> verEntregaSinDireccionamiento(Integer idPrescripcion, Integer idItem, Integer tipo) {
        List<MpDireccionamientoEntregado> direccionamientoIds = new ArrayList<>();
        try {
            direccionamientoIds = getMpPrescripcionDetalleRemoto().consultarEntregaSinDireccionamiento(idPrescripcion, idItem, tipo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return direccionamientoIds;
    }

    @Override
    public MpDireccionamientoEntregado verEntregaSinDireccionamiento(Integer id) {
        MpDireccionamientoEntregado direccionamientoIds = new MpDireccionamientoEntregado();
        try {
            direccionamientoIds = getMpPrescripcionDetalleRemoto().consultarEntregaSinDireccionamiento(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return direccionamientoIds;
    }

    @Override
    public void guardarSuministro(MpEntregaSuministro suministro) {
        try {
            getMpPrescripcionDetalleRemoto().guardarSuministro(suministro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MpEntregaSuministro consultarSuministroDeEntrega(Integer id) {
        MpEntregaSuministro sum = null;
        try {
            sum = getMpPrescripcionDetalleRemoto().consultarSuministroDeEntrega(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    @Override
    public void cierreCiloFactura(Integer id, MipresBean bean) {
        try {
            getMpPrescripcionDetalleRemoto().cierreCiloFactura(id, bean.getConexion().getUsuario().getUsuarioNombre(), bean.getConexion().getIp());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ReporteDireccionamiento consultarDireccionamientoReporte(Integer id) {
        ReporteDireccionamiento dir = null;
        try {
            dir = getMpPrescripcionDetalleRemoto().consultarDireccionamientoReporte(id);
            dir.setStrMunicipio(consultarUbicacionReporte(Integer.parseInt(dir.getStrMunicipio())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dir;
    }

    @Override
    public Integer validarCotizacion(Integer prescripcion, Integer item, Integer tipo) {
        Integer valor = null;
        try {
            valor = getMpPrescripcionDetalleRemoto().consultarCotizacion(prescripcion, item, tipo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor;
    }

    public String consultarUbicacionReporte(Integer id) {
        String resul = "";
        try {
            resul = getMpPrescripcionDetalleRemoto().consultarUbicacionReporte(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resul;
    }

    @Override
    public List<ReportePlanManejo> gestionarPlanDeManejo(Integer id) {
        List<ReportePlanManejo> manejo = new ArrayList<>();
        try {
            ReportePlanManejo reporte = null;
            for (MpPrescripcionMedicamento medicamento : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionMedicamentoPlanManejo(id)) {
                reporte = new ReportePlanManejo();

                manejo.add(reporte);
            }

            for (MpPrescripcionTecnologia tecnologia : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionTecnologiaPlanManejo(id)) {
                reporte = new ReportePlanManejo();
                String fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                reporte.setStrFecha(fechaActual);
                reporte.setStrHora("");
                reporte.setStrNumeroPrescripcion(tecnologia.getMpPrescripcion().getNumeroPrescripcion());
                reporte.setStrDepartamento("");
                reporte.setStrMunicipio("");
                reporte.setStrCodigoHabilitacion(tecnologia.getMpPrescripcion().getSedeCodigoHabilitacion());
                reporte.setStrNumeroDocumentoPrestador(tecnologia.getMpPrescripcion().getPrestadorNumeroDocumento());
                reporte.setStrNombrePrestador(tecnologia.getMpPrescripcion().getPrestadorRazonSocial());
                reporte.setStrDireccion("");
                reporte.setStrTelefono("");
                reporte.setStrNumeroDocumentoPaciente(tecnologia.getMpPrescripcion().getAsegAfiliado().getNumeroDocumento());
                reporte.setStrNombre(tecnologia.getMpPrescripcion().getAsegAfiliado().getPrimerNombre() + " " + tecnologia.getMpPrescripcion().getAsegAfiliado().getSegundoNombre() + " " + tecnologia.getMpPrescripcion().getAsegAfiliado().getPrimerApellido() + " " + tecnologia.getMpPrescripcion().getAsegAfiliado().getSegundoApellido());
                reporte.setStrNumeroHistoria("");
                reporte.setStrDiagnosticoPrincipal(tecnologia.getMpPrescripcion().getMaDiagnosticoPrincipalValor());
                reporte.setStrRegimen(tecnologia.getMpPrescripcion().getAsegAfiliado().getRegimen());
                reporte.setStrAmbito(tecnologia.getMpPrescripcion().getCodAmbAte());
                reporte.setStrTipoPrestacion(optenerTipoPrestacion(tecnologia.getTipoPrestacion()));
                reporte.setStrServicioComplementario(obtenerTipoTecnologia(tecnologia.getTipoTecnologia()));
                reporte.setStrIndicaciones(tecnologia.getIndicacionesPaciente());
                reporte.setStrCantidad(tecnologia.getCantidadFormulada().toString());
                reporte.setStrFrecuencia(tecnologia.getFrecuenciaDeUso().toString() + "-" + optenerFrecuenciaUso(tecnologia.getCodigoUnidadTiempoFrecuenciaUso()));
//                reporte.setStrDuracion(strDuracion);
//                reporte.setStrCantidadTotal(strCantidadTotal);
//                reporte.setStrNumeroDocumentoProfesional(strNumeroDocumentoProfesional);
//                reporte.setStrNombreProfesional(strNombreProfesional);
//                reporte.setStrRegistroProfesional(strRegistroProfesional);
//                reporte.setStrEspecialidad(strEspecialidad);

                manejo.add(reporte);
            }

            for (MpPrescripcionInsumo insumo : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionInsumoPlanManejo(id)) {
                reporte = new ReportePlanManejo();

                manejo.add(reporte);
            }//

        } catch (Exception ex) {
            Logger.getLogger(MipresImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return manejo;

    }

    public String optenerTipoPrestacion(int tipo) {
        String resultado;
        switch (tipo) {
            case 1:
                resultado = "Única";
                break;
            case 2:
                resultado = "Sucesiva";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String obtenerTipoTecnologia(int id) {
        String resultado = "";
        switch (id) {
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_MEDICAMENTO1:
                resultado = "Medicamento";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS1:
                resultado = "Procedimientos";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_DISPOSITIVO1:
                resultado = "Dispositivos";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES1:
                resultado = "Productos Nutricionales";
                break;
            case MpDetalleDTO.ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO1:
                resultado = "Servicios Complementarios";
                break;
            default:
                resultado = "";
                break;
        }
        return resultado;
    }

    public String optenerFrecuenciaUso(Integer tipo) {
        String resultado;
        if (tipo != null) {

            switch (tipo) {
                case 1:
                    resultado = "Minuto(s)";
                    break;
                case 2:
                    resultado = "Hora(s)";
                    break;
                case 3:
                    resultado = "Día(s)";
                    break;
                case 4:
                    resultado = "Semana(s)";
                    break;
                case 5:
                    resultado = "Mes(es)";
                    break;
                case 6:
                    resultado = "Año(s)";
                    break;
                case 7:
                    resultado = "Segun respuesta al tratamiento";
                    break;
                case 8:
                    resultado = "Única";
                    break;
                default:
                    resultado = "";
                    break;
            }
        } else {
            resultado = "";
        }
        return resultado;
    }

    @Override
    public void sincronizarDireccionamiento(String prescripcion, int id, boolean isSub) {
        try {
            sincronizarDireccionamientoWs(prescripcion, id, isSub);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sincronizarDireccionamiento(MipresBean bean) {
        try {
            boolean isSub = false;
            if (bean.getObjeto().getRegimen() == "Subsidiado") {
                isSub = true;
            } else {
                isSub = false;
            }
            StringBuilder errorConsumo = new StringBuilder();
            Date fechaInicioServicio = new Date();
            List<MpDireccionamiento> direccionado = null;
            Date fVencimientoToken = null;
            Token token = null;
            MpConsumo consumo = null;
            String urlServicio = "";
            String servicio = "D";
            try {            //obtener token y tiempo de vencimiento
                consumoExterno = new MiPresConsumoExterno();
                token = consumoExterno.obtenerTokenInterSavia();
                fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
            } catch (Exception ex) {
                ex.printStackTrace();
                errorConsumo.append("Error obteniendo token: ").append(ex.getMessage());
            }
            List<DireccionamientoBaseDTO> respuesta = null;
            errorConsumo = new StringBuilder();
            int exitosos = 0;
            try {
                consumo = inicializarConsumo(servicio);
                // Validar si el servicio a consumir es subsidiado
                if (isSub) {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_DIRECCIONAMIENTOS_SUB);
                    servicio = MpConsumo.SUB_CONSULTA_NRO_DIRECCIONAMIENTO;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("Direccionamiento Sub");
                } else {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_DIRECCIONAMIENTOS_CON);
                    servicio = MpConsumo.CON_CONSULTA_NRO_DIRECCIONAMIENTO;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("Direccionamiento Con");
                }
                // Registra inicio de consumo

                consumo.setPeriodo(formatoF.format(fechaInicioServicio));
                consumo.setServicioDireccion("Consulta");
                consumo.setId(getPrescripcionWsRemoto()
                        .insertarMpConsumo(consumo));

                // Validar vigencia token 
                if (fVencimientoToken.before(new Date())) {
                    //se espera 3 segundos mientras termina de expirar el token
                    Thread.sleep(3000);
                    token = consumoExterno.obtenerTokenInterSavia();
                    fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
                }
                //Quitar ultimo campo servicioPrescripciones
                int intentos = 0;
                while (respuesta == null) {
                    // si ha fallado 3 veces cierra el consumo como fallido y termina los consumos
                    if (intentos < 3) {
                        respuesta = consumoExterno.
                                servicioGetDireccionamientoPorNumeroPrescripcion(
                                        urlServicio,
                                        token,
                                        bean.getObjeto().getNumeroPrescripcion());
                        intentos++;
                    } else {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion("No fue posible consumir servicio # " + intentos);
                        getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                    }
                }
            } catch (Exception ex) {
                errorConsumo.append("error consultando servicio ").append(servicio).append(": ").append(ex.getMessage());
                ex.printStackTrace();
                fVencimientoToken = new Date();
            }

            if (respuesta != null && !respuesta.isEmpty()) {

                if (!errorConsumo.toString().isEmpty()) {
                    try {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion(errorConsumo.toString());
                        getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                    } catch (Exception ex) {
                    }
                }

                // Actualiza el registro de consumo 
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                consumo.setRegistros(respuesta.size());
                consumo.setObservacion("inicio insercion BD");
                try {
                    getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                } catch (Exception ex) {
                }
                try {
                    direccionado = getMpPrescripcionTecnologiaWSRemoto()
                            .consultarDireccionamientosPorIdPrescripcion(bean.getObjeto().getIdPrescripcion());
                } catch (Exception ex) {
                }
                // Recorrer la lista de respuesta
                for (DireccionamientoBaseDTO respuestaDir : respuesta) {
                    // Comparar cada elemento de direccionamiento
                    try {

                        for (MpDireccionamiento dir : direccionado) {
                            if (Objects.equals(respuestaDir.getIDDireccionamiento(), dir.getIdDireccionamiento())) {
                                // Verificar estados, si son iguales no hago nada
                                if (Integer.parseInt(respuestaDir.getEstDireccionamiento()) != (dir.getEstado())) {
                                    getMpPrescripcionTecnologiaWSRemoto()
                                            .cambiarEstadoDireccionamiento(dir.getId(), Integer.parseInt(respuestaDir.getEstDireccionamiento()));

                                    exitosos = exitosos + 1;
                                    if (exitosos % 100 == 0) {
                                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                                        consumo.setObservacion("inicio insercion BD");
                                        consumo.setRegistrosExitosos(exitosos);
                                        try {
                                            getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                                        } catch (Exception ex) {
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        MpConsumoFallo mpFallo = new MpConsumoFallo();
                        mpFallo.setEstado(MpConsumoFallo.ESTADO_CONSUMOFALLO_FALLIDO);
                        mpFallo.setMpConsumoId(consumo);
                        mpFallo.setDescripcion(ex.getMessage());
                        mpFallo.setFechaProceso(fechaInicioServicio);
                        mpFallo.setFechaHoraFallo(new Date());
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(respuestaDir);
                        mpFallo.setJson(jsonString);
                        try {
                            getConsumoFallosWsRemoto().insertarConsumoFallo(mpFallo);
                        } catch (Exception exe) {
                        }
                    }
                }
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                consumo.setObservacion("fin insercion BD");
                consumo.setRegistrosExitosos(exitosos);
                bean.addMensaje("Se sincronizaron " + exitosos + " de " + respuesta.size() + " registros correctamente.");
            } else {
                if (!errorConsumo.toString().isEmpty()) {
                    try {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion(errorConsumo.toString());
                        bean.addError("La sincronización se detuvo. Inténtelo de nuevo.");
                    } catch (Exception ex) {
                    }
                } else {
                    consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                    consumo.setObservacion("sin registros");
                    bean.addMensaje("No se encontraron registros para sincronizar.");
                }
            }
            try {
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sincronizarConsumos(MipresBean bean) {
        try {
            sincronizarAnulacion(bean);
        } catch (Exception ex) {
            bean.addError("se presento una inconsistencia en el servicio de prescripcionesAnular intentelo nuevamente");
        }
        try {
            sincronizarJuntas(bean);
        } catch (Exception ex) {
            bean.addError("se presento una inconsistencia en el servicio de juntas intentelo nuevamente");
        }
        try {
            sincronizarDireccionamientos(bean);
        } catch (Exception ex) {
            bean.addError("se presento una inconsistencia en el servicio de direccionamientos intentelo nuevamente");
        }
        try {
            sincronizarNoDireccionamientos(bean);
        } catch (Exception ex) {
            bean.addError("se presento una inconsistencia en el servicio de no direccionamientos intentelo nuevamente");
        }
        try {
            sincronizarEntregas(bean);
        } catch (Exception ex) {
            bean.addError("se presento una inconsistencia en el servicio de entregas intentelo nuevamente");
        }
        try {
            sincronizarfacturas(bean);
        } catch (Exception ex) {
            bean.addError("se presento una inconsistencia en el servicio de facturas intentelo nuevamente");
        }

    }

    public void sincronizarAnulacion(MipresBean bean) {
        try {
            boolean isSub = false;
            if ("1".equals(bean.getRegimenRe())) {
                isSub = true;
            } else {
                isSub = false;
            }
            StringBuilder errorConsumo = new StringBuilder();
            Date fechaInicioServicio = new Date();
            RespuestaAnulaacionPrescripcionDTO prescripcionesAnular = null;
            Date fVencimientoToken = null;
            Token token = null;
            MpConsumo consumo = null;
            String urlServicio = "";
            String servicio = "D";
            try {            //obtener token y tiempo de vencimiento
                consumoExterno = new MiPresConsumoExterno();
                token = consumoExterno.obtenerTokenInterSavia();
                fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
            } catch (Exception ex) {
                ex.printStackTrace();
                errorConsumo.append("Error obteniendo token AnulacionPrescripcion: ").append(ex.getMessage());
            }
            errorConsumo = new StringBuilder();

            try {
                consumo = inicializarConsumo(servicio);
                // Validar si el servicio a consumir es subsidiado
                if (isSub) {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_ANULACIONXPRESCRIPCION_SUB);
                    servicio = MpConsumo.SUB_CONSULTA_NRO_ANULACION;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("AnulacionPrescripcion Sub");
                } else {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_ANULACIONXPRESCRIPCION_CON);
                    servicio = MpConsumo.CON_CONSULTA_NRO_ANULACION;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("AnulacionPrescripcion Con");
                }
                // Registra inicio de consumo

                consumo.setPeriodo(formatoF.format(fechaInicioServicio));
                consumo.setServicioDireccion("Consulta");
                consumo.setId(getPrescripcionWsRemoto()
                        .insertarMpConsumo(consumo));

                // Validar vigencia token 
                if (fVencimientoToken.before(new Date())) {
                    //se espera 3 segundos mientras termina de expirar el token
                    Thread.sleep(3000);
                    token = consumoExterno.obtenerTokenInterSavia();
                    fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
                }
                //Quitar ultimo campo servicioPrescripciones
                int intentos = 0;
                boolean errorCon = true;

                while (errorCon && intentos < 3) {
                    try {
                        prescripcionesAnular = consumoExterno.consultaPrescripcionesAnulacion(
                                urlServicio, token, bean.getNumeroPrescripcionRe());
                        errorCon = false;
                    } catch (Exception ex) {
                        intentos++;
                        if (intentos >= 3) {
                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                            consumo.setObservacion("No fue posible consumir servicio prescripcionesAnular Re-sincronizacion. Intentos: " + intentos);
                            getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                        }
                    }
                }

            } catch (Exception ex) {
                errorConsumo.append("error consultando servicio prescripcionesAnular").append(servicio).append(": ").append(ex.getMessage());
                ex.printStackTrace();
                fVencimientoToken = new Date();
            }

            if (prescripcionesAnular.getNoPrescripcion() != null) {
                int exitosos = 0;
                int procesadas = 0;
                int editadas = 0;
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                consumo.setRegistros(1);
                consumo.setObservacion("Insercion BD - " + procesadas + " procesados");
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                try {
                    if (prescripcionesAnular.getNoPrescripcion() != null) {
                        if (prescripcionesAnular.getNoPrescripcion() != null && !prescripcionesAnular.getNoPrescripcion().isEmpty()) {
                            MpAnuladaPrescripcion solicitud = getPrescripcionWsRemoto().validarExisteSolicitud(bean.getIdPrescripcionRe(), bean.getNumeroPrescripcionRe());
                            MpPrescripcion prescripcion = getPrescripcionWsRemoto().consultarMpPrescripcion(bean.getNumeroPrescripcionRe());

                            if (solicitud != null) {
                                MpAnuladaPrescripcion anulacion = new MpAnuladaPrescripcion();
                                anulacion.setMpPrescripcionId(new MpPrescripcion(prescripcion.getId()));
                                anulacion.setNumeroPrescripcion(prescripcion.getNumeroPrescripcion());
                                anulacion.setTipo(prescripcionesAnular.getTipoAnulacion());
                                anulacion.setJustificacion(prescripcionesAnular.getJustificacion());
                                anulacion.setObservacion(prescripcionesAnular.getObservacion());
                                anulacion.setFechaHoraSolicitud(prescripcionesAnular.getFSolicitud());
                                anulacion.setUsuarioSolicita(prescripcionesAnular.getUsuario_Solicita());
                                anulacion.setEstado(prescripcionesAnular.getEstAnulacion());
                                anulacion.setFechaHoraAnulacion(prescripcionesAnular.getFAnulacion());
                                anulacion.setUsuarioAnula(prescripcionesAnular.getUsuario_Anula());
                                // Insertar MpPrescripcionAnulada
                                getPrescripcionWsRemoto().insertarMpPrescripcionAnulada(anulacion);
                                if (prescripcion.getEstado() != null) {
                                    switch (prescripcion.getEstado()) {
                                        case 4:// Si el estado es 4 (activo)                                                    
                                            if (prescripcionesAnular.getEstAnulacion() == 1) {
                                                getPrescripcionWsRemoto().actualizaPrescripcionAnulada(
                                                        prescripcionesAnular.getJustificacion(),
                                                        prescripcion.getId(),
                                                        2);
                                                editadas++;
                                            } else if (prescripcionesAnular.getEstAnulacion() == 0) {
                                                getPrescripcionWsRemoto().actualizaPrescripcionAnulada(
                                                        prescripcionesAnular.getJustificacion(),
                                                        prescripcion.getId(),
                                                        5);
                                                editadas++;
                                            }
                                            break;
                                        case 2:// Si ya está anulado (2)
                                            if (prescripcionesAnular.getEstAnulacion() == 1) {
                                                // No hacer nada
                                            }
                                            break;
                                        case 5:// Si está en solicitud de anulación (5)
                                            if (prescripcionesAnular.getEstAnulacion() == 0) {
                                                // No hacer nada
                                            } else if (prescripcionesAnular.getEstAnulacion() == 1) {
                                                getPrescripcionWsRemoto().actualizaPrescripcionAnulada(
                                                        prescripcionesAnular.getJustificacion(),
                                                        prescripcion.getId(),
                                                        2);
                                                editadas++;
                                            }
                                            break;
                                        case 1:// Si está en estado modificado (1)
                                            if (prescripcionesAnular.getEstAnulacion() == 0) {
                                                getPrescripcionWsRemoto().actualizaPrescripcionAnulada(
                                                        prescripcionesAnular.getJustificacion(),
                                                        prescripcion.getId(),
                                                        5);
                                                editadas++;
                                            } else if (prescripcionesAnular.getEstAnulacion() == 1) {
                                                getPrescripcionWsRemoto().actualizaPrescripcionAnulada(
                                                        prescripcionesAnular.getJustificacion(),
                                                        prescripcion.getId(),
                                                        2);
                                                editadas++;
                                            }
                                            break;
                                    }
                                }
                            } else {
                                if (solicitud.getEstado() == 0 && prescripcionesAnular.getEstAnulacion() == 0) {
                                    // no hace nada
                                } else if (solicitud.getEstado() == 0 && prescripcionesAnular.getEstAnulacion() == 1) {
                                    getPrescripcionWsRemoto().actualizaPrescripcionSolicitud(solicitud.getId(), 1);
                                    getPrescripcionWsRemoto().actualizaPrescripcionAnulada(prescripcionesAnular.getJustificacion(), prescripcion.getId(), 2);
                                    editadas++;
                                } else if (solicitud.getEstado() == 1 && prescripcionesAnular.getEstAnulacion() == 1) {
                                    // no hace nada 
                                }
                            }
                        }
                        procesadas++;
                    }
                    exitosos++;
                    if (exitosos % 100 == 0) {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                        consumo.setObservacion("Insercion BD : " + procesadas + " procesado  -" + editadas + "- Update");
                        consumo.setRegistrosExitosos(exitosos);
                        getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                    }
                } catch (Exception ex) {
                    try {
                        MpConsumoFallo mpFallo = new MpConsumoFallo();
                        mpFallo.setEstado(MpConsumoFallo.ESTADO_CONSUMOFALLO_FALLIDO);
                        mpFallo.setMpConsumoId(consumo);
                        mpFallo.setDescripcion(ex.getMessage());
                        mpFallo.setFechaProceso(fechaInicioServicio);
                        mpFallo.setFechaHoraFallo(new Date());
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(null);
                        mpFallo.setJson(jsonString);
                        getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                    } catch (Exception exe) {
                    }
                }

                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                consumo.setObservacion("Fin insercion BD - " + procesadas + " procesados -" + editadas + "- Updates");
                consumo.setRegistrosExitosos(exitosos);
                consumo.setFechaHoraFin(new Date());
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
            } else {
                if (!errorConsumo.toString().isEmpty()) {
                    try {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion(errorConsumo.toString());
                        bean.addError("La sincronización prescripcionesAnular se detuvo. Inténtelo de nuevo.");
                    } catch (Exception ex) {
                    }
                } else {
                    consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                    consumo.setObservacion("sin registros");
                    bean.addMensaje("No se encontraron registros de prescripcionesAnular para sincronizar.");
                }
            }
            try {
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sincronizarJuntas(MipresBean bean) {
        try {
            boolean isSub = false;
            if ("1".equals(bean.getRegimenRe())) {
                isSub = true;
            } else {
                isSub = false;
            }
            StringBuilder errorConsumo = new StringBuilder();
            Date fechaInicioServicio = new Date();
            List<JuntaMedicaBaseDTO> listaJuntasMedicasBase = new ArrayList<>();
            Date fVencimientoToken = null;
            Token token = null;
            MpConsumo consumo = null;
            String urlServicio = "";
            String servicio = "D";
            try {            //obtener token y tiempo de vencimiento
                consumoExterno = new MiPresConsumoExterno();
                token = consumoExterno.obtenerTokenInterSavia();
                fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
            } catch (Exception ex) {
                ex.printStackTrace();
                errorConsumo.append("Error obteniendo token juntas: ").append(ex.getMessage());
            }
            errorConsumo = new StringBuilder();

            try {
                consumo = inicializarConsumo(servicio);
                // Validar si el servicio a consumir es subsidiado
                if (isSub) {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_JUNTAS_SUB);
                    servicio = MpConsumo.SUB_CONSULTA_NRO_JUNTA;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("JuntasRe Sub");
                } else {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_JUNTAS_CON);
                    servicio = MpConsumo.CON_CONSULTA_NRO_JUNTA;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("JuntasRe Con");
                }
                // Registra inicio de consumo

                consumo.setPeriodo(formatoF.format(fechaInicioServicio));
                consumo.setServicioDireccion("Consulta");
                consumo.setId(getPrescripcionWsRemoto()
                        .insertarMpConsumo(consumo));

                // Validar vigencia token 
                if (fVencimientoToken.before(new Date())) {
                    //se espera 3 segundos mientras termina de expirar el token
                    Thread.sleep(3000);
                    token = consumoExterno.obtenerTokenInterSavia();
                    fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
                }
                //Quitar ultimo campo servicioPrescripciones
                int intentos = 0;
                boolean errorCon = true;

                while (errorCon && intentos < 3) {
                    try {
                        listaJuntasMedicasBase = consumoExterno.servicioGetJuntaPorNumeroPrescripcion(
                                urlServicio, token, bean.getNumeroPrescripcionRe());

                        // Si llegó aquí, el consumo fue exitoso (aunque no haya juntas)
                        errorCon = false;

                    } catch (Exception ex) {
                        intentos++;
                        if (intentos >= 3) {
                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                            consumo.setObservacion("No fue posible consumir servicio Juntas Re-sincronizacion. Intentos: " + intentos);
                            getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                        }
                    }
                }

            } catch (Exception ex) {
                errorConsumo.append("error consultando servicio juntas").append(servicio).append(": ").append(ex.getMessage());
                ex.printStackTrace();
                fVencimientoToken = new Date();
            }

            if (listaJuntasMedicasBase != null && !listaJuntasMedicasBase.isEmpty()) {
                //Carga información prescripción
                List<MpPrescripcionMedicamento> listaMedicamentos = new ArrayList();
                List<MpPrescripcionTecnologia> listaTecnologias = new ArrayList();
                List<MpPrescripcionInsumo> listaInsumos = new ArrayList();
                for (JuntaMedicaBaseDTO objBaseDto : listaJuntasMedicasBase) {
                    MpPrescripcion pr = new MpPrescripcion();
                    switch (objBaseDto.getJunta_profesional().getTipoTecnologia().toUpperCase()) {
                        case "M":
                            //1 medicamentos 
                            MpPrescripcionMedicamento med = new MpPrescripcionMedicamento();
                            pr.setNumeroPrescripcion(objBaseDto.getJunta_profesional().getNoPrescripcion());
                            med.setMpPrescripcion(pr);
                            med.setConsecutivoOrden(Integer.parseInt(objBaseDto.getJunta_profesional().getConsecutivo()));
                            med.setTipoTecnologia(1);
                            listaMedicamentos.add(med);
                            break;
                        case "P":
                            //2 procedimientos 
                            MpPrescripcionTecnologia tec = new MpPrescripcionTecnologia();
                            pr.setNumeroPrescripcion(objBaseDto.getJunta_profesional().getNoPrescripcion());
                            tec.setMpPrescripcion(pr);
                            tec.setConsecutivoOrden(Integer.parseInt(objBaseDto.getJunta_profesional().getConsecutivo()));
                            tec.setTipoTecnologia(2);
                            listaTecnologias.add(tec);
                            break;
                        case "D":
                            //3 dispositivos 
                            MpPrescripcionInsumo disp = new MpPrescripcionInsumo();
                            pr.setNumeroPrescripcion(objBaseDto.getJunta_profesional().getNoPrescripcion());
                            disp.setMpPrescripcion(pr);
                            disp.setConsecutivoOrden(Integer.parseInt(objBaseDto.getJunta_profesional().getConsecutivo()));
                            disp.setTipoTecnologia(3);
                            listaInsumos.add(disp);
                            break;
                        case "N":
                            //4 productosnutricionales 
                            MpPrescripcionMedicamento nutr = new MpPrescripcionMedicamento();
                            pr.setNumeroPrescripcion(objBaseDto.getJunta_profesional().getNoPrescripcion());
                            nutr.setMpPrescripcion(pr);
                            nutr.setConsecutivoOrden(Integer.parseInt(objBaseDto.getJunta_profesional().getConsecutivo()));
                            nutr.setTipoTecnologia(4);
                            listaMedicamentos.add(nutr);
                            break;
                        case "S":
                            //5 serviciosComplementarios
                            MpPrescripcionInsumo comp = new MpPrescripcionInsumo();
                            pr.setNumeroPrescripcion(objBaseDto.getJunta_profesional().getNoPrescripcion());
                            comp.setMpPrescripcion(pr);
                            comp.setConsecutivoOrden(Integer.parseInt(objBaseDto.getJunta_profesional().getConsecutivo()));
                            comp.setTipoTecnologia(5);
                            listaInsumos.add(comp);
                            break;
                    }
                }
                Map<String, MpPrescripcionMedicamento> mapMedicamentos = null;
                Map<String, MpPrescripcionTecnologia> mapTecnologias = null;
                Map<String, MpPrescripcionInsumo> mapInsumos = null;
                try {
                    mapMedicamentos = getMpPrescripcionTecnologiaWSRemoto().consultarListaMpMedicamentos(listaMedicamentos);
                    mapTecnologias = getMpPrescripcionTecnologiaWSRemoto().consultarListaMpTecnologias(listaTecnologias);
                    mapInsumos = getMpPrescripcionTecnologiaWSRemoto().consultarListaMpInsumos(listaInsumos);
                } catch (Exception ex) {
                    throw new Exception("Error llenando listas de juntas: " + ex.getMessage());
                }
                int exitosos = 0;
                // Actualizar el estado de consumo
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                consumo.setRegistros(listaJuntasMedicasBase.size());
                consumo.setObservacion("Insercion BD - " + exitosos + " exitosas");
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                for (JuntaMedicaBaseDTO objBaseDto : listaJuntasMedicasBase) {
                    try {
                        procesarJuntaMedica(
                                objBaseDto,
                                mapMedicamentos,
                                mapTecnologias,
                                mapInsumos);
                        exitosos++;
                        if (exitosos % 100 == 0) {
                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                            consumo.setObservacion("Insercion BD - " + exitosos + " exitosas");
                            consumo.setRegistrosExitosos(exitosos);
                            getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                        }
                    } catch (Exception ex) {
                        try {
                            MpConsumoFallo mpFallo = new MpConsumoFallo();
                            mpFallo.setEstado(MpConsumoFallo.ESTADO_CONSUMOFALLO_FALLIDO);
                            mpFallo.setMpConsumoId(consumo);
                            mpFallo.setDescripcion(ex.getMessage());
                            mpFallo.setFechaProceso(fechaInicioServicio);
                            mpFallo.setFechaHoraFallo(new Date());
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(objBaseDto);
                            mpFallo.setJson(jsonString);
                            getConsumoFallosWsRemoto().insertarConsumoFallo(mpFallo);
                        } catch (Exception exe) {
                        }
                    }
                }
                if (exitosos == listaJuntasMedicasBase.size()) {
                    bean.addMensaje("Proceso de juntas exitoso :" + exitosos);
                }
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                consumo.setObservacion("Fin insercion BD - " + exitosos + " exitosas");
                consumo.setRegistrosExitosos(exitosos);
                consumo.setFechaHoraFin(new Date());
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
            } else {
                if (!errorConsumo.toString().isEmpty()) {
                    try {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion(errorConsumo.toString());
                        bean.addError("La sincronización juntas se detuvo. Inténtelo de nuevo.");
                    } catch (Exception ex) {
                    }
                } else {
                    consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                    consumo.setObservacion("sin registros");
                    bean.addMensaje("No se encontraron registros de juntas para sincronizar.");
                }
            }
            try {
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void procesarJuntaMedica(
            JuntaMedicaBaseDTO objBaseDto,
            Map<String, MpPrescripcionMedicamento> mapMedicamentos,
            Map<String, MpPrescripcionTecnologia> mapTecnologias,
            Map<String, MpPrescripcionInsumo> mapInsumos
    ) throws Exception {
//        String error = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
//            String fecha;
//            Date fechaActa;
            switch (objBaseDto.getJunta_profesional().getTipoTecnologia().toUpperCase()) {
                case "M":
                    MpPrescripcionMedicamento medicamento = mapMedicamentos.getOrDefault(
                            objBaseDto.getJunta_profesional().getNoPrescripcion().trim() + "||"
                            + objBaseDto.getJunta_profesional().getConsecutivo().trim() + "||"
                            + 1, null);
                    if (medicamento != null) {
                        medicamento.setEstadoJuntaProfesionales(Integer.parseInt(objBaseDto.getJunta_profesional().getEstJM()));
                        medicamento.setJustificacionTecJunta(objBaseDto.getJunta_profesional().getJustificacionTecnica());
                        medicamento.setModJunta(objBaseDto.getJunta_profesional().getModalidad());
                        medicamento.setNumActaJunta(objBaseDto.getJunta_profesional().getNoActa());
                        if (objBaseDto.getJunta_profesional().getFechaActa() != null) {
//                            fecha = objBaseDto.getJunta_profesional().getFechaActa().substring(0, 10);
//                            fechaActa = formato.parse(fecha);
                            medicamento.setFechaActaJunta(formato.parse(objBaseDto.getJunta_profesional().getFechaActa().substring(0, 10)));
                        }
                        try {
                            getMpPrescripcionTecnologiaWSRemoto().actualizarJuntaMpPrescripcionMedicamento(medicamento);
                        } catch (Exception ex) {
                            throw new Exception("Error Actualizando medicamentos - " + ex.getMessage());
                        }
                    } else {
                        throw new Exception("No se encontro Servicio Medicamento: "
                                + objBaseDto.getJunta_profesional().getNoPrescripcion().trim()
                                + " || "
                                + objBaseDto.getJunta_profesional().getConsecutivo()
                        );
                    }
                    break;
                case "P":
                    MpPrescripcionTecnologia tecnologia = mapTecnologias
                            .getOrDefault(objBaseDto.getJunta_profesional().getNoPrescripcion().trim() + "||"
                                    + objBaseDto.getJunta_profesional().getConsecutivo().trim() + "||"
                                    + 2, null);
                    if (tecnologia != null) {
                        tecnologia.setEstadoJuntaProfesionales(Integer.parseInt(objBaseDto.getJunta_profesional().getEstJM()));
                        tecnologia.setJustificacionTecJunta(objBaseDto.getJunta_profesional().getJustificacionTecnica());
                        tecnologia.setModJunta(objBaseDto.getJunta_profesional().getModalidad());
                        tecnologia.setNumActaJunta(objBaseDto.getJunta_profesional().getNoActa());
                        if (objBaseDto.getJunta_profesional().getFechaActa() != null) {
//                            fecha = objBaseDto.getJunta_profesional().getFechaActa().substring(0, 10);
//                            fechaActa = formato.parse(fecha);
                            tecnologia.setFechaActaJunta(formato.parse(objBaseDto.getJunta_profesional().getFechaActa().substring(0, 10)));
                        }
                        try {
                            getMpPrescripcionTecnologiaWSRemoto().actualizarJuntaMpPrescripcionTecnologia(tecnologia);
                        } catch (Exception ex) {
                            throw new Exception("Error Actualizando procedimiento - " + ex.getMessage());
                        }
                    } else {
                        throw new Exception(
                                "No se encontro Procedimiento: "
                                + objBaseDto.getJunta_profesional().getNoPrescripcion().trim()
                                + " || "
                                + objBaseDto.getJunta_profesional().getConsecutivo()
                        );
                    }
                    break;
                case "D":
                    MpPrescripcionInsumo insumo = mapInsumos
                            .getOrDefault(objBaseDto.getJunta_profesional().getNoPrescripcion().trim() + "||"
                                    + objBaseDto.getJunta_profesional().getConsecutivo().trim() + "||"
                                    + 3, null);
                    if (insumo != null) {
                        insumo.setEstadoJuntaProfesionales(Integer.parseInt(objBaseDto.getJunta_profesional().getEstJM()));
                        insumo.setJustificacionTecJunta(objBaseDto.getJunta_profesional().getJustificacionTecnica());
                        insumo.setModJunta(objBaseDto.getJunta_profesional().getModalidad());
                        insumo.setNumActaJunta(objBaseDto.getJunta_profesional().getNoActa());
                        if (objBaseDto.getJunta_profesional().getFechaActa() != null) {
//                            fecha = objBaseDto.getJunta_profesional().getFechaActa().substring(0, 10);
//                            fechaActa = formato.parse(fecha);
                            insumo.setFechaActaJunta(formato.parse(objBaseDto.getJunta_profesional().getFechaActa().substring(0, 10)));
                        }
                        try {
                            getMpPrescripcionTecnologiaWSRemoto().actualizarJuntaMpPrescripcionInsumo(insumo);
                        } catch (Exception ex) {
                            throw new Exception("Error Actualizando dispositivo - " + ex.getMessage());
                        }
                    } else {
                        throw new Exception("No se encontro Servicio Dispositivo: "
                                + objBaseDto.getJunta_profesional().getNoPrescripcion().trim()
                                + " || "
                                + objBaseDto.getJunta_profesional().getConsecutivo());
                    }
                    break;
                case "N":
                    MpPrescripcionMedicamento nut = mapMedicamentos
                            .getOrDefault(objBaseDto.getJunta_profesional().getNoPrescripcion().trim() + "||"
                                    + objBaseDto.getJunta_profesional().getConsecutivo().trim() + "||"
                                    + 4, null);
                    if (nut != null) {
                        nut.setEstadoJuntaProfesionales(Integer.parseInt(objBaseDto.getJunta_profesional().getEstJM()));
                        nut.setJustificacionTecJunta(objBaseDto.getJunta_profesional().getJustificacionTecnica());
                        nut.setModJunta(objBaseDto.getJunta_profesional().getModalidad());
                        nut.setNumActaJunta(objBaseDto.getJunta_profesional().getNoActa());
                        if (objBaseDto.getJunta_profesional().getFechaActa() != null) {
//                            fecha = objBaseDto.getJunta_profesional().getFechaActa().substring(0, 10);
//                            fechaActa = formato.parse(fecha);
                            nut.setFechaActaJunta(formato.parse(objBaseDto.getJunta_profesional().getFechaActa().substring(0, 10)));
                        }
                        try {
                            getMpPrescripcionTecnologiaWSRemoto().actualizarJuntaMpPrescripcionMedicamento(nut);
                        } catch (Exception ex) {
                            throw new Exception("Error Actualizando producto nutricional - " + ex.getMessage());
                        }
                    } else {
                        throw new Exception(
                                "No se encontro Producto Nutricional Complementarios: "
                                + objBaseDto.getJunta_profesional().getNoPrescripcion().trim()
                                + " || "
                                + objBaseDto.getJunta_profesional().getConsecutivo()
                        );
                    }
                    break;
                case "S":
                    MpPrescripcionInsumo sup = mapInsumos
                            .getOrDefault(objBaseDto.getJunta_profesional().getNoPrescripcion().trim() + "||"
                                    + objBaseDto.getJunta_profesional().getConsecutivo().trim() + "||"
                                    + 5, null);
                    if (sup != null) {
                        sup.setEstadoJuntaProfesionales(Integer.parseInt(objBaseDto.getJunta_profesional().getEstJM()));
                        sup.setJustificacionTecJunta(objBaseDto.getJunta_profesional().getJustificacionTecnica());
                        sup.setModJunta(objBaseDto.getJunta_profesional().getModalidad());
                        sup.setNumActaJunta(objBaseDto.getJunta_profesional().getNoActa());
                        if (objBaseDto.getJunta_profesional().getFechaActa() != null) {
//                            fecha = objBaseDto.getJunta_profesional().getFechaActa().substring(0, 10);
//                            fechaActa = formato.parse(fecha);
                            sup.setFechaActaJunta(formato.parse(objBaseDto.getJunta_profesional().getFechaActa().substring(0, 10)));
                        }
                        try {
                            getMpPrescripcionTecnologiaWSRemoto()
                                    .actualizarJuntaMpPrescripcionInsumo(sup);
                        } catch (Exception ex) {
                            throw new Exception("Error Actualizando servicio complementario - " + ex.getMessage());
                        }
                    } else {
                        throw new Exception(
                                "No se encontro Servicio Complementarios: "
                                + objBaseDto.getJunta_profesional().getNoPrescripcion().trim()
                                + " || "
                                + objBaseDto.getJunta_profesional().getConsecutivo()
                        );
                    }
                    break;
                default:
                    throw new Exception("Tipo Tecnología no soportada");
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
//        return error;
    }

    public void sincronizarEntregas(MipresBean bean) {
        try {
            boolean isSub = false;
            if ("1".equals(bean.getRegimenRe())) {
                isSub = true;
            } else {
                isSub = false;
            }
            StringBuilder errorConsumo = new StringBuilder();
            Date fechaInicioServicio = new Date();
            List<ReporteEntregaBaseDTO> listaEntregasBase = new ArrayList<>();
            Date fVencimientoToken = null;
            Token token = null;
            MpConsumo consumo = null;
            String urlServicio = "";
            String servicio = "D";
            try {            //obtener token y tiempo de vencimiento
                consumoExterno = new MiPresConsumoExterno();
                token = consumoExterno.obtenerTokenInterSavia();
                fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
            } catch (Exception ex) {
                ex.printStackTrace();
                errorConsumo.append("Error obteniendo token entregas: ").append(ex.getMessage());
            }
            errorConsumo = new StringBuilder();

            try {
                consumo = inicializarConsumo(servicio);
//                // Validar si el servicio a consumir es subsidiado
//                if (isSub) {
//                    urlServicio = PropApl.getInstance()
//                            .get(PropApl.MP_WS_URL_SINCRO_REPORTEENTREGAXPRESCRIPCION_SUB);
//                    servicio = MpConsumo.SUB_CONSULTA_NRO_ENTREGA;
//                    consumo.setServicio(servicio);
//                    consumo.setServicioDescripcion("Entregas Sub");
//                } else {
                urlServicio = PropApl.getInstance()
                        .get(PropApl.MP_WS_URL_SINCRO_REPORTEENTREGAXPRESCRIPCION_CON);
                servicio = MpConsumo.CON_CONSULTA_NRO_ENTREGA;
                consumo.setServicio(servicio);
                consumo.setServicioDescripcion("Entregas General");
//                }
                // Registra inicio de consumo

                consumo.setPeriodo(formatoF.format(fechaInicioServicio));
                consumo.setServicioDireccion("Consulta");
                consumo.setId(getPrescripcionWsRemoto()
                        .insertarMpConsumo(consumo));

                // Validar vigencia token 
                if (fVencimientoToken.before(new Date())) {
                    //se espera 3 segundos mientras termina de expirar el token
                    Thread.sleep(3000);
                    token = consumoExterno.obtenerTokenInterSavia();
                    fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
                }
                int intentos = 0;
                boolean errorCon = true;
                while (errorCon && intentos < 3) {
                    try {
                        listaEntregasBase = consumoExterno.servicioGetEntregaPorNumeroPrescripcion(
                                urlServicio, token, bean.getNumeroPrescripcionRe());
                        errorCon = false;
                    } catch (Exception ex) {
                        intentos++;
                        if (intentos >= 3) {
                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                            consumo.setObservacion("No fue posible consumir servicio Entregas Re-sincronizacion. Intentos: " + intentos);
                            getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                        }
                    }
                }

            } catch (Exception ex) {
                errorConsumo.append("error consultando servicio Entregas").append(servicio).append(": ").append(ex.getMessage());
                ex.printStackTrace();
                fVencimientoToken = new Date();
            }

            if (listaEntregasBase != null && !listaEntregasBase.isEmpty()) {
                List<String> listaIdTransaccion = new ArrayList();
                List<MpPrescripcionMedicamento> listaMeds = new ArrayList();
                List<MpPrescripcionTecnologia> listaTecs = new ArrayList();
                List<MpPrescripcionInsumo> listaIns = new ArrayList();
                MpPrescripcion pr;
                MpPrescripcionMedicamento med;
                MpPrescripcionTecnologia tec;
                MpPrescripcionInsumo ins;
                for (ReporteEntregaBaseDTO ent : listaEntregasBase) {
                    String id = ent.getID();
                    if (!listaIdTransaccion.contains(id)) {
                        listaIdTransaccion.add(id);
                    }
                    pr = new MpPrescripcion();
                    switch (ent.getTipoTec()) {
                        case "M":
                        case "m":
                            //1 medicamentos 
                            med = new MpPrescripcionMedicamento();
                            pr.setNumeroPrescripcion(ent.getNoPrescripcion());
                            med.setMpPrescripcion(pr);
                            med.setConsecutivoOrden(Integer.parseInt(ent.getConTec()));
                            med.setTipoTecnologia(MpPrescripcion.TIPO_MEDICAMENTO);
                            listaMeds.add(med);
                            break;
                        case "P":
                        case "p":
                            //2 procedimientos 
                            tec = new MpPrescripcionTecnologia();
                            pr.setNumeroPrescripcion(ent.getNoPrescripcion());
                            tec.setMpPrescripcion(pr);
                            tec.setConsecutivoOrden(Integer.parseInt(ent.getConTec()));
                            tec.setTipoTecnologia(MpPrescripcion.TIPO_TECNOLOGIA);
                            listaTecs.add(tec);
                            break;
                        case "D":
                        case "d":
                            //3 dispositivos 
                            ins = new MpPrescripcionInsumo();
                            pr.setNumeroPrescripcion(ent.getNoPrescripcion());
                            ins.setMpPrescripcion(pr);
                            ins.setConsecutivoOrden(Integer.parseInt(ent.getConTec()));
                            ins.setTipoTecnologia(MpPrescripcion.TIPO_DISPOSITIVO_MEDICO);
                            listaIns.add(ins);
                            break;
                        case "N":
                        case "n":
                            //4 productosnutricionales 
                            med = new MpPrescripcionMedicamento();
                            pr.setNumeroPrescripcion(ent.getNoPrescripcion());
                            med.setMpPrescripcion(pr);
                            med.setConsecutivoOrden(Integer.parseInt(ent.getConTec()));
                            med.setTipoTecnologia(MpPrescripcion.TIPO_PRODUCTO_NUTRICIONAL);
                            listaMeds.add(med);
                            break;
                        case "S":
                        case "s":
                            //5 serviciosComplementarios
                            ins = new MpPrescripcionInsumo();
                            pr.setNumeroPrescripcion(ent.getNoPrescripcion());
                            ins.setMpPrescripcion(pr);
                            ins.setConsecutivoOrden(Integer.parseInt(ent.getConTec()));
                            ins.setTipoTecnologia(MpPrescripcion.TIPO_SERVICIO_COMPLEMENTARIO);
                            listaIns.add(ins);
                            break;
                    }
                }

                Map<String, MpDireccionamiento> listaDireccionamientos = null;
                Map<String, MpPrescripcionMedicamento> listaMedicamentos = null;
                Map<String, MpPrescripcionTecnologia> listaTecnologias = null;
                Map<String, MpPrescripcionInsumo> listaInsumos = null;

                listaDireccionamientos = getMpDireccionamientoWSRemoto()
                        .consultarListaMpDireccionamientosTransaccion(listaIdTransaccion);

                listaMedicamentos = getMpPrescripcionTecnologiaWSRemoto()
                        .consultarListaMpMedicamentos(listaMeds);

                listaTecnologias = getMpPrescripcionTecnologiaWSRemoto()
                        .consultarListaMpTecnologias(listaTecs);

                listaInsumos = getMpPrescripcionTecnologiaWSRemoto()
                        .consultarListaMpInsumos(listaIns);

                // Actualizar el estado de consumo
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                consumo.setRegistros(listaEntregasBase.size());
                consumo.setObservacion("Inicio insercion BD");
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                int exitosos = 0;
                int procesados = 0;
                int editados = 0;
                for (ReporteEntregaBaseDTO ent : listaEntregasBase) {
                    try {
                        MpDireccionamientoEntregado entregadoExistente = getMpDireccionamientoWSRemoto().consultarExistente(ent.getID(), ent.getIDReporteEntrega(), Integer.parseInt(ent.getNoEntrega()));

                        if (entregadoExistente != null) {

                            if (!entregadoExistente.getEstRepEntrega().equals(Integer.parseInt(ent.getEstRepEntrega()))) {
                                SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                Date fechaAnula = null;
                                if (ent.getFecAnulacion() != null) {
                                    fechaAnula = formatoSalida.parse(ent.getFecAnulacion());
                                } else {
                                    fechaAnula = null;
                                }
                                Integer valor = Integer.parseInt(ent.getEstRepEntrega());
                                getMpDireccionamientoWSRemoto().actualizarExistente(entregadoExistente.getId(), valor, fechaAnula);
                                editados++;
                            }
                            exitosos++;
                        } else {
                            procesarEntrega(
                                    ent,
                                    listaDireccionamientos,
                                    listaMedicamentos,
                                    listaTecnologias,
                                    listaInsumos,
                                    fechaInicioServicio);
                            procesados++;
                            exitosos++;
                        }

                        if (exitosos % 100 == 0) {
                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                            consumo.setObservacion("Insercion BD - " + exitosos + " - Exitosos - " + procesados + " - Nuevo(s) registros - " + editados + " - Updates");
                            consumo.setRegistrosExitosos(exitosos);
                            getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                        }
                    } catch (Exception ex) {
                        try {
                            MpConsumoFallo mpFallo = new MpConsumoFallo();
                            mpFallo.setEstado(MpConsumoFallo.ESTADO_CONSUMOFALLO_FALLIDO);
                            mpFallo.setMpConsumoId(consumo);
                            mpFallo.setDescripcion(ex.getMessage());
                            mpFallo.setFechaProceso(new Date());
                            mpFallo.setFechaHoraFallo(new Date());
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(ent);
                            mpFallo.setJson(jsonString);
                            getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                        } catch (Exception exe) {
                        }
                    }
                }
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                consumo.setObservacion("Fin insercion BD - " + exitosos + " - Exitosos - " + procesados + " - Nuevo(s) registros - " + editados + " - Updates");
                consumo.setRegistrosExitosos(exitosos);
                consumo.setFechaHoraFin(new Date());
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                bean.addMensaje("Proceso de entregas exitoso :" + exitosos);
            } else {
                if (!errorConsumo.toString().isEmpty()) {
                    try {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion(errorConsumo.toString());
                        bean.addError("La sincronización entregas se detuvo. Inténtelo de nuevo.");
                    } catch (Exception ex) {
                    }
                } else {
                    consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                    consumo.setObservacion("sin registros");
                    bean.addMensaje("No se encontraron registros de entregas para sincronizar.");
                }
            }
            try {
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void procesarEntrega(ReporteEntregaBaseDTO ent,
            Map<String, MpDireccionamiento> listaDireccionamientos,
            Map<String, MpPrescripcionMedicamento> listaMedicamentos,
            Map<String, MpPrescripcionTecnologia> listaTecnologias,
            Map<String, MpPrescripcionInsumo> listaInsumos,
            Date fechaConsumo) throws Exception {
        SimpleDateFormat formatoF = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoFHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        StringBuilder error = new StringBuilder();
        Date fecha;
        MpDireccionamientoEntregado entrega = new MpDireccionamientoEntregado();
        entrega.setIdTransaccion(Integer.valueOf(ent.getID()));
        MpDireccionamiento direccionamiento = listaDireccionamientos
                .getOrDefault(ent.getID(), null);
        if (direccionamiento != null) {

            entrega.setMpDireccionamientoId(new MpDireccionamiento(direccionamiento.getId()));
            if (direccionamiento.getEntregaTotal() != null) {

                String cantTotEntregadaStr = ent.getCantTotEntregada();
                if (cantTotEntregadaStr != null && !cantTotEntregadaStr.isEmpty()) {
                    cantTotEntregadaStr = cantTotEntregadaStr.replaceAll("[^0-9]", "");
                } else {
                    cantTotEntregadaStr = "0";
                }
                int cantTotEntregada = cantTotEntregadaStr.isEmpty() ? 0 : Integer.parseInt(cantTotEntregadaStr);
                if (direccionamiento.getEntregaTotal().equals(cantTotEntregada)) {
                    entrega.setEntregaCompleta(true);
                } else {
                    entrega.setEntregaCompleta(false);
                }
            } else {
                entrega.setEntregaCompleta(false);
            }
        }
        switch (ent.getTipoTec()) {
            case "M":
            case "m":
                MpPrescripcionMedicamento medicamento = listaMedicamentos
                        .getOrDefault(ent.getNoPrescripcion().trim() + "||"
                                + ent.getConTec() + "||"
                                + 1, null);
                if (medicamento != null) {
                    entrega.setMpPrescripcion(medicamento.getMpPrescripcion());
                    entrega.setMpPrescripcionMedicamentoId(medicamento);
                    entrega.setTipoTecnologia(MpPrescripcion.TIPO_MEDICAMENTO);
                } else {
                    error.append("no se encontro medicamentos: ")
                            .append(ent.getNoPrescripcion().trim()).append("||")
                            .append(ent.getConTec());
                }
                break;
            case "P":
            case "p":
                MpPrescripcionTecnologia tecnologia = listaTecnologias
                        .getOrDefault(ent.getNoPrescripcion().trim() + "||"
                                + ent.getConTec() + "||"
                                + 2, null);
                if (tecnologia != null) {
                    entrega.setMpPrescripcion(tecnologia.getMpPrescripcion());
                    entrega.setMpPrescripcionTecnologiaId(tecnologia);
                    entrega.setTipoTecnologia(MpPrescripcion.TIPO_TECNOLOGIA);
                } else {
                    error.append("no se encontro procedimientos: ")
                            .append(ent.getNoPrescripcion()).append("||")
                            .append(ent.getConTec());
                }
                break;
            case "D":
            case "d":
                MpPrescripcionInsumo insumo = listaInsumos
                        .getOrDefault(ent.getNoPrescripcion().trim() + "||"
                                + ent.getConTec() + "||"
                                + 3, null);
                if (insumo != null) {
                    entrega.setMpPrescripcion(insumo.getMpPrescripcion());
                    entrega.setMpPrescripcionInsumoId(insumo);
                    entrega.setTipoTecnologia(MpPrescripcion.TIPO_DISPOSITIVO_MEDICO);
                } else {
                    error.append("no se encontro dispositivos: ")
                            .append(ent.getNoPrescripcion()).append("||")
                            .append(ent.getConTec());
                }
                break;
            case "N":
            case "n":
                MpPrescripcionMedicamento nut = listaMedicamentos
                        .getOrDefault(ent.getNoPrescripcion().trim() + "||"
                                + ent.getConTec() + "||"
                                + 4, null);
                if (nut != null) {
                    entrega.setMpPrescripcion(nut.getMpPrescripcion());
                    entrega.setMpPrescripcionMedicamentoId(nut);
                    entrega.setTipoTecnologia(MpPrescripcion.TIPO_PRODUCTO_NUTRICIONAL);
                } else {
                    error.append("no se encontro productosnutricionales: ")
                            .append(ent.getNoPrescripcion()).append("||")
                            .append(ent.getConTec());
                }
                break;
            case "S":
            case "s":
                MpPrescripcionInsumo sup = listaInsumos
                        .getOrDefault(ent.getNoPrescripcion().trim() + "||"
                                + ent.getConTec() + "||"
                                + 5, null);
                if (sup != null) {
                    entrega.setMpPrescripcion(sup.getMpPrescripcion());
                    entrega.setMpPrescripcionInsumoId(sup);
                    entrega.setTipoTecnologia(MpPrescripcion.TIPO_SERVICIO_COMPLEMENTARIO);
                } else {
                    error.append("no se encontro serviciosComplementarios: ")
                            .append(ent.getNoPrescripcion()).append("||")
                            .append(ent.getConTec());
                }
                break;
            default:
                error.append("Tipo de tecnología no soportado: ").append(ent.getTipoTec());
        }
        entrega.setIdReporteEntrega(ent.getIDReporteEntrega() != null ? Integer.valueOf(ent.getIDReporteEntrega()) : 0);
        if (ent.getCantTotEntregada()
                != null) {
            String cantidad = ent.getCantTotEntregada().trim();
            String cleanedCantidad = cantidad.replaceAll("[^0-9]", "");
            cleanedCantidad = cleanedCantidad.isEmpty() ? "0" : cleanedCantidad;
            if (cantidad.toLowerCase().contains("string")) {
                cleanedCantidad = "";
            }
            if (cleanedCantidad.isEmpty()) {
                entrega.setCantidadEntrega(null);
            } else {
                try {
                    entrega.setCantidadEntrega(new BigDecimal(cleanedCantidad).intValue());
                } catch (NumberFormatException e) {
                    entrega.setCantidadEntrega(null);
                }
            }
        } else {
            entrega.setCantidadEntrega(null);
        }
        entrega.setNumeroEntrega(ent.getNoEntrega() != null ? Integer.valueOf(ent.getNoEntrega()) : 0);

        entrega.setCausaNoEntrega(ent.getCausaNoEntrega() != null ? Integer.valueOf(ent.getCausaNoEntrega()) : 0);

        entrega.setEstadoEntrega(ent.getEstadoEntrega() != null ? Integer.valueOf(ent.getEstadoEntrega()) : 0);
        try {
            if (ent.getFecEntrega() != null) {
                fecha = formatoFHM.parse(ent.getFecEntrega());
                entrega.setFechaEntrega(fecha);
            }
            if (ent.getFecAnulacion() != null) {
                fecha = formatoFHM.parse(ent.getFecAnulacion());
                entrega.setFechaAnulacion(fecha);
            }
            if (ent.getFecRepEntrega() != null) {
                fecha = formatoFHM.parse(ent.getFecRepEntrega());
                entrega.setFechaReporteFactura(fecha);
            }
        } catch (ParseException ex) {

        }
        if (ent.getValorEntregado() != null) {
            String valorEntregado = ent.getValorEntregado().trim();
            String valorLimpio = "";
            try {
                valorLimpio = valorEntregado.replaceAll(",", ".");
                valorLimpio = valorLimpio.replaceAll("[^\\d.]", "");
                if (valorLimpio.chars().filter(ch -> ch == '.').count() > 1) {
                    valorLimpio = valorLimpio.replaceAll("\\.(?=.*\\.)", "");
                }
                if (valorLimpio.isEmpty()) {
                    entrega.setValorTotal(BigDecimal.ZERO);
                } else {
                    entrega.setValorTotal(new BigDecimal(valorLimpio));
                }
            } catch (NumberFormatException e) {
                entrega.setValorTotal(BigDecimal.ZERO);
            }
        } else {
            entrega.setValorTotal(BigDecimal.ZERO);
        }
        if (ent.getCodTecEntregado() != null) {
            entrega.setCodTecEntregado(ent.getCodTecEntregado());
            MpDescripcionEntregaCoigo objeto = (getMpDireccionamientoWSRemoto().consultarDescripciond(ent.getCodTecEntregado(), entrega.getTipoTecnologia()));
            if (objeto.getDescripcion() != null) {
                entrega.setDescTecEntregado(objeto.getDescripcion());
            }
            if (objeto.getCodigo() != null) {
                entrega.setTipoTecEntregado(objeto.getCodigo());
            }
        } else {
            entrega.setCodTecEntregado(null);
            entrega.setDescTecEntregado(null);
            entrega.setTipoTecEntregado(null);
        }
        if (ent.getNoLote() != null) {
            String noLote = ent.getNoLote().trim();
            if (noLote.isEmpty() || !noLote.matches("\\d+")) {
                entrega.setNumeroLote(null);
            } else {
                entrega.setNumeroLote(noLote);
            }
        } else {
            entrega.setNumeroLote(null);
        }
        if (ent.getEstRepEntrega() != null) {
            try {
                Integer estRepEntrega = Integer.valueOf(ent.getEstRepEntrega().trim());
                entrega.setEstRepEntrega(estRepEntrega);
            } catch (NumberFormatException e) {
                entrega.setEstRepEntrega(null);
            }
        } else {
            entrega.setEstRepEntrega(null);
        }
        fecha = fechaConsumo;
        entrega.setFechaConsumo(fecha);
        entrega.setUsuarioCrea("mipres sincroniza");
        entrega.setTerminalCrea("127.0.0.1");
        entrega.setFechaHoraCrea(new Date());
        entrega.setTipoComparador((short) 3);
        if (error.toString().isEmpty()) {
            try {
                int entregaId = getMpDireccionamientoWSRemoto().insertarMpDireccionamientoEntregado(entrega);

                if (validarEntregaVsDireccionamiento(entrega, direccionamiento)) {
                    entrega.setId(entregaId);
                    MpEntregaSuministro suministro = construirSuministro(entrega, direccionamiento);
                    getMpDireccionamientoWSRemoto().insertarSuministroEntrega(suministro);
                }
            } catch (Exception ex) {
                throw new Exception("Error insertarMpDireccionamientoEntregado " + ex.getMessage());
            }
        } else {
            throw new Exception(error.toString());
        }
    }

    private MpEntregaSuministro construirSuministro(MpDireccionamientoEntregado entrega, MpDireccionamiento direccionamiento) {
        MpEntregaSuministro suministro = new MpEntregaSuministro();
        // Entrega
        if (direccionamiento != null) {
            suministro.setMpDireccionamientoEntregadoId(new MpDireccionamientoEntregado(entrega.getId()));

        }
        suministro.setIdSuministro(null);//*-**          
        suministro.setNumeroPrescripcionAsociada(entrega.getMpPrescripcion().getNumeroPrescripcion());//*-**
        suministro.setEstadoMipres(1);//*-**
        suministro.setFechaSuministro(new Date());//*-**
        suministro.setUltimaEntrega(false);
        suministro.setAnulado(false);//*-**
        suministro.setFechaAnulacion(null);
        //per.setFechaConsumo(obj.getFechaConsumo());
        suministro.setUsuarioCrea("mipres sincroniza");//*-**
        suministro.setTerminalCrea("127.0.0.1");//*-**
        suministro.setFechaHoraCrea(new Date());//*-**

        return suministro;
    }

    private boolean validarEntregaVsDireccionamiento(MpDireccionamientoEntregado entrega, MpDireccionamiento direccionamiento) {
        if (entrega == null || direccionamiento == null) {
            return false;
        }
        if (!Objects.equals(entrega.getNumeroEntrega(), direccionamiento.getConsecutivoEntrega())) { //numeros de entrega
            return false;
        }
        if (entrega.getCodTecEntregado() == null || (!entrega.getCodTecEntregado().trim().equals(direccionamiento.getCodigoMpEntrega().trim()))) {
            return false;
        }

        if (entrega.getCantidadEntrega() == null || direccionamiento.getEntregaTotal() == null) {//valida si almenos uno es null y de una aborta
            return false;
        }
        if (entrega.getCantidadEntrega() > direccionamiento.getEntregaTotal()) {//cantidades de entrega
            return false;
        }
        java.util.Date fechaUtil = entrega.getFechaEntrega();
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());

        if (fechaSql.after(direccionamiento.getFechaMaxEntrega())) {
            return false; // No se permite si la entrega fue después de la fecha máxima
        }
        if (entrega.getEstRepEntrega() != 1 && (direccionamiento.getEstado() != 1 || direccionamiento.getEstado() != 2)) {//si entrega activa y direccionamiento direccionado o programado 
            return false;
        }
        return true; // Si pasa todas las validaciones
    }

    public void sincronizarfacturas(MipresBean bean) {
        try {
            boolean isSub = false;
            if ("1".equals(bean.getRegimenRe())) {
                isSub = true;
            } else {
                isSub = false;
            }
            StringBuilder errorConsumo = new StringBuilder();
            Date fechaInicioServicio = new Date();
            List<FacturacionBaseDTO> listaFacturacion = new ArrayList<>();
            Date fVencimientoToken = null;
            Token token = null;
            MpConsumo consumo = null;
            String urlServicio = "";
            String servicio = "D";
            try {            //obtener token y tiempo de vencimiento
                consumoExterno = new MiPresConsumoExterno();
                token = consumoExterno.obtenerTokenInterSavia();
                fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
            } catch (Exception ex) {
                ex.printStackTrace();
                errorConsumo.append("Error obteniendo token entregas: ").append(ex.getMessage());
            }
            errorConsumo = new StringBuilder();

            try {
                consumo = inicializarConsumo(servicio);
                // Validar si el servicio a consumir es subsidiado
                if (isSub) {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_FACTURAXPRESCRIPCION_SUB);
                    servicio = MpConsumo.SUB_CONSULTA_NRO_FACTURA;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("Facturas Sub");
                } else {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_FACTURAXPRESCRIPCION_CON);
                    servicio = MpConsumo.CON_CONSULTA_NRO_FACTURA;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("Facturas Con");
                }
                // Registra inicio de consumo

                consumo.setPeriodo(formatoF.format(fechaInicioServicio));
                consumo.setServicioDireccion("Consulta");
                consumo.setId(getPrescripcionWsRemoto()
                        .insertarMpConsumo(consumo));

                // Validar vigencia token 
                if (fVencimientoToken.before(new Date())) {
                    //se espera 3 segundos mientras termina de expirar el token
                    Thread.sleep(3000);
                    token = consumoExterno.obtenerTokenInterSavia();
                    fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
                }
                int intentos = 0;
                boolean errorCon = true;
                while (errorCon && intentos < 3) {
                    try {
                        listaFacturacion = consumoExterno.servicioGetFacturaPorNumeroPrescripcion(
                                urlServicio, token, bean.getNumeroPrescripcionRe());
                        errorCon = false;
                    } catch (Exception ex) {
                        intentos++;
                        if (intentos >= 3) {
                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                            consumo.setObservacion("No fue posible consumir servicio Facturas Re-sincronizacion. Intentos: " + intentos);
                            getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                        }
                    }
                }

            } catch (Exception ex) {
                errorConsumo.append("error consultando servicio Facturas").append(servicio).append(": ").append(ex.getMessage());
                ex.printStackTrace();
                fVencimientoToken = new Date();
            }

            if (listaFacturacion != null && !listaFacturacion.isEmpty()) {
                List<MpDireccionamientoEntregado> listaEntregadosFinal = new ArrayList<>();
                Set<String> idsProcesados = new HashSet<>();
                int exitosos = 0;
                int procesados = 0;
                int editados = 0;
                for (FacturacionBaseDTO objBaseDto : listaFacturacion) {
                    String id = objBaseDto.getIDFacturacion();
                    // Verifica si el ID ya fue procesado
                    if (idsProcesados.contains(id)) {
                        continue; // Si ya fue procesado, omite esta iteración
                    }
                    try {
                        List<MpDireccionamientoEntregado> listaEntregados = getMpDireccionamientoWSRemoto()
                                .consultarListaMpEntregaSuministroLista(
                                        objBaseDto.getNoPrescripcion(), objBaseDto.getTipoTec(),
                                        objBaseDto.getConTec(), objBaseDto.getNoEntrega()
                                );
                        if (listaEntregados != null) {
                            listaEntregadosFinal.addAll(listaEntregados);
                        }
                        idsProcesados.add(id);
                    } catch (Exception ex) {
                        throw new Exception("Error llenando listas." + ex.getMessage());
                    }
                    // Actualizar el estado de consumo
                    consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                    consumo.setRegistros(listaFacturacion.size());
                    consumo.setObservacion("Inicio Insercion BD");
                    getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                    Map<String, List<MpDireccionamientoEntregado>> entregasPorTransaccion = listaEntregadosFinal.stream()
                            .filter(Objects::nonNull)
                            .filter(entregado -> entregado.getMpPrescripcion() != null)
                            .collect(Collectors.groupingBy(entregado -> {
                                String numeroPrescripcion = entregado.getMpPrescripcion().getNumeroPrescripcion();
                                Integer numeroEntrega = entregado.getNumeroEntrega();
                                Integer consecutivoOrden = null;
                                Integer tipoTecnologia = null;
                                if (entregado.getMpPrescripcionMedicamentoId() != null) {
                                    consecutivoOrden = entregado.getMpPrescripcionMedicamentoId().getConsecutivoOrden();
                                    tipoTecnologia = 14;
                                } else if (entregado.getMpPrescripcionInsumoId() != null) {
                                    consecutivoOrden = entregado.getMpPrescripcionInsumoId().getConsecutivoOrden();
                                    tipoTecnologia = 35;
                                } else if (entregado.getMpPrescripcionTecnologiaId() != null) {
                                    consecutivoOrden = entregado.getMpPrescripcionTecnologiaId().getConsecutivoOrden();
                                    tipoTecnologia = 2;
                                }
                                return String.join("-", numeroPrescripcion, String.valueOf(numeroEntrega), String.valueOf(consecutivoOrden), String.valueOf(tipoTecnologia));
                            }));
                    try {
                        String clave = objBaseDto.getNoPrescripcion() + "-" + objBaseDto.getNoEntrega() + "-" + objBaseDto.getConTec() + "-"
                                + (objBaseDto.getTipoTec() != null ? obtenerTipoTecnologia(objBaseDto.getTipoTec()) : "");
                        List<MpDireccionamientoEntregado> entregasCorrespondientes = entregasPorTransaccion.get(clave);
                        if (entregasCorrespondientes != null && !entregasCorrespondientes.isEmpty()) {

//                                MpDireccionamientoEntregado entrega = (entregasCorrespondientes.size() > 1)
//                                        ? entregasCorrespondientes.stream().max(Comparator.comparing(MpDireccionamientoEntregado::getId)).orElse(null)
//                                        : entregasCorrespondientes.get(0);
                            Integer estadoFactura = Integer.parseInt(objBaseDto.getEstFacturacion());
                            Date fechaFacturaDate = null;
                            Date fechaFacturaAnulada = null;

                            try {
                                fechaFacturaDate = formatoF.parse(objBaseDto.getFecFacturacion());
                                if (objBaseDto.getFecAnulacion() != null) {
                                    fechaFacturaAnulada = formatoF.parse(objBaseDto.getFecAnulacion());
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            List<MpDireccionamientoEntregado> entregasFiltradas = new ArrayList<>();

                            for (MpDireccionamientoEntregado entrega : entregasCorrespondientes) {
                                Integer estadoEntrega = entrega.getEstRepEntrega();

                                if (estadoFactura == 0) {  // Factura anulada
                                    if (estadoEntrega == 0 || estadoEntrega == 1 || estadoEntrega == 2) {//0 entrega anulada - 1 entrega activa - 2 entrega procesada
                                        entregasFiltradas.add(entrega);
                                    }
                                } else if (estadoFactura == 1 || estadoFactura == 2) {
                                    if (estadoEntrega == 1 || estadoEntrega == 2) {
                                        entregasFiltradas.add(entrega);
                                    }
                                }
                            }

//                              MpDireccionamientoEntregado entregasPorFecha = new MpDireccionamientoEntregado();
                            MpDireccionamientoEntregado entregasPorFecha = null;
                            List<MpDireccionamientoEntregado> entregasCoincidentes = new ArrayList<>();

                            if (estadoFactura == 0) {
                                for (MpDireccionamientoEntregado entrega : entregasFiltradas) {
                                    Date fechaEntrega = formatoF.parse(entrega.getFechaReporteFactura().toString());
                                    Date fechaAnulacionEntrega = (entrega.getFechaAnulacion() != null)
                                            ? formatoF.parse(entrega.getFechaAnulacion().toString())
                                            : null;

                                    if (entrega.getEstRepEntrega() == 0) {  // Entrega anulada
                                        if ((fechaEntrega.before(fechaFacturaDate) || fechaEntrega.equals(fechaFacturaDate))
                                                && (fechaAnulacionEntrega == null || !fechaAnulacionEntrega.before(fechaFacturaAnulada))) {
                                            entregasCoincidentes.add(entrega);
                                        }
                                    } else {  // Entrega activa
                                        if (fechaEntrega.before(fechaFacturaDate) || fechaEntrega.equals(fechaFacturaDate)) {
                                            entregasCoincidentes.add(entrega);
                                        }
                                    }
                                }
                            } else if (estadoFactura == 1 || estadoFactura == 2) {
                                if (entregasFiltradas.size() > 1) {
                                    for (MpDireccionamientoEntregado entrega : entregasFiltradas) {
                                        if (entregasPorFecha == null || entrega.getId() > entregasPorFecha.getId()) {
                                            entregasPorFecha = entrega;
                                        }
                                    }
                                } else if (entregasFiltradas.size() == 1) {
                                    entregasPorFecha = entregasFiltradas.get(0);
                                }
                            }

                            if (entregasCoincidentes.size() == 1) {
                                entregasPorFecha = entregasCoincidentes.get(0);
                            } else if (entregasCoincidentes.size() > 1) {
                                entregasPorFecha = seleccionarMejorEntrega(entregasCoincidentes);
                            }

                            if (entregasPorFecha != null) {
                                MpEntregaFactura facturacion = asignarMpEntregaFactura(objBaseDto, entregasPorFecha, consumo);

                                MpEntregaFactura entregaFacturaExistente = getMpDireccionamientoWSRemoto()
                                        .consultarEntregaFactura(objBaseDto.getID(), objBaseDto.getIDFacturacion());

                                if (entregaFacturaExistente == null) {
                                    getMpDireccionamientoWSRemoto().mergeEntregaFactura(facturacion);
                                    exitosos++;
                                    procesados++;
                                } else {
                                    boolean validacion = false;
                                    if (facturacion.getCodigoFacturado() != null && entregaFacturaExistente.getCodigoFacturado() == null) {
                                        getMpDireccionamientoWSRemoto().actualizarCodigoFacturado(entregaFacturaExistente.getId(), facturacion.getCodigoFacturado());
                                        validacion = true;
                                    }

                                    if (!Objects.equals(entregaFacturaExistente.getMpDireccionamientoEntregadoId().getId(), facturacion.getMpDireccionamientoEntregadoId().getId())) {
                                        getMpDireccionamientoWSRemoto().actualizarIdEntregaFacturaExistente(entregaFacturaExistente.getId(), facturacion.getMpDireccionamientoEntregadoId().getId());
                                        validacion = true;
                                    }
                                    if (entregaFacturaExistente.getEstado() != Integer.parseInt(objBaseDto.getEstFacturacion())) {
                                        String idFact = entregaFacturaExistente.getIdFacturacion();
                                        Short estado = Short.valueOf(objBaseDto.getEstFacturacion());
                                        Date fechaAnulacion = formatoF.parse(objBaseDto.getFecAnulacion());
                                        getMpDireccionamientoWSRemoto().actualizarEntregaFacturaExistente(idFact, estado, null, fechaAnulacion);
                                        validacion = true;
                                    }
                                    exitosos++;
                                    if (validacion) {
                                        editados++;
                                    }
                                }
                            }

                        }

                        if (exitosos % 100 == 0) {
                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                            consumo.setObservacion("Insercion BD: - " + exitosos + " - Exitosos - " + procesados + " - Nuevo(s) registros - " + editados + " - Updates");
                            consumo.setRegistrosExitosos(exitosos);
                            getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                        }
                    } catch (Exception ex) {
                        try {
                            MpConsumoFallo mpFallo = new MpConsumoFallo();
                            mpFallo.setEstado(MpConsumoFallo.ESTADO_CONSUMOFALLO_FALLIDO);
                            mpFallo.setMpConsumoId(consumo);
                            mpFallo.setDescripcion(ex.getMessage());
                            mpFallo.setFechaProceso(fechaInicioServicio);
                            mpFallo.setFechaHoraFallo(new Date());
                            Gson gson = new Gson();
                            String jsonString = gson.toJson(objBaseDto);
                            mpFallo.setJson(jsonString);
                            getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                        } catch (Exception exe) {
                        }
                    }
                }
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                consumo.setObservacion("Fin insercion BD: - " + exitosos + " - Exitosos - " + procesados + " - Nuevo(s) registros - " + editados + " - Updates");
                consumo.setRegistrosExitosos(exitosos);
                consumo.setFechaHoraFin(new Date());
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                bean.addMensaje("Proceso de facturas exitoso :" + exitosos);
            } else {
                if (!errorConsumo.toString().isEmpty()) {
                    try {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion(errorConsumo.toString());
                        bean.addError("La sincronización entregas se detuvo. Inténtelo de nuevo.");
                    } catch (Exception ex) {
                    }
                } else {
                    consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                    consumo.setObservacion("sin registros");
                    bean.addMensaje("No se encontraron registros de entregas para sincronizar.");
                }
            }
            try {
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private MpEntregaFactura asignarMpEntregaFactura(FacturacionBaseDTO objBaseDto, MpDireccionamientoEntregado entrega, MpConsumo consumo) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        MpEntregaFactura facturacion = new MpEntregaFactura();
        try {

            if (entrega != null) {
                facturacion.setMpDireccionamientoEntregadoId(new MpDireccionamientoEntregado(entrega.getId()));
                if (entrega.getMpDireccionamientoId() != null) {
                    facturacion.setNit(entrega.getMpDireccionamientoId().getPrestadorNumeroDocumento().toString());
                } else {
                    facturacion.setNit(entrega.getMpPrescripcion().getPrestadorNumeroDocumento());
                }
            }

            if (objBaseDto.getCodSerTecAEntregado() != null) {
                facturacion.setCodigoFacturado(objBaseDto.getCodSerTecAEntregado());
            }

            if (objBaseDto.getID() != null) {
                facturacion.setIdTransaccion(objBaseDto.getID());
            }
            if (objBaseDto.getIDFacturacion() != null) {
                facturacion.setIdFacturacion(objBaseDto.getIDFacturacion());
            }
            if (objBaseDto.getNoSubEntrega() != null) {
                facturacion.setNoSubEntrega(ParseUtils.parseShort(objBaseDto.getNoSubEntrega()));
            }
            if (objBaseDto.getNoFactura() != null) {
                if (objBaseDto.getNoFactura().length() >= 20) {
                    facturacion.setCufe(objBaseDto.getNoFactura());
                } else {
                    facturacion.setNoFactura(objBaseDto.getNoFactura());
                }
            }
            if (objBaseDto.getNoIDEPS() != null) {
                facturacion.setNoidEPS(objBaseDto.getNoIDEPS());
            }
            if (objBaseDto.getCodEPS() != null) {
                facturacion.setCodEPS(objBaseDto.getCodEPS());
            }
            facturacion.setCantUnitaria(ParseUtils.parseBigDecimal(objBaseDto.getCantUnMinDis()));
            facturacion.setValorTotal(ParseUtils.parseBigDecimal(objBaseDto.getValorTotFacturado()));
            facturacion.setValorUnitario(ParseUtils.parseBigDecimal(objBaseDto.getValorUnitFacturado()));
            facturacion.setCuotaModeradora(ParseUtils.parseBigDecimal(objBaseDto.getCuotaModer()));
            facturacion.setCopago(ParseUtils.parseBigDecimal(objBaseDto.getCopago()));
            if (objBaseDto.getFecFacturacion() != null) {
                try {
                    facturacion.setFechaFacturacion(formato.parse(objBaseDto.getFecFacturacion()));
                } catch (ParseException e) {
                }
            }
            if (objBaseDto.getEstFacturacion() != null) {
                facturacion.setEstado(ParseUtils.parseShort(objBaseDto.getEstFacturacion()));
            }
            if (objBaseDto.getFecAnulacion() != null) {
                try {
                    facturacion.setFechaAnulacion(formato.parse(objBaseDto.getFecAnulacion()));
                } catch (ParseException e) {
                }
            }
            if (objBaseDto.getCodigosFacturacion() != null) {
                facturacion.setMpEntregaFacturascol(objBaseDto.getCodigosFacturacion());
            }
            facturacion.setUsuarioCrea("mipres sincroniza");
            facturacion.setTerminalCrea("127.0.0.1");
            facturacion.setFechaHoraCrea(new Date());

        } catch (Exception ex) {
//            insertarMpFallo(consumo, baseDTO, null, ex.getMessage());
        }
        return facturacion;
    }

    private MpDireccionamientoEntregado seleccionarMejorEntrega(List<MpDireccionamientoEntregado> entregas) {
        for (MpDireccionamientoEntregado entrega : entregas) {
            if (entrega.getEstRepEntrega() == 0) {  // Si hay una entrega anulada, se prioriza para asignar
                return entrega;
            }
        }
        // Si no hay entregas anuladas, devolver la primera entrega activa que encuentre
        return entregas.get(0);
    }

    private Integer obtenerTipoTecnologia(String tipoTec) {
        if (tipoTec == null) {
            return null; // Retorna null si no hay tipoTec
        }
        switch (tipoTec.toUpperCase()) {
            case "M":
            case "N":
                return 14;
            case "D":
            case "S":
                return 35;
            case "P":
                return 2;
            default:
                return null; // Retorna null si no se encuentra un tipo válido
        }
    }

    public void sincronizarDireccionamientos(MipresBean bean) {
        try {
            boolean isSub = false;
            if ("1".equals(bean.getRegimenRe())) {
                isSub = true;
            } else {
                isSub = false;
            }
            StringBuilder errorConsumo = new StringBuilder();
            Date fechaInicioServicio = new Date();
            List<MpDireccionamiento> direccionado = null;
            Date fVencimientoToken = null;
            Token token = null;
            MpConsumo consumo = null;
            String urlServicio = "";
            String servicio = "D";
            try {            //obtener token y tiempo de vencimiento
                consumoExterno = new MiPresConsumoExterno();
                token = consumoExterno.obtenerTokenInterSavia();
                fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
            } catch (Exception ex) {
                ex.printStackTrace();
                errorConsumo.append("Error obteniendo token: ").append(ex.getMessage());
            }
            List<DireccionamientoBaseDTO> respuesta = null;
            errorConsumo = new StringBuilder();
            int exitosos = 0;
            int upd = 0;
            try {
                consumo = inicializarConsumo(servicio);
                // Validar si el servicio a consumir es subsidiado
                if (isSub) {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_DIRECCIONAMIENTOS_SUB);
                    servicio = MpConsumo.SUB_CONSULTA_NRO_DIRECCIONAMIENTO;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("Direccionamiento Sub");
                } else {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_DIRECCIONAMIENTOS_CON);
                    servicio = MpConsumo.CON_CONSULTA_NRO_DIRECCIONAMIENTO;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("Direccionamiento Con");
                }
                // Registra inicio de consumo

                consumo.setPeriodo(formatoF.format(fechaInicioServicio));
                consumo.setServicioDireccion("Consulta");
                consumo.setId(getPrescripcionWsRemoto()
                        .insertarMpConsumo(consumo));

                // Validar vigencia token 
                if (fVencimientoToken.before(new Date())) {
                    //se espera 3 segundos mientras termina de expirar el token
                    Thread.sleep(3000);
                    token = consumoExterno.obtenerTokenInterSavia();
                    fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
                }
                //Quitar ultimo campo servicioPrescripciones
                int intentos = 0;
                while (respuesta == null) {
                    // si ha fallado 3 veces cierra el consumo como fallido y termina los consumos
                    if (intentos < 3) {
                        respuesta = consumoExterno.
                                servicioGetDireccionamientoPorNumeroPrescripcion(
                                        urlServicio,
                                        token,
                                        bean.getNumeroPrescripcionRe());
                        intentos++;
                    } else {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion("No fue posible consumir servicio de direccionamientos # " + intentos);
                        getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                    }
                }
            } catch (Exception ex) {
                errorConsumo.append("error consultando servicio direccionamiento").append(servicio).append(": ").append(ex.getMessage());
                ex.printStackTrace();
                fVencimientoToken = new Date();
            }

            if (respuesta != null && !respuesta.isEmpty()) {
                List<MpPrescripcionMedicamento> listaMeds = new ArrayList();
                List<MpPrescripcionTecnologia> listaTecs = new ArrayList();
                List<MpPrescripcionInsumo> listaIns = new ArrayList();
                MpPrescripcion pr;
                MpPrescripcionMedicamento med;
                MpPrescripcionTecnologia tec;
                MpPrescripcionInsumo ins;
                for (DireccionamientoBaseDTO dir : respuesta) {
                    pr = new MpPrescripcion();
                    switch (dir.getTipoTec()) {
                        case "M":
                            //1 medicamentos 
                            med = new MpPrescripcionMedicamento();
                            pr.setNumeroPrescripcion(dir.getNoPrescripcion());
                            med.setMpPrescripcion(pr);
                            med.setConsecutivoOrden(dir.getConTec());
                            med.setTipoTecnologia(MpPrescripcion.TIPO_MEDICAMENTO);
                            listaMeds.add(med);
                            break;
                        case "P":
                            //2 procedimientos 
                            tec = new MpPrescripcionTecnologia();
                            pr.setNumeroPrescripcion(dir.getNoPrescripcion());
                            tec.setMpPrescripcion(pr);
                            tec.setConsecutivoOrden(dir.getConTec());
                            tec.setTipoTecnologia(MpPrescripcion.TIPO_TECNOLOGIA);
                            listaTecs.add(tec);
                            break;
                        case "D":
                            //3 dispositivos 
                            ins = new MpPrescripcionInsumo();
                            pr.setNumeroPrescripcion(dir.getNoPrescripcion());
                            ins.setMpPrescripcion(pr);
                            ins.setConsecutivoOrden(dir.getConTec());
                            ins.setTipoTecnologia(MpPrescripcion.TIPO_DISPOSITIVO_MEDICO);
                            listaIns.add(ins);
                            break;
                        case "N":
                            //4 productosnutricionales 
                            med = new MpPrescripcionMedicamento();
                            pr.setNumeroPrescripcion(dir.getNoPrescripcion());
                            med.setMpPrescripcion(pr);
                            med.setConsecutivoOrden(dir.getConTec());
                            med.setTipoTecnologia(MpPrescripcion.TIPO_PRODUCTO_NUTRICIONAL);
                            listaMeds.add(med);
                            break;
                        case "S":
                            //5 serviciosComplementarios
                            ins = new MpPrescripcionInsumo();
                            pr.setNumeroPrescripcion(dir.getNoPrescripcion());
                            ins.setMpPrescripcion(pr);
                            ins.setConsecutivoOrden(dir.getConTec());
                            ins.setTipoTecnologia(MpPrescripcion.TIPO_SERVICIO_COMPLEMENTARIO);
                            listaIns.add(ins);
                            break;
                        default:
                            errorConsumo.append("Tipo de tecnología no soportado: ")
                                    .append(dir.getTipoTec());
                    }
                }
                Map<String, MpPrescripcionMedicamento> listaMedicamentos = null;
                Map<String, MpPrescripcionTecnologia> listaTecnologias = null;
                Map<String, MpPrescripcionInsumo> listaInsumos = null;
                try {
                    listaMedicamentos = getMpPrescripcionTecnologiaWSRemoto()
                            .consultarListaMpMedicamentos(listaMeds);

                    listaTecnologias = getMpPrescripcionTecnologiaWSRemoto()
                            .consultarListaMpTecnologias(listaTecs);

                    listaInsumos = getMpPrescripcionTecnologiaWSRemoto()
                            .consultarListaMpInsumos(listaIns);
                } catch (Exception ex) {
                    errorConsumo.append("Error llenando listas de direccionamientos: ").append(ex.getMessage());
                }
                // control error Consumo 
                if (!errorConsumo.toString().isEmpty()) {
                    try {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion(errorConsumo.toString());
                        getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                    } catch (Exception ex) {
                    }
                }
                MpConsumoFallo mpFallo = new MpConsumoFallo();
                // Actualiza el registro de consumo 
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                consumo.setRegistros(respuesta.size());
                consumo.setObservacion("inicio insercion BD");
                try {
                    getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                } catch (Exception ex) {
                }
                try {
                    direccionado = getMpPrescripcionTecnologiaWSRemoto()
                            .consultarDireccionamientosPorIdPrescripcion(bean.getIdPrescripcionRe());
                } catch (Exception ex) {
                }

                Set<Integer> idDireccionamiento = direccionado.stream()
                        .map(MpDireccionamiento::getIdDireccionamiento)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());

                for (DireccionamientoBaseDTO dir : respuesta) {
                    try {
                        Integer id = dir.getIDDireccionamiento();
                        if (!idDireccionamiento.contains(id)) {
                            procesarDireccionamiento(
                                    dir,
                                    listaMedicamentos,
                                    listaTecnologias,
                                    listaInsumos);
                            exitosos = exitosos + 1;
                        } else {
                            for (MpDireccionamiento dire : direccionado) {
                                if (Objects.equals(dir.getIDDireccionamiento(), dire.getIdDireccionamiento())) {
                                    // Verificar estados, si son iguales no hago nada
                                    if (Integer.parseInt(dir.getEstDireccionamiento()) != (dire.getEstado())) {
                                        getMpPrescripcionTecnologiaWSRemoto()
                                                .cambiarEstadoDireccionamiento(dire.getId(), Integer.parseInt(dir.getEstDireccionamiento()));
                                        exitosos = exitosos + 1;
                                        if (exitosos % 100 == 0) {
                                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                                            consumo.setObservacion("inicio insercion BD");
                                            consumo.setRegistrosExitosos(exitosos);
                                            try {
                                                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                                            } catch (Exception ex) {
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                            upd = upd + 1;
                        }

                        if (exitosos % 100 == 0) {
                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                            consumo.setObservacion("inicio insercion BD");
                            consumo.setRegistrosExitosos(exitosos);
                            try {
                                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                            } catch (Exception ex) {
                            }
                        }
                    } catch (Exception ex) {
                        mpFallo.setEstado(MpConsumoFallo.ESTADO_CONSUMOFALLO_FALLIDO);
                        mpFallo.setMpConsumoId(consumo);
                        mpFallo.setDescripcion(ex.getMessage());
                        mpFallo.setFechaProceso(fechaInicioServicio);
                        mpFallo.setFechaHoraFallo(new Date());
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(dir);
                        mpFallo.setJson(jsonString);
                        try {
                            getConsumoFallosWsRemoto().insertarConsumoFallo(mpFallo);
                        } catch (Exception exe) {
                        }
                    }
                }
                Integer valor = exitosos + upd;
                if (valor == respuesta.size()) {
                    bean.addMensaje("Proceso de direccionamientos exitoso :" + valor + " de " + respuesta.size());
                }
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                consumo.setObservacion(exitosos + " - Exitosos " + upd + " - Actuañizados");
                consumo.setRegistrosExitosos(exitosos + upd);
            } else {
                if (!errorConsumo.toString().isEmpty()) {
                    try {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion(errorConsumo.toString());
                        bean.addError("La sincronización direccionamientos se detuvo. Inténtelo de nuevo.");
                    } catch (Exception ex) {
                    }
                } else {
                    consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                    consumo.setObservacion("sin registros");
                    bean.addMensaje("No se encontraron registros para sincronizar direccionamientos.");
                }
            }
            try {
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void procesarDireccionamiento(
            DireccionamientoBaseDTO dir,
            Map<String, MpPrescripcionMedicamento> listaMedicamentos,
            Map<String, MpPrescripcionTecnologia> listaTecnologias,
            Map<String, MpPrescripcionInsumo> listaInsumos) throws Exception {
        StringBuilder error = new StringBuilder();
        Date fecha;
        MpDireccionamiento direccionamiento = new MpDireccionamiento();
        if (dir.getID() != null) {
            direccionamiento.setIdTransaccion(dir.getID());
        }
        if (dir.getIDDireccionamiento() != null) {
            direccionamiento.setIdDireccionamiento(dir.getIDDireccionamiento());
        }
        if (dir.getConTec() != null) {
            direccionamiento.setConsecutivoTecAsociada(String.valueOf(dir.getConTec()));
        }
        if (dir.getNoEntrega() != null) {
            direccionamiento.setConsecutivoEntrega(dir.getNoEntrega());
        }
        if (dir.getNoSubEntrega() != null) {
            direccionamiento.setSubEntrega(dir.getNoSubEntrega());
        }
        if (dir.getFecMaxEnt() != null) {
            fecha = formatoF.parse(dir.getFecMaxEnt());
            direccionamiento.setFechaMaxEntrega(fecha);
        }

        if (dir.getCantTotAEntregar() != null && !dir.getCantTotAEntregar().trim().isEmpty()) {
            String cantidad = dir.getCantTotAEntregar().trim().replaceAll(",", ".");
            try {
                if (validarDecimal(cantidad)) {
                    direccionamiento.setEntregaTotal(new BigDecimal(cantidad).intValue());
                } else {
                    direccionamiento.setEntregaTotal(null);
                }
            } catch (NumberFormatException e) {
                direccionamiento.setEntregaTotal(null);
            }
        } else {
            direccionamiento.setEntregaTotal(null);
        }

        if (dir.getCantTotAEntregar() != null && !dir.getCantTotAEntregar().trim().isEmpty()) {
            direccionamiento.setEntregadoPendiente(new BigDecimal(dir.getCantTotAEntregar().trim().replaceAll(",", ".")).intValue());
        }

        if (dir.getFecDireccionamiento() != null) {
            fecha = formatoF.parse(dir.getFecDireccionamiento());
            direccionamiento.setFechaDireccionamiento(fecha);
        }
        if (dir.getEstDireccionamiento() != null) {
            direccionamiento.setEstado(Integer.parseInt(dir.getEstDireccionamiento()));
        }
        if (dir.getFecAnulacion() != null) {
            fecha = formatoF.parse(dir.getFecAnulacion());
            direccionamiento.setFechaAnulacionDireccionamiento(fecha);
        }
        if (dir.getCodMunEnt() != null) {
            direccionamiento.setUbicacionSedeIdStr(dir.getCodMunEnt());
        }
        if (dir.getTipoIDProv() != null) {
            Maestro tipoDocProv = hashMaestroTipoDocumentoEmpresas.get(dir.getTipoIDProv());
            direccionamiento.setMaeTipoDocumentoPrestadorId(tipoDocProv.getId());
            direccionamiento.setMaeTipoDocumentoPrestadorCodigo(tipoDocProv.getValor());
            direccionamiento.setMaeTipoDocumentoPrestadorValor(tipoDocProv.getNombre());
        }
//        

        if (dir.getNoIDProv() != null) {
            String noIdProv = dir.getNoIDProv().trim();
            String limpiarNoIdProv = noIdProv.replaceAll("[^0-9]", "");
            if (limpiarNoIdProv.isEmpty()) {
                direccionamiento.setPrestadorNumeroDocumento(null);
            } else {
                try {
                    direccionamiento.setPrestadorNumeroDocumento(new BigInteger(limpiarNoIdProv));
                } catch (NumberFormatException e) {
                    direccionamiento.setPrestadorNumeroDocumento(null);
                }
            }
        } else {
            direccionamiento.setPrestadorNumeroDocumento(null);
        }
        if (dir.getTipoIDPaciente() != null) {//validar el tipo NV sigue fallando revisar MAESTRAS
            Maestro tipoDocPaciente = hashMaestroTipoDocumentos.get(dir.getTipoIDPaciente());
            direccionamiento.setMaeTipoDocumentoPacienteId(tipoDocPaciente.getId());
            direccionamiento.setMaeTipoDocumentoPacienteCodigo(tipoDocPaciente.getValor());
            direccionamiento.setMaeTipoDocumentoPacienteValor(tipoDocPaciente.getNombre());
        }
        if (dir.getNoIDPaciente() != null) {
            direccionamiento.setNumeroDocumentoPaciente(dir.getNoIDPaciente());
        }
        if (dir.getCodSerTecAEntregar() != null) {
            direccionamiento.setCodigoMpEntrega(dir.getCodSerTecAEntregar());
        }

        direccionamiento.setUsuarioCrea("mipres sincroniza");
        direccionamiento.setTerminalCrea("127.0.0.1");
        direccionamiento.setFechaHoraCrea(new Date());

        switch (dir.getTipoTec()) {
            case "M":
            case "m":
                MpPrescripcionMedicamento medicamento = listaMedicamentos
                        .getOrDefault(dir.getNoPrescripcion().trim() + "||"
                                + dir.getConTec() + "||"
                                + 1, null);
                if (medicamento != null) {
                    direccionamiento.setMpPrescripcionId(medicamento.getMpPrescripcion());
                    direccionamiento.setMpPrescripcionMedicamentoId(medicamento);
                    direccionamiento.setTipoTecnologia(MpPrescripcion.TIPO_MEDICAMENTO);
                } else {
                    error.append("no se encontro medicamentos: ")
                            .append(dir.getNoPrescripcion().trim()).append("||")
                            .append(dir.getConTec());
                }
                break;
            case "P":
            case "p":
                MpPrescripcionTecnologia tecnologia = listaTecnologias
                        .getOrDefault(dir.getNoPrescripcion().trim() + "||"
                                + dir.getConTec() + "||"
                                + 2, null);
                if (tecnologia != null) {
                    direccionamiento.setMpPrescripcionId(tecnologia.getMpPrescripcion());
                    direccionamiento.setMpPrescripcionTecnologiaId(tecnologia);
                    direccionamiento.setTipoTecnologia(MpPrescripcion.TIPO_TECNOLOGIA);
                } else {
                    error.append("no se encontro procedimientos: ")
                            .append(dir.getNoPrescripcion()).append("||")
                            .append(dir.getConTec());
                }
                break;
            case "D":
            case "d":
                MpPrescripcionInsumo insumo = listaInsumos
                        .getOrDefault(dir.getNoPrescripcion().trim() + "||"
                                + dir.getConTec() + "||"
                                + 3, null);
                if (insumo != null) {
                    direccionamiento.setMpPrescripcionId(insumo.getMpPrescripcion());
                    direccionamiento.setMpPrescripcionInsumoId(insumo);
                    direccionamiento.setTipoTecnologia(MpPrescripcion.TIPO_DISPOSITIVO_MEDICO);
                } else {
                    error.append("no se encontro dispositivos: ")
                            .append(dir.getNoPrescripcion()).append("||")
                            .append(dir.getConTec());
                }
                break;
            case "N":
            case "n":
                MpPrescripcionMedicamento nut = listaMedicamentos
                        .getOrDefault(dir.getNoPrescripcion().trim() + "||"
                                + dir.getConTec() + "||"
                                + 4, null);
                if (nut != null) {
                    direccionamiento.setMpPrescripcionId(nut.getMpPrescripcion());
                    direccionamiento.setMpPrescripcionMedicamentoId(nut);
                    direccionamiento.setTipoTecnologia(MpPrescripcion.TIPO_PRODUCTO_NUTRICIONAL);
                } else {
                    error.append("no se encontro productosnutricionales: ")
                            .append(dir.getNoPrescripcion()).append("||")
                            .append(dir.getConTec());
                }
                break;
            case "S":
            case "s":
                MpPrescripcionInsumo sup = listaInsumos
                        .getOrDefault(dir.getNoPrescripcion().trim() + "||"
                                + dir.getConTec() + "||"
                                + 5, null);
                if (sup != null) {
                    direccionamiento.setMpPrescripcionId(sup.getMpPrescripcion());
                    direccionamiento.setMpPrescripcionInsumoId(sup);
                    direccionamiento.setTipoTecnologia(MpPrescripcion.TIPO_SERVICIO_COMPLEMENTARIO);
                } else {
                    error.append("no se encontro serviciosComplementarios: ")
                            .append(dir.getNoPrescripcion()).append("||")
                            .append(dir.getConTec());
                }
                break;
            default:
                error.append("Tipo de tecnología no soportado: ")
                        .append(dir.getTipoTec());
        }
        if (error.toString().isEmpty()) {
            try {
                getMpDireccionamientoWSRemoto()
                        .insertarMpDireccionamiento(direccionamiento);
            } catch (Exception ex) {
                throw new Exception("Error insertarMpDireccionamiento" + ex.getCause());
            }
        } else {
            throw new Exception(error.toString());
        }

    }

    private boolean validarDecimal(String input) {
        // Verifica si la cadena es un número decimal válido
        return input.matches("^\\d*(\\.\\d+)?$");
    }

    public void sincronizarNoDireccionamientos(MipresBean bean) {
        try {
            boolean isSub = false;
            if ("1".equals(bean.getRegimenRe())) {
                isSub = true;
            } else {
                isSub = false;
            }
            StringBuilder errorConsumo = new StringBuilder();
            Date fechaInicioServicio = new Date();
            List<MpNoDireccionado> noDireccionado = null;
            Date fVencimientoToken = null;
            Token token = null;
            MpConsumo consumo = null;
            String urlServicio = "";
            String servicio = "D";
            try {            //obtener token y tiempo de vencimiento
                consumoExterno = new MiPresConsumoExterno();
                token = consumoExterno.obtenerTokenInterSavia();
                fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
            } catch (Exception ex) {
                ex.printStackTrace();
                errorConsumo.append("Error obteniendo token: ").append(ex.getMessage());
            }
            List<NoDireccionamientoBaseDTO> respuesta = null;
            errorConsumo = new StringBuilder();
            int exitosos = 0;
            int upd = 0;
            try {
                consumo = inicializarConsumo(servicio);
                // Validar si el servicio a consumir es subsidiado
                if (isSub) {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_NO_DIRECCIONAMIENTOS_SUB);
                    servicio = MpConsumo.SUB_CONSULTA_NRO_NO_DIRECCIONAMIENTO;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("NoDireccionamiento Sub");
                } else {
                    urlServicio = PropApl.getInstance()
                            .get(PropApl.MP_WS_URL_SINCRO_NO_DIRECCIONAMIENTOS_CON);
                    servicio = MpConsumo.CON_CONSULTA_NRO_NO_DIRECCIONAMIENTO;
                    consumo.setServicio(servicio);
                    consumo.setServicioDescripcion("NoDireccionamiento Con");
                }
                // Registra inicio de consumo

                consumo.setPeriodo(formatoF.format(fechaInicioServicio));
                consumo.setServicioDireccion("Consulta");
                consumo.setId(getPrescripcionWsRemoto()
                        .insertarMpConsumo(consumo));

                // Validar vigencia token 
                if (fVencimientoToken.before(new Date())) {
                    //se espera 3 segundos mientras termina de expirar el token
                    Thread.sleep(3000);
                    token = consumoExterno.obtenerTokenInterSavia();
                    fVencimientoToken = consumoExterno.obtenerFVencimientoToken(token);
                }
                //Quitar ultimo campo servicioPrescripciones
                int intentos = 0;
                while (respuesta == null) {
                    // si ha fallado 3 veces cierra el consumo como fallido y termina los consumos
                    if (intentos < 3) {
                        respuesta = consumoExterno.
                                servicioGetNoDireccionamientoPorNumeroPrescripcion(
                                        urlServicio,
                                        token,
                                        bean.getNumeroPrescripcionRe());
                        intentos++;
                    } else {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion("No fue posible consumir servicio de noDireccionamientos # " + intentos);
                        getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                    }
                }
            } catch (Exception ex) {
                errorConsumo.append("error consultando servicio noDireccionamiento").append(servicio).append(": ").append(ex.getMessage());
                ex.printStackTrace();
                fVencimientoToken = new Date();
            }

            if (respuesta != null && !respuesta.isEmpty()) {
                List<MpPrescripcionMedicamento> listaMeds = new ArrayList();
                List<MpPrescripcionTecnologia> listaTecs = new ArrayList();
                List<MpPrescripcionInsumo> listaIns = new ArrayList();
                MpPrescripcion pr;
                MpPrescripcionMedicamento med;
                MpPrescripcionTecnologia tec;
                MpPrescripcionInsumo ins;
                for (NoDireccionamientoBaseDTO dir : respuesta) {
                    pr = new MpPrescripcion();
                    switch (dir.getTipoTec()) {
                        case "M":
                            //1 medicamentos 
                            med = new MpPrescripcionMedicamento();
                            pr.setNumeroPrescripcion(dir.getNoPrescripcion());
                            med.setMpPrescripcion(pr);
                            med.setConsecutivoOrden(dir.getConTec());
                            med.setTipoTecnologia(MpPrescripcion.TIPO_MEDICAMENTO);
                            listaMeds.add(med);
                            break;
                        case "P":
                            //2 procedimientos 
                            tec = new MpPrescripcionTecnologia();
                            pr.setNumeroPrescripcion(dir.getNoPrescripcion());
                            tec.setMpPrescripcion(pr);
                            tec.setConsecutivoOrden(dir.getConTec());
                            tec.setTipoTecnologia(MpPrescripcion.TIPO_TECNOLOGIA);
                            listaTecs.add(tec);
                            break;
                        case "D":
                            //3 dispositivos 
                            ins = new MpPrescripcionInsumo();
                            pr.setNumeroPrescripcion(dir.getNoPrescripcion());
                            ins.setMpPrescripcion(pr);
                            ins.setConsecutivoOrden(dir.getConTec());
                            ins.setTipoTecnologia(MpPrescripcion.TIPO_DISPOSITIVO_MEDICO);
                            listaIns.add(ins);
                            break;
                        case "N":
                            //4 productosnutricionales 
                            med = new MpPrescripcionMedicamento();
                            pr.setNumeroPrescripcion(dir.getNoPrescripcion());
                            med.setMpPrescripcion(pr);
                            med.setConsecutivoOrden(dir.getConTec());
                            med.setTipoTecnologia(MpPrescripcion.TIPO_PRODUCTO_NUTRICIONAL);
                            listaMeds.add(med);
                            break;
                        case "S":
                            //5 serviciosComplementarios
                            ins = new MpPrescripcionInsumo();
                            pr.setNumeroPrescripcion(dir.getNoPrescripcion());
                            ins.setMpPrescripcion(pr);
                            ins.setConsecutivoOrden(dir.getConTec());
                            ins.setTipoTecnologia(MpPrescripcion.TIPO_SERVICIO_COMPLEMENTARIO);
                            listaIns.add(ins);
                            break;
                        default:
                            errorConsumo.append("Tipo de tecnología no soportado: ")
                                    .append(dir.getTipoTec());
                    }
                }
                Map<String, MpPrescripcionMedicamento> listaMedicamentos = null;
                Map<String, MpPrescripcionTecnologia> listaTecnologias = null;
                Map<String, MpPrescripcionInsumo> listaInsumos = null;
                try {
                    listaMedicamentos = getMpPrescripcionTecnologiaWSRemoto()
                            .consultarListaMpMedicamentos(listaMeds);

                    listaTecnologias = getMpPrescripcionTecnologiaWSRemoto()
                            .consultarListaMpTecnologias(listaTecs);

                    listaInsumos = getMpPrescripcionTecnologiaWSRemoto()
                            .consultarListaMpInsumos(listaIns);
                } catch (Exception ex) {
                    errorConsumo.append("Error llenando listas de noDireccionamientos: ").append(ex.getMessage());
                }
                // control error Consumo 
                if (!errorConsumo.toString().isEmpty()) {
                    try {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion(errorConsumo.toString());
                        getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                    } catch (Exception ex) {
                    }
                }
                MpConsumoFallo mpFallo = new MpConsumoFallo();
                // Actualiza el registro de consumo 
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                consumo.setRegistros(respuesta.size());
                consumo.setObservacion("inicio insercion BD");
                try {
                    getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                } catch (Exception ex) {
                }
                try {
                    noDireccionado = getMpPrescripcionTecnologiaWSRemoto()
                            .consultarNoDireccionamientosPorIdPrescripcion(bean.getIdPrescripcionRe());
                } catch (Exception ex) {
                }

                Set<Integer> idDireccionamiento = noDireccionado.stream()
                        .map(MpNoDireccionado::getIdNoDireccionamiento)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());

                for (NoDireccionamientoBaseDTO dir : respuesta) {
                    try {
                        Integer id = dir.getIDNODireccionamiento();
                        if (!idDireccionamiento.contains(id)) {
                            procesarNoDireccionamiento(
                                    dir,
                                    listaMedicamentos,
                                    listaTecnologias,
                                    listaInsumos);
                            exitosos = exitosos + 1;
                        } else {
                            for (MpNoDireccionado dire : noDireccionado) {
                                if (Objects.equals(dir.getIDNODireccionamiento(), dire.getIdNoDireccionamiento())) {
                                    // Verificar estados, si son iguales no hago nada
                                    if (dir.getEstNODireccionamiento() != (dire.getEstadoNoDireccionamiento())) {
                                        getMpPrescripcionTecnologiaWSRemoto()
                                                .cambiarEstadoNoDireccionamiento(dire.getId(), dir.getEstNODireccionamiento(), dir.getFecAnulacion());
                                        exitosos = exitosos + 1;
                                        if (exitosos % 100 == 0) {
                                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                                            consumo.setObservacion("inicio insercion BD");
                                            consumo.setRegistrosExitosos(exitosos);
                                            try {
                                                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                                            } catch (Exception ex) {
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                            upd = upd + 1;
                        }

                        if (exitosos % 100 == 0) {
                            consumo.setEstado(MpConsumo.ESTADO_CONSUMO_GUARDANDO);
                            consumo.setObservacion("inicio insercion BD");
                            consumo.setRegistrosExitosos(exitosos);
                            try {
                                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
                            } catch (Exception ex) {
                            }
                        }
                    } catch (Exception ex) {
                        mpFallo.setEstado(MpConsumoFallo.ESTADO_CONSUMOFALLO_FALLIDO);
                        mpFallo.setMpConsumoId(consumo);
                        mpFallo.setDescripcion(ex.getMessage());
                        mpFallo.setFechaProceso(fechaInicioServicio);
                        mpFallo.setFechaHoraFallo(new Date());
                        Gson gson = new Gson();
                        String jsonString = gson.toJson(dir);
                        mpFallo.setJson(jsonString);
                        try {
                            getConsumoFallosWsRemoto().insertarConsumoFallo(mpFallo);
                        } catch (Exception exe) {
                        }
                    }
                }
                Integer valor = exitosos + upd;
                if (valor == respuesta.size()) {
                    bean.addMensaje("Proceso de no direccionamientos exitoso :" + valor + " de " + respuesta.size());
                }
                consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                consumo.setObservacion(exitosos + " - Exitosos " + upd + " - Actuañizados");
                consumo.setRegistrosExitosos(exitosos + upd);
            } else {
                if (!errorConsumo.toString().isEmpty()) {
                    try {
                        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO_ERROR);
                        consumo.setObservacion(errorConsumo.toString());
                        bean.addError("La sincronización no direccionamientos se detuvo. Inténtelo de nuevo.");
                    } catch (Exception ex) {
                    }
                } else {
                    consumo.setEstado(MpConsumo.ESTADO_CONSUMO_EXITOSO);
                    consumo.setObservacion("sin registros");
                    bean.addMensaje("No se encontraron registros para sincronizar no direccionamientos.");
                }
            }
            try {
                getPrescripcionWsRemoto().actualizarMpConsumo(consumo);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void procesarNoDireccionamiento(NoDireccionamientoBaseDTO dir,
            Map<String, MpPrescripcionMedicamento> listaMedicamentos,
            Map<String, MpPrescripcionTecnologia> listaTecnologias,
            Map<String, MpPrescripcionInsumo> listaInsumos) throws Exception {
        MpNoDireccionado noDireccionamiento = new MpNoDireccionado();
        StringBuilder error = new StringBuilder();
        Date fecha;
        try {
            noDireccionamiento.setIdNoDireccionamiento(dir.getIDNODireccionamiento());
            noDireccionamiento.setEstadoNoDireccionamiento(dir.getEstNODireccionamiento());
            if (dir.getCausaNoEntrega() != null) {
                noDireccionamiento.setCodigoNoDireccionamiento(dir.getCausaNoEntrega());
            }
            if (dir.getNoPrescripcionAsociada() != null) {
                noDireccionamiento.setNumeroPrescripcionAsociada(dir.getNoPrescripcionAsociada());
            }
            if (dir.getConTecAsociada() != null) {
                noDireccionamiento.setConTecAsociada(Integer.parseInt(dir.getConTecAsociada()));
            }
            if (dir.getFecNODireccionamiento() != null) {
                fecha = formatoF.parse(dir.getFecNODireccionamiento());
//                            noDireccionamiento.setFecNoDireccionamiento(fecha);
            }
            if (dir.getFecAnulacion() != null) {
                fecha = formatoF.parse(dir.getFecAnulacion());
                noDireccionamiento.setFechaAnulacion(fecha);
            }
            noDireccionamiento.setUsuarioCrea("mipres sincroniza");
            noDireccionamiento.setTerminalCrea("127.0.0.1");
            noDireccionamiento.setFechaHoraCrea(new Date());
            switch (dir.getTipoTec().toUpperCase()) {
                case "M":
                    MpPrescripcionMedicamento medicamento = listaMedicamentos
                            .getOrDefault(dir.getNoPrescripcion().trim() + "||"
                                    + dir.getConTec() + "||"
                                    + 1, null);
                    if (medicamento != null) {
                        noDireccionamiento.setMpPrescripcionesId(medicamento.getMpPrescripcion());
                        noDireccionamiento.setMpPrescripcionMedicamentosId(medicamento);
                        noDireccionamiento.setTipoTecnologia(MpPrescripcion.TIPO_MEDICAMENTO);
                    } else {
                        error.append("no se encontro medicamentos: ")
                                .append(dir.getNoPrescripcion().trim()).append("||")
                                .append(dir.getConTec());
                    }
                    break;
                case "P":
                    MpPrescripcionTecnologia tecnologia = listaTecnologias
                            .getOrDefault(dir.getNoPrescripcion().trim() + "||"
                                    + dir.getConTec() + "||"
                                    + 2, null);
                    if (tecnologia != null) {
                        noDireccionamiento.setMpPrescripcionesId(tecnologia.getMpPrescripcion());
                        noDireccionamiento.setMpPrescripcionTecnologiasId(tecnologia);
                        noDireccionamiento.setTipoTecnologia(MpPrescripcion.TIPO_TECNOLOGIA);
                    } else {
                        error.append("no se encontro procedimientos: ")
                                .append(dir.getNoPrescripcion()).append("||")
                                .append(dir.getConTec());
                    }
                    break;
                case "D":
                    MpPrescripcionInsumo insumo = listaInsumos
                            .getOrDefault(dir.getNoPrescripcion().trim() + "||"
                                    + dir.getConTec() + "||"
                                    + 3, null);
                    if (insumo != null) {
                        noDireccionamiento.setMpPrescripcionesId(insumo.getMpPrescripcion());
                        noDireccionamiento.setMpPrescripcionInsumosId(insumo);
                        noDireccionamiento.setTipoTecnologia(MpPrescripcion.TIPO_DISPOSITIVO_MEDICO);
                    } else {
                        error.append("no se encontro dispositivos: ")
                                .append(dir.getNoPrescripcion()).append("||")
                                .append(dir.getConTec());
                    }
                    break;
                case "N":
                    MpPrescripcionMedicamento nut = listaMedicamentos
                            .getOrDefault(dir.getNoPrescripcion().trim() + "||"
                                    + dir.getConTec() + "||"
                                    + 4, null);
                    if (nut != null) {
                        noDireccionamiento.setMpPrescripcionesId(nut.getMpPrescripcion());
                        noDireccionamiento.setMpPrescripcionMedicamentosId(nut);
                        noDireccionamiento.setTipoTecnologia(MpPrescripcion.TIPO_PRODUCTO_NUTRICIONAL);
                    } else {
                        error.append("no se encontro productosnutricionales: ")
                                .append(dir.getNoPrescripcion()).append("||")
                                .append(dir.getConTec());
                    }
                    break;
                case "S":
                    MpPrescripcionInsumo sup = listaInsumos
                            .getOrDefault(dir.getNoPrescripcion().trim() + "||"
                                    + dir.getConTec() + "||"
                                    + 5, null);
                    if (sup != null) {
                        noDireccionamiento.setMpPrescripcionesId(sup.getMpPrescripcion());
                        noDireccionamiento.setMpPrescripcionInsumosId(sup);
                        noDireccionamiento.setTipoTecnologia(MpPrescripcion.TIPO_SERVICIO_COMPLEMENTARIO);
                    } else {
                        error.append("no se encontro serviciosComplementarios: ")
                                .append(dir.getNoPrescripcion()).append("||")
                                .append(dir.getConTec());
                    }
                    break;
                default:
                    error.append("Tipo de tecnología no soportado: ")
                            .append(dir.getTipoTec());
            }

            if (error.toString().isEmpty()) {
                try {
                    getMpDireccionamientoWSRemoto()
                            .insertarMpNoDireccionamiento(noDireccionamiento);
                } catch (Exception ex) {
                    throw new Exception("Error insertarMpDireccionamiento: " + ex.getCause());
                }
            } else {
                throw new Exception(error.toString());
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public void sincronizarDireccionamientoWs(String prescripcion, int id, boolean isSub) {

    }

    private MpConsumo inicializarConsumo(String servicio) throws Exception {
        MpConsumo consumo = new MpConsumo();
        consumo.setPeriodo(MpPrescripcion.FECHA_INICIO_SERVICIO_SUB_POR_DEFECTO);
        consumo.setEstado(MpConsumo.ESTADO_CONSUMO_CONSULTANDO);
        consumo.setFechaHoraInicio(new Date());
        consumo.setObservacion("inicio servicio");
        consumo.setServicio(servicio);
        consumo.setRegistros(0);
        return consumo;
    }

    @Override
    public void anularNoDireccionamiento(MipresBean bean) {
        try {
            if (getMpPrescripcionDetalleRemoto().anularNoDireccionamiento(bean.getObjeto().getIdPrescripcion(), bean.getObjeto().getTipoTecnologiaItem(),
                    bean.getObjeto().getIdItem(), bean.getConexion().getUsuario().getUsuarioNombre(), bean.getConexion().getIp(), MpNoDireccionado.ESTADO_SOLICITUD_ANULACION)) {
                bean.addMensaje("Se planeo la anulación con exito, por favor esperar a que se finalice el envio");
                MpPrescripcionHistorico historico = new MpPrescripcionHistorico();
                historico.setMpPrescripcion(new MpPrescripcion(bean.getObjeto().getIdPrescripcion()));
                historico.setTipoTecnologia(bean.getObjeto().getTipoTecnologiaItem());
                historico.setIdPrescripcionTecnologia(bean.getObjeto().getIdItem());
                historico.setEstado(19); //anulacion NO direccionamiento
                bean.auditoriaGuardar(historico);
                getMpPrescripcionDetalleRemoto().inicioHistorico(historico);
            } else {
                bean.addError("Hubo un fallo al anular un no direccionamiento");
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al anular no direccionamiento, favor contactar al administrador");
        }
    }

    @Override
    public String consultarCntUnionTemp(Integer id) {
        String resul = "";
        try {
            resul = getMpPrescripcionDetalleRemoto().consultarCntUnionTemp(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resul;
    }
}
