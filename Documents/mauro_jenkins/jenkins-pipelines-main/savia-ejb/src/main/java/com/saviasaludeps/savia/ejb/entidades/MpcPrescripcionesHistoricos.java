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
@Table(name = "mpc_prescripciones_historicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findAll", query = "SELECT m FROM MpcPrescripcionesHistoricos m"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findById", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.id = :id"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByAmbito", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.ambito = :ambito"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByReferenciaAmbitoAtencion", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.referenciaAmbitoAtencion = :referenciaAmbitoAtencion"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByEstado", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByMaePrescriptorTipoDocumentoId", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.maePrescriptorTipoDocumentoId = :maePrescriptorTipoDocumentoId"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByMaePrescriptorTipoDocumentoCodigo", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.maePrescriptorTipoDocumentoCodigo = :maePrescriptorTipoDocumentoCodigo"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByMaePrescriptorTipoDocumentoValor", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.maePrescriptorTipoDocumentoValor = :maePrescriptorTipoDocumentoValor"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByPrescriptorNumeroDocumento", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.prescriptorNumeroDocumento = :prescriptorNumeroDocumento"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByPrescriptorCodigoHabilitacion", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.prescriptorCodigoHabilitacion = :prescriptorCodigoHabilitacion"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByPrescriptorCorreoElectronico", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.prescriptorCorreoElectronico = :prescriptorCorreoElectronico"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByMaeAfiliadoTipoDocumentoId", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.maeAfiliadoTipoDocumentoId = :maeAfiliadoTipoDocumentoId"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByMaeAfiliadoTipoDocumentoCodigo", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.maeAfiliadoTipoDocumentoCodigo = :maeAfiliadoTipoDocumentoCodigo"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByMaeAfiliadoTipoDocumentoValor", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.maeAfiliadoTipoDocumentoValor = :maeAfiliadoTipoDocumentoValor"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByAfiliadoNumeroDocumento", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.afiliadoNumeroDocumento = :afiliadoNumeroDocumento"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByAfiliadoPrimerNombre", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.afiliadoPrimerNombre = :afiliadoPrimerNombre"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByAfiliadoSegundoNombre", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.afiliadoSegundoNombre = :afiliadoSegundoNombre"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByAfiliadoPrimerApellido", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.afiliadoPrimerApellido = :afiliadoPrimerApellido"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByAfiliadoSegundoApellido", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.afiliadoSegundoApellido = :afiliadoSegundoApellido"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByFechaHora", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.fechaHora = :fechaHora"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByRecobrante", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.recobrante = :recobrante"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByTipoTecnologia", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByActaJuantaProfesionales", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.actaJuantaProfesionales = :actaJuantaProfesionales"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByConcentimientoInformado", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.concentimientoInformado = :concentimientoInformado"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByFormatoIntegralidad", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.formatoIntegralidad = :formatoIntegralidad"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByRechazoJustificacion", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.rechazoJustificacion = :rechazoJustificacion"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByConsecutivo", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.consecutivo = :consecutivo"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByMaTecnologiaId", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByMaTecnologiaCodigo", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByMaTecnologiaValor", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByCantidad", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByPeriodicidad", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.periodicidad = :periodicidad"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByNumeroEntregas", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.numeroEntregas = :numeroEntregas"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByUsuarioCrea", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByTerminalCrea", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByFechaHoraCrea", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByUsuarioModifica", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByTerminalModifica", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpcPrescripcionesHistoricos.findByFechaHoraModifica", query = "SELECT m FROM MpcPrescripcionesHistoricos m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MpcPrescripcionesHistoricos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 4)
    @Column(name = "ambito")
    private String ambito;
    @Column(name = "referencia_ambito_atencion")
    private Boolean referenciaAmbitoAtencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "mae_prescriptor_tipo_documento_id")
    private Integer maePrescriptorTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_prescriptor_tipo_documento_codigo")
    private String maePrescriptorTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_prescriptor_tipo_documento_valor")
    private String maePrescriptorTipoDocumentoValor;
    @Size(max = 32)
    @Column(name = "prescriptor_numero_documento")
    private String prescriptorNumeroDocumento;
    @Size(max = 16)
    @Column(name = "prescriptor_codigo_habilitacion")
    private String prescriptorCodigoHabilitacion;
    @Size(max = 64)
    @Column(name = "prescriptor_correo_electronico")
    private String prescriptorCorreoElectronico;
    @Column(name = "mae_afiliado_tipo_documento_id")
    private Integer maeAfiliadoTipoDocumentoId;
    @Size(max = 8)
    @Column(name = "mae_afiliado_tipo_documento_codigo")
    private String maeAfiliadoTipoDocumentoCodigo;
    @Size(max = 128)
    @Column(name = "mae_afiliado_tipo_documento_valor")
    private String maeAfiliadoTipoDocumentoValor;
    @Size(max = 32)
    @Column(name = "afiliado_numero_documento")
    private String afiliadoNumeroDocumento;
    @Size(max = 64)
    @Column(name = "afiliado_primer_nombre")
    private String afiliadoPrimerNombre;
    @Size(max = 64)
    @Column(name = "afiliado_segundo_nombre")
    private String afiliadoSegundoNombre;
    @Size(max = 64)
    @Column(name = "afiliado_primer_apellido")
    private String afiliadoPrimerApellido;
    @Size(max = 64)
    @Column(name = "afiliado_segundo_apellido")
    private String afiliadoSegundoApellido;
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Column(name = "recobrante")
    private Boolean recobrante;
    @Column(name = "tipo_tecnologia")
    private Integer tipoTecnologia;
    @Column(name = "acta_juanta_profesionales")
    private Boolean actaJuantaProfesionales;
    @Column(name = "concentimiento_informado")
    private Boolean concentimientoInformado;
    @Column(name = "formato_integralidad")
    private Boolean formatoIntegralidad;
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
    private Integer periodicidad;
    @Column(name = "numero_entregas")
    private Integer numeroEntregas;
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
    @JoinColumn(name = "cnt_direccionado_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntDireccionadoPrestadorSedesId;
    @JoinColumn(name = "cnt_prescriptor_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrescriptorPrestadorSedesId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;

    public MpcPrescripcionesHistoricos() {
    }

    public MpcPrescripcionesHistoricos(Integer id) {
        this.id = id;
    }

    public MpcPrescripcionesHistoricos(Integer id, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getMaePrescriptorTipoDocumentoId() {
        return maePrescriptorTipoDocumentoId;
    }

    public void setMaePrescriptorTipoDocumentoId(Integer maePrescriptorTipoDocumentoId) {
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

    public Integer getMaeAfiliadoTipoDocumentoId() {
        return maeAfiliadoTipoDocumentoId;
    }

    public void setMaeAfiliadoTipoDocumentoId(Integer maeAfiliadoTipoDocumentoId) {
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

    public Boolean getRecobrante() {
        return recobrante;
    }

    public void setRecobrante(Boolean recobrante) {
        this.recobrante = recobrante;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Boolean getActaJuantaProfesionales() {
        return actaJuantaProfesionales;
    }

    public void setActaJuantaProfesionales(Boolean actaJuantaProfesionales) {
        this.actaJuantaProfesionales = actaJuantaProfesionales;
    }

    public Boolean getConcentimientoInformado() {
        return concentimientoInformado;
    }

    public void setConcentimientoInformado(Boolean concentimientoInformado) {
        this.concentimientoInformado = concentimientoInformado;
    }

    public Boolean getFormatoIntegralidad() {
        return formatoIntegralidad;
    }

    public void setFormatoIntegralidad(Boolean formatoIntegralidad) {
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

    public Integer getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Integer periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Integer getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(Integer numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
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

    public CntPrestadorSedes getCntDireccionadoPrestadorSedesId() {
        return cntDireccionadoPrestadorSedesId;
    }

    public void setCntDireccionadoPrestadorSedesId(CntPrestadorSedes cntDireccionadoPrestadorSedesId) {
        this.cntDireccionadoPrestadorSedesId = cntDireccionadoPrestadorSedesId;
    }

    public CntPrestadorSedes getCntPrescriptorPrestadorSedesId() {
        return cntPrescriptorPrestadorSedesId;
    }

    public void setCntPrescriptorPrestadorSedesId(CntPrestadorSedes cntPrescriptorPrestadorSedesId) {
        this.cntPrescriptorPrestadorSedesId = cntPrescriptorPrestadorSedesId;
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
        if (!(object instanceof MpcPrescripcionesHistoricos)) {
            return false;
        }
        MpcPrescripcionesHistoricos other = (MpcPrescripcionesHistoricos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpcPrescripcionesHistoricos[ id=" + id + " ]";
    }
    
}
