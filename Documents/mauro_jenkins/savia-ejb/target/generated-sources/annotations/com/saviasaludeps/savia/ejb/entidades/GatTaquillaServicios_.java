package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatTaquillaServicios.class)
public abstract class GatTaquillaServicios_ {

	public static volatile SingularAttribute<GatTaquillaServicios, String> usuarioCrea;
	public static volatile SingularAttribute<GatTaquillaServicios, Integer> maeTipoServicioId;
	public static volatile SingularAttribute<GatTaquillaServicios, String> maeTipoServicioValor;
	public static volatile SingularAttribute<GatTaquillaServicios, String> terminalCrea;
	public static volatile SingularAttribute<GatTaquillaServicios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatTaquillaServicios, Integer> id;
	public static volatile SingularAttribute<GatTaquillaServicios, String> maeTipoServicioCodigo;
	public static volatile SingularAttribute<GatTaquillaServicios, GatSedeTaquillas> gatSedeTaquillasId;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_SERVICIO_ID = "maeTipoServicioId";
	public static final String MAE_TIPO_SERVICIO_VALOR = "maeTipoServicioValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_TIPO_SERVICIO_CODIGO = "maeTipoServicioCodigo";
	public static final String GAT_SEDE_TAQUILLAS_ID = "gatSedeTaquillasId";

}

