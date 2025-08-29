package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaIss2001Tarifarios.class)
public abstract class MaIss2001Tarifarios_ {

	public static volatile SingularAttribute<MaIss2001Tarifarios, String> descripcion;
	public static volatile SingularAttribute<MaIss2001Tarifarios, String> codigo;
	public static volatile SingularAttribute<MaIss2001Tarifarios, Integer> tipo;
	public static volatile ListAttribute<MaIss2001Tarifarios, MaTecnologias> maTecnologiasList;
	public static volatile SingularAttribute<MaIss2001Tarifarios, Integer> uvr;
	public static volatile SingularAttribute<MaIss2001Tarifarios, String> terminalModifica;
	public static volatile SingularAttribute<MaIss2001Tarifarios, String> usuarioCrea;
	public static volatile SingularAttribute<MaIss2001Tarifarios, BigDecimal> monto;
	public static volatile SingularAttribute<MaIss2001Tarifarios, String> terminalCrea;
	public static volatile SingularAttribute<MaIss2001Tarifarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaIss2001Tarifarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaIss2001Tarifarios, Integer> id;
	public static volatile SingularAttribute<MaIss2001Tarifarios, String> referencia;
	public static volatile SingularAttribute<MaIss2001Tarifarios, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String CODIGO = "codigo";
	public static final String TIPO = "tipo";
	public static final String MA_TECNOLOGIAS_LIST = "maTecnologiasList";
	public static final String UVR = "uvr";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MONTO = "monto";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String REFERENCIA = "referencia";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

