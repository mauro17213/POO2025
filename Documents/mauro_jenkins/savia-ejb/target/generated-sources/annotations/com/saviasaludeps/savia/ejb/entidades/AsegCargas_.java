package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegCargas.class)
public abstract class AsegCargas_ {

	public static volatile SingularAttribute<AsegCargas, Integer> exitosos;
	public static volatile SingularAttribute<AsegCargas, Integer> estado;
	public static volatile SingularAttribute<AsegCargas, Integer> tipo;
	public static volatile SingularAttribute<AsegCargas, String> archivo;
	public static volatile SingularAttribute<AsegCargas, String> ruta;
	public static volatile SingularAttribute<AsegCargas, String> respNombre;
	public static volatile SingularAttribute<AsegCargas, String> respArchivo;
	public static volatile SingularAttribute<AsegCargas, String> respRuta;
	public static volatile SingularAttribute<AsegCargas, Integer> registros;
	public static volatile SingularAttribute<AsegCargas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<AsegCargas, Boolean> existe;
	public static volatile SingularAttribute<AsegCargas, String> nombre;
	public static volatile SingularAttribute<AsegCargas, String> detalle;
	public static volatile SingularAttribute<AsegCargas, String> usuarioCrea;
	public static volatile ListAttribute<AsegCargas, AsegDetalleCargas> asegDetalleCargasList;
	public static volatile SingularAttribute<AsegCargas, String> terminalCrea;
	public static volatile SingularAttribute<AsegCargas, Integer> fallidos;
	public static volatile SingularAttribute<AsegCargas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegCargas, Integer> id;
	public static volatile SingularAttribute<AsegCargas, Boolean> respExiste;
	public static volatile SingularAttribute<AsegCargas, Date> fechaHoraFin;

	public static final String EXITOSOS = "exitosos";
	public static final String ESTADO = "estado";
	public static final String TIPO = "tipo";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String RESP_NOMBRE = "respNombre";
	public static final String RESP_ARCHIVO = "respArchivo";
	public static final String RESP_RUTA = "respRuta";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String EXISTE = "existe";
	public static final String NOMBRE = "nombre";
	public static final String DETALLE = "detalle";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ASEG_DETALLE_CARGAS_LIST = "asegDetalleCargasList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FALLIDOS = "fallidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String RESP_EXISTE = "respExiste";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";

}

