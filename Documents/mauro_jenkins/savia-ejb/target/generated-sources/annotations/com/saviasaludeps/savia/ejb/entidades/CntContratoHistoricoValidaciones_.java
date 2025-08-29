package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoHistoricoValidaciones.class)
public abstract class CntContratoHistoricoValidaciones_ {

	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, CntContratoDetalles> cntContratoDetallesId;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, String> maTecnologiaValor;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, String> cntPrestadoresNumeroDocumento;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, String> cntPrestadoresCodigoMinsalud;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, BigDecimal> valor;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, Integer> maTecnologiaId;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, Date> fechaFin;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, String> cntPrestadorSedesCodigoHabilitacion;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, Integer> tipoTecnologia;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, Date> fechaInicio;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, CntContratoSedes> cntContratoSedesId;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, Integer> tipoManualTarifario;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, String> maManualTarifarioCodigo;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, Integer> maManualTarifarioAgno;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, BigDecimal> porcentajeVariacion;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, Integer> id;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, BigDecimal> valorManual;
	public static volatile SingularAttribute<CntContratoHistoricoValidaciones, CntContratos> cntContratosId;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String CNT_CONTRATO_DETALLES_ID = "cntContratoDetallesId";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String CNT_PRESTADORES_NUMERO_DOCUMENTO = "cntPrestadoresNumeroDocumento";
	public static final String CNT_PRESTADORES_CODIGO_MINSALUD = "cntPrestadoresCodigoMinsalud";
	public static final String VALOR = "valor";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String FECHA_FIN = "fechaFin";
	public static final String CNT_PRESTADOR_SEDES_CODIGO_HABILITACION = "cntPrestadorSedesCodigoHabilitacion";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String CNT_CONTRATO_SEDES_ID = "cntContratoSedesId";
	public static final String TIPO_MANUAL_TARIFARIO = "tipoManualTarifario";
	public static final String MA_MANUAL_TARIFARIO_CODIGO = "maManualTarifarioCodigo";
	public static final String MA_MANUAL_TARIFARIO_AGNO = "maManualTarifarioAgno";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String PORCENTAJE_VARIACION = "porcentajeVariacion";
	public static final String ID = "id";
	public static final String VALOR_MANUAL = "valorManual";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

