package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class JrAco extends Auditoria {
    
    private Integer id;
    private int maeTipoSolicitudId;
    private String maeTipoSolicitudCodigo;
    private String maeTipoSolicitudValor;
    private Date fechaSolicitud;
    private int maeAreaId;
    private String alcance;
    private String maeAreaCodigo;
    private String maeAreaValor;
    private int maeSubgerenciaId;
    private String maeSubgerenciaCodigo;
    private String maeSubgerenciaValor;
    private int maeEstadoId;
    private String maeEstadoCodigo;
    private String maeEstadoValor;
    private String objeto;
    private String plazoEjecucion;
    private String especificacionTecnica;
    private String codificacionBienesServicios;
    private String contratista;
    private String contratante;
    private String cumplimientoRequisitoTecnico;
    private String cumplimientoRequisitoJuridico;
    private String cumplimientoRequisitoFinanciero;
    private String menorValorFinanciero;
    private BigDecimal solicitudCotizacion;
    private String consultaBdEspecializada;
    private String analisisConsumoPrecioHistorico;
    private String analisisEconomico;
    private BigDecimal contratoSalud;
    private String modalidadContrato;
    private String incluirNotaTecnica;
    private String riesgoEconomico;
    private String responsabilidadCivilExtracontractualClinicaHospital;
    private String cumplimiento;
    private String salarioPrestacion;
    private String supervisionInterventoria;    
    private List<JrAcoAdjunto> listaAcoAdjuntos;
    private List<JrGestion> listaGestiones;
//    private List<JrContrato> listaContratos;

    public JrAco() {
    }

    public JrAco(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoSolicitudId() {
        return maeTipoSolicitudId;
    }

    public void setMaeTipoSolicitudId(int maeTipoSolicitudId) {
        this.maeTipoSolicitudId = maeTipoSolicitudId;
    }

    public String getMaeTipoSolicitudCodigo() {
        return maeTipoSolicitudCodigo;
    }

    public void setMaeTipoSolicitudCodigo(String maeTipoSolicitudCodigo) {
        this.maeTipoSolicitudCodigo = maeTipoSolicitudCodigo;
    }

    public String getMaeTipoSolicitudValor() {
        return maeTipoSolicitudValor;
    }

    public void setMaeTipoSolicitudValor(String maeTipoSolicitudValor) {
        this.maeTipoSolicitudValor = maeTipoSolicitudValor;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getMaeAreaId() {
        return maeAreaId;
    }

    public void setMaeAreaId(int maeAreaId) {
        this.maeAreaId = maeAreaId;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getMaeAreaCodigo() {
        return maeAreaCodigo;
    }

    public void setMaeAreaCodigo(String maeAreaCodigo) {
        this.maeAreaCodigo = maeAreaCodigo;
    }

    public String getMaeAreaValor() {
        return maeAreaValor;
    }

    public void setMaeAreaValor(String maeAreaValor) {
        this.maeAreaValor = maeAreaValor;
    }

    public int getMaeSubgerenciaId() {
        return maeSubgerenciaId;
    }

    public void setMaeSubgerenciaId(int maeSubgerenciaId) {
        this.maeSubgerenciaId = maeSubgerenciaId;
    }

    public String getMaeSubgerenciaCodigo() {
        return maeSubgerenciaCodigo;
    }

    public void setMaeSubgerenciaCodigo(String maeSubgerenciaCodigo) {
        this.maeSubgerenciaCodigo = maeSubgerenciaCodigo;
    }

    public String getMaeSubgerenciaValor() {
        return maeSubgerenciaValor;
    }

    public void setMaeSubgerenciaValor(String maeSubgerenciaValor) {
        this.maeSubgerenciaValor = maeSubgerenciaValor;
    }

    public int getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(int maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getPlazoEjecucion() {
        return plazoEjecucion;
    }

    public void setPlazoEjecucion(String plazoEjecucion) {
        this.plazoEjecucion = plazoEjecucion;
    }

    public String getEspecificacionTecnica() {
        return especificacionTecnica;
    }

    public void setEspecificacionTecnica(String especificacionTecnica) {
        this.especificacionTecnica = especificacionTecnica;
    }

    public String getCodificacionBienesServicios() {
        return codificacionBienesServicios;
    }

    public void setCodificacionBienesServicios(String codificacionBienesServicios) {
        this.codificacionBienesServicios = codificacionBienesServicios;
    }

    public String getContratista() {
        return contratista;
    }

    public void setContratista(String contratista) {
        this.contratista = contratista;
    }

    public String getContratante() {
        return contratante;
    }

    public void setContratante(String contratante) {
        this.contratante = contratante;
    }

    public String getCumplimientoRequisitoTecnico() {
        return cumplimientoRequisitoTecnico;
    }

    public void setCumplimientoRequisitoTecnico(String cumplimientoRequisitoTecnico) {
        this.cumplimientoRequisitoTecnico = cumplimientoRequisitoTecnico;
    }

    public String getCumplimientoRequisitoJuridico() {
        return cumplimientoRequisitoJuridico;
    }

    public void setCumplimientoRequisitoJuridico(String cumplimientoRequisitoJuridico) {
        this.cumplimientoRequisitoJuridico = cumplimientoRequisitoJuridico;
    }

    public String getCumplimientoRequisitoFinanciero() {
        return cumplimientoRequisitoFinanciero;
    }

    public void setCumplimientoRequisitoFinanciero(String cumplimientoRequisitoFinanciero) {
        this.cumplimientoRequisitoFinanciero = cumplimientoRequisitoFinanciero;
    }

    public String getMenorValorFinanciero() {
        return menorValorFinanciero;
    }

    public void setMenorValorFinanciero(String menorValorFinanciero) {
        this.menorValorFinanciero = menorValorFinanciero;
    }

    public BigDecimal getSolicitudCotizacion() {
        return solicitudCotizacion;
    }

    public void setSolicitudCotizacion(BigDecimal solicitudCotizacion) {
        this.solicitudCotizacion = solicitudCotizacion;
    }

    public String getConsultaBdEspecializada() {
        return consultaBdEspecializada;
    }

    public void setConsultaBdEspecializada(String consultaBdEspecializada) {
        this.consultaBdEspecializada = consultaBdEspecializada;
    }

    public String getAnalisisConsumoPrecioHistorico() {
        return analisisConsumoPrecioHistorico;
    }

    public void setAnalisisConsumoPrecioHistorico(String analisisConsumoPrecioHistorico) {
        this.analisisConsumoPrecioHistorico = analisisConsumoPrecioHistorico;
    }

    public String getAnalisisEconomico() {
        return analisisEconomico;
    }

    public void setAnalisisEconomico(String analisisEconomico) {
        this.analisisEconomico = analisisEconomico;
    }

    public BigDecimal getContratoSalud() {
        return contratoSalud;
    }

    public void setContratoSalud(BigDecimal contratoSalud) {
        this.contratoSalud = contratoSalud;
    }

    public String getModalidadContrato() {
        return modalidadContrato;
    }

    public void setModalidadContrato(String modalidadContrato) {
        this.modalidadContrato = modalidadContrato;
    }

    public String getIncluirNotaTecnica() {
        return incluirNotaTecnica;
    }

    public void setIncluirNotaTecnica(String incluirNotaTecnica) {
        this.incluirNotaTecnica = incluirNotaTecnica;
    }

    public String getRiesgoEconomico() {
        return riesgoEconomico;
    }

    public void setRiesgoEconomico(String riesgoEconomico) {
        this.riesgoEconomico = riesgoEconomico;
    }

    public String getResponsabilidadCivilExtracontractualClinicaHospital() {
        return responsabilidadCivilExtracontractualClinicaHospital;
    }

    public void setResponsabilidadCivilExtracontractualClinicaHospital(String responsabilidadCivilExtracontractualClinicaHospital) {
        this.responsabilidadCivilExtracontractualClinicaHospital = responsabilidadCivilExtracontractualClinicaHospital;
    }

    public String getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(String cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public String getSalarioPrestacion() {
        return salarioPrestacion;
    }

    public void setSalarioPrestacion(String salarioPrestacion) {
        this.salarioPrestacion = salarioPrestacion;
    }

    public String getSupervisionInterventoria() {
        return supervisionInterventoria;
    }

    public void setSupervisionInterventoria(String supervisionInterventoria) {
        this.supervisionInterventoria = supervisionInterventoria;
    }

    public List<JrAcoAdjunto> getListaAcoAdjuntos() {
        return listaAcoAdjuntos;
    }

    public void setListaAcoAdjuntos(List<JrAcoAdjunto> listaAcoAdjuntos) {
        this.listaAcoAdjuntos = listaAcoAdjuntos;
    }  

    public List<JrGestion> getListaGestiones() {
        return listaGestiones;
    }

    public void setListaGestiones(List<JrGestion> listaGestiones) {
        this.listaGestiones = listaGestiones;
    }
    
    
}
