package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeDireccionadoItems.class)
public abstract class PeDireccionadoItems_ {

	public static volatile SingularAttribute<PeDireccionadoItems, String> terminalModifica;
	public static volatile SingularAttribute<PeDireccionadoItems, Integer> estado;
	public static volatile SingularAttribute<PeDireccionadoItems, String> usuarioCrea;
	public static volatile SingularAttribute<PeDireccionadoItems, String> terminalCrea;
	public static volatile SingularAttribute<PeDireccionadoItems, AuAnexo3Items> auAnexo3ItemsId;
	public static volatile SingularAttribute<PeDireccionadoItems, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeDireccionadoItems, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeDireccionadoItems, Integer> id;
	public static volatile SingularAttribute<PeDireccionadoItems, Date> fechaPrestacion;
	public static volatile SingularAttribute<PeDireccionadoItems, PeDireccionados> peDireccionadosId;
	public static volatile SingularAttribute<PeDireccionadoItems, String> observacion;
	public static volatile SingularAttribute<PeDireccionadoItems, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AU_ANEXO3_ITEMS_ID = "auAnexo3ItemsId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_PRESTACION = "fechaPrestacion";
	public static final String PE_DIRECCIONADOS_ID = "peDireccionadosId";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

