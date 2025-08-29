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
@Table(name = "ant_anticipo_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntAnticipoItems.findAll", query = "SELECT a FROM AntAnticipoItems a"),
    @NamedQuery(name = "AntAnticipoItems.findById", query = "SELECT a FROM AntAnticipoItems a WHERE a.id = :id"),
    @NamedQuery(name = "AntAnticipoItems.findByTipoTecnologia", query = "SELECT a FROM AntAnticipoItems a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AntAnticipoItems.findByMaTecnologiaId", query = "SELECT a FROM AntAnticipoItems a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AntAnticipoItems.findByMaTecnologiaCodigo", query = "SELECT a FROM AntAnticipoItems a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AntAnticipoItems.findByMaTecnologiaValor", query = "SELECT a FROM AntAnticipoItems a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AntAnticipoItems.findByMaMedicamentoId", query = "SELECT a FROM AntAnticipoItems a WHERE a.maMedicamentoId = :maMedicamentoId"),
    @NamedQuery(name = "AntAnticipoItems.findByMaMedicamentoCodigo", query = "SELECT a FROM AntAnticipoItems a WHERE a.maMedicamentoCodigo = :maMedicamentoCodigo"),
    @NamedQuery(name = "AntAnticipoItems.findByMaMedicamentoValor", query = "SELECT a FROM AntAnticipoItems a WHERE a.maMedicamentoValor = :maMedicamentoValor"),
    @NamedQuery(name = "AntAnticipoItems.findByValorTecnologiaCotizada", query = "SELECT a FROM AntAnticipoItems a WHERE a.valorTecnologiaCotizada = :valorTecnologiaCotizada"),
    @NamedQuery(name = "AntAnticipoItems.findByBorrado", query = "SELECT a FROM AntAnticipoItems a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AntAnticipoItems.findByBorradoObservacion", query = "SELECT a FROM AntAnticipoItems a WHERE a.borradoObservacion = :borradoObservacion"),
    @NamedQuery(name = "AntAnticipoItems.findByCantidad", query = "SELECT a FROM AntAnticipoItems a WHERE a.cantidad = :cantidad"),
    @NamedQuery(name = "AntAnticipoItems.findByMpNumeroPrescripcion", query = "SELECT a FROM AntAnticipoItems a WHERE a.mpNumeroPrescripcion = :mpNumeroPrescripcion"),
    @NamedQuery(name = "AntAnticipoItems.findByValorTecnologiaPagada", query = "SELECT a FROM AntAnticipoItems a WHERE a.valorTecnologiaPagada = :valorTecnologiaPagada"),
    @NamedQuery(name = "AntAnticipoItems.findByUsuarioCrea", query = "SELECT a FROM AntAnticipoItems a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AntAnticipoItems.findByTerminalCrea", query = "SELECT a FROM AntAnticipoItems a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AntAnticipoItems.findByFechaHoraCrea", query = "SELECT a FROM AntAnticipoItems a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AntAnticipoItems.findByUsuarioBorra", query = "SELECT a FROM AntAnticipoItems a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AntAnticipoItems.findByTerminalBorra", query = "SELECT a FROM AntAnticipoItems a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AntAnticipoItems.findByFechaHoraBorra", query = "SELECT a FROM AntAnticipoItems a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AntAnticipoItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_tecnologia_id")
    private int maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Column(name = "ma_medicamento_id")
    private Integer maMedicamentoId;
    @Size(max = 32)
    @Column(name = "ma_medicamento_codigo")
    private String maMedicamentoCodigo;
    @Size(max = 512)
    @Column(name = "ma_medicamento_valor")
    private String maMedicamentoValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_tecnologia_cotizada")
    private BigDecimal valorTecnologiaCotizada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Size(max = 1024)
    @Column(name = "borrado_observacion")
    private String borradoObservacion;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Size(max = 32)
    @Column(name = "mp_numero_prescripcion")
    private String mpNumeroPrescripcion;
    @Column(name = "valor_tecnologia_pagada")
    private BigDecimal valorTecnologiaPagada;
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
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "ant_anticipos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AntAnticipos antAnticiposId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "antAnticipoItemsId", fetch = FetchType.LAZY)
    private List<AntAnticipoValores> antAnticipoValoresList;

    public AntAnticipoItems() {
    }

    public AntAnticipoItems(Integer id) {
        this.id = id;
    }

    public AntAnticipoItems(Integer id, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, BigDecimal valorTecnologiaCotizada, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.valorTecnologiaCotizada = valorTecnologiaCotizada;
        this.borrado = borrado;
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

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(Integer maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public BigDecimal getValorTecnologiaCotizada() {
        return valorTecnologiaCotizada;
    }

    public void setValorTecnologiaCotizada(BigDecimal valorTecnologiaCotizada) {
        this.valorTecnologiaCotizada = valorTecnologiaCotizada;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMpNumeroPrescripcion() {
        return mpNumeroPrescripcion;
    }

    public void setMpNumeroPrescripcion(String mpNumeroPrescripcion) {
        this.mpNumeroPrescripcion = mpNumeroPrescripcion;
    }

    public BigDecimal getValorTecnologiaPagada() {
        return valorTecnologiaPagada;
    }

    public void setValorTecnologiaPagada(BigDecimal valorTecnologiaPagada) {
        this.valorTecnologiaPagada = valorTecnologiaPagada;
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

    public AntAnticipos getAntAnticiposId() {
        return antAnticiposId;
    }

    public void setAntAnticiposId(AntAnticipos antAnticiposId) {
        this.antAnticiposId = antAnticiposId;
    }

    @XmlTransient
    public List<AntAnticipoValores> getAntAnticipoValoresList() {
        return antAnticipoValoresList;
    }

    public void setAntAnticipoValoresList(List<AntAnticipoValores> antAnticipoValoresList) {
        this.antAnticipoValoresList = antAnticipoValoresList;
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
        if (!(object instanceof AntAnticipoItems)) {
            return false;
        }
        AntAnticipoItems other = (AntAnticipoItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AntAnticipoItems[ id=" + id + " ]";
    }
    
}
