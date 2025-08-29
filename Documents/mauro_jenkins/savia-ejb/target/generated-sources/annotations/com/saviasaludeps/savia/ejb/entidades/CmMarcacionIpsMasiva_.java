package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmMarcacionIpsMasiva.class)
public abstract class CmMarcacionIpsMasiva_ {

	public static volatile SingularAttribute<CmMarcacionIpsMasiva, Integer> exitosos;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, Integer> estado;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, String> archivo;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, String> ruta;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, Integer> registros;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, Date> fechaHoraInicio;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, String> nombre;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, String> usuarioCrea;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, String> terminalCrea;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, Integer> fallidos;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, Integer> id;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, Date> fechaHoraFin;
	public static volatile SingularAttribute<CmMarcacionIpsMasiva, GnEmpresas> gnEmpresasId;

	public static final String EXITOSOS = "exitosos";
	public static final String ESTADO = "estado";
	public static final String ARCHIVO = "archivo";
	public static final String RUTA = "ruta";
	public static final String REGISTROS = "registros";
	public static final String FECHA_HORA_INICIO = "fechaHoraInicio";
	public static final String NOMBRE = "nombre";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FALLIDOS = "fallidos";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";
	public static final String FECHA_HORA_FIN = "fechaHoraFin";
	public static final String GN_EMPRESAS_ID = "gnEmpresasId";

}

