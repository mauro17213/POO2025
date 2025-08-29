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
@Table(name = "cntj_tercero_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjTerceroContactos.findAll", query = "SELECT c FROM CntjTerceroContactos c"),
    @NamedQuery(name = "CntjTerceroContactos.findById", query = "SELECT c FROM CntjTerceroContactos c WHERE c.id = :id"),
    @NamedQuery(name = "CntjTerceroContactos.findByMaeTipoContactoId", query = "SELECT c FROM CntjTerceroContactos c WHERE c.maeTipoContactoId = :maeTipoContactoId"),
    @NamedQuery(name = "CntjTerceroContactos.findByMaeTipoContactoCodigo", query = "SELECT c FROM CntjTerceroContactos c WHERE c.maeTipoContactoCodigo = :maeTipoContactoCodigo"),
    @NamedQuery(name = "CntjTerceroContactos.findByMaeTipoContactoValor", query = "SELECT c FROM CntjTerceroContactos c WHERE c.maeTipoContactoValor = :maeTipoContactoValor"),
    @NamedQuery(name = "CntjTerceroContactos.findByContacto", query = "SELECT c FROM CntjTerceroContactos c WHERE c.contacto = :contacto"),
    @NamedQuery(name = "CntjTerceroContactos.findByActivo", query = "SELECT c FROM CntjTerceroContactos c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntjTerceroContactos.findByObservacion", query = "SELECT c FROM CntjTerceroContactos c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CntjTerceroContactos.findByUsuarioCrea", query = "SELECT c FROM CntjTerceroContactos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjTerceroContactos.findByTerminalCrea", query = "SELECT c FROM CntjTerceroContactos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjTerceroContactos.findByFechaHoraCrea", query = "SELECT c FROM CntjTerceroContactos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjTerceroContactos.findByUsuarioModifica", query = "SELECT c FROM CntjTerceroContactos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjTerceroContactos.findByTerminalModifica", query = "SELECT c FROM CntjTerceroContactos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjTerceroContactos.findByFechaHoraModifica", query = "SELECT c FROM CntjTerceroContactos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjTerceroContactos implements Serializable {

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
    @JoinColumn(name = "cntj_terceros_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjTerceros cntjTercerosId;

    public CntjTerceroContactos() {
    }

    public CntjTerceroContactos(Integer id) {
        this.id = id;
    }

    public CntjTerceroContactos(Integer id, int maeTipoContactoId, String maeTipoContactoCodigo, String maeTipoContactoValor, String contacto, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public CntjTerceros getCntjTercerosId() {
        return cntjTercerosId;
    }

    public void setCntjTercerosId(CntjTerceros cntjTercerosId) {
        this.cntjTercerosId = cntjTercerosId;
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
        if (!(object instanceof CntjTerceroContactos)) {
            return false;
        }
        CntjTerceroContactos other = (CntjTerceroContactos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjTerceroContactos[ id=" + id + " ]";
    }
    
}
