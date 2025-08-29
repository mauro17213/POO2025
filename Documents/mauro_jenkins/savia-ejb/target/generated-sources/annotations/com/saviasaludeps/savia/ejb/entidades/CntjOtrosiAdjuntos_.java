package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjOtrosiAdjuntos.class)
public abstract class CntjOtrosiAdjuntos_ {

	public static volatile SingularAttribute<CntjOtrosiAdjuntos, String> maeTipoArchivoValor;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, Integer> maeTipoArchivoId;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, String> archivo;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, String> ruta;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, String> maeTipoArchivoCodigo;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, Integer> id;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, Boolean> existe;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, String> nombre;
	public static volatile SingularAttribute<CntjOtrosiAdjuntos, CntjOtrosies> cntjOtrosiesId;

	public static final String MAE_TIPO_ARCHIVO_VALOR = "maeTipoArchivoValor";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_ARCHIVO_ID = "maeTipoArchivoId";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_ARCHIVO_CODIGO = "maeTipoArchivoCodigo";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String EXISTE = "existe";
	public static final String NOMBRE = "nombre";
	public static final String CNTJ_OTROSIES_ID = "cntjOtrosiesId";

}

