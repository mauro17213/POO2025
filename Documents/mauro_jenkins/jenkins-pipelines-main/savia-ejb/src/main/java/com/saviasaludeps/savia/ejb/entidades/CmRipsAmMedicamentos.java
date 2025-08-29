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
@Table(name = "cm_rips_am_medicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsAmMedicamentos.findAll", query = "SELECT c FROM CmRipsAmMedicamentos c"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findById", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByFila", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByNumeroFactura", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByCodigoReps", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByMaeTipoDocumentoId", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByDocumentoAfiliado", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.documentoAfiliado = :documentoAfiliado"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByAutorizacion", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.autorizacion = :autorizacion"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByMaMedicamentoCodigo", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.maMedicamentoCodigo = :maMedicamentoCodigo"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByMaMedicamentoId", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.maMedicamentoId = :maMedicamentoId"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByMaMedicamentoValor", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.maMedicamentoValor = :maMedicamentoValor"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByMaeTipoMedicamentoCodigo", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.maeTipoMedicamentoCodigo = :maeTipoMedicamentoCodigo"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByMaeTipoMedicamentoId", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.maeTipoMedicamentoId = :maeTipoMedicamentoId"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByMaeTipoMedicamentoValor", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.maeTipoMedicamentoValor = :maeTipoMedicamentoValor"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByNombreGenerico", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.nombreGenerico = :nombreGenerico"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByFormaFarmaceutica", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.formaFarmaceutica = :formaFarmaceutica"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByConcentracion", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.concentracion = :concentracion"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByUnidadMedida", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.unidadMedida = :unidadMedida"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByNumeroUnidades", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.numeroUnidades = :numeroUnidades"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByValorUnitario", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByValorAPagar", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.valorAPagar = :valorAPagar"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByArchivoRuta", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByArchivoNombre", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByUsuarioCrea", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByTerminalCrea", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsAmMedicamentos.findByFechaHoraCrea", query = "SELECT c FROM CmRipsAmMedicamentos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsAmMedicamentos implements Serializable {

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
    @Size(min = 1, max = 32)
    @Column(name = "autorizacion")
    private String autorizacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_medicamento_codigo")
    private String maMedicamentoCodigo;
    @Column(name = "ma_medicamento_id")
    private Integer maMedicamentoId;
    @Size(max = 512)
    @Column(name = "ma_medicamento_valor")
    private String maMedicamentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_medicamento_codigo")
    private String maeTipoMedicamentoCodigo;
    @Column(name = "mae_tipo_medicamento_id")
    private Integer maeTipoMedicamentoId;
    @Size(max = 128)
    @Column(name = "mae_tipo_medicamento_valor")
    private String maeTipoMedicamentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nombre_generico")
    private String nombreGenerico;
    @Size(max = 64)
    @Column(name = "forma_farmaceutica")
    private String formaFarmaceutica;
    @Size(max = 64)
    @Column(name = "concentracion")
    private String concentracion;
    @Size(max = 64)
    @Column(name = "unidad_medida")
    private String unidadMedida;
    @Size(max = 64)
    @Column(name = "numero_unidades")
    private String numeroUnidades;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
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

    public CmRipsAmMedicamentos() {
    }

    public CmRipsAmMedicamentos(Integer id) {
        this.id = id;
    }

    public CmRipsAmMedicamentos(Integer id, int fila, String numeroFactura, String codigoReps, String maeTipoDocumentoCodigo, String documentoAfiliado, String autorizacion, String maMedicamentoCodigo, String maeTipoMedicamentoCodigo, String nombreGenerico, BigDecimal valorUnitario, BigDecimal valorAPagar, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fila = fila;
        this.numeroFactura = numeroFactura;
        this.codigoReps = codigoReps;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.documentoAfiliado = documentoAfiliado;
        this.autorizacion = autorizacion;
        this.maMedicamentoCodigo = maMedicamentoCodigo;
        this.maeTipoMedicamentoCodigo = maeTipoMedicamentoCodigo;
        this.nombreGenerico = nombreGenerico;
        this.valorUnitario = valorUnitario;
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

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public Integer getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(Integer maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public String getMaeTipoMedicamentoCodigo() {
        return maeTipoMedicamentoCodigo;
    }

    public void setMaeTipoMedicamentoCodigo(String maeTipoMedicamentoCodigo) {
        this.maeTipoMedicamentoCodigo = maeTipoMedicamentoCodigo;
    }

    public Integer getMaeTipoMedicamentoId() {
        return maeTipoMedicamentoId;
    }

    public void setMaeTipoMedicamentoId(Integer maeTipoMedicamentoId) {
        this.maeTipoMedicamentoId = maeTipoMedicamentoId;
    }

    public String getMaeTipoMedicamentoValor() {
        return maeTipoMedicamentoValor;
    }

    public void setMaeTipoMedicamentoValor(String maeTipoMedicamentoValor) {
        this.maeTipoMedicamentoValor = maeTipoMedicamentoValor;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(String numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
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
        if (!(object instanceof CmRipsAmMedicamentos)) {
            return false;
        }
        CmRipsAmMedicamentos other = (CmRipsAmMedicamentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsAmMedicamentos[ id=" + id + " ]";
    }
    
}
