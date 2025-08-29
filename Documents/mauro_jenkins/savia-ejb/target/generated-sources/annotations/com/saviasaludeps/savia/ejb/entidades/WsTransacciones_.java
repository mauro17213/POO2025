package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WsTransacciones.class)
public abstract class WsTransacciones_ {

	public static volatile SingularAttribute<WsTransacciones, Integer> estado;
	public static volatile SingularAttribute<WsTransacciones, WsConexiones> wsConexionesId;
	public static volatile SingularAttribute<WsTransacciones, byte[]> jsonSolicitud;
	public static volatile SingularAttribute<WsTransacciones, Date> fechaHoraRespuesta;
	public static volatile SingularAttribute<WsTransacciones, String> usuario;
	public static volatile SingularAttribute<WsTransacciones, Integer> id;
	public static volatile SingularAttribute<WsTransacciones, Date> fechaHoraSolicitud;
	public static volatile SingularAttribute<WsTransacciones, byte[]> jsonRespuesta;
	public static volatile SingularAttribute<WsTransacciones, String> ipSolicitud;
	public static volatile SingularAttribute<WsTransacciones, Boolean> contingencia;
	public static volatile SingularAttribute<WsTransacciones, WsServicios> wsServiciosId;
	public static volatile SingularAttribute<WsTransacciones, String> token;

	public static final String ESTADO = "estado";
	public static final String WS_CONEXIONES_ID = "wsConexionesId";
	public static final String JSON_SOLICITUD = "jsonSolicitud";
	public static final String FECHA_HORA_RESPUESTA = "fechaHoraRespuesta";
	public static final String USUARIO = "usuario";
	public static final String ID = "id";
	public static final String FECHA_HORA_SOLICITUD = "fechaHoraSolicitud";
	public static final String JSON_RESPUESTA = "jsonRespuesta";
	public static final String IP_SOLICITUD = "ipSolicitud";
	public static final String CONTINGENCIA = "contingencia";
	public static final String WS_SERVICIOS_ID = "wsServiciosId";
	public static final String TOKEN = "token";

}

