package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GnUsuarioFirmas.class)
public abstract class GnUsuarioFirmas_ {

	public static volatile SingularAttribute<GnUsuarioFirmas, String> terminalModifica;
	public static volatile SingularAttribute<GnUsuarioFirmas, String> usuarioCrea;
	public static volatile SingularAttribute<GnUsuarioFirmas, Date> fechaInicio;
	public static volatile SingularAttribute<GnUsuarioFirmas, String> terminalCrea;
	public static volatile SingularAttribute<GnUsuarioFirmas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GnUsuarioFirmas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GnUsuarioFirmas, Integer> id;
	public static volatile SingularAttribute<GnUsuarioFirmas, Date> fechaFin;
	public static volatile SingularAttribute<GnUsuarioFirmas, byte[]> firma;
	public static volatile SingularAttribute<GnUsuarioFirmas, String> usuarioModifica;
	public static volatile SingularAttribute<GnUsuarioFirmas, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<GnUsuarioFirmas, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_FIN = "fechaFin";
	public static final String FIRMA = "firma";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String ACTIVO = "activo";

}

