package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnMaestroAccionRelaciones.class)
public abstract class GnMaestroAccionRelaciones_ {

	public static volatile SingularAttribute<GnMaestroAccionRelaciones, String> usuarioCrea;
	public static volatile SingularAttribute<GnMaestroAccionRelaciones, String> terminalCrea;
	public static volatile SingularAttribute<GnMaestroAccionRelaciones, GnMaestros> gnMaestrosId;
	public static volatile SingularAttribute<GnMaestroAccionRelaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnMaestroAccionRelaciones, Integer> id;
	public static volatile SingularAttribute<GnMaestroAccionRelaciones, GnMaestroAcciones> gnMaestroAccionesId;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GN_MAESTROS_ID = "gnMaestrosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String GN_MAESTRO_ACCIONES_ID = "gnMaestroAccionesId";

}

