package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegRadicadoNovedades.class)
public abstract class AsegRadicadoNovedades_ {

	public static volatile SingularAttribute<AsegRadicadoNovedades, Boolean> soporteNovedad;
	public static volatile SingularAttribute<AsegRadicadoNovedades, String> terminalModifica;
	public static volatile SingularAttribute<AsegRadicadoNovedades, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AsegRadicadoNovedades, String> historicoAfiliado;
	public static volatile SingularAttribute<AsegRadicadoNovedades, String> usuarioCrea;
	public static volatile SingularAttribute<AsegRadicadoNovedades, String> terminalCrea;
	public static volatile SingularAttribute<AsegRadicadoNovedades, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegRadicadoNovedades, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AsegRadicadoNovedades, Integer> id;
	public static volatile ListAttribute<AsegRadicadoNovedades, AsegRegistroNovedades> asegRegistroNovedadesList;
	public static volatile SingularAttribute<AsegRadicadoNovedades, String> usuarioModifica;
	public static volatile ListAttribute<AsegRadicadoNovedades, AsegAdjuntos> asegAdjuntosList;

	public static final String SOPORTE_NOVEDAD = "soporteNovedad";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String HISTORICO_AFILIADO = "historicoAfiliado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ASEG_REGISTRO_NOVEDADES_LIST = "asegRegistroNovedadesList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ASEG_ADJUNTOS_LIST = "asegAdjuntosList";

}

