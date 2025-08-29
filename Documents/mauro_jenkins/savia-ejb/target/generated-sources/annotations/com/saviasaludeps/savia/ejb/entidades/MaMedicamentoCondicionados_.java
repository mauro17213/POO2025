package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaMedicamentoCondicionados.class)
public abstract class MaMedicamentoCondicionados_ {

	public static volatile SingularAttribute<MaMedicamentoCondicionados, String> codigo;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, MaMedicamentos> maMedicamentosId;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, Integer> maeGeneroId;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, String> maeGeneroValor;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, String> nombre;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, Integer> edadMaxima;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, String> maeGeneroCodigo;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, String> usuarioCrea;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, String> terminalCrea;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, MaDiagnosticos> maDiagnosticosId;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, Integer> id;
	public static volatile SingularAttribute<MaMedicamentoCondicionados, Integer> edadMinima;

	public static final String CODIGO = "codigo";
	public static final String MA_MEDICAMENTOS_ID = "maMedicamentosId";
	public static final String MAE_GENERO_ID = "maeGeneroId";
	public static final String MAE_GENERO_VALOR = "maeGeneroValor";
	public static final String NOMBRE = "nombre";
	public static final String EDAD_MAXIMA = "edadMaxima";
	public static final String MAE_GENERO_CODIGO = "maeGeneroCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MA_DIAGNOSTICOS_ID = "maDiagnosticosId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String EDAD_MINIMA = "edadMinima";

}

