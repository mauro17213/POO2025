package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpProgramadaEntregas.class)
public abstract class MpProgramadaEntregas_ {

	public static volatile SingularAttribute<MpProgramadaEntregas, Boolean> entregaTotal;
	public static volatile SingularAttribute<MpProgramadaEntregas, Integer> estado;
	public static volatile SingularAttribute<MpProgramadaEntregas, MpPrescripcionTecnologias> mpPrescripcionTecnologiasId;
	public static volatile SingularAttribute<MpProgramadaEntregas, BigDecimal> copago;
	public static volatile SingularAttribute<MpProgramadaEntregas, BigDecimal> valorReportado;
	public static volatile SingularAttribute<MpProgramadaEntregas, String> idReporteEntrega;
	public static volatile SingularAttribute<MpProgramadaEntregas, Date> fechaAnulacion;
	public static volatile SingularAttribute<MpProgramadaEntregas, MpPrescripcionInsumos> mpPrescripcionInsumosId;
	public static volatile SingularAttribute<MpProgramadaEntregas, String> causaNoEntrega;
	public static volatile SingularAttribute<MpProgramadaEntregas, String> codFactCufe;
	public static volatile SingularAttribute<MpProgramadaEntregas, String> usuarioCrea;
	public static volatile SingularAttribute<MpProgramadaEntregas, String> terminalCrea;
	public static volatile SingularAttribute<MpProgramadaEntregas, MpPrescripcionProgramadas> mpPrescripcionProgramadasId;
	public static volatile SingularAttribute<MpProgramadaEntregas, BigDecimal> valorTotal;
	public static volatile SingularAttribute<MpProgramadaEntregas, Date> fechaEntrega;
	public static volatile SingularAttribute<MpProgramadaEntregas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpProgramadaEntregas, Integer> id;
	public static volatile SingularAttribute<MpProgramadaEntregas, BigDecimal> cuotaModeradora;
	public static volatile SingularAttribute<MpProgramadaEntregas, Date> fechaCierreCiclo;
	public static volatile SingularAttribute<MpProgramadaEntregas, Date> fechaCierreFacturaEps;
	public static volatile SingularAttribute<MpProgramadaEntregas, MpPrescripciones> mpPrescripcionesId;
	public static volatile SingularAttribute<MpProgramadaEntregas, Integer> numeroEntrega;
	public static volatile SingularAttribute<MpProgramadaEntregas, Date> fechaReporteFactura;
	public static volatile SingularAttribute<MpProgramadaEntregas, MpPrescripcionMedicamentos> mpPrescripcionMedicamentosId;
	public static volatile SingularAttribute<MpProgramadaEntregas, BigDecimal> cantidad;

	public static final String ENTREGA_TOTAL = "entregaTotal";
	public static final String ESTADO = "estado";
	public static final String MP_PRESCRIPCION_TECNOLOGIAS_ID = "mpPrescripcionTecnologiasId";
	public static final String COPAGO = "copago";
	public static final String VALOR_REPORTADO = "valorReportado";
	public static final String ID_REPORTE_ENTREGA = "idReporteEntrega";
	public static final String FECHA_ANULACION = "fechaAnulacion";
	public static final String MP_PRESCRIPCION_INSUMOS_ID = "mpPrescripcionInsumosId";
	public static final String CAUSA_NO_ENTREGA = "causaNoEntrega";
	public static final String COD_FACT_CUFE = "codFactCufe";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MP_PRESCRIPCION_PROGRAMADAS_ID = "mpPrescripcionProgramadasId";
	public static final String VALOR_TOTAL = "valorTotal";
	public static final String FECHA_ENTREGA = "fechaEntrega";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CUOTA_MODERADORA = "cuotaModeradora";
	public static final String FECHA_CIERRE_CICLO = "fechaCierreCiclo";
	public static final String FECHA_CIERRE_FACTURA_EPS = "fechaCierreFacturaEps";
	public static final String MP_PRESCRIPCIONES_ID = "mpPrescripcionesId";
	public static final String NUMERO_ENTREGA = "numeroEntrega";
	public static final String FECHA_REPORTE_FACTURA = "fechaReporteFactura";
	public static final String MP_PRESCRIPCION_MEDICAMENTOS_ID = "mpPrescripcionMedicamentosId";
	public static final String CANTIDAD = "cantidad";

}

