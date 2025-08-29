package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucDiagnosticos.class)
public abstract class AucDiagnosticos_ {

	public static volatile SingularAttribute<AucDiagnosticos, AucEgresos> aucEgresosId;
	public static volatile SingularAttribute<AucDiagnosticos, Integer> maeTipoDiagnosticoId;
	public static volatile SingularAttribute<AucDiagnosticos, AucHospitalizaciones> aucHospitalizacionId;
	public static volatile SingularAttribute<AucDiagnosticos, String> maeTipoDiagnosticoCodigo;
	public static volatile SingularAttribute<AucDiagnosticos, AucIngresos> aucIngresosId;
	public static volatile SingularAttribute<AucDiagnosticos, String> maeTipoDiagnosticoValor;
	public static volatile SingularAttribute<AucDiagnosticos, Boolean> principal;
	public static volatile SingularAttribute<AucDiagnosticos, String> usuarioCrea;
	public static volatile SingularAttribute<AucDiagnosticos, String> terminalCrea;
	public static volatile SingularAttribute<AucDiagnosticos, String> maDiagnosticoCodigo;
	public static volatile SingularAttribute<AucDiagnosticos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucDiagnosticos, Integer> id;
	public static volatile SingularAttribute<AucDiagnosticos, Integer> maDiagnosticoId;
	public static volatile SingularAttribute<AucDiagnosticos, String> maDiagnosticoValor;

	public static final String AUC_EGRESOS_ID = "aucEgresosId";
	public static final String MAE_TIPO_DIAGNOSTICO_ID = "maeTipoDiagnosticoId";
	public static final String AUC_HOSPITALIZACION_ID = "aucHospitalizacionId";
	public static final String MAE_TIPO_DIAGNOSTICO_CODIGO = "maeTipoDiagnosticoCodigo";
	public static final String AUC_INGRESOS_ID = "aucIngresosId";
	public static final String MAE_TIPO_DIAGNOSTICO_VALOR = "maeTipoDiagnosticoValor";
	public static final String PRINCIPAL = "principal";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_DIAGNOSTICO_CODIGO = "maDiagnosticoCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MA_DIAGNOSTICO_ID = "maDiagnosticoId";
	public static final String MA_DIAGNOSTICO_VALOR = "maDiagnosticoValor";

}

