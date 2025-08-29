package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpDireccionamientosProgramadas.class)
public abstract class MpDireccionamientosProgramadas_ {

	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Integer> entregaTotal;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Integer> estado;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Date> fechaMaximaDireccionamiento;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, MpDireccionamientos> mpDireccionamientosId;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Integer> idReporteEntrega;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Date> fechaAnulacion;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Integer> causaNoEntrega;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Integer> numeroEntrega;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, String> usuarioCrea;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, String> justificacionDireccionamiento;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, String> terminalCrea;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Date> fechaDireccionamientoAutomatico;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Date> fechaEntrega;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Integer> id;
	public static volatile SingularAttribute<MpDireccionamientosProgramadas, Integer> cantidadEntrega;

	public static final String ENTREGA_TOTAL = "entregaTotal";
	public static final String ESTADO = "estado";
	public static final String FECHA_MAXIMA_DIRECCIONAMIENTO = "fechaMaximaDireccionamiento";
	public static final String MP_DIRECCIONAMIENTOS_ID = "mpDireccionamientosId";
	public static final String ID_REPORTE_ENTREGA = "idReporteEntrega";
	public static final String FECHA_ANULACION = "fechaAnulacion";
	public static final String CAUSA_NO_ENTREGA = "causaNoEntrega";
	public static final String NUMERO_ENTREGA = "numeroEntrega";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String JUSTIFICACION_DIRECCIONAMIENTO = "justificacionDireccionamiento";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_DIRECCIONAMIENTO_AUTOMATICO = "fechaDireccionamientoAutomatico";
	public static final String FECHA_ENTREGA = "fechaEntrega";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CANTIDAD_ENTREGA = "cantidadEntrega";

}

