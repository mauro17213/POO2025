package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ArchivoPlanoTemporal.class)
public abstract class ArchivoPlanoTemporal_ {

	public static volatile SingularAttribute<ArchivoPlanoTemporal, String> estado;
	public static volatile SingularAttribute<ArchivoPlanoTemporal, Date> fechaInicio;
	public static volatile SingularAttribute<ArchivoPlanoTemporal, String> query;
	public static volatile SingularAttribute<ArchivoPlanoTemporal, Integer> id;
	public static volatile SingularAttribute<ArchivoPlanoTemporal, String> respuesta;
	public static volatile SingularAttribute<ArchivoPlanoTemporal, Date> fechaFin;

	public static final String ESTADO = "estado";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String QUERY = "query";
	public static final String ID = "id";
	public static final String RESPUESTA = "respuesta";
	public static final String FECHA_FIN = "fechaFin";

}

