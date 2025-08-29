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
@Table(name = "auc_hospitalizacion_estancias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucHospitalizacionEstancias.findAll", query = "SELECT a FROM AucHospitalizacionEstancias a"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findById", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.id = :id"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByFechaIngreso", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByFechaEgreso", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.fechaEgreso = :fechaEgreso"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByDias", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.dias = :dias"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByMaeServicioId", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.maeServicioId = :maeServicioId"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByMaeServicioCodigo", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.maeServicioCodigo = :maeServicioCodigo"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByMaeServicioValor", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.maeServicioValor = :maeServicioValor"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByUsuarioCrea", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByTerminalCrea", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByFechaHoraCrea", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByUsuarioModifica", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByTerminalModifica", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucHospitalizacionEstancias.findByFechaHoraModifica", query = "SELECT a FROM AucHospitalizacionEstancias a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AucHospitalizacionEstancias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "fecha_egreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEgreso;
    @Column(name = "dias")
    private Integer dias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_servicio_id")
    private int maeServicioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_servicio_codigo")
    private String maeServicioCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_servicio_valor")
    private String maeServicioValor;
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

    public AucHospitalizacionEstancias() {
    }

    public AucHospitalizacionEstancias(Integer id) {
        this.id = id;
    }

    public AucHospitalizacionEstancias(Integer id, Date fechaIngreso, int maeServicioId, String maeServicioCodigo, String maeServicioValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.maeServicioId = maeServicioId;
        this.maeServicioCodigo = maeServicioCodigo;
        this.maeServicioValor = maeServicioValor;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public int getMaeServicioId() {
        return maeServicioId;
    }

    public void setMaeServicioId(int maeServicioId) {
        this.maeServicioId = maeServicioId;
    }

    public String getMaeServicioCodigo() {
        return maeServicioCodigo;
    }

    public void setMaeServicioCodigo(String maeServicioCodigo) {
        this.maeServicioCodigo = maeServicioCodigo;
    }

    public String getMaeServicioValor() {
        return maeServicioValor;
    }

    public void setMaeServicioValor(String maeServicioValor) {
        this.maeServicioValor = maeServicioValor;
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
        if (!(object instanceof AucHospitalizacionEstancias)) {
            return false;
        }
        AucHospitalizacionEstancias other = (AucHospitalizacionEstancias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionEstancias[ id=" + id + " ]";
    }
    
}
