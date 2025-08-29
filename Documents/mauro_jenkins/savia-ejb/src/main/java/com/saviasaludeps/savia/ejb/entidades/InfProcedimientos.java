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
import javax.persistence.Lob;
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
@Table(name = "inf_procedimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfProcedimientos.findAll", query = "SELECT i FROM InfProcedimientos i"),
    @NamedQuery(name = "InfProcedimientos.findById", query = "SELECT i FROM InfProcedimientos i WHERE i.id = :id"),
    @NamedQuery(name = "InfProcedimientos.findByNombreScript", query = "SELECT i FROM InfProcedimientos i WHERE i.nombreScript = :nombreScript"),
    @NamedQuery(name = "InfProcedimientos.findByExitoso", query = "SELECT i FROM InfProcedimientos i WHERE i.exitoso = :exitoso"),
    @NamedQuery(name = "InfProcedimientos.findByDescripcion", query = "SELECT i FROM InfProcedimientos i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "InfProcedimientos.findByUsuariosCrear", query = "SELECT i FROM InfProcedimientos i WHERE i.usuariosCrear = :usuariosCrear"),
    @NamedQuery(name = "InfProcedimientos.findByTerminalCrea", query = "SELECT i FROM InfProcedimientos i WHERE i.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "InfProcedimientos.findByFechaHoraCrea", query = "SELECT i FROM InfProcedimientos i WHERE i.fechaHoraCrea = :fechaHoraCrea")})
public class InfProcedimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre_script")
    private String nombreScript;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "script")
    private byte[] script;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exitoso")
    private boolean exitoso;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuarios_crear")
    private String usuariosCrear;
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

    public InfProcedimientos() {
    }

    public InfProcedimientos(Integer id) {
        this.id = id;
    }

    public InfProcedimientos(Integer id, String nombreScript, byte[] script, boolean exitoso, String usuariosCrear, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombreScript = nombreScript;
        this.script = script;
        this.exitoso = exitoso;
        this.usuariosCrear = usuariosCrear;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreScript() {
        return nombreScript;
    }

    public void setNombreScript(String nombreScript) {
        this.nombreScript = nombreScript;
    }

    public byte[] getScript() {
        return script;
    }

    public void setScript(byte[] script) {
        this.script = script;
    }

    public boolean getExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuariosCrear() {
        return usuariosCrear;
    }

    public void setUsuariosCrear(String usuariosCrear) {
        this.usuariosCrear = usuariosCrear;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfProcedimientos)) {
            return false;
        }
        InfProcedimientos other = (InfProcedimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.InfProcedimientos[ id=" + id + " ]";
    }
    
}
