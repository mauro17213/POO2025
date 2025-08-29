package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InfInformeDescargados.class)
public abstract class InfInformeDescargados_ {

	public static volatile SingularAttribute<InfInformeDescargados, String> usuarioCrea;
	public static volatile SingularAttribute<InfInformeDescargados, Integer> infUsuarioId;
	public static volatile SingularAttribute<InfInformeDescargados, String> terminalCrea;
	public static volatile SingularAttribute<InfInformeDescargados, String> empresaNombre;
	public static volatile SingularAttribute<InfInformeDescargados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<InfInformeDescargados, InfInformeGenerados> infInformeGeneradosId;
	public static volatile SingularAttribute<InfInformeDescargados, Date> fechaDescarga;
	public static volatile SingularAttribute<InfInformeDescargados, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String INF_USUARIO_ID = "infUsuarioId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String EMPRESA_NOMBRE = "empresaNombre";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String INF_INFORME_GENERADOS_ID = "infInformeGeneradosId";
	public static final String FECHA_DESCARGA = "fechaDescarga";
	public static final String ID = "id";

}

