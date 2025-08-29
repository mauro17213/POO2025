package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucHospitalizacionObjeciones.class)
public abstract class AucHospitalizacionObjeciones_ {

	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, Date> fechaHoraBorra;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, AucHospitalizaciones> aucHospitalizacionesId;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> maTecnologiaValor;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> usuarioBorra;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, Integer> cantidadSolicitada;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, Integer> maTecnologiaId;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> borradoObservacion;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> terminalModifica;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> usuarioCrea;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, Integer> tipoTecnologia;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> terminalCrea;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, Boolean> borrado;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> terminalBorra;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, Integer> id;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> observacion;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> usuarioModifica;
	public static volatile SingularAttribute<AucHospitalizacionObjeciones, String> notaCm;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String AUC_HOSPITALIZACIONES_ID = "aucHospitalizacionesId";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String CANTIDAD_SOLICITADA = "cantidadSolicitada";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String BORRADO_OBSERVACION = "borradoObservacion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String NOTA_CM = "notaCm";

}

