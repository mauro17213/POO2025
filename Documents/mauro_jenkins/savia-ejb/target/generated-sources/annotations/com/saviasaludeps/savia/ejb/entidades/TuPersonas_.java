package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuPersonas.class)
public abstract class TuPersonas_ {

	public static volatile SingularAttribute<TuPersonas, Integer> asegAfiliadoId;
	public static volatile SingularAttribute<TuPersonas, Date> fechaNacimiento;
	public static volatile SingularAttribute<TuPersonas, Integer> maeGeneroId;
	public static volatile ListAttribute<TuPersonas, TuTutelas> tuTutelasList;
	public static volatile ListAttribute<TuPersonas, TuTutelaItems> tuTutelaItemsList;
	public static volatile SingularAttribute<TuPersonas, String> maeEstadoAfiliadoValor;
	public static volatile SingularAttribute<TuPersonas, String> nombres;
	public static volatile SingularAttribute<TuPersonas, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<TuPersonas, String> terminalModifica;
	public static volatile SingularAttribute<TuPersonas, String> maeGeneroCodigo;
	public static volatile SingularAttribute<TuPersonas, String> usuarioCrea;
	public static volatile SingularAttribute<TuPersonas, String> terminalCrea;
	public static volatile SingularAttribute<TuPersonas, Integer> maeEstadoAfiliadoId;
	public static volatile SingularAttribute<TuPersonas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuPersonas, Integer> id;
	public static volatile SingularAttribute<TuPersonas, String> numeroDocumento;
	public static volatile SingularAttribute<TuPersonas, String> apellidos;
	public static volatile SingularAttribute<TuPersonas, String> direccionResidencia;
	public static volatile SingularAttribute<TuPersonas, String> maeGeneroValor;
	public static volatile ListAttribute<TuPersonas, TuPersonasContactos> tuPersonasContactosList;
	public static volatile SingularAttribute<TuPersonas, String> maeEstadoAfiliadoCodigo;
	public static volatile SingularAttribute<TuPersonas, String> contratoAfiliacion;
	public static volatile SingularAttribute<TuPersonas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuPersonas, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<TuPersonas, String> correoElectronico;
	public static volatile SingularAttribute<TuPersonas, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<TuPersonas, String> usuarioModifica;
	public static volatile SingularAttribute<TuPersonas, String> agenteOficioso;
	public static volatile SingularAttribute<TuPersonas, GnUbicaciones> ubicacionAfiliacionId;

	public static final String ASEG_AFILIADO_ID = "asegAfiliadoId";
	public static final String FECHA_NACIMIENTO = "fechaNacimiento";
	public static final String MAE_GENERO_ID = "maeGeneroId";
	public static final String TU_TUTELAS_LIST = "tuTutelasList";
	public static final String TU_TUTELA_ITEMS_LIST = "tuTutelaItemsList";
	public static final String MAE_ESTADO_AFILIADO_VALOR = "maeEstadoAfiliadoValor";
	public static final String NOMBRES = "nombres";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String MAE_GENERO_CODIGO = "maeGeneroCodigo";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_ESTADO_AFILIADO_ID = "maeEstadoAfiliadoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String APELLIDOS = "apellidos";
	public static final String DIRECCION_RESIDENCIA = "direccionResidencia";
	public static final String MAE_GENERO_VALOR = "maeGeneroValor";
	public static final String TU_PERSONAS_CONTACTOS_LIST = "tuPersonasContactosList";
	public static final String MAE_ESTADO_AFILIADO_CODIGO = "maeEstadoAfiliadoCodigo";
	public static final String CONTRATO_AFILIACION = "contratoAfiliacion";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String CORREO_ELECTRONICO = "correoElectronico";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String AGENTE_OFICIOSO = "agenteOficioso";
	public static final String UBICACION_AFILIACION_ID = "ubicacionAfiliacionId";

}

