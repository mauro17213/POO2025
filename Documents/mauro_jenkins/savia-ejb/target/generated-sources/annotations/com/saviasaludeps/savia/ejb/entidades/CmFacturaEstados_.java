package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFacturaEstados.class)
public abstract class CmFacturaEstados_ {

	public static volatile SingularAttribute<CmFacturaEstados, String> usuarioCrea;
	public static volatile SingularAttribute<CmFacturaEstados, String> terminalCrea;
	public static volatile SingularAttribute<CmFacturaEstados, Integer> estadoFactura;
	public static volatile SingularAttribute<CmFacturaEstados, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmFacturaEstados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmFacturaEstados, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ESTADO_FACTURA = "estadoFactura";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

