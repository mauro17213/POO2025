package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmPagos.class)
public abstract class CmPagos_ {

	public static volatile SingularAttribute<CmPagos, Short> tipo;
	public static volatile SingularAttribute<CmPagos, BigDecimal> valorNeto;
	public static volatile SingularAttribute<CmPagos, Date> fechaHora;
	public static volatile SingularAttribute<CmPagos, Integer> facturas;
	public static volatile SingularAttribute<CmPagos, BigDecimal> valorBruto;
	public static volatile SingularAttribute<CmPagos, BigDecimal> valorCompensacionAnticipos;
	public static volatile SingularAttribute<CmPagos, String> documento;
	public static volatile ListAttribute<CmPagos, CmPagoFacturas> cmPagoFacturasList;
	public static volatile SingularAttribute<CmPagos, Short> forma;
	public static volatile SingularAttribute<CmPagos, BigDecimal> valorDeducciones;
	public static volatile SingularAttribute<CmPagos, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CmPagos, String> usuarioCrea;
	public static volatile SingularAttribute<CmPagos, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CmPagos, String> idetificador;
	public static volatile SingularAttribute<CmPagos, String> terminalCrea;
	public static volatile SingularAttribute<CmPagos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmPagos, Integer> id;
	public static volatile ListAttribute<CmPagos, CmPagoTransacciones> cmPagoTransaccionesList;
	public static volatile SingularAttribute<CmPagos, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CmPagos, Integer> maeTipoDocumentoId;

	public static final String TIPO = "tipo";
	public static final String VALOR_NETO = "valorNeto";
	public static final String FECHA_HORA = "fechaHora";
	public static final String FACTURAS = "facturas";
	public static final String VALOR_BRUTO = "valorBruto";
	public static final String VALOR_COMPENSACION_ANTICIPOS = "valorCompensacionAnticipos";
	public static final String DOCUMENTO = "documento";
	public static final String CM_PAGO_FACTURAS_LIST = "cmPagoFacturasList";
	public static final String FORMA = "forma";
	public static final String VALOR_DEDUCCIONES = "valorDeducciones";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String IDETIFICADOR = "idetificador";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CM_PAGO_TRANSACCIONES_LIST = "cmPagoTransaccionesList";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";

}

