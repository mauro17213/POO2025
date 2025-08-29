package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InfInformeVariables.class)
public abstract class InfInformeVariables_ {

	public static volatile SingularAttribute<InfInformeVariables, Integer> tipo;
	public static volatile SingularAttribute<InfInformeVariables, Boolean> dinamico;
	public static volatile SingularAttribute<InfInformeVariables, Boolean> fechaAutomatica;
	public static volatile SingularAttribute<InfInformeVariables, String> usuarioCrea;
	public static volatile ListAttribute<InfInformeVariables, InfInformeValores> infInformeValoresList;
	public static volatile SingularAttribute<InfInformeVariables, String> terminalCrea;
	public static volatile SingularAttribute<InfInformeVariables, String> valor;
	public static volatile SingularAttribute<InfInformeVariables, Date> fechaHoraCrea;
	public static volatile SingularAttribute<InfInformeVariables, Integer> id;
	public static volatile SingularAttribute<InfInformeVariables, Integer> orden;
	public static volatile SingularAttribute<InfInformeVariables, String> nombre;
	public static volatile SingularAttribute<InfInformeVariables, InfInformes> infInformesId;

	public static final String TIPO = "tipo";
	public static final String DINAMICO = "dinamico";
	public static final String FECHA_AUTOMATICA = "fechaAutomatica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String INF_INFORME_VALORES_LIST = "infInformeValoresList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR = "valor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ORDEN = "orden";
	public static final String NOMBRE = "nombre";
	public static final String INF_INFORMES_ID = "infInformesId";

}

