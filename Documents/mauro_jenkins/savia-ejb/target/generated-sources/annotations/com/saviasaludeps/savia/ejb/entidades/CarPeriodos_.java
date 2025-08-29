package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CarPeriodos.class)
public abstract class CarPeriodos_ {

	public static volatile SingularAttribute<CarPeriodos, String> terminalModifica;
	public static volatile SingularAttribute<CarPeriodos, String> usuarioCrea;
	public static volatile SingularAttribute<CarPeriodos, Date> fechaInicio;
	public static volatile SingularAttribute<CarPeriodos, String> terminalCrea;
	public static volatile ListAttribute<CarPeriodos, CarCargas> carCargasList;
	public static volatile SingularAttribute<CarPeriodos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CarPeriodos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CarPeriodos, CarProcesos> carProcesosId;
	public static volatile SingularAttribute<CarPeriodos, Integer> id;
	public static volatile SingularAttribute<CarPeriodos, String> nombre;
	public static volatile SingularAttribute<CarPeriodos, Date> fechaFin;
	public static volatile SingularAttribute<CarPeriodos, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CAR_CARGAS_LIST = "carCargasList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CAR_PROCESOS_ID = "carProcesosId";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String FECHA_FIN = "fechaFin";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

