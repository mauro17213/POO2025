package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaPaqueteTecnologias.class)
public abstract class MaPaqueteTecnologias_ {

	public static volatile SingularAttribute<MaPaqueteTecnologias, MaTecnologias> maTecnologiasId;
	public static volatile SingularAttribute<MaPaqueteTecnologias, MaInsumosIps> maInsumosIpsId;
	public static volatile SingularAttribute<MaPaqueteTecnologias, String> usuarioCrea;
	public static volatile SingularAttribute<MaPaqueteTecnologias, MaMedicamentos> maMedicamentosId;
	public static volatile SingularAttribute<MaPaqueteTecnologias, Integer> tipoTecnologia;
	public static volatile SingularAttribute<MaPaqueteTecnologias, String> terminalCrea;
	public static volatile SingularAttribute<MaPaqueteTecnologias, MaPaquetes> maPaquetesId;
	public static volatile SingularAttribute<MaPaqueteTecnologias, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaPaqueteTecnologias, Integer> id;

	public static final String MA_TECNOLOGIAS_ID = "maTecnologiasId";
	public static final String MA_INSUMOS_IPS_ID = "maInsumosIpsId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MA_MEDICAMENTOS_ID = "maMedicamentosId";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_PAQUETES_ID = "maPaquetesId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

