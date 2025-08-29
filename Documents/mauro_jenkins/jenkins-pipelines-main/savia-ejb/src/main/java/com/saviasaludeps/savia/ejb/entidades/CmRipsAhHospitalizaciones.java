/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
@Table(name = "cm_rips_ah_hospitalizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findAll", query = "SELECT c FROM CmRipsAhHospitalizaciones c"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findById", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByFila", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByNumeroFactura", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByCodigoReps", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeTipoDocumentoId", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByDocumentoAfiliado", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.documentoAfiliado = :documentoAfiliado"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeViaIngresoCodigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeViaIngresoCodigo = :maeViaIngresoCodigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeViaIngresoId", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeViaIngresoId = :maeViaIngresoId"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeViaIngresoValor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeViaIngresoValor = :maeViaIngresoValor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByFechaIngreso", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByHoraIngreso", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.horaIngreso = :horaIngreso"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByAutorizacion", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.autorizacion = :autorizacion"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeCausaExternaCodigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeCausaExternaCodigo = :maeCausaExternaCodigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeCausaExternaId", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeCausaExternaId = :maeCausaExternaId"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeCausaExternaValor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeCausaExternaValor = :maeCausaExternaValor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoIngresoCodigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoIngresoCodigo = :maDiagnosticoIngresoCodigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoIngresoId", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoIngresoId = :maDiagnosticoIngresoId"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoIngresoValor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoIngresoValor = :maDiagnosticoIngresoValor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoEgresoCodigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoEgresoCodigo = :maDiagnosticoEgresoCodigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoEgresoId", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoEgresoId = :maDiagnosticoEgresoId"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoEgresoValor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoEgresoValor = :maDiagnosticoEgresoValor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoRelacionado1Codigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoRelacionado1Codigo = :maDiagnosticoRelacionado1Codigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoRelacionado1Id", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoRelacionado1Id = :maDiagnosticoRelacionado1Id"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoRelacionado1Valor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoRelacionado1Valor = :maDiagnosticoRelacionado1Valor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoRelacionado2Codigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoRelacionado2Codigo = :maDiagnosticoRelacionado2Codigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoRelacionado2Id", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoRelacionado2Id = :maDiagnosticoRelacionado2Id"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoRelacionado2Valor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoRelacionado2Valor = :maDiagnosticoRelacionado2Valor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoRelacionado3Codigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoRelacionado3Codigo = :maDiagnosticoRelacionado3Codigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoRelacionado3Id", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoRelacionado3Id = :maDiagnosticoRelacionado3Id"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoRelacionado3Valor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoRelacionado3Valor = :maDiagnosticoRelacionado3Valor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoComplicacionCodigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoComplicacionCodigo = :maDiagnosticoComplicacionCodigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoComplicacionId", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoComplicacionId = :maDiagnosticoComplicacionId"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoComplicacionValor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoComplicacionValor = :maDiagnosticoComplicacionValor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeEstadoSalidaCodigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeEstadoSalidaCodigo = :maeEstadoSalidaCodigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeEstadoSalidaId", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeEstadoSalidaId = :maeEstadoSalidaId"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaeEstadoSalidaValor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maeEstadoSalidaValor = :maeEstadoSalidaValor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoMuerteCodigo", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoMuerteCodigo = :maDiagnosticoMuerteCodigo"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoMuerteId", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoMuerteId = :maDiagnosticoMuerteId"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByMaDiagnosticoMuerteValor", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.maDiagnosticoMuerteValor = :maDiagnosticoMuerteValor"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByFechaSalida", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByHoraSalida", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.horaSalida = :horaSalida"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByArchivoRuta", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByArchivoNombre", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByUsuarioCrea", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByTerminalCrea", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsAhHospitalizaciones.findByFechaHoraCrea", query = "SELECT c FROM CmRipsAhHospitalizaciones c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsAhHospitalizaciones implements Serializable {

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
    @Size(max = 8)
    @Column(name = "mae_via_ingreso_codigo")
    private String maeViaIngresoCodigo;
    @Column(name = "mae_via_ingreso_id")
    private Integer maeViaIngresoId;
    @Size(max = 128)
    @Column(name = "mae_via_ingreso_valor")
    private String maeViaIngresoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_ingreso")
    @Temporal(TemporalType.TIME)
    private Date horaIngreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "autorizacion")
    private String autorizacion;
    @Size(max = 8)
    @Column(name = "mae_causa_externa_codigo")
    private String maeCausaExternaCodigo;
    @Column(name = "mae_causa_externa_id")
    private Integer maeCausaExternaId;
    @Size(max = 128)
    @Column(name = "mae_causa_externa_valor")
    private String maeCausaExternaValor;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_ingreso_codigo")
    private String maDiagnosticoIngresoCodigo;
    @Column(name = "ma_diagnostico_ingreso_id")
    private Integer maDiagnosticoIngresoId;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_ingreso_valor")
    private String maDiagnosticoIngresoValor;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_egreso_codigo")
    private String maDiagnosticoEgresoCodigo;
    @Column(name = "ma_diagnostico_egreso_id")
    private Integer maDiagnosticoEgresoId;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_egreso_valor")
    private String maDiagnosticoEgresoValor;
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
    @Size(max = 32)
    @Column(name = "ma_diagnostico_complicacion_codigo")
    private String maDiagnosticoComplicacionCodigo;
    @Column(name = "ma_diagnostico_complicacion_id")
    private Integer maDiagnosticoComplicacionId;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_complicacion_valor")
    private String maDiagnosticoComplicacionValor;
    @Size(max = 8)
    @Column(name = "mae_estado_salida_codigo")
    private String maeEstadoSalidaCodigo;
    @Column(name = "mae_estado_salida_id")
    private Integer maeEstadoSalidaId;
    @Size(max = 128)
    @Column(name = "mae_estado_salida_valor")
    private String maeEstadoSalidaValor;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_muerte_codigo")
    private String maDiagnosticoMuerteCodigo;
    @Column(name = "ma_diagnostico_muerte_id")
    private Integer maDiagnosticoMuerteId;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_muerte_valor")
    private String maDiagnosticoMuerteValor;
    @Column(name = "fecha_salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
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

    public CmRipsAhHospitalizaciones() {
    }

    public CmRipsAhHospitalizaciones(Integer id) {
        this.id = id;
    }

    public CmRipsAhHospitalizaciones(Integer id, int fila, String numeroFactura, String codigoReps, String maeTipoDocumentoCodigo, String documentoAfiliado, Date fechaIngreso, Date horaIngreso, String autorizacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fila = fila;
        this.numeroFactura = numeroFactura;
        this.codigoReps = codigoReps;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.documentoAfiliado = documentoAfiliado;
        this.fechaIngreso = fechaIngreso;
        this.horaIngreso = horaIngreso;
        this.autorizacion = autorizacion;
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

    public String getMaeViaIngresoCodigo() {
        return maeViaIngresoCodigo;
    }

    public void setMaeViaIngresoCodigo(String maeViaIngresoCodigo) {
        this.maeViaIngresoCodigo = maeViaIngresoCodigo;
    }

    public Integer getMaeViaIngresoId() {
        return maeViaIngresoId;
    }

    public void setMaeViaIngresoId(Integer maeViaIngresoId) {
        this.maeViaIngresoId = maeViaIngresoId;
    }

    public String getMaeViaIngresoValor() {
        return maeViaIngresoValor;
    }

    public void setMaeViaIngresoValor(String maeViaIngresoValor) {
        this.maeViaIngresoValor = maeViaIngresoValor;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Date horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
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

    public String getMaDiagnosticoIngresoCodigo() {
        return maDiagnosticoIngresoCodigo;
    }

    public void setMaDiagnosticoIngresoCodigo(String maDiagnosticoIngresoCodigo) {
        this.maDiagnosticoIngresoCodigo = maDiagnosticoIngresoCodigo;
    }

    public Integer getMaDiagnosticoIngresoId() {
        return maDiagnosticoIngresoId;
    }

    public void setMaDiagnosticoIngresoId(Integer maDiagnosticoIngresoId) {
        this.maDiagnosticoIngresoId = maDiagnosticoIngresoId;
    }

    public String getMaDiagnosticoIngresoValor() {
        return maDiagnosticoIngresoValor;
    }

    public void setMaDiagnosticoIngresoValor(String maDiagnosticoIngresoValor) {
        this.maDiagnosticoIngresoValor = maDiagnosticoIngresoValor;
    }

    public String getMaDiagnosticoEgresoCodigo() {
        return maDiagnosticoEgresoCodigo;
    }

    public void setMaDiagnosticoEgresoCodigo(String maDiagnosticoEgresoCodigo) {
        this.maDiagnosticoEgresoCodigo = maDiagnosticoEgresoCodigo;
    }

    public Integer getMaDiagnosticoEgresoId() {
        return maDiagnosticoEgresoId;
    }

    public void setMaDiagnosticoEgresoId(Integer maDiagnosticoEgresoId) {
        this.maDiagnosticoEgresoId = maDiagnosticoEgresoId;
    }

    public String getMaDiagnosticoEgresoValor() {
        return maDiagnosticoEgresoValor;
    }

    public void setMaDiagnosticoEgresoValor(String maDiagnosticoEgresoValor) {
        this.maDiagnosticoEgresoValor = maDiagnosticoEgresoValor;
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

    public String getMaDiagnosticoComplicacionCodigo() {
        return maDiagnosticoComplicacionCodigo;
    }

    public void setMaDiagnosticoComplicacionCodigo(String maDiagnosticoComplicacionCodigo) {
        this.maDiagnosticoComplicacionCodigo = maDiagnosticoComplicacionCodigo;
    }

    public Integer getMaDiagnosticoComplicacionId() {
        return maDiagnosticoComplicacionId;
    }

    public void setMaDiagnosticoComplicacionId(Integer maDiagnosticoComplicacionId) {
        this.maDiagnosticoComplicacionId = maDiagnosticoComplicacionId;
    }

    public String getMaDiagnosticoComplicacionValor() {
        return maDiagnosticoComplicacionValor;
    }

    public void setMaDiagnosticoComplicacionValor(String maDiagnosticoComplicacionValor) {
        this.maDiagnosticoComplicacionValor = maDiagnosticoComplicacionValor;
    }

    public String getMaeEstadoSalidaCodigo() {
        return maeEstadoSalidaCodigo;
    }

    public void setMaeEstadoSalidaCodigo(String maeEstadoSalidaCodigo) {
        this.maeEstadoSalidaCodigo = maeEstadoSalidaCodigo;
    }

    public Integer getMaeEstadoSalidaId() {
        return maeEstadoSalidaId;
    }

    public void setMaeEstadoSalidaId(Integer maeEstadoSalidaId) {
        this.maeEstadoSalidaId = maeEstadoSalidaId;
    }

    public String getMaeEstadoSalidaValor() {
        return maeEstadoSalidaValor;
    }

    public void setMaeEstadoSalidaValor(String maeEstadoSalidaValor) {
        this.maeEstadoSalidaValor = maeEstadoSalidaValor;
    }

    public String getMaDiagnosticoMuerteCodigo() {
        return maDiagnosticoMuerteCodigo;
    }

    public void setMaDiagnosticoMuerteCodigo(String maDiagnosticoMuerteCodigo) {
        this.maDiagnosticoMuerteCodigo = maDiagnosticoMuerteCodigo;
    }

    public Integer getMaDiagnosticoMuerteId() {
        return maDiagnosticoMuerteId;
    }

    public void setMaDiagnosticoMuerteId(Integer maDiagnosticoMuerteId) {
        this.maDiagnosticoMuerteId = maDiagnosticoMuerteId;
    }

    public String getMaDiagnosticoMuerteValor() {
        return maDiagnosticoMuerteValor;
    }

    public void setMaDiagnosticoMuerteValor(String maDiagnosticoMuerteValor) {
        this.maDiagnosticoMuerteValor = maDiagnosticoMuerteValor;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
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
        if (!(object instanceof CmRipsAhHospitalizaciones)) {
            return false;
        }
        CmRipsAhHospitalizaciones other = (CmRipsAhHospitalizaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsAhHospitalizaciones[ id=" + id + " ]";
    }
    
}
