package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjEstadoPlantillas.class)
public abstract class CntjEstadoPlantillas_ {

	public static volatile SingularAttribute<CntjEstadoPlantillas, CntjPlantillas> cntjPlantillasId;
	public static volatile SingularAttribute<CntjEstadoPlantillas, String> usuarioCrea;
	public static volatile SingularAttribute<CntjEstadoPlantillas, String> terminalCrea;
	public static volatile SingularAttribute<CntjEstadoPlantillas, Boolean> editable;
	public static volatile SingularAttribute<CntjEstadoPlantillas, CntjEstados> cntjEstadosId;
	public static volatile SingularAttribute<CntjEstadoPlantillas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjEstadoPlantillas, Integer> id;

	public static final String CNTJ_PLANTILLAS_ID = "cntjPlantillasId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String EDITABLE = "editable";
	public static final String CNTJ_ESTADOS_ID = "cntjEstadosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

