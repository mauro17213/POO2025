package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GjProcesoPretenciones.class)
public abstract class GjProcesoPretenciones_ {

	public static volatile SingularAttribute<GjProcesoPretenciones, String> usuarioCrea;
	public static volatile SingularAttribute<GjProcesoPretenciones, GjProcesos> gjProcesosId;
	public static volatile SingularAttribute<GjProcesoPretenciones, Integer> maePretencionId;
	public static volatile SingularAttribute<GjProcesoPretenciones, String> terminalCrea;
	public static volatile SingularAttribute<GjProcesoPretenciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GjProcesoPretenciones, Integer> id;
	public static volatile SingularAttribute<GjProcesoPretenciones, String> maePretencionCodigo;
	public static volatile SingularAttribute<GjProcesoPretenciones, String> maePretencionValor;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String GJ_PROCESOS_ID = "gjProcesosId";
	public static final String MAE_PRETENCION_ID = "maePretencionId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_PRETENCION_CODIGO = "maePretencionCodigo";
	public static final String MAE_PRETENCION_VALOR = "maePretencionValor";

}

