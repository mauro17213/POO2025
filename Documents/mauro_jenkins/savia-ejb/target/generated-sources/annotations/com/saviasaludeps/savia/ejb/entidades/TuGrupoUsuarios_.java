package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuGrupoUsuarios.class)
public abstract class TuGrupoUsuarios_ {

	public static volatile SingularAttribute<TuGrupoUsuarios, String> terminalModifica;
	public static volatile SingularAttribute<TuGrupoUsuarios, Integer> tipo;
	public static volatile SingularAttribute<TuGrupoUsuarios, String> usuarioCrea;
	public static volatile SingularAttribute<TuGrupoUsuarios, String> terminalCrea;
	public static volatile SingularAttribute<TuGrupoUsuarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuGrupoUsuarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuGrupoUsuarios, Integer> id;
	public static volatile SingularAttribute<TuGrupoUsuarios, TuGrupos> tuGruposId;
	public static volatile SingularAttribute<TuGrupoUsuarios, String> usuarioModifica;
	public static volatile SingularAttribute<TuGrupoUsuarios, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<TuGrupoUsuarios, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String TU_GRUPOS_ID = "tuGruposId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String ACTIVO = "activo";

}

