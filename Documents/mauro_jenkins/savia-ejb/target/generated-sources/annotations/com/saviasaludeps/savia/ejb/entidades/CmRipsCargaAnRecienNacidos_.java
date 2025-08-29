package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsCargaAnRecienNacidos.class)
public abstract class CmRipsCargaAnRecienNacidos_ {

	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Integer> maeSexoId;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Integer> maDiagnosticoPrincipalId;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Date> fechaNacimiento;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Integer> edadGestacion;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> maCausaMuerteDiagnosticoCodigo;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> codigoReps;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Integer> maeControlPenatalId;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Date> horaMuerte;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Integer> fila;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> maeSexoCodigo;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> maeTipoDocumentoMadreCodigo;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Date> horaNacimiento;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Integer> id;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> maeTipoDocumentoMadreValor;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Integer> maeTipoDocumentoMadreId;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> maeSexoValor;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> maeControlPenatalCodigo;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> documentoAfiliadoMadre;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, BigDecimal> peso;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Date> fechaMuerte;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> numeroFactura;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> maDiagnosticoPrincipalValor;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> maDiagnosticoPrincipalCodigo;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> maeControlPenatalValor;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, Integer> maCausaMuerteDiagnosticoId;
	public static volatile SingularAttribute<CmRipsCargaAnRecienNacidos, String> maCausaMuerteDiagnosticoValor;

	public static final String MAE_SEXO_ID = "maeSexoId";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_ID = "maDiagnosticoPrincipalId";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String EDAD_GESTACION = "edadGestacion";
	public static final String MA_CAUSA_MUERTE_DIAGNOSTICO_CODIGO = "maCausaMuerteDiagnosticoCodigo";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CODIGO_REPS = "codigoReps";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_CONTROL_PENATAL_ID = "maeControlPenatalId";
	public static final String HORA_MUERTE = "horaMuerte";
	public static final String FILA = "fila";
	public static final String MAE_SEXO_CODIGO = "maeSexoCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MAE_TIPO_DOCUMENTO_MADRE_CODIGO = "maeTipoDocumentoMadreCodigo";
	public static final String HORA_NACIMIENTO = "horaNacimiento";
	public static final String ID = "id";
	public static final String MAE_TIPO_DOCUMENTO_MADRE_VALOR = "maeTipoDocumentoMadreValor";
	public static final String MAE_TIPO_DOCUMENTO_MADRE_ID = "maeTipoDocumentoMadreId";
	public static final String MAE_SEXO_VALOR = "maeSexoValor";
	public static final String MAE_CONTROL_PENATAL_CODIGO = "maeControlPenatalCodigo";
	public static final String DOCUMENTO_AFILIADO_MADRE = "documentoAfiliadoMadre";
	public static final String PESO = "peso";
	public static final String FECHA_MUERTE = "fechaMuerte";
	public static final String NUMERO_FACTURA = "numeroFactura";
	public static final String ARCHIVO_NOMBRE_ORIGINAL = "archivoNombreOriginal";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_VALOR = "maDiagnosticoPrincipalValor";
	public static final String MA_DIAGNOSTICO_PRINCIPAL_CODIGO = "maDiagnosticoPrincipalCodigo";
	public static final String MAE_CONTROL_PENATAL_VALOR = "maeControlPenatalValor";
	public static final String MA_CAUSA_MUERTE_DIAGNOSTICO_ID = "maCausaMuerteDiagnosticoId";
	public static final String MA_CAUSA_MUERTE_DIAGNOSTICO_VALOR = "maCausaMuerteDiagnosticoValor";

}

