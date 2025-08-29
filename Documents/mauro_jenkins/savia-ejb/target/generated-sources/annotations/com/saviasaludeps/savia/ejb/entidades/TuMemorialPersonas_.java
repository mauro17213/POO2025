package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TuMemorialPersonas.class)
public abstract class TuMemorialPersonas_ {

	public static volatile SingularAttribute<TuMemorialPersonas, Integer> maeGnCargoId;
	public static volatile SingularAttribute<TuMemorialPersonas, String> maeGnCargoCodigo;
	public static volatile SingularAttribute<TuMemorialPersonas, String> segundoNombre;
	public static volatile SingularAttribute<TuMemorialPersonas, String> primerNombre;
	public static volatile SingularAttribute<TuMemorialPersonas, String> primerApellido;
	public static volatile SingularAttribute<TuMemorialPersonas, String> segundoApellido;
	public static volatile SingularAttribute<TuMemorialPersonas, Integer> tipoPersonal;
	public static volatile SingularAttribute<TuMemorialPersonas, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<TuMemorialPersonas, String> terminalModifica;
	public static volatile SingularAttribute<TuMemorialPersonas, String> maeGnCargoValor;
	public static volatile SingularAttribute<TuMemorialPersonas, String> numeroTarjetaProfesional;
	public static volatile SingularAttribute<TuMemorialPersonas, String> usuarioCrea;
	public static volatile SingularAttribute<TuMemorialPersonas, String> terminalCrea;
	public static volatile SingularAttribute<TuMemorialPersonas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<TuMemorialPersonas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<TuMemorialPersonas, Integer> id;
	public static volatile SingularAttribute<TuMemorialPersonas, String> numeroDocumento;
	public static volatile SingularAttribute<TuMemorialPersonas, String> maeTipoDocumentoValor;
	public static volatile ListAttribute<TuMemorialPersonas, TuMemorialFirmas> tuMemorialFirmasList;
	public static volatile SingularAttribute<TuMemorialPersonas, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<TuMemorialPersonas, String> usuarioModifica;

	public static final String MAE_GN_CARGO_ID = "maeGnCargoId";
	public static final String MAE_GN_CARGO_CODIGO = "maeGnCargoCodigo";
	public static final String SEGUNDO_NOMBRE = "segundoNombre";
	public static final String PRIMER_NOMBRE = "primerNombre";
	public static final String PRIMER_APELLIDO = "primerApellido";
	public static final String SEGUNDO_APELLIDO = "segundoApellido";
	public static final String TIPO_PERSONAL = "tipoPersonal";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String MAE_GN_CARGO_VALOR = "maeGnCargoValor";
	public static final String NUMERO_TARJETA_PROFESIONAL = "numeroTarjetaProfesional";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String NUMERO_DOCUMENTO = "numeroDocumento";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String TU_MEMORIAL_FIRMAS_LIST = "tuMemorialFirmasList";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

