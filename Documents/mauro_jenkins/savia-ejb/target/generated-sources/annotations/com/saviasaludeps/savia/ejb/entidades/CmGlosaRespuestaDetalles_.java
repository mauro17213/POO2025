package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmGlosaRespuestaDetalles.class)
public abstract class CmGlosaRespuestaDetalles_ {

	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, BigDecimal> valorFacturado;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, BigDecimal> valorPendiente;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, BigDecimal> valorAceptadoIps;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, CmDetalles> cmDetallesId;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, String> documento;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, BigDecimal> valorPagado;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, BigDecimal> porcentajeAceptadoIps;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, BigDecimal> valorPagadoEps;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, BigDecimal> valorCobroDetalle;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, CmGlosaRespuestas> cmGlosaRespuestasId;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, String> usuarioCrea;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, BigDecimal> porcentajePagadoEps;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, String> terminalCrea;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, Integer> id;
	public static volatile SingularAttribute<CmGlosaRespuestaDetalles, String> observacion;

	public static final String VALOR_FACTURADO = "valorFacturado";
	public static final String VALOR_PENDIENTE = "valorPendiente";
	public static final String VALOR_ACEPTADO_IPS = "valorAceptadoIps";
	public static final String CM_DETALLES_ID = "cmDetallesId";
	public static final String DOCUMENTO = "documento";
	public static final String VALOR_PAGADO = "valorPagado";
	public static final String PORCENTAJE_ACEPTADO_IPS = "porcentajeAceptadoIps";
	public static final String VALOR_PAGADO_EPS = "valorPagadoEps";
	public static final String VALOR_COBRO_DETALLE = "valorCobroDetalle";
	public static final String CM_GLOSA_RESPUESTAS_ID = "cmGlosaRespuestasId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String PORCENTAJE_PAGADO_EPS = "porcentajePagadoEps";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";

}

