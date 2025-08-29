package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WsCmFacturas.class)
public abstract class WsCmFacturas_ {

	public static volatile SingularAttribute<WsCmFacturas, Short> estado;
	public static volatile SingularAttribute<WsCmFacturas, BigDecimal> valorCopago;
	public static volatile SingularAttribute<WsCmFacturas, Date> fechaHoraProceso;
	public static volatile SingularAttribute<WsCmFacturas, Integer> facturaId;
	public static volatile SingularAttribute<WsCmFacturas, String> contrato;
	public static volatile SingularAttribute<WsCmFacturas, BigDecimal> valorPagado;
	public static volatile SingularAttribute<WsCmFacturas, String> proveedorNit;
	public static volatile SingularAttribute<WsCmFacturas, BigDecimal> valorDocumento;
	public static volatile SingularAttribute<WsCmFacturas, BigDecimal> valorGlosado;
	public static volatile SingularAttribute<WsCmFacturas, String> usuarioCrea;
	public static volatile SingularAttribute<WsCmFacturas, Date> fechaHoraDocumento;
	public static volatile SingularAttribute<WsCmFacturas, String> terminalCrea;
	public static volatile ListAttribute<WsCmFacturas, WsCmFacturaDetalles> wsCmFacturaDetallesList;
	public static volatile SingularAttribute<WsCmFacturas, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<WsCmFacturas, CmRadicados> cmRadicadosId;
	public static volatile SingularAttribute<WsCmFacturas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<WsCmFacturas, Integer> id;
	public static volatile SingularAttribute<WsCmFacturas, String> numeroDocumento;
	public static volatile SingularAttribute<WsCmFacturas, BigDecimal> cuotaModeradora;
	public static volatile SingularAttribute<WsCmFacturas, String> numeroRadicado;
	public static volatile SingularAttribute<WsCmFacturas, String> regimen;

	public static final String ESTADO = "estado";
	public static final String VALOR_COPAGO = "valorCopago";
	public static final String FECHA_HORA_PROCESO = "fechaHoraProceso";
	public static final String FACTURA_ID = "facturaId";
	public static final String CONTRATO = "contrato";
	public static final String VALOR_PAGADO = "valorPagado";
	public static final String PROVEEDOR_NIT = "proveedorNit";
	public static final String VALOR_DOCUMENTO = "valorDocumento";
	public static final String VALOR_GLOSADO = "valorGlosado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_HORA_DOCUMENTO = "fechaHoraDocumento";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String WS_CM_FACTURA_DETALLES_LIST = "wsCmFacturaDetallesList";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String CM_RADICADOS_ID = "cmRadicadosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String CUOTA_MODERADORA = "cuotaModeradora";
	public static final String NUMERO_RADICADO = "numeroRadicado";
	public static final String REGIMEN = "regimen";

}

