package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpHomologaciones.class)
public abstract class MpHomologaciones_ {

	public static volatile SingularAttribute<MpHomologaciones, String> descripcion;
	public static volatile SingularAttribute<MpHomologaciones, Integer> tipo;
	public static volatile SingularAttribute<MpHomologaciones, String> codigo;
	public static volatile SingularAttribute<MpHomologaciones, String> usuarioCrea;
	public static volatile SingularAttribute<MpHomologaciones, String> terminalCrea;
	public static volatile SingularAttribute<MpHomologaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpHomologaciones, Integer> id;
	public static volatile SingularAttribute<MpHomologaciones, String> nombre;

	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO = "tipo";
	public static final String CODIGO = "codigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";

}

