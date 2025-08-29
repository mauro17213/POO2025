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
@Table(name = "cm_rips_carga_ap_procedimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findAll", query = "SELECT c FROM CmRipsCargaApProcedimientos c"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findById", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByFila", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByNumeroFactura", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByCodigoReps", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeTipoDocumentoId", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByDocumentoAfiliado", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.documentoAfiliado = :documentoAfiliado"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByFechaProcedimiento", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.fechaProcedimiento = :fechaProcedimiento"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByAutorizacion", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.autorizacion = :autorizacion"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeAmbitoAtencionCodigo", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeAmbitoAtencionCodigo = :maeAmbitoAtencionCodigo"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeAmbitoAtencionId", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeAmbitoAtencionId = :maeAmbitoAtencionId"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeAmbitoAtencionCodigoValor", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeAmbitoAtencionCodigoValor = :maeAmbitoAtencionCodigoValor"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeFinalidadProcedimientoCodigo", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeFinalidadProcedimientoCodigo = :maeFinalidadProcedimientoCodigo"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeFinalidadProcedimientoId", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeFinalidadProcedimientoId = :maeFinalidadProcedimientoId"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeFinalidadProcedimientoValor", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeFinalidadProcedimientoValor = :maeFinalidadProcedimientoValor"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaePersonalAtiendeCodigo", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maePersonalAtiendeCodigo = :maePersonalAtiendeCodigo"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaePersonalAtiendeId", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maePersonalAtiendeId = :maePersonalAtiendeId"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaePersonalAtiendeValor", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maePersonalAtiendeValor = :maePersonalAtiendeValor"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaDiagnosticoPrincipalCodigo", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maDiagnosticoPrincipalCodigo = :maDiagnosticoPrincipalCodigo"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaDiagnosticoPrincipalId", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maDiagnosticoPrincipalId = :maDiagnosticoPrincipalId"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaDiagnosticoPrincipalValor", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maDiagnosticoPrincipalValor = :maDiagnosticoPrincipalValor"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaDiagnosticoRelacionado1Codigo", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maDiagnosticoRelacionado1Codigo = :maDiagnosticoRelacionado1Codigo"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaDiagnosticoRelacionado1Id", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maDiagnosticoRelacionado1Id = :maDiagnosticoRelacionado1Id"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaDiagnosticoRelacionado1Valor", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maDiagnosticoRelacionado1Valor = :maDiagnosticoRelacionado1Valor"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaDiagnosticoRelacionado2Codigo", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maDiagnosticoRelacionado2Codigo = :maDiagnosticoRelacionado2Codigo"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaDiagnosticoRelacionado2Id", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maDiagnosticoRelacionado2Id = :maDiagnosticoRelacionado2Id"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaDiagnosticoRelacionado2Valor", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maDiagnosticoRelacionado2Valor = :maDiagnosticoRelacionado2Valor"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeFormaActoCodigo", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeFormaActoCodigo = :maeFormaActoCodigo"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeFormaActoId", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeFormaActoId = :maeFormaActoId"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaeFormaActoValor", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maeFormaActoValor = :maeFormaActoValor"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaTecnologiaCodigo", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaTecnologiaId", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByMaTecnologiaValor", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByValorAPagar", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.valorAPagar = :valorAPagar"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByArchivoRuta", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByArchivoNombre", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByUsuarioCrea", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByTerminalCrea", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsCargaApProcedimientos.findByFechaHoraCrea", query = "SELECT c FROM CmRipsCargaApProcedimientos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsCargaApProcedimientos implements Serializable {

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
    @Column(name = "numero_factura")
    private String numeroFactura;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_procedimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaProcedimiento;
    @Size(max = 32)
    @Column(name = "autorizacion")
    private String autorizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_ambito_atencion_codigo")
    private String maeAmbitoAtencionCodigo;
    @Column(name = "mae_ambito_atencion_id")
    private Integer maeAmbitoAtencionId;
    @Size(max = 128)
    @Column(name = "mae_ambito_atencion_codigo_valor")
    private String maeAmbitoAtencionCodigoValor;
    @Size(max = 8)
    @Column(name = "mae_finalidad_procedimiento_codigo")
    private String maeFinalidadProcedimientoCodigo;
    @Column(name = "mae_finalidad_procedimiento_id")
    private Integer maeFinalidadProcedimientoId;
    @Size(max = 128)
    @Column(name = "mae_finalidad_procedimiento_valor")
    private String maeFinalidadProcedimientoValor;
    @Size(max = 8)
    @Column(name = "mae_personal_atiende_codigo")
    private String maePersonalAtiendeCodigo;
    @Column(name = "mae_personal_atiende_id")
    private Integer maePersonalAtiendeId;
    @Size(max = 128)
    @Column(name = "mae_personal_atiende_valor")
    private String maePersonalAtiendeValor;
    @Size(max = 32)
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
    @Size(max = 8)
    @Column(name = "mae_forma_acto_codigo")
    private String maeFormaActoCodigo;
    @Column(name = "mae_forma_acto_id")
    private Integer maeFormaActoId;
    @Size(max = 128)
    @Column(name = "mae_forma_acto_valor")
    private String maeFormaActoValor;
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_a_pagar")
    private BigDecimal valorAPagar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo_nombre_original")
    private String archivoNombreOriginal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
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

    public CmRipsCargaApProcedimientos() {
    }

    public CmRipsCargaApProcedimientos(Integer id) {
        this.id = id;
    }

    public CmRipsCargaApProcedimientos(Integer id, int fila, String numeroFactura, String codigoReps, String maeTipoDocumentoCodigo, String documentoAfiliado, Date fechaProcedimiento, String maeAmbitoAtencionCodigo, String maTecnologiaCodigo, BigDecimal valorAPagar, String archivoNombreOriginal, String archivoRuta, String archivoNombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fila = fila;
        this.numeroFactura = numeroFactura;
        this.codigoReps = codigoReps;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.documentoAfiliado = documentoAfiliado;
        this.fechaProcedimiento = fechaProcedimiento;
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.valorAPagar = valorAPagar;
        this.archivoNombreOriginal = archivoNombreOriginal;
        this.archivoRuta = archivoRuta;
        this.archivoNombre = archivoNombre;
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

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
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

    public Date getFechaProcedimiento() {
        return fechaProcedimiento;
    }

    public void setFechaProcedimiento(Date fechaProcedimiento) {
        this.fechaProcedimiento = fechaProcedimiento;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getMaeAmbitoAtencionCodigo() {
        return maeAmbitoAtencionCodigo;
    }

    public void setMaeAmbitoAtencionCodigo(String maeAmbitoAtencionCodigo) {
        this.maeAmbitoAtencionCodigo = maeAmbitoAtencionCodigo;
    }

    public Integer getMaeAmbitoAtencionId() {
        return maeAmbitoAtencionId;
    }

    public void setMaeAmbitoAtencionId(Integer maeAmbitoAtencionId) {
        this.maeAmbitoAtencionId = maeAmbitoAtencionId;
    }

    public String getMaeAmbitoAtencionCodigoValor() {
        return maeAmbitoAtencionCodigoValor;
    }

    public void setMaeAmbitoAtencionCodigoValor(String maeAmbitoAtencionCodigoValor) {
        this.maeAmbitoAtencionCodigoValor = maeAmbitoAtencionCodigoValor;
    }

    public String getMaeFinalidadProcedimientoCodigo() {
        return maeFinalidadProcedimientoCodigo;
    }

    public void setMaeFinalidadProcedimientoCodigo(String maeFinalidadProcedimientoCodigo) {
        this.maeFinalidadProcedimientoCodigo = maeFinalidadProcedimientoCodigo;
    }

    public Integer getMaeFinalidadProcedimientoId() {
        return maeFinalidadProcedimientoId;
    }

    public void setMaeFinalidadProcedimientoId(Integer maeFinalidadProcedimientoId) {
        this.maeFinalidadProcedimientoId = maeFinalidadProcedimientoId;
    }

    public String getMaeFinalidadProcedimientoValor() {
        return maeFinalidadProcedimientoValor;
    }

    public void setMaeFinalidadProcedimientoValor(String maeFinalidadProcedimientoValor) {
        this.maeFinalidadProcedimientoValor = maeFinalidadProcedimientoValor;
    }

    public String getMaePersonalAtiendeCodigo() {
        return maePersonalAtiendeCodigo;
    }

    public void setMaePersonalAtiendeCodigo(String maePersonalAtiendeCodigo) {
        this.maePersonalAtiendeCodigo = maePersonalAtiendeCodigo;
    }

    public Integer getMaePersonalAtiendeId() {
        return maePersonalAtiendeId;
    }

    public void setMaePersonalAtiendeId(Integer maePersonalAtiendeId) {
        this.maePersonalAtiendeId = maePersonalAtiendeId;
    }

    public String getMaePersonalAtiendeValor() {
        return maePersonalAtiendeValor;
    }

    public void setMaePersonalAtiendeValor(String maePersonalAtiendeValor) {
        this.maePersonalAtiendeValor = maePersonalAtiendeValor;
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

    public String getMaeFormaActoCodigo() {
        return maeFormaActoCodigo;
    }

    public void setMaeFormaActoCodigo(String maeFormaActoCodigo) {
        this.maeFormaActoCodigo = maeFormaActoCodigo;
    }

    public Integer getMaeFormaActoId() {
        return maeFormaActoId;
    }

    public void setMaeFormaActoId(Integer maeFormaActoId) {
        this.maeFormaActoId = maeFormaActoId;
    }

    public String getMaeFormaActoValor() {
        return maeFormaActoValor;
    }

    public void setMaeFormaActoValor(String maeFormaActoValor) {
        this.maeFormaActoValor = maeFormaActoValor;
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
        if (!(object instanceof CmRipsCargaApProcedimientos)) {
            return false;
        }
        CmRipsCargaApProcedimientos other = (CmRipsCargaApProcedimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsCargaApProcedimientos[ id=" + id + " ]";
    }
    
}
