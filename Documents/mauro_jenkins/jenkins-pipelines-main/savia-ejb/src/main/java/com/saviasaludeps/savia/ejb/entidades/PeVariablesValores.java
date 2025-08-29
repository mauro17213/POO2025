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
@Table(name = "pe_variables_valores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeVariablesValores.findAll", query = "SELECT p FROM PeVariablesValores p"),
    @NamedQuery(name = "PeVariablesValores.findById", query = "SELECT p FROM PeVariablesValores p WHERE p.id = :id"),
    @NamedQuery(name = "PeVariablesValores.findByNombre", query = "SELECT p FROM PeVariablesValores p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PeVariablesValores.findByDescripcion", query = "SELECT p FROM PeVariablesValores p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PeVariablesValores.findByTipo", query = "SELECT p FROM PeVariablesValores p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PeVariablesValores.findByRecurrente", query = "SELECT p FROM PeVariablesValores p WHERE p.recurrente = :recurrente"),
    @NamedQuery(name = "PeVariablesValores.findByRecurrencia", query = "SELECT p FROM PeVariablesValores p WHERE p.recurrencia = :recurrencia"),
    @NamedQuery(name = "PeVariablesValores.findByValorFecha", query = "SELECT p FROM PeVariablesValores p WHERE p.valorFecha = :valorFecha"),
    @NamedQuery(name = "PeVariablesValores.findByValorEntero", query = "SELECT p FROM PeVariablesValores p WHERE p.valorEntero = :valorEntero"),
    @NamedQuery(name = "PeVariablesValores.findByValorDecimal", query = "SELECT p FROM PeVariablesValores p WHERE p.valorDecimal = :valorDecimal"),
    @NamedQuery(name = "PeVariablesValores.findByValorTexto", query = "SELECT p FROM PeVariablesValores p WHERE p.valorTexto = :valorTexto"),
    @NamedQuery(name = "PeVariablesValores.findByValorTextoLargo", query = "SELECT p FROM PeVariablesValores p WHERE p.valorTextoLargo = :valorTextoLargo"),
    @NamedQuery(name = "PeVariablesValores.findByUsuarioCrea", query = "SELECT p FROM PeVariablesValores p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeVariablesValores.findByTerminalCrea", query = "SELECT p FROM PeVariablesValores p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeVariablesValores.findByFechaHoraCrea", query = "SELECT p FROM PeVariablesValores p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeVariablesValores.findByUsuarioModifica", query = "SELECT p FROM PeVariablesValores p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeVariablesValores.findByTerminalModifica", query = "SELECT p FROM PeVariablesValores p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeVariablesValores.findByFechaHoraModifica", query = "SELECT p FROM PeVariablesValores p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeVariablesValores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "tipo")
    private Short tipo;
    @Column(name = "recurrente")
    private Boolean recurrente;
    @Column(name = "recurrencia")
    private Short recurrencia;
    @Column(name = "valor_fecha")
    @Temporal(TemporalType.DATE)
    private Date valorFecha;
    @Column(name = "valor_entero")
    private Integer valorEntero;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_decimal")
    private BigDecimal valorDecimal;
    @Size(max = 128)
    @Column(name = "valor_texto")
    private String valorTexto;
    @Size(max = 1024)
    @Column(name = "valor_texto_largo")
    private String valorTextoLargo;
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
    @JoinColumn(name = "pe_cargas_variables_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PeCargasVariables peCargasVariablesId;
    @JoinColumn(name = "pe_afiliados_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeAfiliadosProgramas peAfiliadosProgramasId;
    @JoinColumn(name = "pe_variables_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeVariables peVariablesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peVariablesValoresId", fetch = FetchType.LAZY)
    private List<PeVariablesValoresHistoricos> peVariablesValoresHistoricosList;

    public PeVariablesValores() {
    }

    public PeVariablesValores(Integer id) {
        this.id = id;
    }

    public PeVariablesValores(Integer id, String nombre, String descripcion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
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

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }

    public Boolean getRecurrente() {
        return recurrente;
    }

    public void setRecurrente(Boolean recurrente) {
        this.recurrente = recurrente;
    }

    public Short getRecurrencia() {
        return recurrencia;
    }

    public void setRecurrencia(Short recurrencia) {
        this.recurrencia = recurrencia;
    }

    public Date getValorFecha() {
        return valorFecha;
    }

    public void setValorFecha(Date valorFecha) {
        this.valorFecha = valorFecha;
    }

    public Integer getValorEntero() {
        return valorEntero;
    }

    public void setValorEntero(Integer valorEntero) {
        this.valorEntero = valorEntero;
    }

    public BigDecimal getValorDecimal() {
        return valorDecimal;
    }

    public void setValorDecimal(BigDecimal valorDecimal) {
        this.valorDecimal = valorDecimal;
    }

    public String getValorTexto() {
        return valorTexto;
    }

    public void setValorTexto(String valorTexto) {
        this.valorTexto = valorTexto;
    }

    public String getValorTextoLargo() {
        return valorTextoLargo;
    }

    public void setValorTextoLargo(String valorTextoLargo) {
        this.valorTextoLargo = valorTextoLargo;
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

    public PeCargasVariables getPeCargasVariablesId() {
        return peCargasVariablesId;
    }

    public void setPeCargasVariablesId(PeCargasVariables peCargasVariablesId) {
        this.peCargasVariablesId = peCargasVariablesId;
    }

    public PeAfiliadosProgramas getPeAfiliadosProgramasId() {
        return peAfiliadosProgramasId;
    }

    public void setPeAfiliadosProgramasId(PeAfiliadosProgramas peAfiliadosProgramasId) {
        this.peAfiliadosProgramasId = peAfiliadosProgramasId;
    }

    public PeVariables getPeVariablesId() {
        return peVariablesId;
    }

    public void setPeVariablesId(PeVariables peVariablesId) {
        this.peVariablesId = peVariablesId;
    }

    @XmlTransient
    public List<PeVariablesValoresHistoricos> getPeVariablesValoresHistoricosList() {
        return peVariablesValoresHistoricosList;
    }

    public void setPeVariablesValoresHistoricosList(List<PeVariablesValoresHistoricos> peVariablesValoresHistoricosList) {
        this.peVariablesValoresHistoricosList = peVariablesValoresHistoricosList;
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
        if (!(object instanceof PeVariablesValores)) {
            return false;
        }
        PeVariablesValores other = (PeVariablesValores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeVariablesValores[ id=" + id + " ]";
    }
    
}
