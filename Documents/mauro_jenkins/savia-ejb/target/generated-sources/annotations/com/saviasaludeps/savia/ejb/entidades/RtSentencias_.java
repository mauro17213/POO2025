package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RtSentencias.class)
public abstract class RtSentencias_ {

	public static volatile SingularAttribute<RtSentencias, String> descripcion;
	public static volatile SingularAttribute<RtSentencias, byte[]> sentencia;
	public static volatile SingularAttribute<RtSentencias, String> nombre;
	public static volatile SingularAttribute<RtSentencias, String> terminalModifica;
	public static volatile SingularAttribute<RtSentencias, String> usuarioCrea;
	public static volatile SingularAttribute<RtSentencias, RtProcesos> rtProcesosId;
	public static volatile SingularAttribute<RtSentencias, String> terminalCrea;
	public static volatile ListAttribute<RtSentencias, RtSentenciasEjecuciones> rtSentenciasEjecucionesList;
	public static volatile SingularAttribute<RtSentencias, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RtSentencias, Date> fechaHoraModifica;
	public static volatile SingularAttribute<RtSentencias, Integer> id;
	public static volatile SingularAttribute<RtSentencias, Integer> orden;
	public static volatile SingularAttribute<RtSentencias, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String SENTENCIA = "sentencia";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String RT_PROCESOS_ID = "rtProcesosId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String RT_SENTENCIAS_EJECUCIONES_LIST = "rtSentenciasEjecucionesList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ORDEN = "orden";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

