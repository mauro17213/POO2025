package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoDetalles.class)
public abstract class CntContratoDetalles_ {

	public static volatile SingularAttribute<CntContratoDetalles, Boolean> automaticoInteroperabilidad;
	public static volatile SingularAttribute<CntContratoDetalles, String> observacionIncluye;
	public static volatile SingularAttribute<CntContratoDetalles, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<CntContratoDetalles, String> maTecnologiaValor;
	public static volatile SingularAttribute<CntContratoDetalles, Integer> maServicioHabilitacionId;
	public static volatile SingularAttribute<CntContratoDetalles, String> observacionExcluye;
	public static volatile SingularAttribute<CntContratoDetalles, Date> fechaHoraInicio;
	public static volatile ListAttribute<CntContratoDetalles, AuAnexo4Items> auAnexo4ItemsList;
	public static volatile SingularAttribute<CntContratoDetalles, Boolean> preautorizacion;
	public static volatile SingularAttribute<CntContratoDetalles, String> maeAmbitoValor;
	public static volatile SingularAttribute<CntContratoDetalles, Integer> complejidad;
	public static volatile SingularAttribute<CntContratoDetalles, String> terminalModifica;
	public static volatile ListAttribute<CntContratoDetalles, CntContratoHistoricos> cntContratoHistoricosList;
	public static volatile SingularAttribute<CntContratoDetalles, String> maManualTarifarioValor;
	public static volatile SingularAttribute<CntContratoDetalles, String> usuarioCrea;
	public static volatile SingularAttribute<CntContratoDetalles, String> maeAmbitoCodigo;
	public static volatile SingularAttribute<CntContratoDetalles, String> terminalCrea;
	public static volatile SingularAttribute<CntContratoDetalles, Integer> tipoManualTarifario;
	public static volatile SingularAttribute<CntContratoDetalles, String> maManualTarifarioCodigo;
	public static volatile SingularAttribute<CntContratoDetalles, Integer> maManualTarifarioAgno;
	public static volatile SingularAttribute<CntContratoDetalles, String> maServicioHabilitacionValor;
	public static volatile SingularAttribute<CntContratoDetalles, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoDetalles, BigDecimal> porcentajeVariacion;
	public static volatile SingularAttribute<CntContratoDetalles, Integer> id;
	public static volatile SingularAttribute<CntContratoDetalles, Boolean> automaticoManual;
	public static volatile SingularAttribute<CntContratoDetalles, Date> fechaHoraFin;
	public static volatile SingularAttribute<CntContratoDetalles, BigDecimal> valorManual;
	public static volatile SingularAttribute<CntContratoDetalles, Integer> maManualTarifarioId;
	public static volatile SingularAttribute<CntContratoDetalles, BigDecimal> valorContratado;
	public static volatile SingularAttribute<CntContratoDetalles, CntPrestadorSedes> cntPrestadorSedesInterdependenciaId;
	public static volatile SingularAttribute<CntContratoDetalles, BigDecimal> valorMaximoRegulado;
	public static volatile SingularAttribute<CntContratoDetalles, Integer> maTecnologiaId;
	public static volatile ListAttribute<CntContratoDetalles, CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList;
	public static volatile SingularAttribute<CntContratoDetalles, String> maServicioHabilitacionCodigo;
	public static volatile SingularAttribute<CntContratoDetalles, Integer> tipoTecnologia;
	public static volatile SingularAttribute<CntContratoDetalles, CntContratoSedes> cntContratoSedesId;
	public static volatile SingularAttribute<CntContratoDetalles, Boolean> interdependencia;
	public static volatile SingularAttribute<CntContratoDetalles, Integer> maeAmbitoId;
	public static volatile SingularAttribute<CntContratoDetalles, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntContratoDetalles, Boolean> automaticoMasivo;
	public static volatile SingularAttribute<CntContratoDetalles, String> usuarioModifica;
	public static volatile SingularAttribute<CntContratoDetalles, Boolean> activo;
	public static volatile SingularAttribute<CntContratoDetalles, CntContratos> cntContratosId;

	public static final String AUTOMATICO_INTEROPERABILIDAD = "automaticoInteroperabilidad";
	public static final String OBSERVACION_INCLUYE = "observacionIncluye";
	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String MA_SERVICIO_HABILITACION_ID = "maServicioHabilitacionId";
	public static final String OBSERVACION_EXCLUYE = "observacionExcluye";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String AU_ANEXO4_ITEMS_LIST = "auAnexo4ItemsList";
	public static final String PREAUTORIZACION = "preautorizacion";
	public static final String MAE_AMBITO_VALOR = "maeAmbitoValor";
	public static final String COMPLEJIDAD = "complejidad";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNT_CONTRATO_HISTORICOS_LIST = "cntContratoHistoricosList";
	public static final String MA_MANUAL_TARIFARIO_VALOR = "maManualTarifarioValor";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_AMBITO_CODIGO = "maeAmbitoCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String TIPO_MANUAL_TARIFARIO = "tipoManualTarifario";
	public static final String MA_MANUAL_TARIFARIO_CODIGO = "maManualTarifarioCodigo";
	public static final String MA_MANUAL_TARIFARIO_AGNO = "maManualTarifarioAgno";
	public static final String MA_SERVICIO_HABILITACION_VALOR = "maServicioHabilitacionValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String PORCENTAJE_VARIACION = "porcentajeVariacion";
	public static final String ID = "id";
	public static final String AUTOMATICO_MANUAL = "automaticoManual";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String VALOR_MANUAL = "valorManual";
	public static final String MA_MANUAL_TARIFARIO_ID = "maManualTarifarioId";
	public static final String VALOR_CONTRATADO = "valorContratado";
	public static final String CNT_PRESTADOR_SEDES_INTERDEPENDENCIA_ID = "cntPrestadorSedesInterdependenciaId";
	public static final String VALOR_MAXIMO_REGULADO = "valorMaximoRegulado";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String CNT_CONTRATO_HISTORICO_VALIDACIONES_LIST = "cntContratoHistoricoValidacionesList";
	public static final String MA_SERVICIO_HABILITACION_CODIGO = "maServicioHabilitacionCodigo";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String CNT_CONTRATO_SEDES_ID = "cntContratoSedesId";
	public static final String INTERDEPENDENCIA = "interdependencia";
	public static final String MAE_AMBITO_ID = "maeAmbitoId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String AUTOMATICO_MASIVO = "automaticoMasivo";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

