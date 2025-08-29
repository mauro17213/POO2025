package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegEncuestaPreguntas.class)
public abstract class AsegEncuestaPreguntas_ {

	public static volatile SingularAttribute<AsegEncuestaPreguntas, Boolean> estado;
	public static volatile SingularAttribute<AsegEncuestaPreguntas, String> usuarioCrea;
	public static volatile SingularAttribute<AsegEncuestaPreguntas, String> terminalCrea;
	public static volatile SingularAttribute<AsegEncuestaPreguntas, String> norma;
	public static volatile ListAttribute<AsegEncuestaPreguntas, AsegTabulacionEncuestas> asegTabulacionEncuestasList;
	public static volatile SingularAttribute<AsegEncuestaPreguntas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegEncuestaPreguntas, Integer> id;
	public static volatile SingularAttribute<AsegEncuestaPreguntas, String> pregunta;

	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String NORMA = "norma";
	public static final String ASEG_TABULACION_ENCUESTAS_LIST = "asegTabulacionEncuestasList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String PREGUNTA = "pregunta";

}

