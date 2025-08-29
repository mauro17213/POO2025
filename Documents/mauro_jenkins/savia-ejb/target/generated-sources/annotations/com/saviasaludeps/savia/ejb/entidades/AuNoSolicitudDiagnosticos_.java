package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuNoSolicitudDiagnosticos.class)
public abstract class AuNoSolicitudDiagnosticos_ {

	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, Boolean> principal;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, String> terminalModifica;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, Boolean> altoCosto;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, String> usuarioCrea;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, String> terminalCrea;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, Integer> maDiagnosticosId;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, String> maDiagnosticosCodigo;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, String> maDiagnosticosValor;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, Integer> id;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, AuNoSolicitudes> auNoSolicitudesId;
	public static volatile SingularAttribute<AuNoSolicitudDiagnosticos, String> usuarioModifica;

	public static final String PRINCIPAL = "principal";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ALTO_COSTO = "altoCosto";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_DIAGNOSTICOS_ID = "maDiagnosticosId";
	public static final String MA_DIAGNOSTICOS_CODIGO = "maDiagnosticosCodigo";
	public static final String MA_DIAGNOSTICOS_VALOR = "maDiagnosticosValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String AU_NO_SOLICITUDES_ID = "auNoSolicitudesId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

