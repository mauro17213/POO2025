package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GsSolicitudes.class)
public abstract class GsSolicitudes_ {

	public static volatile SingularAttribute<GsSolicitudes, String> descripcion;
	public static volatile SingularAttribute<GsSolicitudes, Integer> tipo;
	public static volatile SingularAttribute<GsSolicitudes, Integer> estado;
	public static volatile SingularAttribute<GsSolicitudes, String> contactoCelular;
	public static volatile SingularAttribute<GsSolicitudes, GsAfiliados> gsAfiliadosId;
	public static volatile ListAttribute<GsSolicitudes, GsGestiones> gsGestionesList;
	public static volatile SingularAttribute<GsSolicitudes, String> nombre;
	public static volatile SingularAttribute<GsSolicitudes, String> contactoTelefono;
	public static volatile ListAttribute<GsSolicitudes, GsAdjuntos> gsAdjuntosList;
	public static volatile SingularAttribute<GsSolicitudes, String> usuarioFinaliza;
	public static volatile SingularAttribute<GsSolicitudes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GsSolicitudes, Integer> id;
	public static volatile SingularAttribute<GsSolicitudes, Date> fechaHoraFinaliza;
	public static volatile SingularAttribute<GsSolicitudes, String> observacion;
	public static volatile SingularAttribute<GsSolicitudes, String> tramiteInterno;
	public static volatile SingularAttribute<GsSolicitudes, String> respuestaReferencia;
	public static volatile SingularAttribute<GsSolicitudes, String> contactoCorreoElectronico;
	public static volatile SingularAttribute<GsSolicitudes, String> usuarioAtiende;
	public static volatile SingularAttribute<GsSolicitudes, Integer> notificacion;
	public static volatile SingularAttribute<GsSolicitudes, Date> fechaHoraReasigna;
	public static volatile SingularAttribute<GsSolicitudes, GnUsuarios> usuariosId;
	public static volatile SingularAttribute<GsSolicitudes, String> usuarioReasigna;
	public static volatile SingularAttribute<GsSolicitudes, GsZonas> gsZonasId;
	public static volatile ListAttribute<GsSolicitudes, GsNotificaciones> gsNotificacionesList;
	public static volatile SingularAttribute<GsSolicitudes, GsMensajes> gsMensajesId;
	public static volatile SingularAttribute<GsSolicitudes, Date> fechaHoraAtiende;

	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String CONTACTO_CELULAR = "contactoCelular";
	public static final String GS_AFILIADOS_ID = "gsAfiliadosId";
	public static final String GS_GESTIONES_LIST = "gsGestionesList";
	public static final String NOMBRE = "nombre";
	public static final String CONTACTO_TELEFONO = "contactoTelefono";
	public static final String GS_ADJUNTOS_LIST = "gsAdjuntosList";
	public static final String USUARIO_FINALIZA = "usuarioFinaliza";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String FECHA_HORA_FINALIZA = "fechaHoraFinaliza";
	public static final String OBSERVACION = "observacion";
	public static final String TRAMITE_INTERNO = "tramiteInterno";
	public static final String RESPUESTA_REFERENCIA = "respuestaReferencia";
	public static final String CONTACTO_CORREO_ELECTRONICO = "contactoCorreoElectronico";
	public static final String USUARIO_ATIENDE = "usuarioAtiende";
	public static final String NOTIFICACION = "notificacion";
	public static final String FECHA_HORA_REASIGNA = "fechaHoraReasigna";
	public static final String USUARIOS_ID = "usuariosId";
	public static final String USUARIO_REASIGNA = "usuarioReasigna";
	public static final String GS_ZONAS_ID = "gsZonasId";
	public static final String GS_NOTIFICACIONES_LIST = "gsNotificacionesList";
	public static final String GS_MENSAJES_ID = "gsMensajesId";
	public static final String FECHA_HORA_ATIENDE = "fechaHoraAtiende";

}

