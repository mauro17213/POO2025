package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjContratoSeguimientos.class)
public abstract class CntjContratoSeguimientos_ {

	public static volatile SingularAttribute<CntjContratoSeguimientos, Integer> periodicidad;
	public static volatile SingularAttribute<CntjContratoSeguimientos, CntjContratos> cntjContratosId;
	public static volatile SingularAttribute<CntjContratoSeguimientos, Date> fechaFinSeguimiento;
	public static volatile SingularAttribute<CntjContratoSeguimientos, Integer> estadoSeguimiento;
	public static volatile SingularAttribute<CntjContratoSeguimientos, String> terminalModifica;
	public static volatile SingularAttribute<CntjContratoSeguimientos, CntjTerceros> cntjTercerosId;
	public static volatile SingularAttribute<CntjContratoSeguimientos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjContratoSeguimientos, Date> fechaInicioSeguimiento;
	public static volatile SingularAttribute<CntjContratoSeguimientos, String> terminalCrea;
	public static volatile SingularAttribute<CntjContratoSeguimientos, String> observaciones;
	public static volatile SingularAttribute<CntjContratoSeguimientos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjContratoSeguimientos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjContratoSeguimientos, Integer> id;
	public static volatile SingularAttribute<CntjContratoSeguimientos, String> usuarioModifica;

	public static final String PERIODICIDAD = "periodicidad";
	public static final String CNTJ_CONTRATOS_ID = "cntjContratosId";
	public static final String FECHA_FIN_SEGUIMIENTO = "fechaFinSeguimiento";
	public static final String ESTADO_SEGUIMIENTO = "estadoSeguimiento";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNTJ_TERCEROS_ID = "cntjTercerosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_INICIO_SEGUIMIENTO = "fechaInicioSeguimiento";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String OBSERVACIONES = "observaciones";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

