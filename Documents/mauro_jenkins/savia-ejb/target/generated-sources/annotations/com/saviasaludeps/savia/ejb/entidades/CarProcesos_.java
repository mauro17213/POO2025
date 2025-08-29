package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CarProcesos.class)
public abstract class CarProcesos_ {

	public static volatile SingularAttribute<CarProcesos, String> descripcion;
	public static volatile ListAttribute<CarProcesos, CarPeriodos> carPeriodosList;
	public static volatile SingularAttribute<CarProcesos, Boolean> editable;
	public static volatile ListAttribute<CarProcesos, CarProcesoUsuarios> carProcesoUsuariosList;
	public static volatile SingularAttribute<CarProcesos, String> estructuraJson;
	public static volatile SingularAttribute<CarProcesos, String> nombre;
	public static volatile SingularAttribute<CarProcesos, String> terminalModifica;
	public static volatile SingularAttribute<CarProcesos, String> usuarioCrea;
	public static volatile ListAttribute<CarProcesos, CarProcesoPrestadores> carProcesoPrestadoresList;
	public static volatile SingularAttribute<CarProcesos, String> terminalCrea;
	public static volatile ListAttribute<CarProcesos, CarCargas> carCargasList;
	public static volatile SingularAttribute<CarProcesos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CarProcesos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CarProcesos, Integer> id;
	public static volatile SingularAttribute<CarProcesos, String> usuarioModifica;
	public static volatile SingularAttribute<CarProcesos, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String CAR_PERIODOS_LIST = "carPeriodosList";
	public static final String EDITABLE = "editable";
	public static final String CAR_PROCESO_USUARIOS_LIST = "carProcesoUsuariosList";
	public static final String ESTRUCTURA_JSON = "estructuraJson";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CAR_PROCESO_PRESTADORES_LIST = "carProcesoPrestadoresList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CAR_CARGAS_LIST = "carCargasList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

