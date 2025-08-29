package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeAfiliadoDiagnosticos.class)
public abstract class PeAfiliadoDiagnosticos_ {

	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, Boolean> principal;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, String> terminalModifica;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, String> usuarioCrea;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, String> terminalCrea;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, Date> fehaHoraCrea;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, String> maDiagnosticosId;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, String> maDiagnosticosCodigo;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, String> maDiagnosticosValor;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, PeAfiliadosProgramas> peAfiliadosProgramasId;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, Integer> id;
	public static volatile SingularAttribute<PeAfiliadoDiagnosticos, String> usuarioModifica;

	public static final String PRINCIPAL = "principal";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FEHA_HORA_CREA = "fehaHoraCrea";
	public static final String MA_DIAGNOSTICOS_ID = "maDiagnosticosId";
	public static final String MA_DIAGNOSTICOS_CODIGO = "maDiagnosticosCodigo";
	public static final String MA_DIAGNOSTICOS_VALOR = "maDiagnosticosValor";
	public static final String PE_AFILIADOS_PROGRAMAS_ID = "peAfiliadosProgramasId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

