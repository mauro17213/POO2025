package com.saviasaludeps.savia.ejb.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CmRipsCargaAdServiciosAgrupados.class)
public abstract class CmRipsCargaAdServiciosAgrupados_ {

	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> conceptoRips;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> archivoRuta;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> numeroFactura;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> archivoControl;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> archivoNombreOriginal;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, CmRipsCargas> cmRipsCargasId;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> cantidadServicios;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> valorUnitario;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> valorConcepto;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> usuarioCrea;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> codigoReps;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> archivoNombre;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, String> terminalCrea;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, Integer> fila;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, Date> fechaHoraCrea;
	public static volatile SingularAttribute<CmRipsCargaAdServiciosAgrupados, Integer> id;

	public static final String CONCEPTO_RIPS = "conceptoRips";
	public static final String ARCHIVO_RUTA = "archivoRuta";
	public static final String NUMERO_FACTURA = "numeroFactura";
	public static final String ARCHIVO_CONTROL = "archivoControl";
	public static final String ARCHIVO_NOMBRE_ORIGINAL = "archivoNombreOriginal";
	public static final String CM_RIPS_CARGAS_ID = "cmRipsCargasId";
	public static final String CANTIDAD_SERVICIOS = "cantidadServicios";
	public static final String VALOR_UNITARIO = "valorUnitario";
	public static final String VALOR_CONCEPTO = "valorConcepto";
	public static final String USUARIO_CREA = "usuarioCrea";
	public static final String CODIGO_REPS = "codigoReps";
	public static final String ARCHIVO_NOMBRE = "archivoNombre";
	public static final String TERMINAL_CREA = "terminalCrea";
	public static final String FILA = "fila";
	public static final String FECHA_HORA_CREA = "fechaHoraCrea";
	public static final String ID = "id";

}

