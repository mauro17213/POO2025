package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntContratoHistoricos.class)
public abstract class CntContratoHistoricos_ {

	public static volatile SingularAttribute<CntContratoHistoricos, Integer> tipo;
	public static volatile SingularAttribute<CntContratoHistoricos, String> usuarioCrea;
	public static volatile SingularAttribute<CntContratoHistoricos, String> terminalCrea;
	public static volatile SingularAttribute<CntContratoHistoricos, CntContratoDetalles> cntContratoDetallesId;
	public static volatile SingularAttribute<CntContratoHistoricos, CntContratoSedes> cntContratoSedesId;
	public static volatile SingularAttribute<CntContratoHistoricos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntContratoHistoricos, String> toString;
	public static volatile SingularAttribute<CntContratoHistoricos, Integer> id;
	public static volatile SingularAttribute<CntContratoHistoricos, Integer> origen;
	public static volatile SingularAttribute<CntContratoHistoricos, CntContratos> cntContratosId;

	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNT_CONTRATO_DETALLES_ID = "cntContratoDetallesId";
	public static final String CNT_CONTRATO_SEDES_ID = "cntContratoSedesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String TO_STRING = "toString";
	public static final String ID = "id";
	public static final String ORIGEN = "origen";
	public static final String CNT_CONTRATOS_ID = "cntContratosId";

}

