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
import javax.persistence.Lob;
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
@Table(name = "ma_medicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaMedicamentos.findAll", query = "SELECT m FROM MaMedicamentos m"),
    @NamedQuery(name = "MaMedicamentos.findById", query = "SELECT m FROM MaMedicamentos m WHERE m.id = :id"),
    @NamedQuery(name = "MaMedicamentos.findByCum", query = "SELECT m FROM MaMedicamentos m WHERE m.cum = :cum"),
    @NamedQuery(name = "MaMedicamentos.findByDescripcionInvima", query = "SELECT m FROM MaMedicamentos m WHERE m.descripcionInvima = :descripcionInvima"),
    @NamedQuery(name = "MaMedicamentos.findByDescripcionEstandarizada", query = "SELECT m FROM MaMedicamentos m WHERE m.descripcionEstandarizada = :descripcionEstandarizada"),
    @NamedQuery(name = "MaMedicamentos.findByDescripcionLargaEstandarizada", query = "SELECT m FROM MaMedicamentos m WHERE m.descripcionLargaEstandarizada = :descripcionLargaEstandarizada"),
    @NamedQuery(name = "MaMedicamentos.findByMaeCoberturaId", query = "SELECT m FROM MaMedicamentos m WHERE m.maeCoberturaId = :maeCoberturaId"),
    @NamedQuery(name = "MaMedicamentos.findByMaeCoberturaCodigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maeCoberturaCodigo = :maeCoberturaCodigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeCoberturaValor", query = "SELECT m FROM MaMedicamentos m WHERE m.maeCoberturaValor = :maeCoberturaValor"),
    @NamedQuery(name = "MaMedicamentos.findByMaeConcentracionId", query = "SELECT m FROM MaMedicamentos m WHERE m.maeConcentracionId = :maeConcentracionId"),
    @NamedQuery(name = "MaMedicamentos.findByMaeConcentracionCodigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maeConcentracionCodigo = :maeConcentracionCodigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeConcentracionValor", query = "SELECT m FROM MaMedicamentos m WHERE m.maeConcentracionValor = :maeConcentracionValor"),
    @NamedQuery(name = "MaMedicamentos.findByMaePrincipioActivoId", query = "SELECT m FROM MaMedicamentos m WHERE m.maePrincipioActivoId = :maePrincipioActivoId"),
    @NamedQuery(name = "MaMedicamentos.findByMaePrincipioActivoCodigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maePrincipioActivoCodigo = :maePrincipioActivoCodigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaePrincipioActivoValor", query = "SELECT m FROM MaMedicamentos m WHERE m.maePrincipioActivoValor = :maePrincipioActivoValor"),
    @NamedQuery(name = "MaMedicamentos.findByMaeFormaFarmaceuticaId", query = "SELECT m FROM MaMedicamentos m WHERE m.maeFormaFarmaceuticaId = :maeFormaFarmaceuticaId"),
    @NamedQuery(name = "MaMedicamentos.findByMaeFormaFarmaceuticaCodigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maeFormaFarmaceuticaCodigo = :maeFormaFarmaceuticaCodigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeFormaFarmaceuticaValor", query = "SELECT m FROM MaMedicamentos m WHERE m.maeFormaFarmaceuticaValor = :maeFormaFarmaceuticaValor"),
    @NamedQuery(name = "MaMedicamentos.findByMaeTipoPpmId", query = "SELECT m FROM MaMedicamentos m WHERE m.maeTipoPpmId = :maeTipoPpmId"),
    @NamedQuery(name = "MaMedicamentos.findByMaeTipoPpmCodigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maeTipoPpmCodigo = :maeTipoPpmCodigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeTipoPpmValor", query = "SELECT m FROM MaMedicamentos m WHERE m.maeTipoPpmValor = :maeTipoPpmValor"),
    @NamedQuery(name = "MaMedicamentos.findByEsAltoCosto", query = "SELECT m FROM MaMedicamentos m WHERE m.esAltoCosto = :esAltoCosto"),
    @NamedQuery(name = "MaMedicamentos.findByEsCapitado", query = "SELECT m FROM MaMedicamentos m WHERE m.esCapitado = :esCapitado"),
    @NamedQuery(name = "MaMedicamentos.findByAplicaSubsidiado", query = "SELECT m FROM MaMedicamentos m WHERE m.aplicaSubsidiado = :aplicaSubsidiado"),
    @NamedQuery(name = "MaMedicamentos.findByAplicaContributivo", query = "SELECT m FROM MaMedicamentos m WHERE m.aplicaContributivo = :aplicaContributivo"),
    @NamedQuery(name = "MaMedicamentos.findBySexoAplica", query = "SELECT m FROM MaMedicamentos m WHERE m.sexoAplica = :sexoAplica"),
    @NamedQuery(name = "MaMedicamentos.findByCodigoIum", query = "SELECT m FROM MaMedicamentos m WHERE m.codigoIum = :codigoIum"),
    @NamedQuery(name = "MaMedicamentos.findByEdadDesde", query = "SELECT m FROM MaMedicamentos m WHERE m.edadDesde = :edadDesde"),
    @NamedQuery(name = "MaMedicamentos.findByEdadHasta", query = "SELECT m FROM MaMedicamentos m WHERE m.edadHasta = :edadHasta"),
    @NamedQuery(name = "MaMedicamentos.findByEsRegulado", query = "SELECT m FROM MaMedicamentos m WHERE m.esRegulado = :esRegulado"),
    @NamedQuery(name = "MaMedicamentos.findByValorMaximoRegulado", query = "SELECT m FROM MaMedicamentos m WHERE m.valorMaximoRegulado = :valorMaximoRegulado"),
    @NamedQuery(name = "MaMedicamentos.findByValorReferenteMinimo", query = "SELECT m FROM MaMedicamentos m WHERE m.valorReferenteMinimo = :valorReferenteMinimo"),
    @NamedQuery(name = "MaMedicamentos.findByValorReferete", query = "SELECT m FROM MaMedicamentos m WHERE m.valorReferete = :valorReferete"),
    @NamedQuery(name = "MaMedicamentos.findByExpediente", query = "SELECT m FROM MaMedicamentos m WHERE m.expediente = :expediente"),
    @NamedQuery(name = "MaMedicamentos.findByNombreComercial", query = "SELECT m FROM MaMedicamentos m WHERE m.nombreComercial = :nombreComercial"),
    @NamedQuery(name = "MaMedicamentos.findByLaboratorio", query = "SELECT m FROM MaMedicamentos m WHERE m.laboratorio = :laboratorio"),
    @NamedQuery(name = "MaMedicamentos.findByRegistroSanitario", query = "SELECT m FROM MaMedicamentos m WHERE m.registroSanitario = :registroSanitario"),
    @NamedQuery(name = "MaMedicamentos.findByFechaExpedicion", query = "SELECT m FROM MaMedicamentos m WHERE m.fechaExpedicion = :fechaExpedicion"),
    @NamedQuery(name = "MaMedicamentos.findByFechaVencimiento", query = "SELECT m FROM MaMedicamentos m WHERE m.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "MaMedicamentos.findByMaeEstadoRegistroSanitarioId", query = "SELECT m FROM MaMedicamentos m WHERE m.maeEstadoRegistroSanitarioId = :maeEstadoRegistroSanitarioId"),
    @NamedQuery(name = "MaMedicamentos.findByMaeEstadoRegistroSanitarioCodigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maeEstadoRegistroSanitarioCodigo = :maeEstadoRegistroSanitarioCodigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeEstadoRegistroSanitarioValor", query = "SELECT m FROM MaMedicamentos m WHERE m.maeEstadoRegistroSanitarioValor = :maeEstadoRegistroSanitarioValor"),
    @NamedQuery(name = "MaMedicamentos.findByCantidadCum", query = "SELECT m FROM MaMedicamentos m WHERE m.cantidadCum = :cantidadCum"),
    @NamedQuery(name = "MaMedicamentos.findByFechaActivo", query = "SELECT m FROM MaMedicamentos m WHERE m.fechaActivo = :fechaActivo"),
    @NamedQuery(name = "MaMedicamentos.findByFechaInactivo", query = "SELECT m FROM MaMedicamentos m WHERE m.fechaInactivo = :fechaInactivo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeAtc1Id", query = "SELECT m FROM MaMedicamentos m WHERE m.maeAtc1Id = :maeAtc1Id"),
    @NamedQuery(name = "MaMedicamentos.findByMaeAtc1Codigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maeAtc1Codigo = :maeAtc1Codigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeAtc1Valor", query = "SELECT m FROM MaMedicamentos m WHERE m.maeAtc1Valor = :maeAtc1Valor"),
    @NamedQuery(name = "MaMedicamentos.findByMaeAtc2Id", query = "SELECT m FROM MaMedicamentos m WHERE m.maeAtc2Id = :maeAtc2Id"),
    @NamedQuery(name = "MaMedicamentos.findByMaeAtc2Codigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maeAtc2Codigo = :maeAtc2Codigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeAtc2Valor", query = "SELECT m FROM MaMedicamentos m WHERE m.maeAtc2Valor = :maeAtc2Valor"),
    @NamedQuery(name = "MaMedicamentos.findByMaeAtc3Id", query = "SELECT m FROM MaMedicamentos m WHERE m.maeAtc3Id = :maeAtc3Id"),
    @NamedQuery(name = "MaMedicamentos.findByMaeAtc3Codigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maeAtc3Codigo = :maeAtc3Codigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeAtc3Valor", query = "SELECT m FROM MaMedicamentos m WHERE m.maeAtc3Valor = :maeAtc3Valor"),
    @NamedQuery(name = "MaMedicamentos.findByNormaRegulacion", query = "SELECT m FROM MaMedicamentos m WHERE m.normaRegulacion = :normaRegulacion"),
    @NamedQuery(name = "MaMedicamentos.findByMce", query = "SELECT m FROM MaMedicamentos m WHERE m.mce = :mce"),
    @NamedQuery(name = "MaMedicamentos.findByMonopolioEstado", query = "SELECT m FROM MaMedicamentos m WHERE m.monopolioEstado = :monopolioEstado"),
    @NamedQuery(name = "MaMedicamentos.findByEstrechoMargenTerapeutico", query = "SELECT m FROM MaMedicamentos m WHERE m.estrechoMargenTerapeutico = :estrechoMargenTerapeutico"),
    @NamedQuery(name = "MaMedicamentos.findByValorFraccion", query = "SELECT m FROM MaMedicamentos m WHERE m.valorFraccion = :valorFraccion"),
    @NamedQuery(name = "MaMedicamentos.findByValorPresentacionComercial", query = "SELECT m FROM MaMedicamentos m WHERE m.valorPresentacionComercial = :valorPresentacionComercial"),
    @NamedQuery(name = "MaMedicamentos.findByCantidad", query = "SELECT m FROM MaMedicamentos m WHERE m.cantidad = :cantidad"),
    @NamedQuery(name = "MaMedicamentos.findByDiasTratamiento", query = "SELECT m FROM MaMedicamentos m WHERE m.diasTratamiento = :diasTratamiento"),
    @NamedQuery(name = "MaMedicamentos.findByMaeGrupoAnatomicoPpalId", query = "SELECT m FROM MaMedicamentos m WHERE m.maeGrupoAnatomicoPpalId = :maeGrupoAnatomicoPpalId"),
    @NamedQuery(name = "MaMedicamentos.findByMaeGrupoAnatomicoPpalCodigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maeGrupoAnatomicoPpalCodigo = :maeGrupoAnatomicoPpalCodigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeGrupoAnatomicoPpalValor", query = "SELECT m FROM MaMedicamentos m WHERE m.maeGrupoAnatomicoPpalValor = :maeGrupoAnatomicoPpalValor"),
    @NamedQuery(name = "MaMedicamentos.findByMaeGrupoTerapeuticoPpalId", query = "SELECT m FROM MaMedicamentos m WHERE m.maeGrupoTerapeuticoPpalId = :maeGrupoTerapeuticoPpalId"),
    @NamedQuery(name = "MaMedicamentos.findByMaeGrupoTerapeuticoPpalCodigo", query = "SELECT m FROM MaMedicamentos m WHERE m.maeGrupoTerapeuticoPpalCodigo = :maeGrupoTerapeuticoPpalCodigo"),
    @NamedQuery(name = "MaMedicamentos.findByMaeGrupoTerapeuticoPpalValor", query = "SELECT m FROM MaMedicamentos m WHERE m.maeGrupoTerapeuticoPpalValor = :maeGrupoTerapeuticoPpalValor"),
    @NamedQuery(name = "MaMedicamentos.findByDci", query = "SELECT m FROM MaMedicamentos m WHERE m.dci = :dci"),
    @NamedQuery(name = "MaMedicamentos.findByDescripcionDci", query = "SELECT m FROM MaMedicamentos m WHERE m.descripcionDci = :descripcionDci"),
    @NamedQuery(name = "MaMedicamentos.findByProvenienteInvima", query = "SELECT m FROM MaMedicamentos m WHERE m.provenienteInvima = :provenienteInvima"),
    @NamedQuery(name = "MaMedicamentos.findByAgrupadorCondicionPbs", query = "SELECT m FROM MaMedicamentos m WHERE m.agrupadorCondicionPbs = :agrupadorCondicionPbs"),
    @NamedQuery(name = "MaMedicamentos.findByValorComercial", query = "SELECT m FROM MaMedicamentos m WHERE m.valorComercial = :valorComercial"),
    @NamedQuery(name = "MaMedicamentos.findByListaUnirs", query = "SELECT m FROM MaMedicamentos m WHERE m.listaUnirs = :listaUnirs"),
    @NamedQuery(name = "MaMedicamentos.findByCodigoSuficienciaUpc", query = "SELECT m FROM MaMedicamentos m WHERE m.codigoSuficienciaUpc = :codigoSuficienciaUpc"),
    @NamedQuery(name = "MaMedicamentos.findByCantidadConcentracionSufUpc", query = "SELECT m FROM MaMedicamentos m WHERE m.cantidadConcentracionSufUpc = :cantidadConcentracionSufUpc"),
    @NamedQuery(name = "MaMedicamentos.findByUnidadConcentracionSufUpc", query = "SELECT m FROM MaMedicamentos m WHERE m.unidadConcentracionSufUpc = :unidadConcentracionSufUpc"),
    @NamedQuery(name = "MaMedicamentos.findByUnidadMedidaSuficiencia", query = "SELECT m FROM MaMedicamentos m WHERE m.unidadMedidaSuficiencia = :unidadMedidaSuficiencia"),
    @NamedQuery(name = "MaMedicamentos.findByNoPbsMenorValor", query = "SELECT m FROM MaMedicamentos m WHERE m.noPbsMenorValor = :noPbsMenorValor"),
    @NamedQuery(name = "MaMedicamentos.findByMuestraMedica", query = "SELECT m FROM MaMedicamentos m WHERE m.muestraMedica = :muestraMedica"),
    @NamedQuery(name = "MaMedicamentos.findByActivo", query = "SELECT m FROM MaMedicamentos m WHERE m.activo = :activo"),
    @NamedQuery(name = "MaMedicamentos.findByFormaFarmaceutica", query = "SELECT m FROM MaMedicamentos m WHERE m.formaFarmaceutica = :formaFarmaceutica"),
    @NamedQuery(name = "MaMedicamentos.findByIdSiifa", query = "SELECT m FROM MaMedicamentos m WHERE m.idSiifa = :idSiifa"),
    @NamedQuery(name = "MaMedicamentos.findByUsuarioCrea", query = "SELECT m FROM MaMedicamentos m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MaMedicamentos.findByTerminalCrea", query = "SELECT m FROM MaMedicamentos m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MaMedicamentos.findByFechaHoraCrea", query = "SELECT m FROM MaMedicamentos m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MaMedicamentos.findByUsuarioModifica", query = "SELECT m FROM MaMedicamentos m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MaMedicamentos.findByTerminalModifica", query = "SELECT m FROM MaMedicamentos m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MaMedicamentos.findByFechaHoraModifica", query = "SELECT m FROM MaMedicamentos m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MaMedicamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "cum")
    private String cum;
    @Size(max = 512)
    @Column(name = "descripcion_invima")
    private String descripcionInvima;
    @Size(max = 512)
    @Column(name = "descripcion_estandarizada")
    private String descripcionEstandarizada;
    @Size(max = 2048)
    @Column(name = "descripcion_larga_estandarizada")
    private String descripcionLargaEstandarizada;
    @Column(name = "mae_cobertura_id")
    private Integer maeCoberturaId;
    @Size(max = 8)
    @Column(name = "mae_cobertura_codigo")
    private String maeCoberturaCodigo;
    @Size(max = 128)
    @Column(name = "mae_cobertura_valor")
    private String maeCoberturaValor;
    @Column(name = "mae_concentracion_id")
    private Integer maeConcentracionId;
    @Size(max = 8)
    @Column(name = "mae_concentracion_codigo")
    private String maeConcentracionCodigo;
    @Size(max = 128)
    @Column(name = "mae_concentracion_valor")
    private String maeConcentracionValor;
    @Column(name = "mae_principio_activo_id")
    private Integer maePrincipioActivoId;
    @Size(max = 8)
    @Column(name = "mae_principio_activo_codigo")
    private String maePrincipioActivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_principio_activo_valor")
    private String maePrincipioActivoValor;
    @Column(name = "mae_forma_farmaceutica_id")
    private Integer maeFormaFarmaceuticaId;
    @Size(max = 8)
    @Column(name = "mae_forma_farmaceutica_codigo")
    private String maeFormaFarmaceuticaCodigo;
    @Size(max = 128)
    @Column(name = "mae_forma_farmaceutica_valor")
    private String maeFormaFarmaceuticaValor;
    @Column(name = "mae_tipo_ppm_id")
    private Integer maeTipoPpmId;
    @Size(max = 8)
    @Column(name = "mae_tipo_ppm_codigo")
    private String maeTipoPpmCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_ppm_valor")
    private String maeTipoPpmValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_alto_costo")
    private boolean esAltoCosto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_capitado")
    private boolean esCapitado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_subsidiado")
    private boolean aplicaSubsidiado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "aplica_contributivo")
    private boolean aplicaContributivo;
    @Column(name = "sexo_aplica")
    private Integer sexoAplica;
    @Size(max = 16)
    @Column(name = "codigo_ium")
    private String codigoIum;
    @Column(name = "edad_desde")
    private Integer edadDesde;
    @Column(name = "edad_hasta")
    private Integer edadHasta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_regulado")
    private boolean esRegulado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_maximo_regulado")
    private BigDecimal valorMaximoRegulado;
    @Column(name = "valor_referente_minimo")
    private BigDecimal valorReferenteMinimo;
    @Column(name = "valor_referete")
    private BigDecimal valorReferete;
    @Size(max = 64)
    @Column(name = "expediente")
    private String expediente;
    @Size(max = 32)
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Size(max = 32)
    @Column(name = "laboratorio")
    private String laboratorio;
    @Size(max = 32)
    @Column(name = "registro_sanitario")
    private String registroSanitario;
    @Column(name = "fecha_expedicion")
    @Temporal(TemporalType.DATE)
    private Date fechaExpedicion;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Column(name = "mae_estado_registro_sanitario_id")
    private Integer maeEstadoRegistroSanitarioId;
    @Size(max = 8)
    @Column(name = "mae_estado_registro_sanitario_codigo")
    private String maeEstadoRegistroSanitarioCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_registro_sanitario_valor")
    private String maeEstadoRegistroSanitarioValor;
    @Column(name = "cantidad_cum")
    private Integer cantidadCum;
    @Column(name = "fecha_activo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActivo;
    @Column(name = "fecha_inactivo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInactivo;
    @Column(name = "mae_atc_1_id")
    private Integer maeAtc1Id;
    @Size(max = 8)
    @Column(name = "mae_atc_1_codigo")
    private String maeAtc1Codigo;
    @Size(max = 128)
    @Column(name = "mae_atc_1_valor")
    private String maeAtc1Valor;
    @Column(name = "mae_atc_2_id")
    private Integer maeAtc2Id;
    @Size(max = 8)
    @Column(name = "mae_atc_2_codigo")
    private String maeAtc2Codigo;
    @Size(max = 128)
    @Column(name = "mae_atc_2_valor")
    private String maeAtc2Valor;
    @Column(name = "mae_atc_3_id")
    private Integer maeAtc3Id;
    @Size(max = 8)
    @Column(name = "mae_atc_3_codigo")
    private String maeAtc3Codigo;
    @Size(max = 128)
    @Column(name = "mae_atc_3_valor")
    private String maeAtc3Valor;
    @Size(max = 64)
    @Column(name = "norma_regulacion")
    private String normaRegulacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mce")
    private boolean mce;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monopolio_estado")
    private boolean monopolioEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estrecho_margen_terapeutico")
    private boolean estrechoMargenTerapeutico;
    @Lob
    @Size(max = 16777215)
    @Column(name = "aclaracion")
    private String aclaracion;
    @Column(name = "valor_fraccion")
    private BigDecimal valorFraccion;
    @Column(name = "valor_presentacion_comercial")
    private BigDecimal valorPresentacionComercial;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "dias_tratamiento")
    private Integer diasTratamiento;
    @Column(name = "mae_grupo_anatomico_ppal_id")
    private Integer maeGrupoAnatomicoPpalId;
    @Size(max = 8)
    @Column(name = "mae_grupo_anatomico_ppal_codigo")
    private String maeGrupoAnatomicoPpalCodigo;
    @Size(max = 128)
    @Column(name = "mae_grupo_anatomico_ppal_valor")
    private String maeGrupoAnatomicoPpalValor;
    @Column(name = "mae_grupo_terapeutico_ppal_id")
    private Integer maeGrupoTerapeuticoPpalId;
    @Size(max = 8)
    @Column(name = "mae_grupo_terapeutico_ppal_codigo")
    private String maeGrupoTerapeuticoPpalCodigo;
    @Size(max = 128)
    @Column(name = "mae_grupo_terapeutico_ppal_valor")
    private String maeGrupoTerapeuticoPpalValor;
    @Size(max = 32)
    @Column(name = "dci")
    private String dci;
    @Size(max = 1024)
    @Column(name = "descripcion_dci")
    private String descripcionDci;
    @Size(max = 32)
    @Column(name = "proveniente_invima")
    private String provenienteInvima;
    @Size(max = 32)
    @Column(name = "agrupador_condicion_pbs")
    private String agrupadorCondicionPbs;
    @Column(name = "valor_comercial")
    private BigDecimal valorComercial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lista_unirs")
    private boolean listaUnirs;
    @Size(max = 16)
    @Column(name = "codigo_suficiencia_upc")
    private String codigoSuficienciaUpc;
    @Size(max = 256)
    @Column(name = "cantidad_concentracion_suf_upc")
    private String cantidadConcentracionSufUpc;
    @Size(max = 16)
    @Column(name = "unidad_concentracion_suf_upc")
    private String unidadConcentracionSufUpc;
    @Size(max = 16)
    @Column(name = "unidad_medida_suficiencia")
    private String unidadMedidaSuficiencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "no_pbs_menor_valor")
    private boolean noPbsMenorValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "muestra_medica")
    private boolean muestraMedica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 16)
    @Column(name = "forma_farmaceutica")
    private String formaFarmaceutica;
    @Column(name = "id_siifa")
    private Integer idSiifa;
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
    @Size(max = 416)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maMedicamentosId", fetch = FetchType.LAZY)
    private List<MaMedicamentoAdministraciones> maMedicamentoAdministracionesList;
    @OneToMany(mappedBy = "maMedicamentosId", fetch = FetchType.LAZY)
    private List<MaPaqueteTecnologias> maPaqueteTecnologiasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maMedicamentosId", fetch = FetchType.LAZY)
    private List<MaDiagnosticoMedicamentos> maDiagnosticoMedicamentosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maMedicamentosId", fetch = FetchType.LAZY)
    private List<MaMedicamentosHistoricos> maMedicamentosHistoricosList;
    @OneToMany(mappedBy = "maMedicamentosId", fetch = FetchType.LAZY)
    private List<MaPaquetes> maPaquetesList;
    @JoinColumn(name = "ma_agrupadores_medicamento_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaAgrupadoresMedicamento maAgrupadoresMedicamentoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maMedicamentosId", fetch = FetchType.LAZY)
    private List<MaMedicamentoCondicionados> maMedicamentoCondicionadosList;

    public MaMedicamentos() {
    }

    public MaMedicamentos(Integer id) {
        this.id = id;
    }

    public MaMedicamentos(Integer id, boolean esAltoCosto, boolean esCapitado, boolean aplicaSubsidiado, boolean aplicaContributivo, boolean esRegulado, boolean mce, boolean monopolioEstado, boolean estrechoMargenTerapeutico, boolean listaUnirs, boolean noPbsMenorValor, boolean muestraMedica, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.esAltoCosto = esAltoCosto;
        this.esCapitado = esCapitado;
        this.aplicaSubsidiado = aplicaSubsidiado;
        this.aplicaContributivo = aplicaContributivo;
        this.esRegulado = esRegulado;
        this.mce = mce;
        this.monopolioEstado = monopolioEstado;
        this.estrechoMargenTerapeutico = estrechoMargenTerapeutico;
        this.listaUnirs = listaUnirs;
        this.noPbsMenorValor = noPbsMenorValor;
        this.muestraMedica = muestraMedica;
        this.activo = activo;
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

    public String getCum() {
        return cum;
    }

    public void setCum(String cum) {
        this.cum = cum;
    }

    public String getDescripcionInvima() {
        return descripcionInvima;
    }

    public void setDescripcionInvima(String descripcionInvima) {
        this.descripcionInvima = descripcionInvima;
    }

    public String getDescripcionEstandarizada() {
        return descripcionEstandarizada;
    }

    public void setDescripcionEstandarizada(String descripcionEstandarizada) {
        this.descripcionEstandarizada = descripcionEstandarizada;
    }

    public String getDescripcionLargaEstandarizada() {
        return descripcionLargaEstandarizada;
    }

    public void setDescripcionLargaEstandarizada(String descripcionLargaEstandarizada) {
        this.descripcionLargaEstandarizada = descripcionLargaEstandarizada;
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

    public Integer getMaeConcentracionId() {
        return maeConcentracionId;
    }

    public void setMaeConcentracionId(Integer maeConcentracionId) {
        this.maeConcentracionId = maeConcentracionId;
    }

    public String getMaeConcentracionCodigo() {
        return maeConcentracionCodigo;
    }

    public void setMaeConcentracionCodigo(String maeConcentracionCodigo) {
        this.maeConcentracionCodigo = maeConcentracionCodigo;
    }

    public String getMaeConcentracionValor() {
        return maeConcentracionValor;
    }

    public void setMaeConcentracionValor(String maeConcentracionValor) {
        this.maeConcentracionValor = maeConcentracionValor;
    }

    public Integer getMaePrincipioActivoId() {
        return maePrincipioActivoId;
    }

    public void setMaePrincipioActivoId(Integer maePrincipioActivoId) {
        this.maePrincipioActivoId = maePrincipioActivoId;
    }

    public String getMaePrincipioActivoCodigo() {
        return maePrincipioActivoCodigo;
    }

    public void setMaePrincipioActivoCodigo(String maePrincipioActivoCodigo) {
        this.maePrincipioActivoCodigo = maePrincipioActivoCodigo;
    }

    public String getMaePrincipioActivoValor() {
        return maePrincipioActivoValor;
    }

    public void setMaePrincipioActivoValor(String maePrincipioActivoValor) {
        this.maePrincipioActivoValor = maePrincipioActivoValor;
    }

    public Integer getMaeFormaFarmaceuticaId() {
        return maeFormaFarmaceuticaId;
    }

    public void setMaeFormaFarmaceuticaId(Integer maeFormaFarmaceuticaId) {
        this.maeFormaFarmaceuticaId = maeFormaFarmaceuticaId;
    }

    public String getMaeFormaFarmaceuticaCodigo() {
        return maeFormaFarmaceuticaCodigo;
    }

    public void setMaeFormaFarmaceuticaCodigo(String maeFormaFarmaceuticaCodigo) {
        this.maeFormaFarmaceuticaCodigo = maeFormaFarmaceuticaCodigo;
    }

    public String getMaeFormaFarmaceuticaValor() {
        return maeFormaFarmaceuticaValor;
    }

    public void setMaeFormaFarmaceuticaValor(String maeFormaFarmaceuticaValor) {
        this.maeFormaFarmaceuticaValor = maeFormaFarmaceuticaValor;
    }

    public Integer getMaeTipoPpmId() {
        return maeTipoPpmId;
    }

    public void setMaeTipoPpmId(Integer maeTipoPpmId) {
        this.maeTipoPpmId = maeTipoPpmId;
    }

    public String getMaeTipoPpmCodigo() {
        return maeTipoPpmCodigo;
    }

    public void setMaeTipoPpmCodigo(String maeTipoPpmCodigo) {
        this.maeTipoPpmCodigo = maeTipoPpmCodigo;
    }

    public String getMaeTipoPpmValor() {
        return maeTipoPpmValor;
    }

    public void setMaeTipoPpmValor(String maeTipoPpmValor) {
        this.maeTipoPpmValor = maeTipoPpmValor;
    }

    public boolean getEsAltoCosto() {
        return esAltoCosto;
    }

    public void setEsAltoCosto(boolean esAltoCosto) {
        this.esAltoCosto = esAltoCosto;
    }

    public boolean getEsCapitado() {
        return esCapitado;
    }

    public void setEsCapitado(boolean esCapitado) {
        this.esCapitado = esCapitado;
    }

    public boolean getAplicaSubsidiado() {
        return aplicaSubsidiado;
    }

    public void setAplicaSubsidiado(boolean aplicaSubsidiado) {
        this.aplicaSubsidiado = aplicaSubsidiado;
    }

    public boolean getAplicaContributivo() {
        return aplicaContributivo;
    }

    public void setAplicaContributivo(boolean aplicaContributivo) {
        this.aplicaContributivo = aplicaContributivo;
    }

    public Integer getSexoAplica() {
        return sexoAplica;
    }

    public void setSexoAplica(Integer sexoAplica) {
        this.sexoAplica = sexoAplica;
    }

    public String getCodigoIum() {
        return codigoIum;
    }

    public void setCodigoIum(String codigoIum) {
        this.codigoIum = codigoIum;
    }

    public Integer getEdadDesde() {
        return edadDesde;
    }

    public void setEdadDesde(Integer edadDesde) {
        this.edadDesde = edadDesde;
    }

    public Integer getEdadHasta() {
        return edadHasta;
    }

    public void setEdadHasta(Integer edadHasta) {
        this.edadHasta = edadHasta;
    }

    public boolean getEsRegulado() {
        return esRegulado;
    }

    public void setEsRegulado(boolean esRegulado) {
        this.esRegulado = esRegulado;
    }

    public BigDecimal getValorMaximoRegulado() {
        return valorMaximoRegulado;
    }

    public void setValorMaximoRegulado(BigDecimal valorMaximoRegulado) {
        this.valorMaximoRegulado = valorMaximoRegulado;
    }

    public BigDecimal getValorReferenteMinimo() {
        return valorReferenteMinimo;
    }

    public void setValorReferenteMinimo(BigDecimal valorReferenteMinimo) {
        this.valorReferenteMinimo = valorReferenteMinimo;
    }

    public BigDecimal getValorReferete() {
        return valorReferete;
    }

    public void setValorReferete(BigDecimal valorReferete) {
        this.valorReferete = valorReferete;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getMaeEstadoRegistroSanitarioId() {
        return maeEstadoRegistroSanitarioId;
    }

    public void setMaeEstadoRegistroSanitarioId(Integer maeEstadoRegistroSanitarioId) {
        this.maeEstadoRegistroSanitarioId = maeEstadoRegistroSanitarioId;
    }

    public String getMaeEstadoRegistroSanitarioCodigo() {
        return maeEstadoRegistroSanitarioCodigo;
    }

    public void setMaeEstadoRegistroSanitarioCodigo(String maeEstadoRegistroSanitarioCodigo) {
        this.maeEstadoRegistroSanitarioCodigo = maeEstadoRegistroSanitarioCodigo;
    }

    public String getMaeEstadoRegistroSanitarioValor() {
        return maeEstadoRegistroSanitarioValor;
    }

    public void setMaeEstadoRegistroSanitarioValor(String maeEstadoRegistroSanitarioValor) {
        this.maeEstadoRegistroSanitarioValor = maeEstadoRegistroSanitarioValor;
    }

    public Integer getCantidadCum() {
        return cantidadCum;
    }

    public void setCantidadCum(Integer cantidadCum) {
        this.cantidadCum = cantidadCum;
    }

    public Date getFechaActivo() {
        return fechaActivo;
    }

    public void setFechaActivo(Date fechaActivo) {
        this.fechaActivo = fechaActivo;
    }

    public Date getFechaInactivo() {
        return fechaInactivo;
    }

    public void setFechaInactivo(Date fechaInactivo) {
        this.fechaInactivo = fechaInactivo;
    }

    public Integer getMaeAtc1Id() {
        return maeAtc1Id;
    }

    public void setMaeAtc1Id(Integer maeAtc1Id) {
        this.maeAtc1Id = maeAtc1Id;
    }

    public String getMaeAtc1Codigo() {
        return maeAtc1Codigo;
    }

    public void setMaeAtc1Codigo(String maeAtc1Codigo) {
        this.maeAtc1Codigo = maeAtc1Codigo;
    }

    public String getMaeAtc1Valor() {
        return maeAtc1Valor;
    }

    public void setMaeAtc1Valor(String maeAtc1Valor) {
        this.maeAtc1Valor = maeAtc1Valor;
    }

    public Integer getMaeAtc2Id() {
        return maeAtc2Id;
    }

    public void setMaeAtc2Id(Integer maeAtc2Id) {
        this.maeAtc2Id = maeAtc2Id;
    }

    public String getMaeAtc2Codigo() {
        return maeAtc2Codigo;
    }

    public void setMaeAtc2Codigo(String maeAtc2Codigo) {
        this.maeAtc2Codigo = maeAtc2Codigo;
    }

    public String getMaeAtc2Valor() {
        return maeAtc2Valor;
    }

    public void setMaeAtc2Valor(String maeAtc2Valor) {
        this.maeAtc2Valor = maeAtc2Valor;
    }

    public Integer getMaeAtc3Id() {
        return maeAtc3Id;
    }

    public void setMaeAtc3Id(Integer maeAtc3Id) {
        this.maeAtc3Id = maeAtc3Id;
    }

    public String getMaeAtc3Codigo() {
        return maeAtc3Codigo;
    }

    public void setMaeAtc3Codigo(String maeAtc3Codigo) {
        this.maeAtc3Codigo = maeAtc3Codigo;
    }

    public String getMaeAtc3Valor() {
        return maeAtc3Valor;
    }

    public void setMaeAtc3Valor(String maeAtc3Valor) {
        this.maeAtc3Valor = maeAtc3Valor;
    }

    public String getNormaRegulacion() {
        return normaRegulacion;
    }

    public void setNormaRegulacion(String normaRegulacion) {
        this.normaRegulacion = normaRegulacion;
    }

    public boolean getMce() {
        return mce;
    }

    public void setMce(boolean mce) {
        this.mce = mce;
    }

    public boolean getMonopolioEstado() {
        return monopolioEstado;
    }

    public void setMonopolioEstado(boolean monopolioEstado) {
        this.monopolioEstado = monopolioEstado;
    }

    public boolean getEstrechoMargenTerapeutico() {
        return estrechoMargenTerapeutico;
    }

    public void setEstrechoMargenTerapeutico(boolean estrechoMargenTerapeutico) {
        this.estrechoMargenTerapeutico = estrechoMargenTerapeutico;
    }

    public String getAclaracion() {
        return aclaracion;
    }

    public void setAclaracion(String aclaracion) {
        this.aclaracion = aclaracion;
    }

    public BigDecimal getValorFraccion() {
        return valorFraccion;
    }

    public void setValorFraccion(BigDecimal valorFraccion) {
        this.valorFraccion = valorFraccion;
    }

    public BigDecimal getValorPresentacionComercial() {
        return valorPresentacionComercial;
    }

    public void setValorPresentacionComercial(BigDecimal valorPresentacionComercial) {
        this.valorPresentacionComercial = valorPresentacionComercial;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getDiasTratamiento() {
        return diasTratamiento;
    }

    public void setDiasTratamiento(Integer diasTratamiento) {
        this.diasTratamiento = diasTratamiento;
    }

    public Integer getMaeGrupoAnatomicoPpalId() {
        return maeGrupoAnatomicoPpalId;
    }

    public void setMaeGrupoAnatomicoPpalId(Integer maeGrupoAnatomicoPpalId) {
        this.maeGrupoAnatomicoPpalId = maeGrupoAnatomicoPpalId;
    }

    public String getMaeGrupoAnatomicoPpalCodigo() {
        return maeGrupoAnatomicoPpalCodigo;
    }

    public void setMaeGrupoAnatomicoPpalCodigo(String maeGrupoAnatomicoPpalCodigo) {
        this.maeGrupoAnatomicoPpalCodigo = maeGrupoAnatomicoPpalCodigo;
    }

    public String getMaeGrupoAnatomicoPpalValor() {
        return maeGrupoAnatomicoPpalValor;
    }

    public void setMaeGrupoAnatomicoPpalValor(String maeGrupoAnatomicoPpalValor) {
        this.maeGrupoAnatomicoPpalValor = maeGrupoAnatomicoPpalValor;
    }

    public Integer getMaeGrupoTerapeuticoPpalId() {
        return maeGrupoTerapeuticoPpalId;
    }

    public void setMaeGrupoTerapeuticoPpalId(Integer maeGrupoTerapeuticoPpalId) {
        this.maeGrupoTerapeuticoPpalId = maeGrupoTerapeuticoPpalId;
    }

    public String getMaeGrupoTerapeuticoPpalCodigo() {
        return maeGrupoTerapeuticoPpalCodigo;
    }

    public void setMaeGrupoTerapeuticoPpalCodigo(String maeGrupoTerapeuticoPpalCodigo) {
        this.maeGrupoTerapeuticoPpalCodigo = maeGrupoTerapeuticoPpalCodigo;
    }

    public String getMaeGrupoTerapeuticoPpalValor() {
        return maeGrupoTerapeuticoPpalValor;
    }

    public void setMaeGrupoTerapeuticoPpalValor(String maeGrupoTerapeuticoPpalValor) {
        this.maeGrupoTerapeuticoPpalValor = maeGrupoTerapeuticoPpalValor;
    }

    public String getDci() {
        return dci;
    }

    public void setDci(String dci) {
        this.dci = dci;
    }

    public String getDescripcionDci() {
        return descripcionDci;
    }

    public void setDescripcionDci(String descripcionDci) {
        this.descripcionDci = descripcionDci;
    }

    public String getProvenienteInvima() {
        return provenienteInvima;
    }

    public void setProvenienteInvima(String provenienteInvima) {
        this.provenienteInvima = provenienteInvima;
    }

    public String getAgrupadorCondicionPbs() {
        return agrupadorCondicionPbs;
    }

    public void setAgrupadorCondicionPbs(String agrupadorCondicionPbs) {
        this.agrupadorCondicionPbs = agrupadorCondicionPbs;
    }

    public BigDecimal getValorComercial() {
        return valorComercial;
    }

    public void setValorComercial(BigDecimal valorComercial) {
        this.valorComercial = valorComercial;
    }

    public boolean getListaUnirs() {
        return listaUnirs;
    }

    public void setListaUnirs(boolean listaUnirs) {
        this.listaUnirs = listaUnirs;
    }

    public String getCodigoSuficienciaUpc() {
        return codigoSuficienciaUpc;
    }

    public void setCodigoSuficienciaUpc(String codigoSuficienciaUpc) {
        this.codigoSuficienciaUpc = codigoSuficienciaUpc;
    }

    public String getCantidadConcentracionSufUpc() {
        return cantidadConcentracionSufUpc;
    }

    public void setCantidadConcentracionSufUpc(String cantidadConcentracionSufUpc) {
        this.cantidadConcentracionSufUpc = cantidadConcentracionSufUpc;
    }

    public String getUnidadConcentracionSufUpc() {
        return unidadConcentracionSufUpc;
    }

    public void setUnidadConcentracionSufUpc(String unidadConcentracionSufUpc) {
        this.unidadConcentracionSufUpc = unidadConcentracionSufUpc;
    }

    public String getUnidadMedidaSuficiencia() {
        return unidadMedidaSuficiencia;
    }

    public void setUnidadMedidaSuficiencia(String unidadMedidaSuficiencia) {
        this.unidadMedidaSuficiencia = unidadMedidaSuficiencia;
    }

    public boolean getNoPbsMenorValor() {
        return noPbsMenorValor;
    }

    public void setNoPbsMenorValor(boolean noPbsMenorValor) {
        this.noPbsMenorValor = noPbsMenorValor;
    }

    public boolean getMuestraMedica() {
        return muestraMedica;
    }

    public void setMuestraMedica(boolean muestraMedica) {
        this.muestraMedica = muestraMedica;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
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

    @XmlTransient
    public List<MaMedicamentoAdministraciones> getMaMedicamentoAdministracionesList() {
        return maMedicamentoAdministracionesList;
    }

    public void setMaMedicamentoAdministracionesList(List<MaMedicamentoAdministraciones> maMedicamentoAdministracionesList) {
        this.maMedicamentoAdministracionesList = maMedicamentoAdministracionesList;
    }

    @XmlTransient
    public List<MaPaqueteTecnologias> getMaPaqueteTecnologiasList() {
        return maPaqueteTecnologiasList;
    }

    public void setMaPaqueteTecnologiasList(List<MaPaqueteTecnologias> maPaqueteTecnologiasList) {
        this.maPaqueteTecnologiasList = maPaqueteTecnologiasList;
    }

    @XmlTransient
    public List<MaDiagnosticoMedicamentos> getMaDiagnosticoMedicamentosList() {
        return maDiagnosticoMedicamentosList;
    }

    public void setMaDiagnosticoMedicamentosList(List<MaDiagnosticoMedicamentos> maDiagnosticoMedicamentosList) {
        this.maDiagnosticoMedicamentosList = maDiagnosticoMedicamentosList;
    }

    @XmlTransient
    public List<MaMedicamentosHistoricos> getMaMedicamentosHistoricosList() {
        return maMedicamentosHistoricosList;
    }

    public void setMaMedicamentosHistoricosList(List<MaMedicamentosHistoricos> maMedicamentosHistoricosList) {
        this.maMedicamentosHistoricosList = maMedicamentosHistoricosList;
    }

    @XmlTransient
    public List<MaPaquetes> getMaPaquetesList() {
        return maPaquetesList;
    }

    public void setMaPaquetesList(List<MaPaquetes> maPaquetesList) {
        this.maPaquetesList = maPaquetesList;
    }

    public MaAgrupadoresMedicamento getMaAgrupadoresMedicamentoId() {
        return maAgrupadoresMedicamentoId;
    }

    public void setMaAgrupadoresMedicamentoId(MaAgrupadoresMedicamento maAgrupadoresMedicamentoId) {
        this.maAgrupadoresMedicamentoId = maAgrupadoresMedicamentoId;
    }

    @XmlTransient
    public List<MaMedicamentoCondicionados> getMaMedicamentoCondicionadosList() {
        return maMedicamentoCondicionadosList;
    }

    public void setMaMedicamentoCondicionadosList(List<MaMedicamentoCondicionados> maMedicamentoCondicionadosList) {
        this.maMedicamentoCondicionadosList = maMedicamentoCondicionadosList;
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
        if (!(object instanceof MaMedicamentos)) {
            return false;
        }
        MaMedicamentos other = (MaMedicamentos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MaMedicamentos[ id=" + id + " ]";
    }
    
}
