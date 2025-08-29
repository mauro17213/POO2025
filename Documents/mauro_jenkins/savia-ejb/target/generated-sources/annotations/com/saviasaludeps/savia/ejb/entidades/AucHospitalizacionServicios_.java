package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucHospitalizacionServicios.class)
public abstract class AucHospitalizacionServicios_ {

	public static volatile SingularAttribute<AucHospitalizacionServicios, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<AucHospitalizacionServicios, Date> fechaHoraBorra;
	public static volatile SingularAttribute<AucHospitalizacionServicios, AucHospitalizaciones> aucHospitalizacionesId;
	public static volatile SingularAttribute<AucHospitalizacionServicios, String> maTecnologiaValor;
	public static volatile SingularAttribute<AucHospitalizacionServicios, String> usuarioBorra;
	public static volatile SingularAttribute<AucHospitalizacionServicios, Integer> maTecnologiaId;
	public static volatile SingularAttribute<AucHospitalizacionServicios, String> borradoObservacion;
	public static volatile SingularAttribute<AucHospitalizacionServicios, Date> fechaPrestacion;
	public static volatile SingularAttribute<AucHospitalizacionServicios, String> terminalModifica;
	public static volatile SingularAttribute<AucHospitalizacionServicios, String> usuarioCrea;
	public static volatile SingularAttribute<AucHospitalizacionServicios, Integer> tipoTecnologia;
	public static volatile SingularAttribute<AucHospitalizacionServicios, String> terminalCrea;
	public static volatile SingularAttribute<AucHospitalizacionServicios, CntContratoSedes> cntContratoSedesId;
	public static volatile SingularAttribute<AucHospitalizacionServicios, Boolean> borrado;
	public static volatile SingularAttribute<AucHospitalizacionServicios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucHospitalizacionServicios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AucHospitalizacionServicios, String> terminalBorra;
	public static volatile SingularAttribute<AucHospitalizacionServicios, Integer> id;
	public static volatile SingularAttribute<AucHospitalizacionServicios, String> observacion;
	public static volatile SingularAttribute<AucHospitalizacionServicios, String> usuarioModifica;
	public static volatile SingularAttribute<AucHospitalizacionServicios, CntContratos> cntContratosId;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String AUC_HOSPITALIZACIONES_ID = "aucHospitalizacionesId";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String BORRADO_OBSERVACION = "borradoObservacion";
	public static final String FECHA_PRESTACION = "fechaPrestacion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNT_CONTRATO_SEDES_ID = "cntContratoSedesId";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

