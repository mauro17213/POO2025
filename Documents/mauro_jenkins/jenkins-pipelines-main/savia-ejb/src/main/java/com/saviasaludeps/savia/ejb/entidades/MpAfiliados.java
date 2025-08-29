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
@Table(name = "mp_afiliados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpAfiliados.findAll", query = "SELECT m FROM MpAfiliados m"),
    @NamedQuery(name = "MpAfiliados.findById", query = "SELECT m FROM MpAfiliados m WHERE m.id = :id"),
    @NamedQuery(name = "MpAfiliados.findByMaeTipoDocumentoId", query = "SELECT m FROM MpAfiliados m WHERE m.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "MpAfiliados.findByMaeTipoDocumentoCodigo", query = "SELECT m FROM MpAfiliados m WHERE m.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "MpAfiliados.findByMaeTipoDocumentoValor", query = "SELECT m FROM MpAfiliados m WHERE m.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "MpAfiliados.findByNumeroDocumento", query = "SELECT m FROM MpAfiliados m WHERE m.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "MpAfiliados.findByPrimerNombre", query = "SELECT m FROM MpAfiliados m WHERE m.primerNombre = :primerNombre"),
    @NamedQuery(name = "MpAfiliados.findBySegundoNombre", query = "SELECT m FROM MpAfiliados m WHERE m.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "MpAfiliados.findByPrimerApellido", query = "SELECT m FROM MpAfiliados m WHERE m.primerApellido = :primerApellido"),
    @NamedQuery(name = "MpAfiliados.findBySegundoApellido", query = "SELECT m FROM MpAfiliados m WHERE m.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "MpAfiliados.findByFechaNacimiento", query = "SELECT m FROM MpAfiliados m WHERE m.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "MpAfiliados.findByMaeGeneroId", query = "SELECT m FROM MpAfiliados m WHERE m.maeGeneroId = :maeGeneroId"),
    @NamedQuery(name = "MpAfiliados.findByMaeGeneroCodigo", query = "SELECT m FROM MpAfiliados m WHERE m.maeGeneroCodigo = :maeGeneroCodigo"),
    @NamedQuery(name = "MpAfiliados.findByMaeGeneroValor", query = "SELECT m FROM MpAfiliados m WHERE m.maeGeneroValor = :maeGeneroValor"),
    @NamedQuery(name = "MpAfiliados.findByMaeEstadoAfiliacionId", query = "SELECT m FROM MpAfiliados m WHERE m.maeEstadoAfiliacionId = :maeEstadoAfiliacionId"),
    @NamedQuery(name = "MpAfiliados.findByMaeEstadoAfiliacionCodigo", query = "SELECT m FROM MpAfiliados m WHERE m.maeEstadoAfiliacionCodigo = :maeEstadoAfiliacionCodigo"),
    @NamedQuery(name = "MpAfiliados.findByMaeEstadoAfiliacionValor", query = "SELECT m FROM MpAfiliados m WHERE m.maeEstadoAfiliacionValor = :maeEstadoAfiliacionValor"),
    @NamedQuery(name = "MpAfiliados.findByUsuarioCrea", query = "SELECT m FROM MpAfiliados m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpAfiliados.findByTerminalCrea", query = "SELECT m FROM MpAfiliados m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpAfiliados.findByFechaHoraCrea", query = "SELECT m FROM MpAfiliados m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpAfiliados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_documento_id")
    private int maeTipoDocumentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 64)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 64)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "mae_genero_id")
    private Integer maeGeneroId;
    @Size(max = 8)
    @Column(name = "mae_genero_codigo")
    private String maeGeneroCodigo;
    @Size(max = 128)
    @Column(name = "mae_genero_valor")
    private String maeGeneroValor;
    @Column(name = "mae_estado_afiliacion_id")
    private Integer maeEstadoAfiliacionId;
    @Size(max = 8)
    @Column(name = "mae_estado_afiliacion_codigo")
    private String maeEstadoAfiliacionCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_afiliacion_valor")
    private String maeEstadoAfiliacionValor;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpAfiliadosId", fetch = FetchType.LAZY)
    private List<MpPrescripciones> mpPrescripcionesList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;

    public MpAfiliados() {
    }

    public MpAfiliados(Integer id) {
        this.id = id;
    }

    public MpAfiliados(Integer id, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, String primerNombre, String primerApellido, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
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

    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getMaeGeneroId() {
        return maeGeneroId;
    }

    public void setMaeGeneroId(Integer maeGeneroId) {
        this.maeGeneroId = maeGeneroId;
    }

    public String getMaeGeneroCodigo() {
        return maeGeneroCodigo;
    }

    public void setMaeGeneroCodigo(String maeGeneroCodigo) {
        this.maeGeneroCodigo = maeGeneroCodigo;
    }

    public String getMaeGeneroValor() {
        return maeGeneroValor;
    }

    public void setMaeGeneroValor(String maeGeneroValor) {
        this.maeGeneroValor = maeGeneroValor;
    }

    public Integer getMaeEstadoAfiliacionId() {
        return maeEstadoAfiliacionId;
    }

    public void setMaeEstadoAfiliacionId(Integer maeEstadoAfiliacionId) {
        this.maeEstadoAfiliacionId = maeEstadoAfiliacionId;
    }

    public String getMaeEstadoAfiliacionCodigo() {
        return maeEstadoAfiliacionCodigo;
    }

    public void setMaeEstadoAfiliacionCodigo(String maeEstadoAfiliacionCodigo) {
        this.maeEstadoAfiliacionCodigo = maeEstadoAfiliacionCodigo;
    }

    public String getMaeEstadoAfiliacionValor() {
        return maeEstadoAfiliacionValor;
    }

    public void setMaeEstadoAfiliacionValor(String maeEstadoAfiliacionValor) {
        this.maeEstadoAfiliacionValor = maeEstadoAfiliacionValor;
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

    @XmlTransient
    public List<MpPrescripciones> getMpPrescripcionesList() {
        return mpPrescripcionesList;
    }

    public void setMpPrescripcionesList(List<MpPrescripciones> mpPrescripcionesList) {
        this.mpPrescripcionesList = mpPrescripcionesList;
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
        if (!(object instanceof MpAfiliados)) {
            return false;
        }
        MpAfiliados other = (MpAfiliados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpAfiliados[ id=" + id + " ]";
    }
    
}
