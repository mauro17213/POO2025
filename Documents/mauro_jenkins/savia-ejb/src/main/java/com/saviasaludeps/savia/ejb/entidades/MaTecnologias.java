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
@Table(name = "ma_tecnologias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaTecnologias.findAll", query = "SELECT m FROM MaTecnologias m"),
    @NamedQuery(name = "MaTecnologias.findById", query = "SELECT m FROM MaTecnologias m WHERE m.id = :id"),
    @NamedQuery(name = "MaTecnologias.findByMaeGrupoTecnologiaId", query = "SELECT m FROM MaTecnologias m WHERE m.maeGrupoTecnologiaId = :maeGrupoTecnologiaId"),
    @NamedQuery(name = "MaTecnologias.findByMaeGrupoTecnologiaCodigo", query = "SELECT m FROM MaTecnologias m WHERE m.maeGrupoTecnologiaCodigo = :maeGrupoTecnologiaCodigo"),
    @NamedQuery(name = "MaTecnologias.findByMaeGrupoTecnologiaValor", query = "SELECT m FROM MaTecnologias m WHERE m.maeGrupoTecnologiaValor = :maeGrupoTecnologiaValor"),
    @NamedQuery(name = "MaTecnologias.findByMaeTipoTecnologiaId", query = "SELECT m FROM MaTecnologias m WHERE m.maeTipoTecnologiaId = :maeTipoTecnologiaId"),
    @NamedQuery(name = "MaTecnologias.findByMaeTipoTecnologiaCodigo", query = "SELECT m FROM MaTecnologias m WHERE m.maeTipoTecnologiaCodigo = :maeTipoTecnologiaCodigo"),
    @NamedQuery(name = "MaTecnologias.findByMaeTipoTecnologiaValor", query = "SELECT m FROM MaTecnologias m WHERE m.maeTipoTecnologiaValor = :maeTipoTecnologiaValor"),
    @NamedQuery(name = "MaTecnologias.findByGrupoDescripcion", query = "SELECT m FROM MaTecnologias m WHERE m.grupoDescripcion = :grupoDescripcion"),
    @NamedQuery(name = "MaTecnologias.findByCups", query = "SELECT m FROM MaTecnologias m WHERE m.cups = :cups"),
    @NamedQuery(name = "MaTecnologias.findByCupsDescipcion", query = "SELECT m FROM MaTecnologias m WHERE m.cupsDescipcion = :cupsDescipcion"),
    @NamedQuery(name = "MaTecnologias.findByCodigoPropio", query = "SELECT m FROM MaTecnologias m WHERE m.codigoPropio = :codigoPropio"),
    @NamedQuery(name = "MaTecnologias.findByPropioDescripcion", query = "SELECT m FROM MaTecnologias m WHERE m.propioDescripcion = :propioDescripcion"),
    @NamedQuery(name = "MaTecnologias.findByAplicaSubsidiado", query = "SELECT m FROM MaTecnologias m WHERE m.aplicaSubsidiado = :aplicaSubsidiado"),
    @NamedQuery(name = "MaTecnologias.findByAplicaContributivo", query = "SELECT m FROM MaTecnologias m WHERE m.aplicaContributivo = :aplicaContributivo"),
    @NamedQuery(name = "MaTecnologias.findBySexoAplica", query = "SELECT m FROM MaTecnologias m WHERE m.sexoAplica = :sexoAplica"),
    @NamedQuery(name = "MaTecnologias.findByMaeCoberturaId", query = "SELECT m FROM MaTecnologias m WHERE m.maeCoberturaId = :maeCoberturaId"),
    @NamedQuery(name = "MaTecnologias.findByMaeCoberturaCodigo", query = "SELECT m FROM MaTecnologias m WHERE m.maeCoberturaCodigo = :maeCoberturaCodigo"),
    @NamedQuery(name = "MaTecnologias.findByMaeCoberturaValor", query = "SELECT m FROM MaTecnologias m WHERE m.maeCoberturaValor = :maeCoberturaValor"),
    @NamedQuery(name = "MaTecnologias.findByCobertura", query = "SELECT m FROM MaTecnologias m WHERE m.cobertura = :cobertura"),
    @NamedQuery(name = "MaTecnologias.findByNivelComplejidad", query = "SELECT m FROM MaTecnologias m WHERE m.nivelComplejidad = :nivelComplejidad"),
    @NamedQuery(name = "MaTecnologias.findByEdadDesde", query = "SELECT m FROM MaTecnologias m WHERE m.edadDesde = :edadDesde"),
    @NamedQuery(name = "MaTecnologias.findByUnidadDesde", query = "SELECT m FROM MaTecnologias m WHERE m.unidadDesde = :unidadDesde"),
    @NamedQuery(name = "MaTecnologias.findByEdadHasta", query = "SELECT m FROM MaTecnologias m WHERE m.edadHasta = :edadHasta"),
    @NamedQuery(name = "MaTecnologias.findByUnidadHasta", query = "SELECT m FROM MaTecnologias m WHERE m.unidadHasta = :unidadHasta"),
    @NamedQuery(name = "MaTecnologias.findByCodigoFinanciador", query = "SELECT m FROM MaTecnologias m WHERE m.codigoFinanciador = :codigoFinanciador"),
    @NamedQuery(name = "MaTecnologias.findByTipoFrecuencia", query = "SELECT m FROM MaTecnologias m WHERE m.tipoFrecuencia = :tipoFrecuencia"),
    @NamedQuery(name = "MaTecnologias.findByFrecuencia", query = "SELECT m FROM MaTecnologias m WHERE m.frecuencia = :frecuencia"),
    @NamedQuery(name = "MaTecnologias.findByTipoFrecuencia2", query = "SELECT m FROM MaTecnologias m WHERE m.tipoFrecuencia2 = :tipoFrecuencia2"),
    @NamedQuery(name = "MaTecnologias.findByFrecuencia2", query = "SELECT m FROM MaTecnologias m WHERE m.frecuencia2 = :frecuencia2"),
    @NamedQuery(name = "MaTecnologias.findByEventoUnico", query = "SELECT m FROM MaTecnologias m WHERE m.eventoUnico = :eventoUnico"),
    @NamedQuery(name = "MaTecnologias.findByActivo", query = "SELECT m FROM MaTecnologias m WHERE m.activo = :activo"),
    @NamedQuery(name = "MaTecnologias.findByMaeAmbitoId", query = "SELECT m FROM MaTecnologias m WHERE m.maeAmbitoId = :maeAmbitoId"),
    @NamedQuery(name = "MaTecnologias.findByMaeAmbitoCodigo", query = "SELECT m FROM MaTecnologias m WHERE m.maeAmbitoCodigo = :maeAmbitoCodigo"),
    @NamedQuery(name = "MaTecnologias.findByMaeAmbitoValor", query = "SELECT m FROM MaTecnologias m WHERE m.maeAmbitoValor = :maeAmbitoValor"),
    @NamedQuery(name = "MaTecnologias.findByTipoPago", query = "SELECT m FROM MaTecnologias m WHERE m.tipoPago = :tipoPago"),
    @NamedQuery(name = "MaTecnologias.findByComplejidad", query = "SELECT m FROM MaTecnologias m WHERE m.complejidad = :complejidad"),
    @NamedQuery(name = "MaTecnologias.findByAclaracion", query = "SELECT m FROM MaTecnologias m WHERE m.aclaracion = :aclaracion"),
    @NamedQuery(name = "MaTecnologias.findByCondicion", query = "SELECT m FROM MaTecnologias m WHERE m.condicion = :condicion"),
    @NamedQuery(name = "MaTecnologias.findByVigenciaAutorizacion", query = "SELECT m FROM MaTecnologias m WHERE m.vigenciaAutorizacion = :vigenciaAutorizacion"),
    @NamedQuery(name = "MaTecnologias.findByIdSiifa", query = "SELECT m FROM MaTecnologias m WHERE m.idSiifa = :idSiifa"),
    @NamedQuery(name = "MaTecnologias.findByUsuarioCrea", query = "SELECT m FROM MaTecnologias m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaTecnologias.findByTerminalCrea", query = "SELECT m FROM MaTecnologias m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaTecnologias.findByFechaHoraCrea", query = "SELECT m FROM MaTecnologias m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaTecnologias.findByUsuarioModifica", query = "SELECT m FROM MaTecnologias m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaTecnologias.findByTerminalModifica", query = "SELECT m FROM MaTecnologias m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaTecnologias.findByFechaHoraModifica", query = "SELECT m FROM MaTecnologias m WHERE m.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "MaTecnologias.findByMaSoatId", query = "SELECT m FROM MaTecnologias m WHERE m.maSoatId = :maSoatId"),
    @NamedQuery(name = "MaTecnologias.findByMaSoatCodigo", query = "SELECT m FROM MaTecnologias m WHERE m.maSoatCodigo = :maSoatCodigo"),
    @NamedQuery(name = "MaTecnologias.findByMaSoatValor", query = "SELECT m FROM MaTecnologias m WHERE m.maSoatValor = :maSoatValor"),
    @NamedQuery(name = "MaTecnologias.findByMaIss2000Id", query = "SELECT m FROM MaTecnologias m WHERE m.maIss2000Id = :maIss2000Id"),
    @NamedQuery(name = "MaTecnologias.findByMaIss2000Codigo", query = "SELECT m FROM MaTecnologias m WHERE m.maIss2000Codigo = :maIss2000Codigo"),
    @NamedQuery(name = "MaTecnologias.findByMaIss2000Valor", query = "SELECT m FROM MaTecnologias m WHERE m.maIss2000Valor = :maIss2000Valor"),
    @NamedQuery(name = "MaTecnologias.findByMaIss2001Id", query = "SELECT m FROM MaTecnologias m WHERE m.maIss2001Id = :maIss2001Id"),
    @NamedQuery(name = "MaTecnologias.findByMaIss2001Codigo", query = "SELECT m FROM MaTecnologias m WHERE m.maIss2001Codigo = :maIss2001Codigo"),
    @NamedQuery(name = "MaTecnologias.findByMaIss2001Valor", query = "SELECT m FROM MaTecnologias m WHERE m.maIss2001Valor = :maIss2001Valor")})
public class MaTecnologias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_grupo_tecnologia_id")
    private Integer maeGrupoTecnologiaId;
    @Size(max = 8)
    @Column(name = "mae_grupo_tecnologia_codigo")
    private String maeGrupoTecnologiaCodigo;
    @Size(max = 128)
    @Column(name = "mae_grupo_tecnologia_valor")
    private String maeGrupoTecnologiaValor;
    @Column(name = "mae_tipo_tecnologia_id")
    private Integer maeTipoTecnologiaId;
    @Size(max = 8)
    @Column(name = "mae_tipo_tecnologia_codigo")
    private String maeTipoTecnologiaCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_tecnologia_valor")
    private String maeTipoTecnologiaValor;
    @Size(max = 64)
    @Column(name = "grupo_descripcion")
    private String grupoDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "cups")
    private String cups;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "cups_descipcion")
    private String cupsDescipcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo_propio")
    private String codigoPropio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "propio_descripcion")
    private String propioDescripcion;
    @Column(name = "aplica_subsidiado")
    private Boolean aplicaSubsidiado;
    @Column(name = "aplica_contributivo")
    private Boolean aplicaContributivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexo_aplica")
    private int sexoAplica;
    @Column(name = "mae_cobertura_id")
    private Integer maeCoberturaId;
    @Size(max = 8)
    @Column(name = "mae_cobertura_codigo")
    private String maeCoberturaCodigo;
    @Size(max = 128)
    @Column(name = "mae_cobertura_valor")
    private String maeCoberturaValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cobertura")
    private int cobertura;
    @Column(name = "nivel_complejidad")
    private Integer nivelComplejidad;
    @Column(name = "edad_desde")
    private Integer edadDesde;
    @Column(name = "unidad_desde")
    private Integer unidadDesde;
    @Column(name = "edad_hasta")
    private Integer edadHasta;
    @Column(name = "unidad_hasta")
    private Integer unidadHasta;
    @Size(max = 20)
    @Column(name = "codigo_financiador")
    private String codigoFinanciador;
    @Column(name = "tipo_frecuencia")
    private Integer tipoFrecuencia;
    @Column(name = "frecuencia")
    private Integer frecuencia;
    @Column(name = "tipo_frecuencia_2")
    private Integer tipoFrecuencia2;
    @Column(name = "frecuencia_2")
    private Integer frecuencia2;
    @Column(name = "evento_unico")
    private Boolean eventoUnico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "mae_ambito_id")
    private Integer maeAmbitoId;
    @Size(max = 16)
    @Column(name = "mae_ambito_codigo")
    private String maeAmbitoCodigo;
    @Size(max = 128)
    @Column(name = "mae_ambito_valor")
    private String maeAmbitoValor;
    @Size(max = 4)
    @Column(name = "tipo_pago")
    private String tipoPago;
    @Column(name = "complejidad")
    private Integer complejidad;
    @Size(max = 1024)
    @Column(name = "aclaracion")
    private String aclaracion;
    @Size(max = 1024)
    @Column(name = "condicion")
    private String condicion;
    @Column(name = "vigencia_autorizacion")
    private Integer vigenciaAutorizacion;
    @Column(name = "id_siifa")
    private Integer idSiifa;
    @Size(max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Size(max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
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
    @Column(name = "ma_soat_id")
    private Integer maSoatId;
    @Size(max = 100)
    @Column(name = "ma_soat_codigo")
    private String maSoatCodigo;
    @Size(max = 512)
    @Column(name = "ma_soat_valor")
    private String maSoatValor;
    @Column(name = "ma_iss2000_id")
    private Integer maIss2000Id;
    @Size(max = 100)
    @Column(name = "ma_iss2000_codigo")
    private String maIss2000Codigo;
    @Size(max = 512)
    @Column(name = "ma_iss2000_valor")
    private String maIss2000Valor;
    @Column(name = "ma_iss2001_id")
    private Integer maIss2001Id;
    @Size(max = 100)
    @Column(name = "ma_iss2001_codigo")
    private String maIss2001Codigo;
    @Size(max = 512)
    @Column(name = "ma_iss2001_valor")
    private String maIss2001Valor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maTecnologiasId", fetch = FetchType.LAZY)
    private List<MaTecnologiasMipres> maTecnologiasMipresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maTecnologiasId", fetch = FetchType.LAZY)
    private List<MaTecnologiaGrupos> maTecnologiaGruposList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maTecnologiasId", fetch = FetchType.LAZY)
    private List<MaTecnologiaServiciosHabilitacion> maTecnologiaServiciosHabilitacionList;
    @OneToMany(mappedBy = "maTecnologiasId", fetch = FetchType.LAZY)
    private List<MaPaqueteTecnologias> maPaqueteTecnologiasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maTecnologiasId", fetch = FetchType.LAZY)
    private List<MaTecnologiaCups> maTecnologiaCupsList;
    @OneToMany(mappedBy = "maTecnologiasId", fetch = FetchType.LAZY)
    private List<MaPaquetes> maPaquetesList;
    @JoinColumn(name = "ma_iss2000_tarifarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaIss2000Tarifarios maIss2000TarifariosId;
    @JoinColumn(name = "ma_iss2001_tarifarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaIss2001Tarifarios maIss2001TarifariosId;
    @JoinColumn(name = "ma_soat_tarifarios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MaSoatTarifarios maSoatTarifariosId;

    public MaTecnologias() {
    }

    public MaTecnologias(Integer id) {
        this.id = id;
    }

    public MaTecnologias(Integer id, String cups, String cupsDescipcion, String codigoPropio, String propioDescripcion, int sexoAplica, int cobertura, boolean activo) {
        this.id = id;
        this.cups = cups;
        this.cupsDescipcion = cupsDescipcion;
        this.codigoPropio = codigoPropio;
        this.propioDescripcion = propioDescripcion;
        this.sexoAplica = sexoAplica;
        this.cobertura = cobertura;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeGrupoTecnologiaId() {
        return maeGrupoTecnologiaId;
    }

    public void setMaeGrupoTecnologiaId(Integer maeGrupoTecnologiaId) {
        this.maeGrupoTecnologiaId = maeGrupoTecnologiaId;
    }

    public String getMaeGrupoTecnologiaCodigo() {
        return maeGrupoTecnologiaCodigo;
    }

    public void setMaeGrupoTecnologiaCodigo(String maeGrupoTecnologiaCodigo) {
        this.maeGrupoTecnologiaCodigo = maeGrupoTecnologiaCodigo;
    }

    public String getMaeGrupoTecnologiaValor() {
        return maeGrupoTecnologiaValor;
    }

    public void setMaeGrupoTecnologiaValor(String maeGrupoTecnologiaValor) {
        this.maeGrupoTecnologiaValor = maeGrupoTecnologiaValor;
    }

    public Integer getMaeTipoTecnologiaId() {
        return maeTipoTecnologiaId;
    }

    public void setMaeTipoTecnologiaId(Integer maeTipoTecnologiaId) {
        this.maeTipoTecnologiaId = maeTipoTecnologiaId;
    }

    public String getMaeTipoTecnologiaCodigo() {
        return maeTipoTecnologiaCodigo;
    }

    public void setMaeTipoTecnologiaCodigo(String maeTipoTecnologiaCodigo) {
        this.maeTipoTecnologiaCodigo = maeTipoTecnologiaCodigo;
    }

    public String getMaeTipoTecnologiaValor() {
        return maeTipoTecnologiaValor;
    }

    public void setMaeTipoTecnologiaValor(String maeTipoTecnologiaValor) {
        this.maeTipoTecnologiaValor = maeTipoTecnologiaValor;
    }

    public String getGrupoDescripcion() {
        return grupoDescripcion;
    }

    public void setGrupoDescripcion(String grupoDescripcion) {
        this.grupoDescripcion = grupoDescripcion;
    }

    public String getCups() {
        return cups;
    }

    public void setCups(String cups) {
        this.cups = cups;
    }

    public String getCupsDescipcion() {
        return cupsDescipcion;
    }

    public void setCupsDescipcion(String cupsDescipcion) {
        this.cupsDescipcion = cupsDescipcion;
    }

    public String getCodigoPropio() {
        return codigoPropio;
    }

    public void setCodigoPropio(String codigoPropio) {
        this.codigoPropio = codigoPropio;
    }

    public String getPropioDescripcion() {
        return propioDescripcion;
    }

    public void setPropioDescripcion(String propioDescripcion) {
        this.propioDescripcion = propioDescripcion;
    }

    public Boolean getAplicaSubsidiado() {
        return aplicaSubsidiado;
    }

    public void setAplicaSubsidiado(Boolean aplicaSubsidiado) {
        this.aplicaSubsidiado = aplicaSubsidiado;
    }

    public Boolean getAplicaContributivo() {
        return aplicaContributivo;
    }

    public void setAplicaContributivo(Boolean aplicaContributivo) {
        this.aplicaContributivo = aplicaContributivo;
    }

    public int getSexoAplica() {
        return sexoAplica;
    }

    public void setSexoAplica(int sexoAplica) {
        this.sexoAplica = sexoAplica;
    }

    public Integer getMaeCoberturaId() {
        return maeCoberturaId;
    }

    public void setMaeCoberturaId(Integer maeCoberturaId) {
        this.maeCoberturaId = maeCoberturaId;
    }

    public String getMaeCoberturaCodigo() {
        return maeCoberturaCodigo;
    }

    public void setMaeCoberturaCodigo(String maeCoberturaCodigo) {
        this.maeCoberturaCodigo = maeCoberturaCodigo;
    }

    public String getMaeCoberturaValor() {
        return maeCoberturaValor;
    }

    public void setMaeCoberturaValor(String maeCoberturaValor) {
        this.maeCoberturaValor = maeCoberturaValor;
    }

    public int getCobertura() {
        return cobertura;
    }

    public void setCobertura(int cobertura) {
        this.cobertura = cobertura;
    }

    public Integer getNivelComplejidad() {
        return nivelComplejidad;
    }

    public void setNivelComplejidad(Integer nivelComplejidad) {
        this.nivelComplejidad = nivelComplejidad;
    }

    public Integer getEdadDesde() {
        return edadDesde;
    }

    public void setEdadDesde(Integer edadDesde) {
        this.edadDesde = edadDesde;
    }

    public Integer getUnidadDesde() {
        return unidadDesde;
    }

    public void setUnidadDesde(Integer unidadDesde) {
        this.unidadDesde = unidadDesde;
    }

    public Integer getEdadHasta() {
        return edadHasta;
    }

    public void setEdadHasta(Integer edadHasta) {
        this.edadHasta = edadHasta;
    }

    public Integer getUnidadHasta() {
        return unidadHasta;
    }

    public void setUnidadHasta(Integer unidadHasta) {
        this.unidadHasta = unidadHasta;
    }

    public String getCodigoFinanciador() {
        return codigoFinanciador;
    }

    public void setCodigoFinanciador(String codigoFinanciador) {
        this.codigoFinanciador = codigoFinanciador;
    }

    public Integer getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    public void setTipoFrecuencia(Integer tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getTipoFrecuencia2() {
        return tipoFrecuencia2;
    }

    public void setTipoFrecuencia2(Integer tipoFrecuencia2) {
        this.tipoFrecuencia2 = tipoFrecuencia2;
    }

    public Integer getFrecuencia2() {
        return frecuencia2;
    }

    public void setFrecuencia2(Integer frecuencia2) {
        this.frecuencia2 = frecuencia2;
    }

    public Boolean getEventoUnico() {
        return eventoUnico;
    }

    public void setEventoUnico(Boolean eventoUnico) {
        this.eventoUnico = eventoUnico;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Integer getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(Integer complejidad) {
        this.complejidad = complejidad;
    }

    public String getAclaracion() {
        return aclaracion;
    }

    public void setAclaracion(String aclaracion) {
        this.aclaracion = aclaracion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Integer getVigenciaAutorizacion() {
        return vigenciaAutorizacion;
    }

    public void setVigenciaAutorizacion(Integer vigenciaAutorizacion) {
        this.vigenciaAutorizacion = vigenciaAutorizacion;
    }

    public Integer getIdSiifa() {
        return idSiifa;
    }

    public void setIdSiifa(Integer idSiifa) {
        this.idSiifa = idSiifa;
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

    public Integer getMaSoatId() {
        return maSoatId;
    }

    public void setMaSoatId(Integer maSoatId) {
        this.maSoatId = maSoatId;
    }

    public String getMaSoatCodigo() {
        return maSoatCodigo;
    }

    public void setMaSoatCodigo(String maSoatCodigo) {
        this.maSoatCodigo = maSoatCodigo;
    }

    public String getMaSoatValor() {
        return maSoatValor;
    }

    public void setMaSoatValor(String maSoatValor) {
        this.maSoatValor = maSoatValor;
    }

    public Integer getMaIss2000Id() {
        return maIss2000Id;
    }

    public void setMaIss2000Id(Integer maIss2000Id) {
        this.maIss2000Id = maIss2000Id;
    }

    public String getMaIss2000Codigo() {
        return maIss2000Codigo;
    }

    public void setMaIss2000Codigo(String maIss2000Codigo) {
        this.maIss2000Codigo = maIss2000Codigo;
    }

    public String getMaIss2000Valor() {
        return maIss2000Valor;
    }

    public void setMaIss2000Valor(String maIss2000Valor) {
        this.maIss2000Valor = maIss2000Valor;
    }

    public Integer getMaIss2001Id() {
        return maIss2001Id;
    }

    public void setMaIss2001Id(Integer maIss2001Id) {
        this.maIss2001Id = maIss2001Id;
    }

    public String getMaIss2001Codigo() {
        return maIss2001Codigo;
    }

    public void setMaIss2001Codigo(String maIss2001Codigo) {
        this.maIss2001Codigo = maIss2001Codigo;
    }

    public String getMaIss2001Valor() {
        return maIss2001Valor;
    }

    public void setMaIss2001Valor(String maIss2001Valor) {
        this.maIss2001Valor = maIss2001Valor;
    }

    @XmlTransient
    public List<MaTecnologiasMipres> getMaTecnologiasMipresList() {
        return maTecnologiasMipresList;
    }

    public void setMaTecnologiasMipresList(List<MaTecnologiasMipres> maTecnologiasMipresList) {
        this.maTecnologiasMipresList = maTecnologiasMipresList;
    }

    @XmlTransient
    public List<MaTecnologiaGrupos> getMaTecnologiaGruposList() {
        return maTecnologiaGruposList;
    }

    public void setMaTecnologiaGruposList(List<MaTecnologiaGrupos> maTecnologiaGruposList) {
        this.maTecnologiaGruposList = maTecnologiaGruposList;
    }

    @XmlTransient
    public List<MaTecnologiaServiciosHabilitacion> getMaTecnologiaServiciosHabilitacionList() {
        return maTecnologiaServiciosHabilitacionList;
    }

    public void setMaTecnologiaServiciosHabilitacionList(List<MaTecnologiaServiciosHabilitacion> maTecnologiaServiciosHabilitacionList) {
        this.maTecnologiaServiciosHabilitacionList = maTecnologiaServiciosHabilitacionList;
    }

    @XmlTransient
    public List<MaPaqueteTecnologias> getMaPaqueteTecnologiasList() {
        return maPaqueteTecnologiasList;
    }

    public void setMaPaqueteTecnologiasList(List<MaPaqueteTecnologias> maPaqueteTecnologiasList) {
        this.maPaqueteTecnologiasList = maPaqueteTecnologiasList;
    }

    @XmlTransient
    public List<MaTecnologiaCups> getMaTecnologiaCupsList() {
        return maTecnologiaCupsList;
    }

    public void setMaTecnologiaCupsList(List<MaTecnologiaCups> maTecnologiaCupsList) {
        this.maTecnologiaCupsList = maTecnologiaCupsList;
    }

    @XmlTransient
    public List<MaPaquetes> getMaPaquetesList() {
        return maPaquetesList;
    }

    public void setMaPaquetesList(List<MaPaquetes> maPaquetesList) {
        this.maPaquetesList = maPaquetesList;
    }

    public MaIss2000Tarifarios getMaIss2000TarifariosId() {
        return maIss2000TarifariosId;
    }

    public void setMaIss2000TarifariosId(MaIss2000Tarifarios maIss2000TarifariosId) {
        this.maIss2000TarifariosId = maIss2000TarifariosId;
    }

    public MaIss2001Tarifarios getMaIss2001TarifariosId() {
        return maIss2001TarifariosId;
    }

    public void setMaIss2001TarifariosId(MaIss2001Tarifarios maIss2001TarifariosId) {
        this.maIss2001TarifariosId = maIss2001TarifariosId;
    }

    public MaSoatTarifarios getMaSoatTarifariosId() {
        return maSoatTarifariosId;
    }

    public void setMaSoatTarifariosId(MaSoatTarifarios maSoatTarifariosId) {
        this.maSoatTarifariosId = maSoatTarifariosId;
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
        if (!(object instanceof MaTecnologias)) {
            return false;
        }
        MaTecnologias other = (MaTecnologias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaTecnologias[ id=" + id + " ]";
    }
    
}
