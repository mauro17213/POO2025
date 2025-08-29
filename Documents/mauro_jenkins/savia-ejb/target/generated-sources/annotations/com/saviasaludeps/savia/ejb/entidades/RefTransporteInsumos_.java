package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RefTransporteInsumos.class)
public abstract class RefTransporteInsumos_ {

	public static volatile SingularAttribute<RefTransporteInsumos, String> usuarioCrea;
	public static volatile SingularAttribute<RefTransporteInsumos, String> terminalCrea;
	public static volatile SingularAttribute<RefTransporteInsumos, String> maInsumoValor;
	public static volatile SingularAttribute<RefTransporteInsumos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<RefTransporteInsumos, Integer> id;
	public static volatile SingularAttribute<RefTransporteInsumos, RefTransportes> refTransportesId;
	public static volatile SingularAttribute<RefTransporteInsumos, Integer> maInsumoId;
	public static volatile SingularAttribute<RefTransporteInsumos, String> maInsumoCodigo;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_INSUMO_VALOR = "maInsumoValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String REF_TRANSPORTES_ID = "refTransportesId";
	public static final String MA_INSUMO_ID = "maInsumoId";
	public static final String MA_INSUMO_CODIGO = "maInsumoCodigo";

}

