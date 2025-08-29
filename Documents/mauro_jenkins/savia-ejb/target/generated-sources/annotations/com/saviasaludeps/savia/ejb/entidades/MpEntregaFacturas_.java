package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpEntregaFacturas.class)
public abstract class MpEntregaFacturas_ {

	public static volatile SingularAttribute<MpEntregaFacturas, Short> estado;
	public static volatile SingularAttribute<MpEntregaFacturas, BigDecimal> copago;
	public static volatile SingularAttribute<MpEntregaFacturas, Date> confirmaFhCierreFacturaEps;
	public static volatile SingularAttribute<MpEntregaFacturas, String> idCicloFacturacion;
	public static volatile SingularAttribute<MpEntregaFacturas, BigDecimal> cantUnitaria;
	public static volatile SingularAttribute<MpEntregaFacturas, Short> confirmaCodigoComparador;
	public static volatile SingularAttribute<MpEntregaFacturas, BigDecimal> valorUnitario;
	public static volatile SingularAttribute<MpEntregaFacturas, Date> fechaAnulacion;
	public static volatile SingularAttribute<MpEntregaFacturas, String> confirmaUnidadAdminstrativo;
	public static volatile SingularAttribute<MpEntregaFacturas, String> idTransaccion;
	public static volatile SingularAttribute<MpEntregaFacturas, String> mpEntregaFacturascol;
	public static volatile SingularAttribute<MpEntregaFacturas, String> usuarioCrea;
	public static volatile SingularAttribute<MpEntregaFacturas, String> respuestaDatosFactura;
	public static volatile SingularAttribute<MpEntregaFacturas, Short> confirmaTipoComparador;
	public static volatile SingularAttribute<MpEntregaFacturas, String> terminalCrea;
	public static volatile SingularAttribute<MpEntregaFacturas, BigDecimal> valorTotal;
	public static volatile SingularAttribute<MpEntregaFacturas, Date> fechaConsumoDato;
	public static volatile SingularAttribute<MpEntregaFacturas, String> nit;
	public static volatile SingularAttribute<MpEntregaFacturas, String> noFactura;
	public static volatile SingularAttribute<MpEntregaFacturas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpEntregaFacturas, Short> noSubEntrega;
	public static volatile SingularAttribute<MpEntregaFacturas, Integer> id;
	public static volatile SingularAttribute<MpEntregaFacturas, BigDecimal> confirmaValorTotalComparador;
	public static volatile SingularAttribute<MpEntregaFacturas, BigDecimal> cuotaModeradora;
	public static volatile SingularAttribute<MpEntregaFacturas, String> cufe;
	public static volatile SingularAttribute<MpEntregaFacturas, Integer> estadoMipres;
	public static volatile SingularAttribute<MpEntregaFacturas, Date> fechaFacturacion;
	public static volatile SingularAttribute<MpEntregaFacturas, String> noidEPS;
	public static volatile SingularAttribute<MpEntregaFacturas, Boolean> confirmaAfectaPresupuesto;
	public static volatile SingularAttribute<MpEntregaFacturas, Boolean> confirmaCierreCiclo;
	public static volatile SingularAttribute<MpEntregaFacturas, String> idFacturacion;
	public static volatile SingularAttribute<MpEntregaFacturas, Date> confirmaFhCierreCiclo;
	public static volatile SingularAttribute<MpEntregaFacturas, String> confirmaUnidadHomologado;
	public static volatile SingularAttribute<MpEntregaFacturas, String> codEPS;
	public static volatile SingularAttribute<MpEntregaFacturas, String> confirmarUsuarioCrea;
	public static volatile SingularAttribute<MpEntregaFacturas, String> confirmarTerminalCrea;
	public static volatile SingularAttribute<MpEntregaFacturas, BigDecimal> confirmaValorMinimaConcentracion;
	public static volatile SingularAttribute<MpEntregaFacturas, Date> confirmaFecha;
	public static volatile SingularAttribute<MpEntregaFacturas, MpDireccionamientoEntregados> mpDireccionamientoEntregadosId;
	public static volatile SingularAttribute<MpEntregaFacturas, Date> confirmarFechaHoraCrea;
	public static volatile SingularAttribute<MpEntregaFacturas, MpEntregaSuministros> mpEntregaSuministrosId;
	public static volatile SingularAttribute<MpEntregaFacturas, Date> fechaConsumo;
	public static volatile SingularAttribute<MpEntregaFacturas, String> codigoFacturado;

	public static final String ESTADO = "estado";
	public static final String COPAGO = "copago";
	public static final String CONFIRMA_FH_CIERRE_FACTURA_EPS = "confirmaFhCierreFacturaEps";
	public static final String ID_CICLO_FACTURACION = "idCicloFacturacion";
	public static final String CANT_UNITARIA = "cantUnitaria";
	public static final String CONFIRMA_CODIGO_COMPARADOR = "confirmaCodigoComparador";
	public static final String VALOR_UNITARIO = "valorUnitario";
	public static final String FECHA_ANULACION = "fechaAnulacion";
	public static final String CONFIRMA_UNIDAD_ADMINSTRATIVO = "confirmaUnidadAdminstrativo";
	public static final String ID_TRANSACCION = "idTransaccion";
	public static final String MP_ENTREGA_FACTURASCOL = "mpEntregaFacturascol";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String RESPUESTA_DATOS_FACTURA = "respuestaDatosFactura";
	public static final String CONFIRMA_TIPO_COMPARADOR = "confirmaTipoComparador";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR_TOTAL = "valorTotal";
	public static final String FECHA_CONSUMO_DATO = "fechaConsumoDato";
	public static final String NIT = "nit";
	public static final String NO_FACTURA = "noFactura";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String NO_SUB_ENTREGA = "noSubEntrega";
	public static final String ID = "id";
	public static final String CONFIRMA_VALOR_TOTAL_COMPARADOR = "confirmaValorTotalComparador";
	public static final String CUOTA_MODERADORA = "cuotaModeradora";
	public static final String CUFE = "cufe";
	public static final String ESTADO_MIPRES = "estadoMipres";
	public static final String FECHA_FACTURACION = "fechaFacturacion";
	public static final String NOID_EP_S = "noidEPS";
	public static final String CONFIRMA_AFECTA_PRESUPUESTO = "confirmaAfectaPresupuesto";
	public static final String CONFIRMA_CIERRE_CICLO = "confirmaCierreCiclo";
	public static final String ID_FACTURACION = "idFacturacion";
	public static final String CONFIRMA_FH_CIERRE_CICLO = "confirmaFhCierreCiclo";
	public static final String CONFIRMA_UNIDAD_HOMOLOGADO = "confirmaUnidadHomologado";
	public static final String COD_EP_S = "codEPS";
	public static final String CONFIRMAR_USUARIO_CREA = "confirmarUsuarioCrea";
	public static final String CONFIRMAR_TERMINAL_CREA = "confirmarTerminalCrea";
	public static final String CONFIRMA_VALOR_MINIMA_CONCENTRACION = "confirmaValorMinimaConcentracion";
	public static final String CONFIRMA_FECHA = "confirmaFecha";
	public static final String MP_DIRECCIONAMIENTO_ENTREGADOS_ID = "mpDireccionamientoEntregadosId";
	public static final String CONFIRMAR_FECHA_HORA_CREA = "confirmarFechaHoraCrea";
	public static final String MP_ENTREGA_SUMINISTROS_ID = "mpEntregaSuministrosId";
	public static final String FECHA_CONSUMO = "fechaConsumo";
	public static final String CODIGO_FACTURADO = "codigoFacturado";

}

