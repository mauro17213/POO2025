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
import javax.persistence.Lob;
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
@Table(name = "cntj_otrosies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjOtrosies.findAll", query = "SELECT c FROM CntjOtrosies c"),
    @NamedQuery(name = "CntjOtrosies.findById", query = "SELECT c FROM CntjOtrosies c WHERE c.id = :id"),
    @NamedQuery(name = "CntjOtrosies.findByNumero", query = "SELECT c FROM CntjOtrosies c WHERE c.numero = :numero"),
    @NamedQuery(name = "CntjOtrosies.findByTipo", query = "SELECT c FROM CntjOtrosies c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CntjOtrosies.findByFechaSuscripcion", query = "SELECT c FROM CntjOtrosies c WHERE c.fechaSuscripcion = :fechaSuscripcion"),
    @NamedQuery(name = "CntjOtrosies.findByFechaInicio", query = "SELECT c FROM CntjOtrosies c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "CntjOtrosies.findByFechaTerminacion", query = "SELECT c FROM CntjOtrosies c WHERE c.fechaTerminacion = :fechaTerminacion"),
    @NamedQuery(name = "CntjOtrosies.findByPlazoProrrogaMeses", query = "SELECT c FROM CntjOtrosies c WHERE c.plazoProrrogaMeses = :plazoProrrogaMeses"),
    @NamedQuery(name = "CntjOtrosies.findByPlazoProrrogaDias", query = "SELECT c FROM CntjOtrosies c WHERE c.plazoProrrogaDias = :plazoProrrogaDias"),
    @NamedQuery(name = "CntjOtrosies.findByValor", query = "SELECT c FROM CntjOtrosies c WHERE c.valor = :valor"),
    @NamedQuery(name = "CntjOtrosies.findByEstado", query = "SELECT c FROM CntjOtrosies c WHERE c.estado = :estado"),
    @NamedQuery(name = "CntjOtrosies.findByEstadoLegalizacion", query = "SELECT c FROM CntjOtrosies c WHERE c.estadoLegalizacion = :estadoLegalizacion"),
    @NamedQuery(name = "CntjOtrosies.findByUsuarioCrea", query = "SELECT c FROM CntjOtrosies c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjOtrosies.findByTerminalCrea", query = "SELECT c FROM CntjOtrosies c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjOtrosies.findByFechaHoraCrea", query = "SELECT c FROM CntjOtrosies c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjOtrosies.findByUsuarioModifica", query = "SELECT c FROM CntjOtrosies c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjOtrosies.findByTerminalModifica", query = "SELECT c FROM CntjOtrosies c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjOtrosies.findByFechaHoraModifica", query = "SELECT c FROM CntjOtrosies c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjOtrosies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_suscripcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSuscripcion;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_terminacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTerminacion;
    @Column(name = "plazo_prorroga_meses")
    private Integer plazoProrrogaMeses;
    @Column(name = "plazo_prorroga_dias")
    private Integer plazoProrrogaDias;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "justificacion")
    private String justificacion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "elementos_adicionales")
    private String elementosAdicionales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "estado_legalizacion")
    private Integer estadoLegalizacion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjOtrosiesId", fetch = FetchType.LAZY)
    private List<CntjOtrosiAdjuntos> cntjOtrosiAdjuntosList;
    @JoinColumn(name = "cntj_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjContratos cntjContratosId;

    public CntjOtrosies() {
    }

    public CntjOtrosies(Integer id) {
        this.id = id;
    }

    public CntjOtrosies(Integer id, int numero, int tipo, Date fechaSuscripcion, String justificacion, String elementosAdicionales, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.fechaSuscripcion = fechaSuscripcion;
        this.justificacion = justificacion;
        this.elementosAdicionales = elementosAdicionales;
        this.estado = estado;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(Date fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(Date fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public Integer getPlazoProrrogaMeses() {
        return plazoProrrogaMeses;
    }

    public void setPlazoProrrogaMeses(Integer plazoProrrogaMeses) {
        this.plazoProrrogaMeses = plazoProrrogaMeses;
    }

    public Integer getPlazoProrrogaDias() {
        return plazoProrrogaDias;
    }

    public void setPlazoProrrogaDias(Integer plazoProrrogaDias) {
        this.plazoProrrogaDias = plazoProrrogaDias;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getElementosAdicionales() {
        return elementosAdicionales;
    }

    public void setElementosAdicionales(String elementosAdicionales) {
        this.elementosAdicionales = elementosAdicionales;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getEstadoLegalizacion() {
        return estadoLegalizacion;
    }

    public void setEstadoLegalizacion(Integer estadoLegalizacion) {
        this.estadoLegalizacion = estadoLegalizacion;
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
    public List<CntjOtrosiAdjuntos> getCntjOtrosiAdjuntosList() {
        return cntjOtrosiAdjuntosList;
    }

    public void setCntjOtrosiAdjuntosList(List<CntjOtrosiAdjuntos> cntjOtrosiAdjuntosList) {
        this.cntjOtrosiAdjuntosList = cntjOtrosiAdjuntosList;
    }

    public CntjContratos getCntjContratosId() {
        return cntjContratosId;
    }

    public void setCntjContratosId(CntjContratos cntjContratosId) {
        this.cntjContratosId = cntjContratosId;
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
        if (!(object instanceof CntjOtrosies)) {
            return false;
        }
        CntjOtrosies other = (CntjOtrosies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjOtrosies[ id=" + id + " ]";
    }
    
}
