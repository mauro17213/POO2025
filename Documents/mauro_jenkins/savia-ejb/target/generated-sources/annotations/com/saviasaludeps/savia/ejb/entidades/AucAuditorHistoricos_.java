package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucAuditorHistoricos.class)
public abstract class AucAuditorHistoricos_ {

	public static volatile SingularAttribute<AucAuditorHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<AucAuditorHistoricos, AucAuditores> aucAuditoresId;
	public static volatile SingularAttribute<AucAuditorHistoricos, Date> fechaInicio;
	public static volatile SingularAttribute<AucAuditorHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<AucAuditorHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucAuditorHistoricos, Integer> id;
	public static volatile SingularAttribute<AucAuditorHistoricos, Date> fechaFin;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String AUC_AUDITORES_ID = "aucAuditoresId";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String FECHA_FIN = "fechaFin";

}

