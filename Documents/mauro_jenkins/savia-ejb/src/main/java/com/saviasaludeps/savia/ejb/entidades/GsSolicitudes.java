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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "gs_solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GsSolicitudes.findAll", query = "SELECT g FROM GsSolicitudes g"),
    @NamedQuery(name = "GsSolicitudes.findById", query = "SELECT g FROM GsSolicitudes g WHERE g.id = :id"),
    @NamedQuery(name = "GsSolicitudes.findByTipo", query = "SELECT g FROM GsSolicitudes g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GsSolicitudes.findByNombre", query = "SELECT g FROM GsSolicitudes g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GsSolicitudes.findByDescripcion", query = "SELECT g FROM GsSolicitudes g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GsSolicitudes.findByObservacion", query = "SELECT g FROM GsSolicitudes g WHERE g.observacion = :observacion"),
    @NamedQuery(name = "GsSolicitudes.findByEstado", query = "SELECT g FROM GsSolicitudes g WHERE g.estado = :estado"),
    @NamedQuery(name = "GsSolicitudes.findByNotificacion", query = "SELECT g FROM GsSolicitudes g WHERE g.notificacion = :notificacion"),
    @NamedQuery(name = "GsSolicitudes.findByContactoTelefono", query = "SELECT g FROM GsSolicitudes g WHERE g.contactoTelefono = :contactoTelefono"),
    @NamedQuery(name = "GsSolicitudes.findByContactoCelular", query = "SELECT g FROM GsSolicitudes g WHERE g.contactoCelular = :contactoCelular"),
    @NamedQuery(name = "GsSolicitudes.findByContactoCorreoElectronico", query = "SELECT g FROM GsSolicitudes g WHERE g.contactoCorreoElectronico = :contactoCorreoElectronico"),
    @NamedQuery(name = "GsSolicitudes.findByTramiteInterno", query = "SELECT g FROM GsSolicitudes g WHERE g.tramiteInterno = :tramiteInterno"),
    @NamedQuery(name = "GsSolicitudes.findByRespuestaReferencia", query = "SELECT g FROM GsSolicitudes g WHERE g.respuestaReferencia = :respuestaReferencia"),
    @NamedQuery(name = "GsSolicitudes.findByFechaHoraCrea", query = "SELECT g FROM GsSolicitudes g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GsSolicitudes.findByFechaHoraAtiende", query = "SELECT g FROM GsSolicitudes g WHERE g.fechaHoraAtiende = :fechaHoraAtiende"),
    @NamedQuery(name = "GsSolicitudes.findByUsuarioAtiende", query = "SELECT g FROM GsSolicitudes g WHERE g.usuarioAtiende = :usuarioAtiende"),
    @NamedQuery(name = "GsSolicitudes.findByFechaHoraReasigna", query = "SELECT g FROM GsSolicitudes g WHERE g.fechaHoraReasigna = :fechaHoraReasigna"),
    @NamedQuery(name = "GsSolicitudes.findByUsuarioReasigna", query = "SELECT g FROM GsSolicitudes g WHERE g.usuarioReasigna = :usuarioReasigna"),
    @NamedQuery(name = "GsSolicitudes.findByFechaHoraFinaliza", query = "SELECT g FROM GsSolicitudes g WHERE g.fechaHoraFinaliza = :fechaHoraFinaliza"),
    @NamedQuery(name = "GsSolicitudes.findByUsuarioFinaliza", query = "SELECT g FROM GsSolicitudes g WHERE g.usuarioFinaliza = :usuarioFinaliza")})
public class GsSolicitudes implements Serializable {

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
    @Size(max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2048)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 8192)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "notificacion")
    private int notificacion;
    @Size(max = 16)
    @Column(name = "contacto_telefono")
    private String contactoTelefono;
    @Size(max = 16)
    @Column(name = "contacto_celular")
    private String contactoCelular;
    @Size(max = 256)
    @Column(name = "contacto_correo_electronico")
    private String contactoCorreoElectronico;
    @Size(max = 256)
    @Column(name = "tramite_interno")
    private String tramiteInterno;
    @Size(max = 128)
    @Column(name = "respuesta_referencia")
    private String respuestaReferencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Column(name = "fecha_hora_atiende")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAtiende;
    @Size(max = 128)
    @Column(name = "usuario_atiende")
    private String usuarioAtiende;
    @Column(name = "fecha_hora_reasigna")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraReasigna;
    @Size(max = 128)
    @Column(name = "usuario_reasigna")
    private String usuarioReasigna;
    @Column(name = "fecha_hora_finaliza")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFinaliza;
    @Size(max = 128)
    @Column(name = "usuario_finaliza")
    private String usuarioFinaliza;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gsSolicitudesId", fetch = FetchType.LAZY)
    private List<GsNotificaciones> gsNotificacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gsSolicitudesId", fetch = FetchType.LAZY)
    private List<GsAdjuntos> gsAdjuntosList;
    @JoinColumn(name = "gs_afiliados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GsAfiliados gsAfiliadosId;
    @JoinColumn(name = "gs_mensajes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GsMensajes gsMensajesId;
    @JoinColumn(name = "gs_zonas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GsZonas gsZonasId;
    @JoinColumn(name = "usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios usuariosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gsSolicitudesId", fetch = FetchType.LAZY)
    private List<GsGestiones> gsGestionesList;

    public GsSolicitudes() {
    }

    public GsSolicitudes(Integer id) {
        this.id = id;
    }

    public GsSolicitudes(Integer id, int tipo, int estado, int notificacion, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.notificacion = notificacion;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(int notificacion) {
        this.notificacion = notificacion;
    }

    public String getContactoTelefono() {
        return contactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        this.contactoTelefono = contactoTelefono;
    }

    public String getContactoCelular() {
        return contactoCelular;
    }

    public void setContactoCelular(String contactoCelular) {
        this.contactoCelular = contactoCelular;
    }

    public String getContactoCorreoElectronico() {
        return contactoCorreoElectronico;
    }

    public void setContactoCorreoElectronico(String contactoCorreoElectronico) {
        this.contactoCorreoElectronico = contactoCorreoElectronico;
    }

    public String getTramiteInterno() {
        return tramiteInterno;
    }

    public void setTramiteInterno(String tramiteInterno) {
        this.tramiteInterno = tramiteInterno;
    }

    public String getRespuestaReferencia() {
        return respuestaReferencia;
    }

    public void setRespuestaReferencia(String respuestaReferencia) {
        this.respuestaReferencia = respuestaReferencia;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Date getFechaHoraAtiende() {
        return fechaHoraAtiende;
    }

    public void setFechaHoraAtiende(Date fechaHoraAtiende) {
        this.fechaHoraAtiende = fechaHoraAtiende;
    }

    public String getUsuarioAtiende() {
        return usuarioAtiende;
    }

    public void setUsuarioAtiende(String usuarioAtiende) {
        this.usuarioAtiende = usuarioAtiende;
    }

    public Date getFechaHoraReasigna() {
        return fechaHoraReasigna;
    }

    public void setFechaHoraReasigna(Date fechaHoraReasigna) {
        this.fechaHoraReasigna = fechaHoraReasigna;
    }

    public String getUsuarioReasigna() {
        return usuarioReasigna;
    }

    public void setUsuarioReasigna(String usuarioReasigna) {
        this.usuarioReasigna = usuarioReasigna;
    }

    public Date getFechaHoraFinaliza() {
        return fechaHoraFinaliza;
    }

    public void setFechaHoraFinaliza(Date fechaHoraFinaliza) {
        this.fechaHoraFinaliza = fechaHoraFinaliza;
    }

    public String getUsuarioFinaliza() {
        return usuarioFinaliza;
    }

    public void setUsuarioFinaliza(String usuarioFinaliza) {
        this.usuarioFinaliza = usuarioFinaliza;
    }

    @XmlTransient
    public List<GsNotificaciones> getGsNotificacionesList() {
        return gsNotificacionesList;
    }

    public void setGsNotificacionesList(List<GsNotificaciones> gsNotificacionesList) {
        this.gsNotificacionesList = gsNotificacionesList;
    }

    @XmlTransient
    public List<GsAdjuntos> getGsAdjuntosList() {
        return gsAdjuntosList;
    }

    public void setGsAdjuntosList(List<GsAdjuntos> gsAdjuntosList) {
        this.gsAdjuntosList = gsAdjuntosList;
    }

    public GsAfiliados getGsAfiliadosId() {
        return gsAfiliadosId;
    }

    public void setGsAfiliadosId(GsAfiliados gsAfiliadosId) {
        this.gsAfiliadosId = gsAfiliadosId;
    }

    public GsMensajes getGsMensajesId() {
        return gsMensajesId;
    }

    public void setGsMensajesId(GsMensajes gsMensajesId) {
        this.gsMensajesId = gsMensajesId;
    }

    public GsZonas getGsZonasId() {
        return gsZonasId;
    }

    public void setGsZonasId(GsZonas gsZonasId) {
        this.gsZonasId = gsZonasId;
    }

    public GnUsuarios getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(GnUsuarios usuariosId) {
        this.usuariosId = usuariosId;
    }

    @XmlTransient
    public List<GsGestiones> getGsGestionesList() {
        return gsGestionesList;
    }

    public void setGsGestionesList(List<GsGestiones> gsGestionesList) {
        this.gsGestionesList = gsGestionesList;
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
        if (!(object instanceof GsSolicitudes)) {
            return false;
        }
        GsSolicitudes other = (GsSolicitudes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GsSolicitudes[ id=" + id + " ]";
    }
    
}
