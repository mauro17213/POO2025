package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RtSentenciasEjecuciones.class)
public abstract class RtSentenciasEjecuciones_ {

	public static volatile SingularAttribute<RtSentenciasEjecuciones, Boolean> estado;
	public static volatile SingularAttribute<RtSentenciasEjecuciones, RtSentencias> rtSentenciasId;
	public static volatile SingularAttribute<RtSentenciasEjecuciones, Date> fechaHoraInicio;
	public static volatile SingularAttribute<RtSentenciasEjecuciones, Integer> id;
	public static volatile SingularAttribute<RtSentenciasEjecuciones, RtProcesoEjecuciones> rtProcesoEjecucionesId;
	public static volatile SingularAttribute<RtSentenciasEjecuciones, Date> fechaHoraFin;

	public static final String ESTADO = "estado";
	public static final String RT_SENTENCIAS_ID = "rtSentenciasId";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String RT_PROCESO_EJECUCIONES_ID = "rtProcesoEjecucionesId";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";

}

