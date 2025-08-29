package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaRelacionTipos.class)
public abstract class MaRelacionTipos_ {

	public static volatile SingularAttribute<MaRelacionTipos, String> descripcion;
	public static volatile SingularAttribute<MaRelacionTipos, String> usuarioCrea;
	public static volatile ListAttribute<MaRelacionTipos, MaRelaciones> maRelacionesList;
	public static volatile SingularAttribute<MaRelacionTipos, String> terminalCrea;
	public static volatile SingularAttribute<MaRelacionTipos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaRelacionTipos, Integer> id;
	public static volatile SingularAttribute<MaRelacionTipos, String> nombre;

	public static final String DESCRIPCION = "descripcion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MA_RELACIONES_LIST = "maRelacionesList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";

}

