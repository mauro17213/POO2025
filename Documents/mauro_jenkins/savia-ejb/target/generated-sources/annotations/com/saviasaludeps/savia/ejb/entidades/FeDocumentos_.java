package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FeDocumentos.class)
public abstract class FeDocumentos_ {

	public static volatile ListAttribute<FeDocumentos, FeDocumentos> feDocumentosList;
	public static volatile SingularAttribute<FeDocumentos, FeDocumentos> feDocumentosId;
	public static volatile SingularAttribute<FeDocumentos, String> estado;
	public static volatile SingularAttribute<FeDocumentos, Short> referenciaDocumentoTipo;
	public static volatile SingularAttribute<FeDocumentos, String> documentoNumero;
	public static volatile SingularAttribute<FeDocumentos, BigDecimal> documentoValor;
	public static volatile SingularAttribute<FeDocumentos, String> referenciaDocumentoNumero;
	public static volatile SingularAttribute<FeDocumentos, String> estadoDescripcion;
	public static volatile SingularAttribute<FeDocumentos, Date> fechaRadicacion;
	public static volatile SingularAttribute<FeDocumentos, String> prestadorNit;
	public static volatile SingularAttribute<FeDocumentos, Integer> referenciaDocumentoId;
	public static volatile SingularAttribute<FeDocumentos, String> referenciaPrestadoroNit;
	public static volatile SingularAttribute<FeDocumentos, String> codigoUnicoDian;
	public static volatile SingularAttribute<FeDocumentos, Date> fechaDocumento;
	public static volatile SingularAttribute<FeDocumentos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<FeDocumentos, Integer> id;
	public static volatile SingularAttribute<FeDocumentos, Short> documentoTipo;
	public static volatile SingularAttribute<FeDocumentos, Integer> documentoId;

	public static final String FE_DOCUMENTOS_LIST = "feDocumentosList";
	public static final String FE_DOCUMENTOS_ID = "feDocumentosId";
	public static final String ESTADO = "estado";
	public static final String REFERENCIA_DOCUMENTO_TIPO = "referenciaDocumentoTipo";
	public static final String DOCUMENTO_NUMERO = "documentoNumero";
	public static final String DOCUMENTO_VALOR = "documentoValor";
	public static final String REFERENCIA_DOCUMENTO_NUMERO = "referenciaDocumentoNumero";
	public static final String ESTADO_DESCRIPCION = "estadoDescripcion";
	public static final String FECHA_RADICACION = "fechaRadicacion";
	public static final String PRESTADOR_NIT = "prestadorNit";
	public static final String REFERENCIA_DOCUMENTO_ID = "referenciaDocumentoId";
	public static final String REFERENCIA_PRESTADORO_NIT = "referenciaPrestadoroNit";
	public static final String CODIGO_UNICO_DIAN = "codigoUnicoDian";
	public static final String FECHA_DOCUMENTO = "fechaDocumento";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String DOCUMENTO_TIPO = "documentoTipo";
	public static final String DOCUMENTO_ID = "documentoId";

}

