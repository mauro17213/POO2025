package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFeRipsValidaciones.class)
public abstract class CmFeRipsValidaciones_ {

	public static volatile SingularAttribute<CmFeRipsValidaciones, String> descripcion;
	public static volatile SingularAttribute<CmFeRipsValidaciones, Integer> idValidacion;
	public static volatile SingularAttribute<CmFeRipsValidaciones, String> terminalModifica;
	public static volatile SingularAttribute<CmFeRipsValidaciones, Boolean> estado;
	public static volatile SingularAttribute<CmFeRipsValidaciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmFeRipsValidaciones, Integer> id;
	public static volatile ListAttribute<CmFeRipsValidaciones, CmFeRipsValidacionesHistoricos> cmFeRipsValidacionesHistoricosList;
	public static volatile SingularAttribute<CmFeRipsValidaciones, String> nombreValidacion;
	public static volatile SingularAttribute<CmFeRipsValidaciones, String> usuarioModifica;

	public static final String DESCRIPCION = "descripcion";
	public static final String ID_VALIDACION = "idValidacion";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ESTADO = "estado";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String CM_FE_RIPS_VALIDACIONES_HISTORICOS_LIST = "cmFeRipsValidacionesHistoricosList";
	public static final String NOMBRE_VALIDACION = "nombreValidacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

