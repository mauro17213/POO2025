package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo2Rescates.class)
public abstract class AuAnexo2Rescates_ {

	public static volatile SingularAttribute<AuAnexo2Rescates, String> descripcion;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> afiliadoNumeroDocumento;
	public static volatile SingularAttribute<AuAnexo2Rescates, Integer> estado;
	public static volatile SingularAttribute<AuAnexo2Rescates, CntPrestadorSedes> cntPrestadorSedesOrigenId;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> afiliadoPrimerNombre;
	public static volatile SingularAttribute<AuAnexo2Rescates, AuAnexos3> auAnexos3Id;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> terminalModifica;
	public static volatile SingularAttribute<AuAnexo2Rescates, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo2Rescates, Date> fechaHoraRescate;
	public static volatile SingularAttribute<AuAnexo2Rescates, AuAnexos2> auAnexos2Id;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo2Rescates, CntPrestadores> cntPrestadoresDestinoId;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> afiliadoSegundoNombre;
	public static volatile SingularAttribute<AuAnexo2Rescates, Integer> maeAfiliadoTipoDocumentoId;
	public static volatile SingularAttribute<AuAnexo2Rescates, Date> fechaHoraCrea;
	public static volatile ListAttribute<AuAnexo2Rescates, AuAnexo2RescateGestiones> auAnexo2RescateGestionesList;
	public static volatile SingularAttribute<AuAnexo2Rescates, Integer> id;
	public static volatile SingularAttribute<AuAnexo2Rescates, CntPrestadorSedes> cntPrestadorSedesDestinoId;
	public static volatile SingularAttribute<AuAnexo2Rescates, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<AuAnexo2Rescates, CntPrestadores> cntPrestadoresOrigenId;
	public static volatile SingularAttribute<AuAnexo2Rescates, Short> fuenteOrigen;
	public static volatile SingularAttribute<AuAnexo2Rescates, AucHospitalizaciones> aucHospitalizacionesId;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> afiliadoSegundoApellido;
	public static volatile SingularAttribute<AuAnexo2Rescates, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> maeAfiliadoTipoDocumentoCodigo;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> afiliadoPrimerApellido;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> motivoConsulta;
	public static volatile ListAttribute<AuAnexo2Rescates, AuSolicitudAdjuntos> auSolicitudAdjuntosList;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> maeAfiliadoTipoDocumentoValor;
	public static volatile SingularAttribute<AuAnexo2Rescates, Integer> tipoRescate;
	public static volatile SingularAttribute<AuAnexo2Rescates, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuAnexo2Rescates, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String AFILIADO_NUMERO_DOCUMENTO = "afiliadoNumeroDocumento";
	public static final String ESTADO = "estado";
	public static final String CNT_PRESTADOR_SEDES_ORIGEN_ID = "cntPrestadorSedesOrigenId";
	public static final String AFILIADO_PRIMER_NOMBRE = "afiliadoPrimerNombre";
	public static final String AU_ANEXOS3_ID = "auAnexos3Id";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_HORA_RESCATE = "fechaHoraRescate";
	public static final String AU_ANEXOS2_ID = "auAnexos2Id";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNT_PRESTADORES_DESTINO_ID = "cntPrestadoresDestinoId";
	public static final String AFILIADO_SEGUNDO_NOMBRE = "afiliadoSegundoNombre";
	public static final String MAE_AFILIADO_TIPO_DOCUMENTO_ID = "maeAfiliadoTipoDocumentoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String AU_ANEXO2_RESCATE_GESTIONES_LIST = "auAnexo2RescateGestionesList";
	public static final String ID = "id";
	public static final String CNT_PRESTADOR_SEDES_DESTINO_ID = "cntPrestadorSedesDestinoId";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String CNT_PRESTADORES_ORIGEN_ID = "cntPrestadoresOrigenId";
	public static final String FUENTE_ORIGEN = "fuenteOrigen";
	public static final String AUC_HOSPITALIZACIONES_ID = "aucHospitalizacionesId";
	public static final String AFILIADO_SEGUNDO_APELLIDO = "afiliadoSegundoApellido";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String MAE_AFILIADO_TIPO_DOCUMENTO_CODIGO = "maeAfiliadoTipoDocumentoCodigo";
	public static final String AFILIADO_PRIMER_APELLIDO = "afiliadoPrimerApellido";
	public static final String MOTIVO_CONSULTA = "motivoConsulta";
	public static final String AU_SOLICITUD_ADJUNTOS_LIST = "auSolicitudAdjuntosList";
	public static final String MAE_AFILIADO_TIPO_DOCUMENTO_VALOR = "maeAfiliadoTipoDocumentoValor";
	public static final String TIPO_RESCATE = "tipoRescate";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

