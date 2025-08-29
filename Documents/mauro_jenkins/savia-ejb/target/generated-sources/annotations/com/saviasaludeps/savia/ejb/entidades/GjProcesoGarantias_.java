package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GjProcesoGarantias.class)
public abstract class GjProcesoGarantias_ {

	public static volatile SingularAttribute<GjProcesoGarantias, String> apellidos;
	public static volatile SingularAttribute<GjProcesoGarantias, GjProcesoHistoricos> gjProcesoHistoricosId;
	public static volatile SingularAttribute<GjProcesoGarantias, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<GjProcesoGarantias, String> usuarioCrea;
	public static volatile SingularAttribute<GjProcesoGarantias, GjProcesos> gjProcesosId;
	public static volatile SingularAttribute<GjProcesoGarantias, String> terminalCrea;
	public static volatile SingularAttribute<GjProcesoGarantias, String> documento;
	public static volatile SingularAttribute<GjProcesoGarantias, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GjProcesoGarantias, Integer> id;
	public static volatile SingularAttribute<GjProcesoGarantias, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<GjProcesoGarantias, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<GjProcesoGarantias, String> nombres;

	public static final String APELLIDOS = "apellidos";
	public static final String GJ_PROCESO_HISTORICOS_ID = "gjProcesoHistoricosId";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String GJ_PROCESOS_ID = "gjProcesosId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String DOCUMENTO = "documento";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String NOMBRES = "nombres";

}

