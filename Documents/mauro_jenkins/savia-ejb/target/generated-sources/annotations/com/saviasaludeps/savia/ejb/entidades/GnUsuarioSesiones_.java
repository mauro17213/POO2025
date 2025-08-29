package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnUsuarioSesiones.class)
public abstract class GnUsuarioSesiones_ {

	public static volatile SingularAttribute<GnUsuarioSesiones, String> idSesion;
	public static volatile SingularAttribute<GnUsuarioSesiones, String> ipServidor;
	public static volatile SingularAttribute<GnUsuarioSesiones, Date> fechaHoraUltimaGestion;
	public static volatile SingularAttribute<GnUsuarioSesiones, Date> fechaHoraInicio;
	public static volatile SingularAttribute<GnUsuarioSesiones, Integer> id;
	public static volatile SingularAttribute<GnUsuarioSesiones, Boolean> activa;
	public static volatile SingularAttribute<GnUsuarioSesiones, Date> fechaHoraFin;
	public static volatile SingularAttribute<GnUsuarioSesiones, GnUsuarios> gnUsuariosId;

	public static final String ID_SESION = "idSesion";
	public static final String IP_SERVIDOR = "ipServidor";
	public static final String FECHA_HORA_ULTIMA_GESTION = "fechaHoraUltimaGestion";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ID = "id";
	public static final String ACTIVA = "activa";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";

}

