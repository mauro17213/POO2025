package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoDescuentos.class)
public abstract class CntContratoDescuentos_ {

	public static volatile SingularAttribute<CntContratoDescuentos, String> terminalModifica;
	public static volatile SingularAttribute<CntContratoDescuentos, String> usuarioCrea;
	public static volatile SingularAttribute<CntContratoDescuentos, String> terminalCrea;
	public static volatile SingularAttribute<CntContratoDescuentos, Integer> maeTipoDescuentoId;
	public static volatile SingularAttribute<CntContratoDescuentos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoDescuentos, String> maeTipoDescuentoValor;
	public static volatile SingularAttribute<CntContratoDescuentos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntContratoDescuentos, Integer> id;
	public static volatile SingularAttribute<CntContratoDescuentos, String> maeTipoDescuentoCodigo;
	public static volatile SingularAttribute<CntContratoDescuentos, BigDecimal> porcentaje;
	public static volatile SingularAttribute<CntContratoDescuentos, String> usuarioModifica;
	public static volatile SingularAttribute<CntContratoDescuentos, CntContratos> cntContratosId;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_DESCUENTO_ID = "maeTipoDescuentoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MAE_TIPO_DESCUENTO_VALOR = "maeTipoDescuentoValor";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_DESCUENTO_CODIGO = "maeTipoDescuentoCodigo";
	public static final String PORCENTAJE = "porcentaje";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

