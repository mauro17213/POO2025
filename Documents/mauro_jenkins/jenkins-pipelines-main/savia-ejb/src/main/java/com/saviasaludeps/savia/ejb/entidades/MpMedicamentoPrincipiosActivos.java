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
@Table(name = "mp_medicamento_principios_activos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findAll", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m"),
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findById", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m WHERE m.id = :id"),
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findByConsecutivoOrden", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m WHERE m.consecutivoOrden = :consecutivoOrden"),
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findByCodigoPrincipioActivo", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m WHERE m.codigoPrincipioActivo = :codigoPrincipioActivo"),
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findByConcecutivoCantidad", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m WHERE m.concecutivoCantidad = :concecutivoCantidad"),
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findByUnidadMedidaConcentracion", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m WHERE m.unidadMedidaConcentracion = :unidadMedidaConcentracion"),
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findByCantidadContenido", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m WHERE m.cantidadContenido = :cantidadContenido"),
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findByUnidadCantidadContenido", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m WHERE m.unidadCantidadContenido = :unidadCantidadContenido"),
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findByUsuarioCrea", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findByTerminalCrea", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpMedicamentoPrincipiosActivos.findByFechaHoraCrea", query = "SELECT m FROM MpMedicamentoPrincipiosActivos m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpMedicamentoPrincipiosActivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "consecutivo_orden")
    private Integer consecutivoOrden;
    @Size(max = 16)
    @Column(name = "codigo_principio_activo")
    private String codigoPrincipioActivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "concecutivo_cantidad")
    private BigDecimal concecutivoCantidad;
    @Size(max = 16)
    @Column(name = "unidad_medida_concentracion")
    private String unidadMedidaConcentracion;
    @Column(name = "cantidad_contenido")
    private BigDecimal cantidadContenido;
    @Size(max = 16)
    @Column(name = "unidad_cantidad_contenido")
    private String unidadCantidadContenido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "mp_prescripcion_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpPrescripcionMedicamentos mpPrescripcionMedicamentosId;

    public MpMedicamentoPrincipiosActivos() {
    }

    public MpMedicamentoPrincipiosActivos(Integer id) {
        this.id = id;
    }

    public MpMedicamentoPrincipiosActivos(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public Integer getConsecutivoOrden() {
        return consecutivoOrden;
    }

    public void setConsecutivoOrden(Integer consecutivoOrden) {
        this.consecutivoOrden = consecutivoOrden;
    }

    public String getCodigoPrincipioActivo() {
        return codigoPrincipioActivo;
    }

    public void setCodigoPrincipioActivo(String codigoPrincipioActivo) {
        this.codigoPrincipioActivo = codigoPrincipioActivo;
    }

    public BigDecimal getConcecutivoCantidad() {
        return concecutivoCantidad;
    }

    public void setConcecutivoCantidad(BigDecimal concecutivoCantidad) {
        this.concecutivoCantidad = concecutivoCantidad;
    }

    public String getUnidadMedidaConcentracion() {
        return unidadMedidaConcentracion;
    }

    public void setUnidadMedidaConcentracion(String unidadMedidaConcentracion) {
        this.unidadMedidaConcentracion = unidadMedidaConcentracion;
    }

    public BigDecimal getCantidadContenido() {
        return cantidadContenido;
    }

    public void setCantidadContenido(BigDecimal cantidadContenido) {
        this.cantidadContenido = cantidadContenido;
    }

    public String getUnidadCantidadContenido() {
        return unidadCantidadContenido;
    }

    public void setUnidadCantidadContenido(String unidadCantidadContenido) {
        this.unidadCantidadContenido = unidadCantidadContenido;
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

    public MpPrescripcionMedicamentos getMpPrescripcionMedicamentosId() {
        return mpPrescripcionMedicamentosId;
    }

    public void setMpPrescripcionMedicamentosId(MpPrescripcionMedicamentos mpPrescripcionMedicamentosId) {
        this.mpPrescripcionMedicamentosId = mpPrescripcionMedicamentosId;
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
        if (!(object instanceof MpMedicamentoPrincipiosActivos)) {
            return false;
        }
        MpMedicamentoPrincipiosActivos other = (MpMedicamentoPrincipiosActivos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpMedicamentoPrincipiosActivos[ id=" + id + " ]";
    }
    
}
