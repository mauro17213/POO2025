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
import javax.persistence.Lob;
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
@Table(name = "mp_consumo_fallos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpConsumoFallos.findAll", query = "SELECT m FROM MpConsumoFallos m"),
    @NamedQuery(name = "MpConsumoFallos.findById", query = "SELECT m FROM MpConsumoFallos m WHERE m.id = :id"),
    @NamedQuery(name = "MpConsumoFallos.findByIdProceso", query = "SELECT m FROM MpConsumoFallos m WHERE m.idProceso = :idProceso"),
    @NamedQuery(name = "MpConsumoFallos.findByFechaProceso", query = "SELECT m FROM MpConsumoFallos m WHERE m.fechaProceso = :fechaProceso"),
    @NamedQuery(name = "MpConsumoFallos.findByDescripcion", query = "SELECT m FROM MpConsumoFallos m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MpConsumoFallos.findByEstado", query = "SELECT m FROM MpConsumoFallos m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpConsumoFallos.findByFechaHoraFallo", query = "SELECT m FROM MpConsumoFallos m WHERE m.fechaHoraFallo = :fechaHoraFallo"),
    @NamedQuery(name = "MpConsumoFallos.findByFechaHoraCorreccion", query = "SELECT m FROM MpConsumoFallos m WHERE m.fechaHoraCorreccion = :fechaHoraCorreccion"),
    @NamedQuery(name = "MpConsumoFallos.findByFechaHoraResincronizacin", query = "SELECT m FROM MpConsumoFallos m WHERE m.fechaHoraResincronizacin = :fechaHoraResincronizacin")})
public class MpConsumoFallos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proceso")
    private int idProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_proceso")
    @Temporal(TemporalType.DATE)
    private Date fechaProceso;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Column(name = "json")
    private byte[] json;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_fallo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFallo;
    @Column(name = "fecha_hora_correccion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCorreccion;
    @Column(name = "fecha_hora_resincronizacin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraResincronizacin;
    @JoinColumn(name = "mp_consumos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpConsumos mpConsumosId;

    public MpConsumoFallos() {
    }

    public MpConsumoFallos(Integer id) {
        this.id = id;
    }

    public MpConsumoFallos(Integer id, int idProceso, Date fechaProceso, short estado, Date fechaHoraFallo) {
        this.id = id;
        this.idProceso = idProceso;
        this.fechaProceso = fechaProceso;
        this.estado = estado;
        this.fechaHoraFallo = fechaHoraFallo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getJson() {
        return json;
    }

    public void setJson(byte[] json) {
        this.json = json;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Date getFechaHoraFallo() {
        return fechaHoraFallo;
    }

    public void setFechaHoraFallo(Date fechaHoraFallo) {
        this.fechaHoraFallo = fechaHoraFallo;
    }

    public Date getFechaHoraCorreccion() {
        return fechaHoraCorreccion;
    }

    public void setFechaHoraCorreccion(Date fechaHoraCorreccion) {
        this.fechaHoraCorreccion = fechaHoraCorreccion;
    }

    public Date getFechaHoraResincronizacin() {
        return fechaHoraResincronizacin;
    }

    public void setFechaHoraResincronizacin(Date fechaHoraResincronizacin) {
        this.fechaHoraResincronizacin = fechaHoraResincronizacin;
    }

    public MpConsumos getMpConsumosId() {
        return mpConsumosId;
    }

    public void setMpConsumosId(MpConsumos mpConsumosId) {
        this.mpConsumosId = mpConsumosId;
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
        if (!(object instanceof MpConsumoFallos)) {
            return false;
        }
        MpConsumoFallos other = (MpConsumoFallos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpConsumoFallos[ id=" + id + " ]";
    }
    
}
