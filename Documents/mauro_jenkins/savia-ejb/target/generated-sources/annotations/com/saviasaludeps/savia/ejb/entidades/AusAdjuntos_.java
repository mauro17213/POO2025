package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusAdjuntos.class)
public abstract class AusAdjuntos_ {

	public static volatile SingularAttribute<AusAdjuntos, String> archivo;
	public static volatile SingularAttribute<AusAdjuntos, String> ruta;
	public static volatile SingularAttribute<AusAdjuntos, AusCasos> ausCasosId;
	public static volatile SingularAttribute<AusAdjuntos, AusSolicitudes> ausSolicitudesId;
	public static volatile SingularAttribute<AusAdjuntos, String> nombre;
	public static volatile SingularAttribute<AusAdjuntos, AusCasoServicios> ausServiciosId;
	public static volatile SingularAttribute<AusAdjuntos, String> terminalModifica;
	public static volatile SingularAttribute<AusAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<AusAdjuntos, AusSeguimientos> ausSeguimientosId;
	public static volatile SingularAttribute<AusAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<AusAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusAdjuntos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AusAdjuntos, Integer> id;
	public static volatile SingularAttribute<AusAdjuntos, String> usuarioModifica;

	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String AUS_CASOS_ID = "ausCasosId";
	public static final String AUS_SOLICITUDES_ID = "ausSolicitudesId";
	public static final String NOMBRE = "nombre";
	public static final String AUS_SERVICIOS_ID = "ausServiciosId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String AUS_SEGUIMIENTOS_ID = "ausSeguimientosId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

