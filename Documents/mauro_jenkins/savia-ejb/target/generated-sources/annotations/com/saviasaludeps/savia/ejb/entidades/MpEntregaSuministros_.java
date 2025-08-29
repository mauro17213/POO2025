package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpEntregaSuministros.class)
public abstract class MpEntregaSuministros_ {

	public static volatile SingularAttribute<MpEntregaSuministros, Integer> estadoMipres;
	public static volatile SingularAttribute<MpEntregaSuministros, String> numeroPrescripcionAsociada;
	public static volatile SingularAttribute<MpEntregaSuministros, String> terminalModifica;
	public static volatile SingularAttribute<MpEntregaSuministros, String> usuarioCrea;
	public static volatile ListAttribute<MpEntregaSuministros, MpEntregaFacturas> mpEntregaFacturasList;
	public static volatile SingularAttribute<MpEntregaSuministros, String> terminalCrea;
	public static volatile SingularAttribute<MpEntregaSuministros, MpDireccionamientoEntregados> mpDireccionamientoEntregadosId;
	public static volatile SingularAttribute<MpEntregaSuministros, Date> fechaHoraSuminisro;
	public static volatile SingularAttribute<MpEntregaSuministros, String> idSuministro;
	public static volatile SingularAttribute<MpEntregaSuministros, String> respuestaSuministro;
	public static volatile SingularAttribute<MpEntregaSuministros, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpEntregaSuministros, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MpEntregaSuministros, Integer> id;
	public static volatile SingularAttribute<MpEntregaSuministros, Boolean> ultimaEntrega;
	public static volatile SingularAttribute<MpEntregaSuministros, Boolean> anulado;
	public static volatile SingularAttribute<MpEntregaSuministros, Date> fechaConsumo;
	public static volatile SingularAttribute<MpEntregaSuministros, String> usuarioModifica;
	public static volatile SingularAttribute<MpEntregaSuministros, Date> fechaHoraAnula;

	public static final String ESTADO_MIPRES = "estadoMipres";
	public static final String NUMERO_PRESCRIPCION_ASOCIADA = "numeroPrescripcionAsociada";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MP_ENTREGA_FACTURAS_LIST = "mpEntregaFacturasList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MP_DIRECCIONAMIENTO_ENTREGADOS_ID = "mpDireccionamientoEntregadosId";
	public static final String FECHA_HORA_SUMINISRO = "fechaHoraSuminisro";
	public static final String ID_SUMINISTRO = "idSuministro";
	public static final String RESPUESTA_SUMINISTRO = "respuestaSuministro";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ULTIMA_ENTREGA = "ultimaEntrega";
	public static final String ANULADO = "anulado";
	public static final String FECHA_CONSUMO = "fechaConsumo";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String FECHA_HORA_ANULA = "fechaHoraAnula";

}

