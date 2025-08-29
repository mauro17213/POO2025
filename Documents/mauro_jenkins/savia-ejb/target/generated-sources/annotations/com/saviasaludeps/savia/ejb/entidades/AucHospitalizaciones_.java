package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucHospitalizaciones.class)
public abstract class AucHospitalizaciones_ {

	public static volatile ListAttribute<AucHospitalizaciones, AucHospitalizacionServicios> aucHospitalizacionServiciosList;
	public static volatile ListAttribute<AucHospitalizaciones, AucHospitalizacionObjeciones> aucHospitalizacionObjecionesList;
	public static volatile SingularAttribute<AucHospitalizaciones, AucEgresos> aucEgresosId;
	public static volatile SingularAttribute<AucHospitalizaciones, Integer> estado;
	public static volatile SingularAttribute<AucHospitalizaciones, Integer> diasHospitalizacion;
	public static volatile SingularAttribute<AucHospitalizaciones, Integer> maEspecialidadesId;
	public static volatile ListAttribute<AucHospitalizaciones, AucHospitalizacionSeguimientos> aucHospitalizacionSeguimientosList;
	public static volatile SingularAttribute<AucHospitalizaciones, AucIngresos> aucIngresosId;
	public static volatile SingularAttribute<AucHospitalizaciones, String> maEspecialidadesCodigo;
	public static volatile SingularAttribute<AucHospitalizaciones, String> terminalModifica;
	public static volatile SingularAttribute<AucHospitalizaciones, String> usuarioCrea;
	public static volatile SingularAttribute<AucHospitalizaciones, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<AucHospitalizaciones, Date> fechaUltimaNota;
	public static volatile ListAttribute<AucHospitalizaciones, AucHospitalizacionPatologias> aucHospitalizacionPatologiasList;
	public static volatile SingularAttribute<AucHospitalizaciones, String> terminalCrea;
	public static volatile ListAttribute<AucHospitalizaciones, PeAfiliadosSugeridos> peAfiliadosSugeridosList;
	public static volatile SingularAttribute<AucHospitalizaciones, Integer> aplicaRescate;
	public static volatile SingularAttribute<AucHospitalizaciones, AucAfiliados> aucAfiliadosId;
	public static volatile SingularAttribute<AucHospitalizaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucHospitalizaciones, GnUsuarios> gnUsuariosAuditorId;
	public static volatile SingularAttribute<AucHospitalizaciones, Integer> id;
	public static volatile SingularAttribute<AucHospitalizaciones, Integer> estadoAuditoria;
	public static volatile SingularAttribute<AucHospitalizaciones, String> maEspecialidadesValor;
	public static volatile SingularAttribute<AucHospitalizaciones, Boolean> cierreAuditoria;
	public static volatile SingularAttribute<AucHospitalizaciones, BigDecimal> valorDiarioAcumulado;
	public static volatile SingularAttribute<AucHospitalizaciones, String> usuarioCierreAuditoria;
	public static volatile SingularAttribute<AucHospitalizaciones, GnEmpresas> gnEmpresasId;
	public static volatile ListAttribute<AucHospitalizaciones, AucHospitalizacionAdversos> aucHospitalizacionAdversosList;
	public static volatile ListAttribute<AucHospitalizaciones, AucHospitalizacionInoportunidades> aucHospitalizacionInoportunidadesList;
	public static volatile ListAttribute<AucHospitalizaciones, AucDiagnosticos> aucDiagnosticosList;
	public static volatile ListAttribute<AucHospitalizaciones, AucHospitalizacionEstancias> aucHospitalizacionEstanciasList;
	public static volatile SingularAttribute<AucHospitalizaciones, String> terminalCierreAuditoria;
	public static volatile SingularAttribute<AucHospitalizaciones, Date> fechaHoraCierreAuditoria;
	public static volatile ListAttribute<AucHospitalizaciones, PeAfiliadosProgramas> peAfiliadosProgramasList;
	public static volatile SingularAttribute<AucHospitalizaciones, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<AucHospitalizaciones, Date> fechaInicioHospitalizacion;
	public static volatile ListAttribute<AucHospitalizaciones, AuAnexo2Rescates> auAnexo2RescatesList;
	public static volatile ListAttribute<AucHospitalizaciones, AucJustificacionEstanciasProlongadas> aucJustificacionEstanciasProlongadasList;
	public static volatile ListAttribute<AucHospitalizaciones, AucHospitalizacionEstados> aucHospitalizacionEstadosList;
	public static volatile SingularAttribute<AucHospitalizaciones, Integer> codigoEvento;
	public static volatile SingularAttribute<AucHospitalizaciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AucHospitalizaciones, Date> fechaFinHospitalizacion;
	public static volatile SingularAttribute<AucHospitalizaciones, String> usuarioModifica;
	public static volatile ListAttribute<AucHospitalizaciones, AucHospitalizacionHistoricos> aucHospitalizacionHistoricosList;

	public static final String AUC_HOSPITALIZACION_SERVICIOS_LIST = "aucHospitalizacionServiciosList";
	public static final String AUC_HOSPITALIZACION_OBJECIONES_LIST = "aucHospitalizacionObjecionesList";
	public static final String AUC_EGRESOS_ID = "aucEgresosId";
	public static final String ESTADO = "estado";
	public static final String DIAS_HOSPITALIZACION = "diasHospitalizacion";
	public static final String MA_ESPECIALIDADES_ID = "maEspecialidadesId";
	public static final String AUC_HOSPITALIZACION_SEGUIMIENTOS_LIST = "aucHospitalizacionSeguimientosList";
	public static final String AUC_INGRESOS_ID = "aucIngresosId";
	public static final String MA_ESPECIALIDADES_CODIGO = "maEspecialidadesCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String FECHA_ULTIMA_NOTA = "fechaUltimaNota";
	public static final String AUC_HOSPITALIZACION_PATOLOGIAS_LIST = "aucHospitalizacionPatologiasList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String PE_AFILIADOS_SUGERIDOS_LIST = "peAfiliadosSugeridosList";
	public static final String APLICA_RESCATE = "aplicaRescate";
	public static final String AUC_AFILIADOS_ID = "aucAfiliadosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String GN_USUARIOS_AUDITOR_ID = "gnUsuariosAuditorId";
	public static final String ID = "id";
	public static final String ESTADO_AUDITORIA = "estadoAuditoria";
	public static final String MA_ESPECIALIDADES_VALOR = "maEspecialidadesValor";
	public static final String CIERRE_AUDITORIA = "cierreAuditoria";
	public static final String VALOR_DIARIO_ACUMULADO = "valorDiarioAcumulado";
	public static final String USUARIO_CIERRE_AUDITORIA = "usuarioCierreAuditoria";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String AUC_HOSPITALIZACION_ADVERSOS_LIST = "aucHospitalizacionAdversosList";
	public static final String AUC_HOSPITALIZACION_INOPORTUNIDADES_LIST = "aucHospitalizacionInoportunidadesList";
	public static final String AUC_DIAGNOSTICOS_LIST = "aucDiagnosticosList";
	public static final String AUC_HOSPITALIZACION_ESTANCIAS_LIST = "aucHospitalizacionEstanciasList";
	public static final String TERMINAL_CIERRE_AUDITORIA = "terminalCierreAuditoria";
	public static final String FECHA_HORA_CIERRE_AUDITORIA = "fechaHoraCierreAuditoria";
	public static final String PE_AFILIADOS_PROGRAMAS_LIST = "peAfiliadosProgramasList";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String FECHA_INICIO_HOSPITALIZACION = "fechaInicioHospitalizacion";
	public static final String AU_ANEXO2_RESCATES_LIST = "auAnexo2RescatesList";
	public static final String AUC_JUSTIFICACION_ESTANCIAS_PROLONGADAS_LIST = "aucJustificacionEstanciasProlongadasList";
	public static final String AUC_HOSPITALIZACION_ESTADOS_LIST = "aucHospitalizacionEstadosList";
	public static final String CODIGO_EVENTO = "codigoEvento";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String FECHA_FIN_HOSPITALIZACION = "fechaFinHospitalizacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String AUC_HOSPITALIZACION_HISTORICOS_LIST = "aucHospitalizacionHistoricosList";

}

