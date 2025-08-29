package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaTecnologiaCups.class)
public abstract class MaTecnologiaCups_ {

	public static volatile SingularAttribute<MaTecnologiaCups, MaTecnologias> maTecnologiasId;
	public static volatile SingularAttribute<MaTecnologiaCups, Date> fechaCorte;
	public static volatile SingularAttribute<MaTecnologiaCups, String> maeCoberturaValor;
	public static volatile SingularAttribute<MaTecnologiaCups, Integer> maeCoberturaId;
	public static volatile SingularAttribute<MaTecnologiaCups, Integer> id;
	public static volatile SingularAttribute<MaTecnologiaCups, String> codigoPropio;
	public static volatile SingularAttribute<MaTecnologiaCups, String> cups;
	public static volatile SingularAttribute<MaTecnologiaCups, String> maeCoberturaCodigo;

	public static final String MA_TECNOLOGIAS_ID = "maTecnologiasId";
	public static final String FECHA_CORTE = "fechaCorte";
	public static final String MAE_COBERTURA_VALOR = "maeCoberturaValor";
	public static final String MAE_COBERTURA_ID = "maeCoberturaId";
	public static final String ID = "id";
	public static final String CODIGO_PROPIO = "codigoPropio";
	public static final String CUPS = "cups";
	public static final String MAE_COBERTURA_CODIGO = "maeCoberturaCodigo";

}

