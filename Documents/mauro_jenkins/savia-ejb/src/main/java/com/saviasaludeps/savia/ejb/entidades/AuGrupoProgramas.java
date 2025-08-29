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
@Table(name = "au_grupo_programas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuGrupoProgramas.findAll", query = "SELECT a FROM AuGrupoProgramas a"),
    @NamedQuery(name = "AuGrupoProgramas.findById", query = "SELECT a FROM AuGrupoProgramas a WHERE a.id = :id"),
    @NamedQuery(name = "AuGrupoProgramas.findByUsuarioCrea", query = "SELECT a FROM AuGrupoProgramas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuGrupoProgramas.findByTerminalCrea", query = "SELECT a FROM AuGrupoProgramas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuGrupoProgramas.findByFechaHoraCrea", query = "SELECT a FROM AuGrupoProgramas a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuGrupoProgramas.findByUsuarioModifica", query = "SELECT a FROM AuGrupoProgramas a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuGrupoProgramas.findByTerminalModifica", query = "SELECT a FROM AuGrupoProgramas a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuGrupoProgramas.findByFechaHoraModifica", query = "SELECT a FROM AuGrupoProgramas a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuGrupoProgramas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @JoinColumn(name = "au_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuGrupos auGruposId;
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;

    public AuGrupoProgramas() {
    }

    public AuGrupoProgramas(Integer id) {
        this.id = id;
    }

    public AuGrupoProgramas(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public AuGrupos getAuGruposId() {
        return auGruposId;
    }

    public void setAuGruposId(AuGrupos auGruposId) {
        this.auGruposId = auGruposId;
    }

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
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
        if (!(object instanceof AuGrupoProgramas)) {
            return false;
        }
        AuGrupoProgramas other = (AuGrupoProgramas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuGrupoProgramas[ id=" + id + " ]";
    }
    
}
