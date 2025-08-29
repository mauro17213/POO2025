package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFeTransacciones.class)
public abstract class CmFeTransacciones_ {

	public static volatile SingularAttribute<CmFeTransacciones, Integer> estado;
	public static volatile SingularAttribute<CmFeTransacciones, String> usuarioCrea;
	public static volatile SingularAttribute<CmFeTransacciones, String> terminalCrea;
	public static volatile SingularAttribute<CmFeTransacciones, Date> fechaHoraRespuesta;
	public static volatile SingularAttribute<CmFeTransacciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmFeTransacciones, Integer> id;
	public static volatile SingularAttribute<CmFeTransacciones, byte[]> jsonRespuesta;
	public static volatile SingularAttribute<CmFeTransacciones, byte[]> jsonEnvio;
	public static volatile SingularAttribute<CmFeTransacciones, Date> fechaHoraEnvio;
	public static volatile SingularAttribute<CmFeTransacciones, CmFeRipsCargas> cmFeRipsCargasId;

	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_RESPUESTA = "fechaHoraRespuesta";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String JSON_RESPUESTA = "jsonRespuesta";
	public static final String JSON_ENVIO = "jsonEnvio";
	public static final String FECHA_HORA_ENVIO = "fechaHoraEnvio";
	public static final String CM_FE_RIPS_CARGAS_ID = "cmFeRipsCargasId";

}

