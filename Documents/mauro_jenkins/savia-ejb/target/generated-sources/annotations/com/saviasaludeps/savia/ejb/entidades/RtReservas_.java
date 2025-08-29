package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RtReservas.class)
public abstract class RtReservas_ {

	public static volatile ListAttribute<RtReservas, RtProcesoEjecuciones> rtProcesoEjecucionesList;
	public static volatile SingularAttribute<RtReservas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<RtReservas, String> nombre;
	public static volatile SingularAttribute<RtReservas, Boolean> aprobacion;
	public static volatile SingularAttribute<RtReservas, Integer> estados;
	public static volatile SingularAttribute<RtReservas, Date> fecha;
	public static volatile SingularAttribute<RtReservas, String> terminalModifica;
	public static volatile SingularAttribute<RtReservas, String> usuarioCrea;
	public static volatile ListAttribute<RtReservas, RtReservaArchivos> rtReservaArchivosList;
	public static volatile SingularAttribute<RtReservas, Short> tiempo;
	public static volatile SingularAttribute<RtReservas, String> terminalCrea;
	public static volatile SingularAttribute<RtReservas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RtReservas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<RtReservas, Integer> id;
	public static volatile SingularAttribute<RtReservas, String> observacion;
	public static volatile SingularAttribute<RtReservas, Date> fechaHoraFin;
	public static volatile SingularAttribute<RtReservas, String> usuarioModifica;

	public static final String RT_PROCESO_EJECUCIONES_LIST = "rtProcesoEjecucionesList";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String NOMBRE = "nombre";
	public static final String APROBACION = "aprobacion";
	public static final String ESTADOS = "estados";
	public static final String FECHA = "fecha";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String RT_RESERVA_ARCHIVOS_LIST = "rtReservaArchivosList";
	public static final String TIEMPO = "tiempo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

