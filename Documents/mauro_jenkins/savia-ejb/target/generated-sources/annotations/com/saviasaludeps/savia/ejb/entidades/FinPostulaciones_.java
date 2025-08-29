package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FinPostulaciones.class)
public abstract class FinPostulaciones_ {

	public static volatile SingularAttribute<FinPostulaciones, BigDecimal> totalPagado;
	public static volatile SingularAttribute<FinPostulaciones, String> usuarioBorra;
	public static volatile SingularAttribute<FinPostulaciones, String> prestadorEstadoAdres;
	public static volatile SingularAttribute<FinPostulaciones, BigDecimal> valorEvento;
	public static volatile SingularAttribute<FinPostulaciones, BigDecimal> valorCapita;
	public static volatile SingularAttribute<FinPostulaciones, BigDecimal> valorCapitaReajuste;
	public static volatile SingularAttribute<FinPostulaciones, String> terminalModifica;
	public static volatile SingularAttribute<FinPostulaciones, String> usuarioCrea;
	public static volatile SingularAttribute<FinPostulaciones, String> prestadorDepartamento;
	public static volatile SingularAttribute<FinPostulaciones, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<FinPostulaciones, String> terminalCrea;
	public static volatile SingularAttribute<FinPostulaciones, BigDecimal> valorPgp;
	public static volatile SingularAttribute<FinPostulaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<FinPostulaciones, String> terminalBorra;
	public static volatile SingularAttribute<FinPostulaciones, Integer> id;
	public static volatile SingularAttribute<FinPostulaciones, String> prestadorNaturaleza;
	public static volatile SingularAttribute<FinPostulaciones, BigDecimal> valorCompromisos;
	public static volatile SingularAttribute<FinPostulaciones, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<FinPostulaciones, Date> fechaHoraBorra;
	public static volatile SingularAttribute<FinPostulaciones, BigDecimal> valorProgramadoTotal;
	public static volatile SingularAttribute<FinPostulaciones, Integer> tipoPostulacion;
	public static volatile SingularAttribute<FinPostulaciones, String> prestadorNit;
	public static volatile SingularAttribute<FinPostulaciones, String> prestadorRazonSocial;
	public static volatile SingularAttribute<FinPostulaciones, FinGiros> finGirosId;
	public static volatile SingularAttribute<FinPostulaciones, Boolean> borrado;
	public static volatile SingularAttribute<FinPostulaciones, String> prestadorMunicipio;
	public static volatile SingularAttribute<FinPostulaciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<FinPostulaciones, String> usuarioModifica;

	public static final String TOTAL_PAGADO = "totalPagado";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String PRESTADOR_ESTADO_ADRES = "prestadorEstadoAdres";
	public static final String VALOR_EVENTO = "valorEvento";
	public static final String VALOR_CAPITA = "valorCapita";
	public static final String VALOR_CAPITA_REAJUSTE = "valorCapitaReajuste";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String PRESTADOR_DEPARTAMENTO = "prestadorDepartamento";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR_PGP = "valorPgp";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String PRESTADOR_NATURALEZA = "prestadorNaturaleza";
	public static final String VALOR_COMPROMISOS = "valorCompromisos";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String VALOR_PROGRAMADO_TOTAL = "valorProgramadoTotal";
	public static final String TIPO_POSTULACION = "tipoPostulacion";
	public static final String PRESTADOR_NIT = "prestadorNit";
	public static final String PRESTADOR_RAZON_SOCIAL = "prestadorRazonSocial";
	public static final String FIN_GIROS_ID = "finGirosId";
	public static final String BORRADO = "borrado";
	public static final String PRESTADOR_MUNICIPIO = "prestadorMunicipio";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

