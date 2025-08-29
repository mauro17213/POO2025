package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuTutelaDiagnosticos.class)
public abstract class TuTutelaDiagnosticos_ {

	public static volatile SingularAttribute<TuTutelaDiagnosticos, Integer> maeTipoDiagnosticoId;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, Boolean> esPrincipal;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, String> maeTipoDiagnosticoCodigo;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, TuTutelaItems> tuTutelaItemsId;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, String> maeTipoDiagnosticoValor;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, String> terminalModifica;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, String> usuarioCrea;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, String> terminalCrea;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, Integer> maDiagnosticosId;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, String> maDiagnosticosCodigo;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, String> maDiagnosticosValor;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, Integer> id;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, TuTutelas> tuTutelasId;
	public static volatile SingularAttribute<TuTutelaDiagnosticos, String> usuarioModifica;

	public static final String MAE_TIPO_DIAGNOSTICO_ID = "maeTipoDiagnosticoId";
	public static final String ES_PRINCIPAL = "esPrincipal";
	public static final String MAE_TIPO_DIAGNOSTICO_CODIGO = "maeTipoDiagnosticoCodigo";
	public static final String TU_TUTELA_ITEMS_ID = "tuTutelaItemsId";
	public static final String MAE_TIPO_DIAGNOSTICO_VALOR = "maeTipoDiagnosticoValor";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_DIAGNOSTICOS_ID = "maDiagnosticosId";
	public static final String MA_DIAGNOSTICOS_CODIGO = "maDiagnosticosCodigo";
	public static final String MA_DIAGNOSTICOS_VALOR = "maDiagnosticosValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String TU_TUTELAS_ID = "tuTutelasId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

