package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsCargaEstados.class)
public abstract class CmRipsCargaEstados_ {

	public static volatile SingularAttribute<CmRipsCargaEstados, Integer> estado;
	public static volatile SingularAttribute<CmRipsCargaEstados, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsCargaEstados, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsCargaEstados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsCargaEstados, Integer> id;
	public static volatile SingularAttribute<CmRipsCargaEstados, CmRipsCargas> cmRipsCargasId;

	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";

}

