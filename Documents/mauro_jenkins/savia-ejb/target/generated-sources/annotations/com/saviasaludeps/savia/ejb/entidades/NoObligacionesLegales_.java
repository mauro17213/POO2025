package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NoObligacionesLegales.class)
public abstract class NoObligacionesLegales_ {

	public static volatile SingularAttribute<NoObligacionesLegales, String> descripcion;
	public static volatile SingularAttribute<NoObligacionesLegales, String> maeEntesControlValor;
	public static volatile SingularAttribute<NoObligacionesLegales, String> maeMedioEnvioValor;
	public static volatile SingularAttribute<NoObligacionesLegales, Integer> maeMedioEnvioId;
	public static volatile ListAttribute<NoObligacionesLegales, NoObligacionLegalDetalles> noObligacionLegalDetallesList;
	public static volatile SingularAttribute<NoObligacionesLegales, String> maeMedioEnvioCodigo;
	public static volatile SingularAttribute<NoObligacionesLegales, String> maeEntesControlCodigo;
	public static volatile SingularAttribute<NoObligacionesLegales, String> terminalModifica;
	public static volatile SingularAttribute<NoObligacionesLegales, String> usuarioCrea;
	public static volatile SingularAttribute<NoObligacionesLegales, String> maePeriodicidadValor;
	public static volatile SingularAttribute<NoObligacionesLegales, Integer> maeObligacionLegalId;
	public static volatile SingularAttribute<NoObligacionesLegales, String> terminalCrea;
	public static volatile SingularAttribute<NoObligacionesLegales, String> maeObligacionLegalCodigo;
	public static volatile SingularAttribute<NoObligacionesLegales, Date> fechaHoraCrea;
	public static volatile SingularAttribute<NoObligacionesLegales, Integer> id;
	public static volatile SingularAttribute<NoObligacionesLegales, Boolean> aplicaDetalle;
	public static volatile SingularAttribute<NoObligacionesLegales, String> maePeriodicidadCodigo;
	public static volatile SingularAttribute<NoObligacionesLegales, String> codigo;
	public static volatile SingularAttribute<NoObligacionesLegales, Integer> maePeriodicidadId;
	public static volatile SingularAttribute<NoObligacionesLegales, Integer> agnoPublicacion;
	public static volatile SingularAttribute<NoObligacionesLegales, Integer> maeEntesControlId;
	public static volatile SingularAttribute<NoObligacionesLegales, String> maeObligacionLegalValor;
	public static volatile SingularAttribute<NoObligacionesLegales, Date> fechaHoraModific;
	public static volatile SingularAttribute<NoObligacionesLegales, String> usuarioModifica;
	public static volatile SingularAttribute<NoObligacionesLegales, Integer> activo;
	public static volatile SingularAttribute<NoObligacionesLegales, String> scriptEjecucion;

	public static final String DESCRIPCION = "descripcion";
	public static final String MAE_ENTES_CONTROL_VALOR = "maeEntesControlValor";
	public static final String MAE_MEDIO_ENVIO_VALOR = "maeMedioEnvioValor";
	public static final String MAE_MEDIO_ENVIO_ID = "maeMedioEnvioId";
	public static final String NO_OBLIGACION_LEGAL_DETALLES_LIST = "noObligacionLegalDetallesList";
	public static final String MAE_MEDIO_ENVIO_CODIGO = "maeMedioEnvioCodigo";
	public static final String MAE_ENTES_CONTROL_CODIGO = "maeEntesControlCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_PERIODICIDAD_VALOR = "maePeriodicidadValor";
	public static final String MAE_OBLIGACION_LEGAL_ID = "maeObligacionLegalId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_OBLIGACION_LEGAL_CODIGO = "maeObligacionLegalCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String APLICA_DETALLE = "aplicaDetalle";
	public static final String MAE_PERIODICIDAD_CODIGO = "maePeriodicidadCodigo";
	public static final String CODIGO = "codigo";
	public static final String MAE_PERIODICIDAD_ID = "maePeriodicidadId";
	public static final String AGNO_PUBLICACION = "agnoPublicacion";
	public static final String MAE_ENTES_CONTROL_ID = "maeEntesControlId";
	public static final String MAE_OBLIGACION_LEGAL_VALOR = "maeObligacionLegalValor";
	public static final String FECHA_HORA_MODIFIC = "fechaHoraModific";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";
	public static final String SCRIPT_EJECUCION = "scriptEjecucion";

}

