package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucCargaCierres.class)
public abstract class AucCargaCierres_ {

	public static volatile ListAttribute<AucCargaCierres, AucCargaCierreSucesos> aucCargaCierreSucesosList;
	public static volatile SingularAttribute<AucCargaCierres, String> nombreArchivo;
	public static volatile SingularAttribute<AucCargaCierres, Short> estado;
	public static volatile SingularAttribute<AucCargaCierres, Short> registrosTotal;
	public static volatile SingularAttribute<AucCargaCierres, String> archivo;
	public static volatile SingularAttribute<AucCargaCierres, String> ruta;
	public static volatile SingularAttribute<AucCargaCierres, Date> fechaHoraInicio;
	public static volatile SingularAttribute<AucCargaCierres, Short> registrosRechazados;
	public static volatile SingularAttribute<AucCargaCierres, Boolean> existe;
	public static volatile SingularAttribute<AucCargaCierres, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<AucCargaCierres, String> usuarioCrea;
	public static volatile SingularAttribute<AucCargaCierres, String> terminalCrear;
	public static volatile SingularAttribute<AucCargaCierres, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucCargaCierres, Integer> id;
	public static volatile SingularAttribute<AucCargaCierres, Date> fechaHoraFin;
	public static volatile SingularAttribute<AucCargaCierres, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<AucCargaCierres, Short> registrosExitosos;

	public static final String AUC_CARGA_CIERRE_SUCESOS_LIST = "aucCargaCierreSucesosList";
	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ESTADO = "estado";
	public static final String REGISTROS_TOTAL = "registrosTotal";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String REGISTROS_RECHAZADOS = "registrosRechazados";
	public static final String EXISTE = "existe";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREAR = "terminalCrear";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String REGISTROS_EXITOSOS = "registrosExitosos";

}

