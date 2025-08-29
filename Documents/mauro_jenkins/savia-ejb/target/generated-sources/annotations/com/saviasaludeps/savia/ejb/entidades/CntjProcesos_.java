package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjProcesos.class)
public abstract class CntjProcesos_ {

	public static volatile SingularAttribute<CntjProcesos, String> descripcion;
	public static volatile ListAttribute<CntjProcesos, CntjCampos> cntjCamposList;
	public static volatile ListAttribute<CntjProcesos, CntjExpedientes> cntjExpedientesList;
	public static volatile SingularAttribute<CntjProcesos, String> nombre;
	public static volatile SingularAttribute<CntjProcesos, Integer> tipoProceso;
	public static volatile SingularAttribute<CntjProcesos, String> terminalModifica;
	public static volatile ListAttribute<CntjProcesos, CntjProcesoDocumentos> cntjProcesoDocumentosList;
	public static volatile SingularAttribute<CntjProcesos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjProcesos, String> terminalCrea;
	public static volatile ListAttribute<CntjProcesos, CntjEstados> cntjEstadosList;
	public static volatile SingularAttribute<CntjProcesos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjProcesos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjProcesos, Integer> id;
	public static volatile SingularAttribute<CntjProcesos, String> usuarioModifica;
	public static volatile SingularAttribute<CntjProcesos, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String CNTJ_CAMPOS_LIST = "cntjCamposList";
	public static final String CNTJ_EXPEDIENTES_LIST = "cntjExpedientesList";
	public static final String NOMBRE = "nombre";
	public static final String TIPO_PROCESO = "tipoProceso";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNTJ_PROCESO_DOCUMENTOS_LIST = "cntjProcesoDocumentosList";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_ESTADOS_LIST = "cntjEstadosList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

