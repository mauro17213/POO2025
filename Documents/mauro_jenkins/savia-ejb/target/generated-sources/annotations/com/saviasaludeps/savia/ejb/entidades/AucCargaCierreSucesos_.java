package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucCargaCierreSucesos.class)
public abstract class AucCargaCierreSucesos_ {

	public static volatile SingularAttribute<AucCargaCierreSucesos, String> descripcion;
	public static volatile SingularAttribute<AucCargaCierreSucesos, Short> columna;
	public static volatile SingularAttribute<AucCargaCierreSucesos, Short> tipo;
	public static volatile SingularAttribute<AucCargaCierreSucesos, String> usuarioCrea;
	public static volatile SingularAttribute<AucCargaCierreSucesos, String> terminalCrea;
	public static volatile SingularAttribute<AucCargaCierreSucesos, Short> fila;
	public static volatile SingularAttribute<AucCargaCierreSucesos, AucCargaCierres> aucCargaCierresId;
	public static volatile SingularAttribute<AucCargaCierreSucesos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucCargaCierreSucesos, Integer> id;

	public static final String DESCRIPCION = "descripcion";
	public static final String COLUMNA = "columna";
	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String AUC_CARGA_CIERRES_ID = "aucCargaCierresId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

