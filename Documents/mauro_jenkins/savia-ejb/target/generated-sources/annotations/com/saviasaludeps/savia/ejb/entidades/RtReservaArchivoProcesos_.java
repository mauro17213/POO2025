package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RtReservaArchivoProcesos.class)
public abstract class RtReservaArchivoProcesos_ {

	public static volatile SingularAttribute<RtReservaArchivoProcesos, String> descripcion;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, Short> estado;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, String> usuarioCrea;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, Short> tiempo;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, String> terminalCrea;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, RtSpProcesos> spProcesosId;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, RtReservaArchivos> rtReservaArchivosId;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, Date> fechaHoraInicio;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, Integer> id;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, String> nombre;
	public static volatile SingularAttribute<RtReservaArchivoProcesos, Date> fechaHoraFin;

	public static final String DESCRIPCION = "descripcion";
	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIEMPO = "tiempo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String SP_PROCESOS_ID = "spProcesosId";
	public static final String RT_RESERVA_ARCHIVOS_ID = "rtReservaArchivosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";

}

