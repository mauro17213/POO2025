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
@Table(name = "mpc_prescripciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpcPrescripciones.findAll", query = "SELECT m FROM MpcPrescripciones m"),
    @NamedQuery(name = "MpcPrescripciones.findById", query = "SELECT m FROM MpcPrescripciones m WHERE m.id = :id"),
    @NamedQuery(name = "MpcPrescripciones.findByEstado", query = "SELECT m FROM MpcPrescripciones m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpcPrescripciones.findByMaePrescriptorTipoDocumentoId", query = "SELECT m FROM MpcPrescripciones m WHERE m.maePrescriptorTipoDocumentoId = :maePrescriptorTipoDocumentoId"),
    @NamedQuery(name = "MpcPrescripciones.findByMaePrescriptorTipoDocumentoCodigo", query = "SELECT m FROM MpcPrescripciones m WHERE m.maePrescriptorTipoDocumentoCodigo = :maePrescriptorTipoDocumentoCodigo"),
    @NamedQuery(name = "MpcPrescripciones.findByMaePrescriptorTipoDocumentoValor", query = "SELECT m FROM MpcPrescripciones m WHERE m.maePrescriptorTipoDocumentoValor = :maePrescriptorTipoDocumentoValor"),
    @NamedQuery(name = "MpcPrescripciones.findByPrescriptorNumeroDocumento", query = "SELECT m FROM MpcPrescripciones m WHERE m.prescriptorNumeroDocumento = :prescriptorNumeroDocumento"),
    @NamedQuery(name = "MpcPrescripciones.findByPrescriptorCodigoHabilitacion", query = "SELECT m FROM MpcPrescripciones m WHERE m.prescriptorCodigoHabilitacion = :prescriptorCodigoHabilitacion"),
    @NamedQuery(name = "MpcPrescripciones.findByPrescriptorCorreoElectronico", query = "SELECT m FROM MpcPrescripciones m WHERE m.prescriptorCorreoElectronico = :prescriptorCorreoElectronico"),
    @NamedQuery(name = "MpcPrescripciones.findByMaeAfiliadoTipoDocumentoId", query = "SELECT m FROM MpcPrescripciones m WHERE m.maeAfiliadoTipoDocumentoId = :maeAfiliadoTipoDocumentoId"),
    @NamedQuery(name = "MpcPrescripciones.findByMaeAfiliadoTipoDocumentoCodigo", query = "SELECT m FROM MpcPrescripciones m WHERE m.maeAfiliadoTipoDocumentoCodigo = :maeAfiliadoTipoDocumentoCodigo"),
    @NamedQuery(name = "MpcPrescripciones.findByMaeAfiliadoTipoDocumentoValor", query = "SELECT m FROM MpcPrescripciones m WHERE m.maeAfiliadoTipoDocumentoValor = :maeAfiliadoTipoDocumentoValor"),
    @NamedQuery(name = "MpcPrescripciones.findByAfiliadoNumeroDocumento", query = "SELECT m FROM MpcPrescripciones m WHERE m.afiliadoNumeroDocumento = :afiliadoNumeroDocumento"),
    @NamedQuery(name = "MpcPrescripciones.findByAfiliadoPrimerNombre", query = "SELECT m FROM MpcPrescripciones m WHERE m.afiliadoPrimerNombre = :afiliadoPrimerNombre"),
    @NamedQuery(name = "MpcPrescripciones.findByAfiliadoSegundoNombre", query = "SELECT m FROM MpcPrescripciones m WHERE m.afiliadoSegundoNombre = :afiliadoSegundoNombre"),
    @NamedQuery(name = "MpcPrescripciones.findByAfiliadoPrimerApellido", query = "SELECT m FROM MpcPrescripciones m WHERE m.afiliadoPrimerApellido = :afiliadoPrimerApellido"),
    @NamedQuery(name = "MpcPrescripciones.findByAfiliadoSegundoApellido", query = "SELECT m FROM MpcPrescripciones m WHERE m.afiliadoSegundoApellido = :afiliadoSegundoApellido"),
    @NamedQuery(name = "MpcPrescripciones.findByFechaHora", query = "SELECT m FROM MpcPrescripciones m WHERE m.fechaHora = :fechaHora"),
    @NamedQuery(name = "MpcPrescripciones.findByRecobrante", query = "SELECT m FROM MpcPrescripciones m WHERE m.recobrante = :recobrante"),
    @NamedQuery(name = "MpcPrescripciones.findByTipoTecnologia", query = "SELECT m FROM MpcPrescripciones m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpcPrescripciones.findByActaJuantaProfesionales", query = "SELECT m FROM MpcPrescripciones m WHERE m.actaJuantaProfesionales = :actaJuantaProfesionales"),
    @NamedQuery(name = "MpcPrescripciones.findByConcentimientoInformado", query = "SELECT m FROM MpcPrescripciones m WHERE m.concentimientoInformado = :concentimientoInformado"),
    @NamedQuery(name = "MpcPrescripciones.findByFormatoIntegralidad", query = "SELECT m FROM MpcPrescripciones m WHERE m.formatoIntegralidad = :formatoIntegralidad"),
    @NamedQuery(name = "MpcPrescripciones.findByRechazoJustificacion", query = "SELECT m FROM MpcPrescripciones m WHERE m.rechazoJustificacion = :rechazoJustificacion"),
    @NamedQuery(name = "MpcPrescripciones.findByConsecutivo", query = "SELECT m FROM MpcPrescripciones m WHERE m.consecutivo = :consecutivo"),
    @NamedQuery(name = "MpcPrescripciones.findByMaTecnologiaId", query = "SELECT m FROM MpcPrescripciones m WHERE m.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "MpcPrescripciones.findByMaTecnologiaCodigo", query = "SELECT m FROM MpcPrescripciones m WHERE m.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "MpcPrescripciones.findByMaTecnologiaValor", query = "SELECT m FROM MpcPrescripciones m WHERE m.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "MpcPrescripciones.findByCantidad", query = "SELECT m FROM MpcPrescripciones m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MpcPrescripciones.findByPeriodicidad", query = "SELECT m FROM MpcPrescripciones m WHERE m.periodicidad = :periodicidad"),
    @NamedQuery(name = "MpcPrescripciones.findByNumeroEntregas", query = "SELECT m FROM MpcPrescripciones m WHERE m.numeroEntregas = :numeroEntregas"),
    @NamedQuery(name = "MpcPrescripciones.findByAmbito", query = "SELECT m FROM MpcPrescripciones m WHERE m.ambito = :ambito"),
    @NamedQuery(name = "MpcPrescripciones.findByReferenciaAmbitoAtencion", query = "SELECT m FROM MpcPrescripciones m WHERE m.referenciaAmbitoAtencion = :referenciaAmbitoAtencion"),
    @NamedQuery(name = "MpcPrescripciones.findByUsuarioCrea", query = "SELECT m FROM MpcPrescripciones m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpcPrescripciones.findByTerminalCrea", query = "SELECT m FROM MpcPrescripciones m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpcPrescripciones.findByFechaHoraCrea", query = "SELECT m FROM MpcPrescripciones m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpcPrescripciones.findByUsuarioModifica", query = "SELECT m FROM MpcPrescripciones m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpcPrescripciones.findByTerminalModifica", query = "SELECT m FROM MpcPrescripciones m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpcPrescripciones.findByFechaHoraModifica", query = "SELECT m FROM MpcPrescripciones m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MpcPrescripciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private short estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_prescriptor_tipo_documento_id")
    private int maePrescriptorTipoDocumentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_prescriptor_tipo_documento_codigo")
    private String maePrescriptorTipoDocumentoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_prescriptor_tipo_documento_valor")
    private String maePrescriptorTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "prescriptor_numero_documento")
    private String prescriptorNumeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "prescriptor_codigo_habilitacion")
    private String prescriptorCodigoHabilitacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "prescriptor_correo_electronico")
    private String prescriptorCorreoElectronico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_afiliado_tipo_documento_id")
    private int maeAfiliadoTipoDocumentoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_afiliado_tipo_documento_codigo")
    private String maeAfiliadoTipoDocumentoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_afiliado_tipo_documento_valor")
    private String maeAfiliadoTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "afiliado_numero_documento")
    private String afiliadoNumeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "afiliado_primer_nombre")
    private String afiliadoPrimerNombre;
    @Size(max = 64)
    @Column(name = "afiliado_segundo_nombre")
    private String afiliadoSegundoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "afiliado_primer_apellido")
    private String afiliadoPrimerApellido;
    @Size(max = 64)
    @Column(name = "afiliado_segundo_apellido")
    private String afiliadoSegundoApellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recobrante")
    private boolean recobrante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private short tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acta_juanta_profesionales")
    private boolean actaJuantaProfesionales;
    @Basic(optional = false)
    @NotNull
    @Column(name = "concentimiento_informado")
    private boolean concentimientoInformado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "formato_integralidad")
    private boolean formatoIntegralidad;
    @Size(max = 1024)
    @Column(name = "rechazo_justificacion")
    private String rechazoJustificacion;
    @Size(max = 32)
    @Column(name = "consecutivo")
    private String consecutivo;
    @Column(name = "ma_tecnologia_id")
    private Integer maTecnologiaId;
    @Size(max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Size(max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "periodicidad")
    private Short periodicidad;
    @Column(name = "numero_entregas")
    private Short numeroEntregas;
    @Size(max = 4)
    @Column(name = "ambito")
    private String ambito;
    @Column(name = "referencia_ambito_atencion")
    private Boolean referenciaAmbitoAtencion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpcPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpcPrescripcionAdjuntos> mpcPrescripcionAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mpcPrescripcionesId", fetch = FetchType.LAZY)
    private List<MpcProgramacionEntregas> mpcProgramacionEntregasList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "cnt_prescriptor_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrescriptorPrestadorSedesId;
    @JoinColumn(name = "cnt_direccionado_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntDireccionadoPrestadorSedesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;

    public MpcPrescripciones() {
    }

    public MpcPrescripciones(Integer id) {
        this.id = id;
    }

    public MpcPrescripciones(Integer id, short estado, int maePrescriptorTipoDocumentoId, String maePrescriptorTipoDocumentoCodigo, String maePrescriptorTipoDocumentoValor, String prescriptorNumeroDocumento, String prescriptorCodigoHabilitacion, String prescriptorCorreoElectronico, int maeAfiliadoTipoDocumentoId, String maeAfiliadoTipoDocumentoCodigo, String maeAfiliadoTipoDocumentoValor, String afiliadoNumeroDocumento, String afiliadoPrimerNombre, String afiliadoPrimerApellido, Date fechaHora, boolean recobrante, short tipoTecnologia, boolean actaJuantaProfesionales, boolean concentimientoInformado, boolean formatoIntegralidad, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.maePrescriptorTipoDocumentoId = maePrescriptorTipoDocumentoId;
        this.maePrescriptorTipoDocumentoCodigo = maePrescriptorTipoDocumentoCodigo;
        this.maePrescriptorTipoDocumentoValor = maePrescriptorTipoDocumentoValor;
        this.prescriptorNumeroDocumento = prescriptorNumeroDocumento;
        this.prescriptorCodigoHabilitacion = prescriptorCodigoHabilitacion;
        this.prescriptorCorreoElectronico = prescriptorCorreoElectronico;
        this.maeAfiliadoTipoDocumentoId = maeAfiliadoTipoDocumentoId;
        this.maeAfiliadoTipoDocumentoCodigo = maeAfiliadoTipoDocumentoCodigo;
        this.maeAfiliadoTipoDocumentoValor = maeAfiliadoTipoDocumentoValor;
        this.afiliadoNumeroDocumento = afiliadoNumeroDocumento;
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
        this.fechaHora = fechaHora;
        this.recobrante = recobrante;
        this.tipoTecnologia = tipoTecnologia;
        this.actaJuantaProfesionales = actaJuantaProfesionales;
        this.concentimientoInformado = concentimientoInformado;
        this.formatoIntegralidad = formatoIntegralidad;
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

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public int getMaePrescriptorTipoDocumentoId() {
        return maePrescriptorTipoDocumentoId;
    }

    public void setMaePrescriptorTipoDocumentoId(int maePrescriptorTipoDocumentoId) {
        this.maePrescriptorTipoDocumentoId = maePrescriptorTipoDocumentoId;
    }

    public String getMaePrescriptorTipoDocumentoCodigo() {
        return maePrescriptorTipoDocumentoCodigo;
    }

    public void setMaePrescriptorTipoDocumentoCodigo(String maePrescriptorTipoDocumentoCodigo) {
        this.maePrescriptorTipoDocumentoCodigo = maePrescriptorTipoDocumentoCodigo;
    }

    public String getMaePrescriptorTipoDocumentoValor() {
        return maePrescriptorTipoDocumentoValor;
    }

    public void setMaePrescriptorTipoDocumentoValor(String maePrescriptorTipoDocumentoValor) {
        this.maePrescriptorTipoDocumentoValor = maePrescriptorTipoDocumentoValor;
    }

    public String getPrescriptorNumeroDocumento() {
        return prescriptorNumeroDocumento;
    }

    public void setPrescriptorNumeroDocumento(String prescriptorNumeroDocumento) {
        this.prescriptorNumeroDocumento = prescriptorNumeroDocumento;
    }

    public String getPrescriptorCodigoHabilitacion() {
        return prescriptorCodigoHabilitacion;
    }

    public void setPrescriptorCodigoHabilitacion(String prescriptorCodigoHabilitacion) {
        this.prescriptorCodigoHabilitacion = prescriptorCodigoHabilitacion;
    }

    public String getPrescriptorCorreoElectronico() {
        return prescriptorCorreoElectronico;
    }

    public void setPrescriptorCorreoElectronico(String prescriptorCorreoElectronico) {
        this.prescriptorCorreoElectronico = prescriptorCorreoElectronico;
    }

    public int getMaeAfiliadoTipoDocumentoId() {
        return maeAfiliadoTipoDocumentoId;
    }

    public void setMaeAfiliadoTipoDocumentoId(int maeAfiliadoTipoDocumentoId) {
        this.maeAfiliadoTipoDocumentoId = maeAfiliadoTipoDocumentoId;
    }

    public String getMaeAfiliadoTipoDocumentoCodigo() {
        return maeAfiliadoTipoDocumentoCodigo;
    }

    public void setMaeAfiliadoTipoDocumentoCodigo(String maeAfiliadoTipoDocumentoCodigo) {
        this.maeAfiliadoTipoDocumentoCodigo = maeAfiliadoTipoDocumentoCodigo;
    }

    public String getMaeAfiliadoTipoDocumentoValor() {
        return maeAfiliadoTipoDocumentoValor;
    }

    public void setMaeAfiliadoTipoDocumentoValor(String maeAfiliadoTipoDocumentoValor) {
        this.maeAfiliadoTipoDocumentoValor = maeAfiliadoTipoDocumentoValor;
    }

    public String getAfiliadoNumeroDocumento() {
        return afiliadoNumeroDocumento;
    }

    public void setAfiliadoNumeroDocumento(String afiliadoNumeroDocumento) {
        this.afiliadoNumeroDocumento = afiliadoNumeroDocumento;
    }

    public String getAfiliadoPrimerNombre() {
        return afiliadoPrimerNombre;
    }

    public void setAfiliadoPrimerNombre(String afiliadoPrimerNombre) {
        this.afiliadoPrimerNombre = afiliadoPrimerNombre;
    }

    public String getAfiliadoSegundoNombre() {
        return afiliadoSegundoNombre;
    }

    public void setAfiliadoSegundoNombre(String afiliadoSegundoNombre) {
        this.afiliadoSegundoNombre = afiliadoSegundoNombre;
    }

    public String getAfiliadoPrimerApellido() {
        return afiliadoPrimerApellido;
    }

    public void setAfiliadoPrimerApellido(String afiliadoPrimerApellido) {
        this.afiliadoPrimerApellido = afiliadoPrimerApellido;
    }

    public String getAfiliadoSegundoApellido() {
        return afiliadoSegundoApellido;
    }

    public void setAfiliadoSegundoApellido(String afiliadoSegundoApellido) {
        this.afiliadoSegundoApellido = afiliadoSegundoApellido;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean getRecobrante() {
        return recobrante;
    }

    public void setRecobrante(boolean recobrante) {
        this.recobrante = recobrante;
    }

    public short getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(short tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public boolean getActaJuantaProfesionales() {
        return actaJuantaProfesionales;
    }

    public void setActaJuantaProfesionales(boolean actaJuantaProfesionales) {
        this.actaJuantaProfesionales = actaJuantaProfesionales;
    }

    public boolean getConcentimientoInformado() {
        return concentimientoInformado;
    }

    public void setConcentimientoInformado(boolean concentimientoInformado) {
        this.concentimientoInformado = concentimientoInformado;
    }

    public boolean getFormatoIntegralidad() {
        return formatoIntegralidad;
    }

    public void setFormatoIntegralidad(boolean formatoIntegralidad) {
        this.formatoIntegralidad = formatoIntegralidad;
    }

    public String getRechazoJustificacion() {
        return rechazoJustificacion;
    }

    public void setRechazoJustificacion(String rechazoJustificacion) {
        this.rechazoJustificacion = rechazoJustificacion;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Short getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Short periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Short getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(Short numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public Boolean getReferenciaAmbitoAtencion() {
        return referenciaAmbitoAtencion;
    }

    public void setReferenciaAmbitoAtencion(Boolean referenciaAmbitoAtencion) {
        this.referenciaAmbitoAtencion = referenciaAmbitoAtencion;
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
    public List<MpcPrescripcionAdjuntos> getMpcPrescripcionAdjuntosList() {
        return mpcPrescripcionAdjuntosList;
    }

    public void setMpcPrescripcionAdjuntosList(List<MpcPrescripcionAdjuntos> mpcPrescripcionAdjuntosList) {
        this.mpcPrescripcionAdjuntosList = mpcPrescripcionAdjuntosList;
    }

    @XmlTransient
    public List<MpcProgramacionEntregas> getMpcProgramacionEntregasList() {
        return mpcProgramacionEntregasList;
    }

    public void setMpcProgramacionEntregasList(List<MpcProgramacionEntregas> mpcProgramacionEntregasList) {
        this.mpcProgramacionEntregasList = mpcProgramacionEntregasList;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public CntPrestadorSedes getCntPrescriptorPrestadorSedesId() {
        return cntPrescriptorPrestadorSedesId;
    }

    public void setCntPrescriptorPrestadorSedesId(CntPrestadorSedes cntPrescriptorPrestadorSedesId) {
        this.cntPrescriptorPrestadorSedesId = cntPrescriptorPrestadorSedesId;
    }

    public CntPrestadorSedes getCntDireccionadoPrestadorSedesId() {
        return cntDireccionadoPrestadorSedesId;
    }

    public void setCntDireccionadoPrestadorSedesId(CntPrestadorSedes cntDireccionadoPrestadorSedesId) {
        this.cntDireccionadoPrestadorSedesId = cntDireccionadoPrestadorSedesId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
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
        if (!(object instanceof MpcPrescripciones)) {
            return false;
        }
        MpcPrescripciones other = (MpcPrescripciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpcPrescripciones[ id=" + id + " ]";
    }
    
}
