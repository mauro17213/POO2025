package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnMaestroRelaciones.class)
public abstract class GnMaestroRelaciones_ {

	public static volatile SingularAttribute<GnMaestroRelaciones, String> usuarioCrea;
	public static volatile SingularAttribute<GnMaestroRelaciones, String> terminalCrea;
	public static volatile SingularAttribute<GnMaestroRelaciones, GnMaestros> gnMaestrosIdPadre;
	public static volatile SingularAttribute<GnMaestroRelaciones, GnMaestros> gnMaestrosIdHijo;
	public static volatile SingularAttribute<GnMaestroRelaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnMaestroRelaciones, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GN_MAESTROS_ID_PADRE = "gnMaestrosIdPadre";
	public static final String GN_MAESTROS_ID_HIJO = "gnMaestrosIdHijo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

