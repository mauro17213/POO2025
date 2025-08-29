package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo4Historicos.class)
public abstract class AuAnexo4Historicos_ {

	public static volatile SingularAttribute<AuAnexo4Historicos, String> maeCausaValor;
	public static volatile SingularAttribute<AuAnexo4Historicos, AuAnexos4> auAnexos4Id;
	public static volatile SingularAttribute<AuAnexo4Historicos, Integer> estado;
	public static volatile SingularAttribute<AuAnexo4Historicos, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo4Historicos, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo4Historicos, String> observacionAutorizacion;
	public static volatile SingularAttribute<AuAnexo4Historicos, Integer> maeCausaId;
	public static volatile SingularAttribute<AuAnexo4Historicos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo4Historicos, Integer> id;
	public static volatile SingularAttribute<AuAnexo4Historicos, String> maeCausaCodigo;

	public static final String MAE_CAUSA_VALOR = "maeCausaValor";
	public static final String AU_ANEXOS4_ID = "auAnexos4Id";
	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String OBSERVACION_AUTORIZACION = "observacionAutorizacion";
	public static final String MAE_CAUSA_ID = "maeCausaId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_CAUSA_CODIGO = "maeCausaCodigo";

}

