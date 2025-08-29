package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GjProcesoTerceros.class)
public abstract class GjProcesoTerceros_ {

	public static volatile SingularAttribute<GjProcesoTerceros, String> apellidos;
	public static volatile SingularAttribute<GjProcesoTerceros, GjProcesos> gjProcesosId;
	public static volatile SingularAttribute<GjProcesoTerceros, String> documento;
	public static volatile SingularAttribute<GjProcesoTerceros, String> maeCalidadActuaValor;
	public static volatile SingularAttribute<GjProcesoTerceros, String> nombres;
	public static volatile SingularAttribute<GjProcesoTerceros, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<GjProcesoTerceros, String> terminalModifica;
	public static volatile SingularAttribute<GjProcesoTerceros, String> razonSocial;
	public static volatile SingularAttribute<GjProcesoTerceros, String> usuarioCrea;
	public static volatile SingularAttribute<GjProcesoTerceros, Integer> maeCalidadActuaId;
	public static volatile SingularAttribute<GjProcesoTerceros, String> terminalCrea;
	public static volatile SingularAttribute<GjProcesoTerceros, GjTerceros> gjTercerosId;
	public static volatile SingularAttribute<GjProcesoTerceros, String> maeCalidadActuaCodigo;
	public static volatile SingularAttribute<GjProcesoTerceros, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GjProcesoTerceros, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GjProcesoTerceros, Integer> id;
	public static volatile SingularAttribute<GjProcesoTerceros, String> telefono;
	public static volatile SingularAttribute<GjProcesoTerceros, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<GjProcesoTerceros, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<GjProcesoTerceros, String> usuarioModifica;

	public static final String APELLIDOS = "apellidos";
	public static final String GJ_PROCESOS_ID = "gjProcesosId";
	public static final String DOCUMENTO = "documento";
	public static final String MAE_CALIDAD_ACTUA_VALOR = "maeCalidadActuaValor";
	public static final String NOMBRES = "nombres";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String RAZON_SOCIAL = "razonSocial";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_CALIDAD_ACTUA_ID = "maeCalidadActuaId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GJ_TERCEROS_ID = "gjTercerosId";
	public static final String MAE_CALIDAD_ACTUA_CODIGO = "maeCalidadActuaCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String TELEFONO = "telefono";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

