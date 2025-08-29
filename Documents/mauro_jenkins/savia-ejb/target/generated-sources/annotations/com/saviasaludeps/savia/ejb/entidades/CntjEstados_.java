package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjEstados.class)
public abstract class CntjEstados_ {

	public static volatile SingularAttribute<CntjEstados, String> descripcion;
	public static volatile ListAttribute<CntjEstados, CntjEstadoGrupos> cntjEstadoGruposList;
	public static volatile SingularAttribute<CntjEstados, Integer> tipo;
	public static volatile ListAttribute<CntjEstados, CntjEstadoUsuarios> cntjEstadoUsuariosList;
	public static volatile ListAttribute<CntjEstados, CntjExpedientes> cntjExpedientesList;
	public static volatile SingularAttribute<CntjEstados, Integer> resultadoComite;
	public static volatile ListAttribute<CntjEstados, CntjTransiciones> cntjTransicionesList;
	public static volatile SingularAttribute<CntjEstados, Boolean> modificaFecha;
	public static volatile SingularAttribute<CntjEstados, String> nombre;
	public static volatile ListAttribute<CntjEstados, CntjEstadoPlantillas> cntjEstadoPlantillasList;
	public static volatile ListAttribute<CntjEstados, CntjEstadoEjecuciones> cntjEstadoEjecucionesList;
	public static volatile ListAttribute<CntjEstados, CntjEstadoProcesoDocumentos> cntjEstadoProcesoDocumentosList;
	public static volatile SingularAttribute<CntjEstados, Boolean> validaGrupo;
	public static volatile SingularAttribute<CntjEstados, String> terminalModifica;
	public static volatile SingularAttribute<CntjEstados, String> usuarioCrea;
	public static volatile SingularAttribute<CntjEstados, Boolean> modificaDatos;
	public static volatile SingularAttribute<CntjEstados, String> terminalCrea;
	public static volatile SingularAttribute<CntjEstados, CntjTransiciones> cntjTransicionesId;
	public static volatile SingularAttribute<CntjEstados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjEstados, CntjProcesos> cntjProcesosId;
	public static volatile SingularAttribute<CntjEstados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjEstados, Integer> id;
	public static volatile SingularAttribute<CntjEstados, String> usuarioModifica;
	public static volatile SingularAttribute<CntjEstados, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String CNTJ_ESTADO_GRUPOS_LIST = "cntjEstadoGruposList";
	public static final String TIPO = "tipo";
	public static final String CNTJ_ESTADO_USUARIOS_LIST = "cntjEstadoUsuariosList";
	public static final String CNTJ_EXPEDIENTES_LIST = "cntjExpedientesList";
	public static final String RESULTADO_COMITE = "resultadoComite";
	public static final String CNTJ_TRANSICIONES_LIST = "cntjTransicionesList";
	public static final String MODIFICA_FECHA = "modificaFecha";
	public static final String NOMBRE = "nombre";
	public static final String CNTJ_ESTADO_PLANTILLAS_LIST = "cntjEstadoPlantillasList";
	public static final String CNTJ_ESTADO_EJECUCIONES_LIST = "cntjEstadoEjecucionesList";
	public static final String CNTJ_ESTADO_PROCESO_DOCUMENTOS_LIST = "cntjEstadoProcesoDocumentosList";
	public static final String VALIDA_GRUPO = "validaGrupo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MODIFICA_DATOS = "modificaDatos";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_TRANSICIONES_ID = "cntjTransicionesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String CNTJ_PROCESOS_ID = "cntjProcesosId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

