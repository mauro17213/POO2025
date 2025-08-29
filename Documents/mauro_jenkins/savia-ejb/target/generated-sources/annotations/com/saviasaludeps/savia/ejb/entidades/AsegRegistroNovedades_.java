package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AsegRegistroNovedades.class)
public abstract class AsegRegistroNovedades_ {

	public static volatile SingularAttribute<AsegRegistroNovedades, AsegReporteNovedades> asegReporteNovedadesId;
	public static volatile SingularAttribute<AsegRegistroNovedades, Date> fechaMarcacion;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> descripcionOrigenUltimoRegistro;
	public static volatile SingularAttribute<AsegRegistroNovedades, Integer> origenUltimoRegistro;
	public static volatile SingularAttribute<AsegRegistroNovedades, Integer> asegInformesIdMarcacion;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> descripcionValorNuevo;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> valorAnterior;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> maeEstadoNovedad;
	public static volatile SingularAttribute<AsegRegistroNovedades, AsegMaNovedades> asegMaNovedadesId;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> terminalModifica;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> usuarioCrea;
	public static volatile SingularAttribute<AsegRegistroNovedades, Integer> idAfiliado;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> terminalCrea;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> valorNuevo;
	public static volatile SingularAttribute<AsegRegistroNovedades, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AsegRegistroNovedades, Integer> sincronizado;
	public static volatile SingularAttribute<AsegRegistroNovedades, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AsegRegistroNovedades, Integer> id;
	public static volatile SingularAttribute<AsegRegistroNovedades, Date> fechaNovedad;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> descripcionValorAnterior;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> observacion;
	public static volatile SingularAttribute<AsegRegistroNovedades, String> usuarioModifica;
	public static volatile SingularAttribute<AsegRegistroNovedades, AsegRadicadoNovedades> radicadoNovedadesId;

	public static final String ASEG_REPORTE_NOVEDADES_ID = "asegReporteNovedadesId";
	public static final String FECHA_MARCACION = "fechaMarcacion";
	public static final String DESCRIPCION_ORIGEN_ULTIMO_REGISTRO = "descripcionOrigenUltimoRegistro";
	public static final String ORIGEN_ULTIMO_REGISTRO = "origenUltimoRegistro";
	public static final String ASEG_INFORMES_ID_MARCACION = "asegInformesIdMarcacion";
	public static final String DESCRIPCION_VALOR_NUEVO = "descripcionValorNuevo";
	public static final String VALOR_ANTERIOR = "valorAnterior";
	public static final String MAE_ESTADO_NOVEDAD = "maeEstadoNovedad";
	public static final String ASEG_MA_NOVEDADES_ID = "asegMaNovedadesId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ID_AFILIADO = "idAfiliado";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR_NUEVO = "valorNuevo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String SINCRONIZADO = "sincronizado";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String FECHA_NOVEDAD = "fechaNovedad";
	public static final String DESCRIPCION_VALOR_ANTERIOR = "descripcionValorAnterior";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String RADICADO_NOVEDADES_ID = "radicadoNovedadesId";

}

