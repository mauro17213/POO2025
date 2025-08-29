package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaAgrupadoresMedicamento.class)
public abstract class MaAgrupadoresMedicamento_ {

	public static volatile SingularAttribute<MaAgrupadoresMedicamento, String> terminalModifica;
	public static volatile SingularAttribute<MaAgrupadoresMedicamento, String> codigo;
	public static volatile SingularAttribute<MaAgrupadoresMedicamento, String> usuarioCrea;
	public static volatile ListAttribute<MaAgrupadoresMedicamento, MaMedicamentosHistoricos> maMedicamentosHistoricosList;
	public static volatile SingularAttribute<MaAgrupadoresMedicamento, String> terminalCrea;
	public static volatile SingularAttribute<MaAgrupadoresMedicamento, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaAgrupadoresMedicamento, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaAgrupadoresMedicamento, Integer> id;
	public static volatile SingularAttribute<MaAgrupadoresMedicamento, String> nombre;
	public static volatile SingularAttribute<MaAgrupadoresMedicamento, String> usuarioModifica;
	public static volatile ListAttribute<MaAgrupadoresMedicamento, MaMedicamentos> maMedicamentosList;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CODIGO = "codigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MA_MEDICAMENTOS_HISTORICOS_LIST = "maMedicamentosHistoricosList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String MA_MEDICAMENTOS_LIST = "maMedicamentosList";

}

