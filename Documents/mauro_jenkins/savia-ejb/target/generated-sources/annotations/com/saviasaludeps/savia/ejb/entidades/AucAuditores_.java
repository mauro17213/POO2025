package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucAuditores.class)
public abstract class AucAuditores_ {

	public static volatile SingularAttribute<AucAuditores, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<AucAuditores, String> usuarioCrea;
	public static volatile SingularAttribute<AucAuditores, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<AucAuditores, String> terminalCrea;
	public static volatile SingularAttribute<AucAuditores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucAuditores, Integer> id;
	public static volatile ListAttribute<AucAuditores, AucAuditorHistoricos> aucAuditorHistoricosList;
	public static volatile SingularAttribute<AucAuditores, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<AucAuditores, Boolean> activo;

	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String AUC_AUDITOR_HISTORICOS_LIST = "aucAuditorHistoricosList";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String ACTIVO = "activo";

}

