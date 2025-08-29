package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsReglas.class)
public abstract class CmRipsReglas_ {

	public static volatile SingularAttribute<CmRipsReglas, String> descripcion;
	public static volatile SingularAttribute<CmRipsReglas, Integer> tipo;
	public static volatile SingularAttribute<CmRipsReglas, Integer> alerta;
	public static volatile SingularAttribute<CmRipsReglas, Date> fechaInicial;
	public static volatile SingularAttribute<CmRipsReglas, String> archivo;
	public static volatile SingularAttribute<CmRipsReglas, Date> fechaFinal;
	public static volatile SingularAttribute<CmRipsReglas, Integer> maeCntTipoContratoId;
	public static volatile SingularAttribute<CmRipsReglas, String> maeCntTipoContratoValor;
	public static volatile SingularAttribute<CmRipsReglas, String> maeCntTipoContratoCodigo;
	public static volatile SingularAttribute<CmRipsReglas, String> nombre;
	public static volatile SingularAttribute<CmRipsReglas, Boolean> activa;
	public static volatile SingularAttribute<CmRipsReglas, String> terminalModifica;
	public static volatile SingularAttribute<CmRipsReglas, String> usuarioCrea;
	public static volatile ListAttribute<CmRipsReglas, CmRipsReglaEntradas> cmRipsReglaEntradasList;
	public static volatile ListAttribute<CmRipsReglas, CmRipsReglaSalidas> cmRipsReglaSalidasList;
	public static volatile SingularAttribute<CmRipsReglas, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsReglas, byte[]> jsonRegla;
	public static volatile SingularAttribute<CmRipsReglas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsReglas, Short> bdEjecucion;
	public static volatile SingularAttribute<CmRipsReglas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmRipsReglas, Integer> id;
	public static volatile SingularAttribute<CmRipsReglas, Integer> orden;
	public static volatile SingularAttribute<CmRipsReglas, String> funcion;
	public static volatile SingularAttribute<CmRipsReglas, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String TIPO = "tipo";
	public static final String ALERTA = "alerta";
	public static final String FECHA_INICIAL = "fechaInicial";
	public static final String ARCHIVO = "archivo";
	public static final String FECHA_FINAL = "fechaFinal";
	public static final String MAE_CNT_TIPO_CONTRATO_ID = "maeCntTipoContratoId";
	public static final String MAE_CNT_TIPO_CONTRATO_VALOR = "maeCntTipoContratoValor";
	public static final String MAE_CNT_TIPO_CONTRATO_CODIGO = "maeCntTipoContratoCodigo";
	public static final String NOMBRE = "nombre";
	public static final String ACTIVA = "activa";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CM_RIPS_REGLA_ENTRADAS_LIST = "cmRipsReglaEntradasList";
	public static final String CM_RIPS_REGLA_SALIDAS_LIST = "cmRipsReglaSalidasList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String JSON_REGLA = "jsonRegla";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String BD_EJECUCION = "bdEjecucion";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ORDEN = "orden";
	public static final String FUNCION = "funcion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

