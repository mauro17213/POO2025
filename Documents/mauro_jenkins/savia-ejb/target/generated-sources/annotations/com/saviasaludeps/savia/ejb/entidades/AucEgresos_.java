package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AucEgresos.class)
public abstract class AucEgresos_ {

	public static volatile SingularAttribute<AucEgresos, Integer> maeConductaEgresoId;
	public static volatile SingularAttribute<AucEgresos, String> maeDestinoEgresoCodigo;
	public static volatile SingularAttribute<AucEgresos, Integer> maEspecialidadesId;
	public static volatile SingularAttribute<AucEgresos, Boolean> ipsEntregaValor;
	public static volatile SingularAttribute<AucEgresos, String> maEspecialidadesCodigo;
	public static volatile SingularAttribute<AucEgresos, Integer> maeDestinoEgresoId;
	public static volatile SingularAttribute<AucEgresos, String> numCertificado;
	public static volatile SingularAttribute<AucEgresos, String> terminalModifica;
	public static volatile SingularAttribute<AucEgresos, String> usuarioCrea;
	public static volatile SingularAttribute<AucEgresos, String> terminalCrea;
	public static volatile SingularAttribute<AucEgresos, BigDecimal> valorEstancia;
	public static volatile SingularAttribute<AucEgresos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AucEgresos, Integer> id;
	public static volatile SingularAttribute<AucEgresos, String> maEspecialidadesValor;
	public static volatile SingularAttribute<AucEgresos, String> maeConductaEgresoCodigo;
	public static volatile SingularAttribute<AucEgresos, Integer> fuenteOrigen;
	public static volatile ListAttribute<AucEgresos, AucDiagnosticos> aucDiagnosticosList;
	public static volatile SingularAttribute<AucEgresos, Date> fechaEgreso;
	public static volatile SingularAttribute<AucEgresos, String> maeConductaEgresoValor;
	public static volatile ListAttribute<AucEgresos, AucHospitalizaciones> aucHospitalizacionesList;
	public static volatile SingularAttribute<AucEgresos, String> maeDestinoEgresoValor;
	public static volatile SingularAttribute<AucEgresos, Integer> facturado;
	public static volatile SingularAttribute<AucEgresos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AucEgresos, Boolean> fallecido;
	public static volatile SingularAttribute<AucEgresos, String> usuarioModifica;

	public static final String MAE_CONDUCTA_EGRESO_ID = "maeConductaEgresoId";
	public static final String MAE_DESTINO_EGRESO_CODIGO = "maeDestinoEgresoCodigo";
	public static final String MA_ESPECIALIDADES_ID = "maEspecialidadesId";
	public static final String IPS_ENTREGA_VALOR = "ipsEntregaValor";
	public static final String MA_ESPECIALIDADES_CODIGO = "maEspecialidadesCodigo";
	public static final String MAE_DESTINO_EGRESO_ID = "maeDestinoEgresoId";
	public static final String NUM_CERTIFICADO = "numCertificado";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String VALOR_ESTANCIA = "valorEstancia";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MA_ESPECIALIDADES_VALOR = "maEspecialidadesValor";
	public static final String MAE_CONDUCTA_EGRESO_CODIGO = "maeConductaEgresoCodigo";
	public static final String FUENTE_ORIGEN = "fuenteOrigen";
	public static final String AUC_DIAGNOSTICOS_LIST = "aucDiagnosticosList";
	public static final String FECHA_EGRESO = "fechaEgreso";
	public static final String MAE_CONDUCTA_EGRESO_VALOR = "maeConductaEgresoValor";
	public static final String AUC_HOSPITALIZACIONES_LIST = "aucHospitalizacionesList";
	public static final String MAE_DESTINO_EGRESO_VALOR = "maeDestinoEgresoValor";
	public static final String FACTURADO = "facturado";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String FALLECIDO = "fallecido";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

