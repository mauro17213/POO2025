package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BasesDatos.class)
public abstract class BasesDatos_ {

	public static volatile SingularAttribute<BasesDatos, String> terminalModifica;
	public static volatile SingularAttribute<BasesDatos, Integer> tipo;
	public static volatile SingularAttribute<BasesDatos, String> usuarioCrea;
	public static volatile SingularAttribute<BasesDatos, String> terminalCrea;
	public static volatile SingularAttribute<BasesDatos, String> cadena;
	public static volatile SingularAttribute<BasesDatos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<BasesDatos, String> usuario;
	public static volatile SingularAttribute<BasesDatos, String> contrasena;
	public static volatile SingularAttribute<BasesDatos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<BasesDatos, Integer> id;
	public static volatile SingularAttribute<BasesDatos, String> nombre;
	public static volatile SingularAttribute<BasesDatos, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CADENA = "cadena";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String USUARIO = "usuario";
	public static final String CONTRASENA = "contrasena";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

