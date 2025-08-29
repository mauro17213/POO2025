package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoGirosCapita.class)
public abstract class CntContratoGirosCapita_ {

	public static volatile SingularAttribute<CntContratoGirosCapita, Date> periodoPago;
	public static volatile SingularAttribute<CntContratoGirosCapita, String> usuarioCrea;
	public static volatile SingularAttribute<CntContratoGirosCapita, BigDecimal> valorUpcAfiliado;
	public static volatile SingularAttribute<CntContratoGirosCapita, String> terminalCrea;
	public static volatile SingularAttribute<CntContratoGirosCapita, Integer> cantidadAfiliados;
	public static volatile SingularAttribute<CntContratoGirosCapita, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoGirosCapita, Integer> id;
	public static volatile SingularAttribute<CntContratoGirosCapita, BigDecimal> valorGiroCapita;
	public static volatile SingularAttribute<CntContratoGirosCapita, Date> periodoGiro;
	public static volatile SingularAttribute<CntContratoGirosCapita, CntContratos> cntContratosId;

	public static final String PERIODO_PAGO = "periodoPago";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String VALOR_UPC_AFILIADO = "valorUpcAfiliado";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CANTIDAD_AFILIADOS = "cantidadAfiliados";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String VALOR_GIRO_CAPITA = "valorGiroCapita";
	public static final String PERIODO_GIRO = "periodoGiro";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

