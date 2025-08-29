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
@Table(name = "au_anexo4_destinos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo4Destinos.findAll", query = "SELECT a FROM AuAnexo4Destinos a"),
    @NamedQuery(name = "AuAnexo4Destinos.findById", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo4Destinos.findByUbicacionId", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.ubicacionId = :ubicacionId"),
    @NamedQuery(name = "AuAnexo4Destinos.findByUbicacionValor", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.ubicacionValor = :ubicacionValor"),
    @NamedQuery(name = "AuAnexo4Destinos.findByOrden", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.orden = :orden"),
    @NamedQuery(name = "AuAnexo4Destinos.findByActivo", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.activo = :activo"),
    @NamedQuery(name = "AuAnexo4Destinos.findByUsuarioCrea", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo4Destinos.findByTerminalCrea", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo4Destinos.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo4Destinos.findByUsuarioModifica", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo4Destinos.findByTerminalModifica", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexo4Destinos.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo4Destinos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexo4Destinos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ubicacion_id")
    private int ubicacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ubicacion_valor")
    private String ubicacionValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
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
    @JoinColumn(name = "au_anexo3_zonas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo4Zonas auAnexo3ZonasId;

    public AuAnexo4Destinos() {
    }

    public AuAnexo4Destinos(Integer id) {
        this.id = id;
    }

    public AuAnexo4Destinos(Integer id, int ubicacionId, String ubicacionValor, int orden, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.ubicacionId = ubicacionId;
        this.ubicacionValor = ubicacionValor;
        this.orden = orden;
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

    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public String getUbicacionValor() {
        return ubicacionValor;
    }

    public void setUbicacionValor(String ubicacionValor) {
        this.ubicacionValor = ubicacionValor;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
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

    public AuAnexo4Zonas getAuAnexo3ZonasId() {
        return auAnexo3ZonasId;
    }

    public void setAuAnexo3ZonasId(AuAnexo4Zonas auAnexo3ZonasId) {
        this.auAnexo3ZonasId = auAnexo3ZonasId;
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
        if (!(object instanceof AuAnexo4Destinos)) {
            return false;
        }
        AuAnexo4Destinos other = (AuAnexo4Destinos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo4Destinos[ id=" + id + " ]";
    }
    
}
