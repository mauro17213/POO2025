package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsAuUrgencias.class)
public abstract class CmRipsAuUrgencias_ {

	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maDiagnosticoSalidaCodigo;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> maDiagnosticoRelacionado3Id;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maDiagnosticoMuerteCodigo;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maeCausaExternaCodigo;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> maDiagnosticoRelacionado2Id;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> maDiagnosticoRelacionado1Id;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> maeCausaExternaId;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maDiagnosticoMuerteValor;
	public static volatile SingularAttribute<CmRipsAuUrgencias, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maDiagnosticoRelacionado2Valor;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maDiagnosticoRelacionado3Codigo;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> codigoReps;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Date> horaIngreso;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> maDiagnosticoSalidaId;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maDiagnosticoSalidaValor;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> fila;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maeEstadoSalidaValor;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Date> fechaSalida;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> id;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> maDiagnosticoMuerteId;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> documentoAfiliado;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maeEstadoSalidaCodigo;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> autorizacion;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maDiagnosticoRelacionado1Valor;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maeDestinoSalidaValor;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> maeDestinoSalidaId;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maeDestinoSalidaCodigo;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> numeroFactura;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maDiagnosticoRelacionado2Codigo;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Date> horaSalida;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Date> fechaIngreso;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> maeEstadoSalidaId;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maDiagnosticoRelacionado3Valor;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maeCausaExternaValor;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maDiagnosticoRelacionado1Codigo;
	public static volatile SingularAttribute<CmRipsAuUrgencias, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CmRipsAuUrgencias, Integer> maeTipoDocumentoId;

	public static final String MA_DIAGNOSTICO_SALIDA_CODIGO = "maDiagnosticoSalidaCodigo";
	public static final String MA_DIAGNOSTICO_RELACIONADO3_ID = "maDiagnosticoRelacionado3Id";
	public static final String MA_DIAGNOSTICO_MUERTE_CODIGO = "maDiagnosticoMuerteCodigo";
	public static final String MAE_CAUSA_EXTERNA_CODIGO = "maeCausaExternaCodigo";
	public static final String MA_DIAGNOSTICO_RELACIONADO2_ID = "maDiagnosticoRelacionado2Id";
	public static final String MA_DIAGNOSTICO_RELACIONADO1_ID = "maDiagnosticoRelacionado1Id";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String MAE_CAUSA_EXTERNA_ID = "maeCausaExternaId";
	public static final String MA_DIAGNOSTICO_MUERTE_VALOR = "maDiagnosticoMuerteValor";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String MA_DIAGNOSTICO_RELACIONADO2_VALOR = "maDiagnosticoRelacionado2Valor";
	public static final String MA_DIAGNOSTICO_RELACIONADO3_CODIGO = "maDiagnosticoRelacionado3Codigo";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CODIGO_REPS = "codigoReps";
	public static final String HORA_INGRESO = "horaIngreso";
	public static final String MA_DIAGNOSTICO_SALIDA_ID = "maDiagnosticoSalidaId";
	public static final String MA_DIAGNOSTICO_SALIDA_VALOR = "maDiagnosticoSalidaValor";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String MAE_ESTADO_SALIDA_VALOR = "maeEstadoSalidaValor";
	public static final String FECHA_SALIDA = "fechaSalida";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MA_DIAGNOSTICO_MUERTE_ID = "maDiagnosticoMuerteId";
	public static final String DOCUMENTO_AFILIADO = "documentoAfiliado";
	public static final String MAE_ESTADO_SALIDA_CODIGO = "maeEstadoSalidaCodigo";
	public static final String AUTORIZACION = "autorizacion";
	public static final String MA_DIAGNOSTICO_RELACIONADO1_VALOR = "maDiagnosticoRelacionado1Valor";
	public static final String MAE_DESTINO_SALIDA_VALOR = "maeDestinoSalidaValor";
	public static final String MAE_DESTINO_SALIDA_ID = "maeDestinoSalidaId";
	public static final String MAE_DESTINO_SALIDA_CODIGO = "maeDestinoSalidaCodigo";
	public static final String NUMERO_FACTURA = "numeroFactura";
	public static final String MA_DIAGNOSTICO_RELACIONADO2_CODIGO = "maDiagnosticoRelacionado2Codigo";
	public static final String ARCHIVO_NOMBRE_ORIGINAL = "archivoNombreOriginal";
	public static final String HORA_SALIDA = "horaSalida";
	public static final String FECHA_INGRESO = "fechaIngreso";
	public static final String MAE_ESTADO_SALIDA_ID = "maeEstadoSalidaId";
	public static final String MA_DIAGNOSTICO_RELACIONADO3_VALOR = "maDiagnosticoRelacionado3Valor";
	public static final String MAE_CAUSA_EXTERNA_VALOR = "maeCausaExternaValor";
	public static final String MA_DIAGNOSTICO_RELACIONADO1_CODIGO = "maDiagnosticoRelacionado1Codigo";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";

}

