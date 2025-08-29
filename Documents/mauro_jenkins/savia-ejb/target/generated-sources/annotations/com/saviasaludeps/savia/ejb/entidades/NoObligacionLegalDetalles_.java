package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NoObligacionLegalDetalles.class)
public abstract class NoObligacionLegalDetalles_ {

	public static volatile SingularAttribute<NoObligacionLegalDetalles, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, String> maTecnologiaValor;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, Integer> maTecnologiaId;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, String> codigoPropio;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, NoObligacionesLegales> noObligacionesLegalesId;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, String> indicador;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, String> terminalModifica;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, String> usuarioCrea;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, Integer> tipoTecnologia;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, String> terminalCrea;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, Date> fechaHoraCrea;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, Date> fechaHoraModifica;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, Integer> id;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, String> usuarioModifica;
	public static volatile SingularAttribute<NoObligacionLegalDetalles, Boolean> activo;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String CODIGO_PROPIO = "codigoPropio";
	public static final String NO_OBLIGACIONES_LEGALES_ID = "noObligacionesLegalesId";
	public static final String INDICADOR = "indicador";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TIPO_TECNOLOGIA = "tipoTecnologia";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

