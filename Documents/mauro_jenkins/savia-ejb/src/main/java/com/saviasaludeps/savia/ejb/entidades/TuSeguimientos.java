/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "tu_seguimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuSeguimientos.findAll", query = "SELECT t FROM TuSeguimientos t"),
    @NamedQuery(name = "TuSeguimientos.findById", query = "SELECT t FROM TuSeguimientos t WHERE t.id = :id"),
    @NamedQuery(name = "TuSeguimientos.findByMaeTipoSeguimientoId", query = "SELECT t FROM TuSeguimientos t WHERE t.maeTipoSeguimientoId = :maeTipoSeguimientoId"),
    @NamedQuery(name = "TuSeguimientos.findByMaeTipoSeguimientoCodigo", query = "SELECT t FROM TuSeguimientos t WHERE t.maeTipoSeguimientoCodigo = :maeTipoSeguimientoCodigo"),
    @NamedQuery(name = "TuSeguimientos.findByMaeTipoSeguimientoValor", query = "SELECT t FROM TuSeguimientos t WHERE t.maeTipoSeguimientoValor = :maeTipoSeguimientoValor"),
    @NamedQuery(name = "TuSeguimientos.findByFechaSeguimiento", query = "SELECT t FROM TuSeguimientos t WHERE t.fechaSeguimiento = :fechaSeguimiento"),
    @NamedQuery(name = "TuSeguimientos.findByUsuarioCrea", query = "SELECT t FROM TuSeguimientos t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuSeguimientos.findByTerminalCrea", query = "SELECT t FROM TuSeguimientos t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuSeguimientos.findByFechaHoraCrea", query = "SELECT t FROM TuSeguimientos t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuSeguimientos.findByUsuarioModifica", query = "SELECT t FROM TuSeguimientos t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuSeguimientos.findByTerminalModifica", query = "SELECT t FROM TuSeguimientos t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuSeguimientos.findByFechaHoraModifica", query = "SELECT t FROM TuSeguimientos t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuSeguimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_seguimiento_id")
    private int maeTipoSeguimientoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_seguimiento_codigo")
    private String maeTipoSeguimientoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "mae_tipo_seguimiento_valor")
    private String maeTipoSeguimientoValor;
    @Column(name = "fecha_seguimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSeguimiento;
    @Lob
    @Size(max = 16777215)
    @Column(name = "observacion")
    private String observacion;
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
    @JoinColumn(name = "gestor_gn_usuario_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gestorGnUsuarioId;
    @JoinColumn(name = "notificado_gn_usuario_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios notificadoGnUsuarioId;
    @JoinColumn(name = "tu_tutela_estados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TuTutelaEstados tuTutelaEstadosId;
    @OneToMany(mappedBy = "tuSeguimientosId", fetch = FetchType.LAZY)
    private List<TuAdjuntos> tuAdjuntosList;

    public TuSeguimientos() {
    }

    public TuSeguimientos(Integer id) {
        this.id = id;
    }

    public TuSeguimientos(Integer id, int maeTipoSeguimientoId, String maeTipoSeguimientoCodigo, String maeTipoSeguimientoValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoSeguimientoId = maeTipoSeguimientoId;
        this.maeTipoSeguimientoCodigo = maeTipoSeguimientoCodigo;
        this.maeTipoSeguimientoValor = maeTipoSeguimientoValor;
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

    public int getMaeTipoSeguimientoId() {
        return maeTipoSeguimientoId;
    }

    public void setMaeTipoSeguimientoId(int maeTipoSeguimientoId) {
        this.maeTipoSeguimientoId = maeTipoSeguimientoId;
    }

    public String getMaeTipoSeguimientoCodigo() {
        return maeTipoSeguimientoCodigo;
    }

    public void setMaeTipoSeguimientoCodigo(String maeTipoSeguimientoCodigo) {
        this.maeTipoSeguimientoCodigo = maeTipoSeguimientoCodigo;
    }

    public String getMaeTipoSeguimientoValor() {
        return maeTipoSeguimientoValor;
    }

    public void setMaeTipoSeguimientoValor(String maeTipoSeguimientoValor) {
        this.maeTipoSeguimientoValor = maeTipoSeguimientoValor;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public GnUsuarios getGestorGnUsuarioId() {
        return gestorGnUsuarioId;
    }

    public void setGestorGnUsuarioId(GnUsuarios gestorGnUsuarioId) {
        this.gestorGnUsuarioId = gestorGnUsuarioId;
    }

    public GnUsuarios getNotificadoGnUsuarioId() {
        return notificadoGnUsuarioId;
    }

    public void setNotificadoGnUsuarioId(GnUsuarios notificadoGnUsuarioId) {
        this.notificadoGnUsuarioId = notificadoGnUsuarioId;
    }

    public TuTutelaEstados getTuTutelaEstadosId() {
        return tuTutelaEstadosId;
    }

    public void setTuTutelaEstadosId(TuTutelaEstados tuTutelaEstadosId) {
        this.tuTutelaEstadosId = tuTutelaEstadosId;
    }

    @XmlTransient
    public List<TuAdjuntos> getTuAdjuntosList() {
        return tuAdjuntosList;
    }

    public void setTuAdjuntosList(List<TuAdjuntos> tuAdjuntosList) {
        this.tuAdjuntosList = tuAdjuntosList;
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
        if (!(object instanceof TuSeguimientos)) {
            return false;
        }
        TuSeguimientos other = (TuSeguimientos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuSeguimientos[ id=" + id + " ]";
    }
    
}
