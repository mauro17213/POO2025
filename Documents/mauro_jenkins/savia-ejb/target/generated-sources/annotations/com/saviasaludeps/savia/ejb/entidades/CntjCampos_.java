package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjCampos.class)
public abstract class CntjCampos_ {

	public static volatile SingularAttribute<CntjCampos, String> descripcion;
	public static volatile SingularAttribute<CntjCampos, String> campoDestino;
	public static volatile SingularAttribute<CntjCampos, Boolean> aplicaMaestro;
	public static volatile SingularAttribute<CntjCampos, String> nombre;
	public static volatile SingularAttribute<CntjCampos, String> campoReferencia;
	public static volatile SingularAttribute<CntjCampos, String> etiqueta;
	public static volatile SingularAttribute<CntjCampos, String> terminalModifica;
	public static volatile SingularAttribute<CntjCampos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjCampos, String> terminalCrea;
	public static volatile ListAttribute<CntjCampos, CntjPlantillaCampos> cntjPlantillaCamposList;
	public static volatile SingularAttribute<CntjCampos, String> valorReferencia;
	public static volatile SingularAttribute<CntjCampos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjCampos, CntjProcesos> cntjProcesosId;
	public static volatile SingularAttribute<CntjCampos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjCampos, Integer> id;
	public static volatile SingularAttribute<CntjCampos, String> valoresLista;
	public static volatile SingularAttribute<CntjCampos, Integer> tipoDato;
	public static volatile SingularAttribute<CntjCampos, Integer> maestro;
	public static volatile SingularAttribute<CntjCampos, Boolean> estatico;
	public static volatile SingularAttribute<CntjCampos, String> tablaDestino;
	public static volatile SingularAttribute<CntjCampos, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String CAMPO_DESTINO = "campoDestino";
	public static final String APLICA_MAESTRO = "aplicaMaestro";
	public static final String NOMBRE = "nombre";
	public static final String CAMPO_REFERENCIA = "campoReferencia";
	public static final String ETIQUETA = "etiqueta";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CNTJ_PLANTILLA_CAMPOS_LIST = "cntjPlantillaCamposList";
	public static final String VALOR_REFERENCIA = "valorReferencia";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String CNTJ_PROCESOS_ID = "cntjProcesosId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String VALORES_LISTA = "valoresLista";
	public static final String TIPO_DATO = "tipoDato";
	public static final String MAESTRO = "maestro";
	public static final String ESTATICO = "estatico";
	public static final String TABLA_DESTINO = "tablaDestino";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

