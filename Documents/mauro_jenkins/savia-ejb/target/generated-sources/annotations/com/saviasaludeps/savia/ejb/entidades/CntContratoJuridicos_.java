package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoJuridicos.class)
public abstract class CntContratoJuridicos_ {

	public static volatile SingularAttribute<CntContratoJuridicos, Date> fechaInicial;
	public static volatile SingularAttribute<CntContratoJuridicos, Integer> estado;
	public static volatile SingularAttribute<CntContratoJuridicos, String> maeDocumentoJuridicoValor;
	public static volatile SingularAttribute<CntContratoJuridicos, BigDecimal> valor;
	public static volatile SingularAttribute<CntContratoJuridicos, Date> fechaFinal;
	public static volatile SingularAttribute<CntContratoJuridicos, Integer> consecutivo;
	public static volatile SingularAttribute<CntContratoJuridicos, String> terminalModifica;
	public static volatile SingularAttribute<CntContratoJuridicos, String> maeDocumentoJuridicoCodigo;
	public static volatile SingularAttribute<CntContratoJuridicos, String> usuarioCrea;
	public static volatile SingularAttribute<CntContratoJuridicos, String> terminalCrea;
	public static volatile SingularAttribute<CntContratoJuridicos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoJuridicos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntContratoJuridicos, Integer> id;
	public static volatile SingularAttribute<CntContratoJuridicos, Integer> maeDocumentoJuridicoId;
	public static volatile SingularAttribute<CntContratoJuridicos, String> usuarioModifica;
	public static volatile SingularAttribute<CntContratoJuridicos, CntContratos> cntContratosId;

	public static final String FECHA_INICIAL = "fechaInicial";
	public static final String ESTADO = "estado";
	public static final String MAE_DOCUMENTO_JURIDICO_VALOR = "maeDocumentoJuridicoValor";
	public static final String VALOR = "valor";
	public static final String FECHA_FINAL = "fechaFinal";
	public static final String CONSECUTIVO = "consecutivo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String MAE_DOCUMENTO_JURIDICO_CODIGO = "maeDocumentoJuridicoCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_DOCUMENTO_JURIDICO_ID = "maeDocumentoJuridicoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

