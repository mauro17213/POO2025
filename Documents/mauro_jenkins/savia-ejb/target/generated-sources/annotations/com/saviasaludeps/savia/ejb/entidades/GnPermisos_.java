package com.saviasaludeps.savia.ejb.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnPermisos.class)
public abstract class GnPermisos_ {

	public static volatile SingularAttribute<GnPermisos, String> privilegios;
	public static volatile SingularAttribute<GnPermisos, GnPermisosPK> gnPermisosPK;
	public static volatile SingularAttribute<GnPermisos, GnModulos> gnModulos;
	public static volatile SingularAttribute<GnPermisos, GnPerfiles> gnPerfiles;

	public static final String PRIVILEGIOS = "privilegios";
	public static final String GN_PERMISOS_PK = "gnPermisosPK";
	public static final String GN_MODULOS = "gnModulos";
	public static final String GN_PERFILES = "gnPerfiles";

}

