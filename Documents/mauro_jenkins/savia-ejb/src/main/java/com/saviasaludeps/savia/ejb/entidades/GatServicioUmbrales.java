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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "gat_servicio_umbrales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatServicioUmbrales.findAll", query = "SELECT g FROM GatServicioUmbrales g"),
    @NamedQuery(name = "GatServicioUmbrales.findById", query = "SELECT g FROM GatServicioUmbrales g WHERE g.id = :id"),
    @NamedQuery(name = "GatServicioUmbrales.findByMaeTipoServicioId", query = "SELECT g FROM GatServicioUmbrales g WHERE g.maeTipoServicioId = :maeTipoServicioId"),
    @NamedQuery(name = "GatServicioUmbrales.findByMaeTipoServicioCodigo", query = "SELECT g FROM GatServicioUmbrales g WHERE g.maeTipoServicioCodigo = :maeTipoServicioCodigo"),
    @NamedQuery(name = "GatServicioUmbrales.findByMaeTipoServicioValor", query = "SELECT g FROM GatServicioUmbrales g WHERE g.maeTipoServicioValor = :maeTipoServicioValor"),
    @NamedQuery(name = "GatServicioUmbrales.findByTiempo", query = "SELECT g FROM GatServicioUmbrales g WHERE g.tiempo = :tiempo"),
    @NamedQuery(name = "GatServicioUmbrales.findByTipo", query = "SELECT g FROM GatServicioUmbrales g WHERE g.tipo = :tipo"),
    @NamedQuery(name = "GatServicioUmbrales.findByUsuarioCrea", query = "SELECT g FROM GatServicioUmbrales g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatServicioUmbrales.findByTerminalCrea", query = "SELECT g FROM GatServicioUmbrales g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatServicioUmbrales.findByFechaHoraCrea", query = "SELECT g FROM GatServicioUmbrales g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GatServicioUmbrales.findByUsuarioModifica", query = "SELECT g FROM GatServicioUmbrales g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GatServicioUmbrales.findByTerminalModifica", query = "SELECT g FROM GatServicioUmbrales g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GatServicioUmbrales.findByFechaHoraModifica", query = "SELECT g FROM GatServicioUmbrales g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GatServicioUmbrales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_servicio_id")
    private int maeTipoServicioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_servicio_codigo")
    private String maeTipoServicioCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_servicio_valor")
    private String maeTipoServicioValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo")
    private int tiempo;
    @Column(name = "tipo")
    private Integer tipo;
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

    public GatServicioUmbrales() {
    }

    public GatServicioUmbrales(Integer id) {
        this.id = id;
    }

    public GatServicioUmbrales(Integer id, int maeTipoServicioId, String maeTipoServicioCodigo, String maeTipoServicioValor, int tiempo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoServicioId = maeTipoServicioId;
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
        this.maeTipoServicioValor = maeTipoServicioValor;
        this.tiempo = tiempo;
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

    public int getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(int maeTipoServicioId) {
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

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GatServicioUmbrales)) {
            return false;
        }
        GatServicioUmbrales other = (GatServicioUmbrales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatServicioUmbrales[ id=" + id + " ]";
    }
    
}
