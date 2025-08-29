package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucJustificacionEstanciasProlongadas.class)
public abstract class AucJustificacionEstanciasProlongadas_ {

	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, String> maeCausaEstanciaProlongadaCodigo;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, AucHospitalizaciones> aucHospitalizacionesId;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, String> maePropuestaIntervencionCodigo;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, String> resumenClinico;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, String> terminalModifica;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, String> usuarioCrea;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, Integer> maePropuestaIntervencionId;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, String> terminalCrea;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, String> maeCausaEstanciaProlongadaValor;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, String> maePropuestaIntervencionValor;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, Integer> id;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, String> usuarioModifica;
	public static volatile SingularAttribute<AucJustificacionEstanciasProlongadas, Integer> maeCausaEstanciaProlongadaId;

	public static final String MAE_CAUSA_ESTANCIA_PROLONGADA_CODIGO = "maeCausaEstanciaProlongadaCodigo";
	public static final String AUC_HOSPITALIZACIONES_ID = "aucHospitalizacionesId";
	public static final String MAE_PROPUESTA_INTERVENCION_CODIGO = "maePropuestaIntervencionCodigo";
	public static final String RESUMEN_CLINICO = "resumenClinico";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_PROPUESTA_INTERVENCION_ID = "maePropuestaIntervencionId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_CAUSA_ESTANCIA_PROLONGADA_VALOR = "maeCausaEstanciaProlongadaValor";
	public static final String MAE_PROPUESTA_INTERVENCION_VALOR = "maePropuestaIntervencionValor";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String MAE_CAUSA_ESTANCIA_PROLONGADA_ID = "maeCausaEstanciaProlongadaId";

}

