package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RefAnexo9Estados.class)
public abstract class RefAnexo9Estados_ {

	public static volatile SingularAttribute<RefAnexo9Estados, Integer> estado;
	public static volatile SingularAttribute<RefAnexo9Estados, String> maeMotivoCodigo;
	public static volatile SingularAttribute<RefAnexo9Estados, String> maeEstadoValor;
	public static volatile SingularAttribute<RefAnexo9Estados, Integer> maeMotivoId;
	public static volatile SingularAttribute<RefAnexo9Estados, String> usuarioCrea;
	public static volatile SingularAttribute<RefAnexo9Estados, String> maeEstadoCodigo;
	public static volatile SingularAttribute<RefAnexo9Estados, String> terminalCrea;
	public static volatile SingularAttribute<RefAnexo9Estados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RefAnexo9Estados, Integer> id;
	public static volatile SingularAttribute<RefAnexo9Estados, Integer> maeEstadoId;
	public static volatile SingularAttribute<RefAnexo9Estados, RefAnexos9> refAnexos9Id;
	public static volatile SingularAttribute<RefAnexo9Estados, String> observacion;
	public static volatile SingularAttribute<RefAnexo9Estados, String> maeMotivoValor;

	public static final String ESTADO = "estado";
	public static final String MAE_MOTIVO_CODIGO = "maeMotivoCodigo";
	public static final String MAE_ESTADO_VALOR = "maeEstadoValor";
	public static final String MAE_MOTIVO_ID = "maeMotivoId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_ESTADO_CODIGO = "maeEstadoCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_ESTADO_ID = "maeEstadoId";
	public static final String REF_ANEXOS9_ID = "refAnexos9Id";
	public static final String OBSERVACION = "observacion";
	public static final String MAE_MOTIVO_VALOR = "maeMotivoValor";

}

