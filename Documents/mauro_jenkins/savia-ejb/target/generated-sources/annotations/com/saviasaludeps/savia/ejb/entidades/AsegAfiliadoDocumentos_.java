package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegAfiliadoDocumentos.class)
public abstract class AsegAfiliadoDocumentos_ {

	public static volatile SingularAttribute<AsegAfiliadoDocumentos, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<AsegAfiliadoDocumentos, Date> fechaExpedicion;
	public static volatile SingularAttribute<AsegAfiliadoDocumentos, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AsegAfiliadoDocumentos, String> usuarioCrea;
	public static volatile SingularAttribute<AsegAfiliadoDocumentos, String> terminalCrea;
	public static volatile SingularAttribute<AsegAfiliadoDocumentos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegAfiliadoDocumentos, Integer> id;
	public static volatile SingularAttribute<AsegAfiliadoDocumentos, String> numeroDocumento;
	public static volatile SingularAttribute<AsegAfiliadoDocumentos, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<AsegAfiliadoDocumentos, Integer> maeTipoDocumentoId;

	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String FECHA_EXPEDICION = "fechaExpedicion";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";

}

