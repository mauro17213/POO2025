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
@Table(name = "au_rechazo_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuRechazoItems.findAll", query = "SELECT a FROM AuRechazoItems a"),
    @NamedQuery(name = "AuRechazoItems.findById", query = "SELECT a FROM AuRechazoItems a WHERE a.id = :id"),
    @NamedQuery(name = "AuRechazoItems.findByUsuarioCrea", query = "SELECT a FROM AuRechazoItems a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuRechazoItems.findByTerminalCrea", query = "SELECT a FROM AuRechazoItems a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuRechazoItems.findByFechaHoraCrea", query = "SELECT a FROM AuRechazoItems a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuRechazoItems implements Serializable {

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
    @JoinColumn(name = "au_anexo3_items_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo3Items auAnexo3ItemsId;
    @JoinColumn(name = "au_rechazos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuRechazos auRechazosId;

    public AuRechazoItems() {
    }

    public AuRechazoItems(Integer id) {
        this.id = id;
    }

    public AuRechazoItems(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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

    public AuAnexo3Items getAuAnexo3ItemsId() {
        return auAnexo3ItemsId;
    }

    public void setAuAnexo3ItemsId(AuAnexo3Items auAnexo3ItemsId) {
        this.auAnexo3ItemsId = auAnexo3ItemsId;
    }

    public AuRechazos getAuRechazosId() {
        return auRechazosId;
    }

    public void setAuRechazosId(AuRechazos auRechazosId) {
        this.auRechazosId = auRechazosId;
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
        if (!(object instanceof AuRechazoItems)) {
            return false;
        }
        AuRechazoItems other = (AuRechazoItems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuRechazoItems[ id=" + id + " ]";
    }
    
}
