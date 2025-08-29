/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "rco_actas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RcoActas.findAll", query = "SELECT r FROM RcoActas r"),
    @NamedQuery(name = "RcoActas.findById", query = "SELECT r FROM RcoActas r WHERE r.id = :id"),
    @NamedQuery(name = "RcoActas.findByArea", query = "SELECT r FROM RcoActas r WHERE r.area = :area"),
    @NamedQuery(name = "RcoActas.findByAsunto", query = "SELECT r FROM RcoActas r WHERE r.asunto = :asunto"),
    @NamedQuery(name = "RcoActas.findByLugar", query = "SELECT r FROM RcoActas r WHERE r.lugar = :lugar"),
    @NamedQuery(name = "RcoActas.findByUsuarioCrea", query = "SELECT r FROM RcoActas r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RcoActas.findByTerminalCrea", query = "SELECT r FROM RcoActas r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RcoActas.findByFechaHoraCrea", query = "SELECT r FROM RcoActas r WHERE r.fechaHoraCrea = :fechaHoraCrea")})
public class RcoActas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "area")
    private String area;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "asunto")
    private String asunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "lugar")
    private String lugar;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "order_del_dia")
    private String orderDelDia;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "desarrollo_reunion")
    private String desarrolloReunion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "observaciones")
    private String observaciones;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rcoActasId", fetch = FetchType.LAZY)
    private List<RcoActaAsistentes> rcoActaAsistentesList;
    @JoinColumn(name = "rco_conciliaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RcoConciliaciones rcoConciliacionesId;

    public RcoActas() {
    }

    public RcoActas(Integer id) {
        this.id = id;
    }

    public RcoActas(Integer id, String area, String asunto, String lugar, String orderDelDia, String desarrolloReunion, String observaciones, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.area = area;
        this.asunto = asunto;
        this.lugar = lugar;
        this.orderDelDia = orderDelDia;
        this.desarrolloReunion = desarrolloReunion;
        this.observaciones = observaciones;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getOrderDelDia() {
        return orderDelDia;
    }

    public void setOrderDelDia(String orderDelDia) {
        this.orderDelDia = orderDelDia;
    }

    public String getDesarrolloReunion() {
        return desarrolloReunion;
    }

    public void setDesarrolloReunion(String desarrolloReunion) {
        this.desarrolloReunion = desarrolloReunion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    @XmlTransient
    public List<RcoActaAsistentes> getRcoActaAsistentesList() {
        return rcoActaAsistentesList;
    }

    public void setRcoActaAsistentesList(List<RcoActaAsistentes> rcoActaAsistentesList) {
        this.rcoActaAsistentesList = rcoActaAsistentesList;
    }

    public RcoConciliaciones getRcoConciliacionesId() {
        return rcoConciliacionesId;
    }

    public void setRcoConciliacionesId(RcoConciliaciones rcoConciliacionesId) {
        this.rcoConciliacionesId = rcoConciliacionesId;
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
        if (!(object instanceof RcoActas)) {
            return false;
        }
        RcoActas other = (RcoActas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RcoActas[ id=" + id + " ]";
    }
    
}
