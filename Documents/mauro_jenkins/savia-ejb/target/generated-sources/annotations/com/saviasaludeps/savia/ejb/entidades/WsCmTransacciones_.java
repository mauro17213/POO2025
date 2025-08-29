package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WsCmTransacciones.class)
public abstract class WsCmTransacciones_ {

	public static volatile SingularAttribute<WsCmTransacciones, Short> estado;
	public static volatile ListAttribute<WsCmTransacciones, WsCmTransaccionDetalles> wsCmTransaccionDetallesList;
	public static volatile SingularAttribute<WsCmTransacciones, CmConciliaciones> cmConciliacionesId;
	public static volatile SingularAttribute<WsCmTransacciones, Short> paquetes;
	public static volatile SingularAttribute<WsCmTransacciones, Date> fechaHoraEnvio;
	public static volatile SingularAttribute<WsCmTransacciones, Short> codigoRetorno;
	public static volatile SingularAttribute<WsCmTransacciones, Short> momento;
	public static volatile SingularAttribute<WsCmTransacciones, CmAuditoriaCierres> cmAuditoriaCierresId;
	public static volatile SingularAttribute<WsCmTransacciones, CmGlosaRespuestas> cmGlosaRespuestasId;
	public static volatile SingularAttribute<WsCmTransacciones, Short> paquetesExitosos;
	public static volatile SingularAttribute<WsCmTransacciones, Integer> cmFacturasId;
	public static volatile SingularAttribute<WsCmTransacciones, CmRadicados> cmRadicadosId;
	public static volatile SingularAttribute<WsCmTransacciones, Date> fechaHoraRespuesta;
	public static volatile SingularAttribute<WsCmTransacciones, Integer> id;
	public static volatile SingularAttribute<WsCmTransacciones, Short> codigoRespuesta;
	public static volatile SingularAttribute<WsCmTransacciones, String> mensajeRespuesta;

	public static final String ESTADO = "estado";
	public static final String WS_CM_TRANSACCION_DETALLES_LIST = "wsCmTransaccionDetallesList";
	public static final String CM_CONCILIACIONES_ID = "cmConciliacionesId";
	public static final String PAQUETES = "paquetes";
	public static final String FECHA_HORA_ENVIO = "fechaHoraEnvio";
	public static final String CODIGO_RETORNO = "codigoRetorno";
	public static final String MOMENTO = "momento";
	public static final String CM_AUDITORIA_CIERRES_ID = "cmAuditoriaCierresId";
	public static final String CM_GLOSA_RESPUESTAS_ID = "cmGlosaRespuestasId";
	public static final String PAQUETES_EXITOSOS = "paquetesExitosos";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String CM_RADICADOS_ID = "cmRadicadosId";
	public static final String FECHA_HORA_RESPUESTA = "fechaHoraRespuesta";
	public static final String ID = "id";
	public static final String CODIGO_RESPUESTA = "codigoRespuesta";
	public static final String MENSAJE_RESPUESTA = "mensajeRespuesta";

}

