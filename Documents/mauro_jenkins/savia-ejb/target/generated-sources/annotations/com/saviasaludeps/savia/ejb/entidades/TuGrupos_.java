package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuGrupos.class)
public abstract class TuGrupos_ {

	public static volatile SingularAttribute<TuGrupos, String> descripcion;
	public static volatile SingularAttribute<TuGrupos, Integer> tipoAuditorInicial;
	public static volatile ListAttribute<TuGrupos, TuGrupoEstados> tuGrupoEstadosList;
	public static volatile SingularAttribute<TuGrupos, String> nombre;
	public static volatile ListAttribute<TuGrupos, TuGrupoHistoricos> tuGrupoHistoricosList;
	public static volatile SingularAttribute<TuGrupos, String> terminalModifica;
	public static volatile SingularAttribute<TuGrupos, String> usuarioCrea;
	public static volatile ListAttribute<TuGrupos, TuGrupoUsuarios> tuGrupoUsuariosList;
	public static volatile SingularAttribute<TuGrupos, String> terminalCrea;
	public static volatile SingularAttribute<TuGrupos, Integer> ultimoUsuarioId;
	public static volatile SingularAttribute<TuGrupos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuGrupos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuGrupos, Integer> id;
	public static volatile SingularAttribute<TuGrupos, Integer> orden;
	public static volatile SingularAttribute<TuGrupos, String> usuarioModifica;
	public static volatile SingularAttribute<TuGrupos, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO_AUDITOR_INICIAL = "tipoAuditorInicial";
	public static final String TU_GRUPO_ESTADOS_LIST = "tuGrupoEstadosList";
	public static final String NOMBRE = "nombre";
	public static final String TU_GRUPO_HISTORICOS_LIST = "tuGrupoHistoricosList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TU_GRUPO_USUARIOS_LIST = "tuGrupoUsuariosList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ULTIMO_USUARIO_ID = "ultimoUsuarioId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ORDEN = "orden";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

