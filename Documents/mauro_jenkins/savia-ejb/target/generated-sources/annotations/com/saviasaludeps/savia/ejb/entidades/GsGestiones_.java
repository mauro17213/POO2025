package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GsGestiones.class)
public abstract class GsGestiones_ {

	public static volatile SingularAttribute<GsGestiones, String> descripcion;
	public static volatile SingularAttribute<GsGestiones, Integer> estado;
	public static volatile SingularAttribute<GsGestiones, String> usuarioCrea;
	public static volatile SingularAttribute<GsGestiones, GsSolicitudes> gsSolicitudesId;
	public static volatile SingularAttribute<GsGestiones, String> terminalCrea;
	public static volatile SingularAttribute<GsGestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GsGestiones, Integer> id;
	public static volatile SingularAttribute<GsGestiones, GnUsuarios> gnUsuariosId;

	public static final String DESCRIPCION = "descripcion";
	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String GS_SOLICITUDES_ID = "gsSolicitudesId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";

}

