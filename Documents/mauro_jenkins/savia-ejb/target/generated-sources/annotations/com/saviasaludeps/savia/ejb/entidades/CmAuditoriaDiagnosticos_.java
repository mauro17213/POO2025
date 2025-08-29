package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmAuditoriaDiagnosticos.class)
public abstract class CmAuditoriaDiagnosticos_ {

	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, Boolean> principal;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, String> terminalModifica;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, String> usuarioCrea;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, String> terminalCrea;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, CmDetalles> cmDetallesId;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, String> maDiagnosticosCodigo;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, String> maDiagnosticosValor;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, Integer> maDiagniosticosId;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, Integer> id;
	public static volatile SingularAttribute<CmAuditoriaDiagnosticos, String> usuarioModifica;

	public static final String PRINCIPAL = "principal";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_DETALLES_ID = "cmDetallesId";
	public static final String MA_DIAGNOSTICOS_CODIGO = "maDiagnosticosCodigo";
	public static final String MA_DIAGNOSTICOS_VALOR = "maDiagnosticosValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MA_DIAGNIOSTICOS_ID = "maDiagniosticosId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

