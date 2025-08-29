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
@Table(name = "cm_rips_carga_am_medicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findAll", query = "SELECT c FROM CmRipsCargaAmMedicamentos c"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findById", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByFila", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByNumeroFactura", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByCodigoReps", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByMaeTipoDocumentoId", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByDocumentoAfiliado", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.documentoAfiliado = :documentoAfiliado"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByAutorizacion", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.autorizacion = :autorizacion"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByMaMedicamentoCodigo", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.maMedicamentoCodigo = :maMedicamentoCodigo"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByMaMedicamentoId", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.maMedicamentoId = :maMedicamentoId"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByMaMedicamentoValor", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.maMedicamentoValor = :maMedicamentoValor"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByMaeTipoMedicamentoCodigo", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.maeTipoMedicamentoCodigo = :maeTipoMedicamentoCodigo"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByMaeTipoMedicamentoId", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.maeTipoMedicamentoId = :maeTipoMedicamentoId"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByMaeTipoMedicamentoValor", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.maeTipoMedicamentoValor = :maeTipoMedicamentoValor"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByNombreGenerico", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.nombreGenerico = :nombreGenerico"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByFormaFarmaceutica", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.formaFarmaceutica = :formaFarmaceutica"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByConcentracion", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.concentracion = :concentracion"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByUnidadMedida", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.unidadMedida = :unidadMedida"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByNumeroUnidades", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.numeroUnidades = :numeroUnidades"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByValorUnitario", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByValorAPagar", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.valorAPagar = :valorAPagar"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByArchivoRuta", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByArchivoNombre", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByUsuarioCrea", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByTerminalCrea", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsCargaAmMedicamentos.findByFechaHoraCrea", query = "SELECT c FROM CmRipsCargaAmMedicamentos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsCargaAmMedicamentos implements Serializable {

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

    public CmRipsCargaAmMedicamentos() {
    }

    public CmRipsCargaAmMedicamentos(Integer id) {
        this.id = id;
    }

    public CmRipsCargaAmMedicamentos(Integer id, int fila, String numeroFactura, String codigoReps, String maeTipoDocumentoCodigo, String documentoAfiliado, String autorizacion, String maMedicamentoCodigo, String maeTipoMedicamentoCodigo, String nombreGenerico, BigDecimal valorUnitario, BigDecimal valorAPagar, String archivoNombreOriginal, String archivoRuta, String archivoNombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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
        if (!(object instanceof CmRipsCargaAmMedicamentos)) {
            return false;
        }
        CmRipsCargaAmMedicamentos other = (CmRipsCargaAmMedicamentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAmMedicamentos[ id=" + id + " ]";
    }
    
}
