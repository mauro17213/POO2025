package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RtReservaArchivos.class)
public abstract class RtReservaArchivos_ {

	public static volatile SingularAttribute<RtReservaArchivos, Boolean> tieneArchivo;
	public static volatile SingularAttribute<RtReservaArchivos, Short> tipo;
	public static volatile SingularAttribute<RtReservaArchivos, Integer> estado;
	public static volatile SingularAttribute<RtReservaArchivos, String> archivo;
	public static volatile SingularAttribute<RtReservaArchivos, String> ruta;
	public static volatile SingularAttribute<RtReservaArchivos, Date> fechaHoraInicio;
	public static volatile SingularAttribute<RtReservaArchivos, RtReservas> rtReservasId;
	public static volatile SingularAttribute<RtReservaArchivos, String> terminalModifica;
	public static volatile SingularAttribute<RtReservaArchivos, String> usuarioCrea;
	public static volatile SingularAttribute<RtReservaArchivos, String> archivoNombre;
	public static volatile SingularAttribute<RtReservaArchivos, Short> tiempo;
	public static volatile SingularAttribute<RtReservaArchivos, String> terminalCrea;
	public static volatile SingularAttribute<RtReservaArchivos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RtReservaArchivos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<RtReservaArchivos, Integer> id;
	public static volatile SingularAttribute<RtReservaArchivos, Boolean> descargado;
	public static volatile ListAttribute<RtReservaArchivos, RtReservaArchivoProcesos> rtReservaArchivoProcesosList;
	public static volatile SingularAttribute<RtReservaArchivos, String> observacion;
	public static volatile SingularAttribute<RtReservaArchivos, Date> fechaHoraFin;
	public static volatile SingularAttribute<RtReservaArchivos, String> usuarioModifica;

	public static final String TIENE_ARCHIVO = "tieneArchivo";
	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String RT_RESERVAS_ID = "rtReservasId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TIEMPO = "tiempo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String DESCARGADO = "descargado";
	public static final String RT_RESERVA_ARCHIVO_PROCESOS_LIST = "rtReservaArchivoProcesosList";
	public static final String OBSERVACION = "observacion";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

