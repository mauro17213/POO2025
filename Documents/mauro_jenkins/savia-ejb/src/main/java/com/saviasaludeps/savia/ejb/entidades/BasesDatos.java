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
@Table(name = "bases_datos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BasesDatos.findAll", query = "SELECT b FROM BasesDatos b"),
    @NamedQuery(name = "BasesDatos.findById", query = "SELECT b FROM BasesDatos b WHERE b.id = :id"),
    @NamedQuery(name = "BasesDatos.findByTipo", query = "SELECT b FROM BasesDatos b WHERE b.tipo = :tipo"),
    @NamedQuery(name = "BasesDatos.findByCadena", query = "SELECT b FROM BasesDatos b WHERE b.cadena = :cadena"),
    @NamedQuery(name = "BasesDatos.findByNombre", query = "SELECT b FROM BasesDatos b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "BasesDatos.findByUsuario", query = "SELECT b FROM BasesDatos b WHERE b.usuario = :usuario"),
    @NamedQuery(name = "BasesDatos.findByContrasena", query = "SELECT b FROM BasesDatos b WHERE b.contrasena = :contrasena"),
    @NamedQuery(name = "BasesDatos.findByUsuarioCrea", query = "SELECT b FROM BasesDatos b WHERE b.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "BasesDatos.findByTerminalCrea", query = "SELECT b FROM BasesDatos b WHERE b.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "BasesDatos.findByFechaHoraCrea", query = "SELECT b FROM BasesDatos b WHERE b.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "BasesDatos.findByUsuarioModifica", query = "SELECT b FROM BasesDatos b WHERE b.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "BasesDatos.findByTerminalModifica", query = "SELECT b FROM BasesDatos b WHERE b.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "BasesDatos.findByFechaHoraModifica", query = "SELECT b FROM BasesDatos b WHERE b.fechaHoraModifica = :fechaHoraModifica")})
public class BasesDatos implements Serializable {

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
    @Size(min = 1, max = 128)
    @Column(name = "cadena")
    private String cadena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "contrasena")
    private String contrasena;
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
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;

    public BasesDatos() {
    }

    public BasesDatos(Integer id) {
        this.id = id;
    }

    public BasesDatos(Integer id, int tipo, String cadena, String nombre, String usuario, String contrasena, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.cadena = cadena;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
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
        if (!(object instanceof BasesDatos)) {
            return false;
        }
        BasesDatos other = (BasesDatos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.BasesDatos[ id=" + id + " ]";
    }
    
}
