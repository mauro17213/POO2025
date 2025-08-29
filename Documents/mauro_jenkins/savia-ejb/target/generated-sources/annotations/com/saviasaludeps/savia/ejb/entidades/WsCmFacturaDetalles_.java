package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WsCmFacturaDetalles.class)
public abstract class WsCmFacturaDetalles_ {

	public static volatile SingularAttribute<WsCmFacturaDetalles, String> consecutivo;
	public static volatile SingularAttribute<WsCmFacturaDetalles, String> conceptoContable;
	public static volatile SingularAttribute<WsCmFacturaDetalles, BigDecimal> valorOperacion;
	public static volatile SingularAttribute<WsCmFacturaDetalles, String> usuarioCrea;
	public static volatile SingularAttribute<WsCmFacturaDetalles, WsCmFacturas> wsCmFacturasId;
	public static volatile SingularAttribute<WsCmFacturaDetalles, String> terminalCrea;
	public static volatile SingularAttribute<WsCmFacturaDetalles, Date> fechaHoraCrea;
	public static volatile SingularAttribute<WsCmFacturaDetalles, Integer> id;
	public static volatile SingularAttribute<WsCmFacturaDetalles, Integer> idDetalles;
	public static volatile SingularAttribute<WsCmFacturaDetalles, String> codigoMunicipio;
	public static volatile SingularAttribute<WsCmFacturaDetalles, Short> tipologia;

	public static final String CONSECUTIVO = "consecutivo";
	public static final String CONCEPTO_CONTABLE = "conceptoContable";
	public static final String VALOR_OPERACION = "valorOperacion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String WS_CM_FACTURAS_ID = "wsCmFacturasId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ID_DETALLES = "idDetalles";
	public static final String CODIGO_MUNICIPIO = "codigoMunicipio";
	public static final String TIPOLOGIA = "tipologia";

}

