package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnPerfiles.class)
public abstract class GnPerfiles_ {

	public static volatile SingularAttribute<GnPerfiles, String> descripcion;
	public static volatile ListAttribute<GnPerfiles, GnPermisos> gnPermisosList;
	public static volatile SingularAttribute<GnPerfiles, String> nombre;
	public static volatile SingularAttribute<GnPerfiles, String> terminalModifica;
	public static volatile SingularAttribute<GnPerfiles, String> usuarioCrea;
	public static volatile SingularAttribute<GnPerfiles, String> terminalCrea;
	public static volatile SingularAttribute<GnPerfiles, Date> fechaHoraCrea;
	public static volatile ListAttribute<GnPerfiles, GnRolesPerfiles> gnRolesPerfilesList;
	public static volatile SingularAttribute<GnPerfiles, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GnPerfiles, Integer> id;
	public static volatile ListAttribute<GnPerfiles, GnMaestroTipos> gnMaestroTiposList;
	public static volatile SingularAttribute<GnPerfiles, String> usuarioModifica;
	public static volatile SingularAttribute<GnPerfiles, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String GN_PERMISOS_LIST = "gnPermisosList";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String GN_ROLES_PERFILES_LIST = "gnRolesPerfilesList";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String GN_MAESTRO_TIPOS_LIST = "gnMaestroTiposList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

