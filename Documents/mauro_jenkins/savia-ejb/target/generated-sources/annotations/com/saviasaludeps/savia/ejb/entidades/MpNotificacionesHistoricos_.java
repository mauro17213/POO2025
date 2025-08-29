package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpNotificacionesHistoricos.class)
public abstract class MpNotificacionesHistoricos_ {

	public static volatile SingularAttribute<MpNotificacionesHistoricos, Integer> procedencia;
	public static volatile SingularAttribute<MpNotificacionesHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<MpNotificacionesHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<MpNotificacionesHistoricos, MpPrescripcionMedicamentos> mpPrescripcionMedicamentoId;
	public static volatile SingularAttribute<MpNotificacionesHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpNotificacionesHistoricos, MpPrescripcionInsumos> mpPrescripcionInsumoId;
	public static volatile SingularAttribute<MpNotificacionesHistoricos, Integer> id;
	public static volatile SingularAttribute<MpNotificacionesHistoricos, String> mensaje;
	public static volatile SingularAttribute<MpNotificacionesHistoricos, MpPrescripciones> mpPrescripcionId;
	public static volatile SingularAttribute<MpNotificacionesHistoricos, MpPrescripcionTecnologias> mpPrescripcionTecnologiaId;

	public static final String PROCEDENCIA = "procedencia";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MP_PRESCRIPCION_MEDICAMENTO_ID = "mpPrescripcionMedicamentoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MP_PRESCRIPCION_INSUMO_ID = "mpPrescripcionInsumoId";
	public static final String ID = "id";
	public static final String MENSAJE = "mensaje";
	public static final String MP_PRESCRIPCION_ID = "mpPrescripcionId";
	public static final String MP_PRESCRIPCION_TECNOLOGIA_ID = "mpPrescripcionTecnologiaId";

}

