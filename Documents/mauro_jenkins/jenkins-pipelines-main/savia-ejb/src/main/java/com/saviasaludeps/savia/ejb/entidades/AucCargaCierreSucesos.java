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
@Table(name = "auc_carga_cierre_sucesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucCargaCierreSucesos.findAll", query = "SELECT a FROM AucCargaCierreSucesos a"),
    @NamedQuery(name = "AucCargaCierreSucesos.findById", query = "SELECT a FROM AucCargaCierreSucesos a WHERE a.id = :id"),
    @NamedQuery(name = "AucCargaCierreSucesos.findByTipo", query = "SELECT a FROM AucCargaCierreSucesos a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AucCargaCierreSucesos.findByDescripcion", query = "SELECT a FROM AucCargaCierreSucesos a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AucCargaCierreSucesos.findByFila", query = "SELECT a FROM AucCargaCierreSucesos a WHERE a.fila = :fila"),
    @NamedQuery(name = "AucCargaCierreSucesos.findByColumna", query = "SELECT a FROM AucCargaCierreSucesos a WHERE a.columna = :columna"),
    @NamedQuery(name = "AucCargaCierreSucesos.findByUsuarioCrea", query = "SELECT a FROM AucCargaCierreSucesos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucCargaCierreSucesos.findByTerminalCrea", query = "SELECT a FROM AucCargaCierreSucesos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucCargaCierreSucesos.findByFechaHoraCrea", query = "SELECT a FROM AucCargaCierreSucesos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AucCargaCierreSucesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private short tipo;
    @Size(max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fila")
    private short fila;
    @Basic(optional = false)
    @NotNull
    @Column(name = "columna")
    private short columna;
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
    @JoinColumn(name = "auc_carga_cierres_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AucCargaCierres aucCargaCierresId;

    public AucCargaCierreSucesos() {
    }

    public AucCargaCierreSucesos(Integer id) {
        this.id = id;
    }

    public AucCargaCierreSucesos(Integer id, short tipo, short fila, short columna, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
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

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getFila() {
        return fila;
    }

    public void setFila(short fila) {
        this.fila = fila;
    }

    public short getColumna() {
        return columna;
    }

    public void setColumna(short columna) {
        this.columna = columna;
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

    public AucCargaCierres getAucCargaCierresId() {
        return aucCargaCierresId;
    }

    public void setAucCargaCierresId(AucCargaCierres aucCargaCierresId) {
        this.aucCargaCierresId = aucCargaCierresId;
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
        if (!(object instanceof AucCargaCierreSucesos)) {
            return false;
        }
        AucCargaCierreSucesos other = (AucCargaCierreSucesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucCargaCierreSucesos[ id=" + id + " ]";
    }
    
}
