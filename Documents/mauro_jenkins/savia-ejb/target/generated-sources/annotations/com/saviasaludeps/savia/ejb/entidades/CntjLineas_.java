package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjLineas.class)
public abstract class CntjLineas_ {

	public static volatile SingularAttribute<CntjLineas, String> descripcion;
	public static volatile SingularAttribute<CntjLineas, String> area;
	public static volatile SingularAttribute<CntjLineas, Short> tipo;
	public static volatile SingularAttribute<CntjLineas, Short> estado;
	public static volatile SingularAttribute<CntjLineas, CntjComites> cntjComitesId;
	public static volatile ListAttribute<CntjLineas, CntjEstadoEjecuciones> cntjEstadoEjecucionesList;
	public static volatile SingularAttribute<CntjLineas, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<CntjLineas, String> terminalModifica;
	public static volatile ListAttribute<CntjLineas, CntjLineaAdjuntos> cntjLineaAdjuntosList;
	public static volatile SingularAttribute<CntjLineas, String> usuarioCrea;
	public static volatile SingularAttribute<CntjLineas, String> terminalCrea;
	public static volatile SingularAttribute<CntjLineas, CntjExpedientes> cntjExpedientesId;
	public static volatile SingularAttribute<CntjLineas, String> observaciones;
	public static volatile SingularAttribute<CntjLineas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjLineas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjLineas, Integer> id;
	public static volatile SingularAttribute<CntjLineas, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String AREA = "area";
	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String CNTJ_COMITES_ID = "cntjComitesId";
	public static final String CNTJ_ESTADO_EJECUCIONES_LIST = "cntjEstadoEjecucionesList";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNTJ_LINEA_ADJUNTOS_LIST = "cntjLineaAdjuntosList";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_EXPEDIENTES_ID = "cntjExpedientesId";
	public static final String OBSERVACIONES = "observaciones";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

