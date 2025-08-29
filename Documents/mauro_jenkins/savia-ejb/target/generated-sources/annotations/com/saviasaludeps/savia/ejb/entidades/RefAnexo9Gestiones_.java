package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RefAnexo9Gestiones.class)
public abstract class RefAnexo9Gestiones_ {

	public static volatile SingularAttribute<RefAnexo9Gestiones, String> contactoNombre;
	public static volatile SingularAttribute<RefAnexo9Gestiones, Date> fechaHoraEgreso;
	public static volatile SingularAttribute<RefAnexo9Gestiones, Integer> origen;
	public static volatile SingularAttribute<RefAnexo9Gestiones, String> maeTipoCodigo;
	public static volatile SingularAttribute<RefAnexo9Gestiones, String> maeMotivoCodigo;
	public static volatile SingularAttribute<RefAnexo9Gestiones, String> contactoTelefono;
	public static volatile SingularAttribute<RefAnexo9Gestiones, Integer> maeMotivoId;
	public static volatile SingularAttribute<RefAnexo9Gestiones, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<RefAnexo9Gestiones, Date> fechaHoraAceptacion;
	public static volatile SingularAttribute<RefAnexo9Gestiones, String> usuarioCrea;
	public static volatile SingularAttribute<RefAnexo9Gestiones, String> terminalCrea;
	public static volatile SingularAttribute<RefAnexo9Gestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RefAnexo9Gestiones, Integer> id;
	public static volatile SingularAttribute<RefAnexo9Gestiones, RefAnexos9> refAnexos9Id;
	public static volatile SingularAttribute<RefAnexo9Gestiones, Integer> maeTipoId;
	public static volatile SingularAttribute<RefAnexo9Gestiones, String> maeTipoValor;
	public static volatile SingularAttribute<RefAnexo9Gestiones, String> observacion;
	public static volatile SingularAttribute<RefAnexo9Gestiones, String> maeMotivoValor;

	public static final String CONTACTO_NOMBRE = "contactoNombre";
	public static final String FECHA_HORA_EGRESO = "fechaHoraEgreso";
	public static final String ORIGEN = "origen";
	public static final String MAE_TIPO_CODIGO = "maeTipoCodigo";
	public static final String MAE_MOTIVO_CODIGO = "maeMotivoCodigo";
	public static final String CONTACTO_TELEFONO = "contactoTelefono";
	public static final String MAE_MOTIVO_ID = "maeMotivoId";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String FECHA_HORA_ACEPTACION = "fechaHoraAceptacion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String REF_ANEXOS9_ID = "refAnexos9Id";
	public static final String MAE_TIPO_ID = "maeTipoId";
	public static final String MAE_TIPO_VALOR = "maeTipoValor";
	public static final String OBSERVACION = "observacion";
	public static final String MAE_MOTIVO_VALOR = "maeMotivoValor";

}

