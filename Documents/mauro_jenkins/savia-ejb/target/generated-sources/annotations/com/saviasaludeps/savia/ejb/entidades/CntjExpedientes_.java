package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjExpedientes.class)
public abstract class CntjExpedientes_ {

	public static volatile ListAttribute<CntjExpedientes, CntjExpedienteAuditorias> cntjExpedienteAuditoriasList;
	public static volatile SingularAttribute<CntjExpedientes, CntjEstados> cntjEstadosActualId;
	public static volatile SingularAttribute<CntjExpedientes, GnUsuarios> gnUsuariosResponsableId;
	public static volatile ListAttribute<CntjExpedientes, CntjExpedienteResponsables> cntjExpedienteResponsablesList;
	public static volatile ListAttribute<CntjExpedientes, CntjExpedientes> cntjExpedientesList;
	public static volatile SingularAttribute<CntjExpedientes, GnUsuarios> gnUsuariosPropietarioId;
	public static volatile SingularAttribute<CntjExpedientes, String> contrato;
	public static volatile ListAttribute<CntjExpedientes, CntjContratos> cntjContratosList;
	public static volatile SingularAttribute<CntjExpedientes, String> numeroExpediente;
	public static volatile ListAttribute<CntjExpedientes, CntjEstadoEjecuciones> cntjEstadoEjecucionesList;
	public static volatile SingularAttribute<CntjExpedientes, Integer> estadoActual;
	public static volatile SingularAttribute<CntjExpedientes, String> terminalModifica;
	public static volatile SingularAttribute<CntjExpedientes, String> usuarioCrea;
	public static volatile ListAttribute<CntjExpedientes, CntjLineas> cntjLineasList;
	public static volatile SingularAttribute<CntjExpedientes, String> jsonData;
	public static volatile SingularAttribute<CntjExpedientes, String> terminalCrea;
	public static volatile ListAttribute<CntjExpedientes, CntjDocumentos> cntjDocumentosList;
	public static volatile SingularAttribute<CntjExpedientes, CntjExpedientes> cntjExpedientesId;
	public static volatile SingularAttribute<CntjExpedientes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjExpedientes, CntjProcesos> cntjProcesosId;
	public static volatile SingularAttribute<CntjExpedientes, Date> fechaEjecucionEstado;
	public static volatile SingularAttribute<CntjExpedientes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjExpedientes, Integer> id;
	public static volatile SingularAttribute<CntjExpedientes, String> usuarioModifica;

	public static final String CNTJ_EXPEDIENTE_AUDITORIAS_LIST = "cntjExpedienteAuditoriasList";
	public static final String CNTJ_ESTADOS_ACTUAL_ID = "cntjEstadosActualId";
	public static final String GN_USUARIOS_RESPONSABLE_ID = "gnUsuariosResponsableId";
	public static final String CNTJ_EXPEDIENTE_RESPONSABLES_LIST = "cntjExpedienteResponsablesList";
	public static final String CNTJ_EXPEDIENTES_LIST = "cntjExpedientesList";
	public static final String GN_USUARIOS_PROPIETARIO_ID = "gnUsuariosPropietarioId";
	public static final String CONTRATO = "contrato";
	public static final String CNTJ_CONTRATOS_LIST = "cntjContratosList";
	public static final String NUMERO_EXPEDIENTE = "numeroExpediente";
	public static final String CNTJ_ESTADO_EJECUCIONES_LIST = "cntjEstadoEjecucionesList";
	public static final String ESTADO_ACTUAL = "estadoActual";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNTJ_LINEAS_LIST = "cntjLineasList";
	public static final String JSON_DATA = "jsonData";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_DOCUMENTOS_LIST = "cntjDocumentosList";
	public static final String CNTJ_EXPEDIENTES_ID = "cntjExpedientesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String CNTJ_PROCESOS_ID = "cntjProcesosId";
	public static final String FECHA_EJECUCION_ESTADO = "fechaEjecucionEstado";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

