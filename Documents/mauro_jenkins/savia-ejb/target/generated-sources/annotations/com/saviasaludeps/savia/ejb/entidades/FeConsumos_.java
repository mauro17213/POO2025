package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FeConsumos.class)
public abstract class FeConsumos_ {

	public static volatile SingularAttribute<FeConsumos, Short> estado;
	public static volatile SingularAttribute<FeConsumos, Date> periodo;
	public static volatile SingularAttribute<FeConsumos, Integer> registrosReportados;
	public static volatile SingularAttribute<FeConsumos, Date> fechaHoraInicio;
	public static volatile SingularAttribute<FeConsumos, Integer> id;
	public static volatile SingularAttribute<FeConsumos, String> observacion;
	public static volatile SingularAttribute<FeConsumos, Date> fechaHoraFin;
	public static volatile SingularAttribute<FeConsumos, Integer> registrosExitosos;

	public static final String ESTADO = "estado";
	public static final String PERIODO = "periodo";
	public static final String REGISTROS_REPORTADOS = "registrosReportados";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String REGISTROS_EXITOSOS = "registrosExitosos";

}

