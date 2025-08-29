package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AntAnticipoItems.class)
public abstract class AntAnticipoItems_ {

	public static volatile SingularAttribute<AntAnticipoItems, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<AntAnticipoItems, Date> fechaHoraBorra;
	public static volatile ListAttribute<AntAnticipoItems, AntAnticipoValores> antAnticipoValoresList;
	public static volatile SingularAttribute<AntAnticipoItems, Integer> maMedicamentoId;
	public static volatile SingularAttribute<AntAnticipoItems, String> maTecnologiaValor;
	public static volatile SingularAttribute<AntAnticipoItems, String> usuarioBorra;
	public static volatile SingularAttribute<AntAnticipoItems, String> maMedicamentoCodigo;
	public static volatile SingularAttribute<AntAnticipoItems, AntAnticipos> antAnticiposId;
	public static volatile SingularAttribute<AntAnticipoItems, Integer> maTecnologiaId;
	public static volatile SingularAttribute<AntAnticipoItems, BigDecimal> valorTecnologiaPagada;
	public static volatile SingularAttribute<AntAnticipoItems, String> borradoObservacion;
	public static volatile SingularAttribute<AntAnticipoItems, String> maMedicamentoValor;
	public static volatile SingularAttribute<AntAnticipoItems, String> mpNumeroPrescripcion;
	public static volatile SingularAttribute<AntAnticipoItems, String> usuarioCrea;
	public static volatile SingularAttribute<AntAnticipoItems, Integer> tipoTecnologia;
	public static volatile SingularAttribute<AntAnticipoItems, String> terminalCrea;
	public static volatile SingularAttribute<AntAnticipoItems, Boolean> borrado;
	public static volatile SingularAttribute<AntAnticipoItems, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AntAnticipoItems, String> terminalBorra;
	public static volatile SingularAttribute<AntAnticipoItems, Integer> id;
	public static volatile SingularAttribute<AntAnticipoItems, Integer> cantidad;
	public static volatile SingularAttribute<AntAnticipoItems, BigDecimal> valorTecnologiaCotizada;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String ANT_ANTICIPO_VALORES_LIST = "antAnticipoValoresList";
	public static final String MA_MEDICAMENTO_ID = "maMedicamentoId";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String MA_MEDICAMENTO_CODIGO = "maMedicamentoCodigo";
	public static final String ANT_ANTICIPOS_ID = "antAnticiposId";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String VALOR_TECNOLOGIA_PAGADA = "valorTecnologiaPagada";
	public static final String BORRADO_OBSERVACION = "borradoObservacion";
	public static final String MA_MEDICAMENTO_VALOR = "maMedicamentoValor";
	public static final String MP_NUMERO_PRESCRIPCION = "mpNumeroPrescripcion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String CANTIDAD = "cantidad";
	public static final String VALOR_TECNOLOGIA_COTIZADA = "valorTecnologiaCotizada";

}

