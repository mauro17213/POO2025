package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RcoActas.class)
public abstract class RcoActas_ {

	public static volatile SingularAttribute<RcoActas, String> area;
	public static volatile ListAttribute<RcoActas, RcoActaAsistentes> rcoActaAsistentesList;
	public static volatile SingularAttribute<RcoActas, RcoConciliaciones> rcoConciliacionesId;
	public static volatile SingularAttribute<RcoActas, String> usuarioCrea;
	public static volatile SingularAttribute<RcoActas, String> desarrolloReunion;
	public static volatile SingularAttribute<RcoActas, String> terminalCrea;
	public static volatile SingularAttribute<RcoActas, String> asunto;
	public static volatile SingularAttribute<RcoActas, String> lugar;
	public static volatile SingularAttribute<RcoActas, String> observaciones;
	public static volatile SingularAttribute<RcoActas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RcoActas, Integer> id;
	public static volatile SingularAttribute<RcoActas, String> orderDelDia;

	public static final String AREA = "area";
	public static final String RCO_ACTA_ASISTENTES_LIST = "rcoActaAsistentesList";
	public static final String RCO_CONCILIACIONES_ID = "rcoConciliacionesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String DESARROLLO_REUNION = "desarrolloReunion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ASUNTO = "asunto";
	public static final String LUGAR = "lugar";
	public static final String OBSERVACIONES = "observaciones";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ORDER_DEL_DIA = "orderDelDia";

}

