package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuTutelas.class)
public abstract class TuTutelas_ {

	public static volatile SingularAttribute<TuTutelas, Integer> diasVencimiento;
	public static volatile SingularAttribute<TuTutelas, Date> fechaHoraBorra;
	public static volatile SingularAttribute<TuTutelas, TuTutelaEstados> actualTuTutelaEstadosId;
	public static volatile SingularAttribute<TuTutelas, String> usuarioBorra;
	public static volatile ListAttribute<TuTutelas, TuTutelaItems> tuTutelaItemsList;
	public static volatile SingularAttribute<TuTutelas, String> radicadoNumero;
	public static volatile ListAttribute<TuTutelas, TuTutelaEstados> tuTutelaEstadosList;
	public static volatile SingularAttribute<TuTutelas, String> terminalModifica;
	public static volatile SingularAttribute<TuTutelas, String> usuarioCrea;
	public static volatile ListAttribute<TuTutelas, TuTutelaDiagnosticos> tuTutelaDiagnosticosList;
	public static volatile SingularAttribute<TuTutelas, TuPersonas> tuPersonasId;
	public static volatile SingularAttribute<TuTutelas, Integer> cantidadItems;
	public static volatile SingularAttribute<TuTutelas, GnUbicaciones> gnUbicacionId;
	public static volatile SingularAttribute<TuTutelas, String> terminalCrea;
	public static volatile SingularAttribute<TuTutelas, Integer> cantidadItemsCerrados;
	public static volatile SingularAttribute<TuTutelas, Boolean> borrado;
	public static volatile SingularAttribute<TuTutelas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuTutelas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuTutelas, String> terminalBorra;
	public static volatile SingularAttribute<TuTutelas, Integer> id;
	public static volatile SingularAttribute<TuTutelas, String> usuarioModifica;

	public static final String DIAS_VENCIMIENTO = "diasVencimiento";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String ACTUAL_TU_TUTELA_ESTADOS_ID = "actualTuTutelaEstadosId";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String TU_TUTELA_ITEMS_LIST = "tuTutelaItemsList";
	public static final String RADICADO_NUMERO = "radicadoNumero";
	public static final String TU_TUTELA_ESTADOS_LIST = "tuTutelaEstadosList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TU_TUTELA_DIAGNOSTICOS_LIST = "tuTutelaDiagnosticosList";
	public static final String TU_PERSONAS_ID = "tuPersonasId";
	public static final String CANTIDAD_ITEMS = "cantidadItems";
	public static final String GN_UBICACION_ID = "gnUbicacionId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CANTIDAD_ITEMS_CERRADOS = "cantidadItemsCerrados";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

