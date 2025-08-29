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
@Table(name = "auc_justificacion_estancias_prolongadas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findAll", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findById", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.id = :id"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByResumenClinico", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.resumenClinico = :resumenClinico"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByMaeCausaEstanciaProlongadaId", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.maeCausaEstanciaProlongadaId = :maeCausaEstanciaProlongadaId"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByMaeCausaEstanciaProlongadaCodigo", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.maeCausaEstanciaProlongadaCodigo = :maeCausaEstanciaProlongadaCodigo"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByMaeCausaEstanciaProlongadaValor", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.maeCausaEstanciaProlongadaValor = :maeCausaEstanciaProlongadaValor"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByMaePropuestaIntervencionId", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.maePropuestaIntervencionId = :maePropuestaIntervencionId"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByMaePropuestaIntervencionCodigo", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.maePropuestaIntervencionCodigo = :maePropuestaIntervencionCodigo"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByMaePropuestaIntervencionValor", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.maePropuestaIntervencionValor = :maePropuestaIntervencionValor"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByUsuarioCrea", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByTerminalCrea", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByFechaHoraCrea", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByUsuarioModifica", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByTerminalModifica", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucJustificacionEstanciasProlongadas.findByFechaHoraModifica", query = "SELECT a FROM AucJustificacionEstanciasProlongadas a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AucJustificacionEstanciasProlongadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "resumen_clinico")
    private String resumenClinico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_causa_estancia_prolongada_id")
    private int maeCausaEstanciaProlongadaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_causa_estancia_prolongada_codigo")
    private String maeCausaEstanciaProlongadaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_causa_estancia_prolongada_valor")
    private String maeCausaEstanciaProlongadaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_propuesta_intervencion_id")
    private int maePropuestaIntervencionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_propuesta_intervencion_codigo")
    private String maePropuestaIntervencionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_propuesta_intervencion_valor")
    private String maePropuestaIntervencionValor;
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
    @JoinColumn(name = "auc_hospitalizaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AucHospitalizaciones aucHospitalizacionesId;

    public AucJustificacionEstanciasProlongadas() {
    }

    public AucJustificacionEstanciasProlongadas(Integer id) {
        this.id = id;
    }

    public AucJustificacionEstanciasProlongadas(Integer id, String resumenClinico, int maeCausaEstanciaProlongadaId, String maeCausaEstanciaProlongadaCodigo, String maeCausaEstanciaProlongadaValor, int maePropuestaIntervencionId, String maePropuestaIntervencionCodigo, String maePropuestaIntervencionValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.resumenClinico = resumenClinico;
        this.maeCausaEstanciaProlongadaId = maeCausaEstanciaProlongadaId;
        this.maeCausaEstanciaProlongadaCodigo = maeCausaEstanciaProlongadaCodigo;
        this.maeCausaEstanciaProlongadaValor = maeCausaEstanciaProlongadaValor;
        this.maePropuestaIntervencionId = maePropuestaIntervencionId;
        this.maePropuestaIntervencionCodigo = maePropuestaIntervencionCodigo;
        this.maePropuestaIntervencionValor = maePropuestaIntervencionValor;
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

    public String getResumenClinico() {
        return resumenClinico;
    }

    public void setResumenClinico(String resumenClinico) {
        this.resumenClinico = resumenClinico;
    }

    public int getMaeCausaEstanciaProlongadaId() {
        return maeCausaEstanciaProlongadaId;
    }

    public void setMaeCausaEstanciaProlongadaId(int maeCausaEstanciaProlongadaId) {
        this.maeCausaEstanciaProlongadaId = maeCausaEstanciaProlongadaId;
    }

    public String getMaeCausaEstanciaProlongadaCodigo() {
        return maeCausaEstanciaProlongadaCodigo;
    }

    public void setMaeCausaEstanciaProlongadaCodigo(String maeCausaEstanciaProlongadaCodigo) {
        this.maeCausaEstanciaProlongadaCodigo = maeCausaEstanciaProlongadaCodigo;
    }

    public String getMaeCausaEstanciaProlongadaValor() {
        return maeCausaEstanciaProlongadaValor;
    }

    public void setMaeCausaEstanciaProlongadaValor(String maeCausaEstanciaProlongadaValor) {
        this.maeCausaEstanciaProlongadaValor = maeCausaEstanciaProlongadaValor;
    }

    public int getMaePropuestaIntervencionId() {
        return maePropuestaIntervencionId;
    }

    public void setMaePropuestaIntervencionId(int maePropuestaIntervencionId) {
        this.maePropuestaIntervencionId = maePropuestaIntervencionId;
    }

    public String getMaePropuestaIntervencionCodigo() {
        return maePropuestaIntervencionCodigo;
    }

    public void setMaePropuestaIntervencionCodigo(String maePropuestaIntervencionCodigo) {
        this.maePropuestaIntervencionCodigo = maePropuestaIntervencionCodigo;
    }

    public String getMaePropuestaIntervencionValor() {
        return maePropuestaIntervencionValor;
    }

    public void setMaePropuestaIntervencionValor(String maePropuestaIntervencionValor) {
        this.maePropuestaIntervencionValor = maePropuestaIntervencionValor;
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

    public AucHospitalizaciones getAucHospitalizacionesId() {
        return aucHospitalizacionesId;
    }

    public void setAucHospitalizacionesId(AucHospitalizaciones aucHospitalizacionesId) {
        this.aucHospitalizacionesId = aucHospitalizacionesId;
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
        if (!(object instanceof AucJustificacionEstanciasProlongadas)) {
            return false;
        }
        AucJustificacionEstanciasProlongadas other = (AucJustificacionEstanciasProlongadas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucJustificacionEstanciasProlongadas[ id=" + id + " ]";
    }
    
}
