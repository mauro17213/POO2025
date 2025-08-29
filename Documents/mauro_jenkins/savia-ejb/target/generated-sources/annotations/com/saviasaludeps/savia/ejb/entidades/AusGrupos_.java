package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusGrupos.class)
public abstract class AusGrupos_ {

	public static volatile SingularAttribute<AusGrupos, String> descripcion;
	public static volatile SingularAttribute<AusGrupos, String> terminalModifica;
	public static volatile SingularAttribute<AusGrupos, Integer> tipo;
	public static volatile SingularAttribute<AusGrupos, String> usuarioCrea;
	public static volatile SingularAttribute<AusGrupos, String> terminalCrea;
	public static volatile ListAttribute<AusGrupos, AusGrupoUsuarios> ausGrupoUsuariosList;
	public static volatile SingularAttribute<AusGrupos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusGrupos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AusGrupos, Integer> id;
	public static volatile SingularAttribute<AusGrupos, String> nombre;
	public static volatile SingularAttribute<AusGrupos, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AUS_GRUPO_USUARIOS_LIST = "ausGrupoUsuariosList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

