package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucAfiliadoContactos.class)
public abstract class AucAfiliadoContactos_ {

	public static volatile SingularAttribute<AucAfiliadoContactos, String> terminalModifica;
	public static volatile SingularAttribute<AucAfiliadoContactos, String> usuarioCrea;
	public static volatile SingularAttribute<AucAfiliadoContactos, String> terminalCrea;
	public static volatile SingularAttribute<AucAfiliadoContactos, AucAfiliados> aucAfiliadosId;
	public static volatile SingularAttribute<AucAfiliadoContactos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucAfiliadoContactos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AucAfiliadoContactos, Integer> id;
	public static volatile SingularAttribute<AucAfiliadoContactos, String> observacion;
	public static volatile SingularAttribute<AucAfiliadoContactos, String> usuarioModifica;
	public static volatile SingularAttribute<AucAfiliadoContactos, String> numeroContacto;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String AUC_AFILIADOS_ID = "aucAfiliadosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String NUMERO_CONTACTO = "numeroContacto";

}

