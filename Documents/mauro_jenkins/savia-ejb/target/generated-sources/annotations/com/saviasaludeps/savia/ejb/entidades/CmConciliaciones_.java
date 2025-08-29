package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmConciliaciones.class)
public abstract class CmConciliaciones_ {

	public static volatile SingularAttribute<CmConciliaciones, Date> horaFinalizacionRegistro;
	public static volatile SingularAttribute<CmConciliaciones, String> archivo;
	public static volatile SingularAttribute<CmConciliaciones, BigDecimal> valorAceptadoIps;
	public static volatile SingularAttribute<CmConciliaciones, String> archivoRuta;
	public static volatile SingularAttribute<CmConciliaciones, BigDecimal> valor;
	public static volatile ListAttribute<CmConciliaciones, CmRadicados> cmRadicadosList;
	public static volatile SingularAttribute<CmConciliaciones, Integer> cantidadFacturas;
	public static volatile SingularAttribute<CmConciliaciones, BigDecimal> porcentajeAceptadoIps;
	public static volatile SingularAttribute<CmConciliaciones, BigDecimal> valorPagadoEps;
	public static volatile ListAttribute<CmConciliaciones, WsCmTransacciones> wsCmTransaccionesList;
	public static volatile SingularAttribute<CmConciliaciones, Integer> estadoProceso;
	public static volatile SingularAttribute<CmConciliaciones, String> usuarioCrea;
	public static volatile ListAttribute<CmConciliaciones, CmGlosaRespuestas> cmGlosaRespuestasList;
	public static volatile SingularAttribute<CmConciliaciones, BigDecimal> porcentajePagadoEps;
	public static volatile SingularAttribute<CmConciliaciones, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CmConciliaciones, Integer> cantidadFacturasRegistradas;
	public static volatile SingularAttribute<CmConciliaciones, String> archivoNombre;
	public static volatile SingularAttribute<CmConciliaciones, String> terminalCrea;
	public static volatile SingularAttribute<CmConciliaciones, Boolean> archivoExiste;
	public static volatile SingularAttribute<CmConciliaciones, Date> fechaHoraCrea;
	public static volatile ListAttribute<CmConciliaciones, CmSincronizaciones> cmSincronizacionesList;
	public static volatile SingularAttribute<CmConciliaciones, Integer> id;
	public static volatile SingularAttribute<CmConciliaciones, BigDecimal> porcentaje;

	public static final String HORA_FINALIZACION_REGISTRO = "horaFinalizacionRegistro";
	public static final String ARCHIVO = "archivo";
	public static final String VALOR_ACEPTADO_IPS = "valorAceptadoIps";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String VALOR = "valor";
	public static final String CM_RADICADOS_LIST = "cmRadicadosList";
	public static final String CANTIDAD_FACTURAS = "cantidadFacturas";
	public static final String PORCENTAJE_ACEPTADO_IPS = "porcentajeAceptadoIps";
	public static final String VALOR_PAGADO_EPS = "valorPagadoEps";
	public static final String WS_CM_TRANSACCIONES_LIST = "wsCmTransaccionesList";
	public static final String ESTADO_PROCESO = "estadoProceso";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CM_GLOSA_RESPUESTAS_LIST = "cmGlosaRespuestasList";
	public static final String PORCENTAJE_PAGADO_EPS = "porcentajePagadoEps";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String CANTIDAD_FACTURAS_REGISTRADAS = "cantidadFacturasRegistradas";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ARCHIVO_EXISTE = "archivoExiste";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String CM_SINCRONIZACIONES_LIST = "cmSincronizacionesList";
	public static final String ID = "id";
	public static final String PORCENTAJE = "porcentaje";

}

