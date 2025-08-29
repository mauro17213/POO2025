package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GatAtencionComentarios.class)
public abstract class GatAtencionComentarios_ {

	public static volatile SingularAttribute<GatAtencionComentarios, String> usuarioCrea;
	public static volatile SingularAttribute<GatAtencionComentarios, GatAtenciones> gatAtencionesId;
	public static volatile SingularAttribute<GatAtencionComentarios, String> terminalCrea;
	public static volatile SingularAttribute<GatAtencionComentarios, Date> fechaHoraCrea;
	public static volatile SingularAttribute<GatAtencionComentarios, Integer> id;
	public static volatile SingularAttribute<GatAtencionComentarios, String> comentario;

	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String GAT_ATENCIONES_ID = "gatAtencionesId";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String COMENTARIO = "comentario";

}

