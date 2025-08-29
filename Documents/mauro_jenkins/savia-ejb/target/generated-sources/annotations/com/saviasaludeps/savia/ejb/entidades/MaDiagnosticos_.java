package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MaDiagnosticos.class)
public abstract class MaDiagnosticos_ {

	public static volatile SingularAttribute<MaDiagnosticos, Integer> maeDiagnosticoCapituloId;
	public static volatile SingularAttribute<MaDiagnosticos, String> grupoMortalidad;
	public static volatile SingularAttribute<MaDiagnosticos, String> maCacValor;
	public static volatile SingularAttribute<MaDiagnosticos, Boolean> priorizarCrue;
	public static volatile SingularAttribute<MaDiagnosticos, String> maeDiagnosticoCategoriaValor;
	public static volatile SingularAttribute<MaDiagnosticos, String> edadSuperior;
	public static volatile ListAttribute<MaDiagnosticos, MaDiagnosticoMedicamentos> maDiagnosticoMedicamentosList;
	public static volatile SingularAttribute<MaDiagnosticos, String> nombre;
	public static volatile SingularAttribute<MaDiagnosticos, Boolean> excentoCobro;
	public static volatile SingularAttribute<MaDiagnosticos, Integer> maeDiagnosticoCategoriaId;
	public static volatile SingularAttribute<MaDiagnosticos, String> edadInferior;
	public static volatile SingularAttribute<MaDiagnosticos, String> terminalModifica;
	public static volatile SingularAttribute<MaDiagnosticos, String> usuarioCrea;
	public static volatile SingularAttribute<MaDiagnosticos, Integer> sexoAplica;
	public static volatile SingularAttribute<MaDiagnosticos, String> terminalCrea;
	public static volatile SingularAttribute<MaDiagnosticos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MaDiagnosticos, Integer> id;
	public static volatile SingularAttribute<MaDiagnosticos, String> maeDiagnosticoCapituloValor;
	public static volatile SingularAttribute<MaDiagnosticos, String> maCacCodigo;
	public static volatile SingularAttribute<MaDiagnosticos, String> maeDiagnosticoCategoriaCodigo;
	public static volatile SingularAttribute<MaDiagnosticos, String> codigo;
	public static volatile SingularAttribute<MaDiagnosticos, Integer> valorLimiteSuperior;
	public static volatile SingularAttribute<MaDiagnosticos, Integer> maCacId;
	public static volatile ListAttribute<MaDiagnosticos, MaMedicamentoCondicionados> maMedicamentoCondicionadosList;
	public static volatile ListAttribute<MaDiagnosticos, AusCasoServicios> ausCasoServiciosList;
	public static volatile SingularAttribute<MaDiagnosticos, String> grupoMortalidadLista;
	public static volatile SingularAttribute<MaDiagnosticos, Boolean> altoCosto;
	public static volatile SingularAttribute<MaDiagnosticos, String> maeDiagnosticoCapituloCodigo;
	public static volatile SingularAttribute<MaDiagnosticos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MaDiagnosticos, Integer> valorLimiteInferior;
	public static volatile SingularAttribute<MaDiagnosticos, String> usuarioModifica;
	public static volatile SingularAttribute<MaDiagnosticos, Boolean> activo;

	public static final String MAE_DIAGNOSTICO_CAPITULO_ID = "maeDiagnosticoCapituloId";
	public static final String GRUPO_MORTALIDAD = "grupoMortalidad";
	public static final String MA_CAC_VALOR = "maCacValor";
	public static final String PRIORIZAR_CRUE = "priorizarCrue";
	public static final String MAE_DIAGNOSTICO_CATEGORIA_VALOR = "maeDiagnosticoCategoriaValor";
	public static final String EDAD_SUPERIOR = "edadSuperior";
	public static final String MA_DIAGNOSTICO_MEDICAMENTOS_LIST = "maDiagnosticoMedicamentosList";
	public static final String NOMBRE = "nombre";
	public static final String EXCENTO_COBRO = "excentoCobro";
	public static final String MAE_DIAGNOSTICO_CATEGORIA_ID = "maeDiagnosticoCategoriaId";
	public static final String EDAD_INFERIOR = "edadInferior";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String SEXO_APLICA = "sexoAplica";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_DIAGNOSTICO_CAPITULO_VALOR = "maeDiagnosticoCapituloValor";
	public static final String MA_CAC_CODIGO = "maCacCodigo";
	public static final String MAE_DIAGNOSTICO_CATEGORIA_CODIGO = "maeDiagnosticoCategoriaCodigo";
	public static final String CODIGO = "codigo";
	public static final String VALOR_LIMITE_SUPERIOR = "valorLimiteSuperior";
	public static final String MA_CAC_ID = "maCacId";
	public static final String MA_MEDICAMENTO_CONDICIONADOS_LIST = "maMedicamentoCondicionadosList";
	public static final String AUS_CASO_SERVICIOS_LIST = "ausCasoServiciosList";
	public static final String GRUPO_MORTALIDAD_LISTA = "grupoMortalidadLista";
	public static final String ALTO_COSTO = "altoCosto";
	public static final String MAE_DIAGNOSTICO_CAPITULO_CODIGO = "maeDiagnosticoCapituloCodigo";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String VALOR_LIMITE_INFERIOR = "valorLimiteInferior";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

