package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegAnexo1Adjuntos.class)
public abstract class AsegAnexo1Adjuntos_ {

	public static volatile SingularAttribute<AsegAnexo1Adjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<AsegAnexo1Adjuntos, AsegAnexos1> asegAnexos1Id;
	public static volatile SingularAttribute<AsegAnexo1Adjuntos, String> archivo;
	public static volatile SingularAttribute<AsegAnexo1Adjuntos, String> ruta;
	public static volatile SingularAttribute<AsegAnexo1Adjuntos, String> terminalCrea;
	public static volatile SingularAttribute<AsegAnexo1Adjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegAnexo1Adjuntos, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ASEG_ANEXOS1_ID = "asegAnexos1Id";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

