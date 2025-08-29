package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpConsumos.class)
public abstract class MpConsumos_ {

	public static volatile ListAttribute<MpConsumos, MpConsumoFallos> mpConsumoFallosList;
	public static volatile SingularAttribute<MpConsumos, String> servicioDireccion;
	public static volatile SingularAttribute<MpConsumos, Integer> estado;
	public static volatile SingularAttribute<MpConsumos, String> servicio;
	public static volatile SingularAttribute<MpConsumos, Date> periodo;
	public static volatile SingularAttribute<MpConsumos, Integer> registros;
	public static volatile SingularAttribute<MpConsumos, Date> fechaHoraInicio;
	public static volatile SingularAttribute<MpConsumos, Integer> id;
	public static volatile SingularAttribute<MpConsumos, String> observacion;
	public static volatile SingularAttribute<MpConsumos, Date> fechaHoraFin;
	public static volatile SingularAttribute<MpConsumos, Integer> registrosExitosos;
	public static volatile SingularAttribute<MpConsumos, String> servicioDescripcion;

	public static final String MP_CONSUMO_FALLOS_LIST = "mpConsumoFallosList";
	public static final String SERVICIO_DIRECCION = "servicioDireccion";
	public static final String ESTADO = "estado";
	public static final String SERVICIO = "servicio";
	public static final String PERIODO = "periodo";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String REGISTROS_EXITOSOS = "registrosExitosos";
	public static final String SERVICIO_DESCRIPCION = "servicioDescripcion";

}

