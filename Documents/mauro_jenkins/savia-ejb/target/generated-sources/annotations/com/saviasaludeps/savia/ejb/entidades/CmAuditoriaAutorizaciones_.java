package com.saviasaludeps.savia.ejb.entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmAuditoriaAutorizaciones.class)
public abstract class CmAuditoriaAutorizaciones_ {

	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, String> numeroAutorizacion;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, String> codigoServicio;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, CmDetalles> cmDetallesId;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, Boolean> activa;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, String> nombreServicio;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, String> terminalModifica;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, AuAnexos4> auAnexos4Id;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, String> usuarioCrea;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, BigDecimal> valorAutorizacion;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, String> terminalCrea;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, CmFacturas> cmFacturasId;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, Date> fechaHoraModifica;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, Integer> id;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, Integer> cantidad;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, String> nombrePrestador;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, Date> fechaAutorizacion;
	public static volatile SingularAttribute<CmAuditoriaAutorizaciones, String> usuarioModifica;

	public static final String NUMERO_AUTORIZACION = "numeroAutorizacion";
	public static final String CODIGO_SERVICIO = "codigoServicio";
	public static final String CM_DETALLES_ID = "cmDetallesId";
	public static final String ACTIVA = "activa";
	public static final String NOMBRE_SERVICIO = "nombreServicio";
	public static final String TERMINAL_MODIFICA = "terminalModifica";
	public static final String AU_ANEXOS4_ID = "auAnexos4Id";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String VALOR_AUTORIZACION = "valorAutorizacion";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String CM_FACTURAS_ID = "cmFacturasId";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String FECHA_HORA_MODIFICA = "fechaHoraModifica";
	public static final String ID = "id";
	public static final String CANTIDAD = "cantidad";
	public static final String NOMBRE_PRESTADOR = "nombrePrestador";
	public static final String FECHA_AUTORIZACION = "fechaAutorizacion";
	public static final String USUARIO_MODIFICA = "usuarioModifica";

}

