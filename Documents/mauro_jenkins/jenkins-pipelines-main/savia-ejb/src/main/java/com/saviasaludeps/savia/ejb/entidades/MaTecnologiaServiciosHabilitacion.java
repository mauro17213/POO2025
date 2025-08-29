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
@Table(name = "ma_tecnologia_servicios_habilitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaTecnologiaServiciosHabilitacion.findAll", query = "SELECT m FROM MaTecnologiaServiciosHabilitacion m"),
    @NamedQuery(name = "MaTecnologiaServiciosHabilitacion.findById", query = "SELECT m FROM MaTecnologiaServiciosHabilitacion m WHERE m.id = :id"),
    @NamedQuery(name = "MaTecnologiaServiciosHabilitacion.findByActivo", query = "SELECT m FROM MaTecnologiaServiciosHabilitacion m WHERE m.activo = :activo"),
    @NamedQuery(name = "MaTecnologiaServiciosHabilitacion.findByUsuarioCrea", query = "SELECT m FROM MaTecnologiaServiciosHabilitacion m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaTecnologiaServiciosHabilitacion.findByTerminalCrea", query = "SELECT m FROM MaTecnologiaServiciosHabilitacion m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaTecnologiaServiciosHabilitacion.findByFechaHoraCrea", query = "SELECT m FROM MaTecnologiaServiciosHabilitacion m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaTecnologiaServiciosHabilitacion.findByUsuarioModifica", query = "SELECT m FROM MaTecnologiaServiciosHabilitacion m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaTecnologiaServiciosHabilitacion.findByTerminalModifica", query = "SELECT m FROM MaTecnologiaServiciosHabilitacion m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaTecnologiaServiciosHabilitacion.findByFechaHoraModifica", query = "SELECT m FROM MaTecnologiaServiciosHabilitacion m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MaTecnologiaServiciosHabilitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
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
    @JoinColumn(name = "ma_servicios_habilitacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaServiciosHabilitacion maServiciosHabilitacionId;
    @JoinColumn(name = "ma_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaTecnologias maTecnologiasId;

    public MaTecnologiaServiciosHabilitacion() {
    }

    public MaTecnologiaServiciosHabilitacion(Integer id) {
        this.id = id;
    }

    public MaTecnologiaServiciosHabilitacion(Integer id, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.activo = activo;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public MaServiciosHabilitacion getMaServiciosHabilitacionId() {
        return maServiciosHabilitacionId;
    }

    public void setMaServiciosHabilitacionId(MaServiciosHabilitacion maServiciosHabilitacionId) {
        this.maServiciosHabilitacionId = maServiciosHabilitacionId;
    }

    public MaTecnologias getMaTecnologiasId() {
        return maTecnologiasId;
    }

    public void setMaTecnologiasId(MaTecnologias maTecnologiasId) {
        this.maTecnologiasId = maTecnologiasId;
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
        if (!(object instanceof MaTecnologiaServiciosHabilitacion)) {
            return false;
        }
        MaTecnologiaServiciosHabilitacion other = (MaTecnologiaServiciosHabilitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaTecnologiaServiciosHabilitacion[ id=" + id + " ]";
    }
    
}
