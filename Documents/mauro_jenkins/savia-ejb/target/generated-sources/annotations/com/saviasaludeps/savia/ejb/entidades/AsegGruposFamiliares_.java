package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegGruposFamiliares.class)
public abstract class AsegGruposFamiliares_ {

	public static volatile SingularAttribute<AsegGruposFamiliares, String> segundoNombre;
	public static volatile SingularAttribute<AsegGruposFamiliares, Integer> maeTipoAfiliadoId;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> primerNombre;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> primerApellido;
	public static volatile SingularAttribute<AsegGruposFamiliares, Integer> maeEpsId;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> maeEpsValor;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> segundoApellido;
	public static volatile ListAttribute<AsegGruposFamiliares, AsegAfiliados> asegAfiliadosList;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> maeTipoAfiliadoValor;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> terminalModifica;
	public static volatile SingularAttribute<AsegGruposFamiliares, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> usuarioCrea;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> maeTipoAfiliadoCodigo;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> terminalCrea;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> maeEpsCodigo;
	public static volatile SingularAttribute<AsegGruposFamiliares, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegGruposFamiliares, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AsegGruposFamiliares, Integer> id;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> numeroDocumento;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> maeTipoDocumentoId;
	public static volatile SingularAttribute<AsegGruposFamiliares, String> usuarioModifica;

	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String MAE_TIPO_AFILIADO_ID = "maeTipoAfiliadoId";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String MAE_EPS_ID = "maeEpsId";
	public static final String MAE_EPS_VALOR = "maeEpsValor";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String ASEG_AFILIADOS_LIST = "asegAfiliadosList";
	public static final String MAE_TIPO_AFILIADO_VALOR = "maeTipoAfiliadoValor";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_AFILIADO_CODIGO = "maeTipoAfiliadoCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_EPS_CODIGO = "maeEpsCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

