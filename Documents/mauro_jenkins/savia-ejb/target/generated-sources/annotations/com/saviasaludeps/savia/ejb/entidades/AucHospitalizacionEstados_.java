package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucHospitalizacionEstados.class)
public abstract class AucHospitalizacionEstados_ {

	public static volatile SingularAttribute<AucHospitalizacionEstados, Short> fuenteOrigen;
	public static volatile SingularAttribute<AucHospitalizacionEstados, Short> estado;
	public static volatile SingularAttribute<AucHospitalizacionEstados, String> usuarioCrea;
	public static volatile SingularAttribute<AucHospitalizacionEstados, String> terminalCrea;
	public static volatile SingularAttribute<AucHospitalizacionEstados, AucHospitalizaciones> aucHospitalizacionesId;
	public static volatile SingularAttribute<AucHospitalizacionEstados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucHospitalizacionEstados, Integer> id;
	public static volatile SingularAttribute<AucHospitalizacionEstados, Short> estadoAuditoria;
	public static volatile SingularAttribute<AucHospitalizacionEstados, String> observacion;

	public static final String FUENTE_ORIGEN = "fuenteOrigen";
	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AUC_HOSPITALIZACIONES_ID = "aucHospitalizacionesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ESTADO_AUDITORIA = "estadoAuditoria";
	public static final String OBSERVACION = "observacion";

}

