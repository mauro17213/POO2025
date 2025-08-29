package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VAsegPortabilidades.class)
public abstract class VAsegPortabilidades_ {

	public static volatile SingularAttribute<VAsegPortabilidades, String> contratoAfiliado;
	public static volatile SingularAttribute<VAsegPortabilidades, String> segundoNombre;
	public static volatile SingularAttribute<VAsegPortabilidades, Integer> cantidadProrrogas;
	public static volatile SingularAttribute<VAsegPortabilidades, String> ipsPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, Date> fechaNacimiento;
	public static volatile SingularAttribute<VAsegPortabilidades, String> municipioPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, Date> periodoFinal;
	public static volatile SingularAttribute<VAsegPortabilidades, String> segundoApellido;
	public static volatile SingularAttribute<VAsegPortabilidades, String> codigoMunicipioPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, Date> fechaRadicacion;
	public static volatile SingularAttribute<VAsegPortabilidades, String> telefono2Afiliado;
	public static volatile SingularAttribute<VAsegPortabilidades, String> codigoMunicipioAfiliacion;
	public static volatile SingularAttribute<VAsegPortabilidades, String> usuarioCrea;
	public static volatile SingularAttribute<VAsegPortabilidades, Date> fechaProrroga;
	public static volatile SingularAttribute<VAsegPortabilidades, String> telefono3Portabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, BigInteger> duracionPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, String> numeroDocumento;
	public static volatile SingularAttribute<VAsegPortabilidades, String> telefono4Portabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, Date> fechaCancelacion;
	public static volatile SingularAttribute<VAsegPortabilidades, String> primerNombre;
	public static volatile SingularAttribute<VAsegPortabilidades, String> primerApellido;
	public static volatile SingularAttribute<VAsegPortabilidades, String> descMigracion;
	public static volatile SingularAttribute<VAsegPortabilidades, String> direccionResidencia;
	public static volatile SingularAttribute<VAsegPortabilidades, String> departamentoAfiliacion;
	public static volatile SingularAttribute<VAsegPortabilidades, String> departamentoPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, String> ipsPrimariaSede;
	public static volatile SingularAttribute<VAsegPortabilidades, Integer> mesesAdicionProrroga;
	public static volatile SingularAttribute<VAsegPortabilidades, String> fichaSisben;
	public static volatile SingularAttribute<VAsegPortabilidades, Date> periodoInicial;
	public static volatile SingularAttribute<VAsegPortabilidades, Integer> radicadoPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, String> codigoDepartamentoPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, String> tipoDocumento;
	public static volatile SingularAttribute<VAsegPortabilidades, String> correoElectronicoPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, String> telefono1Afiliado;
	public static volatile SingularAttribute<VAsegPortabilidades, String> observacionAseguramiento;
	public static volatile SingularAttribute<VAsegPortabilidades, String> origenSolicitudPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, String> grupoPoblacional;
	public static volatile SingularAttribute<VAsegPortabilidades, String> estadoPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, String> codigoHabilitacionIpsPortabilidad;
	public static volatile SingularAttribute<VAsegPortabilidades, String> codigoDepartamentoAfiliacion;
	public static volatile SingularAttribute<VAsegPortabilidades, String> municipioAfiliacion;

	public static final String CONTRATO_AFILIADO = "contratoAfiliado";
	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String CANTIDAD_PRORROGAS = "cantidadProrrogas";
	public static final String IPS_PORTABILIDAD = "ipsPortabilidad";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String MUNICIPIO_PORTABILIDAD = "municipioPortabilidad";
	public static final String PERIODO_FINAL = "periodoFinal";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String CODIGO_MUNICIPIO_PORTABILIDAD = "codigoMunicipioPortabilidad";
	public static final String FECHA_RADICACION = "fechaRadicacion";
	public static final String TELEFONO2_AFILIADO = "telefono2Afiliado";
	public static final String CODIGO_MUNICIPIO_AFILIACION = "codigoMunicipioAfiliacion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_PRORROGA = "fechaProrroga";
	public static final String TELEFONO3_PORTABILIDAD = "telefono3Portabilidad";
	public static final String DURACION_PORTABILIDAD = "duracionPortabilidad";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String TELEFONO4_PORTABILIDAD = "telefono4Portabilidad";
	public static final String FECHA_CANCELACION = "fechaCancelacion";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String DESC_MIGRACION = "descMigracion";
	public static final String DIRECCION_RESIDENCIA = "direccionResidencia";
	public static final String DEPARTAMENTO_AFILIACION = "departamentoAfiliacion";
	public static final String DEPARTAMENTO_PORTABILIDAD = "departamentoPortabilidad";
	public static final String IPS_PRIMARIA_SEDE = "ipsPrimariaSede";
	public static final String MESES_ADICION_PRORROGA = "mesesAdicionProrroga";
	public static final String FICHA_SISBEN = "fichaSisben";
	public static final String PERIODO_INICIAL = "periodoInicial";
	public static final String RADICADO_PORTABILIDAD = "radicadoPortabilidad";
	public static final String CODIGO_DEPARTAMENTO_PORTABILIDAD = "codigoDepartamentoPortabilidad";
	public static final String TIPO_DOCUMENTO = "tipoDocumento";
	public static final String CORREO_ELECTRONICO_PORTABILIDAD = "correoElectronicoPortabilidad";
	public static final String TELEFONO1_AFILIADO = "telefono1Afiliado";
	public static final String OBSERVACION_ASEGURAMIENTO = "observacionAseguramiento";
	public static final String ORIGEN_SOLICITUD_PORTABILIDAD = "origenSolicitudPortabilidad";
	public static final String GRUPO_POBLACIONAL = "grupoPoblacional";
	public static final String ESTADO_PORTABILIDAD = "estadoPortabilidad";
	public static final String CODIGO_HABILITACION_IPS_PORTABILIDAD = "codigoHabilitacionIpsPortabilidad";
	public static final String CODIGO_DEPARTAMENTO_AFILIACION = "codigoDepartamentoAfiliacion";
	public static final String MUNICIPIO_AFILIACION = "municipioAfiliacion";

}

