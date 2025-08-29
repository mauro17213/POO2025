/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ma_iss2001_tarifarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaIss2001Tarifarios.findAll", query = "SELECT m FROM MaIss2001Tarifarios m"),
    @NamedQuery(name = "MaIss2001Tarifarios.findById", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.id = :id"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByReferencia", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.referencia = :referencia"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByCodigo", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByDescripcion", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByTipo", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByUvr", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.uvr = :uvr"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByMonto", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.monto = :monto"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByUsuarioCrea", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByTerminalCrea", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByFechaHoraCrea", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByUsuarioModifica", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByTerminalModifica", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaIss2001Tarifarios.findByFechaHoraModifica", query = "SELECT m FROM MaIss2001Tarifarios m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MaIss2001Tarifarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 8)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Column(name = "uvr")
    private Integer uvr;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "monto")
    private BigDecimal monto;
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
    @OneToMany(mappedBy = "maIss2001TarifariosId", fetch = FetchType.LAZY)
    private List<MaTecnologias> maTecnologiasList;

    public MaIss2001Tarifarios() {
    }

    public MaIss2001Tarifarios(Integer id) {
        this.id = id;
    }

    public MaIss2001Tarifarios(Integer id, String codigo, String descripcion, int tipo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipo = tipo;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getUvr() {
        return uvr;
    }

    public void setUvr(Integer uvr) {
        this.uvr = uvr;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
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
    public List<MaTecnologias> getMaTecnologiasList() {
        return maTecnologiasList;
    }

    public void setMaTecnologiasList(List<MaTecnologias> maTecnologiasList) {
        this.maTecnologiasList = maTecnologiasList;
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
        if (!(object instanceof MaIss2001Tarifarios)) {
            return false;
        }
        MaIss2001Tarifarios other = (MaIss2001Tarifarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaIss2001Tarifarios[ id=" + id + " ]";
    }
    
}
