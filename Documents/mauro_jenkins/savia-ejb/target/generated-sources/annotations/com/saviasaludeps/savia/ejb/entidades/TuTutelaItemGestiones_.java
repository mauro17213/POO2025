package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuTutelaItemGestiones.class)
public abstract class TuTutelaItemGestiones_ {

	public static volatile SingularAttribute<TuTutelaItemGestiones, TuTutelaItems> tuTutelaItemId;
	public static volatile SingularAttribute<TuTutelaItemGestiones, Integer> maeEstadoItemId;
	public static volatile SingularAttribute<TuTutelaItemGestiones, String> maeEstadoItemValor;
	public static volatile SingularAttribute<TuTutelaItemGestiones, String> terminalModifica;
	public static volatile SingularAttribute<TuTutelaItemGestiones, String> usuarioCrea;
	public static volatile SingularAttribute<TuTutelaItemGestiones, String> terminalCrea;
	public static volatile SingularAttribute<TuTutelaItemGestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuTutelaItemGestiones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuTutelaItemGestiones, Integer> id;
	public static volatile SingularAttribute<TuTutelaItemGestiones, String> observacion;
	public static volatile SingularAttribute<TuTutelaItemGestiones, String> observacionIps;
	public static volatile SingularAttribute<TuTutelaItemGestiones, String> usuarioModifica;
	public static volatile SingularAttribute<TuTutelaItemGestiones, String> maeEstadoItemCodigo;

	public static final String TU_TUTELA_ITEM_ID = "tuTutelaItemId";
	public static final String MAE_ESTADO_ITEM_ID = "maeEstadoItemId";
	public static final String MAE_ESTADO_ITEM_VALOR = "maeEstadoItemValor";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String OBSERVACION_IPS = "observacionIps";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String MAE_ESTADO_ITEM_CODIGO = "maeEstadoItemCodigo";

}

