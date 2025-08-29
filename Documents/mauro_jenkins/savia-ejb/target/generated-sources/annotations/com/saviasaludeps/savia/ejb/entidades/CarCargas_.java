package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CarCargas.class)
public abstract class CarCargas_ {

	public static volatile SingularAttribute<CarCargas, Integer> exitosos;
	public static volatile SingularAttribute<CarCargas, String> nombreArchivo;
	public static volatile SingularAttribute<CarCargas, Integer> estado;
	public static volatile SingularAttribute<CarCargas, String> archivo;
	public static volatile SingularAttribute<CarCargas, String> ruta;
	public static volatile SingularAttribute<CarCargas, Integer> registros;
	public static volatile SingularAttribute<CarCargas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<CarCargas, Boolean> existe;
	public static volatile SingularAttribute<CarCargas, CarPeriodos> carPeriodosId;
	public static volatile ListAttribute<CarCargas, CarCargaRegistros> carCargaRegistrosList;
	public static volatile SingularAttribute<CarCargas, String> terminalModifica;
	public static volatile SingularAttribute<CarCargas, String> usuarioCrea;
	public static volatile SingularAttribute<CarCargas, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CarCargas, String> terminalCrea;
	public static volatile SingularAttribute<CarCargas, Integer> fallidos;
	public static volatile SingularAttribute<CarCargas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CarCargas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CarCargas, CarProcesos> carProcesosId;
	public static volatile SingularAttribute<CarCargas, Integer> id;
	public static volatile SingularAttribute<CarCargas, Date> fechaHoraFin;
	public static volatile SingularAttribute<CarCargas, String> usuarioModifica;
	public static volatile SingularAttribute<CarCargas, GnEmpresas> gnEmpresasId;

	public static final String EXITOSOS = "exitosos";
	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ESTADO = "estado";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String EXISTE = "existe";
	public static final String CAR_PERIODOS_ID = "carPeriodosId";
	public static final String CAR_CARGA_REGISTROS_LIST = "carCargaRegistrosList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FALLIDOS = "fallidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CAR_PROCESOS_ID = "carProcesosId";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";

}

