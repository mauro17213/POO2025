package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmHistoricoFacturas.class)
public abstract class CmHistoricoFacturas_ {

	public static volatile SingularAttribute<CmHistoricoFacturas, String> descripcion;
	public static volatile SingularAttribute<CmHistoricoFacturas, String> usuarioCrea;
	public static volatile SingularAttribute<CmHistoricoFacturas, String> terminalCrea;
	public static volatile SingularAttribute<CmHistoricoFacturas, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmHistoricoFacturas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmHistoricoFacturas, Integer> id;
	public static volatile SingularAttribute<CmHistoricoFacturas, Integer> tipos;
	public static volatile SingularAttribute<CmHistoricoFacturas, String> cmHistoricoFacturas;

	public static final String DESCRIPCION = "descripcion";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String TIPOS = "tipos";
	public static final String CM_HISTORICO_FACTURAS = "cmHistoricoFacturas";

}

