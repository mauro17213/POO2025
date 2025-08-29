package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RcoFacturaDetalles.class)
public abstract class RcoFacturaDetalles_ {

	public static volatile SingularAttribute<RcoFacturaDetalles, Boolean> estadoConciliacion;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> tipoServicio;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> valorFacturado;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> maServicioCodigo;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> documento;
	public static volatile SingularAttribute<RcoFacturaDetalles, RcoGrupos> rcoGruposId;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> nombreCompleto;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> maeEstadoValor;
	public static volatile SingularAttribute<RcoFacturaDetalles, RcoFacturas> rcoFacturasId;
	public static volatile SingularAttribute<RcoFacturaDetalles, RcoConciliaciones> rcoConciliacionId;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> conceptoContable;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> terminalModifica;
	public static volatile SingularAttribute<RcoFacturaDetalles, Boolean> causalRecobro;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> usuarioCrea;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> maDiagnostico;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> terminalCrea;
	public static volatile SingularAttribute<RcoFacturaDetalles, CntPrestadorSedes> cntPrestadoresSedesId;
	public static volatile SingularAttribute<RcoFacturaDetalles, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> id;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> maeEstadoId;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> maServicioHabilitadoId;
	public static volatile SingularAttribute<RcoFacturaDetalles, PeProgramas> peProgramaId;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> maDiagnosticoId;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> observacion;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> maDiagnosticoValor;
	public static volatile SingularAttribute<RcoFacturaDetalles, CmDetalles> cmDetalleId;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> numeroContrato;
	public static volatile SingularAttribute<RcoFacturaDetalles, GnUsuarios> gnUsuarioResponsableId;
	public static volatile SingularAttribute<RcoFacturaDetalles, BigDecimal> valorRestanteRecobro;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> cmDetalleEstado;
	public static volatile SingularAttribute<RcoFacturaDetalles, BigDecimal> valorTotalRecobro;
	public static volatile ListAttribute<RcoFacturaDetalles, RcoConciliacionGestiones> rcoConciliacionGestionesList;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> maeEstadoCodigo;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> modalidad;
	public static volatile SingularAttribute<RcoFacturaDetalles, Date> fechaHoraPrestacion;
	public static volatile SingularAttribute<RcoFacturaDetalles, Boolean> aplicaRecobro;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> maDiagnosticoCodigo;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> maServicioValor;
	public static volatile SingularAttribute<RcoFacturaDetalles, Date> fechaHoraModifica;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> cantidad;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> observacionDetalle;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<RcoFacturaDetalles, String> usuarioModifica;
	public static volatile SingularAttribute<RcoFacturaDetalles, Integer> consecutivoItem;

	public static final String ESTADO_CONCILIACION = "estadoConciliacion";
	public static final String TIPO_SERVICIO = "tipoServicio";
	public static final String VALOR_FACTURADO = "valorFacturado";
	public static final String MA_SERVICIO_CODIGO = "maServicioCodigo";
	public static final String DOCUMENTO = "documento";
	public static final String RCO_GRUPOS_ID = "rcoGruposId";
	public static final String NOMBRE_COMPLETO = "nombreCompleto";
	public static final String MAE_ESTADO_VALOR = "maeEstadoValor";
	public static final String RCO_FACTURAS_ID = "rcoFacturasId";
	public static final String RCO_CONCILIACION_ID = "rcoConciliacionId";
	public static final String CONCEPTO_CONTABLE = "conceptoContable";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CAUSAL_RECOBRO = "causalRecobro";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MA_DIAGNOSTICO = "maDiagnostico";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNT_PRESTADORES_SEDES_ID = "cntPrestadoresSedesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_ESTADO_ID = "maeEstadoId";
	public static final String MA_SERVICIO_HABILITADO_ID = "maServicioHabilitadoId";
	public static final String PE_PROGRAMA_ID = "peProgramaId";
	public static final String MA_DIAGNOSTICO_ID = "maDiagnosticoId";
	public static final String OBSERVACION = "observacion";
	public static final String MA_DIAGNOSTICO_VALOR = "maDiagnosticoValor";
	public static final String CM_DETALLE_ID = "cmDetalleId";
	public static final String NUMERO_CONTRATO = "numeroContrato";
	public static final String GN_USUARIO_RESPONSABLE_ID = "gnUsuarioResponsableId";
	public static final String VALOR_RESTANTE_RECOBRO = "valorRestanteRecobro";
	public static final String CM_DETALLE_ESTADO = "cmDetalleEstado";
	public static final String VALOR_TOTAL_RECOBRO = "valorTotalRecobro";
	public static final String RCO_CONCILIACION_GESTIONES_LIST = "rcoConciliacionGestionesList";
	public static final String MAE_ESTADO_CODIGO = "maeEstadoCodigo";
	public static final String MODALIDAD = "modalidad";
	public static final String FECHA_HORA_PRESTACION = "fechaHoraPrestacion";
	public static final String APLICA_RECOBRO = "aplicaRecobro";
	public static final String MA_DIAGNOSTICO_CODIGO = "maDiagnosticoCodigo";
	public static final String MA_SERVICIO_VALOR = "maServicioValor";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CANTIDAD = "cantidad";
	public static final String OBSERVACION_DETALLE = "observacionDetalle";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String CONSECUTIVO_ITEM = "consecutivoItem";

}

