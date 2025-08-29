package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InfInformes.class)
public abstract class InfInformes_ {

	public static volatile SingularAttribute<InfInformes, String> descripcion;
	public static volatile SingularAttribute<InfInformes, String> nombreArchivo;
	public static volatile SingularAttribute<InfInformes, Integer> estado;
	public static volatile SingularAttribute<InfInformes, Integer> periodicidad;
	public static volatile SingularAttribute<InfInformes, Integer> maeTipoFormatoId;
	public static volatile SingularAttribute<InfInformes, String> maeTipoFormatoValor;
	public static volatile SingularAttribute<InfInformes, Date> fechaProgramadaInicio;
	public static volatile ListAttribute<InfInformes, InfInformeGenerados> infInformeGeneradosList;
	public static volatile SingularAttribute<InfInformes, String> maeTipoFormatoCodigo;
	public static volatile SingularAttribute<InfInformes, String> nombre;
	public static volatile SingularAttribute<InfInformes, Date> fechaProgramadoFin;
	public static volatile SingularAttribute<InfInformes, String> terminalModifica;
	public static volatile SingularAttribute<InfInformes, String> usuarioCrea;
	public static volatile SingularAttribute<InfInformes, String> terminalCrea;
	public static volatile SingularAttribute<InfInformes, Boolean> multipleGeneracion;
	public static volatile SingularAttribute<InfInformes, Boolean> programado;
	public static volatile SingularAttribute<InfInformes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<InfInformes, Integer> id;
	public static volatile ListAttribute<InfInformes, InfInformeVariables> infInformeVariablesList;
	public static volatile SingularAttribute<InfInformes, Boolean> requiereAprobacion;
	public static volatile SingularAttribute<InfInformes, String> nombreSp;
	public static volatile SingularAttribute<InfInformes, Boolean> multipleEmpresa;
	public static volatile SingularAttribute<InfInformes, InfGrupos> infGruposId;
	public static volatile SingularAttribute<InfInformes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<InfInformes, Integer> tipoPeriodicidad;
	public static volatile SingularAttribute<InfInformes, String> usuarioModifica;
	public static volatile SingularAttribute<InfInformes, Boolean> activo;
	public static volatile SingularAttribute<InfInformes, String> carpeta;

	public static final String DESCRIPCION = "descripcion";
	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ESTADO = "estado";
	public static final String PERIODICIDAD = "periodicidad";
	public static final String MAE_TIPO_FORMATO_ID = "maeTipoFormatoId";
	public static final String MAE_TIPO_FORMATO_VALOR = "maeTipoFormatoValor";
	public static final String FECHA_PROGRAMADA_INICIO = "fechaProgramadaInicio";
	public static final String INF_INFORME_GENERADOS_LIST = "infInformeGeneradosList";
	public static final String MAE_TIPO_FORMATO_CODIGO = "maeTipoFormatoCodigo";
	public static final String NOMBRE = "nombre";
	public static final String FECHA_PROGRAMADO_FIN = "fechaProgramadoFin";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MULTIPLE_GENERACION = "multipleGeneracion";
	public static final String PROGRAMADO = "programado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String INF_INFORME_VARIABLES_LIST = "infInformeVariablesList";
	public static final String REQUIERE_APROBACION = "requiereAprobacion";
	public static final String NOMBRE_SP = "nombreSp";
	public static final String MULTIPLE_EMPRESA = "multipleEmpresa";
	public static final String INF_GRUPOS_ID = "infGruposId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TIPO_PERIODICIDAD = "tipoPeriodicidad";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";
	public static final String CARPETA = "carpeta";

}

