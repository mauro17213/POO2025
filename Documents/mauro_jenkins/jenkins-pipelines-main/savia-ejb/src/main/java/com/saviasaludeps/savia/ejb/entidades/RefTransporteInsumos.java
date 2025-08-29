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
@Table(name = "ref_transporte_insumos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RefTransporteInsumos.findAll", query = "SELECT r FROM RefTransporteInsumos r"),
    @NamedQuery(name = "RefTransporteInsumos.findById", query = "SELECT r FROM RefTransporteInsumos r WHERE r.id = :id"),
    @NamedQuery(name = "RefTransporteInsumos.findByMaInsumoId", query = "SELECT r FROM RefTransporteInsumos r WHERE r.maInsumoId = :maInsumoId"),
    @NamedQuery(name = "RefTransporteInsumos.findByMaInsumoCodigo", query = "SELECT r FROM RefTransporteInsumos r WHERE r.maInsumoCodigo = :maInsumoCodigo"),
    @NamedQuery(name = "RefTransporteInsumos.findByMaInsumoValor", query = "SELECT r FROM RefTransporteInsumos r WHERE r.maInsumoValor = :maInsumoValor"),
    @NamedQuery(name = "RefTransporteInsumos.findByUsuarioCrea", query = "SELECT r FROM RefTransporteInsumos r WHERE r.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "RefTransporteInsumos.findByTerminalCrea", query = "SELECT r FROM RefTransporteInsumos r WHERE r.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "RefTransporteInsumos.findByFechaHoraCrea", query = "SELECT r FROM RefTransporteInsumos r WHERE r.fechaHoraCrea = :fechaHoraCrea")})
public class RefTransporteInsumos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_insumo_id")
    private int maInsumoId;
    @Size(max = 8)
    @Column(name = "ma_insumo_codigo")
    private String maInsumoCodigo;
    @Size(max = 128)
    @Column(name = "ma_insumo_valor")
    private String maInsumoValor;
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
    @JoinColumn(name = "ref_transportes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RefTransportes refTransportesId;

    public RefTransporteInsumos() {
    }

    public RefTransporteInsumos(Integer id) {
        this.id = id;
    }

    public RefTransporteInsumos(Integer id, int maInsumoId, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maInsumoId = maInsumoId;
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

    public int getMaInsumoId() {
        return maInsumoId;
    }

    public void setMaInsumoId(int maInsumoId) {
        this.maInsumoId = maInsumoId;
    }

    public String getMaInsumoCodigo() {
        return maInsumoCodigo;
    }

    public void setMaInsumoCodigo(String maInsumoCodigo) {
        this.maInsumoCodigo = maInsumoCodigo;
    }

    public String getMaInsumoValor() {
        return maInsumoValor;
    }

    public void setMaInsumoValor(String maInsumoValor) {
        this.maInsumoValor = maInsumoValor;
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

    public RefTransportes getRefTransportesId() {
        return refTransportesId;
    }

    public void setRefTransportesId(RefTransportes refTransportesId) {
        this.refTransportesId = refTransportesId;
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
        if (!(object instanceof RefTransporteInsumos)) {
            return false;
        }
        RefTransporteInsumos other = (RefTransporteInsumos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.RefTransporteInsumos[ id=" + id + " ]";
    }
    
}
