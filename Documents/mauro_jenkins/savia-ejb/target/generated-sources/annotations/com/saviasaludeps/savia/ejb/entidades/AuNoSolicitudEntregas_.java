package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuNoSolicitudEntregas.class)
public abstract class AuNoSolicitudEntregas_ {

	public static volatile SingularAttribute<AuNoSolicitudEntregas, Boolean> reclamaAfiliado;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Integer> faltantes;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Integer> tipoEntrega;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, AuNoSolicitudItems> auNoSolicitudItemsId;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> telefonoReclama;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Boolean> anulada;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Integer> cantidadPendiente;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> nombreReclama;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Integer> cantidadPorEntregar;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> anuladaObservacion;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> terminalModifica;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> usuarioCrea;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> terminalCrea;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Integer> id;
	public static volatile ListAttribute<AuNoSolicitudEntregas, AuNoSolicitudEntregaCargaSucesos> auNoSolicitudEntregaCargaSucesosList;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> maeCausaMoEntregaValor;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> maeCausaMoEntregaCodigo;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, AuNoSolicitudes> auNoSolicitudesId;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, AuNoSolicitudEntregaDetalles> auNoSolicitudEntregaDetallesId;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Integer> fuenteOrigen;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Integer> maeCausaMoEntregaId;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> noPrestadoObservacion;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Date> fechaHoraEntrega;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> maeCausaMoEntregaTipo;
	public static volatile ListAttribute<AuNoSolicitudEntregas, AuSolicitudAdjuntos> auSolicitudAdjuntosList;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Integer> numeroEntrega;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Integer> cantidadEntregada;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> celularReclama;
	public static volatile SingularAttribute<AuNoSolicitudEntregas, String> usuarioModifica;

	public static final String RECLAMA_AFILIADO = "reclamaAfiliado";
	public static final String FALTANTES = "faltantes";
	public static final String TIPO_ENTREGA = "tipoEntrega";
	public static final String AU_NO_SOLICITUD_ITEMS_ID = "auNoSolicitudItemsId";
	public static final String TELEFONO_RECLAMA = "telefonoReclama";
	public static final String ANULADA = "anulada";
	public static final String CANTIDAD_PENDIENTE = "cantidadPendiente";
	public static final String NOMBRE_RECLAMA = "nombreReclama";
	public static final String CANTIDAD_POR_ENTREGAR = "cantidadPorEntregar";
	public static final String ANULADA_OBSERVACION = "anuladaObservacion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String AU_NO_SOLICITUD_ENTREGA_CARGA_SUCESOS_LIST = "auNoSolicitudEntregaCargaSucesosList";
	public static final String MAE_CAUSA_MO_ENTREGA_VALOR = "maeCausaMoEntregaValor";
	public static final String MAE_CAUSA_MO_ENTREGA_CODIGO = "maeCausaMoEntregaCodigo";
	public static final String AU_NO_SOLICITUDES_ID = "auNoSolicitudesId";
	public static final String AU_NO_SOLICITUD_ENTREGA_DETALLES_ID = "auNoSolicitudEntregaDetallesId";
	public static final String FUENTE_ORIGEN = "fuenteOrigen";
	public static final String MAE_CAUSA_MO_ENTREGA_ID = "maeCausaMoEntregaId";
	public static final String NO_PRESTADO_OBSERVACION = "noPrestadoObservacion";
	public static final String FECHA_HORA_ENTREGA = "fechaHoraEntrega";
	public static final String MAE_CAUSA_MO_ENTREGA_TIPO = "maeCausaMoEntregaTipo";
	public static final String AU_SOLICITUD_ADJUNTOS_LIST = "auSolicitudAdjuntosList";
	public static final String NUMERO_ENTREGA = "numeroEntrega";
	public static final String CANTIDAD_ENTREGADA = "cantidadEntregada";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CELULAR_RECLAMA = "celularReclama";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

