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
@Table(name = "gat_atencion_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatAtencionHistoricos.findAll", query = "SELECT g FROM GatAtencionHistoricos g"),
    @NamedQuery(name = "GatAtencionHistoricos.findById", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.id = :id"),
    @NamedQuery(name = "GatAtencionHistoricos.findByMaeTipoServicioId", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.maeTipoServicioId = :maeTipoServicioId"),
    @NamedQuery(name = "GatAtencionHistoricos.findByMaeTipoServicioCodigo", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.maeTipoServicioCodigo = :maeTipoServicioCodigo"),
    @NamedQuery(name = "GatAtencionHistoricos.findByMaeTipoServicioValor", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.maeTipoServicioValor = :maeTipoServicioValor"),
    @NamedQuery(name = "GatAtencionHistoricos.findByFechaHoraInicio", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "GatAtencionHistoricos.findByFechaHoraFin", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "GatAtencionHistoricos.findByTiempo", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.tiempo = :tiempo"),
    @NamedQuery(name = "GatAtencionHistoricos.findByComentario", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.comentario = :comentario"),
    @NamedQuery(name = "GatAtencionHistoricos.findByUsuarioCrea", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatAtencionHistoricos.findByTerminalCrea", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatAtencionHistoricos.findByFechaHoraCrea", query = "SELECT g FROM GatAtencionHistoricos g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GatAtencionHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_tipo_servicio_id")
    private Integer maeTipoServicioId;
    @Size(max = 8)
    @Column(name = "mae_tipo_servicio_codigo")
    private String maeTipoServicioCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_servicio_valor")
    private String maeTipoServicioValor;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "tiempo")
    private Integer tiempo;
    @Size(max = 1024)
    @Column(name = "comentario")
    private String comentario;
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
    @JoinColumn(name = "gat_atenciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GatAtenciones gatAtencionesId;

    public GatAtencionHistoricos() {
    }

    public GatAtencionHistoricos(Integer id) {
        this.id = id;
    }

    public GatAtencionHistoricos(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public GatAtenciones getGatAtencionesId() {
        return gatAtencionesId;
    }

    public void setGatAtencionesId(GatAtenciones gatAtencionesId) {
        this.gatAtencionesId = gatAtencionesId;
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
        if (!(object instanceof GatAtencionHistoricos)) {
            return false;
        }
        GatAtencionHistoricos other = (GatAtencionHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatAtencionHistoricos[ id=" + id + " ]";
    }
    
}
