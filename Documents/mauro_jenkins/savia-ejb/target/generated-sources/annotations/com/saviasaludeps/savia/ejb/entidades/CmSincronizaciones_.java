package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmSincronizaciones.class)
public abstract class CmSincronizaciones_ {

	public static volatile SingularAttribute<CmSincronizaciones, Integer> estadoTransacion;
	public static volatile SingularAttribute<CmSincronizaciones, CmConciliaciones> cmConciliacionesId;
	public static volatile SingularAttribute<CmSincronizaciones, Integer> paquetes;
	public static volatile SingularAttribute<CmSincronizaciones, byte[]> jsonRespuesta;
	public static volatile SingularAttribute<CmSincronizaciones, byte[]> jsonEnvio;
	public static volatile SingularAttribute<CmSincronizaciones, Date> fechaHoraEnvio;
	public static volatile SingularAttribute<CmSincronizaciones, Integer> codigoRetorno;
	public static volatile SingularAttribute<CmSincronizaciones, Integer> momento;
	public static volatile SingularAttribute<CmSincronizaciones, CmAuditoriaCierres> cmAuditoriaCierresId;
	public static volatile SingularAttribute<CmSincronizaciones, CmGlosaRespuestas> cmGlosaRespuestasId;
	public static volatile SingularAttribute<CmSincronizaciones, Integer> paquetesExitosos;
	public static volatile ListAttribute<CmSincronizaciones, CmSincronizacionPaquetes> cmSincronizacionPaquetesList;
	public static volatile SingularAttribute<CmSincronizaciones, Integer> cmFacturasId;
	public static volatile SingularAttribute<CmSincronizaciones, CmRadicados> cmRadicadosId;
	public static volatile SingularAttribute<CmSincronizaciones, Date> fechaHoraRespuesta;
	public static volatile SingularAttribute<CmSincronizaciones, Integer> id;
	public static volatile SingularAttribute<CmSincronizaciones, Integer> codigoRespuesta;
	public static volatile SingularAttribute<CmSincronizaciones, String> mensajeRespuesta;

	public static final String ESTADO_TRANSACION = "estadoTransacion";
	public static final String CM_CONCILIACIONES_ID = "cmConciliacionesId";
	public static final String PAQUETES = "paquetes";
	public static final String JSON_RESPUESTA = "jsonRespuesta";
	public static final String JSON_ENVIO = "jsonEnvio";
	public static final String FECHA_HORA_ENVIO = "fechaHoraEnvio";
	public static final String CODIGO_RETORNO = "codigoRetorno";
	public static final String MOMENTO = "momento";
	public static final String CM_AUDITORIA_CIERRES_ID = "cmAuditoriaCierresId";
	public static final String CM_GLOSA_RESPUESTAS_ID = "cmGlosaRespuestasId";
	public static final String PAQUETES_EXITOSOS = "paquetesExitosos";
	public static final String CM_SINCRONIZACION_PAQUETES_LIST = "cmSincronizacionPaquetesList";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String CM_RADICADOS_ID = "cmRadicadosId";
	public static final String FECHA_HORA_RESPUESTA = "fechaHoraRespuesta";
	public static final String ID = "id";
	public static final String CODIGO_RESPUESTA = "codigoRespuesta";
	public static final String MENSAJE_RESPUESTA = "mensajeRespuesta";

}

