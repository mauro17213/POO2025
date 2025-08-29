package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuGrupoHistoricos.class)
public abstract class TuGrupoHistoricos_ {

	public static volatile SingularAttribute<TuGrupoHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<TuGrupoHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<TuGrupoHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuGrupoHistoricos, String> toString;
	public static volatile SingularAttribute<TuGrupoHistoricos, Integer> id;
	public static volatile SingularAttribute<TuGrupoHistoricos, TuGrupos> tuGruposId;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String TO_STRING = "toString";
	public static final String ID = "id";
	public static final String TU_GRUPOS_ID = "tuGruposId";

}

