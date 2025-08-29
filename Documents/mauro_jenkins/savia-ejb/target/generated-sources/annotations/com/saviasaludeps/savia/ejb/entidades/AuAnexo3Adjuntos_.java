package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo3Adjuntos.class)
public abstract class AuAnexo3Adjuntos_ {

	public static volatile SingularAttribute<AuAnexo3Adjuntos, AuAnexos3> auAnexos3Id;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, String> nombreArchivo;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, String> maeTipoArchivoValor;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, Integer> maeTipoArchivoId;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, String> archivo;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, String> ruta;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, String> maeTipoArchivoCodigo;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, Integer> id;
	public static volatile SingularAttribute<AuAnexo3Adjuntos, Boolean> existe;

	public static final String AU_ANEXOS3_ID = "auAnexos3Id";
	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String MAE_TIPO_ARCHIVO_VALOR = "maeTipoArchivoValor";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_ARCHIVO_ID = "maeTipoArchivoId";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_ARCHIVO_CODIGO = "maeTipoArchivoCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String EXISTE = "existe";

}

