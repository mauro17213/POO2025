package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFeRipsCargaContenidos.class)
public abstract class CmFeRipsCargaContenidos_ {

	public static volatile SingularAttribute<CmFeRipsCargaContenidos, Integer> tipo;
	public static volatile SingularAttribute<CmFeRipsCargaContenidos, String> usuarioCrea;
	public static volatile SingularAttribute<CmFeRipsCargaContenidos, String> cuv;
	public static volatile SingularAttribute<CmFeRipsCargaContenidos, String> terminalCrea;
	public static volatile SingularAttribute<CmFeRipsCargaContenidos, String> xml;
	public static volatile SingularAttribute<CmFeRipsCargaContenidos, String> json;
	public static volatile SingularAttribute<CmFeRipsCargaContenidos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmFeRipsCargaContenidos, Integer> id;
	public static volatile SingularAttribute<CmFeRipsCargaContenidos, String> cuvJson;
	public static volatile SingularAttribute<CmFeRipsCargaContenidos, CmFeRipsCargas> cmFeRipsCargasId;

	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CUV = "cuv";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String XML = "xml";
	public static final String JSON = "json";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CUV_JSON = "cuvJson";
	public static final String CM_FE_RIPS_CARGAS_ID = "cmFeRipsCargasId";

}

