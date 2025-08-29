package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WsConexiones.class)
public abstract class WsConexiones_ {

	public static volatile ListAttribute<WsConexiones, WsTokens> wsTokensList;
	public static volatile SingularAttribute<WsConexiones, String> ip;
	public static volatile ListAttribute<WsConexiones, WsTransacciones> wsTransaccionesList;
	public static volatile SingularAttribute<WsConexiones, String> nombre;
	public static volatile SingularAttribute<WsConexiones, String> terminalModifica;
	public static volatile SingularAttribute<WsConexiones, String> usuarioCrea;
	public static volatile SingularAttribute<WsConexiones, String> llave;
	public static volatile SingularAttribute<WsConexiones, Integer> tipoAutenticacion;
	public static volatile SingularAttribute<WsConexiones, String> terminalCrea;
	public static volatile SingularAttribute<WsConexiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<WsConexiones, String> usuario;
	public static volatile SingularAttribute<WsConexiones, String> contrasena;
	public static volatile SingularAttribute<WsConexiones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<WsConexiones, Integer> id;
	public static volatile ListAttribute<WsConexiones, WsConexionesServicios> wsConexionesServiciosList;
	public static volatile SingularAttribute<WsConexiones, String> usuarioModifica;
	public static volatile SingularAttribute<WsConexiones, Boolean> activo;

	public static final String WS_TOKENS_LIST = "wsTokensList";
	public static final String IP = "ip";
	public static final String WS_TRANSACCIONES_LIST = "wsTransaccionesList";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String LLAVE = "llave";
	public static final String TIPO_AUTENTICACION = "tipoAutenticacion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String USUARIO = "usuario";
	public static final String CONTRASENA = "contrasena";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String WS_CONEXIONES_SERVICIOS_LIST = "wsConexionesServiciosList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

