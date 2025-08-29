package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjComiteAsistentes.class)
public abstract class CntjComiteAsistentes_ {

	public static volatile SingularAttribute<CntjComiteAsistentes, Date> fechaHoraBorra;
	public static volatile SingularAttribute<CntjComiteAsistentes, String> usuarioBorra;
	public static volatile SingularAttribute<CntjComiteAsistentes, CntjComites> cntjComitesId;
	public static volatile SingularAttribute<CntjComiteAsistentes, Boolean> aprueba;
	public static volatile SingularAttribute<CntjComiteAsistentes, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<CntjComiteAsistentes, String> terminalModifica;
	public static volatile SingularAttribute<CntjComiteAsistentes, String> usuarioCrea;
	public static volatile SingularAttribute<CntjComiteAsistentes, String> terminalCrea;
	public static volatile SingularAttribute<CntjComiteAsistentes, Boolean> borrado;
	public static volatile SingularAttribute<CntjComiteAsistentes, String> observaciones;
	public static volatile SingularAttribute<CntjComiteAsistentes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjComiteAsistentes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjComiteAsistentes, String> terminalBorra;
	public static volatile SingularAttribute<CntjComiteAsistentes, Integer> id;
	public static volatile SingularAttribute<CntjComiteAsistentes, String> usuarioModifica;

	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String CNTJ_COMITES_ID = "cntjComitesId";
	public static final String APRUEBA = "aprueba";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String OBSERVACIONES = "observaciones";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

