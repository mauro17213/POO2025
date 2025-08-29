package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsAfFacturas.class)
public abstract class CmRipsAfFacturas_ {

	public static volatile SingularAttribute<CmRipsAfFacturas, String> nombreAdministradora;
	public static volatile SingularAttribute<CmRipsAfFacturas, BigDecimal> valorCopago;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsAfFacturas, Date> fechaFinal;
	public static volatile SingularAttribute<CmRipsAfFacturas, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsAfFacturas, BigDecimal> valorAPagar;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> razonSocial;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> codigoReps;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> numeroPoliza;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsAfFacturas, Date> fechaInicio;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsAfFacturas, Integer> fila;
	public static volatile SingularAttribute<CmRipsAfFacturas, Date> fechaFactura;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> nit;
	public static volatile SingularAttribute<CmRipsAfFacturas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsAfFacturas, Integer> id;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> codigoEps;
	public static volatile SingularAttribute<CmRipsAfFacturas, BigDecimal> valorComision;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> contrato;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> numeroFactura;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsAfFacturas, BigDecimal> valorDescuento;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CmRipsAfFacturas, String> planBeneficios;
	public static volatile SingularAttribute<CmRipsAfFacturas, Integer> maeTipoDocumentoId;

	public static final String NOMBRE_ADMINISTRADORA = "nombreAdministradora";
	public static final String VALOR_COPAGO = "valorCopago";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String FECHA_FINAL = "fechaFinal";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String VALOR_APAGAR = "valorAPagar";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String RAZON_SOCIAL = "razonSocial";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CODIGO_REPS = "codigoReps";
	public static final String NUMERO_POLIZA = "numeroPoliza";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String FECHA_FACTURA = "fechaFactura";
	public static final String NIT = "nit";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CODIGO_EPS = "codigoEps";
	public static final String VALOR_COMISION = "valorComision";
	public static final String CONTRATO = "contrato";
	public static final String NUMERO_FACTURA = "numeroFactura";
	public static final String ARCHIVO_NOMBRE_ORIGINAL = "archivoNombreOriginal";
	public static final String VALOR_DESCUENTO = "valorDescuento";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String PLAN_BENEFICIOS = "planBeneficios";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";

}

