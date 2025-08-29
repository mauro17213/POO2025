package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuCotizacionItems.class)
public abstract class AuCotizacionItems_ {

	public static volatile SingularAttribute<AuCotizacionItems, String> usuarioCrea;
	public static volatile SingularAttribute<AuCotizacionItems, Integer> tipoTecnologiaMipres;
	public static volatile SingularAttribute<AuCotizacionItems, Integer> mpPrescripcionItems;
	public static volatile SingularAttribute<AuCotizacionItems, String> terminalCrea;
	public static volatile SingularAttribute<AuCotizacionItems, AuAnexo3Items> auAnexo3ItemsId;
	public static volatile SingularAttribute<AuCotizacionItems, AuCotizaciones> auCotizacionesId;
	public static volatile SingularAttribute<AuCotizacionItems, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuCotizacionItems, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA_MIPRES = "tipoTecnologiaMipres";
	public static final String MP_PRESCRIPCION_ITEMS = "mpPrescripcionItems";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AU_ANEXO3_ITEMS_ID = "auAnexo3ItemsId";
	public static final String AU_COTIZACIONES_ID = "auCotizacionesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

