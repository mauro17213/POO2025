package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo3CargaSucesos.class)
public abstract class AuAnexo3CargaSucesos_ {

	public static volatile SingularAttribute<AuAnexo3CargaSucesos, String> descripcion;
	public static volatile SingularAttribute<AuAnexo3CargaSucesos, Integer> columna;
	public static volatile SingularAttribute<AuAnexo3CargaSucesos, Integer> tipo;
	public static volatile SingularAttribute<AuAnexo3CargaSucesos, AuAnexo3Cargas> auAnexo3CargasId;
	public static volatile SingularAttribute<AuAnexo3CargaSucesos, AuAnexo3CargaDetalles> auAnexo3CargaDetallesId;
	public static volatile SingularAttribute<AuAnexo3CargaSucesos, Date> fechaHora;
	public static volatile SingularAttribute<AuAnexo3CargaSucesos, Integer> fila;
	public static volatile SingularAttribute<AuAnexo3CargaSucesos, Integer> id;

	public static final String DESCRIPCION = "descripcion";
	public static final String COLUMNA = "columna";
	public static final String TIPO = "tipo";
	public static final String AU_ANEXO3_CARGAS_ID = "auAnexo3CargasId";
	public static final String AU_ANEXO3_CARGA_DETALLES_ID = "auAnexo3CargaDetallesId";
	public static final String FECHA_HORA = "fechaHora";
	public static final String FILA = "fila";
	public static final String ID = "id";

}

