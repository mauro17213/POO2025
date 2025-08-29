package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GjProcesoAdjuntos.class)
public abstract class GjProcesoAdjuntos_ {

	public static volatile SingularAttribute<GjProcesoAdjuntos, GjProcesoHistoricos> gjProcesoHistoricosId;
	public static volatile SingularAttribute<GjProcesoAdjuntos, String> nombreArchivo;
	public static volatile SingularAttribute<GjProcesoAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<GjProcesoAdjuntos, GjProcesos> gjProcesosId;
	public static volatile SingularAttribute<GjProcesoAdjuntos, String> archivo;
	public static volatile SingularAttribute<GjProcesoAdjuntos, String> ruta;
	public static volatile SingularAttribute<GjProcesoAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<GjProcesoAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GjProcesoAdjuntos, Integer> id;
	public static volatile SingularAttribute<GjProcesoAdjuntos, String> maeTipoCodigo;
	public static volatile SingularAttribute<GjProcesoAdjuntos, Integer> maeTipoId;
	public static volatile SingularAttribute<GjProcesoAdjuntos, String> maeTipoValor;

	public static final String GJ_PROCESO_HISTORICOS_ID = "gjProcesoHistoricosId";
	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String GJ_PROCESOS_ID = "gjProcesosId";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_TIPO_CODIGO = "maeTipoCodigo";
	public static final String MAE_TIPO_ID = "maeTipoId";
	public static final String MAE_TIPO_VALOR = "maeTipoValor";

}

