package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjPlantillaCampos.class)
public abstract class CntjPlantillaCampos_ {

	public static volatile SingularAttribute<CntjPlantillaCampos, CntjPlantillas> cntjPlantillasId;
	public static volatile SingularAttribute<CntjPlantillaCampos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjPlantillaCampos, CntjCampos> cntjCampoId;
	public static volatile SingularAttribute<CntjPlantillaCampos, String> terminalCrea;
	public static volatile SingularAttribute<CntjPlantillaCampos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjPlantillaCampos, Integer> id;

	public static final String CNTJ_PLANTILLAS_ID = "cntjPlantillasId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNTJ_CAMPO_ID = "cntjCampoId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

