package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AusPersonas.class)
public abstract class AusPersonas_ {

	public static volatile SingularAttribute<AusPersonas, Integer> maeSexoId;
	public static volatile SingularAttribute<AusPersonas, GnUbicaciones> gnUbicacionesId;
	public static volatile SingularAttribute<AusPersonas, String> documento;
	public static volatile SingularAttribute<AusPersonas, Date> fechaNacimento;
	public static volatile SingularAttribute<AusPersonas, String> maeEstadoValor;
	public static volatile SingularAttribute<AusPersonas, String> nombres;
	public static volatile SingularAttribute<AusPersonas, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<AusPersonas, String> terminalModifica;
	public static volatile SingularAttribute<AusPersonas, AsegAfiliados> asegAfiliadosId;
	public static volatile ListAttribute<AusPersonas, AusPersonaTelefonos> ausPersonaTelefonosList;
	public static volatile SingularAttribute<AusPersonas, String> usuarioCrea;
	public static volatile SingularAttribute<AusPersonas, String> terminalCrea;
	public static volatile SingularAttribute<AusPersonas, String> maeSexoCodigo;
	public static volatile SingularAttribute<AusPersonas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AusPersonas, Integer> id;
	public static volatile SingularAttribute<AusPersonas, Integer> maeEstadoId;
	public static volatile SingularAttribute<AusPersonas, String> maeTipoParentescoValor;
	public static volatile SingularAttribute<AusPersonas, Boolean> regimen;
	public static volatile SingularAttribute<AusPersonas, Integer> maeTipoParentescoId;
	public static volatile SingularAttribute<AusPersonas, String> apellidos;
	public static volatile SingularAttribute<AusPersonas, String> maeSexoValor;
	public static volatile SingularAttribute<AusPersonas, Boolean> gestante;
	public static volatile ListAttribute<AusPersonas, AusCasos> ausCasosList;
	public static volatile SingularAttribute<AusPersonas, String> direccion;
	public static volatile ListAttribute<AusPersonas, AusCasos> ausCasosList1;
	public static volatile SingularAttribute<AusPersonas, String> contrato;
	public static volatile SingularAttribute<AusPersonas, Boolean> dicapacidad;
	public static volatile SingularAttribute<AusPersonas, String> maeEstadoCodigo;
	public static volatile SingularAttribute<AusPersonas, Integer> estratro;
	public static volatile SingularAttribute<AusPersonas, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AusPersonas, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<AusPersonas, String> correoElectronico;
	public static volatile SingularAttribute<AusPersonas, String> maeTipoParentescoCodigo;
	public static volatile SingularAttribute<AusPersonas, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<AusPersonas, String> usuarioModifica;

	public static final String MAE_SEXO_ID = "maeSexoId";
	public static final String GN_UBICACIONES_ID = "gnUbicacionesId";
	public static final String DOCUMENTO = "documento";
	public static final String FECHA_NACIMENTO = "fechaNacimento";
	public static final String MAE_ESTADO_VALOR = "maeEstadoValor";
	public static final String NOMBRES = "nombres";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ASEG_AFILIADOS_ID = "asegAfiliadosId";
	public static final String AUS_PERSONA_TELEFONOS_LIST = "ausPersonaTelefonosList";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_SEXO_CODIGO = "maeSexoCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String MAE_ESTADO_ID = "maeEstadoId";
	public static final String MAE_TIPO_PARENTESCO_VALOR = "maeTipoParentescoValor";
	public static final String REGIMEN = "regimen";
	public static final String MAE_TIPO_PARENTESCO_ID = "maeTipoParentescoId";
	public static final String APELLIDOS = "apellidos";
	public static final String MAE_SEXO_VALOR = "maeSexoValor";
	public static final String GESTANTE = "gestante";
	public static final String AUS_CASOS_LIST = "ausCasosList";
	public static final String DIRECCION = "direccion";
	public static final String AUS_CASOS_LIST1 = "ausCasosList1";
	public static final String CONTRATO = "contrato";
	public static final String DICAPACIDAD = "dicapacidad";
	public static final String MAE_ESTADO_CODIGO = "maeEstadoCodigo";
	public static final String ESTRATRO = "estratro";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String CORREO_ELECTRONICO = "correoElectronico";
	public static final String MAE_TIPO_PARENTESCO_CODIGO = "maeTipoParentescoCodigo";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

