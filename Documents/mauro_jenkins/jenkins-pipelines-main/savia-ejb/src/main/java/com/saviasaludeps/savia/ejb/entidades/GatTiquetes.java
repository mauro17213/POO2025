/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "gat_tiquetes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatTiquetes.findAll", query = "SELECT g FROM GatTiquetes g"),
    @NamedQuery(name = "GatTiquetes.findById", query = "SELECT g FROM GatTiquetes g WHERE g.id = :id"),
    @NamedQuery(name = "GatTiquetes.findByMaeTipoServicioId", query = "SELECT g FROM GatTiquetes g WHERE g.maeTipoServicioId = :maeTipoServicioId"),
    @NamedQuery(name = "GatTiquetes.findByMaeTipoServicioCodigo", query = "SELECT g FROM GatTiquetes g WHERE g.maeTipoServicioCodigo = :maeTipoServicioCodigo"),
    @NamedQuery(name = "GatTiquetes.findByMaeTipoServicioValor", query = "SELECT g FROM GatTiquetes g WHERE g.maeTipoServicioValor = :maeTipoServicioValor"),
    @NamedQuery(name = "GatTiquetes.findByNumero", query = "SELECT g FROM GatTiquetes g WHERE g.numero = :numero"),
    @NamedQuery(name = "GatTiquetes.findByEstado", query = "SELECT g FROM GatTiquetes g WHERE g.estado = :estado"),
    @NamedQuery(name = "GatTiquetes.findByPrioritario", query = "SELECT g FROM GatTiquetes g WHERE g.prioritario = :prioritario"),
    @NamedQuery(name = "GatTiquetes.findByUsuarioCrea", query = "SELECT g FROM GatTiquetes g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatTiquetes.findByTerminalCrea", query = "SELECT g FROM GatTiquetes g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatTiquetes.findByFechaHoraCrea", query = "SELECT g FROM GatTiquetes g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GatTiquetes.findByFechaHoraLlamado", query = "SELECT g FROM GatTiquetes g WHERE g.fechaHoraLlamado = :fechaHoraLlamado"),
    @NamedQuery(name = "GatTiquetes.findByFechaHoraAtendido", query = "SELECT g FROM GatTiquetes g WHERE g.fechaHoraAtendido = :fechaHoraAtendido"),
    @NamedQuery(name = "GatTiquetes.findByFechaHoraFinaliza", query = "SELECT g FROM GatTiquetes g WHERE g.fechaHoraFinaliza = :fechaHoraFinaliza"),
    @NamedQuery(name = "GatTiquetes.findByFechaHoraAbandona", query = "SELECT g FROM GatTiquetes g WHERE g.fechaHoraAbandona = :fechaHoraAbandona")})
public class GatTiquetes implements Serializable {

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
    @Size(max = 10)
    @Column(name = "numero")
    private String numero;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "prioritario")
    private Boolean prioritario;
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
    @Column(name = "fecha_hora_llamado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraLlamado;
    @Column(name = "fecha_hora_atendido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAtendido;
    @Column(name = "fecha_hora_finaliza")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFinaliza;
    @Column(name = "fecha_hora_abandona")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAbandona;
    @OneToMany(mappedBy = "gatTiquetesId", fetch = FetchType.LAZY)
    private List<GatAtenciones> gatAtencionesList;
    @JoinColumn(name = "gat_usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GatUsuarios gatUsuariosId;
    @JoinColumn(name = "gn_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnSedes gnSedesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gatTiquetesId", fetch = FetchType.LAZY)
    private List<GatTiketeLlamados> gatTiketeLlamadosList;

    public GatTiquetes() {
    }

    public GatTiquetes(Integer id) {
        this.id = id;
    }

    public GatTiquetes(Integer id, int maeTipoServicioId, String maeTipoServicioCodigo, String maeTipoServicioValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoServicioId = maeTipoServicioId;
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
        this.maeTipoServicioValor = maeTipoServicioValor;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Boolean getPrioritario() {
        return prioritario;
    }

    public void setPrioritario(Boolean prioritario) {
        this.prioritario = prioritario;
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

    public Date getFechaHoraLlamado() {
        return fechaHoraLlamado;
    }

    public void setFechaHoraLlamado(Date fechaHoraLlamado) {
        this.fechaHoraLlamado = fechaHoraLlamado;
    }

    public Date getFechaHoraAtendido() {
        return fechaHoraAtendido;
    }

    public void setFechaHoraAtendido(Date fechaHoraAtendido) {
        this.fechaHoraAtendido = fechaHoraAtendido;
    }

    public Date getFechaHoraFinaliza() {
        return fechaHoraFinaliza;
    }

    public void setFechaHoraFinaliza(Date fechaHoraFinaliza) {
        this.fechaHoraFinaliza = fechaHoraFinaliza;
    }

    public Date getFechaHoraAbandona() {
        return fechaHoraAbandona;
    }

    public void setFechaHoraAbandona(Date fechaHoraAbandona) {
        this.fechaHoraAbandona = fechaHoraAbandona;
    }

    @XmlTransient
    public List<GatAtenciones> getGatAtencionesList() {
        return gatAtencionesList;
    }

    public void setGatAtencionesList(List<GatAtenciones> gatAtencionesList) {
        this.gatAtencionesList = gatAtencionesList;
    }

    public GatUsuarios getGatUsuariosId() {
        return gatUsuariosId;
    }

    public void setGatUsuariosId(GatUsuarios gatUsuariosId) {
        this.gatUsuariosId = gatUsuariosId;
    }

    public GnSedes getGnSedesId() {
        return gnSedesId;
    }

    public void setGnSedesId(GnSedes gnSedesId) {
        this.gnSedesId = gnSedesId;
    }

    @XmlTransient
    public List<GatTiketeLlamados> getGatTiketeLlamadosList() {
        return gatTiketeLlamadosList;
    }

    public void setGatTiketeLlamadosList(List<GatTiketeLlamados> gatTiketeLlamadosList) {
        this.gatTiketeLlamadosList = gatTiketeLlamadosList;
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
        if (!(object instanceof GatTiquetes)) {
            return false;
        }
        GatTiquetes other = (GatTiquetes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatTiquetes[ id=" + id + " ]";
    }
    
}
