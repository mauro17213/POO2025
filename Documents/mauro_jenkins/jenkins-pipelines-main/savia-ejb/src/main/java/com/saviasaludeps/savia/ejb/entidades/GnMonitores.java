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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "gn_monitores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnMonitores.findAll", query = "SELECT g FROM GnMonitores g"),
    @NamedQuery(name = "GnMonitores.findById", query = "SELECT g FROM GnMonitores g WHERE g.id = :id"),
    @NamedQuery(name = "GnMonitores.findByFecha", query = "SELECT g FROM GnMonitores g WHERE g.fecha = :fecha"),
    @NamedQuery(name = "GnMonitores.findByHora", query = "SELECT g FROM GnMonitores g WHERE g.hora = :hora"),
    @NamedQuery(name = "GnMonitores.findByFechaHora", query = "SELECT g FROM GnMonitores g WHERE g.fechaHora = :fechaHora"),
    @NamedQuery(name = "GnMonitores.findByA3Inter", query = "SELECT g FROM GnMonitores g WHERE g.a3Inter = :a3Inter"),
    @NamedQuery(name = "GnMonitores.findByA3Manual", query = "SELECT g FROM GnMonitores g WHERE g.a3Manual = :a3Manual"),
    @NamedQuery(name = "GnMonitores.findByA3Masiva", query = "SELECT g FROM GnMonitores g WHERE g.a3Masiva = :a3Masiva"),
    @NamedQuery(name = "GnMonitores.findByA3Total", query = "SELECT g FROM GnMonitores g WHERE g.a3Total = :a3Total"),
    @NamedQuery(name = "GnMonitores.findByA2Inter", query = "SELECT g FROM GnMonitores g WHERE g.a2Inter = :a2Inter"),
    @NamedQuery(name = "GnMonitores.findByA2Manual", query = "SELECT g FROM GnMonitores g WHERE g.a2Manual = :a2Manual"),
    @NamedQuery(name = "GnMonitores.findByA2Total", query = "SELECT g FROM GnMonitores g WHERE g.a2Total = :a2Total"),
    @NamedQuery(name = "GnMonitores.findByA9Inter", query = "SELECT g FROM GnMonitores g WHERE g.a9Inter = :a9Inter"),
    @NamedQuery(name = "GnMonitores.findByA9Manual", query = "SELECT g FROM GnMonitores g WHERE g.a9Manual = :a9Manual"),
    @NamedQuery(name = "GnMonitores.findByA9Total", query = "SELECT g FROM GnMonitores g WHERE g.a9Total = :a9Total"),
    @NamedQuery(name = "GnMonitores.findByGnMonitorescol", query = "SELECT g FROM GnMonitores g WHERE g.gnMonitorescol = :gnMonitorescol")})
public class GnMonitores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a3_inter")
    private int a3Inter;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a3_manual")
    private int a3Manual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a3_masiva")
    private int a3Masiva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a3_total")
    private int a3Total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a2_inter")
    private int a2Inter;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a2_manual")
    private int a2Manual;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a2_total")
    private int a2Total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "a9_inter")
    private int a9Inter;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "a9_manual")
    private String a9Manual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "a9_total")
    private String a9Total;
    @Size(max = 45)
    @Column(name = "gn_monitorescol")
    private String gnMonitorescol;

    public GnMonitores() {
    }

    public GnMonitores(Integer id) {
        this.id = id;
    }

    public GnMonitores(Integer id, Date hora, Date fechaHora, int a3Inter, int a3Manual, int a3Masiva, int a3Total, int a2Inter, int a2Manual, int a2Total, int a9Inter, String a9Manual, String a9Total) {
        this.id = id;
        this.hora = hora;
        this.fechaHora = fechaHora;
        this.a3Inter = a3Inter;
        this.a3Manual = a3Manual;
        this.a3Masiva = a3Masiva;
        this.a3Total = a3Total;
        this.a2Inter = a2Inter;
        this.a2Manual = a2Manual;
        this.a2Total = a2Total;
        this.a9Inter = a9Inter;
        this.a9Manual = a9Manual;
        this.a9Total = a9Total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getA3Inter() {
        return a3Inter;
    }

    public void setA3Inter(int a3Inter) {
        this.a3Inter = a3Inter;
    }

    public int getA3Manual() {
        return a3Manual;
    }

    public void setA3Manual(int a3Manual) {
        this.a3Manual = a3Manual;
    }

    public int getA3Masiva() {
        return a3Masiva;
    }

    public void setA3Masiva(int a3Masiva) {
        this.a3Masiva = a3Masiva;
    }

    public int getA3Total() {
        return a3Total;
    }

    public void setA3Total(int a3Total) {
        this.a3Total = a3Total;
    }

    public int getA2Inter() {
        return a2Inter;
    }

    public void setA2Inter(int a2Inter) {
        this.a2Inter = a2Inter;
    }

    public int getA2Manual() {
        return a2Manual;
    }

    public void setA2Manual(int a2Manual) {
        this.a2Manual = a2Manual;
    }

    public int getA2Total() {
        return a2Total;
    }

    public void setA2Total(int a2Total) {
        this.a2Total = a2Total;
    }

    public int getA9Inter() {
        return a9Inter;
    }

    public void setA9Inter(int a9Inter) {
        this.a9Inter = a9Inter;
    }

    public String getA9Manual() {
        return a9Manual;
    }

    public void setA9Manual(String a9Manual) {
        this.a9Manual = a9Manual;
    }

    public String getA9Total() {
        return a9Total;
    }

    public void setA9Total(String a9Total) {
        this.a9Total = a9Total;
    }

    public String getGnMonitorescol() {
        return gnMonitorescol;
    }

    public void setGnMonitorescol(String gnMonitorescol) {
        this.gnMonitorescol = gnMonitorescol;
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
        if (!(object instanceof GnMonitores)) {
            return false;
        }
        GnMonitores other = (GnMonitores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnMonitores[ id=" + id + " ]";
    }
    
}
