package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeTelefonos.class)
public abstract class PeTelefonos_ {

	public static volatile SingularAttribute<PeTelefonos, String> terminalModifica;
	public static volatile SingularAttribute<PeTelefonos, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<PeTelefonos, Integer> tipo;
	public static volatile SingularAttribute<PeTelefonos, String> usuarioCrea;
	public static volatile SingularAttribute<PeTelefonos, String> numero;
	public static volatile SingularAttribute<PeTelefonos, String> terminalCrea;
	public static volatile SingularAttribute<PeTelefonos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeTelefonos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeTelefonos, Integer> id;
	public static volatile SingularAttribute<PeTelefonos, String> observacion;
	public static volatile SingularAttribute<PeTelefonos, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String TIPO = "tipo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String NUMERO = "numero";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

