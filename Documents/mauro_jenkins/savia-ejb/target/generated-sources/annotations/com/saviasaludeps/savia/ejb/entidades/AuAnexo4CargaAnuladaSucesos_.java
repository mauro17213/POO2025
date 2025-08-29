package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo4CargaAnuladaSucesos.class)
public abstract class AuAnexo4CargaAnuladaSucesos_ {

	public static volatile SingularAttribute<AuAnexo4CargaAnuladaSucesos, Integer> columna;
	public static volatile SingularAttribute<AuAnexo4CargaAnuladaSucesos, AuAnexos4> auAnexos4Id;
	public static volatile SingularAttribute<AuAnexo4CargaAnuladaSucesos, Integer> estado;
	public static volatile SingularAttribute<AuAnexo4CargaAnuladaSucesos, AuAnexo4CargaAnuladas> auAnexo4CargaAnuladasId;
	public static volatile SingularAttribute<AuAnexo4CargaAnuladaSucesos, byte[]> data;
	public static volatile SingularAttribute<AuAnexo4CargaAnuladaSucesos, Date> fechaHora;
	public static volatile SingularAttribute<AuAnexo4CargaAnuladaSucesos, Integer> fila;
	public static volatile SingularAttribute<AuAnexo4CargaAnuladaSucesos, String> detalleFallo;
	public static volatile SingularAttribute<AuAnexo4CargaAnuladaSucesos, Integer> id;

	public static final String COLUMNA = "columna";
	public static final String AU_ANEXOS4_ID = "auAnexos4Id";
	public static final String ESTADO = "estado";
	public static final String AU_ANEXO4_CARGA_ANULADAS_ID = "auAnexo4CargaAnuladasId";
	public static final String DATA = "data";
	public static final String FECHA_HORA = "fechaHora";
	public static final String FILA = "fila";
	public static final String DETALLE_FALLO = "detalleFallo";
	public static final String ID = "id";

}

