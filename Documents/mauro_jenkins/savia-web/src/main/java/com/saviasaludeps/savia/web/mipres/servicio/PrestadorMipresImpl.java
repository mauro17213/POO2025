package com.saviasaludeps.savia.web.mipres.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamiento;
import com.saviasaludeps.savia.dominio.mipres.MpDireccionamientoEntregado;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.dominio.mipres.ReporteDireccionamiento;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpPrescripcionDetalleRemoto;
import com.saviasaludeps.savia.web.mipres.bean.DTO.MpDetalleDTO;
import com.saviasaludeps.savia.web.mipres.bean.PrestadorMipresBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author BSGomez
 */
public class PrestadorMipresImpl extends AccionesBO implements PrestadorMipresIface {

    private MpPrescripcionDetalleRemoto getMpPrescripcionDetalleRemoto() throws Exception {
        return (MpPrescripcionDetalleRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionDetalleServicio", MpPrescripcionDetalleRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    @Override
    public void Accion(PrestadorMipresBean bean) {
        if (super.ValidarSesion(bean)) {
            bean.getObjetoPrescripcion().setEmpresa(bean.getConexion().getEmpresa());
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR: {
                    listar(bean);
                    break;
                }
                case Url.ACCION_GUARDAR: {
//                    guardar(bean);
                    break;
                }
                case Url.ACCION_CREAR: {
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
                            verH(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
//                           
                            break;
                    }
                    break;
                }
                case Url.ACCION_ADICIONAL_1: {
//                    verItem(bean);
                    break;
                }
                case Url.ACCION_ADICIONAL_2: {
//                    imprimirPdf(bean);
                    break;
                }
                default:
                    break;
            }
        }
    }

    @Override
    public void CargaInicial(PrestadorMipresBean bean) {
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

    private void listar(PrestadorMipresBean bean) {
        try {
            String numeroPrestador = null;
            numeroPrestador = (bean.getObjetoPrescripcion().getEmpresa().getNit());
            bean.getParamConsulta().setCantidadRegistros(getMpPrescripcionDetalleRemoto().consultarCantidadListaPrescripcionPrestador(bean.getParamConsulta(), numeroPrestador));
            bean.setRegistros(getMpPrescripcionDetalleRemoto().consultarListaPrescripcionPrestador(bean.getParamConsulta(), numeroPrestador));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void ver(PrestadorMipresBean bean) {
        try {
            bean.setObjetoPrescripcion(getMpPrescripcionDetalleRemoto().consultarPrescripcion(bean.getObjetoPrescripcion().getId()));
            bean.setPrescripcionListaHistoricos(new ArrayList<>());
            bean.setPrescripcionListaHistoricos(getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionPorDocumento(bean.getObjetoPrescripcion().getAsegAfiliado().getNumeroDocumento()));

            if (bean.getObjetoPrescripcion().getAsegAfiliado() != null) {
                bean.getObjetoPrescripcion().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjetoPrescripcion().getAsegAfiliado().getId()));
            } else {
                bean.getObjetoPrescripcion().setAsegAfiliado(new AsegAfiliado());
            }
            bean.setNumeroDeTutelas(getMpPrescripcionDetalleRemoto().consultarTutelas(bean.getObjetoPrescripcion().getAsegAfiliado().getId()));
            bean.setMpPrescripcionAnulada(getMpPrescripcionDetalleRemoto().consultarMpPrescripcionAnulada(bean.getObjetoPrescripcion().getId()));

            if (bean.getObjetoPrescripcion() != null
                    && bean.getObjetoPrescripcion().getSedeCodigoHabilitacion() != null
                    && !bean.getObjetoPrescripcion().getSedeCodigoHabilitacion().isBlank()) {
                bean.getObjetoPrescripcion().setCntPrestadorSede(getCntPrestadorSedeRemoto().consultarPorCodigoHabilitacion(bean.getObjetoPrescripcion().getSedeCodigoHabilitacion()));
                if (bean.getObjetoPrescripcion().getCntPrestadorSede() != null) {
                    bean.getObjetoPrescripcion().getCntPrestadorSede().setUbicacion(UbicacionSingle.getInstance().consultarPadre(bean.getObjetoPrescripcion().getCntPrestadorSede().getUbicacionId()));
//                    bean.getPrescripcion().getCntPrestadorSede().setUbicacion(getUbicacionRemoto().consultar(bean.getPrescripcion().getCntPrestadorSede().getUbicacionId()));
                }

            }
            if (bean.getObjetoPrescripcion().isRecobrante() == true) {
                bean.setPrescripcionRecobrante(getMpPrescripcionDetalleRemoto().consultarRecobrante(bean.getObjetoPrescripcion().getId()));
            }
//            bean.setListaPrescripcionDto(new ArrayList<>());

            MpDetalleDTO mpDetalleDTO = null;
            for (MpPrescripcionMedicamento medicamento : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionMedicamento(bean.getObjetoPrescripcion().getId())) {
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

            for (MpPrescripcionTecnologia tecnologia : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionTecnologia(bean.getObjetoPrescripcion().getId())) {
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

            for (MpPrescripcionInsumo insumo : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionInsumo(bean.getObjetoPrescripcion().getId())) {
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

    private void verItem(PrestadorMipresBean bean) {
        int tipoTecnologia = bean.getObjetoPrescripcion().getTipoTecnologiaItem();
        bean.setTieneDireccionamiento(false);
        bean.setTienePrincipioActivo(false);
        bean.setTieneEntregas(false);
        bean.setTieneNoDireccionamientos(false);
        switch (tipoTecnologia) {
            case 1: {
                try {
                    bean.setPrescripcionMedicamento(getMpPrescripcionDetalleRemoto().consultarPorMedicamento(bean.getObjetoPrescripcion().getIdItem()));
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
                    Logger.getLogger(MipresImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case 4: {
                try {
                    bean.setPrescripcionMedicamento(getMpPrescripcionDetalleRemoto().consultarPorMedicamento(bean.getObjetoPrescripcion().getIdItem()));
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
                    Logger.getLogger(MipresImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 2: {
                try {
                    bean.setPrescripcionTecnologia(getMpPrescripcionDetalleRemoto().consultarPorTecnologia(bean.getObjetoPrescripcion().getIdItem()));
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
                    Logger.getLogger(MipresImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 3:
            case 5: {
                try {
                    bean.setPrescripcionInsumo(getMpPrescripcionDetalleRemoto().consultarPorInsumo(bean.getObjetoPrescripcion().getIdItem()));
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

    public void verH(PrestadorMipresBean bean) {

        try {
            bean.setPrescripcionListaHistoricos(new ArrayList<>());
            bean.setPrescripcionListaHistoricos(getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionPorDocumento(bean.getObjetoPrescripcion().getAsegAfiliado().getNumeroDocumento()));

            // Obtener las tecnologías de la prescripción actual
            Set<Integer> tecnologiasActuales = new HashSet<>();

            // Agregar los tipos de tecnologías de la prescripción actual
            for (MpPrescripcionMedicamento med : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionMedicamentoH(bean.getObjetoPrescripcion().getId())) {
                tecnologiasActuales.add(med.getTipoTecnologia());
            }
            for (MpPrescripcionInsumo ins : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionInsumoH(bean.getObjetoPrescripcion().getId())) {
                tecnologiasActuales.add(ins.getTipoTecnologia());
            }
            for (MpPrescripcionTecnologia tec : getMpPrescripcionDetalleRemoto().consultarPorMpPrescripcionTecnologiaH(bean.getObjetoPrescripcion().getId())) {
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
                if (!h.getId().equals(bean.getObjetoPrescripcion().getId())) {
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

    @Override
    public void verHistorico(PrestadorMipresBean bean, int id) {
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

    public String consultarUbicacionReporte(Integer id) {
        String resul = "";
        try {
            resul = getMpPrescripcionDetalleRemoto().consultarUbicacionReporte(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resul;
    }

}
