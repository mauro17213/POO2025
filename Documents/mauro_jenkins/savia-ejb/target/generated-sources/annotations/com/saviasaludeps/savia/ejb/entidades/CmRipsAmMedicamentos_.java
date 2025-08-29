package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsAmMedicamentos.class)
public abstract class CmRipsAmMedicamentos_ {

	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> maeTipoMedicamentoValor;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, BigDecimal> valorUnitario;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, BigDecimal> valorAPagar;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> codigoReps;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> maeTipoMedicamentoCodigo;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, Integer> fila;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, Integer> id;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> documentoAfiliado;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> concentracion;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> nombreGenerico;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> autorizacion;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, Integer> maMedicamentoId;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> unidadMedida;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> maMedicamentoCodigo;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> numeroFactura;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, Integer> maeTipoMedicamentoId;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> maMedicamentoValor;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> numeroUnidades;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, String> formaFarmaceutica;
	public static volatile SingularAttribute<CmRipsAmMedicamentos, Integer> maeTipoDocumentoId;

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

