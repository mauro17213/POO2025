package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatTiketeLlamados.class)
public abstract class GatTiketeLlamados_ {

	public static volatile SingularAttribute<GatTiketeLlamados, Short> estado;
	public static volatile SingularAttribute<GatTiketeLlamados, String> usuarioCrea;
	public static volatile SingularAttribute<GatTiketeLlamados, String> terminalCrea;
	public static volatile SingularAttribute<GatTiketeLlamados, GatTiquetes> gatTiquetesId;
	public static volatile SingularAttribute<GatTiketeLlamados, Integer> maximo;
	public static volatile SingularAttribute<GatTiketeLlamados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatTiketeLlamados, Integer> id;
	public static volatile SingularAttribute<GatTiketeLlamados, Integer> cantidad;
	public static volatile SingularAttribute<GatTiketeLlamados, GatSedeTaquillas> gatSedeTaquillasId;
	public static volatile SingularAttribute<GatTiketeLlamados, GnUsuarios> gnUsuariosId;

	public static final String ESTADO = "estado";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String GAT_TIQUETES_ID = "gatTiquetesId";
	public static final String MAXIMO = "maximo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String CANTIDAD = "cantidad";
	public static final String GAT_SEDE_TAQUILLAS_ID = "gatSedeTaquillasId";
	public static final String GN_USUARIOS_ID = "gnUsuariosId";

}

