package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntProfesionalPrestadores.class)
public abstract class CntProfesionalPrestadores_ {

	public static volatile SingularAttribute<CntProfesionalPrestadores, String> maEspecialidadValor;
	public static volatile SingularAttribute<CntProfesionalPrestadores, CntProfesionales> cntProfesionalesId;
	public static volatile SingularAttribute<CntProfesionalPrestadores, String> terminalModifica;
	public static volatile SingularAttribute<CntProfesionalPrestadores, String> usuarioCrea;
	public static volatile SingularAttribute<CntProfesionalPrestadores, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CntProfesionalPrestadores, String> terminalCrea;
	public static volatile SingularAttribute<CntProfesionalPrestadores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntProfesionalPrestadores, Integer> maEspecialidadId;
	public static volatile SingularAttribute<CntProfesionalPrestadores, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntProfesionalPrestadores, Integer> id;
	public static volatile SingularAttribute<CntProfesionalPrestadores, String> maEspecialidadCodigo;
	public static volatile SingularAttribute<CntProfesionalPrestadores, String> usuarioModifica;
	public static volatile SingularAttribute<CntProfesionalPrestadores, Boolean> activo;

	public static final String MA_ESPECIALIDAD_VALOR = "maEspecialidadValor";
	public static final String CNT_PROFESIONALES_ID = "cntProfesionalesId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MA_ESPECIALIDAD_ID = "maEspecialidadId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MA_ESPECIALIDAD_CODIGO = "maEspecialidadCodigo";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

