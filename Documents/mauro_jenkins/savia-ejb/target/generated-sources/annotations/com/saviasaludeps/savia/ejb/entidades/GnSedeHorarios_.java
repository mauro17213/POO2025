package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnSedeHorarios.class)
public abstract class GnSedeHorarios_ {

	public static volatile SingularAttribute<GnSedeHorarios, Date> horaHasta;
	public static volatile SingularAttribute<GnSedeHorarios, String> terminalModifica;
	public static volatile SingularAttribute<GnSedeHorarios, String> usuarioCrea;
	public static volatile SingularAttribute<GnSedeHorarios, Date> horaDesde;
	public static volatile SingularAttribute<GnSedeHorarios, String> terminalCrea;
	public static volatile SingularAttribute<GnSedeHorarios, String> dias;
	public static volatile SingularAttribute<GnSedeHorarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnSedeHorarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GnSedeHorarios, Integer> id;
	public static volatile SingularAttribute<GnSedeHorarios, GnSedes> gnSedesId;
	public static volatile SingularAttribute<GnSedeHorarios, String> usuarioModifica;
	public static volatile SingularAttribute<GnSedeHorarios, Boolean> activo;

	public static final String HORA_HASTA = "horaHasta";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String HORA_DESDE = "horaDesde";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String DIAS = "dias";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String GN_SEDES_ID = "gnSedesId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

