package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatTiempos.class)
public abstract class GatTiempos_ {

	public static volatile SingularAttribute<GatTiempos, Integer> tipo;
	public static volatile SingularAttribute<GatTiempos, Date> fechaFin;
	public static volatile SingularAttribute<GatTiempos, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<GatTiempos, Integer> tiempoTotal;
	public static volatile SingularAttribute<GatTiempos, Integer> tiempoTranscurrido;
	public static volatile SingularAttribute<GatTiempos, Date> fechaFinReal;
	public static volatile SingularAttribute<GatTiempos, String> usuarioCrea;
	public static volatile SingularAttribute<GatTiempos, GatAtenciones> gatAtencionesId;
	public static volatile SingularAttribute<GatTiempos, Date> fechaInicio;
	public static volatile SingularAttribute<GatTiempos, String> terminalCrea;
	public static volatile SingularAttribute<GatTiempos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatTiempos, Integer> id;
	public static volatile SingularAttribute<GatTiempos, Boolean> activo;

	public static final String TIPO = "tipo";
	public static final String FECHA_FIN = "fechaFin";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String TIEMPO_TOTAL = "tiempoTotal";
	public static final String TIEMPO_TRANSCURRIDO = "tiempoTranscurrido";
	public static final String FECHA_FIN_REAL = "fechaFinReal";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String GAT_ATENCIONES_ID = "gatAtencionesId";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ACTIVO = "activo";

}

