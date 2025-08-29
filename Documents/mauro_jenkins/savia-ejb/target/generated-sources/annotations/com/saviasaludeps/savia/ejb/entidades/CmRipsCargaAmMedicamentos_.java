package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsCargaAmMedicamentos.class)
public abstract class CmRipsCargaAmMedicamentos_ {

	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> maeTipoMedicamentoValor;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, BigDecimal> valorUnitario;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, BigDecimal> valorAPagar;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> codigoReps;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> maeTipoMedicamentoCodigo;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, Integer> fila;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, Integer> id;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> documentoAfiliado;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> concentracion;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> nombreGenerico;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> autorizacion;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, Integer> maMedicamentoId;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> unidadMedida;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> maMedicamentoCodigo;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> numeroFactura;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, Integer> maeTipoMedicamentoId;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> maMedicamentoValor;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> numeroUnidades;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, String> formaFarmaceutica;
	public static volatile SingularAttribute<CmRipsCargaAmMedicamentos, Integer> maeTipoDocumentoId;

	public static final String MAE_TIPO_MEDICAMENTO_VALOR = "maeTipoMedicamentoValor";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String VALOR_UNITARIO = "valorUnitario";
	public static final String VALOR_APAGAR = "valorAPagar";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CODIGO_REPS = "codigoReps";
	public static final String MAE_TIPO_MEDICAMENTO_CODIGO = "maeTipoMedicamentoCodigo";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String DOCUMENTO_AFILIADO = "documentoAfiliado";
	public static final String CONCENTRACION = "concentracion";
	public static final String NOMBRE_GENERICO = "nombreGenerico";
	public static final String AUTORIZACION = "autorizacion";
	public static final String MA_MEDICAMENTO_ID = "maMedicamentoId";
	public static final String UNIDAD_MEDIDA = "unidadMedida";
	public static final String MA_MEDICAMENTO_CODIGO = "maMedicamentoCodigo";
	public static final String NUMERO_FACTURA = "numeroFactura";
	public static final String ARCHIVO_NOMBRE_ORIGINAL = "archivoNombreOriginal";
	public static final String MAE_TIPO_MEDICAMENTO_ID = "maeTipoMedicamentoId";
	public static final String MA_MEDICAMENTO_VALOR = "maMedicamentoValor";
	public static final String NUMERO_UNIDADES = "numeroUnidades";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String FORMA_FARMACEUTICA = "formaFarmaceutica";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";

}

