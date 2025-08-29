/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NEXOS
 */
public class AuNoSolicitudCargaDetalle extends Auditoria{
    
    private Integer id;
    private AuNoSolicitud auNoSolicitudId;
    private AuNoSolicitudCarga auNoSolicitudCargasId;
    private String numeroNoSolicitud;
    private String consecutivoOrden;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String maeTipoDocumentoTipo;
    private String documentoAfiliado;
    private Integer maeAmbitoAtencionId;
    private String maeAmbitoAtencionCodigo;
    private String maeAmbitoAtencionValor;
    private String maeAmbitoAtencionTipo;
    private Date fechaOrdenMedica;
    private int tipoFormula;
    private boolean tutela;
    private Integer maServicioSolicitadoId;
    private String maServicioSolicitadoCodigo;
    private String maServicioSolicitadoValor;
    private String codigoRepsIpsSolicita;
    private Integer maeTipoDocumentoProfesionalId;
    private String maeTipoDocumentoProfesionalCodigo;
    private String maeTipoDocumentoProfesionalValor;
    private String maeTipoDocumentoProfesionalTipo;
    private String documentoProfesional;
    private Integer maEspecialidadId;
    private String maEspecialidadCodigo;
    private String maEspecialidadValor;
    private String codigoRepsIpsEntrega;
    private Integer maDiagnosticosId;
    private String maDiagnosticosCodigo;
    private String maDiagnosticosValor;
    private boolean principal;
    private boolean altoCosto;
    private Integer maServicioHabilitadoId;
    private String maServicioHabilitadoCodigo;
    private String maServicioHabilitadoValor;
    private Integer tipoTecnologia;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer maMedicamentoId;
    private String maMedicamentoCodigo;
    private String maMedicamentoValor;
    private Integer cantidadSolicitada;
    private String dosis;
    private Integer frecuencia;
    private String tipofrecuencia;
    private String viaAdministracion;
    private String justificacionClinica;
    private int fila;
    private String consecutivo;
    private Integer duracionTratamiento;
    private int numEntregas;
    private String valorUnitario;
    private boolean pbs;
    private int maeMotivoSinAutorizacionId;
    private String maeMotivoSinAutorizacionCodigo;
    private String maeMotivoSinAutorizacionValor;
    private String maeMotivoSinAutorizacionTipo;
    
    //Listas
    private List<AuNoSolicitudCargaSuceso> auNoSolicitudCargaSucesosList;
   
    // variable auxiliares
    private boolean anexado;
    private boolean error;
    
    public AuNoSolicitudCargaDetalle(){
        
    }
    
    public AuNoSolicitudCargaDetalle(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuNoSolicitud getAuNoSolicitudId() {
        return auNoSolicitudId;
    }

    public void setAuNoSolicitudId(AuNoSolicitud auNoSolicitudId) {
        this.auNoSolicitudId = auNoSolicitudId;
    }

    public AuNoSolicitudCarga getAuNoSolicitudCargasId() {
        return auNoSolicitudCargasId;
    }

    public void setAuNoSolicitudCargasId(AuNoSolicitudCarga auNoSolicitudCargasId) {
        this.auNoSolicitudCargasId = auNoSolicitudCargasId;
    }

    public String getNumeroNoSolicitud() {
        return numeroNoSolicitud;
    }

    public void setNumeroNoSolicitud(String numeroNoSolicitud) {
        this.numeroNoSolicitud = numeroNoSolicitud;
    }

    public String getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(String consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public int getNumEntregas() {
        return numEntregas;
    }

    public void setNumEntregas(int numEntregas) {
        this.numEntregas = numEntregas;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public boolean isPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getMaeTipoDocumentoTipo() {
        return maeTipoDocumentoTipo;
    }

    public void setMaeTipoDocumentoTipo(String maeTipoDocumentoTipo) {
        this.maeTipoDocumentoTipo = maeTipoDocumentoTipo;
    }

    public String getDocumentoAfiliado() {
        return documentoAfiliado;
    }

    public void setDocumentoAfiliado(String documentoAfiliado) {
        this.documentoAfiliado = documentoAfiliado;
    }

    public Integer getMaeAmbitoAtencionId() {
        return maeAmbitoAtencionId;
    }

    public void setMaeAmbitoAtencionId(Integer maeAmbitoAtencionId) {
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
    }

    public String getMaeAmbitoAtencionCodigo() {
        return maeAmbitoAtencionCodigo;
    }

    public void setMaeAmbitoAtencionCodigo(String maeAmbitoAtencionCodigo) {
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
    }

    public String getMaeAmbitoAtencionValor() {
        return maeAmbitoAtencionValor;
    }

    public void setMaeAmbitoAtencionValor(String maeAmbitoAtencionValor) {
        this.maeAmbitoAtencionValor = maeAmbitoAtencionValor;
    }

    public String getMaeAmbitoAtencionTipo() {
        return maeAmbitoAtencionTipo;
    }

    public void setMaeAmbitoAtencionTipo(String maeAmbitoAtencionTipo) {
        this.maeAmbitoAtencionTipo = maeAmbitoAtencionTipo;
    }

    public Date getFechaOrdenMedica() {
        return fechaOrdenMedica;
    }

    public void setFechaOrdenMedica(Date fechaOrdenMedica) {
        this.fechaOrdenMedica = fechaOrdenMedica;
    }

    public int getTipoFormula() {
        return tipoFormula;
    }

    public void setTipoFormula(int tipoFormula) {
        this.tipoFormula = tipoFormula;
    }

    public boolean isTutela() {
        return tutela;
    }

    public void setTutela(boolean tutela) {
        this.tutela = tutela;
    }

    public Integer getMaServicioSolicitadoId() {
        return maServicioSolicitadoId;
    }

    public void setMaServicioSolicitadoId(Integer maServicioSolicitadoId) {
        this.maServicioSolicitadoId = maServicioSolicitadoId;
    }

    public String getMaServicioSolicitadoCodigo() {
        return maServicioSolicitadoCodigo;
    }

    public void setMaServicioSolicitadoCodigo(String maServicioSolicitadoCodigo) {
        this.maServicioSolicitadoCodigo = maServicioSolicitadoCodigo;
    }

    public String getMaServicioSolicitadoValor() {
        return maServicioSolicitadoValor;
    }

    public void setMaServicioSolicitadoValor(String maServicioSolicitadoValor) {
        this.maServicioSolicitadoValor = maServicioSolicitadoValor;
    }

    public String getCodigoRepsIpsSolicita() {
        return codigoRepsIpsSolicita;
    }

    public void setCodigoRepsIpsSolicita(String codigoRepsIpsSolicita) {
        this.codigoRepsIpsSolicita = codigoRepsIpsSolicita;
    }

    public Integer getMaeTipoDocumentoProfesionalId() {
        return maeTipoDocumentoProfesionalId;
    }

    public void setMaeTipoDocumentoProfesionalId(Integer maeTipoDocumentoProfesionalId) {
        this.maeTipoDocumentoProfesionalId = maeTipoDocumentoProfesionalId;
    }

    public String getMaeTipoDocumentoProfesionalCodigo() {
        return maeTipoDocumentoProfesionalCodigo;
    }

    public void setMaeTipoDocumentoProfesionalCodigo(String maeTipoDocumentoProfesionalCodigo) {
        this.maeTipoDocumentoProfesionalCodigo = maeTipoDocumentoProfesionalCodigo;
    }

    public String getMaeTipoDocumentoProfesionalValor() {
        return maeTipoDocumentoProfesionalValor;
    }

    public void setMaeTipoDocumentoProfesionalValor(String maeTipoDocumentoProfesionalValor) {
        this.maeTipoDocumentoProfesionalValor = maeTipoDocumentoProfesionalValor;
    }

    public String getMaeTipoDocumentoProfesionalTipo() {
        return maeTipoDocumentoProfesionalTipo;
    }

    public void setMaeTipoDocumentoProfesionalTipo(String maeTipoDocumentoProfesionalTipo) {
        this.maeTipoDocumentoProfesionalTipo = maeTipoDocumentoProfesionalTipo;
    }

    public String getDocumentoProfesional() {
        return documentoProfesional;
    }

    public void setDocumentoProfesional(String documentoProfesional) {
        this.documentoProfesional = documentoProfesional;
    }

    public Integer getMaEspecialidadId() {
        return maEspecialidadId;
    }

    public void setMaEspecialidadId(Integer maEspecialidadId) {
        this.maEspecialidadId = maEspecialidadId;
    }

    public String getMaEspecialidadCodigo() {
        return maEspecialidadCodigo;
    }

    public void setMaEspecialidadCodigo(String maEspecialidadCodigo) {
        this.maEspecialidadCodigo = maEspecialidadCodigo;
    }

    public String getMaEspecialidadValor() {
        return maEspecialidadValor;
    }

    public void setMaEspecialidadValor(String maEspecialidadValor) {
        this.maEspecialidadValor = maEspecialidadValor;
    }

    public String getCodigoRepsIpsEntrega() {
        return codigoRepsIpsEntrega;
    }

    public void setCodigoRepsIpsEntrega(String codigoRepsIpsEntrega) {
        this.codigoRepsIpsEntrega = codigoRepsIpsEntrega;
    }

    public Integer getMaDiagnosticosId() {
        return maDiagnosticosId;
    }

    public void setMaDiagnosticosId(Integer maDiagnosticosId) {
        this.maDiagnosticosId = maDiagnosticosId;
    }

    public String getMaDiagnosticosCodigo() {
        return maDiagnosticosCodigo;
    }

    public void setMaDiagnosticosCodigo(String maDiagnosticosCodigo) {
        this.maDiagnosticosCodigo = maDiagnosticosCodigo;
    }

    public String getMaDiagnosticosValor() {
        return maDiagnosticosValor;
    }

    public void setMaDiagnosticosValor(String maDiagnosticosValor) {
        this.maDiagnosticosValor = maDiagnosticosValor;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public boolean isAltoCosto() {
        return altoCosto;
    }

    public void setAltoCosto(boolean altoCosto) {
        this.altoCosto = altoCosto;
    }

    public Integer getMaServicioHabilitadoId() {
        return maServicioHabilitadoId;
    }

    public void setMaServicioHabilitadoId(Integer maServicioHabilitadoId) {
        this.maServicioHabilitadoId = maServicioHabilitadoId;
    }

    public String getMaServicioHabilitadoCodigo() {
        return maServicioHabilitadoCodigo;
    }

    public void setMaServicioHabilitadoCodigo(String maServicioHabilitadoCodigo) {
        this.maServicioHabilitadoCodigo = maServicioHabilitadoCodigo;
    }

    public String getMaServicioHabilitadoValor() {
        return maServicioHabilitadoValor;
    }

    public void setMaServicioHabilitadoValor(String maServicioHabilitadoValor) {
        this.maServicioHabilitadoValor = maServicioHabilitadoValor;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(Integer maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getTipofrecuencia() {
        return tipofrecuencia;
    }

    public void setTipofrecuencia(String tipofrecuencia) {
        this.tipofrecuencia = tipofrecuencia;
    }

    public String getViaAdministracion() {
        return viaAdministracion;
    }

    public void setViaAdministracion(String viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }

    public String getJustificacionClinica() {
        return justificacionClinica;
    }

    public void setJustificacionClinica(String justificacionClinica) {
        this.justificacionClinica = justificacionClinica;
    }

    public int getMaeMotivoSinAutorizacionId() {
        return maeMotivoSinAutorizacionId;
    }

    public void setMaeMotivoSinAutorizacionId(int maeMotivoSinAutorizacionId) {
        this.maeMotivoSinAutorizacionId = maeMotivoSinAutorizacionId;
    }

    public String getMaeMotivoSinAutorizacionCodigo() {
        return maeMotivoSinAutorizacionCodigo;
    }

    public void setMaeMotivoSinAutorizacionCodigo(String maeMotivoSinAutorizacionCodigo) {
        this.maeMotivoSinAutorizacionCodigo = maeMotivoSinAutorizacionCodigo;
    }

    public String getMaeMotivoSinAutorizacionValor() {
        return maeMotivoSinAutorizacionValor;
    }

    public void setMaeMotivoSinAutorizacionValor(String maeMotivoSinAutorizacionValor) {
        this.maeMotivoSinAutorizacionValor = maeMotivoSinAutorizacionValor;
    }

    public String getMaeMotivoSinAutorizacionTipo() {
        return maeMotivoSinAutorizacionTipo;
    }

    public void setMaeMotivoSinAutorizacionTipo(String maeMotivoSinAutorizacionTipo) {
        this.maeMotivoSinAutorizacionTipo = maeMotivoSinAutorizacionTipo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public List<AuNoSolicitudCargaSuceso> getAuNoSolicitudCargaSucesosList() {
        return auNoSolicitudCargaSucesosList;
    }

    public void setAuNoSolicitudCargaSucesosList(List<AuNoSolicitudCargaSuceso> auNoSolicitudCargaSucesosList) {
        this.auNoSolicitudCargaSucesosList = auNoSolicitudCargaSucesosList;
    }

    public boolean getAnexado() {
        return anexado;
    }

    public void setAnexado(boolean anexado) {
        this.anexado = anexado;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "AuNoSolicitudCargaDetalle{" + "id=" + id + ", auNoSolicitudId=" + auNoSolicitudId + ", auNoSolicitudCargasId=" + auNoSolicitudCargasId + ", numeroNoSolicitud=" + numeroNoSolicitud + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", maeTipoDocumentoTipo=" + maeTipoDocumentoTipo + ", documentoAfiliado=" + documentoAfiliado + ", maeAmbitoAtencionId=" + maeAmbitoAtencionId + ", maeAmbitoAtencionCodigo=" + maeAmbitoAtencionCodigo + ", maeAmbitoAtencionValor=" + maeAmbitoAtencionValor + ", maeAmbitoAtencionTipo=" + maeAmbitoAtencionTipo + ", fechaOrdenMedica=" + fechaOrdenMedica + ", maServicioSolicitadoId=" + maServicioSolicitadoId + ", maServicioSolicitadoCodigo=" + maServicioSolicitadoCodigo + ", maServicioSolicitadoValor=" + maServicioSolicitadoValor + ", codigoRepsIpsSolicita=" + codigoRepsIpsSolicita + ", maeTipoDocumentoProfesionalId=" + maeTipoDocumentoProfesionalId + ", maeTipoDocumentoProfesionalCodigo=" + maeTipoDocumentoProfesionalCodigo + ", maeTipoDocumentoProfesionalValor=" + maeTipoDocumentoProfesionalValor + ", maeTipoDocumentoProfesionalTipo=" + maeTipoDocumentoProfesionalTipo + ", documentoProfesional=" + documentoProfesional + ", maEspecialidadId=" + maEspecialidadId + ", maEspecialidadCodigo=" + maEspecialidadCodigo + ", maEspecialidadValor=" + maEspecialidadValor + ", codigoRepsIpsEntrega=" + codigoRepsIpsEntrega + ", maDiagnosticosId=" + maDiagnosticosId + ", maDiagnosticosCodigo=" + maDiagnosticosCodigo + ", maDiagnosticosValor=" + maDiagnosticosValor + ", principal=" + principal + ", maServicioHabilitadoId=" + maServicioHabilitadoId + ", maServicioHabilitadoCodigo=" + maServicioHabilitadoCodigo + ", maServicioHabilitadoValor=" + maServicioHabilitadoValor + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", maMedicamentoId=" + maMedicamentoId + ", maMedicamentoCodigo=" + maMedicamentoCodigo + ", maMedicamentoValor=" + maMedicamentoValor + ", cantidadSolicitada=" + cantidadSolicitada + ", dosis=" + dosis + ", frecuencia=" + frecuencia + ", viaAdministracion=" + viaAdministracion + ", justificacionClinica=" + justificacionClinica + '}';
    }
    
}
