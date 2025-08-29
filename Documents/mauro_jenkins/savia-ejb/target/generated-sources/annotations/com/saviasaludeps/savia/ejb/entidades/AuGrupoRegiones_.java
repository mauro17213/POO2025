package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuGrupoRegiones.class)
public abstract class AuGrupoRegiones_ {

	public static volatile SingularAttribute<AuGrupoRegiones, AuGrupos> auGruposId;
	public static volatile SingularAttribute<AuGrupoRegiones, String> terminalModifica;
	public static volatile SingularAttribute<AuGrupoRegiones, String> usuarioCrea;
	public static volatile SingularAttribute<AuGrupoRegiones, String> maeRegionValor;
	public static volatile SingularAttribute<AuGrupoRegiones, String> terminalCrea;
	public static volatile SingularAttribute<AuGrupoRegiones, Integer> maeRegionId;
	public static volatile SingularAttribute<AuGrupoRegiones, String> maeRegionCodigo;
	public static volatile SingularAttribute<AuGrupoRegiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuGrupoRegiones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuGrupoRegiones, Integer> id;
	public static volatile SingularAttribute<AuGrupoRegiones, String> usuarioModifica;

	public static final String AU_GRUPOS_ID = "auGruposId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_REGION_VALOR = "maeRegionValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_REGION_ID = "maeRegionId";
	public static final String MAE_REGION_CODIGO = "maeRegionCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

