package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmSincronizacionPaquetes.class)
public abstract class CmSincronizacionPaquetes_ {

	public static volatile SingularAttribute<CmSincronizacionPaquetes, Integer> estadoTransacion;
	public static volatile SingularAttribute<CmSincronizacionPaquetes, Date> fechaHoraRespuesta;
	public static volatile SingularAttribute<CmSincronizacionPaquetes, Integer> id;
	public static volatile SingularAttribute<CmSincronizacionPaquetes, byte[]> jsonRespuesta;
	public static volatile SingularAttribute<CmSincronizacionPaquetes, byte[]> jsonEnvio;
	public static volatile SingularAttribute<CmSincronizacionPaquetes, Date> fechaHoraEnvio;
	public static volatile SingularAttribute<CmSincronizacionPaquetes, Integer> codigoRetorno;
	public static volatile SingularAttribute<CmSincronizacionPaquetes, CmSincronizaciones> cmSincronizacionesId;
	public static volatile SingularAttribute<CmSincronizacionPaquetes, Integer> codigoRespuesta;
	public static volatile SingularAttribute<CmSincronizacionPaquetes, String> mensajeRespuesta;

	public static final String ESTADO_TRANSACION = "estadoTransacion";
	public static final String FECHA_HORA_RESPUESTA = "fechaHoraRespuesta";
	public static final String ID = "id";
	public static final String JSON_RESPUESTA = "jsonRespuesta";
	public static final String JSON_ENVIO = "jsonEnvio";
	public static final String FECHA_HORA_ENVIO = "fechaHoraEnvio";
	public static final String CODIGO_RETORNO = "codigoRetorno";
	public static final String CM_SINCRONIZACIONES_ID = "cmSincronizacionesId";
	public static final String CODIGO_RESPUESTA = "codigoRespuesta";
	public static final String MENSAJE_RESPUESTA = "mensajeRespuesta";

}

