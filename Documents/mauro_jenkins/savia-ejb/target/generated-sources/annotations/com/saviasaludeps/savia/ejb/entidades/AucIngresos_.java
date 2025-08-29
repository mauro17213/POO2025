package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucIngresos.class)
public abstract class AucIngresos_ {

	public static volatile SingularAttribute<AucIngresos, String> maeTipoIngresoCodigo;
	public static volatile SingularAttribute<AucIngresos, String> maeAltaTempranaValor;
	public static volatile SingularAttribute<AucIngresos, Integer> maeCntModalidadId;
	public static volatile SingularAttribute<AucIngresos, Integer> maeReingresoMotivoId;
	public static volatile SingularAttribute<AucIngresos, String> maeReingresoMotivoValor;
	public static volatile SingularAttribute<AucIngresos, Integer> maeRemisionNoPertinenteId;
	public static volatile SingularAttribute<AucIngresos, String> maeTipoIngresoValor;
	public static volatile SingularAttribute<AucIngresos, String> maeRemisionNoPertinenteCodigo;
	public static volatile SingularAttribute<AucIngresos, String> terminalModifica;
	public static volatile SingularAttribute<AucIngresos, String> usuarioCrea;
	public static volatile SingularAttribute<AucIngresos, String> maeCausaIngresoPrevalenteValor;
	public static volatile SingularAttribute<AucIngresos, String> terminalCrea;
	public static volatile SingularAttribute<AucIngresos, String> maeReingresoMotivoTipo;
	public static volatile SingularAttribute<AucIngresos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucIngresos, Integer> maeCausaIngresoPrevalenteId;
	public static volatile SingularAttribute<AucIngresos, Integer> id;
	public static volatile SingularAttribute<AucIngresos, String> maeCausaIngresoPrevalenteCodigo;
	public static volatile SingularAttribute<AucIngresos, Integer> maeAreaIngresoPrevenibleId;
	public static volatile SingularAttribute<AucIngresos, String> maeAltaTempranaCodigo;
	public static volatile SingularAttribute<AucIngresos, Integer> fuenteOrigen;
	public static volatile SingularAttribute<AucIngresos, String> maeCntModalidadCodigo;
	public static volatile SingularAttribute<AucIngresos, String> maeCntModalidadValor;
	public static volatile ListAttribute<AucIngresos, AucDiagnosticos> aucDiagnosticosList;
	public static volatile SingularAttribute<AucIngresos, String> maeReingresoMotivoCodigo;
	public static volatile SingularAttribute<AucIngresos, String> descripcionRemisionNoPertinente;
	public static volatile SingularAttribute<AucIngresos, String> maeAreaIngresoPrevenibleCodigo;
	public static volatile ListAttribute<AucIngresos, AucHospitalizaciones> aucHospitalizacionesList;
	public static volatile SingularAttribute<AucIngresos, Date> fechaIngreso;
	public static volatile SingularAttribute<AucIngresos, String> descripcionIngresoPrevenible;
	public static volatile SingularAttribute<AucIngresos, Integer> maeTipoIngresoId;
	public static volatile SingularAttribute<AucIngresos, Short> ingreso;
	public static volatile SingularAttribute<AucIngresos, String> maeAltaTempranaTipo;
	public static volatile SingularAttribute<AucIngresos, Short> altaTemprana;
	public static volatile SingularAttribute<AucIngresos, Integer> maeAltaTempranaId;
	public static volatile SingularAttribute<AucIngresos, String> maeAreaIngresoPrevenibleValor;
	public static volatile SingularAttribute<AucIngresos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AucIngresos, Integer> indiceCharlson;
	public static volatile SingularAttribute<AucIngresos, String> usuarioModifica;
	public static volatile SingularAttribute<AucIngresos, String> maeRemisionNoPertinenteValor;

	public static final String MAE_TIPO_INGRESO_CODIGO = "maeTipoIngresoCodigo";
	public static final String MAE_ALTA_TEMPRANA_VALOR = "maeAltaTempranaValor";
	public static final String MAE_CNT_MODALIDAD_ID = "maeCntModalidadId";
	public static final String MAE_REINGRESO_MOTIVO_ID = "maeReingresoMotivoId";
	public static final String MAE_REINGRESO_MOTIVO_VALOR = "maeReingresoMotivoValor";
	public static final String MAE_REMISION_NO_PERTINENTE_ID = "maeRemisionNoPertinenteId";
	public static final String MAE_TIPO_INGRESO_VALOR = "maeTipoIngresoValor";
	public static final String MAE_REMISION_NO_PERTINENTE_CODIGO = "maeRemisionNoPertinenteCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_CAUSA_INGRESO_PREVALENTE_VALOR = "maeCausaIngresoPrevalenteValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_REINGRESO_MOTIVO_TIPO = "maeReingresoMotivoTipo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String MAE_CAUSA_INGRESO_PREVALENTE_ID = "maeCausaIngresoPrevalenteId";
	public static final String ID = "id";
	public static final String MAE_CAUSA_INGRESO_PREVALENTE_CODIGO = "maeCausaIngresoPrevalenteCodigo";
	public static final String MAE_AREA_INGRESO_PREVENIBLE_ID = "maeAreaIngresoPrevenibleId";
	public static final String MAE_ALTA_TEMPRANA_CODIGO = "maeAltaTempranaCodigo";
	public static final String FUENTE_ORIGEN = "fuenteOrigen";
	public static final String MAE_CNT_MODALIDAD_CODIGO = "maeCntModalidadCodigo";
	public static final String MAE_CNT_MODALIDAD_VALOR = "maeCntModalidadValor";
	public static final String AUC_DIAGNOSTICOS_LIST = "aucDiagnosticosList";
	public static final String MAE_REINGRESO_MOTIVO_CODIGO = "maeReingresoMotivoCodigo";
	public static final String DESCRIPCION_REMISION_NO_PERTINENTE = "descripcionRemisionNoPertinente";
	public static final String MAE_AREA_INGRESO_PREVENIBLE_CODIGO = "maeAreaIngresoPrevenibleCodigo";
	public static final String AUC_HOSPITALIZACIONES_LIST = "aucHospitalizacionesList";
	public static final String FECHA_INGRESO = "fechaIngreso";
	public static final String DESCRIPCION_INGRESO_PREVENIBLE = "descripcionIngresoPrevenible";
	public static final String MAE_TIPO_INGRESO_ID = "maeTipoIngresoId";
	public static final String INGRESO = "ingreso";
	public static final String MAE_ALTA_TEMPRANA_TIPO = "maeAltaTempranaTipo";
	public static final String ALTA_TEMPRANA = "altaTemprana";
	public static final String MAE_ALTA_TEMPRANA_ID = "maeAltaTempranaId";
	public static final String MAE_AREA_INGRESO_PREVENIBLE_VALOR = "maeAreaIngresoPrevenibleValor";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String INDICE_CHARLSON = "indiceCharlson";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String MAE_REMISION_NO_PERTINENTE_VALOR = "maeRemisionNoPertinenteValor";

}

