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
@Table(name = "au_anexo2_rescate_sedes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo2RescateSedes.findAll", query = "SELECT a FROM AuAnexo2RescateSedes a"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findById", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByAplicaRescateAnexo2", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.aplicaRescateAnexo2 = :aplicaRescateAnexo2"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByAplicaRescateAnexo3", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.aplicaRescateAnexo3 = :aplicaRescateAnexo3"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByAplicaRescateHosp", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.aplicaRescateHosp = :aplicaRescateHosp"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByActivo", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.activo = :activo"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByObservacion", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByInactivoObservacion", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.inactivoObservacion = :inactivoObservacion"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByAplicaRescateAnexo3Hosp", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.aplicaRescateAnexo3Hosp = :aplicaRescateAnexo3Hosp"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByUsuarioCrea", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByTerminalCrea", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByUsuarioModifica", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByTerminalModifica", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexo2RescateSedes.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo2RescateSedes a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexo2RescateSedes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_rescate_anexo2")
    private boolean aplicaRescateAnexo2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_rescate_anexo3")
    private boolean aplicaRescateAnexo3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_rescate_hosp")
    private boolean aplicaRescateHosp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 1024)
    @Column(name = "inactivo_observacion")
    private String inactivoObservacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_rescate_anexo3_hosp")
    private boolean aplicaRescateAnexo3Hosp;
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
    @JoinColumn(name = "cnt_prestador_sede_capita_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedeCapitaId;
    @JoinColumn(name = "cnt_prestador_sede_origen_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedeOrigenId;

    public AuAnexo2RescateSedes() {
    }

    public AuAnexo2RescateSedes(Integer id) {
        this.id = id;
    }

    public AuAnexo2RescateSedes(Integer id, boolean aplicaRescateAnexo2, boolean aplicaRescateAnexo3, boolean aplicaRescateHosp, boolean activo, String observacion, boolean aplicaRescateAnexo3Hosp, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.aplicaRescateAnexo2 = aplicaRescateAnexo2;
        this.aplicaRescateAnexo3 = aplicaRescateAnexo3;
        this.aplicaRescateHosp = aplicaRescateHosp;
        this.activo = activo;
        this.observacion = observacion;
        this.aplicaRescateAnexo3Hosp = aplicaRescateAnexo3Hosp;
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

    public boolean getAplicaRescateAnexo2() {
        return aplicaRescateAnexo2;
    }

    public void setAplicaRescateAnexo2(boolean aplicaRescateAnexo2) {
        this.aplicaRescateAnexo2 = aplicaRescateAnexo2;
    }

    public boolean getAplicaRescateAnexo3() {
        return aplicaRescateAnexo3;
    }

    public void setAplicaRescateAnexo3(boolean aplicaRescateAnexo3) {
        this.aplicaRescateAnexo3 = aplicaRescateAnexo3;
    }

    public boolean getAplicaRescateHosp() {
        return aplicaRescateHosp;
    }

    public void setAplicaRescateHosp(boolean aplicaRescateHosp) {
        this.aplicaRescateHosp = aplicaRescateHosp;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getInactivoObservacion() {
        return inactivoObservacion;
    }

    public void setInactivoObservacion(String inactivoObservacion) {
        this.inactivoObservacion = inactivoObservacion;
    }

    public boolean getAplicaRescateAnexo3Hosp() {
        return aplicaRescateAnexo3Hosp;
    }

    public void setAplicaRescateAnexo3Hosp(boolean aplicaRescateAnexo3Hosp) {
        this.aplicaRescateAnexo3Hosp = aplicaRescateAnexo3Hosp;
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

    public CntPrestadorSedes getCntPrestadorSedeCapitaId() {
        return cntPrestadorSedeCapitaId;
    }

    public void setCntPrestadorSedeCapitaId(CntPrestadorSedes cntPrestadorSedeCapitaId) {
        this.cntPrestadorSedeCapitaId = cntPrestadorSedeCapitaId;
    }

    public CntPrestadorSedes getCntPrestadorSedeOrigenId() {
        return cntPrestadorSedeOrigenId;
    }

    public void setCntPrestadorSedeOrigenId(CntPrestadorSedes cntPrestadorSedeOrigenId) {
        this.cntPrestadorSedeOrigenId = cntPrestadorSedeOrigenId;
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
        if (!(object instanceof AuAnexo2RescateSedes)) {
            return false;
        }
        AuAnexo2RescateSedes other = (AuAnexo2RescateSedes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo2RescateSedes[ id=" + id + " ]";
    }
    
}
