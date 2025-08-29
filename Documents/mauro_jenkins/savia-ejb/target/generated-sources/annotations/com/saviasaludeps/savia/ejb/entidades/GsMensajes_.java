package com.saviasaludeps.savia.ejb.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GsMensajes.class)
public abstract class GsMensajes_ {

	public static volatile SingularAttribute<GsMensajes, String> mensajeLargo;
	public static volatile SingularAttribute<GsMensajes, Integer> tipo;
	public static volatile SingularAttribute<GsMensajes, Integer> estado;
	public static volatile SingularAttribute<GsMensajes, String> encabezado;
	public static volatile ListAttribute<GsMensajes, GsSolicitudes> gsSolicitudesList;
	public static volatile SingularAttribute<GsMensajes, Integer> id;
	public static volatile SingularAttribute<GsMensajes, String> mensajeCorto;
	public static volatile SingularAttribute<GsMensajes, String> nombre;

	public static final String MENSAJE_LARGO = "mensajeLargo";
	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String ENCABEZADO = "encabezado";
	public static final String GS_SOLICITUDES_LIST = "gsSolicitudesList";
	public static final String ID = "id";
	public static final String MENSAJE_CORTO = "mensajeCorto";
	public static final String NOMBRE = "nombre";

}

