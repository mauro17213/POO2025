package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmAuditoriaCapitaDescuentos.class)
public abstract class CmAuditoriaCapitaDescuentos_ {

	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, Boolean> marcacion;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, String> terminalModifica;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, String> usuarioCrea;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, String> terminalCrea;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, CmDetalles> cmDetallesId;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, String> contrato;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, Integer> id;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, String> observacion;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, String> usuarioModifica;
	public static volatile SingularAttribute<CmAuditoriaCapitaDescuentos, CntContratos> cntContratosId;

	public static final String MARCACION = "marcacion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_DETALLES_ID = "cmDetallesId";
	public static final String CONTRATO = "contrato";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

