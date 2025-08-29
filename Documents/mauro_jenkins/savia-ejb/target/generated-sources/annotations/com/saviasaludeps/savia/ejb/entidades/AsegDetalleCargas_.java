package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegDetalleCargas.class)
public abstract class AsegDetalleCargas_ {

	public static volatile SingularAttribute<AsegDetalleCargas, Integer> estado;
	public static volatile SingularAttribute<AsegDetalleCargas, Date> fechaHoraProceso;
	public static volatile SingularAttribute<AsegDetalleCargas, byte[]> data;
	public static volatile SingularAttribute<AsegDetalleCargas, String> detalleFallo;
	public static volatile SingularAttribute<AsegDetalleCargas, Integer> id;
	public static volatile SingularAttribute<AsegDetalleCargas, AsegCargas> asegCargasId;

	public static final String ESTADO = "estado";
	public static final String FECHA_HORA_PROCESO = "fechaHoraProceso";
	public static final String DATA = "data";
	public static final String DETALLE_FALLO = "detalleFallo";
	public static final String ID = "id";
	public static final String ASEG_CARGAS_ID = "asegCargasId";

}

