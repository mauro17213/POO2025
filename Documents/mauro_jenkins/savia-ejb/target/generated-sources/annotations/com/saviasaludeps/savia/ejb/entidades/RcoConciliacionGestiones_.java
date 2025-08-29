package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RcoConciliacionGestiones.class)
public abstract class RcoConciliacionGestiones_ {

	public static volatile SingularAttribute<RcoConciliacionGestiones, RcoConciliaciones> rcoConciliacionesId;
	public static volatile SingularAttribute<RcoConciliacionGestiones, Integer> acuerdoRecobro;
	public static volatile SingularAttribute<RcoConciliacionGestiones, Long> valorAceptadoIps;
	public static volatile SingularAttribute<RcoConciliacionGestiones, Boolean> cobroConciliacion;
	public static volatile SingularAttribute<RcoConciliacionGestiones, String> terminalModifica;
	public static volatile SingularAttribute<RcoConciliacionGestiones, RcoFacturaDetalles> rcoFacturaDetallesId;
	public static volatile SingularAttribute<RcoConciliacionGestiones, String> usuarioCrea;
	public static volatile SingularAttribute<RcoConciliacionGestiones, Long> valorRestante;
	public static volatile SingularAttribute<RcoConciliacionGestiones, String> terminalCrea;
	public static volatile SingularAttribute<RcoConciliacionGestiones, Long> valorTotal;
	public static volatile SingularAttribute<RcoConciliacionGestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RcoConciliacionGestiones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<RcoConciliacionGestiones, Integer> id;
	public static volatile SingularAttribute<RcoConciliacionGestiones, String> observacion;
	public static volatile SingularAttribute<RcoConciliacionGestiones, String> usuarioModifica;

	public static final String RCO_CONCILIACIONES_ID = "rcoConciliacionesId";
	public static final String ACUERDO_RECOBRO = "acuerdoRecobro";
	public static final String VALOR_ACEPTADO_IPS = "valorAceptadoIps";
	public static final String COBRO_CONCILIACION = "cobroConciliacion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String RCO_FACTURA_DETALLES_ID = "rcoFacturaDetallesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String VALOR_RESTANTE = "valorRestante";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR_TOTAL = "valorTotal";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

