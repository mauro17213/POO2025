package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnCorreoEnvios.class)
public abstract class GnCorreoEnvios_ {

	public static volatile SingularAttribute<GnCorreoEnvios, String> adjunto2;
	public static volatile SingularAttribute<GnCorreoEnvios, String> adjunto1;
	public static volatile SingularAttribute<GnCorreoEnvios, Integer> estado;
	public static volatile SingularAttribute<GnCorreoEnvios, Integer> tipo;
	public static volatile SingularAttribute<GnCorreoEnvios, String> encabezado;
	public static volatile SingularAttribute<GnCorreoEnvios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnCorreoEnvios, Integer> id;
	public static volatile SingularAttribute<GnCorreoEnvios, Integer> origen;
	public static volatile SingularAttribute<GnCorreoEnvios, String> destino;
	public static volatile SingularAttribute<GnCorreoEnvios, Date> fechaHoraEnvio;
	public static volatile SingularAttribute<GnCorreoEnvios, String> detalle;
	public static volatile SingularAttribute<GnCorreoEnvios, String> adjunto3;

	public static final String ADJUNTO2 = "adjunto2";
	public static final String ADJUNTO1 = "adjunto1";
	public static final String ESTADO = "estado";
	public static final String TIPO = "tipo";
	public static final String ENCABEZADO = "encabezado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ORIGEN = "origen";
	public static final String DESTINO = "destino";
	public static final String FECHA_HORA_ENVIO = "fechaHoraEnvio";
	public static final String DETALLE = "detalle";
	public static final String ADJUNTO3 = "adjunto3";

}

