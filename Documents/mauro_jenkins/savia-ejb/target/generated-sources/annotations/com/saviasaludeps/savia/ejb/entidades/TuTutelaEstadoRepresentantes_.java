package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuTutelaEstadoRepresentantes.class)
public abstract class TuTutelaEstadoRepresentantes_ {

	public static volatile SingularAttribute<TuTutelaEstadoRepresentantes, TuRepresentantes> tuRepresentantesId;
	public static volatile SingularAttribute<TuTutelaEstadoRepresentantes, String> usuarioCrea;
	public static volatile SingularAttribute<TuTutelaEstadoRepresentantes, String> terminalCrea;
	public static volatile SingularAttribute<TuTutelaEstadoRepresentantes, String> tuTutelaEstadoRepresentantescol;
	public static volatile SingularAttribute<TuTutelaEstadoRepresentantes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuTutelaEstadoRepresentantes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuTutelaEstadoRepresentantes, TuTutelaEstados> tuTutelaEstadosId;
	public static volatile SingularAttribute<TuTutelaEstadoRepresentantes, Integer> id;
	public static volatile SingularAttribute<TuTutelaEstadoRepresentantes, String> usuarioModifica;

	public static final String TU_REPRESENTANTES_ID = "tuRepresentantesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String TU_TUTELA_ESTADO_REPRESENTANTESCOL = "tuTutelaEstadoRepresentantescol";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TU_TUTELA_ESTADOS_ID = "tuTutelaEstadosId";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

