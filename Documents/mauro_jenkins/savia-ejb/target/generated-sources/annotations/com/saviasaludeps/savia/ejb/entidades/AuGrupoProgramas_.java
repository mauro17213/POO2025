package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuGrupoProgramas.class)
public abstract class AuGrupoProgramas_ {

	public static volatile SingularAttribute<AuGrupoProgramas, AuGrupos> auGruposId;
	public static volatile SingularAttribute<AuGrupoProgramas, String> terminalModifica;
	public static volatile SingularAttribute<AuGrupoProgramas, String> usuarioCrea;
	public static volatile SingularAttribute<AuGrupoProgramas, String> terminalCrea;
	public static volatile SingularAttribute<AuGrupoProgramas, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<AuGrupoProgramas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuGrupoProgramas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuGrupoProgramas, Integer> id;
	public static volatile SingularAttribute<AuGrupoProgramas, String> usuarioModifica;

	public static final String AU_GRUPOS_ID = "auGruposId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

