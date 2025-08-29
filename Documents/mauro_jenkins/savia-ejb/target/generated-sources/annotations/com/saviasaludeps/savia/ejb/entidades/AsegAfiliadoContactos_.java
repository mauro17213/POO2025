package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegAfiliadoContactos.class)
public abstract class AsegAfiliadoContactos_ {

	public static volatile SingularAttribute<AsegAfiliadoContactos, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AsegAfiliadoContactos, String> usuarioCrea;
	public static volatile SingularAttribute<AsegAfiliadoContactos, String> maeTipoContactoValor;
	public static volatile SingularAttribute<AsegAfiliadoContactos, String> terminalCrea;
	public static volatile SingularAttribute<AsegAfiliadoContactos, Integer> maeTipoContactoId;
	public static volatile SingularAttribute<AsegAfiliadoContactos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegAfiliadoContactos, Integer> id;
	public static volatile SingularAttribute<AsegAfiliadoContactos, String> observacion;
	public static volatile SingularAttribute<AsegAfiliadoContactos, String> numeroContacto;
	public static volatile SingularAttribute<AsegAfiliadoContactos, String> maeTipoContactoCodigo;
	public static volatile SingularAttribute<AsegAfiliadoContactos, Boolean> activo;

	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_CONTACTO_VALOR = "maeTipoContactoValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_CONTACTO_ID = "maeTipoContactoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String NUMERO_CONTACTO = "numeroContacto";
	public static final String MAE_TIPO_CONTACTO_CODIGO = "maeTipoContactoCodigo";
	public static final String ACTIVO = "activo";

}

