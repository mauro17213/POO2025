package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnMensajes.class)
public abstract class GnMensajes_ {

	public static volatile SingularAttribute<GnMensajes, byte[]> contenido;
	public static volatile SingularAttribute<GnMensajes, Date> fechaHasta;
	public static volatile SingularAttribute<GnMensajes, Date> fechaDesde;
	public static volatile SingularAttribute<GnMensajes, String> nombre;
	public static volatile SingularAttribute<GnMensajes, Integer> prioridad;
	public static volatile SingularAttribute<GnMensajes, String> terminalModifica;
	public static volatile SingularAttribute<GnMensajes, String> usuarioCrea;
	public static volatile SingularAttribute<GnMensajes, Integer> exposicion;
	public static volatile SingularAttribute<GnMensajes, String> terminalCrea;
	public static volatile SingularAttribute<GnMensajes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnMensajes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GnMensajes, Integer> id;
	public static volatile SingularAttribute<GnMensajes, String> descripcon;
	public static volatile SingularAttribute<GnMensajes, String> usuarioModifica;

	public static final String CONTENIDO = "contenido";
	public static final String FECHA_HASTA = "fechaHasta";
	public static final String FECHA_DESDE = "fechaDesde";
	public static final String NOMBRE = "nombre";
	public static final String PRIORIDAD = "prioridad";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String EXPOSICION = "exposicion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String DESCRIPCON = "descripcon";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

