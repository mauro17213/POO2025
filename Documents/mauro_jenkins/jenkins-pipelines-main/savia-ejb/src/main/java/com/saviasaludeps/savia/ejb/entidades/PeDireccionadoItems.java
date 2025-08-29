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
@Table(name = "pe_direccionado_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeDireccionadoItems.findAll", query = "SELECT p FROM PeDireccionadoItems p"),
    @NamedQuery(name = "PeDireccionadoItems.findById", query = "SELECT p FROM PeDireccionadoItems p WHERE p.id = :id"),
    @NamedQuery(name = "PeDireccionadoItems.findByEstado", query = "SELECT p FROM PeDireccionadoItems p WHERE p.estado = :estado"),
    @NamedQuery(name = "PeDireccionadoItems.findByFechaPrestacion", query = "SELECT p FROM PeDireccionadoItems p WHERE p.fechaPrestacion = :fechaPrestacion"),
    @NamedQuery(name = "PeDireccionadoItems.findByObservacion", query = "SELECT p FROM PeDireccionadoItems p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "PeDireccionadoItems.findByUsuarioCrea", query = "SELECT p FROM PeDireccionadoItems p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeDireccionadoItems.findByTerminalCrea", query = "SELECT p FROM PeDireccionadoItems p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeDireccionadoItems.findByFechaHoraCrea", query = "SELECT p FROM PeDireccionadoItems p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeDireccionadoItems.findByUsuarioModifica", query = "SELECT p FROM PeDireccionadoItems p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeDireccionadoItems.findByTerminalModifica", query = "SELECT p FROM PeDireccionadoItems p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeDireccionadoItems.findByFechaHoraModifica", query = "SELECT p FROM PeDireccionadoItems p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeDireccionadoItems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "fecha_prestacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPrestacion;
    @Size(max = 1024)
    @Column(name = "observacion")
    private String observacion;
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
    @JoinColumn(name = "au_anexo3_items_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo3Items auAnexo3ItemsId;
    @JoinColumn(name = "pe_direccionados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeDireccionados peDireccionadosId;

    public PeDireccionadoItems() {
    }

    public PeDireccionadoItems(Integer id) {
        this.id = id;
    }

    public PeDireccionadoItems(Integer id, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaPrestacion() {
        return fechaPrestacion;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public AuAnexo3Items getAuAnexo3ItemsId() {
        return auAnexo3ItemsId;
    }

    public void setAuAnexo3ItemsId(AuAnexo3Items auAnexo3ItemsId) {
        this.auAnexo3ItemsId = auAnexo3ItemsId;
    }

    public PeDireccionados getPeDireccionadosId() {
        return peDireccionadosId;
    }

    public void setPeDireccionadosId(PeDireccionados peDireccionadosId) {
        this.peDireccionadosId = peDireccionadosId;
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
        if (!(object instanceof PeDireccionadoItems)) {
            return false;
        }
        PeDireccionadoItems other = (PeDireccionadoItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeDireccionadoItems[ id=" + id + " ]";
    }
    
}
