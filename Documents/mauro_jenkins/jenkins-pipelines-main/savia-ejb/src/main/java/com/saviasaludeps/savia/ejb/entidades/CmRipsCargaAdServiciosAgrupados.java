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
@Table(name = "cm_rips_carga_ad_servicios_agrupados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findAll", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findById", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByFila", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByArchivoControl", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.archivoControl = :archivoControl"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByNumeroFactura", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByCodigoReps", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByConceptoRips", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.conceptoRips = :conceptoRips"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByCantidadServicios", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.cantidadServicios = :cantidadServicios"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByValorUnitario", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.valorUnitario = :valorUnitario"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByValorConcepto", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.valorConcepto = :valorConcepto"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByArchivoRuta", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByArchivoNombre", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByUsuarioCrea", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByTerminalCrea", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsCargaAdServiciosAgrupados.findByFechaHoraCrea", query = "SELECT c FROM CmRipsCargaAdServiciosAgrupados c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsCargaAdServiciosAgrupados implements Serializable {

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
    @Column(name = "archivo_control")
    private String archivoControl;
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
    @Size(min = 1, max = 128)
    @Column(name = "concepto_rips")
    private String conceptoRips;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cantidad_servicios")
    private String cantidadServicios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "valor_unitario")
    private String valorUnitario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "valor_concepto")
    private String valorConcepto;
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

    public CmRipsCargaAdServiciosAgrupados() {
    }

    public CmRipsCargaAdServiciosAgrupados(Integer id) {
        this.id = id;
    }

    public CmRipsCargaAdServiciosAgrupados(Integer id, int fila, String archivoControl, String numeroFactura, String codigoReps, String conceptoRips, String cantidadServicios, String valorUnitario, String valorConcepto, String archivoNombreOriginal, String archivoRuta, String archivoNombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fila = fila;
        this.archivoControl = archivoControl;
        this.numeroFactura = numeroFactura;
        this.codigoReps = codigoReps;
        this.conceptoRips = conceptoRips;
        this.cantidadServicios = cantidadServicios;
        this.valorUnitario = valorUnitario;
        this.valorConcepto = valorConcepto;
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

    public String getArchivoControl() {
        return archivoControl;
    }

    public void setArchivoControl(String archivoControl) {
        this.archivoControl = archivoControl;
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

    public String getConceptoRips() {
        return conceptoRips;
    }

    public void setConceptoRips(String conceptoRips) {
        this.conceptoRips = conceptoRips;
    }

    public String getCantidadServicios() {
        return cantidadServicios;
    }

    public void setCantidadServicios(String cantidadServicios) {
        this.cantidadServicios = cantidadServicios;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getValorConcepto() {
        return valorConcepto;
    }

    public void setValorConcepto(String valorConcepto) {
        this.valorConcepto = valorConcepto;
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
        if (!(object instanceof CmRipsCargaAdServiciosAgrupados)) {
            return false;
        }
        CmRipsCargaAdServiciosAgrupados other = (CmRipsCargaAdServiciosAgrupados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsCargaAdServiciosAgrupados[ id=" + id + " ]";
    }
    
}
