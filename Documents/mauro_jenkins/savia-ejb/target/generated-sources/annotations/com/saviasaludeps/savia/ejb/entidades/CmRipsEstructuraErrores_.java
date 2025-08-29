package com.saviasaludeps.savia.ejb.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsEstructuraErrores.class)
public abstract class CmRipsEstructuraErrores_ {

	public static volatile SingularAttribute<CmRipsEstructuraErrores, String> archivoFila;
	public static volatile SingularAttribute<CmRipsEstructuraErrores, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsEstructuraErrores, Integer> id;
	public static volatile SingularAttribute<CmRipsEstructuraErrores, String> descripcionError;
	public static volatile SingularAttribute<CmRipsEstructuraErrores, CmRipsCargas> cmRipsCargasId;

	public static final String ARCHIVO_FILA = "archivoFila";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String ID = "id";
	public static final String DESCRIPCION_ERROR = "descripcionError";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";

}

