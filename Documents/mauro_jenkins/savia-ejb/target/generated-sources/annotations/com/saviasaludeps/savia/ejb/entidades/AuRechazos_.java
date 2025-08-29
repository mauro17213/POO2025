package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuRechazos.class)
public abstract class AuRechazos_ {

	public static volatile SingularAttribute<AuRechazos, String> maeCausaRechazoCodigo;
	public static volatile SingularAttribute<AuRechazos, String> justificacion;
	public static volatile SingularAttribute<AuRechazos, String> archivo;
	public static volatile SingularAttribute<AuRechazos, String> ruta;
	public static volatile ListAttribute<AuRechazos, AuRechazoItems> auRechazoItemsList;
	public static volatile SingularAttribute<AuRechazos, Integer> maeCausaRechazoId;
	public static volatile SingularAttribute<AuRechazos, String> alternativa;
	public static volatile SingularAttribute<AuRechazos, AuAnexos3> auAnexos3Id;
	public static volatile SingularAttribute<AuRechazos, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AuRechazos, String> usuarioCrea;
	public static volatile SingularAttribute<AuRechazos, String> terminalCrea;
	public static volatile SingularAttribute<AuRechazos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuRechazos, Integer> id;
	public static volatile SingularAttribute<AuRechazos, String> maeCausaRechazoValor;
	public static volatile SingularAttribute<AuRechazos, GnEmpresas> gnEmpresasId;

	public static final String MAE_CAUSA_RECHAZO_CODIGO = "maeCausaRechazoCodigo";
	public static final String JUSTIFICACION = "justificacion";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String AU_RECHAZO_ITEMS_LIST = "auRechazoItemsList";
	public static final String MAE_CAUSA_RECHAZO_ID = "maeCausaRechazoId";
	public static final String ALTERNATIVA = "alternativa";
	public static final String AU_ANEXOS3_ID = "auAnexos3Id";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_CAUSA_RECHAZO_VALOR = "maeCausaRechazoValor";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";

}

