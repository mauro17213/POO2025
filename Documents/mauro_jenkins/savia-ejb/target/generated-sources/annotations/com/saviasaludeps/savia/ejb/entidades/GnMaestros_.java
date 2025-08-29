package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnMaestros.class)
public abstract class GnMaestros_ {

	public static volatile SingularAttribute<GnMaestros, String> descripcion;
	public static volatile ListAttribute<GnMaestros, GnMaestroRelaciones> gnMaestroRelacionesList1;
	public static volatile ListAttribute<GnMaestros, GnMaestroRelaciones> gnMaestroRelacionesList;
	public static volatile SingularAttribute<GnMaestros, GnMaestroTipos> tipo;
	public static volatile SingularAttribute<GnMaestros, String> valor;
	public static volatile SingularAttribute<GnMaestros, String> nombre;
	public static volatile SingularAttribute<GnMaestros, String> terminalModifica;
	public static volatile SingularAttribute<GnMaestros, String> usuarioCrea;
	public static volatile SingularAttribute<GnMaestros, String> terminalCrea;
	public static volatile SingularAttribute<GnMaestros, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnMaestros, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GnMaestros, Integer> id;
	public static volatile SingularAttribute<GnMaestros, Integer> orden;
	public static volatile ListAttribute<GnMaestros, GnMaestroAccionRelaciones> gnMaestroAccionRelacionesList;
	public static volatile SingularAttribute<GnMaestros, GnValidacionCampos> gnValidacionCamposId;
	public static volatile SingularAttribute<GnMaestros, String> usuarioModifica;
	public static volatile SingularAttribute<GnMaestros, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String GN_MAESTRO_RELACIONES_LIST1 = "gnMaestroRelacionesList1";
	public static final String GN_MAESTRO_RELACIONES_LIST = "gnMaestroRelacionesList";
	public static final String TIPO = "tipo";
	public static final String VALOR = "valor";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ORDEN = "orden";
	public static final String GN_MAESTRO_ACCION_RELACIONES_LIST = "gnMaestroAccionRelacionesList";
	public static final String GN_VALIDACION_CAMPOS_ID = "gnValidacionCamposId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

