package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnDivisiones.class)
public abstract class GnDivisiones_ {

	public static volatile SingularAttribute<GnDivisiones, String> codigo;
	public static volatile SingularAttribute<GnDivisiones, GnUbicaciones> gnUbicacionesId;
	public static volatile ListAttribute<GnDivisiones, AsegAfiliados> asegAfiliadosList;
	public static volatile SingularAttribute<GnDivisiones, String> nombre;
	public static volatile SingularAttribute<GnDivisiones, String> terminalModifica;
	public static volatile SingularAttribute<GnDivisiones, String> usuarioCrea;
	public static volatile SingularAttribute<GnDivisiones, String> terminalCrea;
	public static volatile SingularAttribute<GnDivisiones, String> maeZonaValor;
	public static volatile SingularAttribute<GnDivisiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnDivisiones, Integer> maeZonaId;
	public static volatile SingularAttribute<GnDivisiones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GnDivisiones, Integer> id;
	public static volatile SingularAttribute<GnDivisiones, String> maeZonaCodigo;
	public static volatile SingularAttribute<GnDivisiones, String> usuarioModifica;

	public static final String CODIGO = "codigo";
	public static final String GN_UBICACIONES_ID = "gnUbicacionesId";
	public static final String ASEG_AFILIADOS_LIST = "asegAfiliadosList";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_ZONA_VALOR = "maeZonaValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MAE_ZONA_ID = "maeZonaId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_ZONA_CODIGO = "maeZonaCodigo";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

