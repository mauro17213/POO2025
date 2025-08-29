package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjGrupos.class)
public abstract class CntjGrupos_ {

	public static volatile SingularAttribute<CntjGrupos, String> descripcion;
	public static volatile SingularAttribute<CntjGrupos, String> terminalModifica;
	public static volatile ListAttribute<CntjGrupos, CntjEstadoGrupos> cntjEstadoGruposList;
	public static volatile SingularAttribute<CntjGrupos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjGrupos, String> terminalCrea;
	public static volatile SingularAttribute<CntjGrupos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjGrupos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjGrupos, Integer> id;
	public static volatile SingularAttribute<CntjGrupos, String> nombre;
	public static volatile ListAttribute<CntjGrupos, CntjUsuarioGrupos> cntjUsuarioGruposList;
	public static volatile SingularAttribute<CntjGrupos, String> usuarioModifica;
	public static volatile SingularAttribute<CntjGrupos, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNTJ_ESTADO_GRUPOS_LIST = "cntjEstadoGruposList";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String CNTJ_USUARIO_GRUPOS_LIST = "cntjUsuarioGruposList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

