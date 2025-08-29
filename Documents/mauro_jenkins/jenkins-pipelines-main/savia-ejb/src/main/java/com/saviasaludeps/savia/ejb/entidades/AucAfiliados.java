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
@Table(name = "auc_afiliados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucAfiliados.findAll", query = "SELECT a FROM AucAfiliados a"),
    @NamedQuery(name = "AucAfiliados.findById", query = "SELECT a FROM AucAfiliados a WHERE a.id = :id"),
    @NamedQuery(name = "AucAfiliados.findByMaeEstadoAfiliadoId", query = "SELECT a FROM AucAfiliados a WHERE a.maeEstadoAfiliadoId = :maeEstadoAfiliadoId"),
    @NamedQuery(name = "AucAfiliados.findByMaeEstadoAfiliadoCodigo", query = "SELECT a FROM AucAfiliados a WHERE a.maeEstadoAfiliadoCodigo = :maeEstadoAfiliadoCodigo"),
    @NamedQuery(name = "AucAfiliados.findByMaeEstadoAfiliadoValor", query = "SELECT a FROM AucAfiliados a WHERE a.maeEstadoAfiliadoValor = :maeEstadoAfiliadoValor"),
    @NamedQuery(name = "AucAfiliados.findByMaeTipoDocumentoId", query = "SELECT a FROM AucAfiliados a WHERE a.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "AucAfiliados.findByMaeTipoDocumentoCodigo", query = "SELECT a FROM AucAfiliados a WHERE a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "AucAfiliados.findByMaeTipoDocumentoValor", query = "SELECT a FROM AucAfiliados a WHERE a.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "AucAfiliados.findByMaeRegimenId", query = "SELECT a FROM AucAfiliados a WHERE a.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "AucAfiliados.findByMaeRegimenCodigo", query = "SELECT a FROM AucAfiliados a WHERE a.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "AucAfiliados.findByMaeRegimenValor", query = "SELECT a FROM AucAfiliados a WHERE a.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "AucAfiliados.findByNumeroDocumento", query = "SELECT a FROM AucAfiliados a WHERE a.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "AucAfiliados.findByPrimerApellido", query = "SELECT a FROM AucAfiliados a WHERE a.primerApellido = :primerApellido"),
    @NamedQuery(name = "AucAfiliados.findBySegundoApellido", query = "SELECT a FROM AucAfiliados a WHERE a.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "AucAfiliados.findByPrimerNombre", query = "SELECT a FROM AucAfiliados a WHERE a.primerNombre = :primerNombre"),
    @NamedQuery(name = "AucAfiliados.findBySegundoNombre", query = "SELECT a FROM AucAfiliados a WHERE a.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "AucAfiliados.findByFechaNacimiento", query = "SELECT a FROM AucAfiliados a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "AucAfiliados.findByMaeGeneroId", query = "SELECT a FROM AucAfiliados a WHERE a.maeGeneroId = :maeGeneroId"),
    @NamedQuery(name = "AucAfiliados.findByMaeGeneroCodigo", query = "SELECT a FROM AucAfiliados a WHERE a.maeGeneroCodigo = :maeGeneroCodigo"),
    @NamedQuery(name = "AucAfiliados.findByMaeGeneroValor", query = "SELECT a FROM AucAfiliados a WHERE a.maeGeneroValor = :maeGeneroValor"),
    @NamedQuery(name = "AucAfiliados.findByCorreoElectronico", query = "SELECT a FROM AucAfiliados a WHERE a.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "AucAfiliados.findByDireccionResidencia", query = "SELECT a FROM AucAfiliados a WHERE a.direccionResidencia = :direccionResidencia"),
    @NamedQuery(name = "AucAfiliados.findByUbicacionAfiliacionId", query = "SELECT a FROM AucAfiliados a WHERE a.ubicacionAfiliacionId = :ubicacionAfiliacionId"),
    @NamedQuery(name = "AucAfiliados.findByContratoAfiliacion", query = "SELECT a FROM AucAfiliados a WHERE a.contratoAfiliacion = :contratoAfiliacion"),
    @NamedQuery(name = "AucAfiliados.findByUsuarioCrea", query = "SELECT a FROM AucAfiliados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucAfiliados.findByTerminalCrea", query = "SELECT a FROM AucAfiliados a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucAfiliados.findByFechaHoraCrea", query = "SELECT a FROM AucAfiliados a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucAfiliados.findByUsuarioModifica", query = "SELECT a FROM AucAfiliados a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucAfiliados.findByTerminalModifica", query = "SELECT a FROM AucAfiliados a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucAfiliados.findByFechaHoraModifica", query = "SELECT a FROM AucAfiliados a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AucAfiliados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_afiliado_id")
    private int maeEstadoAfiliadoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_estado_afiliado_codigo")
    private String maeEstadoAfiliadoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
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
    @Column(name = "mae_regimen_id")
    private int maeRegimenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_regimen_codigo")
    private String maeRegimenCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 64)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 64)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Column(name = "mae_genero_id")
    private Integer maeGeneroId;
    @Size(max = 8)
    @Column(name = "mae_genero_codigo")
    private String maeGeneroCodigo;
    @Size(max = 128)
    @Column(name = "mae_genero_valor")
    private String maeGeneroValor;
    @Size(max = 256)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Size(max = 256)
    @Column(name = "direccion_residencia")
    private String direccionResidencia;
    @Column(name = "ubicacion_afiliacion_id")
    private Integer ubicacionAfiliacionId;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucAfiliadosId", fetch = FetchType.LAZY)
    private List<AucAfiliadoContactos> aucAfiliadoContactosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aucAfiliadosId", fetch = FetchType.LAZY)
    private List<AucHospitalizaciones> aucHospitalizacionesList;

    public AucAfiliados() {
    }

    public AucAfiliados(Integer id) {
        this.id = id;
    }

    public AucAfiliados(Integer id, int maeEstadoAfiliadoId, String maeEstadoAfiliadoCodigo, String maeEstadoAfiliadoValor, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, int maeRegimenId, String maeRegimenCodigo, String maeRegimenValor, String numeroDocumento, String primerApellido, String primerNombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeEstadoAfiliadoId = maeEstadoAfiliadoId;
        this.maeEstadoAfiliadoCodigo = maeEstadoAfiliadoCodigo;
        this.maeEstadoAfiliadoValor = maeEstadoAfiliadoValor;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.maeRegimenId = maeRegimenId;
        this.maeRegimenCodigo = maeRegimenCodigo;
        this.maeRegimenValor = maeRegimenValor;
        this.numeroDocumento = numeroDocumento;
        this.primerApellido = primerApellido;
        this.primerNombre = primerNombre;
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

    public int getMaeEstadoAfiliadoId() {
        return maeEstadoAfiliadoId;
    }

    public void setMaeEstadoAfiliadoId(int maeEstadoAfiliadoId) {
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

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public Integer getUbicacionAfiliacionId() {
        return ubicacionAfiliacionId;
    }

    public void setUbicacionAfiliacionId(Integer ubicacionAfiliacionId) {
        this.ubicacionAfiliacionId = ubicacionAfiliacionId;
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

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    @XmlTransient
    public List<AucAfiliadoContactos> getAucAfiliadoContactosList() {
        return aucAfiliadoContactosList;
    }

    public void setAucAfiliadoContactosList(List<AucAfiliadoContactos> aucAfiliadoContactosList) {
        this.aucAfiliadoContactosList = aucAfiliadoContactosList;
    }

    @XmlTransient
    public List<AucHospitalizaciones> getAucHospitalizacionesList() {
        return aucHospitalizacionesList;
    }

    public void setAucHospitalizacionesList(List<AucHospitalizaciones> aucHospitalizacionesList) {
        this.aucHospitalizacionesList = aucHospitalizacionesList;
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
        if (!(object instanceof AucAfiliados)) {
            return false;
        }
        AucAfiliados other = (AucAfiliados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucAfiliados[ id=" + id + " ]";
    }
    
}
