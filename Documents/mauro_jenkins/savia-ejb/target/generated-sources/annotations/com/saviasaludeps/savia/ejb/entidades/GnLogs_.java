package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnLogs.class)
public abstract class GnLogs_ {

	public static volatile SingularAttribute<GnLogs, String> accion;
	public static volatile SingularAttribute<GnLogs, String> descripcion;
	public static volatile SingularAttribute<GnLogs, String> opcion;
	public static volatile SingularAttribute<GnLogs, String> empresaUsuario;
	public static volatile SingularAttribute<GnLogs, Date> fechaHora;
	public static volatile SingularAttribute<GnLogs, String> ipPrivada;
	public static volatile SingularAttribute<GnLogs, String> ipPublica;
	public static volatile SingularAttribute<GnLogs, String> usuario;
	public static volatile SingularAttribute<GnLogs, Integer> id;
	public static volatile SingularAttribute<GnLogs, String> modulo;
	public static volatile SingularAttribute<GnLogs, String> empresaEjecucion;

	public static final String ACCION = "accion";
	public static final String DESCRIPCION = "descripcion";
	public static final String OPCION = "opcion";
	public static final String EMPRESA_USUARIO = "empresaUsuario";
	public static final String FECHA_HORA = "fechaHora";
	public static final String IP_PRIVADA = "ipPrivada";
	public static final String IP_PUBLICA = "ipPublica";
	public static final String USUARIO = "usuario";
	public static final String ID = "id";
	public static final String MODULO = "modulo";
	public static final String EMPRESA_EJECUCION = "empresaEjecucion";

}

