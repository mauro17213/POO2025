package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo3CargaAnuladaSucesos.class)
public abstract class AuAnexo3CargaAnuladaSucesos_ {

	public static volatile SingularAttribute<AuAnexo3CargaAnuladaSucesos, Integer> columna;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladaSucesos, AuAnexos3> auAnexos3Id;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladaSucesos, Integer> estado;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladaSucesos, AuAnexo3CargaAnuladas> auAnexo3CargaAnuladasId;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladaSucesos, byte[]> data;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladaSucesos, Date> fechaHora;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladaSucesos, Integer> fila;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladaSucesos, String> detalleFallo;
	public static volatile SingularAttribute<AuAnexo3CargaAnuladaSucesos, Integer> id;

	public static final String COLUMNA = "columna";
	public static final String AU_ANEXOS3_ID = "auAnexos3Id";
	public static final String ESTADO = "estado";
	public static final String AU_ANEXO3_CARGA_ANULADAS_ID = "auAnexo3CargaAnuladasId";
	public static final String DATA = "data";
	public static final String FECHA_HORA = "fechaHora";
	public static final String FILA = "fila";
	public static final String DETALLE_FALLO = "detalleFallo";
	public static final String ID = "id";

}

