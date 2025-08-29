package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CarCargaRegistros.class)
public abstract class CarCargaRegistros_ {

	public static volatile SingularAttribute<CarCargaRegistros, Integer> tipo;
	public static volatile SingularAttribute<CarCargaRegistros, String> jsonDatos;
	public static volatile SingularAttribute<CarCargaRegistros, String> fallos;
	public static volatile SingularAttribute<CarCargaRegistros, String> terminalModifica;
	public static volatile SingularAttribute<CarCargaRegistros, String> usuarioCrea;
	public static volatile SingularAttribute<CarCargaRegistros, String> terminalCrea;
	public static volatile SingularAttribute<CarCargaRegistros, Integer> fila;
	public static volatile SingularAttribute<CarCargaRegistros, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CarCargaRegistros, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CarCargaRegistros, Integer> id;
	public static volatile SingularAttribute<CarCargaRegistros, String> usuarioModifica;
	public static volatile SingularAttribute<CarCargaRegistros, Boolean> fallido;
	public static volatile SingularAttribute<CarCargaRegistros, CarCargas> carCargasId;

	public static final String TIPO = "tipo";
	public static final String JSON_DATOS = "jsonDatos";
	public static final String FALLOS = "fallos";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String FALLIDO = "fallido";
	public static final String CAR_CARGAS_ID = "carCargasId";

}

