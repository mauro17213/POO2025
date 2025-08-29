package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusPersonaTelefonos.class)
public abstract class AusPersonaTelefonos_ {

	public static volatile SingularAttribute<AusPersonaTelefonos, String> usuarioCrea;
	public static volatile SingularAttribute<AusPersonaTelefonos, String> numero;
	public static volatile SingularAttribute<AusPersonaTelefonos, String> terminalCrea;
	public static volatile SingularAttribute<AusPersonaTelefonos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusPersonaTelefonos, Integer> id;
	public static volatile SingularAttribute<AusPersonaTelefonos, AusPersonas> ausPersonasId;
	public static volatile SingularAttribute<AusPersonaTelefonos, String> observacion;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String NUMERO = "numero";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String AUS_PERSONAS_ID = "ausPersonasId";
	public static final String OBSERVACION = "observacion";

}

