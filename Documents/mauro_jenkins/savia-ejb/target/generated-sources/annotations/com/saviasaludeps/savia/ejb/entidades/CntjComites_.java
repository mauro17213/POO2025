package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjComites.class)
public abstract class CntjComites_ {

	public static volatile ListAttribute<CntjComites, CntjComiteAsistentes> cntjComiteAsistentesList;
	public static volatile SingularAttribute<CntjComites, Short> estado;
	public static volatile SingularAttribute<CntjComites, Date> fechaProgramacion;
	public static volatile SingularAttribute<CntjComites, Date> fechaHoraInicio;
	public static volatile SingularAttribute<CntjComites, String> adjuntoArchivo;
	public static volatile SingularAttribute<CntjComites, String> adjuntoRuta;
	public static volatile SingularAttribute<CntjComites, String> terminalModifica;
	public static volatile SingularAttribute<CntjComites, Boolean> adjuntoExiste;
	public static volatile SingularAttribute<CntjComites, String> usuarioCrea;
	public static volatile ListAttribute<CntjComites, CntjLineas> cntjLineasList;
	public static volatile SingularAttribute<CntjComites, String> terminalCrea;
	public static volatile SingularAttribute<CntjComites, String> observaciones;
	public static volatile SingularAttribute<CntjComites, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjComites, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CntjComites, Integer> id;
	public static volatile SingularAttribute<CntjComites, String> adjuntoNombre;
	public static volatile SingularAttribute<CntjComites, Date> fechaHoraFin;
	public static volatile SingularAttribute<CntjComites, String> conclusiones;
	public static volatile SingularAttribute<CntjComites, String> usuarioModifica;

	public static final String CNTJ_COMITE_ASISTENTES_LIST = "cntjComiteAsistentesList";
	public static final String ESTADO = "estado";
	public static final String FECHA_PROGRAMACION = "fechaProgramacion";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String ADJUNTO_ARCHIVO = "adjuntoArchivo";
	public static final String ADJUNTO_RUTA = "adjuntoRuta";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String ADJUNTO_EXISTE = "adjuntoExiste";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNTJ_LINEAS_LIST = "cntjLineasList";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String OBSERVACIONES = "observaciones";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String ADJUNTO_NOMBRE = "adjuntoNombre";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String CONCLUSIONES = "conclusiones";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

