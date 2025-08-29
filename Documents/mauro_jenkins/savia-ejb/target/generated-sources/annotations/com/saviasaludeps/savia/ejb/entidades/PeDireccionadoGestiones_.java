package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeDireccionadoGestiones.class)
public abstract class PeDireccionadoGestiones_ {

	public static volatile SingularAttribute<PeDireccionadoGestiones, String> descripcion;
	public static volatile SingularAttribute<PeDireccionadoGestiones, String> usuarioCrea;
	public static volatile SingularAttribute<PeDireccionadoGestiones, String> terminalCrea;
	public static volatile SingularAttribute<PeDireccionadoGestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeDireccionadoGestiones, Integer> id;
	public static volatile SingularAttribute<PeDireccionadoGestiones, String> maeTipoCodigo;
	public static volatile SingularAttribute<PeDireccionadoGestiones, PeDireccionados> peDireccionadosId;
	public static volatile SingularAttribute<PeDireccionadoGestiones, Integer> maeTipoId;
	public static volatile SingularAttribute<PeDireccionadoGestiones, String> maeTipoValor;

	public static final String DESCRIPCION = "descripcion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_TIPO_CODIGO = "maeTipoCodigo";
	public static final String PE_DIRECCIONADOS_ID = "peDireccionadosId";
	public static final String MAE_TIPO_ID = "maeTipoId";
	public static final String MAE_TIPO_VALOR = "maeTipoValor";

}

