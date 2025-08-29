package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjExpedienteAuditorias.class)
public abstract class CntjExpedienteAuditorias_ {

	public static volatile SingularAttribute<CntjExpedienteAuditorias, String> terminalModifica;
	public static volatile SingularAttribute<CntjExpedienteAuditorias, String> usuarioCrea;
	public static volatile SingularAttribute<CntjExpedienteAuditorias, CntjEstadoEjecuciones> cntjEstadoEjecucionesId;
	public static volatile SingularAttribute<CntjExpedienteAuditorias, String> terminalCrea;
	public static volatile SingularAttribute<CntjExpedienteAuditorias, CntjExpedientes> cntjExpedientesId;
	public static volatile SingularAttribute<CntjExpedienteAuditorias, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjExpedienteAuditorias, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjExpedienteAuditorias, Integer> id;
	public static volatile SingularAttribute<CntjExpedienteAuditorias, String> observacion;
	public static volatile SingularAttribute<CntjExpedienteAuditorias, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNTJ_ESTADO_EJECUCIONES_ID = "cntjEstadoEjecucionesId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_EXPEDIENTES_ID = "cntjExpedientesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

