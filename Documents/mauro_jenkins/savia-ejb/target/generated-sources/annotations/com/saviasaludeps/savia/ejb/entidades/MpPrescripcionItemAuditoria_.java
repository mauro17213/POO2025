package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpPrescripcionItemAuditoria.class)
public abstract class MpPrescripcionItemAuditoria_ {

	public static volatile SingularAttribute<MpPrescripcionItemAuditoria, Integer> estado;
	public static volatile SingularAttribute<MpPrescripcionItemAuditoria, String> usuarioCrea;
	public static volatile SingularAttribute<MpPrescripcionItemAuditoria, String> terminalCrea;
	public static volatile SingularAttribute<MpPrescripcionItemAuditoria, MpPrescripcionMedicamentos> mpPrescripcionMedicamentoId;
	public static volatile SingularAttribute<MpPrescripcionItemAuditoria, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpPrescripcionItemAuditoria, MpPrescripcionInsumos> mpPrescripcionInsumoId;
	public static volatile SingularAttribute<MpPrescripcionItemAuditoria, Integer> id;
	public static volatile SingularAttribute<MpPrescripcionItemAuditoria, String> notaAuditoria;
	public static volatile SingularAttribute<MpPrescripcionItemAuditoria, MpPrescripciones> mpPrescripcionId;
	public static volatile SingularAttribute<MpPrescripcionItemAuditoria, MpPrescripcionTecnologias> mpPrescripcionTecnologiaId;

	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MP_PRESCRIPCION_MEDICAMENTO_ID = "mpPrescripcionMedicamentoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MP_PRESCRIPCION_INSUMO_ID = "mpPrescripcionInsumoId";
	public static final String ID = "id";
	public static final String NOTA_AUDITORIA = "notaAuditoria";
	public static final String MP_PRESCRIPCION_ID = "mpPrescripcionId";
	public static final String MP_PRESCRIPCION_TECNOLOGIA_ID = "mpPrescripcionTecnologiaId";

}

