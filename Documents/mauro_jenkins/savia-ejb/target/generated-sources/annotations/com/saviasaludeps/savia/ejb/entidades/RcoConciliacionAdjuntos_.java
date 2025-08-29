package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RcoConciliacionAdjuntos.class)
public abstract class RcoConciliacionAdjuntos_ {

	public static volatile SingularAttribute<RcoConciliacionAdjuntos, String> nombreArchivo;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, RcoConciliaciones> rcoConciliacionesId;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, String> maeTipoArchivoValor;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, Integer> tipo;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, Integer> maeTipoArchivoId;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, String> archivo;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, String> ruta;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, Boolean> existe;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, String> maeTipoArchivoCodigo;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RcoConciliacionAdjuntos, Integer> id;

	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String RCO_CONCILIACIONES_ID = "rcoConciliacionesId";
	public static final String MAE_TIPO_ARCHIVO_VALOR = "maeTipoArchivoValor";
	public static final String TIPO = "tipo";
	public static final String MAE_TIPO_ARCHIVO_ID = "maeTipoArchivoId";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String EXISTE = "existe";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_ARCHIVO_CODIGO = "maeTipoArchivoCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

