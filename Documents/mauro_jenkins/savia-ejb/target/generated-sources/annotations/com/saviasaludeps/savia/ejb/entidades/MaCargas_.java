package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaCargas.class)
public abstract class MaCargas_ {

	public static volatile SingularAttribute<MaCargas, Integer> exitosos;
	public static volatile SingularAttribute<MaCargas, Integer> estado;
	public static volatile SingularAttribute<MaCargas, Integer> tipo;
	public static volatile SingularAttribute<MaCargas, String> archivo;
	public static volatile SingularAttribute<MaCargas, String> ruta;
	public static volatile SingularAttribute<MaCargas, String> respNombre;
	public static volatile SingularAttribute<MaCargas, String> respArchivo;
	public static volatile SingularAttribute<MaCargas, Integer> registros;
	public static volatile SingularAttribute<MaCargas, String> respRuta;
	public static volatile SingularAttribute<MaCargas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<MaCargas, Boolean> existe;
	public static volatile SingularAttribute<MaCargas, String> nombre;
	public static volatile SingularAttribute<MaCargas, String> detalle;
	public static volatile SingularAttribute<MaCargas, String> usuarioCrea;
	public static volatile SingularAttribute<MaCargas, String> terminalCrea;
	public static volatile ListAttribute<MaCargas, MaDetalleCargas> maDetalleCargasList;
	public static volatile SingularAttribute<MaCargas, Integer> fallidos;
	public static volatile SingularAttribute<MaCargas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaCargas, Integer> id;
	public static volatile SingularAttribute<MaCargas, Date> fechaHoraFin;
	public static volatile SingularAttribute<MaCargas, Boolean> respExiste;

	public static final String EXITOSOS = "exitosos";
	public static final String ESTADO = "estado";
	public static final String TIPO = "tipo";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String RESP_NOMBRE = "respNombre";
	public static final String RESP_ARCHIVO = "respArchivo";
	public static final String REGISTROS = "registros";
	public static final String RESP_RUTA = "respRuta";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String EXISTE = "existe";
	public static final String NOMBRE = "nombre";
	public static final String DETALLE = "detalle";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_DETALLE_CARGAS_LIST = "maDetalleCargasList";
	public static final String FALLIDOS = "fallidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String RESP_EXISTE = "respExiste";

}

