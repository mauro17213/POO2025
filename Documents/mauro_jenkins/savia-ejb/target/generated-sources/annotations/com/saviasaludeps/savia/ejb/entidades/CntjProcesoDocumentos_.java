package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjProcesoDocumentos.class)
public abstract class CntjProcesoDocumentos_ {

	public static volatile SingularAttribute<CntjProcesoDocumentos, String> descripcion;
	public static volatile SingularAttribute<CntjProcesoDocumentos, String> nombre;
	public static volatile ListAttribute<CntjProcesoDocumentos, CntjEstadoProcesoDocumentos> cntjEstadoProcesoDocumentosList;
	public static volatile SingularAttribute<CntjProcesoDocumentos, Integer> tipoDocumento;
	public static volatile SingularAttribute<CntjProcesoDocumentos, String> terminalModifica;
	public static volatile SingularAttribute<CntjProcesoDocumentos, String> usuarioCrea;
	public static volatile ListAttribute<CntjProcesoDocumentos, CntjPlantillas> cntjPlantillasList;
	public static volatile SingularAttribute<CntjProcesoDocumentos, String> terminalCrea;
	public static volatile SingularAttribute<CntjProcesoDocumentos, Integer> etapaContratacion;
	public static volatile SingularAttribute<CntjProcesoDocumentos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjProcesoDocumentos, CntjProcesos> cntjProcesosId;
	public static volatile SingularAttribute<CntjProcesoDocumentos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjProcesoDocumentos, Integer> id;
	public static volatile SingularAttribute<CntjProcesoDocumentos, String> usuarioModifica;
	public static volatile SingularAttribute<CntjProcesoDocumentos, Integer> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String NOMBRE = "nombre";
	public static final String CNTJ_ESTADO_PROCESO_DOCUMENTOS_LIST = "cntjEstadoProcesoDocumentosList";
	public static final String TIPO_DOCUMENTO = "tipoDocumento";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNTJ_PLANTILLAS_LIST = "cntjPlantillasList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ETAPA_CONTRATACION = "etapaContratacion";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String CNTJ_PROCESOS_ID = "cntjProcesosId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

