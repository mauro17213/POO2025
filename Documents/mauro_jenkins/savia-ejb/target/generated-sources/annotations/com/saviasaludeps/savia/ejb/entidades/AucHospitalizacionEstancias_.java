package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucHospitalizacionEstancias.class)
public abstract class AucHospitalizacionEstancias_ {

	public static volatile SingularAttribute<AucHospitalizacionEstancias, AucHospitalizaciones> aucHospitalizacionesId;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, Date> fechaEgreso;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, String> maeServicioCodigo;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, Date> fechaIngreso;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, String> terminalModifica;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, String> usuarioCrea;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, String> terminalCrea;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, String> maeServicioValor;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, Integer> dias;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, Integer> maeServicioId;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, Integer> id;
	public static volatile SingularAttribute<AucHospitalizacionEstancias, String> usuarioModifica;

	public static final String AUC_HOSPITALIZACIONES_ID = "aucHospitalizacionesId";
	public static final String FECHA_EGRESO = "fechaEgreso";
	public static final String MAE_SERVICIO_CODIGO = "maeServicioCodigo";
	public static final String FECHA_INGRESO = "fechaIngreso";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_SERVICIO_VALOR = "maeServicioValor";
	public static final String DIAS = "dias";
	public static final String MAE_SERVICIO_ID = "maeServicioId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

