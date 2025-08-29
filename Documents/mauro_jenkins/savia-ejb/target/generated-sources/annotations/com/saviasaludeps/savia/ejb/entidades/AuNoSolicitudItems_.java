package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuNoSolicitudItems.class)
public abstract class AuNoSolicitudItems_ {

	public static volatile SingularAttribute<AuNoSolicitudItems, Boolean> pbs;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> estado;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> estadoJustificacion;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> maMedicamentosId;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> frecuencia;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> cntContratoDetallesId;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> maTecnologiaValor;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> maeViaAdministracionTipo;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> usuarioBorra;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> maServicioHabilitacionId;
	public static volatile SingularAttribute<AuNoSolicitudItems, BigDecimal> dosis;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> tipoEntrega;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> maeViaAdministracionId;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> duracionTratamiento;
	public static volatile SingularAttribute<AuNoSolicitudItems, BigDecimal> valorUnitario;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> terminalModifica;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> usuarioCrea;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> maeViaAdministracionCodigo;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> terminalCrea;
	public static volatile SingularAttribute<AuNoSolicitudItems, BigDecimal> valorTotal;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> maServicioHabilitacionValor;
	public static volatile SingularAttribute<AuNoSolicitudItems, Date> fechaHoraCrea;
	public static volatile ListAttribute<AuNoSolicitudItems, AuNoSolicitudEntregas> auNoSolicitudEntregasList;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> terminalBorra;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> id;
	public static volatile SingularAttribute<AuNoSolicitudItems, AuNoSolicitudes> auNoSolicitudesId;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> maeViaAdministracionValor;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> numEntregas;
	public static volatile SingularAttribute<AuNoSolicitudItems, Date> fechaHoraBorra;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> cantidadSolicitada;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> maTecnologiaId;
	public static volatile ListAttribute<AuNoSolicitudItems, AuNoSolicitudEntregaDetalles> auNoSolicitudEntregaDetallesList;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> posologia;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> maMedicamentosValor;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> maServicioHabilitacionCodigo;
	public static volatile SingularAttribute<AuNoSolicitudItems, Integer> tipoTecnologia;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> maMedicamentosCodigo;
	public static volatile SingularAttribute<AuNoSolicitudItems, Boolean> borrado;
	public static volatile SingularAttribute<AuNoSolicitudItems, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuNoSolicitudItems, String> usuarioModifica;

	public static final String PBS = "pbs";
	public static final String ESTADO = "estado";
	public static final String ESTADO_JUSTIFICACION = "estadoJustificacion";
	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String MA_MEDICAMENTOS_ID = "maMedicamentosId";
	public static final String FRECUENCIA = "frecuencia";
	public static final String CNT_CONTRATO_DETALLES_ID = "cntContratoDetallesId";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String MAE_VIA_ADMINISTRACION_TIPO = "maeViaAdministracionTipo";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String MA_SERVICIO_HABILITACION_ID = "maServicioHabilitacionId";
	public static final String DOSIS = "dosis";
	public static final String TIPO_ENTREGA = "tipoEntrega";
	public static final String MAE_VIA_ADMINISTRACION_ID = "maeViaAdministracionId";
	public static final String DURACION_TRATAMIENTO = "duracionTratamiento";
	public static final String VALOR_UNITARIO = "valorUnitario";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_VIA_ADMINISTRACION_CODIGO = "maeViaAdministracionCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR_TOTAL = "valorTotal";
	public static final String MA_SERVICIO_HABILITACION_VALOR = "maServicioHabilitacionValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String AU_NO_SOLICITUD_ENTREGAS_LIST = "auNoSolicitudEntregasList";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String AU_NO_SOLICITUDES_ID = "auNoSolicitudesId";
	public static final String MAE_VIA_ADMINISTRACION_VALOR = "maeViaAdministracionValor";
	public static final String NUM_ENTREGAS = "numEntregas";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String CANTIDAD_SOLICITADA = "cantidadSolicitada";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String AU_NO_SOLICITUD_ENTREGA_DETALLES_LIST = "auNoSolicitudEntregaDetallesList";
	public static final String POSOLOGIA = "posologia";
	public static final String MA_MEDICAMENTOS_VALOR = "maMedicamentosValor";
	public static final String MA_SERVICIO_HABILITACION_CODIGO = "maServicioHabilitacionCodigo";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String MA_MEDICAMENTOS_CODIGO = "maMedicamentosCodigo";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

