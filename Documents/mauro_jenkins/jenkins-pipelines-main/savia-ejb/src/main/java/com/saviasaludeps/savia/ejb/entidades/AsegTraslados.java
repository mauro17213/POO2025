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
@Table(name = "aseg_traslados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegTraslados.findAll", query = "SELECT a FROM AsegTraslados a"),
    @NamedQuery(name = "AsegTraslados.findById", query = "SELECT a FROM AsegTraslados a WHERE a.id = :id"),
    @NamedQuery(name = "AsegTraslados.findByCodigoEps", query = "SELECT a FROM AsegTraslados a WHERE a.codigoEps = :codigoEps"),
    @NamedQuery(name = "AsegTraslados.findByTipoDocumentoBdua", query = "SELECT a FROM AsegTraslados a WHERE a.tipoDocumentoBdua = :tipoDocumentoBdua"),
    @NamedQuery(name = "AsegTraslados.findByNumeroDocumentoBdua", query = "SELECT a FROM AsegTraslados a WHERE a.numeroDocumentoBdua = :numeroDocumentoBdua"),
    @NamedQuery(name = "AsegTraslados.findByPrimerApellidoBdua", query = "SELECT a FROM AsegTraslados a WHERE a.primerApellidoBdua = :primerApellidoBdua"),
    @NamedQuery(name = "AsegTraslados.findBySegundoApellidoBdua", query = "SELECT a FROM AsegTraslados a WHERE a.segundoApellidoBdua = :segundoApellidoBdua"),
    @NamedQuery(name = "AsegTraslados.findByPrimerNombreBdua", query = "SELECT a FROM AsegTraslados a WHERE a.primerNombreBdua = :primerNombreBdua"),
    @NamedQuery(name = "AsegTraslados.findBySegundoNombreBdua", query = "SELECT a FROM AsegTraslados a WHERE a.segundoNombreBdua = :segundoNombreBdua"),
    @NamedQuery(name = "AsegTraslados.findByFechaNacimientoBdua", query = "SELECT a FROM AsegTraslados a WHERE a.fechaNacimientoBdua = :fechaNacimientoBdua"),
    @NamedQuery(name = "AsegTraslados.findByGeneroBdua", query = "SELECT a FROM AsegTraslados a WHERE a.generoBdua = :generoBdua"),
    @NamedQuery(name = "AsegTraslados.findByFechaReporte", query = "SELECT a FROM AsegTraslados a WHERE a.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "AsegTraslados.findByUbicacionesId", query = "SELECT a FROM AsegTraslados a WHERE a.ubicacionesId = :ubicacionesId"),
    @NamedQuery(name = "AsegTraslados.findByMaeEpsOrigenId", query = "SELECT a FROM AsegTraslados a WHERE a.maeEpsOrigenId = :maeEpsOrigenId"),
    @NamedQuery(name = "AsegTraslados.findByUsuarioCrea", query = "SELECT a FROM AsegTraslados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegTraslados.findByTerminalCrea", query = "SELECT a FROM AsegTraslados a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegTraslados.findByFechaHoraCrea", query = "SELECT a FROM AsegTraslados a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegTraslados.findByUsuarioModifica", query = "SELECT a FROM AsegTraslados a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AsegTraslados.findByTerminalModifica", query = "SELECT a FROM AsegTraslados a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AsegTraslados.findByFechaHoraModifica", query = "SELECT a FROM AsegTraslados a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AsegTraslados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 8)
    @Column(name = "codigo_eps")
    private String codigoEps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "tipo_documento_bdua")
    private String tipoDocumentoBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_documento_bdua")
    private String numeroDocumentoBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_apellido_bdua")
    private String primerApellidoBdua;
    @Size(max = 64)
    @Column(name = "segundo_apellido_bdua")
    private String segundoApellidoBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_nombre_bdua")
    private String primerNombreBdua;
    @Size(max = 64)
    @Column(name = "segundo_nombre_bdua")
    private String segundoNombreBdua;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento_bdua")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoBdua;
    @Size(max = 8)
    @Column(name = "genero_bdua")
    private String generoBdua;
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;
    @Column(name = "ubicaciones_id")
    private Integer ubicacionesId;
    @Column(name = "mae_eps_origen_id")
    private Integer maeEpsOrigenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 64)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 64)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;

    public AsegTraslados() {
    }

    public AsegTraslados(Integer id) {
        this.id = id;
    }

    public AsegTraslados(Integer id, String tipoDocumentoBdua, String numeroDocumentoBdua, String primerApellidoBdua, String primerNombreBdua, Date fechaNacimientoBdua, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoDocumentoBdua = tipoDocumentoBdua;
        this.numeroDocumentoBdua = numeroDocumentoBdua;
        this.primerApellidoBdua = primerApellidoBdua;
        this.primerNombreBdua = primerNombreBdua;
        this.fechaNacimientoBdua = fechaNacimientoBdua;
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

    public String getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(String codigoEps) {
        this.codigoEps = codigoEps;
    }

    public String getTipoDocumentoBdua() {
        return tipoDocumentoBdua;
    }

    public void setTipoDocumentoBdua(String tipoDocumentoBdua) {
        this.tipoDocumentoBdua = tipoDocumentoBdua;
    }

    public String getNumeroDocumentoBdua() {
        return numeroDocumentoBdua;
    }

    public void setNumeroDocumentoBdua(String numeroDocumentoBdua) {
        this.numeroDocumentoBdua = numeroDocumentoBdua;
    }

    public String getPrimerApellidoBdua() {
        return primerApellidoBdua;
    }

    public void setPrimerApellidoBdua(String primerApellidoBdua) {
        this.primerApellidoBdua = primerApellidoBdua;
    }

    public String getSegundoApellidoBdua() {
        return segundoApellidoBdua;
    }

    public void setSegundoApellidoBdua(String segundoApellidoBdua) {
        this.segundoApellidoBdua = segundoApellidoBdua;
    }

    public String getPrimerNombreBdua() {
        return primerNombreBdua;
    }

    public void setPrimerNombreBdua(String primerNombreBdua) {
        this.primerNombreBdua = primerNombreBdua;
    }

    public String getSegundoNombreBdua() {
        return segundoNombreBdua;
    }

    public void setSegundoNombreBdua(String segundoNombreBdua) {
        this.segundoNombreBdua = segundoNombreBdua;
    }

    public Date getFechaNacimientoBdua() {
        return fechaNacimientoBdua;
    }

    public void setFechaNacimientoBdua(Date fechaNacimientoBdua) {
        this.fechaNacimientoBdua = fechaNacimientoBdua;
    }

    public String getGeneroBdua() {
        return generoBdua;
    }

    public void setGeneroBdua(String generoBdua) {
        this.generoBdua = generoBdua;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Integer getUbicacionesId() {
        return ubicacionesId;
    }

    public void setUbicacionesId(Integer ubicacionesId) {
        this.ubicacionesId = ubicacionesId;
    }

    public Integer getMaeEpsOrigenId() {
        return maeEpsOrigenId;
    }

    public void setMaeEpsOrigenId(Integer maeEpsOrigenId) {
        this.maeEpsOrigenId = maeEpsOrigenId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsegTraslados)) {
            return false;
        }
        AsegTraslados other = (AsegTraslados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegTraslados[ id=" + id + " ]";
    }
    
}
