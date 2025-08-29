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
@Table(name = "pe_gestiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeGestiones.findAll", query = "SELECT p FROM PeGestiones p"),
    @NamedQuery(name = "PeGestiones.findById", query = "SELECT p FROM PeGestiones p WHERE p.id = :id"),
    @NamedQuery(name = "PeGestiones.findByTipo", query = "SELECT p FROM PeGestiones p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PeGestiones.findByMaeTipoId", query = "SELECT p FROM PeGestiones p WHERE p.maeTipoId = :maeTipoId"),
    @NamedQuery(name = "PeGestiones.findByMaeTipoCodigo", query = "SELECT p FROM PeGestiones p WHERE p.maeTipoCodigo = :maeTipoCodigo"),
    @NamedQuery(name = "PeGestiones.findByMaeTipoValor", query = "SELECT p FROM PeGestiones p WHERE p.maeTipoValor = :maeTipoValor"),
    @NamedQuery(name = "PeGestiones.findByDescripcion", query = "SELECT p FROM PeGestiones p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PeGestiones.findByFuenteOrigen", query = "SELECT p FROM PeGestiones p WHERE p.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "PeGestiones.findByBorrado", query = "SELECT p FROM PeGestiones p WHERE p.borrado = :borrado"),
    @NamedQuery(name = "PeGestiones.findByBorradoObservacion", query = "SELECT p FROM PeGestiones p WHERE p.borradoObservacion = :borradoObservacion"),
    @NamedQuery(name = "PeGestiones.findByUsuarioCrea", query = "SELECT p FROM PeGestiones p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeGestiones.findByTerminalCrea", query = "SELECT p FROM PeGestiones p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeGestiones.findByFechaHoraCrea", query = "SELECT p FROM PeGestiones p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeGestiones.findByUsuarioModifica", query = "SELECT p FROM PeGestiones p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeGestiones.findByTerminalModifica", query = "SELECT p FROM PeGestiones p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeGestiones.findByFechaHoraModifica", query = "SELECT p FROM PeGestiones p WHERE p.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "PeGestiones.findByUsuarioBorra", query = "SELECT p FROM PeGestiones p WHERE p.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "PeGestiones.findByTerminalBorra", query = "SELECT p FROM PeGestiones p WHERE p.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "PeGestiones.findByFechaHoraBorra", query = "SELECT p FROM PeGestiones p WHERE p.fechaHoraBorra = :fechaHoraBorra")})
public class PeGestiones implements Serializable {

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
    @Column(name = "mae_tipo_id")
    private Integer maeTipoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_codigo")
    private String maeTipoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_valor")
    private String maeTipoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fuente_origen")
    private Integer fuenteOrigen;
    @Column(name = "borrado")
    private Boolean borrado;
    @Size(max = 512)
    @Column(name = "borrado_observacion")
    private String borradoObservacion;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @JoinColumn(name = "pe_afiliados_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeAfiliadosProgramas peAfiliadosProgramasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peGestionesId", fetch = FetchType.LAZY)
    private List<PeGestionesHistorico> peGestionesHistoricoList;
    @OneToMany(mappedBy = "peGestionesId", fetch = FetchType.LAZY)
    private List<PeAdjuntos> peAdjuntosList;

    public PeGestiones() {
    }

    public PeGestiones(Integer id) {
        this.id = id;
    }

    public PeGestiones(Integer id, int tipo, String descripcion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
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

    public Integer getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(Integer maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Integer fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    public PeAfiliadosProgramas getPeAfiliadosProgramasId() {
        return peAfiliadosProgramasId;
    }

    public void setPeAfiliadosProgramasId(PeAfiliadosProgramas peAfiliadosProgramasId) {
        this.peAfiliadosProgramasId = peAfiliadosProgramasId;
    }

    @XmlTransient
    public List<PeGestionesHistorico> getPeGestionesHistoricoList() {
        return peGestionesHistoricoList;
    }

    public void setPeGestionesHistoricoList(List<PeGestionesHistorico> peGestionesHistoricoList) {
        this.peGestionesHistoricoList = peGestionesHistoricoList;
    }

    @XmlTransient
    public List<PeAdjuntos> getPeAdjuntosList() {
        return peAdjuntosList;
    }

    public void setPeAdjuntosList(List<PeAdjuntos> peAdjuntosList) {
        this.peAdjuntosList = peAdjuntosList;
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
        if (!(object instanceof PeGestiones)) {
            return false;
        }
        PeGestiones other = (PeGestiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeGestiones[ id=" + id + " ]";
    }
    
}
