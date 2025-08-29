package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatUsuarios.class)
public abstract class GatUsuarios_ {

	public static volatile SingularAttribute<GatUsuarios, String> apellidos;
	public static volatile SingularAttribute<GatUsuarios, Date> fechaNacimiento;
	public static volatile ListAttribute<GatUsuarios, GatAtenciones> gatAtencionesList;
	public static volatile SingularAttribute<GatUsuarios, String> nombres;
	public static volatile SingularAttribute<GatUsuarios, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<GatUsuarios, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<GatUsuarios, String> usuarioCrea;
	public static volatile SingularAttribute<GatUsuarios, String> terminalCrea;
	public static volatile SingularAttribute<GatUsuarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatUsuarios, Integer> id;
	public static volatile SingularAttribute<GatUsuarios, String> numeroDocumento;
	public static volatile ListAttribute<GatUsuarios, GatTiquetes> gatTiquetesList;
	public static volatile SingularAttribute<GatUsuarios, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<GatUsuarios, Integer> maeTipoDocumentoId;

	public static final String APELLIDOS = "apellidos";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String GAT_ATENCIONES_LIST = "gatAtencionesList";
	public static final String NOMBRES = "nombres";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String GAT_TIQUETES_LIST = "gatTiquetesList";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";

}

