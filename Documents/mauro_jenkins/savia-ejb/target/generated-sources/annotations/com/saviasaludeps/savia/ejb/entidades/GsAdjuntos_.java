package com.saviasaludeps.savia.ejb.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GsAdjuntos.class)
public abstract class GsAdjuntos_ {

	public static volatile SingularAttribute<GsAdjuntos, Integer> tipo;
	public static volatile SingularAttribute<GsAdjuntos, GsSolicitudes> gsSolicitudesId;
	public static volatile SingularAttribute<GsAdjuntos, String> archivo;
	public static volatile SingularAttribute<GsAdjuntos, String> ruta;
	public static volatile SingularAttribute<GsAdjuntos, Integer> id;
	public static volatile SingularAttribute<GsAdjuntos, Boolean> existe;
	public static volatile SingularAttribute<GsAdjuntos, String> nombre;

	public static final String TIPO = "tipo";
	public static final String GS_SOLICITUDES_ID = "gsSolicitudesId";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String ID = "id";
	public static final String EXISTE = "existe";
	public static final String NOMBRE = "nombre";

}

