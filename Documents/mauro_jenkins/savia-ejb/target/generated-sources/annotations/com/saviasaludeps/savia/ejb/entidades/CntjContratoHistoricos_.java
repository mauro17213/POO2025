package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjContratoHistoricos.class)
public abstract class CntjContratoHistoricos_ {

	public static volatile SingularAttribute<CntjContratoHistoricos, Boolean> tipo;
	public static volatile SingularAttribute<CntjContratoHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjContratoHistoricos, Date> fechaInicio;
	public static volatile SingularAttribute<CntjContratoHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<CntjContratoHistoricos, BigDecimal> valor;
	public static volatile SingularAttribute<CntjContratoHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjContratoHistoricos, Integer> id;
	public static volatile SingularAttribute<CntjContratoHistoricos, CntjContratos> cntjContratosId;
	public static volatile SingularAttribute<CntjContratoHistoricos, Date> fechaFin;

	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR = "valor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CNTJ_CONTRATOS_ID = "cntjContratosId";
	public static final String FECHA_FIN = "fechaFin";

}

