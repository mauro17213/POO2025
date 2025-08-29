package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeUsuariosProgramas.class)
public abstract class PeUsuariosProgramas_ {

	public static volatile SingularAttribute<PeUsuariosProgramas, Integer> tipo;
	public static volatile SingularAttribute<PeUsuariosProgramas, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<PeUsuariosProgramas, String> maeTipoCodigo;
	public static volatile SingularAttribute<PeUsuariosProgramas, Date> fechaFin;
	public static volatile SingularAttribute<PeUsuariosProgramas, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<PeUsuariosProgramas, String> terminalModifica;
	public static volatile SingularAttribute<PeUsuariosProgramas, String> usuarioCrea;
	public static volatile SingularAttribute<PeUsuariosProgramas, Date> fechaInicio;
	public static volatile SingularAttribute<PeUsuariosProgramas, String> terminalCrea;
	public static volatile SingularAttribute<PeUsuariosProgramas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeUsuariosProgramas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeUsuariosProgramas, Integer> id;
	public static volatile SingularAttribute<PeUsuariosProgramas, Integer> maeTipoId;
	public static volatile SingularAttribute<PeUsuariosProgramas, String> maeTipoValor;
	public static volatile SingularAttribute<PeUsuariosProgramas, String> usuarioModifica;
	public static volatile SingularAttribute<PeUsuariosProgramas, Boolean> activo;

	public static final String TIPO = "tipo";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String MAE_TIPO_CODIGO = "maeTipoCodigo";
	public static final String FECHA_FIN = "fechaFin";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_ID = "maeTipoId";
	public static final String MAE_TIPO_VALOR = "maeTipoValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

