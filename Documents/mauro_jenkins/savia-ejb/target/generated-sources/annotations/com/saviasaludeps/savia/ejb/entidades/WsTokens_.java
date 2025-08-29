package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WsTokens.class)
public abstract class WsTokens_ {

	public static volatile SingularAttribute<WsTokens, Integer> tiempo;
	public static volatile SingularAttribute<WsTokens, String> ipSolicita;
	public static volatile SingularAttribute<WsTokens, WsConexiones> wsConexionesId;
	public static volatile SingularAttribute<WsTokens, Integer> transacciones;
	public static volatile SingularAttribute<WsTokens, Date> fechaHoraInicio;
	public static volatile SingularAttribute<WsTokens, Integer> id;
	public static volatile SingularAttribute<WsTokens, Date> fechaHoraFin;
	public static volatile SingularAttribute<WsTokens, String> token;

	public static final String TIEMPO = "tiempo";
	public static final String IP_SOLICITA = "ipSolicita";
	public static final String WS_CONEXIONES_ID = "wsConexionesId";
	public static final String TRANSACCIONES = "transacciones";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String TOKEN = "token";

}

