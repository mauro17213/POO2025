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
@Table(name = "cm_rips_ct_control")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsCtControl.findAll", query = "SELECT c FROM CmRipsCtControl c"),
    @NamedQuery(name = "CmRipsCtControl.findById", query = "SELECT c FROM CmRipsCtControl c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsCtControl.findByFila", query = "SELECT c FROM CmRipsCtControl c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsCtControl.findByCodigoReps", query = "SELECT c FROM CmRipsCtControl c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoControl", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoControl = :archivoControl"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoAf", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoAf = :archivoAf"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoUs", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoUs = :archivoUs"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoAc", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoAc = :archivoAc"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoAp", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoAp = :archivoAp"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoAh", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoAh = :archivoAh"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoAu", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoAu = :archivoAu"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoAm", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoAm = :archivoAm"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoAt", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoAt = :archivoAt"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoAn", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoAn = :archivoAn"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoAd", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoAd = :archivoAd"),
    @NamedQuery(name = "CmRipsCtControl.findByRegistrosAf", query = "SELECT c FROM CmRipsCtControl c WHERE c.registrosAf = :registrosAf"),
    @NamedQuery(name = "CmRipsCtControl.findByRegistrosUs", query = "SELECT c FROM CmRipsCtControl c WHERE c.registrosUs = :registrosUs"),
    @NamedQuery(name = "CmRipsCtControl.findByRegistroAc", query = "SELECT c FROM CmRipsCtControl c WHERE c.registroAc = :registroAc"),
    @NamedQuery(name = "CmRipsCtControl.findByRegistrosAp", query = "SELECT c FROM CmRipsCtControl c WHERE c.registrosAp = :registrosAp"),
    @NamedQuery(name = "CmRipsCtControl.findByRegistrosAh", query = "SELECT c FROM CmRipsCtControl c WHERE c.registrosAh = :registrosAh"),
    @NamedQuery(name = "CmRipsCtControl.findByRegistrosAu", query = "SELECT c FROM CmRipsCtControl c WHERE c.registrosAu = :registrosAu"),
    @NamedQuery(name = "CmRipsCtControl.findByRegistrosAm", query = "SELECT c FROM CmRipsCtControl c WHERE c.registrosAm = :registrosAm"),
    @NamedQuery(name = "CmRipsCtControl.findByRegistrosAt", query = "SELECT c FROM CmRipsCtControl c WHERE c.registrosAt = :registrosAt"),
    @NamedQuery(name = "CmRipsCtControl.findByRegistrosAn", query = "SELECT c FROM CmRipsCtControl c WHERE c.registrosAn = :registrosAn"),
    @NamedQuery(name = "CmRipsCtControl.findByRegistrosAd", query = "SELECT c FROM CmRipsCtControl c WHERE c.registrosAd = :registrosAd"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaAf", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaAf = :fechaAf"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaUs", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaUs = :fechaUs"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaAc", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaAc = :fechaAc"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaAp", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaAp = :fechaAp"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaAh", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaAh = :fechaAh"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaAu", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaAu = :fechaAu"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaAm", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaAm = :fechaAm"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaAt", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaAt = :fechaAt"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaAn", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaAn = :fechaAn"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaAd", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaAd = :fechaAd"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoRuta", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsCtControl.findByArchivoNombre", query = "SELECT c FROM CmRipsCtControl c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsCtControl.findByUsuarioCrea", query = "SELECT c FROM CmRipsCtControl c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsCtControl.findByTerminalCrea", query = "SELECT c FROM CmRipsCtControl c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsCtControl.findByFechaHoraCrea", query = "SELECT c FROM CmRipsCtControl c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsCtControl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fila")
    private int fila;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo_reps")
    private String codigoReps;
    @Size(max = 16)
    @Column(name = "archivo_control")
    private String archivoControl;
    @Size(max = 16)
    @Column(name = "archivo_af")
    private String archivoAf;
    @Size(max = 16)
    @Column(name = "archivo_us")
    private String archivoUs;
    @Size(max = 16)
    @Column(name = "archivo_ac")
    private String archivoAc;
    @Size(max = 16)
    @Column(name = "archivo_ap")
    private String archivoAp;
    @Size(max = 16)
    @Column(name = "archivo_ah")
    private String archivoAh;
    @Size(max = 16)
    @Column(name = "archivo_au")
    private String archivoAu;
    @Size(max = 16)
    @Column(name = "archivo_am")
    private String archivoAm;
    @Size(max = 16)
    @Column(name = "archivo_at")
    private String archivoAt;
    @Size(max = 16)
    @Column(name = "archivo_an")
    private String archivoAn;
    @Size(max = 16)
    @Column(name = "archivo_ad")
    private String archivoAd;
    @Column(name = "registros_af")
    private Integer registrosAf;
    @Column(name = "registros_us")
    private Integer registrosUs;
    @Column(name = "registro_ac")
    private Integer registroAc;
    @Column(name = "registros_ap")
    private Integer registrosAp;
    @Column(name = "registros_ah")
    private Integer registrosAh;
    @Column(name = "registros_au")
    private Integer registrosAu;
    @Column(name = "registros_am")
    private Integer registrosAm;
    @Column(name = "registros_at")
    private Integer registrosAt;
    @Column(name = "registros_an")
    private Integer registrosAn;
    @Column(name = "registros_ad")
    private Integer registrosAd;
    @Column(name = "fecha_af")
    @Temporal(TemporalType.DATE)
    private Date fechaAf;
    @Column(name = "fecha_us")
    @Temporal(TemporalType.DATE)
    private Date fechaUs;
    @Column(name = "fecha_ac")
    @Temporal(TemporalType.DATE)
    private Date fechaAc;
    @Column(name = "fecha_ap")
    @Temporal(TemporalType.DATE)
    private Date fechaAp;
    @Column(name = "fecha_ah")
    @Temporal(TemporalType.DATE)
    private Date fechaAh;
    @Column(name = "fecha_au")
    @Temporal(TemporalType.DATE)
    private Date fechaAu;
    @Column(name = "fecha_am")
    @Temporal(TemporalType.DATE)
    private Date fechaAm;
    @Column(name = "fecha_at")
    @Temporal(TemporalType.DATE)
    private Date fechaAt;
    @Column(name = "fecha_an")
    @Temporal(TemporalType.DATE)
    private Date fechaAn;
    @Column(name = "fecha_ad")
    @Temporal(TemporalType.DATE)
    private Date fechaAd;
    @Size(max = 256)
    @Column(name = "archivo_nombre_original")
    private String archivoNombreOriginal;
    @Size(max = 512)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Size(max = 128)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cm_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRipsCargas cmRipsCargasId;

    public CmRipsCtControl() {
    }

    public CmRipsCtControl(Integer id) {
        this.id = id;
    }

    public CmRipsCtControl(Integer id, int fila, String codigoReps, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fila = fila;
        this.codigoReps = codigoReps;
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

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getCodigoReps() {
        return codigoReps;
    }

    public void setCodigoReps(String codigoReps) {
        this.codigoReps = codigoReps;
    }

    public String getArchivoControl() {
        return archivoControl;
    }

    public void setArchivoControl(String archivoControl) {
        this.archivoControl = archivoControl;
    }

    public String getArchivoAf() {
        return archivoAf;
    }

    public void setArchivoAf(String archivoAf) {
        this.archivoAf = archivoAf;
    }

    public String getArchivoUs() {
        return archivoUs;
    }

    public void setArchivoUs(String archivoUs) {
        this.archivoUs = archivoUs;
    }

    public String getArchivoAc() {
        return archivoAc;
    }

    public void setArchivoAc(String archivoAc) {
        this.archivoAc = archivoAc;
    }

    public String getArchivoAp() {
        return archivoAp;
    }

    public void setArchivoAp(String archivoAp) {
        this.archivoAp = archivoAp;
    }

    public String getArchivoAh() {
        return archivoAh;
    }

    public void setArchivoAh(String archivoAh) {
        this.archivoAh = archivoAh;
    }

    public String getArchivoAu() {
        return archivoAu;
    }

    public void setArchivoAu(String archivoAu) {
        this.archivoAu = archivoAu;
    }

    public String getArchivoAm() {
        return archivoAm;
    }

    public void setArchivoAm(String archivoAm) {
        this.archivoAm = archivoAm;
    }

    public String getArchivoAt() {
        return archivoAt;
    }

    public void setArchivoAt(String archivoAt) {
        this.archivoAt = archivoAt;
    }

    public String getArchivoAn() {
        return archivoAn;
    }

    public void setArchivoAn(String archivoAn) {
        this.archivoAn = archivoAn;
    }

    public String getArchivoAd() {
        return archivoAd;
    }

    public void setArchivoAd(String archivoAd) {
        this.archivoAd = archivoAd;
    }

    public Integer getRegistrosAf() {
        return registrosAf;
    }

    public void setRegistrosAf(Integer registrosAf) {
        this.registrosAf = registrosAf;
    }

    public Integer getRegistrosUs() {
        return registrosUs;
    }

    public void setRegistrosUs(Integer registrosUs) {
        this.registrosUs = registrosUs;
    }

    public Integer getRegistroAc() {
        return registroAc;
    }

    public void setRegistroAc(Integer registroAc) {
        this.registroAc = registroAc;
    }

    public Integer getRegistrosAp() {
        return registrosAp;
    }

    public void setRegistrosAp(Integer registrosAp) {
        this.registrosAp = registrosAp;
    }

    public Integer getRegistrosAh() {
        return registrosAh;
    }

    public void setRegistrosAh(Integer registrosAh) {
        this.registrosAh = registrosAh;
    }

    public Integer getRegistrosAu() {
        return registrosAu;
    }

    public void setRegistrosAu(Integer registrosAu) {
        this.registrosAu = registrosAu;
    }

    public Integer getRegistrosAm() {
        return registrosAm;
    }

    public void setRegistrosAm(Integer registrosAm) {
        this.registrosAm = registrosAm;
    }

    public Integer getRegistrosAt() {
        return registrosAt;
    }

    public void setRegistrosAt(Integer registrosAt) {
        this.registrosAt = registrosAt;
    }

    public Integer getRegistrosAn() {
        return registrosAn;
    }

    public void setRegistrosAn(Integer registrosAn) {
        this.registrosAn = registrosAn;
    }

    public Integer getRegistrosAd() {
        return registrosAd;
    }

    public void setRegistrosAd(Integer registrosAd) {
        this.registrosAd = registrosAd;
    }

    public Date getFechaAf() {
        return fechaAf;
    }

    public void setFechaAf(Date fechaAf) {
        this.fechaAf = fechaAf;
    }

    public Date getFechaUs() {
        return fechaUs;
    }

    public void setFechaUs(Date fechaUs) {
        this.fechaUs = fechaUs;
    }

    public Date getFechaAc() {
        return fechaAc;
    }

    public void setFechaAc(Date fechaAc) {
        this.fechaAc = fechaAc;
    }

    public Date getFechaAp() {
        return fechaAp;
    }

    public void setFechaAp(Date fechaAp) {
        this.fechaAp = fechaAp;
    }

    public Date getFechaAh() {
        return fechaAh;
    }

    public void setFechaAh(Date fechaAh) {
        this.fechaAh = fechaAh;
    }

    public Date getFechaAu() {
        return fechaAu;
    }

    public void setFechaAu(Date fechaAu) {
        this.fechaAu = fechaAu;
    }

    public Date getFechaAm() {
        return fechaAm;
    }

    public void setFechaAm(Date fechaAm) {
        this.fechaAm = fechaAm;
    }

    public Date getFechaAt() {
        return fechaAt;
    }

    public void setFechaAt(Date fechaAt) {
        this.fechaAt = fechaAt;
    }

    public Date getFechaAn() {
        return fechaAn;
    }

    public void setFechaAn(Date fechaAn) {
        this.fechaAn = fechaAn;
    }

    public Date getFechaAd() {
        return fechaAd;
    }

    public void setFechaAd(Date fechaAd) {
        this.fechaAd = fechaAd;
    }

    public String getArchivoNombreOriginal() {
        return archivoNombreOriginal;
    }

    public void setArchivoNombreOriginal(String archivoNombreOriginal) {
        this.archivoNombreOriginal = archivoNombreOriginal;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
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

    public CmRipsCargas getCmRipsCargasId() {
        return cmRipsCargasId;
    }

    public void setCmRipsCargasId(CmRipsCargas cmRipsCargasId) {
        this.cmRipsCargasId = cmRipsCargasId;
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
        if (!(object instanceof CmRipsCtControl)) {
            return false;
        }
        CmRipsCtControl other = (CmRipsCtControl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsCtControl[ id=" + id + " ]";
    }
    
}
