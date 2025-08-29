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
@Table(name = "gat_taquilla_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GatTaquillaServicios.findAll", query = "SELECT g FROM GatTaquillaServicios g"),
    @NamedQuery(name = "GatTaquillaServicios.findById", query = "SELECT g FROM GatTaquillaServicios g WHERE g.id = :id"),
    @NamedQuery(name = "GatTaquillaServicios.findByMaeTipoServicioId", query = "SELECT g FROM GatTaquillaServicios g WHERE g.maeTipoServicioId = :maeTipoServicioId"),
    @NamedQuery(name = "GatTaquillaServicios.findByMaeTipoServicioCodigo", query = "SELECT g FROM GatTaquillaServicios g WHERE g.maeTipoServicioCodigo = :maeTipoServicioCodigo"),
    @NamedQuery(name = "GatTaquillaServicios.findByMaeTipoServicioValor", query = "SELECT g FROM GatTaquillaServicios g WHERE g.maeTipoServicioValor = :maeTipoServicioValor"),
    @NamedQuery(name = "GatTaquillaServicios.findByUsuarioCrea", query = "SELECT g FROM GatTaquillaServicios g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GatTaquillaServicios.findByTerminalCrea", query = "SELECT g FROM GatTaquillaServicios g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GatTaquillaServicios.findByFechaHoraCrea", query = "SELECT g FROM GatTaquillaServicios g WHERE g.fechaHoraCrea = :fechaHoraCrea")})
public class GatTaquillaServicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_servicio_id")
    private int maeTipoServicioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_servicio_codigo")
    private String maeTipoServicioCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_servicio_valor")
    private String maeTipoServicioValor;
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
    @JoinColumn(name = "gat_sede_taquillas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GatSedeTaquillas gatSedeTaquillasId;

    public GatTaquillaServicios() {
    }

    public GatTaquillaServicios(Integer id) {
        this.id = id;
    }

    public GatTaquillaServicios(Integer id, int maeTipoServicioId, String maeTipoServicioCodigo, String maeTipoServicioValor, String usuarioCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoServicioId = maeTipoServicioId;
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
        this.maeTipoServicioValor = maeTipoServicioValor;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(int maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GatTaquillaServicios)) {
            return false;
        }
        GatTaquillaServicios other = (GatTaquillaServicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GatTaquillaServicios[ id=" + id + " ]";
    }
    
}
