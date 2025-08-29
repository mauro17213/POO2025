package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InfInformeGenerados.class)
public abstract class InfInformeGenerados_ {

	public static volatile SingularAttribute<InfInformeGenerados, String> descripcion;
	public static volatile SingularAttribute<InfInformeGenerados, String> nombreArchivo;
	public static volatile SingularAttribute<InfInformeGenerados, Integer> estado;
	public static volatile ListAttribute<InfInformeGenerados, InfInformeDescargados> infInformeDescargadosList;
	public static volatile SingularAttribute<InfInformeGenerados, String> archivo;
	public static volatile SingularAttribute<InfInformeGenerados, String> ruta;
	public static volatile SingularAttribute<InfInformeGenerados, Date> fechaHoraInicio;
	public static volatile SingularAttribute<InfInformeGenerados, Boolean> existe;
	public static volatile SingularAttribute<InfInformeGenerados, String> nombre;
	public static volatile SingularAttribute<InfInformeGenerados, String> usuarioCrea;
	public static volatile ListAttribute<InfInformeGenerados, InfInformeValores> infInformeValoresList;
	public static volatile SingularAttribute<InfInformeGenerados, Integer> tiempo;
	public static volatile SingularAttribute<InfInformeGenerados, String> terminalCrea;
	public static volatile SingularAttribute<InfInformeGenerados, Boolean> plantilla;
	public static volatile SingularAttribute<InfInformeGenerados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<InfInformeGenerados, Integer> id;
	public static volatile SingularAttribute<InfInformeGenerados, InfInformes> infInformesId;
	public static volatile SingularAttribute<InfInformeGenerados, Date> fechaHoraFin;
	public static volatile SingularAttribute<InfInformeGenerados, Integer> gnEmpresasId;

	public static final String DESCRIPCION = "descripcion";
	public static final String NOMBRE_ARCHIVO = "nombreArchivo";
	public static final String ESTADO = "estado";
	public static final String INF_INFORME_DESCARGADOS_LIST = "infInformeDescargadosList";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String EXISTE = "existe";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String INF_INFORME_VALORES_LIST = "infInformeValoresList";
	public static final String TIEMPO = "tiempo";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String PLANTILLA = "plantilla";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String INF_INFORMES_ID = "infInformesId";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";

}

