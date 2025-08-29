package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsCargaApProcedimientos.class)
public abstract class CmRipsCargaApProcedimientos_ {

	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> maDiagnosticoPrincipalId;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maeFinalidadProcedimientoValor;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> maDiagnosticoRelacionado2Id;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> maDiagnosticoRelacionado1Id;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maTecnologiaValor;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maDiagnosticoRelacionado2Valor;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, BigDecimal> valorAPagar;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> codigoReps;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maeAmbitoAtencionCodigoValor;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> fila;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> id;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> documentoAfiliado;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maeAmbitoAtencionCodigo;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> autorizacion;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maePersonalAtiendeCodigo;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Date> fechaProcedimiento;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maDiagnosticoRelacionado1Valor;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> maTecnologiaId;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> numeroFactura;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> maeAmbitoAtencionId;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maDiagnosticoRelacionado2Codigo;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maePersonalAtiendeValor;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maDiagnosticoPrincipalValor;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maDiagnosticoPrincipalCodigo;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maeFormaActoCodigo;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> maeFormaActoId;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maeFormaActoValor;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> maeFinalidadProcedimientoId;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maDiagnosticoRelacionado1Codigo;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, String> maeFinalidadProcedimientoCodigo;
	public static volatile SingularAttribute<CmRipsCargaApProcedimientos, Integer> maePersonalAtiendeId;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_ID = "maDiagnosticoPrincipalId";
	public static final String MAE_FINALIDAD_PROCEDIMIENTO_VALOR = "maeFinalidadProcedimientoValor";
	public static final String MA_DIAGNOSTICO_RELACIONADO2_ID = "maDiagnosticoRelacionado2Id";
	public static final String MA_DIAGNOSTICO_RELACIONADO1_ID = "maDiagnosticoRelacionado1Id";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String MA_DIAGNOSTICO_RELACIONADO2_VALOR = "maDiagnosticoRelacionado2Valor";
	public static final String VALOR_APAGAR = "valorAPagar";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CODIGO_REPS = "codigoReps";
	public static final String MAE_AMBITO_ATENCION_CODIGO_VALOR = "maeAmbitoAtencionCodigoValor";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String DOCUMENTO_AFILIADO = "documentoAfiliado";
	public static final String MAE_AMBITO_ATENCION_CODIGO = "maeAmbitoAtencionCodigo";
	public static final String AUTORIZACION = "autorizacion";
	public static final String MAE_PERSONAL_ATIENDE_CODIGO = "maePersonalAtiendeCodigo";
	public static final String FECHA_PROCEDIMIENTO = "fechaProcedimiento";
	public static final String MA_DIAGNOSTICO_RELACIONADO1_VALOR = "maDiagnosticoRelacionado1Valor";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String NUMERO_FACTURA = "numeroFactura";
	public static final String MAE_AMBITO_ATENCION_ID = "maeAmbitoAtencionId";
	public static final String MA_DIAGNOSTICO_RELACIONADO2_CODIGO = "maDiagnosticoRelacionado2Codigo";
	public static final String ARCHIVO_NOMBRE_ORIGINAL = "archivoNombreOriginal";
	public static final String MAE_PERSONAL_ATIENDE_VALOR = "maePersonalAtiendeValor";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_VALOR = "maDiagnosticoPrincipalValor";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_CODIGO = "maDiagnosticoPrincipalCodigo";
	public static final String MAE_FORMA_ACTO_CODIGO = "maeFormaActoCodigo";
	public static final String MAE_FORMA_ACTO_ID = "maeFormaActoId";
	public static final String MAE_FORMA_ACTO_VALOR = "maeFormaActoValor";
	public static final String MAE_FINALIDAD_PROCEDIMIENTO_ID = "maeFinalidadProcedimientoId";
	public static final String MA_DIAGNOSTICO_RELACIONADO1_CODIGO = "maDiagnosticoRelacionado1Codigo";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String MAE_FINALIDAD_PROCEDIMIENTO_CODIGO = "maeFinalidadProcedimientoCodigo";
	public static final String MAE_PERSONAL_ATIENDE_ID = "maePersonalAtiendeId";

}

