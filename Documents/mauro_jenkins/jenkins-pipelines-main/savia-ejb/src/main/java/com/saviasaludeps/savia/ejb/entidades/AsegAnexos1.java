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
@Table(name = "aseg_anexos1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegAnexos1.findAll", query = "SELECT a FROM AsegAnexos1 a"),
    @NamedQuery(name = "AsegAnexos1.findById", query = "SELECT a FROM AsegAnexos1 a WHERE a.id = :id"),
    @NamedQuery(name = "AsegAnexos1.findByTipoDocumentoInconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.tipoDocumentoInconsistencia = :tipoDocumentoInconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByNumeroDocumentoInconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.numeroDocumentoInconsistencia = :numeroDocumentoInconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByApellido1Inconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.apellido1Inconsistencia = :apellido1Inconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByApellido2Inconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.apellido2Inconsistencia = :apellido2Inconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByNombre1Inconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.nombre1Inconsistencia = :nombre1Inconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByNombre2Inconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.nombre2Inconsistencia = :nombre2Inconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByFechaNacimientoInconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.fechaNacimientoInconsistencia = :fechaNacimientoInconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByFechaExpedicionInconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.fechaExpedicionInconsistencia = :fechaExpedicionInconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findBySexoInconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.sexoInconsistencia = :sexoInconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByDireccionInconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.direccionInconsistencia = :direccionInconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByTelefonoInconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.telefonoInconsistencia = :telefonoInconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByCelularInconsistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.celularInconsistencia = :celularInconsistencia"),
    @NamedQuery(name = "AsegAnexos1.findByTipoDocumentoNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.tipoDocumentoNuevo = :tipoDocumentoNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByNumeroDocumentoNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.numeroDocumentoNuevo = :numeroDocumentoNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByApellido1Nuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.apellido1Nuevo = :apellido1Nuevo"),
    @NamedQuery(name = "AsegAnexos1.findByApellido2Nuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.apellido2Nuevo = :apellido2Nuevo"),
    @NamedQuery(name = "AsegAnexos1.findByNombre1Nuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.nombre1Nuevo = :nombre1Nuevo"),
    @NamedQuery(name = "AsegAnexos1.findByNombre2Nuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.nombre2Nuevo = :nombre2Nuevo"),
    @NamedQuery(name = "AsegAnexos1.findByFechaNacimientoNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.fechaNacimientoNuevo = :fechaNacimientoNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByFechaExpedicionNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.fechaExpedicionNuevo = :fechaExpedicionNuevo"),
    @NamedQuery(name = "AsegAnexos1.findBySexoNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.sexoNuevo = :sexoNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByDireccionNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.direccionNuevo = :direccionNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByTelefonoNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.telefonoNuevo = :telefonoNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByCelularNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.celularNuevo = :celularNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByUsuarioModifica", query = "SELECT a FROM AsegAnexos1 a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AsegAnexos1.findByObservacion", query = "SELECT a FROM AsegAnexos1 a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AsegAnexos1.findByEstado", query = "SELECT a FROM AsegAnexos1 a WHERE a.estado = :estado"),
    @NamedQuery(name = "AsegAnexos1.findByTratamientoDatosAutoriza", query = "SELECT a FROM AsegAnexos1 a WHERE a.tratamientoDatosAutoriza = :tratamientoDatosAutoriza"),
    @NamedQuery(name = "AsegAnexos1.findByTratamientoDatosFechaHora", query = "SELECT a FROM AsegAnexos1 a WHERE a.tratamientoDatosFechaHora = :tratamientoDatosFechaHora"),
    @NamedQuery(name = "AsegAnexos1.findByVersion", query = "SELECT a FROM AsegAnexos1 a WHERE a.version = :version"),
    @NamedQuery(name = "AsegAnexos1.findByCntPrestadoresId", query = "SELECT a FROM AsegAnexos1 a WHERE a.cntPrestadoresId = :cntPrestadoresId"),
    @NamedQuery(name = "AsegAnexos1.findByCntPrestadorSedesId", query = "SELECT a FROM AsegAnexos1 a WHERE a.cntPrestadorSedesId = :cntPrestadorSedesId"),
    @NamedQuery(name = "AsegAnexos1.findByEmailNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.emailNuevo = :emailNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByDireccionAlternaContactoNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.direccionAlternaContactoNuevo = :direccionAlternaContactoNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByNombreContactoEmergenciaNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.nombreContactoEmergenciaNuevo = :nombreContactoEmergenciaNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByTelefonoContactoEmergenciaNuevo", query = "SELECT a FROM AsegAnexos1 a WHERE a.telefonoContactoEmergenciaNuevo = :telefonoContactoEmergenciaNuevo"),
    @NamedQuery(name = "AsegAnexos1.findByEmailIncosistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.emailIncosistencia = :emailIncosistencia"),
    @NamedQuery(name = "AsegAnexos1.findByDireccionAlternaContactoIncosistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.direccionAlternaContactoIncosistencia = :direccionAlternaContactoIncosistencia"),
    @NamedQuery(name = "AsegAnexos1.findByNombreContactoEmergenciaIncosistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.nombreContactoEmergenciaIncosistencia = :nombreContactoEmergenciaIncosistencia"),
    @NamedQuery(name = "AsegAnexos1.findByTelefonoContactoEmergenciaIncosistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.telefonoContactoEmergenciaIncosistencia = :telefonoContactoEmergenciaIncosistencia"),
    @NamedQuery(name = "AsegAnexos1.findByResidenciaUbicacionIdIncosistencia", query = "SELECT a FROM AsegAnexos1 a WHERE a.residenciaUbicacionIdIncosistencia = :residenciaUbicacionIdIncosistencia"),
    @NamedQuery(name = "AsegAnexos1.findByConsecutivo", query = "SELECT a FROM AsegAnexos1 a WHERE a.consecutivo = :consecutivo"),
    @NamedQuery(name = "AsegAnexos1.findByUsuarioCrea", query = "SELECT a FROM AsegAnexos1 a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegAnexos1.findByTerminalCrea", query = "SELECT a FROM AsegAnexos1 a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegAnexos1.findByFechaHoraCrea", query = "SELECT a FROM AsegAnexos1 a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegAnexos1.findByTerminalModifica", query = "SELECT a FROM AsegAnexos1 a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AsegAnexos1.findByFechaHoraModifica", query = "SELECT a FROM AsegAnexos1 a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AsegAnexos1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_documento_inconsistencia")
    private int tipoDocumentoInconsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_documento_inconsistencia")
    private int numeroDocumentoInconsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apellido1_inconsistencia")
    private int apellido1Inconsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "apellido2_inconsistencia")
    private int apellido2Inconsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre1_inconsistencia")
    private int nombre1Inconsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nombre2_inconsistencia")
    private int nombre2Inconsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento_inconsistencia")
    private int fechaNacimientoInconsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_expedicion_inconsistencia")
    private int fechaExpedicionInconsistencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexo_inconsistencia")
    private int sexoInconsistencia;
    @Column(name = "direccion_inconsistencia")
    private Integer direccionInconsistencia;
    @Column(name = "telefono_inconsistencia")
    private Integer telefonoInconsistencia;
    @Column(name = "celular_inconsistencia")
    private Integer celularInconsistencia;
    @Size(max = 8)
    @Column(name = "tipo_documento_nuevo")
    private String tipoDocumentoNuevo;
    @Size(max = 16)
    @Column(name = "numero_documento_nuevo")
    private String numeroDocumentoNuevo;
    @Size(max = 64)
    @Column(name = "apellido1_nuevo")
    private String apellido1Nuevo;
    @Size(max = 64)
    @Column(name = "apellido2_nuevo")
    private String apellido2Nuevo;
    @Size(max = 64)
    @Column(name = "nombre1_nuevo")
    private String nombre1Nuevo;
    @Size(max = 64)
    @Column(name = "nombre2_nuevo")
    private String nombre2Nuevo;
    @Column(name = "fecha_nacimiento_nuevo")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoNuevo;
    @Column(name = "fecha_expedicion_nuevo")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicionNuevo;
    @Size(max = 8)
    @Column(name = "sexo_nuevo")
    private String sexoNuevo;
    @Size(max = 256)
    @Column(name = "direccion_nuevo")
    private String direccionNuevo;
    @Size(max = 16)
    @Column(name = "telefono_nuevo")
    private String telefonoNuevo;
    @Size(max = 16)
    @Column(name = "celular_nuevo")
    private String celularNuevo;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 512)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "tratamiento_datos_autoriza")
    private Boolean tratamientoDatosAutoriza;
    @Column(name = "tratamiento_datos_fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tratamientoDatosFechaHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private boolean version;
    @Column(name = "cnt_prestadores_id")
    private Integer cntPrestadoresId;
    @Column(name = "cnt_prestador_sedes_id")
    private Integer cntPrestadorSedesId;
    @Size(max = 128)
    @Column(name = "email_nuevo")
    private String emailNuevo;
    @Size(max = 512)
    @Column(name = "direccion_alterna_contacto_nuevo")
    private String direccionAlternaContactoNuevo;
    @Size(max = 128)
    @Column(name = "nombre_contacto_emergencia__nuevo")
    private String nombreContactoEmergenciaNuevo;
    @Size(max = 16)
    @Column(name = "telefono_contacto_emergencia__nuevo")
    private String telefonoContactoEmergenciaNuevo;
    @Column(name = "email_incosistencia")
    private Integer emailIncosistencia;
    @Column(name = "direccion_alterna_contacto_incosistencia")
    private Integer direccionAlternaContactoIncosistencia;
    @Column(name = "nombre_contacto_emergencia_incosistencia")
    private Integer nombreContactoEmergenciaIncosistencia;
    @Column(name = "telefono_contacto_emergencia_incosistencia")
    private Integer telefonoContactoEmergenciaIncosistencia;
    @Column(name = "residencia_ubicacion_id_incosistencia")
    private Integer residenciaUbicacionIdIncosistencia;
    @Size(max = 64)
    @Column(name = "consecutivo")
    private String consecutivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "residencia_ubicacion_id_nuevo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones residenciaUbicacionIdNuevo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asegAnexos1Id", fetch = FetchType.LAZY)
    private List<AsegAnexo1Adjuntos> asegAnexo1AdjuntosList;

    public AsegAnexos1() {
    }

    public AsegAnexos1(Integer id) {
        this.id = id;
    }

    public AsegAnexos1(Integer id, int tipoDocumentoInconsistencia, int numeroDocumentoInconsistencia, int apellido1Inconsistencia, int apellido2Inconsistencia, int nombre1Inconsistencia, int nombre2Inconsistencia, int fechaNacimientoInconsistencia, int fechaExpedicionInconsistencia, int sexoInconsistencia, int estado, boolean version, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoDocumentoInconsistencia = tipoDocumentoInconsistencia;
        this.numeroDocumentoInconsistencia = numeroDocumentoInconsistencia;
        this.apellido1Inconsistencia = apellido1Inconsistencia;
        this.apellido2Inconsistencia = apellido2Inconsistencia;
        this.nombre1Inconsistencia = nombre1Inconsistencia;
        this.nombre2Inconsistencia = nombre2Inconsistencia;
        this.fechaNacimientoInconsistencia = fechaNacimientoInconsistencia;
        this.fechaExpedicionInconsistencia = fechaExpedicionInconsistencia;
        this.sexoInconsistencia = sexoInconsistencia;
        this.estado = estado;
        this.version = version;
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

    public int getTipoDocumentoInconsistencia() {
        return tipoDocumentoInconsistencia;
    }

    public void setTipoDocumentoInconsistencia(int tipoDocumentoInconsistencia) {
        this.tipoDocumentoInconsistencia = tipoDocumentoInconsistencia;
    }

    public int getNumeroDocumentoInconsistencia() {
        return numeroDocumentoInconsistencia;
    }

    public void setNumeroDocumentoInconsistencia(int numeroDocumentoInconsistencia) {
        this.numeroDocumentoInconsistencia = numeroDocumentoInconsistencia;
    }

    public int getApellido1Inconsistencia() {
        return apellido1Inconsistencia;
    }

    public void setApellido1Inconsistencia(int apellido1Inconsistencia) {
        this.apellido1Inconsistencia = apellido1Inconsistencia;
    }

    public int getApellido2Inconsistencia() {
        return apellido2Inconsistencia;
    }

    public void setApellido2Inconsistencia(int apellido2Inconsistencia) {
        this.apellido2Inconsistencia = apellido2Inconsistencia;
    }

    public int getNombre1Inconsistencia() {
        return nombre1Inconsistencia;
    }

    public void setNombre1Inconsistencia(int nombre1Inconsistencia) {
        this.nombre1Inconsistencia = nombre1Inconsistencia;
    }

    public int getNombre2Inconsistencia() {
        return nombre2Inconsistencia;
    }

    public void setNombre2Inconsistencia(int nombre2Inconsistencia) {
        this.nombre2Inconsistencia = nombre2Inconsistencia;
    }

    public int getFechaNacimientoInconsistencia() {
        return fechaNacimientoInconsistencia;
    }

    public void setFechaNacimientoInconsistencia(int fechaNacimientoInconsistencia) {
        this.fechaNacimientoInconsistencia = fechaNacimientoInconsistencia;
    }

    public int getFechaExpedicionInconsistencia() {
        return fechaExpedicionInconsistencia;
    }

    public void setFechaExpedicionInconsistencia(int fechaExpedicionInconsistencia) {
        this.fechaExpedicionInconsistencia = fechaExpedicionInconsistencia;
    }

    public int getSexoInconsistencia() {
        return sexoInconsistencia;
    }

    public void setSexoInconsistencia(int sexoInconsistencia) {
        this.sexoInconsistencia = sexoInconsistencia;
    }

    public Integer getDireccionInconsistencia() {
        return direccionInconsistencia;
    }

    public void setDireccionInconsistencia(Integer direccionInconsistencia) {
        this.direccionInconsistencia = direccionInconsistencia;
    }

    public Integer getTelefonoInconsistencia() {
        return telefonoInconsistencia;
    }

    public void setTelefonoInconsistencia(Integer telefonoInconsistencia) {
        this.telefonoInconsistencia = telefonoInconsistencia;
    }

    public Integer getCelularInconsistencia() {
        return celularInconsistencia;
    }

    public void setCelularInconsistencia(Integer celularInconsistencia) {
        this.celularInconsistencia = celularInconsistencia;
    }

    public String getTipoDocumentoNuevo() {
        return tipoDocumentoNuevo;
    }

    public void setTipoDocumentoNuevo(String tipoDocumentoNuevo) {
        this.tipoDocumentoNuevo = tipoDocumentoNuevo;
    }

    public String getNumeroDocumentoNuevo() {
        return numeroDocumentoNuevo;
    }

    public void setNumeroDocumentoNuevo(String numeroDocumentoNuevo) {
        this.numeroDocumentoNuevo = numeroDocumentoNuevo;
    }

    public String getApellido1Nuevo() {
        return apellido1Nuevo;
    }

    public void setApellido1Nuevo(String apellido1Nuevo) {
        this.apellido1Nuevo = apellido1Nuevo;
    }

    public String getApellido2Nuevo() {
        return apellido2Nuevo;
    }

    public void setApellido2Nuevo(String apellido2Nuevo) {
        this.apellido2Nuevo = apellido2Nuevo;
    }

    public String getNombre1Nuevo() {
        return nombre1Nuevo;
    }

    public void setNombre1Nuevo(String nombre1Nuevo) {
        this.nombre1Nuevo = nombre1Nuevo;
    }

    public String getNombre2Nuevo() {
        return nombre2Nuevo;
    }

    public void setNombre2Nuevo(String nombre2Nuevo) {
        this.nombre2Nuevo = nombre2Nuevo;
    }

    public Date getFechaNacimientoNuevo() {
        return fechaNacimientoNuevo;
    }

    public void setFechaNacimientoNuevo(Date fechaNacimientoNuevo) {
        this.fechaNacimientoNuevo = fechaNacimientoNuevo;
    }

    public Date getFechaExpedicionNuevo() {
        return fechaExpedicionNuevo;
    }

    public void setFechaExpedicionNuevo(Date fechaExpedicionNuevo) {
        this.fechaExpedicionNuevo = fechaExpedicionNuevo;
    }

    public String getSexoNuevo() {
        return sexoNuevo;
    }

    public void setSexoNuevo(String sexoNuevo) {
        this.sexoNuevo = sexoNuevo;
    }

    public String getDireccionNuevo() {
        return direccionNuevo;
    }

    public void setDireccionNuevo(String direccionNuevo) {
        this.direccionNuevo = direccionNuevo;
    }

    public String getTelefonoNuevo() {
        return telefonoNuevo;
    }

    public void setTelefonoNuevo(String telefonoNuevo) {
        this.telefonoNuevo = telefonoNuevo;
    }

    public String getCelularNuevo() {
        return celularNuevo;
    }

    public void setCelularNuevo(String celularNuevo) {
        this.celularNuevo = celularNuevo;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Boolean getTratamientoDatosAutoriza() {
        return tratamientoDatosAutoriza;
    }

    public void setTratamientoDatosAutoriza(Boolean tratamientoDatosAutoriza) {
        this.tratamientoDatosAutoriza = tratamientoDatosAutoriza;
    }

    public Date getTratamientoDatosFechaHora() {
        return tratamientoDatosFechaHora;
    }

    public void setTratamientoDatosFechaHora(Date tratamientoDatosFechaHora) {
        this.tratamientoDatosFechaHora = tratamientoDatosFechaHora;
    }

    public boolean getVersion() {
        return version;
    }

    public void setVersion(boolean version) {
        this.version = version;
    }

    public Integer getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(Integer cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public Integer getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(Integer cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public String getEmailNuevo() {
        return emailNuevo;
    }

    public void setEmailNuevo(String emailNuevo) {
        this.emailNuevo = emailNuevo;
    }

    public String getDireccionAlternaContactoNuevo() {
        return direccionAlternaContactoNuevo;
    }

    public void setDireccionAlternaContactoNuevo(String direccionAlternaContactoNuevo) {
        this.direccionAlternaContactoNuevo = direccionAlternaContactoNuevo;
    }

    public String getNombreContactoEmergenciaNuevo() {
        return nombreContactoEmergenciaNuevo;
    }

    public void setNombreContactoEmergenciaNuevo(String nombreContactoEmergenciaNuevo) {
        this.nombreContactoEmergenciaNuevo = nombreContactoEmergenciaNuevo;
    }

    public String getTelefonoContactoEmergenciaNuevo() {
        return telefonoContactoEmergenciaNuevo;
    }

    public void setTelefonoContactoEmergenciaNuevo(String telefonoContactoEmergenciaNuevo) {
        this.telefonoContactoEmergenciaNuevo = telefonoContactoEmergenciaNuevo;
    }

    public Integer getEmailIncosistencia() {
        return emailIncosistencia;
    }

    public void setEmailIncosistencia(Integer emailIncosistencia) {
        this.emailIncosistencia = emailIncosistencia;
    }

    public Integer getDireccionAlternaContactoIncosistencia() {
        return direccionAlternaContactoIncosistencia;
    }

    public void setDireccionAlternaContactoIncosistencia(Integer direccionAlternaContactoIncosistencia) {
        this.direccionAlternaContactoIncosistencia = direccionAlternaContactoIncosistencia;
    }

    public Integer getNombreContactoEmergenciaIncosistencia() {
        return nombreContactoEmergenciaIncosistencia;
    }

    public void setNombreContactoEmergenciaIncosistencia(Integer nombreContactoEmergenciaIncosistencia) {
        this.nombreContactoEmergenciaIncosistencia = nombreContactoEmergenciaIncosistencia;
    }

    public Integer getTelefonoContactoEmergenciaIncosistencia() {
        return telefonoContactoEmergenciaIncosistencia;
    }

    public void setTelefonoContactoEmergenciaIncosistencia(Integer telefonoContactoEmergenciaIncosistencia) {
        this.telefonoContactoEmergenciaIncosistencia = telefonoContactoEmergenciaIncosistencia;
    }

    public Integer getResidenciaUbicacionIdIncosistencia() {
        return residenciaUbicacionIdIncosistencia;
    }

    public void setResidenciaUbicacionIdIncosistencia(Integer residenciaUbicacionIdIncosistencia) {
        this.residenciaUbicacionIdIncosistencia = residenciaUbicacionIdIncosistencia;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
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

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public GnUbicaciones getResidenciaUbicacionIdNuevo() {
        return residenciaUbicacionIdNuevo;
    }

    public void setResidenciaUbicacionIdNuevo(GnUbicaciones residenciaUbicacionIdNuevo) {
        this.residenciaUbicacionIdNuevo = residenciaUbicacionIdNuevo;
    }

    @XmlTransient
    public List<AsegAnexo1Adjuntos> getAsegAnexo1AdjuntosList() {
        return asegAnexo1AdjuntosList;
    }

    public void setAsegAnexo1AdjuntosList(List<AsegAnexo1Adjuntos> asegAnexo1AdjuntosList) {
        this.asegAnexo1AdjuntosList = asegAnexo1AdjuntosList;
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
        if (!(object instanceof AsegAnexos1)) {
            return false;
        }
        AsegAnexos1 other = (AsegAnexos1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegAnexos1[ id=" + id + " ]";
    }
    
}
