package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpDireccionamientoEntregados.class)
public abstract class MpDireccionamientoEntregados_ {

	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> entregaTotal;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> estRepEntrega;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, String> codTecEntregado;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, BigDecimal> valorReportado;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> idReporteEntrega;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> tipoTecEntregado;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Date> fechaAnulacion;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> causaNoEntrega;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, String> descTecEntregado;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> idTransaccion;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, String> usuarioCrea;
	public static volatile ListAttribute<MpDireccionamientoEntregados, MpEntregaFacturas> mpEntregaFacturasList;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Boolean> cierreSuministro;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, MpPrescripcionMedicamentos> mpPrescripicionMedicamentosId;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, String> terminalCrea;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Date> fechaDireccionamientoAutomatico;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, BigDecimal> valorTotal;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Date> fechaEntrega;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Date> fechaHoraCrea;
	public static volatile ListAttribute<MpDireccionamientoEntregados, MpEntregaSuministros> mpEntregaSuministrosList;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> id;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> cantidadEntrega;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> estadoEntrega;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, String> cufe;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> entregaCompleta;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Date> fechaMaximaDireccionamiento;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, String> numeroLote;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, MpDireccionamientos> mpDireccionamientoId;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, MpPrescripcionInsumos> mpPrescripicionInsumosId;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> itemId;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> numeroEntrega;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Date> fechaReporteFactura;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> tipoTecnologia;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, String> justificacionDireccionamiento;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Integer> estadoSuministro;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Boolean> anulado;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, MpPrescripciones> mpPrescripcionId;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, Date> fechaConsumo;
	public static volatile SingularAttribute<MpDireccionamientoEntregados, MpPrescripcionTecnologias> mpPrescripicionTecnologiasId;

	public static final String ENTREGA_TOTAL = "entregaTotal";
	public static final String EST_REP_ENTREGA = "estRepEntrega";
	public static final String COD_TEC_ENTREGADO = "codTecEntregado";
	public static final String VALOR_REPORTADO = "valorReportado";
	public static final String ID_REPORTE_ENTREGA = "idReporteEntrega";
	public static final String TIPO_TEC_ENTREGADO = "tipoTecEntregado";
	public static final String FECHA_ANULACION = "fechaAnulacion";
	public static final String CAUSA_NO_ENTREGA = "causaNoEntrega";
	public static final String DESC_TEC_ENTREGADO = "descTecEntregado";
	public static final String ID_TRANSACCION = "idTransaccion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MP_ENTREGA_FACTURAS_LIST = "mpEntregaFacturasList";
	public static final String CIERRE_SUMINISTRO = "cierreSuministro";
	public static final String MP_PRESCRIPICION_MEDICAMENTOS_ID = "mpPrescripicionMedicamentosId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_DIRECCIONAMIENTO_AUTOMATICO = "fechaDireccionamientoAutomatico";
	public static final String VALOR_TOTAL = "valorTotal";
	public static final String FECHA_ENTREGA = "fechaEntrega";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MP_ENTREGA_SUMINISTROS_LIST = "mpEntregaSuministrosList";
	public static final String ID = "id";
	public static final String CANTIDAD_ENTREGA = "cantidadEntrega";
	public static final String ESTADO_ENTREGA = "estadoEntrega";
	public static final String CUFE = "cufe";
	public static final String ENTREGA_COMPLETA = "entregaCompleta";
	public static final String FECHA_MAXIMA_DIRECCIONAMIENTO = "fechaMaximaDireccionamiento";
	public static final String NUMERO_LOTE = "numeroLote";
	public static final String MP_DIRECCIONAMIENTO_ID = "mpDireccionamientoId";
	public static final String MP_PRESCRIPICION_INSUMOS_ID = "mpPrescripicionInsumosId";
	public static final String ITEM_ID = "itemId";
	public static final String NUMERO_ENTREGA = "numeroEntrega";
	public static final String FECHA_REPORTE_FACTURA = "fechaReporteFactura";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String JUSTIFICACION_DIRECCIONAMIENTO = "justificacionDireccionamiento";
	public static final String ESTADO_SUMINISTRO = "estadoSuministro";
	public static final String ANULADO = "anulado";
	public static final String MP_PRESCRIPCION_ID = "mpPrescripcionId";
	public static final String FECHA_CONSUMO = "fechaConsumo";
	public static final String MP_PRESCRIPICION_TECNOLOGIAS_ID = "mpPrescripicionTecnologiasId";

}

