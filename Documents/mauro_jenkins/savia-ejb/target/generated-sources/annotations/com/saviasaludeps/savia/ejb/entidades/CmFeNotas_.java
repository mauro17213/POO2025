package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFeNotas.class)
public abstract class CmFeNotas_ {

	public static volatile SingularAttribute<CmFeNotas, BigDecimal> valorNota;
	public static volatile SingularAttribute<CmFeNotas, Integer> tipo;
	public static volatile SingularAttribute<CmFeNotas, String> usuarioCrea;
	public static volatile SingularAttribute<CmFeNotas, Date> fechaHoraEmision;
	public static volatile SingularAttribute<CmFeNotas, String> terminalCrea;
	public static volatile SingularAttribute<CmFeNotas, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmFeNotas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmFeNotas, CmFeRipsFacturas> cmFeRipsFacturasId;
	public static volatile SingularAttribute<CmFeNotas, Integer> id;
	public static volatile SingularAttribute<CmFeNotas, String> cude;
	public static volatile SingularAttribute<CmFeNotas, String> numeroNota;

	public static final String VALOR_NOTA = "valorNota";
	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_HORA_EMISION = "fechaHoraEmision";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String CM_FE_RIPS_FACTURAS_ID = "cmFeRipsFacturasId";
	public static final String ID = "id";
	public static final String CUDE = "cude";
	public static final String NUMERO_NOTA = "numeroNota";

}

