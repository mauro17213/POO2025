package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegReporteNovedades.class)
public abstract class AsegReporteNovedades_ {

	public static volatile SingularAttribute<AsegReporteNovedades, String> segundoNombre;
	public static volatile SingularAttribute<AsegReporteNovedades, Date> fechaNacimiento;
	public static volatile SingularAttribute<AsegReporteNovedades, String> valor1;
	public static volatile SingularAttribute<AsegReporteNovedades, String> segundoApellido;
	public static volatile SingularAttribute<AsegReporteNovedades, String> valor3;
	public static volatile SingularAttribute<AsegReporteNovedades, String> valor2;
	public static volatile SingularAttribute<AsegReporteNovedades, Date> fechaReporte;
	public static volatile SingularAttribute<AsegReporteNovedades, String> terminalModifica;
	public static volatile SingularAttribute<AsegReporteNovedades, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AsegReporteNovedades, String> usuarioCrea;
	public static volatile SingularAttribute<AsegReporteNovedades, String> valor5;
	public static volatile SingularAttribute<AsegReporteNovedades, String> valor4;
	public static volatile SingularAttribute<AsegReporteNovedades, String> terminalCrea;
	public static volatile SingularAttribute<AsegReporteNovedades, String> valor6;
	public static volatile SingularAttribute<AsegReporteNovedades, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegReporteNovedades, Integer> id;
	public static volatile SingularAttribute<AsegReporteNovedades, String> codigoEps;
	public static volatile SingularAttribute<AsegReporteNovedades, String> numeroDocumento;
	public static volatile ListAttribute<AsegReporteNovedades, AsegRegistroNovedades> asegRegistroNovedadesList;
	public static volatile SingularAttribute<AsegReporteNovedades, Date> fechaNovedad;
	public static volatile SingularAttribute<AsegReporteNovedades, String> codigoDepartamento;
	public static volatile SingularAttribute<AsegReporteNovedades, String> primerApellido;
	public static volatile SingularAttribute<AsegReporteNovedades, String> primerNombre;
	public static volatile SingularAttribute<AsegReporteNovedades, String> codigoNovedad;
	public static volatile SingularAttribute<AsegReporteNovedades, String> tipoDocumento;
	public static volatile SingularAttribute<AsegReporteNovedades, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AsegReporteNovedades, String> codigoMunicipio;
	public static volatile SingularAttribute<AsegReporteNovedades, String> usuarioModifica;

	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String VALOR1 = "valor1";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String VALOR3 = "valor3";
	public static final String VALOR2 = "valor2";
	public static final String FECHA_REPORTE = "fechaReporte";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String VALOR5 = "valor5";
	public static final String VALOR4 = "valor4";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR6 = "valor6";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CODIGO_EPS = "codigoEps";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String ASEG_REGISTRO_NOVEDADES_LIST = "asegRegistroNovedadesList";
	public static final String FECHA_NOVEDAD = "fechaNovedad";
	public static final String CODIGO_DEPARTAMENTO = "codigoDepartamento";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String CODIGO_NOVEDAD = "codigoNovedad";
	public static final String TIPO_DOCUMENTO = "tipoDocumento";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CODIGO_MUNICIPIO = "codigoMunicipio";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

