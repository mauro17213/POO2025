package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo4Impresiones.class)
public abstract class AuAnexo4Impresiones_ {

	public static volatile SingularAttribute<AuAnexo4Impresiones, AuAnexos4> auAnexos4Id;
	public static volatile SingularAttribute<AuAnexo4Impresiones, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo4Impresiones, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo4Impresiones, Integer> origenImpresion;
	public static volatile SingularAttribute<AuAnexo4Impresiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo4Impresiones, Integer> id;
	public static volatile SingularAttribute<AuAnexo4Impresiones, Integer> tipoImpresion;
	public static volatile SingularAttribute<AuAnexo4Impresiones, Integer> impresion;

	public static final String AU_ANEXOS4_ID = "auAnexos4Id";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ORIGEN_IMPRESION = "origenImpresion";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String TIPO_IMPRESION = "tipoImpresion";
	public static final String IMPRESION = "impresion";

}

