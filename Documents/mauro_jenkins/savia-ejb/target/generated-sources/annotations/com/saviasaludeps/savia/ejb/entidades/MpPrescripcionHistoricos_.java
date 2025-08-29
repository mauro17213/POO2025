package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpPrescripcionHistoricos.class)
public abstract class MpPrescripcionHistoricos_ {

	public static volatile SingularAttribute<MpPrescripcionHistoricos, Integer> estado;
	public static volatile SingularAttribute<MpPrescripcionHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<MpPrescripcionHistoricos, Integer> tipoTecnologia;
	public static volatile SingularAttribute<MpPrescripcionHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<MpPrescripcionHistoricos, Integer> idPrescripcionTecnologia;
	public static volatile SingularAttribute<MpPrescripcionHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpPrescripcionHistoricos, Integer> id;
	public static volatile SingularAttribute<MpPrescripcionHistoricos, MpPrescripciones> mpPrescripcionesId;

	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ID_PRESCRIPCION_TECNOLOGIA = "idPrescripcionTecnologia";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MP_PRESCRIPCIONES_ID = "mpPrescripcionesId";

}

