package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeCargaHistoricos.class)
public abstract class PeCargaHistoricos_ {

	public static volatile SingularAttribute<PeCargaHistoricos, Boolean> tipo;
	public static volatile SingularAttribute<PeCargaHistoricos, Integer> maDiagnosticoPrincipalId;
	public static volatile SingularAttribute<PeCargaHistoricos, String> maDiagnostico3Valor;
	public static volatile SingularAttribute<PeCargaHistoricos, Date> fechaInicioPrograma;
	public static volatile SingularAttribute<PeCargaHistoricos, String> maDiagnostico3Codigo;
	public static volatile SingularAttribute<PeCargaHistoricos, PeAfiliadosProgramas> peAfiliadosProgramasId;
	public static volatile SingularAttribute<PeCargaHistoricos, String> maDiagnosticoPrincipalValor;
	public static volatile SingularAttribute<PeCargaHistoricos, String> maDiagnosticoPrincipalCodigo;
	public static volatile SingularAttribute<PeCargaHistoricos, Date> fechaDiagnostico;
	public static volatile SingularAttribute<PeCargaHistoricos, String> maeRegionCorporalValor;
	public static volatile SingularAttribute<PeCargaHistoricos, Integer> maeRegionCorporalId;
	public static volatile SingularAttribute<PeCargaHistoricos, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<PeCargaHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<PeCargaHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<PeCargaHistoricos, Date> fechaFinPrograma;
	public static volatile SingularAttribute<PeCargaHistoricos, String> maeRegionCorporalCodigo;
	public static volatile SingularAttribute<PeCargaHistoricos, String> maDiagnostico2Valor;
	public static volatile SingularAttribute<PeCargaHistoricos, Integer> maDiagnostico2Id;
	public static volatile SingularAttribute<PeCargaHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeCargaHistoricos, Integer> id;
	public static volatile SingularAttribute<PeCargaHistoricos, Integer> maDiagnostico3Id;
	public static volatile SingularAttribute<PeCargaHistoricos, PeCargas> peCargasId;
	public static volatile SingularAttribute<PeCargaHistoricos, String> maDiagnostico2Codigo;

	public static final String TIPO = "tipo";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_ID = "maDiagnosticoPrincipalId";
	public static final String MA_DIAGNOSTICO3_VALOR = "maDiagnostico3Valor";
	public static final String FECHA_INICIO_PROGRAMA = "fechaInicioPrograma";
	public static final String MA_DIAGNOSTICO3_CODIGO = "maDiagnostico3Codigo";
	public static final String PE_AFILIADOS_PROGRAMAS_ID = "peAfiliadosProgramasId";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_VALOR = "maDiagnosticoPrincipalValor";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_CODIGO = "maDiagnosticoPrincipalCodigo";
	public static final String FECHA_DIAGNOSTICO = "fechaDiagnostico";
	public static final String MAE_REGION_CORPORAL_VALOR = "maeRegionCorporalValor";
	public static final String MAE_REGION_CORPORAL_ID = "maeRegionCorporalId";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_FIN_PROGRAMA = "fechaFinPrograma";
	public static final String MAE_REGION_CORPORAL_CODIGO = "maeRegionCorporalCodigo";
	public static final String MA_DIAGNOSTICO2_VALOR = "maDiagnostico2Valor";
	public static final String MA_DIAGNOSTICO2_ID = "maDiagnostico2Id";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MA_DIAGNOSTICO3_ID = "maDiagnostico3Id";
	public static final String PE_CARGAS_ID = "peCargasId";
	public static final String MA_DIAGNOSTICO2_CODIGO = "maDiagnostico2Codigo";

}

