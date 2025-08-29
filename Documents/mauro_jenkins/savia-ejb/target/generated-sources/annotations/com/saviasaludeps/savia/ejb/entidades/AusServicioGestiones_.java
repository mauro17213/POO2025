package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusServicioGestiones.class)
public abstract class AusServicioGestiones_ {

	public static volatile SingularAttribute<AusServicioGestiones, String> terminalModifica;
	public static volatile SingularAttribute<AusServicioGestiones, String> usuarioCrea;
	public static volatile SingularAttribute<AusServicioGestiones, String> maeEstadoCodigo;
	public static volatile SingularAttribute<AusServicioGestiones, String> terminalCrea;
	public static volatile SingularAttribute<AusServicioGestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusServicioGestiones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AusServicioGestiones, Integer> id;
	public static volatile SingularAttribute<AusServicioGestiones, Integer> maeEstadoId;
	public static volatile SingularAttribute<AusServicioGestiones, String> maeEstadoValor;
	public static volatile SingularAttribute<AusServicioGestiones, String> observacion;
	public static volatile SingularAttribute<AusServicioGestiones, String> usuarioModifica;
	public static volatile SingularAttribute<AusServicioGestiones, AusCasoServicios> ausServiciosId;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_ESTADO_CODIGO = "maeEstadoCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_ESTADO_ID = "maeEstadoId";
	public static final String MAE_ESTADO_VALOR = "maeEstadoValor";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String AUS_SERVICIOS_ID = "ausServiciosId";

}

