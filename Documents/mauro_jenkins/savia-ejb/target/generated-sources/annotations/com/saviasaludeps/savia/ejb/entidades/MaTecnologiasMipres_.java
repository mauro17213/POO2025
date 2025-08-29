package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaTecnologiasMipres.class)
public abstract class MaTecnologiasMipres_ {

	public static volatile SingularAttribute<MaTecnologiasMipres, String> descripcion;
	public static volatile SingularAttribute<MaTecnologiasMipres, MaTecnologias> maTecnologiasId;
	public static volatile SingularAttribute<MaTecnologiasMipres, String> terminalModifica;
	public static volatile SingularAttribute<MaTecnologiasMipres, String> usuarioCrea;
	public static volatile SingularAttribute<MaTecnologiasMipres, String> terminalCrea;
	public static volatile SingularAttribute<MaTecnologiasMipres, String> codigoMipres;
	public static volatile SingularAttribute<MaTecnologiasMipres, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaTecnologiasMipres, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaTecnologiasMipres, MpCodigoInsumos> insumosMipresId;
	public static volatile SingularAttribute<MaTecnologiasMipres, Integer> id;
	public static volatile SingularAttribute<MaTecnologiasMipres, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String MA_TECNOLOGIAS_ID = "maTecnologiasId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CODIGO_MIPRES = "codigoMipres";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String INSUMOS_MIPRES_ID = "insumosMipresId";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

