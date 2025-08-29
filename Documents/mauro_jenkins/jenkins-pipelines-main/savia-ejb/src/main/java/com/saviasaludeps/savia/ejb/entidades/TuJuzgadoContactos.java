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
@Table(name = "tu_juzgado_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuJuzgadoContactos.findAll", query = "SELECT t FROM TuJuzgadoContactos t"),
    @NamedQuery(name = "TuJuzgadoContactos.findById", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.id = :id"),
    @NamedQuery(name = "TuJuzgadoContactos.findByMaeTipoContactoId", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.maeTipoContactoId = :maeTipoContactoId"),
    @NamedQuery(name = "TuJuzgadoContactos.findByMaeTipoContactoCodigo", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.maeTipoContactoCodigo = :maeTipoContactoCodigo"),
    @NamedQuery(name = "TuJuzgadoContactos.findByMaeTipoContactoValor", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.maeTipoContactoValor = :maeTipoContactoValor"),
    @NamedQuery(name = "TuJuzgadoContactos.findByContacto", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.contacto = :contacto"),
    @NamedQuery(name = "TuJuzgadoContactos.findByActivo", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.activo = :activo"),
    @NamedQuery(name = "TuJuzgadoContactos.findByObservacion", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.observacion = :observacion"),
    @NamedQuery(name = "TuJuzgadoContactos.findByUsuarioCrea", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuJuzgadoContactos.findByTerminalCrea", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuJuzgadoContactos.findByFechaHoraCrea", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuJuzgadoContactos.findByUsuarioModifica", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuJuzgadoContactos.findByTerminalModifica", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuJuzgadoContactos.findByFechaHoraModifica", query = "SELECT t FROM TuJuzgadoContactos t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuJuzgadoContactos implements Serializable {

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
    @JoinColumn(name = "tu_juzgados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuJuzgados tuJuzgadosId;

    public TuJuzgadoContactos() {
    }

    public TuJuzgadoContactos(Integer id) {
        this.id = id;
    }

    public TuJuzgadoContactos(Integer id, int maeTipoContactoId, String maeTipoContactoCodigo, String maeTipoContactoValor, String contacto, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public TuJuzgados getTuJuzgadosId() {
        return tuJuzgadosId;
    }

    public void setTuJuzgadosId(TuJuzgados tuJuzgadosId) {
        this.tuJuzgadosId = tuJuzgadosId;
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
        if (!(object instanceof TuJuzgadoContactos)) {
            return false;
        }
        TuJuzgadoContactos other = (TuJuzgadoContactos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuJuzgadoContactos[ id=" + id + " ]";
    }
    
}
