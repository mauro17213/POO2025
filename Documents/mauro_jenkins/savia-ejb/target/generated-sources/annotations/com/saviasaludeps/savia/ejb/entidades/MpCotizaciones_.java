package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpCotizaciones.class)
public abstract class MpCotizaciones_ {

	public static volatile SingularAttribute<MpCotizaciones, Integer> estado;
	public static volatile SingularAttribute<MpCotizaciones, String> numeroPrescripcion;
	public static volatile SingularAttribute<MpCotizaciones, MpPrescripcionTecnologias> mpPrescripcionTecnologiasId;
	public static volatile SingularAttribute<MpCotizaciones, AuCotizaciones> auCotizacionesId;
	public static volatile SingularAttribute<MpCotizaciones, Date> fechaHoraRechaza;
	public static volatile SingularAttribute<MpCotizaciones, MpPrescripciones> mpPrescripcionesId;
	public static volatile SingularAttribute<MpCotizaciones, MpPrescripcionInsumos> mpPrescripcionInsumosId;
	public static volatile SingularAttribute<MpCotizaciones, String> terminalModifica;
	public static volatile SingularAttribute<MpCotizaciones, String> usuarioCrea;
	public static volatile SingularAttribute<MpCotizaciones, MpPrescripcionMedicamentos> mpPrescripcionMedicamentosId;
	public static volatile SingularAttribute<MpCotizaciones, Integer> tipoTecnologia;
	public static volatile SingularAttribute<MpCotizaciones, Integer> consecutivoOrden;
	public static volatile SingularAttribute<MpCotizaciones, String> usuarioRechaza;
	public static volatile SingularAttribute<MpCotizaciones, String> terminalCrea;
	public static volatile SingularAttribute<MpCotizaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpCotizaciones, String> nombreTecnologia;
	public static volatile SingularAttribute<MpCotizaciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MpCotizaciones, String> terminalRechaza;
	public static volatile SingularAttribute<MpCotizaciones, Integer> id;
	public static volatile SingularAttribute<MpCotizaciones, String> usuarioModifica;

	public static final String ESTADO = "estado";
	public static final String NUMERO_PRESCRIPCION = "numeroPrescripcion";
	public static final String MP_PRESCRIPCION_TECNOLOGIAS_ID = "mpPrescripcionTecnologiasId";
	public static final String AU_COTIZACIONES_ID = "auCotizacionesId";
	public static final String FECHA_HORA_RECHAZA = "fechaHoraRechaza";
	public static final String MP_PRESCRIPCIONES_ID = "mpPrescripcionesId";
	public static final String MP_PRESCRIPCION_INSUMOS_ID = "mpPrescripcionInsumosId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MP_PRESCRIPCION_MEDICAMENTOS_ID = "mpPrescripcionMedicamentosId";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String CONSECUTIVO_ORDEN = "consecutivoOrden";
	public static final String USUARIO_RECHAZA = "usuarioRechaza";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String NOMBRE_TECNOLOGIA = "nombreTecnologia";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_RECHAZA = "terminalRechaza";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

