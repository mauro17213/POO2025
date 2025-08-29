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
@Table(name = "gat_tikete_llamados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatTiketeLlamados.findAll", query = "SELECT g FROM GatTiketeLlamados g"),
    @NamedQuery(name = "GatTiketeLlamados.findById", query = "SELECT g FROM GatTiketeLlamados g WHERE g.id = :id"),
    @NamedQuery(name = "GatTiketeLlamados.findByEstado", query = "SELECT g FROM GatTiketeLlamados g WHERE g.estado = :estado"),
    @NamedQuery(name = "GatTiketeLlamados.findByCantidad", query = "SELECT g FROM GatTiketeLlamados g WHERE g.cantidad = :cantidad"),
    @NamedQuery(name = "GatTiketeLlamados.findByMaximo", query = "SELECT g FROM GatTiketeLlamados g WHERE g.maximo = :maximo"),
    @NamedQuery(name = "GatTiketeLlamados.findByUsuarioCrea", query = "SELECT g FROM GatTiketeLlamados g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatTiketeLlamados.findByTerminalCrea", query = "SELECT g FROM GatTiketeLlamados g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatTiketeLlamados.findByFechaHoraCrea", query = "SELECT g FROM GatTiketeLlamados g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GatTiketeLlamados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "maximo")
    private Integer maximo;
    @Size(max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Size(max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "gat_sede_taquillas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GatSedeTaquillas gatSedeTaquillasId;
    @JoinColumn(name = "gat_tiquetes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GatTiquetes gatTiquetesId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;

    public GatTiketeLlamados() {
    }

    public GatTiketeLlamados(Integer id) {
        this.id = id;
    }

    public GatTiketeLlamados(Integer id, short estado) {
        this.id = id;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getMaximo() {
        return maximo;
    }

    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
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

    public GatSedeTaquillas getGatSedeTaquillasId() {
        return gatSedeTaquillasId;
    }

    public void setGatSedeTaquillasId(GatSedeTaquillas gatSedeTaquillasId) {
        this.gatSedeTaquillasId = gatSedeTaquillasId;
    }

    public GatTiquetes getGatTiquetesId() {
        return gatTiquetesId;
    }

    public void setGatTiquetesId(GatTiquetes gatTiquetesId) {
        this.gatTiquetesId = gatTiquetesId;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
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
        if (!(object instanceof GatTiketeLlamados)) {
            return false;
        }
        GatTiketeLlamados other = (GatTiketeLlamados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatTiketeLlamados[ id=" + id + " ]";
    }
    
}
