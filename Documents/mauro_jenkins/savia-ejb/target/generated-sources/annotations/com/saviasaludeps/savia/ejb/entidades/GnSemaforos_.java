package com.saviasaludeps.savia.ejb.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnSemaforos.class)
public abstract class GnSemaforos_ {

	public static volatile SingularAttribute<GnSemaforos, Integer> tipo;
	public static volatile SingularAttribute<GnSemaforos, String> color;
	public static volatile SingularAttribute<GnSemaforos, Integer> tiempo;
	public static volatile SingularAttribute<GnSemaforos, Integer> id;
	public static volatile SingularAttribute<GnSemaforos, String> nombre;

	public static final String TIPO = "tipo";
	public static final String COLOR = "color";
	public static final String TIEMPO = "tiempo";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";

}

