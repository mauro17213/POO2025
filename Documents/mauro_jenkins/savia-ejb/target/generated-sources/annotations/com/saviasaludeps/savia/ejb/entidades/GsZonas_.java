package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GsZonas.class)
public abstract class GsZonas_ {

	public static volatile SingularAttribute<GsZonas, String> descripcion;
	public static volatile SingularAttribute<GsZonas, GnUbicaciones> gnUbicacionesId;
	public static volatile SingularAttribute<GsZonas, Boolean> porDefecto;
	public static volatile ListAttribute<GsZonas, GsSolicitudes> gsSolicitudesList;
	public static volatile SingularAttribute<GsZonas, String> nombre;
	public static volatile SingularAttribute<GsZonas, String> terminalModifica;
	public static volatile SingularAttribute<GsZonas, String> usuarioCrea;
	public static volatile SingularAttribute<GsZonas, String> terminalCrea;
	public static volatile SingularAttribute<GsZonas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GsZonas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GsZonas, Integer> id;
	public static volatile ListAttribute<GsZonas, GsZonaUsuarios> gsZonaUsuariosList;
	public static volatile SingularAttribute<GsZonas, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String GN_UBICACIONES_ID = "gnUbicacionesId";
	public static final String POR_DEFECTO = "porDefecto";
	public static final String GS_SOLICITUDES_LIST = "gsSolicitudesList";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String GS_ZONA_USUARIOS_LIST = "gsZonaUsuariosList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

