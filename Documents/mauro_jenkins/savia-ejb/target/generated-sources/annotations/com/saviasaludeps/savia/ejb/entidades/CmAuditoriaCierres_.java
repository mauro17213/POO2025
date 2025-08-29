package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmAuditoriaCierres.class)
public abstract class CmAuditoriaCierres_ {

	public static volatile SingularAttribute<CmAuditoriaCierres, Integer> cantidadDetalles;
	public static volatile SingularAttribute<CmAuditoriaCierres, Date> fechaHoraRegistroInicio;
	public static volatile SingularAttribute<CmAuditoriaCierres, BigDecimal> valorFacturado;
	public static volatile SingularAttribute<CmAuditoriaCierres, Date> fechaHoraRegistroFinalizacion;
	public static volatile SingularAttribute<CmAuditoriaCierres, BigDecimal> valorPagado;
	public static volatile ListAttribute<CmAuditoriaCierres, CmRadicados> cmRadicadosList;
	public static volatile SingularAttribute<CmAuditoriaCierres, CmAuditoriaMasiva> cmAuditoriaMasivaId;
	public static volatile SingularAttribute<CmAuditoriaCierres, String> usuariosCrea;
	public static volatile ListAttribute<CmAuditoriaCierres, WsCmTransacciones> wsCmTransaccionesList;
	public static volatile SingularAttribute<CmAuditoriaCierres, BigDecimal> valorGlosado;
	public static volatile SingularAttribute<CmAuditoriaCierres, String> terminalCrea;
	public static volatile SingularAttribute<CmAuditoriaCierres, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmAuditoriaCierres, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmAuditoriaCierres, Integer> cantidadDetallesRegistradas;
	public static volatile ListAttribute<CmAuditoriaCierres, CmSincronizaciones> cmSincronizacionesList;
	public static volatile SingularAttribute<CmAuditoriaCierres, Integer> id;

	public static final String CANTIDAD_DETALLES = "cantidadDetalles";
	public static final String FECHA_HORA_REGISTRO_INICIO = "fechaHoraRegistroInicio";
	public static final String VALOR_FACTURADO = "valorFacturado";
	public static final String FECHA_HORA_REGISTRO_FINALIZACION = "fechaHoraRegistroFinalizacion";
	public static final String VALOR_PAGADO = "valorPagado";
	public static final String CM_RADICADOS_LIST = "cmRadicadosList";
	public static final String CM_AUDITORIA_MASIVA_ID = "cmAuditoriaMasivaId";
	public static final String USUARIOS_CREA = "usuariosCrea";
	public static final String WS_CM_TRANSACCIONES_LIST = "wsCmTransaccionesList";
	public static final String VALOR_GLOSADO = "valorGlosado";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String CANTIDAD_DETALLES_REGISTRADAS = "cantidadDetallesRegistradas";
	public static final String CM_SINCRONIZACIONES_LIST = "cmSincronizacionesList";
	public static final String ID = "id";

}

