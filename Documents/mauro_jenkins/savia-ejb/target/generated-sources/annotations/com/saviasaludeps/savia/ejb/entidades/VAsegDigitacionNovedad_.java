package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VAsegDigitacionNovedad.class)
public abstract class VAsegDigitacionNovedad_ {

	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> contratoAfiliado;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> segundoNombre;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> primerApellido;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> primerNombre;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, Date> fechaNacimiento;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, Date> fechaDigitracion;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> descripcionMunicipioAfiliacion;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> segundoApellido;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, BigInteger> codigoNovedad;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> valorAnterior;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> usuarioDigita;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, Integer> radicadoNovedad;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> codigoMunicipioAfiliacion;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> valorNuevo;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, Integer> idNovedad;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, Date> fechaNovedad;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> descripcionNovedad;
	public static volatile SingularAttribute<VAsegDigitacionNovedad, String> codigoDepartamentoAfiliacion;

	public static final String CONTRATO_AFILIADO = "contratoAfiliado";
	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String FECHA_DIGITRACION = "fechaDigitracion";
	public static final String DESCRIPCION_MUNICIPIO_AFILIACION = "descripcionMunicipioAfiliacion";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String CODIGO_NOVEDAD = "codigoNovedad";
	public static final String VALOR_ANTERIOR = "valorAnterior";
	public static final String USUARIO_DIGITA = "usuarioDigita";
	public static final String RADICADO_NOVEDAD = "radicadoNovedad";
	public static final String CODIGO_MUNICIPIO_AFILIACION = "codigoMunicipioAfiliacion";
	public static final String VALOR_NUEVO = "valorNuevo";
	public static final String ID_NOVEDAD = "idNovedad";
	public static final String FECHA_NOVEDAD = "fechaNovedad";
	public static final String DESCRIPCION_NOVEDAD = "descripcionNovedad";
	public static final String CODIGO_DEPARTAMENTO_AFILIACION = "codigoDepartamentoAfiliacion";

}

