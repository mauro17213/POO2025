package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatSedeFuncionarios.class)
public abstract class GatSedeFuncionarios_ {

	public static volatile SingularAttribute<GatSedeFuncionarios, String> terminalModifica;
	public static volatile SingularAttribute<GatSedeFuncionarios, String> usuarioCrea;
	public static volatile SingularAttribute<GatSedeFuncionarios, String> terminalCrea;
	public static volatile SingularAttribute<GatSedeFuncionarios, Date> fechaHoraCrea;
	public static volatile ListAttribute<GatSedeFuncionarios, GatAtenciones> gatAtencionesList;
	public static volatile SingularAttribute<GatSedeFuncionarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GatSedeFuncionarios, Integer> id;
	public static volatile SingularAttribute<GatSedeFuncionarios, GnSedes> gnSedesId;
	public static volatile SingularAttribute<GatSedeFuncionarios, String> usuarioModifica;
	public static volatile SingularAttribute<GatSedeFuncionarios, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<GatSedeFuncionarios, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String GAT_ATENCIONES_LIST = "gatAtencionesList";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String GN_SEDES_ID = "gnSedesId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String ACTIVO = "activo";

}

