package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuSeguimientoAfiliadoContactos.class)
public abstract class AuSeguimientoAfiliadoContactos_ {

	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, Date> fechaHoraBorra;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, String> usuarioBorra;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, String> maeTipoContactoCodigo;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, String> terminalModifica;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, String> usuarioCrea;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, AuSeguimientoAfiliados> auSeguimientoAfiliadoId;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, String> maeTipoContactoValor;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, String> terminalCrea;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, Boolean> borrado;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, Integer> maeTipoContactoId;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, String> terminalBorra;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, Integer> id;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, String> observacion;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, String> usuarioModifica;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, String> numeroContacto;
	public static volatile SingularAttribute<AuSeguimientoAfiliadoContactos, Boolean> activo;

	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String MAE_TIPO_CONTACTO_CODIGO = "maeTipoContactoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String AU_SEGUIMIENTO_AFILIADO_ID = "auSeguimientoAfiliadoId";
	public static final String MAE_TIPO_CONTACTO_VALOR = "maeTipoContactoValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String MAE_TIPO_CONTACTO_ID = "maeTipoContactoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String NUMERO_CONTACTO = "numeroContacto";
	public static final String ACTIVO = "activo";

}

