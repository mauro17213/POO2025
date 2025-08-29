package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AntAnticipoValores.class)
public abstract class AntAnticipoValores_ {

	public static volatile SingularAttribute<AntAnticipoValores, Boolean> devolucion;
	public static volatile SingularAttribute<AntAnticipoValores, String> usuarioCrea;
	public static volatile SingularAttribute<AntAnticipoValores, Integer> tipoDevolucion;
	public static volatile SingularAttribute<AntAnticipoValores, String> terminalCrea;
	public static volatile SingularAttribute<AntAnticipoValores, AuCotizaciones> auCotizacionesId;
	public static volatile SingularAttribute<AntAnticipoValores, AntAnticipos> antAnticiposId;
	public static volatile SingularAttribute<AntAnticipoValores, AntAnticipoItems> antAnticipoItemsId;
	public static volatile SingularAttribute<AntAnticipoValores, BigDecimal> valor;
	public static volatile SingularAttribute<AntAnticipoValores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AntAnticipoValores, Integer> id;
	public static volatile SingularAttribute<AntAnticipoValores, String> observacion;

	public static final String DEVOLUCION = "devolucion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_DEVOLUCION = "tipoDevolucion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AU_COTIZACIONES_ID = "auCotizacionesId";
	public static final String ANT_ANTICIPOS_ID = "antAnticiposId";
	public static final String ANT_ANTICIPO_ITEMS_ID = "antAnticipoItemsId";
	public static final String VALOR = "valor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";

}

