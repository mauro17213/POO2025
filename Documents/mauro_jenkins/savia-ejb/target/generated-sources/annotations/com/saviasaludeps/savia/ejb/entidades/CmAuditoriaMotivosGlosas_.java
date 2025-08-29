package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmAuditoriaMotivosGlosas.class)
public abstract class CmAuditoriaMotivosGlosas_ {

	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, CmDetalles> cmDetallesId;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, BigDecimal> valorMotivo;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, String> maeMotivoEspecificoCodigo;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, String> maeMotivoCodigo;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, String> maeMotivoGlosaAplicacionDescripcion;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, Integer> maeMotivoId;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, Integer> maeMotivoEspecificoId;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, String> maeMotivoGlosaAplicacionCodigo;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, Integer> tipologia;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, String> usuarioCrea;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, String> maeMotivoGlosaAplicacionValor;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, String> terminalCrea;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, Integer> maeMotivoGlosaAplicacionId;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, Integer> id;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, BigDecimal> porcentaje;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, String> maeMotivoEspecificoValor;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, String> observacion;
	public static volatile SingularAttribute<CmAuditoriaMotivosGlosas, String> maeMotivoValor;

	public static final String CM_DETALLES_ID = "cmDetallesId";
	public static final String VALOR_MOTIVO = "valorMotivo";
	public static final String MAE_MOTIVO_ESPECIFICO_CODIGO = "maeMotivoEspecificoCodigo";
	public static final String MAE_MOTIVO_CODIGO = "maeMotivoCodigo";
	public static final String MAE_MOTIVO_GLOSA_APLICACION_DESCRIPCION = "maeMotivoGlosaAplicacionDescripcion";
	public static final String MAE_MOTIVO_ID = "maeMotivoId";
	public static final String MAE_MOTIVO_ESPECIFICO_ID = "maeMotivoEspecificoId";
	public static final String MAE_MOTIVO_GLOSA_APLICACION_CODIGO = "maeMotivoGlosaAplicacionCodigo";
	public static final String TIPOLOGIA = "tipologia";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_MOTIVO_GLOSA_APLICACION_VALOR = "maeMotivoGlosaAplicacionValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_MOTIVO_GLOSA_APLICACION_ID = "maeMotivoGlosaAplicacionId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String PORCENTAJE = "porcentaje";
	public static final String MAE_MOTIVO_ESPECIFICO_VALOR = "maeMotivoEspecificoValor";
	public static final String OBSERVACION = "observacion";
	public static final String MAE_MOTIVO_VALOR = "maeMotivoValor";

}

