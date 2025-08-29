package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuNoSolicitudCargas.class)
public abstract class AuNoSolicitudCargas_ {

	public static volatile SingularAttribute<AuNoSolicitudCargas, String> nombreArchivo;
	public static volatile SingularAttribute<AuNoSolicitudCargas, Integer> estado;
	public static volatile SingularAttribute<AuNoSolicitudCargas, String> usuarioGestionEstado;
	public static volatile SingularAttribute<AuNoSolicitudCargas, Integer> registrosTotal;
	public static volatile SingularAttribute<AuNoSolicitudCargas, String> archivo;
	public static volatile SingularAttribute<AuNoSolicitudCargas, String> ruta;
	public static volatile SingularAttribute<AuNoSolicitudCargas, Date> fechaHoraGestionEstado;
	public static volatile SingularAttribute<AuNoSolicitudCargas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<AuNoSolicitudCargas, Integer> registrosRechazados;
	public static volatile SingularAttribute<AuNoSolicitudCargas, Boolean> existe;
	public static volatile ListAttribute<AuNoSolicitudCargas, AuNoSolicitudCargaDetalles> auNoSolicitudCargaDetallesList;
	public static volatile SingularAttribute<AuNoSolicitudCargas, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<AuNoSolicitudCargas, String> usuarioCrea;
	public static volatile SingularAttribute<AuNoSolicitudCargas, String> estadoObservacion;
	public static volatile SingularAttribute<AuNoSolicitudCargas, String> terminalCrea;
	public static volatile ListAttribute<AuNoSolicitudCargas, AuNoSolicitudCargaSucesos> auNoSolicitudCargaSucesosList;
	public static volatile SingularAttribute<AuNoSolicitudCargas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuNoSolicitudCargas, Integer> id;
	public static volatile SingularAttribute<AuNoSolicitudCargas, String> terminalGestionEstado;
	public static volatile SingularAttribute<AuNoSolicitudCargas, Date> fechaHoraFin;
	public static volatile SingularAttribute<AuNoSolicitudCargas, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<AuNoSolicitudCargas, Integer> registrosExitosos;

	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ESTADO = "estado";
	public static final String USUARIO_GESTION_ESTADO = "usuarioGestionEstado";
	public static final String REGISTROS_TOTAL = "registrosTotal";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String FECHA_HORA_GESTION_ESTADO = "fechaHoraGestionEstado";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String REGISTROS_RECHAZADOS = "registrosRechazados";
	public static final String EXISTE = "existe";
	public static final String AU_NO_SOLICITUD_CARGA_DETALLES_LIST = "auNoSolicitudCargaDetallesList";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ESTADO_OBSERVACION = "estadoObservacion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AU_NO_SOLICITUD_CARGA_SUCESOS_LIST = "auNoSolicitudCargaSucesosList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String TERMINAL_GESTION_ESTADO = "terminalGestionEstado";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String REGISTROS_EXITOSOS = "registrosExitosos";

}

