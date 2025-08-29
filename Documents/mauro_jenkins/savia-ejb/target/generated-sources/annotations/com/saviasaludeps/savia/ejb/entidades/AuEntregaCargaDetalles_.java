package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuEntregaCargaDetalles.class)
public abstract class AuEntregaCargaDetalles_ {

	public static volatile SingularAttribute<AuEntregaCargaDetalles, AuEntregaCargas> auEntregaCargasId;
	public static volatile SingularAttribute<AuEntregaCargaDetalles, Integer> estado;
	public static volatile SingularAttribute<AuEntregaCargaDetalles, Date> fechaHoraProceso;
	public static volatile SingularAttribute<AuEntregaCargaDetalles, byte[]> data;
	public static volatile SingularAttribute<AuEntregaCargaDetalles, Integer> fila;
	public static volatile SingularAttribute<AuEntregaCargaDetalles, String> detalleFallo;
	public static volatile SingularAttribute<AuEntregaCargaDetalles, AuAnexo4Entregas> auAnexo4EntregasId;
	public static volatile SingularAttribute<AuEntregaCargaDetalles, Integer> id;

	public static final String AU_ENTREGA_CARGAS_ID = "auEntregaCargasId";
	public static final String ESTADO = "estado";
	public static final String FECHA_HORA_PROCESO = "fechaHoraProceso";
	public static final String DATA = "data";
	public static final String FILA = "fila";
	public static final String DETALLE_FALLO = "detalleFallo";
	public static final String AU_ANEXO4_ENTREGAS_ID = "auAnexo4EntregasId";
	public static final String ID = "id";

}

