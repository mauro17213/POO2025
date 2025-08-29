package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegPortabilidades.class)
public abstract class AsegPortabilidades_ {

	public static volatile SingularAttribute<AsegPortabilidades, Integer> tipoPortabilidad;
	public static volatile SingularAttribute<AsegPortabilidades, String> maeTipoPortabilidadValor;
	public static volatile SingularAttribute<AsegPortabilidades, Date> periodoFinal;
	public static volatile SingularAttribute<AsegPortabilidades, String> observacionCancelacion;
	public static volatile SingularAttribute<AsegPortabilidades, String> maeOrigenSolicitudValor;
	public static volatile SingularAttribute<AsegPortabilidades, Integer> maeMotivoId;
	public static volatile SingularAttribute<AsegPortabilidades, Integer> numeroProrroga;
	public static volatile SingularAttribute<AsegPortabilidades, String> terminalModifica;
	public static volatile SingularAttribute<AsegPortabilidades, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AsegPortabilidades, String> usuarioCrea;
	public static volatile SingularAttribute<AsegPortabilidades, Date> fechaProrroga;
	public static volatile SingularAttribute<AsegPortabilidades, String> terminalCrea;
	public static volatile SingularAttribute<AsegPortabilidades, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegPortabilidades, Integer> id;
	public static volatile SingularAttribute<AsegPortabilidades, String> maeEstadoPortabilidadCodigo;
	public static volatile SingularAttribute<AsegPortabilidades, String> maeMotivoValor;
	public static volatile SingularAttribute<AsegPortabilidades, Integer> maeTipoPortabilidadId;
	public static volatile SingularAttribute<AsegPortabilidades, Short> envioCorreo;
	public static volatile SingularAttribute<AsegPortabilidades, Date> fechaCancelacion;
	public static volatile SingularAttribute<AsegPortabilidades, Date> fechaSolicitudCancelacion;
	public static volatile SingularAttribute<AsegPortabilidades, String> usuarioCancela;
	public static volatile SingularAttribute<AsegPortabilidades, GnUbicaciones> ubicacionesId;
	public static volatile SingularAttribute<AsegPortabilidades, String> direccion;
	public static volatile SingularAttribute<AsegPortabilidades, Integer> maeOrigenSolicitudId;
	public static volatile SingularAttribute<AsegPortabilidades, String> maeTipoPortabilidadCodigo;
	public static volatile SingularAttribute<AsegPortabilidades, String> maeMotivoCodigo;
	public static volatile SingularAttribute<AsegPortabilidades, Date> periodoInicial;
	public static volatile SingularAttribute<AsegPortabilidades, Integer> maeEstadoPortabilidadId;
	public static volatile SingularAttribute<AsegPortabilidades, String> telefonoContacto2;
	public static volatile SingularAttribute<AsegPortabilidades, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<AsegPortabilidades, String> observacionUsuario;
	public static volatile SingularAttribute<AsegPortabilidades, String> maeEstadoPortabilidadValor;
	public static volatile SingularAttribute<AsegPortabilidades, Integer> origenSolicitud;
	public static volatile SingularAttribute<AsegPortabilidades, String> observacionAseguramiento;
	public static volatile SingularAttribute<AsegPortabilidades, Integer> estadoPortabilidad;
	public static volatile SingularAttribute<AsegPortabilidades, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AsegPortabilidades, Integer> mesesProrroga;
	public static volatile SingularAttribute<AsegPortabilidades, String> telefonoContacto;
	public static volatile SingularAttribute<AsegPortabilidades, String> maeOrigenSolicitudCodigo;
	public static volatile SingularAttribute<AsegPortabilidades, String> correoElectronico;
	public static volatile SingularAttribute<AsegPortabilidades, String> usuarioModifica;

	public static final String TIPO_PORTABILIDAD = "tipoPortabilidad";
	public static final String MAE_TIPO_PORTABILIDAD_VALOR = "maeTipoPortabilidadValor";
	public static final String PERIODO_FINAL = "periodoFinal";
	public static final String OBSERVACION_CANCELACION = "observacionCancelacion";
	public static final String MAE_ORIGEN_SOLICITUD_VALOR = "maeOrigenSolicitudValor";
	public static final String MAE_MOTIVO_ID = "maeMotivoId";
	public static final String NUMERO_PRORROGA = "numeroProrroga";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_PRORROGA = "fechaProrroga";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_ESTADO_PORTABILIDAD_CODIGO = "maeEstadoPortabilidadCodigo";
	public static final String MAE_MOTIVO_VALOR = "maeMotivoValor";
	public static final String MAE_TIPO_PORTABILIDAD_ID = "maeTipoPortabilidadId";
	public static final String ENVIO_CORREO = "envioCorreo";
	public static final String FECHA_CANCELACION = "fechaCancelacion";
	public static final String FECHA_SOLICITUD_CANCELACION = "fechaSolicitudCancelacion";
	public static final String USUARIO_CANCELA = "usuarioCancela";
	public static final String UBICACIONES_ID = "ubicacionesId";
	public static final String DIRECCION = "direccion";
	public static final String MAE_ORIGEN_SOLICITUD_ID = "maeOrigenSolicitudId";
	public static final String MAE_TIPO_PORTABILIDAD_CODIGO = "maeTipoPortabilidadCodigo";
	public static final String MAE_MOTIVO_CODIGO = "maeMotivoCodigo";
	public static final String PERIODO_INICIAL = "periodoInicial";
	public static final String MAE_ESTADO_PORTABILIDAD_ID = "maeEstadoPortabilidadId";
	public static final String TELEFONO_CONTACTO2 = "telefonoContacto2";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String OBSERVACION_USUARIO = "observacionUsuario";
	public static final String MAE_ESTADO_PORTABILIDAD_VALOR = "maeEstadoPortabilidadValor";
	public static final String ORIGEN_SOLICITUD = "origenSolicitud";
	public static final String OBSERVACION_ASEGURAMIENTO = "observacionAseguramiento";
	public static final String ESTADO_PORTABILIDAD = "estadoPortabilidad";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String MESES_PRORROGA = "mesesProrroga";
	public static final String TELEFONO_CONTACTO = "telefonoContacto";
	public static final String MAE_ORIGEN_SOLICITUD_CODIGO = "maeOrigenSolicitudCodigo";
	public static final String CORREO_ELECTRONICO = "correoElectronico";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

