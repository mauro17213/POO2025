package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmGlosaMasiva.class)
public abstract class CmGlosaMasiva_ {

	public static volatile SingularAttribute<CmGlosaMasiva, Date> horaFinalizacionRegistro;
	public static volatile SingularAttribute<CmGlosaMasiva, BigDecimal> valorTotalPagadoEps;
	public static volatile SingularAttribute<CmGlosaMasiva, BigDecimal> valorFacturas;
	public static volatile SingularAttribute<CmGlosaMasiva, Integer> cantidadFacturas;
	public static volatile SingularAttribute<CmGlosaMasiva, BigDecimal> porcentajeAceptadoIps;
	public static volatile SingularAttribute<CmGlosaMasiva, Integer> estadoProceso;
	public static volatile SingularAttribute<CmGlosaMasiva, Integer> cantidadFacturasConRatificacionGlosa;
	public static volatile SingularAttribute<CmGlosaMasiva, String> usuarioCrea;
	public static volatile ListAttribute<CmGlosaMasiva, CmGlosaRespuestas> cmGlosaRespuestasList;
	public static volatile SingularAttribute<CmGlosaMasiva, BigDecimal> porcentajePagadoEps;
	public static volatile SingularAttribute<CmGlosaMasiva, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CmGlosaMasiva, String> terminalCrea;
	public static volatile SingularAttribute<CmGlosaMasiva, Integer> cantidadFacturasConRespuestaGlosa;
	public static volatile SingularAttribute<CmGlosaMasiva, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmGlosaMasiva, Integer> id;
	public static volatile SingularAttribute<CmGlosaMasiva, BigDecimal> valorTotalAceptadoIps;

	public static final String HORA_FINALIZACION_REGISTRO = "horaFinalizacionRegistro";
	public static final String VALOR_TOTAL_PAGADO_EPS = "valorTotalPagadoEps";
	public static final String VALOR_FACTURAS = "valorFacturas";
	public static final String CANTIDAD_FACTURAS = "cantidadFacturas";
	public static final String PORCENTAJE_ACEPTADO_IPS = "porcentajeAceptadoIps";
	public static final String ESTADO_PROCESO = "estadoProceso";
	public static final String CANTIDAD_FACTURAS_CON_RATIFICACION_GLOSA = "cantidadFacturasConRatificacionGlosa";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CM_GLOSA_RESPUESTAS_LIST = "cmGlosaRespuestasList";
	public static final String PORCENTAJE_PAGADO_EPS = "porcentajePagadoEps";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CANTIDAD_FACTURAS_CON_RESPUESTA_GLOSA = "cantidadFacturasConRespuestaGlosa";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String VALOR_TOTAL_ACEPTADO_IPS = "valorTotalAceptadoIps";

}

