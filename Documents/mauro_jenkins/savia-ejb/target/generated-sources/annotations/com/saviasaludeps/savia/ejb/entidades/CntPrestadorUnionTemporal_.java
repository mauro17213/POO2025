package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntPrestadorUnionTemporal.class)
public abstract class CntPrestadorUnionTemporal_ {

	public static volatile SingularAttribute<CntPrestadorUnionTemporal, Date> fechaHoraBorra;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, String> usuarioBorra;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, CntPrestadores> cntPrestadorUnionTemporalId;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, String> terminalModifica;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, String> usuarioCrea;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, String> terminalCrea;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, Boolean> borrado;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, String> terminalBorra;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, Integer> id;
	public static volatile SingularAttribute<CntPrestadorUnionTemporal, String> usuarioModifica;

	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String CNT_PRESTADOR_UNION_TEMPORAL_ID = "cntPrestadorUnionTemporalId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

