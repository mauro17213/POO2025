package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo4Destinos.class)
public abstract class AuAnexo4Destinos_ {

	public static volatile SingularAttribute<AuAnexo4Destinos, String> terminalModifica;
	public static volatile SingularAttribute<AuAnexo4Destinos, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo4Destinos, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo4Destinos, String> ubicacionValor;
	public static volatile SingularAttribute<AuAnexo4Destinos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo4Destinos, Integer> ubicacionId;
	public static volatile SingularAttribute<AuAnexo4Destinos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuAnexo4Destinos, Integer> id;
	public static volatile SingularAttribute<AuAnexo4Destinos, Integer> orden;
	public static volatile SingularAttribute<AuAnexo4Destinos, String> usuarioModifica;
	public static volatile SingularAttribute<AuAnexo4Destinos, AuAnexo4Zonas> auAnexo3ZonasId;
	public static volatile SingularAttribute<AuAnexo4Destinos, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String UBICACION_VALOR = "ubicacionValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String UBICACION_ID = "ubicacionId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ORDEN = "orden";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String AU_ANEXO3_ZONAS_ID = "auAnexo3ZonasId";
	public static final String ACTIVO = "activo";

}

