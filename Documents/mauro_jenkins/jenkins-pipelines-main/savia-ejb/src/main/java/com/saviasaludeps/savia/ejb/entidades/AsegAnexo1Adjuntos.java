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
@Table(name = "aseg_anexo1_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegAnexo1Adjuntos.findAll", query = "SELECT a FROM AsegAnexo1Adjuntos a"),
    @NamedQuery(name = "AsegAnexo1Adjuntos.findById", query = "SELECT a FROM AsegAnexo1Adjuntos a WHERE a.id = :id"),
    @NamedQuery(name = "AsegAnexo1Adjuntos.findByArchivo", query = "SELECT a FROM AsegAnexo1Adjuntos a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AsegAnexo1Adjuntos.findByRuta", query = "SELECT a FROM AsegAnexo1Adjuntos a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AsegAnexo1Adjuntos.findByUsuarioCrea", query = "SELECT a FROM AsegAnexo1Adjuntos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegAnexo1Adjuntos.findByTerminalCrea", query = "SELECT a FROM AsegAnexo1Adjuntos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegAnexo1Adjuntos.findByFechaHoraCrea", query = "SELECT a FROM AsegAnexo1Adjuntos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AsegAnexo1Adjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ruta")
    private String ruta;
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
    @JoinColumn(name = "aseg_anexos1_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAnexos1 asegAnexos1Id;

    public AsegAnexo1Adjuntos() {
    }

    public AsegAnexo1Adjuntos(Integer id) {
        this.id = id;
    }

    public AsegAnexo1Adjuntos(Integer id, String archivo, String ruta, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.archivo = archivo;
        this.ruta = ruta;
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

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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

    public AsegAnexos1 getAsegAnexos1Id() {
        return asegAnexos1Id;
    }

    public void setAsegAnexos1Id(AsegAnexos1 asegAnexos1Id) {
        this.asegAnexos1Id = asegAnexos1Id;
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
        if (!(object instanceof AsegAnexo1Adjuntos)) {
            return false;
        }
        AsegAnexo1Adjuntos other = (AsegAnexo1Adjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegAnexo1Adjuntos[ id=" + id + " ]";
    }
    
}
