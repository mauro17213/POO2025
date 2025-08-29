package com.saviasaludeps.savia.ejb.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnValidacionCampos.class)
public abstract class GnValidacionCampos_ {

	public static volatile ListAttribute<GnValidacionCampos, GnMaestros> gnMaestrosList;
	public static volatile SingularAttribute<GnValidacionCampos, String> expresionRegular;
	public static volatile SingularAttribute<GnValidacionCampos, GnMaestroTipos> gnMaestroTiposTipo;
	public static volatile SingularAttribute<GnValidacionCampos, String> validator;
	public static volatile SingularAttribute<GnValidacionCampos, Integer> id;
	public static volatile SingularAttribute<GnValidacionCampos, String> nombre;

	public static final String GN_MAESTROS_LIST = "gnMaestrosList";
	public static final String EXPRESION_REGULAR = "expresionRegular";
	public static final String GN_MAESTRO_TIPOS_TIPO = "gnMaestroTiposTipo";
	public static final String VALIDATOR = "validator";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";

}

