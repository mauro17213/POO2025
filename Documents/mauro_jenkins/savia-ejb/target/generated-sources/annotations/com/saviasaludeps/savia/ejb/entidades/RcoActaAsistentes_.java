package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RcoActaAsistentes.class)
public abstract class RcoActaAsistentes_ {

	public static volatile SingularAttribute<RcoActaAsistentes, String> usuarioCrea;
	public static volatile SingularAttribute<RcoActaAsistentes, RcoActas> rcoActasId;
	public static volatile SingularAttribute<RcoActaAsistentes, String> terminalCrea;
	public static volatile SingularAttribute<RcoActaAsistentes, String> observaciones;
	public static volatile SingularAttribute<RcoActaAsistentes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RcoActaAsistentes, Integer> id;
	public static volatile SingularAttribute<RcoActaAsistentes, String> cargo;
	public static volatile SingularAttribute<RcoActaAsistentes, String> nombre;
	public static volatile SingularAttribute<RcoActaAsistentes, String> firma;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String RCO_ACTAS_ID = "rcoActasId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String OBSERVACIONES = "observaciones";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CARGO = "cargo";
	public static final String NOMBRE = "nombre";
	public static final String FIRMA = "firma";

}

