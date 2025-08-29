package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaMedicamentosHistoricos.class)
public abstract class MaMedicamentosHistoricos_ {

	public static volatile SingularAttribute<MaMedicamentosHistoricos, Integer> maeFormaFarmaceuticaId;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, MaMedicamentos> maMedicamentosId;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> descripcionEstandarizada;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> maeTipoPpmCodigo;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Boolean> aplicaContributivo;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> maeFormaFarmaceuticaCodigo;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> terminalModifica;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> maeTipoPpmValor;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> maePrincipioActivoCodigo;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Integer> sexoAplica;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Boolean> esAltoCosto;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, BigDecimal> valorReferenteMinimo;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Integer> maeTipoPpmId;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> descripcionInvima;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Boolean> aplicaSubsidiado;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Integer> id;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> codigoFinanciador;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Integer> maeConcentracionId;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Integer> edadHasta;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Integer> maePrincipioActivoId;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Boolean> esCapitado;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> maeConcentracionCodigo;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, BigDecimal> valorMaximoRegulado;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, MaAgrupadoresMedicamento> maAgrupadoresMedicamentoId;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> maeTipoCodigo;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> maeFormaFarmaceuticaValor;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, BigDecimal> valorReferete;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> maeConcentracionValor;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Integer> edadDesde;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> maePrincipioActivoValor;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> cum;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> codigoIum;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Boolean> esRegulado;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Integer> idViejo;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, Integer> maeTipoId;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> maeTipoValor;
	public static volatile SingularAttribute<MaMedicamentosHistoricos, String> usuarioModifica;

	public static final String MAE_FORMA_FARMACEUTICA_ID = "maeFormaFarmaceuticaId";
	public static final String MA_MEDICAMENTOS_ID = "maMedicamentosId";
	public static final String DESCRIPCION_ESTANDARIZADA = "descripcionEstandarizada";
	public static final String MAE_TIPO_PPM_CODIGO = "maeTipoPpmCodigo";
	public static final String APLICA_CONTRIBUTIVO = "aplicaContributivo";
	public static final String MAE_FORMA_FARMACEUTICA_CODIGO = "maeFormaFarmaceuticaCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String MAE_TIPO_PPM_VALOR = "maeTipoPpmValor";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_PRINCIPIO_ACTIVO_CODIGO = "maePrincipioActivoCodigo";
	public static final String SEXO_APLICA = "sexoAplica";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ES_ALTO_COSTO = "esAltoCosto";
	public static final String VALOR_REFERENTE_MINIMO = "valorReferenteMinimo";
	public static final String MAE_TIPO_PPM_ID = "maeTipoPpmId";
	public static final String DESCRIPCION_INVIMA = "descripcionInvima";
	public static final String APLICA_SUBSIDIADO = "aplicaSubsidiado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CODIGO_FINANCIADOR = "codigoFinanciador";
	public static final String MAE_CONCENTRACION_ID = "maeConcentracionId";
	public static final String EDAD_HASTA = "edadHasta";
	public static final String MAE_PRINCIPIO_ACTIVO_ID = "maePrincipioActivoId";
	public static final String ES_CAPITADO = "esCapitado";
	public static final String MAE_CONCENTRACION_CODIGO = "maeConcentracionCodigo";
	public static final String VALOR_MAXIMO_REGULADO = "valorMaximoRegulado";
	public static final String MA_AGRUPADORES_MEDICAMENTO_ID = "maAgrupadoresMedicamentoId";
	public static final String MAE_TIPO_CODIGO = "maeTipoCodigo";
	public static final String MAE_FORMA_FARMACEUTICA_VALOR = "maeFormaFarmaceuticaValor";
	public static final String VALOR_REFERETE = "valorReferete";
	public static final String MAE_CONCENTRACION_VALOR = "maeConcentracionValor";
	public static final String EDAD_DESDE = "edadDesde";
	public static final String MAE_PRINCIPIO_ACTIVO_VALOR = "maePrincipioActivoValor";
	public static final String CUM = "cum";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CODIGO_IUM = "codigoIum";
	public static final String ES_REGULADO = "esRegulado";
	public static final String ID_VIEJO = "idViejo";
	public static final String MAE_TIPO_ID = "maeTipoId";
	public static final String MAE_TIPO_VALOR = "maeTipoValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

