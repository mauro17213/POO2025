package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuAdjuntos.class)
public abstract class TuAdjuntos_ {

	public static volatile SingularAttribute<TuAdjuntos, String> nombreArchivo;
	public static volatile SingularAttribute<TuAdjuntos, String> archivo;
	public static volatile SingularAttribute<TuAdjuntos, String> ruta;
	public static volatile SingularAttribute<TuAdjuntos, String> maeTipoAnexoValor;
	public static volatile SingularAttribute<TuAdjuntos, TuTutelaEstados> tuTutelaEstadosId;
	public static volatile SingularAttribute<TuAdjuntos, TuTutelaItems> tuTutelaItemsId;
	public static volatile SingularAttribute<TuAdjuntos, String> terminalModifica;
	public static volatile SingularAttribute<TuAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<TuAdjuntos, String> maeTipoAnexoCodigo;
	public static volatile SingularAttribute<TuAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<TuAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuAdjuntos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuAdjuntos, Integer> id;
	public static volatile SingularAttribute<TuAdjuntos, Integer> maeTipoAnexoId;
	public static volatile SingularAttribute<TuAdjuntos, String> observacion;
	public static volatile SingularAttribute<TuAdjuntos, String> usuarioModifica;
	public static volatile SingularAttribute<TuAdjuntos, TuSeguimientos> tuSeguimientosId;

	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String MAE_TIPO_ANEXO_VALOR = "maeTipoAnexoValor";
	public static final String TU_TUTELA_ESTADOS_ID = "tuTutelaEstadosId";
	public static final String TU_TUTELA_ITEMS_ID = "tuTutelaItemsId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_ANEXO_CODIGO = "maeTipoAnexoCodigo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_ANEXO_ID = "maeTipoAnexoId";
	public static final String OBSERVACION = "observacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String TU_SEGUIMIENTOS_ID = "tuSeguimientosId";

}

