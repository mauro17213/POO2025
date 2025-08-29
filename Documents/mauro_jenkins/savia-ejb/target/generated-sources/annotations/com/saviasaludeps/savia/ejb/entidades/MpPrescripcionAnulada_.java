package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpPrescripcionAnulada.class)
public abstract class MpPrescripcionAnulada_ {

	public static volatile SingularAttribute<MpPrescripcionAnulada, Integer> estado;
	public static volatile SingularAttribute<MpPrescripcionAnulada, Integer> tipo;
	public static volatile SingularAttribute<MpPrescripcionAnulada, String> justificacion;
	public static volatile SingularAttribute<MpPrescripcionAnulada, String> numeroPrescripcion;
	public static volatile SingularAttribute<MpPrescripcionAnulada, MpPrescripciones> mpPrescripcionesId;
	public static volatile SingularAttribute<MpPrescripcionAnulada, String> usuarioSolicita;
	public static volatile SingularAttribute<MpPrescripcionAnulada, String> usuarioCrea;
	public static volatile SingularAttribute<MpPrescripcionAnulada, String> terminalCrea;
	public static volatile SingularAttribute<MpPrescripcionAnulada, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpPrescripcionAnulada, Integer> id;
	public static volatile SingularAttribute<MpPrescripcionAnulada, Date> fechaHoraSolicitud;
	public static volatile SingularAttribute<MpPrescripcionAnulada, String> observacion;
	public static volatile SingularAttribute<MpPrescripcionAnulada, String> usuarioAnula;
	public static volatile SingularAttribute<MpPrescripcionAnulada, Date> fechaHoraAnulacion;

	public static final String ESTADO = "estado";
	public static final String TIPO = "tipo";
	public static final String JUSTIFICACION = "justificacion";
	public static final String NUMERO_PRESCRIPCION = "numeroPrescripcion";
	public static final String MP_PRESCRIPCIONES_ID = "mpPrescripcionesId";
	public static final String USUARIO_SOLICITA = "usuarioSolicita";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String FECHA_HORA_SOLICITUD = "fechaHoraSolicitud";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_ANULA = "usuarioAnula";
	public static final String FECHA_HORA_ANULACION = "fechaHoraAnulacion";

}

