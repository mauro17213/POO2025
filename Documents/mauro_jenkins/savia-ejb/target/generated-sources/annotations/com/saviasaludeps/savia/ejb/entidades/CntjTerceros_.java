package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjTerceros.class)
public abstract class CntjTerceros_ {

	public static volatile ListAttribute<CntjTerceros, CntjTerceroUnionTemporal> cntjTerceroUnionTemporalList;
	public static volatile SingularAttribute<CntjTerceros, Short> tipoTercero;
	public static volatile SingularAttribute<CntjTerceros, GnUbicaciones> gnUbicacionesId;
	public static volatile SingularAttribute<CntjTerceros, String> representanteNumeroDocumento;
	public static volatile SingularAttribute<CntjTerceros, String> telefonoTercero;
	public static volatile ListAttribute<CntjTerceros, CntjContratos> cntjContratosList;
	public static volatile SingularAttribute<CntjTerceros, Boolean> unionTemporal;
	public static volatile SingularAttribute<CntjTerceros, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<CntjTerceros, String> nombreRepresentanteLegal;
	public static volatile SingularAttribute<CntjTerceros, String> maeRepresentanteTipoDocumentoCodigo;
	public static volatile SingularAttribute<CntjTerceros, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CntjTerceros, String> maeAreaCodigo;
	public static volatile SingularAttribute<CntjTerceros, String> terminalModifica;
	public static volatile SingularAttribute<CntjTerceros, String> razonSocial;
	public static volatile SingularAttribute<CntjTerceros, Integer> maeAreaId;
	public static volatile SingularAttribute<CntjTerceros, String> usuarioCrea;
	public static volatile SingularAttribute<CntjTerceros, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CntjTerceros, String> terminalCrea;
	public static volatile SingularAttribute<CntjTerceros, String> maeAreaValor;
	public static volatile SingularAttribute<CntjTerceros, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjTerceros, Integer> id;
	public static volatile SingularAttribute<CntjTerceros, String> numeroDocumento;
	public static volatile SingularAttribute<CntjTerceros, Integer> maeRepresentanteTipoDocumentoId;
	public static volatile SingularAttribute<CntjTerceros, String> codigoHabilitacion;
	public static volatile ListAttribute<CntjTerceros, CntjContratoSeguimientos> cntjContratoSeguimientosList;
	public static volatile SingularAttribute<CntjTerceros, String> direccion;
	public static volatile SingularAttribute<CntjTerceros, String> maeCargoValor;
	public static volatile SingularAttribute<CntjTerceros, Integer> maeCargoId;
	public static volatile ListAttribute<CntjTerceros, CntjContratoSupervisores> cntjContratoSupervisoresList;
	public static volatile SingularAttribute<CntjTerceros, Short> naturalezaJuridica;
	public static volatile ListAttribute<CntjTerceros, CntjTerceroContactos> cntjTerceroContactosList;
	public static volatile SingularAttribute<CntjTerceros, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjTerceros, String> maeCargoCodigo;
	public static volatile SingularAttribute<CntjTerceros, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CntjTerceros, String> correoElectronico;
	public static volatile SingularAttribute<CntjTerceros, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<CntjTerceros, String> usuarioModifica;
	public static volatile SingularAttribute<CntjTerceros, String> maeRepresentanteTipoDocumentoValor;

	public static final String CNTJ_TERCERO_UNION_TEMPORAL_LIST = "cntjTerceroUnionTemporalList";
	public static final String TIPO_TERCERO = "tipoTercero";
	public static final String GN_UBICACIONES_ID = "gnUbicacionesId";
	public static final String REPRESENTANTE_NUMERO_DOCUMENTO = "representanteNumeroDocumento";
	public static final String TELEFONO_TERCERO = "telefonoTercero";
	public static final String CNTJ_CONTRATOS_LIST = "cntjContratosList";
	public static final String UNION_TEMPORAL = "unionTemporal";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String NOMBRE_REPRESENTANTE_LEGAL = "nombreRepresentanteLegal";
	public static final String MAE_REPRESENTANTE_TIPO_DOCUMENTO_CODIGO = "maeRepresentanteTipoDocumentoCodigo";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String MAE_AREA_CODIGO = "maeAreaCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String RAZON_SOCIAL = "razonSocial";
	public static final String MAE_AREA_ID = "maeAreaId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_AREA_VALOR = "maeAreaValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String MAE_REPRESENTANTE_TIPO_DOCUMENTO_ID = "maeRepresentanteTipoDocumentoId";
	public static final String CODIGO_HABILITACION = "codigoHabilitacion";
	public static final String CNTJ_CONTRATO_SEGUIMIENTOS_LIST = "cntjContratoSeguimientosList";
	public static final String DIRECCION = "direccion";
	public static final String MAE_CARGO_VALOR = "maeCargoValor";
	public static final String MAE_CARGO_ID = "maeCargoId";
	public static final String CNTJ_CONTRATO_SUPERVISORES_LIST = "cntjContratoSupervisoresList";
	public static final String NATURALEZA_JURIDICA = "naturalezaJuridica";
	public static final String CNTJ_TERCERO_CONTACTOS_LIST = "cntjTerceroContactosList";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String MAE_CARGO_CODIGO = "maeCargoCodigo";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String CORREO_ELECTRONICO = "correoElectronico";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String MAE_REPRESENTANTE_TIPO_DOCUMENTO_VALOR = "maeRepresentanteTipoDocumentoValor";

}

