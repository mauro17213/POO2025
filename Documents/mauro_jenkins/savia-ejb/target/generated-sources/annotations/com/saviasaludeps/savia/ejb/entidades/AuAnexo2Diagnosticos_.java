package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo2Diagnosticos.class)
public abstract class AuAnexo2Diagnosticos_ {

	public static volatile SingularAttribute<AuAnexo2Diagnosticos, Integer> maeTipoDiagnosticoId;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, String> maeTipoDiagnosticoCodigo;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, String> maeTipoDiagnosticoValor;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, Boolean> principal;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, String> terminalModifica;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, AuAnexos2> auAnexos2Id;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, Integer> maDiagnosticosId;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, String> maDiagnosticosCodigo;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, String> maDiagnosticosValor;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, Integer> id;
	public static volatile SingularAttribute<AuAnexo2Diagnosticos, String> usuarioModifica;

	public static final String MAE_TIPO_DIAGNOSTICO_ID = "maeTipoDiagnosticoId";
	public static final String MAE_TIPO_DIAGNOSTICO_CODIGO = "maeTipoDiagnosticoCodigo";
	public static final String MAE_TIPO_DIAGNOSTICO_VALOR = "maeTipoDiagnosticoValor";
	public static final String PRINCIPAL = "principal";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String AU_ANEXOS2_ID = "auAnexos2Id";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_DIAGNOSTICOS_ID = "maDiagnosticosId";
	public static final String MA_DIAGNOSTICOS_CODIGO = "maDiagnosticosCodigo";
	public static final String MA_DIAGNOSTICOS_VALOR = "maDiagnosticosValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

