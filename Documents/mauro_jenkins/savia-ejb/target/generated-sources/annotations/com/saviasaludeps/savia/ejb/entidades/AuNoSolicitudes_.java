package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuNoSolicitudes.class)
public abstract class AuNoSolicitudes_ {

	public static volatile SingularAttribute<AuNoSolicitudes, Date> fechaOrdenMedica;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> estado;
	public static volatile SingularAttribute<AuNoSolicitudes, String> estadoJustificacion;
	public static volatile SingularAttribute<AuNoSolicitudes, String> maeMotivoSinAutorizacionValor;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> maServicioHabilitacionId;
	public static volatile SingularAttribute<AuNoSolicitudes, String> justificacionClinica;
	public static volatile SingularAttribute<AuNoSolicitudes, String> maeMotivoSinAutorizacionCodigo;
	public static volatile SingularAttribute<AuNoSolicitudes, String> maeMotivoSinAutorizacionTipo;
	public static volatile SingularAttribute<AuNoSolicitudes, String> terminalModifica;
	public static volatile SingularAttribute<AuNoSolicitudes, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AuNoSolicitudes, String> usuarioCrea;
	public static volatile SingularAttribute<AuNoSolicitudes, String> terminalCrea;
	public static volatile SingularAttribute<AuNoSolicitudes, String> maeAmbitoAtencionTipo;
	public static volatile SingularAttribute<AuNoSolicitudes, String> maServicioHabilitacionValor;
	public static volatile SingularAttribute<AuNoSolicitudes, Date> fechaHoraCrea;
	public static volatile ListAttribute<AuNoSolicitudes, AuNoSolicitudEntregas> auNoSolicitudEntregasList;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> cntPrestadorSedeEntregaId;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> id;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> auNoSolicitudCargasId;
	public static volatile SingularAttribute<AuNoSolicitudes, String> maEspecialidadCodigo;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> maeMotivoSinAutorizacionId;
	public static volatile SingularAttribute<AuNoSolicitudes, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> fuenteOrigen;
	public static volatile SingularAttribute<AuNoSolicitudes, String> maEspecialidadValor;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> cntPrestadorEntregaId;
	public static volatile SingularAttribute<AuNoSolicitudes, String> maeAmbitoAtencionCodigo;
	public static volatile SingularAttribute<AuNoSolicitudes, CntProfesionales> cntProfesionalesId;
	public static volatile SingularAttribute<AuNoSolicitudes, String> maeAmbitoAtencionValor;
	public static volatile ListAttribute<AuNoSolicitudes, AuNoSolicitudDiagnosticos> auNoSolicitudDiagnosticosList;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> maeAmbitoAtencionId;
	public static volatile ListAttribute<AuNoSolicitudes, AuNoSolicitudCargaDetalles> auNoSolicitudCargaDetallesList;
	public static volatile SingularAttribute<AuNoSolicitudes, CntPrestadores> cntPrestadorId;
	public static volatile ListAttribute<AuNoSolicitudes, AuNoSolicitudHistoricos> auNoSolicitudHistoricosList;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> cntPrestadorSedesId;
	public static volatile SingularAttribute<AuNoSolicitudes, String> maServicioHabilitacionCodigo;
	public static volatile ListAttribute<AuNoSolicitudes, AuSolicitudAdjuntos> auSolicitudAdjuntosList;
	public static volatile SingularAttribute<AuNoSolicitudes, String> consecutivoOrden;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> tipoFormula;
	public static volatile SingularAttribute<AuNoSolicitudes, Integer> maEspecialidadId;
	public static volatile SingularAttribute<AuNoSolicitudes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuNoSolicitudes, Boolean> tutela;
	public static volatile SingularAttribute<AuNoSolicitudes, String> usuarioModifica;
	public static volatile ListAttribute<AuNoSolicitudes, AuNoSolicitudItems> auNoSolicitudItemsList;

	public static final String FECHA_ORDEN_MEDICA = "fechaOrdenMedica";
	public static final String ESTADO = "estado";
	public static final String ESTADO_JUSTIFICACION = "estadoJustificacion";
	public static final String MAE_MOTIVO_SIN_AUTORIZACION_VALOR = "maeMotivoSinAutorizacionValor";
	public static final String MA_SERVICIO_HABILITACION_ID = "maServicioHabilitacionId";
	public static final String JUSTIFICACION_CLINICA = "justificacionClinica";
	public static final String MAE_MOTIVO_SIN_AUTORIZACION_CODIGO = "maeMotivoSinAutorizacionCodigo";
	public static final String MAE_MOTIVO_SIN_AUTORIZACION_TIPO = "maeMotivoSinAutorizacionTipo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_AMBITO_ATENCION_TIPO = "maeAmbitoAtencionTipo";
	public static final String MA_SERVICIO_HABILITACION_VALOR = "maServicioHabilitacionValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String AU_NO_SOLICITUD_ENTREGAS_LIST = "auNoSolicitudEntregasList";
	public static final String CNT_PRESTADOR_SEDE_ENTREGA_ID = "cntPrestadorSedeEntregaId";
	public static final String ID = "id";
	public static final String AU_NO_SOLICITUD_CARGAS_ID = "auNoSolicitudCargasId";
	public static final String MA_ESPECIALIDAD_CODIGO = "maEspecialidadCodigo";
	public static final String MAE_MOTIVO_SIN_AUTORIZACION_ID = "maeMotivoSinAutorizacionId";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String FUENTE_ORIGEN = "fuenteOrigen";
	public static final String MA_ESPECIALIDAD_VALOR = "maEspecialidadValor";
	public static final String CNT_PRESTADOR_ENTREGA_ID = "cntPrestadorEntregaId";
	public static final String MAE_AMBITO_ATENCION_CODIGO = "maeAmbitoAtencionCodigo";
	public static final String CNT_PROFESIONALES_ID = "cntProfesionalesId";
	public static final String MAE_AMBITO_ATENCION_VALOR = "maeAmbitoAtencionValor";
	public static final String AU_NO_SOLICITUD_DIAGNOSTICOS_LIST = "auNoSolicitudDiagnosticosList";
	public static final String MAE_AMBITO_ATENCION_ID = "maeAmbitoAtencionId";
	public static final String AU_NO_SOLICITUD_CARGA_DETALLES_LIST = "auNoSolicitudCargaDetallesList";
	public static final String CNT_PRESTADOR_ID = "cntPrestadorId";
	public static final String AU_NO_SOLICITUD_HISTORICOS_LIST = "auNoSolicitudHistoricosList";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String MA_SERVICIO_HABILITACION_CODIGO = "maServicioHabilitacionCodigo";
	public static final String AU_SOLICITUD_ADJUNTOS_LIST = "auSolicitudAdjuntosList";
	public static final String CONSECUTIVO_ORDEN = "consecutivoOrden";
	public static final String TIPO_FORMULA = "tipoFormula";
	public static final String MA_ESPECIALIDAD_ID = "maEspecialidadId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TUTELA = "tutela";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String AU_NO_SOLICITUD_ITEMS_LIST = "auNoSolicitudItemsList";

}

