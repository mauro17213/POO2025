package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CntPrestadorContactos.class)
public abstract class CntPrestadorContactos_ {

	public static volatile SingularAttribute<CntPrestadorContactos, String> contacto;
	public static volatile SingularAttribute<CntPrestadorContactos, String> maeAreaContactoCodigo;
	public static volatile SingularAttribute<CntPrestadorContactos, Boolean> autorizaEnvio;
	public static volatile SingularAttribute<CntPrestadorContactos, String> maeAreaContactoValor;
	public static volatile SingularAttribute<CntPrestadorContactos, String> maeTipoContactoCodigo;
	public static volatile SingularAttribute<CntPrestadorContactos, Integer> maeAreaContactoId;
	public static volatile SingularAttribute<CntPrestadorContactos, CntPrestadorSedes> cntPrestadorSedesId;
	public static volatile SingularAttribute<CntPrestadorContactos, String> usuarioCrea;
	public static volatile SingularAttribute<CntPrestadorContactos, CntPrestadores> cntPrestadoresId;
	public static volatile SingularAttribute<CntPrestadorContactos, String> maeTipoContactoValor;
	public static volatile SingularAttribute<CntPrestadorContactos, String> terminalCrea;
	public static volatile SingularAttribute<CntPrestadorContactos, Integer> maeTipoContactoId;
	public static volatile SingularAttribute<CntPrestadorContactos, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CntPrestadorContactos, Integer> id;
	public static volatile SingularAttribute<CntPrestadorContactos, String> observacion;
	public static volatile SingularAttribute<CntPrestadorContactos, Boolean> activo;

	public static final String CONTACTO = "contacto";
	public static final String MAE_AREA_CONTACTO_CODIGO = "maeAreaContactoCodigo";
	public static final String AUTORIZA_ENVIO = "autorizaEnvio";
	public static final String MAE_AREA_CONTACTO_VALOR = "maeAreaContactoValor";
	public static final String MAE_TIPO_CONTACTO_CODIGO = "maeTipoContactoCodigo";
	public static final String MAE_AREA_CONTACTO_ID = "maeAreaContactoId";
	public static final String CNT_PRESTADOR_SEDES_ID = "cntPrestadorSedesId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CNT_PRESTADORES_ID = "cntPrestadoresId";
	public static final String MAE_TIPO_CONTACTO_VALOR = "maeTipoContactoValor";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String MAE_TIPO_CONTACTO_ID = "maeTipoContactoId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String OBSERVACION = "observacion";
	public static final String ACTIVO = "activo";

}

