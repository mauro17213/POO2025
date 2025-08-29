package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuGrupoHistoricos.class)
public abstract class AuGrupoHistoricos_ {

	public static volatile SingularAttribute<AuGrupoHistoricos, AuGrupos> auGruposId;
	public static volatile SingularAttribute<AuGrupoHistoricos, String> terminalModifica;
	public static volatile SingularAttribute<AuGrupoHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<AuGrupoHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<AuGrupoHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuGrupoHistoricos, String> toString;
	public static volatile SingularAttribute<AuGrupoHistoricos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuGrupoHistoricos, Integer> id;
	public static volatile SingularAttribute<AuGrupoHistoricos, String> usuarioModifica;

	public static final String AU_GRUPOS_ID = "auGruposId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String TO_STRING = "toString";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

