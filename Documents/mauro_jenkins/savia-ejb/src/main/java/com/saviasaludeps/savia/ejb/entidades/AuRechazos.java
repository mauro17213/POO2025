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
@Table(name = "au_rechazos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuRechazos.findAll", query = "SELECT a FROM AuRechazos a"),
    @NamedQuery(name = "AuRechazos.findById", query = "SELECT a FROM AuRechazos a WHERE a.id = :id"),
    @NamedQuery(name = "AuRechazos.findByMaeCausaRechazoId", query = "SELECT a FROM AuRechazos a WHERE a.maeCausaRechazoId = :maeCausaRechazoId"),
    @NamedQuery(name = "AuRechazos.findByMaeCausaRechazoCodigo", query = "SELECT a FROM AuRechazos a WHERE a.maeCausaRechazoCodigo = :maeCausaRechazoCodigo"),
    @NamedQuery(name = "AuRechazos.findByMaeCausaRechazoValor", query = "SELECT a FROM AuRechazos a WHERE a.maeCausaRechazoValor = :maeCausaRechazoValor"),
    @NamedQuery(name = "AuRechazos.findByJustificacion", query = "SELECT a FROM AuRechazos a WHERE a.justificacion = :justificacion"),
    @NamedQuery(name = "AuRechazos.findByRuta", query = "SELECT a FROM AuRechazos a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AuRechazos.findByArchivo", query = "SELECT a FROM AuRechazos a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AuRechazos.findByAlternativa", query = "SELECT a FROM AuRechazos a WHERE a.alternativa = :alternativa"),
    @NamedQuery(name = "AuRechazos.findByUsuarioCrea", query = "SELECT a FROM AuRechazos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuRechazos.findByTerminalCrea", query = "SELECT a FROM AuRechazos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuRechazos.findByFechaHoraCrea", query = "SELECT a FROM AuRechazos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuRechazos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_causa_rechazo_id")
    private Integer maeCausaRechazoId;
    @Size(max = 8)
    @Column(name = "mae_causa_rechazo_codigo")
    private String maeCausaRechazoCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_rechazo_valor")
    private String maeCausaRechazoValor;
    @Size(max = 1024)
    @Column(name = "justificacion")
    private String justificacion;
    @Size(max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Size(max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 1024)
    @Column(name = "alternativa")
    private String alternativa;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auRechazosId", fetch = FetchType.LAZY)
    private List<AuRechazoItems> auRechazoItemsList;

    public AuRechazos() {
    }

    public AuRechazos(Integer id) {
        this.id = id;
    }

    public AuRechazos(Integer id, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
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

    public Integer getMaeCausaRechazoId() {
        return maeCausaRechazoId;
    }

    public void setMaeCausaRechazoId(Integer maeCausaRechazoId) {
        this.maeCausaRechazoId = maeCausaRechazoId;
    }

    public String getMaeCausaRechazoCodigo() {
        return maeCausaRechazoCodigo;
    }

    public void setMaeCausaRechazoCodigo(String maeCausaRechazoCodigo) {
        this.maeCausaRechazoCodigo = maeCausaRechazoCodigo;
    }

    public String getMaeCausaRechazoValor() {
        return maeCausaRechazoValor;
    }

    public void setMaeCausaRechazoValor(String maeCausaRechazoValor) {
        this.maeCausaRechazoValor = maeCausaRechazoValor;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getAlternativa() {
        return alternativa;
    }

    public void setAlternativa(String alternativa) {
        this.alternativa = alternativa;
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

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    @XmlTransient
    public List<AuRechazoItems> getAuRechazoItemsList() {
        return auRechazoItemsList;
    }

    public void setAuRechazoItemsList(List<AuRechazoItems> auRechazoItemsList) {
        this.auRechazoItemsList = auRechazoItemsList;
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
        if (!(object instanceof AuRechazos)) {
            return false;
        }
        AuRechazos other = (AuRechazos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuRechazos[ id=" + id + " ]";
    }
    
}
