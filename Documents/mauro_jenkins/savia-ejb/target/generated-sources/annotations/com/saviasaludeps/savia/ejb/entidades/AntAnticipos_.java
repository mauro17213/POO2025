package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AntAnticipos.class)
public abstract class AntAnticipos_ {

	public static volatile SingularAttribute<AntAnticipos, Boolean> pbs;
	public static volatile SingularAttribute<AntAnticipos, BigDecimal> valorCotizado;
	public static volatile SingularAttribute<AntAnticipos, Integer> estado;
	public static volatile SingularAttribute<AntAnticipos, Integer> tipo;
	public static volatile SingularAttribute<AntAnticipos, String> justificacion;
	public static volatile SingularAttribute<AntAnticipos, String> maeClasificacionTipo;
	public static volatile SingularAttribute<AntAnticipos, BigDecimal> retencionSugerida;
	public static volatile SingularAttribute<AntAnticipos, GnUsuarios> gnUsuariosId;
	public static volatile ListAttribute<AntAnticipos, AntAnticipoItems> antAnticipoItemsList;
	public static volatile SingularAttribute<AntAnticipos, String> terminalModifica;
	public static volatile SingularAttribute<AntAnticipos, AsegAfiliados> asegAfiliadosId;
	public static volatile SingularAttribute<AntAnticipos, String> usuarioCrea;
	public static volatile SingularAttribute<AntAnticipos, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<AntAnticipos, String> terminalCrea;
	public static volatile SingularAttribute<AntAnticipos, BigDecimal> valorDisponible;
	public static volatile SingularAttribute<AntAnticipos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AntAnticipos, Boolean> aplicaRetencion;
	public static volatile SingularAttribute<AntAnticipos, Integer> id;
	public static volatile ListAttribute<AntAnticipos, AntAnticipoGestiones> antAnticipoGestionesList;
	public static volatile SingularAttribute<AntAnticipos, Integer> maDiagnosticoId;
	public static volatile SingularAttribute<AntAnticipos, GnEmpresas> gnEmpresasId;
	public static volatile SingularAttribute<AntAnticipos, String> maeClasificacionCodigo;
	public static volatile SingularAttribute<AntAnticipos, String> maDiagnosticoValor;
	public static volatile SingularAttribute<AntAnticipos, String> codigoContabilizacionSap;
	public static volatile SingularAttribute<AntAnticipos, BigDecimal> retencionAplicada;
	public static volatile ListAttribute<AntAnticipos, AntAnticipoValores> antAnticipoValoresList;
	public static volatile SingularAttribute<AntAnticipos, String> maeClasificacionValor;
	public static volatile SingularAttribute<AntAnticipos, BigDecimal> valorPagado;
	public static volatile SingularAttribute<AntAnticipos, Integer> maeClasificacionId;
	public static volatile SingularAttribute<AntAnticipos, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<AntAnticipos, String> clasificacionObservacion;
	public static volatile SingularAttribute<AntAnticipos, String> maDiagnosticoCodigo;
	public static volatile SingularAttribute<AntAnticipos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AntAnticipos, String> codigoCompensacionSap;
	public static volatile SingularAttribute<AntAnticipos, String> usuarioModifica;
	public static volatile ListAttribute<AntAnticipos, AntAnticipoAdjuntos> antAnticipoAdjuntosList;

	public static final String PBS = "pbs";
	public static final String VALOR_COTIZADO = "valorCotizado";
	public static final String ESTADO = "estado";
	public static final String TIPO = "tipo";
	public static final String JUSTIFICACION = "justificacion";
	public static final String MAE_CLASIFICACION_TIPO = "maeClasificacionTipo";
	public static final String RETENCION_SUGERIDA = "retencionSugerida";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String ANT_ANTICIPO_ITEMS_LIST = "antAnticipoItemsList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR_DISPONIBLE = "valorDisponible";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String APLICA_RETENCION = "aplicaRetencion";
	public static final String ID = "id";
	public static final String ANT_ANTICIPO_GESTIONES_LIST = "antAnticipoGestionesList";
	public static final String MA_DIAGNOSTICO_ID = "maDiagnosticoId";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";
	public static final String MAE_CLASIFICACION_CODIGO = "maeClasificacionCodigo";
	public static final String MA_DIAGNOSTICO_VALOR = "maDiagnosticoValor";
	public static final String CODIGO_CONTABILIZACION_SAP = "codigoContabilizacionSap";
	public static final String RETENCION_APLICADA = "retencionAplicada";
	public static final String ANT_ANTICIPO_VALORES_LIST = "antAnticipoValoresList";
	public static final String MAE_CLASIFICACION_VALOR = "maeClasificacionValor";
	public static final String VALOR_PAGADO = "valorPagado";
	public static final String MAE_CLASIFICACION_ID = "maeClasificacionId";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String CLASIFICACION_OBSERVACION = "clasificacionObservacion";
	public static final String MA_DIAGNOSTICO_CODIGO = "maDiagnosticoCodigo";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String CODIGO_COMPENSACION_SAP = "codigoCompensacionSap";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ANT_ANTICIPO_ADJUNTOS_LIST = "antAnticipoAdjuntosList";

}

