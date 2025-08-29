package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmGrupos.class)
public abstract class CmGrupos_ {

	public static volatile SingularAttribute<CmGrupos, String> descripcion;
	public static volatile SingularAttribute<CmGrupos, Boolean> pbs;
	public static volatile ListAttribute<CmGrupos, CmGrupoHistoricos> cmGrupoHistoricosList;
	public static volatile ListAttribute<CmGrupos, CmGrupoPrestadores> cmGrupoPrestadoresList;
	public static volatile ListAttribute<CmGrupos, CmFacturas> cmFacturasList;
	public static volatile SingularAttribute<CmGrupos, Boolean> camaFija;
	public static volatile SingularAttribute<CmGrupos, Integer> categoria;
	public static volatile SingularAttribute<CmGrupos, Boolean> tipoGrupo;
	public static volatile SingularAttribute<CmGrupos, String> nombre;
	public static volatile SingularAttribute<CmGrupos, String> terminalModifica;
	public static volatile SingularAttribute<CmGrupos, String> usuarioCrea;
	public static volatile SingularAttribute<CmGrupos, Integer> usuariosIdAsignacion;
	public static volatile SingularAttribute<CmGrupos, String> terminalCrea;
	public static volatile SingularAttribute<CmGrupos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmGrupos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmGrupos, Integer> id;
	public static volatile ListAttribute<CmGrupos, CmGrupoUsuarios> cmGrupoUsuariosList;
	public static volatile SingularAttribute<CmGrupos, String> usuarioModifica;
	public static volatile SingularAttribute<CmGrupos, Boolean> activo;

	public static final String DESCRIPCION = "descripcion";
	public static final String PBS = "pbs";
	public static final String CM_GRUPO_HISTORICOS_LIST = "cmGrupoHistoricosList";
	public static final String CM_GRUPO_PRESTADORES_LIST = "cmGrupoPrestadoresList";
	public static final String CM_FACTURAS_LIST = "cmFacturasList";
	public static final String CAMA_FIJA = "camaFija";
	public static final String CATEGORIA = "categoria";
	public static final String TIPO_GRUPO = "tipoGrupo";
	public static final String NOMBRE = "nombre";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String USUARIOS_ID_ASIGNACION = "usuariosIdAsignacion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String CM_GRUPO_USUARIOS_LIST = "cmGrupoUsuariosList";
	public static final String USUARIO_MODIFICA = "usuarioModifica";
	public static final String ACTIVO = "activo";

}

