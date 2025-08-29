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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "gn_divisiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnDivisiones.findAll", query = "SELECT g FROM GnDivisiones g"),
    @NamedQuery(name = "GnDivisiones.findById", query = "SELECT g FROM GnDivisiones g WHERE g.id = :id"),
    @NamedQuery(name = "GnDivisiones.findByCodigo", query = "SELECT g FROM GnDivisiones g WHERE g.codigo = :codigo"),
    @NamedQuery(name = "GnDivisiones.findByMaeZonaId", query = "SELECT g FROM GnDivisiones g WHERE g.maeZonaId = :maeZonaId"),
    @NamedQuery(name = "GnDivisiones.findByMaeZonaCodigo", query = "SELECT g FROM GnDivisiones g WHERE g.maeZonaCodigo = :maeZonaCodigo"),
    @NamedQuery(name = "GnDivisiones.findByMaeZonaValor", query = "SELECT g FROM GnDivisiones g WHERE g.maeZonaValor = :maeZonaValor"),
    @NamedQuery(name = "GnDivisiones.findByNombre", query = "SELECT g FROM GnDivisiones g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnDivisiones.findByUsuarioCrea", query = "SELECT g FROM GnDivisiones g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnDivisiones.findByTerminalCrea", query = "SELECT g FROM GnDivisiones g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnDivisiones.findByFechaHoraCrea", query = "SELECT g FROM GnDivisiones g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnDivisiones.findByUsuarioModifica", query = "SELECT g FROM GnDivisiones g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GnDivisiones.findByTerminalModifica", query = "SELECT g FROM GnDivisiones g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GnDivisiones.findByFechaHoraModifica", query = "SELECT g FROM GnDivisiones g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GnDivisiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_zona_id")
    private int maeZonaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_zona_codigo")
    private String maeZonaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_zona_valor")
    private String maeZonaValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre")
    private String nombre;
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
    @JoinColumn(name = "gn_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones gnUbicacionesId;
    @OneToMany(mappedBy = "gnDivisionesId", fetch = FetchType.LAZY)
    private List<AsegAfiliados> asegAfiliadosList;

    public GnDivisiones() {
    }

    public GnDivisiones(Integer id) {
        this.id = id;
    }

    public GnDivisiones(Integer id, String codigo, int maeZonaId, String maeZonaCodigo, String maeZonaValor, String nombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigo = codigo;
        this.maeZonaId = maeZonaId;
        this.maeZonaCodigo = maeZonaCodigo;
        this.maeZonaValor = maeZonaValor;
        this.nombre = nombre;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getMaeZonaId() {
        return maeZonaId;
    }

    public void setMaeZonaId(int maeZonaId) {
        this.maeZonaId = maeZonaId;
    }

    public String getMaeZonaCodigo() {
        return maeZonaCodigo;
    }

    public void setMaeZonaCodigo(String maeZonaCodigo) {
        this.maeZonaCodigo = maeZonaCodigo;
    }

    public String getMaeZonaValor() {
        return maeZonaValor;
    }

    public void setMaeZonaValor(String maeZonaValor) {
        this.maeZonaValor = maeZonaValor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public GnUbicaciones getGnUbicacionesId() {
        return gnUbicacionesId;
    }

    public void setGnUbicacionesId(GnUbicaciones gnUbicacionesId) {
        this.gnUbicacionesId = gnUbicacionesId;
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
        if (!(object instanceof GnDivisiones)) {
            return false;
        }
        GnDivisiones other = (GnDivisiones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnDivisiones[ id=" + id + " ]";
    }
    
}
