/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "cnt_prestador_sedes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntPrestadorSedes.findAll", query = "SELECT c FROM CntPrestadorSedes c"),
    @NamedQuery(name = "CntPrestadorSedes.findById", query = "SELECT c FROM CntPrestadorSedes c WHERE c.id = :id"),
    @NamedQuery(name = "CntPrestadorSedes.findByCodigoPrestador", query = "SELECT c FROM CntPrestadorSedes c WHERE c.codigoPrestador = :codigoPrestador"),
    @NamedQuery(name = "CntPrestadorSedes.findByUbicacionId", query = "SELECT c FROM CntPrestadorSedes c WHERE c.ubicacionId = :ubicacionId"),
    @NamedQuery(name = "CntPrestadorSedes.findByMaeRegionId", query = "SELECT c FROM CntPrestadorSedes c WHERE c.maeRegionId = :maeRegionId"),
    @NamedQuery(name = "CntPrestadorSedes.findByMaeRegionCodigo", query = "SELECT c FROM CntPrestadorSedes c WHERE c.maeRegionCodigo = :maeRegionCodigo"),
    @NamedQuery(name = "CntPrestadorSedes.findByMaeRegionValor", query = "SELECT c FROM CntPrestadorSedes c WHERE c.maeRegionValor = :maeRegionValor"),
    @NamedQuery(name = "CntPrestadorSedes.findByDireccion", query = "SELECT c FROM CntPrestadorSedes c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "CntPrestadorSedes.findByDireccionGeorreferenciada", query = "SELECT c FROM CntPrestadorSedes c WHERE c.direccionGeorreferenciada = :direccionGeorreferenciada"),
    @NamedQuery(name = "CntPrestadorSedes.findByDireccionGeorefLatitud", query = "SELECT c FROM CntPrestadorSedes c WHERE c.direccionGeorefLatitud = :direccionGeorefLatitud"),
    @NamedQuery(name = "CntPrestadorSedes.findByDireccionGeorefLongitud", query = "SELECT c FROM CntPrestadorSedes c WHERE c.direccionGeorefLongitud = :direccionGeorefLongitud"),
    @NamedQuery(name = "CntPrestadorSedes.findByNombre", query = "SELECT c FROM CntPrestadorSedes c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CntPrestadorSedes.findByCodigo", query = "SELECT c FROM CntPrestadorSedes c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CntPrestadorSedes.findByCodigoHabilitacion", query = "SELECT c FROM CntPrestadorSedes c WHERE c.codigoHabilitacion = :codigoHabilitacion"),
    @NamedQuery(name = "CntPrestadorSedes.findByZonaPrecedencia", query = "SELECT c FROM CntPrestadorSedes c WHERE c.zonaPrecedencia = :zonaPrecedencia"),
    @NamedQuery(name = "CntPrestadorSedes.findByEstadoSede", query = "SELECT c FROM CntPrestadorSedes c WHERE c.estadoSede = :estadoSede"),
    @NamedQuery(name = "CntPrestadorSedes.findByNivelAtencion", query = "SELECT c FROM CntPrestadorSedes c WHERE c.nivelAtencion = :nivelAtencion"),
    @NamedQuery(name = "CntPrestadorSedes.findByMaeClasePrestadorId", query = "SELECT c FROM CntPrestadorSedes c WHERE c.maeClasePrestadorId = :maeClasePrestadorId"),
    @NamedQuery(name = "CntPrestadorSedes.findByMaeClasePrestadorCodigo", query = "SELECT c FROM CntPrestadorSedes c WHERE c.maeClasePrestadorCodigo = :maeClasePrestadorCodigo"),
    @NamedQuery(name = "CntPrestadorSedes.findByMaeClasePrestadorValor", query = "SELECT c FROM CntPrestadorSedes c WHERE c.maeClasePrestadorValor = :maeClasePrestadorValor"),
    @NamedQuery(name = "CntPrestadorSedes.findByFax", query = "SELECT c FROM CntPrestadorSedes c WHERE c.fax = :fax"),
    @NamedQuery(name = "CntPrestadorSedes.findByTelefonoCitas", query = "SELECT c FROM CntPrestadorSedes c WHERE c.telefonoCitas = :telefonoCitas"),
    @NamedQuery(name = "CntPrestadorSedes.findByCorreoElectronico", query = "SELECT c FROM CntPrestadorSedes c WHERE c.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "CntPrestadorSedes.findByTelefonoAdministrativo", query = "SELECT c FROM CntPrestadorSedes c WHERE c.telefonoAdministrativo = :telefonoAdministrativo"),
    @NamedQuery(name = "CntPrestadorSedes.findByCapitacion", query = "SELECT c FROM CntPrestadorSedes c WHERE c.capitacion = :capitacion"),
    @NamedQuery(name = "CntPrestadorSedes.findByInteroperabilidad", query = "SELECT c FROM CntPrestadorSedes c WHERE c.interoperabilidad = :interoperabilidad"),
    @NamedQuery(name = "CntPrestadorSedes.findByFechaFacturaElectronica", query = "SELECT c FROM CntPrestadorSedes c WHERE c.fechaFacturaElectronica = :fechaFacturaElectronica"),
    @NamedQuery(name = "CntPrestadorSedes.findByGrupoRipsMinisterio", query = "SELECT c FROM CntPrestadorSedes c WHERE c.grupoRipsMinisterio = :grupoRipsMinisterio"),
    @NamedQuery(name = "CntPrestadorSedes.findByUsuarioCrea", query = "SELECT c FROM CntPrestadorSedes c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntPrestadorSedes.findByFechaHoraCrea", query = "SELECT c FROM CntPrestadorSedes c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntPrestadorSedes.findByTerminalCrea", query = "SELECT c FROM CntPrestadorSedes c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntPrestadorSedes.findByUsuarioModifica", query = "SELECT c FROM CntPrestadorSedes c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntPrestadorSedes.findByFechaHoraModifica", query = "SELECT c FROM CntPrestadorSedes c WHERE c.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "CntPrestadorSedes.findByTerminalModifica", query = "SELECT c FROM CntPrestadorSedes c WHERE c.terminalModifica = :terminalModifica")})
public class CntPrestadorSedes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "codigo_prestador")
    private String codigoPrestador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ubicacion_id")
    private int ubicacionId;
    @Column(name = "mae_region_id")
    private Integer maeRegionId;
    @Size(max = 8)
    @Column(name = "mae_region_codigo")
    private String maeRegionCodigo;
    @Size(max = 128)
    @Column(name = "mae_region_valor")
    private String maeRegionValor;
    @Size(max = 256)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "direccion_georreferenciada")
    private boolean direccionGeorreferenciada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "direccion_georef_latitud")
    private BigDecimal direccionGeorefLatitud;
    @Column(name = "direccion_georef_longitud")
    private BigDecimal direccionGeorefLongitud;
    @Size(max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 16)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 16)
    @Column(name = "codigo_habilitacion")
    private String codigoHabilitacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "zona_precedencia")
    private String zonaPrecedencia;
    @Column(name = "estado_sede")
    private Boolean estadoSede;
    @Column(name = "nivel_atencion")
    private Integer nivelAtencion;
    @Column(name = "mae_clase_prestador_id")
    private Integer maeClasePrestadorId;
    @Size(max = 8)
    @Column(name = "mae_clase_prestador_codigo")
    private String maeClasePrestadorCodigo;
    @Size(max = 128)
    @Column(name = "mae_clase_prestador_valor")
    private String maeClasePrestadorValor;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 32)
    @Column(name = "fax")
    private String fax;
    @Size(max = 64)
    @Column(name = "telefono_citas")
    private String telefonoCitas;
    @Size(max = 64)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Size(max = 64)
    @Column(name = "telefono_administrativo")
    private String telefonoAdministrativo;
    @Column(name = "capitacion")
    private Boolean capitacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "interoperabilidad")
    private boolean interoperabilidad;
    @Column(name = "fecha_factura_electronica")
    @Temporal(TemporalType.DATE)
    private Date fechaFacturaElectronica;
    @Column(name = "grupo_rips_ministerio")
    private Integer grupoRipsMinisterio;
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
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AsegPortabilidades> asegPortabilidadesList;
    @OneToMany(mappedBy = "destinoCntPrestadorSedeId", fetch = FetchType.LAZY)
    private List<TuTutelaItems> tuTutelaItemsList;
    @OneToMany(mappedBy = "prescripcionCntPrestadorSedeId", fetch = FetchType.LAZY)
    private List<TuTutelaItems> tuTutelaItemsList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AuAnexos2> auAnexos2List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AuAnexos4> auAnexos4List;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AuAnexos3> auAnexos3List;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedeCapitaId", fetch = FetchType.LAZY)
    private List<AuAnexo2RescateSedes> auAnexo2RescateSedesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedeOrigenId", fetch = FetchType.LAZY)
    private List<AuAnexo2RescateSedes> auAnexo2RescateSedesList1;
    @OneToMany(mappedBy = "cntPrestadorSedesInterdependenciaId", fetch = FetchType.LAZY)
    private List<CntContratoDetalles> cntContratoDetallesList;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<PeCargasVariables> peCargasVariablesList;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<CmFeRipsCargas> cmFeRipsCargasList;
    @OneToMany(mappedBy = "cntPrestadorSedePrescriptoraId", fetch = FetchType.LAZY)
    private List<AusCasoServicios> ausCasoServiciosList;
    @OneToMany(mappedBy = "cntPrestadorSedeDestinoId", fetch = FetchType.LAZY)
    private List<AusCasoServicios> ausCasoServiciosList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<CntPrestadorSedeServiciosHabilitacion> cntPrestadorSedeServiciosHabilitacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AucCargas> aucCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<CntContratoCoberturas> cntContratoCoberturasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<RefAnexo9Gestiones> refAnexo9GestionesList;
    @OneToMany(mappedBy = "cntDireccionadoPrestadorSedesId", fetch = FetchType.LAZY)
    private List<MpcPrescripcionesHistoricos> mpcPrescripcionesHistoricosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrescriptorPrestadorSedesId", fetch = FetchType.LAZY)
    private List<MpcPrescripcionesHistoricos> mpcPrescripcionesHistoricosList1;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<PeCargaHistoricos> peCargaHistoricosList;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AuGrupoSedes> auGrupoSedesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPresadoresSedesId", fetch = FetchType.LAZY)
    private List<RcoConciliaciones> rcoConciliacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AucAuditores> aucAuditoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<CntContratoSedes> cntContratoSedesList;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<CntPrestadorContactos> cntPrestadorContactosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AuCotizaciones> auCotizacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<CntPrestadorSedeCapacidades> cntPrestadorSedeCapacidadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AuSeguimientoPrestadorAsignados> auSeguimientoPrestadorAsignadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<PeDireccionados> peDireccionadosList;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<RefTransportes> refTransportesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<RefAnexos9> refAnexos9List;
    @OneToMany(mappedBy = "cntPrestadorSedesUbicacionId", fetch = FetchType.LAZY)
    private List<RefAnexos9> refAnexos9List1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gnPrestadorSedesId", fetch = FetchType.LAZY)
    private List<CmRipsCargas> cmRipsCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<PeIpsProgramas> peIpsProgramasList;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AntAnticipos> antAnticiposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AucCargaCierres> aucCargaCierresList;
    @OneToMany(mappedBy = "cntPrestadorSedeAsignadoId", fetch = FetchType.LAZY)
    private List<AuSeguimientos> auSeguimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<PeAfiliadosProgramas> peAfiliadosProgramasList;
    @OneToMany(mappedBy = "portabilidadCntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AsegAfiliados> asegAfiliadosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "primariaCntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AsegAfiliados> asegAfiliadosList1;
    @OneToMany(mappedBy = "odontologiaCntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AsegAfiliados> asegAfiliadosList2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesOrigenId", fetch = FetchType.LAZY)
    private List<AuAnexo2Rescates> auAnexo2RescatesList;
    @OneToMany(mappedBy = "cntPrestadorSedesDestinoId", fetch = FetchType.LAZY)
    private List<AuAnexo2Rescates> auAnexo2RescatesList1;
    @OneToMany(mappedBy = "cntPrestadoresSedesId", fetch = FetchType.LAZY)
    private List<RcoFacturaDetalles> rcoFacturaDetallesList;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AuNoSolicitudCargas> auNoSolicitudCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AucHospitalizaciones> aucHospitalizacionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntPrescriptorPrestadorSedesId", fetch = FetchType.LAZY)
    private List<MpcPrescripciones> mpcPrescripcionesList;
    @OneToMany(mappedBy = "cntDireccionadoPrestadorSedesId", fetch = FetchType.LAZY)
    private List<MpcPrescripciones> mpcPrescripcionesList1;
    @OneToMany(mappedBy = "cntPrestadorSedesId", fetch = FetchType.LAZY)
    private List<AuAnexo3Cargas> auAnexo3CargasList;

    public CntPrestadorSedes() {
    }

    public CntPrestadorSedes(Integer id) {
        this.id = id;
    }

    public CntPrestadorSedes(Integer id, String codigoPrestador, int ubicacionId, boolean direccionGeorreferenciada, String zonaPrecedencia, boolean interoperabilidad, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.codigoPrestador = codigoPrestador;
        this.ubicacionId = ubicacionId;
        this.direccionGeorreferenciada = direccionGeorreferenciada;
        this.zonaPrecedencia = zonaPrecedencia;
        this.interoperabilidad = interoperabilidad;
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

    public String getCodigoPrestador() {
        return codigoPrestador;
    }

    public void setCodigoPrestador(String codigoPrestador) {
        this.codigoPrestador = codigoPrestador;
    }

    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public Integer getMaeRegionId() {
        return maeRegionId;
    }

    public void setMaeRegionId(Integer maeRegionId) {
        this.maeRegionId = maeRegionId;
    }

    public String getMaeRegionCodigo() {
        return maeRegionCodigo;
    }

    public void setMaeRegionCodigo(String maeRegionCodigo) {
        this.maeRegionCodigo = maeRegionCodigo;
    }

    public String getMaeRegionValor() {
        return maeRegionValor;
    }

    public void setMaeRegionValor(String maeRegionValor) {
        this.maeRegionValor = maeRegionValor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean getDireccionGeorreferenciada() {
        return direccionGeorreferenciada;
    }

    public void setDireccionGeorreferenciada(boolean direccionGeorreferenciada) {
        this.direccionGeorreferenciada = direccionGeorreferenciada;
    }

    public BigDecimal getDireccionGeorefLatitud() {
        return direccionGeorefLatitud;
    }

    public void setDireccionGeorefLatitud(BigDecimal direccionGeorefLatitud) {
        this.direccionGeorefLatitud = direccionGeorefLatitud;
    }

    public BigDecimal getDireccionGeorefLongitud() {
        return direccionGeorefLongitud;
    }

    public void setDireccionGeorefLongitud(BigDecimal direccionGeorefLongitud) {
        this.direccionGeorefLongitud = direccionGeorefLongitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoHabilitacion() {
        return codigoHabilitacion;
    }

    public void setCodigoHabilitacion(String codigoHabilitacion) {
        this.codigoHabilitacion = codigoHabilitacion;
    }

    public String getZonaPrecedencia() {
        return zonaPrecedencia;
    }

    public void setZonaPrecedencia(String zonaPrecedencia) {
        this.zonaPrecedencia = zonaPrecedencia;
    }

    public Boolean getEstadoSede() {
        return estadoSede;
    }

    public void setEstadoSede(Boolean estadoSede) {
        this.estadoSede = estadoSede;
    }

    public Integer getNivelAtencion() {
        return nivelAtencion;
    }

    public void setNivelAtencion(Integer nivelAtencion) {
        this.nivelAtencion = nivelAtencion;
    }

    public Integer getMaeClasePrestadorId() {
        return maeClasePrestadorId;
    }

    public void setMaeClasePrestadorId(Integer maeClasePrestadorId) {
        this.maeClasePrestadorId = maeClasePrestadorId;
    }

    public String getMaeClasePrestadorCodigo() {
        return maeClasePrestadorCodigo;
    }

    public void setMaeClasePrestadorCodigo(String maeClasePrestadorCodigo) {
        this.maeClasePrestadorCodigo = maeClasePrestadorCodigo;
    }

    public String getMaeClasePrestadorValor() {
        return maeClasePrestadorValor;
    }

    public void setMaeClasePrestadorValor(String maeClasePrestadorValor) {
        this.maeClasePrestadorValor = maeClasePrestadorValor;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTelefonoCitas() {
        return telefonoCitas;
    }

    public void setTelefonoCitas(String telefonoCitas) {
        this.telefonoCitas = telefonoCitas;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefonoAdministrativo() {
        return telefonoAdministrativo;
    }

    public void setTelefonoAdministrativo(String telefonoAdministrativo) {
        this.telefonoAdministrativo = telefonoAdministrativo;
    }

    public Boolean getCapitacion() {
        return capitacion;
    }

    public void setCapitacion(Boolean capitacion) {
        this.capitacion = capitacion;
    }

    public boolean getInteroperabilidad() {
        return interoperabilidad;
    }

    public void setInteroperabilidad(boolean interoperabilidad) {
        this.interoperabilidad = interoperabilidad;
    }

    public Date getFechaFacturaElectronica() {
        return fechaFacturaElectronica;
    }

    public void setFechaFacturaElectronica(Date fechaFacturaElectronica) {
        this.fechaFacturaElectronica = fechaFacturaElectronica;
    }

    public Integer getGrupoRipsMinisterio() {
        return grupoRipsMinisterio;
    }

    public void setGrupoRipsMinisterio(Integer grupoRipsMinisterio) {
        this.grupoRipsMinisterio = grupoRipsMinisterio;
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

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    @XmlTransient
    public List<AsegPortabilidades> getAsegPortabilidadesList() {
        return asegPortabilidadesList;
    }

    public void setAsegPortabilidadesList(List<AsegPortabilidades> asegPortabilidadesList) {
        this.asegPortabilidadesList = asegPortabilidadesList;
    }

    @XmlTransient
    public List<TuTutelaItems> getTuTutelaItemsList() {
        return tuTutelaItemsList;
    }

    public void setTuTutelaItemsList(List<TuTutelaItems> tuTutelaItemsList) {
        this.tuTutelaItemsList = tuTutelaItemsList;
    }

    @XmlTransient
    public List<TuTutelaItems> getTuTutelaItemsList1() {
        return tuTutelaItemsList1;
    }

    public void setTuTutelaItemsList1(List<TuTutelaItems> tuTutelaItemsList1) {
        this.tuTutelaItemsList1 = tuTutelaItemsList1;
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

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    @XmlTransient
    public List<AuAnexo2RescateSedes> getAuAnexo2RescateSedesList() {
        return auAnexo2RescateSedesList;
    }

    public void setAuAnexo2RescateSedesList(List<AuAnexo2RescateSedes> auAnexo2RescateSedesList) {
        this.auAnexo2RescateSedesList = auAnexo2RescateSedesList;
    }

    @XmlTransient
    public List<AuAnexo2RescateSedes> getAuAnexo2RescateSedesList1() {
        return auAnexo2RescateSedesList1;
    }

    public void setAuAnexo2RescateSedesList1(List<AuAnexo2RescateSedes> auAnexo2RescateSedesList1) {
        this.auAnexo2RescateSedesList1 = auAnexo2RescateSedesList1;
    }

    @XmlTransient
    public List<CntContratoDetalles> getCntContratoDetallesList() {
        return cntContratoDetallesList;
    }

    public void setCntContratoDetallesList(List<CntContratoDetalles> cntContratoDetallesList) {
        this.cntContratoDetallesList = cntContratoDetallesList;
    }

    @XmlTransient
    public List<PeCargasVariables> getPeCargasVariablesList() {
        return peCargasVariablesList;
    }

    public void setPeCargasVariablesList(List<PeCargasVariables> peCargasVariablesList) {
        this.peCargasVariablesList = peCargasVariablesList;
    }

    @XmlTransient
    public List<CmFeRipsCargas> getCmFeRipsCargasList() {
        return cmFeRipsCargasList;
    }

    public void setCmFeRipsCargasList(List<CmFeRipsCargas> cmFeRipsCargasList) {
        this.cmFeRipsCargasList = cmFeRipsCargasList;
    }

    @XmlTransient
    public List<AusCasoServicios> getAusCasoServiciosList() {
        return ausCasoServiciosList;
    }

    public void setAusCasoServiciosList(List<AusCasoServicios> ausCasoServiciosList) {
        this.ausCasoServiciosList = ausCasoServiciosList;
    }

    @XmlTransient
    public List<AusCasoServicios> getAusCasoServiciosList1() {
        return ausCasoServiciosList1;
    }

    public void setAusCasoServiciosList1(List<AusCasoServicios> ausCasoServiciosList1) {
        this.ausCasoServiciosList1 = ausCasoServiciosList1;
    }

    @XmlTransient
    public List<CntPrestadorSedeServiciosHabilitacion> getCntPrestadorSedeServiciosHabilitacionList() {
        return cntPrestadorSedeServiciosHabilitacionList;
    }

    public void setCntPrestadorSedeServiciosHabilitacionList(List<CntPrestadorSedeServiciosHabilitacion> cntPrestadorSedeServiciosHabilitacionList) {
        this.cntPrestadorSedeServiciosHabilitacionList = cntPrestadorSedeServiciosHabilitacionList;
    }

    @XmlTransient
    public List<AucCargas> getAucCargasList() {
        return aucCargasList;
    }

    public void setAucCargasList(List<AucCargas> aucCargasList) {
        this.aucCargasList = aucCargasList;
    }

    @XmlTransient
    public List<CntContratoCoberturas> getCntContratoCoberturasList() {
        return cntContratoCoberturasList;
    }

    public void setCntContratoCoberturasList(List<CntContratoCoberturas> cntContratoCoberturasList) {
        this.cntContratoCoberturasList = cntContratoCoberturasList;
    }

    @XmlTransient
    public List<RefAnexo9Gestiones> getRefAnexo9GestionesList() {
        return refAnexo9GestionesList;
    }

    public void setRefAnexo9GestionesList(List<RefAnexo9Gestiones> refAnexo9GestionesList) {
        this.refAnexo9GestionesList = refAnexo9GestionesList;
    }

    @XmlTransient
    public List<MpcPrescripcionesHistoricos> getMpcPrescripcionesHistoricosList() {
        return mpcPrescripcionesHistoricosList;
    }

    public void setMpcPrescripcionesHistoricosList(List<MpcPrescripcionesHistoricos> mpcPrescripcionesHistoricosList) {
        this.mpcPrescripcionesHistoricosList = mpcPrescripcionesHistoricosList;
    }

    @XmlTransient
    public List<MpcPrescripcionesHistoricos> getMpcPrescripcionesHistoricosList1() {
        return mpcPrescripcionesHistoricosList1;
    }

    public void setMpcPrescripcionesHistoricosList1(List<MpcPrescripcionesHistoricos> mpcPrescripcionesHistoricosList1) {
        this.mpcPrescripcionesHistoricosList1 = mpcPrescripcionesHistoricosList1;
    }

    @XmlTransient
    public List<PeCargaHistoricos> getPeCargaHistoricosList() {
        return peCargaHistoricosList;
    }

    public void setPeCargaHistoricosList(List<PeCargaHistoricos> peCargaHistoricosList) {
        this.peCargaHistoricosList = peCargaHistoricosList;
    }

    @XmlTransient
    public List<AuGrupoSedes> getAuGrupoSedesList() {
        return auGrupoSedesList;
    }

    public void setAuGrupoSedesList(List<AuGrupoSedes> auGrupoSedesList) {
        this.auGrupoSedesList = auGrupoSedesList;
    }

    @XmlTransient
    public List<RcoConciliaciones> getRcoConciliacionesList() {
        return rcoConciliacionesList;
    }

    public void setRcoConciliacionesList(List<RcoConciliaciones> rcoConciliacionesList) {
        this.rcoConciliacionesList = rcoConciliacionesList;
    }

    @XmlTransient
    public List<AucAuditores> getAucAuditoresList() {
        return aucAuditoresList;
    }

    public void setAucAuditoresList(List<AucAuditores> aucAuditoresList) {
        this.aucAuditoresList = aucAuditoresList;
    }

    @XmlTransient
    public List<CntContratoSedes> getCntContratoSedesList() {
        return cntContratoSedesList;
    }

    public void setCntContratoSedesList(List<CntContratoSedes> cntContratoSedesList) {
        this.cntContratoSedesList = cntContratoSedesList;
    }

    @XmlTransient
    public List<CntPrestadorContactos> getCntPrestadorContactosList() {
        return cntPrestadorContactosList;
    }

    public void setCntPrestadorContactosList(List<CntPrestadorContactos> cntPrestadorContactosList) {
        this.cntPrestadorContactosList = cntPrestadorContactosList;
    }

    @XmlTransient
    public List<AuCotizaciones> getAuCotizacionesList() {
        return auCotizacionesList;
    }

    public void setAuCotizacionesList(List<AuCotizaciones> auCotizacionesList) {
        this.auCotizacionesList = auCotizacionesList;
    }

    @XmlTransient
    public List<CntPrestadorSedeCapacidades> getCntPrestadorSedeCapacidadesList() {
        return cntPrestadorSedeCapacidadesList;
    }

    public void setCntPrestadorSedeCapacidadesList(List<CntPrestadorSedeCapacidades> cntPrestadorSedeCapacidadesList) {
        this.cntPrestadorSedeCapacidadesList = cntPrestadorSedeCapacidadesList;
    }

    @XmlTransient
    public List<AuSeguimientoPrestadorAsignados> getAuSeguimientoPrestadorAsignadosList() {
        return auSeguimientoPrestadorAsignadosList;
    }

    public void setAuSeguimientoPrestadorAsignadosList(List<AuSeguimientoPrestadorAsignados> auSeguimientoPrestadorAsignadosList) {
        this.auSeguimientoPrestadorAsignadosList = auSeguimientoPrestadorAsignadosList;
    }

    @XmlTransient
    public List<PeDireccionados> getPeDireccionadosList() {
        return peDireccionadosList;
    }

    public void setPeDireccionadosList(List<PeDireccionados> peDireccionadosList) {
        this.peDireccionadosList = peDireccionadosList;
    }

    @XmlTransient
    public List<RefTransportes> getRefTransportesList() {
        return refTransportesList;
    }

    public void setRefTransportesList(List<RefTransportes> refTransportesList) {
        this.refTransportesList = refTransportesList;
    }

    @XmlTransient
    public List<RefAnexos9> getRefAnexos9List() {
        return refAnexos9List;
    }

    public void setRefAnexos9List(List<RefAnexos9> refAnexos9List) {
        this.refAnexos9List = refAnexos9List;
    }

    @XmlTransient
    public List<RefAnexos9> getRefAnexos9List1() {
        return refAnexos9List1;
    }

    public void setRefAnexos9List1(List<RefAnexos9> refAnexos9List1) {
        this.refAnexos9List1 = refAnexos9List1;
    }

    @XmlTransient
    public List<CmRipsCargas> getCmRipsCargasList() {
        return cmRipsCargasList;
    }

    public void setCmRipsCargasList(List<CmRipsCargas> cmRipsCargasList) {
        this.cmRipsCargasList = cmRipsCargasList;
    }

    @XmlTransient
    public List<CntContratoHistoricoPrestaciones> getCntContratoHistoricoPrestacionesList() {
        return cntContratoHistoricoPrestacionesList;
    }

    public void setCntContratoHistoricoPrestacionesList(List<CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList) {
        this.cntContratoHistoricoPrestacionesList = cntContratoHistoricoPrestacionesList;
    }

    @XmlTransient
    public List<PeIpsProgramas> getPeIpsProgramasList() {
        return peIpsProgramasList;
    }

    public void setPeIpsProgramasList(List<PeIpsProgramas> peIpsProgramasList) {
        this.peIpsProgramasList = peIpsProgramasList;
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
    public List<AuSeguimientos> getAuSeguimientosList() {
        return auSeguimientosList;
    }

    public void setAuSeguimientosList(List<AuSeguimientos> auSeguimientosList) {
        this.auSeguimientosList = auSeguimientosList;
    }

    @XmlTransient
    public List<PeAfiliadosProgramas> getPeAfiliadosProgramasList() {
        return peAfiliadosProgramasList;
    }

    public void setPeAfiliadosProgramasList(List<PeAfiliadosProgramas> peAfiliadosProgramasList) {
        this.peAfiliadosProgramasList = peAfiliadosProgramasList;
    }

    @XmlTransient
    public List<AsegAfiliados> getAsegAfiliadosList() {
        return asegAfiliadosList;
    }

    public void setAsegAfiliadosList(List<AsegAfiliados> asegAfiliadosList) {
        this.asegAfiliadosList = asegAfiliadosList;
    }

    @XmlTransient
    public List<AsegAfiliados> getAsegAfiliadosList1() {
        return asegAfiliadosList1;
    }

    public void setAsegAfiliadosList1(List<AsegAfiliados> asegAfiliadosList1) {
        this.asegAfiliadosList1 = asegAfiliadosList1;
    }

    @XmlTransient
    public List<AsegAfiliados> getAsegAfiliadosList2() {
        return asegAfiliadosList2;
    }

    public void setAsegAfiliadosList2(List<AsegAfiliados> asegAfiliadosList2) {
        this.asegAfiliadosList2 = asegAfiliadosList2;
    }

    @XmlTransient
    public List<CntContratoHistoricoValidaciones> getCntContratoHistoricoValidacionesList() {
        return cntContratoHistoricoValidacionesList;
    }

    public void setCntContratoHistoricoValidacionesList(List<CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList) {
        this.cntContratoHistoricoValidacionesList = cntContratoHistoricoValidacionesList;
    }

    @XmlTransient
    public List<AuAnexo2Rescates> getAuAnexo2RescatesList() {
        return auAnexo2RescatesList;
    }

    public void setAuAnexo2RescatesList(List<AuAnexo2Rescates> auAnexo2RescatesList) {
        this.auAnexo2RescatesList = auAnexo2RescatesList;
    }

    @XmlTransient
    public List<AuAnexo2Rescates> getAuAnexo2RescatesList1() {
        return auAnexo2RescatesList1;
    }

    public void setAuAnexo2RescatesList1(List<AuAnexo2Rescates> auAnexo2RescatesList1) {
        this.auAnexo2RescatesList1 = auAnexo2RescatesList1;
    }

    @XmlTransient
    public List<RcoFacturaDetalles> getRcoFacturaDetallesList() {
        return rcoFacturaDetallesList;
    }

    public void setRcoFacturaDetallesList(List<RcoFacturaDetalles> rcoFacturaDetallesList) {
        this.rcoFacturaDetallesList = rcoFacturaDetallesList;
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
    public List<MpcPrescripciones> getMpcPrescripcionesList() {
        return mpcPrescripcionesList;
    }

    public void setMpcPrescripcionesList(List<MpcPrescripciones> mpcPrescripcionesList) {
        this.mpcPrescripcionesList = mpcPrescripcionesList;
    }

    @XmlTransient
    public List<MpcPrescripciones> getMpcPrescripcionesList1() {
        return mpcPrescripcionesList1;
    }

    public void setMpcPrescripcionesList1(List<MpcPrescripciones> mpcPrescripcionesList1) {
        this.mpcPrescripcionesList1 = mpcPrescripcionesList1;
    }

    @XmlTransient
    public List<AuAnexo3Cargas> getAuAnexo3CargasList() {
        return auAnexo3CargasList;
    }

    public void setAuAnexo3CargasList(List<AuAnexo3Cargas> auAnexo3CargasList) {
        this.auAnexo3CargasList = auAnexo3CargasList;
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
        if (!(object instanceof CntPrestadorSedes)) {
            return false;
        }
        CntPrestadorSedes other = (CntPrestadorSedes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntPrestadorSedes[ id=" + id + " ]";
    }
    
}
