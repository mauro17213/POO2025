package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpProgramaciones.class)
public abstract class MpProgramaciones_ {

	public static volatile SingularAttribute<MpProgramaciones, Integer> direcionamientoId;
	public static volatile SingularAttribute<MpProgramaciones, Date> fechaAnulacionDireccionamiento;
	public static volatile SingularAttribute<MpProgramaciones, Integer> totalEntregar;
	public static volatile SingularAttribute<MpProgramaciones, Integer> cantidadTotalPrescrita;
	public static volatile SingularAttribute<MpProgramaciones, Integer> estadoProgramacion;
	public static volatile SingularAttribute<MpProgramaciones, Date> fechaDireccionamiento;
	public static volatile SingularAttribute<MpProgramaciones, Date> fechaMaxEntrega;
	public static volatile SingularAttribute<MpProgramaciones, Integer> subEntrega;
	public static volatile SingularAttribute<MpProgramaciones, Integer> numeroProgramacion;
	public static volatile SingularAttribute<MpProgramaciones, Integer> cantidadPendienteEntregar;
	public static volatile SingularAttribute<MpProgramaciones, Integer> cantidadPendiente;
	public static volatile SingularAttribute<MpProgramaciones, String> usuarioCrea;
	public static volatile SingularAttribute<MpProgramaciones, String> terminalCrea;
	public static volatile SingularAttribute<MpProgramaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpProgramaciones, Integer> id;
	public static volatile SingularAttribute<MpProgramaciones, String> causalNoEntrega;

	public static final String DIRECIONAMIENTO_ID = "direcionamientoId";
	public static final String FECHA_ANULACION_DIRECCIONAMIENTO = "fechaAnulacionDireccionamiento";
	public static final String TOTAL_ENTREGAR = "totalEntregar";
	public static final String CANTIDAD_TOTAL_PRESCRITA = "cantidadTotalPrescrita";
	public static final String ESTADO_PROGRAMACION = "estadoProgramacion";
	public static final String FECHA_DIRECCIONAMIENTO = "fechaDireccionamiento";
	public static final String FECHA_MAX_ENTREGA = "fechaMaxEntrega";
	public static final String SUB_ENTREGA = "subEntrega";
	public static final String NUMERO_PROGRAMACION = "numeroProgramacion";
	public static final String CANTIDAD_PENDIENTE_ENTREGAR = "cantidadPendienteEntregar";
	public static final String CANTIDAD_PENDIENTE = "cantidadPendiente";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CAUSAL_NO_ENTREGA = "causalNoEntrega";

}

