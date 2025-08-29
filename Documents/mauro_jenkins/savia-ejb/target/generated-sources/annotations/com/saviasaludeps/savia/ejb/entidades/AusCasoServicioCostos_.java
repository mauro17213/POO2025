package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusCasoServicioCostos.class)
public abstract class AusCasoServicioCostos_ {

	public static volatile SingularAttribute<AusCasoServicioCostos, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<AusCasoServicioCostos, Integer> maMedicamentoId;
	public static volatile SingularAttribute<AusCasoServicioCostos, String> maTecnologiaValor;
	public static volatile SingularAttribute<AusCasoServicioCostos, String> maMedicamentoCodigo;
	public static volatile SingularAttribute<AusCasoServicioCostos, Integer> cntContratoDetalleId;
	public static volatile SingularAttribute<AusCasoServicioCostos, Integer> maTecnologiaId;
	public static volatile SingularAttribute<AusCasoServicioCostos, BigDecimal> costoServicio;
	public static volatile SingularAttribute<AusCasoServicioCostos, String> maMedicamentoValor;
	public static volatile SingularAttribute<AusCasoServicioCostos, Integer> auAnexos4Id;
	public static volatile SingularAttribute<AusCasoServicioCostos, String> usuarioCrea;
	public static volatile SingularAttribute<AusCasoServicioCostos, String> terminalCrea;
	public static volatile SingularAttribute<AusCasoServicioCostos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusCasoServicioCostos, AusCasoServicios> ausCasoServiciosId;
	public static volatile SingularAttribute<AusCasoServicioCostos, Integer> id;
	public static volatile SingularAttribute<AusCasoServicioCostos, AuAnexo4Items> auAnexos4ItemsId;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String MA_MEDICAMENTO_ID = "maMedicamentoId";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String MA_MEDICAMENTO_CODIGO = "maMedicamentoCodigo";
	public static final String CNT_CONTRATO_DETALLE_ID = "cntContratoDetalleId";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String COSTO_SERVICIO = "costoServicio";
	public static final String MA_MEDICAMENTO_VALOR = "maMedicamentoValor";
	public static final String AU_ANEXOS4_ID = "auAnexos4Id";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String AUS_CASO_SERVICIOS_ID = "ausCasoServiciosId";
	public static final String ID = "id";
	public static final String AU_ANEXOS4_ITEMS_ID = "auAnexos4ItemsId";

}

