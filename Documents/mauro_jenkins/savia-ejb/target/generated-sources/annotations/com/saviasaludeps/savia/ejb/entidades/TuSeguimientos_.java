package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuSeguimientos.class)
public abstract class TuSeguimientos_ {

	public static volatile SingularAttribute<TuSeguimientos, TuTutelaEstados> tuTutelaEstadosId;
	public static volatile ListAttribute<TuSeguimientos, TuAdjuntos> tuAdjuntosList;
	public static volatile SingularAttribute<TuSeguimientos, String> terminalModifica;
	public static volatile SingularAttribute<TuSeguimientos, String> usuarioCrea;
	public static volatile SingularAttribute<TuSeguimientos, GnUsuarios> gestorGnUsuarioId;
	public static volatile SingularAttribute<TuSeguimientos, String> terminalCrea;
	public static volatile SingularAttribute<TuSeguimientos, String> maeTipoSeguimientoValor;
	public static volatile SingularAttribute<TuSeguimientos, String> maeTipoSeguimientoCodigo;
	public static volatile SingularAttribute<TuSeguimientos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuSeguimientos, Date> fechaSeguimiento;
	public static volatile SingularAttribute<TuSeguimientos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuSeguimientos, Integer> id;
	public static volatile SingularAttribute<TuSeguimientos, Integer> maeTipoSeguimientoId;
	public static volatile SingularAttribute<TuSeguimientos, GnUsuarios> notificadoGnUsuarioId;
	public static volatile SingularAttribute<TuSeguimientos, String> observacion;
	public static volatile SingularAttribute<TuSeguimientos, String> usuarioModifica;

	public static final String TU_TUTELA_ESTADOS_ID = "tuTutelaEstadosId";
	public static final String TU_ADJUNTOS_LIST = "tuAdjuntosList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String GESTOR_GN_USUARIO_ID = "gestorGnUsuarioId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_SEGUIMIENTO_VALOR = "maeTipoSeguimientoValor";
	public static final String MAE_TIPO_SEGUIMIENTO_CODIGO = "maeTipoSeguimientoCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_SEGUIMIENTO = "fechaSeguimiento";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_SEGUIMIENTO_ID = "maeTipoSeguimientoId";
	public static final String NOTIFICADO_GN_USUARIO_ID = "notificadoGnUsuarioId";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

