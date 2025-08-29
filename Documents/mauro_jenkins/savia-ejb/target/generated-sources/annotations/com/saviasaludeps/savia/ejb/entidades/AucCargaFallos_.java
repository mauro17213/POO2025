package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucCargaFallos.class)
public abstract class AucCargaFallos_ {

	public static volatile SingularAttribute<AucCargaFallos, String> descripcion;
	public static volatile SingularAttribute<AucCargaFallos, Integer> columna;
	public static volatile SingularAttribute<AucCargaFallos, Integer> tipo;
	public static volatile SingularAttribute<AucCargaFallos, AucCargas> aucCargasId;
	public static volatile SingularAttribute<AucCargaFallos, Date> fechaHora;
	public static volatile SingularAttribute<AucCargaFallos, Integer> fila;
	public static volatile SingularAttribute<AucCargaFallos, Integer> id;

	public static final String DESCRIPCION = "descripcion";
	public static final String COLUMNA = "columna";
	public static final String TIPO = "tipo";
	public static final String AUC_CARGAS_ID = "aucCargasId";
	public static final String FECHA_HORA = "fechaHora";
	public static final String FILA = "fila";
	public static final String ID = "id";

}

