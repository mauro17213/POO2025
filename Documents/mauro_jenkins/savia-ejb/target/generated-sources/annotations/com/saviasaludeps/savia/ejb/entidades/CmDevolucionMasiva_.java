package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmDevolucionMasiva.class)
public abstract class CmDevolucionMasiva_ {

	public static volatile SingularAttribute<CmDevolucionMasiva, Date> horaFinalizacionRegistro;
	public static volatile SingularAttribute<CmDevolucionMasiva, String> cmRadicado;
	public static volatile SingularAttribute<CmDevolucionMasiva, BigDecimal> sumaValorBruto;
	public static volatile ListAttribute<CmDevolucionMasiva, CmRadicados> cmRadicadosList;
	public static volatile SingularAttribute<CmDevolucionMasiva, Integer> cantidadFacturas;
	public static volatile SingularAttribute<CmDevolucionMasiva, String> ips;
	public static volatile SingularAttribute<CmDevolucionMasiva, BigDecimal> sumaValorCopago;
	public static volatile ListAttribute<CmDevolucionMasiva, CmAuditoriaDevoluciones> cmAuditoriaDevolucionesList;
	public static volatile SingularAttribute<CmDevolucionMasiva, Integer> estadoProceso;
	public static volatile SingularAttribute<CmDevolucionMasiva, String> usuarioCrea;
	public static volatile SingularAttribute<CmDevolucionMasiva, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CmDevolucionMasiva, Integer> cantidadFacturasRegistradas;
	public static volatile SingularAttribute<CmDevolucionMasiva, String> terminalCrea;
	public static volatile SingularAttribute<CmDevolucionMasiva, String> nit;
	public static volatile SingularAttribute<CmDevolucionMasiva, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmDevolucionMasiva, Integer> id;
	public static volatile SingularAttribute<CmDevolucionMasiva, String> observacion;
	public static volatile SingularAttribute<CmDevolucionMasiva, BigDecimal> sumaValorFacturado;

	public static final String HORA_FINALIZACION_REGISTRO = "horaFinalizacionRegistro";
	public static final String CM_RADICADO = "cmRadicado";
	public static final String SUMA_VALOR_BRUTO = "sumaValorBruto";
	public static final String CM_RADICADOS_LIST = "cmRadicadosList";
	public static final String CANTIDAD_FACTURAS = "cantidadFacturas";
	public static final String IPS = "ips";
	public static final String SUMA_VALOR_COPAGO = "sumaValorCopago";
	public static final String CM_AUDITORIA_DEVOLUCIONES_LIST = "cmAuditoriaDevolucionesList";
	public static final String ESTADO_PROCESO = "estadoProceso";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String CANTIDAD_FACTURAS_REGISTRADAS = "cantidadFacturasRegistradas";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String NIT = "nit";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String SUMA_VALOR_FACTURADO = "sumaValorFacturado";

}

