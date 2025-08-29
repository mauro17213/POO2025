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
import javax.persistence.Lob;
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
@Table(name = "aseg_registro_novedades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegRegistroNovedades.findAll", query = "SELECT a FROM AsegRegistroNovedades a"),
    @NamedQuery(name = "AsegRegistroNovedades.findById", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.id = :id"),
    @NamedQuery(name = "AsegRegistroNovedades.findByIdAfiliado", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.idAfiliado = :idAfiliado"),
    @NamedQuery(name = "AsegRegistroNovedades.findByFechaNovedad", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.fechaNovedad = :fechaNovedad"),
    @NamedQuery(name = "AsegRegistroNovedades.findByValorAnterior", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.valorAnterior = :valorAnterior"),
    @NamedQuery(name = "AsegRegistroNovedades.findByDescripcionValorAnterior", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.descripcionValorAnterior = :descripcionValorAnterior"),
    @NamedQuery(name = "AsegRegistroNovedades.findByValorNuevo", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.valorNuevo = :valorNuevo"),
    @NamedQuery(name = "AsegRegistroNovedades.findByDescripcionValorNuevo", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.descripcionValorNuevo = :descripcionValorNuevo"),
    @NamedQuery(name = "AsegRegistroNovedades.findByFechaMarcacion", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.fechaMarcacion = :fechaMarcacion"),
    @NamedQuery(name = "AsegRegistroNovedades.findByAsegInformesIdMarcacion", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.asegInformesIdMarcacion = :asegInformesIdMarcacion"),
    @NamedQuery(name = "AsegRegistroNovedades.findByMaeEstadoNovedad", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.maeEstadoNovedad = :maeEstadoNovedad"),
    @NamedQuery(name = "AsegRegistroNovedades.findBySincronizado", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.sincronizado = :sincronizado"),
    @NamedQuery(name = "AsegRegistroNovedades.findByOrigenUltimoRegistro", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.origenUltimoRegistro = :origenUltimoRegistro"),
    @NamedQuery(name = "AsegRegistroNovedades.findByDescripcionOrigenUltimoRegistro", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.descripcionOrigenUltimoRegistro = :descripcionOrigenUltimoRegistro"),
    @NamedQuery(name = "AsegRegistroNovedades.findByUsuarioCrea", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegRegistroNovedades.findByTerminalCrea", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegRegistroNovedades.findByFechaHoraCrea", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegRegistroNovedades.findByUsuarioModifica", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AsegRegistroNovedades.findByTerminalModifica", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AsegRegistroNovedades.findByFechaHoraModifica", query = "SELECT a FROM AsegRegistroNovedades a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AsegRegistroNovedades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_afiliado")
    private int idAfiliado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_novedad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNovedad;
    @Size(max = 512)
    @Column(name = "valor_anterior")
    private String valorAnterior;
    @Size(max = 512)
    @Column(name = "descripcion_valor_anterior")
    private String descripcionValorAnterior;
    @Size(max = 512)
    @Column(name = "valor_nuevo")
    private String valorNuevo;
    @Size(max = 512)
    @Column(name = "descripcion_valor_nuevo")
    private String descripcionValorNuevo;
    @Column(name = "fecha_marcacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMarcacion;
    @Column(name = "aseg_informes_id_marcacion")
    private Integer asegInformesIdMarcacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_estado_novedad")
    private String maeEstadoNovedad;
    @Lob
    @Size(max = 65535)
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "sincronizado")
    private Integer sincronizado;
    @Column(name = "origen_ultimo_registro")
    private Integer origenUltimoRegistro;
    @Size(max = 128)
    @Column(name = "descripcion_origen_ultimo_registro")
    private String descripcionOrigenUltimoRegistro;
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
    @JoinColumn(name = "radicado_novedades_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegRadicadoNovedades radicadoNovedadesId;
    @JoinColumn(name = "aseg_ma_novedades_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegMaNovedades asegMaNovedadesId;
    @JoinColumn(name = "aseg_reporte_novedades_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsegReporteNovedades asegReporteNovedadesId;

    public AsegRegistroNovedades() {
    }

    public AsegRegistroNovedades(Integer id) {
        this.id = id;
    }

    public AsegRegistroNovedades(Integer id, int idAfiliado, Date fechaNovedad, String maeEstadoNovedad, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.idAfiliado = idAfiliado;
        this.fechaNovedad = fechaNovedad;
        this.maeEstadoNovedad = maeEstadoNovedad;
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

    public int getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(int idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getDescripcionValorAnterior() {
        return descripcionValorAnterior;
    }

    public void setDescripcionValorAnterior(String descripcionValorAnterior) {
        this.descripcionValorAnterior = descripcionValorAnterior;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public String getDescripcionValorNuevo() {
        return descripcionValorNuevo;
    }

    public void setDescripcionValorNuevo(String descripcionValorNuevo) {
        this.descripcionValorNuevo = descripcionValorNuevo;
    }

    public Date getFechaMarcacion() {
        return fechaMarcacion;
    }

    public void setFechaMarcacion(Date fechaMarcacion) {
        this.fechaMarcacion = fechaMarcacion;
    }

    public Integer getAsegInformesIdMarcacion() {
        return asegInformesIdMarcacion;
    }

    public void setAsegInformesIdMarcacion(Integer asegInformesIdMarcacion) {
        this.asegInformesIdMarcacion = asegInformesIdMarcacion;
    }

    public String getMaeEstadoNovedad() {
        return maeEstadoNovedad;
    }

    public void setMaeEstadoNovedad(String maeEstadoNovedad) {
        this.maeEstadoNovedad = maeEstadoNovedad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Integer sincronizado) {
        this.sincronizado = sincronizado;
    }

    public Integer getOrigenUltimoRegistro() {
        return origenUltimoRegistro;
    }

    public void setOrigenUltimoRegistro(Integer origenUltimoRegistro) {
        this.origenUltimoRegistro = origenUltimoRegistro;
    }

    public String getDescripcionOrigenUltimoRegistro() {
        return descripcionOrigenUltimoRegistro;
    }

    public void setDescripcionOrigenUltimoRegistro(String descripcionOrigenUltimoRegistro) {
        this.descripcionOrigenUltimoRegistro = descripcionOrigenUltimoRegistro;
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

    public AsegRadicadoNovedades getRadicadoNovedadesId() {
        return radicadoNovedadesId;
    }

    public void setRadicadoNovedadesId(AsegRadicadoNovedades radicadoNovedadesId) {
        this.radicadoNovedadesId = radicadoNovedadesId;
    }

    public AsegMaNovedades getAsegMaNovedadesId() {
        return asegMaNovedadesId;
    }

    public void setAsegMaNovedadesId(AsegMaNovedades asegMaNovedadesId) {
        this.asegMaNovedadesId = asegMaNovedadesId;
    }

    public AsegReporteNovedades getAsegReporteNovedadesId() {
        return asegReporteNovedadesId;
    }

    public void setAsegReporteNovedadesId(AsegReporteNovedades asegReporteNovedadesId) {
        this.asegReporteNovedadesId = asegReporteNovedadesId;
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
        if (!(object instanceof AsegRegistroNovedades)) {
            return false;
        }
        AsegRegistroNovedades other = (AsegRegistroNovedades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegRegistroNovedades[ id=" + id + " ]";
    }
    
}
