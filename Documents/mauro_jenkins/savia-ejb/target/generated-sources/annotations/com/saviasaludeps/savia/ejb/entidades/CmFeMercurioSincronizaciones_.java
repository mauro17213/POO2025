package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFeMercurioSincronizaciones.class)
public abstract class CmFeMercurioSincronizaciones_ {

	public static volatile SingularAttribute<CmFeMercurioSincronizaciones, String> descripcion;
	public static volatile SingularAttribute<CmFeMercurioSincronizaciones, Integer> registrosConsultados;
	public static volatile SingularAttribute<CmFeMercurioSincronizaciones, Short> estado;
	public static volatile SingularAttribute<CmFeMercurioSincronizaciones, Date> periodo;
	public static volatile SingularAttribute<CmFeMercurioSincronizaciones, Integer> duracion;
	public static volatile SingularAttribute<CmFeMercurioSincronizaciones, Date> fechaHoraInicio;
	public static volatile SingularAttribute<CmFeMercurioSincronizaciones, Integer> id;
	public static volatile SingularAttribute<CmFeMercurioSincronizaciones, Integer> registrosActualizados;
	public static volatile SingularAttribute<CmFeMercurioSincronizaciones, Date> fechaHoraFin;

	public static final String DESCRIPCION = "descripcion";
	public static final String REGISTROS_CONSULTADOS = "registrosConsultados";
	public static final String ESTADO = "estado";
	public static final String PERIODO = "periodo";
	public static final String DURACION = "duracion";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String REGISTROS_ACTUALIZADOS = "registrosActualizados";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";

}

