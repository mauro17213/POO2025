package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaSoatTarifarios.class)
public abstract class MaSoatTarifarios_ {

	public static volatile SingularAttribute<MaSoatTarifarios, String> descripcion;
	public static volatile SingularAttribute<MaSoatTarifarios, String> codigo;
	public static volatile SingularAttribute<MaSoatTarifarios, Integer> tipo;
	public static volatile ListAttribute<MaSoatTarifarios, MaTecnologias> maTecnologiasList;
	public static volatile SingularAttribute<MaSoatTarifarios, Integer> grupo;
	public static volatile SingularAttribute<MaSoatTarifarios, Integer> agno;
	public static volatile SingularAttribute<MaSoatTarifarios, BigDecimal> puntos;
	public static volatile ListAttribute<MaSoatTarifarios, MaSoatTarifarioValores> maSoatTarifarioValoresList;
	public static volatile SingularAttribute<MaSoatTarifarios, String> terminalModifica;
	public static volatile SingularAttribute<MaSoatTarifarios, String> usuarioCrea;
	public static volatile SingularAttribute<MaSoatTarifarios, String> terminalCrea;
	public static volatile SingularAttribute<MaSoatTarifarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaSoatTarifarios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaSoatTarifarios, Integer> id;
	public static volatile SingularAttribute<MaSoatTarifarios, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String CODIGO = "codigo";
	public static final String TIPO = "tipo";
	public static final String MA_TECNOLOGIAS_LIST = "maTecnologiasList";
	public static final String GRUPO = "grupo";
	public static final String AGNO = "agno";
	public static final String PUNTOS = "puntos";
	public static final String MA_SOAT_TARIFARIO_VALORES_LIST = "maSoatTarifarioValoresList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

