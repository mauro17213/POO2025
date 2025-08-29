package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuSolicitudAdjuntos.class)
public abstract class AuSolicitudAdjuntos_ {

	public static volatile SingularAttribute<AuSolicitudAdjuntos, String> maeTipoArchivoValor;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, Integer> maeTipoArchivoId;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, String> archivo;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, String> ruta;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, AuAnexo4Entregas> auAnexos4EntregasId;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, AuCotizaciones> auCotizacionesId;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, Integer> origen;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, Boolean> existe;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, AuSeguimientoGestiones> auSeguimientoGestionesId;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, String> nombre;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, AuAnexos3> auAnexos3Id;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, AuSeguimientoAfiliados> auSeguimientoAfiliadoId;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, AuAnexo2Rescates> auAnexo2RescatesId;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, AuAnexos2> auAnexos2Id;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, String> maeTipoArchivoCodigo;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, Integer> auTopeAfiliadosId;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, Integer> id;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, AuSeguimientos> auSeguimientosId;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, AuNoSolicitudEntregas> auNoSolicitudEntregasId;
	public static volatile SingularAttribute<AuSolicitudAdjuntos, AuNoSolicitudes> auNoSolicitudesId;

	public static final String MAE_TIPO_ARCHIVO_VALOR = "maeTipoArchivoValor";
	public static final String MAE_TIPO_ARCHIVO_ID = "maeTipoArchivoId";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String AU_ANEXOS4_ENTREGAS_ID = "auAnexos4EntregasId";
	public static final String AU_COTIZACIONES_ID = "auCotizacionesId";
	public static final String ORIGEN = "origen";
	public static final String EXISTE = "existe";
	public static final String AU_SEGUIMIENTO_GESTIONES_ID = "auSeguimientoGestionesId";
	public static final String NOMBRE = "nombre";
	public static final String AU_ANEXOS3_ID = "auAnexos3Id";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String AU_SEGUIMIENTO_AFILIADO_ID = "auSeguimientoAfiliadoId";
	public static final String AU_ANEXO2_RESCATES_ID = "auAnexo2RescatesId";
	public static final String AU_ANEXOS2_ID = "auAnexos2Id";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_ARCHIVO_CODIGO = "maeTipoArchivoCodigo";
	public static final String AU_TOPE_AFILIADOS_ID = "auTopeAfiliadosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String AU_SEGUIMIENTOS_ID = "auSeguimientosId";
	public static final String AU_NO_SOLICITUD_ENTREGAS_ID = "auNoSolicitudEntregasId";
	public static final String AU_NO_SOLICITUDES_ID = "auNoSolicitudesId";

}

