package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeCierreCargas.class)
public abstract class PeCierreCargas_ {

	public static volatile SingularAttribute<PeCierreCargas, String> terminalModifica;
	public static volatile SingularAttribute<PeCierreCargas, String> motivo;
	public static volatile SingularAttribute<PeCierreCargas, String> usuarioCrea;
	public static volatile SingularAttribute<PeCierreCargas, Integer> periodo;
	public static volatile SingularAttribute<PeCierreCargas, Date> fechaHoraHasta;
	public static volatile SingularAttribute<PeCierreCargas, String> terminalCrea;
	public static volatile SingularAttribute<PeCierreCargas, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<PeCierreCargas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeCierreCargas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeCierreCargas, Integer> id;
	public static volatile SingularAttribute<PeCierreCargas, Date> fechaHoraDesde;
	public static volatile SingularAttribute<PeCierreCargas, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String MOTIVO = "motivo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String PERIODO = "periodo";
	public static final String FECHA_HORA_HASTA = "fechaHoraHasta";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_HORA_DESDE = "fechaHoraDesde";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

