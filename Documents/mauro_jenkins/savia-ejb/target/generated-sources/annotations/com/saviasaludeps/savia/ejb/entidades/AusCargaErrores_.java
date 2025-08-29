package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusCargaErrores.class)
public abstract class AusCargaErrores_ {

	public static volatile SingularAttribute<AusCargaErrores, Integer> columna;
	public static volatile SingularAttribute<AusCargaErrores, String> descripcion;
	public static volatile SingularAttribute<AusCargaErrores, AusCargaMasivas> ausCargaMasivasId;
	public static volatile SingularAttribute<AusCargaErrores, String> usuarioCrea;
	public static volatile SingularAttribute<AusCargaErrores, String> terminalCrea;
	public static volatile SingularAttribute<AusCargaErrores, Integer> fila;
	public static volatile SingularAttribute<AusCargaErrores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusCargaErrores, Integer> id;

	public static final String COLUMNA = "columna";
	public static final String DESCRIPCION = "descripcion";
	public static final String AUS_CARGA_MASIVAS_ID = "ausCargaMasivasId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

