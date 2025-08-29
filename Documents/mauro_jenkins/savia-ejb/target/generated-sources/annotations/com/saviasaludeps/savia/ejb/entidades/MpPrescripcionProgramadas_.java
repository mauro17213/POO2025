package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpPrescripcionProgramadas.class)
public abstract class MpPrescripcionProgramadas_ {

	public static volatile ListAttribute<MpPrescripcionProgramadas, MpProgramadaEntregas> mpProgramadaEntregasList;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> estado;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> entregaTotal;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, MpPrescripcionTecnologias> mpPrescripcionTecnologiasId;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Date> fechaMaxEntrega;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, MpPrescripcionInsumos> mpPrescripcionInsumosId;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, String> sedeDireccion;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> idTransaccion;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, String> usuarioCrea;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, String> terminalCrea;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> id;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, String> maeTipoDocumentoPrestadorValor;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> entregaNumero;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, String> sedeTelefono;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> idDireccionamiento;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> entregadoTotal;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> entregadoPendiente;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Date> fechaDireccionamiento;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> entregadoNumero;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, MpPrescripciones> mpPrescripcionesId;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, String> prestadorRazonSocial;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> maeTipoDocumentoPrestadorId;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, String> sedeCodigoHabilitacion;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, MpPrescripcionMedicamentos> mpPrescripcionMedicamentosId;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> tipoTecnologia;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, Integer> entregaCantidad;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, String> maeTipoDocumentoPrestadorCodigo;
	public static volatile SingularAttribute<MpPrescripcionProgramadas, String> prestadorNumeroDocumento;

	public static final String MP_PROGRAMADA_ENTREGAS_LIST = "mpProgramadaEntregasList";
	public static final String ESTADO = "estado";
	public static final String ENTREGA_TOTAL = "entregaTotal";
	public static final String MP_PRESCRIPCION_TECNOLOGIAS_ID = "mpPrescripcionTecnologiasId";
	public static final String FECHA_MAX_ENTREGA = "fechaMaxEntrega";
	public static final String MP_PRESCRIPCION_INSUMOS_ID = "mpPrescripcionInsumosId";
	public static final String SEDE_DIRECCION = "sedeDireccion";
	public static final String ID_TRANSACCION = "idTransaccion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_TIPO_DOCUMENTO_PRESTADOR_VALOR = "maeTipoDocumentoPrestadorValor";
	public static final String ENTREGA_NUMERO = "entregaNumero";
	public static final String SEDE_TELEFONO = "sedeTelefono";
	public static final String ID_DIRECCIONAMIENTO = "idDireccionamiento";
	public static final String ENTREGADO_TOTAL = "entregadoTotal";
	public static final String ENTREGADO_PENDIENTE = "entregadoPendiente";
	public static final String FECHA_DIRECCIONAMIENTO = "fechaDireccionamiento";
	public static final String ENTREGADO_NUMERO = "entregadoNumero";
	public static final String MP_PRESCRIPCIONES_ID = "mpPrescripcionesId";
	public static final String PRESTADOR_RAZON_SOCIAL = "prestadorRazonSocial";
	public static final String MAE_TIPO_DOCUMENTO_PRESTADOR_ID = "maeTipoDocumentoPrestadorId";
	public static final String SEDE_CODIGO_HABILITACION = "sedeCodigoHabilitacion";
	public static final String MP_PRESCRIPCION_MEDICAMENTOS_ID = "mpPrescripcionMedicamentosId";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String ENTREGA_CANTIDAD = "entregaCantidad";
	public static final String MAE_TIPO_DOCUMENTO_PRESTADOR_CODIGO = "maeTipoDocumentoPrestadorCodigo";
	public static final String PRESTADOR_NUMERO_DOCUMENTO = "prestadorNumeroDocumento";

}

