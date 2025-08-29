package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnUbicacionBarrios.class)
public abstract class GnUbicacionBarrios_ {

	public static volatile SingularAttribute<GnUbicacionBarrios, Integer> gnUbicacionesId;
	public static volatile SingularAttribute<GnUbicacionBarrios, String> codigoBarrio;
	public static volatile ListAttribute<GnUbicacionBarrios, AsegAfiliados> asegAfiliadosList;
	public static volatile SingularAttribute<GnUbicacionBarrios, String> nombre;
	public static volatile SingularAttribute<GnUbicacionBarrios, String> codigoComuna;
	public static volatile SingularAttribute<GnUbicacionBarrios, String> terminalModifica;
	public static volatile SingularAttribute<GnUbicacionBarrios, String> usuarioCrea;
	public static volatile SingularAttribute<GnUbicacionBarrios, String> terminalCrea;
	public static volatile SingularAttribute<GnUbicacionBarrios, String> comuna;
	public static volatile SingularAttribute<GnUbicacionBarrios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnUbicacionBarrios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GnUbicacionBarrios, Integer> id;
	public static volatile SingularAttribute<GnUbicacionBarrios, String> usuarioModifica;

	public static final String GN_UBICACIONES_ID = "gnUbicacionesId";
	public static final String CODIGO_BARRIO = "codigoBarrio";
	public static final String ASEG_AFILIADOS_LIST = "asegAfiliadosList";
	public static final String NOMBRE = "nombre";
	public static final String CODIGO_COMUNA = "codigoComuna";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String COMUNA = "comuna";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

