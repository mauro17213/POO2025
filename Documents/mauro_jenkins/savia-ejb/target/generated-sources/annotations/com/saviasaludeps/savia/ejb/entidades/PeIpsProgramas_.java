package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeIpsProgramas.class)
public abstract class PeIpsProgramas_ {

	public static volatile SingularAttribute<PeIpsProgramas, String> terminalModifica;
	public static volatile SingularAttribute<PeIpsProgramas, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<PeIpsProgramas, Date> fechaInicio;
	public static volatile SingularAttribute<PeIpsProgramas, String> terminalCrea;
	public static volatile SingularAttribute<PeIpsProgramas, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<PeIpsProgramas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeIpsProgramas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeIpsProgramas, Integer> id;
	public static volatile SingularAttribute<PeIpsProgramas, Date> fechaFin;
	public static volatile SingularAttribute<PeIpsProgramas, String> usuariosCrea;
	public static volatile SingularAttribute<PeIpsProgramas, String> usuarioModifica;
	public static volatile SingularAttribute<PeIpsProgramas, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_FIN = "fechaFin";
	public static final String USUARIOS_CREA = "usuariosCrea";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

