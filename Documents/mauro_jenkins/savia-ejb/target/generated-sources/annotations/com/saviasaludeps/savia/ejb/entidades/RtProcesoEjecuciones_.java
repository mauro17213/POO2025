package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RtProcesoEjecuciones.class)
public abstract class RtProcesoEjecuciones_ {

	public static volatile SingularAttribute<RtProcesoEjecuciones, Boolean> estado;
	public static volatile SingularAttribute<RtProcesoEjecuciones, RtProcesos> rtProcesosId;
	public static volatile ListAttribute<RtProcesoEjecuciones, RtSentenciasEjecuciones> rtSentenciasEjecucionesList;
	public static volatile SingularAttribute<RtProcesoEjecuciones, Date> fechaHoraInicio;
	public static volatile SingularAttribute<RtProcesoEjecuciones, Integer> id;
	public static volatile SingularAttribute<RtProcesoEjecuciones, RtReservas> rtReservasId;
	public static volatile SingularAttribute<RtProcesoEjecuciones, Date> fechaHoraFin;

	public static final String ESTADO = "estado";
	public static final String RT_PROCESOS_ID = "rtProcesosId";
	public static final String RT_SENTENCIAS_EJECUCIONES_LIST = "rtSentenciasEjecucionesList";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String RT_RESERVAS_ID = "rtReservasId";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";

}

