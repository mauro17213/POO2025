package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucHospitalizacionPatologias.class)
public abstract class AucHospitalizacionPatologias_ {

	public static volatile SingularAttribute<AucHospitalizacionPatologias, String> maePatologiaValor;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, String> descripcion;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, Integer> estado;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, String> maePatologiaCodigo;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, AucHospitalizaciones> aucHospitalizacionesId;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, String> terminalModifica;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, String> usuarioCrea;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, String> terminalCrea;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, Integer> maePatologiaId;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, Integer> id;
	public static volatile SingularAttribute<AucHospitalizacionPatologias, String> usuarioModifica;

	public static final String MAE_PATOLOGIA_VALOR = "maePatologiaValor";
	public static final String DESCRIPCION = "descripcion";
	public static final String ESTADO = "estado";
	public static final String MAE_PATOLOGIA_CODIGO = "maePatologiaCodigo";
	public static final String AUC_HOSPITALIZACIONES_ID = "aucHospitalizacionesId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_PATOLOGIA_ID = "maePatologiaId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

