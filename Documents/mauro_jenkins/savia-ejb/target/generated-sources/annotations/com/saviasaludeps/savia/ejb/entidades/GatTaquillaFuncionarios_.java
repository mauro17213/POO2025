package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatTaquillaFuncionarios.class)
public abstract class GatTaquillaFuncionarios_ {

	public static volatile SingularAttribute<GatTaquillaFuncionarios, String> terminalModifica;
	public static volatile SingularAttribute<GatTaquillaFuncionarios, String> usuarioCrea;
	public static volatile SingularAttribute<GatTaquillaFuncionarios, String> terminalCrea;
	public static volatile SingularAttribute<GatTaquillaFuncionarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatTaquillaFuncionarios, Date> fechaHoraInicio;
	public static volatile SingularAttribute<GatTaquillaFuncionarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GatTaquillaFuncionarios, Integer> id;
	public static volatile SingularAttribute<GatTaquillaFuncionarios, Date> fechaHoraFin;
	public static volatile SingularAttribute<GatTaquillaFuncionarios, String> usuarioModifica;
	public static volatile SingularAttribute<GatTaquillaFuncionarios, GatSedeTaquillas> gatSedeTaquillasId;
	public static volatile SingularAttribute<GatTaquillaFuncionarios, GnUsuarios> gnUsuariosId;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GAT_SEDE_TAQUILLAS_ID = "gatSedeTaquillasId";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";

}

