package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegInformes.class)
public abstract class AsegInformes_ {

	public static volatile SingularAttribute<AsegInformes, Integer> tipo;
	public static volatile SingularAttribute<AsegInformes, Integer> estado;
	public static volatile SingularAttribute<AsegInformes, Date> fechaHasta;
	public static volatile SingularAttribute<AsegInformes, String> archivo;
	public static volatile SingularAttribute<AsegInformes, Date> fechaDesde;
	public static volatile SingularAttribute<AsegInformes, String> ruta;
	public static volatile SingularAttribute<AsegInformes, Integer> registros;
	public static volatile SingularAttribute<AsegInformes, Date> fechaHoraInicio;
	public static volatile SingularAttribute<AsegInformes, String> usuarioCrea;
	public static volatile SingularAttribute<AsegInformes, String> terminalCrea;
	public static volatile SingularAttribute<AsegInformes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegInformes, Integer> id;
	public static volatile SingularAttribute<AsegInformes, Date> fechaHoraFin;
	public static volatile SingularAttribute<AsegInformes, String> observacion;

	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String FECHA_HASTA = "fechaHasta";
	public static final String ARCHIVO = "archivo";
	public static final String FECHA_DESDE = "fechaDesde";
	public static final String RUTA = "ruta";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String OBSERVACION = "observacion";

}

