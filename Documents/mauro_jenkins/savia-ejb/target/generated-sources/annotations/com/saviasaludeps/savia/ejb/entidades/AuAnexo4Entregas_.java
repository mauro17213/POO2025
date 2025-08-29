package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo4Entregas.class)
public abstract class AuAnexo4Entregas_ {

	public static volatile SingularAttribute<AuAnexo4Entregas, Boolean> reclamaAfiliado;
	public static volatile ListAttribute<AuAnexo4Entregas, AuEntregaCargaDetalles> auEntregaCargaDetallesList;
	public static volatile SingularAttribute<AuAnexo4Entregas, Integer> tipoEntrega;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> telefonoReclama;
	public static volatile SingularAttribute<AuAnexo4Entregas, Boolean> anulada;
	public static volatile SingularAttribute<AuAnexo4Entregas, Integer> cantidadPendiente;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> nombreReclama;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> terminalModifica;
	public static volatile SingularAttribute<AuAnexo4Entregas, AuAnexos4> auAnexos4Id;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo4Entregas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo4Entregas, Integer> id;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> maeCausaNoEntregaValor;
	public static volatile SingularAttribute<AuAnexo4Entregas, Short> fuenteOrigen;
	public static volatile SingularAttribute<AuAnexo4Entregas, AuAnexo4Items> auAnexo4ItemsId;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> noPrestadoObservacion;
	public static volatile SingularAttribute<AuAnexo4Entregas, Date> fechaHoraEntrega;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> anulaObservacion;
	public static volatile ListAttribute<AuAnexo4Entregas, AuSolicitudAdjuntos> auSolicitudAdjuntosList;
	public static volatile SingularAttribute<AuAnexo4Entregas, Integer> cantidadAutorizada;
	public static volatile SingularAttribute<AuAnexo4Entregas, Integer> cantidadEntregada;
	public static volatile SingularAttribute<AuAnexo4Entregas, Integer> maeCausaNoEntregaId;
	public static volatile SingularAttribute<AuAnexo4Entregas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> celularReclama;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> maeCausaNoEntregaCodigo;
	public static volatile SingularAttribute<AuAnexo4Entregas, String> usuarioModifica;

	public static final String RECLAMA_AFILIADO = "reclamaAfiliado";
	public static final String AU_ENTREGA_CARGA_DETALLES_LIST = "auEntregaCargaDetallesList";
	public static final String TIPO_ENTREGA = "tipoEntrega";
	public static final String TELEFONO_RECLAMA = "telefonoReclama";
	public static final String ANULADA = "anulada";
	public static final String CANTIDAD_PENDIENTE = "cantidadPendiente";
	public static final String NOMBRE_RECLAMA = "nombreReclama";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String AU_ANEXOS4_ID = "auAnexos4Id";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_CAUSA_NO_ENTREGA_VALOR = "maeCausaNoEntregaValor";
	public static final String FUENTE_ORIGEN = "fuenteOrigen";
	public static final String AU_ANEXO4_ITEMS_ID = "auAnexo4ItemsId";
	public static final String NO_PRESTADO_OBSERVACION = "noPrestadoObservacion";
	public static final String FECHA_HORA_ENTREGA = "fechaHoraEntrega";
	public static final String ANULA_OBSERVACION = "anulaObservacion";
	public static final String AU_SOLICITUD_ADJUNTOS_LIST = "auSolicitudAdjuntosList";
	public static final String CANTIDAD_AUTORIZADA = "cantidadAutorizada";
	public static final String CANTIDAD_ENTREGADA = "cantidadEntregada";
	public static final String MAE_CAUSA_NO_ENTREGA_ID = "maeCausaNoEntregaId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CELULAR_RECLAMA = "celularReclama";
	public static final String MAE_CAUSA_NO_ENTREGA_CODIGO = "maeCausaNoEntregaCodigo";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

