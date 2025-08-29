package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RtSpProcesos.class)
public abstract class RtSpProcesos_ {

	public static volatile SingularAttribute<RtSpProcesos, String> storedProcedure;
	public static volatile SingularAttribute<RtSpProcesos, String> descripcion;
	public static volatile SingularAttribute<RtSpProcesos, Short> tipo;
	public static volatile SingularAttribute<RtSpProcesos, String> usuarioCrea;
	public static volatile SingularAttribute<RtSpProcesos, Short> tiempo;
	public static volatile SingularAttribute<RtSpProcesos, String> terminalCrea;
	public static volatile SingularAttribute<RtSpProcesos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RtSpProcesos, Integer> id;
	public static volatile SingularAttribute<RtSpProcesos, String> orden;
	public static volatile ListAttribute<RtSpProcesos, RtReservaArchivoProcesos> rtReservaArchivoProcesosList;
	public static volatile SingularAttribute<RtSpProcesos, Short> tipoArchivo;

	public static final String STORED_PROCEDURE = "storedProcedure";
	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIEMPO = "tiempo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ORDEN = "orden";
	public static final String RT_RESERVA_ARCHIVO_PROCESOS_LIST = "rtReservaArchivoProcesosList";
	public static final String TIPO_ARCHIVO = "tipoArchivo";

}

