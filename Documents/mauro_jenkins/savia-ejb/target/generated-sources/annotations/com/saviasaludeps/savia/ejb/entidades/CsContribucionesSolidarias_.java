package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CsContribucionesSolidarias.class)
public abstract class CsContribucionesSolidarias_ {

	public static volatile SingularAttribute<CsContribucionesSolidarias, String> maeContribucionSolidariaCodigo;
	public static volatile SingularAttribute<CsContribucionesSolidarias, Integer> maeContribucionSolidariaId;
	public static volatile SingularAttribute<CsContribucionesSolidarias, BigDecimal> valor;
	public static volatile SingularAttribute<CsContribucionesSolidarias, Integer> agno;
	public static volatile SingularAttribute<CsContribucionesSolidarias, String> terminalModifica;
	public static volatile SingularAttribute<CsContribucionesSolidarias, String> usuarioCrea;
	public static volatile SingularAttribute<CsContribucionesSolidarias, String> terminalCrea;
	public static volatile SingularAttribute<CsContribucionesSolidarias, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CsContribucionesSolidarias, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CsContribucionesSolidarias, Integer> id;
	public static volatile SingularAttribute<CsContribucionesSolidarias, BigDecimal> porcentaje;
	public static volatile SingularAttribute<CsContribucionesSolidarias, String> maeContribucionSolidariaValor;
	public static volatile SingularAttribute<CsContribucionesSolidarias, String> usuarioModifica;

	public static final String MAE_CONTRIBUCION_SOLIDARIA_CODIGO = "maeContribucionSolidariaCodigo";
	public static final String MAE_CONTRIBUCION_SOLIDARIA_ID = "maeContribucionSolidariaId";
	public static final String VALOR = "valor";
	public static final String AGNO = "agno";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String PORCENTAJE = "porcentaje";
	public static final String MAE_CONTRIBUCION_SOLIDARIA_VALOR = "maeContribucionSolidariaValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

