package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFeRipsFacturas.class)
public abstract class CmFeRipsFacturas_ {

	public static volatile SingularAttribute<CmFeRipsFacturas, String> usuarioCrea;
	public static volatile SingularAttribute<CmFeRipsFacturas, String> terminalCrea;
	public static volatile ListAttribute<CmFeRipsFacturas, CmFeNotas> cmFeNotasList;
	public static volatile SingularAttribute<CmFeRipsFacturas, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmFeRipsFacturas, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmFeRipsFacturas, Integer> id;
	public static volatile SingularAttribute<CmFeRipsFacturas, CmFeRipsCargas> cmFeRipsCargasId;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_FE_NOTAS_LIST = "cmFeNotasList";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CM_FE_RIPS_CARGAS_ID = "cmFeRipsCargasId";

}

