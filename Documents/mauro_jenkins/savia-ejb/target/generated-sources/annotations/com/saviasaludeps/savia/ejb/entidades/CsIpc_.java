package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CsIpc.class)
public abstract class CsIpc_ {

	public static volatile SingularAttribute<CsIpc, String> usuarioCrea;
	public static volatile SingularAttribute<CsIpc, String> terminalCrea;
	public static volatile SingularAttribute<CsIpc, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CsIpc, Integer> agno;
	public static volatile SingularAttribute<CsIpc, Integer> mes;
	public static volatile SingularAttribute<CsIpc, BigDecimal> incremento;
	public static volatile SingularAttribute<CsIpc, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String AGNO = "agno";
	public static final String MES = "mes";
	public static final String INCREMENTO = "incremento";
	public static final String ID = "id";

}

