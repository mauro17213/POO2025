package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjContratoIndicadores.class)
public abstract class CntjContratoIndicadores_ {

	public static volatile SingularAttribute<CntjContratoIndicadores, String> descripcion;
	public static volatile SingularAttribute<CntjContratoIndicadores, Integer> tipoIndicador;
	public static volatile SingularAttribute<CntjContratoIndicadores, Boolean> aplicaDescuento;
	public static volatile SingularAttribute<CntjContratoIndicadores, CntjContratos> cntjContratosId;
	public static volatile SingularAttribute<CntjContratoIndicadores, String> terminalModifica;
	public static volatile SingularAttribute<CntjContratoIndicadores, String> usuarioCrea;
	public static volatile SingularAttribute<CntjContratoIndicadores, BigDecimal> valorDescuento;
	public static volatile SingularAttribute<CntjContratoIndicadores, BigDecimal> porcentajeDescuento;
	public static volatile SingularAttribute<CntjContratoIndicadores, String> terminalCrea;
	public static volatile SingularAttribute<CntjContratoIndicadores, String> meta;
	public static volatile SingularAttribute<CntjContratoIndicadores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjContratoIndicadores, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjContratoIndicadores, Integer> id;
	public static volatile SingularAttribute<CntjContratoIndicadores, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO_INDICADOR = "tipoIndicador";
	public static final String APLICA_DESCUENTO = "aplicaDescuento";
	public static final String CNTJ_CONTRATOS_ID = "cntjContratosId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String VALOR_DESCUENTO = "valorDescuento";
	public static final String PORCENTAJE_DESCUENTO = "porcentajeDescuento";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String META = "meta";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

