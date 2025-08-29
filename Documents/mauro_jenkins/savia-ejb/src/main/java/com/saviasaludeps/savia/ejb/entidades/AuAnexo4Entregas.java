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
@Table(name = "au_anexo4_entregas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo4Entregas.findAll", query = "SELECT a FROM AuAnexo4Entregas a"),
    @NamedQuery(name = "AuAnexo4Entregas.findById", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo4Entregas.findByReclamaAfiliado", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.reclamaAfiliado = :reclamaAfiliado"),
    @NamedQuery(name = "AuAnexo4Entregas.findByFechaHoraEntrega", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.fechaHoraEntrega = :fechaHoraEntrega"),
    @NamedQuery(name = "AuAnexo4Entregas.findByTipoEntrega", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.tipoEntrega = :tipoEntrega"),
    @NamedQuery(name = "AuAnexo4Entregas.findByFuenteOrigen", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "AuAnexo4Entregas.findByCantidadAutorizada", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.cantidadAutorizada = :cantidadAutorizada"),
    @NamedQuery(name = "AuAnexo4Entregas.findByCantidadEntregada", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.cantidadEntregada = :cantidadEntregada"),
    @NamedQuery(name = "AuAnexo4Entregas.findByCantidadPendiente", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.cantidadPendiente = :cantidadPendiente"),
    @NamedQuery(name = "AuAnexo4Entregas.findByNombreReclama", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.nombreReclama = :nombreReclama"),
    @NamedQuery(name = "AuAnexo4Entregas.findByTelefonoReclama", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.telefonoReclama = :telefonoReclama"),
    @NamedQuery(name = "AuAnexo4Entregas.findByCelularReclama", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.celularReclama = :celularReclama"),
    @NamedQuery(name = "AuAnexo4Entregas.findByMaeCausaNoEntregaId", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.maeCausaNoEntregaId = :maeCausaNoEntregaId"),
    @NamedQuery(name = "AuAnexo4Entregas.findByMaeCausaNoEntregaCodigo", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.maeCausaNoEntregaCodigo = :maeCausaNoEntregaCodigo"),
    @NamedQuery(name = "AuAnexo4Entregas.findByMaeCausaNoEntregaValor", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.maeCausaNoEntregaValor = :maeCausaNoEntregaValor"),
    @NamedQuery(name = "AuAnexo4Entregas.findByAnulada", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.anulada = :anulada"),
    @NamedQuery(name = "AuAnexo4Entregas.findByAnulaObservacion", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.anulaObservacion = :anulaObservacion"),
    @NamedQuery(name = "AuAnexo4Entregas.findByNoPrestadoObservacion", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.noPrestadoObservacion = :noPrestadoObservacion"),
    @NamedQuery(name = "AuAnexo4Entregas.findByUsuarioCrea", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo4Entregas.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo4Entregas.findByTerminalCrea", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo4Entregas.findByUsuarioModifica", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo4Entregas.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AuAnexo4Entregas.findByTerminalModifica", query = "SELECT a FROM AuAnexo4Entregas a WHERE a.terminalModifica = :terminalModifica")})
public class AuAnexo4Entregas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reclama_afiliado")
    private boolean reclamaAfiliado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_entrega")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_entrega")
    private int tipoEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fuente_origen")
    private short fuenteOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_autorizada")
    private int cantidadAutorizada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_entregada")
    private int cantidadEntregada;
    @Column(name = "cantidad_pendiente")
    private Integer cantidadPendiente;
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
    @Column(name = "mae_causa_no_entrega_id")
    private Integer maeCausaNoEntregaId;
    @Size(max = 8)
    @Column(name = "mae_causa_no_entrega_codigo")
    private String maeCausaNoEntregaCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_no_entrega_valor")
    private String maeCausaNoEntregaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulada")
    private boolean anulada;
    @Size(max = 1024)
    @Column(name = "anula_observacion")
    private String anulaObservacion;
    @Size(max = 1024)
    @Column(name = "no_prestado_observacion")
    private String noPrestadoObservacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @JoinColumn(name = "au_anexo4_items_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexo4Items auAnexo4ItemsId;
    @JoinColumn(name = "au_anexos4_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos4 auAnexos4Id;
    @OneToMany(mappedBy = "auAnexos4EntregasId", fetch = FetchType.LAZY)
    private List<AuSolicitudAdjuntos> auSolicitudAdjuntosList;
    @OneToMany(mappedBy = "auAnexo4EntregasId", fetch = FetchType.LAZY)
    private List<AuEntregaCargaDetalles> auEntregaCargaDetallesList;

    public AuAnexo4Entregas() {
    }

    public AuAnexo4Entregas(Integer id) {
        this.id = id;
    }

    public AuAnexo4Entregas(Integer id, boolean reclamaAfiliado, Date fechaHoraEntrega, int tipoEntrega, short fuenteOrigen, int cantidadAutorizada, int cantidadEntregada, String nombreReclama, boolean anulada, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.reclamaAfiliado = reclamaAfiliado;
        this.fechaHoraEntrega = fechaHoraEntrega;
        this.tipoEntrega = tipoEntrega;
        this.fuenteOrigen = fuenteOrigen;
        this.cantidadAutorizada = cantidadAutorizada;
        this.cantidadEntregada = cantidadEntregada;
        this.nombreReclama = nombreReclama;
        this.anulada = anulada;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.terminalCrea = terminalCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getReclamaAfiliado() {
        return reclamaAfiliado;
    }

    public void setReclamaAfiliado(boolean reclamaAfiliado) {
        this.reclamaAfiliado = reclamaAfiliado;
    }

    public Date getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Date fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public int getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(int tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public short getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(short fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public int getCantidadAutorizada() {
        return cantidadAutorizada;
    }

    public void setCantidadAutorizada(int cantidadAutorizada) {
        this.cantidadAutorizada = cantidadAutorizada;
    }

    public int getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(int cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public Integer getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Integer cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
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

    public Integer getMaeCausaNoEntregaId() {
        return maeCausaNoEntregaId;
    }

    public void setMaeCausaNoEntregaId(Integer maeCausaNoEntregaId) {
        this.maeCausaNoEntregaId = maeCausaNoEntregaId;
    }

    public String getMaeCausaNoEntregaCodigo() {
        return maeCausaNoEntregaCodigo;
    }

    public void setMaeCausaNoEntregaCodigo(String maeCausaNoEntregaCodigo) {
        this.maeCausaNoEntregaCodigo = maeCausaNoEntregaCodigo;
    }

    public String getMaeCausaNoEntregaValor() {
        return maeCausaNoEntregaValor;
    }

    public void setMaeCausaNoEntregaValor(String maeCausaNoEntregaValor) {
        this.maeCausaNoEntregaValor = maeCausaNoEntregaValor;
    }

    public boolean getAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public String getAnulaObservacion() {
        return anulaObservacion;
    }

    public void setAnulaObservacion(String anulaObservacion) {
        this.anulaObservacion = anulaObservacion;
    }

    public String getNoPrestadoObservacion() {
        return noPrestadoObservacion;
    }

    public void setNoPrestadoObservacion(String noPrestadoObservacion) {
        this.noPrestadoObservacion = noPrestadoObservacion;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public AuAnexo4Items getAuAnexo4ItemsId() {
        return auAnexo4ItemsId;
    }

    public void setAuAnexo4ItemsId(AuAnexo4Items auAnexo4ItemsId) {
        this.auAnexo4ItemsId = auAnexo4ItemsId;
    }

    public AuAnexos4 getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexos4 auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
    }

    @XmlTransient
    public List<AuSolicitudAdjuntos> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjuntos> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    @XmlTransient
    public List<AuEntregaCargaDetalles> getAuEntregaCargaDetallesList() {
        return auEntregaCargaDetallesList;
    }

    public void setAuEntregaCargaDetallesList(List<AuEntregaCargaDetalles> auEntregaCargaDetallesList) {
        this.auEntregaCargaDetallesList = auEntregaCargaDetallesList;
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
        if (!(object instanceof AuAnexo4Entregas)) {
            return false;
        }
        AuAnexo4Entregas other = (AuAnexo4Entregas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo4Entregas[ id=" + id + " ]";
    }
    
}
