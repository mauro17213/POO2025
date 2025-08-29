package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegTabulacionEncuestas.class)
public abstract class AsegTabulacionEncuestas_ {

	public static volatile SingularAttribute<AsegTabulacionEncuestas, String> segundoNombre;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, String> primerApellido;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, String> primerNombre;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, Date> fechaNacimiento;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, Integer> ubicacionesId;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, String> segundoApellido;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, String> terminalModifica;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, String> usuarioCrea;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, String> terminalCrea;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, AsegEncuestaPreguntas> asegEncuestaPreguntasId;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, Integer> id;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, Boolean> respuesta;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, String> observacion;
	public static volatile SingularAttribute<AsegTabulacionEncuestas, String> usuarioModifica;

	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String UBICACIONES_ID = "ubicacionesId";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ASEG_ENCUESTA_PREGUNTAS_ID = "asegEncuestaPreguntasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String RESPUESTA = "respuesta";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

