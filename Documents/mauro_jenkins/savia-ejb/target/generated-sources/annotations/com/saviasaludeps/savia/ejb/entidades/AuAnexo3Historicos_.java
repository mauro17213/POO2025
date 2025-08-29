package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo3Historicos.class)
public abstract class AuAnexo3Historicos_ {

	public static volatile SingularAttribute<AuAnexo3Historicos, AuAnexos3> auAnexos3Id;
	public static volatile SingularAttribute<AuAnexo3Historicos, Integer> tipo;
	public static volatile SingularAttribute<AuAnexo3Historicos, Integer> estado;
	public static volatile SingularAttribute<AuAnexo3Historicos, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo3Historicos, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo3Historicos, AuAnexo3Items> auAnexo3ItemsId;
	public static volatile SingularAttribute<AuAnexo3Historicos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo3Historicos, Integer> id;
	public static volatile SingularAttribute<AuAnexo3Historicos, String> observacion;

	public static final String AU_ANEXOS3_ID = "auAnexos3Id";
	public static final String TIPO = "tipo";
	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AU_ANEXO3_ITEMS_ID = "auAnexo3ItemsId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";

}

