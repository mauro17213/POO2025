package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsReglaSalidas.class)
public abstract class CmRipsReglaSalidas_ {

	public static volatile SingularAttribute<CmRipsReglaSalidas, String> descripcion;
	public static volatile SingularAttribute<CmRipsReglaSalidas, String> terminalModifica;
	public static volatile SingularAttribute<CmRipsReglaSalidas, Integer> codigo;
	public static volatile SingularAttribute<CmRipsReglaSalidas, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsReglaSalidas, CmRipsReglas> cmRipsReglasId;
	public static volatile SingularAttribute<CmRipsReglaSalidas, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsReglaSalidas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsReglaSalidas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmRipsReglaSalidas, Integer> id;
	public static volatile SingularAttribute<CmRipsReglaSalidas, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String CODIGO = "codigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CM_RIPS_REGLAS_ID = "cmRipsReglasId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

