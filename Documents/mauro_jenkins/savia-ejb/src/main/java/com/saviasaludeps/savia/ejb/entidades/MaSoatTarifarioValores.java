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
@Table(name = "ma_soat_tarifario_valores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaSoatTarifarioValores.findAll", query = "SELECT m FROM MaSoatTarifarioValores m"),
    @NamedQuery(name = "MaSoatTarifarioValores.findById", query = "SELECT m FROM MaSoatTarifarioValores m WHERE m.id = :id"),
    @NamedQuery(name = "MaSoatTarifarioValores.findByAgno", query = "SELECT m FROM MaSoatTarifarioValores m WHERE m.agno = :agno"),
    @NamedQuery(name = "MaSoatTarifarioValores.findByValor", query = "SELECT m FROM MaSoatTarifarioValores m WHERE m.valor = :valor"),
    @NamedQuery(name = "MaSoatTarifarioValores.findByUsuarioCrea", query = "SELECT m FROM MaSoatTarifarioValores m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaSoatTarifarioValores.findByTerminalCrea", query = "SELECT m FROM MaSoatTarifarioValores m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaSoatTarifarioValores.findByFechaHoraCrea", query = "SELECT m FROM MaSoatTarifarioValores m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MaSoatTarifarioValores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agno")
    private int agno;
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
    @JoinColumn(name = "ma_soat_tarifarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaSoatTarifarios maSoatTarifariosId;

    public MaSoatTarifarioValores() {
    }

    public MaSoatTarifarioValores(Integer id) {
        this.id = id;
    }

    public MaSoatTarifarioValores(Integer id, int agno, BigDecimal valor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.agno = agno;
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

    public int getAgno() {
        return agno;
    }

    public void setAgno(int agno) {
        this.agno = agno;
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

    public MaSoatTarifarios getMaSoatTarifariosId() {
        return maSoatTarifariosId;
    }

    public void setMaSoatTarifariosId(MaSoatTarifarios maSoatTarifariosId) {
        this.maSoatTarifariosId = maSoatTarifariosId;
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
        if (!(object instanceof MaSoatTarifarioValores)) {
            return false;
        }
        MaSoatTarifarioValores other = (MaSoatTarifarioValores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaSoatTarifarioValores[ id=" + id + " ]";
    }
    
}
