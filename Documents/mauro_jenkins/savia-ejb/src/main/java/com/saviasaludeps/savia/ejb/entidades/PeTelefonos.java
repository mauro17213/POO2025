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
@Table(name = "pe_telefonos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeTelefonos.findAll", query = "SELECT p FROM PeTelefonos p"),
    @NamedQuery(name = "PeTelefonos.findById", query = "SELECT p FROM PeTelefonos p WHERE p.id = :id"),
    @NamedQuery(name = "PeTelefonos.findByTipo", query = "SELECT p FROM PeTelefonos p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PeTelefonos.findByNumero", query = "SELECT p FROM PeTelefonos p WHERE p.numero = :numero"),
    @NamedQuery(name = "PeTelefonos.findByObservacion", query = "SELECT p FROM PeTelefonos p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "PeTelefonos.findByUsuarioCrea", query = "SELECT p FROM PeTelefonos p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeTelefonos.findByTerminalCrea", query = "SELECT p FROM PeTelefonos p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeTelefonos.findByFechaHoraCrea", query = "SELECT p FROM PeTelefonos p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeTelefonos.findByUsuarioModifica", query = "SELECT p FROM PeTelefonos p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeTelefonos.findByTerminalModifica", query = "SELECT p FROM PeTelefonos p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeTelefonos.findByFechaHoraModifica", query = "SELECT p FROM PeTelefonos p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeTelefonos implements Serializable {

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
    @Size(min = 1, max = 16)
    @Column(name = "numero")
    private String numero;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;

    public PeTelefonos() {
    }

    public PeTelefonos(Integer id) {
        this.id = id;
    }

    public PeTelefonos(Integer id, int tipo, String numero, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.numero = numero;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
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
        if (!(object instanceof PeTelefonos)) {
            return false;
        }
        PeTelefonos other = (PeTelefonos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeTelefonos[ id=" + id + " ]";
    }
    
}
