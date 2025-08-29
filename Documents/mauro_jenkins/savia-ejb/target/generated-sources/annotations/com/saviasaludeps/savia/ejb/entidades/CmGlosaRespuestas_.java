package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmGlosaRespuestas.class)
public abstract class CmGlosaRespuestas_ {

	public static volatile SingularAttribute<CmGlosaRespuestas, String> representanteIps;
	public static volatile ListAttribute<CmGlosaRespuestas, CmGlosaRespuestaDetalles> cmGlosaRespuestaDetallesList;
	public static volatile SingularAttribute<CmGlosaRespuestas, CmConciliaciones> cmConciliacionesId;
	public static volatile SingularAttribute<CmGlosaRespuestas, String> representanteEps;
	public static volatile SingularAttribute<CmGlosaRespuestas, BigDecimal> valorFacturado;
	public static volatile SingularAttribute<CmGlosaRespuestas, BigDecimal> valorPendiente;
	public static volatile SingularAttribute<CmGlosaRespuestas, BigDecimal> valorAceptadoIps;
	public static volatile SingularAttribute<CmGlosaRespuestas, BigDecimal> vaorPagadoEps;
	public static volatile SingularAttribute<CmGlosaRespuestas, CmGlosaMasiva> cmGlosaMasivaId;
	public static volatile SingularAttribute<CmGlosaRespuestas, BigDecimal> valorPagado;
	public static volatile ListAttribute<CmGlosaRespuestas, CmRadicados> cmRadicadosList;
	public static volatile ListAttribute<CmGlosaRespuestas, WsCmTransacciones> wsCmTransaccionesList;
	public static volatile SingularAttribute<CmGlosaRespuestas, BigDecimal> valorCobroDetalle;
	public static volatile SingularAttribute<CmGlosaRespuestas, String> usuarioCrea;
	public static volatile SingularAttribute<CmGlosaRespuestas, String> terminalCrea;
	public static volatile ListAttribute<CmGlosaRespuestas, CmRadicados> cmRadicadosList1;
	public static volatile SingularAttribute<CmGlosaRespuestas, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmGlosaRespuestas, Date> fechaHoraCrea;
	public static volatile ListAttribute<CmGlosaRespuestas, CmSincronizaciones> cmSincronizacionesList;
	public static volatile SingularAttribute<CmGlosaRespuestas, Integer> id;
	public static volatile SingularAttribute<CmGlosaRespuestas, Integer> tipoRespuesta;
	public static volatile SingularAttribute<CmGlosaRespuestas, Integer> estadoSincronizacion;
	public static volatile SingularAttribute<CmGlosaRespuestas, String> observacion;

	public static final String REPRESENTANTE_IPS = "representanteIps";
	public static final String CM_GLOSA_RESPUESTA_DETALLES_LIST = "cmGlosaRespuestaDetallesList";
	public static final String CM_CONCILIACIONES_ID = "cmConciliacionesId";
	public static final String REPRESENTANTE_EPS = "representanteEps";
	public static final String VALOR_FACTURADO = "valorFacturado";
	public static final String VALOR_PENDIENTE = "valorPendiente";
	public static final String VALOR_ACEPTADO_IPS = "valorAceptadoIps";
	public static final String VAOR_PAGADO_EPS = "vaorPagadoEps";
	public static final String CM_GLOSA_MASIVA_ID = "cmGlosaMasivaId";
	public static final String VALOR_PAGADO = "valorPagado";
	public static final String CM_RADICADOS_LIST = "cmRadicadosList";
	public static final String WS_CM_TRANSACCIONES_LIST = "wsCmTransaccionesList";
	public static final String VALOR_COBRO_DETALLE = "valorCobroDetalle";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_RADICADOS_LIST1 = "cmRadicadosList1";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String CM_SINCRONIZACIONES_LIST = "cmSincronizacionesList";
	public static final String ID = "id";
	public static final String TIPO_RESPUESTA = "tipoRespuesta";
	public static final String ESTADO_SINCRONIZACION = "estadoSincronizacion";
	public static final String OBSERVACION = "observacion";

}

