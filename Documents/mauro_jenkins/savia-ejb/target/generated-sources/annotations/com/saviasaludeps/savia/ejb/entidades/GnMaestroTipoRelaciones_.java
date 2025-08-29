package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnMaestroTipoRelaciones.class)
public abstract class GnMaestroTipoRelaciones_ {

	public static volatile SingularAttribute<GnMaestroTipoRelaciones, String> usuarioCrea;
	public static volatile SingularAttribute<GnMaestroTipoRelaciones, String> terminalCrea;
	public static volatile SingularAttribute<GnMaestroTipoRelaciones, GnMaestroTipos> gnMaestroTiposTipoPadre;
	public static volatile SingularAttribute<GnMaestroTipoRelaciones, GnMaestroTipos> gnMaestroTiposTipoHijo;
	public static volatile SingularAttribute<GnMaestroTipoRelaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnMaestroTipoRelaciones, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GN_MAESTRO_TIPOS_TIPO_PADRE = "gnMaestroTiposTipoPadre";
	public static final String GN_MAESTRO_TIPOS_TIPO_HIJO = "gnMaestroTiposTipoHijo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

