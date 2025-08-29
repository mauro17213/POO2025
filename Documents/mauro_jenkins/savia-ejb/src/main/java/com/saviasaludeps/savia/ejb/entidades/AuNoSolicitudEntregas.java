/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "au_no_solicitud_entregas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuNoSolicitudEntregas.findAll", query = "SELECT a FROM AuNoSolicitudEntregas a"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findById", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.id = :id"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByFuenteOrigen", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByTipoEntrega", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.tipoEntrega = :tipoEntrega"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByCantidadPorEntregar", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.cantidadPorEntregar = :cantidadPorEntregar"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByCantidadEntregada", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.cantidadEntregada = :cantidadEntregada"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByCantidadPendiente", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.cantidadPendiente = :cantidadPendiente"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByReclamaAfiliado", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.reclamaAfiliado = :reclamaAfiliado"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByNombreReclama", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.nombreReclama = :nombreReclama"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByTelefonoReclama", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.telefonoReclama = :telefonoReclama"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByCelularReclama", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.celularReclama = :celularReclama"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByMaeCausaMoEntregaId", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.maeCausaMoEntregaId = :maeCausaMoEntregaId"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByMaeCausaMoEntregaCodigo", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.maeCausaMoEntregaCodigo = :maeCausaMoEntregaCodigo"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByMaeCausaMoEntregaValor", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.maeCausaMoEntregaValor = :maeCausaMoEntregaValor"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByMaeCausaMoEntregaTipo", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.maeCausaMoEntregaTipo = :maeCausaMoEntregaTipo"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByAnulada", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.anulada = :anulada"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByAnuladaObservacion", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.anuladaObservacion = :anuladaObservacion"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByNoPrestadoObservacion", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.noPrestadoObservacion = :noPrestadoObservacion"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByFechaHoraEntrega", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.fechaHoraEntrega = :fechaHoraEntrega"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByFaltantes", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.faltantes = :faltantes"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByNumeroEntrega", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.numeroEntrega = :numeroEntrega"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByUsuarioCrea", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByTerminalCrea", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByFechaHoraCrea", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByUsuarioModifica", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByTerminalModifica", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuNoSolicitudEntregas.findByFechaHoraModifica", query = "SELECT a FROM AuNoSolicitudEntregas a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuNoSolicitudEntregas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fuente_origen")
    private int fuenteOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_entrega")
    private int tipoEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_por_entregar")
    private int cantidadPorEntregar;
    @Column(name = "cantidad_entregada")
    private Integer cantidadEntregada;
    @Column(name = "cantidad_pendiente")
    private Integer cantidadPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reclama_afiliado")
    private boolean reclamaAfiliado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre_reclama")
    private String nombreReclama;
    @Size(max = 16)
    @Column(name = "telefono_reclama")
    private String telefonoReclama;
    @Size(max = 16)
    @Column(name = "celular_reclama")
    private String celularReclama;
    @Column(name = "mae_causa_mo_entrega_id")
    private Integer maeCausaMoEntregaId;
    @Size(max = 8)
    @Column(name = "mae_causa_mo_entrega_codigo")
    private String maeCausaMoEntregaCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_mo_entrega_valor")
    private String maeCausaMoEntregaValor;
    @Size(max = 4)
    @Column(name = "mae_causa_mo_entrega_tipo")
    private String maeCausaMoEntregaTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulada")
    private boolean anulada;
    @Size(max = 1024)
    @Column(name = "anulada_observacion")
    private String anuladaObservacion;
    @Size(max = 1024)
    @Column(name = "no_prestado_observacion")
    private String noPrestadoObservacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEntrega;
    @Column(name = "faltantes")
    private Integer faltantes;
    @Column(name = "numero_entrega")
    private Integer numeroEntrega;
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
    @JoinColumn(name = "au_no_solicitud_entrega_detalles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuNoSolicitudEntregaDetalles auNoSolicitudEntregaDetallesId;
    @JoinColumn(name = "au_no_solicitud_items_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuNoSolicitudItems auNoSolicitudItemsId;
    @JoinColumn(name = "au_no_solicitudes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuNoSolicitudes auNoSolicitudesId;
    @OneToMany(mappedBy = "auNoSolicitudEntregasId", fetch = FetchType.LAZY)
    private List<AuSolicitudAdjuntos> auSolicitudAdjuntosList;
    @OneToMany(mappedBy = "auNoSolicitudEntregasId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudEntregaCargaSucesos> auNoSolicitudEntregaCargaSucesosList;

    public AuNoSolicitudEntregas() {
    }

    public AuNoSolicitudEntregas(Integer id) {
        this.id = id;
    }

    public AuNoSolicitudEntregas(Integer id, int fuenteOrigen, int tipoEntrega, int cantidadPorEntregar, boolean reclamaAfiliado, String nombreReclama, boolean anulada, Date fechaHoraEntrega, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fuenteOrigen = fuenteOrigen;
        this.tipoEntrega = tipoEntrega;
        this.cantidadPorEntregar = cantidadPorEntregar;
        this.reclamaAfiliado = reclamaAfiliado;
        this.nombreReclama = nombreReclama;
        this.anulada = anulada;
        this.fechaHoraEntrega = fechaHoraEntrega;
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

    public int getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(int fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public int getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(int tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public int getCantidadPorEntregar() {
        return cantidadPorEntregar;
    }

    public void setCantidadPorEntregar(int cantidadPorEntregar) {
        this.cantidadPorEntregar = cantidadPorEntregar;
    }

    public Integer getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(Integer cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public Integer getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Integer cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public boolean getReclamaAfiliado() {
        return reclamaAfiliado;
    }

    public void setReclamaAfiliado(boolean reclamaAfiliado) {
        this.reclamaAfiliado = reclamaAfiliado;
    }

    public String getNombreReclama() {
        return nombreReclama;
    }

    public void setNombreReclama(String nombreReclama) {
        this.nombreReclama = nombreReclama;
    }

    public String getTelefonoReclama() {
        return telefonoReclama;
    }

    public void setTelefonoReclama(String telefonoReclama) {
        this.telefonoReclama = telefonoReclama;
    }

    public String getCelularReclama() {
        return celularReclama;
    }

    public void setCelularReclama(String celularReclama) {
        this.celularReclama = celularReclama;
    }

    public Integer getMaeCausaMoEntregaId() {
        return maeCausaMoEntregaId;
    }

    public void setMaeCausaMoEntregaId(Integer maeCausaMoEntregaId) {
        this.maeCausaMoEntregaId = maeCausaMoEntregaId;
    }

    public String getMaeCausaMoEntregaCodigo() {
        return maeCausaMoEntregaCodigo;
    }

    public void setMaeCausaMoEntregaCodigo(String maeCausaMoEntregaCodigo) {
        this.maeCausaMoEntregaCodigo = maeCausaMoEntregaCodigo;
    }

    public String getMaeCausaMoEntregaValor() {
        return maeCausaMoEntregaValor;
    }

    public void setMaeCausaMoEntregaValor(String maeCausaMoEntregaValor) {
        this.maeCausaMoEntregaValor = maeCausaMoEntregaValor;
    }

    public String getMaeCausaMoEntregaTipo() {
        return maeCausaMoEntregaTipo;
    }

    public void setMaeCausaMoEntregaTipo(String maeCausaMoEntregaTipo) {
        this.maeCausaMoEntregaTipo = maeCausaMoEntregaTipo;
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public String getAnuladaObservacion() {
        return anuladaObservacion;
    }

    public void setAnuladaObservacion(String anuladaObservacion) {
        this.anuladaObservacion = anuladaObservacion;
    }

    public String getNoPrestadoObservacion() {
        return noPrestadoObservacion;
    }

    public void setNoPrestadoObservacion(String noPrestadoObservacion) {
        this.noPrestadoObservacion = noPrestadoObservacion;
    }

    public Date getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Date fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public Integer getFaltantes() {
        return faltantes;
    }

    public void setFaltantes(Integer faltantes) {
        this.faltantes = faltantes;
    }

    public Integer getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(Integer numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
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

    public AuNoSolicitudEntregaDetalles getAuNoSolicitudEntregaDetallesId() {
        return auNoSolicitudEntregaDetallesId;
    }

    public void setAuNoSolicitudEntregaDetallesId(AuNoSolicitudEntregaDetalles auNoSolicitudEntregaDetallesId) {
        this.auNoSolicitudEntregaDetallesId = auNoSolicitudEntregaDetallesId;
    }

    public AuNoSolicitudItems getAuNoSolicitudItemsId() {
        return auNoSolicitudItemsId;
    }

    public void setAuNoSolicitudItemsId(AuNoSolicitudItems auNoSolicitudItemsId) {
        this.auNoSolicitudItemsId = auNoSolicitudItemsId;
    }

    public AuNoSolicitudes getAuNoSolicitudesId() {
        return auNoSolicitudesId;
    }

    public void setAuNoSolicitudesId(AuNoSolicitudes auNoSolicitudesId) {
        this.auNoSolicitudesId = auNoSolicitudesId;
    }

    @XmlTransient
    public List<AuSolicitudAdjuntos> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjuntos> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    @XmlTransient
    public List<AuNoSolicitudEntregaCargaSucesos> getAuNoSolicitudEntregaCargaSucesosList() {
        return auNoSolicitudEntregaCargaSucesosList;
    }

    public void setAuNoSolicitudEntregaCargaSucesosList(List<AuNoSolicitudEntregaCargaSucesos> auNoSolicitudEntregaCargaSucesosList) {
        this.auNoSolicitudEntregaCargaSucesosList = auNoSolicitudEntregaCargaSucesosList;
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
        if (!(object instanceof AuNoSolicitudEntregas)) {
            return false;
        }
        AuNoSolicitudEntregas other = (AuNoSolicitudEntregas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuNoSolicitudEntregas[ id=" + id + " ]";
    }
    
}
