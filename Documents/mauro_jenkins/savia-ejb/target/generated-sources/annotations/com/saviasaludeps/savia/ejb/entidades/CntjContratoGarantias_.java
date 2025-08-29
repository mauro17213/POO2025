package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjContratoGarantias.class)
public abstract class CntjContratoGarantias_ {

	public static volatile SingularAttribute<CntjContratoGarantias, BigDecimal> valorAsegurado;
	public static volatile SingularAttribute<CntjContratoGarantias, Integer> estado;
	public static volatile SingularAttribute<CntjContratoGarantias, Date> vigenciaDesde;
	public static volatile SingularAttribute<CntjContratoGarantias, Boolean> requiereRenovacion;
	public static volatile SingularAttribute<CntjContratoGarantias, CntjContratos> cntjContratosId;
	public static volatile SingularAttribute<CntjContratoGarantias, BigDecimal> porcentajeValorContrato;
	public static volatile SingularAttribute<CntjContratoGarantias, BigDecimal> porcentajeValorAnticipo;
	public static volatile SingularAttribute<CntjContratoGarantias, Date> fechaExpedicion;
	public static volatile SingularAttribute<CntjContratoGarantias, String> terminalModifica;
	public static volatile SingularAttribute<CntjContratoGarantias, String> usuarioCrea;
	public static volatile SingularAttribute<CntjContratoGarantias, String> terminalCrea;
	public static volatile SingularAttribute<CntjContratoGarantias, Date> fechaAprobacion;
	public static volatile SingularAttribute<CntjContratoGarantias, String> maeGarantiaContratoCodigo;
	public static volatile SingularAttribute<CntjContratoGarantias, String> maeGarantiaContratoValor;
	public static volatile SingularAttribute<CntjContratoGarantias, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjContratoGarantias, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjContratoGarantias, Integer> id;
	public static volatile SingularAttribute<CntjContratoGarantias, Integer> maeGarantiaContratoId;
	public static volatile SingularAttribute<CntjContratoGarantias, Date> vigenciaHasta;
	public static volatile SingularAttribute<CntjContratoGarantias, String> usuarioModifica;

	public static final String VALOR_ASEGURADO = "valorAsegurado";
	public static final String ESTADO = "estado";
	public static final String VIGENCIA_DESDE = "vigenciaDesde";
	public static final String REQUIERE_RENOVACION = "requiereRenovacion";
	public static final String CNTJ_CONTRATOS_ID = "cntjContratosId";
	public static final String PORCENTAJE_VALOR_CONTRATO = "porcentajeValorContrato";
	public static final String PORCENTAJE_VALOR_ANTICIPO = "porcentajeValorAnticipo";
	public static final String FECHA_EXPEDICION = "fechaExpedicion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_APROBACION = "fechaAprobacion";
	public static final String MAE_GARANTIA_CONTRATO_CODIGO = "maeGarantiaContratoCodigo";
	public static final String MAE_GARANTIA_CONTRATO_VALOR = "maeGarantiaContratoValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_GARANTIA_CONTRATO_ID = "maeGarantiaContratoId";
	public static final String VIGENCIA_HASTA = "vigenciaHasta";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

