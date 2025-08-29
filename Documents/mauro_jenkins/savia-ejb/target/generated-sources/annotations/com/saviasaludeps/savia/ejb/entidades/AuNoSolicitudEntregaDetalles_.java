package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuNoSolicitudEntregaDetalles.class)
public abstract class AuNoSolicitudEntregaDetalles_ {

	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Date> fechaHoraBorra;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Integer> faltantes;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, String> usuarioBorra;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, AuNoSolicitudItems> auNoSolicitudItemsId;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Integer> catidadTotal;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Date> fechaFin;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, String> terminalModifica;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Integer> numeroEntrega;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, String> usuarioCrea;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Date> fechaInicio;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, String> terminalCrea;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Integer> cantidadEntregada;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Boolean> borrado;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Date> fechaHoraCrea;
	public static volatile ListAttribute<AuNoSolicitudEntregaDetalles, AuNoSolicitudEntregas> auNoSolicitudEntregasList;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, String> terminalBorra;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Integer> id;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, Integer> clasificacionEntrega;
	public static volatile SingularAttribute<AuNoSolicitudEntregaDetalles, String> usuarioModifica;

	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String FALTANTES = "faltantes";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String AU_NO_SOLICITUD_ITEMS_ID = "auNoSolicitudItemsId";
	public static final String CATIDAD_TOTAL = "catidadTotal";
	public static final String FECHA_FIN = "fechaFin";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String NUMERO_ENTREGA = "numeroEntrega";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CANTIDAD_ENTREGADA = "cantidadEntregada";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String AU_NO_SOLICITUD_ENTREGAS_LIST = "auNoSolicitudEntregasList";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String CLASIFICACION_ENTREGA = "clasificacionEntrega";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

