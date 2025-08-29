package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusGrupoUsuarios.class)
public abstract class AusGrupoUsuarios_ {

	public static volatile SingularAttribute<AusGrupoUsuarios, String> terminalModifica;
	public static volatile SingularAttribute<AusGrupoUsuarios, AusGrupos> ausGruposId;
	public static volatile SingularAttribute<AusGrupoUsuarios, String> usuarioCrea;
	public static volatile SingularAttribute<AusGrupoUsuarios, String> terminalCrea;
	public static volatile SingularAttribute<AusGrupoUsuarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusGrupoUsuarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AusGrupoUsuarios, Integer> id;
	public static volatile SingularAttribute<AusGrupoUsuarios, String> usuarioModifica;
	public static volatile SingularAttribute<AusGrupoUsuarios, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<AusGrupoUsuarios, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String AUS_GRUPOS_ID = "ausGruposId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String ACTIVO = "activo";

}

