package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GsAfiliados.class)
public abstract class GsAfiliados_ {

	public static volatile SingularAttribute<GsAfiliados, String> segundoNombre;
	public static volatile SingularAttribute<GsAfiliados, String> primerNombre;
	public static volatile SingularAttribute<GsAfiliados, String> primerApellido;
	public static volatile SingularAttribute<GsAfiliados, Date> fechaNacimiento;
	public static volatile SingularAttribute<GsAfiliados, String> residenciaUbicacionNombre;
	public static volatile SingularAttribute<GsAfiliados, String> documentoNumero;
	public static volatile SingularAttribute<GsAfiliados, String> residenciaDireccion;
	public static volatile SingularAttribute<GsAfiliados, String> segundoApellido;
	public static volatile SingularAttribute<GsAfiliados, String> residenciaZonaTipo;
	public static volatile ListAttribute<GsAfiliados, GsSolicitudes> gsSolicitudesList;
	public static volatile SingularAttribute<GsAfiliados, Date> documentoFechaExpedicion;
	public static volatile SingularAttribute<GsAfiliados, GnUbicaciones> atencionUbicacionesId;
	public static volatile SingularAttribute<GsAfiliados, String> atencionUbicacionNombre;
	public static volatile SingularAttribute<GsAfiliados, GnUbicaciones> residenciaUbicacionesId;
	public static volatile SingularAttribute<GsAfiliados, Integer> id;
	public static volatile SingularAttribute<GsAfiliados, String> documentoTipo;
	public static volatile SingularAttribute<GsAfiliados, String> sexo;

	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String RESIDENCIA_UBICACION_NOMBRE = "residenciaUbicacionNombre";
	public static final String DOCUMENTO_NUMERO = "documentoNumero";
	public static final String RESIDENCIA_DIRECCION = "residenciaDireccion";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String RESIDENCIA_ZONA_TIPO = "residenciaZonaTipo";
	public static final String GS_SOLICITUDES_LIST = "gsSolicitudesList";
	public static final String DOCUMENTO_FECHA_EXPEDICION = "documentoFechaExpedicion";
	public static final String ATENCION_UBICACIONES_ID = "atencionUbicacionesId";
	public static final String ATENCION_UBICACION_NOMBRE = "atencionUbicacionNombre";
	public static final String RESIDENCIA_UBICACIONES_ID = "residenciaUbicacionesId";
	public static final String ID = "id";
	public static final String DOCUMENTO_TIPO = "documentoTipo";
	public static final String SEXO = "sexo";

}

