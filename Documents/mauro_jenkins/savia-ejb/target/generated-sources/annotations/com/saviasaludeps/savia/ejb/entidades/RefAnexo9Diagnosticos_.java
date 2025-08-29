package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RefAnexo9Diagnosticos.class)
public abstract class RefAnexo9Diagnosticos_ {

	public static volatile SingularAttribute<RefAnexo9Diagnosticos, Boolean> principal;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, String> terminalModifica;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, String> usuarioCrea;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, String> terminalCrea;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, Integer> maDiagnosticosId;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, String> maDiagnosticosCodigo;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, String> maDiagnosticosValor;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, Integer> id;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, RefAnexos9> refAnexos9Id;
	public static volatile SingularAttribute<RefAnexo9Diagnosticos, String> usuarioModifica;

	public static final String PRINCIPAL = "principal";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_DIAGNOSTICOS_ID = "maDiagnosticosId";
	public static final String MA_DIAGNOSTICOS_CODIGO = "maDiagnosticosCodigo";
	public static final String MA_DIAGNOSTICOS_VALOR = "maDiagnosticosValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String REF_ANEXOS9_ID = "refAnexos9Id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

