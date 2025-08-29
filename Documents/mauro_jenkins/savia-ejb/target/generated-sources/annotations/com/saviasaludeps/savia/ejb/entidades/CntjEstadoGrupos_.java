package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjEstadoGrupos.class)
public abstract class CntjEstadoGrupos_ {

	public static volatile SingularAttribute<CntjEstadoGrupos, String> terminalModifica;
	public static volatile SingularAttribute<CntjEstadoGrupos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjEstadoGrupos, CntjGrupos> cntjGruposId;
	public static volatile SingularAttribute<CntjEstadoGrupos, String> terminalCrea;
	public static volatile SingularAttribute<CntjEstadoGrupos, CntjEstados> cntjEstadosId;
	public static volatile SingularAttribute<CntjEstadoGrupos, Boolean> ejecucion;
	public static volatile SingularAttribute<CntjEstadoGrupos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjEstadoGrupos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjEstadoGrupos, Integer> id;
	public static volatile SingularAttribute<CntjEstadoGrupos, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNTJ_GRUPOS_ID = "cntjGruposId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_ESTADOS_ID = "cntjEstadosId";
	public static final String EJECUCION = "ejecucion";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

