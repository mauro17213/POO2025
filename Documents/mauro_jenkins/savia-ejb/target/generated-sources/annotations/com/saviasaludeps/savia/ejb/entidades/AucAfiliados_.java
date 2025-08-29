package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucAfiliados.class)
public abstract class AucAfiliados_ {

	public static volatile SingularAttribute<AucAfiliados, String> segundoNombre;
	public static volatile SingularAttribute<AucAfiliados, Date> fechaNacimiento;
	public static volatile SingularAttribute<AucAfiliados, Integer> maeGeneroId;
	public static volatile SingularAttribute<AucAfiliados, String> segundoApellido;
	public static volatile SingularAttribute<AucAfiliados, Integer> maeRegimenId;
	public static volatile SingularAttribute<AucAfiliados, String> maeEstadoAfiliadoValor;
	public static volatile SingularAttribute<AucAfiliados, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<AucAfiliados, String> terminalModifica;
	public static volatile SingularAttribute<AucAfiliados, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AucAfiliados, String> maeGeneroCodigo;
	public static volatile SingularAttribute<AucAfiliados, String> usuarioCrea;
	public static volatile SingularAttribute<AucAfiliados, String> terminalCrea;
	public static volatile SingularAttribute<AucAfiliados, Integer> maeEstadoAfiliadoId;
	public static volatile SingularAttribute<AucAfiliados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucAfiliados, Integer> id;
	public static volatile SingularAttribute<AucAfiliados, String> numeroDocumento;
	public static volatile SingularAttribute<AucAfiliados, String> primerApellido;
	public static volatile SingularAttribute<AucAfiliados, String> primerNombre;
	public static volatile SingularAttribute<AucAfiliados, String> direccionResidencia;
	public static volatile SingularAttribute<AucAfiliados, String> maeGeneroValor;
	public static volatile SingularAttribute<AucAfiliados, String> maeRegimenValor;
	public static volatile SingularAttribute<AucAfiliados, String> maeEstadoAfiliadoCodigo;
	public static volatile ListAttribute<AucAfiliados, AucHospitalizaciones> aucHospitalizacionesList;
	public static volatile SingularAttribute<AucAfiliados, String> maeRegimenCodigo;
	public static volatile SingularAttribute<AucAfiliados, String> contratoAfiliacion;
	public static volatile ListAttribute<AucAfiliados, AucAfiliadoContactos> aucAfiliadoContactosList;
	public static volatile SingularAttribute<AucAfiliados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AucAfiliados, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<AucAfiliados, String> correoElectronico;
	public static volatile SingularAttribute<AucAfiliados, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<AucAfiliados, String> usuarioModifica;
	public static volatile SingularAttribute<AucAfiliados, Integer> ubicacionAfiliacionId;

	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String MAE_GENERO_ID = "maeGeneroId";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String MAE_REGIMEN_ID = "maeRegimenId";
	public static final String MAE_ESTADO_AFILIADO_VALOR = "maeEstadoAfiliadoValor";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String MAE_GENERO_CODIGO = "maeGeneroCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_ESTADO_AFILIADO_ID = "maeEstadoAfiliadoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String DIRECCION_RESIDENCIA = "direccionResidencia";
	public static final String MAE_GENERO_VALOR = "maeGeneroValor";
	public static final String MAE_REGIMEN_VALOR = "maeRegimenValor";
	public static final String MAE_ESTADO_AFILIADO_CODIGO = "maeEstadoAfiliadoCodigo";
	public static final String AUC_HOSPITALIZACIONES_LIST = "aucHospitalizacionesList";
	public static final String MAE_REGIMEN_CODIGO = "maeRegimenCodigo";
	public static final String CONTRATO_AFILIACION = "contratoAfiliacion";
	public static final String AUC_AFILIADO_CONTACTOS_LIST = "aucAfiliadoContactosList";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String CORREO_ELECTRONICO = "correoElectronico";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String UBICACION_AFILIACION_ID = "ubicacionAfiliacionId";

}

