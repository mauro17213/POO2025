/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "pe_afiliados_sugeridos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeAfiliadosSugeridos.findAll", query = "SELECT p FROM PeAfiliadosSugeridos p"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findById", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.id = :id"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByOrigen", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.origen = :origen"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByEstado", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.estado = :estado"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByObservacion", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByRechazoOrigen", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.rechazoOrigen = :rechazoOrigen"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByRechazoObservacion", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.rechazoObservacion = :rechazoObservacion"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByUsuarioCrea", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByTerminalCrea", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByFechaHoraCrea", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByUsuarioModifica", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByTerminalModifica", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeAfiliadosSugeridos.findByFechaHoraModifica", query = "SELECT p FROM PeAfiliadosSugeridos p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeAfiliadosSugeridos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen")
    private short origen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Size(max = 1024)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "rechazo_origen")
    private Short rechazoOrigen;
    @Size(max = 1024)
    @Column(name = "rechazo_observacion")
    private String rechazoObservacion;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;
    @JoinColumn(name = "auc_hospitalizaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AucHospitalizaciones aucHospitalizacionesId;
    @JoinColumn(name = "mp_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionesId;
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;
    @JoinColumn(name = "ref_anexos9_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RefAnexos9 refAnexos9Id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peAfiliadosSugeridosId", fetch = FetchType.LAZY)
    private List<PeSugeridoAdjuntos> peSugeridoAdjuntosList;
    @OneToMany(mappedBy = "peAfiliadosSugeridosId", fetch = FetchType.LAZY)
    private List<PeAfiliadosProgramas> peAfiliadosProgramasList;

    public PeAfiliadosSugeridos() {
    }

    public PeAfiliadosSugeridos(Integer id) {
        this.id = id;
    }

    public PeAfiliadosSugeridos(Integer id, short origen, short estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.origen = origen;
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

    public short getOrigen() {
        return origen;
    }

    public void setOrigen(short origen) {
        this.origen = origen;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Short getRechazoOrigen() {
        return rechazoOrigen;
    }

    public void setRechazoOrigen(Short rechazoOrigen) {
        this.rechazoOrigen = rechazoOrigen;
    }

    public String getRechazoObservacion() {
        return rechazoObservacion;
    }

    public void setRechazoObservacion(String rechazoObservacion) {
        this.rechazoObservacion = rechazoObservacion;
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

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public AucHospitalizaciones getAucHospitalizacionesId() {
        return aucHospitalizacionesId;
    }

    public void setAucHospitalizacionesId(AucHospitalizaciones aucHospitalizacionesId) {
        this.aucHospitalizacionesId = aucHospitalizacionesId;
    }

    public MpPrescripciones getMpPrescripcionesId() {
        return mpPrescripcionesId;
    }

    public void setMpPrescripcionesId(MpPrescripciones mpPrescripcionesId) {
        this.mpPrescripcionesId = mpPrescripcionesId;
    }

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    public RefAnexos9 getRefAnexos9Id() {
        return refAnexos9Id;
    }

    public void setRefAnexos9Id(RefAnexos9 refAnexos9Id) {
        this.refAnexos9Id = refAnexos9Id;
    }

    @XmlTransient
    public List<PeSugeridoAdjuntos> getPeSugeridoAdjuntosList() {
        return peSugeridoAdjuntosList;
    }

    public void setPeSugeridoAdjuntosList(List<PeSugeridoAdjuntos> peSugeridoAdjuntosList) {
        this.peSugeridoAdjuntosList = peSugeridoAdjuntosList;
    }

    @XmlTransient
    public List<PeAfiliadosProgramas> getPeAfiliadosProgramasList() {
        return peAfiliadosProgramasList;
    }

    public void setPeAfiliadosProgramasList(List<PeAfiliadosProgramas> peAfiliadosProgramasList) {
        this.peAfiliadosProgramasList = peAfiliadosProgramasList;
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
        if (!(object instanceof PeAfiliadosSugeridos)) {
            return false;
        }
        PeAfiliadosSugeridos other = (PeAfiliadosSugeridos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeAfiliadosSugeridos[ id=" + id + " ]";
    }
    
}
