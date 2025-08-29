package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegAdjuntos.class)
public abstract class AsegAdjuntos_ {

	public static volatile SingularAttribute<AsegAdjuntos, String> nombreArchivo;
	public static volatile SingularAttribute<AsegAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<AsegAdjuntos, String> archivo;
	public static volatile SingularAttribute<AsegAdjuntos, String> ruta;
	public static volatile SingularAttribute<AsegAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<AsegAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegAdjuntos, Integer> id;
	public static volatile SingularAttribute<AsegAdjuntos, Integer> tipoArchivo;
	public static volatile SingularAttribute<AsegAdjuntos, String> observacion;
	public static volatile SingularAttribute<AsegAdjuntos, AsegRadicadoNovedades> radicadoNovedadesId;

	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String TIPO_ARCHIVO = "tipoArchivo";
	public static final String OBSERVACION = "observacion";
	public static final String RADICADO_NOVEDADES_ID = "radicadoNovedadesId";

}

