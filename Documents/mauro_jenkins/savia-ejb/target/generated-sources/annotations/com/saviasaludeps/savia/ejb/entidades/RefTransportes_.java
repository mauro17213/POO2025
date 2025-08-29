package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RefTransportes.class)
public abstract class RefTransportes_ {

	public static volatile SingularAttribute<RefTransportes, String> maeTransporteLiquidacionCodigo;
	public static volatile ListAttribute<RefTransportes, RefTransporteInsumos> refTransporteInsumosList;
	public static volatile SingularAttribute<RefTransportes, String> maeClaseTransporteValor;
	public static volatile SingularAttribute<RefTransportes, Integer> maeClaseTransporteId;
	public static volatile SingularAttribute<RefTransportes, String> terminalModifica;
	public static volatile SingularAttribute<RefTransportes, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile ListAttribute<RefTransportes, RefTransporteSeguimientos> refTransporteSeguimientosList;
	public static volatile SingularAttribute<RefTransportes, String> usuarioCrea;
	public static volatile SingularAttribute<RefTransportes, Integer> maeTipoTransporteId;
	public static volatile SingularAttribute<RefTransportes, String> maeTipoTransporteValor;
	public static volatile SingularAttribute<RefTransportes, String> maeTransporteLiquidacionValor;
	public static volatile SingularAttribute<RefTransportes, Date> fechaHoraOrigen;
	public static volatile SingularAttribute<RefTransportes, Date> fechaHoraDestino;
	public static volatile SingularAttribute<RefTransportes, String> terminalCrea;
	public static volatile SingularAttribute<RefTransportes, String> maeTipoTransporteCodigo;
	public static volatile SingularAttribute<RefTransportes, Integer> maeTransporteLiquidacionId;
	public static volatile SingularAttribute<RefTransportes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RefTransportes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<RefTransportes, Integer> id;
	public static volatile SingularAttribute<RefTransportes, String> maeClaseTransporteCodigo;
	public static volatile SingularAttribute<RefTransportes, RefAnexos9> refAnexos9Id;
	public static volatile SingularAttribute<RefTransportes, String> observacion;
	public static volatile SingularAttribute<RefTransportes, String> usuarioModifica;

	public static final String MAE_TRANSPORTE_LIQUIDACION_CODIGO = "maeTransporteLiquidacionCodigo";
	public static final String REF_TRANSPORTE_INSUMOS_LIST = "refTransporteInsumosList";
	public static final String MAE_CLASE_TRANSPORTE_VALOR = "maeClaseTransporteValor";
	public static final String MAE_CLASE_TRANSPORTE_ID = "maeClaseTransporteId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String REF_TRANSPORTE_SEGUIMIENTOS_LIST = "refTransporteSeguimientosList";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_TRANSPORTE_ID = "maeTipoTransporteId";
	public static final String MAE_TIPO_TRANSPORTE_VALOR = "maeTipoTransporteValor";
	public static final String MAE_TRANSPORTE_LIQUIDACION_VALOR = "maeTransporteLiquidacionValor";
	public static final String FECHA_HORA_ORIGEN = "fechaHoraOrigen";
	public static final String FECHA_HORA_DESTINO = "fechaHoraDestino";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_TRANSPORTE_CODIGO = "maeTipoTransporteCodigo";
	public static final String MAE_TRANSPORTE_LIQUIDACION_ID = "maeTransporteLiquidacionId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_CLASE_TRANSPORTE_CODIGO = "maeClaseTransporteCodigo";
	public static final String REF_ANEXOS9_ID = "refAnexos9Id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

