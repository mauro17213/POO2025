package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo4Items.class)
public abstract class AuAnexo4Items_ {

	public static volatile SingularAttribute<AuAnexo4Items, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<AuAnexo4Items, CntContratoDetalles> cntContratoDetallesId;
	public static volatile SingularAttribute<AuAnexo4Items, String> maTecnologiaValor;
	public static volatile SingularAttribute<AuAnexo4Items, AuCotizaciones> auCotizacionesId;
	public static volatile SingularAttribute<AuAnexo4Items, BigDecimal> costoServicio;
	public static volatile SingularAttribute<AuAnexo4Items, String> entregaObservacion;
	public static volatile SingularAttribute<AuAnexo4Items, String> terminalModifica;
	public static volatile SingularAttribute<AuAnexo4Items, AuAnexos4> auAnexos4Id;
	public static volatile SingularAttribute<AuAnexo4Items, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo4Items, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo4Items, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo4Items, Integer> entrega;
	public static volatile SingularAttribute<AuAnexo4Items, Integer> id;
	public static volatile SingularAttribute<AuAnexo4Items, AuAnexo2Items> auAnexo2ItemsId;
	public static volatile SingularAttribute<AuAnexo4Items, Integer> maMedicamentoId;
	public static volatile SingularAttribute<AuAnexo4Items, AuAnexo3Items> auAnexo3ItemsId;
	public static volatile SingularAttribute<AuAnexo4Items, String> maMedicamentoCodigo;
	public static volatile SingularAttribute<AuAnexo4Items, Integer> maTecnologiaId;
	public static volatile SingularAttribute<AuAnexo4Items, String> maMedicamentoValor;
	public static volatile SingularAttribute<AuAnexo4Items, Integer> cantidadAutorizada;
	public static volatile SingularAttribute<AuAnexo4Items, Integer> tipoTecnologia;
	public static volatile ListAttribute<AuAnexo4Items, AuAnexo4Entregas> auAnexo4EntregasList;
	public static volatile ListAttribute<AuAnexo4Items, AusCasoServicioCostos> ausCasoServicioCostosList;
	public static volatile SingularAttribute<AuAnexo4Items, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuAnexo4Items, String> usuarioModifica;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String CNT_CONTRATO_DETALLES_ID = "cntContratoDetallesId";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String AU_COTIZACIONES_ID = "auCotizacionesId";
	public static final String COSTO_SERVICIO = "costoServicio";
	public static final String ENTREGA_OBSERVACION = "entregaObservacion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String AU_ANEXOS4_ID = "auAnexos4Id";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ENTREGA = "entrega";
	public static final String ID = "id";
	public static final String AU_ANEXO2_ITEMS_ID = "auAnexo2ItemsId";
	public static final String MA_MEDICAMENTO_ID = "maMedicamentoId";
	public static final String AU_ANEXO3_ITEMS_ID = "auAnexo3ItemsId";
	public static final String MA_MEDICAMENTO_CODIGO = "maMedicamentoCodigo";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String MA_MEDICAMENTO_VALOR = "maMedicamentoValor";
	public static final String CANTIDAD_AUTORIZADA = "cantidadAutorizada";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String AU_ANEXO4_ENTREGAS_LIST = "auAnexo4EntregasList";
	public static final String AUS_CASO_SERVICIO_COSTOS_LIST = "ausCasoServicioCostosList";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

