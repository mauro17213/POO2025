package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjEstadoProcesoDocumentos.class)
public abstract class CntjEstadoProcesoDocumentos_ {

	public static volatile SingularAttribute<CntjEstadoProcesoDocumentos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjEstadoProcesoDocumentos, CntjProcesoDocumentos> cntjProcesoDocumentosId;
	public static volatile SingularAttribute<CntjEstadoProcesoDocumentos, String> terminalCrea;
	public static volatile SingularAttribute<CntjEstadoProcesoDocumentos, CntjEstados> cntjEstadosId;
	public static volatile SingularAttribute<CntjEstadoProcesoDocumentos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjEstadoProcesoDocumentos, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNTJ_PROCESO_DOCUMENTOS_ID = "cntjProcesoDocumentosId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_ESTADOS_ID = "cntjEstadosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

