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
@Table(name = "au_anexo2_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo2Items.findAll", query = "SELECT a FROM AuAnexo2Items a"),
    @NamedQuery(name = "AuAnexo2Items.findById", query = "SELECT a FROM AuAnexo2Items a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo2Items.findByMaTecnologiaCodigo", query = "SELECT a FROM AuAnexo2Items a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AuAnexo2Items.findByMaTecnologiaId", query = "SELECT a FROM AuAnexo2Items a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AuAnexo2Items.findByMaTecnologiaValor", query = "SELECT a FROM AuAnexo2Items a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AuAnexo2Items.findByCantidadSolicitada", query = "SELECT a FROM AuAnexo2Items a WHERE a.cantidadSolicitada = :cantidadSolicitada"),
    @NamedQuery(name = "AuAnexo2Items.findByMaServicioSolicitadoId", query = "SELECT a FROM AuAnexo2Items a WHERE a.maServicioSolicitadoId = :maServicioSolicitadoId"),
    @NamedQuery(name = "AuAnexo2Items.findByMaServicioSolicitadoCodigo", query = "SELECT a FROM AuAnexo2Items a WHERE a.maServicioSolicitadoCodigo = :maServicioSolicitadoCodigo"),
    @NamedQuery(name = "AuAnexo2Items.findByMaServicioSolicitadoValor", query = "SELECT a FROM AuAnexo2Items a WHERE a.maServicioSolicitadoValor = :maServicioSolicitadoValor"),
    @NamedQuery(name = "AuAnexo2Items.findByEstado", query = "SELECT a FROM AuAnexo2Items a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexo2Items.findByUsuarioCrea", query = "SELECT a FROM AuAnexo2Items a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo2Items.findByTerminalCrea", query = "SELECT a FROM AuAnexo2Items a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo2Items.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo2Items a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo2Items.findByUsuarioModifica", query = "SELECT a FROM AuAnexo2Items a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo2Items.findByTerminalModifica", query = "SELECT a FROM AuAnexo2Items a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexo2Items.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo2Items a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexo2Items implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_tecnologia_id")
    private int maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_solicitada")
    private int cantidadSolicitada;
    @Column(name = "ma_servicio_solicitado_id")
    private Integer maServicioSolicitadoId;
    @Size(max = 32)
    @Column(name = "ma_servicio_solicitado_codigo")
    private String maServicioSolicitadoCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_solicitado_valor")
    private String maServicioSolicitadoValor;
    @Column(name = "estado")
    private Integer estado;
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
    @OneToMany(mappedBy = "auAnexo2ItemsId", fetch = FetchType.LAZY)
    private List<AuAnexo4Items> auAnexo4ItemsList;
    @JoinColumn(name = "au_anexos2_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos2 auAnexos2Id;

    public AuAnexo2Items() {
    }

    public AuAnexo2Items(Integer id) {
        this.id = id;
    }

    public AuAnexo2Items(Integer id, String maTecnologiaCodigo, int maTecnologiaId, String maTecnologiaValor, int cantidadSolicitada, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaValor = maTecnologiaValor;
        this.cantidadSolicitada = cantidadSolicitada;
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

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getMaServicioSolicitadoId() {
        return maServicioSolicitadoId;
    }

    public void setMaServicioSolicitadoId(Integer maServicioSolicitadoId) {
        this.maServicioSolicitadoId = maServicioSolicitadoId;
    }

    public String getMaServicioSolicitadoCodigo() {
        return maServicioSolicitadoCodigo;
    }

    public void setMaServicioSolicitadoCodigo(String maServicioSolicitadoCodigo) {
        this.maServicioSolicitadoCodigo = maServicioSolicitadoCodigo;
    }

    public String getMaServicioSolicitadoValor() {
        return maServicioSolicitadoValor;
    }

    public void setMaServicioSolicitadoValor(String maServicioSolicitadoValor) {
        this.maServicioSolicitadoValor = maServicioSolicitadoValor;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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
    public List<AuAnexo4Items> getAuAnexo4ItemsList() {
        return auAnexo4ItemsList;
    }

    public void setAuAnexo4ItemsList(List<AuAnexo4Items> auAnexo4ItemsList) {
        this.auAnexo4ItemsList = auAnexo4ItemsList;
    }

    public AuAnexos2 getAuAnexos2Id() {
        return auAnexos2Id;
    }

    public void setAuAnexos2Id(AuAnexos2 auAnexos2Id) {
        this.auAnexos2Id = auAnexos2Id;
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
        if (!(object instanceof AuAnexo2Items)) {
            return false;
        }
        AuAnexo2Items other = (AuAnexo2Items) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo2Items[ id=" + id + " ]";
    }
    
}
