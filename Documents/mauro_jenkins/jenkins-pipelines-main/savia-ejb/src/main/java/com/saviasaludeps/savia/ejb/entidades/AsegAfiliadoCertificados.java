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
@Table(name = "aseg_afiliado_certificados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegAfiliadoCertificados.findAll", query = "SELECT a FROM AsegAfiliadoCertificados a"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findById", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.id = :id"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByIdAfiliado", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.idAfiliado = :idAfiliado"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeTipoDocumentoId", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeTipoDocumentoCodigo", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeTipoDocumentoValor", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByNumeroDocumento", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByPrimerApellido", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.primerApellido = :primerApellido"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findBySegundoApellido", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByPrimerNombre", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.primerNombre = :primerNombre"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findBySegundoNombre", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByFechaNacimiento", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeSubgrupoSisbenId", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeSubgrupoSisbenId = :maeSubgrupoSisbenId"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeSubgrupoSisbenCodigo", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeSubgrupoSisbenCodigo = :maeSubgrupoSisbenCodigo"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeSubgrupoSisbenValor", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeSubgrupoSisbenValor = :maeSubgrupoSisbenValor"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeNivelId", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeNivelId = :maeNivelId"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeNivelCodigo", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeNivelCodigo = :maeNivelCodigo"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeNivelValor", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeNivelValor = :maeNivelValor"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeGrupoPoblacionalId", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeGrupoPoblacionalId = :maeGrupoPoblacionalId"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeGrupoPoblacionalCodigo", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeGrupoPoblacionalCodigo = :maeGrupoPoblacionalCodigo"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeGrupoPoblacionalValor", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeGrupoPoblacionalValor = :maeGrupoPoblacionalValor"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByFechaAfiliacion", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.fechaAfiliacion = :fechaAfiliacion"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeRegimenId", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeRegimenId = :maeRegimenId"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeRegimenValor", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeRegimenValor = :maeRegimenValor"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeRegimenDescripcion", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeRegimenDescripcion = :maeRegimenDescripcion"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeEstadoAfiliacionId", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeEstadoAfiliacionId = :maeEstadoAfiliacionId"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeEstadoAfiliacionCodigo", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeEstadoAfiliacionCodigo = :maeEstadoAfiliacionCodigo"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByMaeEstadoAfiliacionValor", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.maeEstadoAfiliacionValor = :maeEstadoAfiliacionValor"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByModeloLiquidacion", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.modeloLiquidacion = :modeloLiquidacion"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByFechaRetiro", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.fechaRetiro = :fechaRetiro"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findBySemanaAfiliacion", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.semanaAfiliacion = :semanaAfiliacion"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByDireccionAfiliado", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.direccionAfiliado = :direccionAfiliado"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByTelefonoAfiliado", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.telefonoAfiliado = :telefonoAfiliado"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByCelularAfiliado", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.celularAfiliado = :celularAfiliado"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByAfiliacionUbicacionId", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.afiliacionUbicacionId = :afiliacionUbicacionId"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByAfiliacionUbicacionValor", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.afiliacionUbicacionValor = :afiliacionUbicacionValor"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByResidenciaUbicacionId", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.residenciaUbicacionId = :residenciaUbicacionId"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByResidenciaUbicacionValor", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.residenciaUbicacionValor = :residenciaUbicacionValor"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByCntPrestadorSedesId", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.cntPrestadorSedesId = :cntPrestadorSedesId"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByCntPrestadorSedesValor", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.cntPrestadorSedesValor = :cntPrestadorSedesValor"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByCorreoElectronico", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByTipo", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByOrigenGeneracion", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.origenGeneracion = :origenGeneracion"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByFechaInicioVigencia", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.fechaInicioVigencia = :fechaInicioVigencia"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByFechaFinVigencia", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.fechaFinVigencia = :fechaFinVigencia"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByDiasVigencia", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.diasVigencia = :diasVigencia"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByNombreArchivo", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByRuta", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByUsuarioCrea", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByTerminalCrea", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegAfiliadoCertificados.findByFechaHoraCrea", query = "SELECT a FROM AsegAfiliadoCertificados a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AsegAfiliadoCertificados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "id_afiliado")
    private String idAfiliado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_documento_id")
    private int maeTipoDocumentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
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
    @Size(min = 1, max = 32)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 32)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 32)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "mae_subgrupo_sisben_id")
    private Integer maeSubgrupoSisbenId;
    @Size(max = 16)
    @Column(name = "mae_subgrupo_sisben_codigo")
    private String maeSubgrupoSisbenCodigo;
    @Size(max = 128)
    @Column(name = "mae_subgrupo_sisben_valor")
    private String maeSubgrupoSisbenValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_nivel_id")
    private int maeNivelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_nivel_codigo")
    private String maeNivelCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_nivel_valor")
    private String maeNivelValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_grupo_poblacional_id")
    private int maeGrupoPoblacionalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_grupo_poblacional_codigo")
    private String maeGrupoPoblacionalCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_grupo_poblacional_valor")
    private String maeGrupoPoblacionalValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_afiliacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_regimen_id")
    private int maeRegimenId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_regimen_valor")
    private String maeRegimenValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_regimen_descripcion")
    private String maeRegimenDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_estado_afiliacion_id")
    private int maeEstadoAfiliacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_estado_afiliacion_codigo")
    private String maeEstadoAfiliacionCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_estado_afiliacion_valor")
    private String maeEstadoAfiliacionValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modelo_liquidacion")
    private int modeloLiquidacion;
    @Column(name = "fecha_retiro")
    @Temporal(TemporalType.DATE)
    private Date fechaRetiro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semana_afiliacion")
    private int semanaAfiliacion;
    @Size(max = 512)
    @Column(name = "direccion_afiliado")
    private String direccionAfiliado;
    @Size(max = 32)
    @Column(name = "telefono_afiliado")
    private String telefonoAfiliado;
    @Size(max = 32)
    @Column(name = "celular_afiliado")
    private String celularAfiliado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "afiliacion_ubicacion_id")
    private int afiliacionUbicacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "afiliacion_ubicacion_valor")
    private String afiliacionUbicacionValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "residencia_ubicacion_id")
    private int residenciaUbicacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "residencia_ubicacion_valor")
    private String residenciaUbicacionValor;
    @Column(name = "cnt_prestador_sedes_id")
    private Integer cntPrestadorSedesId;
    @Size(max = 256)
    @Column(name = "cnt_prestador_sedes_valor")
    private String cntPrestadorSedesValor;
    @Size(max = 512)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen_generacion")
    private int origenGeneracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_vigencia")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin_vigencia")
    @Temporal(TemporalType.DATE)
    private Date fechaFinVigencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dias_vigencia")
    private int diasVigencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ruta")
    private String ruta;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;

    public AsegAfiliadoCertificados() {
    }

    public AsegAfiliadoCertificados(Integer id) {
        this.id = id;
    }

    public AsegAfiliadoCertificados(Integer id, String idAfiliado, int maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String numeroDocumento, String primerApellido, String primerNombre, Date fechaNacimiento, int maeNivelId, String maeNivelCodigo, String maeNivelValor, int maeGrupoPoblacionalId, String maeGrupoPoblacionalCodigo, String maeGrupoPoblacionalValor, Date fechaAfiliacion, int maeRegimenId, String maeRegimenValor, String maeRegimenDescripcion, int maeEstadoAfiliacionId, String maeEstadoAfiliacionCodigo, String maeEstadoAfiliacionValor, int modeloLiquidacion, int semanaAfiliacion, int afiliacionUbicacionId, String afiliacionUbicacionValor, int residenciaUbicacionId, String residenciaUbicacionValor, int tipo, int origenGeneracion, Date fechaInicioVigencia, Date fechaFinVigencia, int diasVigencia, String nombreArchivo, String ruta, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.idAfiliado = idAfiliado;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.numeroDocumento = numeroDocumento;
        this.primerApellido = primerApellido;
        this.primerNombre = primerNombre;
        this.fechaNacimiento = fechaNacimiento;
        this.maeNivelId = maeNivelId;
        this.maeNivelCodigo = maeNivelCodigo;
        this.maeNivelValor = maeNivelValor;
        this.maeGrupoPoblacionalId = maeGrupoPoblacionalId;
        this.maeGrupoPoblacionalCodigo = maeGrupoPoblacionalCodigo;
        this.maeGrupoPoblacionalValor = maeGrupoPoblacionalValor;
        this.fechaAfiliacion = fechaAfiliacion;
        this.maeRegimenId = maeRegimenId;
        this.maeRegimenValor = maeRegimenValor;
        this.maeRegimenDescripcion = maeRegimenDescripcion;
        this.maeEstadoAfiliacionId = maeEstadoAfiliacionId;
        this.maeEstadoAfiliacionCodigo = maeEstadoAfiliacionCodigo;
        this.maeEstadoAfiliacionValor = maeEstadoAfiliacionValor;
        this.modeloLiquidacion = modeloLiquidacion;
        this.semanaAfiliacion = semanaAfiliacion;
        this.afiliacionUbicacionId = afiliacionUbicacionId;
        this.afiliacionUbicacionValor = afiliacionUbicacionValor;
        this.residenciaUbicacionId = residenciaUbicacionId;
        this.residenciaUbicacionValor = residenciaUbicacionValor;
        this.tipo = tipo;
        this.origenGeneracion = origenGeneracion;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
        this.diasVigencia = diasVigencia;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
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

    public String getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(String idAfiliado) {
        this.idAfiliado = idAfiliado;
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

    public Integer getMaeSubgrupoSisbenId() {
        return maeSubgrupoSisbenId;
    }

    public void setMaeSubgrupoSisbenId(Integer maeSubgrupoSisbenId) {
        this.maeSubgrupoSisbenId = maeSubgrupoSisbenId;
    }

    public String getMaeSubgrupoSisbenCodigo() {
        return maeSubgrupoSisbenCodigo;
    }

    public void setMaeSubgrupoSisbenCodigo(String maeSubgrupoSisbenCodigo) {
        this.maeSubgrupoSisbenCodigo = maeSubgrupoSisbenCodigo;
    }

    public String getMaeSubgrupoSisbenValor() {
        return maeSubgrupoSisbenValor;
    }

    public void setMaeSubgrupoSisbenValor(String maeSubgrupoSisbenValor) {
        this.maeSubgrupoSisbenValor = maeSubgrupoSisbenValor;
    }

    public int getMaeNivelId() {
        return maeNivelId;
    }

    public void setMaeNivelId(int maeNivelId) {
        this.maeNivelId = maeNivelId;
    }

    public String getMaeNivelCodigo() {
        return maeNivelCodigo;
    }

    public void setMaeNivelCodigo(String maeNivelCodigo) {
        this.maeNivelCodigo = maeNivelCodigo;
    }

    public String getMaeNivelValor() {
        return maeNivelValor;
    }

    public void setMaeNivelValor(String maeNivelValor) {
        this.maeNivelValor = maeNivelValor;
    }

    public int getMaeGrupoPoblacionalId() {
        return maeGrupoPoblacionalId;
    }

    public void setMaeGrupoPoblacionalId(int maeGrupoPoblacionalId) {
        this.maeGrupoPoblacionalId = maeGrupoPoblacionalId;
    }

    public String getMaeGrupoPoblacionalCodigo() {
        return maeGrupoPoblacionalCodigo;
    }

    public void setMaeGrupoPoblacionalCodigo(String maeGrupoPoblacionalCodigo) {
        this.maeGrupoPoblacionalCodigo = maeGrupoPoblacionalCodigo;
    }

    public String getMaeGrupoPoblacionalValor() {
        return maeGrupoPoblacionalValor;
    }

    public void setMaeGrupoPoblacionalValor(String maeGrupoPoblacionalValor) {
        this.maeGrupoPoblacionalValor = maeGrupoPoblacionalValor;
    }

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public String getMaeRegimenDescripcion() {
        return maeRegimenDescripcion;
    }

    public void setMaeRegimenDescripcion(String maeRegimenDescripcion) {
        this.maeRegimenDescripcion = maeRegimenDescripcion;
    }

    public int getMaeEstadoAfiliacionId() {
        return maeEstadoAfiliacionId;
    }

    public void setMaeEstadoAfiliacionId(int maeEstadoAfiliacionId) {
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

    public int getModeloLiquidacion() {
        return modeloLiquidacion;
    }

    public void setModeloLiquidacion(int modeloLiquidacion) {
        this.modeloLiquidacion = modeloLiquidacion;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public int getSemanaAfiliacion() {
        return semanaAfiliacion;
    }

    public void setSemanaAfiliacion(int semanaAfiliacion) {
        this.semanaAfiliacion = semanaAfiliacion;
    }

    public String getDireccionAfiliado() {
        return direccionAfiliado;
    }

    public void setDireccionAfiliado(String direccionAfiliado) {
        this.direccionAfiliado = direccionAfiliado;
    }

    public String getTelefonoAfiliado() {
        return telefonoAfiliado;
    }

    public void setTelefonoAfiliado(String telefonoAfiliado) {
        this.telefonoAfiliado = telefonoAfiliado;
    }

    public String getCelularAfiliado() {
        return celularAfiliado;
    }

    public void setCelularAfiliado(String celularAfiliado) {
        this.celularAfiliado = celularAfiliado;
    }

    public int getAfiliacionUbicacionId() {
        return afiliacionUbicacionId;
    }

    public void setAfiliacionUbicacionId(int afiliacionUbicacionId) {
        this.afiliacionUbicacionId = afiliacionUbicacionId;
    }

    public String getAfiliacionUbicacionValor() {
        return afiliacionUbicacionValor;
    }

    public void setAfiliacionUbicacionValor(String afiliacionUbicacionValor) {
        this.afiliacionUbicacionValor = afiliacionUbicacionValor;
    }

    public int getResidenciaUbicacionId() {
        return residenciaUbicacionId;
    }

    public void setResidenciaUbicacionId(int residenciaUbicacionId) {
        this.residenciaUbicacionId = residenciaUbicacionId;
    }

    public String getResidenciaUbicacionValor() {
        return residenciaUbicacionValor;
    }

    public void setResidenciaUbicacionValor(String residenciaUbicacionValor) {
        this.residenciaUbicacionValor = residenciaUbicacionValor;
    }

    public Integer getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(Integer cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public String getCntPrestadorSedesValor() {
        return cntPrestadorSedesValor;
    }

    public void setCntPrestadorSedesValor(String cntPrestadorSedesValor) {
        this.cntPrestadorSedesValor = cntPrestadorSedesValor;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getOrigenGeneracion() {
        return origenGeneracion;
    }

    public void setOrigenGeneracion(int origenGeneracion) {
        this.origenGeneracion = origenGeneracion;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public int getDiasVigencia() {
        return diasVigencia;
    }

    public void setDiasVigencia(int diasVigencia) {
        this.diasVigencia = diasVigencia;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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
        if (!(object instanceof AsegAfiliadoCertificados)) {
            return false;
        }
        AsegAfiliadoCertificados other = (AsegAfiliadoCertificados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegAfiliadoCertificados[ id=" + id + " ]";
    }
    
}
