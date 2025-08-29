package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpConsumosAlterno.class)
public abstract class MpConsumosAlterno_ {

	public static volatile SingularAttribute<MpConsumosAlterno, Integer> estado;
	public static volatile SingularAttribute<MpConsumosAlterno, String> servicio;
	public static volatile SingularAttribute<MpConsumosAlterno, Date> periodo;
	public static volatile SingularAttribute<MpConsumosAlterno, Integer> registros;
	public static volatile SingularAttribute<MpConsumosAlterno, Date> fechaHoraInicio;
	public static volatile SingularAttribute<MpConsumosAlterno, Integer> id;
	public static volatile SingularAttribute<MpConsumosAlterno, String> observacion;
	public static volatile SingularAttribute<MpConsumosAlterno, Date> fechaHoraFin;
	public static volatile SingularAttribute<MpConsumosAlterno, Integer> registrosExitosos;

	public static final String ESTADO = "estado";
	public static final String SERVICIO = "servicio";
	public static final String PERIODO = "periodo";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String REGISTROS_EXITOSOS = "registrosExitosos";

}

