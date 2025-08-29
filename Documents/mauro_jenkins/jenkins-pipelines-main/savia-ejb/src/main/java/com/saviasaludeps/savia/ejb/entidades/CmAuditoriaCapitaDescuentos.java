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
import javax.persistence.Lob;
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
@Table(name = "cm_auditoria_capita_descuentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmAuditoriaCapitaDescuentos.findAll", query = "SELECT c FROM CmAuditoriaCapitaDescuentos c"),
    @NamedQuery(name = "CmAuditoriaCapitaDescuentos.findById", query = "SELECT c FROM CmAuditoriaCapitaDescuentos c WHERE c.id = :id"),
    @NamedQuery(name = "CmAuditoriaCapitaDescuentos.findByMarcacion", query = "SELECT c FROM CmAuditoriaCapitaDescuentos c WHERE c.marcacion = :marcacion"),
    @NamedQuery(name = "CmAuditoriaCapitaDescuentos.findByContrato", query = "SELECT c FROM CmAuditoriaCapitaDescuentos c WHERE c.contrato = :contrato"),
    @NamedQuery(name = "CmAuditoriaCapitaDescuentos.findByUsuarioCrea", query = "SELECT c FROM CmAuditoriaCapitaDescuentos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmAuditoriaCapitaDescuentos.findByTerminalCrea", query = "SELECT c FROM CmAuditoriaCapitaDescuentos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmAuditoriaCapitaDescuentos.findByFechaHoraCrea", query = "SELECT c FROM CmAuditoriaCapitaDescuentos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmAuditoriaCapitaDescuentos.findByUsuarioModifica", query = "SELECT c FROM CmAuditoriaCapitaDescuentos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmAuditoriaCapitaDescuentos.findByTerminalModifica", query = "SELECT c FROM CmAuditoriaCapitaDescuentos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmAuditoriaCapitaDescuentos.findByFechaHoraModifica", query = "SELECT c FROM CmAuditoriaCapitaDescuentos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmAuditoriaCapitaDescuentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "marcacion")
    private boolean marcacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "contrato")
    private String contrato;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
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
    @JoinColumn(name = "cm_detalles_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmDetalles cmDetallesId;
    @JoinColumn(name = "cnt_contratos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntContratos cntContratosId;

    public CmAuditoriaCapitaDescuentos() {
    }

    public CmAuditoriaCapitaDescuentos(Integer id) {
        this.id = id;
    }

    public CmAuditoriaCapitaDescuentos(Integer id, boolean marcacion, String contrato, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.marcacion = marcacion;
        this.contrato = contrato;
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

    public boolean getMarcacion() {
        return marcacion;
    }

    public void setMarcacion(boolean marcacion) {
        this.marcacion = marcacion;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
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

    public CmDetalles getCmDetallesId() {
        return cmDetallesId;
    }

    public void setCmDetallesId(CmDetalles cmDetallesId) {
        this.cmDetallesId = cmDetallesId;
    }

    public CntContratos getCntContratosId() {
        return cntContratosId;
    }

    public void setCntContratosId(CntContratos cntContratosId) {
        this.cntContratosId = cntContratosId;
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
        if (!(object instanceof CmAuditoriaCapitaDescuentos)) {
            return false;
        }
        CmAuditoriaCapitaDescuentos other = (CmAuditoriaCapitaDescuentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmAuditoriaCapitaDescuentos[ id=" + id + " ]";
    }
    
}
