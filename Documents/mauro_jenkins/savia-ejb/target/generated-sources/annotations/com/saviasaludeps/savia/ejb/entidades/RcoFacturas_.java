package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RcoFacturas.class)
public abstract class RcoFacturas_ {

	public static volatile SingularAttribute<RcoFacturas, BigDecimal> valorInicialGlosa;
	public static volatile SingularAttribute<RcoFacturas, String> maeEstadoRecobroValor;
	public static volatile SingularAttribute<RcoFacturas, Integer> maeTipoContratoId;
	public static volatile SingularAttribute<RcoFacturas, Date> fechaHoraAuditoria;
	public static volatile SingularAttribute<RcoFacturas, Date> fechaHoraRadicacion;
	public static volatile SingularAttribute<RcoFacturas, String> maeTipoContratoCodigo;
	public static volatile SingularAttribute<RcoFacturas, Integer> estadoFactura;
	public static volatile SingularAttribute<RcoFacturas, Integer> maeRegimenId;
	public static volatile SingularAttribute<RcoFacturas, BigDecimal> valorFactura;
	public static volatile SingularAttribute<RcoFacturas, String> terminalModifica;
	public static volatile SingularAttribute<RcoFacturas, String> usuarioCrea;
	public static volatile SingularAttribute<RcoFacturas, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<RcoFacturas, String> terminalCrea;
	public static volatile SingularAttribute<RcoFacturas, String> maeEstadoRecobroCodigo;
	public static volatile SingularAttribute<RcoFacturas, String> nit;
	public static volatile SingularAttribute<RcoFacturas, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<RcoFacturas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RcoFacturas, String> numeroFacturado;
	public static volatile SingularAttribute<RcoFacturas, Integer> id;
	public static volatile SingularAttribute<RcoFacturas, Integer> tipoRecobro;
	public static volatile SingularAttribute<RcoFacturas, String> terminalAuditoria;
	public static volatile SingularAttribute<RcoFacturas, String> numeroContrato;
	public static volatile SingularAttribute<RcoFacturas, String> maeTipoContratoValor;
	public static volatile SingularAttribute<RcoFacturas, String> ips;
	public static volatile SingularAttribute<RcoFacturas, String> maeRegimenValor;
	public static volatile SingularAttribute<RcoFacturas, String> maeRegimenCodigo;
	public static volatile SingularAttribute<RcoFacturas, String> usuarioAuditoria;
	public static volatile SingularAttribute<RcoFacturas, Date> fechaHoraPrestacion;
	public static volatile SingularAttribute<RcoFacturas, Boolean> aplicaRecobro;
	public static volatile SingularAttribute<RcoFacturas, BigDecimal> valorPagadoFactura;
	public static volatile SingularAttribute<RcoFacturas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<RcoFacturas, Date> cmFechaHoraCrea;
	public static volatile SingularAttribute<RcoFacturas, Integer> maeEstadoRecobroId;
	public static volatile SingularAttribute<RcoFacturas, Integer> numeroRadicado;
	public static volatile ListAttribute<RcoFacturas, RcoFacturaDetalles> rcoFacturaDetallesList;
	public static volatile SingularAttribute<RcoFacturas, String> usuarioModifica;

	public static final String VALOR_INICIAL_GLOSA = "valorInicialGlosa";
	public static final String MAE_ESTADO_RECOBRO_VALOR = "maeEstadoRecobroValor";
	public static final String MAE_TIPO_CONTRATO_ID = "maeTipoContratoId";
	public static final String FECHA_HORA_AUDITORIA = "fechaHoraAuditoria";
	public static final String FECHA_HORA_RADICACION = "fechaHoraRadicacion";
	public static final String MAE_TIPO_CONTRATO_CODIGO = "maeTipoContratoCodigo";
	public static final String ESTADO_FACTURA = "estadoFactura";
	public static final String MAE_REGIMEN_ID = "maeRegimenId";
	public static final String VALOR_FACTURA = "valorFactura";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_ESTADO_RECOBRO_CODIGO = "maeEstadoRecobroCodigo";
	public static final String NIT = "nit";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String NUMERO_FACTURADO = "numeroFacturado";
	public static final String ID = "id";
	public static final String TIPO_RECOBRO = "tipoRecobro";
	public static final String TERMINAL_AUDITORIA = "terminalAuditoria";
	public static final String NUMERO_CONTRATO = "numeroContrato";
	public static final String MAE_TIPO_CONTRATO_VALOR = "maeTipoContratoValor";
	public static final String IPS = "ips";
	public static final String MAE_REGIMEN_VALOR = "maeRegimenValor";
	public static final String MAE_REGIMEN_CODIGO = "maeRegimenCodigo";
	public static final String USUARIO_AUDITORIA = "usuarioAuditoria";
	public static final String FECHA_HORA_PRESTACION = "fechaHoraPrestacion";
	public static final String APLICA_RECOBRO = "aplicaRecobro";
	public static final String VALOR_PAGADO_FACTURA = "valorPagadoFactura";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CM_FECHA_HORA_CREA = "cmFechaHoraCrea";
	public static final String MAE_ESTADO_RECOBRO_ID = "maeEstadoRecobroId";
	public static final String NUMERO_RADICADO = "numeroRadicado";
	public static final String RCO_FACTURA_DETALLES_LIST = "rcoFacturaDetallesList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

