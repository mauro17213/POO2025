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
@Table(name = "ma_medicamento_administraciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaMedicamentoAdministraciones.findAll", query = "SELECT m FROM MaMedicamentoAdministraciones m"),
    @NamedQuery(name = "MaMedicamentoAdministraciones.findById", query = "SELECT m FROM MaMedicamentoAdministraciones m WHERE m.id = :id"),
    @NamedQuery(name = "MaMedicamentoAdministraciones.findByMaeViaAdministracionId", query = "SELECT m FROM MaMedicamentoAdministraciones m WHERE m.maeViaAdministracionId = :maeViaAdministracionId"),
    @NamedQuery(name = "MaMedicamentoAdministraciones.findByMaeViaAdministracionCodigo", query = "SELECT m FROM MaMedicamentoAdministraciones m WHERE m.maeViaAdministracionCodigo = :maeViaAdministracionCodigo"),
    @NamedQuery(name = "MaMedicamentoAdministraciones.findByMaeViaAdministracionValor", query = "SELECT m FROM MaMedicamentoAdministraciones m WHERE m.maeViaAdministracionValor = :maeViaAdministracionValor"),
    @NamedQuery(name = "MaMedicamentoAdministraciones.findByUsuarioCrea", query = "SELECT m FROM MaMedicamentoAdministraciones m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaMedicamentoAdministraciones.findByTerminalCrea", query = "SELECT m FROM MaMedicamentoAdministraciones m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaMedicamentoAdministraciones.findByFechaHoraCrea", query = "SELECT m FROM MaMedicamentoAdministraciones m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MaMedicamentoAdministraciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_via_administracion_id")
    private int maeViaAdministracionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_via_administracion_codigo")
    private String maeViaAdministracionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_via_administracion_valor")
    private String maeViaAdministracionValor;
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
    @JoinColumn(name = "ma_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaMedicamentos maMedicamentosId;

    public MaMedicamentoAdministraciones() {
    }

    public MaMedicamentoAdministraciones(Integer id) {
        this.id = id;
    }

    public MaMedicamentoAdministraciones(Integer id, int maeViaAdministracionId, String maeViaAdministracionCodigo, String maeViaAdministracionValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeViaAdministracionId = maeViaAdministracionId;
        this.maeViaAdministracionCodigo = maeViaAdministracionCodigo;
        this.maeViaAdministracionValor = maeViaAdministracionValor;
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

    public int getMaeViaAdministracionId() {
        return maeViaAdministracionId;
    }

    public void setMaeViaAdministracionId(int maeViaAdministracionId) {
        this.maeViaAdministracionId = maeViaAdministracionId;
    }

    public String getMaeViaAdministracionCodigo() {
        return maeViaAdministracionCodigo;
    }

    public void setMaeViaAdministracionCodigo(String maeViaAdministracionCodigo) {
        this.maeViaAdministracionCodigo = maeViaAdministracionCodigo;
    }

    public String getMaeViaAdministracionValor() {
        return maeViaAdministracionValor;
    }

    public void setMaeViaAdministracionValor(String maeViaAdministracionValor) {
        this.maeViaAdministracionValor = maeViaAdministracionValor;
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

    public MaMedicamentos getMaMedicamentosId() {
        return maMedicamentosId;
    }

    public void setMaMedicamentosId(MaMedicamentos maMedicamentosId) {
        this.maMedicamentosId = maMedicamentosId;
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
        if (!(object instanceof MaMedicamentoAdministraciones)) {
            return false;
        }
        MaMedicamentoAdministraciones other = (MaMedicamentoAdministraciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaMedicamentoAdministraciones[ id=" + id + " ]";
    }
    
}
