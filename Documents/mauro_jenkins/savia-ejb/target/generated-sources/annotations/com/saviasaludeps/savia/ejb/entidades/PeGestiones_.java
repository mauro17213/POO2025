package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeGestiones.class)
public abstract class PeGestiones_ {

	public static volatile SingularAttribute<PeGestiones, String> descripcion;
	public static volatile SingularAttribute<PeGestiones, Integer> fuenteOrigen;
	public static volatile SingularAttribute<PeGestiones, Integer> tipo;
	public static volatile SingularAttribute<PeGestiones, Date> fechaHoraBorra;
	public static volatile SingularAttribute<PeGestiones, String> usuarioBorra;
	public static volatile SingularAttribute<PeGestiones, PeAfiliadosProgramas> peAfiliadosProgramasId;
	public static volatile SingularAttribute<PeGestiones, String> maeTipoCodigo;
	public static volatile SingularAttribute<PeGestiones, String> borradoObservacion;
	public static volatile SingularAttribute<PeGestiones, GnUsuarios> gnUsuariosId;
	public static volatile SingularAttribute<PeGestiones, String> terminalModifica;
	public static volatile SingularAttribute<PeGestiones, String> usuarioCrea;
	public static volatile ListAttribute<PeGestiones, PeGestionesHistorico> peGestionesHistoricoList;
	public static volatile SingularAttribute<PeGestiones, String> terminalCrea;
	public static volatile SingularAttribute<PeGestiones, Boolean> borrado;
	public static volatile SingularAttribute<PeGestiones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeGestiones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<PeGestiones, String> terminalBorra;
	public static volatile SingularAttribute<PeGestiones, Integer> id;
	public static volatile ListAttribute<PeGestiones, PeAdjuntos> peAdjuntosList;
	public static volatile SingularAttribute<PeGestiones, Integer> maeTipoId;
	public static volatile SingularAttribute<PeGestiones, String> maeTipoValor;
	public static volatile SingularAttribute<PeGestiones, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String FUENTE_ORIGEN = "fuenteOrigen";
	public static final String TIPO = "tipo";
	public static final String FECHA_HORA_BORRA = "fechaHoraBorra";
	public static final String USUARIO_BORRA = "usuarioBorra";
	public static final String PE_AFILIADOS_PROGRAMAS_ID = "peAfiliadosProgramasId";
	public static final String MAE_TIPO_CODIGO = "maeTipoCodigo";
	public static final String BORRADO_OBSERVACION = "borradoObservacion";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String PE_GESTIONES_HISTORICO_LIST = "peGestionesHistoricoList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String BORRADO = "borrado";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String TERMINAL_BORRA = "terminalBorra";
	public static final String ID = "id";
	public static final String PE_ADJUNTOS_LIST = "peAdjuntosList";
	public static final String MAE_TIPO_ID = "maeTipoId";
	public static final String MAE_TIPO_VALOR = "maeTipoValor";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

