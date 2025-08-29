package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpcPrescripciones.class)
public abstract class MpcPrescripciones_ {

	public static volatile SingularAttribute<MpcPrescripciones, Short> estado;
	public static volatile SingularAttribute<MpcPrescripciones, String> afiliadoNumeroDocumento;
	public static volatile SingularAttribute<MpcPrescripciones, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<MpcPrescripciones, Short> periodicidad;
	public static volatile SingularAttribute<MpcPrescripciones, Boolean> actaJuantaProfesionales;
	public static volatile SingularAttribute<MpcPrescripciones, Short> numeroEntregas;
	public static volatile SingularAttribute<MpcPrescripciones, String> afiliadoPrimerNombre;
	public static volatile SingularAttribute<MpcPrescripciones, Date> fechaHora;
	public static volatile SingularAttribute<MpcPrescripciones, Boolean> recobrante;
	public static volatile SingularAttribute<MpcPrescripciones, String> maTecnologiaValor;
	public static volatile ListAttribute<MpcPrescripciones, MpcProgramacionEntregas> mpcProgramacionEntregasList;
	public static volatile SingularAttribute<MpcPrescripciones, String> prescriptorNumeroDocumento;
	public static volatile SingularAttribute<MpcPrescripciones, String> prescriptorCorreoElectronico;
	public static volatile SingularAttribute<MpcPrescripciones, Boolean> concentimientoInformado;
	public static volatile SingularAttribute<MpcPrescripciones, String> terminalModifica;
	public static volatile SingularAttribute<MpcPrescripciones, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<MpcPrescripciones, Integer> maePrescriptorTipoDocumentoId;
	public static volatile SingularAttribute<MpcPrescripciones, String> usuarioCrea;
	public static volatile SingularAttribute<MpcPrescripciones, String> terminalCrea;
	public static volatile SingularAttribute<MpcPrescripciones, String> afiliadoSegundoNombre;
	public static volatile SingularAttribute<MpcPrescripciones, String> maePrescriptorTipoDocumentoCodigo;
	public static volatile SingularAttribute<MpcPrescripciones, Integer> maeAfiliadoTipoDocumentoId;
	public static volatile SingularAttribute<MpcPrescripciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpcPrescripciones, String> ambito;
	public static volatile SingularAttribute<MpcPrescripciones, Integer> id;
	public static volatile SingularAttribute<MpcPrescripciones, String> maePrescriptorTipoDocumentoValor;
	public static volatile SingularAttribute<MpcPrescripciones, String> prescriptorCodigoHabilitacion;
	public static volatile SingularAttribute<MpcPrescripciones, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<MpcPrescripciones, CntPrestadorSedes> cntDireccionadoPrestadorSedesId;
	public static volatile ListAttribute<MpcPrescripciones, MpcPrescripcionAdjuntos> mpcPrescripcionAdjuntosList;
	public static volatile SingularAttribute<MpcPrescripciones, String> afiliadoSegundoApellido;
	public static volatile SingularAttribute<MpcPrescripciones, Integer> maTecnologiaId;
	public static volatile SingularAttribute<MpcPrescripciones, String> maeAfiliadoTipoDocumentoCodigo;
	public static volatile SingularAttribute<MpcPrescripciones, String> afiliadoPrimerApellido;
	public static volatile SingularAttribute<MpcPrescripciones, String> consecutivo;
	public static volatile SingularAttribute<MpcPrescripciones, Boolean> formatoIntegralidad;
	public static volatile SingularAttribute<MpcPrescripciones, String> rechazoJustificacion;
	public static volatile SingularAttribute<MpcPrescripciones, String> maeAfiliadoTipoDocumentoValor;
	public static volatile SingularAttribute<MpcPrescripciones, Short> tipoTecnologia;
	public static volatile SingularAttribute<MpcPrescripciones, CntPrestadorSedes> cntPrescriptorPrestadorSedesId;
	public static volatile SingularAttribute<MpcPrescripciones, Boolean> referenciaAmbitoAtencion;
	public static volatile SingularAttribute<MpcPrescripciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MpcPrescripciones, Integer> cantidad;
	public static volatile SingularAttribute<MpcPrescripciones, String> usuarioModifica;

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
	public static final String MPC_PROGRAMACION_ENTREGAS_LIST = "mpcProgramacionEntregasList";
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
	public static final String MPC_PRESCRIPCION_ADJUNTOS_LIST = "mpcPrescripcionAdjuntosList";
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

