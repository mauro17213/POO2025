package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmSincronizacionDetalles.class)
public abstract class CmSincronizacionDetalles_ {

	public static volatile SingularAttribute<CmSincronizacionDetalles, String> consecutivo;
	public static volatile SingularAttribute<CmSincronizacionDetalles, String> conceptoContable;
	public static volatile SingularAttribute<CmSincronizacionDetalles, BigDecimal> valorOperacion;
	public static volatile SingularAttribute<CmSincronizacionDetalles, String> usuarioCrea;
	public static volatile SingularAttribute<CmSincronizacionDetalles, String> terminalCrea;
	public static volatile SingularAttribute<CmSincronizacionDetalles, CmSincronizacionEncabezados> cmSincronizacionEncabezadosId;
	public static volatile SingularAttribute<CmSincronizacionDetalles, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmSincronizacionDetalles, Integer> id;
	public static volatile SingularAttribute<CmSincronizacionDetalles, Integer> idDetalles;
	public static volatile SingularAttribute<CmSincronizacionDetalles, String> codigoMunicipio;
	public static volatile SingularAttribute<CmSincronizacionDetalles, Integer> tipologia;

	public static final String CONSECUTIVO = "consecutivo";
	public static final String CONCEPTO_CONTABLE = "conceptoContable";
	public static final String VALOR_OPERACION = "valorOperacion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_SINCRONIZACION_ENCABEZADOS_ID = "cmSincronizacionEncabezadosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ID_DETALLES = "idDetalles";
	public static final String CODIGO_MUNICIPIO = "codigoMunicipio";
	public static final String TIPOLOGIA = "tipologia";

}

