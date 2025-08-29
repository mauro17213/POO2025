package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnZonasHorarias.class)
public abstract class GnZonasHorarias_ {

	public static volatile SingularAttribute<GnZonasHorarias, String> codigo;
	public static volatile SingularAttribute<GnZonasHorarias, BigDecimal> utc;
	public static volatile SingularAttribute<GnZonasHorarias, Integer> id;
	public static volatile ListAttribute<GnZonasHorarias, GnUsuarios> gnUsuariosList;
	public static volatile SingularAttribute<GnZonasHorarias, String> nombre;
	public static volatile ListAttribute<GnZonasHorarias, GnUbicaciones> gnUbicacionesList;

	public static final String CODIGO = "codigo";
	public static final String UTC = "utc";
	public static final String ID = "id";
	public static final String GN_USUARIOS_LIST = "gnUsuariosList";
	public static final String NOMBRE = "nombre";
	public static final String GN_UBICACIONES_LIST = "gnUbicacionesList";

}

