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
@Table(name = "tu_tutelas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuTutelas.findAll", query = "SELECT t FROM TuTutelas t"),
    @NamedQuery(name = "TuTutelas.findById", query = "SELECT t FROM TuTutelas t WHERE t.id = :id"),
    @NamedQuery(name = "TuTutelas.findByRadicadoNumero", query = "SELECT t FROM TuTutelas t WHERE t.radicadoNumero = :radicadoNumero"),
    @NamedQuery(name = "TuTutelas.findByDiasVencimiento", query = "SELECT t FROM TuTutelas t WHERE t.diasVencimiento = :diasVencimiento"),
    @NamedQuery(name = "TuTutelas.findByCantidadItemsCerrados", query = "SELECT t FROM TuTutelas t WHERE t.cantidadItemsCerrados = :cantidadItemsCerrados"),
    @NamedQuery(name = "TuTutelas.findByCantidadItems", query = "SELECT t FROM TuTutelas t WHERE t.cantidadItems = :cantidadItems"),
    @NamedQuery(name = "TuTutelas.findByUsuarioCrea", query = "SELECT t FROM TuTutelas t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuTutelas.findByTerminalCrea", query = "SELECT t FROM TuTutelas t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuTutelas.findByFechaHoraCrea", query = "SELECT t FROM TuTutelas t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuTutelas.findByUsuarioModifica", query = "SELECT t FROM TuTutelas t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuTutelas.findByTerminalModifica", query = "SELECT t FROM TuTutelas t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuTutelas.findByFechaHoraModifica", query = "SELECT t FROM TuTutelas t WHERE t.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "TuTutelas.findByBorrado", query = "SELECT t FROM TuTutelas t WHERE t.borrado = :borrado"),
    @NamedQuery(name = "TuTutelas.findByUsuarioBorra", query = "SELECT t FROM TuTutelas t WHERE t.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "TuTutelas.findByTerminalBorra", query = "SELECT t FROM TuTutelas t WHERE t.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "TuTutelas.findByFechaHoraBorra", query = "SELECT t FROM TuTutelas t WHERE t.fechaHoraBorra = :fechaHoraBorra")})
public class TuTutelas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "radicado_numero")
    private String radicadoNumero;
    @Column(name = "dias_vencimiento")
    private Integer diasVencimiento;
    @Column(name = "cantidad_items_cerrados")
    private Integer cantidadItemsCerrados;
    @Column(name = "cantidad_items")
    private Integer cantidadItems;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuTutelasId", fetch = FetchType.LAZY)
    private List<TuTutelaItems> tuTutelaItemsList;
    @JoinColumn(name = "gn_ubicacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones gnUbicacionId;
    @JoinColumn(name = "tu_personas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuPersonas tuPersonasId;
    @JoinColumn(name = "actual_tu_tutela_estados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TuTutelaEstados actualTuTutelaEstadosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuTutelasId", fetch = FetchType.LAZY)
    private List<TuTutelaDiagnosticos> tuTutelaDiagnosticosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuTutelasId", fetch = FetchType.LAZY)
    private List<TuTutelaEstados> tuTutelaEstadosList;

    public TuTutelas() {
    }

    public TuTutelas(Integer id) {
        this.id = id;
    }

    public TuTutelas(Integer id, String radicadoNumero, String usuarioCrea, String terminalCrea, Date fechaHoraCrea, boolean borrado) {
        this.id = id;
        this.radicadoNumero = radicadoNumero;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.borrado = borrado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRadicadoNumero() {
        return radicadoNumero;
    }

    public void setRadicadoNumero(String radicadoNumero) {
        this.radicadoNumero = radicadoNumero;
    }

    public Integer getDiasVencimiento() {
        return diasVencimiento;
    }

    public void setDiasVencimiento(Integer diasVencimiento) {
        this.diasVencimiento = diasVencimiento;
    }

    public Integer getCantidadItemsCerrados() {
        return cantidadItemsCerrados;
    }

    public void setCantidadItemsCerrados(Integer cantidadItemsCerrados) {
        this.cantidadItemsCerrados = cantidadItemsCerrados;
    }

    public Integer getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(Integer cantidadItems) {
        this.cantidadItems = cantidadItems;
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

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
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

    @XmlTransient
    public List<TuTutelaItems> getTuTutelaItemsList() {
        return tuTutelaItemsList;
    }

    public void setTuTutelaItemsList(List<TuTutelaItems> tuTutelaItemsList) {
        this.tuTutelaItemsList = tuTutelaItemsList;
    }

    public GnUbicaciones getGnUbicacionId() {
        return gnUbicacionId;
    }

    public void setGnUbicacionId(GnUbicaciones gnUbicacionId) {
        this.gnUbicacionId = gnUbicacionId;
    }

    public TuPersonas getTuPersonasId() {
        return tuPersonasId;
    }

    public void setTuPersonasId(TuPersonas tuPersonasId) {
        this.tuPersonasId = tuPersonasId;
    }

    public TuTutelaEstados getActualTuTutelaEstadosId() {
        return actualTuTutelaEstadosId;
    }

    public void setActualTuTutelaEstadosId(TuTutelaEstados actualTuTutelaEstadosId) {
        this.actualTuTutelaEstadosId = actualTuTutelaEstadosId;
    }

    @XmlTransient
    public List<TuTutelaDiagnosticos> getTuTutelaDiagnosticosList() {
        return tuTutelaDiagnosticosList;
    }

    public void setTuTutelaDiagnosticosList(List<TuTutelaDiagnosticos> tuTutelaDiagnosticosList) {
        this.tuTutelaDiagnosticosList = tuTutelaDiagnosticosList;
    }

    @XmlTransient
    public List<TuTutelaEstados> getTuTutelaEstadosList() {
        return tuTutelaEstadosList;
    }

    public void setTuTutelaEstadosList(List<TuTutelaEstados> tuTutelaEstadosList) {
        this.tuTutelaEstadosList = tuTutelaEstadosList;
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
        if (!(object instanceof TuTutelas)) {
            return false;
        }
        TuTutelas other = (TuTutelas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuTutelas[ id=" + id + " ]";
    }
    
}
