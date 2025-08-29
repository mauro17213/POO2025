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
@Table(name = "ma_tecnologia_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaTecnologiaGrupos.findAll", query = "SELECT m FROM MaTecnologiaGrupos m"),
    @NamedQuery(name = "MaTecnologiaGrupos.findById", query = "SELECT m FROM MaTecnologiaGrupos m WHERE m.id = :id"),
    @NamedQuery(name = "MaTecnologiaGrupos.findByMaeGrupoTecnologiaId", query = "SELECT m FROM MaTecnologiaGrupos m WHERE m.maeGrupoTecnologiaId = :maeGrupoTecnologiaId"),
    @NamedQuery(name = "MaTecnologiaGrupos.findByMaeGrupoTecnologiaCodigo", query = "SELECT m FROM MaTecnologiaGrupos m WHERE m.maeGrupoTecnologiaCodigo = :maeGrupoTecnologiaCodigo"),
    @NamedQuery(name = "MaTecnologiaGrupos.findByMaeGrupoTecnologiaValor", query = "SELECT m FROM MaTecnologiaGrupos m WHERE m.maeGrupoTecnologiaValor = :maeGrupoTecnologiaValor"),
    @NamedQuery(name = "MaTecnologiaGrupos.findByUsuarioCrea", query = "SELECT m FROM MaTecnologiaGrupos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaTecnologiaGrupos.findByTerminalCrea", query = "SELECT m FROM MaTecnologiaGrupos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaTecnologiaGrupos.findByFechaHoraCrea", query = "SELECT m FROM MaTecnologiaGrupos m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MaTecnologiaGrupos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_grupo_tecnologia_id")
    private int maeGrupoTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_grupo_tecnologia_codigo")
    private String maeGrupoTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_grupo_tecnologia_valor")
    private String maeGrupoTecnologiaValor;
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
    @JoinColumn(name = "ma_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaTecnologias maTecnologiasId;

    public MaTecnologiaGrupos() {
    }

    public MaTecnologiaGrupos(Integer id) {
        this.id = id;
    }

    public MaTecnologiaGrupos(Integer id, int maeGrupoTecnologiaId, String maeGrupoTecnologiaCodigo, String maeGrupoTecnologiaValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeGrupoTecnologiaId = maeGrupoTecnologiaId;
        this.maeGrupoTecnologiaCodigo = maeGrupoTecnologiaCodigo;
        this.maeGrupoTecnologiaValor = maeGrupoTecnologiaValor;
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

    public int getMaeGrupoTecnologiaId() {
        return maeGrupoTecnologiaId;
    }

    public void setMaeGrupoTecnologiaId(int maeGrupoTecnologiaId) {
        this.maeGrupoTecnologiaId = maeGrupoTecnologiaId;
    }

    public String getMaeGrupoTecnologiaCodigo() {
        return maeGrupoTecnologiaCodigo;
    }

    public void setMaeGrupoTecnologiaCodigo(String maeGrupoTecnologiaCodigo) {
        this.maeGrupoTecnologiaCodigo = maeGrupoTecnologiaCodigo;
    }

    public String getMaeGrupoTecnologiaValor() {
        return maeGrupoTecnologiaValor;
    }

    public void setMaeGrupoTecnologiaValor(String maeGrupoTecnologiaValor) {
        this.maeGrupoTecnologiaValor = maeGrupoTecnologiaValor;
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

    public MaTecnologias getMaTecnologiasId() {
        return maTecnologiasId;
    }

    public void setMaTecnologiasId(MaTecnologias maTecnologiasId) {
        this.maTecnologiasId = maTecnologiasId;
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
        if (!(object instanceof MaTecnologiaGrupos)) {
            return false;
        }
        MaTecnologiaGrupos other = (MaTecnologiaGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaTecnologiaGrupos[ id=" + id + " ]";
    }
    
}
