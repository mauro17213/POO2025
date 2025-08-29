package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpAfiliados.class)
public abstract class MpAfiliados_ {

	public static volatile SingularAttribute<MpAfiliados, Integer> maeEstadoAfiliacionId;
	public static volatile SingularAttribute<MpAfiliados, String> segundoNombre;
	public static volatile SingularAttribute<MpAfiliados, String> primerNombre;
	public static volatile SingularAttribute<MpAfiliados, String> primerApellido;
	public static volatile SingularAttribute<MpAfiliados, Date> fechaNacimiento;
	public static volatile SingularAttribute<MpAfiliados, Integer> maeGeneroId;
	public static volatile SingularAttribute<MpAfiliados, String> segundoApellido;
	public static volatile SingularAttribute<MpAfiliados, String> maeGeneroValor;
	public static volatile SingularAttribute<MpAfiliados, String> maeEstadoAfiliacionValor;
	public static volatile SingularAttribute<MpAfiliados, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<MpAfiliados, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<MpAfiliados, String> maeGeneroCodigo;
	public static volatile SingularAttribute<MpAfiliados, String> usuarioCrea;
	public static volatile SingularAttribute<MpAfiliados, String> terminalCrea;
	public static volatile ListAttribute<MpAfiliados, MpPrescripciones> mpPrescripcionesList;
	public static volatile SingularAttribute<MpAfiliados, String> maeEstadoAfiliacionCodigo;
	public static volatile SingularAttribute<MpAfiliados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpAfiliados, Integer> id;
	public static volatile SingularAttribute<MpAfiliados, String> numeroDocumento;
	public static volatile SingularAttribute<MpAfiliados, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<MpAfiliados, Integer> maeTipoDocumentoId;

	public static final String MAE_ESTADO_AFILIACION_ID = "maeEstadoAfiliacionId";
	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String MAE_GENERO_ID = "maeGeneroId";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String MAE_GENERO_VALOR = "maeGeneroValor";
	public static final String MAE_ESTADO_AFILIACION_VALOR = "maeEstadoAfiliacionValor";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String MAE_GENERO_CODIGO = "maeGeneroCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MP_PRESCRIPCIONES_LIST = "mpPrescripcionesList";
	public static final String MAE_ESTADO_AFILIACION_CODIGO = "maeEstadoAfiliacionCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";

}

