package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RefTransporteSeguimientos.class)
public abstract class RefTransporteSeguimientos_ {

	public static volatile SingularAttribute<RefTransporteSeguimientos, String> usuarioCrea;
	public static volatile SingularAttribute<RefTransporteSeguimientos, Integer> maeTipoReporteId;
	public static volatile SingularAttribute<RefTransporteSeguimientos, String> terminalCrea;
	public static volatile SingularAttribute<RefTransporteSeguimientos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RefTransporteSeguimientos, Integer> id;
	public static volatile SingularAttribute<RefTransporteSeguimientos, RefTransportes> refTransportesId;
	public static volatile SingularAttribute<RefTransporteSeguimientos, String> maeTipoReporteValor;
	public static volatile SingularAttribute<RefTransporteSeguimientos, String> observacion;
	public static volatile SingularAttribute<RefTransporteSeguimientos, String> maeTipoReporteCodigo;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_REPORTE_ID = "maeTipoReporteId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String REF_TRANSPORTES_ID = "refTransportesId";
	public static final String MAE_TIPO_REPORTE_VALOR = "maeTipoReporteValor";
	public static final String OBSERVACION = "observacion";
	public static final String MAE_TIPO_REPORTE_CODIGO = "maeTipoReporteCodigo";

}

