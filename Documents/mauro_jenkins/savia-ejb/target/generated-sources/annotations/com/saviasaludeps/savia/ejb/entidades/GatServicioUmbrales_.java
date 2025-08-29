package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatServicioUmbrales.class)
public abstract class GatServicioUmbrales_ {

	public static volatile SingularAttribute<GatServicioUmbrales, String> terminalModifica;
	public static volatile SingularAttribute<GatServicioUmbrales, Integer> tipo;
	public static volatile SingularAttribute<GatServicioUmbrales, String> usuarioCrea;
	public static volatile SingularAttribute<GatServicioUmbrales, Integer> maeTipoServicioId;
	public static volatile SingularAttribute<GatServicioUmbrales, String> maeTipoServicioValor;
	public static volatile SingularAttribute<GatServicioUmbrales, Integer> tiempo;
	public static volatile SingularAttribute<GatServicioUmbrales, String> terminalCrea;
	public static volatile SingularAttribute<GatServicioUmbrales, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatServicioUmbrales, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GatServicioUmbrales, Integer> id;
	public static volatile SingularAttribute<GatServicioUmbrales, String> maeTipoServicioCodigo;
	public static volatile SingularAttribute<GatServicioUmbrales, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_SERVICIO_ID = "maeTipoServicioId";
	public static final String MAE_TIPO_SERVICIO_VALOR = "maeTipoServicioValor";
	public static final String TIEMPO = "tiempo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_SERVICIO_CODIGO = "maeTipoServicioCodigo";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

