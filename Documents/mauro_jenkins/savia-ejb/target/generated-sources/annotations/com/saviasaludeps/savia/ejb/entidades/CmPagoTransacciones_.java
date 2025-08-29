package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmPagoTransacciones.class)
public abstract class CmPagoTransacciones_ {

	public static volatile SingularAttribute<CmPagoTransacciones, CmPagos> cmPagosId;
	public static volatile SingularAttribute<CmPagoTransacciones, Integer> facturas;
	public static volatile SingularAttribute<CmPagoTransacciones, Date> fechaHoraCrea;
	public static volatile ListAttribute<CmPagoTransacciones, CmPagoFacturas> cmPagoFacturasList;
	public static volatile SingularAttribute<CmPagoTransacciones, Integer> id;
	public static volatile SingularAttribute<CmPagoTransacciones, Boolean> finalizado;
	public static volatile SingularAttribute<CmPagoTransacciones, Integer> nut;
	public static volatile SingularAttribute<CmPagoTransacciones, Integer> paquete;

	public static final String CM_PAGOS_ID = "cmPagosId";
	public static final String FACTURAS = "facturas";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String CM_PAGO_FACTURAS_LIST = "cmPagoFacturasList";
	public static final String ID = "id";
	public static final String FINALIZADO = "finalizado";
	public static final String NUT = "nut";
	public static final String PAQUETE = "paquete";

}

