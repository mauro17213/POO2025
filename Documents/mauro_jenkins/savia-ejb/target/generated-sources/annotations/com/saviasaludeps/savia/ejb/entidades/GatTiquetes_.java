package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatTiquetes.class)
public abstract class GatTiquetes_ {

	public static volatile SingularAttribute<GatTiquetes, Integer> estado;
	public static volatile SingularAttribute<GatTiquetes, Integer> maeTipoServicioId;
	public static volatile SingularAttribute<GatTiquetes, String> maeTipoServicioValor;
	public static volatile ListAttribute<GatTiquetes, GatTiketeLlamados> gatTiketeLlamadosList;
	public static volatile SingularAttribute<GatTiquetes, String> numero;
	public static volatile SingularAttribute<GatTiquetes, GatUsuarios> gatUsuariosId;
	public static volatile ListAttribute<GatTiquetes, GatAtenciones> gatAtencionesList;
	public static volatile SingularAttribute<GatTiquetes, GnSedes> gnSedesId;
	public static volatile SingularAttribute<GatTiquetes, Date> fechaHoraLlamado;
	public static volatile SingularAttribute<GatTiquetes, String> usuarioCrea;
	public static volatile SingularAttribute<GatTiquetes, Date> fechaHoraAtendido;
	public static volatile SingularAttribute<GatTiquetes, String> terminalCrea;
	public static volatile SingularAttribute<GatTiquetes, Boolean> prioritario;
	public static volatile SingularAttribute<GatTiquetes, Date> fechaHoraAbandona;
	public static volatile SingularAttribute<GatTiquetes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatTiquetes, Integer> id;
	public static volatile SingularAttribute<GatTiquetes, String> maeTipoServicioCodigo;
	public static volatile SingularAttribute<GatTiquetes, Date> fechaHoraFinaliza;

	public static final String ESTADO = "estado";
	public static final String MAE_TIPO_SERVICIO_ID = "maeTipoServicioId";
	public static final String MAE_TIPO_SERVICIO_VALOR = "maeTipoServicioValor";
	public static final String GAT_TIKETE_LLAMADOS_LIST = "gatTiketeLlamadosList";
	public static final String NUMERO = "numero";
	public static final String GAT_USUARIOS_ID = "gatUsuariosId";
	public static final String GAT_ATENCIONES_LIST = "gatAtencionesList";
	public static final String GN_SEDES_ID = "gnSedesId";
	public static final String FECHA_HORA_LLAMADO = "fechaHoraLlamado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_HORA_ATENDIDO = "fechaHoraAtendido";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String PRIORITARIO = "prioritario";
	public static final String FECHA_HORA_ABANDONA = "fechaHoraAbandona";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_TIPO_SERVICIO_CODIGO = "maeTipoServicioCodigo";
	public static final String FECHA_HORA_FINALIZA = "fechaHoraFinaliza";

}

