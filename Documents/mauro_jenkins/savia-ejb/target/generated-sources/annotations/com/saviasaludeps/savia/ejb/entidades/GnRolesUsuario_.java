package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnRolesUsuario.class)
public abstract class GnRolesUsuario_ {

	public static volatile SingularAttribute<GnRolesUsuario, String> usuarioCrea;
	public static volatile SingularAttribute<GnRolesUsuario, String> terminalCrea;
	public static volatile SingularAttribute<GnRolesUsuario, GnRoles> gnRolesId;
	public static volatile SingularAttribute<GnRolesUsuario, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnRolesUsuario, Integer> id;
	public static volatile SingularAttribute<GnRolesUsuario, GnUsuarios> gnUsuariosId;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GN_ROLES_ID = "gnRolesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";

}

