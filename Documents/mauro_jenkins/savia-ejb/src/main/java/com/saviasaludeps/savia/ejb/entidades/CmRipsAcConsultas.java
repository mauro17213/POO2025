/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cm_rips_ac_consultas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsAcConsultas.findAll", query = "SELECT c FROM CmRipsAcConsultas c"),
    @NamedQuery(name = "CmRipsAcConsultas.findById", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsAcConsultas.findByFila", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsAcConsultas.findByNumFactura", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.numFactura = :numFactura"),
    @NamedQuery(name = "CmRipsAcConsultas.findByCodigoReps", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeTipoDocumentoId", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmRipsAcConsultas.findByDocumentoAfiliado", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.documentoAfiliado = :documentoAfiliado"),
    @NamedQuery(name = "CmRipsAcConsultas.findByFechaConsulta", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.fechaConsulta = :fechaConsulta"),
    @NamedQuery(name = "CmRipsAcConsultas.findByAutorizacion", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.autorizacion = :autorizacion"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaTecnologiaCodigo", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaTecnologiaId", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaTecnologiaValor", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeFinalidadConsultaCodigo", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeFinalidadConsultaCodigo = :maeFinalidadConsultaCodigo"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeFinalidadConsultaId", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeFinalidadConsultaId = :maeFinalidadConsultaId"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeFinalidadConsultaValor", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeFinalidadConsultaValor = :maeFinalidadConsultaValor"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeCausaExternaCodigo", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeCausaExternaCodigo = :maeCausaExternaCodigo"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeCausaExternaId", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeCausaExternaId = :maeCausaExternaId"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeCausaExternaValor", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeCausaExternaValor = :maeCausaExternaValor"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoPrincipalCodigo", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoPrincipalCodigo = :maDiagnosticoPrincipalCodigo"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoPrincipalId", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoPrincipalId = :maDiagnosticoPrincipalId"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoPrincipalValor", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoPrincipalValor = :maDiagnosticoPrincipalValor"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoRelacionado1Codigo", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoRelacionado1Codigo = :maDiagnosticoRelacionado1Codigo"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoRelacionado1Id", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoRelacionado1Id = :maDiagnosticoRelacionado1Id"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoRelacionado1Valor", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoRelacionado1Valor = :maDiagnosticoRelacionado1Valor"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoRelacionado2Codigo", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoRelacionado2Codigo = :maDiagnosticoRelacionado2Codigo"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoRelacionado2Id", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoRelacionado2Id = :maDiagnosticoRelacionado2Id"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoRelacionado2Valor", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoRelacionado2Valor = :maDiagnosticoRelacionado2Valor"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoRelacionado3Codigo", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoRelacionado3Codigo = :maDiagnosticoRelacionado3Codigo"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoRelacionado3Id", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoRelacionado3Id = :maDiagnosticoRelacionado3Id"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaDiagnosticoRelacionado3Valor", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maDiagnosticoRelacionado3Valor = :maDiagnosticoRelacionado3Valor"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeTipoDiagnosticoCodigo", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeTipoDiagnosticoCodigo = :maeTipoDiagnosticoCodigo"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeTipoDiagnosticoId", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeTipoDiagnosticoId = :maeTipoDiagnosticoId"),
    @NamedQuery(name = "CmRipsAcConsultas.findByMaeTipoDiagnosticoValor", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.maeTipoDiagnosticoValor = :maeTipoDiagnosticoValor"),
    @NamedQuery(name = "CmRipsAcConsultas.findByValorConsulta", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.valorConsulta = :valorConsulta"),
    @NamedQuery(name = "CmRipsAcConsultas.findByValorCuotaModeradora", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.valorCuotaModeradora = :valorCuotaModeradora"),
    @NamedQuery(name = "CmRipsAcConsultas.findByValorAPagar", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.valorAPagar = :valorAPagar"),
    @NamedQuery(name = "CmRipsAcConsultas.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsAcConsultas.findByArchivoRuta", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsAcConsultas.findByArchivoNombre", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsAcConsultas.findByUsuarioCrea", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsAcConsultas.findByTerminalCrea", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsAcConsultas.findByFechaHoraCrea", query = "SELECT c FROM CmRipsAcConsultas c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsAcConsultas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fila")
    private int fila;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "num_factura")
    private String numFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo_reps")
    private String codigoReps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "documento_afiliado")
    private String documentoAfiliado;
    @Column(name = "fecha_consulta")
    @Temporal(TemporalType.DATE)
    private Date fechaConsulta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "autorizacion")
    private String autorizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Column(name = "ma_tecnologia_id")
    private Integer maTecnologiaId;
    @Size(max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_finalidad_consulta_codigo")
    private String maeFinalidadConsultaCodigo;
    @Column(name = "mae_finalidad_consulta_id")
    private Integer maeFinalidadConsultaId;
    @Size(max = 128)
    @Column(name = "mae_finalidad_consulta_valor")
    private String maeFinalidadConsultaValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_causa_externa_codigo")
    private String maeCausaExternaCodigo;
    @Column(name = "mae_causa_externa_id")
    private Integer maeCausaExternaId;
    @Size(max = 128)
    @Column(name = "mae_causa_externa_valor")
    private String maeCausaExternaValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_diagnostico_principal_codigo")
    private String maDiagnosticoPrincipalCodigo;
    @Column(name = "ma_diagnostico_principal_id")
    private Integer maDiagnosticoPrincipalId;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_principal_valor")
    private String maDiagnosticoPrincipalValor;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_relacionado1_codigo")
    private String maDiagnosticoRelacionado1Codigo;
    @Column(name = "ma_diagnostico_relacionado1_id")
    private Integer maDiagnosticoRelacionado1Id;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_relacionado1_valor")
    private String maDiagnosticoRelacionado1Valor;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_relacionado2_codigo")
    private String maDiagnosticoRelacionado2Codigo;
    @Column(name = "ma_diagnostico_relacionado2_id")
    private Integer maDiagnosticoRelacionado2Id;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_relacionado2_valor")
    private String maDiagnosticoRelacionado2Valor;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_relacionado3_codigo")
    private String maDiagnosticoRelacionado3Codigo;
    @Column(name = "ma_diagnostico_relacionado3_id")
    private Integer maDiagnosticoRelacionado3Id;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_relacionado3_valor")
    private String maDiagnosticoRelacionado3Valor;
    @Size(max = 8)
    @Column(name = "mae_tipo_diagnostico_codigo")
    private String maeTipoDiagnosticoCodigo;
    @Column(name = "mae_tipo_diagnostico_id")
    private Integer maeTipoDiagnosticoId;
    @Size(max = 128)
    @Column(name = "mae_tipo_diagnostico_valor")
    private String maeTipoDiagnosticoValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_consulta")
    private BigDecimal valorConsulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_cuota_moderadora")
    private BigDecimal valorCuotaModeradora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_a_pagar")
    private BigDecimal valorAPagar;
    @Size(max = 256)
    @Column(name = "archivo_nombre_original")
    private String archivoNombreOriginal;
    @Size(max = 512)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Size(max = 128)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cm_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRipsCargas cmRipsCargasId;

    public CmRipsAcConsultas() {
    }

    public CmRipsAcConsultas(Integer id) {
        this.id = id;
    }

    public CmRipsAcConsultas(Integer id, int fila, String numFactura, String codigoReps, String maeTipoDocumentoCodigo, String documentoAfiliado, String autorizacion, String maTecnologiaCodigo, String maeFinalidadConsultaCodigo, String maeCausaExternaCodigo, String maDiagnosticoPrincipalCodigo, BigDecimal valorConsulta, BigDecimal valorCuotaModeradora, BigDecimal valorAPagar, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fila = fila;
        this.numFactura = numFactura;
        this.codigoReps = codigoReps;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.documentoAfiliado = documentoAfiliado;
        this.autorizacion = autorizacion;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maeFinalidadConsultaCodigo = maeFinalidadConsultaCodigo;
        this.maeCausaExternaCodigo = maeCausaExternaCodigo;
        this.maDiagnosticoPrincipalCodigo = maDiagnosticoPrincipalCodigo;
        this.valorConsulta = valorConsulta;
        this.valorCuotaModeradora = valorCuotaModeradora;
        this.valorAPagar = valorAPagar;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public String getCodigoReps() {
        return codigoReps;
    }

    public void setCodigoReps(String codigoReps) {
        this.codigoReps = codigoReps;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
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

    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public String getMaeFinalidadConsultaCodigo() {
        return maeFinalidadConsultaCodigo;
    }

    public void setMaeFinalidadConsultaCodigo(String maeFinalidadConsultaCodigo) {
        this.maeFinalidadConsultaCodigo = maeFinalidadConsultaCodigo;
    }

    public Integer getMaeFinalidadConsultaId() {
        return maeFinalidadConsultaId;
    }

    public void setMaeFinalidadConsultaId(Integer maeFinalidadConsultaId) {
        this.maeFinalidadConsultaId = maeFinalidadConsultaId;
    }

    public String getMaeFinalidadConsultaValor() {
        return maeFinalidadConsultaValor;
    }

    public void setMaeFinalidadConsultaValor(String maeFinalidadConsultaValor) {
        this.maeFinalidadConsultaValor = maeFinalidadConsultaValor;
    }

    public String getMaeCausaExternaCodigo() {
        return maeCausaExternaCodigo;
    }

    public void setMaeCausaExternaCodigo(String maeCausaExternaCodigo) {
        this.maeCausaExternaCodigo = maeCausaExternaCodigo;
    }

    public Integer getMaeCausaExternaId() {
        return maeCausaExternaId;
    }

    public void setMaeCausaExternaId(Integer maeCausaExternaId) {
        this.maeCausaExternaId = maeCausaExternaId;
    }

    public String getMaeCausaExternaValor() {
        return maeCausaExternaValor;
    }

    public void setMaeCausaExternaValor(String maeCausaExternaValor) {
        this.maeCausaExternaValor = maeCausaExternaValor;
    }

    public String getMaDiagnosticoPrincipalCodigo() {
        return maDiagnosticoPrincipalCodigo;
    }

    public void setMaDiagnosticoPrincipalCodigo(String maDiagnosticoPrincipalCodigo) {
        this.maDiagnosticoPrincipalCodigo = maDiagnosticoPrincipalCodigo;
    }

    public Integer getMaDiagnosticoPrincipalId() {
        return maDiagnosticoPrincipalId;
    }

    public void setMaDiagnosticoPrincipalId(Integer maDiagnosticoPrincipalId) {
        this.maDiagnosticoPrincipalId = maDiagnosticoPrincipalId;
    }

    public String getMaDiagnosticoPrincipalValor() {
        return maDiagnosticoPrincipalValor;
    }

    public void setMaDiagnosticoPrincipalValor(String maDiagnosticoPrincipalValor) {
        this.maDiagnosticoPrincipalValor = maDiagnosticoPrincipalValor;
    }

    public String getMaDiagnosticoRelacionado1Codigo() {
        return maDiagnosticoRelacionado1Codigo;
    }

    public void setMaDiagnosticoRelacionado1Codigo(String maDiagnosticoRelacionado1Codigo) {
        this.maDiagnosticoRelacionado1Codigo = maDiagnosticoRelacionado1Codigo;
    }

    public Integer getMaDiagnosticoRelacionado1Id() {
        return maDiagnosticoRelacionado1Id;
    }

    public void setMaDiagnosticoRelacionado1Id(Integer maDiagnosticoRelacionado1Id) {
        this.maDiagnosticoRelacionado1Id = maDiagnosticoRelacionado1Id;
    }

    public String getMaDiagnosticoRelacionado1Valor() {
        return maDiagnosticoRelacionado1Valor;
    }

    public void setMaDiagnosticoRelacionado1Valor(String maDiagnosticoRelacionado1Valor) {
        this.maDiagnosticoRelacionado1Valor = maDiagnosticoRelacionado1Valor;
    }

    public String getMaDiagnosticoRelacionado2Codigo() {
        return maDiagnosticoRelacionado2Codigo;
    }

    public void setMaDiagnosticoRelacionado2Codigo(String maDiagnosticoRelacionado2Codigo) {
        this.maDiagnosticoRelacionado2Codigo = maDiagnosticoRelacionado2Codigo;
    }

    public Integer getMaDiagnosticoRelacionado2Id() {
        return maDiagnosticoRelacionado2Id;
    }

    public void setMaDiagnosticoRelacionado2Id(Integer maDiagnosticoRelacionado2Id) {
        this.maDiagnosticoRelacionado2Id = maDiagnosticoRelacionado2Id;
    }

    public String getMaDiagnosticoRelacionado2Valor() {
        return maDiagnosticoRelacionado2Valor;
    }

    public void setMaDiagnosticoRelacionado2Valor(String maDiagnosticoRelacionado2Valor) {
        this.maDiagnosticoRelacionado2Valor = maDiagnosticoRelacionado2Valor;
    }

    public String getMaDiagnosticoRelacionado3Codigo() {
        return maDiagnosticoRelacionado3Codigo;
    }

    public void setMaDiagnosticoRelacionado3Codigo(String maDiagnosticoRelacionado3Codigo) {
        this.maDiagnosticoRelacionado3Codigo = maDiagnosticoRelacionado3Codigo;
    }

    public Integer getMaDiagnosticoRelacionado3Id() {
        return maDiagnosticoRelacionado3Id;
    }

    public void setMaDiagnosticoRelacionado3Id(Integer maDiagnosticoRelacionado3Id) {
        this.maDiagnosticoRelacionado3Id = maDiagnosticoRelacionado3Id;
    }

    public String getMaDiagnosticoRelacionado3Valor() {
        return maDiagnosticoRelacionado3Valor;
    }

    public void setMaDiagnosticoRelacionado3Valor(String maDiagnosticoRelacionado3Valor) {
        this.maDiagnosticoRelacionado3Valor = maDiagnosticoRelacionado3Valor;
    }

    public String getMaeTipoDiagnosticoCodigo() {
        return maeTipoDiagnosticoCodigo;
    }

    public void setMaeTipoDiagnosticoCodigo(String maeTipoDiagnosticoCodigo) {
        this.maeTipoDiagnosticoCodigo = maeTipoDiagnosticoCodigo;
    }

    public Integer getMaeTipoDiagnosticoId() {
        return maeTipoDiagnosticoId;
    }

    public void setMaeTipoDiagnosticoId(Integer maeTipoDiagnosticoId) {
        this.maeTipoDiagnosticoId = maeTipoDiagnosticoId;
    }

    public String getMaeTipoDiagnosticoValor() {
        return maeTipoDiagnosticoValor;
    }

    public void setMaeTipoDiagnosticoValor(String maeTipoDiagnosticoValor) {
        this.maeTipoDiagnosticoValor = maeTipoDiagnosticoValor;
    }

    public BigDecimal getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(BigDecimal valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public BigDecimal getValorCuotaModeradora() {
        return valorCuotaModeradora;
    }

    public void setValorCuotaModeradora(BigDecimal valorCuotaModeradora) {
        this.valorCuotaModeradora = valorCuotaModeradora;
    }

    public BigDecimal getValorAPagar() {
        return valorAPagar;
    }

    public void setValorAPagar(BigDecimal valorAPagar) {
        this.valorAPagar = valorAPagar;
    }

    public String getArchivoNombreOriginal() {
        return archivoNombreOriginal;
    }

    public void setArchivoNombreOriginal(String archivoNombreOriginal) {
        this.archivoNombreOriginal = archivoNombreOriginal;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

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
    }

    public CmRipsCargas getCmRipsCargasId() {
        return cmRipsCargasId;
    }

    public void setCmRipsCargasId(CmRipsCargas cmRipsCargasId) {
        this.cmRipsCargasId = cmRipsCargasId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CmRipsAcConsultas)) {
            return false;
        }
        CmRipsAcConsultas other = (CmRipsAcConsultas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsAcConsultas[ id=" + id + " ]";
    }
    
}
