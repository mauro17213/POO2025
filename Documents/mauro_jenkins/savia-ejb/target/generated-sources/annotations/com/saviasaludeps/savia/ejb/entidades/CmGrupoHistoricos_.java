package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmGrupoHistoricos.class)
public abstract class CmGrupoHistoricos_ {

	public static volatile SingularAttribute<CmGrupoHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<CmGrupoHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<CmGrupoHistoricos, CmGrupos> cmGruposId;
	public static volatile SingularAttribute<CmGrupoHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmGrupoHistoricos, String> toString;
	public static volatile SingularAttribute<CmGrupoHistoricos, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_GRUPOS_ID = "cmGruposId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String TO_STRING = "toString";
	public static final String ID = "id";

}

