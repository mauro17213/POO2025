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
@Table(name = "auc_carga_fallos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucCargaFallos.findAll", query = "SELECT a FROM AucCargaFallos a"),
    @NamedQuery(name = "AucCargaFallos.findById", query = "SELECT a FROM AucCargaFallos a WHERE a.id = :id"),
    @NamedQuery(name = "AucCargaFallos.findByTipo", query = "SELECT a FROM AucCargaFallos a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AucCargaFallos.findByDescripcion", query = "SELECT a FROM AucCargaFallos a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AucCargaFallos.findByFila", query = "SELECT a FROM AucCargaFallos a WHERE a.fila = :fila"),
    @NamedQuery(name = "AucCargaFallos.findByColumna", query = "SELECT a FROM AucCargaFallos a WHERE a.columna = :columna"),
    @NamedQuery(name = "AucCargaFallos.findByFechaHora", query = "SELECT a FROM AucCargaFallos a WHERE a.fechaHora = :fechaHora")})
public class AucCargaFallos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fila")
    private int fila;
    @Basic(optional = false)
    @NotNull
    @Column(name = "columna")
    private int columna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "auc_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AucCargas aucCargasId;

    public AucCargaFallos() {
    }

    public AucCargaFallos(Integer id) {
        this.id = id;
    }

    public AucCargaFallos(Integer id, int tipo, String descripcion, int fila, int columna, Date fechaHora) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fila = fila;
        this.columna = columna;
        this.fechaHora = fechaHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public AucCargas getAucCargasId() {
        return aucCargasId;
    }

    public void setAucCargasId(AucCargas aucCargasId) {
        this.aucCargasId = aucCargasId;
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
        if (!(object instanceof AucCargaFallos)) {
            return false;
        }
        AucCargaFallos other = (AucCargaFallos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucCargaFallos[ id=" + id + " ]";
    }
    
}
