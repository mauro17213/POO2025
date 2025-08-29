package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjTransiciones.class)
public abstract class CntjTransiciones_ {

	public static volatile SingularAttribute<CntjTransiciones, String> terminalModifica;
	public static volatile SingularAttribute<CntjTransiciones, String> usuarioCrea;
	public static volatile SingularAttribute<CntjTransiciones, String> terminalCrea;
	public static volatile SingularAttribute<CntjTransiciones, CntjEstados> cntjEstadosId;
	public static volatile ListAttribute<CntjTransiciones, CntjEstados> cntjEstadosList;
	public static volatile SingularAttribute<CntjTransiciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjTransiciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjTransiciones, Integer> id;
	public static volatile SingularAttribute<CntjTransiciones, String> nombre;
	public static volatile SingularAttribute<CntjTransiciones, String> usuarioModifica;
	public static volatile SingularAttribute<CntjTransiciones, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_ESTADOS_ID = "cntjEstadosId";
	public static final String CNTJ_ESTADOS_LIST = "cntjEstadosList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

