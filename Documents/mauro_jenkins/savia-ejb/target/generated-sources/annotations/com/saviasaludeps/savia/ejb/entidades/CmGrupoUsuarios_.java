package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmGrupoUsuarios.class)
public abstract class CmGrupoUsuarios_ {

	public static volatile SingularAttribute<CmGrupoUsuarios, String> terminalModifica;
	public static volatile SingularAttribute<CmGrupoUsuarios, Integer> tipo;
	public static volatile SingularAttribute<CmGrupoUsuarios, String> usuarioCrea;
	public static volatile SingularAttribute<CmGrupoUsuarios, String> terminalCrea;
	public static volatile SingularAttribute<CmGrupoUsuarios, CmGrupos> cmGruposId;
	public static volatile SingularAttribute<CmGrupoUsuarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmGrupoUsuarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmGrupoUsuarios, Integer> id;
	public static volatile SingularAttribute<CmGrupoUsuarios, String> usuarioModifica;
	public static volatile SingularAttribute<CmGrupoUsuarios, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<CmGrupoUsuarios, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_GRUPOS_ID = "cmGruposId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String ACTIVO = "activo";

}

