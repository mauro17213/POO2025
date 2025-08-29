package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AuAnexo2Items.class)
public abstract class AuAnexo2Items_ {

	public static volatile SingularAttribute<AuAnexo2Items, String> maTecnologiaCodigo;
	public static volatile SingularAttribute<AuAnexo2Items, Integer> estado;
	public static volatile SingularAttribute<AuAnexo2Items, Integer> maServicioSolicitadoId;
	public static volatile SingularAttribute<AuAnexo2Items, String> maServicioSolicitadoValor;
	public static volatile SingularAttribute<AuAnexo2Items, String> maTecnologiaValor;
	public static volatile SingularAttribute<AuAnexo2Items, Integer> cantidadSolicitada;
	public static volatile SingularAttribute<AuAnexo2Items, Integer> maTecnologiaId;
	public static volatile SingularAttribute<AuAnexo2Items, String> maServicioSolicitadoCodigo;
	public static volatile ListAttribute<AuAnexo2Items, AuAnexo4Items> auAnexo4ItemsList;
	public static volatile SingularAttribute<AuAnexo2Items, String> terminalModifica;
	public static volatile SingularAttribute<AuAnexo2Items, String> usuarioCrea;
	public static volatile SingularAttribute<AuAnexo2Items, AuAnexos2> auAnexos2Id;
	public static volatile SingularAttribute<AuAnexo2Items, String> terminalCrea;
	public static volatile SingularAttribute<AuAnexo2Items, Date> fechaHoraCrea;
	public static volatile SingularAttribute<AuAnexo2Items, Date> fechaHoraModifica;
	public static volatile SingularAttribute<AuAnexo2Items, Integer> id;
	public static volatile SingularAttribute<AuAnexo2Items, String> usuarioModifica;

	public static final String MA_TECNOLOGIA_CODIGO = "maTecnologiaCodigo";
	public static final String ESTADO = "estado";
	public static final String MA_SERVICIO_SOLICITADO_ID = "maServicioSolicitadoId";
	public static final String MA_SERVICIO_SOLICITADO_VALOR = "maServicioSolicitadoValor";
	public static final String MA_TECNOLOGIA_VALOR = "maTecnologiaValor";
	public static final String CANTIDAD_SOLICITADA = "cantidadSolicitada";
	public static final String MA_TECNOLOGIA_ID = "maTecnologiaId";
	public static final String MA_SERVICIO_SOLICITADO_CODIGO = "maServicioSolicitadoCodigo";
	public static final String AU_ANEXO4_ITEMS_LIST = "auAnexo4ItemsList";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String AU_ANEXOS2_ID = "auAnexos2Id";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

