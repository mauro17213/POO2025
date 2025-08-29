/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
@Table(name = "mpc_programacion_entregas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpcProgramacionEntregas.findAll", query = "SELECT m FROM MpcProgramacionEntregas m"),
    @NamedQuery(name = "MpcProgramacionEntregas.findById", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.id = :id"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByEstado", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByNumeroEntregaTotal", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.numeroEntregaTotal = :numeroEntregaTotal"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByNumeroEntrega", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.numeroEntrega = :numeroEntrega"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByCantidad", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByEntregaTotal", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.entregaTotal = :entregaTotal"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByCausaNoEntrega", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.causaNoEntrega = :causaNoEntrega"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByFechaEntrega", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByUsuarioCrea", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByTerminalCrea", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByFechaHoraCrea", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByUsuarioModifica", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByTerminalModifica", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpcProgramacionEntregas.findByFechaHoraModifica", query = "SELECT m FROM MpcProgramacionEntregas m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MpcProgramacionEntregas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_entrega_total")
    private short numeroEntregaTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_entrega")
    private short numeroEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entrega_total")
    private boolean entregaTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "causa_no_entrega")
    private short causaNoEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
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
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "mpc_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpcPrescripciones mpcPrescripcionesId;

    public MpcProgramacionEntregas() {
    }

    public MpcProgramacionEntregas(Integer id) {
        this.id = id;
    }

    public MpcProgramacionEntregas(Integer id, short estado, short numeroEntregaTotal, short numeroEntrega, int cantidad, boolean entregaTotal, short causaNoEntrega, Date fechaEntrega, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.numeroEntregaTotal = numeroEntregaTotal;
        this.numeroEntrega = numeroEntrega;
        this.cantidad = cantidad;
        this.entregaTotal = entregaTotal;
        this.causaNoEntrega = causaNoEntrega;
        this.fechaEntrega = fechaEntrega;
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

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public short getNumeroEntregaTotal() {
        return numeroEntregaTotal;
    }

    public void setNumeroEntregaTotal(short numeroEntregaTotal) {
        this.numeroEntregaTotal = numeroEntregaTotal;
    }

    public short getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(short numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean getEntregaTotal() {
        return entregaTotal;
    }

    public void setEntregaTotal(boolean entregaTotal) {
        this.entregaTotal = entregaTotal;
    }

    public short getCausaNoEntrega() {
        return causaNoEntrega;
    }

    public void setCausaNoEntrega(short causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public MpcPrescripciones getMpcPrescripcionesId() {
        return mpcPrescripcionesId;
    }

    public void setMpcPrescripcionesId(MpcPrescripciones mpcPrescripcionesId) {
        this.mpcPrescripcionesId = mpcPrescripcionesId;
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
        if (!(object instanceof MpcProgramacionEntregas)) {
            return false;
        }
        MpcProgramacionEntregas other = (MpcProgramacionEntregas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpcProgramacionEntregas[ id=" + id + " ]";
    }
    
}
