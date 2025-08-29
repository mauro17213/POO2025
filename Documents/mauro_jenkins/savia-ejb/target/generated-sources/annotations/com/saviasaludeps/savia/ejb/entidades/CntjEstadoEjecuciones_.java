package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjEstadoEjecuciones.class)
public abstract class CntjEstadoEjecuciones_ {

	public static volatile ListAttribute<CntjEstadoEjecuciones, CntjExpedienteAuditorias> cntjExpedienteAuditoriasList;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, CntjDocumentos> cntjDocumentosId;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, Date> fechaEjecucion;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, CntjLineas> cntjLineasId;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, String> terminalModifica;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, String> usuarioCrea;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, String> terminalCrea;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, CntjEstados> cntjEstadosId;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, CntjExpedientes> cntjExpedientesId;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, Integer> id;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, String> observacion;
	public static volatile SingularAttribute<CntjEstadoEjecuciones, String> usuarioModifica;

	public static final String CNTJ_EXPEDIENTE_AUDITORIAS_LIST = "cntjExpedienteAuditoriasList";
	public static final String CNTJ_DOCUMENTOS_ID = "cntjDocumentosId";
	public static final String FECHA_EJECUCION = "fechaEjecucion";
	public static final String CNTJ_LINEAS_ID = "cntjLineasId";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_ESTADOS_ID = "cntjEstadosId";
	public static final String CNTJ_EXPEDIENTES_ID = "cntjExpedientesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

