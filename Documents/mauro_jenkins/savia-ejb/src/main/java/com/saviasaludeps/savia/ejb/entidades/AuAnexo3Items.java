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
@Table(name = "au_anexo3_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo3Items.findAll", query = "SELECT a FROM AuAnexo3Items a"),
    @NamedQuery(name = "AuAnexo3Items.findById", query = "SELECT a FROM AuAnexo3Items a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo3Items.findByProcesoAsignacion", query = "SELECT a FROM AuAnexo3Items a WHERE a.procesoAsignacion = :procesoAsignacion"),
    @NamedQuery(name = "AuAnexo3Items.findByEstado", query = "SELECT a FROM AuAnexo3Items a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexo3Items.findByTipoTecnologia", query = "SELECT a FROM AuAnexo3Items a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AuAnexo3Items.findByMaTecnologiaId", query = "SELECT a FROM AuAnexo3Items a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AuAnexo3Items.findByMaTecnologiaCodigo", query = "SELECT a FROM AuAnexo3Items a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AuAnexo3Items.findByMaTecnologiaValor", query = "SELECT a FROM AuAnexo3Items a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AuAnexo3Items.findByMaMedicamentoId", query = "SELECT a FROM AuAnexo3Items a WHERE a.maMedicamentoId = :maMedicamentoId"),
    @NamedQuery(name = "AuAnexo3Items.findByMaMedicamentoCodigo", query = "SELECT a FROM AuAnexo3Items a WHERE a.maMedicamentoCodigo = :maMedicamentoCodigo"),
    @NamedQuery(name = "AuAnexo3Items.findByMaMedicamentoValor", query = "SELECT a FROM AuAnexo3Items a WHERE a.maMedicamentoValor = :maMedicamentoValor"),
    @NamedQuery(name = "AuAnexo3Items.findByCantidadSolicitada", query = "SELECT a FROM AuAnexo3Items a WHERE a.cantidadSolicitada = :cantidadSolicitada"),
    @NamedQuery(name = "AuAnexo3Items.findByMaServicioSolicitadoId", query = "SELECT a FROM AuAnexo3Items a WHERE a.maServicioSolicitadoId = :maServicioSolicitadoId"),
    @NamedQuery(name = "AuAnexo3Items.findByMaServicioSolicitadoCodigo", query = "SELECT a FROM AuAnexo3Items a WHERE a.maServicioSolicitadoCodigo = :maServicioSolicitadoCodigo"),
    @NamedQuery(name = "AuAnexo3Items.findByMaServicioSolicitadoValor", query = "SELECT a FROM AuAnexo3Items a WHERE a.maServicioSolicitadoValor = :maServicioSolicitadoValor"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeEstadoMotivoItemId", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeEstadoMotivoItemId = :maeEstadoMotivoItemId"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeEstadoMotivoItemCodigo", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeEstadoMotivoItemCodigo = :maeEstadoMotivoItemCodigo"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeEstadoMotivoItemValor", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeEstadoMotivoItemValor = :maeEstadoMotivoItemValor"),
    @NamedQuery(name = "AuAnexo3Items.findByEstadoJustificacion", query = "SELECT a FROM AuAnexo3Items a WHERE a.estadoJustificacion = :estadoJustificacion"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeCausaExternaId", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeCausaExternaId = :maeCausaExternaId"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeCausaExternaCodigo", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeCausaExternaCodigo = :maeCausaExternaCodigo"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeCausaExternaValor", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeCausaExternaValor = :maeCausaExternaValor"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeFinalidadId", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeFinalidadId = :maeFinalidadId"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeFinalidadCodigo", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeFinalidadCodigo = :maeFinalidadCodigo"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeFinalidadValor", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeFinalidadValor = :maeFinalidadValor"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeTipoCatastroficoId", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeTipoCatastroficoId = :maeTipoCatastroficoId"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeTipoCatastroficoCodigo", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeTipoCatastroficoCodigo = :maeTipoCatastroficoCodigo"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeTipoCatastroficoValor", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeTipoCatastroficoValor = :maeTipoCatastroficoValor"),
    @NamedQuery(name = "AuAnexo3Items.findByFrecuencia", query = "SELECT a FROM AuAnexo3Items a WHERE a.frecuencia = :frecuencia"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeViaAdministracionId", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeViaAdministracionId = :maeViaAdministracionId"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeViaAdministracionCodigo", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeViaAdministracionCodigo = :maeViaAdministracionCodigo"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeViaAdministracionValor", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeViaAdministracionValor = :maeViaAdministracionValor"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeAmbitoId", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeAmbitoId = :maeAmbitoId"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeAmbitoCodigo", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeAmbitoCodigo = :maeAmbitoCodigo"),
    @NamedQuery(name = "AuAnexo3Items.findByMaeAmbitoValor", query = "SELECT a FROM AuAnexo3Items a WHERE a.maeAmbitoValor = :maeAmbitoValor"),
    @NamedQuery(name = "AuAnexo3Items.findByNivel", query = "SELECT a FROM AuAnexo3Items a WHERE a.nivel = :nivel"),
    @NamedQuery(name = "AuAnexo3Items.findByMaDiagnosticoId", query = "SELECT a FROM AuAnexo3Items a WHERE a.maDiagnosticoId = :maDiagnosticoId"),
    @NamedQuery(name = "AuAnexo3Items.findByMaDiagnosticoCodigo", query = "SELECT a FROM AuAnexo3Items a WHERE a.maDiagnosticoCodigo = :maDiagnosticoCodigo"),
    @NamedQuery(name = "AuAnexo3Items.findByMaDiagnosticoValor", query = "SELECT a FROM AuAnexo3Items a WHERE a.maDiagnosticoValor = :maDiagnosticoValor"),
    @NamedQuery(name = "AuAnexo3Items.findByPrequirurgico", query = "SELECT a FROM AuAnexo3Items a WHERE a.prequirurgico = :prequirurgico"),
    @NamedQuery(name = "AuAnexo3Items.findByFechaFormula", query = "SELECT a FROM AuAnexo3Items a WHERE a.fechaFormula = :fechaFormula"),
    @NamedQuery(name = "AuAnexo3Items.findByDosis", query = "SELECT a FROM AuAnexo3Items a WHERE a.dosis = :dosis"),
    @NamedQuery(name = "AuAnexo3Items.findByPosologia", query = "SELECT a FROM AuAnexo3Items a WHERE a.posologia = :posologia"),
    @NamedQuery(name = "AuAnexo3Items.findByPosfechado", query = "SELECT a FROM AuAnexo3Items a WHERE a.posfechado = :posfechado"),
    @NamedQuery(name = "AuAnexo3Items.findByFechaPosfechado", query = "SELECT a FROM AuAnexo3Items a WHERE a.fechaPosfechado = :fechaPosfechado"),
    @NamedQuery(name = "AuAnexo3Items.findByPosfechadoPrincipal", query = "SELECT a FROM AuAnexo3Items a WHERE a.posfechadoPrincipal = :posfechadoPrincipal"),
    @NamedQuery(name = "AuAnexo3Items.findByEfectosAdversos", query = "SELECT a FROM AuAnexo3Items a WHERE a.efectosAdversos = :efectosAdversos"),
    @NamedQuery(name = "AuAnexo3Items.findByUsuarioCrea", query = "SELECT a FROM AuAnexo3Items a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo3Items.findByTerminalCrea", query = "SELECT a FROM AuAnexo3Items a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo3Items.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo3Items a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo3Items.findByUsuarioModifica", query = "SELECT a FROM AuAnexo3Items a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo3Items.findByTerminalModifica", query = "SELECT a FROM AuAnexo3Items a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexo3Items.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo3Items a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexo3Items implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proceso_asignacion")
    private boolean procesoAsignacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
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
    @Column(name = "ma_medicamento_id")
    private Integer maMedicamentoId;
    @Size(max = 32)
    @Column(name = "ma_medicamento_codigo")
    private String maMedicamentoCodigo;
    @Size(max = 512)
    @Column(name = "ma_medicamento_valor")
    private String maMedicamentoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_solicitada")
    private int cantidadSolicitada;
    @Column(name = "ma_servicio_solicitado_id")
    private Integer maServicioSolicitadoId;
    @Size(max = 32)
    @Column(name = "ma_servicio_solicitado_codigo")
    private String maServicioSolicitadoCodigo;
    @Size(max = 512)
    @Column(name = "ma_servicio_solicitado_valor")
    private String maServicioSolicitadoValor;
    @Column(name = "mae_estado_motivo_item_id")
    private Integer maeEstadoMotivoItemId;
    @Size(max = 8)
    @Column(name = "mae_estado_motivo_item_codigo")
    private String maeEstadoMotivoItemCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_motivo_item_valor")
    private String maeEstadoMotivoItemValor;
    @Size(max = 1024)
    @Column(name = "estado_justificacion")
    private String estadoJustificacion;
    @Column(name = "mae_causa_externa_id")
    private Integer maeCausaExternaId;
    @Size(max = 8)
    @Column(name = "mae_causa_externa_codigo")
    private String maeCausaExternaCodigo;
    @Size(max = 128)
    @Column(name = "mae_causa_externa_valor")
    private String maeCausaExternaValor;
    @Column(name = "mae_finalidad_id")
    private Integer maeFinalidadId;
    @Size(max = 8)
    @Column(name = "mae_finalidad_codigo")
    private String maeFinalidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_finalidad_valor")
    private String maeFinalidadValor;
    @Column(name = "mae_tipo_catastrofico_id")
    private Integer maeTipoCatastroficoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_catastrofico_codigo")
    private String maeTipoCatastroficoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_catastrofico_valor")
    private String maeTipoCatastroficoValor;
    @Size(max = 3)
    @Column(name = "frecuencia")
    private String frecuencia;
    @Column(name = "mae_via_administracion_id")
    private Integer maeViaAdministracionId;
    @Size(max = 8)
    @Column(name = "mae_via_administracion_codigo")
    private String maeViaAdministracionCodigo;
    @Size(max = 128)
    @Column(name = "mae_via_administracion_valor")
    private String maeViaAdministracionValor;
    @Column(name = "mae_ambito_id")
    private Integer maeAmbitoId;
    @Size(max = 8)
    @Column(name = "mae_ambito_codigo")
    private String maeAmbitoCodigo;
    @Size(max = 128)
    @Column(name = "mae_ambito_valor")
    private String maeAmbitoValor;
    @Column(name = "nivel")
    private Integer nivel;
    @Column(name = "ma_diagnostico_id")
    private Integer maDiagnosticoId;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_codigo")
    private String maDiagnosticoCodigo;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_valor")
    private String maDiagnosticoValor;
    @Column(name = "prequirurgico")
    private Boolean prequirurgico;
    @Column(name = "fecha_formula")
    @Temporal(TemporalType.DATE)
    private Date fechaFormula;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dosis")
    private BigDecimal dosis;
    @Column(name = "posologia")
    private BigDecimal posologia;
    @Column(name = "posfechado")
    private Boolean posfechado;
    @Column(name = "fecha_posfechado")
    @Temporal(TemporalType.DATE)
    private Date fechaPosfechado;
    @Column(name = "posfechado_principal")
    private Boolean posfechadoPrincipal;
    @Column(name = "efectos_adversos")
    private Boolean efectosAdversos;
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
    @OneToMany(mappedBy = "auAnexo3ItemsId", fetch = FetchType.LAZY)
    private List<AuAnexo4Items> auAnexo4ItemsList;
    @OneToMany(mappedBy = "auAnexo3ItemsId", fetch = FetchType.LAZY)
    private List<AuAnexo3Historicos> auAnexo3HistoricosList;
    @OneToMany(mappedBy = "auAnexo3ItemsId", fetch = FetchType.LAZY)
    private List<AuCotizacionItems> auCotizacionItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3ItemsId", fetch = FetchType.LAZY)
    private List<AuRechazoItems> auRechazoItemsList;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;
    @JoinColumn(name = "au_grupos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuGrupos auGruposId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3ItemsId", fetch = FetchType.LAZY)
    private List<PeDireccionadoItems> peDireccionadoItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3ItemsId", fetch = FetchType.LAZY)
    private List<AuSeguimientos> auSeguimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexo3ItemsId", fetch = FetchType.LAZY)
    private List<AuItemBitacoras> auItemBitacorasList;

    public AuAnexo3Items() {
    }

    public AuAnexo3Items(Integer id) {
        this.id = id;
    }

    public AuAnexo3Items(Integer id, boolean procesoAsignacion, int estado, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiaValor, int cantidadSolicitada, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.procesoAsignacion = procesoAsignacion;
        this.estado = estado;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiaValor = maTecnologiaValor;
        this.cantidadSolicitada = cantidadSolicitada;
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

    public boolean getProcesoAsignacion() {
        return procesoAsignacion;
    }

    public void setProcesoAsignacion(boolean procesoAsignacion) {
        this.procesoAsignacion = procesoAsignacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public Integer getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(Integer maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getMaServicioSolicitadoId() {
        return maServicioSolicitadoId;
    }

    public void setMaServicioSolicitadoId(Integer maServicioSolicitadoId) {
        this.maServicioSolicitadoId = maServicioSolicitadoId;
    }

    public String getMaServicioSolicitadoCodigo() {
        return maServicioSolicitadoCodigo;
    }

    public void setMaServicioSolicitadoCodigo(String maServicioSolicitadoCodigo) {
        this.maServicioSolicitadoCodigo = maServicioSolicitadoCodigo;
    }

    public String getMaServicioSolicitadoValor() {
        return maServicioSolicitadoValor;
    }

    public void setMaServicioSolicitadoValor(String maServicioSolicitadoValor) {
        this.maServicioSolicitadoValor = maServicioSolicitadoValor;
    }

    public Integer getMaeEstadoMotivoItemId() {
        return maeEstadoMotivoItemId;
    }

    public void setMaeEstadoMotivoItemId(Integer maeEstadoMotivoItemId) {
        this.maeEstadoMotivoItemId = maeEstadoMotivoItemId;
    }

    public String getMaeEstadoMotivoItemCodigo() {
        return maeEstadoMotivoItemCodigo;
    }

    public void setMaeEstadoMotivoItemCodigo(String maeEstadoMotivoItemCodigo) {
        this.maeEstadoMotivoItemCodigo = maeEstadoMotivoItemCodigo;
    }

    public String getMaeEstadoMotivoItemValor() {
        return maeEstadoMotivoItemValor;
    }

    public void setMaeEstadoMotivoItemValor(String maeEstadoMotivoItemValor) {
        this.maeEstadoMotivoItemValor = maeEstadoMotivoItemValor;
    }

    public String getEstadoJustificacion() {
        return estadoJustificacion;
    }

    public void setEstadoJustificacion(String estadoJustificacion) {
        this.estadoJustificacion = estadoJustificacion;
    }

    public Integer getMaeCausaExternaId() {
        return maeCausaExternaId;
    }

    public void setMaeCausaExternaId(Integer maeCausaExternaId) {
        this.maeCausaExternaId = maeCausaExternaId;
    }

    public String getMaeCausaExternaCodigo() {
        return maeCausaExternaCodigo;
    }

    public void setMaeCausaExternaCodigo(String maeCausaExternaCodigo) {
        this.maeCausaExternaCodigo = maeCausaExternaCodigo;
    }

    public String getMaeCausaExternaValor() {
        return maeCausaExternaValor;
    }

    public void setMaeCausaExternaValor(String maeCausaExternaValor) {
        this.maeCausaExternaValor = maeCausaExternaValor;
    }

    public Integer getMaeFinalidadId() {
        return maeFinalidadId;
    }

    public void setMaeFinalidadId(Integer maeFinalidadId) {
        this.maeFinalidadId = maeFinalidadId;
    }

    public String getMaeFinalidadCodigo() {
        return maeFinalidadCodigo;
    }

    public void setMaeFinalidadCodigo(String maeFinalidadCodigo) {
        this.maeFinalidadCodigo = maeFinalidadCodigo;
    }

    public String getMaeFinalidadValor() {
        return maeFinalidadValor;
    }

    public void setMaeFinalidadValor(String maeFinalidadValor) {
        this.maeFinalidadValor = maeFinalidadValor;
    }

    public Integer getMaeTipoCatastroficoId() {
        return maeTipoCatastroficoId;
    }

    public void setMaeTipoCatastroficoId(Integer maeTipoCatastroficoId) {
        this.maeTipoCatastroficoId = maeTipoCatastroficoId;
    }

    public String getMaeTipoCatastroficoCodigo() {
        return maeTipoCatastroficoCodigo;
    }

    public void setMaeTipoCatastroficoCodigo(String maeTipoCatastroficoCodigo) {
        this.maeTipoCatastroficoCodigo = maeTipoCatastroficoCodigo;
    }

    public String getMaeTipoCatastroficoValor() {
        return maeTipoCatastroficoValor;
    }

    public void setMaeTipoCatastroficoValor(String maeTipoCatastroficoValor) {
        this.maeTipoCatastroficoValor = maeTipoCatastroficoValor;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getMaeViaAdministracionId() {
        return maeViaAdministracionId;
    }

    public void setMaeViaAdministracionId(Integer maeViaAdministracionId) {
        this.maeViaAdministracionId = maeViaAdministracionId;
    }

    public String getMaeViaAdministracionCodigo() {
        return maeViaAdministracionCodigo;
    }

    public void setMaeViaAdministracionCodigo(String maeViaAdministracionCodigo) {
        this.maeViaAdministracionCodigo = maeViaAdministracionCodigo;
    }

    public String getMaeViaAdministracionValor() {
        return maeViaAdministracionValor;
    }

    public void setMaeViaAdministracionValor(String maeViaAdministracionValor) {
        this.maeViaAdministracionValor = maeViaAdministracionValor;
    }

    public Integer getMaeAmbitoId() {
        return maeAmbitoId;
    }

    public void setMaeAmbitoId(Integer maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;
    }

    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(Integer maDiagnosticoId) {
        this.maDiagnosticoId = maDiagnosticoId;
    }

    public String getMaDiagnosticoCodigo() {
        return maDiagnosticoCodigo;
    }

    public void setMaDiagnosticoCodigo(String maDiagnosticoCodigo) {
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
    }

    public String getMaDiagnosticoValor() {
        return maDiagnosticoValor;
    }

    public void setMaDiagnosticoValor(String maDiagnosticoValor) {
        this.maDiagnosticoValor = maDiagnosticoValor;
    }

    public Boolean getPrequirurgico() {
        return prequirurgico;
    }

    public void setPrequirurgico(Boolean prequirurgico) {
        this.prequirurgico = prequirurgico;
    }

    public Date getFechaFormula() {
        return fechaFormula;
    }

    public void setFechaFormula(Date fechaFormula) {
        this.fechaFormula = fechaFormula;
    }

    public BigDecimal getDosis() {
        return dosis;
    }

    public void setDosis(BigDecimal dosis) {
        this.dosis = dosis;
    }

    public BigDecimal getPosologia() {
        return posologia;
    }

    public void setPosologia(BigDecimal posologia) {
        this.posologia = posologia;
    }

    public Boolean getPosfechado() {
        return posfechado;
    }

    public void setPosfechado(Boolean posfechado) {
        this.posfechado = posfechado;
    }

    public Date getFechaPosfechado() {
        return fechaPosfechado;
    }

    public void setFechaPosfechado(Date fechaPosfechado) {
        this.fechaPosfechado = fechaPosfechado;
    }

    public Boolean getPosfechadoPrincipal() {
        return posfechadoPrincipal;
    }

    public void setPosfechadoPrincipal(Boolean posfechadoPrincipal) {
        this.posfechadoPrincipal = posfechadoPrincipal;
    }

    public Boolean getEfectosAdversos() {
        return efectosAdversos;
    }

    public void setEfectosAdversos(Boolean efectosAdversos) {
        this.efectosAdversos = efectosAdversos;
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
    public List<AuAnexo4Items> getAuAnexo4ItemsList() {
        return auAnexo4ItemsList;
    }

    public void setAuAnexo4ItemsList(List<AuAnexo4Items> auAnexo4ItemsList) {
        this.auAnexo4ItemsList = auAnexo4ItemsList;
    }

    @XmlTransient
    public List<AuAnexo3Historicos> getAuAnexo3HistoricosList() {
        return auAnexo3HistoricosList;
    }

    public void setAuAnexo3HistoricosList(List<AuAnexo3Historicos> auAnexo3HistoricosList) {
        this.auAnexo3HistoricosList = auAnexo3HistoricosList;
    }

    @XmlTransient
    public List<AuCotizacionItems> getAuCotizacionItemsList() {
        return auCotizacionItemsList;
    }

    public void setAuCotizacionItemsList(List<AuCotizacionItems> auCotizacionItemsList) {
        this.auCotizacionItemsList = auCotizacionItemsList;
    }

    @XmlTransient
    public List<AuRechazoItems> getAuRechazoItemsList() {
        return auRechazoItemsList;
    }

    public void setAuRechazoItemsList(List<AuRechazoItems> auRechazoItemsList) {
        this.auRechazoItemsList = auRechazoItemsList;
    }

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public AuGrupos getAuGruposId() {
        return auGruposId;
    }

    public void setAuGruposId(AuGrupos auGruposId) {
        this.auGruposId = auGruposId;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    @XmlTransient
    public List<PeDireccionadoItems> getPeDireccionadoItemsList() {
        return peDireccionadoItemsList;
    }

    public void setPeDireccionadoItemsList(List<PeDireccionadoItems> peDireccionadoItemsList) {
        this.peDireccionadoItemsList = peDireccionadoItemsList;
    }

    @XmlTransient
    public List<AuSeguimientos> getAuSeguimientosList() {
        return auSeguimientosList;
    }

    public void setAuSeguimientosList(List<AuSeguimientos> auSeguimientosList) {
        this.auSeguimientosList = auSeguimientosList;
    }

    @XmlTransient
    public List<AuItemBitacoras> getAuItemBitacorasList() {
        return auItemBitacorasList;
    }

    public void setAuItemBitacorasList(List<AuItemBitacoras> auItemBitacorasList) {
        this.auItemBitacorasList = auItemBitacorasList;
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
        if (!(object instanceof AuAnexo3Items)) {
            return false;
        }
        AuAnexo3Items other = (AuAnexo3Items) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo3Items[ id=" + id + " ]";
    }
    
}
