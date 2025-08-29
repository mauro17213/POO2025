package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuSeguimientoServicios.class)
public abstract class AuSeguimientoServicios_ {

	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> maeTallaMascaraId;
	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> presiones;
	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> tiempoUso;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> usuarioBorra;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> maeTipoSondaValor;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> terminalModifica;
	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> maeTipoMascaraId;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> usuarioCrea;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> maeTallaMascaraCodigo;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> terminalCrea;
	public static volatile SingularAttribute<AuSeguimientoServicios, BigDecimal> litros;
	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> maeTipoSondaId;
	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> tiempoDuracionTratamiento;
	public static volatile SingularAttribute<AuSeguimientoServicios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> terminalBorra;
	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> id;
	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> presion;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> maeTipoMascaraValor;
	public static volatile SingularAttribute<AuSeguimientoServicios, Date> fechaHoraBorra;
	public static volatile SingularAttribute<AuSeguimientoServicios, Boolean> gasesArteriales;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> maeSeguimientoServicioCodigo;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> maeTipoInsumoCodigo;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> maeTipoInsumoValor;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> maeTipoMascaraCodigo;
	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> maeSeguimientoServicioId;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> maeTipoSondaCodigo;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> tipoMascaraTos;
	public static volatile SingularAttribute<AuSeguimientoServicios, AuSeguimientos> auSeguimientoId;
	public static volatile SingularAttribute<AuSeguimientoServicios, Boolean> borrado;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> observaciones;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> maeTallaMascaraValor;
	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> maeTipoInsumoId;
	public static volatile SingularAttribute<AuSeguimientoServicios, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuSeguimientoServicios, Integer> rampa;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> usuarioModifica;
	public static volatile SingularAttribute<AuSeguimientoServicios, String> maeSeguimientoServicioValor;

	public static final String MAE_TALLA_MASCARA_ID = "maeTallaMascaraId";
	public static final String PRESIONES = "presiones";
	public static final String TIEMPO_USO = "tiempoUso";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String MAE_TIPO_SONDA_VALOR = "maeTipoSondaValor";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String MAE_TIPO_MASCARA_ID = "maeTipoMascaraId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TALLA_MASCARA_CODIGO = "maeTallaMascaraCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String LITROS = "litros";
	public static final String MAE_TIPO_SONDA_ID = "maeTipoSondaId";
	public static final String TIEMPO_DURACION_TRATAMIENTO = "tiempoDuracionTratamiento";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String PRESION = "presion";
	public static final String MAE_TIPO_MASCARA_VALOR = "maeTipoMascaraValor";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String GASES_ARTERIALES = "gasesArteriales";
	public static final String MAE_SEGUIMIENTO_SERVICIO_CODIGO = "maeSeguimientoServicioCodigo";
	public static final String MAE_TIPO_INSUMO_CODIGO = "maeTipoInsumoCodigo";
	public static final String MAE_TIPO_INSUMO_VALOR = "maeTipoInsumoValor";
	public static final String MAE_TIPO_MASCARA_CODIGO = "maeTipoMascaraCodigo";
	public static final String MAE_SEGUIMIENTO_SERVICIO_ID = "maeSeguimientoServicioId";
	public static final String MAE_TIPO_SONDA_CODIGO = "maeTipoSondaCodigo";
	public static final String TIPO_MASCARA_TOS = "tipoMascaraTos";
	public static final String AU_SEGUIMIENTO_ID = "auSeguimientoId";
	public static final String BORRADO = "borrado";
	public static final String OBSERVACIONES = "observaciones";
	public static final String MAE_TALLA_MASCARA_VALOR = "maeTallaMascaraValor";
	public static final String MAE_TIPO_INSUMO_ID = "maeTipoInsumoId";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String RAMPA = "rampa";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String MAE_SEGUIMIENTO_SERVICIO_VALOR = "maeSeguimientoServicioValor";

}

