package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntjContratoInformeAdjuntos.class)
public abstract class CntjContratoInformeAdjuntos_ {

	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, CntjContratoInformes> cntjContratoInformesId;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, String> maeTipoArchivoValor;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, String> usuarioCrea;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, Integer> maeTipoArchivoId;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, String> archivo;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, String> ruta;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, String> terminalCrea;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, String> maeTipoArchivoCodigo;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, Integer> id;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, Boolean> existe;
	public static volatile SingularAttribute<CntjContratoInformeAdjuntos, String> nombre;

	public static final String CNTJ_CONTRATO_INFORMES_ID = "cntjContratoInformesId";
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

}

