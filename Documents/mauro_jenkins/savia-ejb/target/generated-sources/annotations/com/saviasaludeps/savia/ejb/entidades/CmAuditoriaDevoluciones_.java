package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmAuditoriaDevoluciones.class)
public abstract class CmAuditoriaDevoluciones_ {

	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> maeDevolucionMotivoGeneralValor;
	public static volatile ListAttribute<CmAuditoriaDevoluciones, CmRadicados> cmRadicadosList;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, Date> fechaRadicacion;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, Integer> maeMotivoDevolucionId;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> maeDevolucionMotivoGeneralCodigo;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, Integer> maeRegimenId;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, BigDecimal> valorFactura;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> terminalModifica;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> usuarioCrea;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, Date> fechaDevolucion;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> terminalCrea;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> nit;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> numeroFacturado;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, Integer> id;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> maeMotivoDevolucionValor;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> observacion;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> maeContratoModalidadCodigo;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, Integer> maeContratoModalidadId;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> maeDevolucionMotivoGeneralDescripcion;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, CmDevolucionMasiva> cmDevolucionMasivaId;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> ips;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> maeRegimenValor;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> maeRegimenCodigo;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> maeMotivoDevolucionCodigo;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, Integer> maeDevolucionMotivoGeneralId;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> numeroRadicado;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> maeContratoModalidadValor;
	public static volatile SingularAttribute<CmAuditoriaDevoluciones, String> usuarioModifica;

	public static final String MAE_DEVOLUCION_MOTIVO_GENERAL_VALOR = "maeDevolucionMotivoGeneralValor";
	public static final String CM_RADICADOS_LIST = "cmRadicadosList";
	public static final String FECHA_RADICACION = "fechaRadicacion";
	public static final String MAE_MOTIVO_DEVOLUCION_ID = "maeMotivoDevolucionId";
	public static final String MAE_DEVOLUCION_MOTIVO_GENERAL_CODIGO = "maeDevolucionMotivoGeneralCodigo";
	public static final String MAE_REGIMEN_ID = "maeRegimenId";
	public static final String VALOR_FACTURA = "valorFactura";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_DEVOLUCION = "fechaDevolucion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String NIT = "nit";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String NUMERO_FACTURADO = "numeroFacturado";
	public static final String ID = "id";
	public static final String MAE_MOTIVO_DEVOLUCION_VALOR = "maeMotivoDevolucionValor";
	public static final String OBSERVACION = "observacion";
	public static final String MAE_CONTRATO_MODALIDAD_CODIGO = "maeContratoModalidadCodigo";
	public static final String MAE_CONTRATO_MODALIDAD_ID = "maeContratoModalidadId";
	public static final String MAE_DEVOLUCION_MOTIVO_GENERAL_DESCRIPCION = "maeDevolucionMotivoGeneralDescripcion";
	public static final String CM_DEVOLUCION_MASIVA_ID = "cmDevolucionMasivaId";
	public static final String IPS = "ips";
	public static final String MAE_REGIMEN_VALOR = "maeRegimenValor";
	public static final String MAE_REGIMEN_CODIGO = "maeRegimenCodigo";
	public static final String MAE_MOTIVO_DEVOLUCION_CODIGO = "maeMotivoDevolucionCodigo";
	public static final String MAE_DEVOLUCION_MOTIVO_GENERAL_ID = "maeDevolucionMotivoGeneralId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String NUMERO_RADICADO = "numeroRadicado";
	public static final String MAE_CONTRATO_MODALIDAD_VALOR = "maeContratoModalidadValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

