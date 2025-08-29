package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegMaNovedades.class)
public abstract class AsegMaNovedades_ {

	public static volatile SingularAttribute<AsegMaNovedades, Boolean> reporteNormativo;
	public static volatile SingularAttribute<AsegMaNovedades, String> terminalMofica;
	public static volatile SingularAttribute<AsegMaNovedades, String> codigoNovedad;
	public static volatile SingularAttribute<AsegMaNovedades, String> codigoNovedadPadre;
	public static volatile SingularAttribute<AsegMaNovedades, Integer> codigoCausaNovedad;
	public static volatile SingularAttribute<AsegMaNovedades, String> usuarioCrea;
	public static volatile SingularAttribute<AsegMaNovedades, String> terminalCrea;
	public static volatile SingularAttribute<AsegMaNovedades, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegMaNovedades, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AsegMaNovedades, Integer> id;
	public static volatile ListAttribute<AsegMaNovedades, AsegRegistroNovedades> asegRegistroNovedadesList;
	public static volatile SingularAttribute<AsegMaNovedades, Boolean> sincronizacionSomosMas;
	public static volatile SingularAttribute<AsegMaNovedades, String> descripcionNovedad;
	public static volatile SingularAttribute<AsegMaNovedades, Integer> regimen;
	public static volatile SingularAttribute<AsegMaNovedades, String> usuarioModifica;
	public static volatile SingularAttribute<AsegMaNovedades, Boolean> activo;

	public static final String REPORTE_NORMATIVO = "reporteNormativo";
	public static final String TERMINAL_MOFICA = "terminalMofica";
	public static final String CODIGO_NOVEDAD = "codigoNovedad";
	public static final String CODIGO_NOVEDAD_PADRE = "codigoNovedadPadre";
	public static final String CODIGO_CAUSA_NOVEDAD = "codigoCausaNovedad";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ASEG_REGISTRO_NOVEDADES_LIST = "asegRegistroNovedadesList";
	public static final String SINCRONIZACION_SOMOS_MAS = "sincronizacionSomosMas";
	public static final String DESCRIPCION_NOVEDAD = "descripcionNovedad";
	public static final String REGIMEN = "regimen";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

