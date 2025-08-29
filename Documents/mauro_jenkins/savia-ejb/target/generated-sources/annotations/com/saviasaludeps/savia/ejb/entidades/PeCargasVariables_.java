package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeCargasVariables.class)
public abstract class PeCargasVariables_ {

	public static volatile ListAttribute<PeCargasVariables, PeVariablesValoresHistoricos> peVariablesValoresHistoricosList;
	public static volatile SingularAttribute<PeCargasVariables, Integer> estado;
	public static volatile SingularAttribute<PeCargasVariables, String> ruta;
	public static volatile SingularAttribute<PeCargasVariables, String> respNombre;
	public static volatile SingularAttribute<PeCargasVariables, String> respRuta;
	public static volatile SingularAttribute<PeCargasVariables, Integer> registros;
	public static volatile SingularAttribute<PeCargasVariables, Date> fechaHoraInicio;
	public static volatile SingularAttribute<PeCargasVariables, String> nombre;
	public static volatile SingularAttribute<PeCargasVariables, String> usuarioCrea;
	public static volatile SingularAttribute<PeCargasVariables, String> terminalCrea;
	public static volatile SingularAttribute<PeCargasVariables, Integer> fallidos;
	public static volatile SingularAttribute<PeCargasVariables, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeCargasVariables, Integer> id;
	public static volatile SingularAttribute<PeCargasVariables, Boolean> respExiste;
	public static volatile SingularAttribute<PeCargasVariables, Date> fechaHoraFin;
	public static volatile SingularAttribute<PeCargasVariables, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<PeCargasVariables, Integer> exitosos;
	public static volatile SingularAttribute<PeCargasVariables, String> archivo;
	public static volatile SingularAttribute<PeCargasVariables, Integer> periodoCargue;
	public static volatile SingularAttribute<PeCargasVariables, String> respArchivo;
	public static volatile SingularAttribute<PeCargasVariables, PeProgramas> peProgramasId;
	public static volatile SingularAttribute<PeCargasVariables, Boolean> existe;
	public static volatile SingularAttribute<PeCargasVariables, String> detalle;
	public static volatile SingularAttribute<PeCargasVariables, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile ListAttribute<PeCargasVariables, PeVariablesValores> peVariablesValoresList;

	public static final String PE_VARIABLES_VALORES_HISTORICOS_LIST = "peVariablesValoresHistoricosList";
	public static final String ESTADO = "estado";
	public static final String RUTA = "ruta";
	public static final String RESP_NOMBRE = "respNombre";
	public static final String RESP_RUTA = "respRuta";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FALLIDOS = "fallidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String RESP_EXISTE = "respExiste";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String EXITOSOS = "exitosos";
	public static final String ARCHIVO = "archivo";
	public static final String PERIODO_CARGUE = "periodoCargue";
	public static final String RESP_ARCHIVO = "respArchivo";
	public static final String PE_PROGRAMAS_ID = "peProgramasId";
	public static final String EXISTE = "existe";
	public static final String DETALLE = "detalle";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String PE_VARIABLES_VALORES_LIST = "peVariablesValoresList";

}

