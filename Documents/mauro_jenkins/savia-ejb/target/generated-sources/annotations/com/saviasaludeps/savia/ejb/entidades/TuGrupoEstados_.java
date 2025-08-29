package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuGrupoEstados.class)
public abstract class TuGrupoEstados_ {

	public static volatile SingularAttribute<TuGrupoEstados, String> terminalModifica;
	public static volatile SingularAttribute<TuGrupoEstados, String> usuarioCrea;
	public static volatile SingularAttribute<TuGrupoEstados, String> maeEstadoCodigo;
	public static volatile SingularAttribute<TuGrupoEstados, String> terminalCrea;
	public static volatile SingularAttribute<TuGrupoEstados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuGrupoEstados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuGrupoEstados, Integer> id;
	public static volatile SingularAttribute<TuGrupoEstados, Integer> maeEstadoId;
	public static volatile SingularAttribute<TuGrupoEstados, TuGrupos> tuGruposId;
	public static volatile SingularAttribute<TuGrupoEstados, String> maeEstadoValor;
	public static volatile SingularAttribute<TuGrupoEstados, Boolean> reparto;
	public static volatile SingularAttribute<TuGrupoEstados, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_ESTADO_CODIGO = "maeEstadoCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_ESTADO_ID = "maeEstadoId";
	public static final String TU_GRUPOS_ID = "tuGruposId";
	public static final String MAE_ESTADO_VALOR = "maeEstadoValor";
	public static final String REPARTO = "reparto";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

