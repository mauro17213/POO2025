package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo4Zonas.class)
public abstract class AuAnexo4Zonas_ {

	public static volatile SingularAttribute<AuAnexo4Zonas, String> terminalModifica;
	public static volatile SingularAttribute<AuAnexo4Zonas, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo4Zonas, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo4Zonas, String> ubicacionValor;
	public static volatile SingularAttribute<AuAnexo4Zonas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo4Zonas, Integer> ubicacionId;
	public static volatile SingularAttribute<AuAnexo4Zonas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuAnexo4Zonas, Integer> id;
	public static volatile SingularAttribute<AuAnexo4Zonas, String> nombre;
	public static volatile SingularAttribute<AuAnexo4Zonas, String> usuarioModifica;
	public static volatile ListAttribute<AuAnexo4Zonas, AuAnexo4Destinos> auAnexo4DestinosList;
	public static volatile SingularAttribute<AuAnexo4Zonas, Boolean> activo;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String UBICACION_VALOR = "ubicacionValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String UBICACION_ID = "ubicacionId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String AU_ANEXO4_DESTINOS_LIST = "auAnexo4DestinosList";
	public static final String ACTIVO = "activo";

}

