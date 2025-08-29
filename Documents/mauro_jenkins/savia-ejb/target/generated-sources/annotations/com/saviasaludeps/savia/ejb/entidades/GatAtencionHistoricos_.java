package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatAtencionHistoricos.class)
public abstract class GatAtencionHistoricos_ {

	public static volatile SingularAttribute<GatAtencionHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<GatAtencionHistoricos, Integer> maeTipoServicioId;
	public static volatile SingularAttribute<GatAtencionHistoricos, String> maeTipoServicioValor;
	public static volatile SingularAttribute<GatAtencionHistoricos, GatAtenciones> gatAtencionesId;
	public static volatile SingularAttribute<GatAtencionHistoricos, Integer> tiempo;
	public static volatile SingularAttribute<GatAtencionHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<GatAtencionHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatAtencionHistoricos, Date> fechaHoraInicio;
	public static volatile SingularAttribute<GatAtencionHistoricos, Integer> id;
	public static volatile SingularAttribute<GatAtencionHistoricos, String> maeTipoServicioCodigo;
	public static volatile SingularAttribute<GatAtencionHistoricos, String> comentario;
	public static volatile SingularAttribute<GatAtencionHistoricos, Date> fechaHoraFin;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_SERVICIO_ID = "maeTipoServicioId";
	public static final String MAE_TIPO_SERVICIO_VALOR = "maeTipoServicioValor";
	public static final String GAT_ATENCIONES_ID = "gatAtencionesId";
	public static final String TIEMPO = "tiempo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String MAE_TIPO_SERVICIO_CODIGO = "maeTipoServicioCodigo";
	public static final String COMENTARIO = "comentario";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";

}

