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
import javax.persistence.Id;
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
@Table(name = "aseg_encuesta_preguntas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegEncuestaPreguntas.findAll", query = "SELECT a FROM AsegEncuestaPreguntas a"),
    @NamedQuery(name = "AsegEncuestaPreguntas.findById", query = "SELECT a FROM AsegEncuestaPreguntas a WHERE a.id = :id"),
    @NamedQuery(name = "AsegEncuestaPreguntas.findByPregunta", query = "SELECT a FROM AsegEncuestaPreguntas a WHERE a.pregunta = :pregunta"),
    @NamedQuery(name = "AsegEncuestaPreguntas.findByNorma", query = "SELECT a FROM AsegEncuestaPreguntas a WHERE a.norma = :norma"),
    @NamedQuery(name = "AsegEncuestaPreguntas.findByEstado", query = "SELECT a FROM AsegEncuestaPreguntas a WHERE a.estado = :estado"),
    @NamedQuery(name = "AsegEncuestaPreguntas.findByUsuarioCrea", query = "SELECT a FROM AsegEncuestaPreguntas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegEncuestaPreguntas.findByTerminalCrea", query = "SELECT a FROM AsegEncuestaPreguntas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegEncuestaPreguntas.findByFechaHoraCrea", query = "SELECT a FROM AsegEncuestaPreguntas a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AsegEncuestaPreguntas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "pregunta")
    private String pregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "norma")
    private String norma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegEncuestaPreguntasId", fetch = FetchType.LAZY)
    private List<AsegTabulacionEncuestas> asegTabulacionEncuestasList;

    public AsegEncuestaPreguntas() {
    }

    public AsegEncuestaPreguntas(Integer id) {
        this.id = id;
    }

    public AsegEncuestaPreguntas(Integer id, String pregunta, String norma, boolean estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.pregunta = pregunta;
        this.norma = norma;
        this.estado = estado;
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

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getNorma() {
        return norma;
    }

    public void setNorma(String norma) {
        this.norma = norma;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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
    public List<AsegTabulacionEncuestas> getAsegTabulacionEncuestasList() {
        return asegTabulacionEncuestasList;
    }

    public void setAsegTabulacionEncuestasList(List<AsegTabulacionEncuestas> asegTabulacionEncuestasList) {
        this.asegTabulacionEncuestasList = asegTabulacionEncuestasList;
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
        if (!(object instanceof AsegEncuestaPreguntas)) {
            return false;
        }
        AsegEncuestaPreguntas other = (AsegEncuestaPreguntas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegEncuestaPreguntas[ id=" + id + " ]";
    }
    
}
