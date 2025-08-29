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
@Table(name = "tu_grupo_estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuGrupoEstados.findAll", query = "SELECT t FROM TuGrupoEstados t"),
    @NamedQuery(name = "TuGrupoEstados.findById", query = "SELECT t FROM TuGrupoEstados t WHERE t.id = :id"),
    @NamedQuery(name = "TuGrupoEstados.findByMaeEstadoId", query = "SELECT t FROM TuGrupoEstados t WHERE t.maeEstadoId = :maeEstadoId"),
    @NamedQuery(name = "TuGrupoEstados.findByMaeEstadoCodigo", query = "SELECT t FROM TuGrupoEstados t WHERE t.maeEstadoCodigo = :maeEstadoCodigo"),
    @NamedQuery(name = "TuGrupoEstados.findByMaeEstadoValor", query = "SELECT t FROM TuGrupoEstados t WHERE t.maeEstadoValor = :maeEstadoValor"),
    @NamedQuery(name = "TuGrupoEstados.findByReparto", query = "SELECT t FROM TuGrupoEstados t WHERE t.reparto = :reparto"),
    @NamedQuery(name = "TuGrupoEstados.findByUsuarioCrea", query = "SELECT t FROM TuGrupoEstados t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuGrupoEstados.findByTerminalCrea", query = "SELECT t FROM TuGrupoEstados t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuGrupoEstados.findByFechaHoraCrea", query = "SELECT t FROM TuGrupoEstados t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuGrupoEstados.findByUsuarioModifica", query = "SELECT t FROM TuGrupoEstados t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuGrupoEstados.findByTerminalModifica", query = "SELECT t FROM TuGrupoEstados t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuGrupoEstados.findByFechaHoraModifica", query = "SELECT t FROM TuGrupoEstados t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuGrupoEstados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_id")
    private int maeEstadoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_estado_codigo")
    private String maeEstadoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "mae_estado_valor")
    private String maeEstadoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reparto")
    private boolean reparto;
    @Size(max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Size(max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
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
    @JoinColumn(name = "tu_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuGrupos tuGruposId;

    public TuGrupoEstados() {
    }

    public TuGrupoEstados(Integer id) {
        this.id = id;
    }

    public TuGrupoEstados(Integer id, int maeEstadoId, String maeEstadoCodigo, String maeEstadoValor, boolean reparto) {
        this.id = id;
        this.maeEstadoId = maeEstadoId;
        this.maeEstadoCodigo = maeEstadoCodigo;
        this.maeEstadoValor = maeEstadoValor;
        this.reparto = reparto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(int maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public boolean getReparto() {
        return reparto;
    }

    public void setReparto(boolean reparto) {
        this.reparto = reparto;
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

    public TuGrupos getTuGruposId() {
        return tuGruposId;
    }

    public void setTuGruposId(TuGrupos tuGruposId) {
        this.tuGruposId = tuGruposId;
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
        if (!(object instanceof TuGrupoEstados)) {
            return false;
        }
        TuGrupoEstados other = (TuGrupoEstados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuGrupoEstados[ id=" + id + " ]";
    }
    
}
