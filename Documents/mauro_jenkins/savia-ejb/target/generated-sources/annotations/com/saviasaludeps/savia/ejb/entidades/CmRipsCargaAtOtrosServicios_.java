package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsCargaAtOtrosServicios.class)
public abstract class CmRipsCargaAtOtrosServicios_ {

	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> codigoServicio;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, Integer> maeTipoServicioId;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> maeTipoServicioValor;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> maTecnologiaValor;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, BigDecimal> total;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> codigoReps;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, Integer> fila;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, Integer> id;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> documentoAfiliado;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> autorizacion;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, BigDecimal> valorUnidades;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, Integer> maTecnologiaId;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> numeroFactura;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> nombreServicio;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, Integer> unidades;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> maeTipoServicioCodigo;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CmRipsCargaAtOtrosServicios, Integer> maeTipoDocumentoId;

	public static final String CODIGO_SERVICIO = "codigoServicio";
	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String MAE_TIPO_SERVICIO_ID = "maeTipoServicioId";
	public static final String MAE_TIPO_SERVICIO_VALOR = "maeTipoServicioValor";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TOTAL = "total";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CODIGO_REPS = "codigoReps";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String DOCUMENTO_AFILIADO = "documentoAfiliado";
	public static final String AUTORIZACION = "autorizacion";
	public static final String VALOR_UNIDADES = "valorUnidades";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String NUMERO_FACTURA = "numeroFactura";
	public static final String ARCHIVO_NOMBRE_ORIGINAL = "archivoNombreOriginal";
	public static final String NOMBRE_SERVICIO = "nombreServicio";
	public static final String UNIDADES = "unidades";
	public static final String MAE_TIPO_SERVICIO_CODIGO = "maeTipoServicioCodigo";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";

}

