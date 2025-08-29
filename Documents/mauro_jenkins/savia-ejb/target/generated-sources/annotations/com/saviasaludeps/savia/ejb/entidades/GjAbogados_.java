package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GjAbogados.class)
public abstract class GjAbogados_ {

	public static volatile SingularAttribute<GjAbogados, Short> tipo;
	public static volatile ListAttribute<GjAbogados, GjProcesoAbogados> gjProcesoAbogadosList;
	public static volatile SingularAttribute<GjAbogados, String> documento;
	public static volatile SingularAttribute<GjAbogados, String> tarjetaProfecional;
	public static volatile SingularAttribute<GjAbogados, String> nombre;
	public static volatile SingularAttribute<GjAbogados, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<GjAbogados, String> maeTipoDocumentoCodigo;
	public static volatile SingularAttribute<GjAbogados, String> terminalModifica;
	public static volatile SingularAttribute<GjAbogados, String> usuarioCrea;
	public static volatile SingularAttribute<GjAbogados, String> terminalCrea;
	public static volatile SingularAttribute<GjAbogados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GjAbogados, Date> fechaHoraModifica;
	public static volatile SingularAttribute<GjAbogados, Integer> id;
	public static volatile SingularAttribute<GjAbogados, String> maeTipoDocumentoValor;
	public static volatile SingularAttribute<GjAbogados, Integer> maeTipoDocumentoId;
	public static volatile SingularAttribute<GjAbogados, String> usuarioModifica;

	public static final String TIPO = "tipo";
	public static final String GJ_PROCESO_ABOGADOS_LIST = "gjProcesoAbogadosList";
	public static final String DOCUMENTO = "documento";
	public static final String TARJETA_PROFECIONAL = "tarjetaProfecional";
	public static final String NOMBRE = "nombre";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String MAE_TIPO_DOCUMENTO_CODIGO = "maeTipoDocumentoCodigo";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String MAE_TIPO_DOCUMENTO_VALOR = "maeTipoDocumentoValor";
	public static final String MAE_TIPO_DOCUMENTO_ID = "maeTipoDocumentoId";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

