package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MpcPrescripcionAdjuntos.class)
public abstract class MpcPrescripcionAdjuntos_ {

	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, String> nombreArchivo;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, Short> tipo;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, String> archivo;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, String> ruta;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, Boolean> existe;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, MpcPrescripciones> mpcPrescripcionesId;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, String> terminalModifica;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, Date> fechaHoraModifica;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, Integer> id;
	public static volatile SingularAttribute<MpcPrescripcionAdjuntos, String> usuarioModifica;

	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String TIPO = "tipo";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String EXISTE = "existe";
	public static final String MPC_PRESCRIPCIONES_ID = "mpcPrescripcionesId";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

