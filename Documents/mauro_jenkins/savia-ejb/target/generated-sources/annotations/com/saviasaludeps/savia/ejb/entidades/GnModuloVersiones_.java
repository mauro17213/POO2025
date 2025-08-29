package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnModuloVersiones.class)
public abstract class GnModuloVersiones_ {

	public static volatile SingularAttribute<GnModuloVersiones, String> descripcion;
	public static volatile SingularAttribute<GnModuloVersiones, String> usuarioCrea;
	public static volatile SingularAttribute<GnModuloVersiones, String> terminalCrea;
	public static volatile SingularAttribute<GnModuloVersiones, GnModulos> gnModulosId;
	public static volatile SingularAttribute<GnModuloVersiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnModuloVersiones, Integer> id;
	public static volatile SingularAttribute<GnModuloVersiones, String> version;
	public static volatile SingularAttribute<GnModuloVersiones, Date> fechaVersion;

	public static final String DESCRIPCION = "descripcion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GN_MODULOS_ID = "gnModulosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String VERSION = "version";
	public static final String FECHA_VERSION = "fechaVersion";

}

