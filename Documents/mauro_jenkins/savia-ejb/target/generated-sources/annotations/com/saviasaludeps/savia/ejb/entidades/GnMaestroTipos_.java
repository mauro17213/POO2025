package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnMaestroTipos.class)
public abstract class GnMaestroTipos_ {

	public static volatile ListAttribute<GnMaestroTipos, GnMaestros> gnMaestrosList;
	public static volatile SingularAttribute<GnMaestroTipos, String> tipo;
	public static volatile ListAttribute<GnMaestroTipos, GnMaestroTipoRelaciones> gnMaestroTipoRelacionesList1;
	public static volatile ListAttribute<GnMaestroTipos, GnMaestroTipoRelaciones> gnMaestroTipoRelacionesList;
	public static volatile SingularAttribute<GnMaestroTipos, String> nombre;
	public static volatile ListAttribute<GnMaestroTipos, GnValidacionCampos> gnValidacionCamposList;
	public static volatile SingularAttribute<GnMaestroTipos, String> terminalModifica;
	public static volatile ListAttribute<GnMaestroTipos, GnMaestroAcciones> gnMaestroAccionesList;
	public static volatile SingularAttribute<GnMaestroTipos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnMaestroTipos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GnMaestroTipos, GnPerfiles> gnPerfilesId;
	public static volatile SingularAttribute<GnMaestroTipos, String> usuarioModifica;
	public static volatile SingularAttribute<GnMaestroTipos, Boolean> activo;

	public static final String GN_MAESTROS_LIST = "gnMaestrosList";
	public static final String TIPO = "tipo";
	public static final String GN_MAESTRO_TIPO_RELACIONES_LIST1 = "gnMaestroTipoRelacionesList1";
	public static final String GN_MAESTRO_TIPO_RELACIONES_LIST = "gnMaestroTipoRelacionesList";
	public static final String NOMBRE = "nombre";
	public static final String GN_VALIDACION_CAMPOS_LIST = "gnValidacionCamposList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String GN_MAESTRO_ACCIONES_LIST = "gnMaestroAccionesList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String GN_PERFILES_ID = "gnPerfilesId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

