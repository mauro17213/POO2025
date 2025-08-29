package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeVariablesValores.class)
public abstract class PeVariablesValores_ {

	public static volatile SingularAttribute<PeVariablesValores, String> descripcion;
	public static volatile ListAttribute<PeVariablesValores, PeVariablesValoresHistoricos> peVariablesValoresHistoricosList;
	public static volatile SingularAttribute<PeVariablesValores, Short> tipo;
	public static volatile SingularAttribute<PeVariablesValores, PeVariables> peVariablesId;
	public static volatile SingularAttribute<PeVariablesValores, PeAfiliadosProgramas> peAfiliadosProgramasId;
	public static volatile SingularAttribute<PeVariablesValores, String> valorTexto;
	public static volatile SingularAttribute<PeVariablesValores, String> nombre;
	public static volatile SingularAttribute<PeVariablesValores, Short> recurrencia;
	public static volatile SingularAttribute<PeVariablesValores, BigDecimal> valorDecimal;
	public static volatile SingularAttribute<PeVariablesValores, String> terminalModifica;
	public static volatile SingularAttribute<PeVariablesValores, String> usuarioCrea;
	public static volatile SingularAttribute<PeVariablesValores, Integer> valorEntero;
	public static volatile SingularAttribute<PeVariablesValores, Date> valorFecha;
	public static volatile SingularAttribute<PeVariablesValores, String> terminalCrea;
	public static volatile SingularAttribute<PeVariablesValores, PeCargasVariables> peCargasVariablesId;
	public static volatile SingularAttribute<PeVariablesValores, String> valorTextoLargo;
	public static volatile SingularAttribute<PeVariablesValores, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeVariablesValores, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeVariablesValores, Integer> id;
	public static volatile SingularAttribute<PeVariablesValores, String> usuarioModifica;
	public static volatile SingularAttribute<PeVariablesValores, Boolean> recurrente;

	public static final String DESCRIPCION = "descripcion";
	public static final String PE_VARIABLES_VALORES_HISTORICOS_LIST = "peVariablesValoresHistoricosList";
	public static final String TIPO = "tipo";
	public static final String PE_VARIABLES_ID = "peVariablesId";
	public static final String PE_AFILIADOS_PROGRAMAS_ID = "peAfiliadosProgramasId";
	public static final String VALOR_TEXTO = "valorTexto";
	public static final String NOMBRE = "nombre";
	public static final String RECURRENCIA = "recurrencia";
	public static final String VALOR_DECIMAL = "valorDecimal";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String VALOR_ENTERO = "valorEntero";
	public static final String VALOR_FECHA = "valorFecha";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String PE_CARGAS_VARIABLES_ID = "peCargasVariablesId";
	public static final String VALOR_TEXTO_LARGO = "valorTextoLargo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String RECURRENTE = "recurrente";

}

