package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusSeguimientos.class)
public abstract class AusSeguimientos_ {

	public static volatile ListAttribute<AusSeguimientos, AusAdjuntos> ausAdjuntosList;
	public static volatile SingularAttribute<AusSeguimientos, AusCasos> ausCasosId;
	public static volatile SingularAttribute<AusSeguimientos, String> maeEstadoValor;
	public static volatile SingularAttribute<AusSeguimientos, String> terminalModifica;
	public static volatile SingularAttribute<AusSeguimientos, String> usuarioCrea;
	public static volatile SingularAttribute<AusSeguimientos, String> maeEstadoCodigo;
	public static volatile SingularAttribute<AusSeguimientos, String> terminalCrea;
	public static volatile SingularAttribute<AusSeguimientos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusSeguimientos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AusSeguimientos, Integer> id;
	public static volatile SingularAttribute<AusSeguimientos, Integer> maeEstadoId;
	public static volatile SingularAttribute<AusSeguimientos, String> observacion;
	public static volatile SingularAttribute<AusSeguimientos, String> usuarioModifica;

	public static final String AUS_ADJUNTOS_LIST = "ausAdjuntosList";
	public static final String AUS_CASOS_ID = "ausCasosId";
	public static final String MAE_ESTADO_VALOR = "maeEstadoValor";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_ESTADO_CODIGO = "maeEstadoCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_ESTADO_ID = "maeEstadoId";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

