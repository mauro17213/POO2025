package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmInformes.class)
public abstract class CmInformes_ {

	public static volatile SingularAttribute<CmInformes, Integer> tipo;
	public static volatile SingularAttribute<CmInformes, Integer> estado;
	public static volatile SingularAttribute<CmInformes, String> usuarioCrea;
	public static volatile SingularAttribute<CmInformes, Date> fechaHasta;
	public static volatile SingularAttribute<CmInformes, String> archivo;
	public static volatile SingularAttribute<CmInformes, Date> fechaDesde;
	public static volatile SingularAttribute<CmInformes, String> ruta;
	public static volatile SingularAttribute<CmInformes, Integer> registros;
	public static volatile SingularAttribute<CmInformes, Date> fechaHoraInicio;
	public static volatile SingularAttribute<CmInformes, Integer> id;
	public static volatile SingularAttribute<CmInformes, Date> fechaHoraFin;
	public static volatile SingularAttribute<CmInformes, String> observacion;

	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_HASTA = "fechaHasta";
	public static final String ARCHIVO = "archivo";
	public static final String FECHA_DESDE = "fechaDesde";
	public static final String RUTA = "ruta";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String OBSERVACION = "observacion";

}

