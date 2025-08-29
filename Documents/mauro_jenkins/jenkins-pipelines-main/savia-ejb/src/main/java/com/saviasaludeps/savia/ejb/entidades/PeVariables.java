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
@Table(name = "pe_variables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeVariables.findAll", query = "SELECT p FROM PeVariables p"),
    @NamedQuery(name = "PeVariables.findById", query = "SELECT p FROM PeVariables p WHERE p.id = :id"),
    @NamedQuery(name = "PeVariables.findByNombre", query = "SELECT p FROM PeVariables p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PeVariables.findByDescripcion", query = "SELECT p FROM PeVariables p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PeVariables.findByActiva", query = "SELECT p FROM PeVariables p WHERE p.activa = :activa"),
    @NamedQuery(name = "PeVariables.findByTipo", query = "SELECT p FROM PeVariables p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "PeVariables.findByRecurrente", query = "SELECT p FROM PeVariables p WHERE p.recurrente = :recurrente"),
    @NamedQuery(name = "PeVariables.findByObligatoria", query = "SELECT p FROM PeVariables p WHERE p.obligatoria = :obligatoria"),
    @NamedQuery(name = "PeVariables.findByValidacion", query = "SELECT p FROM PeVariables p WHERE p.validacion = :validacion"),
    @NamedQuery(name = "PeVariables.findByInsumoCalculo", query = "SELECT p FROM PeVariables p WHERE p.insumoCalculo = :insumoCalculo"),
    @NamedQuery(name = "PeVariables.findByOperacion", query = "SELECT p FROM PeVariables p WHERE p.operacion = :operacion"),
    @NamedQuery(name = "PeVariables.findByEditable", query = "SELECT p FROM PeVariables p WHERE p.editable = :editable"),
    @NamedQuery(name = "PeVariables.findByUsuarioCrea", query = "SELECT p FROM PeVariables p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeVariables.findByTerminalCrea", query = "SELECT p FROM PeVariables p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeVariables.findByFechaHoraCrea", query = "SELECT p FROM PeVariables p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeVariables.findByUsuarioModifica", query = "SELECT p FROM PeVariables p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeVariables.findByTerminalModifica", query = "SELECT p FROM PeVariables p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeVariables.findByFechaHoraModifica", query = "SELECT p FROM PeVariables p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeVariables implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "activa")
    private boolean activa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private short tipo;
    @Column(name = "recurrente")
    private Boolean recurrente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "obligatoria")
    private boolean obligatoria;
    @Size(max = 2147483647)
    @Column(name = "validacion")
    private String validacion;
    @Size(max = 2147483647)
    @Column(name = "insumo_calculo")
    private String insumoCalculo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "operacion")
    private BigDecimal operacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "editable")
    private boolean editable;
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
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peVariablesId", fetch = FetchType.LAZY)
    private List<PeVariablesValores> peVariablesValoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peVariablesId", fetch = FetchType.LAZY)
    private List<PeVariablesValoresHistoricos> peVariablesValoresHistoricosList;

    public PeVariables() {
    }

    public PeVariables(Integer id) {
        this.id = id;
    }

    public PeVariables(Integer id, String nombre, String descripcion, boolean activa, short tipo, boolean obligatoria, boolean editable, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activa = activa;
        this.tipo = tipo;
        this.obligatoria = obligatoria;
        this.editable = editable;
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

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public Boolean getRecurrente() {
        return recurrente;
    }

    public void setRecurrente(Boolean recurrente) {
        this.recurrente = recurrente;
    }

    public boolean getObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }

    public String getInsumoCalculo() {
        return insumoCalculo;
    }

    public void setInsumoCalculo(String insumoCalculo) {
        this.insumoCalculo = insumoCalculo;
    }

    public BigDecimal getOperacion() {
        return operacion;
    }

    public void setOperacion(BigDecimal operacion) {
        this.operacion = operacion;
    }

    public boolean getEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
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

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    @XmlTransient
    public List<PeVariablesValores> getPeVariablesValoresList() {
        return peVariablesValoresList;
    }

    public void setPeVariablesValoresList(List<PeVariablesValores> peVariablesValoresList) {
        this.peVariablesValoresList = peVariablesValoresList;
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
        if (!(object instanceof PeVariables)) {
            return false;
        }
        PeVariables other = (PeVariables) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeVariables[ id=" + id + " ]";
    }
    
}
