package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsSucesos.class)
public abstract class CmRipsSucesos_ {

	public static volatile SingularAttribute<CmRipsSucesos, Integer> tipoRegla;
	public static volatile SingularAttribute<CmRipsSucesos, Integer> alerta;
	public static volatile SingularAttribute<CmRipsSucesos, String> descripcionMensaje;
	public static volatile SingularAttribute<CmRipsSucesos, Integer> cmRipsReglasMensajesId;
	public static volatile SingularAttribute<CmRipsSucesos, String> nombreRegla;
	public static volatile SingularAttribute<CmRipsSucesos, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsSucesos, Integer> archivoFila;
	public static volatile SingularAttribute<CmRipsSucesos, Integer> cmRipsReglasId;
	public static volatile SingularAttribute<CmRipsSucesos, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsSucesos, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsSucesos, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsSucesos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsSucesos, Integer> id;

	public static final String TIPO_REGLA = "tipoRegla";
	public static final String ALERTA = "alerta";
	public static final String DESCRIPCION_MENSAJE = "descripcionMensaje";
	public static final String CM_RIPS_REGLAS_MENSAJES_ID = "cmRipsReglasMensajesId";
	public static final String NOMBRE_REGLA = "nombreRegla";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String ARCHIVO_FILA = "archivoFila";
	public static final String CM_RIPS_REGLAS_ID = "cmRipsReglasId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

