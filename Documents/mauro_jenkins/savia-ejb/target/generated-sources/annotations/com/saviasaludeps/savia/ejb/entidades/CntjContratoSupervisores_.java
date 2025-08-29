package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjContratoSupervisores.class)
public abstract class CntjContratoSupervisores_ {

	public static volatile SingularAttribute<CntjContratoSupervisores, CntjContratos> cntjContratosId;
	public static volatile ListAttribute<CntjContratoSupervisores, CntjContratoInformes> cntjContratoInformesList;
	public static volatile SingularAttribute<CntjContratoSupervisores, Date> fechaFin;
	public static volatile SingularAttribute<CntjContratoSupervisores, Integer> estadoSupervision;
	public static volatile SingularAttribute<CntjContratoSupervisores, String> terminalModifica;
	public static volatile SingularAttribute<CntjContratoSupervisores, CntjTerceros> cntjTercerosId;
	public static volatile SingularAttribute<CntjContratoSupervisores, String> usuarioCrea;
	public static volatile SingularAttribute<CntjContratoSupervisores, Date> fechaInicio;
	public static volatile SingularAttribute<CntjContratoSupervisores, String> terminalCrea;
	public static volatile SingularAttribute<CntjContratoSupervisores, Integer> etapaDesignacion;
	public static volatile SingularAttribute<CntjContratoSupervisores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjContratoSupervisores, Integer> informesPendientes;
	public static volatile SingularAttribute<CntjContratoSupervisores, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjContratoSupervisores, Integer> id;
	public static volatile SingularAttribute<CntjContratoSupervisores, String> usuarioModifica;

	public static final String CNTJ_CONTRATOS_ID = "cntjContratosId";
	public static final String CNTJ_CONTRATO_INFORMES_LIST = "cntjContratoInformesList";
	public static final String FECHA_FIN = "fechaFin";
	public static final String ESTADO_SUPERVISION = "estadoSupervision";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNTJ_TERCEROS_ID = "cntjTercerosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ETAPA_DESIGNACION = "etapaDesignacion";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String INFORMES_PENDIENTES = "informesPendientes";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

