package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuRepresentantes.class)
public abstract class TuRepresentantes_ {

	public static volatile SingularAttribute<TuRepresentantes, String> terminalModifica;
	public static volatile SingularAttribute<TuRepresentantes, String> usuarioCrea;
	public static volatile ListAttribute<TuRepresentantes, TuTutelaEstadoRepresentantes> tuTutelaEstadoRepresentantesList;
	public static volatile SingularAttribute<TuRepresentantes, String> terminalCrea;
	public static volatile SingularAttribute<TuRepresentantes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuRepresentantes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuRepresentantes, Integer> id;
	public static volatile SingularAttribute<TuRepresentantes, String> nombre;
	public static volatile SingularAttribute<TuRepresentantes, String> usuarioModifica;
	public static volatile SingularAttribute<TuRepresentantes, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TU_TUTELA_ESTADO_REPRESENTANTES_LIST = "tuTutelaEstadoRepresentantesList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

