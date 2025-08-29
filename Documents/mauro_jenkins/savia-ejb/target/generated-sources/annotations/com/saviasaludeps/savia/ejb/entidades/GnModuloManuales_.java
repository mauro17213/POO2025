package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnModuloManuales.class)
public abstract class GnModuloManuales_ {

	public static volatile SingularAttribute<GnModuloManuales, String> descripcion;
	public static volatile SingularAttribute<GnModuloManuales, Boolean> actual;
	public static volatile SingularAttribute<GnModuloManuales, Integer> tipo;
	public static volatile SingularAttribute<GnModuloManuales, String> archivo;
	public static volatile SingularAttribute<GnModuloManuales, String> ruta;
	public static volatile SingularAttribute<GnModuloManuales, String> version;
	public static volatile SingularAttribute<GnModuloManuales, String> nombre;
	public static volatile SingularAttribute<GnModuloManuales, Date> fechaVersion;
	public static volatile SingularAttribute<GnModuloManuales, String> usuarioCrea;
	public static volatile SingularAttribute<GnModuloManuales, String> terminalCrea;
	public static volatile SingularAttribute<GnModuloManuales, GnModulos> gnModulosId;
	public static volatile SingularAttribute<GnModuloManuales, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnModuloManuales, Integer> id;

	public static final String DESCRIPCION = "descripcion";
	public static final String ACTUAL = "actual";
	public static final String TIPO = "tipo";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String VERSION = "version";
	public static final String NOMBRE = "nombre";
	public static final String FECHA_VERSION = "fechaVersion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GN_MODULOS_ID = "gnModulosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

