package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InfProcedimientos.class)
public abstract class InfProcedimientos_ {

	public static volatile SingularAttribute<InfProcedimientos, String> descripcion;
	public static volatile SingularAttribute<InfProcedimientos, Boolean> exitoso;
	public static volatile SingularAttribute<InfProcedimientos, String> terminalCrea;
	public static volatile SingularAttribute<InfProcedimientos, String> usuariosCrear;
	public static volatile SingularAttribute<InfProcedimientos, String> nombreScript;
	public static volatile SingularAttribute<InfProcedimientos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<InfProcedimientos, Integer> id;
	public static volatile SingularAttribute<InfProcedimientos, byte[]> script;

	public static final String DESCRIPCION = "descripcion";
	public static final String EXITOSO = "exitoso";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String USUARIOS_CREAR = "usuariosCrear";
	public static final String NOMBRE_SCRIPT = "nombreScript";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String SCRIPT = "script";

}

