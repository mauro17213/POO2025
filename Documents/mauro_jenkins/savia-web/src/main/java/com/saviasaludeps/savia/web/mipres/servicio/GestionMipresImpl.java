/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mipres.servicio;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionInsumo;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionMedicamento;
import com.saviasaludeps.savia.dominio.mipres.MpPrescripcionTecnologia;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UbicacionRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpEntregadaRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpInsumoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpMedicamentoRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpPrescripcionRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpProgramadaRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpSuministroRemoto;
import com.saviasaludeps.savia.negocio.mipres.MpTecnologiaRemoto;
import com.saviasaludeps.savia.web.mipres.bean.DTO.MpDetalleDTO;
import com.saviasaludeps.savia.web.mipres.bean.GestionMipresBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.ArrayList;

/**
 *
 * @author BSGomez
 */
public class GestionMipresImpl extends AccionesBO implements GestionMipresIface {

    private MpPrescripcionRemoto getMpPrescripcionRemoto() throws Exception {
        return (MpPrescripcionRemoto) RemotoEJB.getEJBRemoto("MpPrescripcionServicio", MpPrescripcionRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private UbicacionRemoto getUbicacionRemoto() throws Exception {
        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
    }

    private MpTecnologiaRemoto getMpTecnologiaRemoto() throws Exception {
        return (MpTecnologiaRemoto) RemotoEJB.getEJBRemoto("MpTecnologiaServicio", MpTecnologiaRemoto.class.getName());
    }

    private MpMedicamentoRemoto getMpMedicamentoRemoto() throws Exception {
        return (MpMedicamentoRemoto) RemotoEJB.getEJBRemoto("MpMedicamentoServicio", MpMedicamentoRemoto.class.getName());
    }

    private MpInsumoRemoto getMpInsumoRemoto() throws Exception {
        return (MpInsumoRemoto) RemotoEJB.getEJBRemoto("MpInsumoServicio", MpInsumoRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private MpProgramadaRemoto getMpPrescripcionProgramada() throws Exception {
        return (MpProgramadaRemoto) RemotoEJB.getEJBRemoto("MpProgramadaServicio", MpProgramadaRemoto.class.getName());
    }

    private MpEntregadaRemoto getMpEntregadaRemoto() throws Exception {
        return (MpEntregadaRemoto) RemotoEJB.getEJBRemoto("MpEntregadaServicio", MpEntregadaRemoto.class.getName());
    }

    private MpSuministroRemoto getMpSuministroRemoto() throws Exception {
        return (MpSuministroRemoto) RemotoEJB.getEJBRemoto("MpEntregaSuministroServicio", MpSuministroRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }
    
    @Override
    public void accion(GestionMipresBean bean) {
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
                        case Url.ACCION_LISTAR:
                            verProgramadas(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verEntregadas(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            verSuministradas(bean);
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(GestionMipresBean bean) {
        try {
            //Maestro Tipo Documento Persona
            bean.setListaTiposDocumentoPersona(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
            bean.setHashTiposDocumentoPersona(Util.convertToHash(bean.getListaTiposDocumentoPersona()));

            //Maestro Sexo(Genero)
            bean.setHashTiposGenero(Util.convertToHash(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_SEXO)));

            //Maestro Estado afiliación
            bean.setHashEstadosAfiliacion(Util.convertToHash(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION)));
            
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashMunicipios());
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void listar(GestionMipresBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getMpPrescripcionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getMpPrescripcionRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void ver(GestionMipresBean bean) {
        try {
            bean.setObjeto(getMpPrescripcionRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setAsegAfiliado(getAfiliadoRemoto().consultar(bean.getObjeto().getAsegAfiliado().getId()));
            if (bean.getObjeto() != null
                    && bean.getObjeto().getSedeCodigoHabilitacion() != null
                    && !bean.getObjeto().getSedeCodigoHabilitacion().isBlank()) {
                bean.getObjeto().setCntPrestadorSede(getCntPrestadorSedeRemoto().consultarPorCodigoHabilitacion(bean.getObjeto().getSedeCodigoHabilitacion()));
                if (bean.getObjeto().getCntPrestadorSede() != null) {
                    bean.getObjeto().getCntPrestadorSede().setUbicacion(getUbicacionRemoto().consultar(bean.getObjeto().getCntPrestadorSede().getUbicacionId()));
                }

            }
            if(bean.getObjeto().isRecobrante() == true){
                bean.setObjetoRecobrante(getMpPrescripcionRemoto().consultarRecobrante(bean.getObjeto().getId()));
            }
            bean.setListaMpDetalleDTO(new ArrayList<>());
            MpDetalleDTO mpDetalleDTO = null;
            for (MpPrescripcionMedicamento medicamento : getMpMedicamentoRemoto().consultarPorMpPrescripcion(bean.getObjeto().getId())) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(medicamento.getId());
                mpDetalleDTO.setCantidadTotal(medicamento.getCantidadTotalEntrega() != null ? medicamento.getCantidadTotalEntrega().intValue() : null);
                mpDetalleDTO.setCantidadTotalPrescrita(medicamento.getCantidadTotalFormulada() != null ? medicamento.getCantidadTotalFormulada().toString() : null);

                mpDetalleDTO.setCodigoTecnologia("");
                mpDetalleDTO.setCodigoTecnologiaAvalEps("");
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(medicamento.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(medicamento.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(medicamento.getDuracionTratamiento());
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
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoMedicamento(medicamento.getTipoMedicamento() == null ? 0 : medicamento.getTipoMedicamento()));
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(medicamento.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(medicamento.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(medicamento.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(medicamento.getCantidadTratamiento());
                bean.getListaMpDetalleDTO().add(mpDetalleDTO);
            }

            for (MpPrescripcionTecnologia tecnologia : getMpTecnologiaRemoto().consultarPorMpPrescripcion(bean.getObjeto().getId())) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(tecnologia.getId());
                mpDetalleDTO.setCantidadTotal(tecnologia.getCantidadTotal());
                mpDetalleDTO.setCantidadTotalPrescrita(tecnologia.getCantidadFormulada() != null ? tecnologia.getCantidadFormulada().toString() : null);
                mpDetalleDTO.setCodigoFormaFarmaceutica(tecnologia.getMaTecnologiaCodigo());
                mpDetalleDTO.setCodigoTecnologia(tecnologia.getMaTecnologiaCodigo());
                mpDetalleDTO.setCodigoTecnologiaAvalEps(tecnologia.getMaTecnologiaCodigo());
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(tecnologia.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(tecnologia.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(tecnologia.getCantidadDuracionTratamiento());
                mpDetalleDTO.setEstado(bean.obtenerEstado(tecnologia.getEstado()));
                mpDetalleDTO.setFechaDireccionamiento(tecnologia.getFechaDireccionamiento());
                mpDetalleDTO.setPendientes(tecnologia.getPendientes());
                mpDetalleDTO.setNombreTecnologia(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNombreTecnologiaAvalEps(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNombreTecnologiaPrescripta(tecnologia.getMaTecnologiaValor());
                mpDetalleDTO.setNumeroEntregas(tecnologia.getEntregados());
                mpDetalleDTO.setNumeroTransaccion(tecnologia.getIdTransaccion());
                mpDetalleDTO.setTipoMedicamento(bean.obtenerTipoTecnologia(tecnologia.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(tecnologia.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(tecnologia.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(tecnologia.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(tecnologia.getCantidadTotal());
                bean.getListaMpDetalleDTO().add(mpDetalleDTO);
            }

            for (MpPrescripcionInsumo insumo : getMpInsumoRemoto().consultarPorMpPrescripcion(bean.getObjeto().getId())) {
                mpDetalleDTO = new MpDetalleDTO();
                mpDetalleDTO.setId(insumo.getId());
                mpDetalleDTO.setCantidadTotal(insumo.getCantidad() != null ? Integer.parseInt(insumo.getCantidad()) : null);
                mpDetalleDTO.setCantidadTotalPrescrita(insumo.getCantidadFormulada());
                mpDetalleDTO.setCodigoTecnologia(insumo.getCodigoDispositivo());
                mpDetalleDTO.setCodigoTecnologiaAvalEps(insumo.getCodigoDispositivo());
                mpDetalleDTO.setConceptoEvaluacion("");
                mpDetalleDTO.setConceptoJuntaProfesional(bean.obtenerEstadoJuntaProfesional(insumo.getEstadoJuntaProfesionales()));
                mpDetalleDTO.setConsecutivo(insumo.getConsecutivoOrden());
                mpDetalleDTO.setDuracionTratamientoOrdenado(insumo.getDuracionTratamiento());
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
                mpDetalleDTO.setTipoTecnologia(bean.obtenerTipoTecnologia(insumo.getTipoTecnologia()));
                mpDetalleDTO.setTipoTecnologiaId(insumo.getTipoTecnologia());
                mpDetalleDTO.setTipoPrestacion(bean.obtenerTipoPrestacion(insumo.getTipoPrestacion()));
                mpDetalleDTO.setUnidadesAprobadasPeriodo(insumo.getCantidadTotalEntrega().intValue());
                bean.getListaMpDetalleDTO().add(mpDetalleDTO);
            }

        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void verProgramadas(GestionMipresBean bean) {
        try {
            if (bean.getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_MEDICAMENTO1 || bean.getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES1) {
                bean.setObjetoMedicamento(getMpMedicamentoRemoto().consultar(bean.getObjetoDto().getId()));
            } else if (bean.getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS1) {
                bean.setObjetoTecnologia(getMpTecnologiaRemoto().consultar(bean.getObjetoDto().getId()));
            } else if (bean.getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_DISPOSITIVO1 || bean.getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO1) {
                bean.setObjetoInsumo(getMpInsumoRemoto().consultar(bean.getObjetoDto().getId()));
            }

            bean.setListaMpPrescripcionProgramadas(getMpPrescripcionProgramada().consultarPorTipoTecnologia(bean.getObjetoDto().getId(), bean.getObjetoDto().getTipoTecnologiaId()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void verEntregadas(GestionMipresBean bean) {
        try {
            if (bean.getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_MEDICAMENTO1 || bean.getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES1) {
                bean.setListaMpPrescripcionEntregadas(getMpEntregadaRemoto().consultarListaPorMedicamento(bean.getObjetoDto().getId()));
            } else if (bean.getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS1) {
                bean.setListaMpPrescripcionEntregadas(getMpEntregadaRemoto().consultarListaPorTecnologia(bean.getObjetoDto().getId()));
            } else if (bean.getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_DISPOSITIVO1 || bean.getObjetoDto().getTipoTecnologiaId() == MpDetalleDTO.ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO1) {
                bean.setListaMpPrescripcionEntregadas(getMpEntregadaRemoto().consultarListaPorInsumo(bean.getObjetoDto().getId()));
            }
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

    private void verSuministradas(GestionMipresBean bean) {
        try {
            bean.setListaEntregaSuministros(getMpSuministroRemoto().consultarListaPorEntrega(bean.getObjetoEntrega().getId()));
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }

}
