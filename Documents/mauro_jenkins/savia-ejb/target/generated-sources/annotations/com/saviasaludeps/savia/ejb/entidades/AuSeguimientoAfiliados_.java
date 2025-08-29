package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuSeguimientoAfiliados.class)
public abstract class AuSeguimientoAfiliados_ {

	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> segundoNombre;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Date> fechaNacimiento;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> maTecnologiaValor;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> usuarioBorra;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Integer> maeGeneroId;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> segundoApellido;
	public static volatile ListAttribute<AuSeguimientoAfiliados, AuSeguimientos> auSeguimientosList;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Integer> maeRegimenId;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> maeEstadoAfiliadoValor;
	public static volatile ListAttribute<AuSeguimientoAfiliados, AuSeguimientoAfiliadoContactos> auSeguimientoAfiliadoContactosList;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> terminalModifica;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> maeGeneroCodigo;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> usuarioCrea;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> terminalCrea;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Integer> maeEstadoAfiliadoId;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> terminalBorra;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Integer> id;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> numeroDocumento;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Date> fechaHoraBorra;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, GnUbicaciones> gnResidenciaUbicacionId;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> primerApellido;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> primerNombre;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> direccionResidencia;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Integer> maTecnologiaId;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> maeGeneroValor;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> maeRegimenValor;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Boolean> energiaPrepagada;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> maeEstadoAfiliadoCodigo;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> maeRegimenCodigo;
	public static volatile ListAttribute<AuSeguimientoAfiliados, AuSolicitudAdjuntos> auSolicitudAdjuntosList;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> contratoAfiliacion;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Integer> tipoTecnologia;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Boolean> borrado;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> barrioResidencia;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> correoElectronico;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<AuSeguimientoAfiliados, String> usuarioModifica;

	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String MAE_GENERO_ID = "maeGeneroId";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String AU_SEGUIMIENTOS_LIST = "auSeguimientosList";
	public static final String MAE_REGIMEN_ID = "maeRegimenId";
	public static final String MAE_ESTADO_AFILIADO_VALOR = "maeEstadoAfiliadoValor";
	public static final String AU_SEGUIMIENTO_AFILIADO_CONTACTOS_LIST = "auSeguimientoAfiliadoContactosList";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String MAE_GENERO_CODIGO = "maeGeneroCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_ESTADO_AFILIADO_ID = "maeEstadoAfiliadoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String GN_RESIDENCIA_UBICACION_ID = "gnResidenciaUbicacionId";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String DIRECCION_RESIDENCIA = "direccionResidencia";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String MAE_GENERO_VALOR = "maeGeneroValor";
	public static final String MAE_REGIMEN_VALOR = "maeRegimenValor";
	public static final String ENERGIA_PREPAGADA = "energiaPrepagada";
	public static final String MAE_ESTADO_AFILIADO_CODIGO = "maeEstadoAfiliadoCodigo";
	public static final String MAE_REGIMEN_CODIGO = "maeRegimenCodigo";
	public static final String AU_SOLICITUD_ADJUNTOS_LIST = "auSolicitudAdjuntosList";
	public static final String CONTRATO_AFILIACION = "contratoAfiliacion";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String BARRIO_RESIDENCIA = "barrioResidencia";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String CORREO_ELECTRONICO = "correoElectronico";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

