package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GjTerceros.class)
public abstract class GjTerceros_ {

	public static volatile SingularAttribute<GjTerceros, String> apellidos;
	public static volatile SingularAttribute<GjTerceros, Short> tipo;
	public static volatile SingularAttribute<GjTerceros, Integer> gnUbicacionesId;
	public static volatile SingularAttribute<GjTerceros, String> direccion;
	public static volatile SingularAttribute<GjTerceros, String> documento;
	public static volatile SingularAttribute<GjTerceros, String> nombres;
	public static volatile SingularAttribute<GjTerceros, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<GjTerceros, String> terminalModifica;
	public static volatile SingularAttribute<GjTerceros, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<GjTerceros, String> razonSocial;
	public static volatile SingularAttribute<GjTerceros, String> usuarioCrea;
	public static volatile SingularAttribute<GjTerceros, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<GjTerceros, String> terminalCrea;
	public static volatile SingularAttribute<GjTerceros, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GjTerceros, Date> fechaHoraModifica;
	public static volatile ListAttribute<GjTerceros, GjTerceroContactos> gjTerceroContactosList;
	public static volatile SingularAttribute<GjTerceros, Integer> id;
	public static volatile SingularAttribute<GjTerceros, String> telefono;
	public static volatile SingularAttribute<GjTerceros, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<GjTerceros, String> correoElectronico;
	public static volatile ListAttribute<GjTerceros, GjProcesoTerceros> gjProcesoTercerosList;
	public static volatile SingularAttribute<GjTerceros, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<GjTerceros, String> usuarioModifica;

	public static final String APELLIDOS = "apellidos";
	public static final String TIPO = "tipo";
	public static final String GN_UBICACIONES_ID = "gnUbicacionesId";
	public static final String DIRECCION = "direccion";
	public static final String DOCUMENTO = "documento";
	public static final String NOMBRES = "nombres";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String RAZON_SOCIAL = "razonSocial";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String GJ_TERCERO_CONTACTOS_LIST = "gjTerceroContactosList";
	public static final String ID = "id";
	public static final String TELEFONO = "telefono";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String CORREO_ELECTRONICO = "correoElectronico";
	public static final String GJ_PROCESO_TERCEROS_LIST = "gjProcesoTercerosList";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

