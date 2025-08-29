package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoCargas.class)
public abstract class CntContratoCargas_ {

	public static volatile SingularAttribute<CntContratoCargas, String> nombreArchivo;
	public static volatile SingularAttribute<CntContratoCargas, Integer> estado;
	public static volatile SingularAttribute<CntContratoCargas, Integer> tipo;
	public static volatile SingularAttribute<CntContratoCargas, Integer> registrosTotal;
	public static volatile SingularAttribute<CntContratoCargas, String> archivo;
	public static volatile SingularAttribute<CntContratoCargas, String> ruta;
	public static volatile SingularAttribute<CntContratoCargas, String> respNombre;
	public static volatile SingularAttribute<CntContratoCargas, String> respArchivo;
	public static volatile SingularAttribute<CntContratoCargas, String> respRuta;
	public static volatile SingularAttribute<CntContratoCargas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<CntContratoCargas, Integer> registrosRechazados;
	public static volatile SingularAttribute<CntContratoCargas, Boolean> existe;
	public static volatile SingularAttribute<CntContratoCargas, String> usuarioCrea;
	public static volatile SingularAttribute<CntContratoCargas, String> terminalCrea;
	public static volatile ListAttribute<CntContratoCargas, CntContratoCargaSucesos> cntContratoCargaSucesosList;
	public static volatile SingularAttribute<CntContratoCargas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoCargas, Integer> id;
	public static volatile SingularAttribute<CntContratoCargas, Date> fechaHoraFin;
	public static volatile SingularAttribute<CntContratoCargas, Boolean> respExiste;
	public static volatile SingularAttribute<CntContratoCargas, Integer> registrosExitosos;

	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ESTADO = "estado";
	public static final String TIPO = "tipo";
	public static final String REGISTROS_TOTAL = "registrosTotal";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String RESP_NOMBRE = "respNombre";
	public static final String RESP_ARCHIVO = "respArchivo";
	public static final String RESP_RUTA = "respRuta";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String REGISTROS_RECHAZADOS = "registrosRechazados";
	public static final String EXISTE = "existe";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNT_CONTRATO_CARGA_SUCESOS_LIST = "cntContratoCargaSucesosList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String RESP_EXISTE = "respExiste";
	public static final String REGISTROS_EXITOSOS = "registrosExitosos";

}

