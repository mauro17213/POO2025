package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoHistoricoPrestaciones.class)
public abstract class CntContratoHistoricoPrestaciones_ {

	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, Boolean> tipo;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, String> autorizacion;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, String> maTecnologiaValor;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, CmDetalles> cmDetallesId;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, BigDecimal> valor;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, Integer> maTecnologiaId;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, Integer> idcntContratoPrestacion;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, String> terminalModifica;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, AuAnexos4> auAnexos4Id;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, String> usuarioCrea;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, Boolean> tipoTecnologia;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, String> terminalCrea;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, CntContratoSedes> cntContratoSedesId;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, Date> fechaHoraPrestacion;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, Boolean> anulado;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, String> usuarioModifica;
	public static volatile SingularAttribute<CntContratoHistoricoPrestaciones, CntContratos> cntContratosId;

	public static final String TIPO = "tipo";
	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String AUTORIZACION = "autorizacion";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String CM_DETALLES_ID = "cmDetallesId";
	public static final String VALOR = "valor";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String IDCNT_CONTRATO_PRESTACION = "idcntContratoPrestacion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String AU_ANEXOS4_ID = "auAnexos4Id";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNT_CONTRATO_SEDES_ID = "cntContratoSedesId";
	public static final String FECHA_HORA_PRESTACION = "fechaHoraPrestacion";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ANULADO = "anulado";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

