package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuMemorialFirmas.class)
public abstract class TuMemorialFirmas_ {

	public static volatile SingularAttribute<TuMemorialFirmas, String> terminalModifica;
	public static volatile SingularAttribute<TuMemorialFirmas, String> usuarioCrea;
	public static volatile SingularAttribute<TuMemorialFirmas, String> terminalCrea;
	public static volatile SingularAttribute<TuMemorialFirmas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuMemorialFirmas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuMemorialFirmas, Integer> id;
	public static volatile SingularAttribute<TuMemorialFirmas, TuMemorialPersonas> tuMemorialPersonaId;
	public static volatile SingularAttribute<TuMemorialFirmas, byte[]> firma;
	public static volatile SingularAttribute<TuMemorialFirmas, String> nombre;
	public static volatile SingularAttribute<TuMemorialFirmas, String> usuarioModifica;

	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String TU_MEMORIAL_PERSONA_ID = "tuMemorialPersonaId";
	public static final String FIRMA = "firma";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

