package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaDetalleCargas.class)
public abstract class MaDetalleCargas_ {

	public static volatile SingularAttribute<MaDetalleCargas, Integer> estado;
	public static volatile SingularAttribute<MaDetalleCargas, Date> fechaHoraProceso;
	public static volatile SingularAttribute<MaDetalleCargas, byte[]> data;
	public static volatile SingularAttribute<MaDetalleCargas, MaCargas> maCargasId;
	public static volatile SingularAttribute<MaDetalleCargas, String> detalleFallo;
	public static volatile SingularAttribute<MaDetalleCargas, Integer> id;

	public static final String ESTADO = "estado";
	public static final String FECHA_HORA_PROCESO = "fechaHoraProceso";
	public static final String DATA = "data";
	public static final String MA_CARGAS_ID = "maCargasId";
	public static final String DETALLE_FALLO = "detalleFallo";
	public static final String ID = "id";

}

