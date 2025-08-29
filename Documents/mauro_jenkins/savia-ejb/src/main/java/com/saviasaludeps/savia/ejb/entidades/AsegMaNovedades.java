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
@Table(name = "aseg_ma_novedades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegMaNovedades.findAll", query = "SELECT a FROM AsegMaNovedades a"),
    @NamedQuery(name = "AsegMaNovedades.findById", query = "SELECT a FROM AsegMaNovedades a WHERE a.id = :id"),
    @NamedQuery(name = "AsegMaNovedades.findByCodigoNovedad", query = "SELECT a FROM AsegMaNovedades a WHERE a.codigoNovedad = :codigoNovedad"),
    @NamedQuery(name = "AsegMaNovedades.findByDescripcionNovedad", query = "SELECT a FROM AsegMaNovedades a WHERE a.descripcionNovedad = :descripcionNovedad"),
    @NamedQuery(name = "AsegMaNovedades.findByRegimen", query = "SELECT a FROM AsegMaNovedades a WHERE a.regimen = :regimen"),
    @NamedQuery(name = "AsegMaNovedades.findByActivo", query = "SELECT a FROM AsegMaNovedades a WHERE a.activo = :activo"),
    @NamedQuery(name = "AsegMaNovedades.findByCodigoNovedadPadre", query = "SELECT a FROM AsegMaNovedades a WHERE a.codigoNovedadPadre = :codigoNovedadPadre"),
    @NamedQuery(name = "AsegMaNovedades.findByReporteNormativo", query = "SELECT a FROM AsegMaNovedades a WHERE a.reporteNormativo = :reporteNormativo"),
    @NamedQuery(name = "AsegMaNovedades.findByCodigoCausaNovedad", query = "SELECT a FROM AsegMaNovedades a WHERE a.codigoCausaNovedad = :codigoCausaNovedad"),
    @NamedQuery(name = "AsegMaNovedades.findBySincronizacionSomosMas", query = "SELECT a FROM AsegMaNovedades a WHERE a.sincronizacionSomosMas = :sincronizacionSomosMas"),
    @NamedQuery(name = "AsegMaNovedades.findByUsuarioCrea", query = "SELECT a FROM AsegMaNovedades a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegMaNovedades.findByFechaHoraCrea", query = "SELECT a FROM AsegMaNovedades a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegMaNovedades.findByTerminalCrea", query = "SELECT a FROM AsegMaNovedades a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegMaNovedades.findByUsuarioModifica", query = "SELECT a FROM AsegMaNovedades a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AsegMaNovedades.findByFechaHoraModifica", query = "SELECT a FROM AsegMaNovedades a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AsegMaNovedades.findByTerminalMofica", query = "SELECT a FROM AsegMaNovedades a WHERE a.terminalMofica = :terminalMofica")})
public class AsegMaNovedades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo_novedad")
    private String codigoNovedad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion_novedad")
    private String descripcionNovedad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "regimen")
    private int regimen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 8)
    @Column(name = "codigo_novedad_padre")
    private String codigoNovedadPadre;
    @Column(name = "reporte_normativo")
    private Boolean reporteNormativo;
    @Column(name = "codigo_causa_novedad")
    private Integer codigoCausaNovedad;
    @Column(name = "sincronizacion_somos_mas")
    private Boolean sincronizacionSomosMas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Size(max = 64)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @Size(max = 64)
    @Column(name = "terminal_mofica")
    private String terminalMofica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegMaNovedadesId", fetch = FetchType.LAZY)
    private List<AsegRegistroNovedades> asegRegistroNovedadesList;

    public AsegMaNovedades() {
    }

    public AsegMaNovedades(Integer id) {
        this.id = id;
    }

    public AsegMaNovedades(Integer id, String codigoNovedad, String descripcionNovedad, int regimen, boolean activo, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.codigoNovedad = codigoNovedad;
        this.descripcionNovedad = descripcionNovedad;
        this.regimen = regimen;
        this.activo = activo;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.terminalCrea = terminalCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoNovedad() {
        return codigoNovedad;
    }

    public void setCodigoNovedad(String codigoNovedad) {
        this.codigoNovedad = codigoNovedad;
    }

    public String getDescripcionNovedad() {
        return descripcionNovedad;
    }

    public void setDescripcionNovedad(String descripcionNovedad) {
        this.descripcionNovedad = descripcionNovedad;
    }

    public int getRegimen() {
        return regimen;
    }

    public void setRegimen(int regimen) {
        this.regimen = regimen;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCodigoNovedadPadre() {
        return codigoNovedadPadre;
    }

    public void setCodigoNovedadPadre(String codigoNovedadPadre) {
        this.codigoNovedadPadre = codigoNovedadPadre;
    }

    public Boolean getReporteNormativo() {
        return reporteNormativo;
    }

    public void setReporteNormativo(Boolean reporteNormativo) {
        this.reporteNormativo = reporteNormativo;
    }

    public Integer getCodigoCausaNovedad() {
        return codigoCausaNovedad;
    }

    public void setCodigoCausaNovedad(Integer codigoCausaNovedad) {
        this.codigoCausaNovedad = codigoCausaNovedad;
    }

    public Boolean getSincronizacionSomosMas() {
        return sincronizacionSomosMas;
    }

    public void setSincronizacionSomosMas(Boolean sincronizacionSomosMas) {
        this.sincronizacionSomosMas = sincronizacionSomosMas;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public String getTerminalMofica() {
        return terminalMofica;
    }

    public void setTerminalMofica(String terminalMofica) {
        this.terminalMofica = terminalMofica;
    }

    @XmlTransient
    public List<AsegRegistroNovedades> getAsegRegistroNovedadesList() {
        return asegRegistroNovedadesList;
    }

    public void setAsegRegistroNovedadesList(List<AsegRegistroNovedades> asegRegistroNovedadesList) {
        this.asegRegistroNovedadesList = asegRegistroNovedadesList;
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
        if (!(object instanceof AsegMaNovedades)) {
            return false;
        }
        AsegMaNovedades other = (AsegMaNovedades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegMaNovedades[ id=" + id + " ]";
    }
    
}
