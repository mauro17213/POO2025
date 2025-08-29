package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpCodigoUnirs.class)
public abstract class MpCodigoUnirs_ {

	public static volatile SingularAttribute<MpCodigoUnirs, String> terminalModifica;
	public static volatile SingularAttribute<MpCodigoUnirs, String> codigo;
	public static volatile SingularAttribute<MpCodigoUnirs, String> usuarioCrea;
	public static volatile SingularAttribute<MpCodigoUnirs, String> terminalCrea;
	public static volatile SingularAttribute<MpCodigoUnirs, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpCodigoUnirs, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MpCodigoUnirs, Integer> id;
	public static volatile ListAttribute<MpCodigoUnirs, MpMedicamentoIndicacionesUnirs> mpMedicamentoIndicacionesUnirsList;
	public static volatile SingularAttribute<MpCodigoUnirs, String> indicacion;
	public static volatile SingularAttribute<MpCodigoUnirs, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CODIGO = "codigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MP_MEDICAMENTO_INDICACIONES_UNIRS_LIST = "mpMedicamentoIndicacionesUnirsList";
	public static final String INDICACION = "indicacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

