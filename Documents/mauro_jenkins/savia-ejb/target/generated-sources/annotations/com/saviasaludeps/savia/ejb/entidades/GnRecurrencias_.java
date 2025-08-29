package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnRecurrencias.class)
public abstract class GnRecurrencias_ {

	public static volatile SingularAttribute<GnRecurrencias, String> descripcion;
	public static volatile SingularAttribute<GnRecurrencias, Integer> periodicidad;
	public static volatile ListAttribute<GnRecurrencias, GnRecurrenciaHistoricos> gnRecurrenciaHistoricosList;
	public static volatile SingularAttribute<GnRecurrencias, String> nombre;
	public static volatile SingularAttribute<GnRecurrencias, Date> fechaFin;
	public static volatile SingularAttribute<GnRecurrencias, byte[]> script;
	public static volatile SingularAttribute<GnRecurrencias, String> terminalModifica;
	public static volatile SingularAttribute<GnRecurrencias, String> usuarioCrea;
	public static volatile SingularAttribute<GnRecurrencias, Date> fechaInicio;
	public static volatile SingularAttribute<GnRecurrencias, String> terminalCrea;
	public static volatile SingularAttribute<GnRecurrencias, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnRecurrencias, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GnRecurrencias, Integer> id;
	public static volatile SingularAttribute<GnRecurrencias, Integer> tipoPeriodicidad;
	public static volatile SingularAttribute<GnRecurrencias, Date> fechaHoraEjecucion;
	public static volatile SingularAttribute<GnRecurrencias, String> usuarioModifica;
	public static volatile SingularAttribute<GnRecurrencias, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String PERIODICIDAD = "periodicidad";
	public static final String GN_RECURRENCIA_HISTORICOS_LIST = "gnRecurrenciaHistoricosList";
	public static final String NOMBRE = "nombre";
	public static final String FECHA_FIN = "fechaFin";
	public static final String SCRIPT = "script";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String TIPO_PERIODICIDAD = "tipoPeriodicidad";
	public static final String FECHA_HORA_EJECUCION = "fechaHoraEjecucion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

