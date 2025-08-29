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
@Table(name = "auc_hospitalizacion_patologias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucHospitalizacionPatologias.findAll", query = "SELECT a FROM AucHospitalizacionPatologias a"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findById", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.id = :id"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByMaePatologiaId", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.maePatologiaId = :maePatologiaId"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByMaePatologiaCodigo", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.maePatologiaCodigo = :maePatologiaCodigo"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByMaePatologiaValor", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.maePatologiaValor = :maePatologiaValor"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByEstado", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.estado = :estado"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByDescripcion", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByUsuarioCrea", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByTerminalCrea", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByFechaHoraCrea", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByUsuarioModifica", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByTerminalModifica", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucHospitalizacionPatologias.findByFechaHoraModifica", query = "SELECT a FROM AucHospitalizacionPatologias a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AucHospitalizacionPatologias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_patologia_id")
    private int maePatologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_patologia_codigo")
    private String maePatologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_patologia_valor")
    private String maePatologiaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Size(max = 2048)
    @Column(name = "descripcion")
    private String descripcion;
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

    public AucHospitalizacionPatologias() {
    }

    public AucHospitalizacionPatologias(Integer id) {
        this.id = id;
    }

    public AucHospitalizacionPatologias(Integer id, int maePatologiaId, String maePatologiaCodigo, String maePatologiaValor, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maePatologiaId = maePatologiaId;
        this.maePatologiaCodigo = maePatologiaCodigo;
        this.maePatologiaValor = maePatologiaValor;
        this.estado = estado;
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

    public int getMaePatologiaId() {
        return maePatologiaId;
    }

    public void setMaePatologiaId(int maePatologiaId) {
        this.maePatologiaId = maePatologiaId;
    }

    public String getMaePatologiaCodigo() {
        return maePatologiaCodigo;
    }

    public void setMaePatologiaCodigo(String maePatologiaCodigo) {
        this.maePatologiaCodigo = maePatologiaCodigo;
    }

    public String getMaePatologiaValor() {
        return maePatologiaValor;
    }

    public void setMaePatologiaValor(String maePatologiaValor) {
        this.maePatologiaValor = maePatologiaValor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof AucHospitalizacionPatologias)) {
            return false;
        }
        AucHospitalizacionPatologias other = (AucHospitalizacionPatologias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucHospitalizacionPatologias[ id=" + id + " ]";
    }
    
}
