package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaRelaciones.class)
public abstract class MaRelaciones_ {

	public static volatile SingularAttribute<MaRelaciones, String> gnCodigo;
	public static volatile SingularAttribute<MaRelaciones, String> maValor;
	public static volatile SingularAttribute<MaRelaciones, String> gnMaestroTipo;
	public static volatile SingularAttribute<MaRelaciones, String> maCodigo;
	public static volatile SingularAttribute<MaRelaciones, String> terminalModifica;
	public static volatile SingularAttribute<MaRelaciones, Integer> gnId;
	public static volatile SingularAttribute<MaRelaciones, String> usuarioCrea;
	public static volatile SingularAttribute<MaRelaciones, Integer> tipoTecnologia;
	public static volatile SingularAttribute<MaRelaciones, String> terminalCrea;
	public static volatile SingularAttribute<MaRelaciones, MaRelacionTipos> maRelacionTiposId;
	public static volatile SingularAttribute<MaRelaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaRelaciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaRelaciones, Integer> id;
	public static volatile SingularAttribute<MaRelaciones, Integer> maId;
	public static volatile SingularAttribute<MaRelaciones, String> gnValor;
	public static volatile SingularAttribute<MaRelaciones, String> usuarioModifica;
	public static volatile SingularAttribute<MaRelaciones, Boolean> activo;

	public static final String GN_CODIGO = "gnCodigo";
	public static final String MA_VALOR = "maValor";
	public static final String GN_MAESTRO_TIPO = "gnMaestroTipo";
	public static final String MA_CODIGO = "maCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String GN_ID = "gnId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_RELACION_TIPOS_ID = "maRelacionTiposId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MA_ID = "maId";
	public static final String GN_VALOR = "gnValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

