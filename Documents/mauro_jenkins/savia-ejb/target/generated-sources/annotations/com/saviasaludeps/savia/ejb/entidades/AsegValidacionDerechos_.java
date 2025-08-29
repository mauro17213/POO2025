package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegValidacionDerechos.class)
public abstract class AsegValidacionDerechos_ {

	public static volatile SingularAttribute<AsegValidacionDerechos, Integer> estado;
	public static volatile SingularAttribute<AsegValidacionDerechos, String> archivo;
	public static volatile SingularAttribute<AsegValidacionDerechos, Integer> registrosEncontrados;
	public static volatile SingularAttribute<AsegValidacionDerechos, String> ruta;
	public static volatile SingularAttribute<AsegValidacionDerechos, Date> fechaHoraInicio;
	public static volatile SingularAttribute<AsegValidacionDerechos, String> terminalModifica;
	public static volatile SingularAttribute<AsegValidacionDerechos, String> usuarioCrea;
	public static volatile SingularAttribute<AsegValidacionDerechos, String> terminalCrea;
	public static volatile SingularAttribute<AsegValidacionDerechos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegValidacionDerechos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AsegValidacionDerechos, Integer> id;
	public static volatile SingularAttribute<AsegValidacionDerechos, Date> fechaHoraFin;
	public static volatile SingularAttribute<AsegValidacionDerechos, String> usuarioModifica;
	public static volatile SingularAttribute<AsegValidacionDerechos, Integer> registrosCargados;

	public static final String ESTADO = "estado";
	public static final String ARCHIVO = "archivo";
	public static final String REGISTROS_ENCONTRADOS = "registrosEncontrados";
	public static final String RUTA = "ruta";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String REGISTROS_CARGADOS = "registrosCargados";

}

