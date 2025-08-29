package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpPrestadores.class)
public abstract class MpPrestadores_ {

	public static volatile SingularAttribute<MpPrestadores, String> terminalModifica;
	public static volatile SingularAttribute<MpPrestadores, String> usuarioCrea;
	public static volatile SingularAttribute<MpPrestadores, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<MpPrestadores, String> terminalCrea;
	public static volatile SingularAttribute<MpPrestadores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpPrestadores, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MpPrestadores, Integer> id;
	public static volatile SingularAttribute<MpPrestadores, String> usuarioModifica;
	public static volatile SingularAttribute<MpPrestadores, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

