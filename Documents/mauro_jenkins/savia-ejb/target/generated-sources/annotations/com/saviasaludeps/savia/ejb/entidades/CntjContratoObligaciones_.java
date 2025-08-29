package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjContratoObligaciones.class)
public abstract class CntjContratoObligaciones_ {

	public static volatile SingularAttribute<CntjContratoObligaciones, String> descripcion;
	public static volatile SingularAttribute<CntjContratoObligaciones, String> terminalModifica;
	public static volatile SingularAttribute<CntjContratoObligaciones, String> usuarioCrea;
	public static volatile SingularAttribute<CntjContratoObligaciones, String> terminalCrea;
	public static volatile SingularAttribute<CntjContratoObligaciones, Integer> numeroObligacion;
	public static volatile SingularAttribute<CntjContratoObligaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjContratoObligaciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjContratoObligaciones, Integer> id;
	public static volatile SingularAttribute<CntjContratoObligaciones, CntjContratos> cntjContratosId;
	public static volatile SingularAttribute<CntjContratoObligaciones, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String NUMERO_OBLIGACION = "numeroObligacion";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String CNTJ_CONTRATOS_ID = "cntjContratosId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

