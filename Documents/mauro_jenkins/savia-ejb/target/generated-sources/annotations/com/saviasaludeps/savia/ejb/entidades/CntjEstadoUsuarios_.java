package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjEstadoUsuarios.class)
public abstract class CntjEstadoUsuarios_ {

	public static volatile SingularAttribute<CntjEstadoUsuarios, String> terminalModifica;
	public static volatile SingularAttribute<CntjEstadoUsuarios, String> usuarioCrea;
	public static volatile SingularAttribute<CntjEstadoUsuarios, String> terminalCrea;
	public static volatile SingularAttribute<CntjEstadoUsuarios, CntjEstados> cntjEstadosId;
	public static volatile SingularAttribute<CntjEstadoUsuarios, Boolean> ejecucion;
	public static volatile SingularAttribute<CntjEstadoUsuarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjEstadoUsuarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjEstadoUsuarios, Integer> id;
	public static volatile SingularAttribute<CntjEstadoUsuarios, String> usuarioModifica;
	public static volatile SingularAttribute<CntjEstadoUsuarios, GnUsuarios> gnUsuariosId;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_ESTADOS_ID = "cntjEstadosId";
	public static final String EJECUCION = "ejecucion";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";

}

