package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GjProcesoAbogados.class)
public abstract class GjProcesoAbogados_ {

	public static volatile SingularAttribute<GjProcesoAbogados, String> terminalModifica;
	public static volatile SingularAttribute<GjProcesoAbogados, String> usuarioCrea;
	public static volatile SingularAttribute<GjProcesoAbogados, GjProcesos> gjProcesosId;
	public static volatile SingularAttribute<GjProcesoAbogados, Date> fechaInicio;
	public static volatile SingularAttribute<GjProcesoAbogados, String> terminalCrea;
	public static volatile SingularAttribute<GjProcesoAbogados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GjProcesoAbogados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GjProcesoAbogados, Integer> id;
	public static volatile SingularAttribute<GjProcesoAbogados, Date> fechaFin;
	public static volatile SingularAttribute<GjProcesoAbogados, GjAbogados> gjAbogadosId;
	public static volatile SingularAttribute<GjProcesoAbogados, String> usuarioModifica;
	public static volatile SingularAttribute<GjProcesoAbogados, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String GJ_PROCESOS_ID = "gjProcesosId";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_FIN = "fechaFin";
	public static final String GJ_ABOGADOS_ID = "gjAbogadosId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

