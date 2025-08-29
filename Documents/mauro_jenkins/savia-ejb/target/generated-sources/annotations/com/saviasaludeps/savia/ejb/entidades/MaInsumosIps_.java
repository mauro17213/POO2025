package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaInsumosIps.class)
public abstract class MaInsumosIps_ {

	public static volatile SingularAttribute<MaInsumosIps, String> descripcion;
	public static volatile SingularAttribute<MaInsumosIps, String> codigo;
	public static volatile SingularAttribute<MaInsumosIps, String> abreviatura;
	public static volatile SingularAttribute<MaInsumosIps, String> maeTipoCodigo;
	public static volatile SingularAttribute<MaInsumosIps, Integer> cntPrestadorSedesId;
	public static volatile SingularAttribute<MaInsumosIps, String> terminalModifica;
	public static volatile SingularAttribute<MaInsumosIps, String> usuarioCrea;
	public static volatile SingularAttribute<MaInsumosIps, String> terminalCrea;
	public static volatile ListAttribute<MaInsumosIps, MaPaqueteTecnologias> maPaqueteTecnologiasList;
	public static volatile SingularAttribute<MaInsumosIps, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaInsumosIps, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaInsumosIps, Integer> id;
	public static volatile SingularAttribute<MaInsumosIps, Integer> maeTipoId;
	public static volatile SingularAttribute<MaInsumosIps, String> maeTipoValor;
	public static volatile SingularAttribute<MaInsumosIps, String> usuarioModifica;
	public static volatile SingularAttribute<MaInsumosIps, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String CODIGO = "codigo";
	public static final String ABREVIATURA = "abreviatura";
	public static final String MAE_TIPO_CODIGO = "maeTipoCodigo";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_PAQUETE_TECNOLOGIAS_LIST = "maPaqueteTecnologiasList";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_ID = "maeTipoId";
	public static final String MAE_TIPO_VALOR = "maeTipoValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

