package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnRoles.class)
public abstract class GnRoles_ {

	public static volatile SingularAttribute<GnRoles, String> descripcion;
	public static volatile SingularAttribute<GnRoles, String> terminalModifica;
	public static volatile SingularAttribute<GnRoles, String> usuarioCrea;
	public static volatile ListAttribute<GnRoles, GnRolesUsuario> gnRolesUsuarioList;
	public static volatile SingularAttribute<GnRoles, String> terminalCrea;
	public static volatile SingularAttribute<GnRoles, Date> fechaHoraCrea;
	public static volatile ListAttribute<GnRoles, GnRolesPerfiles> gnRolesPerfilesList;
	public static volatile SingularAttribute<GnRoles, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GnRoles, Integer> id;
	public static volatile SingularAttribute<GnRoles, String> nombre;
	public static volatile SingularAttribute<GnRoles, String> usuarioModifica;
	public static volatile SingularAttribute<GnRoles, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String GN_ROLES_USUARIO_LIST = "gnRolesUsuarioList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String GN_ROLES_PERFILES_LIST = "gnRolesPerfilesList";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

