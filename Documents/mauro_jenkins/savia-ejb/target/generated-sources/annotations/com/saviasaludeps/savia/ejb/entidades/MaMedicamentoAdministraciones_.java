package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaMedicamentoAdministraciones.class)
public abstract class MaMedicamentoAdministraciones_ {

	public static volatile SingularAttribute<MaMedicamentoAdministraciones, String> usuarioCrea;
	public static volatile SingularAttribute<MaMedicamentoAdministraciones, MaMedicamentos> maMedicamentosId;
	public static volatile SingularAttribute<MaMedicamentoAdministraciones, String> maeViaAdministracionCodigo;
	public static volatile SingularAttribute<MaMedicamentoAdministraciones, String> terminalCrea;
	public static volatile SingularAttribute<MaMedicamentoAdministraciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaMedicamentoAdministraciones, Integer> id;
	public static volatile SingularAttribute<MaMedicamentoAdministraciones, Integer> maeViaAdministracionId;
	public static volatile SingularAttribute<MaMedicamentoAdministraciones, String> maeViaAdministracionValor;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MA_MEDICAMENTOS_ID = "maMedicamentosId";
	public static final String MAE_VIA_ADMINISTRACION_CODIGO = "maeViaAdministracionCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_VIA_ADMINISTRACION_ID = "maeViaAdministracionId";
	public static final String MAE_VIA_ADMINISTRACION_VALOR = "maeViaAdministracionValor";

}

