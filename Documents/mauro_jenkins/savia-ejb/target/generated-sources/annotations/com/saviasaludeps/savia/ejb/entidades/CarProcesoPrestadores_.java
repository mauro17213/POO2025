package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CarProcesoPrestadores.class)
public abstract class CarProcesoPrestadores_ {

	public static volatile SingularAttribute<CarProcesoPrestadores, String> terminalModifica;
	public static volatile SingularAttribute<CarProcesoPrestadores, String> usuarioCrea;
	public static volatile SingularAttribute<CarProcesoPrestadores, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CarProcesoPrestadores, String> terminalCrea;
	public static volatile SingularAttribute<CarProcesoPrestadores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CarProcesoPrestadores, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CarProcesoPrestadores, CarProcesos> carProcesosId;
	public static volatile SingularAttribute<CarProcesoPrestadores, Integer> id;
	public static volatile SingularAttribute<CarProcesoPrestadores, String> usuarioModifica;
	public static volatile SingularAttribute<CarProcesoPrestadores, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CAR_PROCESOS_ID = "carProcesosId";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

