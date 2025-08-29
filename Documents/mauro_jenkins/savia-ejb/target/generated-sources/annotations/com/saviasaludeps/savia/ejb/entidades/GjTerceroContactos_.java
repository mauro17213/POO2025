package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GjTerceroContactos.class)
public abstract class GjTerceroContactos_ {

	public static volatile SingularAttribute<GjTerceroContactos, String> contacto;
	public static volatile SingularAttribute<GjTerceroContactos, String> maeTipoContactoCodigo;
	public static volatile SingularAttribute<GjTerceroContactos, String> terminalModifica;
	public static volatile SingularAttribute<GjTerceroContactos, String> usuarioCrea;
	public static volatile SingularAttribute<GjTerceroContactos, String> maeTipoContactoValor;
	public static volatile SingularAttribute<GjTerceroContactos, String> terminalCrea;
	public static volatile SingularAttribute<GjTerceroContactos, GjTerceros> gjTercerosId;
	public static volatile SingularAttribute<GjTerceroContactos, Integer> maeTipoContactoId;
	public static volatile SingularAttribute<GjTerceroContactos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GjTerceroContactos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GjTerceroContactos, Integer> id;
	public static volatile SingularAttribute<GjTerceroContactos, String> observacion;
	public static volatile SingularAttribute<GjTerceroContactos, String> usuarioModifica;
	public static volatile SingularAttribute<GjTerceroContactos, Boolean> activo;

	public static final String CONTACTO = "contacto";
	public static final String MAE_TIPO_CONTACTO_CODIGO = "maeTipoContactoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_CONTACTO_VALOR = "maeTipoContactoValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GJ_TERCEROS_ID = "gjTercerosId";
	public static final String MAE_TIPO_CONTACTO_ID = "maeTipoContactoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

