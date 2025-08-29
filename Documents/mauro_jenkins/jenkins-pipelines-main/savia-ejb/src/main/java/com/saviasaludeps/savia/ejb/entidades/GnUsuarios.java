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
@Table(name = "gn_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnUsuarios.findAll", query = "SELECT g FROM GnUsuarios g"),
    @NamedQuery(name = "GnUsuarios.findById", query = "SELECT g FROM GnUsuarios g WHERE g.id = :id"),
    @NamedQuery(name = "GnUsuarios.findByNombre", query = "SELECT g FROM GnUsuarios g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GnUsuarios.findByUsuario", query = "SELECT g FROM GnUsuarios g WHERE g.usuario = :usuario"),
    @NamedQuery(name = "GnUsuarios.findByCorreoElectronico", query = "SELECT g FROM GnUsuarios g WHERE g.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "GnUsuarios.findByContrasena", query = "SELECT g FROM GnUsuarios g WHERE g.contrasena = :contrasena"),
    @NamedQuery(name = "GnUsuarios.findByMaeTipoDocumentoId", query = "SELECT g FROM GnUsuarios g WHERE g.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "GnUsuarios.findByMaeTipoDocumentoCodigo", query = "SELECT g FROM GnUsuarios g WHERE g.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "GnUsuarios.findByMaeTipoDocumentoValor", query = "SELECT g FROM GnUsuarios g WHERE g.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "GnUsuarios.findByDocumento", query = "SELECT g FROM GnUsuarios g WHERE g.documento = :documento"),
    @NamedQuery(name = "GnUsuarios.findByMaeAreaId", query = "SELECT g FROM GnUsuarios g WHERE g.maeAreaId = :maeAreaId"),
    @NamedQuery(name = "GnUsuarios.findByMaeAreaCodigo", query = "SELECT g FROM GnUsuarios g WHERE g.maeAreaCodigo = :maeAreaCodigo"),
    @NamedQuery(name = "GnUsuarios.findByMaeAreaValor", query = "SELECT g FROM GnUsuarios g WHERE g.maeAreaValor = :maeAreaValor"),
    @NamedQuery(name = "GnUsuarios.findByMaeCargoId", query = "SELECT g FROM GnUsuarios g WHERE g.maeCargoId = :maeCargoId"),
    @NamedQuery(name = "GnUsuarios.findByMaeCargoCodigo", query = "SELECT g FROM GnUsuarios g WHERE g.maeCargoCodigo = :maeCargoCodigo"),
    @NamedQuery(name = "GnUsuarios.findByMaeCargoValor", query = "SELECT g FROM GnUsuarios g WHERE g.maeCargoValor = :maeCargoValor"),
    @NamedQuery(name = "GnUsuarios.findByTelefono", query = "SELECT g FROM GnUsuarios g WHERE g.telefono = :telefono"),
    @NamedQuery(name = "GnUsuarios.findByCelular", query = "SELECT g FROM GnUsuarios g WHERE g.celular = :celular"),
    @NamedQuery(name = "GnUsuarios.findByActivo", query = "SELECT g FROM GnUsuarios g WHERE g.activo = :activo"),
    @NamedQuery(name = "GnUsuarios.findBySesiones", query = "SELECT g FROM GnUsuarios g WHERE g.sesiones = :sesiones"),
    @NamedQuery(name = "GnUsuarios.findByFechaUltimoIngreso", query = "SELECT g FROM GnUsuarios g WHERE g.fechaUltimoIngreso = :fechaUltimoIngreso"),
    @NamedQuery(name = "GnUsuarios.findByIntentos", query = "SELECT g FROM GnUsuarios g WHERE g.intentos = :intentos"),
    @NamedQuery(name = "GnUsuarios.findByBloqueado", query = "SELECT g FROM GnUsuarios g WHERE g.bloqueado = :bloqueado"),
    @NamedQuery(name = "GnUsuarios.findByRestaurarContrasegna", query = "SELECT g FROM GnUsuarios g WHERE g.restaurarContrasegna = :restaurarContrasegna"),
    @NamedQuery(name = "GnUsuarios.findByFechaUltimaContrasegna", query = "SELECT g FROM GnUsuarios g WHERE g.fechaUltimaContrasegna = :fechaUltimaContrasegna"),
    @NamedQuery(name = "GnUsuarios.findByFechaRestaurarContrasegna", query = "SELECT g FROM GnUsuarios g WHERE g.fechaRestaurarContrasegna = :fechaRestaurarContrasegna"),
    @NamedQuery(name = "GnUsuarios.findByFechaInicio", query = "SELECT g FROM GnUsuarios g WHERE g.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "GnUsuarios.findByFechaFin", query = "SELECT g FROM GnUsuarios g WHERE g.fechaFin = :fechaFin"),
    @NamedQuery(name = "GnUsuarios.findByGnEmpresaTurnos", query = "SELECT g FROM GnUsuarios g WHERE g.gnEmpresaTurnos = :gnEmpresaTurnos"),
    @NamedQuery(name = "GnUsuarios.findByUsuarioCrea", query = "SELECT g FROM GnUsuarios g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnUsuarios.findByTerminalCrea", query = "SELECT g FROM GnUsuarios g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnUsuarios.findByFechaHoraCrea", query = "SELECT g FROM GnUsuarios g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnUsuarios.findByUsuarioModifica", query = "SELECT g FROM GnUsuarios g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GnUsuarios.findByTerminalModifica", query = "SELECT g FROM GnUsuarios g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GnUsuarios.findByFechaHoraModifica", query = "SELECT g FROM GnUsuarios g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GnUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Size(max = 16)
    @Column(name = "documento")
    private String documento;
    @Column(name = "mae_area_id")
    private Integer maeAreaId;
    @Size(max = 8)
    @Column(name = "mae_area_codigo")
    private String maeAreaCodigo;
    @Size(max = 128)
    @Column(name = "mae_area_valor")
    private String maeAreaValor;
    @Column(name = "mae_cargo_id")
    private Integer maeCargoId;
    @Size(max = 8)
    @Column(name = "mae_cargo_codigo")
    private String maeCargoCodigo;
    @Size(max = 128)
    @Column(name = "mae_cargo_valor")
    private String maeCargoValor;
    @Size(max = 16)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 16)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sesiones")
    private int sesiones;
    @Column(name = "fecha_ultimo_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intentos")
    private int intentos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bloqueado")
    private boolean bloqueado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "restaurar_contrasegna")
    private boolean restaurarContrasegna;
    @Column(name = "fecha_ultima_contrasegna")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaContrasegna;
    @Column(name = "fecha_restaurar_contrasegna")
    @Temporal(TemporalType.DATE)
    private Date fechaRestaurarContrasegna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "gn_empresa_turnos")
    private Integer gnEmpresaTurnos;
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
    @OneToMany(mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GatAtenciones> gatAtencionesList;
    @OneToMany(mappedBy = "gnUsuarioAsignadoId", fetch = FetchType.LAZY)
    private List<TuTutelaItems> tuTutelaItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<TuGrupoUsuarios> tuGrupoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<CntjExpedienteResponsables> cntjExpedienteResponsablesList;
    @OneToMany(mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<CntjEstadoEjecuciones> cntjEstadoEjecucionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GsZonaUsuarios> gsZonaUsuariosList;
    @JoinColumn(name = "au_grupos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuGrupos auGruposId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "gn_zonas_horarias_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnZonasHorarias gnZonasHorariasId;
    @OneToMany(mappedBy = "radicadorAsignado", fetch = FetchType.LAZY)
    private List<CmFeRipsCargas> cmFeRipsCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<CntjLineas> cntjLineasList;
    @OneToMany(mappedBy = "gnUsuariosAsignadoId", fetch = FetchType.LAZY)
    private List<AusCasoServicios> ausCasoServiciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<CntjEstadoUsuarios> cntjEstadoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<CmGrupoUsuarios> cmGrupoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<PeGestiones> peGestionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<AucAuditores> aucAuditoresList;
    @OneToMany(mappedBy = "gestorGnUsuarioId", fetch = FetchType.LAZY)
    private List<TuSeguimientos> tuSeguimientosList;
    @OneToMany(mappedBy = "notificadoGnUsuarioId", fetch = FetchType.LAZY)
    private List<TuSeguimientos> tuSeguimientosList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<InfGrupoUsuarios> infGrupoUsuariosList;
    @OneToMany(mappedBy = "gnUsuariosPropietarioId", fetch = FetchType.LAZY)
    private List<CntjExpedientes> cntjExpedientesList;
    @OneToMany(mappedBy = "gnUsuariosResponsableId", fetch = FetchType.LAZY)
    private List<CntjExpedientes> cntjExpedientesList1;
    @OneToMany(mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GjAbogados> gjAbogadosList;
    @OneToMany(mappedBy = "gnUsuarioCierreId", fetch = FetchType.LAZY)
    private List<AusCasos> ausCasosList;
    @OneToMany(mappedBy = "gnUsuariosResponsableId", fetch = FetchType.LAZY)
    private List<AusCasos> ausCasosList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GatTaquillaFuncionarios> gatTaquillaFuncionariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<PeUsuariosProgramas> peUsuariosProgramasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GnRolesUsuario> gnRolesUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GatTiketeLlamados> gatTiketeLlamadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GatTiempos> gatTiemposList;
    @OneToMany(mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<CntjTerceros> cntjTercerosList;
    @OneToMany(mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<AuAnexo3Items> auAnexo3ItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<CntjComiteAsistentes> cntjComiteAsistentesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<AuGrupoUsuarios> auGrupoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GatSedeFuncionarios> gatSedeFuncionariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<PeProgramas> peProgramasList;
    @OneToMany(mappedBy = "usuariosId", fetch = FetchType.LAZY)
    private List<GsSolicitudes> gsSolicitudesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GnUsuarioSesiones> gnUsuarioSesionesList;
    @OneToMany(mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<AntAnticipos> antAnticiposList;
    @OneToMany(mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<PeCargasGestiones> peCargasGestionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GnUsuarioFirmas> gnUsuarioFirmasList;
    @OneToMany(mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<PeAfiliadosProgramas> peAfiliadosProgramasList;
    @OneToMany(mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GatSedeTaquillas> gatSedeTaquillasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<CarProcesoUsuarios> carProcesoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosLiderId", fetch = FetchType.LAZY)
    private List<CmFacturas> cmFacturasList;
    @OneToMany(mappedBy = "gnUsuariosMedicoId", fetch = FetchType.LAZY)
    private List<CmFacturas> cmFacturasList1;
    @OneToMany(mappedBy = "gnUsuariosTecnicoId", fetch = FetchType.LAZY)
    private List<CmFacturas> cmFacturasList2;
    @OneToMany(mappedBy = "gnUsuariosGestionaId", fetch = FetchType.LAZY)
    private List<CmFacturas> cmFacturasList3;
    @OneToMany(mappedBy = "gnUsuarioResponsableId", fetch = FetchType.LAZY)
    private List<RcoFacturaDetalles> rcoFacturaDetallesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<CntjUsuarioGrupos> cntjUsuarioGruposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosAuditorId", fetch = FetchType.LAZY)
    private List<AucHospitalizaciones> aucHospitalizacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GnAlertas> gnAlertasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<AusGrupoUsuarios> ausGrupoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<RcoGrupoUsuarios> rcoGrupoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnUsuariosId", fetch = FetchType.LAZY)
    private List<GsGestiones> gsGestionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsableGnUsuarioId", fetch = FetchType.LAZY)
    private List<TuTutelaEstados> tuTutelaEstadosList;
    @OneToMany(mappedBy = "gestorGnUsuarioId", fetch = FetchType.LAZY)
    private List<TuTutelaEstados> tuTutelaEstadosList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abogadoGnUsuarioId", fetch = FetchType.LAZY)
    private List<TuTutelaEstados> tuTutelaEstadosList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicoGnUsuarioId", fetch = FetchType.LAZY)
    private List<TuTutelaEstados> tuTutelaEstadosList3;

    public GnUsuarios() {
    }

    public GnUsuarios(Integer id) {
        this.id = id;
    }

    public GnUsuarios(Integer id, String nombre, String usuario, String correoElectronico, String contrasena, boolean activo, int sesiones, int intentos, boolean bloqueado, boolean restaurarContrasegna, Date fechaInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.activo = activo;
        this.sesiones = sesiones;
        this.intentos = intentos;
        this.bloqueado = bloqueado;
        this.restaurarContrasegna = restaurarContrasegna;
        this.fechaInicio = fechaInicio;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getMaeAreaId() {
        return maeAreaId;
    }

    public void setMaeAreaId(Integer maeAreaId) {
        this.maeAreaId = maeAreaId;
    }

    public String getMaeAreaCodigo() {
        return maeAreaCodigo;
    }

    public void setMaeAreaCodigo(String maeAreaCodigo) {
        this.maeAreaCodigo = maeAreaCodigo;
    }

    public String getMaeAreaValor() {
        return maeAreaValor;
    }

    public void setMaeAreaValor(String maeAreaValor) {
        this.maeAreaValor = maeAreaValor;
    }

    public Integer getMaeCargoId() {
        return maeCargoId;
    }

    public void setMaeCargoId(Integer maeCargoId) {
        this.maeCargoId = maeCargoId;
    }

    public String getMaeCargoCodigo() {
        return maeCargoCodigo;
    }

    public void setMaeCargoCodigo(String maeCargoCodigo) {
        this.maeCargoCodigo = maeCargoCodigo;
    }

    public String getMaeCargoValor() {
        return maeCargoValor;
    }

    public void setMaeCargoValor(String maeCargoValor) {
        this.maeCargoValor = maeCargoValor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getSesiones() {
        return sesiones;
    }

    public void setSesiones(int sesiones) {
        this.sesiones = sesiones;
    }

    public Date getFechaUltimoIngreso() {
        return fechaUltimoIngreso;
    }

    public void setFechaUltimoIngreso(Date fechaUltimoIngreso) {
        this.fechaUltimoIngreso = fechaUltimoIngreso;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean getRestaurarContrasegna() {
        return restaurarContrasegna;
    }

    public void setRestaurarContrasegna(boolean restaurarContrasegna) {
        this.restaurarContrasegna = restaurarContrasegna;
    }

    public Date getFechaUltimaContrasegna() {
        return fechaUltimaContrasegna;
    }

    public void setFechaUltimaContrasegna(Date fechaUltimaContrasegna) {
        this.fechaUltimaContrasegna = fechaUltimaContrasegna;
    }

    public Date getFechaRestaurarContrasegna() {
        return fechaRestaurarContrasegna;
    }

    public void setFechaRestaurarContrasegna(Date fechaRestaurarContrasegna) {
        this.fechaRestaurarContrasegna = fechaRestaurarContrasegna;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getGnEmpresaTurnos() {
        return gnEmpresaTurnos;
    }

    public void setGnEmpresaTurnos(Integer gnEmpresaTurnos) {
        this.gnEmpresaTurnos = gnEmpresaTurnos;
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
    public List<GatAtenciones> getGatAtencionesList() {
        return gatAtencionesList;
    }

    public void setGatAtencionesList(List<GatAtenciones> gatAtencionesList) {
        this.gatAtencionesList = gatAtencionesList;
    }

    @XmlTransient
    public List<TuTutelaItems> getTuTutelaItemsList() {
        return tuTutelaItemsList;
    }

    public void setTuTutelaItemsList(List<TuTutelaItems> tuTutelaItemsList) {
        this.tuTutelaItemsList = tuTutelaItemsList;
    }

    @XmlTransient
    public List<TuGrupoUsuarios> getTuGrupoUsuariosList() {
        return tuGrupoUsuariosList;
    }

    public void setTuGrupoUsuariosList(List<TuGrupoUsuarios> tuGrupoUsuariosList) {
        this.tuGrupoUsuariosList = tuGrupoUsuariosList;
    }

    @XmlTransient
    public List<CntjExpedienteResponsables> getCntjExpedienteResponsablesList() {
        return cntjExpedienteResponsablesList;
    }

    public void setCntjExpedienteResponsablesList(List<CntjExpedienteResponsables> cntjExpedienteResponsablesList) {
        this.cntjExpedienteResponsablesList = cntjExpedienteResponsablesList;
    }

    @XmlTransient
    public List<CntjEstadoEjecuciones> getCntjEstadoEjecucionesList() {
        return cntjEstadoEjecucionesList;
    }

    public void setCntjEstadoEjecucionesList(List<CntjEstadoEjecuciones> cntjEstadoEjecucionesList) {
        this.cntjEstadoEjecucionesList = cntjEstadoEjecucionesList;
    }

    @XmlTransient
    public List<GsZonaUsuarios> getGsZonaUsuariosList() {
        return gsZonaUsuariosList;
    }

    public void setGsZonaUsuariosList(List<GsZonaUsuarios> gsZonaUsuariosList) {
        this.gsZonaUsuariosList = gsZonaUsuariosList;
    }

    public AuGrupos getAuGruposId() {
        return auGruposId;
    }

    public void setAuGruposId(AuGrupos auGruposId) {
        this.auGruposId = auGruposId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public GnZonasHorarias getGnZonasHorariasId() {
        return gnZonasHorariasId;
    }

    public void setGnZonasHorariasId(GnZonasHorarias gnZonasHorariasId) {
        this.gnZonasHorariasId = gnZonasHorariasId;
    }

    @XmlTransient
    public List<CmFeRipsCargas> getCmFeRipsCargasList() {
        return cmFeRipsCargasList;
    }

    public void setCmFeRipsCargasList(List<CmFeRipsCargas> cmFeRipsCargasList) {
        this.cmFeRipsCargasList = cmFeRipsCargasList;
    }

    @XmlTransient
    public List<CntjLineas> getCntjLineasList() {
        return cntjLineasList;
    }

    public void setCntjLineasList(List<CntjLineas> cntjLineasList) {
        this.cntjLineasList = cntjLineasList;
    }

    @XmlTransient
    public List<AusCasoServicios> getAusCasoServiciosList() {
        return ausCasoServiciosList;
    }

    public void setAusCasoServiciosList(List<AusCasoServicios> ausCasoServiciosList) {
        this.ausCasoServiciosList = ausCasoServiciosList;
    }

    @XmlTransient
    public List<CntjEstadoUsuarios> getCntjEstadoUsuariosList() {
        return cntjEstadoUsuariosList;
    }

    public void setCntjEstadoUsuariosList(List<CntjEstadoUsuarios> cntjEstadoUsuariosList) {
        this.cntjEstadoUsuariosList = cntjEstadoUsuariosList;
    }

    @XmlTransient
    public List<CmGrupoUsuarios> getCmGrupoUsuariosList() {
        return cmGrupoUsuariosList;
    }

    public void setCmGrupoUsuariosList(List<CmGrupoUsuarios> cmGrupoUsuariosList) {
        this.cmGrupoUsuariosList = cmGrupoUsuariosList;
    }

    @XmlTransient
    public List<PeGestiones> getPeGestionesList() {
        return peGestionesList;
    }

    public void setPeGestionesList(List<PeGestiones> peGestionesList) {
        this.peGestionesList = peGestionesList;
    }

    @XmlTransient
    public List<AucAuditores> getAucAuditoresList() {
        return aucAuditoresList;
    }

    public void setAucAuditoresList(List<AucAuditores> aucAuditoresList) {
        this.aucAuditoresList = aucAuditoresList;
    }

    @XmlTransient
    public List<TuSeguimientos> getTuSeguimientosList() {
        return tuSeguimientosList;
    }

    public void setTuSeguimientosList(List<TuSeguimientos> tuSeguimientosList) {
        this.tuSeguimientosList = tuSeguimientosList;
    }

    @XmlTransient
    public List<TuSeguimientos> getTuSeguimientosList1() {
        return tuSeguimientosList1;
    }

    public void setTuSeguimientosList1(List<TuSeguimientos> tuSeguimientosList1) {
        this.tuSeguimientosList1 = tuSeguimientosList1;
    }

    @XmlTransient
    public List<InfGrupoUsuarios> getInfGrupoUsuariosList() {
        return infGrupoUsuariosList;
    }

    public void setInfGrupoUsuariosList(List<InfGrupoUsuarios> infGrupoUsuariosList) {
        this.infGrupoUsuariosList = infGrupoUsuariosList;
    }

    @XmlTransient
    public List<CntjExpedientes> getCntjExpedientesList() {
        return cntjExpedientesList;
    }

    public void setCntjExpedientesList(List<CntjExpedientes> cntjExpedientesList) {
        this.cntjExpedientesList = cntjExpedientesList;
    }

    @XmlTransient
    public List<CntjExpedientes> getCntjExpedientesList1() {
        return cntjExpedientesList1;
    }

    public void setCntjExpedientesList1(List<CntjExpedientes> cntjExpedientesList1) {
        this.cntjExpedientesList1 = cntjExpedientesList1;
    }

    @XmlTransient
    public List<GjAbogados> getGjAbogadosList() {
        return gjAbogadosList;
    }

    public void setGjAbogadosList(List<GjAbogados> gjAbogadosList) {
        this.gjAbogadosList = gjAbogadosList;
    }

    @XmlTransient
    public List<AusCasos> getAusCasosList() {
        return ausCasosList;
    }

    public void setAusCasosList(List<AusCasos> ausCasosList) {
        this.ausCasosList = ausCasosList;
    }

    @XmlTransient
    public List<AusCasos> getAusCasosList1() {
        return ausCasosList1;
    }

    public void setAusCasosList1(List<AusCasos> ausCasosList1) {
        this.ausCasosList1 = ausCasosList1;
    }

    @XmlTransient
    public List<GatTaquillaFuncionarios> getGatTaquillaFuncionariosList() {
        return gatTaquillaFuncionariosList;
    }

    public void setGatTaquillaFuncionariosList(List<GatTaquillaFuncionarios> gatTaquillaFuncionariosList) {
        this.gatTaquillaFuncionariosList = gatTaquillaFuncionariosList;
    }

    @XmlTransient
    public List<PeUsuariosProgramas> getPeUsuariosProgramasList() {
        return peUsuariosProgramasList;
    }

    public void setPeUsuariosProgramasList(List<PeUsuariosProgramas> peUsuariosProgramasList) {
        this.peUsuariosProgramasList = peUsuariosProgramasList;
    }

    @XmlTransient
    public List<GnRolesUsuario> getGnRolesUsuarioList() {
        return gnRolesUsuarioList;
    }

    public void setGnRolesUsuarioList(List<GnRolesUsuario> gnRolesUsuarioList) {
        this.gnRolesUsuarioList = gnRolesUsuarioList;
    }

    @XmlTransient
    public List<GatTiketeLlamados> getGatTiketeLlamadosList() {
        return gatTiketeLlamadosList;
    }

    public void setGatTiketeLlamadosList(List<GatTiketeLlamados> gatTiketeLlamadosList) {
        this.gatTiketeLlamadosList = gatTiketeLlamadosList;
    }

    @XmlTransient
    public List<GatTiempos> getGatTiemposList() {
        return gatTiemposList;
    }

    public void setGatTiemposList(List<GatTiempos> gatTiemposList) {
        this.gatTiemposList = gatTiemposList;
    }

    @XmlTransient
    public List<CntjTerceros> getCntjTercerosList() {
        return cntjTercerosList;
    }

    public void setCntjTercerosList(List<CntjTerceros> cntjTercerosList) {
        this.cntjTercerosList = cntjTercerosList;
    }

    @XmlTransient
    public List<AuAnexo3Items> getAuAnexo3ItemsList() {
        return auAnexo3ItemsList;
    }

    public void setAuAnexo3ItemsList(List<AuAnexo3Items> auAnexo3ItemsList) {
        this.auAnexo3ItemsList = auAnexo3ItemsList;
    }

    @XmlTransient
    public List<CntjComiteAsistentes> getCntjComiteAsistentesList() {
        return cntjComiteAsistentesList;
    }

    public void setCntjComiteAsistentesList(List<CntjComiteAsistentes> cntjComiteAsistentesList) {
        this.cntjComiteAsistentesList = cntjComiteAsistentesList;
    }

    @XmlTransient
    public List<AuGrupoUsuarios> getAuGrupoUsuariosList() {
        return auGrupoUsuariosList;
    }

    public void setAuGrupoUsuariosList(List<AuGrupoUsuarios> auGrupoUsuariosList) {
        this.auGrupoUsuariosList = auGrupoUsuariosList;
    }

    @XmlTransient
    public List<GatSedeFuncionarios> getGatSedeFuncionariosList() {
        return gatSedeFuncionariosList;
    }

    public void setGatSedeFuncionariosList(List<GatSedeFuncionarios> gatSedeFuncionariosList) {
        this.gatSedeFuncionariosList = gatSedeFuncionariosList;
    }

    @XmlTransient
    public List<PeProgramas> getPeProgramasList() {
        return peProgramasList;
    }

    public void setPeProgramasList(List<PeProgramas> peProgramasList) {
        this.peProgramasList = peProgramasList;
    }

    @XmlTransient
    public List<GsSolicitudes> getGsSolicitudesList() {
        return gsSolicitudesList;
    }

    public void setGsSolicitudesList(List<GsSolicitudes> gsSolicitudesList) {
        this.gsSolicitudesList = gsSolicitudesList;
    }

    @XmlTransient
    public List<GnUsuarioSesiones> getGnUsuarioSesionesList() {
        return gnUsuarioSesionesList;
    }

    public void setGnUsuarioSesionesList(List<GnUsuarioSesiones> gnUsuarioSesionesList) {
        this.gnUsuarioSesionesList = gnUsuarioSesionesList;
    }

    @XmlTransient
    public List<AntAnticipos> getAntAnticiposList() {
        return antAnticiposList;
    }

    public void setAntAnticiposList(List<AntAnticipos> antAnticiposList) {
        this.antAnticiposList = antAnticiposList;
    }

    @XmlTransient
    public List<PeCargasGestiones> getPeCargasGestionesList() {
        return peCargasGestionesList;
    }

    public void setPeCargasGestionesList(List<PeCargasGestiones> peCargasGestionesList) {
        this.peCargasGestionesList = peCargasGestionesList;
    }

    @XmlTransient
    public List<GnUsuarioFirmas> getGnUsuarioFirmasList() {
        return gnUsuarioFirmasList;
    }

    public void setGnUsuarioFirmasList(List<GnUsuarioFirmas> gnUsuarioFirmasList) {
        this.gnUsuarioFirmasList = gnUsuarioFirmasList;
    }

    @XmlTransient
    public List<PeAfiliadosProgramas> getPeAfiliadosProgramasList() {
        return peAfiliadosProgramasList;
    }

    public void setPeAfiliadosProgramasList(List<PeAfiliadosProgramas> peAfiliadosProgramasList) {
        this.peAfiliadosProgramasList = peAfiliadosProgramasList;
    }

    @XmlTransient
    public List<GatSedeTaquillas> getGatSedeTaquillasList() {
        return gatSedeTaquillasList;
    }

    public void setGatSedeTaquillasList(List<GatSedeTaquillas> gatSedeTaquillasList) {
        this.gatSedeTaquillasList = gatSedeTaquillasList;
    }

    @XmlTransient
    public List<CarProcesoUsuarios> getCarProcesoUsuariosList() {
        return carProcesoUsuariosList;
    }

    public void setCarProcesoUsuariosList(List<CarProcesoUsuarios> carProcesoUsuariosList) {
        this.carProcesoUsuariosList = carProcesoUsuariosList;
    }

    @XmlTransient
    public List<CmFacturas> getCmFacturasList() {
        return cmFacturasList;
    }

    public void setCmFacturasList(List<CmFacturas> cmFacturasList) {
        this.cmFacturasList = cmFacturasList;
    }

    @XmlTransient
    public List<CmFacturas> getCmFacturasList1() {
        return cmFacturasList1;
    }

    public void setCmFacturasList1(List<CmFacturas> cmFacturasList1) {
        this.cmFacturasList1 = cmFacturasList1;
    }

    @XmlTransient
    public List<CmFacturas> getCmFacturasList2() {
        return cmFacturasList2;
    }

    public void setCmFacturasList2(List<CmFacturas> cmFacturasList2) {
        this.cmFacturasList2 = cmFacturasList2;
    }

    @XmlTransient
    public List<CmFacturas> getCmFacturasList3() {
        return cmFacturasList3;
    }

    public void setCmFacturasList3(List<CmFacturas> cmFacturasList3) {
        this.cmFacturasList3 = cmFacturasList3;
    }

    @XmlTransient
    public List<RcoFacturaDetalles> getRcoFacturaDetallesList() {
        return rcoFacturaDetallesList;
    }

    public void setRcoFacturaDetallesList(List<RcoFacturaDetalles> rcoFacturaDetallesList) {
        this.rcoFacturaDetallesList = rcoFacturaDetallesList;
    }

    @XmlTransient
    public List<CntjUsuarioGrupos> getCntjUsuarioGruposList() {
        return cntjUsuarioGruposList;
    }

    public void setCntjUsuarioGruposList(List<CntjUsuarioGrupos> cntjUsuarioGruposList) {
        this.cntjUsuarioGruposList = cntjUsuarioGruposList;
    }

    @XmlTransient
    public List<AucHospitalizaciones> getAucHospitalizacionesList() {
        return aucHospitalizacionesList;
    }

    public void setAucHospitalizacionesList(List<AucHospitalizaciones> aucHospitalizacionesList) {
        this.aucHospitalizacionesList = aucHospitalizacionesList;
    }

    @XmlTransient
    public List<GnAlertas> getGnAlertasList() {
        return gnAlertasList;
    }

    public void setGnAlertasList(List<GnAlertas> gnAlertasList) {
        this.gnAlertasList = gnAlertasList;
    }

    @XmlTransient
    public List<AusGrupoUsuarios> getAusGrupoUsuariosList() {
        return ausGrupoUsuariosList;
    }

    public void setAusGrupoUsuariosList(List<AusGrupoUsuarios> ausGrupoUsuariosList) {
        this.ausGrupoUsuariosList = ausGrupoUsuariosList;
    }

    @XmlTransient
    public List<RcoGrupoUsuarios> getRcoGrupoUsuariosList() {
        return rcoGrupoUsuariosList;
    }

    public void setRcoGrupoUsuariosList(List<RcoGrupoUsuarios> rcoGrupoUsuariosList) {
        this.rcoGrupoUsuariosList = rcoGrupoUsuariosList;
    }

    @XmlTransient
    public List<GsGestiones> getGsGestionesList() {
        return gsGestionesList;
    }

    public void setGsGestionesList(List<GsGestiones> gsGestionesList) {
        this.gsGestionesList = gsGestionesList;
    }

    @XmlTransient
    public List<TuTutelaEstados> getTuTutelaEstadosList() {
        return tuTutelaEstadosList;
    }

    public void setTuTutelaEstadosList(List<TuTutelaEstados> tuTutelaEstadosList) {
        this.tuTutelaEstadosList = tuTutelaEstadosList;
    }

    @XmlTransient
    public List<TuTutelaEstados> getTuTutelaEstadosList1() {
        return tuTutelaEstadosList1;
    }

    public void setTuTutelaEstadosList1(List<TuTutelaEstados> tuTutelaEstadosList1) {
        this.tuTutelaEstadosList1 = tuTutelaEstadosList1;
    }

    @XmlTransient
    public List<TuTutelaEstados> getTuTutelaEstadosList2() {
        return tuTutelaEstadosList2;
    }

    public void setTuTutelaEstadosList2(List<TuTutelaEstados> tuTutelaEstadosList2) {
        this.tuTutelaEstadosList2 = tuTutelaEstadosList2;
    }

    @XmlTransient
    public List<TuTutelaEstados> getTuTutelaEstadosList3() {
        return tuTutelaEstadosList3;
    }

    public void setTuTutelaEstadosList3(List<TuTutelaEstados> tuTutelaEstadosList3) {
        this.tuTutelaEstadosList3 = tuTutelaEstadosList3;
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
        if (!(object instanceof GnUsuarios)) {
            return false;
        }
        GnUsuarios other = (GnUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnUsuarios[ id=" + id + " ]";
    }
    
}
