package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmPagoFacturas.class)
public abstract class CmPagoFacturas_ {

	public static volatile SingularAttribute<CmPagoFacturas, String> descripcion;
	public static volatile SingularAttribute<CmPagoFacturas, Integer> cmFacturaEstado;
	public static volatile SingularAttribute<CmPagoFacturas, BigDecimal> cmFacturaValor;
	public static volatile SingularAttribute<CmPagoFacturas, CmPagos> cmPagosId;
	public static volatile SingularAttribute<CmPagoFacturas, BigDecimal> valorNeto;
	public static volatile SingularAttribute<CmPagoFacturas, BigDecimal> valorBruto;
	public static volatile SingularAttribute<CmPagoFacturas, String> numeroFactura;
	public static volatile SingularAttribute<CmPagoFacturas, String> claseDocumento;
	public static volatile SingularAttribute<CmPagoFacturas, BigDecimal> valorDeducciones;
	public static volatile SingularAttribute<CmPagoFacturas, CmPagoTransacciones> cmPagoTransaccionesId;
	public static volatile SingularAttribute<CmPagoFacturas, Integer> consecutivo;
	public static volatile ListAttribute<CmPagoFacturas, CmPagoFacturaRetenciones> cmPagoFacturaRetencionesList;
	public static volatile SingularAttribute<CmPagoFacturas, String> documentoContable;
	public static volatile SingularAttribute<CmPagoFacturas, String> usuarioCrea;
	public static volatile SingularAttribute<CmPagoFacturas, String> terminalCrea;
	public static volatile SingularAttribute<CmPagoFacturas, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmPagoFacturas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmPagoFacturas, Integer> id;
	public static volatile SingularAttribute<CmPagoFacturas, Date> cmFacturaFecha;

	public static final String DESCRIPCION = "descripcion";
	public static final String CM_FACTURA_ESTADO = "cmFacturaEstado";
	public static final String CM_FACTURA_VALOR = "cmFacturaValor";
	public static final String CM_PAGOS_ID = "cmPagosId";
	public static final String VALOR_NETO = "valorNeto";
	public static final String VALOR_BRUTO = "valorBruto";
	public static final String NUMERO_FACTURA = "numeroFactura";
	public static final String CLASE_DOCUMENTO = "claseDocumento";
	public static final String VALOR_DEDUCCIONES = "valorDeducciones";
	public static final String CM_PAGO_TRANSACCIONES_ID = "cmPagoTransaccionesId";
	public static final String CONSECUTIVO = "consecutivo";
	public static final String CM_PAGO_FACTURA_RETENCIONES_LIST = "cmPagoFacturaRetencionesList";
	public static final String DOCUMENTO_CONTABLE = "documentoContable";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CM_FACTURA_FECHA = "cmFacturaFecha";

}

