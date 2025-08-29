package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeProgramaTecnologias.class)
public abstract class PeProgramaTecnologias_ {

	public static volatile SingularAttribute<PeProgramaTecnologias, Boolean> direcciona;
	public static volatile SingularAttribute<PeProgramaTecnologias, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<PeProgramaTecnologias, Date> fechaHoraBorra;
	public static volatile SingularAttribute<PeProgramaTecnologias, String> maTecnologiaValor;
	public static volatile SingularAttribute<PeProgramaTecnologias, String> usuarioBorra;
	public static volatile SingularAttribute<PeProgramaTecnologias, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<PeProgramaTecnologias, Integer> maTecnologiaId;
	public static volatile SingularAttribute<PeProgramaTecnologias, String> terminalModifica;
	public static volatile SingularAttribute<PeProgramaTecnologias, String> usuarioCrea;
	public static volatile SingularAttribute<PeProgramaTecnologias, Short> tipoTecnologia;
	public static volatile SingularAttribute<PeProgramaTecnologias, String> terminalCrea;
	public static volatile SingularAttribute<PeProgramaTecnologias, Boolean> borrado;
	public static volatile SingularAttribute<PeProgramaTecnologias, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeProgramaTecnologias, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeProgramaTecnologias, String> terminalBorra;
	public static volatile SingularAttribute<PeProgramaTecnologias, Integer> id;
	public static volatile SingularAttribute<PeProgramaTecnologias, String> usuarioModifica;
	public static volatile SingularAttribute<PeProgramaTecnologias, Boolean> marcaAutomatico;

	public static final String DIRECCIONA = "direcciona";
	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String MARCA_AUTOMATICO = "marcaAutomatico";

}

