package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjContratoInformes.class)
public abstract class CntjContratoInformes_ {

	public static volatile SingularAttribute<CntjContratoInformes, CntjContratoSupervisores> cntjContratoSupervisoresId;
	public static volatile SingularAttribute<CntjContratoInformes, Integer> estado;
	public static volatile SingularAttribute<CntjContratoInformes, Integer> tipoInforme;
	public static volatile ListAttribute<CntjContratoInformes, CntjContratoInformeAdjuntos> cntjContratoInformeAdjuntosList;
	public static volatile SingularAttribute<CntjContratoInformes, CntjContratos> cntjContratosId;
	public static volatile SingularAttribute<CntjContratoInformes, String> terminalModifica;
	public static volatile SingularAttribute<CntjContratoInformes, String> usuarioCrea;
	public static volatile SingularAttribute<CntjContratoInformes, String> terminalCrea;
	public static volatile SingularAttribute<CntjContratoInformes, Date> fechaFinPeriodo;
	public static volatile SingularAttribute<CntjContratoInformes, Date> fechaAprobacion;
	public static volatile SingularAttribute<CntjContratoInformes, String> observaciones;
	public static volatile SingularAttribute<CntjContratoInformes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjContratoInformes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjContratoInformes, Integer> id;
	public static volatile SingularAttribute<CntjContratoInformes, Date> fechaInicioPeriodo;
	public static volatile SingularAttribute<CntjContratoInformes, String> usuarioModifica;

	public static final String CNTJ_CONTRATO_SUPERVISORES_ID = "cntjContratoSupervisoresId";
	public static final String ESTADO = "estado";
	public static final String TIPO_INFORME = "tipoInforme";
	public static final String CNTJ_CONTRATO_INFORME_ADJUNTOS_LIST = "cntjContratoInformeAdjuntosList";
	public static final String CNTJ_CONTRATOS_ID = "cntjContratosId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_FIN_PERIODO = "fechaFinPeriodo";
	public static final String FECHA_APROBACION = "fechaAprobacion";
	public static final String OBSERVACIONES = "observaciones";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_INICIO_PERIODO = "fechaInicioPeriodo";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

