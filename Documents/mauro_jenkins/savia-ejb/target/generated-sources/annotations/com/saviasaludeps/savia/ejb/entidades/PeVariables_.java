package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeVariables.class)
public abstract class PeVariables_ {

	public static volatile SingularAttribute<PeVariables, String> descripcion;
	public static volatile ListAttribute<PeVariables, PeVariablesValoresHistoricos> peVariablesValoresHistoricosList;
	public static volatile SingularAttribute<PeVariables, Short> tipo;
	public static volatile SingularAttribute<PeVariables, Boolean> editable;
	public static volatile SingularAttribute<PeVariables, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<PeVariables, BigDecimal> operacion;
	public static volatile SingularAttribute<PeVariables, String> nombre;
	public static volatile SingularAttribute<PeVariables, Boolean> activa;
	public static volatile SingularAttribute<PeVariables, String> terminalModifica;
	public static volatile ListAttribute<PeVariables, PeVariablesValores> peVariablesValoresList;
	public static volatile SingularAttribute<PeVariables, String> usuarioCrea;
	public static volatile SingularAttribute<PeVariables, String> validacion;
	public static volatile SingularAttribute<PeVariables, String> terminalCrea;
	public static volatile SingularAttribute<PeVariables, String> insumoCalculo;
	public static volatile SingularAttribute<PeVariables, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeVariables, Boolean> obligatoria;
	public static volatile SingularAttribute<PeVariables, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeVariables, Integer> id;
	public static volatile SingularAttribute<PeVariables, String> usuarioModifica;
	public static volatile SingularAttribute<PeVariables, Boolean> recurrente;

	public static final String DESCRIPCION = "descripcion";
	public static final String PE_VARIABLES_VALORES_HISTORICOS_LIST = "peVariablesValoresHistoricosList";
	public static final String TIPO = "tipo";
	public static final String EDITABLE = "editable";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String OPERACION = "operacion";
	public static final String NOMBRE = "nombre";
	public static final String ACTIVA = "activa";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String PE_VARIABLES_VALORES_LIST = "peVariablesValoresList";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String VALIDACION = "validacion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String INSUMO_CALCULO = "insumoCalculo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String OBLIGATORIA = "obligatoria";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String RECURRENTE = "recurrente";

}

