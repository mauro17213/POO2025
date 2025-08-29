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
@Table(name = "rco_conciliaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RcoConciliaciones.findAll", query = "SELECT r FROM RcoConciliaciones r"),
    @NamedQuery(name = "RcoConciliaciones.findById", query = "SELECT r FROM RcoConciliaciones r WHERE r.id = :id"),
    @NamedQuery(name = "RcoConciliaciones.findByNombre", query = "SELECT r FROM RcoConciliaciones r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "RcoConciliaciones.findByDescripcion", query = "SELECT r FROM RcoConciliaciones r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RcoConciliaciones.findByEstado", query = "SELECT r FROM RcoConciliaciones r WHERE r.estado = :estado"),
    @NamedQuery(name = "RcoConciliaciones.findByNumeroContrato", query = "SELECT r FROM RcoConciliaciones r WHERE r.numeroContrato = :numeroContrato"),
    @NamedQuery(name = "RcoConciliaciones.findByCntContratoId", query = "SELECT r FROM RcoConciliaciones r WHERE r.cntContratoId = :cntContratoId"),
    @NamedQuery(name = "RcoConciliaciones.findByFechaInicio", query = "SELECT r FROM RcoConciliaciones r WHERE r.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "RcoConciliaciones.findByFechaFin", query = "SELECT r FROM RcoConciliaciones r WHERE r.fechaFin = :fechaFin"),
    @NamedQuery(name = "RcoConciliaciones.findByCorreoEnvio", query = "SELECT r FROM RcoConciliaciones r WHERE r.correoEnvio = :correoEnvio"),
    @NamedQuery(name = "RcoConciliaciones.findByCantidadItems", query = "SELECT r FROM RcoConciliaciones r WHERE r.cantidadItems = :cantidadItems"),
    @NamedQuery(name = "RcoConciliaciones.findByCantidadItemsRecobrados", query = "SELECT r FROM RcoConciliaciones r WHERE r.cantidadItemsRecobrados = :cantidadItemsRecobrados"),
    @NamedQuery(name = "RcoConciliaciones.findByValorTotalConciliado", query = "SELECT r FROM RcoConciliaciones r WHERE r.valorTotalConciliado = :valorTotalConciliado"),
    @NamedQuery(name = "RcoConciliaciones.findByValorRestanteNoConciliado", query = "SELECT r FROM RcoConciliaciones r WHERE r.valorRestanteNoConciliado = :valorRestanteNoConciliado"),
    @NamedQuery(name = "RcoConciliaciones.findByValorConciliacion", query = "SELECT r FROM RcoConciliaciones r WHERE r.valorConciliacion = :valorConciliacion"),
    @NamedQuery(name = "RcoConciliaciones.findByUsuarioCrea", query = "SELECT r FROM RcoConciliaciones r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RcoConciliaciones.findByTerminalCrea", query = "SELECT r FROM RcoConciliaciones r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RcoConciliaciones.findByFechaHoraCrea", query = "SELECT r FROM RcoConciliaciones r WHERE r.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "RcoConciliaciones.findByUsuarioModifica", query = "SELECT r FROM RcoConciliaciones r WHERE r.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "RcoConciliaciones.findByTerminalModifica", query = "SELECT r FROM RcoConciliaciones r WHERE r.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "RcoConciliaciones.findByFechaHoraModifica", query = "SELECT r FROM RcoConciliaciones r WHERE r.fechaHoraModifica = :fechaHoraModifica")})
public class RcoConciliaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2048)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Integer estado;
    @Size(max = 32)
    @Column(name = "numero_contrato")
    private String numeroContrato;
    @Column(name = "cnt_contrato_id")
    private Integer cntContratoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 128)
    @Column(name = "correo_envio")
    private String correoEnvio;
    @Column(name = "cantidad_items")
    private Integer cantidadItems;
    @Column(name = "cantidad_items_recobrados")
    private Integer cantidadItemsRecobrados;
    @Column(name = "valor_total_conciliado")
    private Long valorTotalConciliado;
    @Column(name = "valor_restante_no_conciliado")
    private Long valorRestanteNoConciliado;
    @Column(name = "valor_conciliacion")
    private Long valorConciliacion;
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
    @OneToMany(mappedBy = "rcoConciliacionesId", fetch = FetchType.LAZY)
    private List<RcoConciliacionGestiones> rcoConciliacionGestionesList;
    @JoinColumn(name = "cnt_presadores_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPresadoresSedesId;
    @OneToMany(mappedBy = "rcoConciliacionesId", fetch = FetchType.LAZY)
    private List<RcoConciliacionAdjuntos> rcoConciliacionAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rcoConciliacionesId", fetch = FetchType.LAZY)
    private List<RcoActas> rcoActasList;
    @OneToMany(mappedBy = "rcoConciliacionId", fetch = FetchType.LAZY)
    private List<RcoFacturaDetalles> rcoFacturaDetallesList;

    public RcoConciliaciones() {
    }

    public RcoConciliaciones(Integer id) {
        this.id = id;
    }

    public RcoConciliaciones(Integer id, Date fechaInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaInicio = fechaInicio;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Integer getCntContratoId() {
        return cntContratoId;
    }

    public void setCntContratoId(Integer cntContratoId) {
        this.cntContratoId = cntContratoId;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getCorreoEnvio() {
        return correoEnvio;
    }

    public void setCorreoEnvio(String correoEnvio) {
        this.correoEnvio = correoEnvio;
    }

    public Integer getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
    }

    public Integer getCantidadItemsRecobrados() {
        return cantidadItemsRecobrados;
    }

    public void setCantidadItemsRecobrados(Integer cantidadItemsRecobrados) {
        this.cantidadItemsRecobrados = cantidadItemsRecobrados;
    }

    public Long getValorTotalConciliado() {
        return valorTotalConciliado;
    }

    public void setValorTotalConciliado(Long valorTotalConciliado) {
        this.valorTotalConciliado = valorTotalConciliado;
    }

    public Long getValorRestanteNoConciliado() {
        return valorRestanteNoConciliado;
    }

    public void setValorRestanteNoConciliado(Long valorRestanteNoConciliado) {
        this.valorRestanteNoConciliado = valorRestanteNoConciliado;
    }

    public Long getValorConciliacion() {
        return valorConciliacion;
    }

    public void setValorConciliacion(Long valorConciliacion) {
        this.valorConciliacion = valorConciliacion;
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

    @XmlTransient
    public List<RcoConciliacionGestiones> getRcoConciliacionGestionesList() {
        return rcoConciliacionGestionesList;
    }

    public void setRcoConciliacionGestionesList(List<RcoConciliacionGestiones> rcoConciliacionGestionesList) {
        this.rcoConciliacionGestionesList = rcoConciliacionGestionesList;
    }

    public CntPrestadorSedes getCntPresadoresSedesId() {
        return cntPresadoresSedesId;
    }

    public void setCntPresadoresSedesId(CntPrestadorSedes cntPresadoresSedesId) {
        this.cntPresadoresSedesId = cntPresadoresSedesId;
    }

    @XmlTransient
    public List<RcoConciliacionAdjuntos> getRcoConciliacionAdjuntosList() {
        return rcoConciliacionAdjuntosList;
    }

    public void setRcoConciliacionAdjuntosList(List<RcoConciliacionAdjuntos> rcoConciliacionAdjuntosList) {
        this.rcoConciliacionAdjuntosList = rcoConciliacionAdjuntosList;
    }

    @XmlTransient
    public List<RcoActas> getRcoActasList() {
        return rcoActasList;
    }

    public void setRcoActasList(List<RcoActas> rcoActasList) {
        this.rcoActasList = rcoActasList;
    }

    @XmlTransient
    public List<RcoFacturaDetalles> getRcoFacturaDetallesList() {
        return rcoFacturaDetallesList;
    }

    public void setRcoFacturaDetallesList(List<RcoFacturaDetalles> rcoFacturaDetallesList) {
        this.rcoFacturaDetallesList = rcoFacturaDetallesList;
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
        if (!(object instanceof RcoConciliaciones)) {
            return false;
        }
        RcoConciliaciones other = (RcoConciliaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RcoConciliaciones[ id=" + id + " ]";
    }
    
}
