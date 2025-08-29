package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoNotasTecnicas.class)
public abstract class CntContratoNotasTecnicas_ {

	public static volatile SingularAttribute<CntContratoNotasTecnicas, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, String> maTecnologiaValor;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, Integer> maTecnologiaId;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, BigDecimal> frecuenciaUso;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, Integer> tipoFrecuencia;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, Date> fechaFin;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, String> maeAmbitoValor;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, String> terminalModifica;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, String> usuarioCrea;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, Integer> tipoTecnologia;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, String> maeAmbitoCodigo;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, Date> fechaInicio;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, String> terminalCrea;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, BigDecimal> costoPromedio;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, Integer> cantidadAfiliados;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, Integer> maeAmbitoId;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, Integer> id;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, String> observacion;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, String> usuarioModifica;
	public static volatile SingularAttribute<CntContratoNotasTecnicas, CntContratos> cntContratosId;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String FRECUENCIA_USO = "frecuenciaUso";
	public static final String TIPO_FRECUENCIA = "tipoFrecuencia";
	public static final String FECHA_FIN = "fechaFin";
	public static final String MAE_AMBITO_VALOR = "maeAmbitoValor";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String MAE_AMBITO_CODIGO = "maeAmbitoCodigo";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String COSTO_PROMEDIO = "costoPromedio";
	public static final String CANTIDAD_AFILIADOS = "cantidadAfiliados";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MAE_AMBITO_ID = "maeAmbitoId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

