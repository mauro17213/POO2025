package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmAuditoriaConceptosContables.class)
public abstract class CmAuditoriaConceptosContables_ {

	public static volatile SingularAttribute<CmAuditoriaConceptosContables, CmDetalles> cmDetallesId;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, String> maeCentroCostoValor;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, String> municipioAfiliado;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, String> maeConceptosValor;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, Integer> maeConceptosId;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, String> usuarioCrea;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, String> terminalCrea;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, Integer> maeCentroCostoId;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, Integer> id;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, BigDecimal> porcentaje;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, String> codigoMunicipio;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, String> maeConceptosCodigo;
	public static volatile SingularAttribute<CmAuditoriaConceptosContables, String> maeCentroCostoCodigo;

	public static final String CM_DETALLES_ID = "cmDetallesId";
	public static final String MAE_CENTRO_COSTO_VALOR = "maeCentroCostoValor";
	public static final String MUNICIPIO_AFILIADO = "municipioAfiliado";
	public static final String MAE_CONCEPTOS_VALOR = "maeConceptosValor";
	public static final String MAE_CONCEPTOS_ID = "maeConceptosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_CENTRO_COSTO_ID = "maeCentroCostoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String PORCENTAJE = "porcentaje";
	public static final String CODIGO_MUNICIPIO = "codigoMunicipio";
	public static final String MAE_CONCEPTOS_CODIGO = "maeConceptosCodigo";
	public static final String MAE_CENTRO_COSTO_CODIGO = "maeCentroCostoCodigo";

}

