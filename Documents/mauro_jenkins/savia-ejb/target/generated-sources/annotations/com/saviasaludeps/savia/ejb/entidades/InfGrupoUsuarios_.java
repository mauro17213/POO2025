package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InfGrupoUsuarios.class)
public abstract class InfGrupoUsuarios_ {

	public static volatile SingularAttribute<InfGrupoUsuarios, String> usuarioCrea;
	public static volatile SingularAttribute<InfGrupoUsuarios, String> terminalCrea;
	public static volatile SingularAttribute<InfGrupoUsuarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<InfGrupoUsuarios, InfGrupos> infGruposId;
	public static volatile SingularAttribute<InfGrupoUsuarios, Integer> id;
	public static volatile SingularAttribute<InfGrupoUsuarios, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<InfGrupoUsuarios, Boolean> activo;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String INF_GRUPOS_ID = "infGruposId";
	public static final String ID = "id";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String ACTIVO = "activo";

}

