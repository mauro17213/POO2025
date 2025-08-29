package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuJuzgados.class)
public abstract class TuJuzgados_ {

	public static volatile SingularAttribute<TuJuzgados, GnUbicaciones> gnUbicacionesId;
	public static volatile ListAttribute<TuJuzgados, GjProcesos> gjProcesosList;
	public static volatile SingularAttribute<TuJuzgados, String> nombre;
	public static volatile ListAttribute<TuJuzgados, TuJuzgadoContactos> tuJuzgadoContactosList;
	public static volatile ListAttribute<TuJuzgados, TuTutelaEstados> tuTutelaEstadosList;
	public static volatile SingularAttribute<TuJuzgados, String> terminalModifica;
	public static volatile SingularAttribute<TuJuzgados, String> usuarioCrea;
	public static volatile SingularAttribute<TuJuzgados, String> terminalCrea;
	public static volatile SingularAttribute<TuJuzgados, String> codigoDespacho;
	public static volatile SingularAttribute<TuJuzgados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuJuzgados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuJuzgados, Integer> id;
	public static volatile SingularAttribute<TuJuzgados, String> usuarioModifica;
	public static volatile SingularAttribute<TuJuzgados, Boolean> activo;

	public static final String GN_UBICACIONES_ID = "gnUbicacionesId";
	public static final String GJ_PROCESOS_LIST = "gjProcesosList";
	public static final String NOMBRE = "nombre";
	public static final String TU_JUZGADO_CONTACTOS_LIST = "tuJuzgadoContactosList";
	public static final String TU_TUTELA_ESTADOS_LIST = "tuTutelaEstadosList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CODIGO_DESPACHO = "codigoDespacho";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

