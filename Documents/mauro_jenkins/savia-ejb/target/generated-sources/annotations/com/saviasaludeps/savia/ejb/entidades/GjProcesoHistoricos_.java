package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GjProcesoHistoricos.class)
public abstract class GjProcesoHistoricos_ {

	public static volatile SingularAttribute<GjProcesoHistoricos, Short> estado;
	public static volatile SingularAttribute<GjProcesoHistoricos, Integer> maeMedicaCautelarId;
	public static volatile SingularAttribute<GjProcesoHistoricos, GjProcesos> gjProcesosId;
	public static volatile SingularAttribute<GjProcesoHistoricos, Short> riesgoClasificacion;
	public static volatile SingularAttribute<GjProcesoHistoricos, Short> probabilidad;
	public static volatile SingularAttribute<GjProcesoHistoricos, String> maeMedicaCautelarCodigo;
	public static volatile SingularAttribute<GjProcesoHistoricos, String> maeActuacionTerminacionValor;
	public static volatile SingularAttribute<GjProcesoHistoricos, BigDecimal> valorSentenciaEjecutoria;
	public static volatile SingularAttribute<GjProcesoHistoricos, String> usuariosCrea;
	public static volatile SingularAttribute<GjProcesoHistoricos, String> maeMedicaCautelarValor;
	public static volatile SingularAttribute<GjProcesoHistoricos, Integer> maeActuacionTerminacionId;
	public static volatile SingularAttribute<GjProcesoHistoricos, String> terminalModifica;
	public static volatile ListAttribute<GjProcesoHistoricos, GjProcesoAdjuntos> gjProcesoAdjuntosList;
	public static volatile SingularAttribute<GjProcesoHistoricos, Date> fechaActuacion;
	public static volatile SingularAttribute<GjProcesoHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<GjProcesoHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GjProcesoHistoricos, Integer> id;
	public static volatile SingularAttribute<GjProcesoHistoricos, Integer> maeInstanciaId;
	public static volatile SingularAttribute<GjProcesoHistoricos, BigDecimal> montoMedida;
	public static volatile SingularAttribute<GjProcesoHistoricos, byte[]> actuacion;
	public static volatile SingularAttribute<GjProcesoHistoricos, BigDecimal> valorAcuerdoTransaccion;
	public static volatile SingularAttribute<GjProcesoHistoricos, Date> fechaContestacion;
	public static volatile SingularAttribute<GjProcesoHistoricos, Short> sentidoSentencia;
	public static volatile SingularAttribute<GjProcesoHistoricos, Short> claseProvision;
	public static volatile SingularAttribute<GjProcesoHistoricos, Short> estadoProceso;
	public static volatile SingularAttribute<GjProcesoHistoricos, String> maeInstanciaCodigo;
	public static volatile SingularAttribute<GjProcesoHistoricos, Boolean> llamamientoGarantia;
	public static volatile SingularAttribute<GjProcesoHistoricos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GjProcesoHistoricos, String> maeInstanciaValor;
	public static volatile ListAttribute<GjProcesoHistoricos, GjProcesoGarantias> gjProcesoGarantiasList;
	public static volatile SingularAttribute<GjProcesoHistoricos, Date> fechaTerminacion;
	public static volatile SingularAttribute<GjProcesoHistoricos, Short> estadoCumplimientoCondena;
	public static volatile SingularAttribute<GjProcesoHistoricos, String> maeActuacionTerminacionCodigo;
	public static volatile SingularAttribute<GjProcesoHistoricos, String> usuarioModifica;

	public static final String ESTADO = "estado";
	public static final String MAE_MEDICA_CAUTELAR_ID = "maeMedicaCautelarId";
	public static final String GJ_PROCESOS_ID = "gjProcesosId";
	public static final String RIESGO_CLASIFICACION = "riesgoClasificacion";
	public static final String PROBABILIDAD = "probabilidad";
	public static final String MAE_MEDICA_CAUTELAR_CODIGO = "maeMedicaCautelarCodigo";
	public static final String MAE_ACTUACION_TERMINACION_VALOR = "maeActuacionTerminacionValor";
	public static final String VALOR_SENTENCIA_EJECUTORIA = "valorSentenciaEjecutoria";
	public static final String USUARIOS_CREA = "usuariosCrea";
	public static final String MAE_MEDICA_CAUTELAR_VALOR = "maeMedicaCautelarValor";
	public static final String MAE_ACTUACION_TERMINACION_ID = "maeActuacionTerminacionId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String GJ_PROCESO_ADJUNTOS_LIST = "gjProcesoAdjuntosList";
	public static final String FECHA_ACTUACION = "fechaActuacion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_INSTANCIA_ID = "maeInstanciaId";
	public static final String MONTO_MEDIDA = "montoMedida";
	public static final String ACTUACION = "actuacion";
	public static final String VALOR_ACUERDO_TRANSACCION = "valorAcuerdoTransaccion";
	public static final String FECHA_CONTESTACION = "fechaContestacion";
	public static final String SENTIDO_SENTENCIA = "sentidoSentencia";
	public static final String CLASE_PROVISION = "claseProvision";
	public static final String ESTADO_PROCESO = "estadoProceso";
	public static final String MAE_INSTANCIA_CODIGO = "maeInstanciaCodigo";
	public static final String LLAMAMIENTO_GARANTIA = "llamamientoGarantia";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String MAE_INSTANCIA_VALOR = "maeInstanciaValor";
	public static final String GJ_PROCESO_GARANTIAS_LIST = "gjProcesoGarantiasList";
	public static final String FECHA_TERMINACION = "fechaTerminacion";
	public static final String ESTADO_CUMPLIMIENTO_CONDENA = "estadoCumplimientoCondena";
	public static final String MAE_ACTUACION_TERMINACION_CODIGO = "maeActuacionTerminacionCodigo";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

