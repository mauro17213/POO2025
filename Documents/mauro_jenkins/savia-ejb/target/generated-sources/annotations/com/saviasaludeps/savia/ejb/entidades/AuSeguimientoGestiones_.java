package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuSeguimientoGestiones.class)
public abstract class AuSeguimientoGestiones_ {

	public static volatile SingularAttribute<AuSeguimientoGestiones, String> descripcion;
	public static volatile SingularAttribute<AuSeguimientoGestiones, Integer> tipo;
	public static volatile SingularAttribute<AuSeguimientoGestiones, String> maeEstadoSeguimientoGestionCodigo;
	public static volatile SingularAttribute<AuSeguimientoGestiones, Date> fechaHoraBorra;
	public static volatile SingularAttribute<AuSeguimientoGestiones, Integer> maeEstadoSeguimientoGestionId;
	public static volatile SingularAttribute<AuSeguimientoGestiones, String> maeMotivoSeguimientoValor;
	public static volatile SingularAttribute<AuSeguimientoGestiones, String> maeMotivoSeguimientoCodigo;
	public static volatile SingularAttribute<AuSeguimientoGestiones, String> usuarioBorra;
	public static volatile SingularAttribute<AuSeguimientoGestiones, String> maeEstadoSeguimientoGestionValor;
	public static volatile SingularAttribute<AuSeguimientoGestiones, String> terminalModifica;
	public static volatile ListAttribute<AuSeguimientoGestiones, AuSolicitudAdjuntos> auSolicitudAdjuntosList;
	public static volatile SingularAttribute<AuSeguimientoGestiones, String> usuarioCrea;
	public static volatile SingularAttribute<AuSeguimientoGestiones, String> terminalCrea;
	public static volatile SingularAttribute<AuSeguimientoGestiones, Boolean> borrado;
	public static volatile SingularAttribute<AuSeguimientoGestiones, Date> fechaEntregaPropuesta;
	public static volatile SingularAttribute<AuSeguimientoGestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuSeguimientoGestiones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuSeguimientoGestiones, String> terminalBorra;
	public static volatile SingularAttribute<AuSeguimientoGestiones, Integer> id;
	public static volatile SingularAttribute<AuSeguimientoGestiones, Date> fechaHoraEntregaReal;
	public static volatile SingularAttribute<AuSeguimientoGestiones, AuSeguimientos> auSeguimientosId;
	public static volatile SingularAttribute<AuSeguimientoGestiones, Integer> maeMotivoSeguimientoId;
	public static volatile SingularAttribute<AuSeguimientoGestiones, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO = "tipo";
	public static final String MAE_ESTADO_SEGUIMIENTO_GESTION_CODIGO = "maeEstadoSeguimientoGestionCodigo";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String MAE_ESTADO_SEGUIMIENTO_GESTION_ID = "maeEstadoSeguimientoGestionId";
	public static final String MAE_MOTIVO_SEGUIMIENTO_VALOR = "maeMotivoSeguimientoValor";
	public static final String MAE_MOTIVO_SEGUIMIENTO_CODIGO = "maeMotivoSeguimientoCodigo";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String MAE_ESTADO_SEGUIMIENTO_GESTION_VALOR = "maeEstadoSeguimientoGestionValor";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String AU_SOLICITUD_ADJUNTOS_LIST = "auSolicitudAdjuntosList";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String FECHA_ENTREGA_PROPUESTA = "fechaEntregaPropuesta";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String FECHA_HORA_ENTREGA_REAL = "fechaHoraEntregaReal";
	public static final String AU_SEGUIMIENTOS_ID = "auSeguimientosId";
	public static final String MAE_MOTIVO_SEGUIMIENTO_ID = "maeMotivoSeguimientoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

