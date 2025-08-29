package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeCargas.class)
public abstract class PeCargas_ {

	public static volatile SingularAttribute<PeCargas, Integer> exitosos;
	public static volatile SingularAttribute<PeCargas, Integer> tipo;
	public static volatile SingularAttribute<PeCargas, Integer> estado;
	public static volatile SingularAttribute<PeCargas, String> archivo;
	public static volatile SingularAttribute<PeCargas, String> ruta;
	public static volatile SingularAttribute<PeCargas, String> respNombre;
	public static volatile SingularAttribute<PeCargas, String> respArchivo;
	public static volatile SingularAttribute<PeCargas, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<PeCargas, String> respRuta;
	public static volatile SingularAttribute<PeCargas, Integer> registros;
	public static volatile SingularAttribute<PeCargas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<PeCargas, Boolean> existe;
	public static volatile SingularAttribute<PeCargas, String> nombre;
	public static volatile SingularAttribute<PeCargas, String> detalle;
	public static volatile SingularAttribute<PeCargas, String> usuarioCrea;
	public static volatile SingularAttribute<PeCargas, String> terminalCrea;
	public static volatile SingularAttribute<PeCargas, Integer> fallidos;
	public static volatile SingularAttribute<PeCargas, Date> fechaHoraCrea;
	public static volatile ListAttribute<PeCargas, PeCargaHistoricos> peCargaHistoricosList;
	public static volatile SingularAttribute<PeCargas, Integer> id;
	public static volatile SingularAttribute<PeCargas, Boolean> respExiste;
	public static volatile SingularAttribute<PeCargas, Date> fechaHoraFin;
	public static volatile SingularAttribute<PeCargas, GnEmpresas> gnEmpresasId;

	public static final String EXITOSOS = "exitosos";
	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String RESP_NOMBRE = "respNombre";
	public static final String RESP_ARCHIVO = "respArchivo";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String RESP_RUTA = "respRuta";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String EXISTE = "existe";
	public static final String NOMBRE = "nombre";
	public static final String DETALLE = "detalle";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FALLIDOS = "fallidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String PE_CARGA_HISTORICOS_LIST = "peCargaHistoricosList";
	public static final String ID = "id";
	public static final String RESP_EXISTE = "respExiste";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";

}

