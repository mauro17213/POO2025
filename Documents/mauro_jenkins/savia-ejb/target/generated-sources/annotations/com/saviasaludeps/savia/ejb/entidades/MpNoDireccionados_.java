package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpNoDireccionados.class)
public abstract class MpNoDireccionados_ {

	public static volatile SingularAttribute<MpNoDireccionados, String> respuestaNoDireccionamiento;
	public static volatile SingularAttribute<MpNoDireccionados, Integer> estadoNoDireccionamiento;
	public static volatile SingularAttribute<MpNoDireccionados, Integer> consecutivoTecnologia;
	public static volatile SingularAttribute<MpNoDireccionados, MpPrescripcionTecnologias> mpPrescripcionTecnologiasId;
	public static volatile SingularAttribute<MpNoDireccionados, Integer> conTecAsociada;
	public static volatile SingularAttribute<MpNoDireccionados, String> numeroPrescripcionAsociada;
	public static volatile SingularAttribute<MpNoDireccionados, Integer> idNoDireccionamiento;
	public static volatile SingularAttribute<MpNoDireccionados, MpPrescripciones> mpPrescripcionesId;
	public static volatile SingularAttribute<MpNoDireccionados, String> justificacionNoDireccionamiento;
	public static volatile SingularAttribute<MpNoDireccionados, Date> fechaAnulacion;
	public static volatile SingularAttribute<MpNoDireccionados, Integer> codigoNoDireccionamiento;
	public static volatile SingularAttribute<MpNoDireccionados, MpPrescripcionInsumos> mpPrescripcionInsumosId;
	public static volatile SingularAttribute<MpNoDireccionados, Integer> idTransaccion;
	public static volatile SingularAttribute<MpNoDireccionados, String> usuarioCrea;
	public static volatile SingularAttribute<MpNoDireccionados, MpPrescripcionMedicamentos> mpPrescripcionMedicamentosId;
	public static volatile SingularAttribute<MpNoDireccionados, Integer> tipoTecnologia;
	public static volatile SingularAttribute<MpNoDireccionados, String> terminalCrea;
	public static volatile SingularAttribute<MpNoDireccionados, String> terminalAnula;
	public static volatile SingularAttribute<MpNoDireccionados, Date> fecNoDireccionamiento;
	public static volatile SingularAttribute<MpNoDireccionados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpNoDireccionados, Integer> id;
	public static volatile SingularAttribute<MpNoDireccionados, String> respuestaAnulacion;
	public static volatile SingularAttribute<MpNoDireccionados, String> usuarioAnula;
	public static volatile SingularAttribute<MpNoDireccionados, Date> fechaHoraAnula;

	public static final String RESPUESTA_NO_DIRECCIONAMIENTO = "respuestaNoDireccionamiento";
	public static final String ESTADO_NO_DIRECCIONAMIENTO = "estadoNoDireccionamiento";
	public static final String CONSECUTIVO_TECNOLOGIA = "consecutivoTecnologia";
	public static final String MP_PRESCRIPCION_TECNOLOGIAS_ID = "mpPrescripcionTecnologiasId";
	public static final String CON_TEC_ASOCIADA = "conTecAsociada";
	public static final String NUMERO_PRESCRIPCION_ASOCIADA = "numeroPrescripcionAsociada";
	public static final String ID_NO_DIRECCIONAMIENTO = "idNoDireccionamiento";
	public static final String MP_PRESCRIPCIONES_ID = "mpPrescripcionesId";
	public static final String JUSTIFICACION_NO_DIRECCIONAMIENTO = "justificacionNoDireccionamiento";
	public static final String FECHA_ANULACION = "fechaAnulacion";
	public static final String CODIGO_NO_DIRECCIONAMIENTO = "codigoNoDireccionamiento";
	public static final String MP_PRESCRIPCION_INSUMOS_ID = "mpPrescripcionInsumosId";
	public static final String ID_TRANSACCION = "idTransaccion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MP_PRESCRIPCION_MEDICAMENTOS_ID = "mpPrescripcionMedicamentosId";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String TERMINAL_ANULA = "terminalAnula";
	public static final String FEC_NO_DIRECCIONAMIENTO = "fecNoDireccionamiento";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String RESPUESTA_ANULACION = "respuestaAnulacion";
	public static final String USUARIO_ANULA = "usuarioAnula";
	public static final String FECHA_HORA_ANULA = "fechaHoraAnula";

}

