package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsInhabilitados.class)
public abstract class CmRipsInhabilitados_ {

	public static volatile SingularAttribute<CmRipsInhabilitados, String> motivo;
	public static volatile SingularAttribute<CmRipsInhabilitados, String> maeContratoModalidadCodigo;
	public static volatile SingularAttribute<CmRipsInhabilitados, Integer> maeContratoModalidadId;
	public static volatile SingularAttribute<CmRipsInhabilitados, Date> fechaHoraHasta;
	public static volatile SingularAttribute<CmRipsInhabilitados, Integer> coberturaCierre;
	public static volatile SingularAttribute<CmRipsInhabilitados, String> terminalModifica;
	public static volatile SingularAttribute<CmRipsInhabilitados, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsInhabilitados, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsInhabilitados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsInhabilitados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmRipsInhabilitados, Integer> id;
	public static volatile SingularAttribute<CmRipsInhabilitados, Date> fechaHoraDesde;
	public static volatile SingularAttribute<CmRipsInhabilitados, String> maeContratoModalidadValor;
	public static volatile SingularAttribute<CmRipsInhabilitados, String> usuarioModifica;

	public static final String MOTIVO = "motivo";
	public static final String MAE_CONTRATO_MODALIDAD_CODIGO = "maeContratoModalidadCodigo";
	public static final String MAE_CONTRATO_MODALIDAD_ID = "maeContratoModalidadId";
	public static final String FECHA_HORA_HASTA = "fechaHoraHasta";
	public static final String COBERTURA_CIERRE = "coberturaCierre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_HORA_DESDE = "fechaHoraDesde";
	public static final String MAE_CONTRATO_MODALIDAD_VALOR = "maeContratoModalidadValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

