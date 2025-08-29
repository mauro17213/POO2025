package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeVariablesValoresHistoricos.class)
public abstract class PeVariablesValoresHistoricos_ {

	public static volatile SingularAttribute<PeVariablesValoresHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<PeVariablesValoresHistoricos, PeVariables> peVariablesId;
	public static volatile SingularAttribute<PeVariablesValoresHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<PeVariablesValoresHistoricos, PeCargasVariables> peCargasVariablesId;
	public static volatile SingularAttribute<PeVariablesValoresHistoricos, String> valor;
	public static volatile SingularAttribute<PeVariablesValoresHistoricos, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<PeVariablesValoresHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeVariablesValoresHistoricos, PeAfiliadosProgramas> peAfiliadosProgramasId;
	public static volatile SingularAttribute<PeVariablesValoresHistoricos, Integer> id;
	public static volatile SingularAttribute<PeVariablesValoresHistoricos, PeVariablesValores> peVariablesValoresId;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String PE_VARIABLES_ID = "peVariablesId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String PE_CARGAS_VARIABLES_ID = "peCargasVariablesId";
	public static final String VALOR = "valor";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String PE_AFILIADOS_PROGRAMAS_ID = "peAfiliadosProgramasId";
	public static final String ID = "id";
	public static final String PE_VARIABLES_VALORES_ID = "peVariablesValoresId";

}

