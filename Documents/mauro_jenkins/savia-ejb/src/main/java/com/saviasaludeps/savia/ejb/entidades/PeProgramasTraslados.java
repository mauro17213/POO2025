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
@Table(name = "pe_programas_traslados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeProgramasTraslados.findAll", query = "SELECT p FROM PeProgramasTraslados p"),
    @NamedQuery(name = "PeProgramasTraslados.findById", query = "SELECT p FROM PeProgramasTraslados p WHERE p.id = :id"),
    @NamedQuery(name = "PeProgramasTraslados.findByObservacion", query = "SELECT p FROM PeProgramasTraslados p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "PeProgramasTraslados.findByUsuarioCrea", query = "SELECT p FROM PeProgramasTraslados p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeProgramasTraslados.findByTerminalCrea", query = "SELECT p FROM PeProgramasTraslados p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeProgramasTraslados.findByFechaHoraCrea", query = "SELECT p FROM PeProgramasTraslados p WHERE p.fechaHoraCrea = :fechaHoraCrea")})
public class PeProgramasTraslados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "pe_afiliados_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeAfiliadosProgramas peAfiliadosProgramasId;
    @JoinColumn(name = "pe_programa_id_destino", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramaIdDestino;
    @JoinColumn(name = "pe_programa_id_origen", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramaIdOrigen;

    public PeProgramasTraslados() {
    }

    public PeProgramasTraslados(Integer id) {
        this.id = id;
    }

    public PeProgramasTraslados(Integer id, String observacion, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.observacion = observacion;
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

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public PeAfiliadosProgramas getPeAfiliadosProgramasId() {
        return peAfiliadosProgramasId;
    }

    public void setPeAfiliadosProgramasId(PeAfiliadosProgramas peAfiliadosProgramasId) {
        this.peAfiliadosProgramasId = peAfiliadosProgramasId;
    }

    public PeProgramas getPeProgramaIdDestino() {
        return peProgramaIdDestino;
    }

    public void setPeProgramaIdDestino(PeProgramas peProgramaIdDestino) {
        this.peProgramaIdDestino = peProgramaIdDestino;
    }

    public PeProgramas getPeProgramaIdOrigen() {
        return peProgramaIdOrigen;
    }

    public void setPeProgramaIdOrigen(PeProgramas peProgramaIdOrigen) {
        this.peProgramaIdOrigen = peProgramaIdOrigen;
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
        if (!(object instanceof PeProgramasTraslados)) {
            return false;
        }
        PeProgramasTraslados other = (PeProgramasTraslados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeProgramasTraslados[ id=" + id + " ]";
    }
    
}
