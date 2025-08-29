package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuPersonasContactos.class)
public abstract class TuPersonasContactos_ {

	public static volatile SingularAttribute<TuPersonasContactos, String> terminalModifica;
	public static volatile SingularAttribute<TuPersonasContactos, String> usuarioCrea;
	public static volatile SingularAttribute<TuPersonasContactos, TuPersonas> tuPersonasId;
	public static volatile SingularAttribute<TuPersonasContactos, String> terminalCrea;
	public static volatile SingularAttribute<TuPersonasContactos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuPersonasContactos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuPersonasContactos, Integer> id;
	public static volatile SingularAttribute<TuPersonasContactos, String> observacion;
	public static volatile SingularAttribute<TuPersonasContactos, String> usuarioModifica;
	public static volatile SingularAttribute<TuPersonasContactos, String> numeroContacto;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TU_PERSONAS_ID = "tuPersonasId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String NUMERO_CONTACTO = "numeroContacto";

}

