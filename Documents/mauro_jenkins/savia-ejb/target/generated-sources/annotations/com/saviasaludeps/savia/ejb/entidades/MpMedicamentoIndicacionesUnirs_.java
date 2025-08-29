package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpMedicamentoIndicacionesUnirs.class)
public abstract class MpMedicamentoIndicacionesUnirs_ {

	public static volatile SingularAttribute<MpMedicamentoIndicacionesUnirs, String> usuarioCrea;
	public static volatile SingularAttribute<MpMedicamentoIndicacionesUnirs, MpPrescripcionMedicamentos> mpPrescripcionMedicamentosId;
	public static volatile SingularAttribute<MpMedicamentoIndicacionesUnirs, Integer> consecutivoOrden;
	public static volatile SingularAttribute<MpMedicamentoIndicacionesUnirs, String> terminalCrea;
	public static volatile SingularAttribute<MpMedicamentoIndicacionesUnirs, String> codigoIndicacion;
	public static volatile SingularAttribute<MpMedicamentoIndicacionesUnirs, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpMedicamentoIndicacionesUnirs, MpCodigoUnirs> mpCodigoUnirsId;
	public static volatile SingularAttribute<MpMedicamentoIndicacionesUnirs, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MP_PRESCRIPCION_MEDICAMENTOS_ID = "mpPrescripcionMedicamentosId";
	public static final String CONSECUTIVO_ORDEN = "consecutivoOrden";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CODIGO_INDICACION = "codigoIndicacion";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MP_CODIGO_UNIRS_ID = "mpCodigoUnirsId";
	public static final String ID = "id";

}

