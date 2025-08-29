package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmGestionUsuarios.class)
public abstract class CmGestionUsuarios_ {

	public static volatile SingularAttribute<CmGestionUsuarios, Integer> estado;
	public static volatile SingularAttribute<CmGestionUsuarios, String> usuarioCrea;
	public static volatile SingularAttribute<CmGestionUsuarios, BigDecimal> porcentajePagadoEps;
	public static volatile SingularAttribute<CmGestionUsuarios, String> terminalCrea;
	public static volatile SingularAttribute<CmGestionUsuarios, BigDecimal> valorAceptadoIps;
	public static volatile SingularAttribute<CmGestionUsuarios, CmDetalles> cmDetallesId;
	public static volatile SingularAttribute<CmGestionUsuarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmGestionUsuarios, Integer> id;
	public static volatile SingularAttribute<CmGestionUsuarios, BigDecimal> porcentajeAceptadoIps;
	public static volatile SingularAttribute<CmGestionUsuarios, String> usuarioGestiona;
	public static volatile SingularAttribute<CmGestionUsuarios, BigDecimal> valorPagadoEps;

	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String PORCENTAJE_PAGADO_EPS = "porcentajePagadoEps";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR_ACEPTADO_IPS = "valorAceptadoIps";
	public static final String CM_DETALLES_ID = "cmDetallesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String PORCENTAJE_ACEPTADO_IPS = "porcentajeAceptadoIps";
	public static final String USUARIO_GESTIONA = "usuarioGestiona";
	public static final String VALOR_PAGADO_EPS = "valorPagadoEps";

}

