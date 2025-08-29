package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusSolicitudes.class)
public abstract class AusSolicitudes_ {

	public static volatile SingularAttribute<AusSolicitudes, String> descripcion;
	public static volatile ListAttribute<AusSolicitudes, AusAdjuntos> ausAdjuntosList;
	public static volatile SingularAttribute<AusSolicitudes, String> nombres;
	public static volatile SingularAttribute<AusSolicitudes, Integer> maeEstadoSolicitudId;
	public static volatile SingularAttribute<AusSolicitudes, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<AusSolicitudes, String> terminalModifica;
	public static volatile SingularAttribute<AusSolicitudes, String> usuarioCrea;
	public static volatile SingularAttribute<AusSolicitudes, String> terminalCrea;
	public static volatile SingularAttribute<AusSolicitudes, String> maeEstadoSolicitudValor;
	public static volatile SingularAttribute<AusSolicitudes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusSolicitudes, Integer> id;
	public static volatile SingularAttribute<AusSolicitudes, String> numeroDocumento;
	public static volatile SingularAttribute<AusSolicitudes, String> telefono;
	public static volatile SingularAttribute<AusSolicitudes, String> email;
	public static volatile SingularAttribute<AusSolicitudes, String> apellidos;
	public static volatile SingularAttribute<AusSolicitudes, Integer> maeTipoSolicitudId;
	public static volatile SingularAttribute<AusSolicitudes, AusCasos> ausCasosId;
	public static volatile SingularAttribute<AusSolicitudes, String> detalleEmail;
	public static volatile SingularAttribute<AusSolicitudes, String> maeTipoSolicitudValor;
	public static volatile SingularAttribute<AusSolicitudes, String> maeEstadoSolicitudCodigo;
	public static volatile SingularAttribute<AusSolicitudes, String> direccionNotificacion;
	public static volatile SingularAttribute<AusSolicitudes, String> maeTipoSolicitudCodigo;
	public static volatile SingularAttribute<AusSolicitudes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AusSolicitudes, Boolean> aplicaCaso;
	public static volatile SingularAttribute<AusSolicitudes, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<AusSolicitudes, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<AusSolicitudes, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String AUS_ADJUNTOS_LIST = "ausAdjuntosList";
	public static final String NOMBRES = "nombres";
	public static final String MAE_ESTADO_SOLICITUD_ID = "maeEstadoSolicitudId";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_ESTADO_SOLICITUD_VALOR = "maeEstadoSolicitudValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String TELEFONO = "telefono";
	public static final String EMAIL = "email";
	public static final String APELLIDOS = "apellidos";
	public static final String MAE_TIPO_SOLICITUD_ID = "maeTipoSolicitudId";
	public static final String AUS_CASOS_ID = "ausCasosId";
	public static final String DETALLE_EMAIL = "detalleEmail";
	public static final String MAE_TIPO_SOLICITUD_VALOR = "maeTipoSolicitudValor";
	public static final String MAE_ESTADO_SOLICITUD_CODIGO = "maeEstadoSolicitudCodigo";
	public static final String DIRECCION_NOTIFICACION = "direccionNotificacion";
	public static final String MAE_TIPO_SOLICITUD_CODIGO = "maeTipoSolicitudCodigo";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String APLICA_CASO = "aplicaCaso";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

