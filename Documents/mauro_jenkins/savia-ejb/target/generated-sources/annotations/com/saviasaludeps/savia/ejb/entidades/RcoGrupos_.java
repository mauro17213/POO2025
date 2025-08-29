package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RcoGrupos.class)
public abstract class RcoGrupos_ {

	public static volatile SingularAttribute<RcoGrupos, String> descripcion;
	public static volatile SingularAttribute<RcoGrupos, Integer> tipo;
	public static volatile SingularAttribute<RcoGrupos, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<RcoGrupos, String> nombre;
	public static volatile SingularAttribute<RcoGrupos, String> terminalModifica;
	public static volatile SingularAttribute<RcoGrupos, String> usuarioCrea;
	public static volatile SingularAttribute<RcoGrupos, String> terminalCrea;
	public static volatile SingularAttribute<RcoGrupos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RcoGrupos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<RcoGrupos, Integer> id;
	public static volatile ListAttribute<RcoGrupos, RcoGrupoUsuarios> rcoGrupoUsuariosList;
	public static volatile ListAttribute<RcoGrupos, RcoFacturaDetalles> rcoFacturaDetallesList;
	public static volatile SingularAttribute<RcoGrupos, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO = "tipo";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String RCO_GRUPO_USUARIOS_LIST = "rcoGrupoUsuariosList";
	public static final String RCO_FACTURA_DETALLES_LIST = "rcoFacturaDetallesList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

