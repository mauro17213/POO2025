package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuGrupoDiagnosticos.class)
public abstract class AuGrupoDiagnosticos_ {

	public static volatile SingularAttribute<AuGrupoDiagnosticos, AuGrupos> auGruposId;
	public static volatile SingularAttribute<AuGrupoDiagnosticos, String> terminalModifica;
	public static volatile SingularAttribute<AuGrupoDiagnosticos, String> usuarioCrea;
	public static volatile SingularAttribute<AuGrupoDiagnosticos, String> terminalCrea;
	public static volatile SingularAttribute<AuGrupoDiagnosticos, String> maDiagnosticoCodigo;
	public static volatile SingularAttribute<AuGrupoDiagnosticos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuGrupoDiagnosticos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuGrupoDiagnosticos, Integer> id;
	public static volatile SingularAttribute<AuGrupoDiagnosticos, Integer> maDiagnosticoId;
	public static volatile SingularAttribute<AuGrupoDiagnosticos, String> usuarioModifica;
	public static volatile SingularAttribute<AuGrupoDiagnosticos, String> maDiagnosticoValor;

	public static final String AU_GRUPOS_ID = "auGruposId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_DIAGNOSTICO_CODIGO = "maDiagnosticoCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MA_DIAGNOSTICO_ID = "maDiagnosticoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String MA_DIAGNOSTICO_VALOR = "maDiagnosticoValor";

}

