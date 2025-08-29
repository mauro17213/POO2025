package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFeRipsCargaAdjuntos.class)
public abstract class CmFeRipsCargaAdjuntos_ {

	public static volatile SingularAttribute<CmFeRipsCargaAdjuntos, Integer> tipo;
	public static volatile SingularAttribute<CmFeRipsCargaAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<CmFeRipsCargaAdjuntos, String> archivoNombre;
	public static volatile SingularAttribute<CmFeRipsCargaAdjuntos, String> archivo;
	public static volatile SingularAttribute<CmFeRipsCargaAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<CmFeRipsCargaAdjuntos, String> archivoRuta;
	public static volatile SingularAttribute<CmFeRipsCargaAdjuntos, Boolean> archivoExiste;
	public static volatile SingularAttribute<CmFeRipsCargaAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmFeRipsCargaAdjuntos, Integer> id;
	public static volatile SingularAttribute<CmFeRipsCargaAdjuntos, CmFeRipsCargas> cmFeRipsCargasId;

	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String ARCHIVO = "archivo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String ARCHIVO_EXISTE = "archivoExiste";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CM_FE_RIPS_CARGAS_ID = "cmFeRipsCargasId";

}

