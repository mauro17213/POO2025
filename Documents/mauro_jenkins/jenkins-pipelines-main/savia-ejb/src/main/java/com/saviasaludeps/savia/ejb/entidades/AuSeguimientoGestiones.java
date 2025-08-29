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
@Table(name = "au_seguimiento_gestiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuSeguimientoGestiones.findAll", query = "SELECT a FROM AuSeguimientoGestiones a"),
    @NamedQuery(name = "AuSeguimientoGestiones.findById", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.id = :id"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByDescripcion", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByTipo", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByMaeEstadoSeguimientoGestionId", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.maeEstadoSeguimientoGestionId = :maeEstadoSeguimientoGestionId"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByMaeEstadoSeguimientoGestionCodigo", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.maeEstadoSeguimientoGestionCodigo = :maeEstadoSeguimientoGestionCodigo"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByMaeEstadoSeguimientoGestionValor", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.maeEstadoSeguimientoGestionValor = :maeEstadoSeguimientoGestionValor"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByMaeMotivoSeguimientoId", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.maeMotivoSeguimientoId = :maeMotivoSeguimientoId"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByMaeMotivoSeguimientoCodigo", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.maeMotivoSeguimientoCodigo = :maeMotivoSeguimientoCodigo"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByMaeMotivoSeguimientoValor", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.maeMotivoSeguimientoValor = :maeMotivoSeguimientoValor"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByBorrado", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByFechaEntregaPropuesta", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.fechaEntregaPropuesta = :fechaEntregaPropuesta"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByFechaHoraEntregaReal", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.fechaHoraEntregaReal = :fechaHoraEntregaReal"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByUsuarioCrea", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByTerminalCrea", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByFechaHoraCrea", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByUsuarioModifica", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByTerminalModifica", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByFechaHoraModifica", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByUsuarioBorra", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByTerminalBorra", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AuSeguimientoGestiones.findByFechaHoraBorra", query = "SELECT a FROM AuSeguimientoGestiones a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AuSeguimientoGestiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_seguimiento_gestion_id")
    private int maeEstadoSeguimientoGestionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_estado_seguimiento_gestion_codigo")
    private String maeEstadoSeguimientoGestionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_estado_seguimiento_gestion_valor")
    private String maeEstadoSeguimientoGestionValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_motivo_seguimiento_id")
    private int maeMotivoSeguimientoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_motivo_seguimiento_codigo")
    private String maeMotivoSeguimientoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_motivo_seguimiento_valor")
    private String maeMotivoSeguimientoValor;
    @Column(name = "borrado")
    private Boolean borrado;
    @Column(name = "fecha_entrega_propuesta")
    @Temporal(TemporalType.DATE)
    private Date fechaEntregaPropuesta;
    @Column(name = "fecha_hora_entrega_real")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEntregaReal;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @OneToMany(mappedBy = "auSeguimientoGestionesId", fetch = FetchType.LAZY)
    private List<AuSolicitudAdjuntos> auSolicitudAdjuntosList;
    @JoinColumn(name = "au_seguimientos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuSeguimientos auSeguimientosId;

    public AuSeguimientoGestiones() {
    }

    public AuSeguimientoGestiones(Integer id) {
        this.id = id;
    }

    public AuSeguimientoGestiones(Integer id, String descripcion, int tipo, int maeEstadoSeguimientoGestionId, String maeEstadoSeguimientoGestionCodigo, String maeEstadoSeguimientoGestionValor, int maeMotivoSeguimientoId, String maeMotivoSeguimientoCodigo, String maeMotivoSeguimientoValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.maeEstadoSeguimientoGestionId = maeEstadoSeguimientoGestionId;
        this.maeEstadoSeguimientoGestionCodigo = maeEstadoSeguimientoGestionCodigo;
        this.maeEstadoSeguimientoGestionValor = maeEstadoSeguimientoGestionValor;
        this.maeMotivoSeguimientoId = maeMotivoSeguimientoId;
        this.maeMotivoSeguimientoCodigo = maeMotivoSeguimientoCodigo;
        this.maeMotivoSeguimientoValor = maeMotivoSeguimientoValor;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getMaeEstadoSeguimientoGestionId() {
        return maeEstadoSeguimientoGestionId;
    }

    public void setMaeEstadoSeguimientoGestionId(int maeEstadoSeguimientoGestionId) {
        this.maeEstadoSeguimientoGestionId = maeEstadoSeguimientoGestionId;
    }

    public String getMaeEstadoSeguimientoGestionCodigo() {
        return maeEstadoSeguimientoGestionCodigo;
    }

    public void setMaeEstadoSeguimientoGestionCodigo(String maeEstadoSeguimientoGestionCodigo) {
        this.maeEstadoSeguimientoGestionCodigo = maeEstadoSeguimientoGestionCodigo;
    }

    public String getMaeEstadoSeguimientoGestionValor() {
        return maeEstadoSeguimientoGestionValor;
    }

    public void setMaeEstadoSeguimientoGestionValor(String maeEstadoSeguimientoGestionValor) {
        this.maeEstadoSeguimientoGestionValor = maeEstadoSeguimientoGestionValor;
    }

    public int getMaeMotivoSeguimientoId() {
        return maeMotivoSeguimientoId;
    }

    public void setMaeMotivoSeguimientoId(int maeMotivoSeguimientoId) {
        this.maeMotivoSeguimientoId = maeMotivoSeguimientoId;
    }

    public String getMaeMotivoSeguimientoCodigo() {
        return maeMotivoSeguimientoCodigo;
    }

    public void setMaeMotivoSeguimientoCodigo(String maeMotivoSeguimientoCodigo) {
        this.maeMotivoSeguimientoCodigo = maeMotivoSeguimientoCodigo;
    }

    public String getMaeMotivoSeguimientoValor() {
        return maeMotivoSeguimientoValor;
    }

    public void setMaeMotivoSeguimientoValor(String maeMotivoSeguimientoValor) {
        this.maeMotivoSeguimientoValor = maeMotivoSeguimientoValor;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public Date getFechaEntregaPropuesta() {
        return fechaEntregaPropuesta;
    }

    public void setFechaEntregaPropuesta(Date fechaEntregaPropuesta) {
        this.fechaEntregaPropuesta = fechaEntregaPropuesta;
    }

    public Date getFechaHoraEntregaReal() {
        return fechaHoraEntregaReal;
    }

    public void setFechaHoraEntregaReal(Date fechaHoraEntregaReal) {
        this.fechaHoraEntregaReal = fechaHoraEntregaReal;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    @XmlTransient
    public List<AuSolicitudAdjuntos> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjuntos> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    public AuSeguimientos getAuSeguimientosId() {
        return auSeguimientosId;
    }

    public void setAuSeguimientosId(AuSeguimientos auSeguimientosId) {
        this.auSeguimientosId = auSeguimientosId;
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
        if (!(object instanceof AuSeguimientoGestiones)) {
            return false;
        }
        AuSeguimientoGestiones other = (AuSeguimientoGestiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuSeguimientoGestiones[ id=" + id + " ]";
    }
    
}
