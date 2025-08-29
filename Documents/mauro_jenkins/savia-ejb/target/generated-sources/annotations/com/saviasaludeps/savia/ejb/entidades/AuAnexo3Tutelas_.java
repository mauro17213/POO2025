package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo3Tutelas.class)
public abstract class AuAnexo3Tutelas_ {

	public static volatile SingularAttribute<AuAnexo3Tutelas, String> fase;
	public static volatile SingularAttribute<AuAnexo3Tutelas, Date> fechaVencimiento;
	public static volatile SingularAttribute<AuAnexo3Tutelas, Date> fechaNotificacion;
	public static volatile SingularAttribute<AuAnexo3Tutelas, AuAnexos3> auAnexos3Id;
	public static volatile SingularAttribute<AuAnexo3Tutelas, String> terminalModifica;
	public static volatile SingularAttribute<AuAnexo3Tutelas, String> estadoProceso;
	public static volatile SingularAttribute<AuAnexo3Tutelas, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo3Tutelas, Boolean> medidaProvisional;
	public static volatile SingularAttribute<AuAnexo3Tutelas, Integer> numeroTutela;
	public static volatile SingularAttribute<AuAnexo3Tutelas, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo3Tutelas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo3Tutelas, Boolean> exoneracionCopago;
	public static volatile SingularAttribute<AuAnexo3Tutelas, Date> fechaFallo;
	public static volatile SingularAttribute<AuAnexo3Tutelas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuAnexo3Tutelas, Integer> id;
	public static volatile SingularAttribute<AuAnexo3Tutelas, Boolean> integralidad;
	public static volatile SingularAttribute<AuAnexo3Tutelas, String> usuarioModifica;
	public static volatile SingularAttribute<AuAnexo3Tutelas, String> numeroFallo;
	public static volatile SingularAttribute<AuAnexo3Tutelas, String> radicadoJuzgado;

	public static final String FASE = "fase";
	public static final String FECHA_VENCIMIENTO = "fechaVencimiento";
	public static final String FECHA_NOTIFICACION = "fechaNotificacion";
	public static final String AU_ANEXOS3_ID = "auAnexos3Id";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ESTADO_PROCESO = "estadoProceso";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MEDIDA_PROVISIONAL = "medidaProvisional";
	public static final String NUMERO_TUTELA = "numeroTutela";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String EXONERACION_COPAGO = "exoneracionCopago";
	public static final String FECHA_FALLO = "fechaFallo";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String INTEGRALIDAD = "integralidad";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String NUMERO_FALLO = "numeroFallo";
	public static final String RADICADO_JUZGADO = "radicadoJuzgado";

}

