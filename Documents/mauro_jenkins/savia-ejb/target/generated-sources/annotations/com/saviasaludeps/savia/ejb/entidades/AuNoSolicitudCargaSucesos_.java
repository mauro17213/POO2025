package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuNoSolicitudCargaSucesos.class)
public abstract class AuNoSolicitudCargaSucesos_ {

	public static volatile SingularAttribute<AuNoSolicitudCargaSucesos, String> descripcion;
	public static volatile SingularAttribute<AuNoSolicitudCargaSucesos, Integer> columna;
	public static volatile SingularAttribute<AuNoSolicitudCargaSucesos, AuNoSolicitudCargaDetalles> auNoSolicitudCargaDetallesId;
	public static volatile SingularAttribute<AuNoSolicitudCargaSucesos, Integer> tipo;
	public static volatile SingularAttribute<AuNoSolicitudCargaSucesos, Date> fechaHora;
	public static volatile SingularAttribute<AuNoSolicitudCargaSucesos, Integer> fila;
	public static volatile SingularAttribute<AuNoSolicitudCargaSucesos, Integer> id;
	public static volatile SingularAttribute<AuNoSolicitudCargaSucesos, AuNoSolicitudCargas> auNoSolicitudCargasId;

	public static final String DESCRIPCION = "descripcion";
	public static final String COLUMNA = "columna";
	public static final String AU_NO_SOLICITUD_CARGA_DETALLES_ID = "auNoSolicitudCargaDetallesId";
	public static final String TIPO = "tipo";
	public static final String FECHA_HORA = "fechaHora";
	public static final String FILA = "fila";
	public static final String ID = "id";
	public static final String AU_NO_SOLICITUD_CARGAS_ID = "auNoSolicitudCargasId";

}

