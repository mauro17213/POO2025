package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatSedeTaquillas.class)
public abstract class GatSedeTaquillas_ {

	public static volatile ListAttribute<GatSedeTaquillas, GatTiketeLlamados> gatTiketeLlamadosList;
	public static volatile ListAttribute<GatSedeTaquillas, GatTaquillaFuncionarios> gatTaquillaFuncionariosList;
	public static volatile ListAttribute<GatSedeTaquillas, GatAtenciones> gatAtencionesList;
	public static volatile ListAttribute<GatSedeTaquillas, GatTaquillaServicios> gatTaquillaServiciosList;
	public static volatile SingularAttribute<GatSedeTaquillas, String> nombre;
	public static volatile SingularAttribute<GatSedeTaquillas, GnSedes> gnSedesId;
	public static volatile SingularAttribute<GatSedeTaquillas, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<GatSedeTaquillas, String> terminalModifica;
	public static volatile SingularAttribute<GatSedeTaquillas, String> usuarioCrea;
	public static volatile SingularAttribute<GatSedeTaquillas, String> terminalCrea;
	public static volatile SingularAttribute<GatSedeTaquillas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatSedeTaquillas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GatSedeTaquillas, Integer> id;
	public static volatile SingularAttribute<GatSedeTaquillas, String> usuarioModifica;
	public static volatile SingularAttribute<GatSedeTaquillas, Boolean> activo;
	public static volatile SingularAttribute<GatSedeTaquillas, Boolean> disponible;

	public static final String GAT_TIKETE_LLAMADOS_LIST = "gatTiketeLlamadosList";
	public static final String GAT_TAQUILLA_FUNCIONARIOS_LIST = "gatTaquillaFuncionariosList";
	public static final String GAT_ATENCIONES_LIST = "gatAtencionesList";
	public static final String GAT_TAQUILLA_SERVICIOS_LIST = "gatTaquillaServiciosList";
	public static final String NOMBRE = "nombre";
	public static final String GN_SEDES_ID = "gnSedesId";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";
	public static final String DISPONIBLE = "disponible";

}

