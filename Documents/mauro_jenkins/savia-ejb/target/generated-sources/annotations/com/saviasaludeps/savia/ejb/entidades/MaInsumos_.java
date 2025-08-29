package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaInsumos.class)
public abstract class MaInsumos_ {

	public static volatile SingularAttribute<MaInsumos, Boolean> automatico;
	public static volatile SingularAttribute<MaInsumos, String> codigoHabilitacion;
	public static volatile SingularAttribute<MaInsumos, String> descripcion;
	public static volatile SingularAttribute<MaInsumos, Integer> cobertura;
	public static volatile SingularAttribute<MaInsumos, String> codigo;
	public static volatile SingularAttribute<MaInsumos, String> abreviatura;
	public static volatile ListAttribute<MaInsumos, MaPaquetes> maPaquetesList;
	public static volatile SingularAttribute<MaInsumos, String> maeTipoCodigo;
	public static volatile ListAttribute<MaInsumos, MaInsumosMipres> maInsumosMipresList;
	public static volatile SingularAttribute<MaInsumos, String> terminalModifica;
	public static volatile SingularAttribute<MaInsumos, String> usuarioCrea;
	public static volatile SingularAttribute<MaInsumos, String> terminalCrea;
	public static volatile SingularAttribute<MaInsumos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaInsumos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaInsumos, Integer> id;
	public static volatile SingularAttribute<MaInsumos, Integer> maeTipoId;
	public static volatile SingularAttribute<MaInsumos, String> maeTipoValor;
	public static volatile SingularAttribute<MaInsumos, String> usuarioModifica;
	public static volatile SingularAttribute<MaInsumos, Boolean> activo;

	public static final String AUTOMATICO = "automatico";
	public static final String CODIGO_HABILITACION = "codigoHabilitacion";
	public static final String DESCRIPCION = "descripcion";
	public static final String COBERTURA = "cobertura";
	public static final String CODIGO = "codigo";
	public static final String ABREVIATURA = "abreviatura";
	public static final String MA_PAQUETES_LIST = "maPaquetesList";
	public static final String MAE_TIPO_CODIGO = "maeTipoCodigo";
	public static final String MA_INSUMOS_MIPRES_LIST = "maInsumosMipresList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_ID = "maeTipoId";
	public static final String MAE_TIPO_VALOR = "maeTipoValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

