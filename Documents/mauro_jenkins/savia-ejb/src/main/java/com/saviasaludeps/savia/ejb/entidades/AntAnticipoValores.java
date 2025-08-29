/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ant_anticipo_valores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntAnticipoValores.findAll", query = "SELECT a FROM AntAnticipoValores a"),
    @NamedQuery(name = "AntAnticipoValores.findById", query = "SELECT a FROM AntAnticipoValores a WHERE a.id = :id"),
    @NamedQuery(name = "AntAnticipoValores.findByObservacion", query = "SELECT a FROM AntAnticipoValores a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AntAnticipoValores.findByDevolucion", query = "SELECT a FROM AntAnticipoValores a WHERE a.devolucion = :devolucion"),
    @NamedQuery(name = "AntAnticipoValores.findByTipoDevolucion", query = "SELECT a FROM AntAnticipoValores a WHERE a.tipoDevolucion = :tipoDevolucion"),
    @NamedQuery(name = "AntAnticipoValores.findByValor", query = "SELECT a FROM AntAnticipoValores a WHERE a.valor = :valor"),
    @NamedQuery(name = "AntAnticipoValores.findByUsuarioCrea", query = "SELECT a FROM AntAnticipoValores a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AntAnticipoValores.findByTerminalCrea", query = "SELECT a FROM AntAnticipoValores a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AntAnticipoValores.findByFechaHoraCrea", query = "SELECT a FROM AntAnticipoValores a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AntAnticipoValores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2048)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "devolucion")
    private boolean devolucion;
    @Column(name = "tipo_devolucion")
    private Integer tipoDevolucion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
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
    @JoinColumn(name = "ant_anticipos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AntAnticipos antAnticiposId;
    @JoinColumn(name = "ant_anticipo_items_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AntAnticipoItems antAnticipoItemsId;
    @JoinColumn(name = "au_cotizaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuCotizaciones auCotizacionesId;

    public AntAnticipoValores() {
    }

    public AntAnticipoValores(Integer id) {
        this.id = id;
    }

    public AntAnticipoValores(Integer id, boolean devolucion, BigDecimal valor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.devolucion = devolucion;
        this.valor = valor;
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

    public boolean getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(boolean devolucion) {
        this.devolucion = devolucion;
    }

    public Integer getTipoDevolucion() {
        return tipoDevolucion;
    }

    public void setTipoDevolucion(Integer tipoDevolucion) {
        this.tipoDevolucion = tipoDevolucion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

    public AntAnticipos getAntAnticiposId() {
        return antAnticiposId;
    }

    public void setAntAnticiposId(AntAnticipos antAnticiposId) {
        this.antAnticiposId = antAnticiposId;
    }

    public AntAnticipoItems getAntAnticipoItemsId() {
        return antAnticipoItemsId;
    }

    public void setAntAnticipoItemsId(AntAnticipoItems antAnticipoItemsId) {
        this.antAnticipoItemsId = antAnticipoItemsId;
    }

    public AuCotizaciones getAuCotizacionesId() {
        return auCotizacionesId;
    }

    public void setAuCotizacionesId(AuCotizaciones auCotizacionesId) {
        this.auCotizacionesId = auCotizacionesId;
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
        if (!(object instanceof AntAnticipoValores)) {
            return false;
        }
        AntAnticipoValores other = (AntAnticipoValores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AntAnticipoValores[ id=" + id + " ]";
    }
    
}
