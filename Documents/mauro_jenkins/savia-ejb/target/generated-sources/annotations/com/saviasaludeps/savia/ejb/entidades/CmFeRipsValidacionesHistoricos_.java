package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFeRipsValidacionesHistoricos.class)
public abstract class CmFeRipsValidacionesHistoricos_ {

	public static volatile SingularAttribute<CmFeRipsValidacionesHistoricos, Boolean> estado;
	public static volatile SingularAttribute<CmFeRipsValidacionesHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<CmFeRipsValidacionesHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<CmFeRipsValidacionesHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmFeRipsValidacionesHistoricos, Integer> id;
	public static volatile SingularAttribute<CmFeRipsValidacionesHistoricos, CmFeRipsValidaciones> cmFeRipsValidacionesId;

	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CM_FE_RIPS_VALIDACIONES_ID = "cmFeRipsValidacionesId";

}

