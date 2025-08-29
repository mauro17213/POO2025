package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsAtOtrosServicios.class)
public abstract class CmRipsAtOtrosServicios_ {

	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> codigoServicio;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, Integer> maeTipoServicioId;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> maeTipoServicioValor;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> maTecnologiaValor;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, BigDecimal> total;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> codigoReps;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, Integer> fila;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, Integer> id;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> documentoAfiliado;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> autorizacion;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, BigDecimal> valorUnidades;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, Integer> maTecnologiaId;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> numeroFactura;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> nombreServicio;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, Integer> unidades;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> maeTipoServicioCodigo;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CmRipsAtOtrosServicios, Integer> maeTipoDocumentoId;

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

