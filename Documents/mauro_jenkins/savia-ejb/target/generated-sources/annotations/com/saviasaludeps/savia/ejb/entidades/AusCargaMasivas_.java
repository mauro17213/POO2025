package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusCargaMasivas.class)
public abstract class AusCargaMasivas_ {

	public static volatile SingularAttribute<AusCargaMasivas, Integer> exitosos;
	public static volatile SingularAttribute<AusCargaMasivas, Integer> estado;
	public static volatile SingularAttribute<AusCargaMasivas, Integer> tipo;
	public static volatile SingularAttribute<AusCargaMasivas, String> archivo;
	public static volatile SingularAttribute<AusCargaMasivas, String> ruta;
	public static volatile ListAttribute<AusCargaMasivas, AusCasos> ausCasosList;
	public static volatile SingularAttribute<AusCargaMasivas, String> respNombre;
	public static volatile SingularAttribute<AusCargaMasivas, String> respArchivo;
	public static volatile SingularAttribute<AusCargaMasivas, String> respRuta;
	public static volatile SingularAttribute<AusCargaMasivas, Date> fechaHoraInicio;
	public static volatile SingularAttribute<AusCargaMasivas, Boolean> existe;
	public static volatile SingularAttribute<AusCargaMasivas, Integer> cantidadRegistros;
	public static volatile SingularAttribute<AusCargaMasivas, String> nombre;
	public static volatile SingularAttribute<AusCargaMasivas, String> usuarioCrea;
	public static volatile SingularAttribute<AusCargaMasivas, String> terminalCrea;
	public static volatile SingularAttribute<AusCargaMasivas, GnEmpresas> gnEmpresaId;
	public static volatile ListAttribute<AusCargaMasivas, AusCargaErrores> ausCargaErroresList;
	public static volatile SingularAttribute<AusCargaMasivas, Integer> fallidos;
	public static volatile SingularAttribute<AusCargaMasivas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusCargaMasivas, Integer> id;
	public static volatile SingularAttribute<AusCargaMasivas, Boolean> respExiste;
	public static volatile SingularAttribute<AusCargaMasivas, Date> fechaHoraFin;
	public static volatile SingularAttribute<AusCargaMasivas, String> observacion;

	public static final String EXITOSOS = "exitosos";
	public static final String ESTADO = "estado";
	public static final String TIPO = "tipo";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String AUS_CASOS_LIST = "ausCasosList";
	public static final String RESP_NOMBRE = "respNombre";
	public static final String RESP_ARCHIVO = "respArchivo";
	public static final String RESP_RUTA = "respRuta";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String EXISTE = "existe";
	public static final String CANTIDAD_REGISTROS = "cantidadRegistros";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GN_EMPRESA_ID = "gnEmpresaId";
	public static final String AUS_CARGA_ERRORES_LIST = "ausCargaErroresList";
	public static final String FALLIDOS = "fallidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String RESP_EXISTE = "respExiste";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String OBSERVACION = "observacion";

}

