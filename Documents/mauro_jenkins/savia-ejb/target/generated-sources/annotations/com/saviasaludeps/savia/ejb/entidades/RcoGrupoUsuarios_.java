package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RcoGrupoUsuarios.class)
public abstract class RcoGrupoUsuarios_ {

	public static volatile SingularAttribute<RcoGrupoUsuarios, String> usuarioCrea;
	public static volatile SingularAttribute<RcoGrupoUsuarios, String> terminalCrea;
	public static volatile SingularAttribute<RcoGrupoUsuarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RcoGrupoUsuarios, RcoGrupos> rcoGruposId;
	public static volatile SingularAttribute<RcoGrupoUsuarios, Integer> id;
	public static volatile SingularAttribute<RcoGrupoUsuarios, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<RcoGrupoUsuarios, Boolean> activo;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String RCO_GRUPOS_ID = "rcoGruposId";
	public static final String ID = "id";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String ACTIVO = "activo";

}

