package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatPantallas.class)
public abstract class GatPantallas_ {

	public static volatile SingularAttribute<GatPantallas, Integer> maeTipoServicioId;
	public static volatile SingularAttribute<GatPantallas, String> maeTipoServicioValor;
	public static volatile SingularAttribute<GatPantallas, GnSedes> gnSedesId;
	public static volatile SingularAttribute<GatPantallas, String> terminalModifica;
	public static volatile SingularAttribute<GatPantallas, String> usuarioCrea;
	public static volatile SingularAttribute<GatPantallas, String> idSesion;
	public static volatile SingularAttribute<GatPantallas, String> terminalCrea;
	public static volatile SingularAttribute<GatPantallas, Boolean> cuenta;
	public static volatile SingularAttribute<GatPantallas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatPantallas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GatPantallas, Integer> id;
	public static volatile SingularAttribute<GatPantallas, String> maeTipoServicioCodigo;
	public static volatile SingularAttribute<GatPantallas, String> usuarioModifica;
	public static volatile SingularAttribute<GatPantallas, Boolean> activo;

	public static final String MAE_TIPO_SERVICIO_ID = "maeTipoServicioId";
	public static final String MAE_TIPO_SERVICIO_VALOR = "maeTipoServicioValor";
	public static final String GN_SEDES_ID = "gnSedesId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ID_SESION = "idSesion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CUENTA = "cuenta";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_SERVICIO_CODIGO = "maeTipoServicioCodigo";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

