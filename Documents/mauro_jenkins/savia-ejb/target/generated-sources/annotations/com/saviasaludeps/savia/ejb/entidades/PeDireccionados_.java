package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeDireccionados.class)
public abstract class PeDireccionados_ {

	public static volatile SingularAttribute<PeDireccionados, String> afiliadoNumeroDocumento;
	public static volatile SingularAttribute<PeDireccionados, Integer> estado;
	public static volatile SingularAttribute<PeDireccionados, String> afiliadoPrimerNombre;
	public static volatile SingularAttribute<PeDireccionados, AuAnexos3> auAnexos3Id;
	public static volatile SingularAttribute<PeDireccionados, String> terminalModifica;
	public static volatile SingularAttribute<PeDireccionados, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<PeDireccionados, String> usuarioCrea;
	public static volatile SingularAttribute<PeDireccionados, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<PeDireccionados, Date> fechaHoraEnGestion;
	public static volatile SingularAttribute<PeDireccionados, String> terminalCrea;
	public static volatile SingularAttribute<PeDireccionados, String> afiliadoSegundoNombre;
	public static volatile SingularAttribute<PeDireccionados, Integer> maeAfiliadoTipoDocumentoId;
	public static volatile SingularAttribute<PeDireccionados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeDireccionados, Boolean> tieneTutela;
	public static volatile SingularAttribute<PeDireccionados, Integer> id;
	public static volatile SingularAttribute<PeDireccionados, String> observacion;
	public static volatile SingularAttribute<PeDireccionados, GnEmpresas> gnEmpresasId;
	public static volatile ListAttribute<PeDireccionados, PeDireccionadoItems> peDireccionadoItemsList;
	public static volatile SingularAttribute<PeDireccionados, String> afiliadoSegundoApellido;
	public static volatile SingularAttribute<PeDireccionados, PeProgramas> peProgramasId;
	public static volatile ListAttribute<PeDireccionados, PeDireccionadoGestiones> peDireccionadoGestionesList;
	public static volatile SingularAttribute<PeDireccionados, String> maeAfiliadoTipoDocumentoCodigo;
	public static volatile SingularAttribute<PeDireccionados, String> afiliadoPrimerApellido;
	public static volatile SingularAttribute<PeDireccionados, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<PeDireccionados, String> maeAfiliadoTipoDocumentoValor;
	public static volatile SingularAttribute<PeDireccionados, Date> fechaHoraGestiona;
	public static volatile SingularAttribute<PeDireccionados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeDireccionados, String> usuarioModifica;

	public static final String AFILIADO_NUMERO_DOCUMENTO = "afiliadoNumeroDocumento";
	public static final String ESTADO = "estado";
	public static final String AFILIADO_PRIMER_NOMBRE = "afiliadoPrimerNombre";
	public static final String AU_ANEXOS3_ID = "auAnexos3Id";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String FECHA_HORA_EN_GESTION = "fechaHoraEnGestion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AFILIADO_SEGUNDO_NOMBRE = "afiliadoSegundoNombre";
	public static final String MAE_AFILIADO_TIPO_DOCUMENTO_ID = "maeAfiliadoTipoDocumentoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String TIENE_TUTELA = "tieneTutela";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String PE_DIRECCIONADO_ITEMS_LIST = "peDireccionadoItemsList";
	public static final String AFILIADO_SEGUNDO_APELLIDO = "afiliadoSegundoApellido";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String PE_DIRECCIONADO_GESTIONES_LIST = "peDireccionadoGestionesList";
	public static final String MAE_AFILIADO_TIPO_DOCUMENTO_CODIGO = "maeAfiliadoTipoDocumentoCodigo";
	public static final String AFILIADO_PRIMER_APELLIDO = "afiliadoPrimerApellido";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String MAE_AFILIADO_TIPO_DOCUMENTO_VALOR = "maeAfiliadoTipoDocumentoValor";
	public static final String FECHA_HORA_GESTIONA = "fechaHoraGestiona";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

