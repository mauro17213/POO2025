package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnRecurrenciaHistoricos.class)
public abstract class GnRecurrenciaHistoricos_ {

	public static volatile SingularAttribute<GnRecurrenciaHistoricos, Boolean> exitoso;
	public static volatile SingularAttribute<GnRecurrenciaHistoricos, Integer> tiempo;
	public static volatile SingularAttribute<GnRecurrenciaHistoricos, Date> fechaHoraInicio;
	public static volatile SingularAttribute<GnRecurrenciaHistoricos, Integer> id;
	public static volatile SingularAttribute<GnRecurrenciaHistoricos, String> respuesta;
	public static volatile SingularAttribute<GnRecurrenciaHistoricos, GnRecurrencias> gnRecurrenciasId;
	public static volatile SingularAttribute<GnRecurrenciaHistoricos, Date> fechaHoraFin;

	public static final String EXITOSO = "exitoso";
	public static final String TIEMPO = "tiempo";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String RESPUESTA = "respuesta";
	public static final String GN_RECURRENCIAS_ID = "gnRecurrenciasId";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";

}

