package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmPagoFacturaRetenciones.class)
public abstract class CmPagoFacturaRetenciones_ {

	public static volatile SingularAttribute<CmPagoFacturaRetenciones, String> descripcion;
	public static volatile SingularAttribute<CmPagoFacturaRetenciones, String> codigo;
	public static volatile SingularAttribute<CmPagoFacturaRetenciones, BigDecimal> valor;
	public static volatile SingularAttribute<CmPagoFacturaRetenciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmPagoFacturaRetenciones, Integer> id;
	public static volatile SingularAttribute<CmPagoFacturaRetenciones, CmPagoFacturas> cmPagoFacturasId;

	public static final String DESCRIPCION = "descripcion";
	public static final String CODIGO = "codigo";
	public static final String VALOR = "valor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CM_PAGO_FACTURAS_ID = "cmPagoFacturasId";

}

