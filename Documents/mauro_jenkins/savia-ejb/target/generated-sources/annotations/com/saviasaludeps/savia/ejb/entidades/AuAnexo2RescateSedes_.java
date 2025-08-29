package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo2RescateSedes.class)
public abstract class AuAnexo2RescateSedes_ {

	public static volatile SingularAttribute<AuAnexo2RescateSedes, Boolean> aplicaRescateAnexo3;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, Boolean> aplicaRescateAnexo2;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, CntPrestadorSedes> cntPrestadorSedeCapitaId;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, String> terminalModifica;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, Boolean> aplicaRescateHosp;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, Boolean> aplicaRescateAnexo3Hosp;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, Integer> id;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, String> inactivoObservacion;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, String> observacion;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, String> usuarioModifica;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, Boolean> activo;
	public static volatile SingularAttribute<AuAnexo2RescateSedes, CntPrestadorSedes> cntPrestadorSedeOrigenId;

	public static final String APLICA_RESCATE_ANEXO3 = "aplicaRescateAnexo3";
	public static final String APLICA_RESCATE_ANEXO2 = "aplicaRescateAnexo2";
	public static final String CNT_PRESTADOR_SEDE_CAPITA_ID = "cntPrestadorSedeCapitaId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String APLICA_RESCATE_HOSP = "aplicaRescateHosp";
	public static final String APLICA_RESCATE_ANEXO3_HOSP = "aplicaRescateAnexo3Hosp";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String INACTIVO_OBSERVACION = "inactivoObservacion";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";
	public static final String CNT_PRESTADOR_SEDE_ORIGEN_ID = "cntPrestadorSedeOrigenId";

}

