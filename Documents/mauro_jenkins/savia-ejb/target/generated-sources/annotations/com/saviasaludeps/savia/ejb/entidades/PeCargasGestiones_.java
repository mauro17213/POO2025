package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeCargasGestiones.class)
public abstract class PeCargasGestiones_ {

	public static volatile SingularAttribute<PeCargasGestiones, Integer> exitosos;
	public static volatile SingularAttribute<PeCargasGestiones, Integer> estado;
	public static volatile SingularAttribute<PeCargasGestiones, String> archivo;
	public static volatile SingularAttribute<PeCargasGestiones, String> ruta;
	public static volatile SingularAttribute<PeCargasGestiones, String> respNombre;
	public static volatile SingularAttribute<PeCargasGestiones, String> respArchivo;
	public static volatile SingularAttribute<PeCargasGestiones, String> respRuta;
	public static volatile SingularAttribute<PeCargasGestiones, Integer> registros;
	public static volatile SingularAttribute<PeCargasGestiones, Date> fechaHoraInicio;
	public static volatile SingularAttribute<PeCargasGestiones, Boolean> existe;
	public static volatile SingularAttribute<PeCargasGestiones, String> nombre;
	public static volatile SingularAttribute<PeCargasGestiones, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<PeCargasGestiones, String> detalle;
	public static volatile SingularAttribute<PeCargasGestiones, String> usuarioCrea;
	public static volatile SingularAttribute<PeCargasGestiones, String> terminalCrea;
	public static volatile SingularAttribute<PeCargasGestiones, Integer> fallidos;
	public static volatile SingularAttribute<PeCargasGestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeCargasGestiones, Integer> id;
	public static volatile SingularAttribute<PeCargasGestiones, Boolean> respExiste;
	public static volatile SingularAttribute<PeCargasGestiones, Date> fechaHoraFin;

	public static final String EXITOSOS = "exitosos";
	public static final String ESTADO = "estado";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String RESP_NOMBRE = "respNombre";
	public static final String RESP_ARCHIVO = "respArchivo";
	public static final String RESP_RUTA = "respRuta";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String EXISTE = "existe";
	public static final String NOMBRE = "nombre";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String DETALLE = "detalle";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FALLIDOS = "fallidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String RESP_EXISTE = "respExiste";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";

}

