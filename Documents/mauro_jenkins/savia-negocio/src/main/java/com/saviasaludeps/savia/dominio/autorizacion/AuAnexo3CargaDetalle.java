package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

public class AuAnexo3CargaDetalle extends Auditoria {

    private Integer id;
    private Integer auAnexo3Id;
    private AuAnexo3Carga auAnexo3CargasId;
    private String numeroSolicitud;
    private Integer maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String documentoAfiliado;
    private String telefonoAfiliado;
    private String celularAfiliado;
    private Integer maeCausaExternaId;
    private String maeCausaExternaCodigo;
    private String maeCausaExternaValor;
    private Integer maServicioHabilitadoId;
    private String maServicioHabilitadoCodigo;
    private String maServicioHabilitadoValor;
    private Integer maServicioSolicitadoId;
    private String maServicioSolicitadoCodigo;
    private String maServicioSolicitadoValor;
    private Date fechaSolicitud;
    private String codigoREPS;
    private String nombreProfesional;
    private Integer maeTipoDocumentoProfesionalId;
    private String maeTipoDocumentoProfesionalCodigo;
    private String maeTipoDocumentoProfesionalValor;
    private String documentoProfesional;
    private String telefonoProfesional;
    private Integer maeOrigenAtencionId;
    private String maeOrigenAtencionCodigo;
    private String maeOrigenAtencionValor;
    private Integer prioridadAtencion;
    private Integer tipoServicioSolicitado;
    private Integer maeAmbitoAtencionId;
    private String maeAmbitoAtencionCodigo;
    private String maeAmbitoAtencionValor;
    private Boolean esPrincipal;
    private Integer tipoTecnologia;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer maMedicamentoId;
    private String maMedicamentoCodigo;
    private String maMedicamentoValor;
    private Integer cantidadSolicitada;
    private Integer duracionTratamiento;
    private String dosis;
    private Integer frecuencia;
    private String tipoFrecuencia;
    private Integer viaAdministracion;
    //2023-09-19 nuevos campos
    private Integer peProgramaEspecialId;
    private String peProgramaEspecialCodigo;
    private String peProgramaEspecialDescripcion;
    private Boolean posfechado;
    private Boolean posfechadoPrincipal;
    private Integer posfechadoDuracion;
    private Integer posfechadoEntregas;
    private String justificacionClinica;
    private Integer maDiagnosticoId;
    private String maDiagnosticoCodigo;
    private String maDiagnosticoValor;
    private Integer maeTipoDiagnosticoId;
    private String maeTipoDiagnosticoCodigo;
    private String maeTipoDiagnosticoValor;
    private Integer maeUbicacionesId;
    private String maeUbicacionCodigo;
    private String maeUbicacionValor;
    private Integer maeTipoServicioId;
    private String maeTipoServicioCodigo;
    private String maeTipoServicioValor;
    private Integer maEspecialidadId;
    private String maEspecialidadCodigo;
    private String maEspecialidadValor;
    private String descripcion;
    private boolean version;
    private Integer maeModalidadTecnologiaId;
    private String maeModalidadTecnologiaCodigo;
    private String maeModalidadTecnologiaValor;
    private Integer maeFinalidadTecnologiaId;
    private String maeFinalidadTecnologiaCodigo;
    private String maeFinalidadTecnologiaValor;
    private String direccionAlternativa;
    //private String usuarioCrea;
    //private String terminalCrea;
    //private Date fechaHoraCrea;
    private int fila;
    private int columna;
    private List<AuAnexo3CargaSuceso> auAnexo3CargaSucesosList;
    private String consecutivo;
    private Boolean error;
    private Boolean anexado;

    public AuAnexo3CargaDetalle() {
    }

    public AuAnexo3CargaDetalle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuAnexo3Id() {
        return auAnexo3Id;
    }

    public void setAuAnexo3Id(Integer auAnexo3Id) {
        this.auAnexo3Id = auAnexo3Id;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
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

    public String getDocumentoAfiliado() {
        return documentoAfiliado;
    }

    public void setDocumentoAfiliado(String documentoAfiliado) {
        this.documentoAfiliado = documentoAfiliado;
    }

    public String getTelefonoAfiliado() {
        return telefonoAfiliado;
    }

    public void setTelefonoAfiliado(String telefonoAfiliado) {
        this.telefonoAfiliado = telefonoAfiliado;
    }

    public String getCelularAfiliado() {
        return celularAfiliado;
    }

    public void setCelularAfiliado(String celularAfiliado) {
        this.celularAfiliado = celularAfiliado;
    }

    public Integer getMaeCausaExternaId() {
        return maeCausaExternaId;
    }

    public void setMaeCausaExternaId(Integer maeCausaExternaId) {
        this.maeCausaExternaId = maeCausaExternaId;
    }

    public String getMaeCausaExternaCodigo() {
        return maeCausaExternaCodigo;
    }

    public void setMaeCausaExternaCodigo(String maeCausaExternaCodigo) {
        this.maeCausaExternaCodigo = maeCausaExternaCodigo;
    }

    public String getMaeCausaExternaValor() {
        return maeCausaExternaValor;
    }

    public void setMaeCausaExternaValor(String maeCausaExternaValor) {
        this.maeCausaExternaValor = maeCausaExternaValor;
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

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getCodigoREPS() {
        return codigoREPS;
    }

    public void setCodigoREPS(String codigoREPS) {
        this.codigoREPS = codigoREPS;
    }

    public String getNombreProfesional() {
        return nombreProfesional;
    }

    public void setNombreProfesional(String nombreProfesional) {
        this.nombreProfesional = nombreProfesional;
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

    public String getDocumentoProfesional() {
        return documentoProfesional;
    }

    public void setDocumentoProfesional(String documentoProfesional) {
        this.documentoProfesional = documentoProfesional;
    }

    public String getTelefonoProfesional() {
        return telefonoProfesional;
    }

    public void setTelefonoProfesional(String telefonoProfesional) {
        this.telefonoProfesional = telefonoProfesional;
    }

    public Integer getMaeOrigenAtencionId() {
        return maeOrigenAtencionId;
    }

    public void setMaeOrigenAtencionId(Integer maeOrigenAtencionId) {
        this.maeOrigenAtencionId = maeOrigenAtencionId;
    }

    public String getMaeOrigenAtencionCodigo() {
        return maeOrigenAtencionCodigo;
    }

    public void setMaeOrigenAtencionCodigo(String maeOrigenAtencionCodigo) {
        this.maeOrigenAtencionCodigo = maeOrigenAtencionCodigo;
    }

    public String getMaeOrigenAtencionValor() {
        return maeOrigenAtencionValor;
    }

    public void setMaeOrigenAtencionValor(String maeOrigenAtencionValor) {
        this.maeOrigenAtencionValor = maeOrigenAtencionValor;
    }

    public Integer getPrioridadAtencion() {
        return prioridadAtencion;
    }

    public void setPrioridadAtencion(Integer prioridadAtencion) {
        this.prioridadAtencion = prioridadAtencion;
    }

    public Integer getTipoServicioSolicitado() {
        return tipoServicioSolicitado;
    }

    public void setTipoServicioSolicitado(Integer tipoServicioSolicitado) {
        this.tipoServicioSolicitado = tipoServicioSolicitado;
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

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
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

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
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

    public String getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    public void setTipoFrecuencia(String tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    public Integer getViaAdministracion() {
        return viaAdministracion;
    }

    public void setViaAdministracion(Integer viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }

    public String getJustificacionClinica() {
        return justificacionClinica;
    }

    public void setJustificacionClinica(String justificacionClinica) {
        this.justificacionClinica = justificacionClinica;
    }

    public Integer getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(Integer maDiagnosticoId) {
        this.maDiagnosticoId = maDiagnosticoId;
    }

    public String getMaDiagnosticoCodigo() {
        return maDiagnosticoCodigo;
    }

    public void setMaDiagnosticoCodigo(String maDiagnosticoCodigo) {
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
    }

    public String getMaDiagnosticoValor() {
        return maDiagnosticoValor;
    }

    public void setMaDiagnosticoValor(String maDiagnosticoValor) {
        this.maDiagnosticoValor = maDiagnosticoValor;
    }

    public Integer getMaeTipoDiagnosticoId() {
        return maeTipoDiagnosticoId;
    }

    public void setMaeTipoDiagnosticoId(Integer maeTipoDiagnosticoId) {
        this.maeTipoDiagnosticoId = maeTipoDiagnosticoId;
    }

    public String getMaeTipoDiagnosticoCodigo() {
        return maeTipoDiagnosticoCodigo;
    }

    public void setMaeTipoDiagnosticoCodigo(String maeTipoDiagnosticoCodigo) {
        this.maeTipoDiagnosticoCodigo = maeTipoDiagnosticoCodigo;
    }

    public String getMaeTipoDiagnosticoValor() {
        return maeTipoDiagnosticoValor;
    }

    public void setMaeTipoDiagnosticoValor(String maeTipoDiagnosticoValor) {
        this.maeTipoDiagnosticoValor = maeTipoDiagnosticoValor;
    }

    public Integer getMaeUbicacionesId() {
        return maeUbicacionesId;
    }

    public void setMaeUbicacionesId(Integer maeUbicacionesId) {
        this.maeUbicacionesId = maeUbicacionesId;
    }

    public String getMaeUbicacionCodigo() {
        return maeUbicacionCodigo;
    }

    public void setMaeUbicacionCodigo(String maeUbicacionCodigo) {
        this.maeUbicacionCodigo = maeUbicacionCodigo;
    }

    public String getMaeUbicacionValor() {
        return maeUbicacionValor;
    }

    public void setMaeUbicacionValor(String maeUbicacionValor) {
        this.maeUbicacionValor = maeUbicacionValor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*
    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }*/
    public List<AuAnexo3CargaSuceso> getAuAnexo3CargaSucesosList() {
        return auAnexo3CargaSucesosList;
    }

    public AuAnexo3Carga getAuAnexo3CargasId() {
        return auAnexo3CargasId;
    }

    public void setAuAnexo3CargasId(AuAnexo3Carga auAnexo3CargasId) {
        this.auAnexo3CargasId = auAnexo3CargasId;
    }

    public void setAuAnexo3CargaSucesosList(List<AuAnexo3CargaSuceso> auAnexo3CargaSucesosList) {
        this.auAnexo3CargaSucesosList = auAnexo3CargaSucesosList;
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

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Boolean getAnexado() {
        return anexado;
    }

    public void setAnexado(Boolean anexado) {
        this.anexado = anexado;
    }

    public Integer getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(Integer maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
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

    /**
     * @return the peProgramaEspecialCodigo
     */
    public String getPeProgramaEspecialCodigo() {
        return peProgramaEspecialCodigo;
    }

    /**
     * @param peProgramaEspecialCodigo the peProgramaEspecialCodigo to set
     */
    public void setPeProgramaEspecialCodigo(String peProgramaEspecialCodigo) {
        this.peProgramaEspecialCodigo = peProgramaEspecialCodigo;
    }

    /**
     * @return the peProgramaEspecialDescripcion
     */
    public String getPeProgramaEspecialDescripcion() {
        return peProgramaEspecialDescripcion;
    }

    /**
     * @param peProgramaEspecialDescripcion the peProgramaEspecialDescripcion to set
     */
    public void setPeProgramaEspecialDescripcion(String peProgramaEspecialDescripcion) {
        this.peProgramaEspecialDescripcion = peProgramaEspecialDescripcion;
    }

    /**
     * @return the posfechado
     */
    public Boolean getPosfechado() {
        return posfechado;
    }

    /**
     * @param posfechado the posfechado to set
     */
    public void setPosfechado(Boolean posfechado) {
        this.posfechado = posfechado;
    }

    /**
     * @return the peProgramaEspecialId
     */
    public Integer getPeProgramaEspecialId() {
        return peProgramaEspecialId;
    }

    /**
     * @param peProgramaEspecialId the peProgramaEspecialId to set
     */
    public void setPeProgramaEspecialId(Integer peProgramaEspecialId) {
        this.peProgramaEspecialId = peProgramaEspecialId;
    }

    /**
     * @return the posfechadoPrincipal
     */
    public Boolean getPosfechadoPrincipal() {
        return posfechadoPrincipal;
    }

    /**
     * @param posfechadoPrincipal the posfechadoPrincipal to set
     */
    public void setPosfechadoPrincipal(Boolean posfechadoPrincipal) {
        this.posfechadoPrincipal = posfechadoPrincipal;
    }

    /**
     * @return the posfechadoDuracion
     */
    public Integer getPosfechadoDuracion() {
        return posfechadoDuracion;
    }

    /**
     * @param posfechadoDuracion the posfechadoDuracion to set
     */
    public void setPosfechadoDuracion(Integer posfechadoDuracion) {
        this.posfechadoDuracion = posfechadoDuracion;
    }

    /**
     * @return the posfechadoEntregas
     */
    public Integer getPosfechadoEntregas() {
        return posfechadoEntregas;
    }

    /**
     * @param posfechadoEntregas the posfechadoEntregas to set
     */
    public void setPosfechadoEntregas(Integer posfechadoEntregas) {
        this.posfechadoEntregas = posfechadoEntregas;
    }

    public boolean isVersion() {
        return version;
    }

    public void setVersion(boolean version) {
        this.version = version;
    }

    public Integer getMaeModalidadTecnologiaId() {
        return maeModalidadTecnologiaId;
    }

    public void setMaeModalidadTecnologiaId(Integer maeModalidadTecnologiaId) {
        this.maeModalidadTecnologiaId = maeModalidadTecnologiaId;
    }

    public String getMaeModalidadTecnologiaCodigo() {
        return maeModalidadTecnologiaCodigo;
    }

    public void setMaeModalidadTecnologiaCodigo(String maeModalidadTecnologiaCodigo) {
        this.maeModalidadTecnologiaCodigo = maeModalidadTecnologiaCodigo;
    }

    public String getMaeModalidadTecnologiaValor() {
        return maeModalidadTecnologiaValor;
    }

    public void setMaeModalidadTecnologiaValor(String maeModalidadTecnologiaValor) {
        this.maeModalidadTecnologiaValor = maeModalidadTecnologiaValor;
    }

    public Integer getMaeFinalidadTecnologiaId() {
        return maeFinalidadTecnologiaId;
    }

    public void setMaeFinalidadTecnologiaId(Integer maeFinalidadTecnologiaId) {
        this.maeFinalidadTecnologiaId = maeFinalidadTecnologiaId;
    }

    public String getMaeFinalidadTecnologiaCodigo() {
        return maeFinalidadTecnologiaCodigo;
    }

    public void setMaeFinalidadTecnologiaCodigo(String maeFinalidadTecnologiaCodigo) {
        this.maeFinalidadTecnologiaCodigo = maeFinalidadTecnologiaCodigo;
    }

    public String getMaeFinalidadTecnologiaValor() {
        return maeFinalidadTecnologiaValor;
    }

    public void setMaeFinalidadTecnologiaValor(String maeFinalidadTecnologiaValor) {
        this.maeFinalidadTecnologiaValor = maeFinalidadTecnologiaValor;
    }

    public String getDireccionAlternativa() {
        return direccionAlternativa;
    }

    public void setDireccionAlternativa(String direccionAlternativa) {
        this.direccionAlternativa = direccionAlternativa;
    }
    
    public String getVersionStr(){
        String versionStr = "3047";
        if(version){
            versionStr = "2335";
        }
        return versionStr;
    }
}
