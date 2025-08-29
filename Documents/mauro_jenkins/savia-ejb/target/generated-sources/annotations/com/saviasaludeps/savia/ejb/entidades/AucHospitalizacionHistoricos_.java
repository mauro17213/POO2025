package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucHospitalizacionHistoricos.class)
public abstract class AucHospitalizacionHistoricos_ {

	public static volatile SingularAttribute<AucHospitalizacionHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<AucHospitalizacionHistoricos, String> tostringHospitalizacion;
	public static volatile SingularAttribute<AucHospitalizacionHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<AucHospitalizacionHistoricos, AucHospitalizaciones> aucHospitalizacionesId;
	public static volatile SingularAttribute<AucHospitalizacionHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucHospitalizacionHistoricos, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TOSTRING_HOSPITALIZACION = "tostringHospitalizacion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AUC_HOSPITALIZACIONES_ID = "aucHospitalizacionesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

