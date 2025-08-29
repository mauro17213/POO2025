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
@Table(name = "cm_fe_transacciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmFeTransacciones.findAll", query = "SELECT c FROM CmFeTransacciones c"),
    @NamedQuery(name = "CmFeTransacciones.findById", query = "SELECT c FROM CmFeTransacciones c WHERE c.id = :id"),
    @NamedQuery(name = "CmFeTransacciones.findByFechaHoraEnvio", query = "SELECT c FROM CmFeTransacciones c WHERE c.fechaHoraEnvio = :fechaHoraEnvio"),
    @NamedQuery(name = "CmFeTransacciones.findByFechaHoraRespuesta", query = "SELECT c FROM CmFeTransacciones c WHERE c.fechaHoraRespuesta = :fechaHoraRespuesta"),
    @NamedQuery(name = "CmFeTransacciones.findByEstado", query = "SELECT c FROM CmFeTransacciones c WHERE c.estado = :estado"),
    @NamedQuery(name = "CmFeTransacciones.findByUsuarioCrea", query = "SELECT c FROM CmFeTransacciones c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmFeTransacciones.findByTerminalCrea", query = "SELECT c FROM CmFeTransacciones c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmFeTransacciones.findByFechaHoraCrea", query = "SELECT c FROM CmFeTransacciones c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmFeTransacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "json_envio")
    private byte[] jsonEnvio;
    @Column(name = "fecha_hora_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraEnvio;
    @Lob
    @Column(name = "json_respuesta")
    private byte[] jsonRespuesta;
    @Column(name = "fecha_hora_respuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRespuesta;
    @Column(name = "estado")
    private Integer estado;
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
    @JoinColumn(name = "cm_fe_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFeRipsCargas cmFeRipsCargasId;

    public CmFeTransacciones() {
    }

    public CmFeTransacciones(Integer id) {
        this.id = id;
    }

    public CmFeTransacciones(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public byte[] getJsonEnvio() {
        return jsonEnvio;
    }

    public void setJsonEnvio(byte[] jsonEnvio) {
        this.jsonEnvio = jsonEnvio;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public byte[] getJsonRespuesta() {
        return jsonRespuesta;
    }

    public void setJsonRespuesta(byte[] jsonRespuesta) {
        this.jsonRespuesta = jsonRespuesta;
    }

    public Date getFechaHoraRespuesta() {
        return fechaHoraRespuesta;
    }

    public void setFechaHoraRespuesta(Date fechaHoraRespuesta) {
        this.fechaHoraRespuesta = fechaHoraRespuesta;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
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

    public CmFeRipsCargas getCmFeRipsCargasId() {
        return cmFeRipsCargasId;
    }

    public void setCmFeRipsCargasId(CmFeRipsCargas cmFeRipsCargasId) {
        this.cmFeRipsCargasId = cmFeRipsCargasId;
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
        if (!(object instanceof CmFeTransacciones)) {
            return false;
        }
        CmFeTransacciones other = (CmFeTransacciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFeTransacciones[ id=" + id + " ]";
    }
    
}
