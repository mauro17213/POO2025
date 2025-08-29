package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InfGrupos.class)
public abstract class InfGrupos_ {

	public static volatile SingularAttribute<InfGrupos, String> descripcion;
	public static volatile SingularAttribute<InfGrupos, String> terminalModifica;
	public static volatile SingularAttribute<InfGrupos, String> usuarioCrea;
	public static volatile SingularAttribute<InfGrupos, String> terminalCrea;
	public static volatile SingularAttribute<InfGrupos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<InfGrupos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<InfGrupos, Integer> id;
	public static volatile SingularAttribute<InfGrupos, String> nombre;
	public static volatile ListAttribute<InfGrupos, InfInformes> infInformesList;
	public static volatile SingularAttribute<InfGrupos, String> usuarioModifica;
	public static volatile ListAttribute<InfGrupos, InfGrupoUsuarios> infGrupoUsuariosList;

	public static final String DESCRIPCION = "descripcion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String INF_INFORMES_LIST = "infInformesList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String INF_GRUPO_USUARIOS_LIST = "infGrupoUsuariosList";

}

