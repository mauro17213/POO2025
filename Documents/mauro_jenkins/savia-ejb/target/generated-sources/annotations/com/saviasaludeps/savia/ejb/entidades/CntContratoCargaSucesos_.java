package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoCargaSucesos.class)
public abstract class CntContratoCargaSucesos_ {

	public static volatile SingularAttribute<CntContratoCargaSucesos, Integer> estado;
	public static volatile SingularAttribute<CntContratoCargaSucesos, Date> fechaHoraProceso;
	public static volatile SingularAttribute<CntContratoCargaSucesos, byte[]> data;
	public static volatile SingularAttribute<CntContratoCargaSucesos, String> detalleFallo;
	public static volatile SingularAttribute<CntContratoCargaSucesos, Integer> id;
	public static volatile SingularAttribute<CntContratoCargaSucesos, CntContratoCargas> cntContratoCargasId;

	public static final String ESTADO = "estado";
	public static final String FECHA_HORA_PROCESO = "fechaHoraProceso";
	public static final String DATA = "data";
	public static final String DETALLE_FALLO = "detalleFallo";
	public static final String ID = "id";
	public static final String CNT_CONTRATO_CARGAS_ID = "cntContratoCargasId";

}

