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
@Table(name = "cntj_contrato_informes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjContratoInformes.findAll", query = "SELECT c FROM CntjContratoInformes c"),
    @NamedQuery(name = "CntjContratoInformes.findById", query = "SELECT c FROM CntjContratoInformes c WHERE c.id = :id"),
    @NamedQuery(name = "CntjContratoInformes.findByFechaInicioPeriodo", query = "SELECT c FROM CntjContratoInformes c WHERE c.fechaInicioPeriodo = :fechaInicioPeriodo"),
    @NamedQuery(name = "CntjContratoInformes.findByFechaFinPeriodo", query = "SELECT c FROM CntjContratoInformes c WHERE c.fechaFinPeriodo = :fechaFinPeriodo"),
    @NamedQuery(name = "CntjContratoInformes.findByTipoInforme", query = "SELECT c FROM CntjContratoInformes c WHERE c.tipoInforme = :tipoInforme"),
    @NamedQuery(name = "CntjContratoInformes.findByFechaAprobacion", query = "SELECT c FROM CntjContratoInformes c WHERE c.fechaAprobacion = :fechaAprobacion"),
    @NamedQuery(name = "CntjContratoInformes.findByObservaciones", query = "SELECT c FROM CntjContratoInformes c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "CntjContratoInformes.findByEstado", query = "SELECT c FROM CntjContratoInformes c WHERE c.estado = :estado"),
    @NamedQuery(name = "CntjContratoInformes.findByUsuarioCrea", query = "SELECT c FROM CntjContratoInformes c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjContratoInformes.findByTerminalCrea", query = "SELECT c FROM CntjContratoInformes c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjContratoInformes.findByFechaHoraCrea", query = "SELECT c FROM CntjContratoInformes c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjContratoInformes.findByUsuarioModifica", query = "SELECT c FROM CntjContratoInformes c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjContratoInformes.findByTerminalModifica", query = "SELECT c FROM CntjContratoInformes c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjContratoInformes.findByFechaHoraModifica", query = "SELECT c FROM CntjContratoInformes c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjContratoInformes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_periodo")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin_periodo")
    @Temporal(TemporalType.DATE)
    private Date fechaFinPeriodo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_informe")
    private int tipoInforme;
    @Column(name = "fecha_aprobacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;
    @Size(max = 1024)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjContratoInformesId", fetch = FetchType.LAZY)
    private List<CntjContratoInformeAdjuntos> cntjContratoInformeAdjuntosList;
    @JoinColumn(name = "cntj_contrato_supervisores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjContratoSupervisores cntjContratoSupervisoresId;
    @JoinColumn(name = "cntj_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjContratos cntjContratosId;

    public CntjContratoInformes() {
    }

    public CntjContratoInformes(Integer id) {
        this.id = id;
    }

    public CntjContratoInformes(Integer id, Date fechaInicioPeriodo, Date fechaFinPeriodo, int tipoInforme, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaInicioPeriodo = fechaInicioPeriodo;
        this.fechaFinPeriodo = fechaFinPeriodo;
        this.tipoInforme = tipoInforme;
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

    public Date getFechaInicioPeriodo() {
        return fechaInicioPeriodo;
    }

    public void setFechaInicioPeriodo(Date fechaInicioPeriodo) {
        this.fechaInicioPeriodo = fechaInicioPeriodo;
    }

    public Date getFechaFinPeriodo() {
        return fechaFinPeriodo;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo;
    }

    public int getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(int tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
    public List<CntjContratoInformeAdjuntos> getCntjContratoInformeAdjuntosList() {
        return cntjContratoInformeAdjuntosList;
    }

    public void setCntjContratoInformeAdjuntosList(List<CntjContratoInformeAdjuntos> cntjContratoInformeAdjuntosList) {
        this.cntjContratoInformeAdjuntosList = cntjContratoInformeAdjuntosList;
    }

    public CntjContratoSupervisores getCntjContratoSupervisoresId() {
        return cntjContratoSupervisoresId;
    }

    public void setCntjContratoSupervisoresId(CntjContratoSupervisores cntjContratoSupervisoresId) {
        this.cntjContratoSupervisoresId = cntjContratoSupervisoresId;
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
        if (!(object instanceof CntjContratoInformes)) {
            return false;
        }
        CntjContratoInformes other = (CntjContratoInformes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjContratoInformes[ id=" + id + " ]";
    }
    
}
