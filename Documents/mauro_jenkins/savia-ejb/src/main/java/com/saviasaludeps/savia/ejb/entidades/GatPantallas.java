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
@Table(name = "gat_pantallas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatPantallas.findAll", query = "SELECT g FROM GatPantallas g"),
    @NamedQuery(name = "GatPantallas.findById", query = "SELECT g FROM GatPantallas g WHERE g.id = :id"),
    @NamedQuery(name = "GatPantallas.findByIdSesion", query = "SELECT g FROM GatPantallas g WHERE g.idSesion = :idSesion"),
    @NamedQuery(name = "GatPantallas.findByCuenta", query = "SELECT g FROM GatPantallas g WHERE g.cuenta = :cuenta"),
    @NamedQuery(name = "GatPantallas.findByActivo", query = "SELECT g FROM GatPantallas g WHERE g.activo = :activo"),
    @NamedQuery(name = "GatPantallas.findByMaeTipoServicioId", query = "SELECT g FROM GatPantallas g WHERE g.maeTipoServicioId = :maeTipoServicioId"),
    @NamedQuery(name = "GatPantallas.findByMaeTipoServicioCodigo", query = "SELECT g FROM GatPantallas g WHERE g.maeTipoServicioCodigo = :maeTipoServicioCodigo"),
    @NamedQuery(name = "GatPantallas.findByMaeTipoServicioValor", query = "SELECT g FROM GatPantallas g WHERE g.maeTipoServicioValor = :maeTipoServicioValor"),
    @NamedQuery(name = "GatPantallas.findByUsuarioCrea", query = "SELECT g FROM GatPantallas g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatPantallas.findByTerminalCrea", query = "SELECT g FROM GatPantallas g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatPantallas.findByFechaHoraCrea", query = "SELECT g FROM GatPantallas g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GatPantallas.findByUsuarioModifica", query = "SELECT g FROM GatPantallas g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GatPantallas.findByTerminalModifica", query = "SELECT g FROM GatPantallas g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GatPantallas.findByFechaHoraModifica", query = "SELECT g FROM GatPantallas g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GatPantallas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "id_sesion")
    private String idSesion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cuenta")
    private boolean cuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "mae_tipo_servicio_id")
    private Integer maeTipoServicioId;
    @Size(max = 8)
    @Column(name = "mae_tipo_servicio_codigo")
    private String maeTipoServicioCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_servicio_valor")
    private String maeTipoServicioValor;
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
    @JoinColumn(name = "gn_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnSedes gnSedesId;

    public GatPantallas() {
    }

    public GatPantallas(Integer id) {
        this.id = id;
    }

    public GatPantallas(Integer id, String idSesion, boolean cuenta, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.idSesion = idSesion;
        this.cuenta = cuenta;
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

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public boolean getCuenta() {
        return cuenta;
    }

    public void setCuenta(boolean cuenta) {
        this.cuenta = cuenta;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(Integer maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
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

    public GnSedes getGnSedesId() {
        return gnSedesId;
    }

    public void setGnSedesId(GnSedes gnSedesId) {
        this.gnSedesId = gnSedesId;
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
        if (!(object instanceof GatPantallas)) {
            return false;
        }
        GatPantallas other = (GatPantallas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatPantallas[ id=" + id + " ]";
    }
    
}
