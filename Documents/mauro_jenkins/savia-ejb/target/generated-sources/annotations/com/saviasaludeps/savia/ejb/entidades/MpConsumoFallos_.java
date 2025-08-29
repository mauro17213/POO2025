package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpConsumoFallos.class)
public abstract class MpConsumoFallos_ {

	public static volatile SingularAttribute<MpConsumoFallos, String> descripcion;
	public static volatile SingularAttribute<MpConsumoFallos, Short> estado;
	public static volatile SingularAttribute<MpConsumoFallos, Date> fechaProceso;
	public static volatile SingularAttribute<MpConsumoFallos, Date> fechaHoraResincronizacin;
	public static volatile SingularAttribute<MpConsumoFallos, Date> fechaHoraFallo;
	public static volatile SingularAttribute<MpConsumoFallos, MpConsumos> mpConsumosId;
	public static volatile SingularAttribute<MpConsumoFallos, Date> fechaHoraCorreccion;
	public static volatile SingularAttribute<MpConsumoFallos, byte[]> json;
	public static volatile SingularAttribute<MpConsumoFallos, Integer> id;
	public static volatile SingularAttribute<MpConsumoFallos, Integer> idProceso;

	public static final String DESCRIPCION = "descripcion";
	public static final String ESTADO = "estado";
	public static final String FECHA_PROCESO = "fechaProceso";
	public static final String FECHA_HORA_RESINCRONIZACIN = "fechaHoraResincronizacin";
	public static final String FECHA_HORA_FALLO = "fechaHoraFallo";
	public static final String MP_CONSUMOS_ID = "mpConsumosId";
	public static final String FECHA_HORA_CORRECCION = "fechaHoraCorreccion";
	public static final String JSON = "json";
	public static final String ID = "id";
	public static final String ID_PROCESO = "idProceso";

}

