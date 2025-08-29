package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaTecnologiaGrupos.class)
public abstract class MaTecnologiaGrupos_ {

	public static volatile SingularAttribute<MaTecnologiaGrupos, MaTecnologias> maTecnologiasId;
	public static volatile SingularAttribute<MaTecnologiaGrupos, String> maeGrupoTecnologiaCodigo;
	public static volatile SingularAttribute<MaTecnologiaGrupos, String> usuarioCrea;
	public static volatile SingularAttribute<MaTecnologiaGrupos, String> terminalCrea;
	public static volatile SingularAttribute<MaTecnologiaGrupos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaTecnologiaGrupos, String> maeGrupoTecnologiaValor;
	public static volatile SingularAttribute<MaTecnologiaGrupos, Integer> id;
	public static volatile SingularAttribute<MaTecnologiaGrupos, Integer> maeGrupoTecnologiaId;

	public static final String MA_TECNOLOGIAS_ID = "maTecnologiasId";
	public static final String MAE_GRUPO_TECNOLOGIA_CODIGO = "maeGrupoTecnologiaCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MAE_GRUPO_TECNOLOGIA_VALOR = "maeGrupoTecnologiaValor";
	public static final String ID = "id";
	public static final String MAE_GRUPO_TECNOLOGIA_ID = "maeGrupoTecnologiaId";

}

