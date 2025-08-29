package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuJuzgadoContactos.class)
public abstract class TuJuzgadoContactos_ {

	public static volatile SingularAttribute<TuJuzgadoContactos, String> contacto;
	public static volatile SingularAttribute<TuJuzgadoContactos, TuJuzgados> tuJuzgadosId;
	public static volatile SingularAttribute<TuJuzgadoContactos, String> maeTipoContactoCodigo;
	public static volatile SingularAttribute<TuJuzgadoContactos, String> terminalModifica;
	public static volatile SingularAttribute<TuJuzgadoContactos, String> usuarioCrea;
	public static volatile SingularAttribute<TuJuzgadoContactos, String> maeTipoContactoValor;
	public static volatile SingularAttribute<TuJuzgadoContactos, String> terminalCrea;
	public static volatile SingularAttribute<TuJuzgadoContactos, Integer> maeTipoContactoId;
	public static volatile SingularAttribute<TuJuzgadoContactos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuJuzgadoContactos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuJuzgadoContactos, Integer> id;
	public static volatile SingularAttribute<TuJuzgadoContactos, String> observacion;
	public static volatile SingularAttribute<TuJuzgadoContactos, String> usuarioModifica;
	public static volatile SingularAttribute<TuJuzgadoContactos, Boolean> activo;

	public static final String CONTACTO = "contacto";
	public static final String TU_JUZGADOS_ID = "tuJuzgadosId";
	public static final String MAE_TIPO_CONTACTO_CODIGO = "maeTipoContactoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_CONTACTO_VALOR = "maeTipoContactoValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_CONTACTO_ID = "maeTipoContactoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

