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
@Table(name = "gj_tercero_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GjTerceroContactos.findAll", query = "SELECT g FROM GjTerceroContactos g"),
    @NamedQuery(name = "GjTerceroContactos.findById", query = "SELECT g FROM GjTerceroContactos g WHERE g.id = :id"),
    @NamedQuery(name = "GjTerceroContactos.findByMaeTipoContactoId", query = "SELECT g FROM GjTerceroContactos g WHERE g.maeTipoContactoId = :maeTipoContactoId"),
    @NamedQuery(name = "GjTerceroContactos.findByMaeTipoContactoCodigo", query = "SELECT g FROM GjTerceroContactos g WHERE g.maeTipoContactoCodigo = :maeTipoContactoCodigo"),
    @NamedQuery(name = "GjTerceroContactos.findByMaeTipoContactoValor", query = "SELECT g FROM GjTerceroContactos g WHERE g.maeTipoContactoValor = :maeTipoContactoValor"),
    @NamedQuery(name = "GjTerceroContactos.findByContacto", query = "SELECT g FROM GjTerceroContactos g WHERE g.contacto = :contacto"),
    @NamedQuery(name = "GjTerceroContactos.findByActivo", query = "SELECT g FROM GjTerceroContactos g WHERE g.activo = :activo"),
    @NamedQuery(name = "GjTerceroContactos.findByObservacion", query = "SELECT g FROM GjTerceroContactos g WHERE g.observacion = :observacion"),
    @NamedQuery(name = "GjTerceroContactos.findByUsuarioCrea", query = "SELECT g FROM GjTerceroContactos g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GjTerceroContactos.findByTerminalCrea", query = "SELECT g FROM GjTerceroContactos g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GjTerceroContactos.findByFechaHoraCrea", query = "SELECT g FROM GjTerceroContactos g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GjTerceroContactos.findByUsuarioModifica", query = "SELECT g FROM GjTerceroContactos g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GjTerceroContactos.findByTerminalModifica", query = "SELECT g FROM GjTerceroContactos g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GjTerceroContactos.findByFechaHoraModifica", query = "SELECT g FROM GjTerceroContactos g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GjTerceroContactos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_contacto_id")
    private int maeTipoContactoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_contacto_codigo")
    private String maeTipoContactoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_contacto_valor")
    private String maeTipoContactoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "contacto")
    private String contacto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 128)
    @Column(name = "observacion")
    private String observacion;
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
    @JoinColumn(name = "gj_terceros_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GjTerceros gjTercerosId;

    public GjTerceroContactos() {
    }

    public GjTerceroContactos(Integer id) {
        this.id = id;
    }

    public GjTerceroContactos(Integer id, int maeTipoContactoId, String maeTipoContactoCodigo, String maeTipoContactoValor, String contacto, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoContactoId = maeTipoContactoId;
        this.maeTipoContactoCodigo = maeTipoContactoCodigo;
        this.maeTipoContactoValor = maeTipoContactoValor;
        this.contacto = contacto;
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

    public int getMaeTipoContactoId() {
        return maeTipoContactoId;
    }

    public void setMaeTipoContactoId(int maeTipoContactoId) {
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public GjTerceros getGjTercerosId() {
        return gjTercerosId;
    }

    public void setGjTercerosId(GjTerceros gjTercerosId) {
        this.gjTercerosId = gjTercerosId;
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
        if (!(object instanceof GjTerceroContactos)) {
            return false;
        }
        GjTerceroContactos other = (GjTerceroContactos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GjTerceroContactos[ id=" + id + " ]";
    }
    
}
