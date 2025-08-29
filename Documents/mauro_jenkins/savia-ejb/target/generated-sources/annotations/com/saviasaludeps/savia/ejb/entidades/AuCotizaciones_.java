package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuCotizaciones.class)
public abstract class AuCotizaciones_ {

	public static volatile SingularAttribute<AuCotizaciones, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<AuCotizaciones, Date> fechaInicioVigencia;
	public static volatile SingularAttribute<AuCotizaciones, String> maTecnologiaValor;
	public static volatile SingularAttribute<AuCotizaciones, Integer> antAnticiposId;
	public static volatile ListAttribute<AuCotizaciones, AuAnexo4Items> auAnexo4ItemsList;
	public static volatile SingularAttribute<AuCotizaciones, String> maTarifarioCodigo;
	public static volatile SingularAttribute<AuCotizaciones, AuAnexos3> auAnexos3Id;
	public static volatile SingularAttribute<AuCotizaciones, String> terminalModifica;
	public static volatile SingularAttribute<AuCotizaciones, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AuCotizaciones, String> usuarioCrea;
	public static volatile SingularAttribute<AuCotizaciones, String> maTarifarioValor;
	public static volatile SingularAttribute<AuCotizaciones, String> terminalCrea;
	public static volatile SingularAttribute<AuCotizaciones, Integer> antAnticipoItemsId;
	public static volatile SingularAttribute<AuCotizaciones, Date> fechaHoraCrea;
	public static volatile ListAttribute<AuCotizaciones, MpCotizaciones> mpCotizacionesList;
	public static volatile SingularAttribute<AuCotizaciones, Integer> id;
	public static volatile ListAttribute<AuCotizaciones, AuCotizacionItems> auCotizacionItemsList;
	public static volatile SingularAttribute<AuCotizaciones, String> observacion;
	public static volatile SingularAttribute<AuCotizaciones, Integer> tipoTarifa;
	public static volatile SingularAttribute<AuCotizaciones, Integer> fuenteOrigen;
	public static volatile SingularAttribute<AuCotizaciones, Integer> tipoTecnologiaMipres;
	public static volatile ListAttribute<AuCotizaciones, AntAnticipoValores> antAnticipoValoresList;
	public static volatile SingularAttribute<AuCotizaciones, Integer> maMedicamentoId;
	public static volatile SingularAttribute<AuCotizaciones, Integer> maTarifarioId;
	public static volatile SingularAttribute<AuCotizaciones, String> maMedicamentoCodigo;
	public static volatile SingularAttribute<AuCotizaciones, Integer> maTecnologiaId;
	public static volatile SingularAttribute<AuCotizaciones, Date> fechaFinVigencia;
	public static volatile SingularAttribute<AuCotizaciones, BigDecimal> porcentajeNegociacion;
	public static volatile SingularAttribute<AuCotizaciones, String> maMedicamentoValor;
	public static volatile SingularAttribute<AuCotizaciones, BigDecimal> valorTecnologia;
	public static volatile SingularAttribute<AuCotizaciones, String> mpNumeroPrescripcion;
	public static volatile SingularAttribute<AuCotizaciones, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile ListAttribute<AuCotizaciones, AuSolicitudAdjuntos> auSolicitudAdjuntosList;
	public static volatile ListAttribute<AuCotizaciones, AuCotizacionAdjuntos> auCotizacionAdjuntosList;
	public static volatile SingularAttribute<AuCotizaciones, Integer> tipoTecnologia;
	public static volatile SingularAttribute<AuCotizaciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuCotizaciones, MpPrescripciones> mpPrescripcionId;
	public static volatile SingularAttribute<AuCotizaciones, Boolean> pagoAnticipado;
	public static volatile SingularAttribute<AuCotizaciones, String> usuarioModifica;
	public static volatile SingularAttribute<AuCotizaciones, Boolean> activo;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String FECHA_INICIO_VIGENCIA = "fechaInicioVigencia";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String ANT_ANTICIPOS_ID = "antAnticiposId";
	public static final String AU_ANEXO4_ITEMS_LIST = "auAnexo4ItemsList";
	public static final String MA_TARIFARIO_CODIGO = "maTarifarioCodigo";
	public static final String AU_ANEXOS3_ID = "auAnexos3Id";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MA_TARIFARIO_VALOR = "maTarifarioValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ANT_ANTICIPO_ITEMS_ID = "antAnticipoItemsId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MP_COTIZACIONES_LIST = "mpCotizacionesList";
	public static final String ID = "id";
	public static final String AU_COTIZACION_ITEMS_LIST = "auCotizacionItemsList";
	public static final String OBSERVACION = "observacion";
	public static final String TIPO_TARIFA = "tipoTarifa";
	public static final String FUENTE_ORIGEN = "fuenteOrigen";
	public static final String TIPO_TECNOLOGIA_MIPRES = "tipoTecnologiaMipres";
	public static final String ANT_ANTICIPO_VALORES_LIST = "antAnticipoValoresList";
	public static final String MA_MEDICAMENTO_ID = "maMedicamentoId";
	public static final String MA_TARIFARIO_ID = "maTarifarioId";
	public static final String MA_MEDICAMENTO_CODIGO = "maMedicamentoCodigo";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String FECHA_FIN_VIGENCIA = "fechaFinVigencia";
	public static final String PORCENTAJE_NEGOCIACION = "porcentajeNegociacion";
	public static final String MA_MEDICAMENTO_VALOR = "maMedicamentoValor";
	public static final String VALOR_TECNOLOGIA = "valorTecnologia";
	public static final String MP_NUMERO_PRESCRIPCION = "mpNumeroPrescripcion";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String AU_SOLICITUD_ADJUNTOS_LIST = "auSolicitudAdjuntosList";
	public static final String AU_COTIZACION_ADJUNTOS_LIST = "auCotizacionAdjuntosList";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String MP_PRESCRIPCION_ID = "mpPrescripcionId";
	public static final String PAGO_ANTICIPADO = "pagoAnticipado";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

