package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmGrupoPrestadores.class)
public abstract class CmGrupoPrestadores_ {

	public static volatile SingularAttribute<CmGrupoPrestadores, String> terminalModifica;
	public static volatile SingularAttribute<CmGrupoPrestadores, String> usuarioCrea;
	public static volatile SingularAttribute<CmGrupoPrestadores, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CmGrupoPrestadores, String> terminalCrea;
	public static volatile SingularAttribute<CmGrupoPrestadores, CmGrupos> cmGruposId;
	public static volatile SingularAttribute<CmGrupoPrestadores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmGrupoPrestadores, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmGrupoPrestadores, Integer> id;
	public static volatile SingularAttribute<CmGrupoPrestadores, String> usuarioModifica;
	public static volatile SingularAttribute<CmGrupoPrestadores, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_GRUPOS_ID = "cmGruposId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

