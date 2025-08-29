package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnAlertas.class)
public abstract class GnAlertas_ {

	public static volatile SingularAttribute<GnAlertas, String> descripcion;
	public static volatile SingularAttribute<GnAlertas, Integer> estado;
	public static volatile SingularAttribute<GnAlertas, Date> fechaHoraDescarta;
	public static volatile SingularAttribute<GnAlertas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnAlertas, Integer> severidad;
	public static volatile SingularAttribute<GnAlertas, Integer> id;
	public static volatile SingularAttribute<GnAlertas, String> nombre;
	public static volatile SingularAttribute<GnAlertas, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<GnAlertas, Date> fechaHoraLee;

	public static final String DESCRIPCION = "descripcion";
	public static final String ESTADO = "estado";
	public static final String FECHA_HORA_DESCARTA = "fechaHoraDescarta";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String SEVERIDAD = "severidad";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String FECHA_HORA_LEE = "fechaHoraLee";

}

