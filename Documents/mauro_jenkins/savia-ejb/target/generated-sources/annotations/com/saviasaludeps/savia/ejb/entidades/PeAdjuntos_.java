package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeAdjuntos.class)
public abstract class PeAdjuntos_ {

	public static volatile SingularAttribute<PeAdjuntos, String> maeTipoArchivoValor;
	public static volatile SingularAttribute<PeAdjuntos, Integer> maeTipoArchivoId;
	public static volatile SingularAttribute<PeAdjuntos, String> archivo;
	public static volatile SingularAttribute<PeAdjuntos, String> ruta;
	public static volatile SingularAttribute<PeAdjuntos, Boolean> existe;
	public static volatile SingularAttribute<PeAdjuntos, String> nombre;
	public static volatile SingularAttribute<PeAdjuntos, PeGestiones> peGestionesId;
	public static volatile SingularAttribute<PeAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<PeAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<PeAdjuntos, String> maeTipoArchivoCodigo;
	public static volatile SingularAttribute<PeAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<PeAdjuntos, PeAfiliadosProgramas> peAfiliadosId;
	public static volatile SingularAttribute<PeAdjuntos, Integer> id;
	public static volatile SingularAttribute<PeAdjuntos, String> observacion;

	public static final String MAE_TIPO_ARCHIVO_VALOR = "maeTipoArchivoValor";
	public static final String MAE_TIPO_ARCHIVO_ID = "maeTipoArchivoId";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String EXISTE = "existe";
	public static final String NOMBRE = "nombre";
	public static final String PE_GESTIONES_ID = "peGestionesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_ARCHIVO_CODIGO = "maeTipoArchivoCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String PE_AFILIADOS_ID = "peAfiliadosId";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";

}

