/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "gn_ubicacion_barrios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnUbicacionBarrios.findAll", query = "SELECT g FROM GnUbicacionBarrios g"),
    @NamedQuery(name = "GnUbicacionBarrios.findById", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.id = :id"),
    @NamedQuery(name = "GnUbicacionBarrios.findByGnUbicacionesId", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.gnUbicacionesId = :gnUbicacionesId"),
    @NamedQuery(name = "GnUbicacionBarrios.findByCodigoBarrio", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.codigoBarrio = :codigoBarrio"),
    @NamedQuery(name = "GnUbicacionBarrios.findByNombre", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnUbicacionBarrios.findByCodigoComuna", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.codigoComuna = :codigoComuna"),
    @NamedQuery(name = "GnUbicacionBarrios.findByComuna", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.comuna = :comuna"),
    @NamedQuery(name = "GnUbicacionBarrios.findByUsuarioCrea", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnUbicacionBarrios.findByTerminalCrea", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnUbicacionBarrios.findByFechaHoraCrea", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnUbicacionBarrios.findByUsuarioModifica", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GnUbicacionBarrios.findByTerminalModifica", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GnUbicacionBarrios.findByFechaHoraModifica", query = "SELECT g FROM GnUbicacionBarrios g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GnUbicacionBarrios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gn_ubicaciones_id")
    private int gnUbicacionesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "codigo_barrio")
    private String codigoBarrio;
    @Size(max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "codigo_comuna")
    private String codigoComuna;
    @Size(max = 64)
    @Column(name = "comuna")
    private String comuna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Size(max = 16)
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
    @OneToMany(mappedBy = "gnUbicacionBarriosId", fetch = FetchType.LAZY)
    private List<AsegAfiliados> asegAfiliadosList;

    public GnUbicacionBarrios() {
    }

    public GnUbicacionBarrios(Integer id) {
        this.id = id;
    }

    public GnUbicacionBarrios(Integer id, int gnUbicacionesId, String codigoBarrio, String codigoComuna, String usuarioCrea, Date fechaHoraCrea) {
        this.id = id;
        this.gnUbicacionesId = gnUbicacionesId;
        this.codigoBarrio = codigoBarrio;
        this.codigoComuna = codigoComuna;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGnUbicacionesId() {
        return gnUbicacionesId;
    }

    public void setGnUbicacionesId(int gnUbicacionesId) {
        this.gnUbicacionesId = gnUbicacionesId;
    }

    public String getCodigoBarrio() {
        return codigoBarrio;
    }

    public void setCodigoBarrio(String codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoComuna() {
        return codigoComuna;
    }

    public void setCodigoComuna(String codigoComuna) {
        this.codigoComuna = codigoComuna;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
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
    public List<AsegAfiliados> getAsegAfiliadosList() {
        return asegAfiliadosList;
    }

    public void setAsegAfiliadosList(List<AsegAfiliados> asegAfiliadosList) {
        this.asegAfiliadosList = asegAfiliadosList;
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
        if (!(object instanceof GnUbicacionBarrios)) {
            return false;
        }
        GnUbicacionBarrios other = (GnUbicacionBarrios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnUbicacionBarrios[ id=" + id + " ]";
    }
    
}
