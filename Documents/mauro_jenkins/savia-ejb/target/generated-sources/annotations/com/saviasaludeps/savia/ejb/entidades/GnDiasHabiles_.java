package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnDiasHabiles.class)
public abstract class GnDiasHabiles_ {

	public static volatile SingularAttribute<GnDiasHabiles, Date> fecha;
	public static volatile SingularAttribute<GnDiasHabiles, String> usuarioCrea;
	public static volatile SingularAttribute<GnDiasHabiles, String> terminalCrea;
	public static volatile SingularAttribute<GnDiasHabiles, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnDiasHabiles, Integer> agno;
	public static volatile SingularAttribute<GnDiasHabiles, Integer> id;
	public static volatile SingularAttribute<GnDiasHabiles, Boolean> habil;

	public static final String FECHA = "fecha";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String AGNO = "agno";
	public static final String ID = "id";
	public static final String HABIL = "habil";

}

