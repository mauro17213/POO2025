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
@Table(name = "auc_hospitalizacion_adversos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucHospitalizacionAdversos.findAll", query = "SELECT a FROM AucHospitalizacionAdversos a"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findById", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.id = :id"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByFechaEvento", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.fechaEvento = :fechaEvento"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByDescripcionEvento", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.descripcionEvento = :descripcionEvento"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByMaeCategoriaEventoId", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.maeCategoriaEventoId = :maeCategoriaEventoId"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByMaeCategoriaEventoCodigo", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.maeCategoriaEventoCodigo = :maeCategoriaEventoCodigo"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByMaeCategoriaEventoValor", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.maeCategoriaEventoValor = :maeCategoriaEventoValor"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByMaeSubcategoriaEventoId", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.maeSubcategoriaEventoId = :maeSubcategoriaEventoId"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByMaeSubcategoriaEventoCodigo", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.maeSubcategoriaEventoCodigo = :maeSubcategoriaEventoCodigo"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByMaeSubcategoriaEventoValor", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.maeSubcategoriaEventoValor = :maeSubcategoriaEventoValor"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByFechaAnalisis", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.fechaAnalisis = :fechaAnalisis"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByDescripcionPlanMejora", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.descripcionPlanMejora = :descripcionPlanMejora"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByFechaSolicitudAnalisis", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.fechaSolicitudAnalisis = :fechaSolicitudAnalisis"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByDescripcionAnalisis", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.descripcionAnalisis = :descripcionAnalisis"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByMaeConclusionEventoId", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.maeConclusionEventoId = :maeConclusionEventoId"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByMaeConclusionEventoCodigo", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.maeConclusionEventoCodigo = :maeConclusionEventoCodigo"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByMaeConclusionEventoValor", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.maeConclusionEventoValor = :maeConclusionEventoValor"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByBorrado", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByBorradoObservacion", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.borradoObservacion = :borradoObservacion"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByUsuarioCrea", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByTerminalCrea", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByFechaHoraCrea", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByUsuarioModifica", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByTerminalModifica", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByFechaHoraModifica", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByUsuarioBorra", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByTerminalBorra", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AucHospitalizacionAdversos.findByFechaHoraBorra", query = "SELECT a FROM AucHospitalizacionAdversos a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AucHospitalizacionAdversos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_evento")
    @Temporal(TemporalType.DATE)
    private Date fechaEvento;
    @Size(max = 4096)
    @Column(name = "descripcion_evento")
    private String descripcionEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_categoria_evento_id")
    private int maeCategoriaEventoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_categoria_evento_codigo")
    private String maeCategoriaEventoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_categoria_evento_valor")
    private String maeCategoriaEventoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_subcategoria_evento_id")
    private int maeSubcategoriaEventoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_subcategoria_evento_codigo")
    private String maeSubcategoriaEventoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_subcategoria_evento_valor")
    private String maeSubcategoriaEventoValor;
    @Column(name = "fecha_analisis")
    @Temporal(TemporalType.DATE)
    private Date fechaAnalisis;
    @Size(max = 2048)
    @Column(name = "descripcion_plan_mejora")
    private String descripcionPlanMejora;
    @Column(name = "fecha_solicitud_analisis")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitudAnalisis;
    @Size(max = 4096)
    @Column(name = "descripcion_analisis")
    private String descripcionAnalisis;
    @Column(name = "mae_conclusion_evento_id")
    private Integer maeConclusionEventoId;
    @Size(max = 8)
    @Column(name = "mae_conclusion_evento_codigo")
    private String maeConclusionEventoCodigo;
    @Size(max = 128)
    @Column(name = "mae_conclusion_evento_valor")
    private String maeConclusionEventoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Size(max = 512)
    @Column(name = "borrado_observacion")
    private String borradoObservacion;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "auc_hospitalizaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AucHospitalizaciones aucHospitalizacionesId;

    public AucHospitalizacionAdversos() {
    }

    public AucHospitalizacionAdversos(Integer id) {
        this.id = id;
    }

    public AucHospitalizacionAdversos(Integer id, Date fechaEvento, int maeCategoriaEventoId, String maeCategoriaEventoCodigo, String maeCategoriaEventoValor, int maeSubcategoriaEventoId, String maeSubcategoriaEventoCodigo, String maeSubcategoriaEventoValor, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaEvento = fechaEvento;
        this.maeCategoriaEventoId = maeCategoriaEventoId;
        this.maeCategoriaEventoCodigo = maeCategoriaEventoCodigo;
        this.maeCategoriaEventoValor = maeCategoriaEventoValor;
        this.maeSubcategoriaEventoId = maeSubcategoriaEventoId;
        this.maeSubcategoriaEventoCodigo = maeSubcategoriaEventoCodigo;
        this.maeSubcategoriaEventoValor = maeSubcategoriaEventoValor;
        this.borrado = borrado;
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

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public void setDescripcionEvento(String descripcionEvento) {
        this.descripcionEvento = descripcionEvento;
    }

    public int getMaeCategoriaEventoId() {
        return maeCategoriaEventoId;
    }

    public void setMaeCategoriaEventoId(int maeCategoriaEventoId) {
        this.maeCategoriaEventoId = maeCategoriaEventoId;
    }

    public String getMaeCategoriaEventoCodigo() {
        return maeCategoriaEventoCodigo;
    }

    public void setMaeCategoriaEventoCodigo(String maeCategoriaEventoCodigo) {
        this.maeCategoriaEventoCodigo = maeCategoriaEventoCodigo;
    }

    public String getMaeCategoriaEventoValor() {
        return maeCategoriaEventoValor;
    }

    public void setMaeCategoriaEventoValor(String maeCategoriaEventoValor) {
        this.maeCategoriaEventoValor = maeCategoriaEventoValor;
    }

    public int getMaeSubcategoriaEventoId() {
        return maeSubcategoriaEventoId;
    }

    public void setMaeSubcategoriaEventoId(int maeSubcategoriaEventoId) {
        this.maeSubcategoriaEventoId = maeSubcategoriaEventoId;
    }

    public String getMaeSubcategoriaEventoCodigo() {
        return maeSubcategoriaEventoCodigo;
    }

    public void setMaeSubcategoriaEventoCodigo(String maeSubcategoriaEventoCodigo) {
        this.maeSubcategoriaEventoCodigo = maeSubcategoriaEventoCodigo;
    }

    public String getMaeSubcategoriaEventoValor() {
        return maeSubcategoriaEventoValor;
    }

    public void setMaeSubcategoriaEventoValor(String maeSubcategoriaEventoValor) {
        this.maeSubcategoriaEventoValor = maeSubcategoriaEventoValor;
    }

    public Date getFechaAnalisis() {
        return fechaAnalisis;
    }

    public void setFechaAnalisis(Date fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
    }

    public String getDescripcionPlanMejora() {
        return descripcionPlanMejora;
    }

    public void setDescripcionPlanMejora(String descripcionPlanMejora) {
        this.descripcionPlanMejora = descripcionPlanMejora;
    }

    public Date getFechaSolicitudAnalisis() {
        return fechaSolicitudAnalisis;
    }

    public void setFechaSolicitudAnalisis(Date fechaSolicitudAnalisis) {
        this.fechaSolicitudAnalisis = fechaSolicitudAnalisis;
    }

    public String getDescripcionAnalisis() {
        return descripcionAnalisis;
    }

    public void setDescripcionAnalisis(String descripcionAnalisis) {
        this.descripcionAnalisis = descripcionAnalisis;
    }

    public Integer getMaeConclusionEventoId() {
        return maeConclusionEventoId;
    }

    public void setMaeConclusionEventoId(Integer maeConclusionEventoId) {
        this.maeConclusionEventoId = maeConclusionEventoId;
    }

    public String getMaeConclusionEventoCodigo() {
        return maeConclusionEventoCodigo;
    }

    public void setMaeConclusionEventoCodigo(String maeConclusionEventoCodigo) {
        this.maeConclusionEventoCodigo = maeConclusionEventoCodigo;
    }

    public String getMaeConclusionEventoValor() {
        return maeConclusionEventoValor;
    }

    public void setMaeConclusionEventoValor(String maeConclusionEventoValor) {
        this.maeConclusionEventoValor = maeConclusionEventoValor;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
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
        if (!(object instanceof AucHospitalizacionAdversos)) {
            return false;
        }
        AucHospitalizacionAdversos other = (AucHospitalizacionAdversos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionAdversos[ id=" + id + " ]";
    }
    
}
