package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjExpedienteResponsables.class)
public abstract class CntjExpedienteResponsables_ {

	public static volatile SingularAttribute<CntjExpedienteResponsables, String> terminalModifica;
	public static volatile SingularAttribute<CntjExpedienteResponsables, Date> fechaInicial;
	public static volatile SingularAttribute<CntjExpedienteResponsables, String> usuarioCrea;
	public static volatile SingularAttribute<CntjExpedienteResponsables, String> terminalCrea;
	public static volatile SingularAttribute<CntjExpedienteResponsables, CntjExpedientes> cntjExpedientesId;
	public static volatile SingularAttribute<CntjExpedienteResponsables, Date> fechaFinal;
	public static volatile SingularAttribute<CntjExpedienteResponsables, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjExpedienteResponsables, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjExpedienteResponsables, Integer> id;
	public static volatile SingularAttribute<CntjExpedienteResponsables, Integer> rol;
	public static volatile SingularAttribute<CntjExpedienteResponsables, String> usuarioModifica;
	public static volatile SingularAttribute<CntjExpedienteResponsables, GnUsuarios> gnUsuariosId;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String FECHA_INICIAL = "fechaInicial";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_EXPEDIENTES_ID = "cntjExpedientesId";
	public static final String FECHA_FINAL = "fechaFinal";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ROL = "rol";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";

}

