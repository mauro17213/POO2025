package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuCotizacionAdjuntos.class)
public abstract class AuCotizacionAdjuntos_ {

	public static volatile SingularAttribute<AuCotizacionAdjuntos, String> nombreArchivo;
	public static volatile SingularAttribute<AuCotizacionAdjuntos, String> maeTipoArchivoValor;
	public static volatile SingularAttribute<AuCotizacionAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<AuCotizacionAdjuntos, String> archivo;
	public static volatile SingularAttribute<AuCotizacionAdjuntos, Integer> maeTipoArchivoId;
	public static volatile SingularAttribute<AuCotizacionAdjuntos, String> ruta;
	public static volatile SingularAttribute<AuCotizacionAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<AuCotizacionAdjuntos, String> maeTipoArchivoCodigo;
	public static volatile SingularAttribute<AuCotizacionAdjuntos, AuCotizaciones> auCotizacionesId;
	public static volatile SingularAttribute<AuCotizacionAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuCotizacionAdjuntos, Integer> id;

	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String MAE_TIPO_ARCHIVO_VALOR = "maeTipoArchivoValor";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ARCHIVO = "archivo";
	public static final String MAE_TIPO_ARCHIVO_ID = "maeTipoArchivoId";
	public static final String RUTA = "ruta";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_ARCHIVO_CODIGO = "maeTipoArchivoCodigo";
	public static final String AU_COTIZACIONES_ID = "auCotizacionesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

