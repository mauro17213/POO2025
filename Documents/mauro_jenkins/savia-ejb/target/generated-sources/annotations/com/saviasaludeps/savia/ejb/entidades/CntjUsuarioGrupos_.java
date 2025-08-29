package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjUsuarioGrupos.class)
public abstract class CntjUsuarioGrupos_ {

	public static volatile SingularAttribute<CntjUsuarioGrupos, String> terminalModifica;
	public static volatile SingularAttribute<CntjUsuarioGrupos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjUsuarioGrupos, CntjGrupos> cntjGruposId;
	public static volatile SingularAttribute<CntjUsuarioGrupos, String> terminalCrea;
	public static volatile SingularAttribute<CntjUsuarioGrupos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjUsuarioGrupos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjUsuarioGrupos, Integer> id;
	public static volatile SingularAttribute<CntjUsuarioGrupos, String> usuarioModifica;
	public static volatile SingularAttribute<CntjUsuarioGrupos, GnUsuarios> gnUsuariosId;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNTJ_GRUPOS_ID = "cntjGruposId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";

}

