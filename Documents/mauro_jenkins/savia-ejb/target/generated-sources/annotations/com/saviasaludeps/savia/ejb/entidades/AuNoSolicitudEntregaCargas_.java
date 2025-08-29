package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuNoSolicitudEntregaCargas.class)
public abstract class AuNoSolicitudEntregaCargas_ {

	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, Integer> exitosos;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, Integer> estado;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, String> archivo;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, String> ruta;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, Integer> registros;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, Boolean> existe;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, String> nombre;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, String> detalle;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, String> usuarioCrea;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, Integer> tipoTecnologia;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, String> terminalCrea;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, Integer> fallidos;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, Integer> id;
	public static volatile ListAttribute<AuNoSolicitudEntregaCargas, AuNoSolicitudEntregaCargaSucesos> auNoSolicitudEntregaCargaSucesosList;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, Date> fechaHoraFin;
	public static volatile SingularAttribute<AuNoSolicitudEntregaCargas, GnEmpresas> gnEmpresasId;

	public static final String EXITOSOS = "exitosos";
	public static final String ESTADO = "estado";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String EXISTE = "existe";
	public static final String NOMBRE = "nombre";
	public static final String DETALLE = "detalle";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FALLIDOS = "fallidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String AU_NO_SOLICITUD_ENTREGA_CARGA_SUCESOS_LIST = "auNoSolicitudEntregaCargaSucesosList";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";

}

