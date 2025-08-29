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
@Table(name = "au_seguimiento_afiliados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuSeguimientoAfiliados.findAll", query = "SELECT a FROM AuSeguimientoAfiliados a"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findById", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.id = :id"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeEstadoAfiliadoId", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeEstadoAfiliadoId = :maeEstadoAfiliadoId"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeEstadoAfiliadoCodigo", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeEstadoAfiliadoCodigo = :maeEstadoAfiliadoCodigo"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeEstadoAfiliadoValor", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeEstadoAfiliadoValor = :maeEstadoAfiliadoValor"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeTipoDocumentoId", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeTipoDocumentoCodigo", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeTipoDocumentoValor", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeRegimenId", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeRegimenCodigo", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeRegimenCodigo = :maeRegimenCodigo"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeRegimenValor", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByNumeroDocumento", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByPrimerApellido", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.primerApellido = :primerApellido"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findBySegundoApellido", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByPrimerNombre", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.primerNombre = :primerNombre"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findBySegundoNombre", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByFechaNacimiento", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeGeneroId", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeGeneroId = :maeGeneroId"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeGeneroCodigo", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeGeneroCodigo = :maeGeneroCodigo"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaeGeneroValor", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maeGeneroValor = :maeGeneroValor"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByCorreoElectronico", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByContratoAfiliacion", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.contratoAfiliacion = :contratoAfiliacion"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByTipoTecnologia", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaTecnologiaId", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaTecnologiaCodigo", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByMaTecnologiaValor", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByDireccionResidencia", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.direccionResidencia = :direccionResidencia"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByBarrioResidencia", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.barrioResidencia = :barrioResidencia"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByEnergiaPrepagada", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.energiaPrepagada = :energiaPrepagada"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByBorrado", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByUsuarioCrea", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByTerminalCrea", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByFechaHoraCrea", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByUsuarioModifica", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByTerminalModifica", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByFechaHoraModifica", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByUsuarioBorra", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByTerminalBorra", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AuSeguimientoAfiliados.findByFechaHoraBorra", query = "SELECT a FROM AuSeguimientoAfiliados a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AuSeguimientoAfiliados implements Serializable {

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
    @Size(min = 1, max = 128)
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
    @Basic(optional = false)
    @NotNull
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
    @Size(max = 256)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Size(max = 32)
    @Column(name = "contrato_afiliacion")
    private String contratoAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_tecnologia_id")
    private int maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Size(max = 512)
    @Column(name = "direccion_residencia")
    private String direccionResidencia;
    @Size(max = 64)
    @Column(name = "barrio_residencia")
    private String barrioResidencia;
    @Column(name = "energia_prepagada")
    private Boolean energiaPrepagada;
    @Column(name = "borrado")
    private Boolean borrado;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @OneToMany(mappedBy = "auSeguimientoAfiliadoId", fetch = FetchType.LAZY)
    private List<AuSolicitudAdjuntos> auSolicitudAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auSeguimientoAfiliadosId", fetch = FetchType.LAZY)
    private List<AuSeguimientos> auSeguimientosList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "gn_residencia_ubicacion_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones gnResidenciaUbicacionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auSeguimientoAfiliadoId", fetch = FetchType.LAZY)
    private List<AuSeguimientoAfiliadoContactos> auSeguimientoAfiliadoContactosList;

    public AuSeguimientoAfiliados() {
    }

    public AuSeguimientoAfiliados(Integer id) {
        this.id = id;
    }

    public AuSeguimientoAfiliados(Integer id, int maeEstadoAfiliadoId, String maeEstadoAfiliadoCodigo, String maeEstadoAfiliadoValor, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, int maeRegimenId, String maeRegimenCodigo, String maeRegimenValor, String numeroDocumento, String primerApellido, String primerNombre, Date fechaNacimiento, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
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
        this.fechaNacimiento = fechaNacimiento;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
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

    public String getContratoAfiliacion() {
        return contratoAfiliacion;
    }

    public void setContratoAfiliacion(String contratoAfiliacion) {
        this.contratoAfiliacion = contratoAfiliacion;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getBarrioResidencia() {
        return barrioResidencia;
    }

    public void setBarrioResidencia(String barrioResidencia) {
        this.barrioResidencia = barrioResidencia;
    }

    public Boolean getEnergiaPrepagada() {
        return energiaPrepagada;
    }

    public void setEnergiaPrepagada(Boolean energiaPrepagada) {
        this.energiaPrepagada = energiaPrepagada;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    @XmlTransient
    public List<AuSolicitudAdjuntos> getAuSolicitudAdjuntosList() {
        return auSolicitudAdjuntosList;
    }

    public void setAuSolicitudAdjuntosList(List<AuSolicitudAdjuntos> auSolicitudAdjuntosList) {
        this.auSolicitudAdjuntosList = auSolicitudAdjuntosList;
    }

    @XmlTransient
    public List<AuSeguimientos> getAuSeguimientosList() {
        return auSeguimientosList;
    }

    public void setAuSeguimientosList(List<AuSeguimientos> auSeguimientosList) {
        this.auSeguimientosList = auSeguimientosList;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public GnUbicaciones getGnResidenciaUbicacionId() {
        return gnResidenciaUbicacionId;
    }

    public void setGnResidenciaUbicacionId(GnUbicaciones gnResidenciaUbicacionId) {
        this.gnResidenciaUbicacionId = gnResidenciaUbicacionId;
    }

    @XmlTransient
    public List<AuSeguimientoAfiliadoContactos> getAuSeguimientoAfiliadoContactosList() {
        return auSeguimientoAfiliadoContactosList;
    }

    public void setAuSeguimientoAfiliadoContactosList(List<AuSeguimientoAfiliadoContactos> auSeguimientoAfiliadoContactosList) {
        this.auSeguimientoAfiliadoContactosList = auSeguimientoAfiliadoContactosList;
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
        if (!(object instanceof AuSeguimientoAfiliados)) {
            return false;
        }
        AuSeguimientoAfiliados other = (AuSeguimientoAfiliados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuSeguimientoAfiliados[ id=" + id + " ]";
    }
    
}
