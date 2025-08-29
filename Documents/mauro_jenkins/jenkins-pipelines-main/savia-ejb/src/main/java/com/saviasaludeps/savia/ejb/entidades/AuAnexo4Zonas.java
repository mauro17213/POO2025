/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "au_anexo4_zonas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo4Zonas.findAll", query = "SELECT a FROM AuAnexo4Zonas a"),
    @NamedQuery(name = "AuAnexo4Zonas.findById", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo4Zonas.findByUbicacionId", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.ubicacionId = :ubicacionId"),
    @NamedQuery(name = "AuAnexo4Zonas.findByUbicacionValor", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.ubicacionValor = :ubicacionValor"),
    @NamedQuery(name = "AuAnexo4Zonas.findByNombre", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AuAnexo4Zonas.findByActivo", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.activo = :activo"),
    @NamedQuery(name = "AuAnexo4Zonas.findByUsuarioCrea", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo4Zonas.findByTerminalCrea", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo4Zonas.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo4Zonas.findByUsuarioModifica", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo4Zonas.findByTerminalModifica", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexo4Zonas.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo4Zonas a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexo4Zonas implements Serializable {

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
    @Size(min = 1, max = 64)
    @Column(name = "ubicacion_valor")
    private String ubicacionValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3ZonasId", fetch = FetchType.LAZY)
    private List<AuAnexo4Destinos> auAnexo4DestinosList;

    public AuAnexo4Zonas() {
    }

    public AuAnexo4Zonas(Integer id) {
        this.id = id;
    }

    public AuAnexo4Zonas(Integer id, int ubicacionId, String ubicacionValor, String nombre, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.ubicacionId = ubicacionId;
        this.ubicacionValor = ubicacionValor;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @XmlTransient
    public List<AuAnexo4Destinos> getAuAnexo4DestinosList() {
        return auAnexo4DestinosList;
    }

    public void setAuAnexo4DestinosList(List<AuAnexo4Destinos> auAnexo4DestinosList) {
        this.auAnexo4DestinosList = auAnexo4DestinosList;
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
        if (!(object instanceof AuAnexo4Zonas)) {
            return false;
        }
        AuAnexo4Zonas other = (AuAnexo4Zonas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo4Zonas[ id=" + id + " ]";
    }
    
}
