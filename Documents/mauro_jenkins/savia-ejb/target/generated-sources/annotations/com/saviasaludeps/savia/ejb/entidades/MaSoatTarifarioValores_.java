package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaSoatTarifarioValores.class)
public abstract class MaSoatTarifarioValores_ {

	public static volatile SingularAttribute<MaSoatTarifarioValores, String> usuarioCrea;
	public static volatile SingularAttribute<MaSoatTarifarioValores, String> terminalCrea;
	public static volatile SingularAttribute<MaSoatTarifarioValores, BigDecimal> valor;
	public static volatile SingularAttribute<MaSoatTarifarioValores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaSoatTarifarioValores, MaSoatTarifarios> maSoatTarifariosId;
	public static volatile SingularAttribute<MaSoatTarifarioValores, Integer> agno;
	public static volatile SingularAttribute<MaSoatTarifarioValores, Integer> id;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR = "valor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MA_SOAT_TARIFARIOS_ID = "maSoatTarifariosId";
	public static final String AGNO = "agno";
	public static final String ID = "id";

}

