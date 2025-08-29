package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WsServicios.class)
public abstract class WsServicios_ {

	public static volatile SingularAttribute<WsServicios, String> descripcion;
	public static volatile ListAttribute<WsServicios, WsTransacciones> wsTransaccionesList;
	public static volatile SingularAttribute<WsServicios, String> nombre;
	public static volatile SingularAttribute<WsServicios, String> terminalModifica;
	public static volatile SingularAttribute<WsServicios, String> usuarioCrea;
	public static volatile SingularAttribute<WsServicios, String> terminalCrea;
	public static volatile SingularAttribute<WsServicios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<WsServicios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<WsServicios, Integer> id;
	public static volatile ListAttribute<WsServicios, WsConexionesServicios> wsConexionesServiciosList;
	public static volatile SingularAttribute<WsServicios, Boolean> cont;
	public static volatile SingularAttribute<WsServicios, String> usuarioModifica;
	public static volatile SingularAttribute<WsServicios, Integer> tiempoCont;
	public static volatile SingularAttribute<WsServicios, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String WS_TRANSACCIONES_LIST = "wsTransaccionesList";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String WS_CONEXIONES_SERVICIOS_LIST = "wsConexionesServiciosList";
	public static final String CONT = "cont";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String TIEMPO_CONT = "tiempoCont";
	public static final String ACTIVO = "activo";

}

