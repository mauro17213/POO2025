package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRadicados.class)
public abstract class CmRadicados_ {

	public static volatile SingularAttribute<CmRadicados, Integer> radicado;
	public static volatile SingularAttribute<CmRadicados, Short> estado;
	public static volatile SingularAttribute<CmRadicados, CmConciliaciones> cmConciliacionesId;
	public static volatile SingularAttribute<CmRadicados, Integer> cmGlosaMasivaId;
	public static volatile SingularAttribute<CmRadicados, CmAuditoriaMasiva> cmAuditoriaMasivaId;
	public static volatile SingularAttribute<CmRadicados, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRadicados, Date> fechaHoraTransaccion;
	public static volatile SingularAttribute<CmRadicados, Date> fechaHoraEnvioSap;
	public static volatile SingularAttribute<CmRadicados, CmAuditoriaDevoluciones> cmAuditoriaDevolucionesId;
	public static volatile SingularAttribute<CmRadicados, CmFeRipsCargas> cmFeRipsCargasId;
	public static volatile SingularAttribute<CmRadicados, CmAuditoriaCierres> cmAuditoriaCierresId;
	public static volatile SingularAttribute<CmRadicados, CmGlosaRespuestas> cmGlosaRespuestasId;
	public static volatile SingularAttribute<CmRadicados, String> usuarioCrea;
	public static volatile SingularAttribute<CmRadicados, CmGlosaRespuestas> cmGlosaRespuestasConciliacionId;
	public static volatile SingularAttribute<CmRadicados, String> terminalCrea;
	public static volatile SingularAttribute<CmRadicados, Short> intentosPermitidos;
	public static volatile SingularAttribute<CmRadicados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRadicados, Integer> id;
	public static volatile SingularAttribute<CmRadicados, Short> tipoRelacion;
	public static volatile ListAttribute<CmRadicados, CmSincronizacionEncabezados> cmSincronizacionEncabezadosList;
	public static volatile SingularAttribute<CmRadicados, CmDevolucionMasiva> cmDevolucionMasivaId;
	public static volatile SingularAttribute<CmRadicados, Short> intentosEjecutados;
	public static volatile ListAttribute<CmRadicados, WsCmTransacciones> wsCmTransaccionesList;
	public static volatile SingularAttribute<CmRadicados, Boolean> estadoRadicado;
	public static volatile SingularAttribute<CmRadicados, Integer> cmFacturaId;
	public static volatile ListAttribute<CmRadicados, WsCmFacturas> wsCmFacturasList;
	public static volatile ListAttribute<CmRadicados, CmSincronizaciones> cmSincronizacionesList;
	public static volatile SingularAttribute<CmRadicados, Short> tipoTransaccion;
	public static volatile SingularAttribute<CmRadicados, Date> fechaHoraFactura;

	public static final String RADICADO = "radicado";
	public static final String ESTADO = "estado";
	public static final String CM_CONCILIACIONES_ID = "cmConciliacionesId";
	public static final String CM_GLOSA_MASIVA_ID = "cmGlosaMasivaId";
	public static final String CM_AUDITORIA_MASIVA_ID = "cmAuditoriaMasivaId";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String FECHA_HORA_TRANSACCION = "fechaHoraTransaccion";
	public static final String FECHA_HORA_ENVIO_SAP = "fechaHoraEnvioSap";
	public static final String CM_AUDITORIA_DEVOLUCIONES_ID = "cmAuditoriaDevolucionesId";
	public static final String CM_FE_RIPS_CARGAS_ID = "cmFeRipsCargasId";
	public static final String CM_AUDITORIA_CIERRES_ID = "cmAuditoriaCierresId";
	public static final String CM_GLOSA_RESPUESTAS_ID = "cmGlosaRespuestasId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CM_GLOSA_RESPUESTAS_CONCILIACION_ID = "cmGlosaRespuestasConciliacionId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String INTENTOS_PERMITIDOS = "intentosPermitidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String TIPO_RELACION = "tipoRelacion";
	public static final String CM_SINCRONIZACION_ENCABEZADOS_LIST = "cmSincronizacionEncabezadosList";
	public static final String CM_DEVOLUCION_MASIVA_ID = "cmDevolucionMasivaId";
	public static final String INTENTOS_EJECUTADOS = "intentosEjecutados";
	public static final String WS_CM_TRANSACCIONES_LIST = "wsCmTransaccionesList";
	public static final String ESTADO_RADICADO = "estadoRadicado";
	public static final String CM_FACTURA_ID = "cmFacturaId";
	public static final String WS_CM_FACTURAS_LIST = "wsCmFacturasList";
	public static final String CM_SINCRONIZACIONES_LIST = "cmSincronizacionesList";
	public static final String TIPO_TRANSACCION = "tipoTransaccion";
	public static final String FECHA_HORA_FACTURA = "fechaHoraFactura";

}

