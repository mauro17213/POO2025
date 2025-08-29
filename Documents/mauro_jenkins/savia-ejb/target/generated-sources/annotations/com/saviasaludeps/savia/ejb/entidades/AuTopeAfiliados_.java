package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuTopeAfiliados.class)
public abstract class AuTopeAfiliados_ {

	public static volatile SingularAttribute<AuTopeAfiliados, String> terminalModifica;
	public static volatile SingularAttribute<AuTopeAfiliados, String> justificacionActivo;
	public static volatile SingularAttribute<AuTopeAfiliados, String> usuarioCrea;
	public static volatile SingularAttribute<AuTopeAfiliados, String> terminalCrea;
	public static volatile SingularAttribute<AuTopeAfiliados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuTopeAfiliados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuTopeAfiliados, AsegAfiliados> aseAfiliadosId;
	public static volatile SingularAttribute<AuTopeAfiliados, Integer> id;
	public static volatile SingularAttribute<AuTopeAfiliados, String> usuarioModifica;
	public static volatile SingularAttribute<AuTopeAfiliados, Boolean> activo;
	public static volatile SingularAttribute<AuTopeAfiliados, String> justificacionInactivo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String JUSTIFICACION_ACTIVO = "justificacionActivo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ASE_AFILIADOS_ID = "aseAfiliadosId";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";
	public static final String JUSTIFICACION_INACTIVO = "justificacionInactivo";

}

