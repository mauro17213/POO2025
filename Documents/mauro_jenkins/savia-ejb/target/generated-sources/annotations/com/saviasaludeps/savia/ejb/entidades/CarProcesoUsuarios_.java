package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CarProcesoUsuarios.class)
public abstract class CarProcesoUsuarios_ {

	public static volatile SingularAttribute<CarProcesoUsuarios, String> terminalModifica;
	public static volatile SingularAttribute<CarProcesoUsuarios, String> usuarioCrea;
	public static volatile SingularAttribute<CarProcesoUsuarios, String> terminalCrea;
	public static volatile SingularAttribute<CarProcesoUsuarios, Boolean> admin;
	public static volatile SingularAttribute<CarProcesoUsuarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CarProcesoUsuarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CarProcesoUsuarios, CarProcesos> carProcesosId;
	public static volatile SingularAttribute<CarProcesoUsuarios, Integer> id;
	public static volatile SingularAttribute<CarProcesoUsuarios, String> usuarioModifica;
	public static volatile SingularAttribute<CarProcesoUsuarios, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<CarProcesoUsuarios, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ADMIN = "admin";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CAR_PROCESOS_ID = "carProcesosId";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String ACTIVO = "activo";

}

