package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaEspecialidades.class)
public abstract class MaEspecialidades_ {

	public static volatile SingularAttribute<MaEspecialidades, String> descripcion;
	public static volatile SingularAttribute<MaEspecialidades, String> terminalModifica;
	public static volatile SingularAttribute<MaEspecialidades, String> codigo;
	public static volatile SingularAttribute<MaEspecialidades, String> usuarioCrea;
	public static volatile SingularAttribute<MaEspecialidades, Date> fechaHporaModifica;
	public static volatile SingularAttribute<MaEspecialidades, String> terminalCrea;
	public static volatile SingularAttribute<MaEspecialidades, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaEspecialidades, Integer> id;
	public static volatile SingularAttribute<MaEspecialidades, String> nombre;
	public static volatile SingularAttribute<MaEspecialidades, String> usuarioModifica;
	public static volatile SingularAttribute<MaEspecialidades, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CODIGO = "codigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_HPORA_MODIFICA = "fechaHporaModifica";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

