package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo2RescateGestiones.class)
public abstract class AuAnexo2RescateGestiones_ {

	public static volatile SingularAttribute<AuAnexo2RescateGestiones, Short> tipo;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, Date> fechaHoraGestion;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, String> maeMotivoRescateValor;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, Date> fechaHoraDireccionamiento;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, String> maeMotivoRescateCodigo;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, Integer> maeMotivoRescateId;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, AuAnexo2Rescates> auAnexo2RescatesId;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, Integer> id;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, String> maeMotivoRescateTipo;
	public static volatile SingularAttribute<AuAnexo2RescateGestiones, String> observacion;

	public static final String TIPO = "tipo";
	public static final String FECHA_HORA_GESTION = "fechaHoraGestion";
	public static final String MAE_MOTIVO_RESCATE_VALOR = "maeMotivoRescateValor";
	public static final String FECHA_HORA_DIRECCIONAMIENTO = "fechaHoraDireccionamiento";
	public static final String MAE_MOTIVO_RESCATE_CODIGO = "maeMotivoRescateCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_MOTIVO_RESCATE_ID = "maeMotivoRescateId";
	public static final String AU_ANEXO2_RESCATES_ID = "auAnexo2RescatesId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_MOTIVO_RESCATE_TIPO = "maeMotivoRescateTipo";
	public static final String OBSERVACION = "observacion";

}

