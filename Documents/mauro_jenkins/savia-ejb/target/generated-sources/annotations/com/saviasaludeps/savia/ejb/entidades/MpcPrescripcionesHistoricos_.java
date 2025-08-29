package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpcPrescripcionesHistoricos.class)
public abstract class MpcPrescripcionesHistoricos_ {

	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Integer> estado;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> afiliadoNumeroDocumento;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Integer> periodicidad;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Boolean> actaJuantaProfesionales;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Integer> numeroEntregas;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> afiliadoPrimerNombre;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Date> fechaHora;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Boolean> recobrante;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> maTecnologiaValor;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> prescriptorNumeroDocumento;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> prescriptorCorreoElectronico;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Boolean> concentimientoInformado;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> terminalModifica;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Integer> maePrescriptorTipoDocumentoId;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> afiliadoSegundoNombre;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> maePrescriptorTipoDocumentoCodigo;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Integer> maeAfiliadoTipoDocumentoId;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> ambito;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Integer> id;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> maePrescriptorTipoDocumentoValor;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> prescriptorCodigoHabilitacion;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, CntPrestadorSedes> cntDireccionadoPrestadorSedesId;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> afiliadoSegundoApellido;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Integer> maTecnologiaId;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> maeAfiliadoTipoDocumentoCodigo;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> afiliadoPrimerApellido;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> consecutivo;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Boolean> formatoIntegralidad;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> rechazoJustificacion;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> maeAfiliadoTipoDocumentoValor;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Integer> tipoTecnologia;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, CntPrestadorSedes> cntPrescriptorPrestadorSedesId;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Boolean> referenciaAmbitoAtencion;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, Integer> cantidad;
	public static volatile SingularAttribute<MpcPrescripcionesHistoricos, String> usuarioModifica;

	public static final String ESTADO = "estado";
	public static final String AFILIADO_NUMERO_DOCUMENTO = "afiliadoNumeroDocumento";
	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String PERIODICIDAD = "periodicidad";
	public static final String ACTA_JUANTA_PROFESIONALES = "actaJuantaProfesionales";
	public static final String NUMERO_ENTREGAS = "numeroEntregas";
	public static final String AFILIADO_PRIMER_NOMBRE = "afiliadoPrimerNombre";
	public static final String FECHA_HORA = "fechaHora";
	public static final String RECOBRANTE = "recobrante";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String PRESCRIPTOR_NUMERO_DOCUMENTO = "prescriptorNumeroDocumento";
	public static final String PRESCRIPTOR_CORREO_ELECTRONICO = "prescriptorCorreoElectronico";
	public static final String CONCENTIMIENTO_INFORMADO = "concentimientoInformado";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String MAE_PRESCRIPTOR_TIPO_DOCUMENTO_ID = "maePrescriptorTipoDocumentoId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AFILIADO_SEGUNDO_NOMBRE = "afiliadoSegundoNombre";
	public static final String MAE_PRESCRIPTOR_TIPO_DOCUMENTO_CODIGO = "maePrescriptorTipoDocumentoCodigo";
	public static final String MAE_AFILIADO_TIPO_DOCUMENTO_ID = "maeAfiliadoTipoDocumentoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String AMBITO = "ambito";
	public static final String ID = "id";
	public static final String MAE_PRESCRIPTOR_TIPO_DOCUMENTO_VALOR = "maePrescriptorTipoDocumentoValor";
	public static final String PRESCRIPTOR_CODIGO_HABILITACION = "prescriptorCodigoHabilitacion";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String CNT_DIRECCIONADO_PRESTADOR_SEDES_ID = "cntDireccionadoPrestadorSedesId";
	public static final String AFILIADO_SEGUNDO_APELLIDO = "afiliadoSegundoApellido";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String MAE_AFILIADO_TIPO_DOCUMENTO_CODIGO = "maeAfiliadoTipoDocumentoCodigo";
	public static final String AFILIADO_PRIMER_APELLIDO = "afiliadoPrimerApellido";
	public static final String CONSECUTIVO = "consecutivo";
	public static final String FORMATO_INTEGRALIDAD = "formatoIntegralidad";
	public static final String RECHAZO_JUSTIFICACION = "rechazoJustificacion";
	public static final String MAE_AFILIADO_TIPO_DOCUMENTO_VALOR = "maeAfiliadoTipoDocumentoValor";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String CNT_PRESCRIPTOR_PRESTADOR_SEDES_ID = "cntPrescriptorPrestadorSedesId";
	public static final String REFERENCIA_AMBITO_ATENCION = "referenciaAmbitoAtencion";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CANTIDAD = "cantidad";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

