package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmAuditoriaAdjuntos.class)
public abstract class CmAuditoriaAdjuntos_ {

	public static volatile SingularAttribute<CmAuditoriaAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<CmAuditoriaAdjuntos, String> archivoNombre;
	public static volatile SingularAttribute<CmAuditoriaAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<CmAuditoriaAdjuntos, String> archivoRuta;
	public static volatile SingularAttribute<CmAuditoriaAdjuntos, CmDetalles> cmDetallesId;
	public static volatile SingularAttribute<CmAuditoriaAdjuntos, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmAuditoriaAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmAuditoriaAdjuntos, Integer> id;
	public static volatile SingularAttribute<CmAuditoriaAdjuntos, Integer> archivoTipo;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String CM_DETALLES_ID = "cmDetallesId";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ARCHIVO_TIPO = "archivoTipo";

}

