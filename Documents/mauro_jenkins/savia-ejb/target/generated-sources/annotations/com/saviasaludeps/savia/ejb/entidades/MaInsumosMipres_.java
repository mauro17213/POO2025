package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaInsumosMipres.class)
public abstract class MaInsumosMipres_ {

	public static volatile SingularAttribute<MaInsumosMipres, String> terminalModifica;
	public static volatile SingularAttribute<MaInsumosMipres, MaInsumos> maInsumosId;
	public static volatile SingularAttribute<MaInsumosMipres, String> usuarioCrea;
	public static volatile SingularAttribute<MaInsumosMipres, String> descripcionMipres;
	public static volatile SingularAttribute<MaInsumosMipres, String> terminalCrea;
	public static volatile SingularAttribute<MaInsumosMipres, String> codigoMipres;
	public static volatile SingularAttribute<MaInsumosMipres, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaInsumosMipres, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaInsumosMipres, MpCodigoInsumos> insumosMipresId;
	public static volatile SingularAttribute<MaInsumosMipres, Integer> id;
	public static volatile SingularAttribute<MaInsumosMipres, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String MA_INSUMOS_ID = "maInsumosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String DESCRIPCION_MIPRES = "descripcionMipres";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CODIGO_MIPRES = "codigoMipres";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String INSUMOS_MIPRES_ID = "insumosMipresId";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

