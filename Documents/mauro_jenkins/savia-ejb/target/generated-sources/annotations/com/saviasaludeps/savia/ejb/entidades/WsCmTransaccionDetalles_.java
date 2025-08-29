package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WsCmTransaccionDetalles.class)
public abstract class WsCmTransaccionDetalles_ {

	public static volatile SingularAttribute<WsCmTransaccionDetalles, Short> estado;
	public static volatile SingularAttribute<WsCmTransaccionDetalles, Date> fechaHoraRespuesta;
	public static volatile SingularAttribute<WsCmTransaccionDetalles, WsCmTransacciones> wsCmTransaccionesId;
	public static volatile SingularAttribute<WsCmTransaccionDetalles, Integer> id;
	public static volatile SingularAttribute<WsCmTransaccionDetalles, byte[]> jsonRespuesta;
	public static volatile SingularAttribute<WsCmTransaccionDetalles, byte[]> jsonEnvio;
	public static volatile SingularAttribute<WsCmTransaccionDetalles, Date> fechaHoraEnvio;
	public static volatile SingularAttribute<WsCmTransaccionDetalles, Short> codigoRetorno;
	public static volatile SingularAttribute<WsCmTransaccionDetalles, Short> codigoRespuesta;
	public static volatile SingularAttribute<WsCmTransaccionDetalles, String> mensajeRespuesta;

	public static final String ESTADO = "estado";
	public static final String FECHA_HORA_RESPUESTA = "fechaHoraRespuesta";
	public static final String WS_CM_TRANSACCIONES_ID = "wsCmTransaccionesId";
	public static final String ID = "id";
	public static final String JSON_RESPUESTA = "jsonRespuesta";
	public static final String JSON_ENVIO = "jsonEnvio";
	public static final String FECHA_HORA_ENVIO = "fechaHoraEnvio";
	public static final String CODIGO_RETORNO = "codigoRetorno";
	public static final String CODIGO_RESPUESTA = "codigoRespuesta";
	public static final String MENSAJE_RESPUESTA = "mensajeRespuesta";

}

