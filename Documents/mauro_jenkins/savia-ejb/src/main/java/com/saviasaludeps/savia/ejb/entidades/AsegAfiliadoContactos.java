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
@Table(name = "aseg_afiliado_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegAfiliadoContactos.findAll", query = "SELECT a FROM AsegAfiliadoContactos a"),
    @NamedQuery(name = "AsegAfiliadoContactos.findById", query = "SELECT a FROM AsegAfiliadoContactos a WHERE a.id = :id"),
    @NamedQuery(name = "AsegAfiliadoContactos.findByNumeroContacto", query = "SELECT a FROM AsegAfiliadoContactos a WHERE a.numeroContacto = :numeroContacto"),
    @NamedQuery(name = "AsegAfiliadoContactos.findByMaeTipoContactoId", query = "SELECT a FROM AsegAfiliadoContactos a WHERE a.maeTipoContactoId = :maeTipoContactoId"),
    @NamedQuery(name = "AsegAfiliadoContactos.findByMaeTipoContactoCodigo", query = "SELECT a FROM AsegAfiliadoContactos a WHERE a.maeTipoContactoCodigo = :maeTipoContactoCodigo"),
    @NamedQuery(name = "AsegAfiliadoContactos.findByMaeTipoContactoValor", query = "SELECT a FROM AsegAfiliadoContactos a WHERE a.maeTipoContactoValor = :maeTipoContactoValor"),
    @NamedQuery(name = "AsegAfiliadoContactos.findByObservacion", query = "SELECT a FROM AsegAfiliadoContactos a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AsegAfiliadoContactos.findByActivo", query = "SELECT a FROM AsegAfiliadoContactos a WHERE a.activo = :activo"),
    @NamedQuery(name = "AsegAfiliadoContactos.findByUsuarioCrea", query = "SELECT a FROM AsegAfiliadoContactos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegAfiliadoContactos.findByTerminalCrea", query = "SELECT a FROM AsegAfiliadoContactos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegAfiliadoContactos.findByFechaHoraCrea", query = "SELECT a FROM AsegAfiliadoContactos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AsegAfiliadoContactos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_contacto")
    private String numeroContacto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_contacto_id")
    private int maeTipoContactoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_tipo_contacto_codigo")
    private String maeTipoContactoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_contacto_valor")
    private String maeTipoContactoValor;
    @Size(max = 128)
    @Column(name = "observacion")
    private String observacion;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;

    public AsegAfiliadoContactos() {
    }

    public AsegAfiliadoContactos(Integer id) {
        this.id = id;
    }

    public AsegAfiliadoContactos(Integer id, String numeroContacto, int maeTipoContactoId, String maeTipoContactoCodigo, String maeTipoContactoValor, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.numeroContacto = numeroContacto;
        this.maeTipoContactoId = maeTipoContactoId;
        this.maeTipoContactoCodigo = maeTipoContactoCodigo;
        this.maeTipoContactoValor = maeTipoContactoValor;
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

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        if (!(object instanceof AsegAfiliadoContactos)) {
            return false;
        }
        AsegAfiliadoContactos other = (AsegAfiliadoContactos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoContactos[ id=" + id + " ]";
    }
    
}
