package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucCargas.class)
public abstract class AucCargas_ {

	public static volatile SingularAttribute<AucCargas, String> nombreArchivo;
	public static volatile SingularAttribute<AucCargas, Integer> estado;
	public static volatile SingularAttribute<AucCargas, Short> hopitalizados;
	public static volatile SingularAttribute<AucCargas, Integer> tipo;
	public static volatile SingularAttribute<AucCargas, Integer> registrosTotal;
	public static volatile SingularAttribute<AucCargas, String> archivo;
	public static volatile SingularAttribute<AucCargas, String> ruta;
	public static volatile SingularAttribute<AucCargas, String> respNombre;
	public static volatile SingularAttribute<AucCargas, String> respArchivo;
	public static volatile SingularAttribute<AucCargas, String> respRuta;
	public static volatile SingularAttribute<AucCargas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<AucCargas, Integer> registrosRechazados;
	public static volatile SingularAttribute<AucCargas, Boolean> existe;
	public static volatile SingularAttribute<AucCargas, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<AucCargas, String> usuarioCrea;
	public static volatile SingularAttribute<AucCargas, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<AucCargas, String> terminalCrea;
	public static volatile SingularAttribute<AucCargas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucCargas, Integer> id;
	public static volatile ListAttribute<AucCargas, AucCargaFallos> aucCargaFallosList;
	public static volatile SingularAttribute<AucCargas, Date> fechaHoraFin;
	public static volatile SingularAttribute<AucCargas, Boolean> respExiste;
	public static volatile SingularAttribute<AucCargas, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<AucCargas, Integer> registrosExitosos;

	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ESTADO = "estado";
	public static final String HOPITALIZADOS = "hopitalizados";
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
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String AUC_CARGA_FALLOS_LIST = "aucCargaFallosList";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String RESP_EXISTE = "respExiste";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String REGISTROS_EXITOSOS = "registrosExitosos";

}

