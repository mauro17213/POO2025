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
@Table(name = "cm_rips_inhabilitados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsInhabilitados.findAll", query = "SELECT c FROM CmRipsInhabilitados c"),
    @NamedQuery(name = "CmRipsInhabilitados.findById", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsInhabilitados.findByMotivo", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.motivo = :motivo"),
    @NamedQuery(name = "CmRipsInhabilitados.findByFechaHoraDesde", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.fechaHoraDesde = :fechaHoraDesde"),
    @NamedQuery(name = "CmRipsInhabilitados.findByFechaHoraHasta", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.fechaHoraHasta = :fechaHoraHasta"),
    @NamedQuery(name = "CmRipsInhabilitados.findByMaeContratoModalidadId", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.maeContratoModalidadId = :maeContratoModalidadId"),
    @NamedQuery(name = "CmRipsInhabilitados.findByMaeContratoModalidadCodigo", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.maeContratoModalidadCodigo = :maeContratoModalidadCodigo"),
    @NamedQuery(name = "CmRipsInhabilitados.findByMaeContratoModalidadValor", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.maeContratoModalidadValor = :maeContratoModalidadValor"),
    @NamedQuery(name = "CmRipsInhabilitados.findByCoberturaCierre", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.coberturaCierre = :coberturaCierre"),
    @NamedQuery(name = "CmRipsInhabilitados.findByUsuarioCrea", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsInhabilitados.findByTerminalCrea", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsInhabilitados.findByFechaHoraCrea", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmRipsInhabilitados.findByUsuarioModifica", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmRipsInhabilitados.findByTerminalModifica", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmRipsInhabilitados.findByFechaHoraModifica", query = "SELECT c FROM CmRipsInhabilitados c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmRipsInhabilitados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "motivo")
    private String motivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_contrato_modalidad_id")
    private int maeContratoModalidadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_contrato_modalidad_codigo")
    private String maeContratoModalidadCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_contrato_modalidad_valor")
    private String maeContratoModalidadValor;
    @Column(name = "cobertura_cierre")
    private Integer coberturaCierre;
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

    public CmRipsInhabilitados() {
    }

    public CmRipsInhabilitados(Integer id) {
        this.id = id;
    }

    public CmRipsInhabilitados(Integer id, String motivo, Date fechaHoraDesde, Date fechaHoraHasta, int maeContratoModalidadId, String maeContratoModalidadCodigo, String maeContratoModalidadValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.motivo = motivo;
        this.fechaHoraDesde = fechaHoraDesde;
        this.fechaHoraHasta = fechaHoraHasta;
        this.maeContratoModalidadId = maeContratoModalidadId;
        this.maeContratoModalidadCodigo = maeContratoModalidadCodigo;
        this.maeContratoModalidadValor = maeContratoModalidadValor;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaHoraDesde() {
        return fechaHoraDesde;
    }

    public void setFechaHoraDesde(Date fechaHoraDesde) {
        this.fechaHoraDesde = fechaHoraDesde;
    }

    public Date getFechaHoraHasta() {
        return fechaHoraHasta;
    }

    public void setFechaHoraHasta(Date fechaHoraHasta) {
        this.fechaHoraHasta = fechaHoraHasta;
    }

    public int getMaeContratoModalidadId() {
        return maeContratoModalidadId;
    }

    public void setMaeContratoModalidadId(int maeContratoModalidadId) {
        this.maeContratoModalidadId = maeContratoModalidadId;
    }

    public String getMaeContratoModalidadCodigo() {
        return maeContratoModalidadCodigo;
    }

    public void setMaeContratoModalidadCodigo(String maeContratoModalidadCodigo) {
        this.maeContratoModalidadCodigo = maeContratoModalidadCodigo;
    }

    public String getMaeContratoModalidadValor() {
        return maeContratoModalidadValor;
    }

    public void setMaeContratoModalidadValor(String maeContratoModalidadValor) {
        this.maeContratoModalidadValor = maeContratoModalidadValor;
    }

    public Integer getCoberturaCierre() {
        return coberturaCierre;
    }

    public void setCoberturaCierre(Integer coberturaCierre) {
        this.coberturaCierre = coberturaCierre;
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
        if (!(object instanceof CmRipsInhabilitados)) {
            return false;
        }
        CmRipsInhabilitados other = (CmRipsInhabilitados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsInhabilitados[ id=" + id + " ]";
    }
    
}
