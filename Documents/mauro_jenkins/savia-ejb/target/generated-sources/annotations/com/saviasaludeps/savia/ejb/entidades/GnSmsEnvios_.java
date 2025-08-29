package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnSmsEnvios.class)
public abstract class GnSmsEnvios_ {

	public static volatile SingularAttribute<GnSmsEnvios, String> texto;
	public static volatile SingularAttribute<GnSmsEnvios, Integer> estado;
	public static volatile SingularAttribute<GnSmsEnvios, String> celulares;
	public static volatile SingularAttribute<GnSmsEnvios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnSmsEnvios, Integer> id;
	public static volatile SingularAttribute<GnSmsEnvios, Integer> origen;
	public static volatile SingularAttribute<GnSmsEnvios, Date> fechaHoraEnvio;

	public static final String TEXTO = "texto";
	public static final String ESTADO = "estado";
	public static final String CELULARES = "celulares";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ORIGEN = "origen";
	public static final String FECHA_HORA_ENVIO = "fechaHoraEnvio";

}

