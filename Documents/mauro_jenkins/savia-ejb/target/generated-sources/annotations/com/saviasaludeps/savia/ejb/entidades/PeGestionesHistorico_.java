package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeGestionesHistorico.class)
public abstract class PeGestionesHistorico_ {

	public static volatile SingularAttribute<PeGestionesHistorico, String> descripcion;
	public static volatile SingularAttribute<PeGestionesHistorico, PeGestiones> peGestionesId;
	public static volatile SingularAttribute<PeGestionesHistorico, String> usuarioCrea;
	public static volatile SingularAttribute<PeGestionesHistorico, String> terminalCrea;
	public static volatile SingularAttribute<PeGestionesHistorico, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeGestionesHistorico, Integer> id;

	public static final String DESCRIPCION = "descripcion";
	public static final String PE_GESTIONES_ID = "peGestionesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

