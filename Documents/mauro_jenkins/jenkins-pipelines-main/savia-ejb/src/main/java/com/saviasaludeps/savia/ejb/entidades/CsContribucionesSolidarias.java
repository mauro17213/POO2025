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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cs_contribuciones_solidarias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CsContribucionesSolidarias.findAll", query = "SELECT c FROM CsContribucionesSolidarias c"),
    @NamedQuery(name = "CsContribucionesSolidarias.findById", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.id = :id"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByMaeContribucionSolidariaId", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.maeContribucionSolidariaId = :maeContribucionSolidariaId"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByMaeContribucionSolidariaCodigo", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.maeContribucionSolidariaCodigo = :maeContribucionSolidariaCodigo"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByMaeContribucionSolidariaValor", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.maeContribucionSolidariaValor = :maeContribucionSolidariaValor"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByAgno", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.agno = :agno"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByValor", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.valor = :valor"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByPorcentaje", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.porcentaje = :porcentaje"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByUsuarioCrea", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByTerminalCrea", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByFechaHoraCrea", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByUsuarioModifica", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByTerminalModifica", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CsContribucionesSolidarias.findByFechaHoraModifica", query = "SELECT c FROM CsContribucionesSolidarias c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CsContribucionesSolidarias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_contribucion_solidaria_id")
    private int maeContribucionSolidariaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_contribucion_solidaria_codigo")
    private String maeContribucionSolidariaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_contribucion_solidaria_valor")
    private String maeContribucionSolidariaValor;
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
    @Column(name = "porcentaje")
    private BigDecimal porcentaje;
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

    public CsContribucionesSolidarias() {
    }

    public CsContribucionesSolidarias(Integer id) {
        this.id = id;
    }

    public CsContribucionesSolidarias(Integer id, int maeContribucionSolidariaId, String maeContribucionSolidariaCodigo, String maeContribucionSolidariaValor, int agno, BigDecimal valor, BigDecimal porcentaje, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeContribucionSolidariaId = maeContribucionSolidariaId;
        this.maeContribucionSolidariaCodigo = maeContribucionSolidariaCodigo;
        this.maeContribucionSolidariaValor = maeContribucionSolidariaValor;
        this.agno = agno;
        this.valor = valor;
        this.porcentaje = porcentaje;
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

    public int getMaeContribucionSolidariaId() {
        return maeContribucionSolidariaId;
    }

    public void setMaeContribucionSolidariaId(int maeContribucionSolidariaId) {
        this.maeContribucionSolidariaId = maeContribucionSolidariaId;
    }

    public String getMaeContribucionSolidariaCodigo() {
        return maeContribucionSolidariaCodigo;
    }

    public void setMaeContribucionSolidariaCodigo(String maeContribucionSolidariaCodigo) {
        this.maeContribucionSolidariaCodigo = maeContribucionSolidariaCodigo;
    }

    public String getMaeContribucionSolidariaValor() {
        return maeContribucionSolidariaValor;
    }

    public void setMaeContribucionSolidariaValor(String maeContribucionSolidariaValor) {
        this.maeContribucionSolidariaValor = maeContribucionSolidariaValor;
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

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CsContribucionesSolidarias)) {
            return false;
        }
        CsContribucionesSolidarias other = (CsContribucionesSolidarias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CsContribucionesSolidarias[ id=" + id + " ]";
    }
    
}
