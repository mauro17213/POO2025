package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuGrupoUsuarios.class)
public abstract class AuGrupoUsuarios_ {

	public static volatile SingularAttribute<AuGrupoUsuarios, AuGrupos> auGruposId;
	public static volatile SingularAttribute<AuGrupoUsuarios, Integer> tipo;
	public static volatile SingularAttribute<AuGrupoUsuarios, String> maeTipoAuditorCodigo;
	public static volatile SingularAttribute<AuGrupoUsuarios, Integer> maeTipoAuditorId;
	public static volatile SingularAttribute<AuGrupoUsuarios, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<AuGrupoUsuarios, String> maeTipoAuditorValor;
	public static volatile SingularAttribute<AuGrupoUsuarios, String> terminalModifica;
	public static volatile SingularAttribute<AuGrupoUsuarios, String> usuarioCrea;
	public static volatile SingularAttribute<AuGrupoUsuarios, String> terminalCrea;
	public static volatile SingularAttribute<AuGrupoUsuarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuGrupoUsuarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuGrupoUsuarios, Integer> id;
	public static volatile SingularAttribute<AuGrupoUsuarios, String> usuarioModifica;
	public static volatile SingularAttribute<AuGrupoUsuarios, Boolean> activo;

	public static final String AU_GRUPOS_ID = "auGruposId";
	public static final String TIPO = "tipo";
	public static final String MAE_TIPO_AUDITOR_CODIGO = "maeTipoAuditorCodigo";
	public static final String MAE_TIPO_AUDITOR_ID = "maeTipoAuditorId";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String MAE_TIPO_AUDITOR_VALOR = "maeTipoAuditorValor";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

