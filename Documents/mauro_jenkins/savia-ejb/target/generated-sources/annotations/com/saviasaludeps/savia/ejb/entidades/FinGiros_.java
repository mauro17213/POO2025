package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FinGiros.class)
public abstract class FinGiros_ {

	public static volatile SingularAttribute<FinGiros, Integer> tipo;
	public static volatile SingularAttribute<FinGiros, Date> fechaHoraBorra;
	public static volatile SingularAttribute<FinGiros, String> usuarioBorra;
	public static volatile SingularAttribute<FinGiros, String> nombre;
	public static volatile SingularAttribute<FinGiros, String> terminalModifica;
	public static volatile SingularAttribute<FinGiros, String> usuarioCrea;
	public static volatile SingularAttribute<FinGiros, String> terminalCrea;
	public static volatile SingularAttribute<FinGiros, Boolean> borrado;
	public static volatile ListAttribute<FinGiros, FinPostulaciones> finPostulacionesList;
	public static volatile SingularAttribute<FinGiros, Date> fechaHoraCrea;
	public static volatile SingularAttribute<FinGiros, Date> fechaHoraModifica;
	public static volatile SingularAttribute<FinGiros, String> terminalBorra;
	public static volatile SingularAttribute<FinGiros, Integer> id;
	public static volatile SingularAttribute<FinGiros, String> usuarioModifica;

	public static final String TIPO = "tipo";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String FIN_POSTULACIONES_LIST = "finPostulacionesList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

