package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo3Cargas.class)
public abstract class AuAnexo3Cargas_ {

	public static volatile SingularAttribute<AuAnexo3Cargas, String> nombreArchivo;
	public static volatile SingularAttribute<AuAnexo3Cargas, Integer> estado;
	public static volatile SingularAttribute<AuAnexo3Cargas, String> usuarioGestionEstado;
	public static volatile SingularAttribute<AuAnexo3Cargas, Integer> registrosTotal;
	public static volatile SingularAttribute<AuAnexo3Cargas, String> archivo;
	public static volatile SingularAttribute<AuAnexo3Cargas, String> ruta;
	public static volatile SingularAttribute<AuAnexo3Cargas, Date> fechaHoraGestionEstado;
	public static volatile SingularAttribute<AuAnexo3Cargas, Date> fechaHoraInicio;
	public static volatile ListAttribute<AuAnexo3Cargas, AuAnexo3CargaDetalles> auAnexo3CargaDetallesList;
	public static volatile SingularAttribute<AuAnexo3Cargas, Integer> registrosRechazados;
	public static volatile SingularAttribute<AuAnexo3Cargas, Boolean> existe;
	public static volatile SingularAttribute<AuAnexo3Cargas, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<AuAnexo3Cargas, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo3Cargas, String> estadoObservacion;
	public static volatile SingularAttribute<AuAnexo3Cargas, String> terminalCrea;
	public static volatile ListAttribute<AuAnexo3Cargas, AuAnexo3CargaSucesos> auAnexo3CargaSucesosList;
	public static volatile SingularAttribute<AuAnexo3Cargas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo3Cargas, Integer> id;
	public static volatile SingularAttribute<AuAnexo3Cargas, String> terminalGestionEstado;
	public static volatile SingularAttribute<AuAnexo3Cargas, Date> fechaHoraFin;
	public static volatile SingularAttribute<AuAnexo3Cargas, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<AuAnexo3Cargas, Integer> registrosExitosos;
	public static volatile ListAttribute<AuAnexo3Cargas, AuAnexos3> auAnexos3List;
	public static volatile ListAttribute<AuAnexo3Cargas, AuCargaDetallesAnexos3> auCargaDetallesAnexos3List;

	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ESTADO = "estado";
	public static final String USUARIO_GESTION_ESTADO = "usuarioGestionEstado";
	public static final String REGISTROS_TOTAL = "registrosTotal";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String FECHA_HORA_GESTION_ESTADO = "fechaHoraGestionEstado";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String AU_ANEXO3_CARGA_DETALLES_LIST = "auAnexo3CargaDetallesList";
	public static final String REGISTROS_RECHAZADOS = "registrosRechazados";
	public static final String EXISTE = "existe";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ESTADO_OBSERVACION = "estadoObservacion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AU_ANEXO3_CARGA_SUCESOS_LIST = "auAnexo3CargaSucesosList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String TERMINAL_GESTION_ESTADO = "terminalGestionEstado";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String REGISTROS_EXITOSOS = "registrosExitosos";
	public static final String AU_ANEXOS3_LIST = "auAnexos3List";
	public static final String AU_CARGA_DETALLES_ANEXOS3_LIST = "auCargaDetallesAnexos3List";

}

