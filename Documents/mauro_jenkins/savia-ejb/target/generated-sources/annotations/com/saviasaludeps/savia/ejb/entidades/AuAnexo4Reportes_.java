package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo4Reportes.class)
public abstract class AuAnexo4Reportes_ {

	public static volatile SingularAttribute<AuAnexo4Reportes, String> descripcion;
	public static volatile SingularAttribute<AuAnexo4Reportes, Integer> cantidadTotal;
	public static volatile SingularAttribute<AuAnexo4Reportes, Integer> estado;
	public static volatile SingularAttribute<AuAnexo4Reportes, String> archivo;
	public static volatile SingularAttribute<AuAnexo4Reportes, String> ruta;
	public static volatile SingularAttribute<AuAnexo4Reportes, Date> fechaFin;
	public static volatile SingularAttribute<AuAnexo4Reportes, Integer> usuarioCreaId;
	public static volatile SingularAttribute<AuAnexo4Reportes, Integer> cantidadProcesada;
	public static volatile SingularAttribute<AuAnexo4Reportes, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo4Reportes, Date> fechaInicio;
	public static volatile SingularAttribute<AuAnexo4Reportes, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo4Reportes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo4Reportes, Integer> id;

	public static final String DESCRIPCION = "descripcion";
	public static final String CANTIDAD_TOTAL = "cantidadTotal";
	public static final String ESTADO = "estado";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String FECHA_FIN = "fechaFin";
	public static final String USUARIO_CREA_ID = "usuarioCreaId";
	public static final String CANTIDAD_PROCESADA = "cantidadProcesada";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

