package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeProgramaDiagnosticos.class)
public abstract class PeProgramaDiagnosticos_ {

	public static volatile SingularAttribute<PeProgramaDiagnosticos, Boolean> direcciona;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, Date> fechaHoraBorra;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, String> usuarioBorra;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, String> terminalModifica;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, String> usuarioCrea;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, String> terminalCrea;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, Boolean> borrado;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, String> maDiagnosticoCodigo;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, Boolean> aplicaRescate;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, String> terminalBorra;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, Integer> id;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, Integer> maDiagnosticoId;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, String> usuarioModifica;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, String> maDiagnosticoValor;
	public static volatile SingularAttribute<PeProgramaDiagnosticos, Boolean> marcaAutomatico;

	public static final String DIRECCIONA = "direcciona";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String MA_DIAGNOSTICO_CODIGO = "maDiagnosticoCodigo";
	public static final String APLICA_RESCATE = "aplicaRescate";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String MA_DIAGNOSTICO_ID = "maDiagnosticoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String MA_DIAGNOSTICO_VALOR = "maDiagnosticoValor";
	public static final String MARCA_AUTOMATICO = "marcaAutomatico";

}

