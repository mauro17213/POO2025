package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoSedes.class)
public abstract class CntContratoSedes_ {

	public static volatile ListAttribute<CntContratoSedes, AucHospitalizacionServicios> aucHospitalizacionServiciosList;
	public static volatile SingularAttribute<CntContratoSedes, Integer> nivelAtencion;
	public static volatile SingularAttribute<CntContratoSedes, Boolean> aplicaAgendamiento;
	public static volatile SingularAttribute<CntContratoSedes, Boolean> aplicaContribuitivo;
	public static volatile ListAttribute<CntContratoSedes, CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList;
	public static volatile ListAttribute<CntContratoSedes, CntContratoDetalles> cntContratoDetallesList;
	public static volatile SingularAttribute<CntContratoSedes, Integer> complejidad;
	public static volatile SingularAttribute<CntContratoSedes, BigDecimal> valorContrato;
	public static volatile SingularAttribute<CntContratoSedes, String> terminalModifica;
	public static volatile ListAttribute<CntContratoSedes, CntContratoHistoricos> cntContratoHistoricosList;
	public static volatile SingularAttribute<CntContratoSedes, String> usuarioCrea;
	public static volatile SingularAttribute<CntContratoSedes, BigDecimal> valorUpcAfiliado;
	public static volatile SingularAttribute<CntContratoSedes, Date> fechaInicio;
	public static volatile SingularAttribute<CntContratoSedes, String> terminalCrea;
	public static volatile SingularAttribute<CntContratoSedes, Boolean> aplicaSubsidiado;
	public static volatile SingularAttribute<CntContratoSedes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoSedes, Integer> id;
	public static volatile SingularAttribute<CntContratoSedes, Integer> maeModalidadContratoId;
	public static volatile SingularAttribute<CntContratoSedes, String> maeModalidadContratoValor;
	public static volatile SingularAttribute<CntContratoSedes, String> observacion;
	public static volatile SingularAttribute<CntContratoSedes, Boolean> aplicaPortabilidad;
	public static volatile SingularAttribute<CntContratoSedes, Boolean> aplicaRecaudoCopagosIps;
	public static volatile SingularAttribute<CntContratoSedes, Integer> numAfiliados;
	public static volatile SingularAttribute<CntContratoSedes, Boolean> aplicaGlosaExtemporanea;
	public static volatile SingularAttribute<CntContratoSedes, String> maeModalidadContratoCodigo;
	public static volatile ListAttribute<CntContratoSedes, CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList;
	public static volatile ListAttribute<CntContratoSedes, CntContratoCoberturas> cntContratoCoberturasList;
	public static volatile SingularAttribute<CntContratoSedes, Date> fechaFin;
	public static volatile SingularAttribute<CntContratoSedes, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<CntContratoSedes, Boolean> aplicaPac;
	public static volatile SingularAttribute<CntContratoSedes, String> cucon;
	public static volatile SingularAttribute<CntContratoSedes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntContratoSedes, Boolean> aplicaAutorizacion;
	public static volatile SingularAttribute<CntContratoSedes, Boolean> aplicaAuditoria;
	public static volatile SingularAttribute<CntContratoSedes, String> usuarioModifica;
	public static volatile SingularAttribute<CntContratoSedes, CntContratos> cntContratosId;

	public static final String AUC_HOSPITALIZACION_SERVICIOS_LIST = "aucHospitalizacionServiciosList";
	public static final String NIVEL_ATENCION = "nivelAtencion";
	public static final String APLICA_AGENDAMIENTO = "aplicaAgendamiento";
	public static final String APLICA_CONTRIBUITIVO = "aplicaContribuitivo";
	public static final String CNT_CONTRATO_HISTORICO_PRESTACIONES_LIST = "cntContratoHistoricoPrestacionesList";
	public static final String CNT_CONTRATO_DETALLES_LIST = "cntContratoDetallesList";
	public static final String COMPLEJIDAD = "complejidad";
	public static final String VALOR_CONTRATO = "valorContrato";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNT_CONTRATO_HISTORICOS_LIST = "cntContratoHistoricosList";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String VALOR_UPC_AFILIADO = "valorUpcAfiliado";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String APLICA_SUBSIDIADO = "aplicaSubsidiado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_MODALIDAD_CONTRATO_ID = "maeModalidadContratoId";
	public static final String MAE_MODALIDAD_CONTRATO_VALOR = "maeModalidadContratoValor";
	public static final String OBSERVACION = "observacion";
	public static final String APLICA_PORTABILIDAD = "aplicaPortabilidad";
	public static final String APLICA_RECAUDO_COPAGOS_IPS = "aplicaRecaudoCopagosIps";
	public static final String NUM_AFILIADOS = "numAfiliados";
	public static final String APLICA_GLOSA_EXTEMPORANEA = "aplicaGlosaExtemporanea";
	public static final String MAE_MODALIDAD_CONTRATO_CODIGO = "maeModalidadContratoCodigo";
	public static final String CNT_CONTRATO_HISTORICO_VALIDACIONES_LIST = "cntContratoHistoricoValidacionesList";
	public static final String CNT_CONTRATO_COBERTURAS_LIST = "cntContratoCoberturasList";
	public static final String FECHA_FIN = "fechaFin";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String APLICA_PAC = "aplicaPac";
	public static final String CUCON = "cucon";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String APLICA_AUTORIZACION = "aplicaAutorizacion";
	public static final String APLICA_AUDITORIA = "aplicaAuditoria";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

