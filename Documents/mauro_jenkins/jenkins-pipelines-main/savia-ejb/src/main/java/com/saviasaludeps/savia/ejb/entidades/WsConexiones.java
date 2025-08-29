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
@Table(name = "ws_conexiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WsConexiones.findAll", query = "SELECT w FROM WsConexiones w"),
    @NamedQuery(name = "WsConexiones.findById", query = "SELECT w FROM WsConexiones w WHERE w.id = :id"),
    @NamedQuery(name = "WsConexiones.findByNombre", query = "SELECT w FROM WsConexiones w WHERE w.nombre = :nombre"),
    @NamedQuery(name = "WsConexiones.findByTipoAutenticacion", query = "SELECT w FROM WsConexiones w WHERE w.tipoAutenticacion = :tipoAutenticacion"),
    @NamedQuery(name = "WsConexiones.findByUsuario", query = "SELECT w FROM WsConexiones w WHERE w.usuario = :usuario"),
    @NamedQuery(name = "WsConexiones.findByContrasena", query = "SELECT w FROM WsConexiones w WHERE w.contrasena = :contrasena"),
    @NamedQuery(name = "WsConexiones.findByLlave", query = "SELECT w FROM WsConexiones w WHERE w.llave = :llave"),
    @NamedQuery(name = "WsConexiones.findByIp", query = "SELECT w FROM WsConexiones w WHERE w.ip = :ip"),
    @NamedQuery(name = "WsConexiones.findByActivo", query = "SELECT w FROM WsConexiones w WHERE w.activo = :activo"),
    @NamedQuery(name = "WsConexiones.findByUsuarioCrea", query = "SELECT w FROM WsConexiones w WHERE w.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "WsConexiones.findByTerminalCrea", query = "SELECT w FROM WsConexiones w WHERE w.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "WsConexiones.findByFechaHoraCrea", query = "SELECT w FROM WsConexiones w WHERE w.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "WsConexiones.findByUsuarioModifica", query = "SELECT w FROM WsConexiones w WHERE w.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "WsConexiones.findByTerminalModifica", query = "SELECT w FROM WsConexiones w WHERE w.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "WsConexiones.findByFechaHoraModifica", query = "SELECT w FROM WsConexiones w WHERE w.fechaHoraModifica = :fechaHoraModifica")})
public class WsConexiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_autenticacion")
    private int tipoAutenticacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "contrasena")
    private String contrasena;
    @Size(max = 512)
    @Column(name = "llave")
    private String llave;
    @Size(max = 16)
    @Column(name = "ip")
    private String ip;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wsConexionesId", fetch = FetchType.LAZY)
    private List<WsTransacciones> wsTransaccionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wsConexionesId", fetch = FetchType.LAZY)
    private List<WsConexionesServicios> wsConexionesServiciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wsConexionesId", fetch = FetchType.LAZY)
    private List<WsTokens> wsTokensList;

    public WsConexiones() {
    }

    public WsConexiones(Integer id) {
        this.id = id;
    }

    public WsConexiones(Integer id, String nombre, int tipoAutenticacion, String usuario, String contrasena, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.tipoAutenticacion = tipoAutenticacion;
        this.usuario = usuario;
        this.contrasena = contrasena;
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

    public int getTipoAutenticacion() {
        return tipoAutenticacion;
    }

    public void setTipoAutenticacion(int tipoAutenticacion) {
        this.tipoAutenticacion = tipoAutenticacion;
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

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    @XmlTransient
    public List<WsTokens> getWsTokensList() {
        return wsTokensList;
    }

    public void setWsTokensList(List<WsTokens> wsTokensList) {
        this.wsTokensList = wsTokensList;
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
        if (!(object instanceof WsConexiones)) {
            return false;
        }
        WsConexiones other = (WsConexiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.WsConexiones[ id=" + id + " ]";
    }
    
}
