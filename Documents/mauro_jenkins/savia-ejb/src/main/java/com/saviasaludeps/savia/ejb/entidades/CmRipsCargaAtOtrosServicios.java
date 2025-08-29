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
@Table(name = "cm_rips_carga_at_otros_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findAll", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findById", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByFila", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByNumeroFactura", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByCodigoReps", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByCodigoServicio", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.codigoServicio = :codigoServicio"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByMaeTipoDocumentoId", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByDocumentoAfiliado", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.documentoAfiliado = :documentoAfiliado"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByAutorizacion", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.autorizacion = :autorizacion"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByMaeTipoServicioCodigo", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.maeTipoServicioCodigo = :maeTipoServicioCodigo"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByMaeTipoServicioId", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.maeTipoServicioId = :maeTipoServicioId"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByMaeTipoServicioValor", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.maeTipoServicioValor = :maeTipoServicioValor"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByMaTecnologiaCodigo", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByMaTecnologiaId", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByMaTecnologiaValor", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByNombreServicio", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.nombreServicio = :nombreServicio"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByUnidades", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.unidades = :unidades"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByValorUnidades", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.valorUnidades = :valorUnidades"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByTotal", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.total = :total"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByArchivoRuta", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByArchivoNombre", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByUsuarioCrea", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByTerminalCrea", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsCargaAtOtrosServicios.findByFechaHoraCrea", query = "SELECT c FROM CmRipsCargaAtOtrosServicios c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsCargaAtOtrosServicios implements Serializable {

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
    @Size(min = 1, max = 32)
    @Column(name = "codigo_servicio")
    private String codigoServicio;
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
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_servicio_codigo")
    private String maeTipoServicioCodigo;
    @Column(name = "mae_tipo_servicio_id")
    private Integer maeTipoServicioId;
    @Size(max = 128)
    @Column(name = "mae_tipo_servicio_valor")
    private String maeTipoServicioValor;
    @Size(max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Column(name = "ma_tecnologia_id")
    private Integer maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Size(max = 512)
    @Column(name = "nombre_servicio")
    private String nombreServicio;
    @Column(name = "unidades")
    private Integer unidades;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_unidades")
    private BigDecimal valorUnidades;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo_nombre_original")
    private String archivoNombreOriginal;
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

    public CmRipsCargaAtOtrosServicios() {
    }

    public CmRipsCargaAtOtrosServicios(Integer id) {
        this.id = id;
    }

    public CmRipsCargaAtOtrosServicios(Integer id, int fila, String numeroFactura, String codigoReps, String codigoServicio, String maeTipoDocumentoCodigo, String documentoAfiliado, String autorizacion, String maeTipoServicioCodigo, String maTecnologiaValor, BigDecimal valorUnidades, BigDecimal total, String archivoRuta, String archivoNombre, String archivoNombreOriginal, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fila = fila;
        this.numeroFactura = numeroFactura;
        this.codigoReps = codigoReps;
        this.codigoServicio = codigoServicio;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.documentoAfiliado = documentoAfiliado;
        this.autorizacion = autorizacion;
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.valorUnidades = valorUnidades;
        this.total = total;
        this.archivoRuta = archivoRuta;
        this.archivoNombre = archivoNombre;
        this.archivoNombreOriginal = archivoNombreOriginal;
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

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
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

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public Integer getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(Integer maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
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

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public BigDecimal getValorUnidades() {
        return valorUnidades;
    }

    public void setValorUnidades(BigDecimal valorUnidades) {
        this.valorUnidades = valorUnidades;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public String getArchivoNombreOriginal() {
        return archivoNombreOriginal;
    }

    public void setArchivoNombreOriginal(String archivoNombreOriginal) {
        this.archivoNombreOriginal = archivoNombreOriginal;
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
        if (!(object instanceof CmRipsCargaAtOtrosServicios)) {
            return false;
        }
        CmRipsCargaAtOtrosServicios other = (CmRipsCargaAtOtrosServicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAtOtrosServicios[ id=" + id + " ]";
    }
    
}
