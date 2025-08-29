package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjOtrosies.class)
public abstract class CntjOtrosies_ {

	public static volatile SingularAttribute<CntjOtrosies, Integer> tipo;
	public static volatile SingularAttribute<CntjOtrosies, String> justificacion;
	public static volatile SingularAttribute<CntjOtrosies, Integer> estado;
	public static volatile SingularAttribute<CntjOtrosies, Integer> numero;
	public static volatile SingularAttribute<CntjOtrosies, BigDecimal> valor;
	public static volatile SingularAttribute<CntjOtrosies, Integer> plazoProrrogaMeses;
	public static volatile SingularAttribute<CntjOtrosies, CntjContratos> cntjContratosId;
	public static volatile SingularAttribute<CntjOtrosies, String> elementosAdicionales;
	public static volatile SingularAttribute<CntjOtrosies, Integer> plazoProrrogaDias;
	public static volatile SingularAttribute<CntjOtrosies, String> terminalModifica;
	public static volatile SingularAttribute<CntjOtrosies, String> usuarioCrea;
	public static volatile ListAttribute<CntjOtrosies, CntjOtrosiAdjuntos> cntjOtrosiAdjuntosList;
	public static volatile SingularAttribute<CntjOtrosies, Date> fechaInicio;
	public static volatile SingularAttribute<CntjOtrosies, String> terminalCrea;
	public static volatile SingularAttribute<CntjOtrosies, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjOtrosies, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjOtrosies, Integer> id;
	public static volatile SingularAttribute<CntjOtrosies, Date> fechaSuscripcion;
	public static volatile SingularAttribute<CntjOtrosies, Date> fechaTerminacion;
	public static volatile SingularAttribute<CntjOtrosies, Integer> estadoLegalizacion;
	public static volatile SingularAttribute<CntjOtrosies, String> usuarioModifica;

	public static final String TIPO = "tipo";
	public static final String JUSTIFICACION = "justificacion";
	public static final String ESTADO = "estado";
	public static final String NUMERO = "numero";
	public static final String VALOR = "valor";
	public static final String PLAZO_PRORROGA_MESES = "plazoProrrogaMeses";
	public static final String CNTJ_CONTRATOS_ID = "cntjContratosId";
	public static final String ELEMENTOS_ADICIONALES = "elementosAdicionales";
	public static final String PLAZO_PRORROGA_DIAS = "plazoProrrogaDias";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNTJ_OTROSI_ADJUNTOS_LIST = "cntjOtrosiAdjuntosList";
	public static final String FECHA_INICIO = "fechaInicio";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_SUSCRIPCION = "fechaSuscripcion";
	public static final String FECHA_TERMINACION = "fechaTerminacion";
	public static final String ESTADO_LEGALIZACION = "estadoLegalizacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

