package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatConfiguraccionAnuncios.class)
public abstract class GatConfiguraccionAnuncios_ {

	public static volatile SingularAttribute<GatConfiguraccionAnuncios, String> descripcion;
	public static volatile SingularAttribute<GatConfiguraccionAnuncios, String> usuarioCrea;
	public static volatile SingularAttribute<GatConfiguraccionAnuncios, Date> fechaHasta;
	public static volatile SingularAttribute<GatConfiguraccionAnuncios, Date> fechaDesde;
	public static volatile SingularAttribute<GatConfiguraccionAnuncios, String> terminalCrea;
	public static volatile SingularAttribute<GatConfiguraccionAnuncios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatConfiguraccionAnuncios, Integer> id;
	public static volatile SingularAttribute<GatConfiguraccionAnuncios, String> nombre;
	public static volatile SingularAttribute<GatConfiguraccionAnuncios, String> url;

	public static final String DESCRIPCION = "descripcion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_HASTA = "fechaHasta";
	public static final String FECHA_DESDE = "fechaDesde";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String URL = "url";

}

