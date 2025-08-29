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
@Table(name = "au_seguimiento_afiliado_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findAll", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findById", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.id = :id"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByNumeroContacto", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.numeroContacto = :numeroContacto"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByMaeTipoContactoId", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.maeTipoContactoId = :maeTipoContactoId"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByMaeTipoContactoCodigo", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.maeTipoContactoCodigo = :maeTipoContactoCodigo"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByMaeTipoContactoValor", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.maeTipoContactoValor = :maeTipoContactoValor"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByObservacion", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByActivo", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.activo = :activo"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByBorrado", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByUsuarioCrea", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByTerminalCrea", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByFechaHoraCrea", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByUsuarioModifica", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByTerminalModifica", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByFechaHoraModifica", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByUsuarioBorra", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByTerminalBorra", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AuSeguimientoAfiliadoContactos.findByFechaHoraBorra", query = "SELECT a FROM AuSeguimientoAfiliadoContactos a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AuSeguimientoAfiliadoContactos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "numero_contacto")
    private String numeroContacto;
    @Column(name = "mae_tipo_contacto_id")
    private Integer maeTipoContactoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_contacto_codigo")
    private String maeTipoContactoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_contacto_valor")
    private String maeTipoContactoValor;
    @Size(max = 128)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "borrado")
    private Boolean borrado;
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
    @JoinColumn(name = "au_seguimiento_afiliado_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuSeguimientoAfiliados auSeguimientoAfiliadoId;

    public AuSeguimientoAfiliadoContactos() {
    }

    public AuSeguimientoAfiliadoContactos(Integer id) {
        this.id = id;
    }

    public AuSeguimientoAfiliadoContactos(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    public Integer getMaeTipoContactoId() {
        return maeTipoContactoId;
    }

    public void setMaeTipoContactoId(Integer maeTipoContactoId) {
        this.maeTipoContactoId = maeTipoContactoId;
    }

    public String getMaeTipoContactoCodigo() {
        return maeTipoContactoCodigo;
    }

    public void setMaeTipoContactoCodigo(String maeTipoContactoCodigo) {
        this.maeTipoContactoCodigo = maeTipoContactoCodigo;
    }

    public String getMaeTipoContactoValor() {
        return maeTipoContactoValor;
    }

    public void setMaeTipoContactoValor(String maeTipoContactoValor) {
        this.maeTipoContactoValor = maeTipoContactoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
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

    public AuSeguimientoAfiliados getAuSeguimientoAfiliadoId() {
        return auSeguimientoAfiliadoId;
    }

    public void setAuSeguimientoAfiliadoId(AuSeguimientoAfiliados auSeguimientoAfiliadoId) {
        this.auSeguimientoAfiliadoId = auSeguimientoAfiliadoId;
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
        if (!(object instanceof AuSeguimientoAfiliadoContactos)) {
            return false;
        }
        AuSeguimientoAfiliadoContactos other = (AuSeguimientoAfiliadoContactos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuSeguimientoAfiliadoContactos[ id=" + id + " ]";
    }
    
}
