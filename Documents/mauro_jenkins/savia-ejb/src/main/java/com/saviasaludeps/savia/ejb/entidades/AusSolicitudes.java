/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "aus_solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusSolicitudes.findAll", query = "SELECT a FROM AusSolicitudes a"),
    @NamedQuery(name = "AusSolicitudes.findById", query = "SELECT a FROM AusSolicitudes a WHERE a.id = :id"),
    @NamedQuery(name = "AusSolicitudes.findByMaeTipoSolicitudId", query = "SELECT a FROM AusSolicitudes a WHERE a.maeTipoSolicitudId = :maeTipoSolicitudId"),
    @NamedQuery(name = "AusSolicitudes.findByMaeTipoSolicitudCodigo", query = "SELECT a FROM AusSolicitudes a WHERE a.maeTipoSolicitudCodigo = :maeTipoSolicitudCodigo"),
    @NamedQuery(name = "AusSolicitudes.findByMaeTipoSolicitudValor", query = "SELECT a FROM AusSolicitudes a WHERE a.maeTipoSolicitudValor = :maeTipoSolicitudValor"),
    @NamedQuery(name = "AusSolicitudes.findByNombres", query = "SELECT a FROM AusSolicitudes a WHERE a.nombres = :nombres"),
    @NamedQuery(name = "AusSolicitudes.findByApellidos", query = "SELECT a FROM AusSolicitudes a WHERE a.apellidos = :apellidos"),
    @NamedQuery(name = "AusSolicitudes.findByMaeTipoDocumentoId", query = "SELECT a FROM AusSolicitudes a WHERE a.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "AusSolicitudes.findByMaeTipoDocumentoCodigo", query = "SELECT a FROM AusSolicitudes a WHERE a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "AusSolicitudes.findByMaeTipoDocumentoValor", query = "SELECT a FROM AusSolicitudes a WHERE a.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "AusSolicitudes.findByNumeroDocumento", query = "SELECT a FROM AusSolicitudes a WHERE a.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "AusSolicitudes.findByEmail", query = "SELECT a FROM AusSolicitudes a WHERE a.email = :email"),
    @NamedQuery(name = "AusSolicitudes.findByDetalleEmail", query = "SELECT a FROM AusSolicitudes a WHERE a.detalleEmail = :detalleEmail"),
    @NamedQuery(name = "AusSolicitudes.findByTelefono", query = "SELECT a FROM AusSolicitudes a WHERE a.telefono = :telefono"),
    @NamedQuery(name = "AusSolicitudes.findByDescripcion", query = "SELECT a FROM AusSolicitudes a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AusSolicitudes.findByMaeEstadoSolicitudId", query = "SELECT a FROM AusSolicitudes a WHERE a.maeEstadoSolicitudId = :maeEstadoSolicitudId"),
    @NamedQuery(name = "AusSolicitudes.findByMaeEstadoSolicitudCodigo", query = "SELECT a FROM AusSolicitudes a WHERE a.maeEstadoSolicitudCodigo = :maeEstadoSolicitudCodigo"),
    @NamedQuery(name = "AusSolicitudes.findByMaeEstadoSolicitudValor", query = "SELECT a FROM AusSolicitudes a WHERE a.maeEstadoSolicitudValor = :maeEstadoSolicitudValor"),
    @NamedQuery(name = "AusSolicitudes.findByAplicaCaso", query = "SELECT a FROM AusSolicitudes a WHERE a.aplicaCaso = :aplicaCaso"),
    @NamedQuery(name = "AusSolicitudes.findByDireccionNotificacion", query = "SELECT a FROM AusSolicitudes a WHERE a.direccionNotificacion = :direccionNotificacion"),
    @NamedQuery(name = "AusSolicitudes.findByUsuarioCrea", query = "SELECT a FROM AusSolicitudes a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusSolicitudes.findByTerminalCrea", query = "SELECT a FROM AusSolicitudes a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusSolicitudes.findByFechaHoraCrea", query = "SELECT a FROM AusSolicitudes a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AusSolicitudes.findByUsuarioModifica", query = "SELECT a FROM AusSolicitudes a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AusSolicitudes.findByTerminalModifica", query = "SELECT a FROM AusSolicitudes a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AusSolicitudes.findByFechaHoraModifica", query = "SELECT a FROM AusSolicitudes a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AusSolicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_tipo_solicitud_id")
    private Integer maeTipoSolicitudId;
    @Size(max = 8)
    @Column(name = "mae_tipo_solicitud_codigo")
    private String maeTipoSolicitudCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_solicitud_valor")
    private String maeTipoSolicitudValor;
    @Size(max = 128)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 128)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Size(max = 16)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 128)
    @Column(name = "email")
    private String email;
    @Size(max = 2048)
    @Column(name = "detalle_email")
    private String detalleEmail;
    @Size(max = 16)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 2048)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "mae_estado_solicitud_id")
    private Integer maeEstadoSolicitudId;
    @Size(max = 8)
    @Column(name = "mae_estado_solicitud_codigo")
    private String maeEstadoSolicitudCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_solicitud_valor")
    private String maeEstadoSolicitudValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_caso")
    private boolean aplicaCaso;
    @Size(max = 512)
    @Column(name = "direccion_notificacion")
    private String direccionNotificacion;
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
    @JoinColumn(name = "aus_casos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AusCasos ausCasosId;
    @OneToMany(mappedBy = "ausSolicitudesId", fetch = FetchType.LAZY)
    private List<AusAdjuntos> ausAdjuntosList;

    public AusSolicitudes() {
    }

    public AusSolicitudes(Integer id) {
        this.id = id;
    }

    public AusSolicitudes(Integer id, boolean aplicaCaso, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.aplicaCaso = aplicaCaso;
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

    public Integer getMaeTipoSolicitudId() {
        return maeTipoSolicitudId;
    }

    public void setMaeTipoSolicitudId(Integer maeTipoSolicitudId) {
        this.maeTipoSolicitudId = maeTipoSolicitudId;
    }

    public String getMaeTipoSolicitudCodigo() {
        return maeTipoSolicitudCodigo;
    }

    public void setMaeTipoSolicitudCodigo(String maeTipoSolicitudCodigo) {
        this.maeTipoSolicitudCodigo = maeTipoSolicitudCodigo;
    }

    public String getMaeTipoSolicitudValor() {
        return maeTipoSolicitudValor;
    }

    public void setMaeTipoSolicitudValor(String maeTipoSolicitudValor) {
        this.maeTipoSolicitudValor = maeTipoSolicitudValor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetalleEmail() {
        return detalleEmail;
    }

    public void setDetalleEmail(String detalleEmail) {
        this.detalleEmail = detalleEmail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMaeEstadoSolicitudId() {
        return maeEstadoSolicitudId;
    }

    public void setMaeEstadoSolicitudId(Integer maeEstadoSolicitudId) {
        this.maeEstadoSolicitudId = maeEstadoSolicitudId;
    }

    public String getMaeEstadoSolicitudCodigo() {
        return maeEstadoSolicitudCodigo;
    }

    public void setMaeEstadoSolicitudCodigo(String maeEstadoSolicitudCodigo) {
        this.maeEstadoSolicitudCodigo = maeEstadoSolicitudCodigo;
    }

    public String getMaeEstadoSolicitudValor() {
        return maeEstadoSolicitudValor;
    }

    public void setMaeEstadoSolicitudValor(String maeEstadoSolicitudValor) {
        this.maeEstadoSolicitudValor = maeEstadoSolicitudValor;
    }

    public boolean getAplicaCaso() {
        return aplicaCaso;
    }

    public void setAplicaCaso(boolean aplicaCaso) {
        this.aplicaCaso = aplicaCaso;
    }

    public String getDireccionNotificacion() {
        return direccionNotificacion;
    }

    public void setDireccionNotificacion(String direccionNotificacion) {
        this.direccionNotificacion = direccionNotificacion;
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

    public AusCasos getAusCasosId() {
        return ausCasosId;
    }

    public void setAusCasosId(AusCasos ausCasosId) {
        this.ausCasosId = ausCasosId;
    }

    @XmlTransient
    public List<AusAdjuntos> getAusAdjuntosList() {
        return ausAdjuntosList;
    }

    public void setAusAdjuntosList(List<AusAdjuntos> ausAdjuntosList) {
        this.ausAdjuntosList = ausAdjuntosList;
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
        if (!(object instanceof AusSolicitudes)) {
            return false;
        }
        AusSolicitudes other = (AusSolicitudes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusSolicitudes[ id=" + id + " ]";
    }
    
}
