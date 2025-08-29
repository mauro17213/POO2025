package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratos.class)
public abstract class CntContratos_ {

	public static volatile SingularAttribute<CntContratos, String> descripcion;
	public static volatile ListAttribute<CntContratos, AucHospitalizacionServicios> aucHospitalizacionServiciosList;
	public static volatile SingularAttribute<CntContratos, Integer> maeRegimenId;
	public static volatile SingularAttribute<CntContratos, BigDecimal> ejecucionContratoPrestado;
	public static volatile ListAttribute<CntContratos, AuAnexos4> auAnexos4List;
	public static volatile ListAttribute<CntContratos, CntContratoAdjuntos> cntContratoAdjuntosList;
	public static volatile ListAttribute<CntContratos, CntContratoHistoricoPrestaciones> cntContratoHistoricoPrestacionesList;
	public static volatile ListAttribute<CntContratos, CntContratoDetalles> cntContratoDetallesList;
	public static volatile SingularAttribute<CntContratos, String> maeEstadoContratoValor;
	public static volatile SingularAttribute<CntContratos, String> terminalModifica;
	public static volatile ListAttribute<CntContratos, CntContratoHistoricos> cntContratoHistoricosList;
	public static volatile SingularAttribute<CntContratos, String> usuarioCrea;
	public static volatile ListAttribute<CntContratos, CntContratoSedes> cntContratoSedesList;
	public static volatile SingularAttribute<CntContratos, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CntContratos, BigDecimal> valorMes;
	public static volatile SingularAttribute<CntContratos, Date> fechaInicio;
	public static volatile SingularAttribute<CntContratos, String> terminalCrea;
	public static volatile SingularAttribute<CntContratos, Boolean> autorizaGestion;
	public static volatile SingularAttribute<CntContratos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratos, Integer> id;
	public static volatile SingularAttribute<CntContratos, Integer> diasLimitePago;
	public static volatile SingularAttribute<CntContratos, String> maeEstadoContratoCodigo;
	public static volatile ListAttribute<CntContratos, CntContratoDescuentos> cntContratoDescuentosList;
	public static volatile SingularAttribute<CntContratos, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<CntContratos, Integer> numAfiliados;
	public static volatile SingularAttribute<CntContratos, String> negociacion;
	public static volatile SingularAttribute<CntContratos, BigDecimal> valor;
	public static volatile ListAttribute<CntContratos, CntContratoJuridicos> cntContratoJuridicosList;
	public static volatile SingularAttribute<CntContratos, String> contrato;
	public static volatile ListAttribute<CntContratos, CntContratoGirosCapita> cntContratoGirosCapitaList;
	public static volatile ListAttribute<CntContratos, CntContratoHistoricoValidaciones> cntContratoHistoricoValidacionesList;
	public static volatile ListAttribute<CntContratos, CntContratoCoberturas> cntContratoCoberturasList;
	public static volatile SingularAttribute<CntContratos, Date> fechaFin;
	public static volatile ListAttribute<CntContratos, CntContratoNotasTecnicas> cntContratoNotasTecnicasList;
	public static volatile SingularAttribute<CntContratos, String> maeRegimenValor;
	public static volatile SingularAttribute<CntContratos, String> maeRegimenCodigo;
	public static volatile SingularAttribute<CntContratos, Integer> maeEstadoContratoId;
	public static volatile SingularAttribute<CntContratos, BigDecimal> ejecucionTotalContrato;
	public static volatile SingularAttribute<CntContratos, BigDecimal> ejecucionContratoAutorizado;
	public static volatile ListAttribute<CntContratos, CmRipsCargas> cmRipsCargasList;
	public static volatile SingularAttribute<CntContratos, Date> fechaHoraModifica;
	public static volatile ListAttribute<CntContratos, CmAuditoriaCapitaDescuentos> cmAuditoriaCapitaDescuentosList;
	public static volatile SingularAttribute<CntContratos, BigDecimal> valorPresupuestoTotal;
	public static volatile ListAttribute<CntContratos, CmFeRipsCargas> cmFeRipsCargasList;
	public static volatile SingularAttribute<CntContratos, String> usuarioModifica;
	public static volatile SingularAttribute<CntContratos, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String AUC_HOSPITALIZACION_SERVICIOS_LIST = "aucHospitalizacionServiciosList";
	public static final String MAE_REGIMEN_ID = "maeRegimenId";
	public static final String EJECUCION_CONTRATO_PRESTADO = "ejecucionContratoPrestado";
	public static final String AU_ANEXOS4_LIST = "auAnexos4List";
	public static final String CNT_CONTRATO_ADJUNTOS_LIST = "cntContratoAdjuntosList";
	public static final String CNT_CONTRATO_HISTORICO_PRESTACIONES_LIST = "cntContratoHistoricoPrestacionesList";
	public static final String CNT_CONTRATO_DETALLES_LIST = "cntContratoDetallesList";
	public static final String MAE_ESTADO_CONTRATO_VALOR = "maeEstadoContratoValor";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNT_CONTRATO_HISTORICOS_LIST = "cntContratoHistoricosList";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_CONTRATO_SEDES_LIST = "cntContratoSedesList";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String VALOR_MES = "valorMes";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AUTORIZA_GESTION = "autorizaGestion";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String DIAS_LIMITE_PAGO = "diasLimitePago";
	public static final String MAE_ESTADO_CONTRATO_CODIGO = "maeEstadoContratoCodigo";
	public static final String CNT_CONTRATO_DESCUENTOS_LIST = "cntContratoDescuentosList";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String NUM_AFILIADOS = "numAfiliados";
	public static final String NEGOCIACION = "negociacion";
	public static final String VALOR = "valor";
	public static final String CNT_CONTRATO_JURIDICOS_LIST = "cntContratoJuridicosList";
	public static final String CONTRATO = "contrato";
	public static final String CNT_CONTRATO_GIROS_CAPITA_LIST = "cntContratoGirosCapitaList";
	public static final String CNT_CONTRATO_HISTORICO_VALIDACIONES_LIST = "cntContratoHistoricoValidacionesList";
	public static final String CNT_CONTRATO_COBERTURAS_LIST = "cntContratoCoberturasList";
	public static final String FECHA_FIN = "fechaFin";
	public static final String CNT_CONTRATO_NOTAS_TECNICAS_LIST = "cntContratoNotasTecnicasList";
	public static final String MAE_REGIMEN_VALOR = "maeRegimenValor";
	public static final String MAE_REGIMEN_CODIGO = "maeRegimenCodigo";
	public static final String MAE_ESTADO_CONTRATO_ID = "maeEstadoContratoId";
	public static final String EJECUCION_TOTAL_CONTRATO = "ejecucionTotalContrato";
	public static final String EJECUCION_CONTRATO_AUTORIZADO = "ejecucionContratoAutorizado";
	public static final String CM_RIPS_CARGAS_LIST = "cmRipsCargasList";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CM_AUDITORIA_CAPITA_DESCUENTOS_LIST = "cmAuditoriaCapitaDescuentosList";
	public static final String VALOR_PRESUPUESTO_TOTAL = "valorPresupuestoTotal";
	public static final String CM_FE_RIPS_CARGAS_LIST = "cmFeRipsCargasList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

