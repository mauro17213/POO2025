package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmAuditoriaMasiva.class)
public abstract class CmAuditoriaMasiva_ {

	public static volatile SingularAttribute<CmAuditoriaMasiva, Date> horaFinalizacionRegistro;
	public static volatile ListAttribute<CmAuditoriaMasiva, CmAuditoriaCierres> cmAuditoriaCierresList;
	public static volatile SingularAttribute<CmAuditoriaMasiva, String> cmRadicado;
	public static volatile SingularAttribute<CmAuditoriaMasiva, BigDecimal> sumaValorBruto;
	public static volatile ListAttribute<CmAuditoriaMasiva, CmRadicados> cmRadicadosList;
	public static volatile SingularAttribute<CmAuditoriaMasiva, Integer> cantidadFacturas;
	public static volatile SingularAttribute<CmAuditoriaMasiva, String> ips;
	public static volatile SingularAttribute<CmAuditoriaMasiva, BigDecimal> sumaValorCopago;
	public static volatile SingularAttribute<CmAuditoriaMasiva, BigDecimal> valorAuditoriaExitosa;
	public static volatile SingularAttribute<CmAuditoriaMasiva, BigDecimal> valorGlosado;
	public static volatile SingularAttribute<CmAuditoriaMasiva, Integer> estadoProceso;
	public static volatile SingularAttribute<CmAuditoriaMasiva, String> usuarioCrea;
	public static volatile SingularAttribute<CmAuditoriaMasiva, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CmAuditoriaMasiva, Integer> cantidadFacturasRegistradas;
	public static volatile SingularAttribute<CmAuditoriaMasiva, String> terminalCrea;
	public static volatile SingularAttribute<CmAuditoriaMasiva, String> nit;
	public static volatile SingularAttribute<CmAuditoriaMasiva, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmAuditoriaMasiva, Integer> id;
	public static volatile SingularAttribute<CmAuditoriaMasiva, String> observacion;
	public static volatile SingularAttribute<CmAuditoriaMasiva, BigDecimal> sumaValorFacturado;

	public static final String HORA_FINALIZACION_REGISTRO = "horaFinalizacionRegistro";
	public static final String CM_AUDITORIA_CIERRES_LIST = "cmAuditoriaCierresList";
	public static final String CM_RADICADO = "cmRadicado";
	public static final String SUMA_VALOR_BRUTO = "sumaValorBruto";
	public static final String CM_RADICADOS_LIST = "cmRadicadosList";
	public static final String CANTIDAD_FACTURAS = "cantidadFacturas";
	public static final String IPS = "ips";
	public static final String SUMA_VALOR_COPAGO = "sumaValorCopago";
	public static final String VALOR_AUDITORIA_EXITOSA = "valorAuditoriaExitosa";
	public static final String VALOR_GLOSADO = "valorGlosado";
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

