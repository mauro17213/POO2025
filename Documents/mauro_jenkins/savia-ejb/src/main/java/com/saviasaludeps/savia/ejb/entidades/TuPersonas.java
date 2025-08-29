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
@Table(name = "tu_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TuPersonas.findAll", query = "SELECT t FROM TuPersonas t"),
    @NamedQuery(name = "TuPersonas.findById", query = "SELECT t FROM TuPersonas t WHERE t.id = :id"),
    @NamedQuery(name = "TuPersonas.findByAsegAfiliadoId", query = "SELECT t FROM TuPersonas t WHERE t.asegAfiliadoId = :asegAfiliadoId"),
    @NamedQuery(name = "TuPersonas.findByMaeEstadoAfiliadoId", query = "SELECT t FROM TuPersonas t WHERE t.maeEstadoAfiliadoId = :maeEstadoAfiliadoId"),
    @NamedQuery(name = "TuPersonas.findByMaeEstadoAfiliadoCodigo", query = "SELECT t FROM TuPersonas t WHERE t.maeEstadoAfiliadoCodigo = :maeEstadoAfiliadoCodigo"),
    @NamedQuery(name = "TuPersonas.findByMaeEstadoAfiliadoValor", query = "SELECT t FROM TuPersonas t WHERE t.maeEstadoAfiliadoValor = :maeEstadoAfiliadoValor"),
    @NamedQuery(name = "TuPersonas.findByMaeTipoDocumentoId", query = "SELECT t FROM TuPersonas t WHERE t.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "TuPersonas.findByMaeTipoDocumentoCodigo", query = "SELECT t FROM TuPersonas t WHERE t.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "TuPersonas.findByMaeTipoDocumentoValor", query = "SELECT t FROM TuPersonas t WHERE t.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "TuPersonas.findByNumeroDocumento", query = "SELECT t FROM TuPersonas t WHERE t.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "TuPersonas.findByNombres", query = "SELECT t FROM TuPersonas t WHERE t.nombres = :nombres"),
    @NamedQuery(name = "TuPersonas.findByApellidos", query = "SELECT t FROM TuPersonas t WHERE t.apellidos = :apellidos"),
    @NamedQuery(name = "TuPersonas.findByFechaNacimiento", query = "SELECT t FROM TuPersonas t WHERE t.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "TuPersonas.findByMaeGeneroId", query = "SELECT t FROM TuPersonas t WHERE t.maeGeneroId = :maeGeneroId"),
    @NamedQuery(name = "TuPersonas.findByMaeGeneroCodigo", query = "SELECT t FROM TuPersonas t WHERE t.maeGeneroCodigo = :maeGeneroCodigo"),
    @NamedQuery(name = "TuPersonas.findByMaeGeneroValor", query = "SELECT t FROM TuPersonas t WHERE t.maeGeneroValor = :maeGeneroValor"),
    @NamedQuery(name = "TuPersonas.findByAgenteOficioso", query = "SELECT t FROM TuPersonas t WHERE t.agenteOficioso = :agenteOficioso"),
    @NamedQuery(name = "TuPersonas.findByCorreoElectronico", query = "SELECT t FROM TuPersonas t WHERE t.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "TuPersonas.findByDireccionResidencia", query = "SELECT t FROM TuPersonas t WHERE t.direccionResidencia = :direccionResidencia"),
    @NamedQuery(name = "TuPersonas.findByContratoAfiliacion", query = "SELECT t FROM TuPersonas t WHERE t.contratoAfiliacion = :contratoAfiliacion"),
    @NamedQuery(name = "TuPersonas.findByUsuarioCrea", query = "SELECT t FROM TuPersonas t WHERE t.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "TuPersonas.findByTerminalCrea", query = "SELECT t FROM TuPersonas t WHERE t.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "TuPersonas.findByFechaHoraCrea", query = "SELECT t FROM TuPersonas t WHERE t.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "TuPersonas.findByUsuarioModifica", query = "SELECT t FROM TuPersonas t WHERE t.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "TuPersonas.findByTerminalModifica", query = "SELECT t FROM TuPersonas t WHERE t.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "TuPersonas.findByFechaHoraModifica", query = "SELECT t FROM TuPersonas t WHERE t.fechaHoraModifica = :fechaHoraModifica")})
public class TuPersonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "aseg_afiliado_id")
    private Integer asegAfiliadoId;
    @Column(name = "mae_estado_afiliado_id")
    private Integer maeEstadoAfiliadoId;
    @Size(max = 8)
    @Column(name = "mae_estado_afiliado_codigo")
    private String maeEstadoAfiliadoCodigo;
    @Size(max = 64)
    @Column(name = "mae_estado_afiliado_valor")
    private String maeEstadoAfiliadoValor;
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
    @Size(min = 1, max = 128)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_genero_id")
    private int maeGeneroId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_genero_codigo")
    private String maeGeneroCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "mae_genero_valor")
    private String maeGeneroValor;
    @Size(max = 256)
    @Column(name = "agente_oficioso")
    private String agenteOficioso;
    @Size(max = 256)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "direccion_residencia")
    private String direccionResidencia;
    @Size(max = 32)
    @Column(name = "contrato_afiliacion")
    private String contratoAfiliacion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuPersonasId", fetch = FetchType.LAZY)
    private List<TuTutelaItems> tuTutelaItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuPersonasId", fetch = FetchType.LAZY)
    private List<TuTutelas> tuTutelasList;
    @JoinColumn(name = "ubicacion_afiliacion_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones ubicacionAfiliacionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tuPersonasId", fetch = FetchType.LAZY)
    private List<TuPersonasContactos> tuPersonasContactosList;

    public TuPersonas() {
    }

    public TuPersonas(Integer id) {
        this.id = id;
    }

    public TuPersonas(Integer id, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, String nombres, String apellidos, Date fechaNacimiento, int maeGeneroId, String maeGeneroCodigo, String maeGeneroValor, String direccionResidencia, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.maeGeneroId = maeGeneroId;
        this.maeGeneroCodigo = maeGeneroCodigo;
        this.maeGeneroValor = maeGeneroValor;
        this.direccionResidencia = direccionResidencia;
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

    public Integer getAsegAfiliadoId() {
        return asegAfiliadoId;
    }

    public void setAsegAfiliadoId(Integer asegAfiliadoId) {
        this.asegAfiliadoId = asegAfiliadoId;
    }

    public Integer getMaeEstadoAfiliadoId() {
        return maeEstadoAfiliadoId;
    }

    public void setMaeEstadoAfiliadoId(Integer maeEstadoAfiliadoId) {
        this.maeEstadoAfiliadoId = maeEstadoAfiliadoId;
    }

    public String getMaeEstadoAfiliadoCodigo() {
        return maeEstadoAfiliadoCodigo;
    }

    public void setMaeEstadoAfiliadoCodigo(String maeEstadoAfiliadoCodigo) {
        this.maeEstadoAfiliadoCodigo = maeEstadoAfiliadoCodigo;
    }

    public String getMaeEstadoAfiliadoValor() {
        return maeEstadoAfiliadoValor;
    }

    public void setMaeEstadoAfiliadoValor(String maeEstadoAfiliadoValor) {
        this.maeEstadoAfiliadoValor = maeEstadoAfiliadoValor;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getMaeGeneroId() {
        return maeGeneroId;
    }

    public void setMaeGeneroId(int maeGeneroId) {
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

    public String getAgenteOficioso() {
        return agenteOficioso;
    }

    public void setAgenteOficioso(String agenteOficioso) {
        this.agenteOficioso = agenteOficioso;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getContratoAfiliacion() {
        return contratoAfiliacion;
    }

    public void setContratoAfiliacion(String contratoAfiliacion) {
        this.contratoAfiliacion = contratoAfiliacion;
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

    @XmlTransient
    public List<TuTutelaItems> getTuTutelaItemsList() {
        return tuTutelaItemsList;
    }

    public void setTuTutelaItemsList(List<TuTutelaItems> tuTutelaItemsList) {
        this.tuTutelaItemsList = tuTutelaItemsList;
    }

    @XmlTransient
    public List<TuTutelas> getTuTutelasList() {
        return tuTutelasList;
    }

    public void setTuTutelasList(List<TuTutelas> tuTutelasList) {
        this.tuTutelasList = tuTutelasList;
    }

    public GnUbicaciones getUbicacionAfiliacionId() {
        return ubicacionAfiliacionId;
    }

    public void setUbicacionAfiliacionId(GnUbicaciones ubicacionAfiliacionId) {
        this.ubicacionAfiliacionId = ubicacionAfiliacionId;
    }

    @XmlTransient
    public List<TuPersonasContactos> getTuPersonasContactosList() {
        return tuPersonasContactosList;
    }

    public void setTuPersonasContactosList(List<TuPersonasContactos> tuPersonasContactosList) {
        this.tuPersonasContactosList = tuPersonasContactosList;
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
        if (!(object instanceof TuPersonas)) {
            return false;
        }
        TuPersonas other = (TuPersonas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.TuPersonas[ id=" + id + " ]";
    }
    
}
