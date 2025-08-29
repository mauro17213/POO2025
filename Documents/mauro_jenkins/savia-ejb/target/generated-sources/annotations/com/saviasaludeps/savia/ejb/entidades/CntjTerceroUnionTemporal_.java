package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjTerceroUnionTemporal.class)
public abstract class CntjTerceroUnionTemporal_ {

	public static volatile SingularAttribute<CntjTerceroUnionTemporal, Date> fechaHoraBorra;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, String> usuarioBorra;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, BigDecimal> porcentajeParticipacion;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, String> terminalModifica;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, CntjTerceros> cntjTercerosId;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, String> razonSocial;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, String> usuarioCrea;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, String> terminalCrea;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, Boolean> borrado;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, Integer> naturalezaJuridica;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, String> terminalBorra;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, Integer> id;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, String> numeroDocumento;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<CntjTerceroUnionTemporal, String> usuarioModifica;

	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String PORCENTAJE_PARTICIPACION = "porcentajeParticipacion";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNTJ_TERCEROS_ID = "cntjTercerosId";
	public static final String RAZON_SOCIAL = "razonSocial";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String NATURALEZA_JURIDICA = "naturalezaJuridica";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

