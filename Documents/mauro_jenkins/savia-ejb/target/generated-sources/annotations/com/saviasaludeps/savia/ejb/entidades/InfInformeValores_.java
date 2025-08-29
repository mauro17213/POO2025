package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InfInformeValores.class)
public abstract class InfInformeValores_ {

	public static volatile SingularAttribute<InfInformeValores, String> terminalModifica;
	public static volatile SingularAttribute<InfInformeValores, String> usuarioCrea;
	public static volatile SingularAttribute<InfInformeValores, String> terminalCrea;
	public static volatile SingularAttribute<InfInformeValores, String> variable;
	public static volatile SingularAttribute<InfInformeValores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<InfInformeValores, Date> fechaHoraModifica;
	public static volatile SingularAttribute<InfInformeValores, Integer> id;
	public static volatile SingularAttribute<InfInformeValores, InfInformeVariables> infInformeVariablesId;
	public static volatile SingularAttribute<InfInformeValores, InfInformeGenerados> infGeneradosId;
	public static volatile SingularAttribute<InfInformeValores, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VARIABLE = "variable";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String INF_INFORME_VARIABLES_ID = "infInformeVariablesId";
	public static final String INF_GENERADOS_ID = "infGeneradosId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

