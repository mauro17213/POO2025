package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaTecnologiaServiciosHabilitacion.class)
public abstract class MaTecnologiaServiciosHabilitacion_ {

	public static volatile SingularAttribute<MaTecnologiaServiciosHabilitacion, MaTecnologias> maTecnologiasId;
	public static volatile SingularAttribute<MaTecnologiaServiciosHabilitacion, String> terminalModifica;
	public static volatile SingularAttribute<MaTecnologiaServiciosHabilitacion, String> usuarioCrea;
	public static volatile SingularAttribute<MaTecnologiaServiciosHabilitacion, MaServiciosHabilitacion> maServiciosHabilitacionId;
	public static volatile SingularAttribute<MaTecnologiaServiciosHabilitacion, String> terminalCrea;
	public static volatile SingularAttribute<MaTecnologiaServiciosHabilitacion, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaTecnologiaServiciosHabilitacion, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaTecnologiaServiciosHabilitacion, Integer> id;
	public static volatile SingularAttribute<MaTecnologiaServiciosHabilitacion, String> usuarioModifica;
	public static volatile SingularAttribute<MaTecnologiaServiciosHabilitacion, Boolean> activo;

	public static final String MA_TECNOLOGIAS_ID = "maTecnologiasId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MA_SERVICIOS_HABILITACION_ID = "maServiciosHabilitacionId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

