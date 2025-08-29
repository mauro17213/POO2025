package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuRechazoItems.class)
public abstract class AuRechazoItems_ {

	public static volatile SingularAttribute<AuRechazoItems, String> usuarioCrea;
	public static volatile SingularAttribute<AuRechazoItems, String> terminalCrea;
	public static volatile SingularAttribute<AuRechazoItems, AuAnexo3Items> auAnexo3ItemsId;
	public static volatile SingularAttribute<AuRechazoItems, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuRechazoItems, Integer> id;
	public static volatile SingularAttribute<AuRechazoItems, AuRechazos> auRechazosId;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AU_ANEXO3_ITEMS_ID = "auAnexo3ItemsId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String AU_RECHAZOS_ID = "auRechazosId";

}

