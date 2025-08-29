package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RcoConciliaciones.class)
public abstract class RcoConciliaciones_ {

	public static volatile SingularAttribute<RcoConciliaciones, String> descripcion;
	public static volatile SingularAttribute<RcoConciliaciones, Integer> estado;
	public static volatile SingularAttribute<RcoConciliaciones, Long> valorConciliacion;
	public static volatile SingularAttribute<RcoConciliaciones, Long> valorTotalConciliado;
	public static volatile SingularAttribute<RcoConciliaciones, Integer> cantidadItemsRecobrados;
	public static volatile SingularAttribute<RcoConciliaciones, Long> valorRestanteNoConciliado;
	public static volatile SingularAttribute<RcoConciliaciones, String> nombre;
	public static volatile SingularAttribute<RcoConciliaciones, Integer> cntContratoId;
	public static volatile SingularAttribute<RcoConciliaciones, String> terminalModifica;
	public static volatile SingularAttribute<RcoConciliaciones, String> usuarioCrea;
	public static volatile ListAttribute<RcoConciliaciones, RcoConciliacionAdjuntos> rcoConciliacionAdjuntosList;
	public static volatile SingularAttribute<RcoConciliaciones, Integer> cantidadItems;
	public static volatile SingularAttribute<RcoConciliaciones, Date> fechaInicio;
	public static volatile SingularAttribute<RcoConciliaciones, String> correoEnvio;
	public static volatile SingularAttribute<RcoConciliaciones, String> terminalCrea;
	public static volatile SingularAttribute<RcoConciliaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RcoConciliaciones, Integer> id;
	public static volatile SingularAttribute<RcoConciliaciones, String> numeroContrato;
	public static volatile SingularAttribute<RcoConciliaciones, Date> fechaFin;
	public static volatile ListAttribute<RcoConciliaciones, RcoActas> rcoActasList;
	public static volatile SingularAttribute<RcoConciliaciones, CntPrestadorSedes> cntPresadoresSedesId;
	public static volatile ListAttribute<RcoConciliaciones, RcoConciliacionGestiones> rcoConciliacionGestionesList;
	public static volatile SingularAttribute<RcoConciliaciones, Date> fechaHoraModifica;
	public static volatile ListAttribute<RcoConciliaciones, RcoFacturaDetalles> rcoFacturaDetallesList;
	public static volatile SingularAttribute<RcoConciliaciones, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String ESTADO = "estado";
	public static final String VALOR_CONCILIACION = "valorConciliacion";
	public static final String VALOR_TOTAL_CONCILIADO = "valorTotalConciliado";
	public static final String CANTIDAD_ITEMS_RECOBRADOS = "cantidadItemsRecobrados";
	public static final String VALOR_RESTANTE_NO_CONCILIADO = "valorRestanteNoConciliado";
	public static final String NOMBRE = "nombre";
	public static final String CNT_CONTRATO_ID = "cntContratoId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String RCO_CONCILIACION_ADJUNTOS_LIST = "rcoConciliacionAdjuntosList";
	public static final String CANTIDAD_ITEMS = "cantidadItems";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String CORREO_ENVIO = "correoEnvio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NUMERO_CONTRATO = "numeroContrato";
	public static final String FECHA_FIN = "fechaFin";
	public static final String RCO_ACTAS_LIST = "rcoActasList";
	public static final String CNT_PRESADORES_SEDES_ID = "cntPresadoresSedesId";
	public static final String RCO_CONCILIACION_GESTIONES_LIST = "rcoConciliacionGestionesList";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String RCO_FACTURA_DETALLES_LIST = "rcoFacturaDetallesList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

