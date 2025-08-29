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
@Table(name = "gn_empresas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GnEmpresas.findAll", query = "SELECT g FROM GnEmpresas g"),
    @NamedQuery(name = "GnEmpresas.findById", query = "SELECT g FROM GnEmpresas g WHERE g.id = :id"),
    @NamedQuery(name = "GnEmpresas.findByRazonSocial", query = "SELECT g FROM GnEmpresas g WHERE g.razonSocial = :razonSocial"),
    @NamedQuery(name = "GnEmpresas.findByNit", query = "SELECT g FROM GnEmpresas g WHERE g.nit = :nit"),
    @NamedQuery(name = "GnEmpresas.findByNombreComercial", query = "SELECT g FROM GnEmpresas g WHERE g.nombreComercial = :nombreComercial"),
    @NamedQuery(name = "GnEmpresas.findByDescripcion", query = "SELECT g FROM GnEmpresas g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GnEmpresas.findByAdministradora", query = "SELECT g FROM GnEmpresas g WHERE g.administradora = :administradora"),
    @NamedQuery(name = "GnEmpresas.findByActiva", query = "SELECT g FROM GnEmpresas g WHERE g.activa = :activa"),
    @NamedQuery(name = "GnEmpresas.findByCodigoHabilitacion", query = "SELECT g FROM GnEmpresas g WHERE g.codigoHabilitacion = :codigoHabilitacion"),
    @NamedQuery(name = "GnEmpresas.findByContrasenaLongitudMinimo", query = "SELECT g FROM GnEmpresas g WHERE g.contrasenaLongitudMinimo = :contrasenaLongitudMinimo"),
    @NamedQuery(name = "GnEmpresas.findByContrasenaLongitudMaximo", query = "SELECT g FROM GnEmpresas g WHERE g.contrasenaLongitudMaximo = :contrasenaLongitudMaximo"),
    @NamedQuery(name = "GnEmpresas.findByContrasenaDiasVencimiento", query = "SELECT g FROM GnEmpresas g WHERE g.contrasenaDiasVencimiento = :contrasenaDiasVencimiento"),
    @NamedQuery(name = "GnEmpresas.findByContrasenaDiasNotificacion", query = "SELECT g FROM GnEmpresas g WHERE g.contrasenaDiasNotificacion = :contrasenaDiasNotificacion"),
    @NamedQuery(name = "GnEmpresas.findByContrasenaIntentos", query = "SELECT g FROM GnEmpresas g WHERE g.contrasenaIntentos = :contrasenaIntentos"),
    @NamedQuery(name = "GnEmpresas.findByReceptorUsuario", query = "SELECT g FROM GnEmpresas g WHERE g.receptorUsuario = :receptorUsuario"),
    @NamedQuery(name = "GnEmpresas.findByReceptorContrasena", query = "SELECT g FROM GnEmpresas g WHERE g.receptorContrasena = :receptorContrasena"),
    @NamedQuery(name = "GnEmpresas.findBySesionesUsuario", query = "SELECT g FROM GnEmpresas g WHERE g.sesionesUsuario = :sesionesUsuario"),
    @NamedQuery(name = "GnEmpresas.findByUsuarioCrea", query = "SELECT g FROM GnEmpresas g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GnEmpresas.findByTerminalCrea", query = "SELECT g FROM GnEmpresas g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GnEmpresas.findByFechaHoraCrea", query = "SELECT g FROM GnEmpresas g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GnEmpresas.findByUsuarioModifica", query = "SELECT g FROM GnEmpresas g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GnEmpresas.findByTerminalModifica", query = "SELECT g FROM GnEmpresas g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GnEmpresas.findByFechaHoraModifica", query = "SELECT g FROM GnEmpresas g WHERE g.fechaHoraModifica = :fechaHoraModifica")})
public class GnEmpresas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "razon_social")
    private String razonSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "nit")
    private String nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "administradora")
    private boolean administradora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activa")
    private boolean activa;
    @Size(max = 13)
    @Column(name = "codigo_habilitacion")
    private String codigoHabilitacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contrasena_longitud_minimo")
    private int contrasenaLongitudMinimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contrasena_longitud_maximo")
    private int contrasenaLongitudMaximo;
    @Column(name = "contrasena_dias_vencimiento")
    private Integer contrasenaDiasVencimiento;
    @Column(name = "contrasena_dias_notificacion")
    private Integer contrasenaDiasNotificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contrasena_intentos")
    private int contrasenaIntentos;
    @Size(max = 8)
    @Column(name = "receptor_usuario")
    private String receptorUsuario;
    @Size(max = 8)
    @Column(name = "receptor_contrasena")
    private String receptorContrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sesiones_usuario")
    private short sesionesUsuario;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<GnSedes> gnSedesList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuAnexos2> auAnexos2List;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuAnexos4> auAnexos4List;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuAnexos3> auAnexos3List;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<PeCargasVariables> peCargasVariablesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<GnUsuarios> gnUsuariosList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuRechazos> auRechazosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<CmFeRipsCargas> cmFeRipsCargasList;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "gn_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones gnUbicacionesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AucCargas> aucCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<MpcProgramacionEntregas> mpcProgramacionEntregasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<MpcPrescripcionesHistoricos> mpcPrescripcionesHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudes> auNoSolicitudesList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<PeCargas> peCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<CarCargas> carCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AusCasos> ausCasosList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudEntregaCargas> auNoSolicitudEntregaCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<FinPostulaciones> finPostulacionesList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuAnexo4CargaAnuladas> auAnexo4CargaAnuladasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<PeDireccionados> peDireccionadosList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AsegAnexos1> asegAnexos1List;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<RefAnexos9> refAnexos9List;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<CmRipsCargas> cmRipsCargasList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AntAnticipos> antAnticiposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AucCargaCierres> aucCargaCierresList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<CmFeSoportes> cmFeSoportesList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuSeguimientos> auSeguimientosList;
    @OneToMany(mappedBy = "gnEmpresaId", fetch = FetchType.LAZY)
    private List<AusCargaMasivas> ausCargaMasivasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<CmMarcacionIpsMasiva> cmMarcacionIpsMasivaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuAnexo2Rescates> auAnexo2RescatesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<CmFacturas> cmFacturasList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudCargas> auNoSolicitudCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AucHospitalizaciones> aucHospitalizacionesList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<CntContratos> cntContratosList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<MpcPrescripciones> mpcPrescripcionesList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuAnexo3Cargas> auAnexo3CargasList;
    @OneToMany(mappedBy = "gnEmpresasId", fetch = FetchType.LAZY)
    private List<AuAnexo3CargaAnuladas> auAnexo3CargaAnuladasList;

    public GnEmpresas() {
    }

    public GnEmpresas(Integer id) {
        this.id = id;
    }

    public GnEmpresas(Integer id, String razonSocial, String nit, String nombreComercial, boolean administradora, boolean activa, int contrasenaLongitudMinimo, int contrasenaLongitudMaximo, int contrasenaIntentos, short sesionesUsuario, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.nit = nit;
        this.nombreComercial = nombreComercial;
        this.administradora = administradora;
        this.activa = activa;
        this.contrasenaLongitudMinimo = contrasenaLongitudMinimo;
        this.contrasenaLongitudMaximo = contrasenaLongitudMaximo;
        this.contrasenaIntentos = contrasenaIntentos;
        this.sesionesUsuario = sesionesUsuario;
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getAdministradora() {
        return administradora;
    }

    public void setAdministradora(boolean administradora) {
        this.administradora = administradora;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
    }

    public int getContrasenaLongitudMinimo() {
        return contrasenaLongitudMinimo;
    }

    public void setContrasenaLongitudMinimo(int contrasenaLongitudMinimo) {
        this.contrasenaLongitudMinimo = contrasenaLongitudMinimo;
    }

    public int getContrasenaLongitudMaximo() {
        return contrasenaLongitudMaximo;
    }

    public void setContrasenaLongitudMaximo(int contrasenaLongitudMaximo) {
        this.contrasenaLongitudMaximo = contrasenaLongitudMaximo;
    }

    public Integer getContrasenaDiasVencimiento() {
        return contrasenaDiasVencimiento;
    }

    public void setContrasenaDiasVencimiento(Integer contrasenaDiasVencimiento) {
        this.contrasenaDiasVencimiento = contrasenaDiasVencimiento;
    }

    public Integer getContrasenaDiasNotificacion() {
        return contrasenaDiasNotificacion;
    }

    public void setContrasenaDiasNotificacion(Integer contrasenaDiasNotificacion) {
        this.contrasenaDiasNotificacion = contrasenaDiasNotificacion;
    }

    public int getContrasenaIntentos() {
        return contrasenaIntentos;
    }

    public void setContrasenaIntentos(int contrasenaIntentos) {
        this.contrasenaIntentos = contrasenaIntentos;
    }

    public String getReceptorUsuario() {
        return receptorUsuario;
    }

    public void setReceptorUsuario(String receptorUsuario) {
        this.receptorUsuario = receptorUsuario;
    }

    public String getReceptorContrasena() {
        return receptorContrasena;
    }

    public void setReceptorContrasena(String receptorContrasena) {
        this.receptorContrasena = receptorContrasena;
    }

    public short getSesionesUsuario() {
        return sesionesUsuario;
    }

    public void setSesionesUsuario(short sesionesUsuario) {
        this.sesionesUsuario = sesionesUsuario;
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
    public List<GnSedes> getGnSedesList() {
        return gnSedesList;
    }

    public void setGnSedesList(List<GnSedes> gnSedesList) {
        this.gnSedesList = gnSedesList;
    }

    @XmlTransient
    public List<AuAnexos2> getAuAnexos2List() {
        return auAnexos2List;
    }

    public void setAuAnexos2List(List<AuAnexos2> auAnexos2List) {
        this.auAnexos2List = auAnexos2List;
    }

    @XmlTransient
    public List<AuAnexos4> getAuAnexos4List() {
        return auAnexos4List;
    }

    public void setAuAnexos4List(List<AuAnexos4> auAnexos4List) {
        this.auAnexos4List = auAnexos4List;
    }

    @XmlTransient
    public List<AuAnexos3> getAuAnexos3List() {
        return auAnexos3List;
    }

    public void setAuAnexos3List(List<AuAnexos3> auAnexos3List) {
        this.auAnexos3List = auAnexos3List;
    }

    @XmlTransient
    public List<PeCargasVariables> getPeCargasVariablesList() {
        return peCargasVariablesList;
    }

    public void setPeCargasVariablesList(List<PeCargasVariables> peCargasVariablesList) {
        this.peCargasVariablesList = peCargasVariablesList;
    }

    @XmlTransient
    public List<GnUsuarios> getGnUsuariosList() {
        return gnUsuariosList;
    }

    public void setGnUsuariosList(List<GnUsuarios> gnUsuariosList) {
        this.gnUsuariosList = gnUsuariosList;
    }

    @XmlTransient
    public List<AuRechazos> getAuRechazosList() {
        return auRechazosList;
    }

    public void setAuRechazosList(List<AuRechazos> auRechazosList) {
        this.auRechazosList = auRechazosList;
    }

    @XmlTransient
    public List<CmFeRipsCargas> getCmFeRipsCargasList() {
        return cmFeRipsCargasList;
    }

    public void setCmFeRipsCargasList(List<CmFeRipsCargas> cmFeRipsCargasList) {
        this.cmFeRipsCargasList = cmFeRipsCargasList;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public GnUbicaciones getGnUbicacionesId() {
        return gnUbicacionesId;
    }

    public void setGnUbicacionesId(GnUbicaciones gnUbicacionesId) {
        this.gnUbicacionesId = gnUbicacionesId;
    }

    @XmlTransient
    public List<AucCargas> getAucCargasList() {
        return aucCargasList;
    }

    public void setAucCargasList(List<AucCargas> aucCargasList) {
        this.aucCargasList = aucCargasList;
    }

    @XmlTransient
    public List<MpcProgramacionEntregas> getMpcProgramacionEntregasList() {
        return mpcProgramacionEntregasList;
    }

    public void setMpcProgramacionEntregasList(List<MpcProgramacionEntregas> mpcProgramacionEntregasList) {
        this.mpcProgramacionEntregasList = mpcProgramacionEntregasList;
    }

    @XmlTransient
    public List<MpcPrescripcionesHistoricos> getMpcPrescripcionesHistoricosList() {
        return mpcPrescripcionesHistoricosList;
    }

    public void setMpcPrescripcionesHistoricosList(List<MpcPrescripcionesHistoricos> mpcPrescripcionesHistoricosList) {
        this.mpcPrescripcionesHistoricosList = mpcPrescripcionesHistoricosList;
    }

    @XmlTransient
    public List<AuNoSolicitudes> getAuNoSolicitudesList() {
        return auNoSolicitudesList;
    }

    public void setAuNoSolicitudesList(List<AuNoSolicitudes> auNoSolicitudesList) {
        this.auNoSolicitudesList = auNoSolicitudesList;
    }

    @XmlTransient
    public List<PeCargas> getPeCargasList() {
        return peCargasList;
    }

    public void setPeCargasList(List<PeCargas> peCargasList) {
        this.peCargasList = peCargasList;
    }

    @XmlTransient
    public List<CarCargas> getCarCargasList() {
        return carCargasList;
    }

    public void setCarCargasList(List<CarCargas> carCargasList) {
        this.carCargasList = carCargasList;
    }

    @XmlTransient
    public List<AusCasos> getAusCasosList() {
        return ausCasosList;
    }

    public void setAusCasosList(List<AusCasos> ausCasosList) {
        this.ausCasosList = ausCasosList;
    }

    @XmlTransient
    public List<AuNoSolicitudEntregaCargas> getAuNoSolicitudEntregaCargasList() {
        return auNoSolicitudEntregaCargasList;
    }

    public void setAuNoSolicitudEntregaCargasList(List<AuNoSolicitudEntregaCargas> auNoSolicitudEntregaCargasList) {
        this.auNoSolicitudEntregaCargasList = auNoSolicitudEntregaCargasList;
    }

    @XmlTransient
    public List<FinPostulaciones> getFinPostulacionesList() {
        return finPostulacionesList;
    }

    public void setFinPostulacionesList(List<FinPostulaciones> finPostulacionesList) {
        this.finPostulacionesList = finPostulacionesList;
    }

    @XmlTransient
    public List<AuAnexo4CargaAnuladas> getAuAnexo4CargaAnuladasList() {
        return auAnexo4CargaAnuladasList;
    }

    public void setAuAnexo4CargaAnuladasList(List<AuAnexo4CargaAnuladas> auAnexo4CargaAnuladasList) {
        this.auAnexo4CargaAnuladasList = auAnexo4CargaAnuladasList;
    }

    @XmlTransient
    public List<PeDireccionados> getPeDireccionadosList() {
        return peDireccionadosList;
    }

    public void setPeDireccionadosList(List<PeDireccionados> peDireccionadosList) {
        this.peDireccionadosList = peDireccionadosList;
    }

    @XmlTransient
    public List<AsegAnexos1> getAsegAnexos1List() {
        return asegAnexos1List;
    }

    public void setAsegAnexos1List(List<AsegAnexos1> asegAnexos1List) {
        this.asegAnexos1List = asegAnexos1List;
    }

    @XmlTransient
    public List<RefAnexos9> getRefAnexos9List() {
        return refAnexos9List;
    }

    public void setRefAnexos9List(List<RefAnexos9> refAnexos9List) {
        this.refAnexos9List = refAnexos9List;
    }

    @XmlTransient
    public List<CmRipsCargas> getCmRipsCargasList() {
        return cmRipsCargasList;
    }

    public void setCmRipsCargasList(List<CmRipsCargas> cmRipsCargasList) {
        this.cmRipsCargasList = cmRipsCargasList;
    }

    @XmlTransient
    public List<AntAnticipos> getAntAnticiposList() {
        return antAnticiposList;
    }

    public void setAntAnticiposList(List<AntAnticipos> antAnticiposList) {
        this.antAnticiposList = antAnticiposList;
    }

    @XmlTransient
    public List<AucCargaCierres> getAucCargaCierresList() {
        return aucCargaCierresList;
    }

    public void setAucCargaCierresList(List<AucCargaCierres> aucCargaCierresList) {
        this.aucCargaCierresList = aucCargaCierresList;
    }

    @XmlTransient
    public List<CmFeSoportes> getCmFeSoportesList() {
        return cmFeSoportesList;
    }

    public void setCmFeSoportesList(List<CmFeSoportes> cmFeSoportesList) {
        this.cmFeSoportesList = cmFeSoportesList;
    }

    @XmlTransient
    public List<AuSeguimientos> getAuSeguimientosList() {
        return auSeguimientosList;
    }

    public void setAuSeguimientosList(List<AuSeguimientos> auSeguimientosList) {
        this.auSeguimientosList = auSeguimientosList;
    }

    @XmlTransient
    public List<AusCargaMasivas> getAusCargaMasivasList() {
        return ausCargaMasivasList;
    }

    public void setAusCargaMasivasList(List<AusCargaMasivas> ausCargaMasivasList) {
        this.ausCargaMasivasList = ausCargaMasivasList;
    }

    @XmlTransient
    public List<CmMarcacionIpsMasiva> getCmMarcacionIpsMasivaList() {
        return cmMarcacionIpsMasivaList;
    }

    public void setCmMarcacionIpsMasivaList(List<CmMarcacionIpsMasiva> cmMarcacionIpsMasivaList) {
        this.cmMarcacionIpsMasivaList = cmMarcacionIpsMasivaList;
    }

    @XmlTransient
    public List<AuAnexo2Rescates> getAuAnexo2RescatesList() {
        return auAnexo2RescatesList;
    }

    public void setAuAnexo2RescatesList(List<AuAnexo2Rescates> auAnexo2RescatesList) {
        this.auAnexo2RescatesList = auAnexo2RescatesList;
    }

    @XmlTransient
    public List<CmFacturas> getCmFacturasList() {
        return cmFacturasList;
    }

    public void setCmFacturasList(List<CmFacturas> cmFacturasList) {
        this.cmFacturasList = cmFacturasList;
    }

    @XmlTransient
    public List<AuNoSolicitudCargas> getAuNoSolicitudCargasList() {
        return auNoSolicitudCargasList;
    }

    public void setAuNoSolicitudCargasList(List<AuNoSolicitudCargas> auNoSolicitudCargasList) {
        this.auNoSolicitudCargasList = auNoSolicitudCargasList;
    }

    @XmlTransient
    public List<AucHospitalizaciones> getAucHospitalizacionesList() {
        return aucHospitalizacionesList;
    }

    public void setAucHospitalizacionesList(List<AucHospitalizaciones> aucHospitalizacionesList) {
        this.aucHospitalizacionesList = aucHospitalizacionesList;
    }

    @XmlTransient
    public List<CntContratos> getCntContratosList() {
        return cntContratosList;
    }

    public void setCntContratosList(List<CntContratos> cntContratosList) {
        this.cntContratosList = cntContratosList;
    }

    @XmlTransient
    public List<MpcPrescripciones> getMpcPrescripcionesList() {
        return mpcPrescripcionesList;
    }

    public void setMpcPrescripcionesList(List<MpcPrescripciones> mpcPrescripcionesList) {
        this.mpcPrescripcionesList = mpcPrescripcionesList;
    }

    @XmlTransient
    public List<AuAnexo3Cargas> getAuAnexo3CargasList() {
        return auAnexo3CargasList;
    }

    public void setAuAnexo3CargasList(List<AuAnexo3Cargas> auAnexo3CargasList) {
        this.auAnexo3CargasList = auAnexo3CargasList;
    }

    @XmlTransient
    public List<AuAnexo3CargaAnuladas> getAuAnexo3CargaAnuladasList() {
        return auAnexo3CargaAnuladasList;
    }

    public void setAuAnexo3CargaAnuladasList(List<AuAnexo3CargaAnuladas> auAnexo3CargaAnuladasList) {
        this.auAnexo3CargaAnuladasList = auAnexo3CargaAnuladasList;
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
        if (!(object instanceof GnEmpresas)) {
            return false;
        }
        GnEmpresas other = (GnEmpresas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GnEmpresas[ id=" + id + " ]";
    }
    
}
