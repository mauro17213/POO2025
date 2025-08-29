package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFacturaTransacciones.class)
public abstract class CmFacturaTransacciones_ {

	public static volatile SingularAttribute<CmFacturaTransacciones, Integer> tipo;
	public static volatile SingularAttribute<CmFacturaTransacciones, Integer> estado;
	public static volatile SingularAttribute<CmFacturaTransacciones, Integer> respuestaCodigo;
	public static volatile SingularAttribute<CmFacturaTransacciones, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmFacturaTransacciones, Date> fechaHoraInicio;
	public static volatile SingularAttribute<CmFacturaTransacciones, Integer> id;
	public static volatile SingularAttribute<CmFacturaTransacciones, String> respuestaDescripcion;
	public static volatile SingularAttribute<CmFacturaTransacciones, Date> fechaHoraFin;

	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String RESPUESTA_CODIGO = "respuestaCodigo";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String RESPUESTA_DESCRIPCION = "respuestaDescripcion";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";

}

