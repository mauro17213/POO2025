/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "fin_postulaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FinPostulaciones.findAll", query = "SELECT f FROM FinPostulaciones f"),
    @NamedQuery(name = "FinPostulaciones.findById", query = "SELECT f FROM FinPostulaciones f WHERE f.id = :id"),
    @NamedQuery(name = "FinPostulaciones.findByTipoPostulacion", query = "SELECT f FROM FinPostulaciones f WHERE f.tipoPostulacion = :tipoPostulacion"),
    @NamedQuery(name = "FinPostulaciones.findByPrestadorNit", query = "SELECT f FROM FinPostulaciones f WHERE f.prestadorNit = :prestadorNit"),
    @NamedQuery(name = "FinPostulaciones.findByPrestadorRazonSocial", query = "SELECT f FROM FinPostulaciones f WHERE f.prestadorRazonSocial = :prestadorRazonSocial"),
    @NamedQuery(name = "FinPostulaciones.findByPrestadorDepartamento", query = "SELECT f FROM FinPostulaciones f WHERE f.prestadorDepartamento = :prestadorDepartamento"),
    @NamedQuery(name = "FinPostulaciones.findByPrestadorMunicipio", query = "SELECT f FROM FinPostulaciones f WHERE f.prestadorMunicipio = :prestadorMunicipio"),
    @NamedQuery(name = "FinPostulaciones.findByPrestadorNaturaleza", query = "SELECT f FROM FinPostulaciones f WHERE f.prestadorNaturaleza = :prestadorNaturaleza"),
    @NamedQuery(name = "FinPostulaciones.findByPrestadorEstadoAdres", query = "SELECT f FROM FinPostulaciones f WHERE f.prestadorEstadoAdres = :prestadorEstadoAdres"),
    @NamedQuery(name = "FinPostulaciones.findByValorCapita", query = "SELECT f FROM FinPostulaciones f WHERE f.valorCapita = :valorCapita"),
    @NamedQuery(name = "FinPostulaciones.findByValorCapitaReajuste", query = "SELECT f FROM FinPostulaciones f WHERE f.valorCapitaReajuste = :valorCapitaReajuste"),
    @NamedQuery(name = "FinPostulaciones.findByValorPgp", query = "SELECT f FROM FinPostulaciones f WHERE f.valorPgp = :valorPgp"),
    @NamedQuery(name = "FinPostulaciones.findByValorCompromisos", query = "SELECT f FROM FinPostulaciones f WHERE f.valorCompromisos = :valorCompromisos"),
    @NamedQuery(name = "FinPostulaciones.findByValorEvento", query = "SELECT f FROM FinPostulaciones f WHERE f.valorEvento = :valorEvento"),
    @NamedQuery(name = "FinPostulaciones.findByValorProgramadoTotal", query = "SELECT f FROM FinPostulaciones f WHERE f.valorProgramadoTotal = :valorProgramadoTotal"),
    @NamedQuery(name = "FinPostulaciones.findByTotalPagado", query = "SELECT f FROM FinPostulaciones f WHERE f.totalPagado = :totalPagado"),
    @NamedQuery(name = "FinPostulaciones.findByBorrado", query = "SELECT f FROM FinPostulaciones f WHERE f.borrado = :borrado"),
    @NamedQuery(name = "FinPostulaciones.findByUsuarioCrea", query = "SELECT f FROM FinPostulaciones f WHERE f.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "FinPostulaciones.findByTerminalCrea", query = "SELECT f FROM FinPostulaciones f WHERE f.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "FinPostulaciones.findByFechaHoraCrea", query = "SELECT f FROM FinPostulaciones f WHERE f.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "FinPostulaciones.findByUsuarioModifica", query = "SELECT f FROM FinPostulaciones f WHERE f.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "FinPostulaciones.findByTerminalModifica", query = "SELECT f FROM FinPostulaciones f WHERE f.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "FinPostulaciones.findByFechaHoraModifica", query = "SELECT f FROM FinPostulaciones f WHERE f.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "FinPostulaciones.findByUsuarioBorra", query = "SELECT f FROM FinPostulaciones f WHERE f.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "FinPostulaciones.findByTerminalBorra", query = "SELECT f FROM FinPostulaciones f WHERE f.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "FinPostulaciones.findByFechaHoraBorra", query = "SELECT f FROM FinPostulaciones f WHERE f.fechaHoraBorra = :fechaHoraBorra")})
public class FinPostulaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_postulacion")
    private int tipoPostulacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "prestador_nit")
    private String prestadorNit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "prestador_razon_social")
    private String prestadorRazonSocial;
    @Size(max = 32)
    @Column(name = "prestador_departamento")
    private String prestadorDepartamento;
    @Size(max = 32)
    @Column(name = "prestador_municipio")
    private String prestadorMunicipio;
    @Size(max = 16)
    @Column(name = "prestador_naturaleza")
    private String prestadorNaturaleza;
    @Size(max = 16)
    @Column(name = "prestador_estado_adres")
    private String prestadorEstadoAdres;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_capita")
    private BigDecimal valorCapita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_capita_reajuste")
    private BigDecimal valorCapitaReajuste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pgp")
    private BigDecimal valorPgp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_compromisos")
    private BigDecimal valorCompromisos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_evento")
    private BigDecimal valorEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_programado_total")
    private BigDecimal valorProgramadoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_pagado")
    private BigDecimal totalPagado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
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
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "fin_giros_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FinGiros finGirosId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;

    public FinPostulaciones() {
    }

    public FinPostulaciones(Integer id) {
        this.id = id;
    }

    public FinPostulaciones(Integer id, int tipoPostulacion, String prestadorNit, String prestadorRazonSocial, BigDecimal valorCapita, BigDecimal valorCapitaReajuste, BigDecimal valorPgp, BigDecimal valorCompromisos, BigDecimal valorEvento, BigDecimal valorProgramadoTotal, BigDecimal totalPagado, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoPostulacion = tipoPostulacion;
        this.prestadorNit = prestadorNit;
        this.prestadorRazonSocial = prestadorRazonSocial;
        this.valorCapita = valorCapita;
        this.valorCapitaReajuste = valorCapitaReajuste;
        this.valorPgp = valorPgp;
        this.valorCompromisos = valorCompromisos;
        this.valorEvento = valorEvento;
        this.valorProgramadoTotal = valorProgramadoTotal;
        this.totalPagado = totalPagado;
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

    public int getTipoPostulacion() {
        return tipoPostulacion;
    }

    public void setTipoPostulacion(int tipoPostulacion) {
        this.tipoPostulacion = tipoPostulacion;
    }

    public String getPrestadorNit() {
        return prestadorNit;
    }

    public void setPrestadorNit(String prestadorNit) {
        this.prestadorNit = prestadorNit;
    }

    public String getPrestadorRazonSocial() {
        return prestadorRazonSocial;
    }

    public void setPrestadorRazonSocial(String prestadorRazonSocial) {
        this.prestadorRazonSocial = prestadorRazonSocial;
    }

    public String getPrestadorDepartamento() {
        return prestadorDepartamento;
    }

    public void setPrestadorDepartamento(String prestadorDepartamento) {
        this.prestadorDepartamento = prestadorDepartamento;
    }

    public String getPrestadorMunicipio() {
        return prestadorMunicipio;
    }

    public void setPrestadorMunicipio(String prestadorMunicipio) {
        this.prestadorMunicipio = prestadorMunicipio;
    }

    public String getPrestadorNaturaleza() {
        return prestadorNaturaleza;
    }

    public void setPrestadorNaturaleza(String prestadorNaturaleza) {
        this.prestadorNaturaleza = prestadorNaturaleza;
    }

    public String getPrestadorEstadoAdres() {
        return prestadorEstadoAdres;
    }

    public void setPrestadorEstadoAdres(String prestadorEstadoAdres) {
        this.prestadorEstadoAdres = prestadorEstadoAdres;
    }

    public BigDecimal getValorCapita() {
        return valorCapita;
    }

    public void setValorCapita(BigDecimal valorCapita) {
        this.valorCapita = valorCapita;
    }

    public BigDecimal getValorCapitaReajuste() {
        return valorCapitaReajuste;
    }

    public void setValorCapitaReajuste(BigDecimal valorCapitaReajuste) {
        this.valorCapitaReajuste = valorCapitaReajuste;
    }

    public BigDecimal getValorPgp() {
        return valorPgp;
    }

    public void setValorPgp(BigDecimal valorPgp) {
        this.valorPgp = valorPgp;
    }

    public BigDecimal getValorCompromisos() {
        return valorCompromisos;
    }

    public void setValorCompromisos(BigDecimal valorCompromisos) {
        this.valorCompromisos = valorCompromisos;
    }

    public BigDecimal getValorEvento() {
        return valorEvento;
    }

    public void setValorEvento(BigDecimal valorEvento) {
        this.valorEvento = valorEvento;
    }

    public BigDecimal getValorProgramadoTotal() {
        return valorProgramadoTotal;
    }

    public void setValorProgramadoTotal(BigDecimal valorProgramadoTotal) {
        this.valorProgramadoTotal = valorProgramadoTotal;
    }

    public BigDecimal getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(BigDecimal totalPagado) {
        this.totalPagado = totalPagado;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
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

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public FinGiros getFinGirosId() {
        return finGirosId;
    }

    public void setFinGirosId(FinGiros finGirosId) {
        this.finGirosId = finGirosId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
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
        if (!(object instanceof FinPostulaciones)) {
            return false;
        }
        FinPostulaciones other = (FinPostulaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.FinPostulaciones[ id=" + id + " ]";
    }
    
}
