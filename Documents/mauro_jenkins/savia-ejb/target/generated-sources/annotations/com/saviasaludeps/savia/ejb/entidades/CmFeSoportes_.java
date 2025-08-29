package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmFeSoportes.class)
public abstract class CmFeSoportes_ {

	public static volatile SingularAttribute<CmFeSoportes, String> maeTipoSoporteCodigo;
	public static volatile SingularAttribute<CmFeSoportes, String> archivo;
	public static volatile SingularAttribute<CmFeSoportes, String> archivoRuta;
	public static volatile SingularAttribute<CmFeSoportes, Integer> maeTipoSoporteId;
	public static volatile SingularAttribute<CmFeSoportes, CmFeRipsCargas> cmFeRipsCargasId;
	public static volatile SingularAttribute<CmFeSoportes, String> usuarioCrea;
	public static volatile SingularAttribute<CmFeSoportes, String> maeTipoSoporteValor;
	public static volatile SingularAttribute<CmFeSoportes, String> archivoNombre;
	public static volatile SingularAttribute<CmFeSoportes, String> terminalCrea;
	public static volatile SingularAttribute<CmFeSoportes, Boolean> archivoExiste;
	public static volatile SingularAttribute<CmFeSoportes, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmFeSoportes, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmFeSoportes, Integer> id;
	public static volatile SingularAttribute<CmFeSoportes, GnEmpresas> gnEmpresasId;

	public static final String MAE_TIPO_SOPORTE_CODIGO = "maeTipoSoporteCodigo";
	public static final String ARCHIVO = "archivo";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String MAE_TIPO_SOPORTE_ID = "maeTipoSoporteId";
	public static final String CM_FE_RIPS_CARGAS_ID = "cmFeRipsCargasId";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String MAE_TIPO_SOPORTE_VALOR = "maeTipoSoporteValor";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String ARCHIVO_EXISTE = "archivoExiste";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";

}

