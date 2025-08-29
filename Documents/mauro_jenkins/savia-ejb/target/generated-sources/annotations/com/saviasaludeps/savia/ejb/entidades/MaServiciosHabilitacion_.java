package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaServiciosHabilitacion.class)
public abstract class MaServiciosHabilitacion_ {

	public static volatile SingularAttribute<MaServiciosHabilitacion, Integer> maeGrupoId;
	public static volatile SingularAttribute<MaServiciosHabilitacion, Integer> codigo;
	public static volatile SingularAttribute<MaServiciosHabilitacion, String> nombre;
	public static volatile SingularAttribute<MaServiciosHabilitacion, String> maeGrupoValor;
	public static volatile ListAttribute<MaServiciosHabilitacion, CntPrestadorSedeServiciosHabilitacion> cntPrestadorSedeServiciosHabilitacionList;
	public static volatile SingularAttribute<MaServiciosHabilitacion, String> terminalModifica;
	public static volatile SingularAttribute<MaServiciosHabilitacion, String> usuarioCrea;
	public static volatile ListAttribute<MaServiciosHabilitacion, MaTecnologiaServiciosHabilitacion> maTecnologiaServiciosHabilitacionList;
	public static volatile SingularAttribute<MaServiciosHabilitacion, String> terminalCrea;
	public static volatile SingularAttribute<MaServiciosHabilitacion, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaServiciosHabilitacion, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaServiciosHabilitacion, Integer> id;
	public static volatile SingularAttribute<MaServiciosHabilitacion, String> maeGrupoCodigo;
	public static volatile SingularAttribute<MaServiciosHabilitacion, String> usuarioModifica;
	public static volatile SingularAttribute<MaServiciosHabilitacion, Boolean> activo;

	public static final String MAE_GRUPO_ID = "maeGrupoId";
	public static final String CODIGO = "codigo";
	public static final String NOMBRE = "nombre";
	public static final String MAE_GRUPO_VALOR = "maeGrupoValor";
	public static final String CNT_PRESTADOR_SEDE_SERVICIOS_HABILITACION_LIST = "cntPrestadorSedeServiciosHabilitacionList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MA_TECNOLOGIA_SERVICIOS_HABILITACION_LIST = "maTecnologiaServiciosHabilitacionList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_GRUPO_CODIGO = "maeGrupoCodigo";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

