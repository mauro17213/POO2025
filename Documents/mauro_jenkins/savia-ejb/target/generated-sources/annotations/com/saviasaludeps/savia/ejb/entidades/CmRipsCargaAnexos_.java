package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsCargaAnexos.class)
public abstract class CmRipsCargaAnexos_ {

	public static volatile SingularAttribute<CmRipsCargaAnexos, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsCargaAnexos, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsCargaAnexos, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsCargaAnexos, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsCargaAnexos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsCargaAnexos, Integer> id;
	public static volatile SingularAttribute<CmRipsCargaAnexos, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsCargaAnexos, Boolean> existe;
	public static volatile SingularAttribute<CmRipsCargaAnexos, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsCargaAnexos, String> tipoArchivo;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String ARCHIVO_NOMBRE_ORIGINAL = "archivoNombreOriginal";
	public static final String EXISTE = "existe";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String TIPO_ARCHIVO = "tipoArchivo";

}

