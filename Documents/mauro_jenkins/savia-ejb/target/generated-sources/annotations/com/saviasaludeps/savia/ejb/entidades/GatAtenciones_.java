package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatAtenciones.class)
public abstract class GatAtenciones_ {

	public static volatile SingularAttribute<GatAtenciones, Integer> estado;
	public static volatile SingularAttribute<GatAtenciones, Date> fechaHoraCalificacion;
	public static volatile SingularAttribute<GatAtenciones, GatSedeFuncionarios> gatSedeFuncionariosId;
	public static volatile SingularAttribute<GatAtenciones, GatUsuarios> gatUsuariosId;
	public static volatile ListAttribute<GatAtenciones, GatAtencionComentarios> gatAtencionComentariosList;
	public static volatile ListAttribute<GatAtenciones, GatAtencionHistoricos> gatAtencionHistoricosList;
	public static volatile SingularAttribute<GatAtenciones, Date> fechaHoraInicio;
	public static volatile SingularAttribute<GatAtenciones, Date> fechaHoraCancela;
	public static volatile SingularAttribute<GatAtenciones, GnSedes> gnSedesId;
	public static volatile SingularAttribute<GatAtenciones, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<GatAtenciones, Date> fechaHoraTiquete;
	public static volatile SingularAttribute<GatAtenciones, String> usuarioCrea;
	public static volatile SingularAttribute<GatAtenciones, String> terminalCrea;
	public static volatile SingularAttribute<GatAtenciones, GatTiquetes> gatTiquetesId;
	public static volatile SingularAttribute<GatAtenciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatAtenciones, Integer> id;
	public static volatile ListAttribute<GatAtenciones, GatTiempos> gatTiemposList;
	public static volatile SingularAttribute<GatAtenciones, GatSedeTaquillas> gatTaquillasId;
	public static volatile SingularAttribute<GatAtenciones, Date> fechaHoraFin;

	public static final String ESTADO = "estado";
	public static final String FECHA_HORA_CALIFICACION = "fechaHoraCalificacion";
	public static final String GAT_SEDE_FUNCIONARIOS_ID = "gatSedeFuncionariosId";
	public static final String GAT_USUARIOS_ID = "gatUsuariosId";
	public static final String GAT_ATENCION_COMENTARIOS_LIST = "gatAtencionComentariosList";
	public static final String GAT_ATENCION_HISTORICOS_LIST = "gatAtencionHistoricosList";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String FECHA_HORA_CANCELA = "fechaHoraCancela";
	public static final String GN_SEDES_ID = "gnSedesId";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String FECHA_HORA_TIQUETE = "fechaHoraTiquete";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GAT_TIQUETES_ID = "gatTiquetesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String GAT_TIEMPOS_LIST = "gatTiemposList";
	public static final String GAT_TAQUILLAS_ID = "gatTaquillasId";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";

}

