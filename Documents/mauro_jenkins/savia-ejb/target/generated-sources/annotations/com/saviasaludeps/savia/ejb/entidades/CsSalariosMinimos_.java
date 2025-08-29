package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CsSalariosMinimos.class)
public abstract class CsSalariosMinimos_ {

	public static volatile SingularAttribute<CsSalariosMinimos, Integer> salarioMinimoMensual;
	public static volatile SingularAttribute<CsSalariosMinimos, Integer> salarioMinimoDiario;
	public static volatile SingularAttribute<CsSalariosMinimos, String> usuarioCrea;
	public static volatile SingularAttribute<CsSalariosMinimos, String> terminalCrea;
	public static volatile SingularAttribute<CsSalariosMinimos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CsSalariosMinimos, Integer> agno;
	public static volatile SingularAttribute<CsSalariosMinimos, BigDecimal> incremento;
	public static volatile SingularAttribute<CsSalariosMinimos, Integer> id;

	public static final String SALARIO_MINIMO_MENSUAL = "salarioMinimoMensual";
	public static final String SALARIO_MINIMO_DIARIO = "salarioMinimoDiario";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String AGNO = "agno";
	public static final String INCREMENTO = "incremento";
	public static final String ID = "id";

}

