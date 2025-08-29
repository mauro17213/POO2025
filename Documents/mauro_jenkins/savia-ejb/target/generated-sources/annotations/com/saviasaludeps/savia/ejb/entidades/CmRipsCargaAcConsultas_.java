package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsCargaAcConsultas.class)
public abstract class CmRipsCargaAcConsultas_ {

	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> maDiagnosticoPrincipalId;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> maeTipoDiagnosticoId;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maeFinalidadConsultaCodigo;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> maDiagnosticoRelacionado3Id;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maeCausaExternaCodigo;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> maDiagnosticoRelacionado2Id;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maTecnologiaValor;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> maDiagnosticoRelacionado1Id;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> maeCausaExternaId;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> numFactura;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maDiagnosticoRelacionado2Valor;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maeTipoDiagnosticoValor;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, BigDecimal> valorAPagar;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Date> fechaConsulta;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maDiagnosticoRelacionado3Codigo;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> codigoReps;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> fila;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maeFinalidadConsultaValor;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> id;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, BigDecimal> valorConsulta;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> documentoAfiliado;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, BigDecimal> valorCuotaModeradora;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> maeFinalidadConsultaId;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> autorizacion;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maDiagnosticoRelacionado1Valor;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> maTecnologiaId;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maDiagnosticoRelacionado2Codigo;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maeTipoDiagnosticoCodigo;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maDiagnosticoPrincipalValor;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maDiagnosticoPrincipalCodigo;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maDiagnosticoRelacionado3Valor;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maeCausaExternaValor;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maDiagnosticoRelacionado1Codigo;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CmRipsCargaAcConsultas, Integer> maeTipoDocumentoId;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_ID = "maDiagnosticoPrincipalId";
	public static final String MAE_TIPO_DIAGNOSTICO_ID = "maeTipoDiagnosticoId";
	public static final String MAE_FINALIDAD_CONSULTA_CODIGO = "maeFinalidadConsultaCodigo";
	public static final String MA_DIAGNOSTICO_RELACIONADO3_ID = "maDiagnosticoRelacionado3Id";
	public static final String MAE_CAUSA_EXTERNA_CODIGO = "maeCausaExternaCodigo";
	public static final String MA_DIAGNOSTICO_RELACIONADO2_ID = "maDiagnosticoRelacionado2Id";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String MA_DIAGNOSTICO_RELACIONADO1_ID = "maDiagnosticoRelacionado1Id";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String MAE_CAUSA_EXTERNA_ID = "maeCausaExternaId";
	public static final String NUM_FACTURA = "numFactura";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String MA_DIAGNOSTICO_RELACIONADO2_VALOR = "maDiagnosticoRelacionado2Valor";
	public static final String MAE_TIPO_DIAGNOSTICO_VALOR = "maeTipoDiagnosticoValor";
	public static final String VALOR_APAGAR = "valorAPagar";
	public static final String FECHA_CONSULTA = "fechaConsulta";
	public static final String MA_DIAGNOSTICO_RELACIONADO3_CODIGO = "maDiagnosticoRelacionado3Codigo";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CODIGO_REPS = "codigoReps";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String MAE_FINALIDAD_CONSULTA_VALOR = "maeFinalidadConsultaValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String VALOR_CONSULTA = "valorConsulta";
	public static final String DOCUMENTO_AFILIADO = "documentoAfiliado";
	public static final String VALOR_CUOTA_MODERADORA = "valorCuotaModeradora";
	public static final String MAE_FINALIDAD_CONSULTA_ID = "maeFinalidadConsultaId";
	public static final String AUTORIZACION = "autorizacion";
	public static final String MA_DIAGNOSTICO_RELACIONADO1_VALOR = "maDiagnosticoRelacionado1Valor";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String MA_DIAGNOSTICO_RELACIONADO2_CODIGO = "maDiagnosticoRelacionado2Codigo";
	public static final String MAE_TIPO_DIAGNOSTICO_CODIGO = "maeTipoDiagnosticoCodigo";
	public static final String ARCHIVO_NOMBRE_ORIGINAL = "archivoNombreOriginal";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_VALOR = "maDiagnosticoPrincipalValor";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_CODIGO = "maDiagnosticoPrincipalCodigo";
	public static final String MA_DIAGNOSTICO_RELACIONADO3_VALOR = "maDiagnosticoRelacionado3Valor";
	public static final String MAE_CAUSA_EXTERNA_VALOR = "maeCausaExternaValor";
	public static final String MA_DIAGNOSTICO_RELACIONADO1_CODIGO = "maDiagnosticoRelacionado1Codigo";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";

}

