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
@Table(name = "pe_programas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeProgramas.findAll", query = "SELECT p FROM PeProgramas p"),
    @NamedQuery(name = "PeProgramas.findById", query = "SELECT p FROM PeProgramas p WHERE p.id = :id"),
    @NamedQuery(name = "PeProgramas.findByMaeAgrupadorId", query = "SELECT p FROM PeProgramas p WHERE p.maeAgrupadorId = :maeAgrupadorId"),
    @NamedQuery(name = "PeProgramas.findByMaeAgrupadorCodigo", query = "SELECT p FROM PeProgramas p WHERE p.maeAgrupadorCodigo = :maeAgrupadorCodigo"),
    @NamedQuery(name = "PeProgramas.findByMaeAgrupadorValor", query = "SELECT p FROM PeProgramas p WHERE p.maeAgrupadorValor = :maeAgrupadorValor"),
    @NamedQuery(name = "PeProgramas.findByCodigoPrograma", query = "SELECT p FROM PeProgramas p WHERE p.codigoPrograma = :codigoPrograma"),
    @NamedQuery(name = "PeProgramas.findByDescripcionPrograma", query = "SELECT p FROM PeProgramas p WHERE p.descripcionPrograma = :descripcionPrograma"),
    @NamedQuery(name = "PeProgramas.findByMaeCategoriaId", query = "SELECT p FROM PeProgramas p WHERE p.maeCategoriaId = :maeCategoriaId"),
    @NamedQuery(name = "PeProgramas.findByMaeCategoriaCodigo", query = "SELECT p FROM PeProgramas p WHERE p.maeCategoriaCodigo = :maeCategoriaCodigo"),
    @NamedQuery(name = "PeProgramas.findByMaeCategoriaValor", query = "SELECT p FROM PeProgramas p WHERE p.maeCategoriaValor = :maeCategoriaValor"),
    @NamedQuery(name = "PeProgramas.findByMaeTipoProgramaId", query = "SELECT p FROM PeProgramas p WHERE p.maeTipoProgramaId = :maeTipoProgramaId"),
    @NamedQuery(name = "PeProgramas.findByMaeTipoProgramaCodigo", query = "SELECT p FROM PeProgramas p WHERE p.maeTipoProgramaCodigo = :maeTipoProgramaCodigo"),
    @NamedQuery(name = "PeProgramas.findByMaeTipoProgramaValor", query = "SELECT p FROM PeProgramas p WHERE p.maeTipoProgramaValor = :maeTipoProgramaValor"),
    @NamedQuery(name = "PeProgramas.findByActivo", query = "SELECT p FROM PeProgramas p WHERE p.activo = :activo"),
    @NamedQuery(name = "PeProgramas.findByExoneradoCopago", query = "SELECT p FROM PeProgramas p WHERE p.exoneradoCopago = :exoneradoCopago"),
    @NamedQuery(name = "PeProgramas.findByAplicaPreMarca", query = "SELECT p FROM PeProgramas p WHERE p.aplicaPreMarca = :aplicaPreMarca"),
    @NamedQuery(name = "PeProgramas.findByMarcacionAutomaticaAfiliado", query = "SELECT p FROM PeProgramas p WHERE p.marcacionAutomaticaAfiliado = :marcacionAutomaticaAfiliado"),
    @NamedQuery(name = "PeProgramas.findByRegistroAfiliadoAfiliacion", query = "SELECT p FROM PeProgramas p WHERE p.registroAfiliadoAfiliacion = :registroAfiliadoAfiliacion"),
    @NamedQuery(name = "PeProgramas.findByRegistroAfiliadoSolicitud", query = "SELECT p FROM PeProgramas p WHERE p.registroAfiliadoSolicitud = :registroAfiliadoSolicitud"),
    @NamedQuery(name = "PeProgramas.findByRegistroAfiliadoHospitalizacion", query = "SELECT p FROM PeProgramas p WHERE p.registroAfiliadoHospitalizacion = :registroAfiliadoHospitalizacion"),
    @NamedQuery(name = "PeProgramas.findByAplicaRescate", query = "SELECT p FROM PeProgramas p WHERE p.aplicaRescate = :aplicaRescate"),
    @NamedQuery(name = "PeProgramas.findByAplicaRescateAnexo3Ambulatorio", query = "SELECT p FROM PeProgramas p WHERE p.aplicaRescateAnexo3Ambulatorio = :aplicaRescateAnexo3Ambulatorio"),
    @NamedQuery(name = "PeProgramas.findByAplicaRescateAnexo3Hospitalario", query = "SELECT p FROM PeProgramas p WHERE p.aplicaRescateAnexo3Hospitalario = :aplicaRescateAnexo3Hospitalario"),
    @NamedQuery(name = "PeProgramas.findByAplicaRescateAnexo2Urgencias", query = "SELECT p FROM PeProgramas p WHERE p.aplicaRescateAnexo2Urgencias = :aplicaRescateAnexo2Urgencias"),
    @NamedQuery(name = "PeProgramas.findByDirecciona", query = "SELECT p FROM PeProgramas p WHERE p.direcciona = :direcciona"),
    @NamedQuery(name = "PeProgramas.findBySexoAplica", query = "SELECT p FROM PeProgramas p WHERE p.sexoAplica = :sexoAplica"),
    @NamedQuery(name = "PeProgramas.findByCantidadRegistros", query = "SELECT p FROM PeProgramas p WHERE p.cantidadRegistros = :cantidadRegistros"),
    @NamedQuery(name = "PeProgramas.findByAplicaRecobro", query = "SELECT p FROM PeProgramas p WHERE p.aplicaRecobro = :aplicaRecobro"),
    @NamedQuery(name = "PeProgramas.findByExoneracionObligatoria", query = "SELECT p FROM PeProgramas p WHERE p.exoneracionObligatoria = :exoneracionObligatoria"),
    @NamedQuery(name = "PeProgramas.findByUsuarioCrea", query = "SELECT p FROM PeProgramas p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeProgramas.findByTerminalCrea", query = "SELECT p FROM PeProgramas p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeProgramas.findByFechaHoraCrea", query = "SELECT p FROM PeProgramas p WHERE p.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "PeProgramas.findByUsuarioModifica", query = "SELECT p FROM PeProgramas p WHERE p.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "PeProgramas.findByTerminalModifica", query = "SELECT p FROM PeProgramas p WHERE p.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "PeProgramas.findByFechaHoraModifica", query = "SELECT p FROM PeProgramas p WHERE p.fechaHoraModifica = :fechaHoraModifica")})
public class PeProgramas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_agrupador_id")
    private Integer maeAgrupadorId;
    @Size(max = 8)
    @Column(name = "mae_agrupador_codigo")
    private String maeAgrupadorCodigo;
    @Size(max = 128)
    @Column(name = "mae_agrupador_valor")
    private String maeAgrupadorValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo_programa")
    private String codigoPrograma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "descripcion_programa")
    private String descripcionPrograma;
    @Column(name = "mae_categoria_id")
    private Integer maeCategoriaId;
    @Size(max = 16)
    @Column(name = "mae_categoria_codigo")
    private String maeCategoriaCodigo;
    @Size(max = 128)
    @Column(name = "mae_categoria_valor")
    private String maeCategoriaValor;
    @Column(name = "mae_tipo_programa_id")
    private Integer maeTipoProgramaId;
    @Size(max = 16)
    @Column(name = "mae_tipo_programa_codigo")
    private String maeTipoProgramaCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_programa_valor")
    private String maeTipoProgramaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exonerado_copago")
    private boolean exoneradoCopago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_pre_marca")
    private boolean aplicaPreMarca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "marcacion_automatica_afiliado")
    private boolean marcacionAutomaticaAfiliado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_afiliado_afiliacion")
    private short registroAfiliadoAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_afiliado_solicitud")
    private short registroAfiliadoSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_afiliado_hospitalizacion")
    private short registroAfiliadoHospitalizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_rescate")
    private boolean aplicaRescate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_rescate_anexo3_ambulatorio")
    private boolean aplicaRescateAnexo3Ambulatorio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_rescate_anexo3_hospitalario")
    private boolean aplicaRescateAnexo3Hospitalario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_rescate_anexo2_urgencias")
    private boolean aplicaRescateAnexo2Urgencias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "direcciona")
    private boolean direcciona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexo_aplica")
    private short sexoAplica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_registros")
    private boolean cantidadRegistros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_recobro")
    private boolean aplicaRecobro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exoneracion_obligatoria")
    private boolean exoneracionObligatoria;
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
    @OneToMany(mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<AuAnexos3> auAnexos3List;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeAfiliadosSugeridos> peAfiliadosSugeridosList;
    @OneToMany(mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeCargasVariables> peCargasVariablesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<AuGrupoProgramas> auGrupoProgramasList;
    @OneToMany(mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<RcoGrupos> rcoGruposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeCierreCargas> peCierreCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramaIdDestino", fetch = FetchType.LAZY)
    private List<PeProgramasTraslados> peProgramasTrasladosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramaIdOrigen", fetch = FetchType.LAZY)
    private List<PeProgramasTraslados> peProgramasTrasladosList1;
    @OneToMany(mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeCargas> peCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeVariables> peVariablesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeProgramaTecnologias> peProgramaTecnologiasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeUsuariosProgramas> peUsuariosProgramasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeDireccionados> peDireccionadosList;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeIpsProgramas> peIpsProgramasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeAfiliadosProgramas> peAfiliadosProgramasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeVariablesValoresHistoricos> peVariablesValoresHistoricosList;
    @OneToMany(mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<AuAnexo2Rescates> auAnexo2RescatesList;
    @OneToMany(mappedBy = "peProgramaId", fetch = FetchType.LAZY)
    private List<RcoFacturaDetalles> rcoFacturaDetallesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peProgramasId", fetch = FetchType.LAZY)
    private List<PeProgramaDiagnosticos> peProgramaDiagnosticosList;

    public PeProgramas() {
    }

    public PeProgramas(Integer id) {
        this.id = id;
    }

    public PeProgramas(Integer id, String codigoPrograma, String descripcionPrograma, boolean activo, boolean exoneradoCopago, boolean aplicaPreMarca, boolean marcacionAutomaticaAfiliado, short registroAfiliadoAfiliacion, short registroAfiliadoSolicitud, short registroAfiliadoHospitalizacion, boolean aplicaRescate, boolean aplicaRescateAnexo3Ambulatorio, boolean aplicaRescateAnexo3Hospitalario, boolean aplicaRescateAnexo2Urgencias, boolean direcciona, short sexoAplica, boolean cantidadRegistros, boolean aplicaRecobro, boolean exoneracionObligatoria, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigoPrograma = codigoPrograma;
        this.descripcionPrograma = descripcionPrograma;
        this.activo = activo;
        this.exoneradoCopago = exoneradoCopago;
        this.aplicaPreMarca = aplicaPreMarca;
        this.marcacionAutomaticaAfiliado = marcacionAutomaticaAfiliado;
        this.registroAfiliadoAfiliacion = registroAfiliadoAfiliacion;
        this.registroAfiliadoSolicitud = registroAfiliadoSolicitud;
        this.registroAfiliadoHospitalizacion = registroAfiliadoHospitalizacion;
        this.aplicaRescate = aplicaRescate;
        this.aplicaRescateAnexo3Ambulatorio = aplicaRescateAnexo3Ambulatorio;
        this.aplicaRescateAnexo3Hospitalario = aplicaRescateAnexo3Hospitalario;
        this.aplicaRescateAnexo2Urgencias = aplicaRescateAnexo2Urgencias;
        this.direcciona = direcciona;
        this.sexoAplica = sexoAplica;
        this.cantidadRegistros = cantidadRegistros;
        this.aplicaRecobro = aplicaRecobro;
        this.exoneracionObligatoria = exoneracionObligatoria;
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

    public Integer getMaeAgrupadorId() {
        return maeAgrupadorId;
    }

    public void setMaeAgrupadorId(Integer maeAgrupadorId) {
        this.maeAgrupadorId = maeAgrupadorId;
    }

    public String getMaeAgrupadorCodigo() {
        return maeAgrupadorCodigo;
    }

    public void setMaeAgrupadorCodigo(String maeAgrupadorCodigo) {
        this.maeAgrupadorCodigo = maeAgrupadorCodigo;
    }

    public String getMaeAgrupadorValor() {
        return maeAgrupadorValor;
    }

    public void setMaeAgrupadorValor(String maeAgrupadorValor) {
        this.maeAgrupadorValor = maeAgrupadorValor;
    }

    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public String getDescripcionPrograma() {
        return descripcionPrograma;
    }

    public void setDescripcionPrograma(String descripcionPrograma) {
        this.descripcionPrograma = descripcionPrograma;
    }

    public Integer getMaeCategoriaId() {
        return maeCategoriaId;
    }

    public void setMaeCategoriaId(Integer maeCategoriaId) {
        this.maeCategoriaId = maeCategoriaId;
    }

    public String getMaeCategoriaCodigo() {
        return maeCategoriaCodigo;
    }

    public void setMaeCategoriaCodigo(String maeCategoriaCodigo) {
        this.maeCategoriaCodigo = maeCategoriaCodigo;
    }

    public String getMaeCategoriaValor() {
        return maeCategoriaValor;
    }

    public void setMaeCategoriaValor(String maeCategoriaValor) {
        this.maeCategoriaValor = maeCategoriaValor;
    }

    public Integer getMaeTipoProgramaId() {
        return maeTipoProgramaId;
    }

    public void setMaeTipoProgramaId(Integer maeTipoProgramaId) {
        this.maeTipoProgramaId = maeTipoProgramaId;
    }

    public String getMaeTipoProgramaCodigo() {
        return maeTipoProgramaCodigo;
    }

    public void setMaeTipoProgramaCodigo(String maeTipoProgramaCodigo) {
        this.maeTipoProgramaCodigo = maeTipoProgramaCodigo;
    }

    public String getMaeTipoProgramaValor() {
        return maeTipoProgramaValor;
    }

    public void setMaeTipoProgramaValor(String maeTipoProgramaValor) {
        this.maeTipoProgramaValor = maeTipoProgramaValor;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getExoneradoCopago() {
        return exoneradoCopago;
    }

    public void setExoneradoCopago(boolean exoneradoCopago) {
        this.exoneradoCopago = exoneradoCopago;
    }

    public boolean getAplicaPreMarca() {
        return aplicaPreMarca;
    }

    public void setAplicaPreMarca(boolean aplicaPreMarca) {
        this.aplicaPreMarca = aplicaPreMarca;
    }

    public boolean getMarcacionAutomaticaAfiliado() {
        return marcacionAutomaticaAfiliado;
    }

    public void setMarcacionAutomaticaAfiliado(boolean marcacionAutomaticaAfiliado) {
        this.marcacionAutomaticaAfiliado = marcacionAutomaticaAfiliado;
    }

    public short getRegistroAfiliadoAfiliacion() {
        return registroAfiliadoAfiliacion;
    }

    public void setRegistroAfiliadoAfiliacion(short registroAfiliadoAfiliacion) {
        this.registroAfiliadoAfiliacion = registroAfiliadoAfiliacion;
    }

    public short getRegistroAfiliadoSolicitud() {
        return registroAfiliadoSolicitud;
    }

    public void setRegistroAfiliadoSolicitud(short registroAfiliadoSolicitud) {
        this.registroAfiliadoSolicitud = registroAfiliadoSolicitud;
    }

    public short getRegistroAfiliadoHospitalizacion() {
        return registroAfiliadoHospitalizacion;
    }

    public void setRegistroAfiliadoHospitalizacion(short registroAfiliadoHospitalizacion) {
        this.registroAfiliadoHospitalizacion = registroAfiliadoHospitalizacion;
    }

    public boolean getAplicaRescate() {
        return aplicaRescate;
    }

    public void setAplicaRescate(boolean aplicaRescate) {
        this.aplicaRescate = aplicaRescate;
    }

    public boolean getAplicaRescateAnexo3Ambulatorio() {
        return aplicaRescateAnexo3Ambulatorio;
    }

    public void setAplicaRescateAnexo3Ambulatorio(boolean aplicaRescateAnexo3Ambulatorio) {
        this.aplicaRescateAnexo3Ambulatorio = aplicaRescateAnexo3Ambulatorio;
    }

    public boolean getAplicaRescateAnexo3Hospitalario() {
        return aplicaRescateAnexo3Hospitalario;
    }

    public void setAplicaRescateAnexo3Hospitalario(boolean aplicaRescateAnexo3Hospitalario) {
        this.aplicaRescateAnexo3Hospitalario = aplicaRescateAnexo3Hospitalario;
    }

    public boolean getAplicaRescateAnexo2Urgencias() {
        return aplicaRescateAnexo2Urgencias;
    }

    public void setAplicaRescateAnexo2Urgencias(boolean aplicaRescateAnexo2Urgencias) {
        this.aplicaRescateAnexo2Urgencias = aplicaRescateAnexo2Urgencias;
    }

    public boolean getDirecciona() {
        return direcciona;
    }

    public void setDirecciona(boolean direcciona) {
        this.direcciona = direcciona;
    }

    public short getSexoAplica() {
        return sexoAplica;
    }

    public void setSexoAplica(short sexoAplica) {
        this.sexoAplica = sexoAplica;
    }

    public boolean getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(boolean cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public boolean getAplicaRecobro() {
        return aplicaRecobro;
    }

    public void setAplicaRecobro(boolean aplicaRecobro) {
        this.aplicaRecobro = aplicaRecobro;
    }

    public boolean getExoneracionObligatoria() {
        return exoneracionObligatoria;
    }

    public void setExoneracionObligatoria(boolean exoneracionObligatoria) {
        this.exoneracionObligatoria = exoneracionObligatoria;
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
    public List<AuAnexos3> getAuAnexos3List() {
        return auAnexos3List;
    }

    public void setAuAnexos3List(List<AuAnexos3> auAnexos3List) {
        this.auAnexos3List = auAnexos3List;
    }

    @XmlTransient
    public List<PeAfiliadosSugeridos> getPeAfiliadosSugeridosList() {
        return peAfiliadosSugeridosList;
    }

    public void setPeAfiliadosSugeridosList(List<PeAfiliadosSugeridos> peAfiliadosSugeridosList) {
        this.peAfiliadosSugeridosList = peAfiliadosSugeridosList;
    }

    @XmlTransient
    public List<PeCargasVariables> getPeCargasVariablesList() {
        return peCargasVariablesList;
    }

    public void setPeCargasVariablesList(List<PeCargasVariables> peCargasVariablesList) {
        this.peCargasVariablesList = peCargasVariablesList;
    }

    @XmlTransient
    public List<AuGrupoProgramas> getAuGrupoProgramasList() {
        return auGrupoProgramasList;
    }

    public void setAuGrupoProgramasList(List<AuGrupoProgramas> auGrupoProgramasList) {
        this.auGrupoProgramasList = auGrupoProgramasList;
    }

    @XmlTransient
    public List<RcoGrupos> getRcoGruposList() {
        return rcoGruposList;
    }

    public void setRcoGruposList(List<RcoGrupos> rcoGruposList) {
        this.rcoGruposList = rcoGruposList;
    }

    @XmlTransient
    public List<PeCierreCargas> getPeCierreCargasList() {
        return peCierreCargasList;
    }

    public void setPeCierreCargasList(List<PeCierreCargas> peCierreCargasList) {
        this.peCierreCargasList = peCierreCargasList;
    }

    @XmlTransient
    public List<PeProgramasTraslados> getPeProgramasTrasladosList() {
        return peProgramasTrasladosList;
    }

    public void setPeProgramasTrasladosList(List<PeProgramasTraslados> peProgramasTrasladosList) {
        this.peProgramasTrasladosList = peProgramasTrasladosList;
    }

    @XmlTransient
    public List<PeProgramasTraslados> getPeProgramasTrasladosList1() {
        return peProgramasTrasladosList1;
    }

    public void setPeProgramasTrasladosList1(List<PeProgramasTraslados> peProgramasTrasladosList1) {
        this.peProgramasTrasladosList1 = peProgramasTrasladosList1;
    }

    @XmlTransient
    public List<PeCargas> getPeCargasList() {
        return peCargasList;
    }

    public void setPeCargasList(List<PeCargas> peCargasList) {
        this.peCargasList = peCargasList;
    }

    @XmlTransient
    public List<PeVariables> getPeVariablesList() {
        return peVariablesList;
    }

    public void setPeVariablesList(List<PeVariables> peVariablesList) {
        this.peVariablesList = peVariablesList;
    }

    @XmlTransient
    public List<PeProgramaTecnologias> getPeProgramaTecnologiasList() {
        return peProgramaTecnologiasList;
    }

    public void setPeProgramaTecnologiasList(List<PeProgramaTecnologias> peProgramaTecnologiasList) {
        this.peProgramaTecnologiasList = peProgramaTecnologiasList;
    }

    @XmlTransient
    public List<PeUsuariosProgramas> getPeUsuariosProgramasList() {
        return peUsuariosProgramasList;
    }

    public void setPeUsuariosProgramasList(List<PeUsuariosProgramas> peUsuariosProgramasList) {
        this.peUsuariosProgramasList = peUsuariosProgramasList;
    }

    @XmlTransient
    public List<PeDireccionados> getPeDireccionadosList() {
        return peDireccionadosList;
    }

    public void setPeDireccionadosList(List<PeDireccionados> peDireccionadosList) {
        this.peDireccionadosList = peDireccionadosList;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    @XmlTransient
    public List<PeIpsProgramas> getPeIpsProgramasList() {
        return peIpsProgramasList;
    }

    public void setPeIpsProgramasList(List<PeIpsProgramas> peIpsProgramasList) {
        this.peIpsProgramasList = peIpsProgramasList;
    }

    @XmlTransient
    public List<PeAfiliadosProgramas> getPeAfiliadosProgramasList() {
        return peAfiliadosProgramasList;
    }

    public void setPeAfiliadosProgramasList(List<PeAfiliadosProgramas> peAfiliadosProgramasList) {
        this.peAfiliadosProgramasList = peAfiliadosProgramasList;
    }

    @XmlTransient
    public List<PeVariablesValoresHistoricos> getPeVariablesValoresHistoricosList() {
        return peVariablesValoresHistoricosList;
    }

    public void setPeVariablesValoresHistoricosList(List<PeVariablesValoresHistoricos> peVariablesValoresHistoricosList) {
        this.peVariablesValoresHistoricosList = peVariablesValoresHistoricosList;
    }

    @XmlTransient
    public List<AuAnexo2Rescates> getAuAnexo2RescatesList() {
        return auAnexo2RescatesList;
    }

    public void setAuAnexo2RescatesList(List<AuAnexo2Rescates> auAnexo2RescatesList) {
        this.auAnexo2RescatesList = auAnexo2RescatesList;
    }

    @XmlTransient
    public List<RcoFacturaDetalles> getRcoFacturaDetallesList() {
        return rcoFacturaDetallesList;
    }

    public void setRcoFacturaDetallesList(List<RcoFacturaDetalles> rcoFacturaDetallesList) {
        this.rcoFacturaDetallesList = rcoFacturaDetallesList;
    }

    @XmlTransient
    public List<PeProgramaDiagnosticos> getPeProgramaDiagnosticosList() {
        return peProgramaDiagnosticosList;
    }

    public void setPeProgramaDiagnosticosList(List<PeProgramaDiagnosticos> peProgramaDiagnosticosList) {
        this.peProgramaDiagnosticosList = peProgramaDiagnosticosList;
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
        if (!(object instanceof PeProgramas)) {
            return false;
        }
        PeProgramas other = (PeProgramas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeProgramas[ id=" + id + " ]";
    }
    
}
