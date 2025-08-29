package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmSincronizacionEncabezados.class)
public abstract class CmSincronizacionEncabezados_ {

	public static volatile SingularAttribute<CmSincronizacionEncabezados, Integer> estado;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, BigDecimal> valorCopago;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, Date> fechaHoraProceso;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, Integer> facturaId;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, String> contrato;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, BigDecimal> valorPagado;
	public static volatile ListAttribute<CmSincronizacionEncabezados, CmSincronizacionDetalles> cmSincronizacionDetallesList;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, String> proveedorNit;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, BigDecimal> valorDocumento;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, BigDecimal> valorGlosado;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, String> usuarioCrea;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, Date> fechaHoraDocumento;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, String> terminalCrea;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, CmRadicados> cmRadicadosId;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, Integer> id;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, String> numeroDocumento;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, BigDecimal> cuotaModeradora;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, String> numeroRadicado;
	public static volatile SingularAttribute<CmSincronizacionEncabezados, String> regimen;

	public static final String ESTADO = "estado";
	public static final String VALOR_COPAGO = "valorCopago";
	public static final String FECHA_HORA_PROCESO = "fechaHoraProceso";
	public static final String FACTURA_ID = "facturaId";
	public static final String CONTRATO = "contrato";
	public static final String VALOR_PAGADO = "valorPagado";
	public static final String CM_SINCRONIZACION_DETALLES_LIST = "cmSincronizacionDetallesList";
	public static final String PROVEEDOR_NIT = "proveedorNit";
	public static final String VALOR_DOCUMENTO = "valorDocumento";
	public static final String VALOR_GLOSADO = "valorGlosado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_HORA_DOCUMENTO = "fechaHoraDocumento";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_RADICADOS_ID = "cmRadicadosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String CUOTA_MODERADORA = "cuotaModeradora";
	public static final String NUMERO_RADICADO = "numeroRadicado";
	public static final String REGIMEN = "regimen";

}

