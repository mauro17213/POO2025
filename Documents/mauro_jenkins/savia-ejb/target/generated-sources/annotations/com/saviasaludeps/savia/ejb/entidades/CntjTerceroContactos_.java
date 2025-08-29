package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjTerceroContactos.class)
public abstract class CntjTerceroContactos_ {

	public static volatile SingularAttribute<CntjTerceroContactos, String> contacto;
	public static volatile SingularAttribute<CntjTerceroContactos, String> maeTipoContactoCodigo;
	public static volatile SingularAttribute<CntjTerceroContactos, String> terminalModifica;
	public static volatile SingularAttribute<CntjTerceroContactos, CntjTerceros> cntjTercerosId;
	public static volatile SingularAttribute<CntjTerceroContactos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjTerceroContactos, String> maeTipoContactoValor;
	public static volatile SingularAttribute<CntjTerceroContactos, String> terminalCrea;
	public static volatile SingularAttribute<CntjTerceroContactos, Integer> maeTipoContactoId;
	public static volatile SingularAttribute<CntjTerceroContactos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjTerceroContactos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjTerceroContactos, Integer> id;
	public static volatile SingularAttribute<CntjTerceroContactos, String> observacion;
	public static volatile SingularAttribute<CntjTerceroContactos, String> usuarioModifica;
	public static volatile SingularAttribute<CntjTerceroContactos, Boolean> activo;

	public static final String CONTACTO = "contacto";
	public static final String MAE_TIPO_CONTACTO_CODIGO = "maeTipoContactoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CNTJ_TERCEROS_ID = "cntjTercerosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_CONTACTO_VALOR = "maeTipoContactoValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_CONTACTO_ID = "maeTipoContactoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

