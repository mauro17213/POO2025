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
@Table(name = "ws_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WsServicios.findAll", query = "SELECT w FROM WsServicios w"),
    @NamedQuery(name = "WsServicios.findById", query = "SELECT w FROM WsServicios w WHERE w.id = :id"),
    @NamedQuery(name = "WsServicios.findByNombre", query = "SELECT w FROM WsServicios w WHERE w.nombre = :nombre"),
    @NamedQuery(name = "WsServicios.findByDescripcion", query = "SELECT w FROM WsServicios w WHERE w.descripcion = :descripcion"),
    @NamedQuery(name = "WsServicios.findByTiempoCont", query = "SELECT w FROM WsServicios w WHERE w.tiempoCont = :tiempoCont"),
    @NamedQuery(name = "WsServicios.findByCont", query = "SELECT w FROM WsServicios w WHERE w.cont = :cont"),
    @NamedQuery(name = "WsServicios.findByActivo", query = "SELECT w FROM WsServicios w WHERE w.activo = :activo"),
    @NamedQuery(name = "WsServicios.findByUsuarioCrea", query = "SELECT w FROM WsServicios w WHERE w.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "WsServicios.findByTerminalCrea", query = "SELECT w FROM WsServicios w WHERE w.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "WsServicios.findByFechaHoraCrea", query = "SELECT w FROM WsServicios w WHERE w.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "WsServicios.findByUsuarioModifica", query = "SELECT w FROM WsServicios w WHERE w.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "WsServicios.findByTerminalModifica", query = "SELECT w FROM WsServicios w WHERE w.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "WsServicios.findByFechaHoraModifica", query = "SELECT w FROM WsServicios w WHERE w.fechaHoraModifica = :fechaHoraModifica")})
public class WsServicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo_cont")
    private int tiempoCont;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cont")
    private boolean cont;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wsServiciosId", fetch = FetchType.LAZY)
    private List<WsTransacciones> wsTransaccionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wsServiciosId", fetch = FetchType.LAZY)
    private List<WsConexionesServicios> wsConexionesServiciosList;

    public WsServicios() {
    }

    public WsServicios(Integer id) {
        this.id = id;
    }

    public WsServicios(Integer id, String nombre, int tiempoCont, boolean cont, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoCont = tiempoCont;
        this.cont = cont;
        this.activo = activo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTiempoCont() {
        return tiempoCont;
    }

    public void setTiempoCont(int tiempoCont) {
        this.tiempoCont = tiempoCont;
    }

    public boolean getCont() {
        return cont;
    }

    public void setCont(boolean cont) {
        this.cont = cont;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    @XmlTransient
    public List<WsTransacciones> getWsTransaccionesList() {
        return wsTransaccionesList;
    }

    public void setWsTransaccionesList(List<WsTransacciones> wsTransaccionesList) {
        this.wsTransaccionesList = wsTransaccionesList;
    }

    @XmlTransient
    public List<WsConexionesServicios> getWsConexionesServiciosList() {
        return wsConexionesServiciosList;
    }

    public void setWsConexionesServiciosList(List<WsConexionesServicios> wsConexionesServiciosList) {
        this.wsConexionesServiciosList = wsConexionesServiciosList;
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
        if (!(object instanceof WsServicios)) {
            return false;
        }
        WsServicios other = (WsServicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.WsServicios[ id=" + id + " ]";
    }
    
}
