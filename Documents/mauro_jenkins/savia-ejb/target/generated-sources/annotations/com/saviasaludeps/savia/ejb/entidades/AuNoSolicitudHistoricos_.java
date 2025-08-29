package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuNoSolicitudHistoricos.class)
public abstract class AuNoSolicitudHistoricos_ {

	public static volatile SingularAttribute<AuNoSolicitudHistoricos, Integer> estado;
	public static volatile SingularAttribute<AuNoSolicitudHistoricos, Integer> tipo;
	public static volatile SingularAttribute<AuNoSolicitudHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<AuNoSolicitudHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<AuNoSolicitudHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuNoSolicitudHistoricos, Integer> id;
	public static volatile SingularAttribute<AuNoSolicitudHistoricos, AuNoSolicitudes> auNoSolicitudesId;
	public static volatile SingularAttribute<AuNoSolicitudHistoricos, String> observacion;

	public static final String ESTADO = "estado";
	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String AU_NO_SOLICITUDES_ID = "auNoSolicitudesId";
	public static final String OBSERVACION = "observacion";

}

