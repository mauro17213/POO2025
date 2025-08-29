package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaPaquetesMipres.class)
public abstract class MaPaquetesMipres_ {

	public static volatile SingularAttribute<MaPaquetesMipres, String> terminalModifica;
	public static volatile SingularAttribute<MaPaquetesMipres, String> usuarioCrea;
	public static volatile SingularAttribute<MaPaquetesMipres, String> descripcionMipres;
	public static volatile SingularAttribute<MaPaquetesMipres, String> terminalCrea;
	public static volatile SingularAttribute<MaPaquetesMipres, MpCodigoInsumos> mpCodigoInsumosId;
	public static volatile SingularAttribute<MaPaquetesMipres, MaPaquetes> maPaquetesId;
	public static volatile SingularAttribute<MaPaquetesMipres, String> codigoMipres;
	public static volatile SingularAttribute<MaPaquetesMipres, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaPaquetesMipres, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaPaquetesMipres, Integer> id;
	public static volatile SingularAttribute<MaPaquetesMipres, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String DESCRIPCION_MIPRES = "descripcionMipres";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MP_CODIGO_INSUMOS_ID = "mpCodigoInsumosId";
	public static final String MA_PAQUETES_ID = "maPaquetesId";
	public static final String CODIGO_MIPRES = "codigoMipres";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

