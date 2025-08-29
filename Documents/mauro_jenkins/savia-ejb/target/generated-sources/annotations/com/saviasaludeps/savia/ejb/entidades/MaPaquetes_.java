package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaPaquetes.class)
public abstract class MaPaquetes_ {

	public static volatile SingularAttribute<MaPaquetes, String> codigo;
	public static volatile SingularAttribute<MaPaquetes, MaMedicamentos> maMedicamentosId;
	public static volatile SingularAttribute<MaPaquetes, String> excluye;
	public static volatile SingularAttribute<MaPaquetes, String> nombre;
	public static volatile SingularAttribute<MaPaquetes, String> maeAmbitoValor;
	public static volatile SingularAttribute<MaPaquetes, MaTecnologias> maTecnologiasId;
	public static volatile SingularAttribute<MaPaquetes, Boolean> esAtoCosto;
	public static volatile SingularAttribute<MaPaquetes, String> terminalModifica;
	public static volatile SingularAttribute<MaPaquetes, MaInsumos> maInsumosId;
	public static volatile SingularAttribute<MaPaquetes, String> usuarioCrea;
	public static volatile SingularAttribute<MaPaquetes, Integer> tipoTecnologia;
	public static volatile SingularAttribute<MaPaquetes, String> maeAmbitoCodigo;
	public static volatile SingularAttribute<MaPaquetes, String> terminalCrea;
	public static volatile ListAttribute<MaPaquetes, MaPaqueteTecnologias> maPaqueteTecnologiasList;
	public static volatile SingularAttribute<MaPaquetes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaPaquetes, String> incluye;
	public static volatile SingularAttribute<MaPaquetes, Integer> maeAmbitoId;
	public static volatile SingularAttribute<MaPaquetes, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaPaquetes, Integer> id;
	public static volatile ListAttribute<MaPaquetes, MaPaquetesMipres> maPaquetesMipresList;
	public static volatile SingularAttribute<MaPaquetes, String> observacion;
	public static volatile SingularAttribute<MaPaquetes, String> requisitosTecnicos;
	public static volatile SingularAttribute<MaPaquetes, String> usuarioModifica;
	public static volatile SingularAttribute<MaPaquetes, Boolean> activo;

	public static final String CODIGO = "codigo";
	public static final String MA_MEDICAMENTOS_ID = "maMedicamentosId";
	public static final String EXCLUYE = "excluye";
	public static final String NOMBRE = "nombre";
	public static final String MAE_AMBITO_VALOR = "maeAmbitoValor";
	public static final String MA_TECNOLOGIAS_ID = "maTecnologiasId";
	public static final String ES_ATO_COSTO = "esAtoCosto";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String MA_INSUMOS_ID = "maInsumosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String MAE_AMBITO_CODIGO = "maeAmbitoCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_PAQUETE_TECNOLOGIAS_LIST = "maPaqueteTecnologiasList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String INCLUYE = "incluye";
	public static final String MAE_AMBITO_ID = "maeAmbitoId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MA_PAQUETES_MIPRES_LIST = "maPaquetesMipresList";
	public static final String OBSERVACION = "observacion";
	public static final String REQUISITOS_TECNICOS = "requisitosTecnicos";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

