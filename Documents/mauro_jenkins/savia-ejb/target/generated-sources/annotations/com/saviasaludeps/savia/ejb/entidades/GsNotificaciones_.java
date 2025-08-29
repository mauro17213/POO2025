package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GsNotificaciones.class)
public abstract class GsNotificaciones_ {

	public static volatile SingularAttribute<GsNotificaciones, Integer> tipo;
	public static volatile SingularAttribute<GsNotificaciones, Integer> estado;
	public static volatile SingularAttribute<GsNotificaciones, GsSolicitudes> gsSolicitudesId;
	public static volatile SingularAttribute<GsNotificaciones, String> correo;
	public static volatile SingularAttribute<GsNotificaciones, String> celular;
	public static volatile SingularAttribute<GsNotificaciones, String> encabezado;
	public static volatile SingularAttribute<GsNotificaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GsNotificaciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GsNotificaciones, Integer> id;
	public static volatile SingularAttribute<GsNotificaciones, String> detalle;

	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String GS_SOLICITUDES_ID = "gsSolicitudesId";
	public static final String CORREO = "correo";
	public static final String CELULAR = "celular";
	public static final String ENCABEZADO = "encabezado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String DETALLE = "detalle";

}

