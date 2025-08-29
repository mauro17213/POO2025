package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuNoSolicitudEntregaCargaSucesos.class)
public abstract class AuNoSolicitudEntregaCargaSucesos_ {

	public static volatile SingularAttribute<AuNoSolicitudEntregaCargaSucesos, Integer> estado;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargaSucesos, Date> fechaHoraProceso;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargaSucesos, byte[]> data;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargaSucesos, AuNoSolicitudEntregaCargas> auNoSolicitudEntregaCargasId;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargaSucesos, Integer> fila;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargaSucesos, String> detalleFallo;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargaSucesos, Integer> id;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargaSucesos, AuNoSolicitudEntregas> auNoSolicitudEntregasId;

	public static final String ESTADO = "estado";
	public static final String FECHA_HORA_PROCESO = "fechaHoraProceso";
	public static final String DATA = "data";
	public static final String AU_NO_SOLICITUD_ENTREGA_CARGAS_ID = "auNoSolicitudEntregaCargasId";
	public static final String FILA = "fila";
	public static final String DETALLE_FALLO = "detalleFallo";
	public static final String ID = "id";
	public static final String AU_NO_SOLICITUD_ENTREGAS_ID = "auNoSolicitudEntregasId";

}

