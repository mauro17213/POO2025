package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntProfesionales.class)
public abstract class CntProfesionales_ {

	public static volatile ListAttribute<CntProfesionales, AuAnexos2> auAnexos2List;
	public static volatile SingularAttribute<CntProfesionales, String> segundoNombre;
	public static volatile ListAttribute<CntProfesionales, RefAnexos9> refAnexos9List;
	public static volatile SingularAttribute<CntProfesionales, String> primerNombre;
	public static volatile SingularAttribute<CntProfesionales, String> primerApellido;
	public static volatile SingularAttribute<CntProfesionales, String> registroMedico;
	public static volatile SingularAttribute<CntProfesionales, String> documento;
	public static volatile SingularAttribute<CntProfesionales, String> segundoApellido;
	public static volatile ListAttribute<CntProfesionales, CntProfesionalPrestadores> cntProfesionalPrestadoresList;
	public static volatile ListAttribute<CntProfesionales, AuNoSolicitudes> auNoSolicitudesList;
	public static volatile SingularAttribute<CntProfesionales, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<CntProfesionales, String> terminalModifica;
	public static volatile SingularAttribute<CntProfesionales, String> usuarioCrea;
	public static volatile SingularAttribute<CntProfesionales, String> terminalCrea;
	public static volatile ListAttribute<CntProfesionales, MpPrescripciones> mpPrescripcionesList;
	public static volatile SingularAttribute<CntProfesionales, Integer> maeTipoCodumentoId;
	public static volatile SingularAttribute<CntProfesionales, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntProfesionales, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntProfesionales, Integer> id;
	public static volatile SingularAttribute<CntProfesionales, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<CntProfesionales, String> usuarioModifica;
	public static volatile ListAttribute<CntProfesionales, AuAnexos3> auAnexos3List;

	public static final String AU_ANEXOS2_LIST = "auAnexos2List";
	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String REF_ANEXOS9_LIST = "refAnexos9List";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String REGISTRO_MEDICO = "registroMedico";
	public static final String DOCUMENTO = "documento";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String CNT_PROFESIONAL_PRESTADORES_LIST = "cntProfesionalPrestadoresList";
	public static final String AU_NO_SOLICITUDES_LIST = "auNoSolicitudesList";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MP_PRESCRIPCIONES_LIST = "mpPrescripcionesList";
	public static final String MAE_TIPO_CODUMENTO_ID = "maeTipoCodumentoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String AU_ANEXOS3_LIST = "auAnexos3List";

}

