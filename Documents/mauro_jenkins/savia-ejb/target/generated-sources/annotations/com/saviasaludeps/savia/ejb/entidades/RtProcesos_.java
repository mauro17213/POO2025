package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RtProcesos.class)
public abstract class RtProcesos_ {

	public static volatile SingularAttribute<RtProcesos, String> descripcion;
	public static volatile SingularAttribute<RtProcesos, String> terminalModifica;
	public static volatile SingularAttribute<RtProcesos, String> usuarioCrea;
	public static volatile SingularAttribute<RtProcesos, String> terminalCrea;
	public static volatile ListAttribute<RtProcesos, RtProcesoEjecuciones> rtProcesoEjecucionesList;
	public static volatile SingularAttribute<RtProcesos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RtProcesos, Date> fechaHoraModifica;
	public static volatile ListAttribute<RtProcesos, RtSentencias> rtSentenciasList;
	public static volatile SingularAttribute<RtProcesos, Integer> id;
	public static volatile SingularAttribute<RtProcesos, Integer> orden;
	public static volatile SingularAttribute<RtProcesos, String> nombre;
	public static volatile SingularAttribute<RtProcesos, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String RT_PROCESO_EJECUCIONES_LIST = "rtProcesoEjecucionesList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String RT_SENTENCIAS_LIST = "rtSentenciasList";
	public static final String ID = "id";
	public static final String ORDEN = "orden";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

