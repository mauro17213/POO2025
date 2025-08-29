package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AntAnticipoGestiones.class)
public abstract class AntAnticipoGestiones_ {

	public static volatile SingularAttribute<AntAnticipoGestiones, String> descripcion;
	public static volatile SingularAttribute<AntAnticipoGestiones, Integer> tipo;
	public static volatile SingularAttribute<AntAnticipoGestiones, Integer> estado;
	public static volatile SingularAttribute<AntAnticipoGestiones, String> usuarioCrea;
	public static volatile SingularAttribute<AntAnticipoGestiones, String> terminalCrea;
	public static volatile SingularAttribute<AntAnticipoGestiones, AntAnticipos> antAnticiposId;
	public static volatile SingularAttribute<AntAnticipoGestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AntAnticipoGestiones, Integer> id;

	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ANT_ANTICIPOS_ID = "antAnticiposId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

